package com.prietosanti.entity;

import com.prietosanti.model.TipoClima;

import javax.persistence.*;

@Entity
@Table(name = "CLIMA")
public class Clima {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "DIA", nullable = false)
    private int dia;

    @Column(name = "TIPO_CLIMA")
    private TipoClima tipoClima;

    public Clima(){}

    public Clima(int dia, TipoClima tipoClima) {
        this.dia = dia;
        this.tipoClima = tipoClima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public TipoClima getTipoClima() {
        return tipoClima;
    }

    public void setTipoClima(TipoClima tipoClima) {
        this.tipoClima = tipoClima;
    }

}
