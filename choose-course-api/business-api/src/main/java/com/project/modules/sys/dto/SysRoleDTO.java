

package com.project.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.common.validator.group.AddGroup;
import com.project.common.validator.group.DefaultGroup;
import com.project.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel(value = "sysRoleManagement")
public class SysRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "roleName")
	@NotBlank(message="{sysrole.name.require}", groups = DefaultGroup.class)
	private String name;

	@ApiModelProperty(value = "remark")
	private String remark;

	@ApiModelProperty(value = "createDate")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "menuIdList")
	private List<Long> menuIdList;

	@ApiModelProperty(value = "deptIdList")
	private List<Long> deptIdList;

}