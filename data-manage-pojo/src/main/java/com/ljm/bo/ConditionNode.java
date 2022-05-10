package com.ljm.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConditionNode
 * @Description sql条件解析结点
 * @Author Jim
 * @Date 2022/3/14 14:53
 **/
@Data
public class ConditionNode implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int ROOT_NODE = 0;
    public static final int AND_NODE = 1;
    public static final int OR_NODE = 2;
    public static final int VALUE_NODE = 3;

    private Integer nodeType;
    private String value;
    private List<ConditionNode> children;

    public ConditionNode(int nodeType, String value){
        this.nodeType = nodeType;
        this.value = value;
    }

    public void addChildren(ConditionNode node){
        if(children == null){
            children = new ArrayList<>();
        }
        this.children.add(node);
    }
}
