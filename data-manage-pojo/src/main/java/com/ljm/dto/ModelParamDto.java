package com.ljm.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName ModelParamDto
 * @Description 为了方便处理前端传递过来的参数
 * @Author zyt
 * @Date 2022/4/12 9:52
 **/
@Data
@NoArgsConstructor
@ApiModel(value="ModelParamDto对象", description="")
public class ModelParamDto {
    /*
    	"modelName": "user",
	"fieldTreeId": 5,
	"index": [
	    {"type": "unique_key", "field": "phone", "sort": -1},
	    {"type": "hash_key", "field": "email", "sort": 1},
	    {"type": "multi_key", "field": [{"field": "name", "sort": 1},{"field": "age", "sort": -1}]}
	],
	"parentModelId": 7, //用户前端效果：本模型继承7号模型，但是实际上复制7号模型的字段树，用于创建一份新的字段树，新字段树ID更新到本模型的fieldTreeId
	"remark": "描述说明......"
     */
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
    private List<LinkedHashMap<String,Object>> index;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
}
