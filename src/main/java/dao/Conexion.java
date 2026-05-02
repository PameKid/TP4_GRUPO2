package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
-- 1. Creación de la base de datos
CREATE DATABASE SegurosGroup;
USE SegurosGroup;

-- 2. Creación de la tabla TipoSeguros
-- Se utiliza para llenar el desplegable en AgregarSeguro.jsp y el filtro en ListarSeguros.jsp
CREATE TABLE TipoSeguros (
    idTipo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(100) NOT NULL
);

-- 3. Creación de la tabla Seguros
-- El ID se genera automáticamente (AUTO_INCREMENT) según el requerimiento
CREATE TABLE Seguros (
    idSeguro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL,
    idTipo INT NOT NULL,
    costoContratacion DECIMAL(10, 2) NOT NULL,
    costoMaximo DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idTipo) REFERENCES TipoSeguros(idTipo)
);

-- 4. Inserción de datos de prueba (basados en los ejemplos del enunciado)
INSERT INTO TipoSeguros (descripcion) VALUES 
('Seguro de casas'), 
('Seguro de motos'),
('Seguro de vida');

INSERT INTO Seguros (descripcion, idTipo, costoContratacion, costoMaximo) VALUES 
('Es un seguro de salud para intervenciones quirúrgicas de alta complejidad, a un costo accesible.', 1, 600.0, 15000.0),
('Asegura toda la gama de motocicletas de uso particular, desde motos y ciclomotores hasta deportivas.', 2, 1200.0, 28000.0);

 * Clase para crear una conexión única a la bbdd para toda la app.
 * Evita repetir código y mantener múltiples conexiones abiertas
 *
 * Ejemplo de uso:
 * Connection conn = Conexion.getInstancia().getConnection();
 * PreparedStatement ps = Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO...");
 */

public class Conexion {

	private static final String HOST = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "segurosgroup";
	private static final String USER = "";
	private static final String PASS = "";

	private static Conexion instancia;
	private Connection connection;
	
	private Conexion() {
		String cadenaConexion = HOST + DB_NAME;

		/** carga el driver mysql**/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			this.connection = DriverManager.getConnection(cadenaConexion, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** método estático para obtener la instancia única. **/
	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	/** método para obtener la conexión desde la instancia **/
	public Connection getConnection() {
		if (connection == null) {
			throw new IllegalStateException("No se pudo establecer la conexion con la base de datos.");
		}
		return connection;
	}
	
	/** método para cerrar la conexión **/
	public void cerrarConexion() {
	    try {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	} 
}
