package com.ljm.enums;

/**
 * @ClassName ApiEnum
 * @Description
 * @Author Jim
 * @Date 2022/4/9 21:42
 **/
public enum ApiLabelEnum {
    NAME("name"),
    API_TYPE_ADD("add"),
    API_TYPE_UPDATE("update"),
    API_TYPE_DELETE("delete"),
    API_TYPE_GET("get");

    private final String value;

    ApiLabelEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
