package clases;


import static org.junit.Assert.*;

import org.junit.Test;

public class testPersona {

	@Test
	public void testPersona() {
		Persona p1 = new Persona("23456789W"," "," ",345678908,null,null," ",0);
		assertTrue(p1.getDni().equals("23456789W"));
		
		Persona p2 =  new Persona("234567"," "," ",345678908,null,null," ",0);
		assertTrue(p2.getDni().equals("INCORRECTO"));
		
	}
	}


