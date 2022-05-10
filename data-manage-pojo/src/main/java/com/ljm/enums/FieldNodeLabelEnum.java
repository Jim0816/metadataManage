package com.ljm.enums;

public enum FieldNodeLabelEnum {

    KEY_ID("id"),
    DB_KEY_PARENT_ID("parent_id"),
    KEY_PARENT_ID("parentId"),
    KEY_NODE_TYPE("nodeType"),
    DB_KEY_NODE_TYPE("node_type"),
    KEY_FIELD_INFO_ID("fieldInfoId"),
    KEY_DEFAULT_NAME("defaultName"),
    DB_DEFAULT_NAME("default_name");
    private final String value;

    FieldNodeLabelEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
