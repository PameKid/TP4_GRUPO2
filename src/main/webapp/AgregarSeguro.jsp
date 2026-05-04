<%@ page import="java.util.List" %>
<%@ page import="entidades.TipoSeguro" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="Inicio.jsp">Inicio</a> |
<a href="agregarSeguroServlet">AgregarSeguro</a> |
<a href="listarSegurosServlet">ListarSeguros</a>

<hr>

<h1>Agregar Seguro</h1>

<%
    String mensaje = (String) request.getAttribute("mensaje");
    String error = (String) request.getAttribute("error");

    if (mensaje != null) {
%>
        <p style="color: green;"><%= mensaje %></p>
<%
    }

    if (error != null) {
%>
        <p style="color: red;"><%= error %></p>
<%
    }
%>

	<form method="post" action="AgregarSeguroServlet">
		
		<!-- IdSeguro -->
		<label>Id Seguro:</label>
		<input type="text" value="Automático" readonly>
		<br><br>
		
		<!-- Descripción -->
		<label>Descripción:</label>
		<input type="text" name="descripcion" required>
		<br><br>
		
		<!-- Tipo de seguro con un desplegable -->
		<label>Tipo de seguro:</label>
		<select name="idTipoSeguro" required>
	    <option value="">Seleccione un tipo</option>
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
		<br><br>
		
		<!-- Costo de contratación  -->
		<label>Costo contratación:</label>
		<input type="number" name="costoContratacion" step="0.01" required>
		<br><br>
		
		<label>Costo máximo asegurado:</label>
		<input type="number" name="costoMaximoAsegurado" step="0.01" required>
		<br><br>
		
		<input type="submit" value="Agregar Seguro">

		<a href="Inicio.jsp">
    	<button type="button">Cancelar</button>
		</a>
		
	</form>

</body>
</html>