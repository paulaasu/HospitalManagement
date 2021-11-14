package clases;

import java.text.SimpleDateFormat;

public class Usuario {
	private String DNI;	
	String nombre;
	String apellidos;
	int telefono;
	String email;
	String direccion;
	private SimpleDateFormat FechaNacimiento = new SimpleDateFormat( "dd/MM/yyyy" );
	int salario;
	
	public Usuario(String dNI, String nombre, String apellidos, int telefono, String email, String direccion,
			SimpleDateFormat fechaNacimiento, int salario) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		FechaNacimiento = fechaNacimiento;
		this.salario = salario;
	}
	public Usuario() {
		super();
		DNI = "";
		this.nombre = "";
		this.apellidos = "";
		this.telefono = 0;
		this.email = "";
		this.direccion = "";
		FechaNacimiento = new SimpleDateFormat( "dd/MM/yyyy" ) ;
		this.salario =0;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
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
	public SimpleDateFormat getFechaNacimiento() {
		return FechaNacimiento;
	}
	public void setFechaNacimiento(SimpleDateFormat fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", email=" + email + ", direccion=" + direccion + ", FechaNacimiento=" + FechaNacimiento
				+ ", salario=" + salario + "]";
	}
}

