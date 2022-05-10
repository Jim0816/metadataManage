package com.ljm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ljm
 * @since 2022-02-22
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_field_node")
@ApiModel(value="FieldNode对象", description="")
public class FieldNode implements Serializable {
    public static final int NODE_TYPE_ROOT_PARENT = -1; // 叶子节点类型（前端定义好待创建）
    public static final int NODE_TYPE_FIELD_CREATE = 0; // 叶子节点类型（前端定义好待创建）
    public static final int NODE_TYPE_ROOT = 1; // 根节点类型
    public static final int NODE_TYPE_PARENT = 2; // 复合节点类型
    public static final int NODE_TYPE_FIELD_EXIST = 3; // 叶子节点类型（已存在）

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "节点类型 1:根节点；2:中间节点（复合属性）；3:叶子节点  " +
            "注意：0表示新创建的节点,创建成功后数据库存储3")
    @TableField("node_type")
    private Integer nodeType;

    @ApiModelProperty(value = "父节点id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "字段定义对象id")
    @TableField("field_info_id")
    private Long fieldInfoId;

    @ApiModelProperty(value = "允许对定义的字段进行重命名")
    @TableField("default_name")
    private String defaultName;




    public FieldNode(Long id, Integer nodeType, Long parentId, Long fieldInfoId, String defaultName) {
        this.id = id;
        this.nodeType = nodeType;
        this.parentId = parentId;
        this.fieldInfoId = fieldInfoId;
        this.defaultName = defaultName;
    }
}
