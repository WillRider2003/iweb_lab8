package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao { // Extend baseDao

    public ArrayList<pelicula> listarPeliculas() {
        ArrayList<pelicula> listaPeliculas = new ArrayList<>();

        try (Connection conn = getConnection(); // Use baseDao connection
                Statement stmt = conn.createStatement()) {

            String sql = "SELECT P.*, G.nombre as nombre_genero, S.nombreServicio " +
                    "FROM PELICULA P " +
                    "INNER JOIN GENERO G ON P.IDGENERO = G.IDGENERO " +
                    "INNER JOIN STREAMING S ON P.IDSTREAMING = S.IDSTREAMING " +
                    "ORDER BY P.RATING DESC, P.BOXOFFICE DESC";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                movie.setIdPelicula(rs.getInt("idPelicula"));
                movie.setTitulo(rs.getString("titulo"));
                movie.setDirector(rs.getString("director"));
                movie.setAnoPublicacion(rs.getInt("anoPublicacion"));
                movie.setRating(rs.getDouble("rating"));
                movie.setBoxOffice(rs.getDouble("boxOffice"));

                // Create genero object
                genero gen = new genero();
                gen.setIdGenero(rs.getInt("idGenero"));
                gen.setNombre(rs.getString("nombre_genero"));
                movie.setGenero(gen);

                // Create streaming object
                streaming stream = new streaming();
                stream.setIdStreaming(rs.getInt("idStreaming"));
                stream.setNombreServicio(rs.getString("nombreServicio"));
                movie.setStreaming(stream);

                movie.setDuracion(rs.getString("duracion"));
                movie.setPremioOscar(rs.getBoolean("premioOscar"));

                listaPeliculas.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero) {
        ArrayList<pelicula> listaPeliculasFiltradas = new ArrayList<>();

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT P.*, G.nombre as nombre_genero, S.nombreServicio " +
                                "FROM PELICULA P " +
                                "INNER JOIN GENERO G ON P.IDGENERO = G.IDGENERO " +
                                "INNER JOIN STREAMING S ON P.IDSTREAMING = S.IDSTREAMING " +
                                "WHERE P.IDGENERO = ? " +
                                "ORDER BY P.RATING DESC, P.BOXOFFICE DESC")) {

            pstmt.setInt(1, idGenero);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pelicula movie = new pelicula();
                movie.setIdPelicula(rs.getInt("idPelicula"));
                movie.setTitulo(rs.getString("titulo"));
                movie.setDirector(rs.getString("director"));
                movie.setAnoPublicacion(rs.getInt("anoPublicacion"));
                movie.setRating(rs.getDouble("rating"));
                movie.setBoxOffice(rs.getDouble("boxOffice"));

                genero gen = new genero();
                gen.setIdGenero(rs.getInt("idGenero"));
                gen.setNombre(rs.getString("nombre_genero"));
                movie.setGenero(gen);

                streaming stream = new streaming();
                stream.setIdStreaming(rs.getInt("idStreaming"));
                stream.setNombreServicio(rs.getString("nombreServicio"));
                movie.setStreaming(stream);

                movie.setDuracion(rs.getString("duracion"));
                movie.setPremioOscar(rs.getBoolean("premioOscar"));

                listaPeliculasFiltradas.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculasFiltradas;
    }

    public void crearPelicula(pelicula nuevaPelicula) {
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO PELICULA (titulo, director, anoPublicacion, rating, " +
                                "boxOffice, idGenero, idStreaming, duracion, premioOscar) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, nuevaPelicula.getTitulo());
            pstmt.setString(2, nuevaPelicula.getDirector());
            pstmt.setInt(3, nuevaPelicula.getAnoPublicacion());
            pstmt.setDouble(4, nuevaPelicula.getRating());
            pstmt.setDouble(5, nuevaPelicula.getBoxOffice());
            pstmt.setInt(6, nuevaPelicula.getGenero().getIdGenero());
            pstmt.setInt(7, nuevaPelicula.getStreaming().getIdStreaming());
            pstmt.setString(8, nuevaPelicula.getDuracion());
            pstmt.setBoolean(9, nuevaPelicula.isPremioOscar());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarPelicula(int idPelicula) {
        // First get the movie to validate
        pelicula pelicula = obtenerPeliculaPorId(idPelicula);

        if (pelicula != null && validarBorrado(pelicula)) {
            try (Connection conn = getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(
                            "DELETE FROM PELICULA WHERE idPelicula = ?")) {

                pstmt.setInt(1, idPelicula);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private pelicula obtenerPeliculaPorId(int idPelicula) {
        pelicula movie = null;

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT P.*, G.nombre as nombre_genero, S.nombreServicio " +
                                "FROM PELICULA P " +
                                "INNER JOIN GENERO G ON P.IDGENERO = G.IDGENERO " +
                                "INNER JOIN STREAMING S ON P.IDSTREAMING = S.IDSTREAMING " +
                                "WHERE P.idPelicula = ?")) {

            pstmt.setInt(1, idPelicula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                movie = new pelicula();
                movie.setIdPelicula(rs.getInt("idPelicula"));
                movie.setTitulo(rs.getString("titulo"));
                movie.setDirector(rs.getString("director"));
                movie.setAnoPublicacion(rs.getInt("anoPublicacion"));
                movie.setRating(rs.getDouble("rating"));
                movie.setBoxOffice(rs.getDouble("boxOffice"));

                genero gen = new genero();
                gen.setIdGenero(rs.getInt("idGenero"));
                gen.setNombre(rs.getString("nombre_genero"));
                movie.setGenero(gen);

                streaming stream = new streaming();
                stream.setIdStreaming(rs.getInt("idStreaming"));
                stream.setNombreServicio(rs.getString("nombreServicio"));
                movie.setStreaming(stream);

                movie.setDuracion(rs.getString("duracion"));
                movie.setPremioOscar(rs.getBoolean("premioOscar"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }

    @Override
    public boolean validarBorrado(Object entity) {
        if (entity instanceof pelicula) {
            pelicula pelicula = (pelicula) entity;
            // Extract minutes from duration string (e.g., "120min" -> 120)
            String duracionStr = pelicula.getDuracion().replace("min", "").trim();
            try {
                int duracion = Integer.parseInt(duracionStr);
                // Condition: duration > 180 minutes AND no Oscar award
                return duracion > 180 && !pelicula.isPremioOscar();
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}