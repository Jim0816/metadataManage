package com.ljm.enums;

/**
 * @ClassName FieldLabelEnum
 * @Description 与字段相关的前端配置key的枚举
 * @Author Jim
 * @Date 2022/2/22 16:17
 **/
public enum FieldLabelEnum {
    KEY_ID("id"),
    KEY_NODE_TYPE("nodeType"),
    DB_KEY_NODE_TYPE("node_type"),
    KEY_PARENT_ID("parentId"),
    KEY_FIELD_INFO_ID("fieldInfoId"),
    KEY_DEFAULT_NAME("defaultName"),
    KEY_FIELD_NAME("fieldName"),
    DB_KEY_FIELD_NAME("field_name"),
    KEY_FIELD_TYPE("fieldType"),
    KEY_LENGTH("length"),
    KEY_IS_REQUIRE("isRequire"),
    KEY_IS_UNIQUE("isUnique"),
    KEY_DEFAULT_VALUE("defaultValue"),
    KEY_REMARK("remark"),
    DB_KEY_FIELD_INFO_ID("field_info_id"),
    DB_KEY_PARENT_ID("parent_id"),
    DB_KEY_FIELD_TYPE("field_type"),
    DB_KEY_LENGTH("length"),
    DB_KEY_IS_REQUIRE("is_require"),
    DB_KEY_IS_UNIQUE("is_unique"),
    DB_KEY_DEFAULT_VALUE("default_value"),
    DB_KEY_REMARK("remark");



    private final String value;

    FieldLabelEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
