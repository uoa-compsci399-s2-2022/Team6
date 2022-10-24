

package com.project.modules.sys.dao;

import com.project.common.dao.BaseDao;
import com.project.modules.sys.entity.SysRoleDataScopeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 *
 */
@Mapper
public interface SysRoleDataScopeDao extends BaseDao<SysRoleDataScopeEntity> {


    List<Long> getDeptIdList(Long roleId);


    List<Long> getDataScopeList(Long userId);


    void deleteByRoleIds(Long[] roleIds);
}