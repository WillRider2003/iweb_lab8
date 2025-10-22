package com.example.pruebalaboratorio1.beans;

public class actor {


    private int idActor;
    private String Nombre;

    private String Apellido;

    private boolean premioOscar;

    private int anoNacimiento;

    public actor() {
    }

    public actor(int idActor, String nombre, String apellido, boolean premioOscar, int anoNacimiento) {
        this.idActor = idActor;
        Nombre = nombre;
        Apellido = apellido;
        this.premioOscar = premioOscar;
        this.anoNacimiento = anoNacimiento;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public boolean isPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }
}
