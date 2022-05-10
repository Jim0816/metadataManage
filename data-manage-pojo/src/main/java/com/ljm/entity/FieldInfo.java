package com.ljm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ljm.enums.FieldLabelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

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
@TableName("tb_field_info")
@ApiModel(value="FieldInfo对象", description="")
public class FieldInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字段名称")
    @TableField("field_name")
    private String fieldName;

    @ApiModelProperty(value = "数据类型")
    @TableField("field_type")
    private String fieldType;

    @ApiModelProperty(value = "字段长度")
    private Integer length;

    @ApiModelProperty(value = "是否必填 1必填 0非必填")
    @TableField("is_require")
    private Integer isRequire;

    @ApiModelProperty(value = "是否唯一 1唯一 0不唯一")
    @TableField("is_unique")
    private Integer isUnique;

    @ApiModelProperty(value = "本字段默认值，需要按照field_type转换数据类型")
    @TableField("default_value")
    private String defaultValue;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Override
    public String toString() {
        return fieldName + fieldType + length + isRequire + isUnique + defaultValue;
    }

    public QueryWrapper<FieldInfo> buildQueryWrapper(){
        QueryWrapper query = new QueryWrapper<FieldInfo>();
        //query.eq(StringUtils.isNotBlank(field.getId().toString()), "id", field.getId());
        return query;
    }
}
