package com.ljm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.annotation.MyDataSource;
import com.ljm.entity.DataSource;
import com.ljm.enums.DataSourceEnum;
import com.ljm.mapper.DataSourceMapper;
import com.ljm.service.DataSourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljm
 * @since 2022-02-22
 */

@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {

    @MyDataSource(DataSourceEnum.SYSTEM_DB_MYSQL)
    @Override
    public List<DataSource> list(DataSource dataSource) {
        QueryWrapper<DataSource> queryWrapper = new QueryWrapper();
        return baseMapper.selectList(queryWrapper);
    }
}
