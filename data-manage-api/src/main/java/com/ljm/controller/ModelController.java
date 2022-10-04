package com.ljm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.dto.ModelDto;
import com.ljm.dto.ModelParamDto;
import com.ljm.dynamic.DynamicAPI;
import com.ljm.entity.FieldInfo;
import com.ljm.entity.FieldNode;
import com.ljm.entity.Model;
import com.ljm.enums.ResCodeEnum;
import com.ljm.service.FieldNodeService;
import com.ljm.service.ModelService;
import com.ljm.utils.PageUtils;
import com.ljm.utils.QueryUtils;
import com.ljm.vo.ModelVO;
import com.ljm.vo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/model")
public class ModelController extends BaseController{

    private ModelService modelService;

    private FieldNodeService fieldNodeService;


    /**
     * @return
     * @throws
     * @description TODO 添加模型 通过模版配置
     * @author zyt
     * @date 2022/4/12 9:54
     **/
    @PostMapping(value = "/saveByProperties")
    public Result saveByProperties(@RequestBody String data){
        JSONObject json = JSONObject.parseObject(data);
        boolean result = modelService.createModelByProperties(json);
        return result ? Result.ok() : Result.failed();
    }

    /**
     * @return
     * @throws
     * @description TODO 添加模型
     * @author zyt
     * @date 2022/4/12 9:54
     **/
    @PostMapping(value = "/save")
    public Result save(@RequestBody ModelParamDto model){
        // 模型创建失败的话，方法内就会抛出异常
        if(!modelService.createModel(model)) {
            return Result.failed();
        }
        return Result.ok(null,ResCodeEnum.MODEL_CREATE_SUCCESS);
    }

    /**
     * @return
     * @throws
     * @description TODO 修改模型
     * @author zyt
     * @date 2022/2/25 13:41
     **/
    @PostMapping(value = "/update")
    public Result update(@RequestBody ModelParamDto modelParamDto) {
       if(modelService.updateModel(modelParamDto)) {
           return Result.ok(null,ResCodeEnum.MODEL_UPDATE_SUCCESS);
       }
       return Result.failed(ResCodeEnum.MODEL_UPDATE_ERRORS);
    }

    /**
     * @return
     * @throws
     * @description TODO 删除模型
     * @author zyt
     * @date 2022/4/11 19:08
     **/
    @PostMapping(value = "/remove")
    public Result remove(@RequestBody Long[] ids){
        if(modelService.removeModel(ids)) {
            return Result.ok(null,ResCodeEnum.SUCCESS);
        }
        return Result.failed(ResCodeEnum.MODEL_DELETE_ERROR);
    }

    /**
     * @return
     * @throws
     * @description TODO 查询模型
     * @author zrj
     * @date 2022/2/25 13:41
     **/
    @GetMapping(value = "/get/{id}")
    public Result get(@PathVariable(name = "id") String id) {
        return modelService.getModel(id) == null ? Result.failed() : Result.ok(modelService.getModel(id));
    }

    /**
     * @return
     * @throws
     * @description TODO 条件查询模型
     * @author zrj
     * @date 2022/2/25 13:41
     **/
    @PostMapping(value = "/list")
    public Result list(@RequestBody Model model) {
        return modelService.getListModel(model).size() > 0 ? Result.ok(modelService.getListModel(model)) : Result.failed();
    }

    /**
     * @return
     * @throws
     * @description TODO 条件分页查询模型
     * @author zrj
     * @date 2022/4/4 16:26
     **/
    @PostMapping(value = "/page")
    public Result page(@RequestBody Model model) {
        Map<String, Object> map = QueryUtils.getFieldAndValue(model, Model.class, "serialVersionUID");
        QueryWrapper query = new QueryWrapper<Model>();
        for (String key : map.keySet()){
            Object value = map.get(key);
            query.eq(value != null && StringUtils.isNotBlank(value.toString()), key, value);
        }
        Page<Model> pageData = modelService.page(getPage(), query);
        List<ModelVO> list = pageData.getRecords().stream().map(item -> {
            ModelVO modelVO = new ModelVO(item);
            if (item.getParentModelId() != null){
                modelVO.setParentModelName(modelService.getModel(String.valueOf(item.getParentModelId()))
                        .getModelName());
            }
            FieldNode fieldNode = fieldNodeService.getFieldNode(item.getFieldTreeId());
            modelVO.setFieldTreeName(fieldNode != null ? fieldNode.getDefaultName() : "");
            modelVO.setJsonIndex(JSONObject.parseObject(item.getIndex()));
            return modelVO;
        }).collect(Collectors.toList());

        Page<ModelVO> page = PageUtils.changeRecord(pageData, list);
        return Result.ok(page);
    }
}
