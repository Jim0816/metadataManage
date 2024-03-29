package com.ljm.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.entity.FieldInfo;
import com.ljm.entity.FieldNode;
import com.ljm.enums.FieldNodeLabelEnum;
import com.ljm.service.FieldInfoService;
import com.ljm.service.FieldNodeService;
import com.ljm.utils.PageUtils;
import com.ljm.utils.QueryUtils;
import com.ljm.vo.FieldTreeVO;
import com.ljm.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;



@AllArgsConstructor
@RestController
@RequestMapping("/fieldTree")
public class FieldNodeController extends BaseController{

    private FieldNodeService fieldNodeService;
    private FieldInfoService fieldInfoService;
    /**
     * @description TODO 添加字段树   注意：不能直接存前端传来的ID，需要在数据库生成新的ID
     * @return
     * @exception
     * @author gcy
     *
     * @date 2022/4/1 16:06
     **/
    @PostMapping(value = "/save")
    public Result save(@RequestBody List<Map<String ,Object>> fields){
        boolean save = fieldNodeService.saveFieldNode(fields);
        if (!save){
            return Result.failed();
        }
        return Result.ok();
    }

    /**
     * @description TODO 修改字段树  注意：不能直接存前端传来的ID，需要在数据库生成新的ID
     * @return
     * @exception
     * @author gcy
     * @date 2022/4/1 16:06
     **/
    @PostMapping(value = "/update")
    public Result update(@RequestBody List<FieldNode> fieldNode){
        boolean update=fieldNodeService.updateFieldNode(fieldNode);
        if (!update){
            return Result.failed();
        }
        return Result.ok();
    }

    /**
     * @description TODO 修改当前节点，涉及到添加子节点
     * @return
     * @exception
     * @author gcy
     * @date 2022/4/1 16:06
     **/
    @PostMapping(value = "/updateNode")
    public Result update(@RequestBody String data){
        JSONObject json = JSONObject.parseObject(data);
        long nodeId = json.getLong("nodeId");
        long nodeFieldId = json.getLong("nodeFieldId");
        JSONArray childFieldIds = json.getJSONArray("childFieldIds");


        // 1.查询当前节点信息
        FieldNode fieldNode = fieldNodeService.getById(nodeId);
        int type = fieldNode.getNodeType();

        // 2.判断当前节点被修改后的类型
        if (nodeFieldId != -1){
            // 被修改
            if (type == 1){
                return Result.failed("根节点类型不能被修改!");
            }

            FieldInfo fieldInfo = fieldInfoService.getById(nodeFieldId);
            type = fieldInfo.getFieldType().equals("complex") ? 2 : 1;

            // 更新当前节点类型信息
            fieldNode.setFieldInfoId(nodeFieldId);
            fieldNode.setNodeType(type);
            fieldNodeService.updateById(fieldNode);
        }

        // 针对更新后的节点类型操作子节点
        if (type == 3){
            // 叶子节点不能添加子节点
            return Result.failed("叶子节点不能添加子节点，请将当前节点的类型修改为组合节点!");
        }

        // 此时为根节点、复合节点添加子节点
        List<FieldNode> list = new ArrayList<>();
        for (Object item : childFieldIds){
            long fieldInfoId = Long.valueOf(item.toString());
            FieldInfo fieldInfo = fieldInfoService.getById(fieldInfoId);
            FieldNode node = new FieldNode();
            node.setNodeType(fieldInfo.getFieldType().equals("complex") ? 2 : 3);
            node.setDefaultName(fieldInfo.getFieldName());
            node.setFieldInfoId(fieldInfoId);
            node.setParentId(nodeId);
            list.add(node);
        }

        fieldNodeService.saveBatch(list);
        return Result.ok();

    }

    /**
     * @description TODO 删除字段树  (既可以单条、也可以批量)
     * @return
     * @exception
     * @author gcy
     * @date 2022/4/1 16:07
     **/
    @PostMapping(value = "/remove")
    public Result remove(@RequestBody List<Long> ids){
        boolean delete = fieldNodeService.deleteFieldNode(ids);
        if (!delete){
            return Result.failed();
        }
        return Result.ok();
    }

    /**
     * @description TODO 无条件查询所有字段树
     * @return
     * @exception
     * @author cyf
     * @date 2022/4/1 16:07
     **/
    @GetMapping(value = "/list")
    public Result list(){
        return Result.ok(fieldNodeService.getFieldTreeList());
    }

    /**
     * @description TODO 通过字段树根节点ID查询字段树
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/21 14:47
     **/
    @GetMapping(value = "/get")
    public Result get(Long id){
        return Result.ok(fieldNodeService.getFieldTree(id));
    }

    /**
     * @description TODO 条件分页查询
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/1 16:29
     **/
    @PostMapping(value = "/page")
    public Result page(@RequestBody FieldNode fieldNode){
        Map<String, Object> map = QueryUtils.getFieldAndValue(fieldNode, FieldNode.class, "serialVersionUID");
        QueryWrapper query = new QueryWrapper<FieldNode>();
        for (String key : map.keySet()){
            Object value = map.get(key);
            query.eq(value != null && StringUtils.isNotBlank(value.toString()), key, value);
        }
        query.eq(FieldNodeLabelEnum.DB_KEY_NODE_TYPE.getValue(), FieldNode.NODE_TYPE_ROOT);
        //得到所有根节点
        Page<FieldNode> pageData = fieldNodeService.page(getPage(), query);

        List<FieldTreeVO> list = new ArrayList<>();
        for (FieldNode node : pageData.getRecords()){
            list.add(fieldNodeService.getFieldTree(node.getId()));
        }
        Page<FieldTreeVO> page = PageUtils.changeRecord(pageData, list);
        return Result.ok(page);
    }

    /**
     * @description 查询字段树名称是否存在
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/21 14:47
     **/
    @GetMapping(value = "/hasFieldTreeName")
    public Result hasFieldTreeName(String fieldTreeName){
        QueryWrapper<FieldNode> fieldNodeQueryWrapper = new QueryWrapper<FieldNode>().eq(
                FieldNodeLabelEnum.DB_DEFAULT_NAME.getValue(), fieldTreeName);

        int count = fieldNodeService.count(fieldNodeQueryWrapper);
        return Result.ok(count);
    }

}
