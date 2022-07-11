package usuarioRegistrado;

/**
 * @author Brian Da Silva
 */

import mvc.Modelo;
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

public class SolicitudesPendientesClubs extends ContenidoPrincipal {
    private Boton btn_Clubs;
    private Boton btn_Proyectos;
    private JSeparator separator;
    private JScrollPane scrollPane;
    private JPanel jp_ContenedorGeneral;
    private int altoContenido;
    private ArrayList<CajaSolicitudes> arraySoliClub;

    private ContenidoPrincipal contenidoPrincipal;


    private JLabel lbl_SolicitudesPendientes;

    public SolicitudesPendientesClubs() {

        arraySoliClub = new ArrayList<>();
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

        btn_Clubs = new Boton("Hola");
        btn_Clubs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Clubs.setText("Clubs");
        btn_Clubs.setBounds(140, 74, 129, 34);
        jp_ContenedorGeneral.add(btn_Clubs);

        btn_Proyectos = new Boton("Proyectos");
        btn_Proyectos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controlador.cambiarContenido(Paginas.SOLICITUDES_PENDIENTES_PROYECTOS);
            }
        });
        btn_Proyectos.setBackground(Color.GRAY);
        btn_Proyectos.setBounds(327, 74, 129, 34);
        jp_ContenedorGeneral.add(btn_Proyectos);

        separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(126, 53, 411, 2);
        jp_ContenedorGeneral.add(separator);

        lbl_SolicitudesPendientes = new JLabel("Solicitudes pendientes de clubs");
        lbl_SolicitudesPendientes.setFont(new Font("Dialog", Font.BOLD, 20));
        lbl_SolicitudesPendientes.setBounds(126, 22, 367, 17);
        jp_ContenedorGeneral.add(lbl_SolicitudesPendientes);


    }

    public void crearSoliClub(String titulo, String descripcion, int cod_club, Modelo modelo) {
        int y;
        if (arraySoliClub.size() > 0) {
            y = arraySoliClub.get(arraySoliClub.size() - 1).getY() + arraySoliClub.get(arraySoliClub.size() - 1).getHeight() + 35;
            int jp_AltoContent = arraySoliClub.get(arraySoliClub.size() - 1).getY()
                    + arraySoliClub.get(arraySoliClub.size() - 1).getHeight() + 280;
            jp_ContenedorGeneral.setPreferredSize(new Dimension(820, jp_AltoContent));
        } else {
            y = 170;
        }
        CajaSolicitudes test = new CajaSolicitudes(y, titulo, descripcion, cod_club, controlador);
        jp_ContenedorGeneral.add(test);
        arraySoliClub.add(test);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public void reiniciarSolicitudes() {
        for (CajaSolicitudes caja : arraySoliClub) {
            jp_ContenedorGeneral.remove(caja);
        }

        arraySoliClub = new ArrayList<>();
    }

}

