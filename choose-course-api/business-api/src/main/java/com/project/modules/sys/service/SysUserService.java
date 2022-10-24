

package com.project.modules.sys.service;

import com.project.modules.sys.dto.SysUserDTO;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.common.page.PageData;
import com.project.common.service.BaseService;

import java.util.List;
import java.util.Map;


/**
 * System User
 * 
 *
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDTO> page(Map<String, Object> params);

	List<SysUserDTO> list(Map<String, Object> params);

	SysUserDTO get(Long id);

	SysUserDTO getByUsername(String username,String realName);

	void save(SysUserDTO dto);

	void update(SysUserDTO dto);

	void delete(Long[] ids);

	/**
	 * Update new password
	 * @param id
	 * @param newPassword
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * Query the number of users based on the department ID
	 */
	int getCountByDeptId(Long deptId);

	/**
	 * Query the user ID list by department Id
	 */
	List<Long> getUserIdListByDeptId(List<Long> deptIdList);

}
