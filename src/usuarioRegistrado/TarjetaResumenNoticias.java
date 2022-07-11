package usuarioRegistrado;

import clubUnico.PaginaClub_Abstract;
import clubUnico.PaginaClubClubDeAdministrador;
import mvc.Modelo;
import proyectoUnico.PaginaProyectos;
import utilidades.Boton;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.Abandonar_club;
import ventanasEmergentes.Abandonar_proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TarjetaResumenNoticias extends ContenidoPrincipal {

    private static int y = 30;
    private JPanel miClub;
    private Boton btn_AbandonarClub;
    private Boton btn_VerClub;
    private JLabel lblUltiNoti;
    private JPanel pnlNoticiero;
    private JLabel lbl_titulo1, lblClub;
    private JTextArea txtTexto1;
    private JLabel lbl_autor1;
    private JLabel lblFecha1;
    private JSeparator separator;
    private JLabel lblTitulo2;
    private JTextArea txtTexto2;
    private JLabel lblAutor2;
    private JLabel lblFecha2;
    private JLabel lbl_NewLabel;
    private Abandonar_club abandonar_club;
    private Abandonar_proyecto abandonar_proyecto;
    private int id_club;
    private int idProyecto;
    private MisProyectos proyectos;
    private MisClubs clubs;
    protected static boolean esAdmin;

    private static Modelo.TipoDeUsuario tipoDeUsuario;

    /** Crea la tarjeta resumen de un club */

    public TarjetaResumenNoticias(int y, String nombreClub, String titulo_noticia1, String textoNoticia1, String fecha1, String autor1, String titulo_noticia2, String textoNoticia2, String fecha2, String autor2, int id_club) {
        this.id_club = id_club;
        miClub = new JPanel();
        this.setBounds(99, y, 721, 275);
        this.setLayout(null);
        add(miClub);
        btn_AbandonarClub = new Boton("Abandonar Club");
        btn_AbandonarClub.setBounds(565, 12, 142, 27);
        Abandonar_club abandonar = new Abandonar_club(this);
        btn_AbandonarClub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                abandonar.setVisible(true);
            }
        });
        this.add(btn_AbandonarClub);

        btn_VerClub = new Boton("Ver Club");
        btn_VerClub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                getModelo().obtenerDatosClub(id_club);
                controlador.cambiarContenido(Paginas.PAG_CLUB_ADMINISTRADOR);
                getModelo().esAdminClub(id_club);

                if (esAdmin) {
                    PaginaClubClubDeAdministrador.mostrarBotonCrear();
                    Paginas.PAG_CLUB_ADMINISTRADOR.scrollToTop();
                } else
                    Paginas.PAG_CLUB_ADMINISTRADOR.scrollToTop();

                PaginaClub_Abstract.setIdClub(id_club);
            }
        });
        btn_VerClub.setBounds(296, 238, 142, 27);
        this.add(btn_VerClub);

        lblUltiNoti = new JLabel(nombreClub + " -  Últimas Noticias");
        lblUltiNoti.setForeground(Color.RED);
        lblUltiNoti.setFont(new Font("Dialog", Font.BOLD, 18));
        lblUltiNoti.setBounds(12, 17, 535, 22);
        this.add(lblUltiNoti);

        pnlNoticiero = new JPanel();
        pnlNoticiero.setBounds(40, 46, 669, 180);
        pnlNoticiero.setLayout(null);
        this.add(pnlNoticiero);

        lbl_titulo1 = new JLabel(titulo_noticia1);
        lbl_titulo1.setFont(new Font("Dialog", Font.BOLD, 14));
        lbl_titulo1.setBounds(12, 12, 343, 17);
        pnlNoticiero.add(lbl_titulo1);

        txtTexto1 = new JTextArea(textoNoticia1);
        txtTexto1.setWrapStyleWord(true);
        txtTexto1.setLineWrap(true);
        txtTexto1.setOpaque(false);
        txtTexto1.setEditable(false);
        txtTexto1.setFocusable(false);
        txtTexto1.setBounds(12, 41, 645, 47);
        pnlNoticiero.add(txtTexto1);

        lbl_autor1 = new JLabel(autor1);
        lbl_autor1.setBounds(416, 12, 96, 17);
        pnlNoticiero.add(lbl_autor1);

        lblFecha1 = new JLabel(fecha1);
        lblFecha1.setBounds(569, 12, 88, 17);
        pnlNoticiero.add(lblFecha1);

        separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(22, 85, 615, 2);
        pnlNoticiero.add(separator);

        lblTitulo2 = new JLabel(titulo_noticia2);
        lblTitulo2.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTitulo2.setBounds(12, 99, 343, 17);
        pnlNoticiero.add(lblTitulo2);

        txtTexto2 = new JTextArea(textoNoticia2);
        txtTexto2.setWrapStyleWord(true);
        txtTexto2.setOpaque(false);
        txtTexto2.setLineWrap(true);
        txtTexto2.setFocusable(false);
        txtTexto2.setEditable(false);
        txtTexto2.setBounds(12, 128, 645, 47);
        pnlNoticiero.add(txtTexto2);

        lblAutor2 = new JLabel(autor2);
        lblAutor2.setBounds(416, 99, 96, 17);
        pnlNoticiero.add(lblAutor2);

        lblFecha2 = new JLabel(fecha2);
        lblFecha2.setBounds(569, 99, 88, 17);
        pnlNoticiero.add(lblFecha2);

        y += 80;
    }

   /** Crea la tarjeta resumen de un proyecto (necesario indicar el club al que pertenece)*/
    public TarjetaResumenNoticias(int y, int idProyecto, String nombreProyecto, String titulo_noticia1, String textoNoticia1, String fecha1, String autor1, String titulo_noticia2, String textoNoticia2, String fecha2, String autor2, String club) {
        this.proyectos = proyectos;
        this.idProyecto = idProyecto;
        miClub = new JPanel();
        this.setBounds(99, y, 721, 275);
        this.setLayout(null);
        add(miClub);
        btn_AbandonarClub = new Boton("Abandonar Proyecto");
        btn_AbandonarClub.setBounds(545, 12, 160, 27);
        Abandonar_proyecto abandonar = new Abandonar_proyecto(this);
        btn_AbandonarClub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abandonar.setVisible(true);
            }
        });
        this.add(btn_AbandonarClub);

        btn_VerClub = new Boton("Ver Proyecto");
        btn_VerClub.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                getModelo().obtenerDatosProyecto(idProyecto);
                controlador.cambiarContenido(Paginas.PAGINA_PROYECTO_MIEMBRO);
                PaginaProyectos.setIdProyecto(idProyecto);
            }
        });
        btn_VerClub.setBounds(296, 238, 172, 27);
        this.add(btn_VerClub);

        lblUltiNoti = new JLabel(nombreProyecto + " -  Últimas Noticias");
        lblUltiNoti.setForeground(Color.RED);
        lblUltiNoti.setFont(new Font("Dialog", Font.BOLD, 18));
        lblUltiNoti.setBounds(12, 17, 535, 22);
        this.add(lblUltiNoti);

        lblClub = new JLabel(club);
        lblClub.setForeground(Color.RED);
        lblClub.setFont(new Font("Dialog", Font.ITALIC, 18));
        lblClub.setBounds(525, 238, 300, 22);
        this.add(lblClub);

        pnlNoticiero = new JPanel();
        pnlNoticiero.setBounds(40, 46, 669, 180);
        pnlNoticiero.setLayout(null);
        this.add(pnlNoticiero);

        lbl_titulo1 = new JLabel(titulo_noticia1);
        lbl_titulo1.setFont(new Font("Dialog", Font.BOLD, 14));
        lbl_titulo1.setBounds(12, 12, 343, 17);
        pnlNoticiero.add(lbl_titulo1);

        txtTexto1 = new JTextArea(textoNoticia1);
        txtTexto1.setWrapStyleWord(true);
        txtTexto1.setLineWrap(true);
        txtTexto1.setOpaque(false);
        txtTexto1.setEditable(false);
        txtTexto1.setFocusable(false);
        txtTexto1.setBounds(12, 41, 645, 47);
        pnlNoticiero.add(txtTexto1);

        lbl_autor1 = new JLabel(autor1);
        lbl_autor1.setBounds(416, 12, 96, 17);
        pnlNoticiero.add(lbl_autor1);

        lblFecha1 = new JLabel(fecha1);
        lblFecha1.setBounds(569, 12, 88, 17);
        pnlNoticiero.add(lblFecha1);

        separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(22, 85, 615, 2);
        pnlNoticiero.add(separator);

        lblTitulo2 = new JLabel(titulo_noticia2);
        lblTitulo2.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTitulo2.setBounds(12, 99, 343, 17);
        pnlNoticiero.add(lblTitulo2);

        txtTexto2 = new JTextArea(textoNoticia2);
        txtTexto2.setWrapStyleWord(true);
        txtTexto2.setOpaque(false);
        txtTexto2.setLineWrap(true);
        txtTexto2.setFocusable(false);
        txtTexto2.setEditable(false);
        txtTexto2.setBounds(12, 128, 645, 47);
        pnlNoticiero.add(txtTexto2);

        lblAutor2 = new JLabel(autor2);
        lblAutor2.setBounds(416, 99, 96, 17);
        pnlNoticiero.add(lblAutor2);

        lblFecha2 = new JLabel(fecha2);
        lblFecha2.setBounds(569, 99, 88, 17);
        pnlNoticiero.add(lblFecha2);
    }

    public static void setEsAdmin(boolean Admin) {
        esAdmin = Admin;
    }

    public int getId_club() {
        return id_club;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public static void setTipoDeUsuario(Modelo.TipoDeUsuario tipo) {
        tipoDeUsuario = tipo;
    }
}


/**
 * @wbp.parser.constructor
 */

