package entidades;

public class Seguro {
	int idSeguro;
	String descripcion;
	int tipoSeguro;
	double costoContratacion;
	double costoMaximoAsegurado;

	/** Constructor vacío **/
	public Seguro() {
	}

	/** Constructor parametrizado con todos los datos **/
	public Seguro(int idSeguro, String descripcion, int tipoSeguro, double costoContratacion,
			double costoMaximoAsegurado) {
		this.idSeguro = idSeguro;
		this.descripcion = descripcion;
		this.tipoSeguro = tipoSeguro;
		this.costoContratacion = costoContratacion;
		this.costoMaximoAsegurado = costoMaximoAsegurado;
	}

	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		if (idSeguro <= 0) {
			throw new IllegalArgumentException("El ID debe ser mayor a cero.");
		}
		this.idSeguro = idSeguro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (descripcion == null || descripcion.trim().isEmpty()) {
			throw new IllegalArgumentException("La descripción es obligatoria.");
		}
		this.descripcion = descripcion;
	}

	public int getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(int tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public double getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(double costoContratacion) {
		if (costoContratacion < 0) {
			throw new IllegalArgumentException("El costo no puede ser negativo.");
		}
		this.costoContratacion = costoContratacion;
	}

	public double getCostoMaximoAsegurado() {
		return costoMaximoAsegurado;
	}

	public void setCostoMaximoAsegurado(double costoMaximoAsegurado) {
		this.costoMaximoAsegurado = costoMaximoAsegurado;
	}

}
