package com.example.acuarioutl.model;

import android.media.Image;

public class Acuario {

    private String nombreA;
    private String descripcionA;
    private String precioA;

    public Acuario(String nomA, String desA, String preA){
        this.nombreA = nomA;
        this.descripcionA = desA;
        this.precioA = preA;
    }

    public Acuario(){}

    //GETTERS

    public String getNombreA() {
        return nombreA;
    }

    public String getDescripcionA() {
        return descripcionA;
    }

    public String getPrecioA() {
        return precioA;
    }


    //SETTERS

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public void setDescripcionA(String descripcionA) {
        this.descripcionA = descripcionA;
    }

    public void setPrecioA(String precioA) {
        this.precioA = precioA;
    }

}
