package com.ljm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.entity.FieldInfo;
import org.apache.ibatis.annotations.Select;
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
public interface FieldInfoMapper extends BaseMapper<FieldInfo> {
    //获取所有字段信息
    List<FieldInfo> getFieldList();

    //通过id得到字段信息
    FieldInfo getField();

    /**
     * @description TODO 这个方法是否需要，考虑废弃？
     * @return
     * @exception
     * @author zjm
     * @date 2022/4/7 19:28
     **/
    FieldInfo getFieldByName(FieldInfo fieldInfo);

    IPage<FieldInfo> getFieldInfoList(Page<FieldInfo> page, FieldInfo fieldInfo);
}
