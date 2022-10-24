

package com.project.modules.security.service;

import com.project.modules.security.entity.SysUserTokenEntity;
import com.project.common.service.BaseService;
import com.project.common.utils.Result;
import com.project.modules.sys.dto.SysUserDTO;

/**
 * User Token
 *
 *
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

	/**
	 * Generate token
	 * @param userId
	 */
	Result createToken(Long userId, SysUserDTO user);

	/**
	 * logout
	 * @param userId
	 */
	void logout(Long userId);

}