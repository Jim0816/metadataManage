package com.ljm.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;

/**
 *@ClassName FieldInfoDto
 *@Description
 *@Author zyt
 *@Date 2022/4/4 16:28
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FieldInfoDto对象", description="")
public class FieldInfoDto implements Serializable {

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

    @ApiModelProperty(value = "当前页")
    private Long currentPage;

    @ApiModelProperty(value = "一页多少条")
    private Long pageSize;

    // 可以使用这种方式
//    public static FieldInfo createFieldInfo(FieldInfoDto fieldInfoDto){
//        FieldInfo fieldInfo = new FieldInfo();
//        fieldInfo.setId()
//        return fieldInfo;
//    }
}
