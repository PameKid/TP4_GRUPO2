<%@ page import="java.util.List" %>
<%@ page import="entidades.Seguro" %>
<%@ page import="entidades.TipoSeguro" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Seguros</title>
</head>
<body>

<nav>
    <a href="Inicio.jsp">Inicio</a> |
    <a href="AgregarSeguroServlet">AgregarSeguros</a> |
    <a href="ListarSegurosServlet">ListarSeguros</a>
</nav>

<hr>

<h1>Listado de Seguros</h1>

<form action="ListarSegurosServlet" method="get">
    <label>Filtrar por tipo:</label>
    <select name="idTipoSeguro">
        <option value="0">Todos</option>

        <%
            List<TipoSeguro> listaTipos = (List<TipoSeguro>) request.getAttribute("listaTipos");

            if (listaTipos != null) {
                for (TipoSeguro tipo : listaTipos) {
        %>
            <option value="<%= tipo.getIdTipoSeguro() %>">
                <%= tipo.getDescripcionTipoSeguro() %>
            </option>
        <%
                }
            }
        %>
    </select>

    <input type="submit" value="Filtrar">
</form>

<br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Descripción</th>
        <th>Tipo</th>
        <th>Costo contratación</th>
        <th>Costo máximo asegurado</th>
    </tr>

    <%
        List<Seguro> lista = (List<Seguro>) request.getAttribute("listaSeguros");

        if (lista != null && !lista.isEmpty()) {
            for (Seguro s : lista) {
    %>
        <tr>
            <td><%= s.getIdSeguro() %></td>
            <td><%= s.getDescripcion() %></td>
            <td><%= s.getTipoSeguro().getDescripcionTipoSeguro() %></td>
            <td><%= s.getCostoContratacion() %></td>
            <td><%= s.getCostoMaximoAsegurado() %></td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="5">No hay datos</td>
        </tr>
    <%
        }
    %>
</table>

</body>
</html>