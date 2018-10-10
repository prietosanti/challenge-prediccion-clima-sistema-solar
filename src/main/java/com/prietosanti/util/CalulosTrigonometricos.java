package com.prietosanti.util;

import com.prietosanti.model.Punto;

import java.text.DecimalFormat;

public class CalulosTrigonometricos {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.000");

    public static double pendienteQuePasaPorDosPuntos(Punto punto1, Punto punto2) {
        if (Double.compare(punto1.x(), punto2.x()) == 0) {
            return Double.POSITIVE_INFINITY;
        }
        double pendiente = (punto2.y() - punto1.y()) / (punto2.x() - punto1.x());
        return Double.valueOf(decimalFormat.format(pendiente)) + 0.0;
    }

    public static double ordenadaDePuntoYPendiente(Punto punto, Double pendiente) {
        // ordenada b = y - mx
        if (pendiente.compareTo(Double.POSITIVE_INFINITY) == 0) {
            return Double.POSITIVE_INFINITY;
        }
        double ordenada = punto.y() - pendiente * punto.x();
        return Double.valueOf(decimalFormat.format(ordenada)) + 0.0;
    }

    public static boolean puntoTieneLaMismaRecta(Punto puntoDesconocido, Punto puntoDeLaRecta, double pendiente) {
        double ordenadaDeLaRecta = ordenadaDePuntoYPendiente(puntoDeLaRecta, pendiente);
        double ordenadaDePuntoAComparar = ordenadaDePuntoYPendiente(puntoDesconocido, pendiente);
        return Double.compare(ordenadaDeLaRecta, ordenadaDePuntoAComparar) == 0;
    }

    public static boolean puntoYPendientePasanPorElOrigen(Punto unPunto, double pendiente, int toleranciaDeError) {
        double pendienteDesdeElCentro = pendienteQuePasaPorDosPuntos(new Punto(0, 0), unPunto);
        double margenErrorInferiorPendiente = margenDeErrorInferior(pendienteDesdeElCentro, toleranciaDeError);
        double margenErrorSuperiorPendiente = margenDeErrorSuperior(pendienteDesdeElCentro, toleranciaDeError);

        return Double.compare(Math.abs(pendiente), margenErrorInferiorPendiente) >= 0 &&
                Double.compare(Math.abs(pendiente), margenErrorSuperiorPendiente) <= 0;
    }

    public static boolean puntosConLaMismaRecta(Punto punto1, Punto punto2, Punto punto3, int toleranciaDeError) {
        double pendiente = pendienteQuePasaPorDosPuntos(punto1, punto2);
        double margenErrorInferiorPendiente = margenDeErrorInferior(pendiente, toleranciaDeError);
        double margenErrorSuperiorPendiente = margenDeErrorSuperior(pendiente, toleranciaDeError);

        double pendienteAComparar = pendienteQuePasaPorDosPuntos(punto1, punto3);

        if (Double.compare(pendiente, Double.POSITIVE_INFINITY) == 0) {
            int toleranciaErrorDeAbscisa = 1;
            double margenErrorInferiorAbscisa = margenDeErrorInferior(punto1.x(), toleranciaErrorDeAbscisa);
            double margenErrorSuperiorAbscisa = margenDeErrorSuperior(punto1.x(), toleranciaErrorDeAbscisa);
            if (Double.compare(Math.abs(punto3.x()), margenErrorInferiorAbscisa) >= 0 &&
                    Double.compare(Math.abs(punto3.x()), margenErrorSuperiorAbscisa) <= 0) {
                return true;
            }
        }

        if (Double.compare(Math.abs(pendienteAComparar), margenErrorInferiorPendiente) >= 0 &&
                Double.compare(Math.abs(pendienteAComparar), margenErrorSuperiorPendiente) <= 0) {
            return true;
        }

        return false;
    }

    private static double margenDeErrorInferior(double elemento, int toleranciaDeError) {
        return Math.abs(elemento * (100 - toleranciaDeError) / 100);
    }

    private static double margenDeErrorSuperior(double elemento, int toleranciaDeError) {
        return Math.abs(elemento * (100 + toleranciaDeError) / 100);
    }

    public static boolean rectaResultantePasaPorOrigen(Punto punto1, Punto punto2, int toleranciaDeError) {
        double pendiente = pendienteQuePasaPorDosPuntos(punto1, punto2);
        if (Double.compare(pendiente, Double.POSITIVE_INFINITY) == 0) {
            return Double.compare(punto1.x(), 0) == 0;
        }
        return puntoYPendientePasanPorElOrigen(punto1, pendiente, toleranciaDeError);
    }

    /***
     *
     * @param puntoCentral
     * @param punto1: Vértice 1 del triangulo
     * @param punto2: Vértice 2 del triangulo
     * @param punto3: Vértice 3 del triangulo
     * @return verdadero, si el punto(0,0) está dentro del triángulo o falso en caso contrario
     */
    public static boolean centroDentroDeTriangulo(Punto puntoCentral, Punto punto1, Punto punto2, Punto punto3) {
        boolean orientacionP1P2P3 = puntosConOrientacionPositiva(punto1, punto2, punto3);
        boolean orientacionP1P2Centro = puntosConOrientacionPositiva(punto1, punto2, puntoCentral);
        boolean orientacionP2P3Centro = puntosConOrientacionPositiva(punto1, punto2, puntoCentral);
        boolean orientacionP3P1Centro = puntosConOrientacionPositiva(punto1, punto2, puntoCentral);

        return orientacionP1P2P3 == orientacionP1P2Centro == orientacionP2P3Centro == orientacionP3P1Centro;
    }

    private static boolean puntosConOrientacionPositiva(Punto punto1, Punto punto2, Punto punto3) {
        // (P1.x - P3.x) * (P2.y - P3.y) - (P1.y - P3.y) * (P2.x - P3.x)
        return Double.compare((punto1.x() - punto3.x()) * (punto2.y() - punto3.y()) -
                ((punto1.y() - punto3.y()) * (punto2.x() - punto3.x())), 0) >= 0;
    }

    public static double distanciaEntrePuntos(Punto punto1, Punto punto2) {
        double distanciaP1P2 = Math.sqrt(Math.pow((punto2.x() - punto1.x()), 2) + Math.pow((punto2.y() - punto1.y()), 2));
        return Double.valueOf(decimalFormat.format(distanciaP1P2)) + 0.0;
    }

    public static double perimetroTriangulo(Punto punto1, Punto punto2, Punto punto3) {
        double perimetro = distanciaEntrePuntos(punto1, punto2) + distanciaEntrePuntos(punto2, punto3) + distanciaEntrePuntos(punto1, punto3);
        return Double.valueOf(decimalFormat.format(perimetro)) + 0.0;
    }
}
