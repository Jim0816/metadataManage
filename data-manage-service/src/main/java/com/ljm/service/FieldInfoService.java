package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.dto.FieldInfoDto;
import com.ljm.entity.FieldInfo;

import java.util.List;

public interface FieldInfoService extends IService<FieldInfo> {

    /**
     * @description 添加字段
     * @return 操作成功行数
     * @exception
     * @author Jim
     * @date 2022/2/27 15:32
     **/
    int add(FieldInfo fieldInfo);

    /**
     * @description TODO 更新字段信息
     * @return
     * @exception
     * @author Zrj
     * @date 2022/4/5 16:33
     **/
    int update(FieldInfo fieldInfo);

    /**
     * @description TODO 删除字段
     * @return
     * @exception
     * @author Zrj
     * @date 2022/4/5 21:21
     **/
    int remove(List<Long> ids);

    /**
     * @description TODO 删除字段
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/7 19:31
     **/
//    int remove(FieldInfo fieldInfo);

    /**
     * @description  查询字段
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/1 13:50
     **/
    FieldInfo get(FieldInfo fieldInfo);

    /**
     * @description 获取字段列表
     * @return
     * @exception
     * @author zjm
     * @date 2022/3/29 16:21
     **/
    List<FieldInfo> list();

    /**
     * @description TODO 插入新字段，并且返回字段ID
     * @return 新字段ID
     * @exception
     * @author Jim
     * @date 2022/2/26 11:06
     **/
    Long getFieldIdWhenAddField(FieldInfo fieldInfo);

    /**
     * @description TODO 获取字段信息
     * @return
     * @exception
     * @author zjm
     * @date 2022/3/29 16:20
     **/
    FieldInfo getField(FieldInfo fieldInfo);


    /**
     * @description 多条件查询字段
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/4 16:31
     **/
    List<FieldInfo> getFieldList(FieldInfoDto fieldInfoDto);

}
