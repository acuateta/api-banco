package com.acuateta.banco.service;

import com.acuateta.banco.model.Movimiento;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface IReporteExcel {

    /**
     * Crea un documento en formato xls
     *
     * @param list Lista de movimientos
     * @param sheetName Nombre del libro de excel
     * @return La respuesta con el documento
     */
    InputStreamResource createXls(List<Movimiento> list, String sheetName);
}
