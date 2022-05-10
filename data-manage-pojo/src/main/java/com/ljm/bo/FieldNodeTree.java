package com.ljm.bo;

import com.ljm.entity.FieldNode;
import lombok.Data;

import java.util.List;

/**
 * @ClassName FieldNodeTree
 * @Description
 * @Author Jim
 * @Date 2022/2/28 9:52
 **/
@Data
public class FieldNodeTree {
    private FieldNode node;
    private List<FieldNodeTree> children;
}
