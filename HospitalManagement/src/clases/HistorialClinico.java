package clases;

public class HistorialClinico {
		String diagnostico;
<<<<<<< HEAD
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
=======
		TipoAnalisis analisis; 	//Igual estaría bien crear un enum de TipoAnalisis/TipoCita�
		
		
				public HistorialClinico(String diagnostico, TipoAnalisis analisis) {
					super();
					this.diagnostico = diagnostico;
					this.analisis = analisis;
				}
				public HistorialClinico() {
					super();
					this.diagnostico = diagnostico;
					this.analisis =analisis.HEMATOLOG�A;
				}
				public String getDiagnostico() {
					return diagnostico;
				}
				public void setDiagnostico(String diagnostico) {
					this.diagnostico = diagnostico;
				}
				public TipoAnalisis getAnalisis() {
					return analisis;
				}
				public void setAnalisis(TipoAnalisis analisis) {
					this.analisis = analisis;
				}
				@Override
				public String toString() {
					return "HistorialClinico diagnostico=" + diagnostico + ", Tipo de analisis=" + analisis ;
				}

>>>>>>> branch 'master' of https://github.com/paulaasu/HospitalManagement
		
		
}
