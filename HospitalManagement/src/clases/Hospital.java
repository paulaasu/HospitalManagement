package clases;

public class Hospital {
		
	private String nombreHospital;
	private String ubicacion;
	
	
	//Getters y Setters Hospital
	public String getNombreHospital() {
		return nombreHospital;
	}


	public void setNombreHospital(String nombreHospital) {
		this.nombreHospital = nombreHospital;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	@Override
	public String toString() {
		return "Hospital [nombreHospital=" + nombreHospital + ", ubicacion=" + ubicacion + "]";
	}


	public static void main(String[] args) {
			
			
		}
}
