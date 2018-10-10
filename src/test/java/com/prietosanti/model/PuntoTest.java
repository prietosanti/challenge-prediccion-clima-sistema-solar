package com.prietosanti.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PuntoTest {

    @Test
    public void test01_debeFormatearElPuntoA1Decimal() {

        int gradosPorDia = 45;
        int cantidadDias = 3;

        double valorDeX = 5000*Math.cos(Math.toRadians(cantidadDias * gradosPorDia));
        double valorDeY = 5000*Math.sin(Math.toRadians(cantidadDias * gradosPorDia));

        Punto punto1 = new Punto(valorDeX, valorDeY);

        assertEquals(-3535.5, punto1.x(), 0);
        assertEquals(3535.5, punto1.y(), 0);
    }

    @Test
    public void test02_debeFormatearCorrectamenteElCero() {

        double ceroY = Math.sin(Math.toRadians(360));

        Punto puntoCero = new Punto(10, ceroY);

        assertEquals(0, puntoCero.y(), 0);
        assertTrue(Double.compare(puntoCero.y(), 0) == 0);
    }

    @Test
    public void test03_debeRedondearLosDecimales() {

        Punto punto = new Punto(6.25, 6.24);
        assertEquals(6.3, punto.x(), 0);
        assertEquals(6.2, punto.y(), 0);
    }

}