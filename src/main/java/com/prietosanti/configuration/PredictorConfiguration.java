package com.prietosanti.configuration;

import com.prietosanti.model.DesplazamientoCiruclar;
import com.prietosanti.model.Planeta;
import com.prietosanti.model.PredictorClimatico;
import com.prietosanti.model.SistemaSolar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredictorConfiguration {

    @Bean
    public SistemaSolar sistemaSolar() {
        return new SistemaSolar(
                new Planeta("Ferengis", 500, new DesplazamientoCiruclar(1, DesplazamientoCiruclar.Sentido.HORARIO)),
                new Planeta("Vulcanos", 1000, new DesplazamientoCiruclar(5, DesplazamientoCiruclar.Sentido.ANTIHORARIO)),
                new Planeta("Betasoides", 2000, new DesplazamientoCiruclar(3, DesplazamientoCiruclar.Sentido.HORARIO))
        );
    }

    @Bean
    public PredictorClimatico predictorClimatico(SistemaSolar sistemaSolar) {
        // Mayor tolerancia, menos precisa es la recta que alinea los planetas
        // Tolerancia de 0% a 100%
        int toleranciaDeError = 10;
        return new PredictorClimatico(sistemaSolar, toleranciaDeError);
    }
}