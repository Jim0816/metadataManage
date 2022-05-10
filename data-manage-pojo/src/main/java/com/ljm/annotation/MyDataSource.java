package com.ljm.annotation;

import com.ljm.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @ClassName DynamicDataSource
 * @Description 自定义动态数据源注解
 * @Author Jim
 * @Date 2022/2/24 11:04
 **/

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyDataSource {
    DataSourceEnum value() default DataSourceEnum.SYSTEM_DB_MYSQL;
}
