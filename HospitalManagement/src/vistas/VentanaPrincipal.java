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
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import clases.Paciente;

import paneles.PanelPacientes;
import paneles.PanelAgenda;
import paneles.PanelCitas;
import paneles.PanelExportar;
import paneles.PanelHistorial;
import paneles.PanelImportar;
import paneles.PanelInicio;

public class VentanaPrincipal extends JFrame {
	private boolean paciente = false;
	JButton btnInicio,btnAgenda,btnPaciente,btnCitas,btnExportar,btnImportar, btnHistorial;
	//Creamos un treemap de pacientes
	public static TreeMap<String,Paciente> tmPacientes;
	//para ir cambiando de ventana en ventana
	private static JFrame ventanaAnterior;
	private static JFrame ventanaActual;
	
	

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
		
		// va a ser la ventana actual
//		ventanaActual = this;
//		ventanaAnterior = va;
		PanelInicio pii = new PanelInicio();
		PanelAgenda pa=new PanelAgenda();
		PanelCitas pc=new PanelCitas();
		PanelExportar pe=new PanelExportar();
		PanelImportar pi=new PanelImportar();
		PanelPacientes pp= new PanelPacientes();
		PanelHistorial ph= new PanelHistorial();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Panel1 panel1 = new Panel1();
		add(panel1, BorderLayout.WEST);
		Panel2 panel2 = new Panel2();
		add(panel2); //borderlayout.center
		 //CAMBIO DE PANELES
		
		
		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(true);
//				pi.setVisible(false);pe.setVisible(false);pc.setVisible(false);//pp.setVisible(false);
//				pa.setVisible(false);
//				add(panel2, BorderLayout.CENTER);

				panel2.removeAll();
				panel2.add(pii);
				panel2.updateUI();
				
			}
			
		});
		btnAgenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(false);pi.setVisible(false);pe.setVisible(false);pc.setVisible(false);//pp.setVisible(false);
//				pa.setVisible(true);
//				add(pa, BorderLayout.CENTER);
				
				panel2.removeAll();
				panel2.add(pa);
				panel2.updateUI();
				

			}

			
		});
		btnPaciente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(false);pa.setVisible(false);pi.setVisible(false);pe.setVisible(false);pc.setVisible(false);
//				
//				pp.setVisible(true);
//				add(pp, BorderLayout.CENTER);
				
				panel2.removeAll();
				panel2.add(pp);
				panel2.updateUI();
				
			}
			
		});
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(false);pa.setVisible(false);pi.setVisible(false);pe.setVisible(false);pc.setVisible(false);
//				
//				ph.setVisible(true);
//				add(ph, BorderLayout.CENTER);
				
				panel2.removeAll();
				panel2.add(ph);
				panel2.updateUI();
			}
			
		});
		btnCitas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(false);pa.setVisible(false);pi.setVisible(false);pe.setVisible(false);//pp.setVisible(false);
//
//				pc.setVisible(true);
//				add(pc, BorderLayout.CENTER);
				
				panel2.removeAll();
				panel2.add(pc);
				panel2.updateUI();
			}
			
		});
		btnExportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(false);pa.setVisible(false);pi.setVisible(false);//pp.setVisible(false);
//				pc.setVisible(false);
//
//				pe.setVisible(true);
//				add(pe, BorderLayout.CENTER);
				
				panel2.removeAll();
				panel2.add(pe);
				panel2.updateUI();

			}
			
		});
		btnImportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				panel2.setVisible(false);pa.setVisible(false);//pp.setVisible(false);
//				pe.setVisible(false);pc.setVisible(false);
//
//				pi.setVisible(true);
//				add(pi, BorderLayout.CENTER);
				
				panel2.removeAll();
				panel2.add(pi);
				panel2.updateUI();
			}
			
		});
		
	}
	
class Panel2 extends JPanel{ //PANEL QUE TE APARECE AL PRINCIPIO
		
		public Panel2(){
			setLayout(new GridLayout(1,1));
			PanelInicio panelInicio = new PanelInicio();
			add(panelInicio);
			// llamamos a la ventana de inicio
			
//			ventanaActual.dispose();
//			ventanaAnterior.setVisible(true);

			

			
		}
	}
	class Panel1 extends JPanel{
		
		public Panel1() {
			setLayout(new BorderLayout());
			JLabel JLabelImagen = new JLabel();
			JLabelImagen.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ImagenLogo.jpg")));
			add(JLabelImagen, BorderLayout.NORTH);
			Panel3 panel3 = new Panel3();
			add(panel3, BorderLayout.CENTER);
			setBackground(Color.WHITE);
			
		}
		
		class Panel3 extends JPanel{
	
			public Panel3() {
				setLayout(new GridLayout(7,1));
				

				btnInicio=new JButton("INICIO");
				btnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnInicio.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnInicio.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnInicio.setBackground(new Color(176, 196, 222));
				btnInicio.setOpaque(true);
				add(btnInicio, BorderLayout.CENTER);
				
				btnAgenda=new JButton("AGENDA");
				//lblAgenda = new JLabel("Agenda");
				btnAgenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnAgenda.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnAgenda.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnAgenda.setBackground(new Color(176, 196, 222));
				btnAgenda.setOpaque(true);
				add(btnAgenda, BorderLayout.CENTER);
				
				btnPaciente=new JButton("PACIENTE");
				//lblPaciente = new JLabel("Pacientes");
				btnPaciente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnPaciente.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnPaciente.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnPaciente.setBackground(new Color(176, 196, 222));
				btnPaciente.setOpaque(true);
				add(btnPaciente, BorderLayout.CENTER);
				
				
				btnHistorial=new JButton("HISTORIAL CLINICO");
				btnHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnHistorial.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnHistorial.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnHistorial.setBackground(new Color(176, 196, 222));
				btnHistorial.setOpaque(true);
				add(btnHistorial, BorderLayout.CENTER);
				
				btnCitas=new JButton("CITAS");
				//lblCitas = new JLabel("Citas");
				btnCitas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnCitas.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnCitas.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnCitas.setBackground(new Color(176, 196, 222));
				btnCitas.setOpaque(true);
				add(btnCitas, BorderLayout.CENTER);
				
				btnExportar=new JButton("EXPORTAR DATOS");
				//lblExportar = new JLabel("Exportar datos");
				btnExportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnExportar.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnExportar.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnExportar.setBackground(new Color(176, 196, 222));
				btnExportar.setOpaque(true);
				add(btnExportar, BorderLayout.CENTER);
				
				btnImportar=new JButton("IMPORTAR DATOS");
				//btnImportar = new JLabel("Importar datos");
				btnImportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnImportar.setFont(new Font("Verdana", Font.PLAIN, 14));
				btnImportar.setBorder(new MatteBorder(2, 0, 2, 2, (Color) Color.WHITE));
				btnImportar.setBackground(new Color(176, 196, 222));
				btnImportar.setOpaque(true);
				add(btnImportar, BorderLayout.CENTER);
				
			}
			
			
		}
		
		
	}
	
	
	

}