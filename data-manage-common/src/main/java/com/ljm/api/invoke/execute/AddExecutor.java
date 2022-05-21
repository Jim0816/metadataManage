package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.bo.ConditionNode;
import com.ljm.utils.MongoDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AddExecutor
 * @Description
 * @Author Jim
 * @Date 2022/5/21 12:54
 **/
@Slf4j
public class AddExecutor implements ExecuteStrategy{
    @Autowired
    private MongoDBUtil mongoDBUtil;

    @Override
    public Integer execute(JSONObject data, ApiResult apiResult) {
        ConditionNode conditionNode = apiResult.getConditionNode();
        // TODO 主外键约束
        /*if (conditionNode != null){
            // 先执行条件 遍历conditionNode
            injectData(conditionNode, data);
        }*/
        // 条件执行成功，插入mongo
        String collName = apiResult.getApi().getModel();

        // TODO 插入前需要判断该集合是否存在 保证mysql、mongo一致性
        mongoDBUtil.insert(data, collName);
        return 1;
    }

    /**
     * @description TODO 注入数据到 Condition
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/21 13:49
     **/
    private void injectData(ConditionNode conditionNode, JSONObject data){
        if (conditionNode.getNodeType() == ConditionNode.VALUE_NODE){
            // 叶子节点 如 #{dept_id} in dept.id
            replaceParamVar(conditionNode.getValue(), data);
        }else {
            List<ConditionNode> childes = conditionNode.getChildren();
            for (ConditionNode child : childes){
                injectData(child, data);
            }
        }
    }

    private String replaceParamVar(String conditionValue, JSONObject data){
        Map<String, Object> map = new HashMap<>();
        int start = -1;
        for (int i = 0 ; i < conditionValue.length() ; i ++){
            char c = conditionValue.charAt(i);
            if ('#' == c){
                start = i;
            }else if('}' == c){
                // 首部已经找到
                if (start != -1){
                    String key = conditionValue.substring(start, i + 1);
                    String paramName = conditionValue.substring(key.indexOf('{') + 1, key.indexOf('}'));
                    map.put(key, data.get(paramName));
                    start = -1;
                }
            }
        }

        for (String key : map.keySet()){
            String newKey = key.replace("{", "\\{")
                    .replace("}", "\\}");
            conditionValue = conditionValue.replaceAll(newKey, map.get(key).toString());
        }
        return conditionValue;
    }
}
