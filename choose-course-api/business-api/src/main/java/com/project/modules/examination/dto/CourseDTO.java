package com.project.modules.examination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 *
 *
 */
@Data
@ApiModel(value = "")
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "Stage")
	private String stage;

	@ApiModelProperty(value = "Major")
	private String major;

	@ApiModelProperty(value = "course")
	private String course;

	@ApiModelProperty(value = "remark")
	private String remark;

	@ApiModelProperty(value = "majorRemark")
	private String majorRemark;
}