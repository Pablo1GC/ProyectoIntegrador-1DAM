/**
 * @author Jos� Mora
 */
package utilidades;

import java.awt.Color;

import javax.swing.JButton;

public class Boton extends JButton {
	public Boton(String texto, int x, int y, int width, int height) {
		this.setForeground(Color.WHITE);
		this.setBackground(new Color(255, 0, 51));
		this.setBounds(x, y, width, height);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setText(texto);
	}
	
	public Boton(String texto) {
		this.setForeground(Color.WHITE);
		this.setBackground(new Color(255, 0, 51));
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setText(texto);
	}
}
