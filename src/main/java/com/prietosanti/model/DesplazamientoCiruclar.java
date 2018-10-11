package com.prietosanti.model;

public class DesplazamientoCiruclar implements TipoDesplazamiento {

    public enum Sentido {
        HORARIO,
        ANTIHORARIO
    }

    private double gradosPorDia;
    private Sentido sentido;

    public DesplazamientoCiruclar(double gradosPorDia, Sentido sentido) {
        this.gradosPorDia = gradosPorDia;
        this.sentido = sentido;
    }

    @Override
    public double desplazarse(int cantidadDias) {
        int signo = sentido.equals(Sentido.HORARIO) ? -1 : 1;
        return signo * gradosPorDia * cantidadDias;
    }

    @Override
    public String toString() {
        return "DesplazamientoCiruclar{" +
                "gradosPorDia=" + gradosPorDia +
                ", sentido=" + sentido +
                '}';
    }
}
