package clases;

import java.text.SimpleDateFormat;

public class Informatico extends Usuario {
	protected String cargo;

	public Informatico(String dNI, String nombre, String apellidos, int telefono, String email, String direccion,
			SimpleDateFormat fechaNacimiento, int salario, String cargo) {
		super(dNI, nombre, apellidos, telefono, email, direccion, fechaNacimiento, salario);
		this.cargo = cargo;
	}
	public Informatico() {
		super();
		this.cargo = "";
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	@Override
	public String toString() {
		return "Informatico [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email="
				+ email + ", direccion=" + direccion + ", salario=" + salario + ", cargo=" + cargo + "]";
	}
	
	
}
