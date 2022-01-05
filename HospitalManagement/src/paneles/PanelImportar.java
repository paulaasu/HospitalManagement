package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import clases.BaseDeDatos;
import clases.HistorialClinico;
import clases.Paciente;

public class PanelImportar extends JPanel{
	private JButton btnAceptar;
	private JPanel panelT;
	/**
	 * Create the panel.
	 */
	public PanelImportar() {
		

		setLayout(new BorderLayout());
		setBackground(new Color(176, 196, 222));
		
		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		JLabel lblTitulo=new JLabel("IMPORTAR DATOS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		
		
		JLabel lblNewLabel = new JLabel("El fichero SIN ENCABEZADO debe de estar compuesto por todos los atributos de paciente: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.add(lblNewLabel);
		//colocar todos los atributos que contine ela tabla paciente

		JLabel lblNewLabel1 = new JLabel("\t(dni,nombre,apellidos,telefono,direccion,fecha nacimiento,genero)");
		lblNewLabel1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.add(lblNewLabel1);
		
		JButton btnBuscarFP = new JButton("BUSCAR FICHERO");
		btnBuscarFP.setBackground(new Color(192, 192, 192));
		btnBuscarFP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportarFicheroCsvP();
				
			}

			

			
		});
		btnBuscarFP.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelPaciente.add(btnBuscarFP);
		
		/*
		 * PESTAÑA HISTORIAL IMPORTAR
		 * */
		JPanel panelHistorial = new JPanel();
		tabbedPane.addTab("HISTORIAL", null, panelHistorial, null);
		JLabel lbl1 = new JLabel("El fichero SIN ENCABEZADO debe de estar compuesto por todos los atributos de historial  ");
		lbl1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.setBackground(new Color(220, 220, 220));
		panelHistorial.add(lbl1);
		
		JLabel lbl2 = new JLabel("\t(Enfermedad,Sintoma,Tiempo,Sed,Sueño,Miccion,Dni_paciente)");
		lbl2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelHistorial.add(lbl2);
		
		JButton btnBuscarFH = new JButton("BUSCAR FICHERO");
		btnBuscarFH.setBackground(new Color(192, 192, 192));
		btnBuscarFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportarFicheroCsvH(); 
				
			}

			
		});
		btnBuscarFH.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelHistorial.add(btnBuscarFH);
	}
	private void ImportarFicheroCsvH() {
		File fichero=BuscarFichero();
		Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
		if(fichero!=null) {
			
			FileReader fr;
			BufferedReader bw;
			String [] lista = null;
			String [] campos = null;
			
			try {
				HistorialClinico h=new HistorialClinico();
	
				 bw =new BufferedReader(new FileReader(fichero));
		         String line;
		         while ((line = bw.readLine()) != null ){
		             campos = line.split(";");
		            h.enfermedad=campos[0];
		            h.sintomas=campos[1];
		            h.tiempo=campos[2];
		            h.sed=campos[3];
		            h.miccion=campos[4];
		            h.dni_p=campos[5];
		           
		            
		           
		            BaseDeDatos.insertarHistorial(con,h);
		          
		         }
		            
			
				bw.close();
				
				
				
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR ");
				e.printStackTrace();
			}
		}else {
		
			JOptionPane.showMessageDialog(null, "DEBES DE SELECCIONAR UN FICHERO VALIDO ");
		}
	}

	
	private void ImportarFicheroCsvP() {
		File fichero=BuscarFichero();
		Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
		if(fichero!=null) {
			
			FileReader fr;
			BufferedReader bw;
			String [] lista = null;
			String [] campos = null;
			
			try {
				Paciente p=new Paciente();
	
				
				 bw =new BufferedReader(new FileReader(fichero));
		         String line;
		         while ((line = bw.readLine()) != null ){
		             campos = line.split(";");
		            p.dni=campos[0];
		            p.nombre=campos[1];
		            p.apellidos=campos[2];
		            p.telefono=Integer.parseInt(campos[3]);
		            p.direccion=campos[4];
		            p.fechaNac=campos[5];
		            p.genero=campos[6];
		            
		           
		            BaseDeDatos.insertarPaciente(con,p);
		          
		         }
		            
			
				bw.close();
				
				
				
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR ");
				e.printStackTrace();
			}
		}else {
		
			JOptionPane.showMessageDialog(null, "DEBES DE SELECCIONAR UN FICHERO VALIDO ");
		}
	}
	
	private File BuscarFichero() {
		JFileChooser j=new JFileChooser();
		int opcion=j.showOpenDialog(this);
		File fichero=j.getSelectedFile();
		
		return fichero;
	}

}
