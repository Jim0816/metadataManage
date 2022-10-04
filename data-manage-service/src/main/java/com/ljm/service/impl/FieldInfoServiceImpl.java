package com.ljm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.dto.FieldInfoDto;
import com.ljm.entity.FieldInfo;
import com.ljm.enums.FieldLabelEnum;
import com.ljm.enums.ResCodeEnum;
import com.ljm.except.CustomException;
import com.ljm.mapper.FieldInfoMapper;
import com.ljm.service.FieldInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljm
 * @since 2022-02-22
 */

@Slf4j
@Service
public class FieldInfoServiceImpl extends ServiceImpl<FieldInfoMapper, FieldInfo> implements FieldInfoService {

    /**
     * @description 判断字段是否已经存在 主键：字段名称+字段类型+是否必须+是否唯一+默认值
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/16 17:11
     **/
    public boolean judgeFieldIsExist(FieldInfo fieldInfo){
        // 1.先查询出所有重名字段
        List<FieldInfo> list = baseMapper.selectList(new QueryWrapper<FieldInfo>()
                .eq(FieldLabelEnum.DB_KEY_FIELD_NAME.getValue(), fieldInfo.getFieldName()));
        // 2.记录重复字段
        for (FieldInfo field : list){
            // 除了名称，只要存在一项不相同，就认为二者不相同（备注不算）
            boolean flag = (field.getFieldType() != fieldInfo.getFieldType()) || (field.getLength() != fieldInfo.getLength())
                    || (field.getIsRequire() != fieldInfo.getIsRequire()) || (field.getIsUnique() != fieldInfo.getIsUnique())
                    || (field.getDefaultValue() != fieldInfo.getDefaultValue());
            if (!flag){
                // 相同，就直接返回true
                return true;
            }
        }
        // 没有相同字段 -> 不存在
        return false;
    }

    /**
     * @return
     * @throws
     * @description TODO 在插入字段时进行判断所插入的字段是否存在，不存时进行插入
     * @author Zrj
     * @date 2022/4/5 16:16
     **/
    @Override
    public int add(FieldInfo fieldInfo) {
        if (judgeFieldIsExist(fieldInfo)){
            // 存在重复字段
            throw new CustomException(ResCodeEnum.FIELD_EXISTED_CREATE_ERROR);
        }
        return baseMapper.insert(fieldInfo);
    }

    /**
     * @return
     * @throws
     * @description TODO
     * @author Zrj
     * @date 2022/4/5 16:34
     **/
    @Override
    public int update(FieldInfo fieldInfo) {
        if (judgeFieldIsExist(fieldInfo)){
            // 存在重复字段
            throw new CustomException(ResCodeEnum.FIELD_EXISTED_UPDATE_ERROR);
        }
        return baseMapper.updateById(fieldInfo);
    }

    /**
     * @return FieldInfo 按条件查询
     * @throws
     * @description TODO 优化代码 字段不能写成硬编码名称
     * @author zjm
     * @date 2022/4/7 11:13
     **/
    @Override
    public FieldInfo get(FieldInfo fieldInfo) {
        Map<String, Object> map = new HashMap<>();

        if (fieldInfo.getId() != null)
            map.put("id", fieldInfo.getId());

        if(fieldInfo.getFieldName() != null && !fieldInfo.getFieldName().equals(""))
            map.put("field_name",fieldInfo.getFieldName());

        if(fieldInfo.getFieldType() != null && !fieldInfo.getFieldType().equals(""))
            map.put("field_type",fieldInfo.getFieldType());

        if (fieldInfo.getLength() != null)
            map.put("length", fieldInfo.getLength());

        if (fieldInfo.getIsRequire() != null)
            map.put("is_require", fieldInfo.getIsRequire());

        if (fieldInfo.getIsUnique() != null)
            map.put("is_unique", fieldInfo.getIsUnique());

        if(fieldInfo.getDefaultValue()!=null&&!fieldInfo.getDefaultValue().equals(""))
            map.put("default_value",fieldInfo.getDefaultValue());

        if(fieldInfo.getRemark()!=null&&!fieldInfo.getRemark().equals(""))
            map.put("remark",fieldInfo.getRemark());

        if(baseMapper.selectByMap(map).size()>0)
            return baseMapper.selectByMap(map).get(0);

        return null;
    }

    /**
     * @return List<FieldInfo> 无条件查询
     * @throws
     * @description Done
     * @author zjm
     * @date 2022/4/7 11:14
     **/
    @Override
    public List<FieldInfo> list() {
        return baseMapper.selectList(null);
//        return fieldInfoMapper.getFieldList();
    }

    /**
     * @description TODO 批量删除
     * @return
     * @exception
     * @author Zrj
     * @date 2022/4/11 13:36
     **/
    @Override
    public int remove(List<Long> ids) {
        if (ids.size() <= 0) {
            return -1;
        }
        baseMapper.deleteBatchIds(ids);
        return 1;
    }


    /**
     * @description TODO  在添加模型时判断所需要的字段是否存在，存在则返回字段ID，不存在则插入
     * @return
     * @throws
     * @author Zrj
     * @date 2022/4/5 16:15
     **/
    @Override
    public Long getFieldIdWhenAddField(FieldInfo fieldInfo) {
        QueryWrapper queryWrapper = new QueryWrapper<FieldInfo>();
        queryWrapper.eq(FieldLabelEnum.DB_KEY_FIELD_NAME.getValue(),
                fieldInfo.getFieldName());
        queryWrapper.eq(FieldLabelEnum.DB_KEY_FIELD_TYPE.getValue(),
                fieldInfo.getFieldType());
        queryWrapper.eq(FieldLabelEnum.DB_KEY_LENGTH.getValue(),
                fieldInfo.getLength());
        queryWrapper.eq(FieldLabelEnum.DB_KEY_IS_REQUIRE.getValue(),
                fieldInfo.getIsRequire());
        queryWrapper.eq(FieldLabelEnum.DB_KEY_IS_UNIQUE.getValue(),
                fieldInfo.getIsUnique());
        FieldInfo result = baseMapper.selectOne(queryWrapper);

        if (result != null) {
            // 存在重复字段
            log.info("当前字段名称已经存在,无需要重复创建，直接引用,字段：" + fieldInfo.getFieldName());
            return result.getId();
        }
        if (baseMapper.insert(fieldInfo) > 0) {
            // 插入成功
            return fieldInfo.getId();
        } else {
            // 插入失败
            log.error("当前字段插入失败,字段：" + fieldInfo.getFieldName());
            return Long.valueOf(0);
        }

    }

    @Autowired
    private FieldInfoMapper fieldInfoMapper;

    @Override
    public FieldInfo getField(FieldInfo fieldInfo) {
        return fieldInfoMapper.getField();
    }

    /**
     * @return
     * @throws
     * @description 多条件查询字段信息，目前所有字段都有，如果有的不需要，再进行修改。
     * 前端可以不传下面所有的条件，下面的不用注释，注释后执行时，xml里面会出问题。
     * @author zyt
     * @date 2022/4/4 16:34
     **/
    @Override
    public List<FieldInfo> getFieldList(FieldInfoDto fieldInfoDto) {
//        baseMapper.listFieldInfo();
        Page<FieldInfo> page = new Page<>(fieldInfoDto.getCurrentPage(), fieldInfoDto.getPageSize());

        // 以后需要哪些查询  具体再修正
        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.setId(fieldInfoDto.getId());
        fieldInfo.setFieldName(fieldInfoDto.getFieldName());
        fieldInfo.setRemark(fieldInfoDto.getRemark());
        fieldInfo.setFieldType(fieldInfoDto.getFieldType());
        fieldInfo.setLength(fieldInfoDto.getLength());
        fieldInfo.setIsRequire(fieldInfoDto.getIsRequire());
        fieldInfo.setIsUnique(fieldInfoDto.getIsUnique());
        fieldInfo.setDefaultValue(fieldInfoDto.getDefaultValue());
        IPage<FieldInfo> infoIPage = fieldInfoMapper.getFieldInfoList(page, fieldInfo);
        List<FieldInfo> records = infoIPage.getRecords();
        //没有VO类，暂时直接返回结果;
        return records;
    }
}
