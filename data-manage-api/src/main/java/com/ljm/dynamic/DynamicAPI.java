package com.ljm.dynamic;

import com.alibaba.fastjson.JSONObject;
import com.ljm.api.invoke.execute.ExecuteContext;
import com.ljm.api.invoke.parse.APIParser;
import com.ljm.bo.ApiResult;
import com.ljm.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DynamicAPI
 * @Description
 * @Author Jim
 * @Date 2022/10/2 下午2:55
 **/
@Slf4j
@Service
public class DynamicAPI {

    @Autowired
    private ExecuteContext executeContext;

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    public Result handle(@RequestBody String data) {
        String uri = request.getRequestURI();
        log.info("======================= 正在访问接口：{} =======================", uri);

        // 接口请求参数格式化
        JSONObject acceptData = JSONObject.parseObject(data);

        // 1.从redis中获取接口解析结果【接口注册时已经存入缓存】
        ApiResult apiResult = null;
        if (apiResult == null){
            // redis中不存在该接口缓存，重新解析，并且放入redis
        }

        // 2.参数校验 【校验参数是否合法】
        //CheckParams.checkParams(apiResult.getParams(), acceptData);

        // 3.封装执行
        //executeContext.setExecuteStrategy(apiResult);
        //Object result = executeContext.execute(acceptData, apiResult);

        // 返回执行结果
        return Result.ok();
    }

}