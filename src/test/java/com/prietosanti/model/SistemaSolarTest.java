package com.prietosanti.model;

import com.prietosanti.configuration.PredictorConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PredictorConfiguration.class })
public class SistemaSolarTest {

    @Autowired
    private SistemaSolar sistemaSolar;

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