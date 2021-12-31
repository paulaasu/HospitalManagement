package paneles;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import vistas.VentanaBorrarPaciente;
import vistas.VentanaHistorial;
import vistas.VentanaPaciente;
import clases.BaseDeDatos;
import clases.Pacientte;




public class PanelPacientes extends JPanel {
	public static DefaultTableModel modelo;
	public static JTable tabla;
	
	public PanelPacientes() {
		setLayout(new BorderLayout());
		JLabel datos = new JLabel("LISTADO DE PACIENTES");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
		setBackground(new Color(176, 196, 222));
		add(datos, BorderLayout.NORTH);
		Panel4 panel4 = new Panel4();
		add(panel4, BorderLayout.CENTER);
		
		
	}
	
	class Panel4 extends JPanel { 
		public Panel4() {
			setLayout(new GridLayout(2,1));
			PanelArriba panelarriba = new PanelArriba();
			add(panelarriba);
			PanelAbajo panelabajo = new PanelAbajo();
			add(panelabajo);
		}
	}
	class PanelArriba extends JPanel{

		public PanelArriba() {
			setLayout(new BorderLayout());
;
			//Creamos la JTable
			modelo = new DefaultTableModel();
			tabla = new JTable(modelo);
			//Creamos las columnas
			modelo.addColumn("Nombre");
			modelo.addColumn("Apellido");
			modelo.addColumn("DNI");
			modelo.addColumn("Fecha nacimiento");
			modelo.addColumn("Género");
			modelo.addColumn("Teléfono");
			modelo.addColumn("Dirección");
			modelo.addColumn("Nª Historial");
			
			actualizaTablaPaciente();
			
			// JSCROLLPANE Y AÑADIR LA TABLA
			JScrollPane scrollPane = new JScrollPane(tabla);
			scrollPane.setVisible(true);
			add(scrollPane, BorderLayout.CENTER);
		}
	}
	
	public class PanelAbajo extends JPanel{
		private JTextField buscar;
		private JTextField añadir ,txtDni,txtNombre , txtApellidos,txtdir, txttl,txtfecha,txthc;
		private JButton botonBuscar;
		private JButton botonAñadir;
		private JButton botonBorrar;
		private JButton botonhistorialClinico;
		private String dni;
		
		public PanelAbajo() {
			setLayout(new FlowLayout());
			
			JPanel PanelBuscar = new JPanel();
			PanelBuscar.setLayout(new GridLayout(5, 1));
			PanelBuscar.add(new JLabel("Busar paciente..."));
			buscar = new JTextField();
			PanelBuscar.add(buscar);
			botonBuscar = new JButton("Buscar");
			PanelBuscar.add(botonBuscar);
			
			add(PanelBuscar);
			// M. recorrer la base de datos de los pacientes y buscarlos mediente el dni
			botonBuscar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String dniBuscar = buscar.getText();
					
					try {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						String sentSQL = "SELECT * FROM paciente WHERE dni = '" + dniBuscar + "' ";
						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
						
						BaseDeDatos.closeBD();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			});
			// Buscar paciente por DNI
			botonBuscar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String dniB = buscar.getText();
					try {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						String sentSQL = "SELECT * FROM paciente WHERE dni = '" + dniB + "' ";
						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
						
						
						if(BaseDeDatos.rs.next()) {
							int rowCount = modelo.getRowCount();
							//Elimina las filas uno a uno desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
							    modelo.removeRow(i);
							}
							
							
							Object[] fila = new Object[8]; // hay 7 columnas en la tabla paciente
							//se rellena cada posición del array con una de las columnas de la tabla de bd
							for (int i=0; i<8; i++) {
								fila[i] = BaseDeDatos.rs.getObject(i+1);
							}
							modelo.addRow(fila);
						}else {
							int rowCount = modelo.getRowCount();
							//Elimina las filas uno a uno desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
							    modelo.removeRow(i);
							}
							BaseDeDatos.anadirPacienteTabla(modelo);
					
						}
//						while(BaseDeDatos.rs.next()) {
//							int rowCount = modelo.getRowCount();
//							//Elimina las filas uno a uno desde el final de la tabla
//							for (int i = rowCount - 1; i >= 0; i--) {
//							    modelo.removeRow(i);
//							}
//							
//							Pacientte pacienteB = new Pacientte(BaseDeDatos.rs.getString("nombre"), BaseDeDatos.rs.getString("apellido"), BaseDeDatos.rs.getString("dni"), BaseDeDatos.rs.getString("fecha_nacimiento"), BaseDeDatos.rs.getString("genero"),BaseDeDatos.rs.getInt("telefono"),BaseDeDatos.rs.getString("direccion"),BaseDeDatos.rs.getInt("numHistorial"));
//							Object[] fila = new Object[8]; // hay 7 columnas en la tabla paciente
//							//se rellena cada posición del array con una de las columnas de la tabla de bd
//							for (int i=0; i<8; i++) {
//								fila[i] = BaseDeDatos.rs.getObject(i+1);
//							}
//							modelo.addRow(fila);	
//						}
						
						BaseDeDatos.closeBD();
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			});
			
//			botonBuscar.addActionListener(new ActionListener() {
//				
//				String dni = txtDni.getText();
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					String erdni = "[0-9]{8}[A-Z]";
//					String dni = txtDni.getText();
//					boolean correctoDni = Pattern.matches(erdni, dni);
//					if(correctoDni) {
//						Connection con = BaseDeDatos.initBD("hospitalmanagementBD.db");
//						try {
//							BaseDeDatos.buscaUnPacientePorDNI( con, dni );
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						JOptionPane.showMessageDialog(null, "USUARIO ENCONTRADO");
//						
//					}else {
//						JOptionPane.showMessageDialog(null, "ERROR! , NO SE HA ENCONTRADO EL USUARIO ");
//					}
//					
//					BaseDeDatos.closeBD();
//					
//				}
//			}
//			);
			
			JPanel PanelVacio = new JPanel();
			PanelVacio.setLayout(new GridLayout(1, 1));
			PanelVacio.add(new JLabel(""));
			add(PanelVacio);
			
			JPanel PanelAñadir = new JPanel();
			PanelAñadir.setLayout(new GridLayout(2, 1));
			PanelAñadir.add(new JLabel("Añadir paciente..."));
			botonAñadir = new JButton("Añadir");
			PanelAñadir.add(botonAñadir);
			add(PanelAñadir);
			VentanaPaciente ventanaPaciente = new VentanaPaciente();
			botonAñadir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ventanaPaciente.setVisible(true);
					
				}
			});
			
			JPanel PanelBorrar = new JPanel();
			PanelBorrar.setLayout(new GridLayout(2, 1));
			PanelBorrar.add(new JLabel("Borrar paciente..."));
			botonBorrar = new JButton("Borrar");
			PanelBorrar.add(botonBorrar);
			add(PanelBorrar);
			
			
			
			//M.borrar todos los pacientes por el dni
			
			botonBorrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaBorrarPaciente ventanaBorrar = new VentanaBorrarPaciente();
					ventanaBorrar.setVisible(true);
					
				}
			});



			
		
			
		}
	}
	
	public static void actualizaTablaPaciente(){
		try {
			BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
			BaseDeDatos.anadirPacienteTabla(modelo);
			BaseDeDatos.closeBD();
		} catch (Exception e) {
			System.out.println("No se puede rellenar la tabla");
			e.printStackTrace();
		}
		
		
	}
	
	public static void eliminaTablaPaciente(){
		int rowCount = modelo.getRowCount();
		//Elimina las filas uno a uno desde el final de la tabla
		for (int i = rowCount - 1; i >= 0; i--) {
		    modelo.removeRow(i);
		}
		
	}

}



