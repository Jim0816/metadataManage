package com.ljm.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ljm.entity.ForeignRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ForeignRelationBO
 * @Description
 * @Author Jim
 * @Date 2022/3/29 15:17
 **/
@Data
public class ForeignRelationVO extends ForeignRelation {

    @ApiModelProperty(value = "关系类型名称")
    private String relationName;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "字段名称")
    private String fieldName;

    @ApiModelProperty(value = "被参照模型名称")
    private String referredModelName;

    @ApiModelProperty(value = "被参照字段名称")
    private String referredFieldName;
}
