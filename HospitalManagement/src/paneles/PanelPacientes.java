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

import vistas.VentanaPaciente;
import clases.BaseDeDatos;




public class PanelPacientes extends JPanel {
	
	public PanelPacientes() {
		setLayout(new BorderLayout());
		JLabel datos = new JLabel("LISTADO DE PACIENTES");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
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
			DefaultTableModel modelo = new DefaultTableModel();
			JTable tabla = new JTable(modelo);
			//Creamos las columnas
			modelo.addColumn("Nombre");
			modelo.addColumn("Apellido");
			modelo.addColumn("DNI");
			modelo.addColumn("Fecha nacimiento");
			modelo.addColumn("Género");
			modelo.addColumn("Teléfono");
			modelo.addColumn("Dirección");
			
			try {
				BaseDeDatos.anadirPacienteTabla(modelo);
			} catch (Exception e) {
				System.out.println("No se puede rellenar la tabla");
				e.printStackTrace();
			}
			
//			try {
//				// hay que abrir y cerrar siempre la base de datos
//				BaseDeDatos.initBD("BaseDeDatos.db");
//				Connection con = null;
//				BaseDeDatos.anadirPacienteTabla(con,modelo);
//				BaseDeDatos.closeBD();
//			} catch (Exception e) {
//				System.out.println("No se puede rellenar la tabla");
//				e.printStackTrace();
//			}
			
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
//			botonBorrar.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					String dni =txtDni.getText();
//					//expresion regular para saber si el dni es correcto y existe podamos eliminar el paciente
//					String erdni = "[0-9]{8}[A-Z]";
//					String n =txtNombre.getText();
//					String a =txtApellidos.getText();
//					int tl = Integer.parseInt(txttl.getText());
//					String dir =txtdir.getText();
//					String f = txtfecha.getText();
//					// lo pongo a null
//					//String hc = txthc.getText();
//					boolean correctoDni = Pattern.matches(erdni, dni);
//					if(correctoDni) {
//						Connection con = BaseDeDatos.initBD("hospitalmanagementBD.db");
//						BaseDeDatos.eliminarPacientePorDni(con, dni,n, a,tl, dir,f, null);
//						BaseDeDatos.closeBD();
//						JOptionPane.showMessageDialog(null, "EL PACIENTE SE HA BORRADO ");
//						
//					}else {
//						JOptionPane.showMessageDialog(null, "EL DNI ES INCORRECTO , ENTONCES NO SE HA BORRADO .");
//						
//						
//					}
//				}
//			});


			
			JPanel PanelHistorial = new JPanel();
			PanelHistorial.setLayout(new GridLayout(2, 1));
			PanelHistorial.add(new JLabel("Añadir historial clínico..."));
			botonhistorialClinico = new JButton("Añadir");
			PanelHistorial.add(botonhistorialClinico);
			add(PanelHistorial);
			
			
		}
	}

}


