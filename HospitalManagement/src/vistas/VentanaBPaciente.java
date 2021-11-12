package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class VentanaBPaciente extends JFrame{
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBPaciente frame = new VentanaBPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaBPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblInsertePaciente = new JLabel("INSERTE PACIENTE");
		lblInsertePaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblInsertePaciente);
		
		
		/*panel inferior con titulo*/
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBackground(new Color(176, 196, 222));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnBuscar);
		
	}

}
