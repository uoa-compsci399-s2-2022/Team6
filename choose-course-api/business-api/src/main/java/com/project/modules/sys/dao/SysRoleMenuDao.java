

package com.project.modules.sys.dao;

import com.project.common.dao.BaseDao;
import com.project.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * 
 *
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {

	/**
	 *
	 */
	List<Long> getMenuIdList(Long roleId);

	/**
	 *
	 * @param roleIds
	 */
	void deleteByRoleIds(Long[] roleIds);

	/**
	 *
	 * @param menuId
	 */
	void deleteByMenuId(Long menuId);
}
