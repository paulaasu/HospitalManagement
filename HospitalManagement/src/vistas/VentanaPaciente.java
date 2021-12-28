package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;

import clases.BaseDeDatos;
import clases.Paciente;
import paneles.PanelPacientes;



public class VentanaPaciente extends JFrame {
	private JButton a�adir;

	private JPanel contentPanePaciente;
	
	private static Logger logger = Logger.getLogger( "VentanaPaciente" );
	
	//para cambiar de una ventana a otra
//	private JFrame ventanaAnterior;
	//private static JFrame ventanaActual;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPaciente frame = new VentanaPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPaciente() {		
		setBounds(100, 100, 664, 446);
		contentPanePaciente = new JPanel();
		contentPanePaciente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePaciente);
		contentPanePaciente.setLayout(new BorderLayout());
		
		Panel1 panel1 = new Panel1();
		contentPanePaciente.add(panel1, BorderLayout.CENTER);

		
		
		contentPanePaciente.setBackground(Color.WHITE);
	}
	
	class Panel1 extends JPanel{

		
		public Panel1() {
			setLayout(new BorderLayout());
			JLabel datos = new JLabel("DATOS PERSONALES:");
			datos.setHorizontalAlignment(SwingConstants.CENTER);
			datos.setFont(new Font("Sherif", Font.PLAIN, 24));
			add(datos, BorderLayout.NORTH);
			
			Panel3 panel3 = new Panel3();
			add(panel3, BorderLayout.CENTER);
			
		//	setBackground(Color.RED);
		}
			
		}
	
	
	
	class Panel3 extends JPanel{
		private JTextField nombre;
		private JRadioButton femenino;
		private JRadioButton masculino;
		private Object[] addPaciente;
		public Panel3() {
			setLayout(new GridLayout(9, 2));
			
			
			add(new JLabel("Nombre: "));
			JTextField nombreTxt= new JTextField(20);
			add(nombreTxt);
			
			
			add(new JLabel("Apellido: "));
			JTextField apellidoTxt= new JTextField(20);
			add(apellidoTxt);
			
			
			add(new JLabel("DNI: "));
			JTextField dniTxt = new JTextField(20);
			add(dniTxt);
			
			
			add(new JLabel("Fecha nacimiento: "));
			JTextField fchanacTxt= new JTextField("DD/MM/YYYY");
			add(fchanacTxt);
			
			add(new JLabel("G�nero"));
			
			//EJ JRADIOBUTTON
			JPanel genero = new JPanel();
			femenino = new JRadioButton("Femenino");
			masculino = new JRadioButton("Masculino");
			
			//hay que agrupar los JRadioButton para que al darle al S�, no le puedas dar al NO, solo puedes clickar uno
			ButtonGroup bg = new ButtonGroup(); 
			bg.add(femenino);
			bg.add(masculino);
			
			genero.add(femenino);
			genero.add(masculino);
			
			add(genero);
			
	
			add(new JLabel("Tel�fono: "));
			JTextField telefonoTxt = new JTextField(20);
			add(telefonoTxt);
		
			
			add(new JLabel("Direcci�n: "));
			JTextField dirTxt = new JTextField(20);
			add(dirTxt);
			
		
			
			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			a�adir = new JButton("A�adir paciente");
			add(a�adir);
			
			a�adir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// a�adir los datos a la BD
					if (!nombreTxt.getText().isEmpty() && !apellidoTxt.getText().isEmpty() && !dniTxt.getText().isEmpty() && !fchanacTxt.getText().isEmpty() && !telefonoTxt.getText().isEmpty() && !dirTxt.getText().isEmpty()) {
					String nombre = nombreTxt.getText();
					String apellido = apellidoTxt.getText();
					String dni = dniTxt.getText();
					String fchanac = fchanacTxt.getText();
					String dir = dirTxt.getText();
					int telefono = Integer.parseInt(telefonoTxt.getText());
					String genero1 = "";
					if(femenino.isSelected()) {
						genero1 = "Femenino";
					}if(masculino.isSelected()) {
						genero1 = "Masculino";
					}

			
					//FALTAN LAS CONDICIONES!!
					
					try {
						java.sql.Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
						//para aumentar el n� historial cada vez que se a�ade un paciente --> HAY QUE CORREGIR, DA ERROR
//						String sentSQL = "SELECT max(numHistorial) FROM paciente";
//						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
//						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
//						int historial=1;
//						while(BaseDeDatos.rs.next()) {
//							historial = BaseDeDatos.rs.getInt(9) + 1;
////							historial = historial + 1;
//						}
//						System.out.println(historial);
						int historial = 2;
						BaseDeDatos.anadirPaciente(con, nombre, apellido, dni, fchanac, genero1, telefono, dir, historial); //
						PanelPacientes.eliminaTablaPaciente();
						PanelPacientes.actualizaTablaPaciente();
						BaseDeDatos.closeBD();	
						System.out.println(historial);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					}else {
						JOptionPane.showMessageDialog( contentPanePaciente, "Debes rellenar todos campos");
					}
					
					repaint();
					dispose(); //para que cierre al darle a a�adir
			
					//pone en blanco los txtfields denuevo
					nombreTxt.setText("");
					apellidoTxt.setText("");
					dniTxt.setText("");
					fchanacTxt.setText("");
					dirTxt.setText("");
					telefonoTxt.setText("");
					//COMO SE PONE EN RADIOBUTTON EN BLANCO?
					
				}
			});
			
		}
	}
}	

