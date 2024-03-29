package com.ljm.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.dto.SysMenuDto;
import com.ljm.entity.SysMenu;
import com.ljm.entity.SysRoleMenu;
import com.ljm.entity.SysUser;
import com.ljm.vo.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

	/**
	 * 用户当前用户的菜单和权限信息
	 * @param principal
	 * @return
	 */
	@GetMapping("/nav")
	public Result nav(Principal principal) {
		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		// 获取权限信息
		String authorityInfo = sysUserService.getUserAuthorityInfo(sysUser.getId());// ROLE_admin,ROLE_normal,sys:user:list,....
		String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

		// 获取导航栏信息
		List<SysMenuDto> navs = sysMenuService.getCurrentUserNav();

		return Result.ok(MapUtil.builder()
				.put("authoritys", authorityInfoArray)
				.put("nav", navs)
				.map()
		);
	}

	@GetMapping("/info/{id}")
	@PreAuthorize("hasAuthority('sys:menu:list')")
	public Result info(@PathVariable(name = "id") Long id) {
		return Result.ok(sysMenuService.getById(id));
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('sys:menu:list')")
	public Result list() {

		List<SysMenu> menus = sysMenuService.tree();
		return Result.ok(menus);
	}

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('sys:menu:save')")
	public Result save(@Validated @RequestBody SysMenu sysMenu) {

		sysMenu.setCreated(LocalDateTime.now());

		sysMenuService.save(sysMenu);
		return Result.ok(sysMenu);
	}

	@PostMapping("/update")
	@PreAuthorize("hasAuthority('sys:menu:update')")
	public Result update(@Validated @RequestBody SysMenu sysMenu) {

		sysMenu.setUpdated(LocalDateTime.now());

		sysMenuService.updateById(sysMenu);

		// 清除所有与该菜单相关的权限缓存
		sysUserService.clearUserAuthorityInfoByMenuId(sysMenu.getId());
		return Result.ok(sysMenu);
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	public Result delete(@PathVariable("id") Long id) {

		int count = sysMenuService.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
		if (count > 0) {
			return Result.failed("请先删除子菜单");
		}

		// 清除所有与该菜单相关的权限缓存
		sysUserService.clearUserAuthorityInfoByMenuId(id);

		sysMenuService.removeById(id);

		// 同步删除中间关联表
		sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("menu_id", id));
		return Result.ok("");
	}
}
