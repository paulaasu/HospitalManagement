package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
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
			modelo.addColumn("G�nero");
			modelo.addColumn("Tel�fono");
			modelo.addColumn("Direcci�n");
			
			try {
				BaseDeDatos.actualizaTablaPaciente(modelo);
			} catch (Exception e) {
				System.out.println("No se puede rellenar la tabla");
				e.printStackTrace();
			}
			
			// JSCROLLPANE Y A�ADIR LA TABLA
			JScrollPane scrollPane = new JScrollPane(tabla);
			scrollPane.setVisible(true);
			add(scrollPane, BorderLayout.CENTER);
		}
	}
	
	public class PanelAbajo extends JPanel{
		private JTextField buscar;
		private JTextField a�adir;
		private JButton botonBuscar;
		private JButton botonA�adir;
		private JButton botonBorrar;
		public PanelAbajo() {
			setLayout(new FlowLayout());
		//	setBackground(Color.BLUE);
			
			JPanel PanelBuscar = new JPanel();
			PanelBuscar.setLayout(new GridLayout(4, 1));
			PanelBuscar.add(new JLabel("Busar paciente..."));
			buscar = new JTextField();
			PanelBuscar.add(buscar);
			botonBuscar = new JButton("Buscar");
			PanelBuscar.add(botonBuscar);
			add(PanelBuscar);
			
			JPanel PanelVacio = new JPanel();
			PanelVacio.setLayout(new GridLayout(1, 1));
			PanelVacio.add(new JLabel(""));
			add(PanelVacio);
			
			JPanel PanelA�adir = new JPanel();
			PanelA�adir.setLayout(new GridLayout(2, 1));
			PanelA�adir.add(new JLabel("A�adir paciente..."));
			botonA�adir = new JButton("A�adir");
			PanelA�adir.add(botonA�adir);
			add(PanelA�adir);
			VentanaPaciente ventanaPaciente = new VentanaPaciente();
			botonA�adir.addActionListener(new ActionListener() {
				
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


			
			
			
			
		}
	}

}
