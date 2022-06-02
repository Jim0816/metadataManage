package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.bo.ConditionNode;
import com.ljm.utils.MongoDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    private MongoDBUtil mongoDBUtil;

    public AddExecutor(MongoDBUtil mongoDBUtil){
        this.mongoDBUtil = mongoDBUtil;
    }

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



}
