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
import java.text.SimpleDateFormat;
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


public class VentanaCitas extends JFrame{
	private static Logger logger = Logger.getLogger( "VentanaCitas" );
	JPanel contentPaneCitas;
	JLabel datos ;
	PanelCita2 panelComponentes;
	private JTextField txtdni,txtnombre,txtapellidos,txtfechayhora;
	private JButton btnanadir, btncerrar;
	public SimpleDateFormat sdf1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
	private Object[] addCita;
	 private JComboBox ctipoCita;
	 private TipoCita tp;
	
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
		public SimpleDateFormat sdf1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
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
			JTextField txtapellido= new JTextField(50);
			add(txtapellido);
		
			add(new JLabel("FECHA Y HORA: "));
			txtfechayhora=new JTextField("dd/MM/yyyy HH:mm:ss") ;
			add(txtfechayhora);
			

			ctipoCita = new JComboBox<>();
			add(ctipoCita);
			
			
//			add(new JLabel("TIPO: "));
//			ButtonGroup bg = new ButtonGroup(); 
//			JPanel tipo= new JPanel();
//			JRadioButton CABECERA = new JRadioButton("CABECERA");
//			JRadioButton GINECOLOGO = new JRadioButton("GINECOLOGO");
//			JRadioButton OTRO = new JRadioButton("OTRO");
//
//			bg.add(CABECERA);
//			bg.add(GINECOLOGO);
//			bg.add(OTRO);
//			
//			
//			
//			tipo.add(CABECERA);
//			tipo.add(GINECOLOGO);
//			tipo.add(OTRO);
//			
//			add(tipo);
//		
			

			add(new JLabel(" "));
			add(new JLabel(" "));
			add(new JLabel(" "));
			btnanadir = new JButton("Añadir ");
			add(btnanadir);
			
			
			
			
			
			btnanadir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog( contentPaneCitas, "Debes rellenar todos campos");
					if (!txtdni.getText().isEmpty() && !txtnombre.getText().isEmpty() && !txtapellido.getText().isEmpty()   ) {
						String dni = txtdni.getText();
						String nombre = txtnombre.getText();
						String apellido = txtapellido.getText();
						String fechayhora = sdf1.format(txtfechayhora.getText());
						TipoCita t = (TipoCita) ctipoCita.getSelectedItem();
						
						try {
						Connection con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						BaseDeDatos.initBD("BaseDeDatos");
						
						BaseDeDatos.anadirCita(con, dni,nombre, apellido,fechayhora, String.valueOf(ctipoCita)); //
						PanelCitas.eliminaTablaCita();
						PanelCitas.actualizarTablaCita();
						BaseDeDatos.closeBD(con);	
						}catch (Exception ex) {
							// TODO: handle exception
							
						}
						
						
					
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
	

