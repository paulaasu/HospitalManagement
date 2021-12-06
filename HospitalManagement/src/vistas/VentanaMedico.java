package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import clases.Menu;
import clases.Menu2;
import clases.Menu3;
import paneles.PanelExportar;
import paneles.PanelImportar;

public class VentanaMedico extends JFrame  implements ActionListener {
	private JPanel contentPane;
	private Menu menu;
	private Menu2 mntmAgenda,mntmDatos;
	private Menu3 mntmExportar,mntmImportar;
	private JButton btnAceptar;
	private PanelExportar panelE;
	private PanelImportar panelI;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMedico frame = new VentanaMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public VentanaMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 374);
		
		menu = new Menu();
		setJMenuBar(menu);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);//ventana centrada
		
		
		
		mntmAgenda= new Menu2("Agenda");
		menu.add(mntmAgenda);
		mntmDatos= new Menu2("Datos");
		menu.add(mntmDatos);
		/*menu item*/
		mntmExportar=new Menu3("Expotar");
		mntmDatos.add(mntmExportar);
		mntmImportar=new Menu3("Importar");
		mntmDatos.add(mntmImportar);
		
		mntmExportar.addActionListener(this);
		panelI=new PanelImportar();
		
		//provisional para visualizar paneImportar
		panelI.setVisible(true);
		add(panelI);
	}

	/*------------NO FUNCIONA-------------------*/
	public void actionPerformed(ActionEvent e) {
		//No funciona dentro del action Performed en el constructo si
		if(e.getSource()== mntmExportar){
			panelE=new PanelExportar();
			
			panelE.setVisible(true);
			add(panelE);
	
			//VentanaMedico.this.dispose();
			//System.out.println("dfgfghfgh");
		}//fin if
		if(e.getSource()== mntmImportar){
			panelI=new PanelImportar();
			
			panelI.setVisible(true);
			add(panelI);
	
			//VentanaMedico.this.dispose();
			//System.out.println("dfgfghfgh");
		}//fin if
	}
	
	private JPanel titulo() {
		JPanel panelT=new JPanel();
		panelT.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelT.setBackground(new Color(0, 206, 209));
		JLabel lblTitulo=new JLabel("EXPORTAR DATOS");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelT.add(lblTitulo);
		return panelT;
		
	}
	
	
	
	
	
}
