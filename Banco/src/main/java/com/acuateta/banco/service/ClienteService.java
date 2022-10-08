package com.acuateta.banco.service;

import com.acuateta.banco.model.Cliente;
import com.acuateta.banco.repository.IClienteDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService implements IClienteService{


    private final IClienteDao clienteDao;

    @Override
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }
}
