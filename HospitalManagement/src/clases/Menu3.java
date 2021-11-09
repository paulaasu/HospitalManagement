package clases;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenuItem;

public class Menu3 extends JMenuItem{
	public Menu3(String nombre){
		setForeground(Color.BLACK);
		setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.setText(nombre);
	}

}
