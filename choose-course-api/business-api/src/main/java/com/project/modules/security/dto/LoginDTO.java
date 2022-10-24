

package com.project.modules.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * The login form
 *
 *
 */
@Data
@ApiModel(value = "The login form")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "userName", required = true)
    @NotBlank(message="{sysuser.username.require}")
    private String username;

    @ApiModelProperty(value = "password")
    @NotBlank(message="{sysuser.password.require}")
    private String password;

    @ApiModelProperty(value = "verification code")
    @NotBlank(message="{sysuser.captcha.require}")
    private String captcha;

    @ApiModelProperty(value = "uniqueId")
    @NotBlank(message="{sysuser.uuid.require}")
    private String uuid;

}