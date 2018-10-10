package com.prietosanti.model;

public class Planeta implements ObjetoOrbitable {

    private String nombre;
    private double distanciaAlCentro;
    private TipoDesplazamiento desplazamientoCircular;
    private Punto posicion;

    public Planeta(String nombre, double distanciaAlSol, TipoDesplazamiento desplazamientoCircular) {
        this.posicion = new Punto(distanciaAlSol, 0);
        this.nombre = nombre;
        this.distanciaAlCentro = distanciaAlSol;
        this.desplazamientoCircular = desplazamientoCircular;
    }

    @Override
    public Punto posicion() {
        return posicion;
    }

    @Override
    public Planeta desplazar(int cantidadDias) {
        // Pox(x) = distancia * cos(angulo)
        // Pos(y) = distancia * sen(angulo)

        double gradosDesplazados = desplazamientoCircular.desplazarse(cantidadDias);

        cambiarPosicion(new Punto(distanciaAlCentro * Math.cos(Math.toRadians(gradosDesplazados)),
                distanciaAlCentro * Math.sin(Math.toRadians(gradosDesplazados))));

        return this;
    }

    private void cambiarPosicion(Punto punto) {
        this.posicion = punto;
    }

    @Override
    public String toString() {
        return "Planeta{" +
                "nombre=" + nombre +
                ", desplazamientoCircular=" + desplazamientoCircular +
                ", posicion=" + posicion +
                ", distanciaAlCentro='" + distanciaAlCentro + '\'' +
                '}';
    }

}
