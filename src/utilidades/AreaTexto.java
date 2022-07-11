/**
 * @author Jos� Mora
 */
package utilidades;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class AreaTexto extends JTextArea {
	public AreaTexto(int x, int y, int width, int height) {
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setBounds(x, y, width, height);
		this.setBorder(new LineBorder(Color.BLACK, 1, true));
	}
}
