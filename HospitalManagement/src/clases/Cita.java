package clases;

import java.text.SimpleDateFormat;

public class Cita {
	
	protected static final SimpleDateFormat fechaYHoraCita = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );

	
	//Getter fechaYHoraCita
	public static SimpleDateFormat getFechayhoracita() {
		return fechaYHoraCita;
	}
	
	
}
