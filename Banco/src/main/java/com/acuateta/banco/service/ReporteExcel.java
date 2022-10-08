package com.acuateta.banco.service;

import com.acuateta.banco.model.Movimiento;
import com.acuateta.banco.util.IExcelGenerator;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ReporteExcel implements IReporteExcel{


    private final IExcelGenerator excelGenerator;

    @Override
    public InputStreamResource createXls(List<Movimiento> list, String sheetName) {
        String[] columns = {"Fecha","Cliente","Número Cuenta","Tipo","Saldo inicial","Estado","Movimiento","Saldo Disponible"};

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(sheetName);

        excelGenerator.createHeaders(workbook, sheetName, columns);

        int rowIdx =1;

        for (Movimiento m: list){
            Row row = sheet.createRow(rowIdx++);
            int cellIdx = 0;

            row.createCell(cellIdx++).setCellValue(m.getFecha().toString());
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getCuenta().getCliente().getNombre()).orElse(""));
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getCuenta().getNumCuenta()).orElse(""));
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getCuenta().getTipo()).orElse(""));
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getCuenta().getSaldoInicial()).orElse(0.0));
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getCuenta().getEstado()).orElse(Boolean.valueOf("")));
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getValor()).orElse(0.0));
            row.createCell(cellIdx++).setCellValue(Optional.ofNullable(m.getSaldo()).orElse(0.0));
        }

        for (int i = 0; i<columns.length; i++){
            sheet.autoSizeColumn(i);
        }

        return new InputStreamResource(excelGenerator.write(workbook));
    }
}
