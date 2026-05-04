package servlet;
import java.util.ArrayList;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TipoSeguroDao tipoDao = new TipoSeguroDao();
		List<TipoSeguro> listaTipos =  tipoDao.listarTiposSeguros();
		request.setAttribute("listaTipos", listaTipos);
		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String descripcion = request.getParameter("descripcion");
		String idTipoSeguroTexto = request.getParameter("idTipoSeguro");
		String costoContratacionTexto = request.getParameter("costoContratacion");
		String costoMaximoAseguradoTexto = request.getParameter("costoMaximoAsegurado");
		
		int idTipoSeguro = Integer.parseInt(idTipoSeguroTexto);
	    double costoContratacion = Double.parseDouble(costoContratacionTexto);
	    double costoMaximoAsegurado = Double.parseDouble(costoMaximoAseguradoTexto);
	    
	    TipoSeguro tipo = new TipoSeguro();
	    tipo.setIdTipoSeguro(idTipoSeguro);

	    Seguro seguro = new Seguro();
	    seguro.setDescripcion(descripcion);
	    seguro.setTipoSeguro(tipo);
	    seguro.setCostoContratacion(costoContratacion);
	    seguro.setCostoMaximoAsegurado(costoMaximoAsegurado);

	    SeguroDao dao = new SeguroDao();
	    int resultado = 0;
		try {
			resultado = dao.agregarSeguro(seguro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    if (resultado == 1) {
	        request.setAttribute("mensaje", "Seguro agregado correctamente");
	    } else {
	        request.setAttribute("error", "No se pudo agregar el seguro");
	    }

	    TipoSeguroDao tipoDao = new TipoSeguroDao();
	    request.setAttribute("listaTipos", tipoDao.listarTiposSeguros());

	    request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
		
	}

}
