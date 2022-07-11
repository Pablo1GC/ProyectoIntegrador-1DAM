package ventanasEmergentes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import utilidades.Boton;

public class ProyectoUnido extends La_Ventana_Emergente_Abstract {
	private Boton cerrar;

	public ProyectoUnido() {
		cerrar = new Boton("Cerrar", 50, 59, 133, 28);
		cerrar.setLocation(182, 202);
		getContentPane().add(cerrar);

		JLabel lblNewLabel = new JLabel("¡Solicitud enviada!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setBounds(0, 145, 486, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ProyectoUnido.class.getResource("/img/sentmail.png")));
		lblNewLabel_1.setBounds(169, 25, 138, 137);
		getContentPane().add(lblNewLabel_1);
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
