package com.ljm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljm.entity.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends BaseMapper<SysUser> {

	List<Long> getNavMenuIds(Long userId);

	List<SysUser> listByMenuId(Long menuId);

	@Select("select * from sys_user where username = #{username}")
	Map<String, Object> getSysUser(String username);
}
