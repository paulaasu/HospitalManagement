package paneles;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelInicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelInicio() {
		setLayout(new BorderLayout());
		JLabel lblTitulo=new JLabel("Bienvenido/a a Hospital Management", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		//panel1.add(lblTitulo,FlowLayout.CENTER);
		add(lblTitulo,BorderLayout.NORTH);

	}

}
