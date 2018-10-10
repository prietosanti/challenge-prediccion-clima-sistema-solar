package com.prietosanti.model;

import java.util.ArrayList;
import java.util.List;

public class PredictorClimatico {

    public static final int DIAS_POR_ANIO = 365;

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
                    predecirTipoDeLLuvia(diasDeLLuviaACorregir, diaActual);
                } else {
                    climas.add(ClimaModel.of(diaActual, TipoClima.DESPEJADO));
                }
            }
        }

        return climas;
    }

    private void predecirTipoDeLLuvia(List<Integer> diasDeLLuviaACorregir, int diaActual) {
        // Calculo el perimetro mayor
        double perimetroTriangulo = sistemaSolar.getPerimetroFormadoPorLosPlanetas();

        if(Double.compare(perimetroTriangulo, mayorPerimetro) == 0 ) {
            diasDeLLuviaACorregir.add(diaActual);
            climas.add(diaActual, ClimaModel.of(diaActual, TipoClima.LLUVIA_INTENSA));
        }
        if (Double.compare(perimetroTriangulo, mayorPerimetro) > 0) {
            mayorPerimetro = perimetroTriangulo;
            corregirClimasConTipoClima(diasDeLLuviaACorregir, TipoClima.LLUVIA);
            diasDeLLuviaACorregir.clear();
            diasDeLLuviaACorregir.add(diaActual);
            climas.add(diaActual, ClimaModel.of(diaActual, TipoClima.LLUVIA_INTENSA));
        } else {
            climas.add(diaActual, ClimaModel.of(diaActual, TipoClima.LLUVIA));
        }
    }

    private void corregirClimasConTipoClima(List<Integer> posiblesDiasACorregir, TipoClima tipoClima) {
        posiblesDiasACorregir
                .stream()
                .forEach(diaACorregir -> climas.set(diaACorregir, ClimaModel.of(diaACorregir, tipoClima)));
    }
}
