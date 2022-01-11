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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private JButton añadir;

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
			setBackground(new Color(176, 196, 222));
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
			
			add(new JLabel("DNI: "));
			JTextField dniTxt = new JTextField(20);
			add(dniTxt);
			
			add(new JLabel("Nombre: "));
			JTextField nombreTxt= new JTextField(20);
			add(nombreTxt);
			
			
			add(new JLabel("Apellido: "));
			JTextField apellidoTxt= new JTextField(20);
			add(apellidoTxt);
			
			add(new JLabel("Teléfono: "));
			JTextField telefonoTxt = new JTextField(20);
			add(telefonoTxt);
			
			add(new JLabel("Dirección: "));
			JTextField dirTxt = new JTextField(20);
			add(dirTxt);
			
			add(new JLabel("Fecha nacimiento: "));
			JTextField fchanacTxt= new JTextField("DD/MM/YYYY");
			add(fchanacTxt);
			
			add(new JLabel("Género"));
			
			//EJ JRADIOBUTTON
			JPanel genero = new JPanel();
			femenino = new JRadioButton("Femenino");
			masculino = new JRadioButton("Masculino");
			
			//hay que agrupar los JRadioButton para que al darle al SÍ, no le puedas dar al NO, solo puedes clickar uno
			ButtonGroup bg = new ButtonGroup(); 
			bg.add(femenino);
			bg.add(masculino);
			
			genero.add(femenino);
			genero.add(masculino);
			
			add(genero);
			
	
			
			
			
		
			
			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			añadir = new JButton("Añadir paciente");
			add(añadir);
			
			añadir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// añadir los datos a la BD
					if (!nombreTxt.getText().isEmpty() && !apellidoTxt.getText().isEmpty() && !dniTxt.getText().isEmpty() && !fchanacTxt.getText().isEmpty() && !telefonoTxt.getText().isEmpty() && !dirTxt.getText().isEmpty()) {
					String nombre = nombreTxt.getText();
					String apellido = apellidoTxt.getText();
					String dni = dniTxt.getText();
					String fchanac = fchanacTxt.getText();
					String dir = dirTxt.getText();
					String telefono = telefonoTxt.getText();
					String genero1 = "";
					if(femenino.isSelected()) {
						genero1 = "Femenino";
					}if(masculino.isSelected()) {
						genero1 = "Masculino";
					}
					 //LOS PATRONES
					Pattern patronDni = Pattern.compile("[0-9]{7,8}[A-Z]"); // Patron DNI
					Matcher matDni = patronDni.matcher(dni);
					boolean cumplePatronDni = matDni.matches();
					
					Pattern patronTf = Pattern.compile("[0-9]{9}"); // Patron teléfono
					Matcher matTf = patronTf.matcher(telefono);
					boolean cumplePatronTf = matTf.matches();
					//DD/MM/YYYY
					Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
					Matcher matFecha = patronFecha.matcher(fchanac);
					boolean cumplePatronFecha = matFecha.matches();
					
					
					
					if(cumplePatronDni == true) {
						if( cumplePatronTf== true) {
							int tf = Integer.parseInt(telefonoTxt.getText());
							if(cumplePatronFecha == true) {	
								try {
									java.sql.Connection con = BaseDeDatos.initBD("BaseDeDatos.db",true);
									BaseDeDatos.anadirPaciente(con, dni, nombre, apellido, tf, dir, fchanac, genero1);
									PanelPacientes.eliminaTablaPaciente();
									PanelPacientes.actualizaTablaPaciente();
									BaseDeDatos.closeBD(con);	
									dispose();
									repaint();
									//pone en blanco los txtfields denuevo
									nombreTxt.setText("");
									apellidoTxt.setText("");
									dniTxt.setText("");
									fchanacTxt.setText("");
									dirTxt.setText("");
									telefonoTxt.setText("");
									//RADIOBUTON EN BLANCO?
									BaseDeDatos.closeBD(con);
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog( null, "Fecha incorrecta");
							}					
						}else {
							JOptionPane.showMessageDialog( null, "Teléfono incorrecto");
						}				
					}else{
						JOptionPane.showMessageDialog( null, "Dni incorrecto");
					}
					
		
					}else {
						JOptionPane.showMessageDialog( contentPanePaciente, "Debes rellenar todos campos");
					}
					
					 //para que cierre al darle a añadir
					
					
					//COMO SE PONE EN RADIOBUTTON EN BLANCO?
					
				}
			});
			
		}
	}
}	

