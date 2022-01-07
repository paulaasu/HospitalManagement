package clases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import vistas.VentanaPaciente; // importamos el panel pacientes


/***
 * 
 * @author BSILV
 * Para llamar a la base de datos y abrirla
 *
 */
public class BaseDeDatos {
	public static Statement stmt;
	public static ResultSet rs;
	public static char[] com;
	public static Connection con;

	private static Logger logger = Logger.getLogger( "BaseDeDatos.db" );
	private static Handler handler ;
	
	public static Connection initBD(String BaseDeDatos) {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");// Carga la base de datos en el squliteman
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			logger.log( Level.INFO, "Abriendo conexión con " + BaseDeDatos );// se habre la conexion con la bbd
			con = DriverManager.getConnection("jdbc:sqlite:" + BaseDeDatos );
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
 *Para que el logger cuando ejecute la ventana , me salga en un fichero de texto
 */
	public static void prepararLogger() {
		try {
			handler = new FileHandler("BaseDatos.log");
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * Usamos este metodo para cerrar la conexión de la base de datos
 * @param con la conexion  la cerramos
 *
 */
	/** 
	 */
	public static void closeBD() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			con.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	/***
	 * Este metodo se utiliza para crear las tablas en la base de datos
	 * @param con Pasamos la conexion 
	 */
	public static void crearTablas(Connection con) {
		String sent1= "CREATE TABLE IF NOT EXISTS Paciente(nombre String,  apellido string, dni String, fecha_nacimiento string, genero string, telefono integer, direccion string)";
		/*String sent1= "CREATE TABLE Paciente(
			Dni VARCHAR(20) PRIMARY KEY NOT NULL, 
		    Nombre VARCHAR(20),
		    Apellidos VARCHAR(40),
		    Telefono INTEGER,

		    Direccion VARCHAR(40),
		    Fecha_Nac Date,
		    Genero VARCHAR(9)
		    )
;*/
		
		String sent2 = "CREATE TABLA IF NOT EXISTS Medico( dni Integer,  nombre String,  apellidos String, telefono Integer, email String,  direccion String , fecha_nacimiento String, Especialidad string)" ;
		/*String sent2 = "CREATE TABLE Medico(
			ID_medico INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
			Nombre VARCHAR(20),
			Apellidos VARCHAR(30),
			Telefono INTEGER(9),
			Email VARCHAR(40),
			Direccion VARCHAR(40),
			Fecha_nac Date
			Especialidad VARCHAR(30)
		);*/
		
		
		String sent3 ="CREATE TABLA IF NOT EXISTS Historial(dni String, enfermedad String,  sintoma String, tiempo String, sed String, sueño String, miccion String, FOREIGN KEY(dni) REFERENCES paciente(dni) ON DELETE CASCADE)";
		/*String sent3 = "CREATE TABLE Historial(
			ID_historial INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
			Enfermedad VARCHAR(50),
			Sintoma VARCHAR(50),
			Tiempo VARCHAR(15),
			Sed VARCHAR(15),
			Sueño VARCHAR(15),
			Miccion VARCHAR(15),
			Dni_paciente CHAR(9),
		 	FOREIGN KEY(Dni_paciente) REFERENCES Paciente(Dni) ON DELETE CASCADE
		 );*/
		
		
		String sent4 ="CREATE TABLA IF NOT EXITS Usuario( nombre String,  contrasena String,  )";
		/*String sent4 = "CREATE TABLE Usuario(
			ID_usuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
			user VARCHAR(20),
			password VARCHAR(30),
			Rol VARCHAR(20)
		)
*/

		
		String sent5 ="CREATE TABLA IF NOT EXITS Cita( dni String,nombre String,  apellido String, fechayhora String, TipoCita String  )";
		/*String sent5 = "CREATE TABLE Cita(
				Id_cita INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
				Fecha VARCHAR(20),
				Dni_paciente VARCHAR(20),
				Id_medico INTEGER,
				Tipo VARCHAR(20),
				FOREIGN KEY(Dni_paciente) REFERENCES Paciente(Dni) ON DELETE CASCADE,
				FOREIGN KEY(Id_medico) REFERENCES Medico(Id)
				)*/
		
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
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		}
	
	
	
	
	
	/***
	 * Metodo que añade un paciente
	 */
	public static void anadirPaciente(Connection con ,String dni ,String nombre,String apellido , Integer telefono, 
			 String direccion, String fecha_nacimiento, String genero) {
		String sentSQL = "INSERT INTO paciente VALUES('"+dni+"','"+nombre+"','"+apellido+
				"','"+telefono+"','"+direccion+"','"+fecha_nacimiento+"','"+genero+"')";
		
		try {
			Statement stmt =null;
			stmt= con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		}
		/***
		 * Metodo que añade un historial
		 * @throws SQLException 
		 */
		public static void anadirHistorial(Connection con2, HistorialClinico h) throws SQLException {
			try (Statement statement = con.createStatement()) {
			String sentSQL2= "Select * from paciente where Dni='"+ h.getDni()+"'";
			Statement stmt1 = con.createStatement();
			logger.log( Level.INFO, "Statement: " + sentSQL2 );
			ResultSet rs1 = stmt1.executeQuery(sentSQL2);
			if(rs1.next()) {
				String sentSQL = "INSERT INTO Historial (Enfermedad,Sintoma,Tiempo,Sed,Sueño,Miccion,Dni_paciente)  VALUES('"+h.getEnfermedad()+"','"+h.getSintomas()+"','"+h.getTiempo()+
						"','"+h.getSed()+"','"+h.getSueño()+"','"+h.getMiccion()+"','"+h.getDni()+"');";
				logger.log( Level.INFO, "Statement: " + sentSQL );
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			}else {
				JOptionPane.showMessageDialog(null, "No existe ese dni");
			}
			} catch (org.sqlite.SQLiteException e) {
				e.printStackTrace();
			}
	}
	/***
	 * Metodo que pone a todos los pacientes en un ArrayList
	 */
	public static ArrayList<Paciente> pacientesTotales(){
		try (Statement statement = con.createStatement()) {
			ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
			String sentSQL = "SELECT * FROM paciente";
			ResultSet rs = statement.executeQuery( sentSQL );
			while( rs.next() ) {
				String dni = rs.getString("Dni");
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellidos");
				int telefono = rs.getInt("Telefono");
				String dir = rs.getString("Direccion");
				String genero = rs.getString("Genero");
				String fecha = rs.getString("Fecha_nac");
				pacientes.add( new Paciente(dni, nombre, apellido, telefono, dir, fecha, genero) );
			}
			return pacientes;
		}catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return null;
			}
		}
	/**
	 * Metodo que añade un medico usando los siguientes parametros:
	 */
	public static void anadirMedico(Connection con ,String nombre,String apellido , String dni , //FALTA EL HISTORIAL CLINICO
			String fecha_nacimiento, String genero, Integer telefono, String direccion,Integer salario) {
		String sentSQL = "INSERT INTO medico VALUES('"+nombre+"','"+apellido+"','"+dni+
				"','"+fecha_nacimiento+"','"+genero+"','"+telefono+"','"+direccion+"','"+salario+"')";
		
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
	/**
	 * Metodo que añade un medico usando los siguientes parametros:
	 * @param con
	 * @param objeto tipo Paciente 
	 * 
	 */

	public static void insertarPaciente(Connection con, Paciente p) {
	
		
		String sentSQL = "INSERT INTO paciente VALUES('"+p.getDni()+"','"+p.getNombre()+"','"+p.getApellidos()+
				"',"+p.getTelefono()+",'"+p.getDireccion()+"','"+p.getFechaNac()+"','"+p.getGenero()+"')";
		
		try {
			Statement stmt =null;
			stmt= con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (org.sqlite.SQLiteException e) {
			
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR COMPRUEBE QUE EL FCIHERO ESTE BIEN FORMADO");
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/***
	 * Metodo que elimina un paciente de la base de datos  mediante el dni 
	 */
	public static void eliminarPacientePorDni(String dniPB) throws SQLException {
		Statement statement = con.createStatement();
		String sentSQL2 = "SELECT * FROM paciente WHERE Dni = '" + dniPB + "' ";
		logger.log( Level.INFO, "Statement: " + sentSQL2 );
		stmt = BaseDeDatos.con.createStatement();
		rs = BaseDeDatos.stmt.executeQuery(sentSQL2);
		if(rs.next()) {
			String borrarPaciente = "DELETE FROM paciente WHERE Dni = '" + dniPB + "' ";
			logger.log( Level.INFO, "Statement: " + borrarPaciente );
			int filasEliminadas = stmt.executeUpdate(borrarPaciente); 
		}else {
			JOptionPane.showMessageDialog(null, "El dni no existe");
		}
		
	
	}
	
	public static void eliminarMedicoPorDni(Connection con, String dni, String nombre, String apellidos, 
			Integer telefono, String direccion ,String fecha_nacimiento,Integer salario,HistorialClinico historialClinico) {
		String sentSQL = "DELETE FROM Medico  WHERE dni ='"+dni+"'";
		 
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
	/***
	 * Metodo que elimina un historial de la base de datos  mediante el dni (va junto a eliminarPacienteDni)
	 */
	public static void eliminarHistorialPorDni(String dniHB) throws SQLException {
		Statement statement = con.createStatement();
		String sentSQL2 = "SELECT * FROM historial WHERE Dni_paciente = '" + dniHB + "' ";
		stmt = BaseDeDatos.con.createStatement();
		rs = BaseDeDatos.stmt.executeQuery(sentSQL2);
		while(rs.next()) {
			String borrarHistorial = "DELETE FROM historial WHERE Dni_paciente = '" + dniHB + "' ";
			int filasEliminadas = stmt.executeUpdate(borrarHistorial); 
		}
	
	}
	/***
	 * Metodo que busca el paciente por el DNI
	 */	 
	public static Paciente buscarPacienteDni( String dniB ) {
			try (Statement statement = con.createStatement()) {
			Paciente p=null;
			ArrayList<Paciente> buscado = new ArrayList<>();
			String sentSQL = "SELECT * FROM paciente WHERE dni = '" + dniB + "' ";
			Statement stmt1 = con.createStatement();
			logger.log( Level.INFO, "Statement: " + sentSQL );
			ResultSet rs1 = stmt1.executeQuery(sentSQL);
			HistorialClinico h= null;
			while( rs1.next() ) { // Leer el resultset
				String dni = rs1.getString("Dni");
				String nombre = rs1.getString("Nombre");
				String apellido = rs1.getString("Apellidos");
				int telefono = rs1.getInt("Telefono");
				String dir = rs1.getString("Direccion");
				String genero = rs1.getString("Genero");
				String fecha = rs1.getString("Fecha_nac");
				String sentSQL2 = "SELECT * FROM historial WHERE Dni_paciente = '" + dniB + "' ";
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sentSQL2);			
				if(rs2.next()) {
					int numHist = rs2.getInt("ID_historial");
					String enfermedad = rs2.getString("Enfermedad");
					String sintoma = rs2.getString("Sintoma");
					String tiempo = rs2.getString("Tiempo");
					String sed = rs2.getString("Sed");
					String sueño = rs2.getString("Sueño");
					String miccion = rs2.getString("Miccion");
					String dniP = rs2.getString("Dni_paciente");
					h = new HistorialClinico(numHist, enfermedad, sintoma, tiempo, sed, sueño ,miccion, dniP);
				}	
				;
				p = new Paciente(dni, nombre, apellido, telefono, dir, fecha, h,genero);			
			}
			return p;
		}catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return null;
		}
	}
	/***
	 * Metodo que modifica el dni del paciente por el dni
	 * @param con La conexion con la base de datos
	 * @param dni El dni del paciente 
	 * @param nombre El nombre del pacientes
	 * @param apellidos Los apellido del paciente
	 * @param telefono El telefono del paciente
	 * @param direccion La dirección del paciente 
	 * @param fecha_nacimiento La fecha de nacimiento del paciente ( revisar)
	 * @param historialClinico El historial clinico del paciente
	 */
	public static void modificarPacientePorDni( Connection con, String dni,String nombre , String apellidos ,
			Integer telefono,String email, String direccion, String fecha_nacimiento, HistorialClinico historialClinico) {
		String sentSQL = "UPDATE  Paciente set nombre WHERE dni ='"+dni+"' ";
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Metodo que modifica un medico a traves del dni
	 * 
	 * @param con La conexion con la base de datos
	 * @param dni El dni del medico
	 * @param nombre El nombre del medico
	 * @param apellidos Los apellido del medico
	 * @param telefono El telefono del medico
	 * @param direccion La dirección del medico
	 * @param salario El salario que gana el medico
	 * @param fecha_nacimiento La fecha de nacimiento del medico ( revisar)
	 */
	public static void modificarMedicioPorDni( Connection con, String dni,String nombre , String apellidos ,
			Integer telefono,String email, String direccion, String fecha_nacimiento,Integer salario) {
		String sentSQL = "UPDATE  Medico set nombre WHERE dni ='"+dni+"' ";
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Medico que busca un paciente por el dni
	 * @param con conexion con la bbdd
	 * @param dni El dni del paciente
	 * @throws SQLException
	 */
	public  static void buscaUnPacientePorDNI(Connection con,String dni) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select * from persona where dni ="+dni;
		ResultSet rs = statement.executeQuery(sent);
		Paciente pac = null;
		if(rs.next()) {
			String nombre = rs.getString("nombre");
			String apellido =rs.getString("apellido"); 
			String dn = rs.getString("dni");
			String fecha_nacimiento  = rs.getString("fecha_nacimiento");
			String genero  =rs.getString("genero");
			Integer telefono  =rs.getInt("telefono");
			String direccion  =rs.getString("direccion");
			// comentado lo de abajo pq al cambiar la clase persona da error
			//pac = new Paciente(dni, nombre, apellido, 0, genero, direccion, fecha_nacimiento, 0, null);
			
		}
		rs.close();
		
	}
	
	
	/**
	 * Buscar pacientes en bbd
	 * metodo usado en exportar datos a un .csv
	 * @param con conexion con la bbdd
	 * @throws SQLException
	 */
	public  static ArrayList<Paciente> getPaciente(Connection con) {
		ArrayList <Paciente> lista=new ArrayList<Paciente>();
		ResultSet rs;
		try {
		Statement statement = con.createStatement();
		String sent = "select * from paciente";
		 rs = statement.executeQuery(sent);
		Paciente p=new Paciente();
		
			while (rs.next()) {
				p.dni = rs.getString("Dni");
				p.nombre = rs.getString("Nombre");
				p.apellidos =rs.getString("Apellidos"); 
				p.telefono  =rs.getInt("Telefono");
				p.direccion  =rs.getString("Direccion");
				p.fechaNac= rs.getString("Fecha_Nac");
				p.genero= rs.getString("Genero");
				lista.add(p);
				 
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showOptionDialog(null, "Se ha producido un error en la busqueda de pacientes", null, 0, 0, null, null, e);
			e.printStackTrace();
		}
		return lista;
		
	}
	
	/***
	 * Metodo que guarda los usuarios que se han registrado 
	 * @param con conexion de la base de datos
	 * @param p la clase hija de la clase usuario donde sabremos a que tipo de persona se refiere
	 */
	public static void guardarPersona(Connection con ,Persona p) {
		String sentSQL = "INSERT INTO Persona  VALUES ('"+p.getDni()+"'"+p.getNombre()+"','"+p.getApellidos()+"','"+p.getDireccion()+"','"+p.getEmail()+"','"+p.getTelefono()+"','"+p.getFechaNac()+"')";
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	
	/***
	 * Metodo de login
	 */	
	public static boolean comprobarUsuario(String u, String c) {
		boolean result=false;
		try (Statement statement = con.createStatement()) {
			String sentSQL = "SELECT user, password FROM usuario WHERE user = '" + u +"' AND password = '" + c + "' ";
			BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
			BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
			
			while(rs.next()) {
				result = true;
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return result;
		}
	}
	/**
	 * Metodo que obtiene el usuario 
	 * @param con Conexion con la tabla
	 * @param nombre Nombre del paciente/medico
	 * @param contrasena Contraseña del paciente/medico
	 * @return devuelve si el usuario es un paciente/medico
	 */
	
	public static Usuario obtenerUsuario(Connection con, String nombre, String contrasena) {
		String sentSQL = "SELECT * FROM Usuario WHERE nombre='"+nombre+"'AND contrasena='"+contrasena+"'";
		Statement stmt = null;
		Usuario u = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sentSQL);
			if(rs.next()) {
				u = new Usuario(rs.getString("nombre"), rs.getString("contrasena"), 0, null);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return u;
	}
		//PREGUNTAR 
	/***
	 * METODO SI HA ENCONTRADO UN USUARIO CON EL NOMBRE Y LA CONTRASEÑA INDICADA, MIRE EL DNI Y EL NOMBRE EN LA CLASE PERSONA Y ME LLEVE A LA VENTA MEDICO O PACIENTE
	 * @param con
	 * @param nombre
	 * @param contrasena
	 * @return
	 */
		public static Persona obtenerPersona(Connection con, String nombre, String contrasena, String tipo) {
			String sentSQL = "";
			Persona p = null;
			Statement stmt = null;
try {
				stmt = con.createStatement();
				Usuario u = obtenerUsuario(con, nombre, contrasena);
				if(u!=null) {
					if(tipo.equals("P")) {
						sentSQL = "SELECT * FROM Paciente WHERE nom = '" + nombre;
						ResultSet rs =stmt.executeQuery(sentSQL);
						if(rs.next()) { 
							String dni = rs.getString("dni");
							String nom= rs.getString("nom");
							String apellidos = rs.getString("apellidos");
							String email = rs.getString("email");
							String telefono = rs.getString("telefono");
							String diagnostico = rs.getString("diagnostico");
							String analisis = rs.getString("analisis");
							String fnac = rs.getString("fecha_nacimiento");
							String direccion = rs.getString("direccion");
	//					p= new Paciente(dni, nombre, apellidos, (int)0,email, direccion, fnac, (int)0, new HistorialClinico(diagnostico, TipoAnalisis.valueOf(analisis)));
								
							}
						}
					}else {
						sentSQL = "SELECT * FROM Medico WHERE nom = '" + nombre;
							ResultSet rst =stmt.executeQuery(sentSQL);
							if(rst.next()) { 
								String dni= rst.getString("dni");
								String nom= rst.getString("nom");
								String apellidos = rst.getString("apellidos");
								String email = rst.getString("email");
								String telefono= rst.getString("telefono");
								String fnac = rst.getString("fecha_nacimiento");
								String direccion = rst.getString("direccion");
								String cita = rst.getString("cita");
// comentado lo de abajo pq al cambiar la clase persona da error
								// p = new Medico(dni, nombre, apellidos, (int)0, email, direccion, fnac, (int)0,new ArrayList<Cita>());
									
								}
							}
					
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
					if(stmt!=null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
				
				return p;
			
		}
		
		/**
		 * Metodo que añade los pacientes a la tabla en la bbddd
		 * @param con Conexion con la bbdd
		 * @param tabla JTable 
		 */
public static void anadirPacienteTabla(DefaultTableModel tabla) { 
			
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
				String sentSQL = "SELECT * FROM paciente";
				stmt = con.createStatement();
				rs = stmt.executeQuery(sentSQL); //
				
				
				
				while (rs.next()) {
					
					Object[] fila = new Object[7]; // hay 7 columnas en la tabla paciente
					//se rellena cada posición del array con una de las columnas de la tabla de bd
					for (int i=0; i<7; i++) {
						fila[i] = rs.getObject(i+1);
					}
					tabla.addRow(fila);					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

public static void anadirHistorialTabla(DefaultTableModel tabla) { 
	
	try {
		con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
		String sentSQL = "SELECT * FROM historial";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sentSQL); //
		
		
		
		while (rs.next()) {
			
			Object[] fila = new Object[8]; // hay 8 columnas en la tabla historial
			//se rellena cada posición del array con una de las columnas de la tabla de bd
			for (int i=0; i<8; i++) {
				fila[i] = rs.getObject(i+1);
			}
			tabla.addRow(fila);					
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

/**
 * Metodo que carga el historial
 */
public static ArrayList<HistorialClinico> cargarHistorial(String hc) throws SQLException{
	ArrayList<HistorialClinico> historial = new ArrayList<HistorialClinico>();

	try {
		con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
		String sentSQL = "SELECT * from historial";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sentSQL);
		
		while(rs.next()) {
			HistorialClinico h = new HistorialClinico(rs.getInt("numHistorial"), rs.getString("enfermedad"), rs.getString("sintoma"), rs.getString("tiempo"), rs.getString("sed"),rs.getString("sueño"),rs.getString("miccion"));
			historial.add(h);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return historial;
	
	
}
	
/**
 * Metodo que visualiza los historiales en la ventanaHistorial a través del boton visualizar preguntando el dni
 */
public static HistorialClinico visualizarHistorial(String dni) {
	try (Statement statement = con.createStatement()) {
		String sentSQL = "SELECT * FROM historial WHERE Dni_paciente = '" + dni + "' ";
		BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
		BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
		HistorialClinico h = null;
		while(rs.next()) {
			int numHist = rs.getInt("ID_historial");
			String enfermedad = rs.getString("Enfermedad");
			String sintoma = rs.getString("Sintoma");
			String tiempo = rs.getString("Tiempo");
			String sed = rs.getString("Sed");
			String sueño = rs.getString("Sueño");
			String miccion = rs.getString("Miccion");
			String dniP = rs.getString("Dni_paciente");
			h = new HistorialClinico(numHist, enfermedad, sintoma, tiempo, sed, sueño ,miccion, dniP);
		}
		return h;
	}catch (Exception e) {
		logger.log( Level.SEVERE, "ExcepciÃ³n", e );
		return null;
	}
	
}
	
/*
 * Metodod obtener los nombres de los pacientes para comboBox
 *  @param conexion a la bbdd
 * @throws SQLException
 */
public  static  ArrayList<String> ObtenerPacientes(Connection con){

	ArrayList<String> ret = new ArrayList<>();
	
	ResultSet rs;
	try {
		Statement statement = con.createStatement();
		String sent = "select * from paciente ";
		 rs = statement.executeQuery(sent);

		while(rs.next()) {

			String dni = rs.getString("dni");
			ret.add(dni);
		}
		rs.close();
		return ret;
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	return ret;

}
/*
 * Metodod obtener los nombres de los pacientes para comboBox
 *  @param conexion a la bbdd
 * @throws SQLException
 */
public  static  ArrayList<HistorialClinico> ObtenerHistorialDni(Connection con,String dni){

	ArrayList<HistorialClinico> ret = new ArrayList<>();
	HistorialClinico h=new HistorialClinico();
	ResultSet rs;
	try {
		Statement statement = con.createStatement();
		String sent = "select * from Historial where Dni_paciente='"+dni+"' ";
		 rs = statement.executeQuery(sent);

		if(rs.next()) {
			h.numHistorial = rs.getInt("ID_historial");
			h.enfermedad =rs.getString("Enfermedad"); 
			h.sintomas  =rs.getString("Sintoma");
			h.tiempo  =rs.getString("Tiempo");
			h.sed= rs.getString("Sed");
			h.sueño= rs.getString("Sueño");
			h.miccion= rs.getString("Miccion");
			h.dni_p= rs.getString("Dni_paciente");
			ret.add(h);
		}else {
			JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO NINGUN HISTORIAL");
		}
		rs.close();
		return ret;
	} catch (SQLException e) {
			// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR ");
			e.printStackTrace();
	}
	return ret;

}

	/*
	 * Metodod obtener las citas para exportar a fichero .csv
	 *  @param conexion a la bbdd
	 * @throws SQLException
	 */
	public static ArrayList<Cita> ObtenerCitas(Connection con2) {
		ArrayList<Cita> ret = new ArrayList<>();
		Cita c=new Cita();
		ResultSet rs;
		try {
			
			Statement statement = con.createStatement();
			String sent = "select * from cita ";
			 rs = statement.executeQuery(sent);
	
			if(rs.next()) {
				/*
			c.f= rs.getInt("ID_historial");
				h.enfermedad =rs.getString("Enfermedad"); 
				h.sintomas  =rs.getString("Sintoma");
				h.tiempo  =rs.getString("Tiempo");
				h.sed= rs.getString("Sed");
				h.sueño= rs.getString("Sueño");
				h.miccion= rs.getString("Miccion");
				h.dni_p= rs.getString("Dni_paciente");
				ret.add(h);ç*/
			}else {
				JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO NINGUN HISTORIAL");
			}
			rs.close();
			return ret;
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR ");
				e.printStackTrace();
		}
		return null;
	}
	

/**
		 * Método que obtiene un mapa con los pacientes de la BBDD
		 * @param con Conexión con la base de datos
		 * @return TreeMap String,Pacientes
		 */
		public static TreeMap<String, Paciente> obtenerMapaPaciente(Connection con) {
			TreeMap<String, Paciente>tmPacientes = new TreeMap<>();
			String sentSQL = "SELECT * FROM paciente";
			Statement stmt;
			Paciente paciente;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sentSQL);
				while(rs.next()) {
					String d = rs.getString("dni");
					String nom= rs.getString("nom");
					String apellidos = rs.getString("apellidos");
					String email = rs.getString("email");
					String telefono = rs.getString("telefono");
					String diagnostico = rs.getString("diagnostico");
					String analisis = rs.getString("analisis");
					String fnac = rs.getString("fecha_nacimiento");
					String direccion = rs.getString("direccion");
//					paciente = new Paciente(d, nom, apellidos, (int)0,email, direccion, fnac, (int)0, new HistorialClinico(diagnostico, TipoAnalisis.valueOf(analisis)));
//					tmPacientes.put(d, paciente);
					
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
			return tmPacientes;
			
		}
		
	
		/**
		 * Metodo que devuelve el tipo de analisis  del paciente
		 * @param dni a traves del dni de la tabla paciente sabemos quien es 
		 * @return devuelve el tipo de analisis que se realiza el paciente
		 * @throws SQLException
		 */
		public static TipoAnalisis getTipoAnalisis(String dni) throws SQLException {
			Statement statement = con.createStatement();
			String sent = "select tipo from paciente where dni="+dni;
			ResultSet rs = statement.executeQuery(sent);
			TipoAnalisis tipo = TipoAnalisis.BIOLOGIA_MOLECULAR;
			if(rs.next()) {
				String t = rs.getString("tipo");
				tipo = TipoAnalisis.valueOf(t);
			}
			rs.close();
			return tipo;
		}
		
		//nuevo
		/**
		 * Metodo de añadir una cita 
		 * @param con conexión con la bd
		 * @param dni dni paciente
		 * @param nombre nombre paciente
		 * @param apellido apellido paciente
		 * @param fechayhora fecha de la cita 
		 * @param tipocita tipo de cita
		 */
	public static void anadirCita(Connection con, String dni, String nombre, String apellido, String fechayhora,
				String tipocita) {
			// TODO Auto-generated method stub
			String sentSQL = "INSERT INTO cita VALUES('"+dni+"','"+nombre+"','"+apellido+
					"','"+fechayhora+"','"+tipocita+"')";
			
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
		//nuevo añadir cita a la tabla
	/**
	 * Metodo que añade las citas la tabla en la bbddd
	 * @param con Conexion con la bbdd
	 * @param tabla JTable 
	 * @throws SQLException
	 */
	public static void anadirCitaTabla(DefaultTableModel  tabla) throws SQLException {
		
		con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
		String sentSQL = "SELECT * FROM cita";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sentSQL); //
		while (rs.next()) {
			
			Object[] fila = new Object[4]; // hay 8 columnas en la tabla paciente
			//se rellena cada posición del array con una de las columnas de la tabla de bd
			for (int i=0; i<4; i++) {
				fila[i] = rs.getObject(i+1);
			}
			tabla.addRow(fila);					
		}
		
		
	}
	//eliminar cita nuevo
	public static void elimiarCitaPorDni(Connection con, String dni, String nombre, String apellido, String fechayhora,
			TipoCita cabecera) {
		String sentSQL = "DELETE FROM Cita  WHERE dni ='"+dni+"'";
		 
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
	
	
		//CARGAR CITAS nuevo
	/**
	 * Cargar las citas
	 * @param cc un string de cargar citas
	 * @return devuelve la arralist
	 * @throws SQLException
	 */
		public static ArrayList<Cita>cargarCitas(String cc) throws SQLException{
			 String[] nombrecolumna = { "dni", "nombre", "apellidos", "fecha y hora ", "Tipo cita"};
//			ArrayList<Cita>citas= new ArrayList<>();
			Object[][]cita = new Object[4][4];
			
			con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
			String sentSQL = "SELECT * from Cita";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sentSQL);
			int i = 0;
			if(cargarCitas(cc).size()!=0) {
				for(Cita c : cargarCitas(cc)) {
					cita [i][0]= c.getDni();
					cita [i][1]= c.getNombre();
					cita[i][2]= c.getApellidos();
					cita[i][3]=c.getFechaYHoraCita();
					cita[i][4]= c.getTipodecita();
					i++;
					
					cargarCitas(cc).add(c);
					
					
				}}
			return cargarCitas(cc);
			}

		
	/**
	 * Metodo que devuelve el tipo de cita
	 * @param dni el dni de la tabla medico
	 * @return devuelve el tipo
	 * @throws SQLException
	 */
		//hay que terminar
	public static TipoCita getTipoCita(String dni) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select tipo from medico where dni="+dni;
		ResultSet rs = statement.executeQuery(sent);
		TipoCita tip = TipoCita.CABECERA;
		if(rs.next()) {
			String t = rs.getString("tipo");
			tip = TipoCita.valueOf(t);
		}
		rs.close();
		return tip;
		
		
	}	public static void main(String[] args) {
			try {
				Connection con = null;
				con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
				stmt = con.createStatement();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}


	public static void insertarHistorial(Connection con2, HistorialClinico h) {

		/*CREATE TABLE Historial(
			ID_historial INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
			Enfermedad VARCHAR(50),
			Sintoma VARCHAR(50),
			Tiempo VARCHAR(15),
			Sed VARCHAR(15),
			Sueño VARCHAR(15),
			Miccion VARCHAR(15),
			Dni_paciente CHAR(9),
		 	FOREIGN KEY(Dni_paciente) REFERENCES Paciente(Dni) ON DELETE CASCADE
		 )
*/
		//INSERT INTO Historial (Enfermedad,Sintoma,Tiempo,Sed,Sueño,Miccion,Dni_paciente) VALUES('Dolor de cabeza','Dolor','3 dias','no','no','no','76855467v');
		String sentSQL = "INSERT INTO Historial (Enfermedad,Sintoma,Tiempo,Sed,Sueño,Miccion,Dni_paciente)  VALUES('"+h.getEnfermedad()+"','"+h.getSintomas()+"','"+h.getTiempo()+
				"','"+h.getSed()+"','"+h.getSueño()+"','"+h.getMiccion()+"','"+h.getDni()+"');";
		
		try {
			Statement stmt =null;
			stmt= con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (org.sqlite.SQLiteException e) {
			
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR COMPRUEBE QUE EL FCIHERO ESTE BIEN FORMADO");
			e.printStackTrace();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR COMPRUEBE");
			e.printStackTrace();
		}
		
	}
	
	public static void anadirUsuario(Connection con, Usuario u) {
		
		String sentSQL = "INSERT INTO usuario VALUES('"+u.getID_usuario()+"', '"+u.getNom()+"','"+u.getContrasena()+"', '"+u.getRol()+"')";
		
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

}


