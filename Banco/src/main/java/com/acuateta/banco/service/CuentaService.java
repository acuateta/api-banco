package com.acuateta.banco.service;

import com.acuateta.banco.model.Cuenta;
import com.acuateta.banco.repository.ICuentaDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CuentaService implements ICuentaService{

    private final ICuentaDao service;

    @Override
    public List<Cuenta> findAll() {
        return service.findAll();
    }

    @Override
    public void save(Cuenta cuenta) {
        service.save(cuenta);
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public void delete(Long id) {
        service.deleteById(id);
    }
}
