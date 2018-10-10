package com.prietosanti.controller;

import com.prietosanti.model.ClimaModel;
import com.prietosanti.model.TipoClima;
import com.prietosanti.service.PrediccionClimaticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrediccionClimaticaController {

    @Autowired
    @Qualifier("prediccionClimaticaServiceImpl")
    private PrediccionClimaticaService prediccionClimaticaService;

    @GetMapping("/clima")
    public ResponseEntity getClimaDeUnDia(@RequestParam(value = "dia", required = true) int dia) {
        boolean diaValido = dia >= 0 && dia <= (365 * 10);
        if(diaValido) {
            return new ResponseEntity<>(prediccionClimaticaService.consultarClimaPorDia(dia), HttpStatus.OK);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("El día consultado debe estar comprendido entre 1 y 3650");
        }
    }

    @GetMapping("/climas/tipo")
    public ResponseEntity<List<ClimaModel>> getClimasPorTipo(@RequestParam(value = "tipoClima", required = true) String tipoClima) {
        try {
            return new ResponseEntity<>(prediccionClimaticaService.consultarDiasPorTipoClima(TipoClima.valueOf(tipoClima)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Los tipos de clima válidos son: " + TipoClima.getTiposDeClima(), HttpStatus.OK);
        }
    }

    @GetMapping("/climas")
    public ResponseEntity<List<ClimaModel>> listarClimas() {
        return new ResponseEntity<>(prediccionClimaticaService.listarTodos(), HttpStatus.OK);
    }

}
