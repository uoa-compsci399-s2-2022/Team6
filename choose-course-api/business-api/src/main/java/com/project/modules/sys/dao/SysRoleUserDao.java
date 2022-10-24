

package com.project.modules.sys.dao;

import com.project.common.dao.BaseDao;
import com.project.modules.sys.entity.SysRoleUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 *
 */
@Mapper
public interface SysRoleUserDao extends BaseDao<SysRoleUserEntity> {

    /**
     *
     * @param roleIds
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     *
     * @param userIds
     */
    void deleteByUserIds(Long[] userIds);

    /**
     *
     * @param userId
     *
     * @return
     */
    List<Long> getRoleIdList(Long userId);
}