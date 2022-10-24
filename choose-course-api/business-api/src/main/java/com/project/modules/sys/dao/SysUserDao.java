

package com.project.modules.sys.dao;

import com.project.common.dao.BaseDao;
import com.project.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * 
 *
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getById(Long id);

	SysUserEntity getByUsername(String username,String realName);

	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	/**
	 *
	 */
	int getCountByDeptId(Long deptId);

	/**
	 *
	 */
	List<Long> getUserIdListByDeptId(List<Long> deptIdList);
}