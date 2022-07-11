/**
 * @author Jos� Mora
 */
package utilidades;

import java.awt.Color;

import javax.swing.JPanel;

public class Lineas extends JPanel {
	public Lineas(int x, int y, int largo, int grosor) {
		this.setBackground(Color.BLACK);
		this.setBounds(x, y, largo, grosor);
	}
}
