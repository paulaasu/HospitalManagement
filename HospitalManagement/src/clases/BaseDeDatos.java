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

	private static Logger logger = Logger.getLogger( "BaseDatos" );
	private static Handler handler ;
	
	public static Connection initBD(String HospitalManagementBD) {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");// Carga la base de datos en el squliteman
//			logger.log( Level.INFO, "Carga de librer�a org.sqlite.JDBC" );
//			logger.log( Level.INFO, "Abriendo conexi�n con " + BaseDeDatos );// se habre la conexion con la bbd
			con = DriverManager.getConnection("jdbc:sqlite:" + HospitalManagementBD );
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
 * Usamos este metodo para cerrar la conexi�n de la base de datos
 * @param con la conexion  la cerramos
 *
 */
	/** 
	 */
	public static void closeBD() {
		try {
			logger.log( Level.INFO, "Cerrando conexi�n" );
			con.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepci�n", e );
		}
	}
	
	/***
	 * Este metodo se utiliza para crear las tablas en la base de datos
	 * @param con Pasamos la conexion 
	 */
	public static void crearTablas(Connection con) {
		String sent1= "CREATE TABLE IF NOT EXISTS Paciente(nombre string,  apellido string, dni string, fecha_nacimiento string, genero string, telefono integer, direccion string )";
		
		String sent2 = "CREATE TABLA IF NOT EXISTS Medico( dni String,  nombre String,  apellidos String,"
				+ " telefono Integer, email String,  direccion String , fecha_nacimiento String,salario Integer,cita String)" ;
		
		String sent3 ="CREATE TABLA IF NOT EXISTS Persona(dni String,  nombre String,  apellidos String, "
				+ "telefono Integer, email String,  direccion String , fecha_nacimiento Date, salario Intenger)";
	
		String sent4 ="CREATE TABLA IF NOT EXITS Usuario( nombre String,  contrasena String,  )";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
			st.executeUpdate(sent2);
			st.executeUpdate(sent3);
			st.executeUpdate(sent4);
			
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
	 * Metodo que a�ade un paciente usando los siguientes parametros:
	 * @param con
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param telefono
	 * @param direccion
	 * @param fecha_nacimiento
	 * @param historialClinico
	 */
	public static void anadirPaciente(Connection con ,String nombre,String apellido , String dni , //FALTA EL HISTORIAL CLINICO
			String fecha_nacimiento, String genero, Integer telefono, String direccion) {
		String sentSQL = "INSERT INTO paciente VALUES('"+nombre+"','"+apellido+"','"+dni+
				"','"+fecha_nacimiento+"','"+genero+"','"+telefono+"','"+direccion+"')";
		
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
	 * Metodo que a�ade un medico usando los siguientes parametros:
	 * @param con
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param fecha_nacimiento
	 * @param genero
	 * @param telefono
	 * @param direccion
	 * @param salario
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
	/***
	 * Metodo que elimina un paciente de la base de datos  mediante el dni 
	 *@param con La conexion con la base de datos
	 * @param dni El dni del paciente 
	 * @param nombre El nombre del pacientes
	 * @param apellidos Los apellido del paciente
	 * @param telefono El telefono del paciente
	 * @param direccion La direcci�n del paciente 
	 * @param fecha_nacimiento La fecha de nacimiento del paciente ( revisar)
	 * @param historialClinico El historial clinico del paciente
	 */
	public static void eliminarPacientePorDni(Connection con, String dni, String nombre, String apellidos, 
			Integer telefono, String direccion ,String fecha_nacimiento,HistorialClinico historialClinico) {
		String sentSQL = "DELETE FROM Paciente  WHERE dni ='"+dni+"'";
		 
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
	 * Metodo que modifica el dni del paciente por el dni
	 * @param con La conexion con la base de datos
	 * @param dni El dni del paciente 
	 * @param nombre El nombre del pacientes
	 * @param apellidos Los apellido del paciente
	 * @param telefono El telefono del paciente
	 * @param direccion La direcci�n del paciente 
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
	 * @param direccion La direcci�n del medico
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
			pac = new Paciente(dni, nombre, apellido, 0, genero, direccion, fecha_nacimiento, 0, null);
			
		}
		rs.close();
		
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
	
	/**
	 * Metodo que obtiene el usuario 
	 * @param con Conexion con la tabla
	 * @param nombre Nombre del paciente/medico
	 * @param contrasena Contrase�a del paciente/medico
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
				u = new Usuario(rs.getString("nombre"), rs.getString("contrasena"));
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
	 * METODO SI HA ENCONTRADO UN USUARIO CON EL NOMBRE Y LA CONTRASE�A INDICADA, MIRE EL DNI Y EL NOMBRE EN LA CLASE PERSONA Y ME LLEVE A LA VENTA MEDICO O PACIENTE
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
							p= new Paciente(dni, nombre, apellidos, (int)0,email, direccion, fnac, (int)0, new HistorialClinico(diagnostico, TipoAnalisis.valueOf(analisis)));
								
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
								p = new Medico(dni, nombre, apellidos, (int)0, email, direccion, fnac, (int)0,new ArrayList<Cita>());
									
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
		 * Metodo que a�ade los pacientes a la tabla en la bbddd
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
					//se rellena cada posici�n del array con una de las columnas de la tabla de bd
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
		
		/**
		 * M�todo que obtiene un mapa con los pacientes de la BBDD
		 * @param con Conexi�n con la base de datos
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
					paciente = new Paciente(d, nom, apellidos, (int)0,email, direccion, fnac, (int)0, new HistorialClinico(diagnostico, TipoAnalisis.valueOf(analisis)));
					tmPacientes.put(d, paciente);
					
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
			return tmPacientes;
			
		}
		public static void a�adirCita(Connection con , String fechaYHoraCita) {
			//M.anadir una cita pasando la fecha y hora
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
	/**
	 * Metodo que devuelve el tipo de cita
	 * @param dni el dni de la tabla medico
	 * @return devuelve el tipo
	 * @throws SQLException
	 */
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
				 
				stmt.executeUpdate("drop table if exists paciente");
				stmt.executeUpdate("create table paciente(nombre string,  apellido string, dni string, fecha_nacimiento string, genero string, telefono integer, direccion string )");
				stmt.executeUpdate("insert into paciente values('Paula', 'Asua', '79079419Z', '26-07-2001', 'Femenino', 711726903, 'Zabale kalea')");
				
				stmt.executeUpdate("drop table if exists usuario");
				stmt.executeUpdate("create table usuario(nombre String, contrasena String )");
				stmt.executeUpdate("insert into usuario values('admin', 'admin')");
				
				stmt.executeUpdate("drop table if exists Medico");
				stmt.executeUpdate("create table Medico( dni String,  nombre String,  apellidos String, telefono Integer, email String,  direccion String , fecha_nacimiento String,salario Integer,cita String)");
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}}

	/*	-CREAR TABLAS (HECHO)
	 * Logger ( hecho
	 * -A�ADIR UN PACIENTE A LA BASE DE DATOS (HECH0)
	 * -ELIMINAR UN PACIENTE DE LA BASE DE DATOS
	 *  -MODIFICAR UN PACIENTE DE LA BASE DE DATOS
	 *  
	 * 	-A�ADIR UN MEDICO NUEVO A LA BASE DE DATOS
	 * 
	 * 	
	 * -ELIMINAR UN PACIENTE DE LA BASE DE DATOS 
	 * -GUARDAR UN USUARIO
	 * -OBTENER UN USUARIO
	 * 	-MODIFICAR UN PACIENTE
	 * - MOFICIAR UN MEDICO
	 * 
	 * 
	 * 
	 * */
	
	
	

	
	
	
