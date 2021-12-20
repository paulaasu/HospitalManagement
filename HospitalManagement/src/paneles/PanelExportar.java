package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import clases.BaseDeDatos;
import clases.Paciente;

public class PanelExportar extends JPanel  {
	private JButton btnAceptar;
	JPanel panelT;
	
	
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
		/*
		JFileChooser n=new JFileChooser();
		n.setPreferredSize(new Dimension(200, 200));
		panelT.add(n);
		*/
	
		/*
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Date fechai = null,fechaf=null;
				String resp = JOptionPane.showInputDialog( null, "Fecha inicial filtro:", null );
				String resp2 = JOptionPane.showInputDialog( null, "Fecha final filtro:", null );
				try {
					 fechai = (Date) formatof.parse( resp );
					 fechaf = (Date) formatof.parse( resp2 );
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					//System.out.println(fechai);
				ExportarDatos();
				//System.out.println("fgfdgdfg");
				
				
			}

			
		});
		*/
		
	}
	
	//MEJORAR--RELLENAR CON CONSULTA A BBDD
	private JComboBox AgregarCombo(JComboBox comboBox) {
		//lista de pacientes
		/*
		*COMENTADO DE MOMENTO BBDD NO FUNCIONA
		/*
		String tablas[]={"pacientes","medicos","planificacion"};
		ArrayList<Paciente> ret = new ArrayList<>();
		ret=BaseDeDatos.ObtenerPacientes();
		for(Paciente item:ret){
			comboBox.addItem(item.getNombre());			
		}
		*/
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
		
		
		JLabel lblNewLabel = new JLabel("Se exportar\u00E1 los pacientes con todos sus datos");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelPaciente.add(lblNewLabel);
		
		
		JButton btnBuscarFP = new JButton("EXPORTAR FICHERO");
		btnBuscarFP.setBackground(new Color(211, 211, 211));
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
		JLabel lbl1 = new JLabel("Se exportar el historial de un paciente.");
		lbl1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		panelHistorial.add(lbl1);
		
		/*
		 * FALTA COLOCAR TODOS LOS ATRIBUTOS DE LA TABLA
		 */
		JLabel lbl2 = new JLabel("\t(nombre_p,hiStorial...)  ");
		lbl2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelHistorial.add(lbl2);
		
		JLabel lblNombrePaciente = new JLabel("NOMBRE PACIENTE:");
		panelHistorial.add(lblNombrePaciente);
		
		JComboBox comboBox_1 = new JComboBox();
		panelHistorial.add(comboBox_1);
		comboBox_1.setPreferredSize(new Dimension(150,20));
		AgregarCombo(comboBox_1);
		JButton btnBuscarFH = new JButton("EXPORTAR FICHERO");
		btnBuscarFH.setBackground(new Color(211, 211, 211));
		btnBuscarFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarFichero();
			}

			
		});
		btnBuscarFH.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panelHistorial.add(btnBuscarFH);
		
		Panel panelAgenda = new Panel();
		tabbedPane.addTab("AGENDA", null, panelAgenda, null);
		panelAgenda.setBackground(new Color(240, 240, 240));
	}
	
	private void BuscarFichero() {
		JFileChooser j=new JFileChooser();
		int opcion=j.showOpenDialog(this);
		File fichero=j.getSelectedFile();
		
		/*-------------CODIGO---------------*/
		//CONTROLAR FICHEOR NO NULO...
	}

}

