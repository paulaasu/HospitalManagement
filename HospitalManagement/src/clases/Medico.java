package clases;

import java.util.ArrayList;

public class Medico extends Usuario {
		private ArrayList<Cita> cita;

		public Medico(ArrayList<Cita> cita) {
			super();
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
