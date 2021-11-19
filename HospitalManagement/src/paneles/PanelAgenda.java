package paneles;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelAgenda extends JPanel {

	public PanelAgenda() {
		setLayout(new BorderLayout());
		JLabel lblTitulo=new JLabel("AGENDA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		//panel1.add(lblTitulo,FlowLayout.CENTER);
		add(lblTitulo,BorderLayout.NORTH);
		
	}

}
