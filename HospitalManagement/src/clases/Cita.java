package clases;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Cita extends Medico{
	/*como tenemos un SimpleDateFormat  hay que pase de   date a string*/
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );
	private Date fechaYHoraCita ;
	TipoCita tipodecita;
	public Cita(ArrayList<Cita> cita, String fechaYHoraCita, TipoCita tipodecita) {
		super();
		try {
			this.fechaYHoraCita = (Date) sdf.parse(fechaYHoraCita);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tipodecita =tipodecita.CABECERA ;
	}
	public Cita() {
		super();
	}
	public Date getFechaYHoraCita() {
		return fechaYHoraCita;
	}
	public void setFechaYHoraCita(String fechaYHoraCita) {
		try {
			this.fechaYHoraCita =  (Date) sdf.parse(fechaYHoraCita);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
<<<<<<< HEAD
	protected static final SimpleDateFormat fechaYHoraCita = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );

	
	//Getter fechaYHoraCita
	public static SimpleDateFormat getFechayhoracita() {
		return fechaYHoraCita;
	}
=======
	
>>>>>>> branch 'master' of https://github.com/paulaasu/HospitalManagement
	
	
}
