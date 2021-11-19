package clases;

public class HistorialClinico {
		String diagnostico;
		String analisis; 	//Igual estaría bien crear un enum de TipoAnalisis/TipoCita
		
		//Getters y setters de HistorialClinico
		public String getDiagnostico() {
			return diagnostico;
		}
		public void setDiagnostico(String diagnostico) {
			this.diagnostico = diagnostico;
		}
		public String getAnalisis() {
			return analisis;
		}
		public void setAnalisis(String analisis) {
			this.analisis = analisis;
		}
		@Override
		public String toString() {
			return "HistorialClinico [diagnostico=" + diagnostico + ", analisis=" + analisis + "]";
		}
		
		
}
