package com.prietosanti.service;

import com.prietosanti.model.ClimaModel;
import com.prietosanti.model.InformeModel;
import com.prietosanti.model.TipoClima;

import java.util.EnumMap;
import java.util.List;

public interface PrediccionClimaticaService {

    long consultarPeriodosPorClima(TipoClima tipoClima);
    void iniciarPrediccionEnAnios(int anios);
    ClimaModel consultarClimaPorDia(int dia);
    List<ClimaModel> listarTodos();
    List<ClimaModel> consultarDiasPorTipoClima(TipoClima tipoClima);
    InformeModel informeDePredicciones();
}
