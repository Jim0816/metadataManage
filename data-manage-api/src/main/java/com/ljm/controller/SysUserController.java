package com.ljm.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.dto.PassDto;
import com.ljm.entity.SysRole;
import com.ljm.entity.SysUser;
import com.ljm.entity.SysUserRole;
import com.ljm.lang.Const;
import com.ljm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Autowired
    BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/info/{id}")
	@PreAuthorize("hasAuthority('sys:user:list')")
	public Result info(@PathVariable("id") Long id) {

		SysUser sysUser = sysUserService.getById(id);
		Assert.notNull(sysUser, "找不到该管理员");

		List<SysRole> roles = sysRoleService.listRolesByUserId(id);

		sysUser.setSysRoles(roles);
		return Result.ok(sysUser);
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('sys:user:list')")
	public Result list(String username) {

		Page<SysUser> pageData = sysUserService.page(getPage(), new QueryWrapper<SysUser>()
				.like(StrUtil.isNotBlank(username), "username", username));

		pageData.getRecords().forEach(u -> {

			u.setSysRoles(sysRoleService.listRolesByUserId(u.getId()));
		});

		return Result.ok(pageData);
	}

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('sys:user:save')")
	public Result save(@Validated @RequestBody SysUser sysUser) {

		sysUser.setCreated(LocalDateTime.now());
		sysUser.setStatu(Const.STATUS_ON);

		// 默认密码
		String password = passwordEncoder.encode(Const.DEFULT_PASSWORD);
		sysUser.setPassword(password);

		// 默认头像
		sysUser.setAvatar(Const.DEFULT_AVATAR);

		sysUserService.save(sysUser);
		return Result.ok(sysUser);
	}

	@PostMapping("/update")
	@PreAuthorize("hasAuthority('sys:user:update')")
	public Result update(@Validated @RequestBody SysUser sysUser) {

		sysUser.setUpdated(LocalDateTime.now());

		sysUserService.updateById(sysUser);
		return Result.ok(sysUser);
	}

	@Transactional
	@PostMapping("/delete")
	@PreAuthorize("hasAuthority('sys:user:delete')")
	public Result delete(@RequestBody Long[] ids) {

		sysUserService.removeByIds(Arrays.asList(ids));
		sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("user_id", ids));

		return Result.ok("");
	}

	@Transactional
	@PostMapping("/role/{userId}")
	@PreAuthorize("hasAuthority('sys:user:role')")
	public Result rolePerm(@PathVariable("userId") Long userId, @RequestBody Long[] roleIds) {

		List<SysUserRole> userRoles = new ArrayList<>();

		Arrays.stream(roleIds).forEach(r -> {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(r);
			sysUserRole.setUserId(userId);

			userRoles.add(sysUserRole);
		});

		sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", userId));
		sysUserRoleService.saveBatch(userRoles);

		// 删除缓存
		SysUser sysUser = sysUserService.getById(userId);
		sysUserService.clearUserAuthorityInfo(sysUser.getUsername());

		return Result.ok("");
	}

	@PostMapping("/repass")
	@PreAuthorize("hasAuthority('sys:user:repass')")
	public Result repass(@RequestBody Long userId) {

		SysUser sysUser = sysUserService.getById(userId);

		sysUser.setPassword(passwordEncoder.encode(Const.DEFULT_PASSWORD));
		sysUser.setUpdated(LocalDateTime.now());

		sysUserService.updateById(sysUser);
		return Result.ok("");
	}

	@PostMapping("/updatePass")
	public Result updatePass(@Validated @RequestBody PassDto passDto, Principal principal) {

		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		boolean matches = passwordEncoder.matches(passDto.getCurrentPass(), sysUser.getPassword());
		if (!matches) {
			return Result.failed("旧密码不正确");
		}

		sysUser.setPassword(passwordEncoder.encode(passDto.getPassword()));
		sysUser.setUpdated(LocalDateTime.now());

		sysUserService.updateById(sysUser);
		return Result.ok("");
	}
}
