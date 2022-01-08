package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.BaseDeDatos;
import clases.HistorialClinico;
import clases.Paciente;
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
		
		JLabel lblInsertePaciente = new JLabel("INSERTE NÚMERO DNI DEL PACIENTE");
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
				 String dni = txt_dni.getText();
				 String enfermedad = PanelHistorial.devuelveEnfermedad().getText();
				 String sintomas = PanelHistorial.devuelveSintomas().getText();
				 String tiempo = PanelHistorial.devuelveTiempo().getText();
				 String sed = PanelHistorial.devuelveSed().getText();
				 String sueño = PanelHistorial.devuelveSueño().getText();
				 String miccion = PanelHistorial.devuelveMiccion().getText();
				 
				 
				 if (!enfermedad.isEmpty() && !sintomas.isEmpty() && !tiempo.isEmpty() && !sed.isEmpty() && !sueño.isEmpty() && !miccion.isEmpty()) {	
					 try {
						Connection con = null;
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						ArrayList<Paciente> pacientesTot = BaseDeDatos.pacientesTotales();
						String sentSQL2= "Select * from paciente where Dni='"+ dni+"'";
						
						//para aumentar el nª historial cada vez que se añade un paciente 
						String sentSQL = "SELECT ID_historial, Enfermedad, Sintoma, Tiempo, Sed, Sueño, Miccion, max(ID_historial) FROM historial";
						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL);
						int numH=1;
						while(BaseDeDatos.rs.next()) {
							numH = BaseDeDatos.rs.getInt(8) + 1;
						}
						HistorialClinico historial = new HistorialClinico(numH, enfermedad, sintomas, tiempo, sed, sueño, miccion, dni);
						BaseDeDatos.anadirHistorial(BaseDeDatos.con, historial);						
						PanelHistorial.actualizarTablaHistorial();
							
						BaseDeDatos.closeBD(con);
							
							
						PanelHistorial.devuelveEnfermedad().setText("");
						PanelHistorial.devuelveSintomas().setText("");
						PanelHistorial.devuelveTiempo().setText("");
						PanelHistorial.devuelveSed().setText("");
						PanelHistorial.devuelveSueño().setText("");
						PanelHistorial.devuelveMiccion().setText("");
						dispose();
							
					} catch (Exception e2) {
						e2.getStackTrace();
					}
			
				 }else {
					 JOptionPane.showMessageDialog(btnAñadir, "Debes rellenar todos campos");
					
				 }
				
			}
		});
		
		String DNI = txt_dni.getText();
		setVisible(false);

		
	}

	public static String devuelveDni(){
		return txt_dni.getText();
	
		// asi te devuelve lo que ha puesto en el texto
	}
}