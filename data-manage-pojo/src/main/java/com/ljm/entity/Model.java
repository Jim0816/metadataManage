package com.ljm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_model")
@NoArgsConstructor
@ApiModel(value="Model对象", description="")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MODEL_DELETE = 1; // 逻辑删除model

    @ApiModelProperty(value = "主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模型名称")
    @TableField("model_name")
    private String modelName;

    @ApiModelProperty(value = "字段树根节点ID")
    @TableField("field_tree_id")
    private Long fieldTreeId;

    @ApiModelProperty(value = "继承的父模型ID")
    @TableField("parent_model_id")
    private Long parentModelId;

    @ApiModelProperty(value = "索引信息")
    @TableField("`index`")
    private String index;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private int isDelete;



}
