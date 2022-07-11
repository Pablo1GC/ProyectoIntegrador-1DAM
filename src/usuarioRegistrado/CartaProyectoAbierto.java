package usuarioRegistrado;

/**
 * @author Brian Da Silva
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import mvc.Modelo;
import proyectoUnico.PaginaProyectoRegistradoSolicita;
import proyectoUnico.PaginaProyectos;
import utilidades.Boton;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;

import java.awt.SystemColor;

public class CartaProyectoAbierto extends JPanel {

    private JLabel lbl_Titulo;
    private JTextArea textDescripcion;
    private Boton btn_VerProyecto;
    private JSeparator separator;
    private JLabel lbl_ImagenPersonas;
    private JLabel lbl_NombreDeClub;
    protected ContenidoPrincipal contenidoPrincipal;
    private int idProyecto;
    protected static Modelo.TipoDeUsuario tipoDeUsuario;

    private final static int posX = 200;
    private final static int posY = 151 - 200;
    private final static int diferencia = 200;

    private static int numeroDeCartas = 0;

    public CartaProyectoAbierto(int idProyecto, String titulo, String club, String descripcion) {
        this.idProyecto = idProyecto;
        numeroDeCartas++;

        this.setBounds(posX, calcularPosicionY(), 449, 160);
        this.setLayout(null);

        lbl_Titulo = new JLabel(titulo);
        lbl_Titulo.setForeground(Color.RED);
        lbl_Titulo.setFont(new Font("Dialog", Font.BOLD, 14));
        lbl_Titulo.setBounds(12, 12, 230, 17);
        this.add(lbl_Titulo);

        textDescripcion = new JTextArea(descripcion);
        textDescripcion.setBounds(12, 33, 425, 78);
        textDescripcion.setWrapStyleWord(true);
        textDescripcion.setLineWrap(true);
        textDescripcion.setOpaque(false);
        textDescripcion.setEditable(false);
        textDescripcion.setFocusable(false);
        this.add(textDescripcion);

        separator = new JSeparator();
        separator.setBackground(SystemColor.controlHighlight);
        separator.setForeground(Color.BLACK);
        separator.setBounds(46, 117, 361, 2);
        this.add(separator);

        lbl_ImagenPersonas = new JLabel();
        lbl_ImagenPersonas.setBounds(12, 110, 60, 50);
        lbl_ImagenPersonas.setIcon(new ImageIcon(ProyectosAbiertos.class.getResource("/img/icons8-conferencia-de-primer-plano-seleccionado-50.png")));
        this.add(lbl_ImagenPersonas);

        lbl_NombreDeClub = new JLabel(club);
        lbl_NombreDeClub.setBounds(84, 131, 150, 17);
        this.add(lbl_NombreDeClub);


        btn_VerProyecto = new Boton("Ver Proyecto");
        btn_VerProyecto.setBounds(302, 126, 123, 27);
        this.add(btn_VerProyecto);

    }

    public int calcularPosicionY() {
        return posY + (diferencia * numeroDeCartas);
    }

    public void setContenidoPrincipal(ContenidoPrincipal contenidoPrincipal) {
        this.contenidoPrincipal = contenidoPrincipal;
        btn_VerProyecto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenidoPrincipal.getControlador().cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
                PaginaProyectoRegistradoSolicita proyecto = new PaginaProyectoRegistradoSolicita();
                proyecto.imprProyecto(idProyecto);
                contenidoPrincipal.getModelo().obtenerTipoDeUsuarioProyecto(idProyecto);

                switch (tipoDeUsuario){
                    case MIEMBRO -> {
                        proyecto.imprProyectoRegistrado(idProyecto);
                        contenidoPrincipal.getControlador().cambiarContenido(Paginas.PAGINA_PROYECTO_MIEMBRO);
                    }
                    case OBSERVADOR -> {
                        proyecto.imprProyectoRegistrado(idProyecto);
                        contenidoPrincipal.getControlador().cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.mostrarBotonSolicitar();
                    }
                    case SOLICITA -> {
                        contenidoPrincipal.getControlador().cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.ocultarBotonSolicitar();
                    }
                }

                PaginaProyectos.setIdProyecto(idProyecto);

            }
        });
    }

    public static void setTipoDeUsuario(Modelo.TipoDeUsuario Miembro) {
        tipoDeUsuario = Miembro;
    }


}
