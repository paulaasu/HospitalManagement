package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import paneles.PanelPacientes;


public class VentanaHistorial extends JFrame {
	private JButton añadir;

	private JPanel contentPanePaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHistorial frame = new VentanaHistorial();
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
	public VentanaHistorial() {
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
			JLabel datos = new JLabel("Enfermeda actual:");
			datos.setHorizontalAlignment(SwingConstants.CENTER);
			datos.setFont(new Font("Sherif", Font.PLAIN, 24));
			add(datos, BorderLayout.NORTH);
			
			Panel3 panel3 = new Panel3();
			add(panel3, BorderLayout.CENTER);
			
		//	setBackground(Color.RED);
		}
			
		}
	
	
	
	class Panel3 extends JPanel{
		private JTextField motivoTxt;
		private JTextField sintomasTxt;
		private JTextField tiempoTxt;
		private JTextField apetitoTxt;
		private JTextField sedTxt;
		private JTextField sueñoTxt;
		private JTextField miccionTxt;

		public Panel3() {
			setLayout(new GridLayout(9, 2));
			
			
			add(new JLabel("Motivo de consulta: "));
			motivoTxt= new JTextField(20);
			add(motivoTxt);
			
			add(new JLabel("Signos y síntomas: "));
			sintomasTxt= new JTextField(20);
			add(sintomasTxt);
			
			add(new JLabel("Tiempo de enfermedad"));
			tiempoTxt = new JTextField(20);
			add(tiempoTxt);
			
			JLabel funcion = new JLabel("Funciones biológicas:");
			funcion.setFont(new Font("Sherif", Font.BOLD, 15));
			add(funcion);
			add(new JLabel(" "));
			
		
			
			add(new JLabel("Sed: "));
			sedTxt = new JTextField(20);
			add(sedTxt);
			
			add(new JLabel("Sueño: "));
			sueñoTxt = new JTextField(20);
			add(sueñoTxt);
			
			add(new JLabel("Micción: "));
			miccionTxt = new JTextField(20);
			add(miccionTxt);
			
		
			
			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			añadir = new JButton("Añadir");
			add(añadir);
			
			
			
			
		}
	}
}
	