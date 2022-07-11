package login;

import mvc.Controlador;
import mvc.Modelo;
import mvc.Vista;
import utilidades.Paginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Franco
 */
public class BarraLateralLogin extends JPanel implements Vista {
    protected static Modelo modelo;
    protected static Controlador controlador;
    protected static VentanaLogin frame;
    protected JLabel faq;
    protected JLabel lbl_Logo;
    protected VentanaLogin contenedor;

    public BarraLateralLogin() {
        this.setBackground(new Color(190, 190, 200));
        setLayout(null);
        this.setBounds(0, 0, 323, 720);
        lbl_Logo = new JLabel();
        lbl_Logo.setIcon(new ImageIcon(
                BarraLateralLogin.class.getResource("/img/02083_ue_centro_profesional_logo_rgb_positive.png")));
        lbl_Logo.setBounds(12, 12, 239, 70);

        add(lbl_Logo);
        faq = new JLabel("");
        faq.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        faq.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Paginas.FAQS_SIN_LOGUEADO.setControlador(controlador);
                contenedor.getControlador().cambiarContenidoLogin(Paginas.FAQS_SIN_LOGUEADO);
            }
        });
        faq.setIcon(new ImageIcon(BarraLateralLogin.class.getResource("/img/icons8-signo-de-interrogacion-15.png")));
        faq.setBounds(280, 35, 20, 20);
        add(faq);

    }

    public static void setFrame(VentanaLogin f) {
        frame = f;
    }

    /**
     * Este metodo conecta a la barra lateral con el JFrame que lo contiene (es como un setter)
     *
     * @param ventanaLogin es el JFrame que contiene al objeto que queremos conectar
     */
    public void conectarVentana(VentanaLogin ventanaLogin) {
        this.contenedor = ventanaLogin;
    }

    public VentanaLogin getContenedor() {
        return contenedor;
    }

    public void setContenedor(VentanaLogin contenedor) {
        this.contenedor = contenedor;
    }

    @Override
    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo m) {
        this.modelo = m;

    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador c) {
        this.controlador = c;
    }


}
