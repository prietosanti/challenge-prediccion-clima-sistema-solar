package com.prietosanti.configuration;

import com.prietosanti.service.PrediccionClimaticaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InicioPrediccion {

    public static final Log LOGGER = LogFactory.getLog(InicioPrediccion.class);

    @Autowired
    @Qualifier("prediccionClimaticaServiceImpl")
    private PrediccionClimaticaService prediccionClimaticaService;

    @PostConstruct
    public void predecirClimas() {
        LOGGER.info("Iniciando la predicción a 10 años");
        prediccionClimaticaService.iniciarPrediccionEnAnios(10);
    }
}
