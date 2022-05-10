package com.ljm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljm
 * @since 2022-02-22
 */
public interface ModelMapper extends BaseMapper<Model> {


    IPage<Model> listModelDetailList(Page<Model> page, Model model);


    /**
     * @description TODO 获取模型基本信息
     * @return
     * @exception
     * @author Zrj
     * @date 2022/4/11 11:29
     **/
    List<Model> listModel(@Param("model") Model model);


}
