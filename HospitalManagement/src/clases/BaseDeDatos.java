package clases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/***
 * 
 * @author BSILV
 * Para llamar a la base de datos y abrirla
 *
 */
public class BaseDeDatos {
	public static Connection initBD(String hospitalManagementBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = (Connection) DriverManager.getConnection("jdbc:sqlite:"+hospitalManagementBD);
			
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
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public static void crearTablas(Connection con) {
		String sent1= "CREATE TABLE IF NOT EXISTS Paciente( dni String,  nombre String,  apellidos String, telefono Integer,  direccion String , "
				+ "fecha_nacimiento Date, historialClinico HistorialClinico)";
		String sent2 = "CREATE TABLA IF NOT EXISTS Medico( dni String,  nombre String,  apellidos String,"
				+ " telefono Integer,  direccion String , fecha_nacimiento Date)" ;
		String sent3 ="CREATE TABLA IF NOT EXISTS Usuario(dni String,  nombre String,  apellidos String, "
				+ "telefono Integer,  direccion String , fecha_nacimiento Date, salario Intenger)";
		String sent4="CREATE TABLA IF NOT EXISTS Informatico(dni String,  nombre String,  apellidos String, telefono Integer,"
				+ "  direccion String , fecha_nacimiento Date, salario Intenger,cargo String)";
		String sent5 ="CREATE TABLA IF NO EXISTS Recepcionista(dni String,  nombre String,  apellidos String, telefono Integer, "
				+ "direccion String , fecha_nacimiento Date, salario Intenger,funciones String)";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
			st.executeUpdate(sent2);
			st.executeUpdate(sent3);
			st.executeUpdate(sent4);
			st.executeUpdate(sent5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}
	
	
	public static void anadirPacientes(Connection con, String dni, String nombre, String apellidos, 
Integer telefono, String direccion ,Date fecha_nacimiento,HistorialClinico historialClinico ) {
		String sentSQL = "INSERT INTO alumno VALUES('"+dni+"','"+nombre+"','"+apellidos+
				"','+telefono+','"+direccion+"','"+fecha_nacimiento+"','"+historialClinico+")";
		try {
			Statement stmt =null;
			stmt= con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void eliminarPacientes(Connection con, String dni, String nombre, String apellidos, 
			Integer telefono, String direccion ,Date fecha_nacimiento,HistorialClinico historialClinico) {
		String sentSQL = "DELETE FROM alumno WHERE dni ='"+dni+"'";
		try {
			Statement stmt = null;
			stmt= con.createStatement();
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
	
	
	

