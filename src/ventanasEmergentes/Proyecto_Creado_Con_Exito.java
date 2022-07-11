package ventanasEmergentes;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utilidades.Boton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Proyecto_Creado_Con_Exito extends La_Ventana_Emergente_Abstract {

	public Proyecto_Creado_Con_Exito() {
		this.botonCerrar();
		this.anadirTexto();
		this.anadirIcono();
	}

	public void anadirIcono() {

		JLabel lblNewLabel = new JLabel("Proyecto \ncreado ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setBounds(174, 156, 163, 28);
		getContentPane().add(lblNewLabel);

	}

	public void anadirTexto() {

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Proyecto_Creado_Con_Exito.class.getResource("/img/clipboard.png")));
		lblNewLabel_1.setBounds(182, 0, 171, 165);
		getContentPane().add(lblNewLabel_1);

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
