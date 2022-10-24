

package com.project.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.project.modules.sys.dao.SysRoleUserDao;
import com.project.modules.sys.entity.SysRoleUserEntity;
import com.project.modules.sys.service.SysRoleUserService;
import com.project.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 *
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserDao, SysRoleUserEntity> implements SysRoleUserService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //Delete the role-user relationship first
        deleteByUserIds(new Long[]{userId});

        //A user does not have permissions
        if(CollUtil.isEmpty(roleIdList)){
            return ;
        }

        //Save the role-user relationship
        for(Long roleId : roleIdList){
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(userId);
            sysRoleUserEntity.setRoleId(roleId);

            //Save
            insert(sysRoleUserEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseDao.deleteByUserIds(userIds);
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {

        return baseDao.getRoleIdList(userId);
    }
}