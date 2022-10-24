

package com.project.modules.sys.controller;

import com.project.modules.sys.dto.PasswordDTO;
import com.project.modules.sys.dto.SysUserDTO;
import com.project.modules.sys.excel.SysUserExcel;
import com.project.modules.sys.service.SysRoleUserService;
import com.project.modules.sys.service.SysUserService;
import com.project.common.annotation.LogOperation;
import com.project.common.constant.Constant;
import com.project.common.exception.ErrorCode;
import com.project.common.page.PageData;
import com.project.common.utils.ConvertUtils;
import com.project.common.utils.ExcelUtils;
import com.project.common.utils.Result;
import com.project.common.validator.AssertUtils;
import com.project.common.validator.ValidatorUtils;
import com.project.common.validator.group.AddGroup;
import com.project.common.validator.group.DefaultGroup;
import com.project.common.validator.group.UpdateGroup;
import com.project.modules.security.user.SecurityUser;
import com.project.modules.security.user.UserDetail;
import com.project.modules.security.password.PasswordUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 *
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags="sysUserManagement")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleUserService sysRoleUserService;

	@GetMapping("page")
	@ApiOperation("page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = Constant.PAGE, value = "Current page number, starting at 1", paramType = "query", required = true, dataType="int") ,
			@ApiImplicitParam(name = Constant.LIMIT, value = "Number of records per page", paramType = "query",required = true, dataType="int") ,
			@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "Sort field", paramType = "query", dataType="String") ,
			@ApiImplicitParam(name = Constant.ORDER, value = "Sort mode, optional (asc, desc)", paramType = "query", dataType="String") ,
			@ApiImplicitParam(name = "username", value = "roleName", paramType = "query", dataType="String"),
			@ApiImplicitParam(name = "gender", value = "gender", paramType = "query", dataType="String"),
			@ApiImplicitParam(name = "deptId", value = "deptID", paramType = "query", dataType="String")
	})
	@RequiresPermissions("sys:user:page")
	public Result<PageData<SysUserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SysUserDTO> page = sysUserService.page(params);

		return new Result<PageData<SysUserDTO>>().ok(page);
	}

	@GetMapping("{id}")
	@ApiOperation("information")
	@RequiresPermissions("sys:user:info")
	public Result<SysUserDTO> get(@PathVariable("id") Long id){
		SysUserDTO data = sysUserService.get(id);


		List<Long> roleIdList = sysRoleUserService.getRoleIdList(id);
		data.setRoleIdList(roleIdList);

		return new Result<SysUserDTO>().ok(data);
	}

	@GetMapping("info")
	@ApiOperation("info")
	public Result<SysUserDTO> info(){
		SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
		return new Result<SysUserDTO>().ok(data);
	}

	@PutMapping("password")
	@ApiOperation("updatePassword")
	@LogOperation("updatePassword")
	public Result password(@RequestBody PasswordDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto);

		UserDetail user = SecurityUser.getUser();

		//The original password is incorrect
		if(!PasswordUtils.matches(dto.getPassword(), user.getPassword())){
			return new Result().error(ErrorCode.PASSWORD_ERROR);
		}

		sysUserService.updatePassword(user.getId(), dto.getNewPassword());

		return new Result();
	}

	@PostMapping
	@ApiOperation("save")
	@LogOperation("save")
	@RequiresPermissions("sys:user:save")
	public Result save(@RequestBody SysUserDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

		sysUserService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("update")
	@LogOperation("update")
	@RequiresPermissions("sys:user:update")
	public Result update(@RequestBody SysUserDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		sysUserService.update(dto);

		return new Result();
	}

	@DeleteMapping
	@ApiOperation("delete")
	@LogOperation("delete")
	@RequiresPermissions("sys:user:delete")
	public Result delete(@RequestBody Long[] ids){
		//check data
		AssertUtils.isArrayEmpty(ids, "id");

		sysUserService.deleteBatchIds(Arrays.asList(ids));

		return new Result();
	}

	@GetMapping("export")
	@ApiOperation("export")
	@LogOperation("export")
	@RequiresPermissions("sys:user:export")
	@ApiImplicitParam(name = "username", value = "username", paramType = "query", dataType="String")
	public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		List<SysUserDTO> list = sysUserService.list(params);

		ExcelUtils.exportExcelToTarget(response, null, list, SysUserExcel.class);
	}
}