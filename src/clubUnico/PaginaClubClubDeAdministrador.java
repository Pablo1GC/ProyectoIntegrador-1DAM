/*
 * @Author:SamuelOrtega
 *
 */
package clubUnico;

import utilidades.Paginas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PaginaClubClubDeAdministrador extends PaginaClub_Abstract {

    protected JScrollPane sp_Noticias;
    protected static JPanel jp_ContenedorCrearProyecto;
    protected JSeparator separator_deNoticias_Y_Proyectos;
    protected JPanel jp_ContenedorProyectos;
    protected JScrollPane sp_Proyectos;
    protected ArrayList<JPanel> arrayProyectos_Club;

    protected CajaNoticiasClub cajaNoticiasClub;
    protected CajaProyectosClub proyecto;
    protected JPanel jp_Noticias;
    protected static JButton btn_CrearProyecto;

    public PaginaClubClubDeAdministrador() {
        super();
        arrayNoticias = new ArrayList<JPanel>();
        arrayProyectos_Club = new ArrayList<JPanel>();
        separator_principal.setBounds(47, 255, 827, 12);
        scrollPanePrincipal.setSize(921, 656);
        jp_BackGround.setPreferredSize(new Dimension(656, 1050));
        sp_Noticias = new JScrollPane();
        jp_Noticias = new JPanel();
        this.agregarTitulosCajas();
        this.agregarSeparadorNoticiasYProyectos();
        this.agregarBtnCrearProyecto();
        this.agregarContenedorProyectos();
        this.agregarScrollPaneDeNoticias();
        scrollToTop();
    }


    /**
     * Borra todo del array de noticias del club anterior
     */
    public void reiniciarNoticias() {
        arrayNoticias.clear();
        jp_Noticias.removeAll();
        jp_Noticias.setBounds(10, 0, 0, 0);
    }

    /**
     * Borra todo del array de proyectos del club anterior
     */

    public void reiniciarProyectos() {
        arrayProyectos_Club.clear();
        jp_ContenedorProyectos.removeAll();
        jp_ContenedorProyectos.setBounds(10, 0, 0, 0);
    }

    public void crearNoticia(String titulo, String noticia, String autor, String fecha) {
        int y;
        if (arrayNoticias.size() > 0) {
            y = arrayNoticias.get(arrayNoticias.size() - 1).getY() + arrayNoticias.get(arrayNoticias.size() - 1).getHeight() + 20;
            int jp_AltoContent = arrayNoticias.get(arrayNoticias.size() - 1).getY() + arrayNoticias.get(arrayNoticias.size() - 1).getHeight() + 85;
            jp_Noticias.setPreferredSize(new Dimension(820, jp_AltoContent));
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    sp_Noticias.getVerticalScrollBar().setValue(0);
                }
            });
        } else {
            y = 15;
        }
        cajaNoticiasClub = new CajaNoticiasClub(y, titulo, noticia, autor, fecha);
        arrayNoticias.add(cajaNoticiasClub);
        jp_Noticias.add(cajaNoticiasClub);
        int alt = cajaNoticiasClub.getHeight() + cajaNoticiasClub.getY() + 8;
        JSeparator separador = new JSeparator();
        separador.setBounds(10, alt, 754, 2);
        separador.setForeground(Color.BLACK);
        jp_Noticias.add(separador);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                sp_Noticias.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public void crearProyecto(String titulo, String tema, int codProyecto) {
        int y;
        if (arrayProyectos_Club.size() > 0) {
            y = arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getY() + arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getHeight() + 20;
            int jp_AltoContent = arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getY() + arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getHeight() + 85;
            jp_ContenedorProyectos.setPreferredSize(new Dimension(820, jp_AltoContent));
        } else {
            y = 20;
        }
        proyecto = new CajaProyectosClub(y, titulo, tema, codProyecto);
        arrayProyectos_Club.add(proyecto);
        jp_ContenedorProyectos.add(proyecto);
        int alt = proyecto.getHeight() + proyecto.getY() + 8;

        JSeparator separador = new JSeparator();
        separador.setBounds(10, alt, 754, 2);
        separador.setForeground(Color.BLACK);
        jp_ContenedorProyectos.add(separador);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                sp_Proyectos.getVerticalScrollBar().setValue(0);
            }
        });

    }

    public void agregarTitulosCajas() {

        JLabel lbl_TituloClub_en_Noticias = new JLabel("Últimas Noticias");
        lbl_TituloClub_en_Noticias.setBounds(100, 260, 293, 25);
        lbl_TituloClub_en_Noticias.setFont(new Font("Roboto", Font.BOLD, 18));
        lbl_TituloClub_en_Noticias.setForeground(Color.RED);
        jp_BackGround.add(lbl_TituloClub_en_Noticias);


        JLabel lbl_TituloClub_en_Proyectos = new JLabel("Proyectos");
        lbl_TituloClub_en_Proyectos.setBounds(100, 605, 293, 25);
        lbl_TituloClub_en_Proyectos.setFont(new Font("Roboto", Font.BOLD, 18));
        lbl_TituloClub_en_Proyectos.setForeground(Color.RED);
        jp_BackGround.add(lbl_TituloClub_en_Proyectos);

    }

    public void agregarScrollPaneDeNoticias() {


        sp_Noticias.setBounds(57, 288, 800, 282);
        sp_Noticias.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp_Noticias.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
        sp_Noticias.setAutoscrolls(true);
        jp_BackGround.add(sp_Noticias);


        sp_Noticias.setViewportView(jp_Noticias);
        jp_Noticias.setPreferredSize(new Dimension(500, 270));
        jp_Noticias.setLayout(null);

    }

    public void agregarSeparadorNoticiasYProyectos() {
        separator_deNoticias_Y_Proyectos = new JSeparator();
        separator_deNoticias_Y_Proyectos.setForeground(Color.RED);
        separator_deNoticias_Y_Proyectos.setBounds(47, 601, 827, 12);
        jp_BackGround.add(separator_deNoticias_Y_Proyectos);
    }

    public void agregarBtnCrearProyecto() {
        jp_ContenedorCrearProyecto = new JPanel();
        jp_ContenedorCrearProyecto.setBounds(753, 11, 121, 28);
        jp_ContenedorCrearProyecto.setBackground(Color.RED);
        jp_ContenedorCrearProyecto.setBorder(new LineBorder(new Color(255, 0, 0), 0));
        jp_BackGround.add(jp_ContenedorCrearProyecto);
        jp_ContenedorCrearProyecto.setLayout(null);

        btn_CrearProyecto = new JButton("Crear proyecto");
        btn_CrearProyecto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlador.cambiarContenido(Paginas.FORMULARIO_PROYECTO);

            }
        });
        btn_CrearProyecto.setBounds(10, 0, 101, 24);
        btn_CrearProyecto.setBorder(null);
        jp_ContenedorCrearProyecto.add(btn_CrearProyecto);
        btn_CrearProyecto.setFont(new Font("Roboto", Font.BOLD, 11));
        btn_CrearProyecto.setForeground(Color.WHITE);
        btn_CrearProyecto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_CrearProyecto.setBackground(Color.RED);
    }

    public void agregarContenedorProyectos() {

        sp_Proyectos = new JScrollPane();
        sp_Proyectos.setBounds(58, 635, 800, 282);
        jp_BackGround.add(sp_Proyectos);
        sp_Proyectos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp_Proyectos.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
        sp_Proyectos.setAutoscrolls(true);

        jp_ContenedorProyectos = new JPanel();
        jp_ContenedorProyectos.setLayout(null);
        jp_ContenedorProyectos.setPreferredSize(new Dimension(500, 279));
        sp_Proyectos.setViewportView(jp_ContenedorProyectos);

    }

    /**
     * Si no eres admin, oculta el botón de crear proyecto
     */
    public static void quitarBotonCrear() {
        jp_ContenedorCrearProyecto.setVisible(false);
        btn_CrearProyecto.setVisible(false);
    }

    /**
     * Si  eres admin, muestra el botón de crear proyecto
     */

    public static void mostrarBotonCrear() {
        jp_ContenedorCrearProyecto.setVisible(true);
        btn_CrearProyecto.setVisible(true);
    }


}
