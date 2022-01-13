package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.BaseDeDatos;
import clases.TipoCita;
import clases.Cita;
import paneles.PanelCitas;
import java.sql.Connection;

public class VentanaCitas extends JFrame{
	private static Logger logger = Logger.getLogger( "VentanaCitas" );
	JPanel contentPaneCitas;
	JLabel datos ;
	PanelCita2 panelComponentes;
	private JTextField txtdni,txtnombre,txtapellidos,txtfechayhora;
	private JButton btnanadir, btncerrar;
	public SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
	private Object[] addCita;
	 private JComboBox ctipoCita;
	 private TipoCita tp;
	 public static Connection con;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCitas frame = new VentanaCitas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public VentanaCitas() {
		
		setBounds(100, 100, 664, 446);
		contentPaneCitas = new JPanel();
		contentPaneCitas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCitas);
		contentPaneCitas.setLayout(new BorderLayout());
		
		PanelCita panelCita = new PanelCita();
		contentPaneCitas.add(panelCita, BorderLayout.CENTER);
		
		
		contentPaneCitas.setBackground(new Color(345));
	}
	
	class PanelCita extends JPanel{
		 public PanelCita() {
			
		setLayout(new BorderLayout());
		 datos = new JLabel("AÑADIR DATOS :");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
		add(datos, BorderLayout.NORTH);
			

		 panelComponentes = new PanelCita2();
		add( panelComponentes, BorderLayout.CENTER);
		
		
		
		}
		

	}
	class PanelCita2  extends JPanel{
		private JTextField txtdni,txtnombre,txtapellidos,txtfechayhora,txttipoCita;
		private JButton btnanadir, btncerrar;
		public SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		private Object[] addCita;
		protected Component contentPaneCitas;
		
		 public PanelCita2() {
			// TODO Auto-generated constructor stub
			 setLayout(new GridLayout(9, 2));
			
			add(new JLabel("DNI: "));
			txtdni = new JTextField(20);
			add( txtdni);
			add(new JLabel("NOMBRE: "));
			txtnombre= new JTextField(20);
			add(txtnombre);
			
			
			add(new JLabel("APEDILLOS: "));
			txtapellidos= new JTextField(50);
			add(txtapellidos);
		
			add(new JLabel("FECHA Y HORA: "));
			txtfechayhora=new JTextField("yyyy-MM-dd HH:mm") ;
			add(txtfechayhora);
			

			//cambio
			add(new JLabel("TIPO CITA: "));
			ctipoCita = new JComboBox<>(TipoCita.values());
			add(ctipoCita);
			
//			add(new JLabel("TIPO: "));

			

			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			btnanadir = new JButton("Añadir ");
			add(btnanadir);
			
			
			
			
			
			btnanadir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (!txtdni.getText().isEmpty() && !txtnombre.getText().isEmpty() && !txtapellidos.getText().isEmpty()   ) {
						String dni = txtdni.getText();
						String nombre = txtnombre.getText();
						String apellido = txtapellidos.getText();
						//String fechayhora = txtfechayhora.getText();
						//nuevo 
						Date fechayhorad = null;
						try {
							fechayhorad = sdf1.parse(txtfechayhora.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						TipoCita t = (TipoCita) ctipoCita.getSelectedItem();
						Cita cita = new Cita(dni, nombre, apellido, fechayhorad, t);
						try {
						 con = BaseDeDatos.initBD("BaseDeDatos.db");
						//nuevo4/01
						BaseDeDatos.anadirCita(con , cita); //
						PanelCitas.cargarModeloTabla();
						BaseDeDatos.closeBD(con);	
						}catch (Exception ex) {
							// TODO: handle exception
							
						}
						
						
//						con = BaseDeDatos.initBD("BaseDeDatos.db");
//						BaseDeDatos.anadirCita(con, cita);
//						
//						PanelCitas.cargarModeloTabla();
//						BaseDeDatos.closeBD(con);
					
					repaint();
					dispose();
			
					//pone en blanco los txtfields denuevo
					txtnombre.setText("");
					txtapellidos.setText("");
					txtdni.setText("");
					txtfechayhora.setText("");
					}
				}
				
					
			});
			
			}
		 
			
}
	
	
		
	}
	

