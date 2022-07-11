package ventanasEmergentes;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utilidades.Boton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;

public class Gracias_por_Contactar extends La_Ventana_Emergente_Abstract {
	
	public Gracias_por_Contactar() {
		this.botonCerrar();
		this.anadirTexto();
		this.anadirIcono();
	}

	public void anadirIcono() {

		JLabel lblNewLabel = new JLabel("¡Gracias por contactarnos! ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setBounds(124, 117, 263, 28);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Gracias_por_Contactar.class.getResource("/img/avion.png")));
		lblNewLabel_1.setBounds(167, 11, 193, 124);
		getContentPane().add(lblNewLabel_1);
		
		JTextPane txtpnUnAdministradorSe = new JTextPane();
		txtpnUnAdministradorSe.setEditable(false);
		txtpnUnAdministradorSe.setForeground(Color.GRAY);
		txtpnUnAdministradorSe.setText("Un administrador se pondrá  en contacto contigo");
		txtpnUnAdministradorSe.setBounds(167, 146, 159, 37);
		getContentPane().add(txtpnUnAdministradorSe);

	}

	public void anadirTexto() {

	}

	public void botonCerrar() {
		Boton cerrar = new Boton("Cerrar", 182, 202, 133, 28);
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(cerrar);

	}
}
