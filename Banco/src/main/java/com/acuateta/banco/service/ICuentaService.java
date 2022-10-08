package com.acuateta.banco.service;

import com.acuateta.banco.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface ICuentaService {

    List<Cuenta> findAll();

    void save(Cuenta cuenta);

    Optional<Cuenta> findById(Long id);

    void delete(Long id);
}
