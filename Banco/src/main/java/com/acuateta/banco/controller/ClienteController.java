package com.acuateta.banco.controller;

import com.acuateta.banco.dto.RespuestaApi;
import com.acuateta.banco.model.Cliente;
import com.acuateta.banco.service.IClienteService;
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
@RequestMapping(CLIENTES)
public class ClienteController {


    @Autowired
    private IClienteService clienteService;


    @GetMapping(value = LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> listar() {
        try {
            return new ResponseEntity<>(
                    clienteService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = REGISTRAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> guardar(@RequestBody Cliente cliente) {
        try {
            clienteService.save(cliente);
            return new ResponseEntity<>(new RespuestaApi(OK, cliente), HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ELIMINAR_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>(new RespuestaApi(OK, SE_ELIMINO_EXITO), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = MODIFICAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> editar(@PathVariable Long id, @RequestBody Cliente cliente) {

        return clienteService.findById(id)
                .map(c -> {
                    c.setNombre(cliente.getNombre());
                    c.setGenero(cliente.getGenero());
                    c.setEdad(cliente.getEdad());
                    c.setIdentificacion(cliente.getIdentificacion());
                    clienteService.save(c);
                    return new ResponseEntity<>(new RespuestaApi(OK, c), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new RespuestaApi(HttpStatus.NOT_FOUND.toString(), NO_EXISTE), HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = LISTAR_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> listarPorId(@PathVariable Long id) {

        return clienteService.findById(id)
                .map(c -> new ResponseEntity<>(new RespuestaApi(OK, c), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new RespuestaApi(HttpStatus.NOT_FOUND.toString(), NO_EXISTE), HttpStatus.NOT_FOUND));
    }
}
