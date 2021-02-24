package com.example.acuarioutl.model;

public class Peces {

    private int idPeces;
    private String nombreP;
    private String especieP;
    private float precio;
    private float raiting;
    private String foto;

    public Peces(String nombreP, String especieP, float precio, float raiting, String foto) {
        this.nombreP = nombreP;
        this.especieP = especieP;
        this.precio = precio;
        this.raiting = raiting;
        this.foto = foto;
    }

    public Peces(String nombre) {
        this.nombreP = nombre;
    }

    //GETTERS
    public String getNombre() {
        return nombreP;
    }

    public String getEspecie() {
        return especieP;
    }

    public float getPrecio() {
        return precio;
    }

    public float getCalificacion() {
        return raiting;
    }

    public String getFoto() {
        return foto;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombreP = nombre;
    }

    public void setEspecie(String especie) {
        this.especieP = especie;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCalificacion(float calificacion) {
        this.raiting = calificacion;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
