/**
 * @author Jos� Mora
 */
package utilidades;

import java.awt.Font;

import javax.swing.JLabel;

public class Titulos extends JLabel {
	public Titulos(String texto, int x, int y, int width, int height) {
		this.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.setBounds(x, y, width, height);
		this.setText(texto);
	}
}
