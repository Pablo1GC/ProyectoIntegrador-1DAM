package usuarioRegistrado;

/**
 * @author Brian Da Silva
 */

import utilidades.Boton;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MisClubs extends ContenidoPrincipal {
    private Boton btn_Clubs;
    private Boton btn_Proyectos;
    private Boton btn_CreaTuClub;

    private JScrollPane scrollPane;
    private JPanel jp_ContenedorGeneral;
    private ArrayList<TarjetaResumenNoticias> arrayClubs;

    public MisClubs() {
        super();
        arrayClubs = new ArrayList<>();

//		Este es el contenedor con la barra de deslizar.
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 921, 657);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        agregarContenidoGeneral();
        this.add(scrollPane);

    }

    public void reiniciarMisClubs() {
        for (TarjetaResumenNoticias tarjeta : arrayClubs) {
            jp_ContenedorGeneral.remove(tarjeta);
        }

        arrayClubs = new ArrayList<>();
    }

    public void crearClub(String nombreClub,
                          String titulo_noticia1, String textoNoticia1, String fecha1, String autor1,
                          String titulo_noticia2, String textoNoticia2, String fecha2, String autor2, int id_club) {
        int y;
        if (arrayClubs.size() > 0) {
            y = arrayClubs.get(arrayClubs.size() - 1).getY() + arrayClubs.get(arrayClubs.size() - 1).getHeight() + 20;
            int jp_AltoContenido = arrayClubs.get(arrayClubs.size() - 1).getY() + arrayClubs.get(arrayClubs.size() - 1).getHeight() + 370;
            jp_ContenedorGeneral.setPreferredSize(new Dimension(820, jp_AltoContenido));
        } else {
            y = 120;
        }

        TarjetaResumenNoticias test = new TarjetaResumenNoticias(y, nombreClub,
                titulo_noticia1, textoNoticia1, fecha1, autor1,
                titulo_noticia2, textoNoticia2, fecha2, autor2, id_club);
        jp_ContenedorGeneral.add(test);
        arrayClubs.add(test);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public void setCartasMisClubs(ArrayList<TarjetaResumenNoticias> array) {
        this.arrayClubs = array;
    }

    private void agregarContenidoGeneral() {
        jp_ContenedorGeneral = new JPanel();
        scrollPane.setViewportView(jp_ContenedorGeneral);
        jp_ContenedorGeneral.setLayout(null);
        jp_ContenedorGeneral.setBackground(Color.WHITE);
        jp_ContenedorGeneral.setBounds(0, 0, 1420, 1200);
        jp_ContenedorGeneral.setLayout(null);
        btn_Clubs = new Boton("Clubs");
        btn_Clubs.setBounds(99, 46, 129, 34);
        jp_ContenedorGeneral.add(btn_Clubs);

        btn_Proyectos = new Boton("Proyectos");
        btn_Proyectos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarContenido(Paginas.MIS_PROYECTOS);
            }
        });
        btn_Proyectos.setBackground(Color.GRAY);
        btn_Proyectos.setBounds(306, 46, 129, 34);
        jp_ContenedorGeneral.add(btn_Proyectos);

        btn_CreaTuClub = new Boton("Crea tu Club");
        btn_CreaTuClub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controlador.cambiarContenido(Paginas.FORMULARIO_CLUB);
            }
        });
        btn_CreaTuClub.setBounds(691, 46, 129, 34);
        jp_ContenedorGeneral.add(btn_CreaTuClub);


    }

}
