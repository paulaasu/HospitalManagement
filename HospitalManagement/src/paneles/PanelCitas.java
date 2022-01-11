package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.BaseDeDatos;
import clases.Cita;
import clases.TipoCita;
import paneles.PanelPacientes.Panel4;
import paneles.PanelPacientes.PanelAbajo;
import paneles.PanelPacientes.PanelArriba;
import vistas.VentanaCitas;
import vistas.VentanaPaciente;

public class PanelCitas extends JPanel {
	//nuevo
	Connection con;
	public static DefaultTableModel modelo = new DefaultTableModel();
	public static JTable tabla = new JTable(modelo);

	
	static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );
	

	public PanelCitas() {
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		JLabel datos = new JLabel("CITAS");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
		setBackground(new Color(176, 196, 222));
		add(datos, BorderLayout.NORTH);
		Panel4 panel4 = new Panel4();
		add(panel4, BorderLayout.CENTER);
		
	}
	class Panel4 extends JPanel { //AQUI ESTA EL GRIDLAYOUT DEL CENTRO
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
			setBackground(Color.BLUE);
			//hacemos la conexion con la BD
			
			
			
			
			//Creamos las columnas
	//nuevo
			modelo.addColumn("Dni"); 
			modelo.addColumn("Nombre"); //nombre del paciente??
			modelo.addColumn("Apellido");
			modelo.addColumn("Fecha y Hora");
	//nuevo
			modelo.addColumn("Tipo cita");
			actualizarTablaCita();
			

		
			
					
			// JSCROLLPANE Y AÑADIR LA TABLA
			JScrollPane scrollPane = new JScrollPane(tabla);
			scrollPane.setVisible(true);
			add(scrollPane, BorderLayout.CENTER);
		}
	}
	
	public class PanelAbajo extends JPanel{
		private JTextField buscar;
		private JTextField añadir;
		private JButton botonBuscar;
		private JButton botonAñadir;
		private JButton botonBorrar;
		public PanelAbajo() {
			setLayout(new FlowLayout());
		//	setBackground(Color.BLUE);
			
			JPanel PanelBuscar = new JPanel();
			PanelBuscar.setLayout(new GridLayout(4, 1));
			PanelBuscar.add(new JLabel("Busar cita..."));
			buscar = new JTextField();
			PanelBuscar.add(buscar);
			botonBuscar = new JButton("Buscar");
			PanelBuscar.add(botonBuscar);
			add(PanelBuscar);
			JFrame f= new JFrame();
			f.addWindowListener( new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					if (new File("BaseDeDatos.db").exists()) {
						// Poner el parÃ¡metro a true si se quiere reiniciar la base de datos
						BaseDeDatos.initBD( "BaseDeDatos.db", false );  // Abrir base de datos existente
					} else {
						BaseDeDatos.initBD( "BaseDeDatos.db", true );  // Crear base de datos con datos iniciales
					}
					 BaseDeDatos.volcarJTableATablaCita(con, modelo);// SegÃºn se inicia la ventana se visualizan los productos
				}
				@Override
				public void windowClosed(WindowEvent e) {
					BaseDeDatos.closeBD(con);;
				}
			});
			botonBuscar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String dni= buscar.getText() ;
				//nuevo
					try {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						String sentSQL = "SELECT * FROM cita WHERE dni = '" + dni + "' ";
						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
						
						if(BaseDeDatos.rs.next()) {
							int rowCount = modelo.getRowCount();
							//Elimina las filas uno a uno desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
							    modelo.removeRow(i);
							}
							
							
							Object[] fila = new Object[5]; // hay 4 columnas en la tabla cita
							//se rellena cada posición del array con una de las columnas de la tabla de bd
							for (int i=0; i<5; i++) {
								fila[i] = BaseDeDatos.rs.getObject(i+1);
							}
							modelo.addRow(fila);
						}else {
							JOptionPane.showMessageDialog(PanelBuscar, "Dni incorrecto, no existe en la base de datos");
							int rowCount = modelo.getRowCount();
							//Elimina las filas uno a uno desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
							    modelo.removeRow(i);
							}
							BaseDeDatos.volcarJTableATablaCita(con,modelo);
						}					
							BaseDeDatos.closeBD(con);
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}
				});
			
			JPanel PanelVacio = new JPanel();
			PanelVacio.setLayout(new GridLayout(1, 1));
			PanelVacio.add(new JLabel(""));
			add(PanelVacio);
			
			JPanel PanelAñadir = new JPanel();
			PanelAñadir.setLayout(new GridLayout(2, 1));
			PanelAñadir.add(new JLabel("Añadir cita..."));
			botonAñadir = new JButton("Añadir");
			PanelAñadir.add(botonAñadir);
			add(PanelAñadir);
			VentanaCitas ventanaCitas = new VentanaCitas();
			botonAñadir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ventanaCitas.setVisible(true);
						
					}
				});
			
			
			JPanel PanelBorrar = new JPanel();
			PanelBorrar.setLayout(new GridLayout(2, 1));
			PanelBorrar.add(new JLabel("Borrar paciente..."));
			botonBorrar = new JButton("Borrar");
			PanelBorrar.add(botonBorrar);
			add(PanelBorrar);
			//nuevo 4/01
			botonBorrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int filaSeleccionada = tabla.getSelectedRow();
					if(filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(null, "Primero debes seleccionar una fila de la tabla");
					}else {
						modelo.removeRow(filaSeleccionada);
						con = BaseDeDatos.initBD("BaseDeDatos.db",true);
						//m
						BaseDeDatos.eliminarCita(con);
						eliminaTablaCita();
						actualizarTablaCita();
						//BaseDeDatos.volcarJTableATablaCita(con, modelo);
						BaseDeDatos.closeBD(con);
					}
				}
			});
			
				
			
		
//			
				
			}
		}
	
	public static void eliminaTablaCita(){
		int rowCount = modelo.getRowCount();
		//Elimina las filas uno a uno desde el final de la tabla
		for (int i = rowCount - 1; i >= 0; i--) {
		    modelo.removeRow(i);
		}
		
	}
	public static void actualizarTablaCita() {
		try {
			//nuevo
			Connection con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
			BaseDeDatos.volcarJTableATablaCita(con ,modelo);
			BaseDeDatos.closeBD(con);
		} catch (Exception e) {
			System.out.println("No se puede rellenar la tabla");
			e.printStackTrace();
		}
		
		
	
		
	}
	

}
