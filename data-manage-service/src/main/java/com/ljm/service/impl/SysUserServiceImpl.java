package com.ljm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.entity.SysMenu;
import com.ljm.entity.SysRole;
import com.ljm.entity.SysUser;
import com.ljm.mapper.SysUserMapper;
import com.ljm.service.SysMenuService;
import com.ljm.service.SysRoleService;
import com.ljm.service.SysUserService;

import com.ljm.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysUserMapper sysUserMapper;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	RedisUtil redisUtil;

	@Override
	public SysUser getByUsername(String username) {
		return getOne(new QueryWrapper<SysUser>().eq("username", username));
	}

	@Override
	public String getUserAuthorityInfo(Long userId) {

		SysUser sysUser = sysUserMapper.selectById(userId);

		//  ROLE_admin,ROLE_normal,sys:user:list,....
		String authority = "";

		if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getUsername())) {
			authority = (String) redisUtil.get("GrantedAuthority:" + sysUser.getUsername());

		} else {
			// 获取角色编码
			List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>()
					.inSql("id", "select role_id from sys_user_role where user_id = " + userId));

			if (roles.size() > 0) {
				String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
				authority = roleCodes.concat(",");
			}

			// 获取菜单操作编码
			List<Long> menuIds = sysUserMapper.getNavMenuIds(userId);
			if (menuIds.size() > 0) {

				List<SysMenu> menus = sysMenuService.listByIds(menuIds);
				String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

				authority = authority.concat(menuPerms);
			}

			redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority, 60 * 60);
		}

		return authority;
	}

	@Override
	public void clearUserAuthorityInfo(String username) {
		redisUtil.del("GrantedAuthority:" + username);
	}

	@Override
	public void clearUserAuthorityInfoByRoleId(Long roleId) {

		List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>()
				.inSql("id", "select user_id from sys_user_role where role_id = " + roleId));

		sysUsers.forEach(u -> {
			this.clearUserAuthorityInfo(u.getUsername());
		});

	}

	@Override
	public void clearUserAuthorityInfoByMenuId(Long menuId) {
		List<SysUser> sysUsers = sysUserMapper.listByMenuId(menuId);

		sysUsers.forEach(u -> {
			this.clearUserAuthorityInfo(u.getUsername());
		});
	}

	@Override
	public List<SysUser> getUserList(String[] ids) {
		if (ids == null){
			return null;
		}
		List<SysUser> userList = new ArrayList<>();
		for (String userId : ids){
			SysUser sysUser = baseMapper.selectById(userId);
			userList.add(sysUser);
		}
		return userList;
	}
}
