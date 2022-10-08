package com.acuateta.banco.controller;

import com.acuateta.banco.dto.RespuestaApi;
import com.acuateta.banco.model.Cliente;
import com.acuateta.banco.model.Cuenta;
import com.acuateta.banco.service.IClienteService;
import com.acuateta.banco.service.ICuentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.acuateta.banco.AppConstants.*;

@Slf4j
@RestController
@RequestMapping(CUENTAS)
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    @Autowired
    private IClienteService clienteService;

    @GetMapping(value = LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cuenta>> listar() {
        try {
            return new ResponseEntity<>(
                    cuentaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = REGISTRAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> guardar(@RequestBody Cuenta cuenta) {
        try {
            Cliente cliente = clienteService.findById(cuenta.getCliente().getId()).orElse(null);
            cuenta.setCliente(cliente);
            cuentaService.save(cuenta);
            return new ResponseEntity<>(new RespuestaApi(OK, cuenta), HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ELIMINAR_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable Long id) {
        try {
            cuentaService.delete(id);
            return new ResponseEntity<>(new RespuestaApi(OK, SE_ELIMINO_EXITO), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = MODIFICAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> editar(@PathVariable Long id, @RequestBody Cuenta cuenta) {

        return cuentaService.findById(id)
                .map(c -> {
                    c.setEstado(cuenta.getEstado());
                    c.setTipo(cuenta.getTipo());
                    c.setSaldoInicial(cuenta.getSaldoInicial());
                    cuentaService.save(c);
                    return new ResponseEntity<>(new RespuestaApi(OK, c), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new RespuestaApi("NOT_FOUND", NO_EXISTE), HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = LISTAR_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> listarPorId(@PathVariable Long id) {

        return cuentaService.findById(id)
                .map(c -> new ResponseEntity<>(new RespuestaApi(OK, c), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new RespuestaApi("NOT_FOUND", NO_EXISTE), HttpStatus.NOT_FOUND));
    }
}
