package com.prietosanti.model;

public class Punto {

    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = Math.round(x * 10.0) / 10.0;
        this.y = Math.round(y * 10.0) / 10.0;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public String toString() {
        return "Punto{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public String toStringPunto() {
        return "(" + x +", " + y + ")";
    }

}
