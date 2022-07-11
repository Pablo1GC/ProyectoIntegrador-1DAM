package login;

/**
 * @author Franco
 */

import faqs.Faqs;
import utilidades.Boton;
import utilidades.Paginas;
import ventanaPrincipal.BarraSuperiorApp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FaqSinRegistro extends Contenido {
    protected BarraSuperiorApp BarraRoja;
    private Faqs faq;
    private Boton btnRegresar;

    public FaqSinRegistro() {
        removeAll();
        BarraRoja = new BarraSuperiorApp();
        BarraRoja.setBounds(0, 0, 1190, 65);
        BarraRoja.setVisible(true);
        add(BarraRoja);
        this.setSize(1500, 850);
        this.setLocation(-300, 0);
        setBackground(new Color(190, 190, 190));
        faq = new Faqs();
        this.add(faq);

        btnRegresar = new Boton("Regresar");
        btnRegresar.setFont(new Font("Dialog", Font.ITALIC, 12));
        btnRegresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegresar.setBounds(68, 590, 99, 25);
        btnRegresar.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                Paginas.CONTENIDO_LOGIN.setBounds(0, 0, 920, 720);
                contenedor.getControlador().cambiarContenidoLogin(Paginas.CONTENIDO_LOGIN);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
        faq.add(btnRegresar);

    }

    /**
     * Este metodo conecta a la barra lateral con el JFrame que lo contiene (es como un setter)
     *
     * @param contenedor es el JFrame que contiene al objeto que queremos conectar
     */
    public void conectarVentana(VentanaLogin ventanaLogin) {
        this.contenedor = ventanaLogin;
    }
}
