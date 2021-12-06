package clases;

public class HistorialClinico {
		String diagnostico;

		TipoAnalisis analisis; 	//Igual estarÃ­a bien crear un enum de TipoAnalisis/TipoCitaç
		
		
				public HistorialClinico(String diagnostico, TipoAnalisis analisis) {
					super();
					this.diagnostico = diagnostico;
					this.analisis = analisis;
				}
				public HistorialClinico() {
					super();
					this.diagnostico = diagnostico;
					this.analisis =analisis.HEMATOLOGÍA;
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

}
