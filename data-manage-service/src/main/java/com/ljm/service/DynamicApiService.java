package com.ljm.service;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ApiResult;
import com.ljm.bo.ParamData;
import com.ljm.entity.Api;

import java.util.List;

public interface DynamicApiService {

    /**
     * @description TODO 解析API对象，获取解析结果
     * @return
     * @exception
     * @author gcy
     * @date 2022/2/25 12:10
     **/
    ApiResult parseApi(Api api);

    /**
     * @description TODO 校验接口收到的数据或者参数
     * @param data 接口中传输的数据
     * @param
     * @return
     * @exception
     * @author Jim
     * @date 2022/3/29 20:08
     **/
    boolean checkApiData(JSONObject data, List<ParamData> rules);
}
