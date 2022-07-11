package usuarioRegistrado;

/**
 * @author Brian Da Silva
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import ventanaPrincipal.ContenidoPrincipal;

public class ProyectosAbiertos extends ContenidoPrincipal {
    private JScrollPane scrollPane;
    private JPanel jp_ContenedorGeneral;
    private int altoContenido;

    private ArrayList<CartaProyectoAbierto> cartas;

    public ProyectosAbiertos() {
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
        jp_ContenedorGeneral.setBounds(0, 0, 1420, 1200);

        this.setBounds(270, 65, 920, 657);
        this.setBackground(Color.white);
        setLayout(null);

        JLabel lblPerfilDeUsuario = new JLabel("Proyectos Abiertos");
        lblPerfilDeUsuario.setFont(new Font("Dialog", Font.BOLD, 21));
        lblPerfilDeUsuario.setBounds(125, 28, 215, 76);
        jp_ContenedorGeneral.add(lblPerfilDeUsuario);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(125, 103, 605, 17);
        jp_ContenedorGeneral.add(separator);

        cartas = new ArrayList<CartaProyectoAbierto>();


    }

    public void agregarCarta(CartaProyectoAbierto carta) {
        cartas.add(carta);
        carta.setContenidoPrincipal(this);
        jp_ContenedorGeneral.add(carta);
        calcularTamanoScroll();

    }


    private void calcularTamanoScroll() {
        int posicionYUltimaCarta = cartas.get(cartas.size() - 1).getY();
        int heightUltimaCart = cartas.get(cartas.size() - 1).getHeight();

//		Con esto le dices que tanto quieres que te deje deslizar en X y Y.
        altoContenido = (posicionYUltimaCarta + heightUltimaCart) +90;
        jp_ContenedorGeneral.setPreferredSize(new Dimension(820, altoContenido));
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scrollPane.getVerticalScrollBar().setValue(0);
            }
        });

    }

}