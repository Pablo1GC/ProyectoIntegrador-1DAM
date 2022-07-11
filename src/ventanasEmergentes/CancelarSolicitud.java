package ventanasEmergentes;
/**
 * @author Franco
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import mvc.Controlador;
import utilidades.Boton;
import utilidades.Paginas;

public class CancelarSolicitud extends La_Ventana_Emergente_Abstract {
	Controlador controlador;
	int codigo;
	boolean esClub;

	public CancelarSolicitud(int codigo, Controlador controlador, boolean esClub) {
		this.codigo = codigo;
		this.controlador = controlador;
		this.esClub = esClub;
		this.agregarBotones();
		this.anadirIcono();
		this.anadirTexto();
	}

	public void agregarBotones() {
		Boton Cancelar = new Boton("Cancelar", 182, 202, 133, 28);
		Cancelar.setBackground(Color.DARK_GRAY);
		Cancelar.setLocation(87, 202);
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(Cancelar);

		Boton Eliminar = new Boton("Eliminar", 182, 202, 133, 28);
		Eliminar.setText("Aceptar");
		Eliminar.setLocation(278, 202);
		if (esClub){

			Eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Debido a cómo funciona el query de solicitudes pendientes,
					// Abandonar club = eliminar solicitud pendiente
					controlador.abandonarClub(codigo);
					Paginas.SOLICITUDES_PENDIENTES_CLUBS.reiniciarSolicitudes();

					controlador.getSolicitudesPendientesClubs();

					dispose();

				}
			});
		} else {
			Eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Debido a cómo funciona el query de solicitudes pendientes,
					// Abandonar club = eliminar solicitud pendiente
					controlador.abandonarProyecto(codigo);
					Paginas.SOLICITUDES_PENDIENTES_PROYECTOS.reiniciarSolicitudes();
					controlador.getSolicitudesPendientesProyecto();

					dispose();

				}
			});
		}
		getContentPane().add(Eliminar);

	}

	public void anadirIcono() {

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Eliminar_Cuenta.class.getResource("/img/alerta.png")));
		lblNewLabel_1.setBounds(197, 11, 94, 104);
		getContentPane().add(lblNewLabel_1);
	}

	public void anadirTexto() {
		{
			JTextPane txtpnestSeguroQue = new JTextPane();
			txtpnestSeguroQue.setFont(new Font("Dialog", Font.BOLD, 19));
			txtpnestSeguroQue.setEditable(false);
			txtpnestSeguroQue.setText("¿Está seguro que desea cancelar \n 	    su solicitud?");
			txtpnestSeguroQue.setBounds(91, 115, 332, 67);
			getContentPane().add(txtpnestSeguroQue);
		}

	}
}
