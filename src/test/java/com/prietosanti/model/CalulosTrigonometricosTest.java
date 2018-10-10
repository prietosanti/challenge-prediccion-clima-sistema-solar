package com.prietosanti.model;

import com.prietosanti.util.CalulosTrigonometricos;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalulosTrigonometricosTest {

    private int toleranciaDeError = 0;
    private Punto puntoCentral = new Punto(0, 0);

    @Test
    public void test1_debeRetornarElValorDeLaPendienteQuePasaPorDosPuntos() {
        assertEquals(1, CalulosTrigonometricos.pendienteQuePasaPorDosPuntos(new Punto(2.0, 2.0), new Punto(-1, -1)), 0.001);
        assertEquals(Double.POSITIVE_INFINITY, CalulosTrigonometricos.pendienteQuePasaPorDosPuntos(new Punto(2.0, 2.0), new Punto(2, -1)), 0.001);
    }

    @Test
    public void test2_debeRetornarElValorDeLaOrdenadaParaUnPuntoYPendiente() {
        Punto punto1 = new Punto(-2, 0);
        Punto punto2 = new Punto(1, 3);

        double pendientePositiva = CalulosTrigonometricos.pendienteQuePasaPorDosPuntos(punto1, punto2);

        assertEquals(2, CalulosTrigonometricos.ordenadaDePuntoYPendiente(new Punto(0, 2), pendientePositiva), 0);
        assertEquals(0, CalulosTrigonometricos.ordenadaDePuntoYPendiente(new Punto(-30, 0), -0.0), 0);
    }

    @Test
    public void test3_puntoPerteneceAMismaRectaQueOtroPuntoYPendiente() {
        Punto punto2 = new Punto(1, 3);
        double pendiente = 1;
        assertTrue(CalulosTrigonometricos.puntoTieneLaMismaRecta(new Punto(0.5, 2.5), punto2, pendiente));
    }

    @Test
    public void test4_puntoNoPerteneceAMismaRectaQueOtroPuntoYPendiente() {
        Punto punto2 = new Punto(1, 1);
        double pendiente = 1;
        assertFalse(CalulosTrigonometricos.puntoTieneLaMismaRecta(new Punto(0.5, 2.5), punto2, pendiente));
    }

    @Test
    public void test5_rectaPasaPorElOrigenTeniendoUnPuntoYPendienteDependiendoLaToleranciaDeError() {
        assertTrue(CalulosTrigonometricos.puntoYPendientePasanPorElOrigen(new Punto(2, 2), 1, toleranciaDeError));
        toleranciaDeError = 10; // 0.495 <= pendiente <= 0.605
        assertTrue(CalulosTrigonometricos.puntoYPendientePasanPorElOrigen(new Punto(1800, 1000), 0.54, toleranciaDeError));
        toleranciaDeError = 5; // 0.5225 <= pendiente <= 0.5775
        assertFalse(CalulosTrigonometricos.puntoYPendientePasanPorElOrigen(new Punto(1800, 1000), 0.521, toleranciaDeError));
        toleranciaDeError = 1; // 0.5445 <= pendiente <= 0.5555
        assertFalse(CalulosTrigonometricos.puntoYPendientePasanPorElOrigen(new Punto(1800, 1000), 0.545, toleranciaDeError));
        toleranciaDeError = 2; // 0.539 <= pendiente <= 0.561
        assertTrue(CalulosTrigonometricos.puntoYPendientePasanPorElOrigen(new Punto(1800, 1000), 0.545, toleranciaDeError));
    }

    @Test
    public void test6_rectaNoPasaPorElOrigenTeniendoUnPuntoYPendiente() {
        double pendiente = 1;
        assertFalse(CalulosTrigonometricos.puntoYPendientePasanPorElOrigen(new Punto(1, 3), pendiente, toleranciaDeError));
    }

    @Test
    public void test7_puntosConLaMismaRecta() {

        Punto punto1 = new Punto(-2.0, 0);
        Punto punto2 = new Punto(1, 3);
        Punto punto3 = new Punto(0, 2);

        Punto puntoParaleloEjeY1 = new Punto(1, 0);
        Punto puntoParaleloEjeY2 = new Punto(1, 1);
        Punto puntoParaleloEjeY3 = new Punto(1, 2);

        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(puntoParaleloEjeY1, puntoParaleloEjeY2, puntoParaleloEjeY3, toleranciaDeError));
    }

    @Test
    public void test8_puntosConDistintaRecta() {

        Punto punto1 = new Punto(-2.0, 0);
        Punto punto2 = new Punto(1, 2);
        Punto punto3 = new Punto(0, 2);

        assertFalse(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
    }

    @Test
    public void test9_puntosConLaMismaRectaQuePasanPorElOrigen() {
        toleranciaDeError = 10;
        assertTrue(CalulosTrigonometricos.rectaResultantePasaPorOrigen(new Punto(2, 2.5), new Punto(5, 6.25), toleranciaDeError));
        assertTrue(CalulosTrigonometricos.rectaResultantePasaPorOrigen(new Punto(-1, 0), new Punto(4, 0), toleranciaDeError));
        assertTrue(CalulosTrigonometricos.rectaResultantePasaPorOrigen(new Punto(0, 1), new Punto(0, 3), toleranciaDeError));
    }

    @Test
    public void test10_debeFormatearLaPendienteCon3Decimales() {

        Punto punto1 = new Punto(500 * Math.cos(Math.toRadians(-206)), 500 * Math.sin(Math.toRadians(-206)));
        Punto punto2 = new Punto(1000 * Math.cos(Math.toRadians(310)), 1000 * Math.sin(Math.toRadians(310)));

        assertEquals(-0.902, CalulosTrigonometricos.pendienteQuePasaPorDosPuntos(punto1, punto2), 0);
    }

    @Test
    public void test11_puntosDeUnaCircunferenciaConLaMismaRecta() {

        int gradosPorDia = 45;
        int cantidadDias = 3;
        Punto punto1 = new Punto(10 * Math.cos(cantidadDias * Math.toRadians(gradosPorDia)), 10 * Math.sin(cantidadDias * Math.toRadians(gradosPorDia)));
        Punto punto2 = new Punto(20 * Math.cos(cantidadDias * Math.toRadians(gradosPorDia)), 20 * Math.sin(cantidadDias * Math.toRadians(gradosPorDia)));
        Punto punto3 = new Punto(10000 * Math.cos(cantidadDias * Math.toRadians(gradosPorDia)), 10000 * Math.sin(cantidadDias * Math.toRadians(gradosPorDia)));

        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
    }

    @Test
    public void test12_puntosDeUnaCircunferenciaConLaMismaRectaQueNoPasanPorElOrigen() {

        Punto punto1 = new Punto(5 * Math.cos(Math.toRadians(-206)), 5 * Math.sin(Math.toRadians(-206)));
        Punto punto2 = new Punto(10 * Math.cos(Math.toRadians(310)), 10 * Math.sin(Math.toRadians(310)));
        Punto punto3 = new Punto(10 * Math.cos(Math.toRadians(310)), 10 * Math.sin(Math.toRadians(310)));

        Punto puntoA = new Punto(500 * Math.cos(Math.toRadians(50)), 500 * Math.sin(Math.toRadians(50)) );
        Punto puntoB = new Punto(1000 * Math.cos(Math.toRadians(71.25)), 1000 * Math.sin(Math.toRadians(71.25)) );
        Punto puntoC = new Punto(2000 * Math.cos(Math.toRadians(80.75)), 2000 * Math.sin(Math.toRadians(80.75)) );

        toleranciaDeError = 10;
        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(puntoA, puntoB, puntoC, toleranciaDeError));
        assertFalse(CalulosTrigonometricos.rectaResultantePasaPorOrigen(punto1, punto2, toleranciaDeError));
        assertFalse(CalulosTrigonometricos.rectaResultantePasaPorOrigen(puntoA, puntoB, toleranciaDeError));
    }

    @Test
    public void test13_puntosConLaMismaRectaYSiLaToleranciaEsAlMenosDel10Porciento() {

        Punto punto1 = new Punto(50, 50);
        Punto punto2 = new Punto(52, 100);
        Punto punto3 = new Punto(52, 95);

        toleranciaDeError = 10;
        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
        toleranciaDeError = 50;
        assertTrue(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
        toleranciaDeError = 9;
        assertFalse(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
    }

    @Test
    public void test14_puntosConDistintaRectaParalelaAlEjeYNoDependeDeLaTolerancia() {
        Punto punto1 = new Punto(50, 50);
        Punto punto2 = new Punto(50, 150);
        Punto punto3 = new Punto(200, 100);

        toleranciaDeError = 0;
        assertFalse(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
        toleranciaDeError = 100;
        assertFalse(CalulosTrigonometricos.puntosConLaMismaRecta(punto1, punto2, punto3, toleranciaDeError));
    }

    @Test
    public void test15_puntoCentralEstaDentroDeTriangulo() {
        Punto punto1 = new Punto(-341.0, -365.7);
        Punto punto2 = new Punto(573.6, -819.2);
        Punto punto3 = new Punto(0, 1000);

        assertTrue(CalulosTrigonometricos.centroDentroDeTriangulo( puntoCentral, punto1, punto2, punto3));
    }

    @Test
    public void test16_puntoCentralFueraDelTriangulo() {
        Punto punto1 = new Punto(-341.0, -365.7);
        Punto punto2 = new Punto(573.6, -819.2);
        Punto punto3 = new Punto(0, -1000);

        assertFalse(CalulosTrigonometricos.centroDentroDeTriangulo(puntoCentral, punto1, punto2, punto3));
    }

    @Test
    public void test17_distanciaEntre2Puntos() {
        Punto punto1 = new Punto(-341.0, -365.7);
        Punto punto2 = new Punto(573.6, -819.2);

        assertEquals(1020.860, CalulosTrigonometricos.distanciaEntrePuntos(punto1, punto2), 0);
    }

    @Test
    public void test18_perimetroDelTrianguloEsLaSumaDeSusLados() {
        Punto punto1 = new Punto(-341.0, -365.7);
        Punto punto2 = new Punto(573.6, -819.2);
        Punto punto3 = new Punto(0, -1000);

        assertEquals(2342.431, CalulosTrigonometricos.perimetroTriangulo(punto1, punto2, punto3), 0);
    }

}