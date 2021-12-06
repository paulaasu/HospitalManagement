package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class VentanaPaciente extends JFrame {
	private JButton añadir;

	private JPanel contentPanePaciente;
	
	private static Logger logger = Logger.getLogger( "VentanaPaciente" );

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
		setDefaultCloseOperation(VentanaPaciente.EXIT_ON_CLOSE);
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
			String nombre = nombreTxt.getText();
			
			add(new JLabel("Apellido: "));
			JTextField apellidoTxt= new JTextField(20);
			add(apellidoTxt);
			String apellido = apellidoTxt.getText();
			
			add(new JLabel("DNI: "));
			JTextField dniTxt = new JTextField(20);
			add(dniTxt);
			String dni = dniTxt.getText();
			
			add(new JLabel("Fecha nacimiento: "));
			JTextField fchanacTxt= new JTextField("DD/MM/YYYY");
			add(fchanacTxt);
			String fchanac = fchanacTxt.getText();
			
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
			//para al darle a femenino se guarde este en la tabla
			String genero1;
//			if(femenino.isSelected()) {
//				genero1 = "Femenino";
//			}else {
//				genero1 = "Masculino";
//			}
			genero1 = "Femenino";
			
			
			add(new JLabel("Teléfono: "));
			JTextField telefonoTxt = new JTextField(20);
			add(telefonoTxt);
		//	int telefono = Integer.parseInt(telefonoTxt.getText());
			int telefono = 1;
			
			add(new JLabel("Dirección: "));
			JTextField dirTxt = new JTextField(20);
			add(dirTxt);
			String dir = dirTxt.getText();
			
		
			
			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			añadir = new JButton("Añadir paciente");
			add(añadir);
			
			añadir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// añadir los datos a la BD
					if (!(nombreTxt.getText().isEmpty() && apellidoTxt.getText().isEmpty() && dniTxt.getText().isEmpty() && fchanacTxt.getText().isEmpty() && telefonoTxt.getText().isEmpty() && dirTxt.getText().isEmpty())) {
						
					
					java.sql.Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
					// añadir un paciente
					BaseDeDatos.anadirPaciente(con, nombre, apellido, dni, fchanac, genero1, telefono, dir); //aqui no esta el error
					logger.log( Level.INFO, "Se ha añadido el paciente" );
					BaseDeDatos.closeBD();
					}else {
						JOptionPane.showMessageDialog( contentPanePaciente, "Debes rellenar todos campos" );
					}

					
					dispose(); //para que cierre al darle a añadir
				}
			});
			
		}
	}
}	//AÑADIR UN PACIENTE Y GUARDARLO EN LA BASE DE DATOS(hecho)
	

