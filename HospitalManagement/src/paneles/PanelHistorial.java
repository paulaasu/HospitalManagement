package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.BaseDeDatos;
import paneles.PanelHistorial;
import vistas.VentanaA�adirHist;
import vistas.VentanaBPaciente;
import vistas.VentanaVisualizarHist;

public class PanelHistorial extends JPanel {
	public static JTextField enfermedadTxt;
	public static JTextField sintomasTxt;
	public static JTextField tiempoTxt;
	public static JTextField sedTxt;
	public static JTextField sue�oTxt;
	public static JTextField miccionTxt;
	
	public static DefaultTableModel modelo;
	public static JTable tabla;
	static Connection con;
	public PanelHistorial() {
		
		setLayout(new GridLayout(2,1));
		 //a�adimos los paneles
		Panel1 panel1 = new Panel1();
		add(panel1);
		Panel2 panel2 = new Panel2();
		add(panel2);
		

	}
	
class Panel1 extends JPanel{ //panel que contiene la INFORMACION

		private JButton visualizarHistorial;
		private JButton a�adirHistorial;
		private JButton editarHistorial;
		
		public Panel1() {
			setLayout(new BorderLayout());
		
			JLabel datos = new JLabel("HISTORIAL CL�NICO");
			datos.setHorizontalAlignment(SwingConstants.CENTER);
			datos.setFont(new Font("Sherif", Font.PLAIN, 24));
			setBackground(new Color(176, 196, 222));
			add(datos, BorderLayout.NORTH);
			
			
			Panel3 panel3 = new Panel3();
			add(panel3, BorderLayout.CENTER);
			
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			visualizarHistorial = new JButton("Visualizar");
			panelBotones.add(visualizarHistorial);
			a�adirHistorial = new JButton("A�adir");
			panelBotones.add(a�adirHistorial);
//			editarHistorial = new JButton("Editar");
//			panelBotones.add(editarHistorial);
			add(panelBotones,BorderLayout.SOUTH);
			
			visualizarHistorial.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaVisualizarHist ventanaVisualizar = new VentanaVisualizarHist();
					ventanaVisualizar.setVisible(true);
					
				}
			});
			
			a�adirHistorial.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaA�adirHist ventanaA�adir = new VentanaA�adirHist();
					ventanaA�adir.setVisible(true);
					
				}
			});
			

		}
			
		}
	
	
	
	class Panel3 extends JPanel{
		

		public Panel3() {
			setLayout(new GridLayout(1, 2));			
			
			JPanel panel4 = new JPanel();
			panel4.setLayout(new GridLayout(3,2));
			panel4.add(new JLabel("Enfermedad: "));
			enfermedadTxt= new JTextField(20);
			panel4.add(enfermedadTxt);
			
			panel4.add(new JLabel("Signos y s�ntomas: "));
			sintomasTxt= new JTextField(20);
			panel4.add(sintomasTxt);
			
			panel4.add(new JLabel("Tiempo de enfermedad"));
			tiempoTxt = new JTextField(20);
			panel4.add(tiempoTxt);
			
			JPanel panel5 = new JPanel();
			panel5.setLayout(new GridLayout(4,2));
			JLabel funcion = new JLabel("Funciones biol�gicas:");
			funcion.setFont(new Font("Sherif", Font.BOLD, 15));
			panel5.add(funcion);
			panel5.add(new JLabel(" "));
			
			panel5.add(new JLabel("Sed: "));
			sedTxt = new JTextField(20);
			panel5.add(sedTxt);
			
			panel5.add(new JLabel("Sue�o: "));
			sue�oTxt = new JTextField(20);
			panel5.add(sue�oTxt);
			
			panel5.add(new JLabel("Micci�n: "));
			miccionTxt = new JTextField(20);
			panel5.add(miccionTxt);
			
			add(panel4);
			add(panel5);
		
//			a�adir = new JButton("A�adir");
//			add(a�adir);
//			
			
			
			
		}
	}
	
class Panel2 extends JPanel{ //panel que contiene la TABLA PACIENTES

		
		public Panel2() {
			setLayout(new GridLayout(1,1));
			//Creamos la JTable
			 modelo = new DefaultTableModel();
			 tabla = new JTable(modelo);
			//Creamos las columnas
			modelo.addColumn("Num Historial");
			modelo.addColumn("Enfermedad");
			modelo.addColumn("S�ntomas");
			modelo.addColumn("Tiempo");
			modelo.addColumn("Sed");
			modelo.addColumn("Sue�o");
			modelo.addColumn("Micci�n");
			modelo.addColumn("Dni");
			try {
				actualizarTablaHistorial();
			} catch (Exception e) {
				System.out.println("No se puede rellenar la tabla");
				e.printStackTrace();
			}
			
			// JSCROLLPANE Y A�ADIR LA TABLA
			JScrollPane scrollPane = new JScrollPane(tabla);
			scrollPane.setVisible(true);
			add(scrollPane);
		}
}


public  void nuevaTablaHistorial(){
	try {
		BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
		BaseDeDatos.anadirHistorialTabla(modelo);
		BaseDeDatos.closeBD(con);
	} catch (Exception e) {
		System.out.println("No se puede rellenar la tabla");
		e.printStackTrace();
	}
	
	
}
public static void eliminaTablaHistorial(){
	int rowCount = modelo.getRowCount();
	//Elimina las filas uno a uno desde el final de la tabla
	for (int i = rowCount - 1; i >= 0; i--) {
	    modelo.removeRow(i);
	}
	
}
public static JTextField devuelveEnfermedad(){
	return enfermedadTxt;	
}


public static JTextField devuelveSintomas(){
	return sintomasTxt;	
}

public static JTextField devuelveTiempo(){
	return tiempoTxt;
}
public static JTextField devuelveSed(){
	return sedTxt;
}
public static JTextField devuelveSue�o(){
	return sue�oTxt;
}
public static JTextField devuelveMiccion(){
	return miccionTxt;
}

public static   void actualizarTablaHistorial() {
	int rowCount = modelo.getRowCount();
	//Elimina las filas uno a uno desde el final de la tabla
	for (int i = rowCount - 1; i >= 0; i--) {
	    modelo.removeRow(i);
	}
	try {
		BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
		BaseDeDatos.anadirHistorialTabla(modelo);
		BaseDeDatos.closeBD(con);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}

}