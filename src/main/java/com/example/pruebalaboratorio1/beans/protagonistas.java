package com.example.pruebalaboratorio1.beans;

public class protagonistas {

    private pelicula idPelicula;

    private actor idActor;

    public protagonistas() {
    }

    public protagonistas(pelicula idPelicula, actor idActor) {
        this.idPelicula = idPelicula;
        this.idActor = idActor;
    }

    public pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public actor getIdActor() {
        return idActor;
    }

    public void setIdActor(actor idActor) {
        this.idActor = idActor;
    }
}
