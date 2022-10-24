

package com.project.modules.sys.controller;

import com.project.modules.sys.dto.SysMenuDTO;
import com.project.modules.sys.enums.MenuTypeEnum;
import com.project.modules.sys.service.SysMenuService;
import com.project.common.annotation.LogOperation;
import com.project.common.exception.ErrorCode;
import com.project.common.utils.Result;
import com.project.common.validator.AssertUtils;
import com.project.common.validator.ValidatorUtils;
import com.project.common.validator.group.DefaultGroup;
import com.project.modules.security.user.SecurityUser;
import com.project.modules.security.user.UserDetail;
import com.project.modules.security.service.ShiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 *
 * 
 *
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags="menuMangement")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroService shiroService;

	@GetMapping("nav")
	@ApiOperation("navigation")
	public Result<List<SysMenuDTO>> nav(){
		UserDetail user = SecurityUser.getUser();
		List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());

		return new Result<List<SysMenuDTO>>().ok(list);
	}

	@GetMapping("permissions")
	@ApiOperation("permissions")
	public Result<Set<String>> permissions(){
		UserDetail user = SecurityUser.getUser();
		Set<String> set = shiroService.getUserPermissions(user);

		return new Result<Set<String>>().ok(set);
	}

	@GetMapping("list")
	@ApiOperation("list")
	@ApiImplicitParam(name = "type", value = "menu type 0：menu 1：button  null：all", paramType = "query", dataType="int")
	@RequiresPermissions("sys:menu:list")
	public Result<List<SysMenuDTO>> list(Integer type){
		List<SysMenuDTO> list = sysMenuService.getAllMenuList(type);

		return new Result<List<SysMenuDTO>>().ok(list);
	}

	@GetMapping("{id}")
	@ApiOperation("information")
	@RequiresPermissions("sys:menu:info")
	public Result<SysMenuDTO> get(@PathVariable("id") Long id){
		SysMenuDTO data = sysMenuService.get(id);

		return new Result<SysMenuDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("save")
	@LogOperation("save")
	@RequiresPermissions("sys:menu:save")
	public Result save(@RequestBody SysMenuDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto, DefaultGroup.class);

		sysMenuService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("update")
	@LogOperation("update")
	@RequiresPermissions("sys:menu:update")
	public Result update(@RequestBody SysMenuDTO dto){
		//check data
		ValidatorUtils.validateEntity(dto, DefaultGroup.class);

		sysMenuService.update(dto);

		return new Result();
	}

	@DeleteMapping("{id}")
	@ApiOperation("delete")
	@LogOperation("delete")
	@RequiresPermissions("sys:menu:delete")
	public Result delete(@PathVariable("id") Long id){
		//check data
		AssertUtils.isNull(id, "id");


		List<SysMenuDTO> list = sysMenuService.getListPid(id);
		if(list.size() > 0){
			return new Result().error(ErrorCode.SUB_MENU_EXIST);
		}

		sysMenuService.delete(id);

		return new Result();
	}

	@GetMapping("select")
	@ApiOperation("select")
	@RequiresPermissions("sys:menu:select")
	public Result<List<SysMenuDTO>> select(){
		UserDetail user = SecurityUser.getUser();
		List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, null);

		return new Result<List<SysMenuDTO>>().ok(list);
	}
}