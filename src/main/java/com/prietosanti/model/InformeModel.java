package com.prietosanti.model;

import java.util.EnumMap;

public class InformeModel {

    private EnumMap<TipoClima, Long> periodosPorTipoClima;

    public static InformeModel of(EnumMap<TipoClima, Long> periodosPorClima) {
        return new InformeModel(periodosPorClima);
    }

    private InformeModel() {
    }

    private InformeModel(EnumMap<TipoClima, Long> periodosPorTipoClima) {
        this.periodosPorTipoClima = periodosPorTipoClima;
    }

    public EnumMap<TipoClima, Long> getPeriodosPorTipoClima() {
        return periodosPorTipoClima;
    }
}
