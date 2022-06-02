package com.ljm.api.invoke.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.bo.ConditionNode;
import com.ljm.bo.ModelDetail;
import com.ljm.bo.ParamData;
import com.ljm.entity.Api;
import com.ljm.entity.Model;
import com.ljm.entity.SysUser;
import com.ljm.enums.ModelLabelEnum;
import com.ljm.service.ApiService;
import com.ljm.service.FieldInfoService;
import com.ljm.service.ModelService;
import com.ljm.service.SysUserService;
import com.ljm.utils.ConditionParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @ClassName APIParser
 * @Description
 * @Author Jim
 * @Date 2022/4/23 15:39
 **/
@Slf4j
@Component
public class APIParser extends AbstractAPIParser {
    @Autowired
    private ApiService apiService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FieldInfoService fieldInfoService;

    @Autowired
    private ModelService modelService;

    @Override
    Api parseApi(String apiName) {
        return apiService.getOne(new QueryWrapper<Api>().eq("name", apiName));
    }

    @Override
    List<SysUser> parseAccessUsers(Api api) {
        // "1,2,3"
        String ids[] = null;
        String accessors = api.getAccessUser();
        if (accessors.contains(",")){
            ids = accessors.split(",");
        }else if(accessors != null && !"".equals(accessors)){
            ids = new String[]{accessors};
        }
        return sysUserService.getUserList(ids);
    }

    @Override
    List<ModelDetail> parseAllModel(Api api) {
        String models = api.getModelNames();
        String[] modelNames = null;
        if (models.contains(",")){
            modelNames = models.split(",");
        }else if(models != null && !"".equals(models)){
            modelNames = new String[]{models};
        }else{
            return null;
        }

        List<ModelDetail> list = new ArrayList<>();

        for (String modelName : modelNames){
            // 查询当前model的id
            QueryWrapper<Model> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ModelLabelEnum.DB_KEY_MODEL_NAME.getValue(), modelName);
            Model model = modelService.getOne(queryWrapper);
            if (model != null){
                list.add(modelService.getModelDetail(modelService.getById(model.getId())));
            }else{
                log.info("当前model不存在!" + "模型：" + modelName);
            }
        }

        return list;
    }

    @Override
    Map<String, ParamData> parseParams(Api api) {
        Map<String, ParamData> map = new HashMap<>();
        // "params": { "user": {"type": "model", "id": 10000}}
        JSONObject jsonData = JSON.parseObject(api.getParams());
        //遍历JSON获取信息
        for (String paramName : jsonData.keySet()){
            ParamData paramData = new ParamData();
            paramData.setParamName(paramName);
            JSONObject paramValue = (JSONObject) jsonData.get(paramName);
            // 设置参数类型
            paramData.setType(paramValue.get("type").toString());

            // 设置参数类型值
            String fieldId = paramValue.get("id").toString();
            if (paramData.getType().equals(ParamData.PARAM_FIELD_TYPE)){
                // 字段类型
                paramData.setValue(fieldInfoService.getById(Long.valueOf(fieldId)));
            }else if (paramData.getType().equals(ParamData.PARAM_MODEL_TYPE)){
                // 模型类型
                paramData.setValue( modelService.getModelDetail(modelService.getById(Long.valueOf(fieldId))));
            }

            map.put(paramName, paramData);
        }
        return map;
    }

    @Override
    ConditionNode parseCondition(Api api) {
        String whereCondition = api.getFilter();
        if (whereCondition == null){
            return null;
        }
        ConditionNode conditionNode = new ConditionNode(ConditionNode.ROOT_NODE,whereCondition);
        ConditionParserUtil.buildCondition(conditionNode,whereCondition);
        return conditionNode;
    }
}
