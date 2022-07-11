package ventanasEmergentes;

import utilidades.Boton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClubCreado extends La_Ventana_Emergente_Abstract {
	private Boton cerrar;

	public ClubCreado() {
		cerrar = new Boton("Cerrar", 50, 59, 133, 28);
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
			}
		});
		cerrar.setLocation(182, 202);
		getContentPane().add(cerrar);
		
		JLabel lblNewLabel = new JLabel("¡Solicitud enviada!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setBounds(0, 145, 486, 28);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ClubCreado.class.getResource("/img/pulgares.png")));
		lblNewLabel_1.setBounds(182, -3, 131, 137);
		getContentPane().add(lblNewLabel_1);
	}

}
