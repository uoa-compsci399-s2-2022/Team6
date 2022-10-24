package com.project.modules.examination.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 *
 *
 */
@Data
@TableName("tb_course")
public class CourseEntity {


    @TableId(type = IdType.AUTO)
	private Integer id;

	private String stage;

	private String major;

	private String course;

	private String remark;
}