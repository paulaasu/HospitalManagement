package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class VentanaPaciente extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout());
		
		Panel1 panel1 = new Panel1();
		contentPane.add(panel1);
		Panel2 panel2 = new Panel2();
		contentPane.add(panel2);
		
		
		contentPane.setBackground(Color.WHITE);
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
	}
	
	class Panel2 extends JPanel{ //TABLA DATOS PACIENTE
		
		public Panel2() {
			setLayout(new BorderLayout());
			add(new JLabel("AQUÍ VA LA JTABLE DE PACIENTE"), BorderLayout.CENTER);
			setBackground(Color.GRAY);
			
		}
	}
	
	class Panel3 extends JPanel{
		private JTextField nombre;
		private JRadioButton femenino;
		private JRadioButton masculino;
		private JButton añadir;
		public Panel3() {
			setLayout(new GridLayout(9, 2));
			
			
			add(new JLabel("Nombre: "));
			JTextField nombre= new JTextField(20);
			add(nombre);
			
			add(new JLabel("Apellido: "));
			JTextField apellido= new JTextField(20);
			add(apellido);
			
			add(new JLabel("DNI: "));
			JTextField dni = new JTextField(20);
			add(dni);
			
			add(new JLabel("Fecha nacimiento: "));
			JTextField fchanac= new JTextField("DD/MM/YYYY");
			add(fchanac);
			
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
			
			add(new JLabel("Teléfono: "));
			JTextField telefono = new JTextField(20);
			add(telefono);
			
			add(new JLabel("Dirección: "));
			JTextField dir = new JTextField(20);
			add(dir);
			
			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			añadir = new JButton("Añadir paciente");
			add(añadir);
			
		}
	}
	
	