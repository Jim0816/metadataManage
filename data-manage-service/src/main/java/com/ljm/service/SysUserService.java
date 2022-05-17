package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.entity.SysUser;

import java.util.List;


public interface SysUserService extends IService<SysUser> {

	SysUser getByUsername(String username);

	String getUserAuthorityInfo(Long userId);

	void clearUserAuthorityInfo(String username);

	void clearUserAuthorityInfoByRoleId(Long roleId);

	void clearUserAuthorityInfoByMenuId(Long menuId);

	/**
	 * @description TODO 通过ID出对应用户
	 * @return
	 * @exception
	 * @author gcy
	 * @date 2022/4/10 13:04
	 **/
	List<SysUser> getUserList(String[] ids);
}
