package com.example.pruebalaboratorio1.beans;

public class streaming {
    int idStreaming;
    String nombreServicio;

    public streaming() {
    }

    public streaming(int idStreaming, String nombreServicio) {
        this.idStreaming = idStreaming;
        this.nombreServicio = nombreServicio;
    }

    public int getIdStreaming() {
        return idStreaming;
    }

    public void setIdStreaming(int idStreaming) {
        this.idStreaming = idStreaming;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
