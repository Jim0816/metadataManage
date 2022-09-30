package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.utils.MongoDBUtil;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GetExecutor
 * @Description
 * @Author Jim
 * @Date 2022/5/21 13:04
 **/
public class GetExecutor implements ExecuteStrategy{
    private MongoDBUtil mongoDBUtil;

    public GetExecutor(MongoDBUtil mongoDBUtil){
        this.mongoDBUtil = mongoDBUtil;
    }

    @Override
    public Object execute(JSONObject data, ApiResult apiResult) {
        Aggregation aggregation = MongoHelper.createAggregation(apiResult, data);
        List<Map> result = mongoDBUtil.query(aggregation, apiResult.getApi().getModel());
        return result;
    }
}
