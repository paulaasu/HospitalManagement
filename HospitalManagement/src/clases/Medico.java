package clases;

import java.util.ArrayList;

public class Medico extends Usuario {
		private ArrayList<Cita> cita;

		//Getters y Setters Médico
		public ArrayList<Cita> getCita() {
			return cita;
		}

		public void setCita(ArrayList<Cita> cita) {
			this.cita = cita;
		}

		@Override
		public String toString() {
			return "Medico [cita=" + cita + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
					+ ", email=" + email + ", direccion=" + direccion + ", salario=" + salario + "]";
		}
		
		
}
