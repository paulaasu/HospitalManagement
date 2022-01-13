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
import java.sql.DriverManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.BaseDeDatos;
import clases.Persona;
import clases.Usuario;


public class VentanaLogin extends JFrame {
	private JTextField txt_user;
	private JPasswordField txt_password;
	private Persona p = null;
	private Usuario u = null;
	private JButton botonIniciarSesion, botonRegistrar;
	Connection con;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaLogin() {
	
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
		 //BOTON PARA INICIAR SESION
		 botonIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String u = txt_user.getText();
				String c= String.valueOf(txt_password.getPassword());
				

					try {
						
						
						//usuario.setRol1(rol);
						
					BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
					
					if (BaseDeDatos.comprobarUsuario(u, c)) {
						setVisible(false);
						String rol=(BaseDeDatos.RolUsuario(u,c));
						VentanaPrincipal ventana = new VentanaPrincipal(rol);
						ventana.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(contentPane, "Usuario o contraseña incorrecta");
					}
					BaseDeDatos.closeBD(con);
					} catch (Exception e1){
						e1.printStackTrace();
					}
					
			
			}
		});

	}
	
	class Panel1 extends JPanel{
		
		public Panel1() { //PANEL DE LA IZQUIERDA FOTO
			
			setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon(VentanaLogin.class.getResource("/img/LoginImagen1.jpg")));
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
		
		private Panel6() {
			
setLayout(new GridLayout(7,1));
			
			JLabel lblNewLabel = new JLabel("USUARIO:");
			 add(lblNewLabel);
			 txt_user = new JTextField(10);
			 add(txt_user);
			 
			 JLabel lblPassword = new JLabel("CONTRASEÑA:");
			 add(lblPassword);
			 txt_password = new JPasswordField(10);
			 add(txt_password);
			 

			 
			 botonIniciarSesion = new JButton("INICIAR SESION");
			 add(botonIniciarSesion);
			 
			 botonRegistrar = new JButton("REGISTRARSE");
			 add(botonRegistrar);
			 
			add(new JLabel(""));
			
			 botonRegistrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					VentanaRegistro ventanaRegistro = new VentanaRegistro();
					ventanaRegistro.setVisible(true);
					
				}
			});
			
		}
	}
	
	class Panel7 extends JPanel{
		
		private Panel7() {
			setLayout(new FlowLayout());
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon(VentanaLogin.class.getResource("/img/UsuarioIcono.png")));
			add(JLabelImagen, BorderLayout.NORTH);
		}
	}

	class Panel3 extends JPanel{ //PANEL DERECHA IMAGEN
		
		public Panel3() {
			
			setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon(VentanaLogin.class.getResource("/img/LoginImagen2.jpg")));
			add(JLabelImagen);			
		}
	}

	public String Desencriptar(String con) {
		char array[]=con.toCharArray();
		for(int i=0;i<array.length;i++) {
			array[i]=(char)(array[i]-(char)5);
		}
		String desencriptado=String.valueOf(array);
		System.out.println(desencriptado);
		return desencriptado;
	}

}
