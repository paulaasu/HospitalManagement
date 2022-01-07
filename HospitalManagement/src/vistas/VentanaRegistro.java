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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;

import clases.BaseDeDatos;
import clases.Paciente;
import clases.TipoUsuario;
import clases.Usuario;
import paneles.PanelPacientes;


public class VentanaRegistro extends JFrame {
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
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {		
		setBounds(100, 100, 522, 246);
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
			JLabel datos = new JLabel("REGISTRARSE:");
			datos.setHorizontalAlignment(SwingConstants.CENTER);
			datos.setFont(new Font("Sherif", Font.PLAIN, 24));
			datos.setBackground(new Color(176, 196, 222));
			add(datos, BorderLayout.NORTH);
			
			Panel3 panel3 = new Panel3();
			add(panel3, BorderLayout.CENTER);
			
		//	setBackground(Color.RED);
		}
			
		}
	
	
	
	class Panel3 extends JPanel{
		private JTextField usuario;
		private JPasswordField contraseña;
		private JPasswordField repiteContraseña;
		private JButton registro;
		private JComboBox<TipoUsuario> tipoUsuario;
		public Panel3() {
			setLayout(new GridLayout(5, 2));
			
			add(new JLabel("Usuario: "));
			 usuario = new JTextField(20);
			add(usuario);
			
			add(new JLabel("Contraseña: "));
			 contraseña= new JPasswordField(20);
			add(contraseña);
			
			add(new JLabel("Repita la contraseña: "));
			 repiteContraseña= new JPasswordField(20);
			add(repiteContraseña);
			
			//el combobox de los tipos: medico, recepcionista, informatico
			add(new JLabel("Escoge el tipo de usuario: "));
			tipoUsuario = new JComboBox<TipoUsuario>();
			for (TipoUsuario tu : TipoUsuario.values()) {
				tipoUsuario.addItem(tu);
			}
			add(tipoUsuario);
			
			
//			add(new JLabel(" "));
//			add(new JLabel(" "));
			add(new JLabel(" "));
			registro = new JButton("Aceptar");
			add(registro);
			registro.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					String usuario1 = usuario.getText();
//					String password1 = usuario.getText();
//					String passwordRepeat = usuario.getText();
//					TipoUsuario rol1 = (TipoUsuario) tipoUsuario.getSelectedItem();
//					//ID --> No se pregunta, cada nuevo usuario se crea un nuevo ID automáticamente
//					//Rol
//					
//					//Conseguir lo que la persona ha puesto en el comboBox
//				//	para aumentar el nª historial cada vez que se añade un paciente --> HAY QUE CORREGIR, DA ERROR
//					
//					try {
//						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
//						String sentSQL = "SELECT ID_usuario, user, password, Rol, max(ID_usuario) FROM usuario";
//						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
//						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
//						int numeroUsuario = 1;
//						while(BaseDeDatos.rs.next()) {
//							numeroUsuario = BaseDeDatos.rs.getInt(5) + 1;
////							numeroUsuario = numeroUsuario + 1;
//							Usuario usuarioNuevo = new Usuario(usuario1, password1, numeroUsuario, rol1);
//						}
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
					System.out.println(tipoUsuario.getSelectedItem());
				}
			});
			
			
}
		}
	}