package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
