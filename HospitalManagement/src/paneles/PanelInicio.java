package paneles;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInicio extends JPanel {

	/**
	 * Create the panel.
	 */
	
	//Como ponemos la foto, el t?tulo no es necesario.
	public PanelInicio() {
//		setLayout(new BorderLayout());
//		JLabel lblTitulo=new JLabel("Bienvenido/a a Hospital Management", SwingConstants.CENTER);
//		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
//		//panel1.add(lblTitulo,FlowLayout.CENTER);
//		add(lblTitulo,BorderLayout.NORTH);
		
		setLayout(new BorderLayout());
		setBackground(new Color(91, 135, 175));
		ImageIcon img = new ImageIcon("src/img/DeustoHospital.png");
		JLabel lbl_imagen = new JLabel(img);
		//lbl_imagen.setSize(1900, 1500);;
	
		add(lbl_imagen,BorderLayout.CENTER);
	}
	
}


