package com.ljm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.bo.ApiResult;
import com.ljm.entity.Api;
import com.ljm.enums.ResCodeEnum;
import com.ljm.lang.Const;
import com.ljm.service.ApiService;
import com.ljm.service.DynamicApiService;
import com.ljm.vo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
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
    /**
     * @description 处理put类型接口  apiName是接口名称（唯一），通过接口名称查找接口数据
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/25 11:21
     **/
    @PostMapping(value = "/put/{model}/{apiName}")
    public Result add(@RequestBody String data, @PathVariable("apiName") String apiName){
        JSONObject acceptData = JSONObject.parseObject(data);
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",apiName);
        Api api = apiService.getOne(queryWrapper);
        String key = api.getModel() + ":" + api.getName();
        Object object = redisUtil.hget(Const.API_KEY, key);
        ApiResult apiResult = JSON.parseObject(object.toString(), ApiResult.class);
        // 1.解析API结果 （可以从缓存获取）
        //ApiResult apiResult = dynamicApiService.parseApi(api);

        // 2.数据校验 zrj + hwl
        //boolean checkResult = dynamicApiService.checkApiData(acceptData, apiResult.getParams());
        /*if (!checkResult){
            // 校验失败
            return Result.failed(ResCodeEnum.API_DATA_IS_NOT_VALID);
        }*/

        // 3.注入：data + apiResult => mongo执行对象  zyt + ljm
        // 此时，接口校验通过，并且acceptData在校验过程中可能预处理了

        // http://127.0.0.1:8081/server/user/put/addUser
       /* System.out.println(acceptData);
        System.out.println(acceptData.values());
        //获取json数据信息
        for (Object o : acceptData.keySet()){
            System.out.println(o +"==="+acceptData.get(o));
            JSONObject jsonObject= (JSONObject) acceptData.get(o);
            for (Object t : jsonObject.keySet()){
                System.out.println(t+"+++++++"+jsonObject.get(t));
            }
        }
        */
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
    public String get(@RequestBody String data, @PathVariable("apiName") String apiName){
        return "";
    }

}
