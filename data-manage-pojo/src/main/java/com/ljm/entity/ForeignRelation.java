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
 * @ClassName ForeignRelation
 * @Description 外键关系实体
 * @Author Jim
 * @Date 2022/3/29 15:09
 **/
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_foreign_relation")
@ApiModel(value="外键关系实体", description="")
public class ForeignRelation  implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int FOREIGN_KET_RELATION = 1;

    @ApiModelProperty(value = "主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关系类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "模型ID")
    @TableField("model_id")
    private Long modelId;

    @ApiModelProperty(value = "字段ID")
    @TableField("field_id")
    private Long fieldId;

    @ApiModelProperty(value = "被参照模型ID")
    @TableField("referred_model_id")
    private Long referredModelId;

    @ApiModelProperty(value = "被参照字段ID")
    @TableField("referred_field_id")
    private Long referredFieldId;
}
