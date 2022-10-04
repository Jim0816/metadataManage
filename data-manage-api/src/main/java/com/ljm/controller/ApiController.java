package com.ljm.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.api.invoke.parse.APIParser;
import com.ljm.bo.ApiResult;
import com.ljm.dynamic.DynamicAPI;
import com.ljm.entity.Api;
import com.ljm.enums.ApiLabelEnum;
import com.ljm.enums.ResCodeEnum;
import com.ljm.lang.Const;
import com.ljm.service.ApiService;
import com.ljm.service.DynamicApiService;
import com.ljm.utils.QueryUtils;
import com.ljm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description TODO 接口的定义控制器
 * @return
 * @exception
 * @author Jim
 * @date 2022/4/9 16:27
 **/
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Value("${custom.apiUrlPattern}")
    private String urlPattern;

    @Autowired
    private ApiService apiService;

    @Autowired
    private APIParser apiParser;

    @Autowired
    private DynamicApiService dynamicApiService;

    @Autowired
    private DynamicAPI apiHandler;

    @Autowired
    private RequestMappingHandlerMapping mapping;


    /**
     * @description Done 添加接口 (需要优化)
     * @return
     * @exception
     * @author zjm
     * @date 2022/4/9 16:29
     **/
    @PostMapping(value = "/save")
    public Result save(@RequestBody String data) throws NoSuchMethodException {
        Api api = new Api(data, urlPattern);
        // 1.先判断是否name存在重名
        int count = apiService.count(new QueryWrapper<Api>().eq(ApiLabelEnum.NAME.toString(), api.getName()));
        if (count > 0){
            return Result.failed(ResCodeEnum.API_NAME_IS_EXIST);
        }

        // 2.若不存在，则添加
        if (apiService.save(api)){
            // 添加成功，则解析接口加入缓存
            //String key = api.getModel() + ":" + api.getName();
            //ApiResult apiResult = dynamicApiService.parseApi(api);
            //APIParser apiParser = new APIParser();
            //ApiResult apiResult = apiParser.parse(api.getId());
            //String value = JSON.toJSONString(apiResult);
            //redisUtil.hset(Const.API_KEY, key, value, 6 * 60); //6小时过期

            // 解析接口结果，存入redis
            ApiResult apiResult = apiParser.parse(api.getName());

            // 通过反射生成接口，并注入容器
            String apiPath = api.getApiPath();
            RequestMappingInfo info = RequestMappingInfo.paths(apiPath).methods("get".equals(api.getHttpType()) ? RequestMethod.GET : RequestMethod.POST).build();

            // 反射得到方法
            Method method = DynamicAPI.class.getMethod("handle", String.class);

            // 将方法注册到ioc容器
            mapping.registerMapping(info, apiHandler, method);

            return Result.ok();
        }
        return Result.failed();
    }

    /**
     * @description Done 修改接口
     * @return
     * @exception
     * @author zjm
     * @date 2022/4/9 16:29
     **/
    @PostMapping(value = "/update")
    public Result update(@RequestBody Api api){
        //1.先判断接口是否存在
        int count = apiService.count(new QueryWrapper<Api>().eq(ApiLabelEnum.NAME.toString(),api.getName()));
        if(count==0){
            return Result.failed(ResCodeEnum.FAILED);
        }

        //2.存在就修改
        return apiService.updateById(api) ? Result.ok() : Result.failed();
    }

    /**
     * @description Done 删除接口
     * @return
     * @exception
     * @author zjm
     * @date 2022/4/9 16:29
     **/
    @PostMapping(value = "/remove")
    public Result remove(@RequestBody Long[] ids){
        for(long id : ids){
            apiService.removeById(id);
        }
        return Result.ok();
    }

    /**
     * @description 根据id查询 done
     * @return 
     * @exception 
     * @author hwl
     * @date 2022/4/11 23:04
     **/
    @GetMapping(value = "/get/{id}")
    public Result get(@PathVariable(name = "id") String id){
        return Result.ok(apiService.getById(id));
    }

    /**
     * @description done 按照条件(name)查询所有接口
     * @return
     * @exception
     * @author hwl
     * @date 2022/4/9 16:29
     **/
    @PostMapping(value = "/list")
    public Result list(@RequestBody Api api){
        return Result.ok( apiService.listApi(api));
    }

    /**
     * @description done 可以实现无条件查询和条件查询
     * @return
     * @exception
     * @author hwl
     * @date 2022/4/14 17:29
     **/
    @PostMapping(value = "/page")
    public Result page(@RequestBody Api api){
        Map<String, Object> map = QueryUtils.getFieldAndValue(api, Api.class, "serialVersionUID");
        QueryWrapper<Api> query = new QueryWrapper<Api>();
        for (String key : map.keySet()){
            Object value = map.get(key);
            query.eq(value != null && StringUtils.isNotBlank(value.toString()), key, value);
        }
        Page<Api> pageData = apiService.page(getPage(), query);
        return Result.ok(pageData);
    }
}
