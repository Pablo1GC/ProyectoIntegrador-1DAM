package ventanasEmergentes;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class CamposContrasenas extends JPasswordField {
	public CamposContrasenas(int x, int y, int width, int height) {
		this.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		this.setBounds(x, y, width, height);
		this.setColumns(10);
	}
}
