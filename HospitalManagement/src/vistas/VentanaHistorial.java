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
import clases.HistorialClinico;
import paneles.PanelPacientes;


public class VentanaHistorial extends JFrame {
	private JButton editar;
	private JButton salir;

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
			JLabel datos = new JLabel("Enfermedad actual:");
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
		private JTextField motivoTxt;
		private JTextField sintomasTxt;
		private JTextField tiempoTxt;
		private JTextField apetitoTxt;
		private JTextField sedTxt;
		private JTextField sueñoTxt;
		private JTextField miccionTxt;
		java.sql.Connection con;
		public Panel3() {
			setLayout(new GridLayout(7, 2));

			
			try {
				BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
				HistorialClinico h = BaseDeDatos.visualizarHistorial(VentanaVisualizarHist.devuelveDni());
				
				JLabel motivo = new JLabel("Enfermedad: "); // ESTO ES LO QUE HACE QUE EL FON
				add(motivo);
				JLabel motivoHist = new JLabel(h.getEnfermedad());
				motivoHist.setBackground(Color.WHITE);
				motivoHist.setOpaque(true);
				add(motivoHist);

					
				add(new JLabel("Signos y síntomas: "));
				JLabel sintomasHist = new JLabel(h.getSintomas());
				sintomasHist.setBackground(Color.WHITE);
				sintomasHist.setOpaque(true);
				add(sintomasHist);
					
				add(new JLabel("Tiempo de enfermedad"));
				JLabel tiempoHist = new JLabel(h.getTiempo());
				tiempoHist.setBackground(Color.WHITE);
				tiempoHist.setOpaque(true);
				add(tiempoHist);
					
				JLabel funcion = new JLabel("Funciones biológicas:");
				funcion.setFont(new Font("Sherif", Font.BOLD, 15));
				add(funcion);
				add(new JLabel(" "));
					
				
					
				add(new JLabel("Sed: "));
				JLabel sedHist = new JLabel(h.getSed());
				sedHist.setBackground(Color.WHITE);
				sedHist.setOpaque(true);
				add(sedHist);

					
				add(new JLabel("Sueño: "));
				JLabel sueñoHist = new JLabel(h.getSueño());
				sueñoHist.setBackground(Color.WHITE);
				sueñoHist.setOpaque(true);
				add(sueñoHist);
					
				add(new JLabel("Micción: "));
				JLabel miccionHist = new JLabel(h.getMiccion());
				miccionHist.setBackground(Color.WHITE);
				miccionHist.setOpaque(true);
				add(miccionHist);
					
					BaseDeDatos.closeBD(con);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
		}
	}
	
}
	
	