package com.ljm.component;

import com.ljm.dynamic.DynamicAPI;
import com.ljm.entity.Api;
import com.ljm.service.ApiService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName ApplicationListenerImpl
 * @Description
 * @Author Jim
 * @Date 2022/10/2 下午4:27
 **/
@Slf4j
@Component
public class ApplicationListenerImpl implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private ApiService apiService;

    @Autowired
    private DynamicAPI apiHandler;

    @Autowired
    private RequestMappingHandlerMapping mapping;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("springboot 启动完成......");
        log.info("======================  加载系统所有可用接口  ======================");
        List<Api> apiList = apiService.listApi();
        for (Api api : apiList) {
            log.info("加载接口: {名称：{}, url: {}}", api.getName(), api.getUrl());
            // 通过反射生成接口，并注入容器
            // 通过反射生成接口，并注入容器
            String apiPath = api.getApiPath();
            RequestMappingInfo info = RequestMappingInfo.paths(apiPath).methods("get".equals(api.getHttpType()) ? RequestMethod.GET : RequestMethod.POST).build();

            // 反射得到方法
            Method method = DynamicAPI.class.getMethod("handle", String.class);

            // 将方法注册到ioc容器
            mapping.registerMapping(info, apiHandler, method);
        }
    }
}