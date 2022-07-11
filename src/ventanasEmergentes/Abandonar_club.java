/*
 * @author: SamuelOrtega
 * */
package ventanasEmergentes;

import usuarioRegistrado.TarjetaResumenNoticias;
import utilidades.Boton;
import utilidades.Paginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Abandonar_club extends La_Ventana_Emergente_Abstract {
    private TarjetaResumenNoticias contenedor;

    public Abandonar_club(TarjetaResumenNoticias contenedor) {
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

        Boton Eliminar = new Boton("Eliminar", 266, 202, 133, 28);
        Eliminar.setText("Abandonar");
        Eliminar.setLocation(278, 202);
        Eliminar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.getControlador().abandonarClub(contenedor.getId_club());
                Paginas.MIS_CLUBS.reiniciarMisClubs();
                Paginas.MIS_CLUBS.getControlador().cambiarContenido(Paginas.MIS_CLUBS);
                contenedor.getControlador().obtenerResumenMisClubs();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(Eliminar);

    }

    public void anadirIcono() {

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Abandonar_club.class.getResource("/img/alerta.png")));
        lblNewLabel_1.setBounds(197, 11, 94, 104);
        getContentPane().add(lblNewLabel_1);
    }

    public void anadirTexto() {
        {
            JTextPane txtpnestSeguroQue = new JTextPane();
            txtpnestSeguroQue.setFont(new Font("Dialog", Font.BOLD, 19));
            txtpnestSeguroQue.setEditable(false);
            txtpnestSeguroQue.setText("¿Está seguro que desea abandonar\n       el club permanentemente?");
            txtpnestSeguroQue.setBounds(91, 115, 346, 67);
            getContentPane().add(txtpnestSeguroQue);
        }

    }

}
