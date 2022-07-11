/**
 * @author Jos� Mora
 */
package utilidades;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class Opciones extends JComboBox {
	public Opciones(int x, int y, int width, int height) {
		this.setBackground(Color.WHITE);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setOpaque(false);
		this.setBounds(x, y, width, height);
		this.setBorder(new LineBorder(Color.BLACK, 1, true));
	}
}
