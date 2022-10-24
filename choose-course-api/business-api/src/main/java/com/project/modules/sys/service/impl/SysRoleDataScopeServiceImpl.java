

package com.project.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.project.modules.sys.dao.SysRoleDataScopeDao;
import com.project.modules.sys.entity.SysRoleDataScopeEntity;
import com.project.modules.sys.service.SysRoleDataScopeService;
import com.project.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 *
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeDao, SysRoleDataScopeEntity>
        implements SysRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
        return baseDao.getDeptIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //Delete the data permission relationship of the role first
        deleteByRoleIds(new Long[]{roleId});

        //The role does not have a data permission
        if(CollUtil.isEmpty(deptIdList)){
            return ;
        }

        //
        //The role data permission relationship is saved
        for(Long deptId : deptIdList){
            SysRoleDataScopeEntity sysRoleDataScopeEntity = new SysRoleDataScopeEntity();
            sysRoleDataScopeEntity.setDeptId(deptId);
            sysRoleDataScopeEntity.setRoleId(roleId);

            //save
            insert(sysRoleDataScopeEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }
}