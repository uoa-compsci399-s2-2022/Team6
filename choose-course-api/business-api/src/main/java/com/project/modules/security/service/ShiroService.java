

package com.project.modules.security.service;

import com.project.modules.security.entity.SysUserTokenEntity;
import com.project.modules.security.user.UserDetail;
import com.project.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Set;

/**
 * shiro interface
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ShiroService {
    /**
     * Gets a list of user permissions
     */
    Set<String> getUserPermissions(UserDetail user);

    SysUserTokenEntity getByToken(String token);

    /**
     * Query the user based on the user ID
     * @param userId
     */
    SysUserEntity getUser(Long userId);

    /**
     *Ger the department data permission of the user
     * @param userId
     * @return
     */
    List<Long> getDataScopeList(Long userId);
}
