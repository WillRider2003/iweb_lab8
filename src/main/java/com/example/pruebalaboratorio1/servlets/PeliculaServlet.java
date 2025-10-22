package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.daos.listasDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        listasDao listaDao = new listasDao();

        ArrayList<genero> listarGeneros = listaDao.listarGeneros();
        ArrayList<streaming> listarStreaming = listaDao.listarStreaming();

        request.setAttribute("listarGeneros", listarGeneros);
        request.setAttribute("listarStreaming", listarStreaming);

        switch (action) {
            case "listar":
                ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                request.setAttribute("listaPeliculas", listaPeliculas);

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request, response);
                break;

            case "filtrar":
                int idGenero = Integer.parseInt(request.getParameter("genero"));
                ArrayList<pelicula> listaPeliculasFiltradas = peliculaDao.listarPeliculasFiltradas(idGenero);
                request.setAttribute("listaPeliculas", listaPeliculasFiltradas);

                RequestDispatcher viewFiltrado = request.getRequestDispatcher("listaPeliculas.jsp");
                viewFiltrado.forward(request, response);
                break;

            case "crear":
                RequestDispatcher view2 = request.getRequestDispatcher("crearPelicula.jsp");
                view2.forward(request, response);
                break;

            case "borrar":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                peliculaDao.borrarPelicula(idPelicula);
                response.sendRedirect(request.getContextPath() + "/listaPeliculas?action=listar");
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Handle movie creation
        peliculaDao peliculaDao = new peliculaDao();
        listasDao listaDao = new listasDao();

        // Create new pelicula object
        pelicula nuevaPelicula = new pelicula();
        nuevaPelicula.setTitulo(request.getParameter("titulo"));
        nuevaPelicula.setDirector(request.getParameter("director"));
        nuevaPelicula.setAnoPublicacion(Integer.parseInt(request.getParameter("anoPublicacion")));
        nuevaPelicula.setRating(Double.parseDouble(request.getParameter("rating")));
        nuevaPelicula.setBoxOffice(Double.parseDouble(request.getParameter("boxOffice")));

        // Create genero object
        genero gen = new genero();
        gen.setIdGenero(Integer.parseInt(request.getParameter("genero")));
        nuevaPelicula.setGenero(gen);

        // Create streaming object
        streaming stream = new streaming();
        stream.setIdStreaming(Integer.parseInt(request.getParameter("streaming")));
        nuevaPelicula.setStreaming(stream);

        nuevaPelicula.setDuracion(request.getParameter("duracion") + "min");
        nuevaPelicula.setPremioOscar(request.getParameter("premioOscar").equals("si"));

        // Create the movie
        peliculaDao.crearPelicula(nuevaPelicula);

        // Redirect to movie list
        response.sendRedirect(request.getContextPath() + "/listaPeliculas?action=listar");
    }
}