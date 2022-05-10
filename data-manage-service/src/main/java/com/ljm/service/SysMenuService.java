package com.ljm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljm.dto.SysMenuDto;
import com.ljm.entity.SysMenu;

import java.util.List;


public interface SysMenuService extends IService<SysMenu> {

	List<SysMenuDto> getCurrentUserNav();

	List<SysMenu> tree();

}
