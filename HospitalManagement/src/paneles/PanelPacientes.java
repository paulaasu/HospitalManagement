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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

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

import vistas.VentanaBorrarPaciente;
import vistas.VentanaHistorial;
import vistas.VentanaPaciente;
import vistas.VentanaPrincipal;
import clases.BaseDeDatos;
import clases.Paciente;
import clases.Pacientte;

public class PanelPacientes extends JPanel {
	public static DefaultTableModel modelo;
	public static JTable tabla;
	public static JLabel jLabelTemporizador;
	
	
	
	public PanelPacientes() {
		setLayout(new BorderLayout());
		JLabel datos = new JLabel("LISTADO DE PACIENTES");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
		setBackground(new Color(176, 196, 222));
		add(datos, BorderLayout.NORTH);
		Panel4 panel4 = new Panel4();
		add(panel4, BorderLayout.CENTER);
		jLabelTemporizador = new JLabel("Tiempo restante: ");
		//JPanel panelTemporizador = new JPanel();
		//panelTemporizador.add(jLabelTemporizador);
		add(jLabelTemporizador, BorderLayout.SOUTH);
		
		
		
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
			modelo.addColumn("Dni");
			modelo.addColumn("Nombre");
			modelo.addColumn("Apellido");
			modelo.addColumn("Teléfono");
			modelo.addColumn("Dirección");
			modelo.addColumn("Fecha nacimiento");
			modelo.addColumn("Género");



			
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

			// Buscar paciente por DNI
			botonBuscar.addActionListener(new ActionListener() {
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String dniB = buscar.getText();
					try {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						Paciente p = BaseDeDatos.buscarPacienteDni(dniB);
						if(p!=null) {
							eliminaTablaPaciente();	
							Object[] fila = new Object[7];
							fila[0]=p.getDni();
							fila[1]=p.getNombre();
							fila[2]=p.getApellidos();
							fila[3]=p.getTelefono();
							fila[4]=p.getDireccion();
							fila[5]=p.getFechaNac();
							fila[6]=p.getGenero();
							modelo.addRow(fila);	


								/* Al buscar el DNI del paciente, aparece solamente ese por pantalla. En ese momento, al darle a buscar, se
								 * inicializa una cuenta atrás de 15 segundos. Al acabarse el tiempo, toda la lista de pacientes vuelve a 
								 * cómo estaba al comienzo, para que se pueda realizar otra consulta  nueva sin necesidad de cerrar la ventana
								 * y volver a entrar. 
								 */
								jLabelTemporizador.setText("");
								Timer timer = new Timer();

						        timer.scheduleAtFixedRate(new TimerTask() {
						            int i = 15;

						            public void run() {

						            	jLabelTemporizador.setText("Tiempo restante: " + i);
						                i--;

						                if (i < 0) {
						                    timer.cancel();
						                    jLabelTemporizador.setText("");
						                    int rowCount = modelo.getRowCount();
											//Elimina las filas uno a uno desde el final de la tabla
											for (int i = rowCount - 1; i >= 0; i--) {
											    modelo.removeRow(i);
											}
											BaseDeDatos.anadirPacienteTabla(modelo);
						                   
						                }
						            }
						        }, 0, 1000);	
						        BaseDeDatos.closeBD();

						}else {
							BaseDeDatos.closeBD();
							JOptionPane.showMessageDialog(PanelBuscar,"El dni no existe");
						}
						
						
				
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			});
			

			
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
			
			
			//Borrar paciente
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

//	//Método para transformar fecha de nacimiento en edad en el momento. 
//	public static String EdadActualPactiente(Pacientte.getFechaNac()) {
//		
//	}
//	//Método recursivo para calcular la edad media de los pacientes del hospital. 
//	public static void edadMedia(ArrayList<String> edades) {
//		int resultado = 0;
//		
//	}
//
//}