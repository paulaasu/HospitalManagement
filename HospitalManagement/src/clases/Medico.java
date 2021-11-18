package clases;

import java.util.ArrayList;

public class Medico extends Persona {
		private ArrayList<Cita> cita;

		
		public Medico(String dni, String nombre, String apellidos, int telefono, String email, String direccion,
				String fechaNac, int salario, ArrayList<Cita> cita) {
			super(dni, nombre, apellidos, telefono, email, direccion, fechaNac, salario);
			this.cita = new ArrayList<>();
		}
		public Medico() {
			super();
			this.cita = new ArrayList<>();
		}

		public ArrayList<Cita> getCita() {
			return cita;
		}

		public void setCita(ArrayList<Cita> cita) {
			this.cita = cita;
		}
		@Override
		public String toString() {
			return "Medico [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email="
					+ email + ", direccion=" + direccion + ", salario=" + salario + ", cita=" + cita + "]";
		}
}
