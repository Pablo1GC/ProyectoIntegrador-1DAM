package usuarioRegistrado;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import mvc.Controlador;
import mvc.Modelo;
import utilidades.Boton;
import ventanasEmergentes.CancelarSolicitud;

public class CajaSolicitudes extends JPanel {
    final int separacion_ideal = 200;
    private JPanel solicitudes;
    private Boton btn_CancelarSolicitud;
    private JLabel lbl_MaratonDeVerano, lbl_NewLabel;


    private int codigo;

    private JTextArea txt_EsteVerano;

    private CancelarSolicitud cancelar;
    boolean esSolicitudDeClub;

    private Controlador controlador;

    //  Crear solicitudes de un club
    public CajaSolicitudes(int y, String titulo, String descripcion, int codigo, Controlador controlador) {
        esSolicitudDeClub = true;
        this.controlador = controlador;
        solicitudes = new JPanel();
        this.setBounds(199, y, 449, 192);
        this.setLayout(null);
        add(solicitudes);

        lbl_MaratonDeVerano = new JLabel(titulo);
        lbl_MaratonDeVerano.setForeground(Color.RED);
        lbl_MaratonDeVerano.setFont(new Font("Dialog", Font.BOLD, 14));
        lbl_MaratonDeVerano.setBounds(12, 12, 326, 17);
        this.add(lbl_MaratonDeVerano);

        txt_EsteVerano = new JTextArea(descripcion);
        txt_EsteVerano.setBounds(12, 56, 425, 85);
        txt_EsteVerano.setWrapStyleWord(true);
        txt_EsteVerano.setLineWrap(true);
        txt_EsteVerano.setOpaque(false);
        txt_EsteVerano.setEditable(false);
        txt_EsteVerano.setFocusable(false);
        this.add(txt_EsteVerano);

        btn_CancelarSolicitud = new Boton("Cancelar Solicitud");
        btn_CancelarSolicitud.setBounds(295, 153, 142, 27);
        this.add(btn_CancelarSolicitud);
        btn_CancelarSolicitud.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelar = new CancelarSolicitud(codigo, controlador, esSolicitudDeClub);
                cancelar.setVisible(true);
            }
        });
    }

    //  Crear solicitud de proyecto
    public CajaSolicitudes(int y, String titulo, String descripcion, String clubRealizador, int codigo, Controlador controlador) {
        this(y, titulo, descripcion, codigo, controlador);

        esSolicitudDeClub = false;

        //  Sobrescribir el listener
        btn_CancelarSolicitud.removeActionListener(btn_CancelarSolicitud.getActionListeners()[0]);

        btn_CancelarSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar = new CancelarSolicitud(codigo, controlador, esSolicitudDeClub);
                cancelar.setVisible(true);
            }
        });

        lbl_NewLabel = new JLabel(clubRealizador);
        lbl_NewLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
        lbl_NewLabel.setForeground(Color.RED);
        lbl_NewLabel.setBounds(12, 33, 496, 17);
        this.add(lbl_NewLabel);
    }


}
