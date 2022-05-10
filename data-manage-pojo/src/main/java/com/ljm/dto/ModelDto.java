package com.ljm.dto;

import com.ljm.entity.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ModelDto对象", description="")
public class ModelDto extends Model {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页")
    private Long current;

    @ApiModelProperty(value = "一页多少条")
    private Long size;


}
