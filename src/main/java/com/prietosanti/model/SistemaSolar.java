package com.prietosanti.model;

import com.prietosanti.util.CalulosTrigonometricos;

public class SistemaSolar {

    private Sol sol;
    private Planeta planeta1;
    private Planeta planeta2;
    private Planeta planeta3;

    public SistemaSolar(Planeta planeta1, Planeta planeta2, Planeta planeta3) {
        this.planeta1 = planeta1;
        this.planeta2 = planeta2;
        this.planeta3 = planeta3;
        this.sol = new Sol(new Punto(0,0));
    }

    public void desplazarPlanetas(int diaActual) {
        planeta1.desplazar(diaActual);
        planeta2.desplazar(diaActual);
        planeta3.desplazar(diaActual);
    }

    public boolean planetasAlineados(int toleranciaDeError) {
        return CalulosTrigonometricos.puntosConLaMismaRecta(planeta1.posicion(), planeta2.posicion(), planeta3.posicion(), toleranciaDeError);
    }

    public boolean planetasAlineadosConElCentro(int toleranciaDeError) {
        return CalulosTrigonometricos.rectaResultantePasaPorOrigen(planeta1.posicion(), planeta2.posicion(), toleranciaDeError);
    }

    public boolean solEnElInteriorDelTriangulo() {
        return CalulosTrigonometricos.centroDentroDeTriangulo(sol.getPosicion(), planeta1.posicion(), planeta2.posicion(), planeta3.posicion());
    }

    public double getPerimetroFormadoPorLosPlanetas() {
        return CalulosTrigonometricos.perimetroTriangulo(planeta1.posicion(), planeta2.posicion(), planeta3.posicion());
    }

}
