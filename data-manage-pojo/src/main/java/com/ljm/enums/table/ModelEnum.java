package com.ljm.enums.table;

public enum ModelEnum {
    Model_name("name"),
    Model_remark("description"),
    Model_properties("properties"),
    Field_required("required"),
    Field_type("type"),
    Field_children("children"),
    Field_unique("unique"),
    Field_length("length"),
    Field_default_value("default_value"),
    Field_remark("description");
    private final String value;

    ModelEnum(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
