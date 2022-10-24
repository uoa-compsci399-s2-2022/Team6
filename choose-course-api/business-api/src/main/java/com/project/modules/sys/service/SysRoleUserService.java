

package com.project.modules.sys.service;

import com.project.modules.sys.entity.SysRoleUserEntity;
import com.project.common.service.BaseService;

import java.util.List;

/**
 *
 *
 *
 */
public interface SysRoleUserService extends BaseService<SysRoleUserEntity> {

    /**
     * Save or Update
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * Delete the role-user relationship based on role ids
     * @param roleIds
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * The rol-user relationship is deleted based on the user id
     * @param userIds 用户ids
     */
    void deleteByUserIds(Long[] userIds);

    List<Long> getRoleIdList(Long userId);
}