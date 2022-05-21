package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.bo.Condition;
import com.ljm.bo.ConditionNode;
import com.ljm.bo.ParamData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        // 过滤条件
        if (apiResult.getConditionNode() != null){
            Criteria criteria = new Criteria();
            //getCriteria(apiResult, data);
            operations.add(Aggregation.match(criteria));
        }

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
                                    Criteria criteria){
        if(conditionNode.getNodeType() == ConditionNode.ROOT_NODE){

        }else if(conditionNode.getNodeType() == ConditionNode.AND_NODE){

        }else if(conditionNode.getNodeType() == ConditionNode.OR_NODE){

        }else{
            // 叶子节点 #{dept_id} in dept.id
            String conditionVal = conditionNode.getValue();
            String fieldName = "";
            //criteria.and("filed").is();
        }
    }

    /**
     * @description TODO 转换出Condition
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/21 16:28
     **/
    private Condition getCondition(String condition, Map<String, ParamData> params,
                                   JSONObject data){
        // 分析condition，获取字段名称、操作符号、值等信息 #{dept_id} = dept.id
        return null;
    }
}
