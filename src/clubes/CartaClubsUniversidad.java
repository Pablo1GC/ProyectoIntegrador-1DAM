/**
 * @author Jos� Mora
 */
package clubes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import clubUnico.PaginaClub_Abstract;
import clubUnico.PaginaClubClubDeAdministrador;
import mvc.Modelo;
import utilidades.Boton;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;

public class CartaClubsUniversidad extends JPanel {
    protected JTextArea txaDescripcion;
    protected JLabel lblImgFondo;
    protected JLabel lblTitulo;
    protected JPanel pnlCabecera;
    protected ContenidoPrincipal contenidoPrincipal;
    protected Boton verMas;
    protected int id_club;



    protected static Modelo.TipoDeUsuario tipoDeUsuario;
    protected static boolean esAdmin;

    public CartaClubsUniversidad(String titulo, String texto, InputStream imagen, int id_club) {
        this.id_club = id_club;
        this.setSize(235, 282);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBorder(new MatteBorder(0, 3, 2, 0, (Color) new Color(192, 192, 192)));

        //		Contenedor de imagen y título
        pnlCabecera = new JPanel();
        pnlCabecera.setBorder(new MatteBorder(0, 3, 0, 0, (Color) new Color(192, 192, 192)));
        pnlCabecera.setBackground(Color.GRAY);
        pnlCabecera.setBounds(0, 0, 235, 72);
        pnlCabecera.setLayout(null);
        this.add(pnlCabecera);

        //		Título (Ejemplo: Running Club)
        lblTitulo = new JLabel(titulo);
        lblTitulo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        lblTitulo.setBorder(null);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 0, 0, 170));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(26, 23, 190, 31);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCabecera.add(lblTitulo);

        //		Imagen
        lblImgFondo = new JLabel("");
        lblImgFondo.setHorizontalAlignment(SwingConstants.LEFT);

        try {
            lblImgFondo.setIcon(new ImageIcon(ImageIO.read(imagen)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        lblImgFondo.setBounds(-95, 0, 344, 96);
        pnlCabecera.add(lblImgFondo);

        //		Descripción
        txaDescripcion = new JTextArea();
        txaDescripcion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        txaDescripcion.setBackground(Color.WHITE);
        txaDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
        txaDescripcion.setEditable(false);
        txaDescripcion.setLineWrap(true);
        txaDescripcion.setWrapStyleWord(true);
        txaDescripcion.setText(texto);
        txaDescripcion.setBounds(24, 80, 201, 164);
        add(txaDescripcion);

        //		Botón
        verMas = new Boton("Únete", 66, 247, 103, 22);
        add(verMas);
    }

    public String getTitulo() {
        return lblTitulo.getText();
    }

    /**Imprime la página de un club dependiendo de si es miembro, administrador, solicitante o registrado*/
    public void setContenidoPrincipal(ContenidoPrincipal contenidoPrincipal) {
        this.contenidoPrincipal = contenidoPrincipal;
    }

    public void setListenerVerMas(){
        verMas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenidoPrincipal.getModelo().obtenerDatosClub(id_club);
                contenidoPrincipal.getModelo().obtenerTipoDeUsuarioClub(id_club);

                switch (tipoDeUsuario){
                    case MIEMBRO -> {
                        contenidoPrincipal.getControlador().cambiarContenido(Paginas.PAG_CLUB_ADMINISTRADOR);
                        contenidoPrincipal.getModelo().esAdminClub(id_club);
                        Paginas.PAG_CLUB_REGISTRADO_SOLICITA.ocultarBotonSolicitar();
                        if (esAdmin)
                            PaginaClubClubDeAdministrador.mostrarBotonCrear();
                    }
                    case OBSERVADOR, SOLICITA -> {
                        contenidoPrincipal.getControlador().cambiarContenido(Paginas.PAG_CLUB_REGISTRADO_SOLICITA);
                        Paginas.PAG_CLUB_REGISTRADO_SOLICITA.mostrarBotonSolicitar();
                    }
                }
                Paginas.PAG_CLUB_REGISTRADO_SOLICITA.scrollToTop();
                Paginas.PAG_CLUB_ADMINISTRADOR.scrollToTop();
                PaginaClub_Abstract.setIdClub(id_club);
            }
        });
    }

    public static void setTipoDeUsuario(Modelo.TipoDeUsuario Miembro) {
        CartaClubsUniversidad.tipoDeUsuario = Miembro;
    }

    public static void setEsAdmin(boolean Admin) {
        CartaClubsUniversidad.esAdmin = Admin;
    }


}
