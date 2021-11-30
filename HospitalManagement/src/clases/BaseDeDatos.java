package clases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public static Connection initBD(String hospitalManagementBD) {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+hospitalManagementBD);
			
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
	
	/***
	 * Este metodo se utiliza para crear las tablas en la base de datos
	 * @param con Pasamos la conexion 
	 */
	public static void crearTablas(Connection con) {
		String sent1= "create table paciente(nombre string,  apellido string, dni string, fecha_nacimiento string, genero string, telefono integer, direccion string )";
		
		String sent2 = "CREATE TABLA IF NOT EXISTS Medico( dni String,  nombre String,  apellidos String,"
				+ " telefono Integer, email String,  direccion String , fecha_nacimiento String,cita String)" ;
		
		String sent3 ="CREATE TABLA IF NOT EXISTS Persona(dni String,  nombre String,  apellidos String, "
				+ "telefono Integer, email String,  direccion String , fecha_nacimiento Date, salario Intenger)";
	
		String sent4 ="create table Usuario( nombre String,  contrasena String,  )";
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
	 * Metodo que a�ade paciente usando los siguientes parametros:
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
		String sentSQL = "INSERT INTO alumno VALUES('"+nombre+"','"+apellido+"','"+dni+
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
	public static void eliminarPaciente(Connection con, String dni, String nombre, String apellidos, 
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
	public static void modificarPaciente( Connection con, String dni,String nombre , String apellidos ,
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
		
		public static void actualizaTablaPaciente(DefaultTableModel tabla) { 
			
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
		public static void a�adirPaciente(DefaultTableModel tabla) { 
			
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
		
		public static void main(String[] args) {
			try {
				Connection connection = null;
				connection = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
				 stmt = connection.createStatement();
				 
				stmt.executeUpdate("drop table if exists paciente");
				stmt.executeUpdate("create table paciente(nombre string,  apellido string, dni string, fecha_nacimiento string, genero string, telefono integer, direccion string )");
				stmt.executeUpdate("insert into paciente values('Paula', 'Asua', '79079419Z', '26-07-2001', 'Femenino', 711726903, 'Zabale kalea')");
				
				stmt.executeUpdate("drop table if exists Usuario");
				stmt.executeUpdate("create table Usuario(nombre String, contrasena String )");
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	/*	-CREAR TABLAS
	 * -A�ADIR UN PACIENTE A LA BASE DE DATOS 
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
	
	
	