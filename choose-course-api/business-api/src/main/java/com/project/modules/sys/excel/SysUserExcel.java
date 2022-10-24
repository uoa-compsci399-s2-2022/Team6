

package com.project.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 *
 *
 *
 */
@Data
public class SysUserExcel {
    @Excel(name = "studentName")
    private String studentName;
    @Excel(name = "realName")
    private String realName;
    @Excel(name = "gender", replace = {"male_0", "female_1", "secret_2"})
    private Integer gender;
    @Excel(name = "email")
    private String email;
    @Excel(name = "mobile")
    private String mobile;
    @Excel(name = "deptName")
    private String deptName;
    @Excel(name = "status", replace = {"block_0", "normal_1"})
    private Integer status;
    @Excel(name = "remark")
    private String remark;
    @Excel(name = "createDate", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
