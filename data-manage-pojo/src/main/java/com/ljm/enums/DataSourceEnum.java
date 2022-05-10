package com.ljm.enums;

/**
 * @ClassName DataSourceEnum
 * @Description 数据源枚举枚举  用于存放数据源名称
 * @Author Jim
 * @Date 2022/2/22 16:17
 **/
public enum DataSourceEnum {
    SYSTEM_DB_MYSQL("system_db_mysql");

    private final String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
