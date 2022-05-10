package com.ljm.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ljm.entity.FieldInfo;
import com.ljm.entity.FieldNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FieldNodeVO
 * @Description
 * @Author Jim
 * @Date 2022/3/29 16:07
 **/
@Data
@NoArgsConstructor
public class FieldNodeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    // 节点类型  1:根节点；2:中间节点（复合属性）；3:叶子节点；
    private Integer nodeType;

    // 非叶子节点名称（nodeType为1,2时有效）//1，2时为default_name
    private String nodeName;

    // 叶子节点字段信息 （nodeType为3时有效）
    private FieldInfo fieldInfo;

    @ApiModelProperty(value = "父节点id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "字段定义对象id")
    @TableField("field_info_id")
    private Long fieldInfoId;

//    @ApiModelProperty(value = "允许对定义的字段进行重命名")
//    @TableField("default_name")
//    private String defaultName;

    /**
     * @description  FieldNde 和FieldInfo中的内容映射为FIeldNodeVO,当不是叶子结点时，fieldInfo应为null
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/6 21:31
     **/
    public FieldNodeVO(FieldNode fieldNode, FieldInfo fieldInfo){
        this.id = fieldNode.getId();
        this.nodeType = fieldNode.getNodeType();
        this.parentId = fieldNode.getParentId();
        this.fieldInfoId = fieldNode.getFieldInfoId();
        if(fieldNode.getNodeType() == 1 || fieldNode.getNodeType() == 2){
            this.nodeName = fieldNode.getDefaultName();
        }else {
            this.fieldInfo = fieldInfo;
        }
    }
}
