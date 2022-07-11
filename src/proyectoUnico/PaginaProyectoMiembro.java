/*
 * @Author:SamuelOrtega
 *
 */

package proyectoUnico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;


public class PaginaProyectoMiembro extends PaginaProyectos {
    protected JPanel jp_Noticias;
    protected JPanel jp_Contenedor_Iconos_Abajo, jp_BtnSolicitarUnirme;
    protected JScrollPane sp_Noticias;
    protected JLabel jp_ContenedorProyectos, lbl_txtCorreo, lblNewLabel_4_5, lbl_txtIntegrantes, lbl_iconoCorreo;
    protected NoticiaProyecto test;
    protected ArrayList<JPanel> arrayNoticias_Proyectos;

    public PaginaProyectoMiembro() {
        super();
        arrayNoticias_Proyectos = new ArrayList<JPanel>();
        jp_NombreProyecto.setBounds(208, 24, 500, 51);
        separator_principal.setBounds(47, 248, 827, 12);
        scrollPanePrincipalProyecto.setSize(921, 656);
        this.agregarScrollPaneDeNoticias();
    }

    /**Crea la noticia, la añade al array, la posiciona una debajo de otra y añade un separator después de cada una*/
    public void crearNoticia(String titulo, String club, String autor, String fecha, String contenido) {
        int y;
        if (arrayNoticias_Proyectos.size() > 0) {
            y = arrayNoticias_Proyectos.get(arrayNoticias_Proyectos.size() - 1).getY() + arrayNoticias_Proyectos.get(arrayNoticias_Proyectos.size() - 1).getHeight() + 15;
            int jp_AltoContent = arrayNoticias_Proyectos.get(arrayNoticias_Proyectos.size() - 1).getY()
                    + arrayNoticias_Proyectos.get(arrayNoticias_Proyectos.size() - 1).getHeight() + 85;
            jp_Noticias.setPreferredSize(new Dimension(820, jp_AltoContent));
        } else {
            y = 15;
        }
        test = new NoticiaProyecto(y, titulo, club, autor, fecha, contenido);
        arrayNoticias_Proyectos.add(test);
        jp_Noticias.add(test);
        int alt = test.getHeight() + test.getY() + 8;
        JSeparator separator_deNoticias = new JSeparator();
        separator_deNoticias.setBounds(10, alt, 754, 12);
        separator_deNoticias.setForeground(Color.DARK_GRAY);
        jp_Noticias.add(separator_deNoticias);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                sp_Noticias.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public void agregarScrollPaneDeNoticias() {
        sp_Noticias = new JScrollPane();
        sp_Noticias.setBounds(59, 279, 800, 260);
        sp_Noticias.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp_Noticias.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
        sp_Noticias.setAutoscrolls(true);
        jp_BackGroundProyecto.add(sp_Noticias);

        jp_Noticias = new JPanel();
        sp_Noticias.setViewportView(jp_Noticias);
        jp_Noticias.setPreferredSize(new Dimension(500, 255));
        jp_Noticias.setLayout(null);

        JLabel lbl_TituloClub_en_Noticias = new JLabel("Útimas Noticias");
        lbl_TituloClub_en_Noticias.setBounds(100, 250, 293, 25);
        lbl_TituloClub_en_Noticias.setFont(new Font("Roboto", Font.BOLD, 18));
        lbl_TituloClub_en_Noticias.setForeground(Color.RED);
        jp_BackGroundProyecto.add(lbl_TituloClub_en_Noticias);
        jp_Noticias.setLayout(null);
    }

    public void reiniciarNoticias() {
        arrayNoticias_Proyectos.clear();
        jp_Noticias.removeAll();
    }
}
