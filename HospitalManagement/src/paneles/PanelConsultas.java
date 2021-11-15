package paneles;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelConsultas extends JPanel {

	public PanelConsultas() {
		
		setLayout(new BorderLayout());
		JLabel lblTitulo=new JLabel("CONSULTAS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblTitulo,BorderLayout.NORTH);
	}

}
