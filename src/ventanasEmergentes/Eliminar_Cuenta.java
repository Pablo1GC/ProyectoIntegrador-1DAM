package ventanasEmergentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import usuarioRegistrado.PerfilUsuario;
import utilidades.Boton;

import javax.swing.JTextPane;

public class Eliminar_Cuenta extends La_Ventana_Emergente_Abstract {
PerfilUsuario contenedor;
	public Eliminar_Cuenta(PerfilUsuario contenedor) {
		this.contenedor = contenedor;
		this.agregarBotones();
		this.anadirIcono();
		this.anadirTexto();
	}

	public void agregarBotones() {
		Boton Cancelar = new Boton("Cancelar", 182, 202, 133, 28);
		Cancelar.setBackground(Color.GRAY);
		Cancelar.setLocation(87, 202);
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(Cancelar);

		Boton Eliminar = new Boton("Eliminar", 182, 202, 133, 28);
		Eliminar.setLocation(278, 202);
		Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Eliminar.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				contenedor.getControlador().eliminarCuenta();
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
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
			txtpnestSeguroQue.setText("¿Está seguro que desea eliminar \r\n  su cuenta permanentemente?");
			txtpnestSeguroQue.setBounds(91, 115, 332, 67);
			getContentPane().add(txtpnestSeguroQue);
		}

	}

}
