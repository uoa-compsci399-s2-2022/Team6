

package com.project.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.modules.sys.dao.SysUserDao;
import com.project.modules.sys.dto.SysUserDTO;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.enums.SuperAdminEnum;
import com.project.modules.sys.service.SysRoleUserService;
import com.project.modules.sys.service.SysUserService;
import com.project.common.constant.Constant;
import com.project.common.page.PageData;
import com.project.common.service.impl.BaseServiceImpl;
import com.project.common.utils.ConvertUtils;
import com.project.modules.security.user.SecurityUser;
import com.project.modules.security.user.UserDetail;
import com.project.modules.security.password.PasswordUtils;
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
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysRoleUserService sysRoleUserService;

	@Override
	public PageData<SysUserDTO> page(Map<String, Object> params) {

		paramsToLike(params, "username");


		IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);


		//Query
		List<SysUserEntity> list = baseDao.getList(params);

		return getPageData(list, page.getTotal(), SysUserDTO.class);
//		return null;

	}

	@Override
	public List<SysUserDTO> list(Map<String, Object> params) {


		UserDetail user = SecurityUser.getUser();


		List<SysUserEntity> entityList = baseDao.getList(params);

		return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);

	}

	@Override
	public SysUserDTO get(Long id) {
		SysUserEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	public SysUserDTO getByUsername(String username,String realName) {
		SysUserEntity entity = baseDao.getByUsername(username,realName);
		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);


		String password = PasswordUtils.encode(entity.getPassword());
		entity.setPassword(password);


		entity.setSuperAdmin(SuperAdminEnum.NO.value());
		insert(entity);


		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);


		if(StringUtils.isBlank(dto.getPassword())){
			entity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(entity.getPassword());
			entity.setPassword(password);
		}


		updateById(entity);


		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	public void delete(Long[] ids) {

		baseDao.deleteBatchIds(Arrays.asList(ids));


		sysRoleUserService.deleteByUserIds(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(Long id, String newPassword) {
		newPassword = PasswordUtils.encode(newPassword);

		baseDao.updatePassword(id, newPassword);
	}

	@Override
	public int getCountByDeptId(Long deptId) {
		return baseDao.getCountByDeptId(deptId);
	}

	@Override
	public List<Long> getUserIdListByDeptId(List<Long> deptIdList) {
		return baseDao.getUserIdListByDeptId(deptIdList);
	}

}
