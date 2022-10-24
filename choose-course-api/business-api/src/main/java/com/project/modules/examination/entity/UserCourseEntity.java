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
 */
@Data
@TableName("tb_user_course")
public class UserCourseEntity {


    @TableId(type = IdType.AUTO)
	private Integer id;

	private Integer userId;

	private String userName;

	private String userCode;

	private String stage1;

	private String stage2;

	private String stage3;

    private String ge;
}