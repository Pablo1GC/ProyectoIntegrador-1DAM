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
import java.util.ArrayList;

public class MisProyectos extends ContenidoPrincipal {

    private Boton btn_Clubs;
    private Boton btn_Proyectos;
    private JScrollPane scrollPane;
    private JPanel jp_ContenedorGeneral;
    private int altoContenido;
    private ArrayList<TarjetaResumenNoticias> arrayProyectos;

    public MisProyectos() {
        arrayProyectos = new ArrayList<>();
//		Este es el contenedor con la barra de deslizar.
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 921, 657);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

//		Este es el contenedor grande que se a�ade a la barra de deslizar.
        jp_ContenedorGeneral = new JPanel();
        scrollPane.setViewportView(jp_ContenedorGeneral);
        jp_ContenedorGeneral.setLayout(null);
        jp_ContenedorGeneral.setBackground(Color.WHITE);
        jp_ContenedorGeneral.setBounds(0, 0, 920, 670);
        this.setBackground(Color.WHITE);
        setLayout(null);
        btn_Clubs = new Boton("Clubs");
        btn_Clubs.setBackground(Color.GRAY);
        btn_Clubs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarContenido(Paginas.MIS_CLUBS);
            }
        });
        btn_Clubs.setBounds(99, 46, 129, 34);
        jp_ContenedorGeneral.add(btn_Clubs);

        btn_Proyectos = new Boton("Proyectos");
        btn_Proyectos.setBounds(306, 46, 129, 34);
        jp_ContenedorGeneral.add(btn_Proyectos);
    }

    /**Elimina todos los proyectos del arraylist de mis proyectos*/
    public void reiniciarMisProyectos() {
        for (TarjetaResumenNoticias tarjeta : arrayProyectos) {
            jp_ContenedorGeneral.remove(tarjeta);
        }

        arrayProyectos = new ArrayList<>();

    }

    /**
     * Create the jp_.
     */

    public void crearProyecto(int idProyecto, String nombreProyecto,
                              String titulo_noticia1, String textoNoticia1, String fecha1, String autor1,
                              String titulo_noticia2, String textoNoticia2, String fecha2, String autor2, String club) {
        int y;
        if (arrayProyectos.size() > 0) {
            y = arrayProyectos.get(arrayProyectos.size() - 1).getY() + arrayProyectos.get(arrayProyectos.size() - 1).getHeight() + 20;
            int jp_AltoContenido = arrayProyectos.get(arrayProyectos.size() - 1).getY() + arrayProyectos.get(arrayProyectos.size() - 1).getHeight() + 370;
            jp_ContenedorGeneral.setPreferredSize(new Dimension(820, jp_AltoContenido));
        } else {
            y = 120;
        }

        TarjetaResumenNoticias test = new TarjetaResumenNoticias(y, idProyecto,
                nombreProyecto,
                titulo_noticia1, textoNoticia1, fecha1, autor1,
                titulo_noticia2, textoNoticia2, fecha2, autor2, club);
        jp_ContenedorGeneral.add(test);
        arrayProyectos.add(test);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }
}
