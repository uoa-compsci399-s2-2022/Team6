

package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_role_user")
public class SysRoleUserEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;


	private Long roleId;

	private Long userId;

}
