package com.ljm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljm.entity.Api;
import com.ljm.entity.ApiAcl;
import com.ljm.entity.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApiMapper extends BaseMapper<Api> {

    @Select("select * from tb_api where id = #{id} limit 0, 1")
    Api getApi(Integer id);


    @Select("select tb1.id, tb1.username, tb1.email, tb1.password from sys_user tb1, tb_api_acl tb2 where tb1.id = tb2.user_id and tb2.api_id = #{apiId}")
    List<SysUser> getAccessorList(Long apiId);
}
