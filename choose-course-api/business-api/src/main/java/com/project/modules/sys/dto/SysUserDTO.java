

package com.project.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.common.validator.group.AddGroup;
import com.project.common.validator.group.DefaultGroup;
import com.project.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 *
 *
 */
@Data
@ApiModel(value = "sysUserManagement")
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "username", required = true)
	@NotBlank(message="{sysuser.username.require}", groups = DefaultGroup.class)
	private String username;

	@ApiModelProperty(value = "password")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message="{sysuser.password.require}", groups = AddGroup.class)
	private String password;

	@ApiModelProperty(value = "realName", required = true)
	@NotBlank(message="{sysuser.realname.require}", groups = DefaultGroup.class)
	private String realName;

	@ApiModelProperty(value = "headUrl")
	private String headUrl;

	@ApiModelProperty(value = "gender   0：male   1：female    2：secret", required = true)
	@Range(min=0, max=2, message = "{sysuser.gender.range}", groups = DefaultGroup.class)
	private Integer gender;

	@ApiModelProperty(value = "email")
	@Email(message="{sysuser.email.error}", groups = DefaultGroup.class)
	private String email;

	@ApiModelProperty(value = "mobile")
	private String mobile;
//
//	@ApiModelProperty(value = "deptId", required = true)
////	@NotNull(message="{sysuser.deptId.require}", groups = DefaultGroup.class)
//	private Long deptId;

	@ApiModelProperty(value = "status  0：block    1：normal", required = true)
	@Range(min=0, max=1, message = "{sysuser.status.range}", groups = DefaultGroup.class)
	private Integer status;

	@ApiModelProperty(value = "createDate")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "superAdmin   0：no   1：yes")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer superAdmin;

	@ApiModelProperty(value = "roleIdList")
	private List<Long> roleIdList;

	@ApiModelProperty(value = "deptName")
	private String deptName;

}