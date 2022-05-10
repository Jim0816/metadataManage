package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.entity.DataSource;

import java.util.List;


public interface DataSourceService extends IService<DataSource> {

    /**
     * @description 获取数据源列表
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/24 14:53
     **/
    List<DataSource> list(DataSource dataSource);
}
