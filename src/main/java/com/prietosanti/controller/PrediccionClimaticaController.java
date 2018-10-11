package com.prietosanti.controller;

import com.prietosanti.model.ClimaModel;
import com.prietosanti.model.TipoClima;
import com.prietosanti.service.PrediccionClimaticaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrediccionClimaticaController {

    private final PrediccionClimaticaService prediccionClimaticaService;

    public PrediccionClimaticaController(@Qualifier("prediccionClimaticaServiceImpl") PrediccionClimaticaService prediccionClimaticaService) {
        this.prediccionClimaticaService = prediccionClimaticaService;
    }

    @GetMapping("/climas/dias/{dia}")
    public ResponseEntity getClimaDeUnDia(@PathVariable(value = "dia") int dia) {
        boolean diaValido = dia >= 0 && dia <= (365 * 10);
        if (diaValido) {
            return new ResponseEntity<>(prediccionClimaticaService.consultarClimaPorDia(dia), HttpStatus.OK);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El día consultado debe estar comprendido entre 0 y 3650");
        }
    }

    @GetMapping("/climas/tipos/{tipo}")
    public ResponseEntity<List<ClimaModel>> getClimasPorTipo(@PathVariable(value = "tipo") String tipoClima) {
        try {
            return new ResponseEntity<>(prediccionClimaticaService.consultarDiasPorTipoClima(TipoClima.valueOf(tipoClima)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Los tipos de clima válidos son: " + TipoClima.getTiposDeClima(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/climas/tipos")
    public ResponseEntity<ArrayList<String>> getClimasPorTipo() {
        return new ResponseEntity<>(TipoClima.getTiposDeClima(), HttpStatus.OK);
    }

    @GetMapping("/climas")
    public ResponseEntity<List<ClimaModel>> listarClimas() {
        return new ResponseEntity<>(prediccionClimaticaService.listarTodos(), HttpStatus.OK);
    }

}
