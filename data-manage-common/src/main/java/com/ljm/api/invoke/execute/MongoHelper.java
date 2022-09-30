package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.bo.Condition;
import com.ljm.bo.ConditionNode;
import com.ljm.bo.ParamData;
import com.ljm.entity.FieldInfo;
import com.ljm.enums.ResCodeEnum;
import com.ljm.except.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.*;

/**
 * @ClassName MongoHelper
 * @Description
 * @Author Jim
 * @Date 2022/4/19 19:35
 **/
@Slf4j
public class MongoHelper {

    public static Aggregation createAggregation(ApiResult apiResult, JSONObject data){
        List<AggregationOperation> operations = new ArrayList<>();
        // 判断是否多表联合查询 TODO

        // 过滤条件 TODO 存在bug
        if (apiResult.getConditionNode() != null){
            ConditionNode conditionNode = apiResult.getConditionNode();
            Criteria rootCriteria = new Criteria();
            getCriteria(apiResult, data, conditionNode, rootCriteria);
            operations.add(Aggregation.match(rootCriteria));
        }

        //Criteria criteria = new Criteria().and("name").is("张三");
        //operations.add(Aggregation.match(criteria));


        // 排序 TODO

        // 分页 TODO

        Aggregation aggregation = Aggregation.newAggregation(operations);
        return aggregation;
    }

    /**
     * @description TODO 注入查询条件
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/21 15:40
     **/
    private static void getCriteria(ApiResult apiResult,
                                    JSONObject data,
                                    ConditionNode conditionNode,
                                    Criteria parentCriteria){
        Criteria criteria = parentCriteria;
        int parentType = conditionNode.getNodeType();

        if (parentType == ConditionNode.VALUE_NODE){
            // 叶子节点 "name = #{name}"
            Condition condition = getCondition(conditionNode.getValue(), apiResult.getParams(), data);
            // 设置criteria
            criteria.andOperator(ConditionToCriteria(condition));
        }else {
            // 非叶子节点：根节点、and节点、or节点
            if (parentType == ConditionNode.AND_NODE){
                // and节点
                criteria = new Criteria();
                parentCriteria.andOperator(criteria);
            }else if (parentType == ConditionNode.OR_NODE){
                // or节点
                criteria = new Criteria();
                parentCriteria.orOperator(criteria);
            }else if (parentType == ConditionNode.ROOT_NODE){
                // root节点
            }

            List<ConditionNode> childList = conditionNode.getChildren();
            for (ConditionNode child : childList){
                getCriteria(apiResult, data, child, criteria);
            }

        }



    }

    /**
     * @description TODO Condition 转换为 Criteria
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/23 下午2:31
     **/
    private static Criteria ConditionToCriteria(Condition condition){
        Criteria criteria = new Criteria().and(condition.getFieldName());
        String opType = condition.getOperate();
        switch (opType){
            case ">":
                criteria.gt(condition.getValue());
                break;
            case ">=":
                criteria.gte(condition.getValue());
                break;
            case "<":
                criteria.lt(condition.getValue());
                break;
            case "<=":
                criteria.lte(condition.getValue());
                break;
            case "=":
                criteria.is(condition.getValue());
                break;
            case "!=":
                criteria.ne(condition.getValue());
                break;
            default:
                break;
        }
        return criteria;
    }

    /**
     * @description TODO 解析每一个条件，转换出Condition
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/21 16:28
     **/
    private static Condition getCondition(String condition, Map<String, ParamData> params,
                                   JSONObject data){
        // 分析condition，获取字段名称、操作符号、值等信息 condition "name = #{name}"
        // condition 由三部分组成 参数名称 操作符号 变量
        Set<String> set = Condition.operateSet;
        // 寻找操作符在字符串中位置 [start, end]
        int start = 0, end = 0;
        for (String op : set){
            // 找到操作符
            if ((start = condition.indexOf(op)) != -1){
                end = start + op.length() - 1;
                break;
            }
        }
        // 没有找到操作符号
        if (start == 0 || start == -1){
            throw new CustomException(ResCodeEnum.CONDITION_ERROR);
        }

        String filedName = condition.substring(0, start).trim();
        String operator = condition.substring(start, end + 1).trim();
        String var = condition.substring(end + 1, condition.length()).trim(); // #{name}
        // 从数据中获取变量值
        Object value = getParamVarValue(var, params, data);
        Condition conditionObj = new Condition();
        conditionObj.setFieldName(filedName);
        conditionObj.setOperate(operator);
        conditionObj.setValue(value);
        return conditionObj;
    }

    /**
     * @description TODO 从数据中提取变量的值
     * @param paramVar : "#{name}"
     * @param data : json数据
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/23 下午1:11
     **/
    private static Object getParamVarValue(String paramVar, Map<String, ParamData> params,
                                    JSONObject data){
        int start = paramVar.indexOf("{");
        int end = paramVar.indexOf("}");

        if (start == -1 || end == -1){
            throw new CustomException(ResCodeEnum.CONDITION_ERROR);
        }

        String paramName = paramVar.substring(start + 1, end);
        ParamData paramData = params.get(paramName);
        // 数据值
        String val = data.get(paramName).toString();
        Object value = null;

        // 数据类型转换
        if (ParamData.PARAM_FIELD_TYPE.equals(paramData.getType())){
            // 当前参数为
            FieldInfo fieldInfo = (FieldInfo) paramData.getValue();
            String type = fieldInfo.getFieldType();
            switch (type){
                case "string":
                    value = val;
                    break;
                case "int":
                    value = Integer.valueOf(val);
                    break;
                default:
                    break;
            }
        }else{
            // 当前参数为model TODO 暂时用不到
        }
        return value;
    }
}
