package com.ljm.vo;

import com.alibaba.fastjson.JSONObject;
import com.ljm.entity.Model;
import lombok.Data;

/**
 * @ClassName ModelVO
 * @Description
 * @Author Jim
 * @Date 2022/4/21 15:14
 **/
@Data
public class ModelVO extends Model {
    private String parentModelName;
    private String fieldTreeName;
    private JSONObject jsonIndex;


    public ModelVO(Model model){
        this.setId(model.getId());
        this.setFieldTreeId(model.getFieldTreeId());
        this.setIndex(model.getIndex());
        this.setIsDelete(model.getIsDelete());
        this.setModelName(model.getModelName());
        this.setRemark(model.getRemark());
        this.setParentModelId(model.getParentModelId());
    }
}
