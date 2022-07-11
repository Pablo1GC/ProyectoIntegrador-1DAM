/**
 * @author Jos� Mora
 */
package utilidades;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CamposTexto extends JTextField {
	public CamposTexto(int x, int y, int width, int height) {
		this.setBounds(x, y, width, height);
		this.setColumns(10);
		this.setBorder(new LineBorder(Color.BLACK, 1, true));
	}
}
