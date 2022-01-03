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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
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
	private ArrayList<Cita> listaCitas;
	protected VentanaCitas ventanacitas;
	
// ver las citas que hay en la tabla
	private void verCitas() throws SQLException {
	
		//Vector<String> cabeceras = new Vector<String>( Arrays.asList( "Cod", "Prod", "Cliente", "Fecha", "Cant" ) );
		String cc =null;
		ArrayList<Cita> listaCitas = BaseDeDatos.cargarCitas( cc );
		for (Cita c : listaCitas) {
			
			modelo.addRow( new Object[] {  } );
		}
		tabla.setModel( modelo );
	}
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
			
			
			//Creamos la JTable
			DefaultTableModel modelo = new DefaultTableModel();
			JTable tabla = new JTable(modelo);
			
//			//Creamos la JTable
//			DefaultTableModel modelo = new DefaultTableModel();
//			JTable tabla = new JTable(modelo);
			
			//Creamos las columnas
	//nuevo
			modelo.addColumn("Dni"); 
			modelo.addColumn("Nombre"); //nombre del paciente??
			modelo.addColumn("Apellido");
			modelo.addColumn("Fecha y Hora");
	//nuevo
			modelo.addColumn("Tipo cita");
			

		
			
					
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
							
							
							Object[] fila = new Object[3]; // hay 4 columnas en la tabla cita
							//se rellena cada posición del array con una de las columnas de la tabla de bd
							for (int i=0; i<3; i++) {
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
							BaseDeDatos.anadirCitaTabla(modelo);
						}					
							BaseDeDatos.closeBD();
							
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
			VentanaPaciente ventanaPaciente = new VentanaPaciente();
			botonAñadir.addActionListener(new ActionListener() {
				//M.metodo añadir una cita al paciente
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						ventanacitas.setVisible(true);
						BaseDeDatos.anadirPacienteTabla(modelo );
						
					
					BaseDeDatos.closeBD();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			JPanel PanelBorrar = new JPanel();
			PanelBorrar.setLayout(new GridLayout(2, 1));
			PanelBorrar.add(new JLabel("Borrar paciente..."));
			botonBorrar = new JButton("Borrar");
			PanelBorrar.add(botonBorrar);
			add(PanelBorrar);
			//preguntar
//			botonBorrar.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					
//					int columna = tabla.getRowCount();
//					// TODO Auto-generated method stub
//					try {
//						
//						Connection con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
//					String dni = null;;
//					String nombre = null;
//					String apellido=null;
//					String fechayhora=null;
//					BaseDeDatos.elimiarCitaPorDni( con,dni, nombre, apellido, fechayhora, TipoCita.CABECERA);
//					BaseDeDatos.closeBD();				
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//					
//			});
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
			BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
			BaseDeDatos.anadirCitaTabla(modelo);
			BaseDeDatos.closeBD();
		} catch (Exception e) {
			System.out.println("No se puede rellenar la tabla");
			e.printStackTrace();
		}
		
		
	
		
	}
	

}
