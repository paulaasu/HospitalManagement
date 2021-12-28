package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.BaseDeDatos;
import paneles.PanelHistorial;
import paneles.PanelPacientes;



public class VentanaAñadirHist extends JFrame{ //QUEDAN LAS CONDICIONES
	private JPanel contentPane;
	private static JTextField txt_dni;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAñadirHist frame = new VentanaAñadirHist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaAñadirHist() {
		setBounds(100, 100, 450, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*panel central con casilla dni*/
		JPanel panel1=new JPanel();
		contentPane.add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDni = new JLabel("DNI: ");
		panel1.add(lblDni);
		
		txt_dni = new JTextField();
		txt_dni.setPreferredSize(new Dimension(43, 29));
		panel1.add(txt_dni);
		txt_dni.setColumns(10);
		
		/*panel superior con titulo*/
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 206, 209));
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblInsertePaciente = new JLabel("INSERTE NÚMERO DE HISTORIAL");
		lblInsertePaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblInsertePaciente);
		
		
		/*panel inferior con titulo*/
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAñadir = new JButton("AÑADIR HISTORIAL");
		btnAñadir.setBackground(new Color(176, 196, 222));
		btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAñadir.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnAñadir);
		
		btnAñadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int num = VentanaAñadirHist.devuelveDni();
				 String enfermedad = PanelHistorial.devuelveEnfermedad();
				 String sintomas = PanelHistorial.devuelveSintomas();
				 String tiempo = PanelHistorial.devuelveTiempo();
				 String sed = PanelHistorial.devuelveSed();
				 String sueño = PanelHistorial.devuelveSueño();
				 String miccion = PanelHistorial.devuelveMiccion();
				 
				try {
					BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
					BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
					BaseDeDatos.stmt.executeUpdate("insert into historial values("+num+", '"+enfermedad+"', '"+sintomas+"', '"+tiempo+"', '"+sed+"', '"+sueño+"', '"+miccion+"')");
					
					PanelHistorial.eliminaTablaHistorial();
					PanelHistorial.actualizaTablaHistorial();
					
					BaseDeDatos.closeBD();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				dispose();
			}
		});
		
		String DNI = txt_dni.getText();
		setVisible(false);

		
	}

	public static int devuelveDni(){
		int dni = Integer.parseInt(txt_dni.getText());
		return dni;
		// asi te devuelve lo que ha puesto en el texto
	}
}