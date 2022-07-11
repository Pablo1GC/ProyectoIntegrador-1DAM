package ventanasEmergentes;

import utilidades.Boton;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Publicacion_Exitosa extends La_Ventana_Emergente_Abstract {
	public Publicacion_Exitosa() {
		this.botonCerrar();
		this.anadirTexto();
		this.anadirIcono();
	}

	public void anadirIcono() {
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Publicacion_Exitosa.class.getResource("/img/checkpantall (2).png")));
		lblNewLabel_1.setBounds(201, 27, 94, 104);
		getContentPane().add(lblNewLabel_1);

	}

	public void anadirTexto() {

		JLabel lblNewLabel = new JLabel("\u00A1Publicaci\u00F3n exitosa!");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setBounds(146, 141, 197, 28);
		getContentPane().add(lblNewLabel);

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
