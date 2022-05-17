package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.entity.Api;

import java.util.List;


public interface ApiService extends IService<Api> {

    /**
     * @description 查询api
     * @return
     * @exception
     * @author hwl
     * @date 2022/4/14 20:31
     **/
    List<Api> listApi(Api api);

    /**
     * @description 添加api
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/16 20:07
     **/
    boolean save(Api api);
}
