package clases;

import java.text.SimpleDateFormat;

public class Paciente /*extends Persona*/{
	private HistorialClinico historialClinico;
	
	public String genero;
	public String dni,nombre,apellidos,direccion,fechaNac;
	public int telefono;

	public Paciente(String dni, String nombre , String apellidos) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	public Paciente ( String dni, String nombre, String apellidos, int telefono, String direccion,
			String fechaNac, HistorialClinico historialClinico,String genero){
		
		this.historialClinico = historialClinico;
		this.genero = genero;
		this.nombre=nombre;
		this.dni=dni;
		this.telefono=telefono;
		this.apellidos=apellidos;
		this.direccion=direccion;
	}
	public Paciente ( String dni, String nombre, String apellidos, int telefono, String direccion,
			String fechaNac,String genero){
		
		
		this.genero = genero;
		this.nombre=nombre;
		this.dni=dni;
		this.telefono=telefono;
		this.apellidos=apellidos;
		this.direccion=direccion;
	}
	
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/*
	public Paciente(String dni, String nombre, String apellidos, int telefono, String email, String direccion,
			String fechaNac, HistorialClinico historialClinico,String genero) {
		super(dni, nombre, apellidos, telefono, email, direccion, fechaNac);
		this.historialClinico = historialClinico;
		//this.genero = genero;
	}
*/
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
		return "Paciente [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + 
				 ", direccion=" + direccion + ", fechaNac=" + fechaNac + ", historialClinico=" + historialClinico
				+ "]";
	}
	
	

	
}