

package com.project.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Update password
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@ApiModel(value = "updatePassword")
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "originalPassword")
    @NotBlank(message="{sysuser.password.require}")
    private String password;

    @ApiModelProperty(value = "newPassword")
    @NotBlank(message="{sysuser.password.require}")
    private String newPassword;

}