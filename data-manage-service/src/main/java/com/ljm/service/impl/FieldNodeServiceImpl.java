package com.ljm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.bo.FieldNodeTree;
import com.ljm.entity.FieldInfo;
import com.ljm.entity.FieldNode;
import com.ljm.entity.Model;
import com.ljm.enums.FieldLabelEnum;
import com.ljm.enums.FieldNodeLabelEnum;
import com.ljm.mapper.FieldInfoMapper;
import com.ljm.mapper.FieldNodeMapper;
import com.ljm.mapper.ModelMapper;
import com.ljm.service.FieldNodeService;
import com.ljm.vo.FieldNodeVO;
import com.ljm.vo.FieldTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class FieldNodeServiceImpl extends ServiceImpl<FieldNodeMapper, FieldNode> implements FieldNodeService {

    @Autowired
    private FieldNodeMapper fieldNodeMapper;

    @Autowired
    private FieldInfoMapper fieldInfoMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FieldNode getFieldNode(Long id) {
        return baseMapper.selectOne(new QueryWrapper<FieldNode>().eq("id", id));
    }

    /**
     * @description  TODO 根据字段数的根结点ID,获取字段数信息 待测试
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/7 10:42
     **/
    @Override
    public FieldTreeVO getFieldTree(Long rootId) {
        //根据id在数据库中查询;
        Queue<FieldNode> queue = new LinkedList<>();

        // 取数据中查询
        FieldNode fieldNode = fieldNodeMapper.selectById(rootId);
        List<FieldNodeVO> fields = new ArrayList<>();

        //if(fieldNode == null) throw
        queue.offer(fieldNode);

        FieldNodeVO fieldNodeVO = new FieldNodeVO(fieldNode,null);

        // 这是根节点将FieldNode 转化为FieldNodeV
        fields.add(fieldNodeVO);
        while(!queue.isEmpty()){
            List<FieldNode> fieldNodeList = fieldNodeMapper.selectList(new QueryWrapper<FieldNode>().eq(FieldNodeLabelEnum.DB_KEY_PARENT_ID.getValue(), queue.poll().getId()));
            //拿到了根据父id查询出来的id
            for(FieldNode node : fieldNodeList){
                queue.offer(node);
                // 查询叶节点的fieldInfo;
                FieldInfo fieldInfo = fieldInfoMapper.selectById(node.getFieldInfoId());
                fields.add(new FieldNodeVO(node,fieldInfo));
            }
        }

        // 此时已经拿到这个列表，将这个列表构造成树的形式
        List<FieldNodeVO> childFields = fields.stream().filter(e ->{
            return FieldNode.NODE_TYPE_ROOT == e.getNodeType();
        }).collect(Collectors.toList());

        FieldTreeVO fieldTree = new FieldTreeVO();
        getFieldTreeStruct(fields,childFields,fieldTree);

        if(fieldTree != null ) return fieldTree;
        return null;
    }

    /**
     * @description TODO 查询出来的数据构造为树的形式,没有删除之前的方法，重载了一个。
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/6 21:13
     **/
    private void getFieldTreeStruct(List<FieldNodeVO> fields,List<FieldNodeVO> childFields,FieldTreeVO fieldTree){

        // 遍历childFields
        for(FieldNodeVO fieldNodeVO : childFields){
            long id = fieldNodeVO.getId();
            if(fieldNodeVO.getNodeType() == FieldNode.NODE_TYPE_ROOT){
                fieldTree.setNode(fieldNodeVO);
                fieldTree.setChildren(new ArrayList<FieldTreeVO>());
                // 得到根节点的所有孩子结点的配置信息
                childFields = fields.stream().filter(e ->{
                    return id == e.getParentId();
                }).collect(Collectors.toList());
                //递归
                getFieldTreeStruct(fields,childFields,fieldTree);
            }else if(fieldNodeVO.getNodeType() == FieldNode.NODE_TYPE_PARENT || fieldNodeVO.getNodeType() == FieldNode.NODE_TYPE_FIELD_EXIST){
                // 判断父节点是否已经存在
                FieldNodeVO parent = fieldTree.getNode();
                if(parent != null && parent.getId().equals(fieldNodeVO.getParentId())){
                    // 父节点信息提取成功 -> 添加该子节点进入字段树
                    FieldTreeVO childNode = new FieldTreeVO();
                    childNode.setNode(fieldNodeVO);
                    childNode.setChildren(new ArrayList<FieldTreeVO>());
                    fieldTree.getChildren().add(childNode);

                    // 如果是中间结点，需要递归
                    if(fieldNodeVO.getNodeType() == FieldNode.NODE_TYPE_PARENT){
                        childFields = fields.stream().filter(e ->{
                            return id == e.getParentId();
                        }).collect(Collectors.toList());
                        // 递归
                        getFieldTreeStruct(fields,childFields,childNode);
                    }
                }
            }
        }
    }


    /**
     * @description TODO 获取字段树列表
     * @return
     * @exception
     * @author cyf
     **/
    public List<FieldTreeVO> getFieldTreeList() {
        QueryWrapper<FieldNode> wrapperRoot = new QueryWrapper<FieldNode>();
        wrapperRoot.eq(FieldLabelEnum.DB_KEY_NODE_TYPE.getValue(), FieldNode.NODE_TYPE_ROOT);
        List<FieldNode> rootList = fieldNodeMapper.selectList(wrapperRoot);

        List<FieldTreeVO> fieldTreeVoList = new ArrayList<FieldTreeVO>();
        for (FieldNode fn : rootList){
            FieldTreeVO fieldTree = getFieldTree(fn.getId());
            if(!fieldTree.toString().isEmpty()){
                fieldTreeVoList.add(fieldTree);
            }
        }

        return fieldTreeVoList;
    }

   /**
    * @description TODO 添加字段树
    * @return boolean
    * @exception
    * @author Gcy
    * @date 2022/4/4 19:33
    **/
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public boolean saveFieldNode(List<Map<String, Object>> fields) {
        //准备添加字段树所使用的字段
        //1、提取字段树
        FieldNodeTree fieldNodeTree = new FieldNodeTree();
        List<Map<String, Object>> childFields = fields.stream().filter(e -> {
            return FieldNode.NODE_TYPE_ROOT == Integer.valueOf(e.get(FieldLabelEnum.KEY_NODE_TYPE.getValue()).toString());
        }).collect(Collectors.toList());
        getFieldTreeStruct(fields, childFields, fieldNodeTree);

        //2、判断字段树是否存在
        //judgeFieldTreeIsExist(fieldNodeTree);
        //3存储字段树-1为根节点的父节点
        createFieldTree(fieldNodeTree, -1);
        return true;
    }

    /**
     * @description  保存父模型中复制的字段树，并拿到刚插入树的根结点
     * 把根节点的值保存在Model中，不使用引用类型无法获取值  TODO 移到字段树fieldNodeService
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/11 9:16
     **/
    @Transactional
    public boolean saveFieldNode(List<Map<String, Object>> fields, Model modelRoot) {
        //准备添加字段树所使用的字段
        //1、提取字段树
        FieldNodeTree fieldNodeTree = new FieldNodeTree();
        List<Map<String, Object>> childFields = fields.stream().filter(e -> {
            return FieldNode.NODE_TYPE_ROOT == Integer.valueOf(e.get(FieldLabelEnum.KEY_NODE_TYPE.getValue()).toString());
        }).collect(Collectors.toList());
//        getFieldTreeStructFromInfo(fields, childFields, fieldNodeTree);
        getFieldTreeStruct(fields, childFields, fieldNodeTree);
        //3字段树-1为根节点的父节点
        createFieldTree(fieldNodeTree, -1,modelRoot);
        return true;
    }

    /**
     * @description TODO 根据前端传来的字段树根节点删除整棵树
     * @return Boolean
     * @exception
     * @author Gcy
     * @date 2022/4/5 21:52
     **/
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public boolean deleteFieldNode(List<Long> ids) {
        for (long id : ids){
            Queue<FieldNode> queue = new LinkedList<FieldNode>();
            QueryWrapper<FieldNode> wrapperRoot = new QueryWrapper<FieldNode>();
            wrapperRoot.eq("id",id);
            FieldNode fd =fieldNodeMapper.selectOne(wrapperRoot);
            int f = fieldNodeMapper.selectCount(wrapperRoot);
            //判断是否传入的是根节点
            if (fd.getNodeType() != fd.NODE_TYPE_ROOT || f == 0){
                return false;
            }

            //判断是否有模型引用字段树,如果有引用返回false
            QueryWrapper<Model> wrapperModel = new QueryWrapper<>();
            int model = modelMapper.selectCount(wrapperModel.eq("field_tree_id",id));
            if(model != 0){
                return false;
            };

            queue.offer(fd);//将根节点进入队列

            while ( ! queue.isEmpty()){

                //查询根节点的所有孩子节点
                QueryWrapper<FieldNode> wrapper = new QueryWrapper<FieldNode>();
                wrapper.eq("parent_id",queue.peek().getId());
                List<FieldNode> listNodeId=fieldNodeMapper.selectList(wrapper);

                fieldNodeMapper.deleteById(queue.poll().getId());//删除根节点

                for (FieldNode fds:listNodeId){//遍历孩子进入队列
                    queue.offer(fds);
                }
            }
        }
        return true;
    }

    @Override
    /**
     * @description TODO 根据前端传来的字段树去匹配数据库中的然后进行结点上的修改删除插入
     * @return boolean
     * @exception
     * @author Gcy
     * @date 2022/4/6 17:22
     **/
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public boolean updateFieldNode(List<FieldNode> fields) {
        //1、先取到前端传入的根节点id，然后查询到数据库中的整颗树，并将树存入到hashmap中，
        // 2、然后遍历前端传入的fieldnode依次跟hashmap比较，如果没有在map查到则添加，
        // 3、如果查询到比较内容是否相同，若不同则修改。并且将查询到的结点在hashmap进行删除
        // 4、最后遍历hashmap进行删除结点。
        FieldNode rootFd = new FieldNode();
        for (FieldNode fd : fields){
            if (fd.getNodeType() == fd.NODE_TYPE_ROOT){
                rootFd = fd;
                break;
            }
        }
        //层次遍历，然后将字段树存入hashmap
        HashMap<Long,FieldNode> dbFieldNode = new HashMap<Long,FieldNode>();
        Queue<FieldNode> queue = new LinkedList<FieldNode>();
        QueryWrapper<FieldNode> wrapperRoot = new QueryWrapper<FieldNode>();
        wrapperRoot.eq("id",rootFd.getId());
        FieldNode fd =fieldNodeMapper.selectOne(wrapperRoot);
        queue.offer(fd);//将根节点进入队列
        while (!queue.isEmpty()){
            //查询根节点的所有孩子节点
            QueryWrapper<FieldNode> wrapper = new QueryWrapper<FieldNode>();
            wrapper.eq("parent_id",queue.peek().getId());
            List<FieldNode> listNodeId=fieldNodeMapper.selectList(wrapper);

            dbFieldNode.put(queue.peek().getId(),queue.poll());  //将结点存入map，并将id设置为key

            for (FieldNode fds:listNodeId){//遍历孩子进入队列
                queue.offer(fds);
            }
        }
        for (FieldNode fds : fields){
            if (dbFieldNode.containsKey(fds.getId())){
                FieldNode curFd = dbFieldNode.get(fds.getId());
                if (curFd.getParentId() != fds.getParentId()
                        || curFd.getNodeType() != fds.getNodeType()
                        || curFd.getDefaultName() != fds.getDefaultName()
                        || curFd.getFieldInfoId() != fds.getFieldInfoId()){
                    fieldNodeMapper.updateById(fds);
                    dbFieldNode.remove(fds.getId());
                }
            }else {
                fds.setId(null);
                fieldNodeMapper.insert(fds);
            }
        }

        for (FieldNode fds : dbFieldNode.values()){
            fieldNodeMapper.deleteById(fds.getId());
        }
        return true;
    }


    @Override
    public List<FieldNode> getFieldNodeList(Long rootId){
        //根据id在数据库中查询;
        Queue<FieldNode> queue = new LinkedList<>();
        // 取数据中查询
        FieldNode fieldNode = fieldNodeMapper.selectById(rootId);
        List<FieldNode> fields = new ArrayList<>();
        //if(fieldNode == null) throw
        queue.offer(fieldNode);

        // 这是根节点将FieldNode 转化为FieldNodeV
        fields.add(fieldNode);
        while(!queue.isEmpty()){
            List<FieldNode> fieldNodeList = fieldNodeMapper.selectList(new QueryWrapper<FieldNode>().eq(FieldNodeLabelEnum.DB_KEY_PARENT_ID.getValue(), queue.poll().getId()));
            //拿到了根据父id查询出来的id
            for(FieldNode node : fieldNodeList){
                queue.offer(node);
                fields.add(node);
            }
        }
        if(fields.size() > 0) return fields;
        return null;
    }


    /**
     * @description TODO 判断字段树是否存在，不存在返回false，存在返回true
     * @return
     * @exception
     * @author Gcy
     * @date 2022/4/4 19:48
     **/
    private int judgeFieldTreeIsExist(FieldNodeTree fieldNodeTree){
        List<FieldNodeTree> children=fieldNodeTree.getChildren();
        FieldNode root = null;
        if(children!=null){
            for (FieldNodeTree fieldNodeTreeItem :children){
                int flag=fieldNodeMapper.selectCount(new QueryWrapper<FieldNode>().eq("node_type",fieldNodeTreeItem.getNode().getNodeType())
                        .eq("field_info_id",fieldNodeTreeItem.getNode().getFieldInfoId()));
                if(flag==0){return -1;}
                root=fieldNodeMapper.selectOne(new QueryWrapper<FieldNode>().eq("node_type",fieldNodeTreeItem.getNode().getNodeType())
                        .eq("field_info_id",fieldNodeTreeItem.getNode().getFieldInfoId()));
                if(judgeFieldTreeIsExist(fieldNodeTreeItem)==-1){return -1;}
            }
        }

        return root.getParentId().intValue();
    }


    /**
     * @description TODO 从模型配置信息中提取出字段树的结构信息 算法需要优化
     * @param fields 所有字段结构信息列表
     * @param childFields 本层待加入的节点配置信息列表
     * @param fieldNodeTree 构建的字段树  指针引用传值
     * @return
     * @exception
     * @author Gcy
     * @date 2022/4/4 19:49
     **/
    private void getFieldTreeStruct(List<Map<String, Object>> fields,
                                    List<Map<String, Object>> childFields,
                                    FieldNodeTree fieldNodeTree){
        for (Map<String, Object> fieldMap : childFields){
            long id = Long.valueOf(fieldMap.get(FieldLabelEnum.KEY_ID.getValue()).toString());
            int nodeType = Integer.valueOf(fieldMap.get(FieldLabelEnum.KEY_NODE_TYPE.getValue()).toString());
            long parentId = Long.valueOf(fieldMap.get(FieldLabelEnum.KEY_PARENT_ID.getValue()).toString());
            long fieldInfoId = Long.valueOf(fieldMap.get(FieldLabelEnum.KEY_FIELD_INFO_ID.getValue()).toString());
            String defaultName = fieldMap.get(FieldLabelEnum.KEY_DEFAULT_NAME.getValue()).toString();

            FieldNode fieldNode = new FieldNode(id, nodeType, parentId, fieldInfoId, defaultName);

            if (nodeType == FieldNode.NODE_TYPE_ROOT){
                // 更新配置信息: 从配置信息中移除当前节点
                fieldNodeTree.setNode(fieldNode);
                fieldNodeTree.setChildren(new ArrayList<FieldNodeTree>());
                // 得到根节点的所有孩子节点配置信息
                childFields = fields.stream().filter(e -> {
                    return id == Integer.valueOf(e.get(FieldLabelEnum.KEY_PARENT_ID.getValue()).toString());
                }).collect(Collectors.toList());

                // 递归
                getFieldTreeStruct(fields, childFields, fieldNodeTree);
            } else if (nodeType == FieldNode.NODE_TYPE_PARENT || nodeType == FieldNode.NODE_TYPE_FIELD_EXIST){
                // 判断父节点是否已经存在
                FieldNode parent = fieldNodeTree.getNode();
                if (parent != null && parent.getId() == parentId){
                    // 父节点信息已经提取成功 -> 添加该子节点进入字段树
                    FieldNodeTree childNode = new FieldNodeTree();
                    childNode.setNode(fieldNode);
                    childNode.setChildren(new ArrayList<FieldNodeTree>());
                    fieldNodeTree.getChildren().add(childNode);

                    // 如果是中间节点（复合属性），需要递归
                    if (nodeType == FieldNode.NODE_TYPE_PARENT){
                        childFields = fields.stream().filter(e -> {
                            return id == Integer.valueOf(e.get(FieldLabelEnum.KEY_PARENT_ID.getValue()).toString());
                        }).collect(Collectors.toList());

                        // 递归 childNode为该复合字段节点
                        getFieldTreeStruct(fields, childFields, childNode);
                    }
                }
            }
        }


    }

    /**
     * @description TODO 存储字段树结构信息
     * @fieldNodeTree 提取到的 字段树结构信息对象
     * @return 返回根节点ID
     * @exception
     * @author Gcy
     *
     * @date 2022/4/4 19:50
     **/
    private boolean createFieldTree(FieldNodeTree fieldNodeTree, long parentId){
        FieldNode fieldNode = null;
        List<FieldNodeTree> children = null;
        // 存储当前节点
        if (fieldNodeTree != null && (fieldNode = fieldNodeTree.getNode()) != null){
            // id需要按照数据库主键分配
            fieldNode.setId(null);
            fieldNode.setParentId(parentId);
            fieldNodeMapper.insert(fieldNode);
        }

        // 存储当前节点的子节点
        children = fieldNodeTree.getChildren();
        if (children != null){
            for (FieldNodeTree fieldNodeTreeItem : children) {
                createFieldTree(fieldNodeTreeItem, fieldNode.getId());
            }
        }

        return true;
    }

    /**
     * @description  拿到刚插入的rootId
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/11 9:16
     **/
    private boolean createFieldTree(FieldNodeTree fieldNodeTree, long parentId,Model modelRoot){
        FieldNode fieldNode = null;
        List<FieldNodeTree> children = null;
        // 存储当前节点
        if (fieldNodeTree != null && (fieldNode = fieldNodeTree.getNode()) != null){
            // id需要按照数据库主键分配
            fieldNode.setId(null);
            fieldNode.setParentId(parentId);
            fieldNodeMapper.insert(fieldNode);
            if(parentId == -1) modelRoot.setId(fieldNode.getId());//回溯拿到根结点id
            //           log.info("回溯拿到复制字段树之后的根结点id:"+fieldNode.getId()+"---"+parentId+"----" + modelRoot.getId());
        }
        // 存储当前节点的子节点
        children = fieldNodeTree.getChildren();
        if (children != null){
            for (FieldNodeTree fieldNodeTreeItem : children) {
                createFieldTree(fieldNodeTreeItem, fieldNode.getId(),modelRoot);
            }
        }
        return true;
    }

    /**
     * @description TODO 根据传入的rootid从数据库中获取整个树的节点list
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/2 20:53
     **/
    @Override
    public List<FieldNode> getTreeField(Long id) {
        List<FieldNode> list = new ArrayList<>();
        List<FieldNode> tmplist= fieldNodeMapper.selectList(new QueryWrapper<FieldNode>().eq("parent_id",id));
        if(tmplist != null){
            list.addAll(tmplist);
        }
        List<FieldNode> fieldNode = fieldNodeMapper.selectList(new QueryWrapper<FieldNode>()
                .eq("parent_id",id).eq("field_info_id",-1));
        if(fieldNode != null){
            for (FieldNode ft : fieldNode)
                list.addAll(getTreeField(ft.getId()));
        }

        return list;
    }

    /**
     * @description TODO 根据传入树返回该树所有字段集合
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/2 21:17
     **/
    @Override
    public List<FieldNode> TreeFields(FieldNodeTree fieldNodeTree) {
        List<FieldNode> list = new ArrayList<>();
        list.add(fieldNodeTree.getNode());
        if(fieldNodeTree.getChildren()!=null){
            for (FieldNodeTree ft:fieldNodeTree.getChildren()){
                list.addAll(TreeFields(ft));
            }
        }
        return list;
    }

}
