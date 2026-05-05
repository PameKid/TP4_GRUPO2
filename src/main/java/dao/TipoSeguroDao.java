package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Seguro;
import entidades.TipoSeguro;

public class TipoSeguroDao {

    public List<TipoSeguro> listarTiposSeguros() {
        List<TipoSeguro> tipos = new ArrayList<TipoSeguro>();
        String sql = "SELECT idTipo, descripcion FROM tipoSeguros";

        try {
            Connection conexion = Conexion.getInstancia().getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idTipo = resultSet.getInt("idTipo");
                String descripcion = resultSet.getString("descripcion");
                TipoSeguro tipo = new TipoSeguro(idTipo, descripcion);
                tipos.add(tipo);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }
    
    public List<Seguro> listarSeguros() throws Exception{
        List<Seguro> lista = new ArrayList<>();
        String sql = "SELECT s.idSeguro, s.descripcion, s.idTipo, s.costoContratacion, s.costoAsegurado, " +
                     "t.descripcion AS tipoDescripcion " +
                     "FROM seguros s " +
                     "INNER JOIN tipoSeguros t ON s.idTipo = t.idTipo";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = Conexion.getInstancia().getConnection().prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();

                seguro.setIdSeguro(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));

                TipoSeguro tipo = new TipoSeguro();
                tipo.setIdTipoSeguro(rs.getInt("idTipo"));
                tipo.setDescripcionTipoSeguro(rs.getString("tipoDescripcion"));

                seguro.setTipoSeguro(tipo);
                seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
                seguro.setCostoMaximoAsegurado(rs.getDouble("costoAsegurado"));

                lista.add(seguro);
            }

        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        }

        return lista;
    }
    
    public List<Seguro> listarSegurosPorTipo(int idTipo) throws Exception {
        List<Seguro> lista = new ArrayList<>();

        String sql = "SELECT s.idSeguro, s.descripcion, s.idTipo, s.costoContratacion, s.costoAsegurado, " +
                     "t.descripcion AS tipoDescripcion " +
                     "FROM seguros s " +
                     "INNER JOIN tipoSeguros t ON s.idTipo = t.idTipo " +
                     "WHERE s.idTipo = ?";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = Conexion.getInstancia().getConnection().prepareStatement(sql);
            pst.setInt(1, idTipo);

            rs = pst.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();

                seguro.setIdSeguro(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));

                TipoSeguro tipo = new TipoSeguro();
                tipo.setIdTipoSeguro(rs.getInt("idTipo"));
                tipo.setDescripcionTipoSeguro(rs.getString("tipoDescripcion"));

                seguro.setTipoSeguro(tipo);
                seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
                seguro.setCostoMaximoAsegurado(rs.getDouble("costoAsegurado"));

                lista.add(seguro);
            }

        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        }

        return lista;
    }
}
 