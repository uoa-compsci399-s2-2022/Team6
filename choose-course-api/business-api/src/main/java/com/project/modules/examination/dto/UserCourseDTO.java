package com.project.modules.examination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 * @
 */
@Data
@ApiModel(value = "")
public class UserCourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "studentId")
	private Integer studentId;

	@ApiModelProperty(value = "studentName")
	private String studentName;

	@ApiModelProperty(value = "studentCode")
	private String studentCode;

	@ApiModelProperty(value = "STAGE1")
	private String stage1;

	@ApiModelProperty(value = "STAGE2")
	private String stage2;

	@ApiModelProperty(value = "STAGE3")
	private String stage3;

	@ApiModelProperty(value = "GE")
	private String ge;
}