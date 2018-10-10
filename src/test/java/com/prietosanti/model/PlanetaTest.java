package com.prietosanti.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanetaTest {

    @Test
    public void test01_debeRespetarLosGradosEnSentidoHorario() {
        int gradosPorDia = 45;
        int cantidadDias = 7;

        Planeta planetaGirandoHorario = new Planeta("planetaH", 1,
                new DesplazamientoCiruclar(gradosPorDia, DesplazamientoCiruclar.Sentido.HORARIO));

        double gradosEnRadianes = Math.toRadians(cantidadDias * gradosPorDia);
        Punto puntoEsperado = new Punto(Math.cos(-gradosEnRadianes), Math.sin(-gradosEnRadianes));

        assertEquals(puntoEsperado.x(), planetaGirandoHorario.desplazar(cantidadDias).posicion().x(), 0);
        assertEquals(puntoEsperado.y(), planetaGirandoHorario.desplazar(cantidadDias).posicion().y(), 0);
    }

    @Test
    public void test02_debeRespetarLosGradosEnSentidoAntiHorario() {
        int gradosPorDia = 45;
        int cantidadDias = 7;

        Planeta planetaGirandoAntiHorario = new Planeta("planetaAH", 1,
                new DesplazamientoCiruclar(gradosPorDia, DesplazamientoCiruclar.Sentido.ANTIHORARIO));

        double gradosEnRadianes = Math.toRadians(cantidadDias * gradosPorDia);
        Punto puntoEsperado = new Punto(Math.cos(gradosEnRadianes), Math.sin(gradosEnRadianes));

        assertEquals(puntoEsperado.x(), planetaGirandoAntiHorario.desplazar(cantidadDias).posicion().x(), 0);
        assertEquals(puntoEsperado.y(), planetaGirandoAntiHorario.desplazar(cantidadDias).posicion().y(), 0);
    }

}