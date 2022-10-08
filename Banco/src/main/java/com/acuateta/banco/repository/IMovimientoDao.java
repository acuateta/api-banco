package com.acuateta.banco.repository;

import com.acuateta.banco.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoDao extends JpaRepository<Movimiento, Long> {

    //@Query("SELECT m FROM Movimiento m WHERE m.fecha >= ?1 AND m.fecha <= ?2 AND m.cuenta.cliente.clienteId = ?3")
    @Query(value = "select * from movimientos where fecha >= ?1 and fecha <= ?2 and cuenta_id = ?3", nativeQuery = true)
    List<Movimiento> findByAllData(LocalDate des, LocalDate has, Long id);
}
