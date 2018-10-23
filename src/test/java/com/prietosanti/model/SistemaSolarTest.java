package com.prietosanti.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SistemaSolarTest {

    private SistemaSolar sistemaSolar;

    @Before
    public void setup(){
        sistemaSolar = new SistemaSolar(
                new Planeta("Ferengis", 500, new DesplazamientoCiruclar(1, DesplazamientoCiruclar.Sentido.HORARIO)),
                new Planeta("Vulcanos", 1000, new DesplazamientoCiruclar(5, DesplazamientoCiruclar.Sentido.ANTIHORARIO)),
                new Planeta("Betasoides", 2000, new DesplazamientoCiruclar(3, DesplazamientoCiruclar.Sentido.HORARIO))
        );

    }

    @Test
    public void planetasAlineadosConElCentro() {
        sistemaSolar.desplazarPlanetas(0);
        assertTrue(sistemaSolar.planetasAlineados(10));
        assertTrue(sistemaSolar.planetasAlineadosConElCentro(10));
    }

    @Test
    public void planetasAlineadosSinElCentro() {
        sistemaSolar.desplazarPlanetas(47);
        assertTrue(sistemaSolar.planetasAlineados(10));
        assertFalse(sistemaSolar.planetasAlineadosConElCentro(10));
    }

    @Test
    public void SolDentroDelTriangulo() {
        sistemaSolar.desplazarPlanetas(48);
        assertTrue(sistemaSolar.solEnElInteriorDelTriangulo());
        assertFalse(sistemaSolar.planetasAlineados(10));
        assertFalse(sistemaSolar.planetasAlineadosConElCentro(10));
    }
}