package com.project.modules.examination.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 *
 *
 *
 */
@Data
public class UserCourseExcel {
    @Excel(name = "ID")
    private Integer id;
    @Excel(name = "studentId")
    private Integer userId;
    @Excel(name = "studentName")
    private String userName;
    @Excel(name = "studentCode")
    private String userCode;
    @Excel(name = "STAGE1")
    private String stage1;
    @Excel(name = "STAGE2")
    private String stage2;
    @Excel(name = "STAGE3")
    private String stage3;

}