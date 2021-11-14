package clases;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.jdi.connect.spi.Connection;
/***
 * 
 * @author BSILV
 * Para llamar a la base de datos y abrirla
 *
 */
public class BaseDeDatos {
	public static Connection initBD(String hospitalBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = (Connection) DriverManager.getConnection("jdbc:sqlite:"+hospitalBD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
/**
 * Usamos este metodo para cuando queremos cerrar la base de datos
 * @param con la conexion  la cerramos
 *
 */
	public static void closeBD(Connection con) {
	if(con!=null)	{
		try {
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
	
	public static void anadirPacientes(Connection con, String dni, String nombre, String apellidos, 
Integer telefono, String direccion ,Date fecha_nacimiento,HistorialClinico historialClinico ) {
		String sentSQL = "INSERT INTO alumno VALUES('"+dni+"','"+nombre+"','"+apellidos+
				"','+telefono+','"+direccion+"','"+fecha_nacimiento+"','"+historialClinico+")";
		try {
			Statement stmt = ((java.sql.Connection) con).createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
	}
	/*	-AÑADIR UN PACIENTE A LA BASE DE DATOS 
	 * 	-AÑADIR UN MEDICO NUEVO A LA BASE DE DATOS
	 * -CREAR TABLAS
	 * 	-ELIMINAR UN PACIENTE DE LA BASE DE DATOS
	 * -ELIMINAR UN PACIENTE DE LA BASE DE DATOS 
	 * -GUARDAR UN USUARIO
	 * -OBTENER UN USUARIO
	 * 	-MODIFICAR UN PACIENTE
	 * - MOFICIAR UN MEDICO
	 * 
	 * 
	 * 
	 * */
	
	
	

