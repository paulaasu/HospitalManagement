package clases;

public class HistorialClinico {
	private int numHistorial;
	private String enfermedad;
	private String sintomas;
	private String tiempo;
	private String sed;
	private String sue�o;
	private String miccion;
	
	public HistorialClinico(int numHistorial, String enfermedad, String sintomas, String tiempo, String sed,
			String sue�o, String miccion) {
		super();
		this.numHistorial = numHistorial;
		this.enfermedad = enfermedad;
		this.sintomas = sintomas;
		this.tiempo = tiempo;
		this.sed = sed;
		this.sue�o = sue�o;
		this.miccion = miccion;
	}

	public int getNumHistorial() {
		return numHistorial;
	}

	public void setNumHistorial(int numHistorial) {
		this.numHistorial = numHistorial;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getSed() {
		return sed;
	}

	public void setSed(String sed) {
		this.sed = sed;
	}

	public String getSue�o() {
		return sue�o;
	}

	public void setSue�o(String sue�o) {
		this.sue�o = sue�o;
	}

	public String getMiccion() {
		return miccion;
	}

	public void setMiccion(String miccion) {
		this.miccion = miccion;
	}

	@Override
	public String toString() {
		return "HistorialClinico [numHistorial=" + numHistorial + ", enfermedad=" + enfermedad + ", sintomas="
				+ sintomas + ", tiempo=" + tiempo + ", sed=" + sed + ", sue�o=" + sue�o + ", miccion=" + miccion + "]";
	}
}
	
	
