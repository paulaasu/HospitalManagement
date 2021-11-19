package clases;


public class Paciente extends Usuario{
	private HistorialClinico historialClinico;

	
	//Getters y Setters HistorialClinico
	public HistorialClinico getHistorialClinico() {
		return historialClinico;
	}

	public void setHistorialClinico(HistorialClinico historialClinico) {
		this.historialClinico = historialClinico;
	}

	@Override
	public String toString() {
		return "Paciente [historialClinico=" + historialClinico + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", salario=" + salario
				+ "]";
	}
	
	
	
}