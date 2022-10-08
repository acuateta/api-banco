package com.acuateta.banco.service;

import com.acuateta.banco.exception.SaldoInsuficienteException;
import com.acuateta.banco.model.Movimiento;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoService {

    List<Movimiento> findAll();

    void save(Movimiento movimiento) throws SaldoInsuficienteException;

    List<Movimiento> findByAllData(LocalDate des, LocalDate has, Long id);
}
