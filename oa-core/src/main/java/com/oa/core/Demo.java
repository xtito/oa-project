package com.oa.core;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by [张渊]
 * 2018/11/23 16:51
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream(new File("F:/test/abddd111.xlsx"));
        XSSFSheet spreadsheet = workbook.createSheet("new sheet");
        XSSFRow row = spreadsheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        workbook.write(fos);
        fos.close();

    }

}
