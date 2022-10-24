

package com.project.modules.sys.service;

import com.project.modules.sys.entity.SysRoleDataScopeEntity;
import com.project.common.service.BaseService;

import java.util.List;

/**
 *
 *
 *
 */
public interface SysRoleDataScopeService extends BaseService<SysRoleDataScopeEntity> {


    List<Long> getDeptIdList(Long roleId);

    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    void deleteByRoleIds(Long[] roleId);
}