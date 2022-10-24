

package com.project.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *
 *
 *
 */
@Data
public class SysParamsExcel {
    @Excel(name = "paramCode")
    private String paramCode;
    @Excel(name = "paramValue")
    private String paramValue;
    @Excel(name = "remark")
    private String remark;

}