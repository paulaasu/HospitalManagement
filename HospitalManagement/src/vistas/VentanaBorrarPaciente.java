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
import paneles.PanelHistorial;
import paneles.PanelPacientes;




public class VentanaBorrarPaciente extends JFrame{
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBorrarPaciente frame = new VentanaBorrarPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaBorrarPaciente() {
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
		
		JTextField txt_dni = new JTextField();
		txt_dni.setPreferredSize(new Dimension(43, 29));
		panel1.add(txt_dni);
		txt_dni.setColumns(10);
		
		/*panel superior con titulo*/
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 206, 209));
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblInsertePaciente = new JLabel("INSERTE DNI PACIENTE");
		lblInsertePaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblInsertePaciente);
		
		
		/*panel inferior con titulo*/
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBuscar = new JButton("BORRAR");
		btnBuscar.setBackground(new Color(176, 196, 222));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = txt_dni.getText();
				try {
					if(dni.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "Tiene que insertar un dni");
					}else {
						BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
						String sentSQL2 = "SELECT * FROM paciente WHERE dni = '" + dni + "' ";
						BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
						BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL2);
						if(BaseDeDatos.rs.next()) {
							String borrarPaciente = "DELETE FROM paciente WHERE dni = '" + dni + "' ";
						//	BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
		//For INSERT, UPDATE or DELETE use the executeUpdate() method and for SELECT use the executeQuery() method which returns the ResultSet.
							int filasEliminadas = BaseDeDatos.stmt.executeUpdate(borrarPaciente); // con esto se borra al paciente de la bd
							PanelPacientes.eliminaTablaPaciente();
							PanelPacientes.actualizaTablaPaciente();
							BaseDeDatos.closeBD();
							
							BaseDeDatos.con = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
							String sentSQL3 = "SELECT * FROM historial WHERE dni = '" + dni + "' ";
							BaseDeDatos.stmt = BaseDeDatos.con.createStatement();
							BaseDeDatos.rs = BaseDeDatos.stmt.executeQuery(sentSQL3);
							if(BaseDeDatos.rs.next()) {
								String borrarHistorial = "DELETE FROM historial WHERE dni = '" + dni + "' ";
								int filasEliminadas2 = BaseDeDatos.stmt.executeUpdate(borrarHistorial); 
								PanelHistorial.actualizarTablaHistorial();
							}
							BaseDeDatos.closeBD();
						} else {
							JOptionPane.showMessageDialog(contentPane, "El dni insertado no existe");		
						}
						
						
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
				
				
				
			}
		});
		
		
	}

}
