package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import clases.BaseDeDatos;
import clases.Cita;
import clases.HistorialClinico;
import clases.Paciente;



public class PanelExportar extends JPanel  {
	private JButton btnAceptar;
	JPanel panelT;
	JComboBox comboBox_1 ;
	
	public PanelExportar() {
		
		setLayout(new BorderLayout());
		setBackground(new Color(176, 196, 222));
		
		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		JLabel lblTitulo=new JLabel("EXPORTAR DATOS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBackground(new Color(176, 196, 222));
		
		add(lblTitulo,BorderLayout.NORTH);
		
		 panelT=new JPanel();
		 add(panelT,BorderLayout.CENTER);
		panelT.setLayout(new BorderLayout(0, 0));
			
		CrearPaneles();
		
	}
	
	//MEJORAR--RELLENAR CON CONSULTA A BBDD
	private JComboBox AgregarCombo(JComboBox comboBox) {
		ArrayList<String> dni =new ArrayList<>();
		Connection con = BaseDeDatos.initBD("BaseDeDatos.db",true);
		dni=BaseDeDatos.ObtenerPacientes(con);
		
	
		for(String item:dni){
			comboBox.addItem(item);			
		}
		
		return comboBox;
		
	}
	private void CrearPaneles() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 228, 181));
		panelT.add(tabbedPane, BorderLayout.CENTER);
		
		/*
		 * PESTAÑA PACIENTE IMPORTAR
		 * */
		JPanel panelPaciente = new JPanel();
		
		tabbedPane.addTab("PACIENTE", null, panelPaciente, null);
		panelPaciente.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JLabel lblNewLabel = new JLabel("Se exportan los pacientes con todos sus datos");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.add(lblNewLabel);
		
		
		JButton btnBuscarFP = new JButton("EXPORTAR FICHERO");
		btnBuscarFP.setBackground(new Color(211, 211, 211));
		btnBuscarFP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarFicheroCsv();
			}

			
		});
		btnBuscarFP.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelPaciente.add(btnBuscarFP);
		
		/*
		 * PESTAÑA HISTORIAL IMPORTAR
		 * */
		JPanel panelHistorial = new JPanel();
		tabbedPane.addTab("HISTORIAL", null, panelHistorial, null);
		JLabel lbl1 = new JLabel("Se exportan el historial de un paciente.");
		lbl1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		panelHistorial.add(lbl1);
		
		JLabel lblNombrePaciente = new JLabel(" DNI PACIENTE:");
		panelHistorial.add(lblNombrePaciente);
		
		 comboBox_1 = new JComboBox();
		panelHistorial.add(comboBox_1);
		comboBox_1.setPreferredSize(new Dimension(150,20));
		AgregarCombo(comboBox_1);
		JButton btnBuscarFH = new JButton("EXPORTAR FICHERO");
		btnBuscarFH.setBackground(new Color(211, 211, 211));
		btnBuscarFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarFicheroCsvH();
			}


			
		});
		btnBuscarFH.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelHistorial.add(btnBuscarFH);
		
		Panel panelCitas = new Panel();
		tabbedPane.addTab("CITAS", null, panelCitas, null);
		panelCitas.setBackground(new Color(240, 240, 240));
		
		JLabel lblNewLabel1 = new JLabel("Se exportan las citas con todos sus datos  ");
		lblNewLabel1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelCitas.add(lblNewLabel1);
		JLabel lblNewLabel2 = new JLabel("FECHA INICIO:");
		lblNewLabel2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelCitas.add(lblNewLabel2);
	//	DateChooserCombo dateChooserCombo1 = new DateChooserCombo();
	//	panelCitas.add(dateChooserCombo1);
		JLabel lblNewLabel3 = new JLabel("FECHA FIN:");
		lblNewLabel3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelCitas.add(lblNewLabel3);
		/*
		DateChooserPanel dateChooserPanel = new DateChooserPanel();
		dateChooserPanel.setBounds(12, 36, 250, 180);
		panelCitas.add(dateChooserPanel);
		*/
		
		//DateChooserCombo dateChooserCombo2 = new DateChooserCombo();
		//panelCitas.add(dateChooserCombo2);
		
		JButton btnBuscarFA = new JButton("EXPORTAR FICHERO");
		btnBuscarFA.setBackground(new Color(211, 211, 211));
		btnBuscarFA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MEJORA
				/*
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//fecha 1
				Calendar cal = dateChooserCombo1.getSelectedDate();
				String date1 = sdf.format(cal.getTime());
				//fecha 2
				Calendar cal1 = dateChooserCombo1.getSelectedDate();
				String date2 = sdf.format(cal.getTime());
				//System.out.println(date);
				 * 
				 */
			//System.out.println(f);
				ExportarFicheroCsvA();
			}

			
		});
		btnBuscarFA.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelCitas.add(btnBuscarFA);
		
	}
	
	/*
	 *  Metdodo exportar Citas de bbdd a fichero .csv
	 *  @throws SQLException
	 */
	private void ExportarFicheroCsvA() {
		// TODO Auto-generated method stub
		JFileChooser j = Explorador();
		ArrayList<Cita> ret = new ArrayList<>();
		Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
		ret=BaseDeDatos.ObtenerCitas(con);
		FileWriter fw;
		
		//************TERMINAR************
	}
	/*
	 *  Metdodo exportar Historial de bbdd a fichero .csv
	 *  @throws SQLException
	 */
	public void ExportarFicheroCsvH() {
		
		JFileChooser j = Explorador();
		
		String dni=(String) comboBox_1.getSelectedItem();
		System.out.println(dni);
		
		ArrayList<HistorialClinico> ret = new ArrayList<>();
		Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
		ret=BaseDeDatos.ObtenerHistorialDni(con,dni);
		FileWriter fw;
		
		try {
			fw=new FileWriter(j.getSelectedFile()+".csv");
			BufferedWriter bw=new BufferedWriter(fw);
			String cabecera="NUM HISTORIAL"+";"+"ENFERMEDAD"+";"+"SINTOMAS"+";"+"TIEMPO"+";"+"SED"+";"+"SUEÑO"+";"+"MICCION"+";"+"DNI PACIENTE"+"\n";
			bw.write(cabecera);
			for(HistorialClinico h:ret){
				String numHistorial = String.valueOf(h.getNumHistorial());
				String enfermedad=h.getEnfermedad();
				String sintomas= h.getSintomas();
				String tiempo=  h.getTiempo();
				String sed= h.getSed();
				String sueño= h.getSueño();
				String miccion= h.getMiccion();
				String dni_p= h.getDni();
				String t=numHistorial+";"+enfermedad+";"+sintomas+";"+tiempo+";"+sed+";"+sueño+";"+miccion+";"+dni_p+"\n";
				bw.write(t);
			}
			bw.flush();
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			JOptionPane.showOptionDialog(null, "SE HA PRODUCIDO UN ERROR AL EXPORTAR LO DATOS", null, 0, 0, null, null, e);
			e.printStackTrace();
		}
		
		//JOptionPane.("SE HA COMPLETADO CORRECTAMENTE");
		
	}
	
	
	/*
	 * Metdodo exportar paciente de bbdd a fichero .csv
	 *  @throws SQLException
	 */
	private void ExportarFicheroCsv() {
		//Abrir explorador de archivo
				JFileChooser j = Explorador();
				
				ArrayList<Paciente> ret = new ArrayList<>();
				Connection con = BaseDeDatos.initBD("BaseDeDatos.db");
				ret=BaseDeDatos.getPaciente(con);
				FileWriter fw;
				
				try {
					fw=new FileWriter(j.getSelectedFile()+".csv");
					BufferedWriter bw=new BufferedWriter(fw);
					String cabecera="DNI"+";"+"NOMBRE"+";"+"APELLIDOS"+";"+"TELEFONO"+";"+"DIRECCION"+";"+"FECHA"+";"+"GENERO"+"\n";
					bw.write(cabecera);
					for(Paciente p:ret){
						String dni = String.valueOf(p.getDni());
						String nombre=p.getNombre();
						String apellidos= p.getApellidos();
						String tel=  String.valueOf(p.getTelefono());
						String dir= p.getDireccion();
						String fecha= p.getFechaNac();
						String genero=p.getGenero();
						String t=dni+";"+nombre+";"+apellidos+";"+tel+";"+dir+";"+fecha+";"+genero+"\n";
						bw.write(t);
					}
					bw.flush();
					bw.close();
					fw.close();
					
				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					JOptionPane.showOptionDialog(null, "SE HA PRODUCIDO UN ERROR AL EXPORTAR LO DATOS", null, 0, 0, null, null, e);
					e.printStackTrace();
				}
				//JOptionPane.("SE HA COMPLETADO CORRECTAMENTE");
				
	}

	private JFileChooser Explorador() {
		JFileChooser j = new JFileChooser();
		j.setApproveButtonText("Guardar");
		j.showSaveDialog(null);
		return j;
	}
}

