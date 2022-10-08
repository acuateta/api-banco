package com.acuateta.banco.service;

import com.acuateta.banco.exception.SaldoInsuficienteException;
import com.acuateta.banco.model.Cuenta;
import com.acuateta.banco.model.Movimiento;
import com.acuateta.banco.repository.ICuentaDao;
import com.acuateta.banco.repository.IMovimientoDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovimientoService implements IMovimientoService {

    private final IMovimientoDao dao;

    private final ICuentaDao cuentaService;

    @Override
    public List<Movimiento> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Movimiento movimiento) throws SaldoInsuficienteException {
        Double saldo = null;

        Cuenta cuenta = cuentaService.findByNumCuenta(movimiento.getNumCuenta());
        movimiento.setCuenta(cuenta);

        if (movimiento.getTipoMovimiento().equals("Retiro")) {
            if (movimiento.getCuenta().getSaldoInicial() <= 0) {
                throw new SaldoInsuficienteException("Saldo no disponible: ");
            }
            saldo = movimiento.getCuenta().getSaldoInicial() - movimiento.getValor();
            Double valor = movimiento.getValor()*-1;
            movimiento.setValor(valor);
        } else {
            saldo = movimiento.getCuenta().getSaldoInicial() + movimiento.getValor();
        }
        LocalDate date = LocalDate.now();
        movimiento.setFecha(date);
        movimiento.setSaldo(saldo);
        dao.save(movimiento);

    }

    @Override
    public List<Movimiento> findByAllData(LocalDate des, LocalDate has, Long id) {
        return dao.findByAllData(des, has, id);
    }

}
