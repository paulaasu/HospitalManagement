package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import paneles.PanelPacientes;
import paneles.PanelAgenda;
import paneles.PanelConsultas;
import paneles.PanelExportar;
import paneles.PanelImportar;

public class VentanaPrincipal extends JFrame {
	private boolean paciente = false;
	private JLabel lblPaciente;
	private JLabel lblConsultas;
	private JLabel lblAgenda;
	private JLabel lblExportar;
	private JLabel lblImportar;
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel1 panel1 = new Panel1();
		add(panel1, BorderLayout.WEST);
		Panel2 panel2 = new Panel2();
		add(panel2, BorderLayout.CENTER);
		 //CAMBIO DE PANELES
		lblAgenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel2.removeAll();
				panel2.revalidate();
				add(new PanelAgenda(), BorderLayout.CENTER);
				
			}
		}); 
		
		lblConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel2.removeAll();
				panel2.revalidate();
				add(new PanelConsultas(), BorderLayout.CENTER);
				
			}
		});
		
		lblPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel2.removeAll();
				panel2.revalidate();
				add(new PanelPacientes(), BorderLayout.CENTER);
				
				
			}
		});
		
		lblExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel2.removeAll();
				panel2.revalidate();
				add(new PanelExportar(), BorderLayout.CENTER);
				
			}
		});
		
		lblImportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel2.removeAll();
				panel2.revalidate();
				add(new PanelImportar(), BorderLayout.CENTER);
				
			}
		});
		
	}
	
	void paciente(boolean paciente) {
		
	}
	 
	
	class Panel1 extends JPanel{
		
		public Panel1() {
			setLayout(new BorderLayout());
			
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon("C:\\Users\\Propietario\\Pictures\\Imagenesproyecto\\ImagenLogo.jpg"));
			add(JLabelImagen, BorderLayout.NORTH);
			
			Panel3 panel3 = new Panel3();
			add(panel3, BorderLayout.CENTER);
			
			JLabel JLabelImagen2 = new JLabel();
			JLabelImagen2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			JLabelImagen2.setIcon(new ImageIcon("file:///C:/Users/Propietario/Pictures/Imagenesproyecto/Logosalir.png"));
			JLabelImagen2.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
			JLabelImagen2.setBackground(Color.LIGHT_GRAY);
			JLabelImagen2.setOpaque(true);
			add(JLabelImagen2, BorderLayout.SOUTH);
			
			setBackground(Color.WHITE);
			
		}
		
		class Panel3 extends JPanel{
	
			public Panel3() {
				setLayout(new GridLayout(5,1));
				
				lblAgenda = new JLabel("Agenda");
				lblAgenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblAgenda.setFont(new Font("Verdana", Font.PLAIN, 14));
				lblAgenda.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				lblAgenda.setBackground(Color.LIGHT_GRAY);
				lblAgenda.setOpaque(true);
				add(lblAgenda, BorderLayout.CENTER);
				
				lblPaciente = new JLabel("Pacientes");
				lblPaciente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblPaciente.setFont(new Font("Verdana", Font.PLAIN, 14));
				lblPaciente.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				lblPaciente.setBackground(Color.LIGHT_GRAY);
				lblPaciente.setOpaque(true);
				add(lblPaciente, BorderLayout.CENTER);
				
				
				
				lblConsultas = new JLabel("Consultas");
				lblConsultas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblConsultas.setFont(new Font("Verdana", Font.PLAIN, 14));
				lblConsultas.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				lblConsultas.setBackground(Color.LIGHT_GRAY);
				lblConsultas.setOpaque(true);
				add(lblConsultas, BorderLayout.CENTER);
				
				lblExportar = new JLabel("Exportar datos");
				lblExportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblExportar.setFont(new Font("Verdana", Font.PLAIN, 14));
				lblExportar.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				lblExportar.setBackground(Color.LIGHT_GRAY);
				lblExportar.setOpaque(true);
				add(lblExportar, BorderLayout.CENTER);
				
				lblImportar = new JLabel("Importar datos");
				lblImportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblImportar.setFont(new Font("Verdana", Font.PLAIN, 14));
				lblImportar.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				lblImportar.setBackground(Color.LIGHT_GRAY);
				lblImportar.setOpaque(true);
				add(lblImportar, BorderLayout.CENTER);
				
				
				
			}
			
			
		}
		
		
	}
	
	class Panel2 extends JPanel{ //PANEL QUE TE APARECE AL PRINCIPIO
		public Panel2(){
			setLayout(new BorderLayout());
			JLabel datos = new JLabel("HOSPITAL MANAGEMENT");
			datos.setHorizontalAlignment(SwingConstants.CENTER);
			datos.setFont(new Font("Tahoma", Font.BOLD, 24));
			add(datos, BorderLayout.NORTH);
			

			
		}
	}

}