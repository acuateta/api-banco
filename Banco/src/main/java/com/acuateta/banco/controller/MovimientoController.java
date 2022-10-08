package com.acuateta.banco.controller;


import com.acuateta.banco.dto.RespuestaApi;
import com.acuateta.banco.model.Movimiento;
import com.acuateta.banco.service.IMovimientoService;
import com.acuateta.banco.service.IReporteExcel;
import com.acuateta.banco.util.IDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.acuateta.banco.AppConstants.*;

@Slf4j
@RestController
@RequestMapping(MOVIMIENTOS)
public class MovimientoController {

    @Autowired
    private IMovimientoService service;

    @Autowired
    private IDownloadUtil downloadUtil;

    @Autowired
    private IReporteExcel reporteExcel;

    @GetMapping(value = LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movimiento>> listar() {
        try {
            return new ResponseEntity<>(
                    service.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = REGISTRAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> guardar(@RequestBody Movimiento movimiento) {
        try {
            service.save(movimiento);
            return new ResponseEntity<>(new RespuestaApi("OK", movimiento), HttpStatus.OK);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(new RespuestaApi(HttpStatus.NOT_ACCEPTABLE.toString(), e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(value = REPORTE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movimiento>> reporte(@PathVariable Long id,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        try {

            List<Movimiento> movimientoList = service.findByAllData(desde, hasta, id);
            InputStreamResource resource = reporteExcel.createXls(movimientoList, NOMBRE_HOJA);
            return downloadUtil.downloadFile(resource, ESTADO_DE_CUENTA);
        } catch (Exception e) {
            log.error(ERROR, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
