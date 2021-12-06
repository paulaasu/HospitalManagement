package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.BaseDeDatos;
import clases.Persona;
import clases.Usuario;




public class VentanaLogin extends JFrame {
	private JPanel contentPane;
	public Connection con;
	public VentanaPaciente vp;
	
	
	// ventana 
	private JFrame ventanaAnterior;
	private static JFrame ventanaActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin(ventanaActual );
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaLogin(JFrame va) {
//		 llamar a la base de datos
		
		BaseDeDatos.initBD( "hospitalManagementBD.db");
		BaseDeDatos.crearTablas(con);
		BaseDeDatos.closeBD();
		
		ventanaActual = this;
		ventanaAnterior = va;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout());
		
		Panel1 panel1 = new Panel1();
		contentPane.add(panel1);
		Panel2 panel2 = new Panel2();
		contentPane.add(panel2);
		Panel3 panel3 = new Panel3();
		contentPane.add(panel3);
		
		contentPane.setBackground(Color.WHITE);

	}
	
	class Panel1 extends JPanel{
		
		public Panel1() { //PANEL DE LA IZQUIERDA FOTO
			
			setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon("C:\\Users\\Propietario\\Pictures\\Imagenesproyecto\\LoginImagen1.jpg"));
			add(JLabelImagen);
			
		//	setBackground(Color.BLUE);
			
			
		}
	}
	
	class Panel2 extends JPanel{ //PANEL DEL MEDIO 
		private BorderLayout borderlayout1 = new BorderLayout();
		
		public Panel2() {
			setLayout(borderlayout1);
			Panel4 panel4 = new Panel4();
			add(panel4, BorderLayout.NORTH);
			setBackground(Color.GREEN);
			
			Panel5 panel5 = new Panel5();
			add(panel5, BorderLayout.CENTER);
			
		}
	}
	
	class Panel4 extends JPanel{ //PANEL QUE CONTIENE "INICIAR SESION"
		
		public Panel4() {
			
			JLabel datos = new JLabel("INICIAR SESIÓN");
			datos.setHorizontalAlignment(SwingConstants.CENTER);
			datos.setFont(new Font("Sherif", Font.PLAIN, 24));
			add(datos, BorderLayout.NORTH);
			
//			JLabel JLabelImagen = new JLabel();
//			JLabelImagen.setIcon(new ImageIcon("C:\\Users\\Propietario\\Pictures\\Imagenesproyecto\\UsuarioIcono.png"));
//			add(JLabelImagen);
		//	setBackground(Color.PINK);
		}
		
		
	}
	
	class Panel5 extends JPanel{ //CON EL LOGO Y LOS USUARIOS Y CONTRASEÑAS
		private JTextField txt_user;
		private JTextField txt_password;
		private JButton boton;
		
		public Panel5() {
			
			setLayout(new BorderLayout());
			
			
			Panel7 panel7 = new Panel7();
			add(panel7, BorderLayout.NORTH);
			
			Panel6 panel6 = new Panel6();
			add(panel6, BorderLayout.CENTER);
			
		//	 setBackground(Color.GRAY);
		}
	}
	
	class Panel6 extends JPanel{ //PANEL QUE ORDENA LOS USUARIOS Y CONTRASEÑAS
		private JTextField txt_user;
		private JTextField txt_password;
		private JTextField txt_tipo;
		private Persona p = null;
		private Usuario u = null;
		private JButton botonIniciarSesion, botonRegistrar;
		
		private Panel6() {
			
			setLayout(new GridLayout(6,1));
			
			JLabel lblNewLabel = new JLabel("USUARIO:");
			 add(lblNewLabel);
			 txt_user = new JTextField(10);
			 add(txt_user);
			 
			 JLabel lblPassword = new JLabel("CONTRASEÑA:");
			 add(lblPassword);
			 txt_password = new JTextField(10);
			 add(txt_password);
			 
			 JLabel lblTipo = new JLabel("TIPO:");
			 add(lblTipo);
			  txt_tipo = new JTextField(10);
			 add(txt_tipo);
			 
			 botonIniciarSesion = new JButton("INICIAR SESION");
			 add(botonIniciarSesion);
			 
			 //BOTON QUE VA A LA BASE DE DATOS 
			 botonIniciarSesion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
						String n = txt_user.getText();
						String c= txt_password.getText();
						String t = txt_tipo.getText();
						
						
						/*char [] ac = textContrasenia.getPassword();
						String c = String.valueOf(ac);*/
						
						Connection con = BaseDeDatos.initBD("hospitalmanagement.db");
						Usuario u = obtenerUsuario(con, n, c); // porque me da problemas??
//						Persona p = BaseDeDatos. obtenerPersona(con, n, c,t); 
						BaseDeDatos.closeBD();
						
						if(u==null) {
							
							JOptionPane.showMessageDialog(null, "Para iniciar sesión tienes que registrarte primero");
							
						}else if( !u.equals(txt_password)) {
							JOptionPane.showMessageDialog(null, "ERROR! La contraseña no es correcta");
						}else {
							
								JOptionPane.showMessageDialog(null, "Ongi etorri!");
								Persona p = BaseDeDatos.obtenerPersona(con, n, c, t);
							
							
							txt_password.setEnabled(false);
							txt_user.setEnabled(false);
							txt_tipo.setEnabled(false);
							vp.setVisible(true);
							
						}
						txt_user.setText("");
						txt_password.setText("");
					
					
					
				}
			});
			 botonRegistrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
				}
			});
		
			
		}
	}
	
	class Panel7 extends JPanel{
		
		private Panel7() {
			setLayout(new FlowLayout());
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon("C:\\Users\\Propietario\\Pictures\\Imagenesproyecto\\UsuarioIcono.png"));
			add(JLabelImagen, BorderLayout.NORTH);
		}
	}
	
	
	
	class Panel3 extends JPanel{ //PANEL DERECHA IMAGEN
		
		public Panel3() {
			
			setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon("C:\\Users\\Propietario\\Pictures\\Imagenesproyecto\\LoginImagen2.jpg"));
			add(JLabelImagen);
			
		//	setBackground(Color.RED);
			
		}
	}
	
	
	
}
