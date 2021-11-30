package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
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

import paneles.PanelPacientes.Panel4;
import paneles.PanelPacientes.PanelAbajo;
import paneles.PanelPacientes.PanelArriba;
import vistas.VentanaPaciente;

public class PanelCitas extends JPanel {

	public PanelCitas() {
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		JLabel datos = new JLabel("CITAS");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
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
			
			
			//Creamos la JTable
			DefaultTableModel modelo = new DefaultTableModel();
			JTable tabla = new JTable(modelo);
			//Creamos las columnas
			modelo.addColumn("Nombre"); //nombre del paciente??
			modelo.addColumn("Fecha");
			modelo.addColumn("Hora");

		
			
					
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


			
			
			
			
		}
	}

}
