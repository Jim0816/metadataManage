package com.ljm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@TableName("tb_sort")
@ApiModel(value="Sort对象", description="")
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "API对象ID")
    @TableField("api_id")
    private Long apiId;

    @ApiModelProperty(value = "排序字段名称")
    @TableField("sort_field_name")
    private String sortFieldName;

    @ApiModelProperty(value = "排序字段先后位置，先排序哪个字段")
    private Integer order;

    @ApiModelProperty(value = "排序方向 1升序 0降序")
    private Integer direction;


}
