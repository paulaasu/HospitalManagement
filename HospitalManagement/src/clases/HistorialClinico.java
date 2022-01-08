package clases;

public class HistorialClinico {
	public int numHistorial;
	 public String enfermedad;
	 public String sintomas;
	 public String tiempo;
	 public String sed;
	 public String sueño;
	 public String miccion;
	 public String dni_p;
	
	public HistorialClinico(int numHistorial, String enfermedad, String sintomas, String tiempo, String sed,
			String sueño, String miccion) {
		super();
		this.numHistorial = numHistorial;
		this.enfermedad = enfermedad;
		this.sintomas = sintomas;
		this.tiempo = tiempo;
		this.sed = sed;
		this.sueño = sueño;
		this.miccion = miccion;
	}

	public HistorialClinico(int numHistorial, String enfermedad, String sintomas, String tiempo, String sed,
			String sueño, String miccion,String dni_p) {
		super();
		this.numHistorial = numHistorial;
		this.enfermedad = enfermedad;
		this.sintomas = sintomas;
		this.tiempo = tiempo;
		this.sed = sed;
		this.sueño = sueño;
		this.miccion = miccion;
		this.dni_p = dni_p;
	}

	public HistorialClinico() {
		// TODO Auto-generated constructor stub
	}

	public HistorialClinico(String string, String enfermedad2, String sintomas2, String tiempo2, String sed2,
			String sueño2, String miccion2) {
		this.enfermedad = enfermedad;
		this.sintomas = sintomas;
		this.tiempo = tiempo;
		this.sed = sed;
		this.sueño = sueño;
		this.miccion = miccion;
		this.dni_p = dni_p;
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

	public String getSueño() {
		return sueño;
	}

	public void setSueño(String sueño) {
		this.sueño = sueño;
	}

	public String getMiccion() {
		return miccion;
	}

	public void setMiccion(String miccion) {
		this.miccion = miccion;
	}
	public String getDni() {
		return dni_p;
	}

	public void setDni(String dni_p) {
		this.dni_p = dni_p;
	}

	@Override
	public String toString() {
		return "HistorialClinico [numHistorial=" + numHistorial + ", enfermedad=" + enfermedad + ", sintomas="
				+ sintomas + ", tiempo=" + tiempo + ", sed=" + sed + ", sueño=" + sueño + ", miccion=" + miccion + "]";
	}
}
	
	
