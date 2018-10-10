package com.prietosanti.model;

public class ClimaModel {

    private int dia;
    private TipoClima tipoClima;

    public static ClimaModel of(int dia, TipoClima tipoClima) {
        return new ClimaModel(dia, tipoClima);
    }

    private ClimaModel() {}

    private ClimaModel(int dia, TipoClima tipoClima) {
        this.dia = dia;
        this.tipoClima = tipoClima;
    }

    public int getDia() {
        return dia;
    }

    public TipoClima getTipoClima() {
        return tipoClima;
    }
}
