package servlet;

import java.util.List;

import dao.SeguroDao;
import dao.TipoSeguroDao;
import entidades.Seguro;
import entidades.TipoSeguro;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgregarSeguroServlet
 */
@WebServlet("/AgregarSeguroServlet")
public class AgregarSeguroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarSeguroServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TipoSeguroDao tipoDao = new TipoSeguroDao();
		SeguroDao seguroDao = new SeguroDao();

		try {
			List<TipoSeguro> listaTipos = tipoDao.listarTiposSeguros();
			request.setAttribute("listaTipos", listaTipos);
			request.setAttribute("proximoId", seguroDao.obtenerProximoId());
		} catch (Exception e) {
			request.setAttribute("error", "Error al cargar los datos: " + e.getMessage());
		}
		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descripcion = request.getParameter("descripcion");
		String idTipoSeguroTexto = request.getParameter("idTipoSeguro");
		String costoContratacionTexto = request.getParameter("costoContratacion");
		String costoMaximoAseguradoTexto = request.getParameter("costoMaximoAsegurado");

		SeguroDao dao = new SeguroDao();
		TipoSeguroDao tipoDao = new TipoSeguroDao();

		try {
			// validar los datos strings
			if (descripcion == null || descripcion.trim().isEmpty() || idTipoSeguroTexto == null
					|| idTipoSeguroTexto.trim().isEmpty() || costoContratacionTexto == null
					|| costoContratacionTexto.trim().isEmpty() || costoMaximoAseguradoTexto == null
					|| costoMaximoAseguradoTexto.trim().isEmpty()) {

				throw new IllegalArgumentException("Todos los campos son obligatorios.");
			}

			// conversión de tipos para doubles
			int idTipoSeguro = Integer.parseInt(idTipoSeguroTexto);
			double costoContratacion = Double.parseDouble(costoContratacionTexto);
			double costoMaximoAsegurado = Double.parseDouble(costoMaximoAseguradoTexto);

			// evitar valores negativos
			if (costoContratacion < 0 || costoMaximoAsegurado < 0) {
				throw new IllegalArgumentException("Los costos no pueden ser negativos.");
			}

			TipoSeguro tipo = new TipoSeguro();
			tipo.setIdTipoSeguro(idTipoSeguro);

			Seguro seguro = new Seguro();
			seguro.setDescripcion(descripcion);
			seguro.setTipoSeguro(tipo);
			seguro.setCostoContratacion(costoContratacion);
			seguro.setCostoMaximoAsegurado(costoMaximoAsegurado);

			int resultado = dao.agregarSeguro(seguro);

			if (resultado == 1) {
				request.setAttribute("mensaje", "Seguro agregado correctamente");
			} else {
				request.setAttribute("error", "No se pudo agregar el seguro");
			}

		} catch (NumberFormatException e) {
			request.setAttribute("error", "Error de formato en los datos ingresados.");
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", "Error de validación: " + e.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
		} finally {
			// se vuelve a recargar, no importa el resultado!!
			try {
				request.setAttribute("listaTipos", tipoDao.listarTiposSeguros());
				request.setAttribute("proximoId", dao.obtenerProximoId());
			} catch (Exception ex) {
				request.setAttribute("error", "Error crítico al recargar la vista.");
			}
			request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
		}

	}
}
