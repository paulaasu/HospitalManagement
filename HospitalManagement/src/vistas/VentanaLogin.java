package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		setBounds(100, 100, 565, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		Panel1 panel1=new Panel1();
		contentPane.add(panel1, BorderLayout.SOUTH);
		Panel3 panel3=new Panel3();
		contentPane.add(panel3, BorderLayout.WEST);
		Panel2 panel2=new Panel2();
		contentPane.add(panel2, BorderLayout.CENTER);
	
		
		
	}
	/* Panel inferior con el boton aceptar*/
	 class Panel1 extends JPanel{
			private JButton btnAceptar;
		 //constructor
		public Panel1(){
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			btnAceptar=new JButton("Aceptar");
			btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
			add(btnAceptar);
			
			
			
		}
	}
	 /* Panel central */
	 class Panel2 extends JPanel{
			private JButton btnAceptar;
			private JTextField txt_user;
			private JTextField txt_password;
		 //constructor
		public Panel2(){
			setLayout(null);
			JLabel lblNewLabel = new JLabel("USER:");
			lblNewLabel.setBounds(141, 77, 72, 16);
			add(lblNewLabel);
			
			txt_user = new JTextField();
			txt_user.setBounds(225, 74, 200, 22);
			add(txt_user);
			txt_user.setColumns(10);
			
			JLabel lblPassword = new JLabel("PASSWORD:");
			lblPassword.setBounds(141, 112, 88, 16);
			add(lblPassword);
			
			txt_password = new JTextField();
			txt_password.setColumns(10);
			txt_password.setBounds(228, 109, 200, 22);
			add(txt_password);
		
			
		}
	}
	 /* Panel inferior con el boton aceptar*/
	 class Panel3 extends JPanel{
			private JButton btnAceptar;
			private JLabel label;
		 //constructor
		public Panel3(){
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label=new JLabel();
			label.setBounds(12, 56, 88, 123);
			ImageIcon imagen=new ImageIcon("Media/imagen1.jpg");
			Icon icon=new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), label.ABORT));
			label.setIcon(icon);
			
			add(label);
			
			
			
			
		}
	 }
	
	
}
