package com.example.pruebalaboratorio1.beans;

public class pelicula {

    private int idPelicula;
    private String titulo;

    private String director;
    private int anoPublicacion;
    private Double rating;
    private double boxOffice;
    private genero Genero;
    private streaming Streaming;
    private String duracion;
    private boolean premioOscar;

    public pelicula() {
    }

    public pelicula(int idPelicula, String titulo, String director, int anoPublicacion, Double rating, double boxOffice,
            genero Genero, streaming Streaming, String duracion, boolean premioOscar) {

        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.anoPublicacion = anoPublicacion;
        this.rating = rating;
        this.boxOffice = boxOffice;
        this.Genero = Genero;
        this.Streaming = Streaming;
        this.duracion = duracion;
        this.premioOscar = premioOscar;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public genero getGenero() {
        return Genero;
    }

    public void setGenero(genero Genero) {
        this.Genero = Genero;
    }

    public streaming getStreaming() {
        return Streaming;
    }

    public void setStreaming(streaming Streaming) {
        this.Streaming = Streaming;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean isPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }
}
