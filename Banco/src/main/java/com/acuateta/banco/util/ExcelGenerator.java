package com.acuateta.banco.util;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ExcelGenerator implements IExcelGenerator{


    @Override
    public void createHeaders(Workbook workbook, String sheetName, String[] colums) {

        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.createRow(0);

        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(headerFont);

        for (int col = 0; col < colums.length; col++){
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(colums[col]);
            cell.setCellStyle(cellStyle);
        }

    }

    @Override
    public void autoSizeColums(Sheet sheet, Integer totalColums) {

        for (int i = 0; i < totalColums; i++){
            sheet.autoSizeColumn(1);
        }

    }

    @Override
    public ByteArrayInputStream write(Workbook workbook) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            workbook.write(out);
        }catch (IOException e){

        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
