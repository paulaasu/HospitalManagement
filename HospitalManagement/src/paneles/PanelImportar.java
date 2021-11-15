package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class PanelImportar extends JPanel{
	private JButton btnAceptar;
	private JPanel panelT;
	/**
	 * Create the panel.
	 */
	public PanelImportar() {
		

		setLayout(new BorderLayout());
		setBackground(new Color(0, 206, 209));
		
		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		JLabel lblTitulo=new JLabel("IMPORTAR DATOS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		//panel1.add(lblTitulo,FlowLayout.CENTER);
		add(lblTitulo,BorderLayout.NORTH);
		
		 panelT=new JPanel();
		add(panelT,BorderLayout.CENTER);
		
		
		
		
		panelT.setLayout(new BorderLayout(0, 0));
		
		CrearPaneles();
		
		
		/*
		JFileChooser n=new JFileChooser();
		n.setPreferredSize(new Dimension(200, 200));
		panelT.add(n);
		*/
		btnAceptar = new JButton("ACEPTAR");
		
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAceptar.getBackground();
		add(btnAceptar, BorderLayout.SOUTH);

	}
	private void CrearPaneles() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 228, 181));
		panelT.add(tabbedPane, BorderLayout.CENTER);
		
		/*
		 * PESTAÑA PACIENTE IMPORTAR
		 * */
		JPanel panelPaciente = new JPanel();
		panelPaciente.setBackground(new Color(220, 220, 220));
		tabbedPane.addTab("PACIENTE", null, panelPaciente, null);
		panelPaciente.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JLabel lblNewLabel = new JLabel("El fichero debe de estar compuesto por todos los atributos de paciente ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.add(lblNewLabel);
		//colocar todos los atributos que contine ela tabla paciente
		JLabel lblNewLabel1 = new JLabel("\t(nombre,dni,fecha_nacimiento,domicilio,ciudad,provincia)");
		lblNewLabel1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.add(lblNewLabel1);
		
		JButton btnBuscarFP = new JButton("BUSCAR FICHERO");
		btnBuscarFP.setBackground(new Color(192, 192, 192));
		btnBuscarFP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarFichero();
			}

			
		});
		btnBuscarFP.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelPaciente.add(btnBuscarFP);
		
		/*
		 * PESTAÑA HISTORIAL IMPORTAR
		 * */
		JPanel panelHistorial = new JPanel();
		tabbedPane.addTab("HISTORIAL", null, panelHistorial, null);
		JLabel lbl1 = new JLabel("El fichero debe de estar compuesto por todos los atributos de historial ");
		lbl1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.setBackground(new Color(220, 220, 220));
		panelHistorial.add(lbl1);
		
		//colocar todos los atributos que contine ela tabla historial
		JLabel lbl2 = new JLabel("\t(nombre_p,hitorial...)");
		lbl2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelHistorial.add(lbl2);
		
		JButton btnBuscarFH = new JButton("BUSCAR FICHERO");
		btnBuscarFH.setBackground(new Color(192, 192, 192));
		btnBuscarFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarFichero();
			}

			
		});
		btnBuscarFH.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelHistorial.add(btnBuscarFH);
	}
	private void BuscarFichero() {
		JFileChooser j=new JFileChooser();
		int opcion=j.showOpenDialog(this);
		File fichero=j.getSelectedFile();
		
		/*-------------CODIGO---------------*/
		//CONTROLAR FICHEOR NO NULO...
	}

}
