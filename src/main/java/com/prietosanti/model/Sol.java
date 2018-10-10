package com.prietosanti.model;

public class Sol {

    private Punto posicion;
    private int radio;

    public Sol(Punto punto) {
        this.posicion = punto;
        this.radio = 30;
    }

    public Punto getPosicion() {
        return posicion;
    }

    public int getRadio() {
        return radio;
    }
}
