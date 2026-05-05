package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	/** Método para obtener el último id de la base de datos**/
	public int obtenerProximoId() throws Exception {
		int proximoId = 1;
		String query = "SELECT MAX(idSeguro) AS max_id FROM seguros";
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = Conexion.getInstancia().getConnection().prepareStatement(query);
			rs = pst.executeQuery();
			if (rs.next()) {
				proximoId = rs.getInt("max_id") + 1;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
		}
		return proximoId;
	}

}
