

package com.project.modules.sys.service;

import com.project.modules.sys.dto.SysMenuDTO;
import com.project.modules.sys.entity.SysMenuEntity;
import com.project.common.service.BaseService;
import com.project.modules.security.user.UserDetail;

import java.util.List;


/**
 *
 * 
 *
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDTO get(Long id);

	void save(SysMenuDTO dto);

	void update(SysMenuDTO dto);

	void delete(Long id);


	List<SysMenuDTO> getAllMenuList(Integer type);


	List<SysMenuDTO> getUserMenuList(UserDetail user, Integer type);


	List<SysMenuDTO> getListPid(Long pid);
}
