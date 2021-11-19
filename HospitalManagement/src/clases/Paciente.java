package clases;

import java.text.SimpleDateFormat;

public class Paciente extends Persona{
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
	
	
	
	public Paciente(String dNI, String nombre, String apellidos, int telefono, String email, String direccion,
			String fechaNac, int salario, HistorialClinico historialClinico) {
		super(dNI, nombre, apellidos, telefono, email, direccion, fechaNac, salario);
		this.historialClinico = historialClinico;
	}

	public HistorialClinico getHistorialClinico() {
		return historialClinico;
	}

	public void setHistorialClinico(HistorialClinico historialClinico) {
		this.historialClinico = historialClinico;
	}

	@Override
	public String toString() {
		return "Paciente [historialClinico=" + historialClinico + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + "]";
	}
	
}