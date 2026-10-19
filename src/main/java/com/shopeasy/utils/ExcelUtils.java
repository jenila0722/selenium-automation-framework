
package com.shopeasy.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getExcelData(
            String filePath, String sheetName) throws IOException {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName);
            }

            int lastRowNum = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[lastRowNum][colCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);

                for (int j = 0; j < colCount; j++) {
                    Cell cell = (row == null) ? null : row.getCell(j);
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }

            return data;
        }
    }
}
