package com.ljm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.bo.FieldNodeTree;
import com.ljm.bo.mongo.Index;
import com.ljm.bo.mongo.QueryModel;
import com.ljm.dto.ModelParamDto;
import com.ljm.entity.FieldNode;
import com.ljm.entity.Model;
import com.ljm.enums.FieldLabelEnum;
import com.ljm.enums.FieldNodeLabelEnum;
import com.ljm.enums.ModelLabelEnum;
import com.ljm.enums.ResCodeEnum;
import com.ljm.except.CustomException;
import com.ljm.mapper.FieldNodeMapper;
import com.ljm.mapper.ModelMapper;
import com.ljm.service.FieldNodeService;
import com.ljm.service.ModelService;
import com.ljm.bo.ModelDetail;
import com.ljm.vo.FieldTreeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ljm.utils.MongoDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    private FieldNodeMapper fieldNodeMapper;

    private ModelMapper modelMapper;

    private FieldNodeService fieldNodeService; // 注意: service层之间不要循环调用

    private MongoDBUtil mongoDBUtil;

    /**
     * @param fields        所有字段结构信息列表
     * @param childFields   本层待加入的节点配置信息列表
     * @param fieldNodeTree 构建的字段树  指针引用传值
     * @return
     * @throws
     * @description TODO 从模型配置信息中提取出字段树的结构信息 算法需要优化
     * @author Jim
     * @date 2022/2/27 16:28
     **/
    private void getFieldTreeStructFromInfo(List<Map<String, Object>> fields,
                                    List<Map<String, Object>> childFields,
                                    FieldNodeTree fieldNodeTree) {
        for (Map<String, Object> fieldMap : childFields) {
            long id = Long.valueOf(fieldMap.get(FieldLabelEnum.KEY_ID.getValue()).toString());
            int nodeType = Integer.valueOf(fieldMap.get(FieldLabelEnum.KEY_NODE_TYPE.getValue()).toString());
            long parentId = Long.valueOf(fieldMap.get(FieldLabelEnum.KEY_PARENT_ID.getValue()).toString());
            long fieldInfoId = Long.valueOf(fieldMap.get(FieldLabelEnum.KEY_FIELD_INFO_ID.getValue()).toString());
            String defaultName = fieldMap.get(FieldLabelEnum.KEY_DEFAULT_NAME.getValue()).toString();

            FieldNode fieldNode = new FieldNode(id, nodeType, parentId, fieldInfoId, defaultName);

            if (nodeType == FieldNode.NODE_TYPE_ROOT) {
                // 更新配置信息: 从配置信息中移除当前节点
                fieldNodeTree.setNode(fieldNode);
                fieldNodeTree.setChildren(new ArrayList<FieldNodeTree>());
                // 得到根节点的所有孩子节点配置信息
                childFields = fields.stream().filter(e -> {
                    return id == Integer.valueOf(e.get(FieldLabelEnum.KEY_PARENT_ID.getValue()).toString());
                }).collect(Collectors.toList());

                // 递归
                getFieldTreeStructFromInfo(fields, childFields, fieldNodeTree);
            } else if (nodeType == FieldNode.NODE_TYPE_PARENT || nodeType == FieldNode.NODE_TYPE_FIELD_EXIST) {
                // 判断父节点是否已经存在
                FieldNode parent = fieldNodeTree.getNode();
                if (parent != null && parent.getId() == parentId) {
                    // 父节点信息已经提取成功 -> 添加该子节点进入字段树
                    FieldNodeTree childNode = new FieldNodeTree();
                    childNode.setNode(fieldNode);
                    childNode.setChildren(new ArrayList<FieldNodeTree>());
                    fieldNodeTree.getChildren().add(childNode);

                    // 如果是中间节点（复合属性），需要递归
                    if (nodeType == FieldNode.NODE_TYPE_PARENT) {
                        childFields = fields.stream().filter(e -> {
                            return id == Integer.valueOf(e.get(FieldLabelEnum.KEY_PARENT_ID.getValue()).toString());
                        }).collect(Collectors.toList());

                        // 递归 childNode为该复合字段节点
                        getFieldTreeStructFromInfo(fields, childFields, childNode);
                    }
                }
            }
        }
    }

    @Override
    public boolean createModelByProperties(JSONObject prop) {
        return false;
    }

    /**
     * @description  创建数据模型 TODO mongoDB的事务正在调研
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/10 18:15
     **/
    @Transactional
    @Override
    public boolean createModel(ModelParamDto model) {
            // 校验fieldTreeId 和 parentModelId 是否符合条件
            if(model.getParentModelId() == null && model.getFieldTreeId() == null) return false;

            // 校验模型是否重名
            Model modelOne = modelMapper.selectOne(new QueryWrapper<Model>()
                    .eq(ModelLabelEnum.DB_KEY_MODEL_NAME.getValue(), model.getModelName()));
            if(modelOne != null) throw new CustomException(ResCodeEnum.MODEL_NAME_EXISTS);
            /*
             * 判断是否继承了父模型,不等于空相当于继承了父模型
             * 继承父模型之后，复制父模型的字段树(从数据库找到父模型树的根节点),
             * 然后根据这个根节点拿到整棵树，进而在将拿到的这棵树重新插入到数据库中
             */
            if(model.getParentModelId() != null){
                Model parent = modelMapper.selectOne(new QueryWrapper<Model>()
                        .eq(ModelLabelEnum.KEY_ID.getValue(),model.getParentModelId()));
                // 根据父模型中的field_tree_id 查到整棵树,然后再插入到数据库中，调用zyt方法，自己又写了一个
                List<FieldNode> treeFieldList = fieldNodeService.getFieldNodeList(parent.getFieldTreeId());
                log.info("字段树列表"+treeFieldList.toString());
                // List<FieldNode> 转为Map
                List<Map<String, Object>> maps = convertToMap(treeFieldList);

                // 使用引用类型拿到刚插入结点的根节点。
                // 为了获取到刚插入结点的根节点，在fieldNodeServiceImpl中重载了创建模型的方法
                Model modelRoot = new Model();
                boolean isSave = fieldNodeService.saveFieldNode(maps,modelRoot);
                log.info("rootID=" +modelRoot.getId());
                // 当插入失败时，抛出异常进行回滚，反馈给前端插入失败
                if(!isSave) throw new CustomException(ResCodeEnum.MODEL_CREATE_ERROR);
                // 覆盖fieldTreeId
                model.setFieldTreeId(modelRoot.getId());
            }

            // 不是继承直接插入模型
            Model m = new Model();
            m.setModelName(model.getModelName());
            m.setFieldTreeId(model.getFieldTreeId());
            m.setIndex(JSON.toJSONString(model.getIndex())); // 转为json字符串保存进去
            m.setRemark(model.getRemark());
            m.setParentModelId(model.getParentModelId());

            int rows = modelMapper.insert(m);
            log.info("插入模型" + m.toString());
            if(rows > 0){ // 插入成功创建mongoDb集合和索引
                // 解析index
                log.info("index"+JSON.toJSONString(model.getIndex()));
                List<Index> indexList = convertToIndex(model.getIndex(), model.getModelName());
                // TODO 创建mongoDb集合
                mongoDBUtil.createCollection(model.getModelName());
                log.info("创建集合成功");
                // TODO 创建mongoDb索引
                for (Index index : indexList){
                    mongoDBUtil.createIndex(index);
                }
                log.info("创建索引成功");
            }else {
                throw new CustomException(ResCodeEnum.MODEL_CREATE_ERROR);
            }
            log.info("完成模型创建");
        return true;
    }

    /**
     * @description  修改模型
     *
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/12 12:00
     **/
    @Transactional
    @Override
    public boolean updateModel(ModelParamDto modelParamDto){
        /**
         * 根据id定位数据,modelName、fieldTreeId、index、parentModelId、remark
         */
        // ①后端接受更新后的对象，并将ModelParamDto转为Model
        Model newModel = convertToModel(modelParamDto);

        Model modelOne = modelMapper.selectOne(new QueryWrapper<Model>()
                .eq(ModelLabelEnum.DB_KEY_MODEL_NAME.getValue(), newModel.getModelName()));
        if(modelOne != null && modelOne.getId() != modelParamDto.getId()) throw new CustomException(ResCodeEnum.MODEL_NAME_EXISTS);
        // ②根据id查询旧模型
        Model oldModel = this.getById(newModel.getId());
        log.info("newModel:"+newModel.toString());
        log.info("oldModel:"+oldModel.toString());
        // ③比较新旧模型哪些地方需要修改，并返回需要修改的地方
        Model model = judgeModelIsUpdate(newModel, oldModel);
        if (model == null)
            throw new CustomException(ResCodeEnum.MODEL_NOT_UPDATE_SUCCESS);
        log.info("更新模型" + model.toString());

        if(model.getParentModelId() !=null || model.getFieldTreeId() != null){
            //  判断mongoDb中是否存在数据
            QueryModel queryModel = new QueryModel(oldModel.getModelName(), null, null, null);
            List<String> res = mongoDBUtil.query(queryModel, String.class);
            if(res != null && res.size() !=0){
                // 存在数据 拒绝修改,查看是否有数据，存在数据拒绝修改
                throw new CustomException(ResCodeEnum.MODEL_DATA_EXISTS);
            }
        }
        // 使用修改项，更新mysql数据库的数据
        if(!this.updateById(model)){ // 更新失败，抛出异常进行回滚
            throw new CustomException(ResCodeEnum.MODEL_UPDATE_ERRORS);
        }

        //   更新成功之后更新mongo索引,并更新集合名
        try {
            if(StringUtils.isNotBlank(model.getModelName())){
                // 不为空则需要先修改集合名称
                mongoDBUtil.renameCollection(oldModel.getModelName(),model.getModelName());
            }else{ // 否则的话使用继续旧的集合名称
                model.setModelName(oldModel.getModelName());
            }
            // 更新索引
            mongoDBUtil.removeAllIndex(model.getModelName());
            List<Index> indexList = convertToIndex(convertToList(model.getIndex()), model.getModelName());
            for(Index index : indexList){
                mongoDBUtil.createIndex(index);
            }
        }catch (RuntimeException e){
            throw new CustomException(ResCodeEnum.MODEL_UPDATE_ERRORS);
        }
        return true;
    }
    /**
     * @description  判断模型是否需要修改，需要则返回所有修改项
     *  如果不需要,则返回null
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/12 15:37
     **/
    private Model judgeModelIsUpdate(Model newModel,Model oldModel){
        boolean modelNameIsEqual = newModel.getModelName().equals(oldModel.getModelName());
        boolean remarkIsEqual = newModel.getRemark().equals(oldModel.getRemark());
        boolean parentModelIdIsEqual = newModel.getParentModelId().equals(oldModel.getParentModelId());
        boolean fieldTreeIdIsEquals = newModel.getFieldTreeId().equals(oldModel.getFieldTreeId());
        boolean indexIsEquals = newModel.getIndex().equals(oldModel.getIndex());
        if(modelNameIsEqual) { // 不修改的字段进行清空、
            newModel.setModelName(null);
        }
        if(remarkIsEqual){ //不修改
            newModel.setRemark(null);
        }
        if(parentModelIdIsEqual){ // 不修改
            newModel.setParentModelId(null);
        }
        if(fieldTreeIdIsEquals){ // 不修改
            newModel.setFieldTreeId(null);
        }
        if(indexIsEquals){ // 不修改
            newModel.setIndex(null);
        }
        if(modelNameIsEqual && remarkIsEqual && parentModelIdIsEqual && fieldTreeIdIsEquals && indexIsEquals)
            return null;
        return newModel;
    }

    /**
     * @description TODO 将 ModelParamDto 转为 Model
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/12 15:45
     **/
    private Model convertToModel(ModelParamDto modelParamDto){
        Model model = new Model();
        model.setId(modelParamDto.getId());
        model.setModelName(modelParamDto.getModelName());
        model.setFieldTreeId(modelParamDto.getFieldTreeId());
        model.setParentModelId(modelParamDto.getParentModelId());
        model.setIndex(JSON.toJSONString(modelParamDto.getIndex()));
        model.setRemark(modelParamDto.getRemark());
        return model;
    }
    /**
     * @description TODO 将字符串转为 List<LinkedHashMap<String,Object>>
     * @return List<LinkedHashMap<String,Object>>
     * @exception
     * @author zyt
     * @date 2022/4/12 13:47
     **/
    private List<LinkedHashMap<String,Object>> convertToList(String index){
        List<LinkedHashMap<String,Object>> list = new ArrayList<>();
        List<JSONObject> jsonObjectList = (List<JSONObject>) JSONObject.parse(index,Feature.OrderedField);
        for(JSONObject jsonObject : jsonObjectList){
            LinkedHashMap<String,Object> fieldNames = new LinkedHashMap<>();
            for( Iterator<String> iterator = jsonObject.keySet().iterator();iterator.hasNext();){
                String key = iterator.next();
                Object object = jsonObject.get(key);
                fieldNames.put(key, object);
            }
            list.add(fieldNames);
        }
        log.info("解析完成:"+ JSON.toJSONString(list));
        return list;
    }
    /**
     * @description TODO 解析Index
     * @return List<Index>
     * @exception
     * @author zyt
     * @date 2022/4/12 11:44
     **/
    private List<Index> convertToIndex(List<LinkedHashMap<String, Object>> indexs,String modelName){
        List<Index> indexList = new ArrayList<>();
        for(LinkedHashMap<String,Object> index : indexs){
            log.info("解析index:"+JSON.toJSONString(index));
            String type = (String) index.get("type");
            Index index1 = new Index();
            index1.setCollectionName(modelName);
            LinkedHashMap<String,Integer> fieldNames = new LinkedHashMap<>();
            if(type.equals(Index.INDEX_SINGLE) || type.equals(Index.INDEX_HASH_KEY)){
                fieldNames.put((String)index.get("field"),(Integer)index.get("sort"));
            }else if(type.equals(Index.INDEX_COMPOSITE)){
                JSONObject jsonObject = new JSONObject(index);
                JSONArray field = jsonObject.getJSONArray("field");
                fieldNames = new LinkedHashMap<>();
                for (int i = 0; i < field.size(); i++) {
                    JSONObject json = (JSONObject) JSON.toJSON(field.get(i));
                    fieldNames.put((String)json.get("field"),(Integer) json.get("sort"));
                }
            }
            index1.setType(type);
            index1.setFieldNames(fieldNames);
            indexList.add(index1);
            log.info("解析完成：" +index1.getType()+" fieldsName"+index1.getFieldNames());
        }
        log.info("size：" + indexList.size()+" "+JSON.toJSONString(indexList));
        return indexList;
    }

    /**
     * @description  将List<FieldNode>转换为 List<Map>
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/10 21:22
     **/
    private List<Map<String, Object>> convertToMap(List<FieldNode> fieldNodes){
        List<Map<String, Object>> fields= new ArrayList<>();
        for (FieldNode fieldNode : fieldNodes){
            Map<String, Object> map = new HashMap<>();
            map.put(FieldNodeLabelEnum.KEY_ID.getValue(),fieldNode.getId());
            map.put(FieldNodeLabelEnum.KEY_DEFAULT_NAME.getValue(),fieldNode.getDefaultName());
            map.put(FieldNodeLabelEnum.KEY_FIELD_INFO_ID.getValue(),fieldNode.getFieldInfoId());
            map.put(FieldNodeLabelEnum.KEY_NODE_TYPE.getValue(),fieldNode.getNodeType());
            map.put(FieldNodeLabelEnum.KEY_PARENT_ID.getValue(),fieldNode.getParentId());
            fields.add(map);
        }
        return fields;
    }

    /**
     * @description TODO 删除模型 本质上是逻辑删除
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/11 19:37
     **/
    @Transactional
    @Override
    public boolean removeModel(Long[] ids) {
        List<Model> models = this.listByIds(Arrays.asList(ids));
        for(Model model : models){
            model.setIsDelete(Model.MODEL_DELETE);
        }
        if(!this.updateBatchById(models))
          throw new CustomException(ResCodeEnum.MODEL_DELETE_ERROR);
        log.info("删除模型成功"+ids);
        return true;
    }

    /**
     * @return
     * @throws
     * @description TODO 根据传入的id获取模型基本信息
     * @author Zrj
     * @date 2022/4/11 11:39
     **/
    @Override
    public Model getModel(String id) {
        Model model = modelMapper.selectById(Long.valueOf(id));
        return model;
    }

    @Override
    public ModelDetail getModelDetail(Model model) {
        if (model == null){
            return null;
        }
        ModelDetail modelDetail = new ModelDetail();
        modelDetail.setModel(model);
        FieldTreeVO fieldTreeVO = fieldNodeService.getFieldTree(model.getFieldTreeId());
        modelDetail.setFieldTree(fieldTreeVO);
        return modelDetail;
    }

    /**
     * @return
     * @throws
     * @description TODO 调用modelMapper中的listModel查询模型基本信息
     * @author Zrj
     * @date 2022/4/11 11:27
     **/
    @Override
    public List<Model> getListModel(Model model) {
        List<Model> list = modelMapper.listModel(model);
        return list;
    }


}
