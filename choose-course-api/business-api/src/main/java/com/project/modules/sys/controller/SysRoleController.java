

package com.project.modules.sys.controller;

import com.project.modules.sys.dto.SysRoleDTO;
import com.project.modules.sys.service.SysRoleDataScopeService;
import com.project.modules.sys.service.SysRoleMenuService;
import com.project.modules.sys.service.SysRoleService;
import com.project.common.annotation.LogOperation;
import com.project.common.constant.Constant;
import com.project.common.page.PageData;
import com.project.common.utils.Result;
import com.project.common.validator.AssertUtils;
import com.project.common.validator.ValidatorUtils;
import com.project.common.validator.group.AddGroup;
import com.project.common.validator.group.DefaultGroup;
import com.project.common.validator.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 *
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags="roleManagement")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDataScopeService sysRoleDataScopeService;

	@GetMapping("page")
	@ApiOperation("page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = Constant.PAGE, value = "Current page number, starting at 1", paramType = "query", required = true, dataType="int") ,
			@ApiImplicitParam(name = Constant.LIMIT, value = "Number of records per page", paramType = "query",required = true, dataType="int") ,
			@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "Sort field", paramType = "query", dataType="String") ,
			@ApiImplicitParam(name = Constant.ORDER, value = "Sort mode, optional (asc, desc)", paramType = "query", dataType="String") ,
			@ApiImplicitParam(name = "name", value = "roleName", paramType = "query", dataType="String")
	})
	@RequiresPermissions("sys:role:page")
	public Result<PageData<SysRoleDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SysRoleDTO> page = sysRoleService.page(params);

		return new Result<PageData<SysRoleDTO>>().ok(page);
	}

	@GetMapping("list")
	@ApiOperation("list")
	@RequiresPermissions("sys:role:list")
	public Result<List<SysRoleDTO>> list(){
		List<SysRoleDTO> data = sysRoleService.list(new HashMap<>(1));

		return new Result<List<SysRoleDTO>>().ok(data);
	}

	@GetMapping("{id}")
	@ApiOperation("information")
	@RequiresPermissions("sys:role:info")
	public Result<SysRoleDTO> get(@PathVariable("id") Long id){
		SysRoleDTO data = sysRoleService.get(id);


		List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
		data.setMenuIdList(menuIdList);


		List<Long> deptIdList = sysRoleDataScopeService.getDeptIdList(id);
		data.setDeptIdList(deptIdList);

		return new Result<SysRoleDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("save")
	@LogOperation("save")
	@RequiresPermissions("sys:role:save")
	public Result save(@RequestBody SysRoleDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

		sysRoleService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("update")
	@LogOperation("update")
	@RequiresPermissions("sys:role:update")
	public Result update(@RequestBody SysRoleDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		sysRoleService.update(dto);

		return new Result();
	}

	@DeleteMapping
	@ApiOperation("delete")
	@LogOperation("delete")
	@RequiresPermissions("sys:role:delete")
	public Result delete(@RequestBody Long[] ids){
		//check data
		AssertUtils.isArrayEmpty(ids, "id");

		sysRoleService.delete(ids);

		return new Result();
	}
}