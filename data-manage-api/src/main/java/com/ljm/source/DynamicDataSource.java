package com.ljm.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DynamicDataSource
 * @Description 动态数据源
 * @Author Jim
 * @Date 2022/2/24 9:29
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Map<Object, DataSource> dataSourceMap = new HashMap<>();


    /**
     * @description 获取数据源标识，由数据源控制器指定
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/24 9:34
     **/
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSource();
    }

    /**
     * @description 通过数据源标识决定使用哪一个数据源
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/25 9:53
     **/
    @Override
    protected DataSource determineTargetDataSource() {
        return dataSourceMap.get(determineCurrentLookupKey());
    }

    /**
     * @description 设置动态数据源
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/24 16:28
     **/
    public void setDynamicDataSource(Map<Object, Object> targetDataSources,
                                                  Object defaultTargetDataSourceKey){
        // 添加数据源
        super.setTargetDataSources(targetDataSources);
        // 设置默认数据源
        Object defaultTargetDataSource = targetDataSources.get(defaultTargetDataSourceKey);
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }





    /**
     * 对数据源的初始化方法，由于这里已经将数据源集合放在本类中，如果不重写将会由于父类参数为null而抛出异常。
     */
    @Override
    public void afterPropertiesSet() {

    }
}
