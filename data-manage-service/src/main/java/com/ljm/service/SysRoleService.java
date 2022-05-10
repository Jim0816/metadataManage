package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.entity.SysRole;

import java.util.List;


public interface SysRoleService extends IService<SysRole> {

	List<SysRole> listRolesByUserId(Long userId);

}
