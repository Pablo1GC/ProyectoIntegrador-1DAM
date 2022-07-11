package utilidades;
/**
 * @author Franco
 */
import java.awt.Color;

import javax.swing.JTextField;

public class TextFieldsCustom extends JTextField {
	public TextFieldsCustom() {
		setBorder(null);
		setBackground(new Color(245, 245, 245));
		setSize(280, 30);
		setOpaque(true);
		setColumns(10);
	}

	public TextFieldsCustom(String texto) {
		this();
		setSize(280, 30);
		this.setText(texto);
	}
}
