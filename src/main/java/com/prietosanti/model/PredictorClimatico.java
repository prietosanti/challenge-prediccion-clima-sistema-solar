package com.prietosanti.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class PredictorClimatico {

    private static final int DIAS_POR_ANIO = 365;

    private final SistemaSolar sistemaSolar;
    private List<ClimaModel> climas = new ArrayList<>();
    private int toleranciaDeError;
    private double mayorPerimetro = 0;

    public PredictorClimatico(SistemaSolar sistemaSolar, int toleranciaDeError) {
        this.sistemaSolar = sistemaSolar;
        this.toleranciaDeError = toleranciaDeError;
    }

    public List<ClimaModel> predecirClimasEnUnPeriodo(int anios) {
        int totalDias = DIAS_POR_ANIO * anios;

        List<Integer> diasDeLLuviaACorregir = new ArrayList<>();

        for (int diaActual = 0; diaActual <= totalDias; ++diaActual) {
            sistemaSolar.desplazarPlanetas(diaActual);

            if(sistemaSolar.planetasAlineados(toleranciaDeError)) {
                if(sistemaSolar.planetasAlineadosConElCentro(toleranciaDeError)) {
                    climas.add(ClimaModel.of(diaActual, TipoClima.SEQUIA));
                } else {
                    climas.add(ClimaModel.of(diaActual, TipoClima.PYT_OPTIMA));
                }
            } else {
                if (sistemaSolar.solEnElInteriorDelTriangulo()) {
                    predecirTipoDeLluvia(diasDeLLuviaACorregir, diaActual);
                } else {
                    climas.add(ClimaModel.of(diaActual, TipoClima.DESPEJADO));
                }
            }
        }
        corregirDiasDeLluviaIntensa(diasDeLLuviaACorregir);

        return climas;
    }

    private void predecirTipoDeLluvia(List<Integer> diasDeLLuviaACorregir, int diaActual) {
        climas.add(diaActual, ClimaModel.of(diaActual, TipoClima.LLUVIA));
        double perimetroTriangulo = sistemaSolar.getPerimetroFormadoPorLosPlanetas();

        if(Double.compare(perimetroTriangulo, mayorPerimetro) == 0 ) {
            diasDeLLuviaACorregir.add(diaActual);
        }
        if (Double.compare(perimetroTriangulo, mayorPerimetro) > 0) {
            mayorPerimetro = perimetroTriangulo;
            diasDeLLuviaACorregir.clear();
            diasDeLLuviaACorregir.add(diaActual);
        }
    }

    private void corregirDiasDeLluviaIntensa(List<Integer> posiblesDiasACorregir) {
        posiblesDiasACorregir
                .stream()
                .forEach(diaACorregir -> climas.set(diaACorregir, ClimaModel.of(diaACorregir, TipoClima.LLUVIA_INTENSA)));
    }
}
