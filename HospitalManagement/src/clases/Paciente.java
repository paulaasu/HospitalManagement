package clases;

import java.text.SimpleDateFormat;

public class Paciente extends Persona{
	private HistorialClinico historialClinico;
	String genero;
	
	public Paciente(String dni, String nombre, String apellidos, int telefono, String email, String direccion,
			String fechaNac, HistorialClinico historialClinico,String genero) {
		super(dni, nombre, apellidos, telefono, email, direccion, fechaNac);
		this.historialClinico = historialClinico;
		this.genero = genero;
	}

	//constructor vacio
	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public HistorialClinico getHistorialClinico() {
		return historialClinico;
	}

	public void setHistorialClinico(HistorialClinico historialClinico) {
		this.historialClinico = historialClinico;
	}

	@Override
	public String toString() {
		return "Paciente [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email
				+ ", direccion=" + direccion + ", fechaNac=" + fechaNac + ", historialClinico=" + historialClinico
				+ "]";
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getGenero() {
		return genero;
	}

	
}