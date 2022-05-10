package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.dto.ModelParamDto;
import com.ljm.entity.Model;
import com.ljm.bo.ModelDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljm
 * @since 2022-02-22
 */
public interface ModelService extends IService<Model> {
    /**
     * @description  创建数据模型
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/10 18:14
     **/
    boolean createModel(ModelParamDto model);

    /**
     * @description TODO 根据传入的模型id获取模型基本信息
     * @return
     * @exception
     * @author Zrj
     * @date 2022/4/11 11:29
     **/
    Model getModel(String id);

    /**
     * @description TODO 获取模型详细信息
     * @return
     * @exception
     * @author Gcy
     * @date 2022/3/29 16:16
     **/
    ModelDetail getModelDetail(Model model);

    /**
     * @description TODO 根据传入的若干条件查询模型的基本信息
     * @return
     * @exception
     * @author Zrj
     * @date 2022/4/11 11:19
     **/
    List<Model> getListModel(Model model);


    /**
     * @description  删除模型
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/12 11:58
     **/
    boolean removeModel(Long[] ids);

    /**
     * @description TODO 修改模型
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/12 11:58
     **/
    boolean updateModel(ModelParamDto modelParamDto);

}
