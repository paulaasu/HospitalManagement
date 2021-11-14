package clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Cita extends Medico{
	
	protected  SimpleDateFormat fechaYHoraCita = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );
	TipoCita tipodecita;
	public Cita(ArrayList<Cita> cita, SimpleDateFormat fechaYHoraCita, TipoCita tipodecita) {
		super(cita);
		this.fechaYHoraCita = fechaYHoraCita;
		this.tipodecita = tipodecita.GINECOLOGO;
		
	}
	public SimpleDateFormat getFechaYHoraCita() {
		return fechaYHoraCita;
	}
	public void setFechaYHoraCita(SimpleDateFormat fechaYHoraCita) {
		this.fechaYHoraCita = fechaYHoraCita;
	}
	public TipoCita getTipodecita() {
		return tipodecita;
	}
	public void setTipodecita(TipoCita tipodecita) {
		this.tipodecita = tipodecita;
	}
	@Override
	public String toString() {
		return "Cita Nombre del medico =" + nombre + ", Fecha  Y HoraCita=" + fechaYHoraCita + ", Tipo de cita=" + tipodecita;
	}
	
	
	
}
