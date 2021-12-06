package clases;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Persona extends Usuario {
	private String dni;
	
	String nombre;
	String apellidos;
	int telefono;
	String email;
	String direccion;
	 static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
	String fechaNac;
	int salario;
	
	public Persona(String dni, String nombre, String apellidos, int telefono, String email, String direccion,
			String fechaNac, int salario) {
		super();
		this.dni = dni;
		String erdni = "[0-9]{8}[A-Z]";
		boolean correctoDni = Pattern.matches(erdni, "12345678H");
		if(correctoDni)
			System.out.println("El DNI es correcto");
		else
			System.out.println("El DNI no es correcto");
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.fechaNac =sdf.format(fechaNac);
		this.salario = salario;
	}
	public Persona() {
		super();
		this.dni = "";
		this.nombre = "";
		this.apellidos = "";
		this.telefono = 0;
		this.email = "";
		this.direccion = "";
		fechaNac = "00-00-0000";
		this.salario =0;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		this.fechaNac = sdf.format(fechaNac);
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Persona Dni=" +dni+ ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", email=" + email + ", direccion=" + direccion + ", FechaNacimiento=" + fechaNac
				+ ", salario=" + salario ;
	}
	
}

