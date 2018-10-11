package com.prietosanti.service.impl;

import com.prietosanti.component.ClimaConverter;
import com.prietosanti.entity.Clima;
import com.prietosanti.model.ClimaModel;
import com.prietosanti.model.InformeModel;
import com.prietosanti.model.PredictorClimatico;
import com.prietosanti.model.TipoClima;
import com.prietosanti.repository.PrediccionClimaticaRepository;
import com.prietosanti.service.PrediccionClimaticaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;

@Service("prediccionClimaticaServiceImpl")
public class PrediccionClimaticaServiceImpl implements PrediccionClimaticaService {

    private final PrediccionClimaticaRepository prediccionClimaticaRepository;

    private final ClimaConverter climaConverter;

    private final PredictorClimatico predictorClimatico;

    public PrediccionClimaticaServiceImpl(
            @Qualifier("prediccionClimaticaRepository") PrediccionClimaticaRepository prediccionClimaticaRepository,
            @Qualifier("climaConverter") ClimaConverter climaConverter,
            @Qualifier("predictorClimatico") PredictorClimatico predictorClimatico
    )
    {
        this.prediccionClimaticaRepository = prediccionClimaticaRepository;
        this.climaConverter = climaConverter;
        this.predictorClimatico = predictorClimatico;
    }

    @Override
    public ClimaModel consultarClimaPorDia(int dia) {
        Clima climaDeUnDia = prediccionClimaticaRepository.findByDia(dia);
        return climaConverter.toModel(climaDeUnDia);
    }

    @Override
    public List<ClimaModel> listarTodos() {
        List<Clima> climas = prediccionClimaticaRepository.findAll();
        return climaConverter.toModelList(climas);
    }

    @Override
    public List<ClimaModel> consultarDiasPorTipoClima(TipoClima tipoClima) {
        List<Clima> climasPorTipoClima = prediccionClimaticaRepository.findAllByTipoClima(tipoClima);
        return climaConverter.toModelList(climasPorTipoClima);
    }

    @Override
    public long consultarPeriodosPorClima(TipoClima tipoClima) {
        return prediccionClimaticaRepository.countByTipoClima(tipoClima);
    }

    public InformeModel informeDePredicciones() {
        EnumMap<TipoClima, Long> periodosPorClima = new EnumMap<>(TipoClima.class);

        for (TipoClima tipoClima : TipoClima.values()) {
            long cantidad = consultarPeriodosPorClima(tipoClima);
            periodosPorClima.put(tipoClima, cantidad);
        }

        return InformeModel.of(periodosPorClima);
    }

    @Override
    public void iniciarPrediccionEnAnios(int anios) {
        List<ClimaModel> climasModel = predictorClimatico.predecirClimasEnUnPeriodo(anios);
        List<Clima> climas = climaConverter.toEntityList(climasModel);
        prediccionClimaticaRepository.saveAll(climas);
    }
}
