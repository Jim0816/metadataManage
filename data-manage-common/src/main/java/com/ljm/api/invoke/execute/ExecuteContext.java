package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.enums.ApiLabelEnum;
import com.ljm.utils.MongoDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExecuteContext
 * @Description
 * @Author Jim
 * @Date 2022/5/21 12:47
 **/
@Component
public class ExecuteContext {
    @Autowired
    private MongoDBUtil mongoDBUtil;

    private ExecuteStrategy executeStrategy;

    public void setExecuteStrategy(ApiResult apiResult){
        String operation = apiResult.getApi().getOpType();
        if (ApiLabelEnum.API_TYPE_ADD.getValue().equals(operation)){
            // 添加类型接口操作
            this.executeStrategy = new AddExecutor(mongoDBUtil);
        }else if (ApiLabelEnum.API_TYPE_UPDATE.getValue().equals(operation)){
            // 修改类型接口操作
            this.executeStrategy = new UpdateExecutor();
        }else if (ApiLabelEnum.API_TYPE_DELETE.getValue().equals(operation)){
            // 删除类型接口操作
            this.executeStrategy = new DeleteExecutor();
        }else if (ApiLabelEnum.API_TYPE_GET.getValue().equals(operation)){
            // 查询类型接口操作
            this.executeStrategy = new GetExecutor(mongoDBUtil);
        }
    }


    public <T> T execute(JSONObject data, ApiResult apiResult){
        return this.executeStrategy.execute(data, apiResult);
    }
}
