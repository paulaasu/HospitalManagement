package clases;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenu;

public class Menu2  extends JMenu{
	public Menu2(String nombre){
		setForeground(Color.BLACK);
		setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.setText(nombre);
	}
}
