package com.acuateta.banco.controller;

import com.acuateta.banco.model.Cliente;
import com.acuateta.banco.service.IClienteService;
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


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClienteService service;

    @Test
    public void listarTest() throws Exception {

        Cliente cliente = new Cliente();

        when(service.findAll()).thenReturn(Arrays.asList(cliente));

        mockMvc.perform(get(CLIENTES + LISTAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void listarPorIdTest() throws Exception {

        Cliente cliente = new Cliente();

        when(service.findById(any())).thenReturn(java.util.Optional.of(cliente));

        mockMvc.perform(get(CLIENTES + LISTAR + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
