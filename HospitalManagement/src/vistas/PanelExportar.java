package src.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelExportar extends JPanel  {
	private JButton btnAceptar;
	
	
	public PanelExportar() {
		
		setLayout(new BorderLayout());
		setBackground(new Color(0, 206, 209));
		
		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		JLabel lblTitulo=new JLabel("EXPORTAR DATOS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		//panel1.add(lblTitulo,FlowLayout.CENTER);
		add(lblTitulo,BorderLayout.NORTH);
		
		JPanel panelT=new JPanel();
		panelT.setLayout(new FlowLayout());
		
		JLabel jlabel1=new JLabel("Seleccione la tabla a expotar:");
		panelT.add(jlabel1);
		add(panelT,BorderLayout.CENTER);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(120, 29));
		
		comboBox=AgregarCombo(comboBox);
		
		panelT.add(comboBox);
		/*
		JFileChooser n=new JFileChooser();
		n.setPreferredSize(new Dimension(200, 200));
		panelT.add(n);
		*/
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnAceptar, BorderLayout.SOUTH);
		
		
	}
	
	//MEJORAR--RELLENAR CON CONSULTA A BBDD
	private JComboBox AgregarCombo(JComboBox comboBox) {
		//PARA U
		String tablas[]={"pacientes","medicos","planificacion"};
		
		for(String item:tablas){
			comboBox.addItem(item);			
		}
		return comboBox;
		
	}

}

