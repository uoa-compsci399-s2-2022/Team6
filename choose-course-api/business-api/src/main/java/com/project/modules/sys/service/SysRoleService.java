

package com.project.modules.sys.service;


import com.project.modules.sys.dto.SysRoleDTO;
import com.project.modules.sys.entity.SysRoleEntity;
import com.project.common.page.PageData;
import com.project.common.service.BaseService;

import java.util.List;
import java.util.Map;


/**
 *
 * 
 *
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageData<SysRoleDTO> page(Map<String, Object> params);

	List<SysRoleDTO> list(Map<String, Object> params);

	SysRoleDTO get(Long id);

	void save(SysRoleDTO dto);

	void update(SysRoleDTO dto);

	void delete(Long[] ids);

}
