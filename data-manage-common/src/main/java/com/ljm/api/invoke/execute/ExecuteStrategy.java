package com.ljm.api.invoke.execute;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;

/**
 * @description TODO 执行mongo策略
 * @return
 * @exception
 * @author Jim
 * @date 2022/5/21 12:48
 **/
public interface ExecuteStrategy {
    /**
     * @description TODO 执行mongo
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/21 12:50
     **/
    public <T> T execute(JSONObject data, ApiResult apiResult);
}
