package servlet;
import entidades.Seguro;
import entidades.TipoSeguro;
import dao.TipoSeguroDao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListarSegurosServlet
 */
@WebServlet("/ListarSegurosServlet")
public class ListarSegurosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarSegurosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idTipo = request.getParameter("idTipoSeguro");
		TipoSeguroDao datos = new TipoSeguroDao();
		ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
		

		if (request.getAttribute("listaTipos")== null) {
			try {				
				ArrayList<TipoSeguro> listaTipos = datos.listarTiposSeguros();
				request.setAttribute("listaTipos", listaTipos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// Lógica para ingestar de datos la lista de seguros
		if (idTipo == null || idTipo.equals("0")) {
			// Es la primera vez o eligió "Todos"
			// Lista sin filtrar
			try {
				listaSeguros = datos.listarSeguros();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    request.setAttribute("listaSeguros", listaSeguros);
		    RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguro.jsp");   
	        rd.forward(request, response);
		} else {
		    // Es una búsqueda filtrada
			try {
				listaSeguros = datos.listarSegurosPorTipo(Integer.parseInt(idTipo));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    request.setAttribute("listaSeguros", listaSeguros);
		    RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguro.jsp");   
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
