package com.ljm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljm.entity.FieldNode;
import com.ljm.vo.FieldNodeVO;
import org.mapstruct.Mapper;
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
@Mapper
@Repository
public interface FieldNodeMapper extends BaseMapper<FieldNode> {

    //获取字段树的根
   FieldNodeVO getRoot();

   //获取字段树的孩子数组
   List<FieldNodeVO> getFieldTreeChildren();




}
