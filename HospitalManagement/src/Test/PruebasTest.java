package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import clases.BaseDeDatos;
import clases.HistorialClinico;
import clases.Paciente;
import clases.TipoUsuario;
import clases.Usuario;
import paneles.PanelExportar;

public class PruebasTest {
	PanelExportar e;
	Connection con ;
	ArrayList<HistorialClinico> ret;
	ArrayList<Paciente> ret1;
	Paciente p;
	HistorialClinico h;
	
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		con= BaseDeDatos.initBD("testBD.db",true);
		BaseDeDatos.crearTablas(con,true);
		
		/*---PANEL EXPORTAR CSV---*/
		 ret = new ArrayList<>();
		 
		 /*---PANEL IMPORTAR CSV---*/
		 p=new Paciente("75888562f","ana","gonzalez arnaiz",655894321,"dir 1","01-02-2020","femenino");
			//dni existente
		 h=new HistorialClinico("Dolor de cabeza","Dolor","3 dias","no","no","no","76855467v");

	}

	
	/*---PANEL EXPORTAR CVS---*/
	
	//RESULTADO CORRECTO: correcto
	@Test
	public void testObtenerHistorial() {			
		ret=BaseDeDatos.ObtenerHistorialDni(con,"76855447v");
		assertEquals(0, ret.size());
			
	}
		
	//RESULTADO CORRECTO: error
	@Test
	public void testObtenerPaciente() {
		ret1=BaseDeDatos.getPaciente(con);
		assertNotEquals(null, ret.size());
			
	}
	
	/*---PANEL IMPORTAR CVS---*/
	
	//RESULTADO CORRECTO: correcto (en primera ejecucion)
	@Test
	public void testInsertarPaciente() throws IOException {

		  BaseDeDatos.insertarPaciente(con,p);
			
		assertNotEquals(null, p);
	}
//	//RESULTADO CORRECTO: correcto
//	@Test
//	public void testInsertarHistorial() throws IOException {
//
//		BaseDeDatos.insertarHistorial(con,h);
//				
//		assertNotEquals(null, h);
//	}
	@Test //TEST HISTORIAL: que al insertar un historial de un paciente que no existe genere una excepción
	public void getHistorial() {
		try {//DNI correcto
			BaseDeDatos.anadirHistorial(BaseDeDatos.con, "Diabetes", "Fatiga, visión borrosa, úlceras, pérdida de peso", "4 meses", "Aumento", "Normal", "Aumento", "7907963T");
		} catch (SQLException e) {
			fail( "Error en el borrar el historial correcto" );
		}
		
		try {//DNI incorrecto
			BaseDeDatos.anadirHistorial(BaseDeDatos.con, "Diabetes", "Fatiga, visión borrosa, úlceras, pérdida de peso", "4 meses", "Aumento", "Normal", "Aumento", "11111111T");
		} catch (SQLException e) {
			fail( "No hay error en borrar el historial incorrecto" );
		}
		}
	
	
}


