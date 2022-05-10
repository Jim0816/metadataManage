package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.bo.ConditionNode;
import com.ljm.bo.mongo.FilterModel;
import com.ljm.bo.mongo.QueryModel;
import com.ljm.utils.MongoDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName MongoHelper
 * @Description
 * @Author Jim
 * @Date 2022/4/19 19:35
 **/
@Slf4j
public class MongoHelper {
    @Autowired
    private MongoDBUtil mongoDBUtil;
    /**
     * @description TODO 执行添加操作
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/19 19:36
     **/
    public int add(ApiResult apiResult, JSONObject data){
        ConditionNode conditionNode = apiResult.getConditionNode();
        if (conditionNode != null){
            // 先执行条件 遍历conditionNode
            if(!judgeCondition(conditionNode, data)){
                return 0;
            }
        }
        // 条件执行成功，插入
        String collName = apiResult.getApi().getModel();
        mongoDBUtil.insert(data, collName);
        return 0;
    }

    /**
     * @description TODO 判断条件是否正确
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/20 15:25
     **/
    private boolean judgeCondition(ConditionNode conditionNode,JSONObject data){
        // 获取当前结点
        Condition condition = conditionNode.getCondition();
        // 获取孩子结点
        List<ConditionNode> children = conditionNode.getChildren();
        // 孩子为空
        if(children == null || children.size() == 0){
            return false;
        }
        // 判断当前结点类型
        Integer type = condition.getType();
        if(type == ConditionNode.ROOT_NODE){
            // 遍历孩子结点
            for(ConditionNode rootChild : children){
                return judgeCondition(rootChild,data);
            }
            return false;
        }else if(type == ConditionNode.AND_NODE){// and条件

            //默认and为true,只要孩子有一个结果为false,就可保证返回结果为false
            boolean and = true;
            //遍历and条件的孩子
            for(ConditionNode andChild : children){
                and = and && judgeCondition(andChild,data);
            }
            // 返回and条件的孩子进行and操作之后的布尔值
            return and;
        }else if (type == ConditionNode.OR_NODE){ // or条件
            //默认or为false,只要孩子有一个结果为true，就可保证返回结果为true
            boolean or = false;
            // 遍历孩子结点
            for(ConditionNode orChild : children){
                or = or || judgeCondition(orChild,data);
            }
            return or;
        }else{
            //解析value_node结点,获取运算条件字符串
            String value = condition.getValue();
            LinkedHashMap<String, String[]> operatorMap = parseString(value);

            QueryModel queryModel = new QueryModel("", "*", 1, 10);
            List<FilterModel> list = new ArrayList();

            for(String operator : operatorMap.keySet()){
                // 操作符 operator,
                // 拿到操作符两边的数据
                String[] conditions = operatorMap.get(operator);
                String valueType = null;

                if(conditions[1].contains("\"")){// 不需要查询mongoDB就可以进行判断的 包含""则说明是字符串
                    //获取""中的字符串
                    String val = conditions[1].substring(conditions[1].indexOf("\"") + 1, conditions[1].lastIndexOf("\""));
                    //  与传递过来的信息进行比较,字符串暂定是比较相等 eg name = "jim"
                    return  data.get(conditions[0]).equals(val);

                }else{

                    //dept.id  key:id
                    String key = conditions[1].split(",")[1];
                    Object v = data.get(conditions[0].split(".")[1].trim());
                    if(v instanceof String){
                        valueType = "string";
                    }else if(v instanceof  Integer){
                        valueType = "int";
                    }
                    FilterModel filterModel = new FilterModel(key,(String) v,valueType,operator,"and");
                    list.add(filterModel);
                    queryModel.setFilter(list);

                    List<String> res = mongoDBUtil.query(queryModel, String.class);
                    log.info("res:"+res.toString());
                    if(res == null && res.size() <= 0 ){
                        return false;
                    }
                    return true;
                }

            }
            return false;
        }
//        return false;
    }

    /**
     * @description TODO 解析字符串
     * @return LinkedHashMap<String,String[]> String存放操作符，String[]存放操作符两边的字符串
     * @exception
     * @author zyt
     * @date 2022/4/20 21:35
     **/
    private LinkedHashMap<String,String[]> parseString(String value){
        // 可能出现的操作符，也有可能出现in,那这个解法不是太对
        // 可以找到}的位置，然后找到这个中找到。
        // #{user.dept_id} in dept.id
        String[] reg = {"=",">","<","!=",">=","<=","in"};
        for(int i = 0 ;i < reg.length;i++){
            String operator = reg[i];
            if(value.indexOf(reg[i]) != -1){
                LinkedHashMap<String,String[]> map = new LinkedHashMap<>();
                String[] split = value.split(reg[i]);
                // 将运算符前面的#{}去掉
                split[0] = split[0].substring(split[0].indexOf("{"), split[0].indexOf("}"));
                map.put(operator,split);//根据运算符分割字符，一般被分割为两个;
                return map;
            }
        }
        return null;
    }

    /**
     * @description TODO 执行修改
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/19 19:38
     **/
    public int update(ApiResult apiResult){
        return 0;
    }

    /**
     * @description TODO 执行删除操作
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/19 19:38
     **/
    public int remove(ApiResult apiResult){
        return 0;
    }

    /**
     * @description TODO 执行查询操作
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/19 19:40
     **/
    public <T> List<T> get(ApiResult apiResult){
        return null;
    }
}
