package com.acuateta.banco.controller;


import com.acuateta.banco.model.Movimiento;
import com.acuateta.banco.service.IMovimientoService;
import com.acuateta.banco.service.IReporteExcel;
import com.acuateta.banco.util.IDownloadUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.acuateta.banco.AppConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovimientoController.class)
public class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMovimientoService service;

    @MockBean
    private IDownloadUtil downloadUtil;

    @MockBean
    private IReporteExcel reporteExcel;

    @Test
    public void listarTest() throws Exception {

        Movimiento movimiento = new Movimiento();

        when(service.findAll()).thenReturn(Arrays.asList(movimiento));

        mockMvc.perform(get(MOVIMIENTOS + LISTAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


/*    @Test
    public void listarPorIdTest() throws Exception {

        Movimiento movimiento = new Movimiento();

        when(service.findByAllData(any(), any(), any()))
                .thenReturn(Arrays.asList(movimiento));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(reporteExcel.createXls(any(), any()))
                .thenReturn(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));


        mockMvc.perform(get(MOVIMIENTOS + "/reporte/1")
                        .param("desde", "2020/10/07")
                        .param("hasta", "2020/10/07")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/
}
