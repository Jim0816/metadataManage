package com.ljm.bo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Condition
 * @Description
 * @Author Jim
 * @Date 2022/5/21 16:25
 **/
@Data
public class Condition {
    private static final String operators = ">,>=,<,<=,=,!=";
    public static Set<String> operateSet;

    static {
        operateSet = new HashSet<>();
        String[] operators = Condition.operators.split(",");
        for (String op : operators){
            operateSet.add(op);
        }
    }

    // 字段名称
    private String fieldName;

    // 运算符
    private String operate;

    // 值 (已经转换好类型的值)
    private Object value;

}
