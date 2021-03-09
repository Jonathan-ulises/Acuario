package com.example.acuarioutl.model;

public class Usuario {
    private int idUsuario;
    private String userName;
    private String pass;

    public Usuario(){};

    public Usuario(String userName, String pass){
        this.userName = userName;
        this.pass = pass;
    }

    public Usuario(int idUsuario, String userName, String pass){
        this.idUsuario = idUsuario;
        this.userName = userName;
        this.pass = pass;
    }

    //GETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    //SETTERS

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", userName='" + userName + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
