package clases;

import java.text.SimpleDateFormat;

public class Pacientte {
	private String nombre;
	private String apellidos;
	private String dni;
	private static String fechaNac;
	private String genero;
	private int telefono;
	private String direccion;
	private int numHistorial;
	
	public Pacientte(String nombre, String apellidos, String dni, String fechaNac, String genero, int telefono,
			String direccion, int numHistorial) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNac = fechaNac;
		this.genero = genero;
		this.telefono = telefono;
		this.direccion = direccion;
		this.numHistorial = numHistorial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public static String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumHistorial() {
		return numHistorial;
	}

	public void setNumHistorial(int numHistorial) {
		this.numHistorial = numHistorial;
	}

	@Override
	public String toString() {
		return "Pacientte [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fechaNac=" + fechaNac
				+ ", genero=" + genero + ", telefono=" + telefono + ", direccion=" + direccion + ", numHistorial="
				+ numHistorial + "]";
	}
	
	

}
