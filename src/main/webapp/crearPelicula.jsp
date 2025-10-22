<%@page import="java.util.ArrayList" %>
    <%@page import="com.example.pruebalaboratorio1.beans.genero" %>
        <%@page import="com.example.pruebalaboratorio1.beans.streaming" %>
            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Creacion Pelicula</title>
            </head>

            <body>
                <h2>Formulario de Creacion de Pelicula</h2>
                <form action="listaPeliculas" method="post">
                    <!-- Tus campos del formulario -->
                    <input type="text" name="titulo" placeholder="Titulo" required>
                    <input type="text" name="director" placeholder="Director" required>
                    <input type="number" name="anoPublicacion" placeholder="Anio" required>
                    <input type="number" step="0.1" name="rating" placeholder="Rating" required>
                    <input type="number" name="boxOffice" placeholder="Box Office" required>
                    <input type="number" name="duracion" placeholder="Duracion (minutos)" required>

                    <!-- ComboBox para Género -->
                    <select name="genero" required>
                        <option value="">Seleccione genero</option>
                        <% ArrayList<genero> listarGeneros = (ArrayList<genero>) request.getAttribute("listarGeneros");
                                if (listarGeneros != null) {
                                for (genero g : listarGeneros) {
                                %>
                                <option value="<%= g.getIdGenero() %>">
                                    <%= g.getNombre() %>
                                </option>
                                <% } } %>
                    </select>

                    <!-- ComboBox para Streaming -->
                    <select name="streaming" required>
                        <option value="">Seleccione streaming</option>
                        <% ArrayList<streaming> listarStreaming = (ArrayList<streaming>)
                                request.getAttribute("listarStreaming");
                                if (listarStreaming != null) {
                                for (streaming s : listarStreaming) {
                                %>
                                <option value="<%= s.getIdStreaming() %>">
                                    <%= s.getNombreServicio() %>
                                </option>
                                <% } } %>
                    </select>

                    <!-- ComboBox para Oscar Award -->
                    <select name="premioOscar" required>
                        <option value="no">No</option>
                        <option value="si">Sí</option>
                    </select>

                    <button type="submit">Crear Pelicula</button>
                </form>
            </body>

            </html>