package com.prietosanti.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoClima {

    LLUVIA("Lluvia"),
    LLUVIA_INTENSA("Lluvia Intensa"),
    SEQUIA("Sequía"),
    PYT_OPTIMA("Presión y Temperatura Óptima"),
    DESPEJADO("Cielo Despejado");

    private String descripcion;

    TipoClima(String valor) {
        this.descripcion = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static ArrayList<String> getTiposDeClima() {
        TipoClima[] climas = TipoClima.values();
        ArrayList<String> climaString = new ArrayList<String>();
        for (TipoClima clima : climas) {
            climaString.add(clima.toString());
        }
        return climaString;
    }
}
