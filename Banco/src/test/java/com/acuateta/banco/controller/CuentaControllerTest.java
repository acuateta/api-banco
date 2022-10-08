package com.acuateta.banco.controller;


import com.acuateta.banco.model.Cuenta;
import com.acuateta.banco.service.IClienteService;
import com.acuateta.banco.service.ICuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.acuateta.banco.AppConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICuentaService service;

    @MockBean
    private IClienteService clienteService;

    @Test
    public void listarTest() throws Exception {

        Cuenta cuenta = new Cuenta();

        when(service.findAll()).thenReturn(Arrays.asList(cuenta));

        mockMvc.perform(get(CUENTAS + LISTAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void listarPorIdTest() throws Exception {

        Cuenta cuenta = new Cuenta();

        when(service.findById(any())).thenReturn(java.util.Optional.of(cuenta));

        mockMvc.perform(get(CUENTAS + LISTAR + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
