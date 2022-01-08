package Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clases.BaseDeDatos;
import clases.HistorialClinico;
import clases.Paciente;
import paneles.PanelExportar;

public class PanelExportarTest {
	PanelExportar e;
	Connection con ;
	ArrayList<HistorialClinico> ret;
	ArrayList<Paciente> ret1;
	@Before
	public void setUp() throws Exception {
		con= BaseDeDatos.initBD("BaseDeDatos.db");
		 ret = new ArrayList<>();
	}

	//RESULTADO CORRECTO: correcto
	@Test
	public void testObtenerHistorial() {
		//e.ExportarFicheroCsvH();
		ret=BaseDeDatos.ObtenerHistorialDni(con,"76855447v");
		
		assertEquals(0, ret.size());
		
	}
	
	//RESULTADO CORRECTO: error
	@Test
	public void testObtenerPaciente() {
		//e.ExportarFicheroCsvH();
		ret1=BaseDeDatos.getPaciente(con);
		assertEquals(null, ret.size());
		
	}

}
