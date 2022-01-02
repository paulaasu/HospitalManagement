package clases;

import java.text.SimpleDateFormat;

public class Informatico extends Persona {
	protected String cargo;

	public Informatico(String dni, String nombre, String apellidos, int telefono, String email, String direccion,
			String fechaNac, String cargo) {
		super(dni, nombre, apellidos, telefono, email, direccion, fechaNac);
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
				+ email + ", direccion=" + direccion + ", salario=" + ", cargo=" + cargo + "]";
	}
	
	
}
