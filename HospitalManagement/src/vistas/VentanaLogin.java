package vistas;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;




public class VentanaLogin extends JFrame {
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
		private JButton boton;
		
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
			 
			 boton = new JButton("Aceptar");
			 add(boton);
			
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
