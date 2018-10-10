package com.prietosanti.service.impl;

import com.prietosanti.component.ClimaConverter;
import com.prietosanti.entity.Clima;
import com.prietosanti.model.ClimaModel;
import com.prietosanti.model.PredictorClimatico;
import com.prietosanti.model.TipoClima;
import com.prietosanti.repository.PrediccionClimaticaRepository;
import com.prietosanti.service.PrediccionClimaticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prediccionClimaticaServiceImpl")
public class PrediccionClimaticaServiceImpl implements PrediccionClimaticaService {

    @Autowired
    @Qualifier("prediccionClimaticaRepository")
    private PrediccionClimaticaRepository prediccionClimaticaRepository;

    @Autowired
    @Qualifier("climaConverter")
    private ClimaConverter climaConverter;

    @Autowired
    @Qualifier("predictorClimatico")
    private PredictorClimatico predictorClimatico;

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
    public void iniciarPrediccionEnAnios(int anios) {
        List<ClimaModel> climasModel = predictorClimatico.predecirClimasEnUnPeriodo(anios);
        List<Clima> climas = climaConverter.toEntityList(climasModel);
        prediccionClimaticaRepository.saveAll(climas);
    }
}
