

package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.modules.sys.dao.SysRoleDao;
import com.project.modules.sys.dto.SysRoleDTO;
import com.project.modules.sys.entity.SysRoleEntity;
import com.project.modules.sys.enums.SuperAdminEnum;
import com.project.modules.sys.service.*;
import com.project.common.constant.Constant;
import com.project.common.page.PageData;
import com.project.common.service.impl.BaseServiceImpl;
import com.project.common.utils.ConvertUtils;
import com.project.modules.security.user.SecurityUser;
import com.project.modules.security.user.UserDetail;
import com.project.modules.sys.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 *
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDataScopeService sysRoleDataScopeService;
	@Autowired
	private SysRoleUserService sysRoleUserService;


	@Override
	public PageData<SysRoleDTO> page(Map<String, Object> params) {
		IPage<SysRoleEntity> page = baseDao.selectPage(
			getPage(params, Constant.CREATE_DATE, false),
			getWrapper(params)
		);

		return getPageData(page, SysRoleDTO.class);
	}

	@Override
	public List<SysRoleDTO> list(Map<String, Object> params) {
		List<SysRoleEntity> entityList = baseDao.selectList(getWrapper(params));

		return ConvertUtils.sourceToTarget(entityList, SysRoleDTO.class);
	}

	private QueryWrapper<SysRoleEntity> getWrapper(Map<String, Object> params){
		String name = (String)params.get("name");

		QueryWrapper<SysRoleEntity> wrapper = new QueryWrapper<>();
		wrapper.like(StringUtils.isNotBlank(name), "name", name);


		return wrapper;
	}

	@Override
	public SysRoleDTO get(Long id) {
		SysRoleEntity entity = baseDao.selectById(id);

		return ConvertUtils.sourceToTarget(entity, SysRoleDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleDTO dto) {
		SysRoleEntity entity = ConvertUtils.sourceToTarget(dto, SysRoleEntity.class);

		//Save the role
		insert(entity);

		//Save the role-menu relationship
		sysRoleMenuService.saveOrUpdate(entity.getId(), dto.getMenuIdList());

		//Save he role-data permission relationship
		sysRoleDataScopeService.saveOrUpdate(entity.getId(), dto.getDeptIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleDTO dto) {
		SysRoleEntity entity = ConvertUtils.sourceToTarget(dto, SysRoleEntity.class);

		//Update the role
		updateById(entity);

		//Update the role-menu relationship
		sysRoleMenuService.saveOrUpdate(entity.getId(), dto.getMenuIdList());

		//Update he role-data permission relationship
		sysRoleDataScopeService.saveOrUpdate(entity.getId(), dto.getDeptIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long[] ids) {
		//删除角色
		baseDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		sysRoleUserService.deleteByRoleIds(ids);

		//删除角色菜单关系
		sysRoleMenuService.deleteByRoleIds(ids);

		//删除角色数据权限关系
		sysRoleDataScopeService.deleteByRoleIds(ids);
	}

}