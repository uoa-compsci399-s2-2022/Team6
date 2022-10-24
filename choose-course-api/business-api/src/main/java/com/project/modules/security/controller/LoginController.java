

package com.project.modules.security.controller;

import com.project.common.exception.ErrorCode;
import com.project.common.exception.RenException;
import com.project.common.utils.Result;
import com.project.common.validator.AssertUtils;
import com.project.common.validator.ValidatorUtils;
import com.project.modules.security.dto.LoginDTO;
import com.project.modules.security.password.PasswordUtils;
import com.project.modules.security.service.CaptchaService;
import com.project.modules.security.service.SysUserTokenService;
import com.project.modules.security.user.SecurityUser;
import com.project.modules.security.user.UserDetail;
import com.project.modules.sys.dto.SysUserDTO;
import com.project.modules.sys.enums.UserStatusEnum;
import com.project.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 
 *
 */
@RestController
@Api(tags="loginManagement")
public class LoginController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private CaptchaService captchaService;

	@GetMapping("captcha")
	@ApiOperation(value = "verification code", produces="application/octet-stream")
	@ApiImplicitParam(paramType = "query", dataType="string", name = "uuid", required = true)
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		//uuid cannot be empty
		AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

		//generate the verification code
		captchaService.create(response, uuid);
	}

	@GetMapping("getUserInfo")
	@ApiOperation(value = "getUserInfo")
	public SysUserDTO getUserInfo(@RequestParam(required = false) String  userName,
							@RequestParam(required = false) String realName) {
		return  sysUserService.getByUsername(userName, realName);
	}

	@PostMapping("login")
	@ApiOperation(value = "login")
	public Result login(HttpServletRequest request, @RequestBody LoginDTO login) {
		//check data
		ValidatorUtils.validateEntity(login);

		//check verification code
		boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
		if(!flag){
			return new Result().error(ErrorCode.CAPTCHA_ERROR);
		}

		//User information
		SysUserDTO user = sysUserService.getByUsername(login.getUsername(),"");



		if(user == null){

			throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
		}

		//password error
		if(!PasswordUtils.matches(login.getPassword(), user.getPassword())){

			throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
		}


		if(user.getStatus() == UserStatusEnum.DISABLE.value()){

			throw new RenException(ErrorCode.ACCOUNT_DISABLE);
		}


		return sysUserTokenService.createToken(user.getId(),user);
	}

	@PostMapping("logout")
	@ApiOperation(value = "退出")
	public Result logout(HttpServletRequest request) {
		UserDetail user = SecurityUser.getUser();

		//logout
		sysUserTokenService.logout(user.getId());



		return new Result();
	}
	
}