

package com.project.modules.sys.service;

import com.project.modules.sys.entity.SysRoleMenuEntity;
import com.project.common.service.BaseService;

import java.util.List;


/**
 *
 * 
 *
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {


	List<Long> getMenuIdList(Long roleId);


	void saveOrUpdate(Long roleId, List<Long> menuIdList);


	void deleteByRoleIds(Long[] roleIds);


	void deleteByMenuId(Long menuId);
}