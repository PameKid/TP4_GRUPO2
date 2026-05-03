package entidades;

public class TipoSeguro {
	
	//atributos
	private int idTipoSeguro;
	private String descripcionTipoSeguro;
	
	//constructor vacío
	public TipoSeguro() {
		
	}
	
	//constructor con parámetros
	
	public TipoSeguro(int idTipoSeguro, String descripcionTipoSeguro) {
		this.idTipoSeguro = idTipoSeguro;
		this.descripcionTipoSeguro = descripcionTipoSeguro; 
	}

	public int getIdTipoSeguro() {
		return idTipoSeguro;
	}

	public void setIdTipoSeguro(int idTipoSeguro) {
		if (idTipoSeguro <= 0) {
			throw new IllegalArgumentException("El ID debe ser mayor a cero.");
		}
		
		this.idTipoSeguro = idTipoSeguro;
	}

	public String getDescripcionTipoSeguro() {
		return descripcionTipoSeguro;
	}

	public void setDescripcionTipoSeguro(String descripcionTipoSeguro) {
		if (descripcionTipoSeguro == null || descripcionTipoSeguro.trim().isEmpty()) {
			throw new IllegalArgumentException("La descripción es obligatoria.");
		}
		
		this.descripcionTipoSeguro = descripcionTipoSeguro;
	}
	
}
