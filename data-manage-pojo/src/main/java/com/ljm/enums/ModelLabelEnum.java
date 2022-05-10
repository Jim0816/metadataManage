package com.ljm.enums;

/**
 * @description TODO
 * @return
 * @exception
 * @author zyt
 * @date 2022/4/10 19:38
 **/
public enum ModelLabelEnum {

    KEY_ID("id"),
    DB_KEY_MODEL_NAME("model_name"),
    DB_KEY_INDEX("index"),
    DB_KEY_PARENT_MODEL_ID("parent_model_id"),
    DB_KEY_FIELD_TREE_ID("field_tree_id"),
    DB_KEY_REMARK("remark"),
    DB_KEY_IS_DELETE("is_delete");

    private final String value;

    ModelLabelEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
