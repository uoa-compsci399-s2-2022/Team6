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
public class CourseExcel {
    @Excel(name = "ID")
    private Integer id;
    @Excel(name = "stage")
    private String stage;
    @Excel(name = "major")
    private String major;
    @Excel(name = "course")
    private String course;
    @Excel(name = "remark")
    private String remark;

}