package com.acuateta.banco.repository;

import com.acuateta.banco.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICuentaDao extends JpaRepository<Cuenta, Long> {

    @Query("select c from Cuenta c where c.numCuenta like %?1%")
    Cuenta findByNumCuenta(Long id);
}