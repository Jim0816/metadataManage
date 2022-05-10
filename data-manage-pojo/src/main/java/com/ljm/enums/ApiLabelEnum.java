package com.ljm.enums;

/**
 * @ClassName ApiEnum
 * @Description
 * @Author Jim
 * @Date 2022/4/9 21:42
 **/
public enum ApiLabelEnum {
    NAME("name");

    private final String value;

    ApiLabelEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
