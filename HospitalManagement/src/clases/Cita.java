package clases;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 // no deberia tener un paciente una cita en vez de el medico?
public class Cita extends Paciente{
	/*como tenemos un SimpleDateFormat  hay que pase de   date a string*/
	//private static SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
	//nuevo
	private Date fechaYHoraCita ; // no entiendo porque da error
	
	TipoCita tipodecita;
	public Cita(String dni, String nombre , String apellidos, Date fechaYHoraCita, TipoCita tipodecita) {
		super(dni,nombre,apellidos);
		this.fechaYHoraCita = fechaYHoraCita;
		this.tipodecita = tipodecita;
	}

	public Cita() {
		super();
	}
	public Date getFechaYHoraCita() {
		return fechaYHoraCita;
	}
	public void setFechaYHoraCita(Date fechaYHoraCita) {

			this.fechaYHoraCita =  fechaYHoraCita;
		
	}
	public TipoCita getTipodecita() {
		return tipodecita;
	}
	public void setTipodecita(TipoCita tipodecita) {
		this.tipodecita = tipodecita;
	}
	@Override
	public String toString() {
		return "Cita nombre=" + nombre + ", apellidos=" + apellidos + ", fechaYHoraCita=" + fechaYHoraCita
				+ ", tipodecita=" + tipodecita ;
	}
	

	
}
