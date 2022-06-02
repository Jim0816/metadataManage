package com.ljm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljm.api.invoke.execute.ExecuteContext;
import com.ljm.api.invoke.parse.APIParser;
import com.ljm.bo.ApiResult;
import com.ljm.service.ApiService;
import com.ljm.service.DynamicApiService;
import com.ljm.vo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DynamicAPIController
 * @Description 只负责处理动态接口调用过程 （即接口服务）
 * @Author Jim
 * @Date 2022/2/21 16:38
 **/
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/server")
public class DynamicAPIController extends BaseController{

    private DynamicApiService dynamicApiService;
    private ApiService apiService;
    private APIParser apiParser;
    private ExecuteContext executeContext;

    /**
     * @description 处理add类型接口  apiName是接口名称（唯一），通过接口名称查找接口数据
     * @return http://127.0.0.1:8081/server/put/user/addUser
     * @exception
     * @author Jim
     * @date 2022/2/25 11:21
     **/
    @PostMapping(value = "/add/{model}/{apiName}")
    public Result add(@RequestBody String data, @PathVariable("apiName") String apiName){
        // 接口请求参数
        JSONObject acceptData = JSONObject.parseObject(data);

        // 1.解析接口
        // TODO 缓存优化 从缓存获取 (暂时直接解析)
        //String key = api.getModel() + ":" + api.getName();
        //Object object = redisUtil.hget(Const.API_KEY, key);
        //ApiResult apiResult = JSON.parseObject(object.toString(), ApiResult.class);

        // 1.解析API结果 （可以从缓存获取）
        ApiResult apiResult = apiParser.parse(apiName);

        // 2.参数校验 acceptData校验过程会被处理
        //CheckParams.checkParams(apiResult.getParams(), acceptData);

        // 3.封装执行
        // data + api -> mongo
        executeContext.setExecuteStrategy(apiResult);
        int result = executeContext.execute(acceptData, apiResult);


        return Result.ok();
    }

    /**
     * @description delete类型接口
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/25 11:21
     **/
    @PostMapping(value = "/delete/{model}/{apiName}")
    public String delete(@RequestBody String data, @PathVariable("apiName") String apiName){
        return "";
    }

    /**
     * @description post类型接口
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/25 11:21
     **/
    @PostMapping(value = "/post/{model}/{apiName}")
    public String update(@RequestBody String data, @PathVariable("apiName") String apiName){
        return "";
    }

    /**
     * @description get类型接口
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/25 11:21
     **/
    @PostMapping(value = "/get/{model}/{apiName}")
    public Result get(@RequestBody String data, @PathVariable("apiName") String apiName){
        // 接口请求参数
        JSONObject acceptData = JSONObject.parseObject(data);

        // 1.解析API结果 （可以从缓存获取）
        ApiResult apiResult = apiParser.parse(apiName);

        // 2.参数校验 acceptData校验过程会被处理
        //CheckParams.checkParams(apiResult.getParams(), acceptData);

        // 3.封装执行
        // data + api -> mongo
        executeContext.setExecuteStrategy(apiResult);
        int result = executeContext.execute(acceptData, apiResult);

        return Result.ok();
    }

}
