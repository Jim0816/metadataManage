package com.ljm.vo;

import com.ljm.bo.FieldNodeTree;
import com.ljm.entity.FieldNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName FieldTreeVO
 * @Description 字段树对象视图：整棵字段树信息
 * @Author Jim
 * @Date 2022/3/29 16:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldTreeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private FieldNodeVO node;
    private List<FieldTreeVO> children;
}
