package clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Recepcionista extends Usuario {
	protected ArrayList<String>funciones = new ArrayList();

	public Recepcionista(String dNI, String nombre, String apellidos, int telefono, String email, String direccion,
			SimpleDateFormat fechaNacimiento, int salario, ArrayList<String> funciones) {
		super(dNI, nombre, apellidos, telefono, email, direccion, fechaNacimiento, salario);
		this.funciones = funciones;
	}
	public Recepcionista() {
		super();
		this.funciones = new ArrayList();
		
	}
	public ArrayList<String> getFunciones() {
		return funciones;
	}
	public void setFunciones(ArrayList<String> funciones) {
		this.funciones = funciones;
	}
	@Override
	public String toString() {
		return "Nombre=" + nombre + ", Apellidos=" + apellidos + ", Telefono=" + telefono + ", Email="
				+ email + ", Direccion=" + direccion + ", Salario=" + salario + ", Funciones=" + funciones ;
	}
}
