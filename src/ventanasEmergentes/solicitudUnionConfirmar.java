package ventanasEmergentes;
/**
 * @author Franco
 */

import herramientasAdministrador.AdministrarSolicitudes;
import utilidades.Boton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class solicitudUnionConfirmar extends La_Ventana_Emergente_Abstract {
    private AdministrarSolicitudes administrarSolicitudes;

    public solicitudUnionConfirmar(AdministrarSolicitudes administrarSolicitudes) {
        this.administrarSolicitudes = administrarSolicitudes;
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

        Boton btn_aceptar = new Boton("Aceptar", 182, 202, 133, 28);
        btn_aceptar.setLocation(278, 202);
        btn_aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                administrarSolicitudes.confirmAceptarMiembro();
                dispose();
            }
        });
        getContentPane().add(btn_aceptar);

    }

    public void anadirIcono() {

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Eliminar_Cuenta.class.getResource("/img/alerta.png")));
        lblNewLabel_1.setBounds(197, 11, 94, 104);
        getContentPane().add(lblNewLabel_1);
    }

    public void anadirTexto() {

        JTextPane txtpnestSeguroQue = new JTextPane();
        txtpnestSeguroQue.setFont(new Font("Dialog", Font.BOLD, 19));
        txtpnestSeguroQue.setEditable(false);
        txtpnestSeguroQue.setText("¿Está seguro que desea aceptar las solicitudes seleccionadas?");
        txtpnestSeguroQue.setBounds(91, 115, 332, 67);
        getContentPane().add(txtpnestSeguroQue);
    }

}
