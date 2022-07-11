package ventanasEmergentes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utilidades.Boton;

public class ClaveCambiada extends La_Ventana_Emergente_Abstract {
	public ClaveCambiada() {
		
			this.agregarBotones();
			this.anadirIcono();
			this.anadirTexto();
		}

		public void agregarBotones() {

			Boton Eliminar = new Boton("Aceptar", 182, 200, 133, 28);
			Eliminar.setLocation(187, 196);
			Eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			getContentPane().add(Eliminar);

		}

		public void anadirIcono() {

			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(ClaveCambiada.class.getResource("/img/pulgares.png")));
			lblNewLabel_1.setBounds(187, 11, 143, 121);
			getContentPane().add(lblNewLabel_1);
		}

		public void anadirTexto() {

			JLabel lblNewLabel = new JLabel("Contraseña cambiada");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
			lblNewLabel.setBounds(151, 142, 213, 28);
			getContentPane().add(lblNewLabel);

		}


}


