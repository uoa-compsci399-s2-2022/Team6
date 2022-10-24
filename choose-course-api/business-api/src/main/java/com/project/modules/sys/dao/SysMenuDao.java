

package com.project.modules.sys.dao;

import com.project.common.dao.BaseDao;
import com.project.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 
 *
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

	SysMenuEntity getById(@Param("id") Long id);


	List<SysMenuEntity> getMenuList(@Param("type") Integer type);


	List<SysMenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("type") Integer type);


	List<String> getUserPermissionsList(Long userId);


	List<String> getPermissionsList();


	List<SysMenuEntity> getListPid(Long pid);

}
