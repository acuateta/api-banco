package com.acuateta.banco.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;

public interface IExcelGenerator {


    void createHeaders(Workbook workbook, String sheetName, String[] colums);

    void autoSizeColums(Sheet sheet, Integer totalColums);

    ByteArrayInputStream write(Workbook workbook);


}
