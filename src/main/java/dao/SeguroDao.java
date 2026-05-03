package dao;

import java.sql.PreparedStatement;
import entidades.Seguro;

public class SeguroDao {

	public int agregarSeguro(Seguro seguro) throws Exception {
		int filas = 0;
		String query = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
		PreparedStatement pst = null;

		/**
		 * dejo que la excepción suba hacia el servlet ya que es quien se encargará de
		 * mapearla a un mensaje coherente en la vista si ocurre algo mal. No se coloca
		 * catch ni prints porque no es un programa de consola.
		 **/
		try {
			pst = Conexion.getInstancia().getConnection().prepareStatement(query);
			pst.setString(1, seguro.getDescripcion());
			pst.setInt(2, seguro.getTipoSeguro().getIdTipoSeguro());
			pst.setDouble(3, seguro.getCostoContratacion());
			pst.setDouble(4, seguro.getCostoMaximoAsegurado());
			filas = pst.executeUpdate();
		} finally {
			if (pst != null)
				pst.close();
		}

		return filas;
	}
}
