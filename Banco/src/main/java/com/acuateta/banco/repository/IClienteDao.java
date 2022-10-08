package com.acuateta.banco.repository;

import com.acuateta.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
