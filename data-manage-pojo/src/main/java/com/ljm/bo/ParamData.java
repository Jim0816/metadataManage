package com.ljm.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ParamData
 * @Description
 * @Author Jim
 * @Date 2022/4/19 18:19
 **/
@Data
public class ParamData <T> implements Serializable {
    public static final String PARAM_FIELD_TYPE = "field";
    public static final String PARAM_MODEL_TYPE = "model";

    private static final long serialVersionUID = 1L;
    // 参数名称
    private String paramName;

    // 参数的数据类型：field 或者 model
    private String type;

    // 参数对应的结构信息 value可以为FieldInfo 、 ModelDetail
    private T value;
}
