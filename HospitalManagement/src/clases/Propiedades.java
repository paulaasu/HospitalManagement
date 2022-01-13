package clases;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {

	public static void main(String[] args) {
		Properties p = new Properties();
		p.setProperty("Autoras", "Maitane Garcia, Paula Asua,Irene Gonzalez,Cristina Alonso,Alba Cuñado");
		p.setProperty("Email", "maitane.garcia@opendeusto.es , paula.asua1@opendeusto.es , irenegonzalez@opendeusto.es , cris.alonso@opendeusto.es , alba.cunado@opendeusto.es");
		try {
			p.store(new FileWriter("HospitalManagement.properties"), "HospitalManagement Properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
