package com.acuateta.banco.service;


import com.acuateta.banco.model.Cliente;


import java.util.List;
import java.util.Optional;

public interface IClienteService {

    List<Cliente> findAll();

    void save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    void delete(Long id);

}
