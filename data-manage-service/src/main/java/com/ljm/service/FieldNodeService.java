package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.bo.FieldNodeTree;
import com.ljm.entity.FieldNode;
import com.ljm.entity.Model;
import com.ljm.vo.FieldNodeVO;
import com.ljm.vo.FieldTreeVO;

import java.util.List;
import java.util.Map;


public interface FieldNodeService extends IService<FieldNode> {

    /**
     * @description 获取节点对象
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/21 15:31
     **/
    FieldNode getFieldNode(Long id);

    /**
     * @description TODO 根据字段数的根结点ID,获取字段数信息
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/6 17:08
     **/
    FieldTreeVO getFieldTree(Long rootId);

    /**
     * @description TODO 查询字段树列表
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/11 10:45
     **/
    List<FieldNode> getFieldNodeList(Long rootId);

    /**
     * @description TODO 获取字段树列表
     * @return
     * @exception
     * @author cyf
     **/
    List<FieldTreeVO> getFieldTreeList();

    /**
     * @description 存储字段树
     * @return
     * @exception
     * @author gcy
     * @date 2022/4/7 9:28
     **/
    boolean saveFieldNode(List<Map<String, Object>> fieldNode);

    /**
     * @description  存储字段树
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/19 17:23
     **/
    boolean saveFieldNode(List<Map<String, Object>> fields, Model modelRoot);

    /**
     * @description 删除字段树
     * @return
     * @exception
     * @author gcy
     * @date 2022/4/7 9:28
     **/
    boolean deleteFieldNode(List<Long> ids);

    /**
     * @description 更新字段树
     * @return
     * @exception
     * @author gcy
     * @date 2022/4/7 9:28
     **/
    boolean updateFieldNode(List<FieldNode> fieldNode);

    /**
     * @description TODO 根据rootid获取该树所有字段
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/2 19:05
     */
    List<FieldNode> getTreeField(Long id);

    /**
     * @description TODO 根据传入树返回该树所有字段集合
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/2 21:17
     */
    List<FieldNode> TreeFields(FieldNodeTree fieldNodeTree);

}
