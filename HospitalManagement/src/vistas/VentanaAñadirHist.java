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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.BaseDeDatos;
import clases.HistorialClinico;
import paneles.PanelHistorial;
import paneles.PanelPacientes;



public class VentanaA�adirHist extends JFrame{ //QUEDAN LAS CONDICIONES
	private JPanel contentPane;
	private static JTextField txt_dni;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaA�adirHist frame = new VentanaA�adirHist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaA�adirHist() {
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
		
		JLabel lblInsertePaciente = new JLabel("INSERTE N�MERO DNI DEL PACIENTE");
		lblInsertePaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblInsertePaciente);
		
		
		/*panel inferior con titulo*/
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnA�adir = new JButton("A�ADIR HISTORIAL");
		btnA�adir.setBackground(new Color(176, 196, 222));
		btnA�adir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnA�adir.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnA�adir);
		
		btnA�adir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String dni = txt_dni.getText();
				 String enfermedad = PanelHistorial.devuelveEnfermedad().getText();
				 String sintomas = PanelHistorial.devuelveSintomas().getText();
				 String tiempo = PanelHistorial.devuelveTiempo().getText();
				 String sed = PanelHistorial.devuelveSed().getText();
				 String sue�o = PanelHistorial.devuelveSue�o().getText();
				 String miccion = PanelHistorial.devuelveMiccion().getText();
				 HistorialClinico historial = new HistorialClinico(1, enfermedad, sintomas, tiempo, sed, sue�o, miccion, dni);
				 
				 if (enfermedad!=null && sintomas!=null && tiempo!=null && sed!=null && sue�o!=null && miccion!=null) {
					 try {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						BaseDeDatos.insertarHistorial(BaseDeDatos.con, historial);						
						PanelHistorial.actualizarTablaHistorial();
							
						BaseDeDatos.closeBD();
							
							
						PanelHistorial.devuelveEnfermedad().setText("");
						PanelHistorial.devuelveSintomas().setText("");
						PanelHistorial.devuelveTiempo().setText("");
						PanelHistorial.devuelveSed().setText("");
						PanelHistorial.devuelveSue�o().setText("");
						PanelHistorial.devuelveMiccion().setText("");
						dispose();
							
//						}else {
//							JOptionPane.showMessageDialog(btnA�adir, "El dni insertado no existe");
//							
//						}
					} catch (Exception e2) {
						
					}
			
				 }else {
					 JOptionPane.showMessageDialog(btnA�adir, "Debes rellenar todos campos");
					
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