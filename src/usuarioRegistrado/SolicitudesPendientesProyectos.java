package usuarioRegistrado;

/**
 * @author Brian Da Silva
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import mvc.Modelo;
import utilidades.Boton;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;

public class SolicitudesPendientesProyectos extends ContenidoPrincipal {
    private Boton btn_Clubs;
    private Boton btn_Proyectos;
    private JLabel lbl_SolicitudesPendientes;
    private JScrollPane scrollPane;
    private JPanel jp_ContenedorGeneral;
    private JSeparator separator;
    protected ArrayList<CajaSolicitudes> arraySoliPro;

    public void reiniciarSolicitudes() {
        for (CajaSolicitudes caja : arraySoliPro) {
            jp_ContenedorGeneral.remove(caja);
        }

        arraySoliPro = new ArrayList<>();
    }

    public void crearSoli(String titulo, String descripcion, String clubRealizador, int codigo, Modelo modelo) {
        int y;
        if (arraySoliPro.size() > 0) {
            y = arraySoliPro.get(arraySoliPro.size() - 1).getY() + arraySoliPro.get(arraySoliPro.size() - 1).getHeight() + 35;
            int jp_AltoContent = arraySoliPro.get(arraySoliPro.size() - 1).getY()
                    + arraySoliPro.get(arraySoliPro.size() - 1).getHeight() + 270;
            jp_ContenedorGeneral.setPreferredSize(new Dimension(820, jp_AltoContent));
        } else {
            y = 170;
        }
        CajaSolicitudes test = new CajaSolicitudes(y, titulo, descripcion, clubRealizador, codigo,  controlador);
        jp_ContenedorGeneral.add(test);
        arraySoliPro.add(test);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public SolicitudesPendientesProyectos() {
        arraySoliPro = new ArrayList<>();
        this.setBounds(270, 65, 1520, 657);
        this.setBackground(Color.WHITE);
        setLayout(null);
//		Este es el contenedor con la barra de deslizar.
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 920, 657);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

//		Este es el contenedor grande que se a�ade a la barra de deslizar. 
        jp_ContenedorGeneral = new JPanel();
        scrollPane.setViewportView(jp_ContenedorGeneral);
        jp_ContenedorGeneral.setLayout(null);
        jp_ContenedorGeneral.setBackground(Color.WHITE);
        jp_ContenedorGeneral.setBounds(0, 0, 1420, 1200);

        separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(126, 53, 458, 2);
        jp_ContenedorGeneral.add(separator);

//		Con esto le dices que tanto quieres que te deje deslizar en X y Y.
        jp_ContenedorGeneral.setPreferredSize(new Dimension(820, 640));

        btn_Clubs = new Boton("Clubs");
        btn_Clubs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controlador.cambiarContenido(Paginas.SOLICITUDES_PENDIENTES_CLUBS);
            }
        });
        btn_Clubs.setBackground(Color.GRAY);
        btn_Clubs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Clubs.setBounds(140, 74, 129, 34);
        jp_ContenedorGeneral.add(btn_Clubs);

        btn_Proyectos = new Boton("Proyectos");
        btn_Proyectos.setBounds(327, 74, 129, 34);
        jp_ContenedorGeneral.add(btn_Proyectos);
        lbl_SolicitudesPendientes = new JLabel("Solicitudes pendientes de proyectos");
        lbl_SolicitudesPendientes.setFont(new Font("Dialog", Font.BOLD, 20));
        lbl_SolicitudesPendientes.setBounds(126, 22, 458, 17);
        jp_ContenedorGeneral.add(lbl_SolicitudesPendientes);

    }

}
