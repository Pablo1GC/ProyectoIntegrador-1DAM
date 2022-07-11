package utilidades;
/*
 * @Author:SamuelOrtega
 * 
 */

import java.awt.Color;

import javax.swing.JSeparator;

public class SeparatorOficial extends JSeparator {
	
	public SeparatorOficial (){
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(190, 106, 474, 14);
	}

}
