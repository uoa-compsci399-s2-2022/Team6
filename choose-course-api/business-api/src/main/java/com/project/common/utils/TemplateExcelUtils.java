package com.project.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

@Component
public class TemplateExcelUtils {

    public static void exportMultipleSheet(Map<String, Object> map, HttpServletResponse response) throws IOException {

        File file = new File("business-api/static/template.xlsx");
        System.out.println(". Represents the absolute path of" + file.getAbsolutePath());


        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            Workbook wb = null;
            boolean is2003Excel = 1==0? true : false;
            if (is2003Excel) {
                wb = new HSSFWorkbook(fis);
            } else {
                wb = new XSSFWorkbook(fis);
            }

            TemplateExportParams params = new TemplateExportParams(file.getAbsolutePath(), true);


            System.out.println("generating...");
            wb = ExcelExportUtil.exportExcel(params, map);
            Random r = new Random();

            try {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode("choose-course-record", "UTF-8") + ".xlsx");
                ServletOutputStream out = response.getOutputStream();
                wb.write(out);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

