package com.prietosanti.service;

import com.prietosanti.model.ClimaModel;
import com.prietosanti.model.TipoClima;

import java.util.List;

public interface PrediccionClimaticaService {

    void iniciarPrediccionEnAnios(int anios);
    ClimaModel consultarClimaPorDia(int dia);
    List<ClimaModel> listarTodos();
    List<ClimaModel> consultarDiasPorTipoClima(TipoClima tipoClima);
}
