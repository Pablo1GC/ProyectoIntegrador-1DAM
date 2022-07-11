/**
 * @author José Mora
 *
 * Se utiliza la clase ContenedorPreguntas y se colocan
 * con el espacio necesario entre cada una, dándoles
 * la funcionalidad de abrir y cerrar.
 *
 * @see faqs.ContenedorPreguntas
 */
package faqs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import utilidades.Boton;
import utilidades.Lineas;
import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.FormularioAyuda;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Faqs extends ContenidoPrincipal {

    private final JPanel jp_Panel;
    private final JScrollPane scrScrollPane;
    private final Lineas pnlLinea;
    private final ArrayList<ContenedorPreguntas> arrayPaneles;
    private final Boton btn_Contacto;
    private final JLabel lbl_Ayuda;

    public void accionarBoton(int i) {
        //  Si el botón está abierto se cierra y viceversa
        if (arrayPaneles.get(i).estado % 2 == 0) {
            abrir(i);
        } else {
            cerrar(i);
        }
        arrayPaneles.get(i).estado++;
    }

    public void abrir(int num) {
        jp_Panel.setPreferredSize(new Dimension(700, (int) jp_Panel.getSize().getHeight() + 30));
        ContenedorPreguntas contenedor = arrayPaneles.get(num);

        contenedor.setSize(contenedor.getWidth(), contenedor.getHeight() + 30);
        contenedor.txt_Respuesta.setVisible(true);
        contenedor.btn_Desplegable.setIcon(new ImageIcon(Faqs.class.getResource("/img/flecha-hacia-arriba.npg")));

        for (int i = num + 1; i < arrayPaneles.size(); i++) {
            arrayPaneles.get(i).setBounds(contenedor.getX(), arrayPaneles.get(i).getY() + 30, 627,
                    arrayPaneles.get(i).getHeight());
        }
    }

    public void cerrar(int num) {
        jp_Panel.setPreferredSize(new Dimension(700, (int) jp_Panel.getSize().getHeight() - 30));
        ContenedorPreguntas contenedor = arrayPaneles.get(num);

        contenedor.setSize(contenedor.getWidth(), contenedor.getHeight() - 30);
        contenedor.txt_Respuesta.setVisible(false);
        contenedor.btn_Desplegable.setIcon(new ImageIcon(Faqs.class.getResource("/img/flecha-hacia-abajo.png")));

        for (int i = num + 1; i < arrayPaneles.size(); i++) {
            arrayPaneles.get(i).setBounds(arrayPaneles.get(i).getX(), arrayPaneles.get(i).getY() - 30, 627,
                    arrayPaneles.get(i).getHeight());
        }
    }
/**Crea el rectángulo de la faq y la posiciona*/
    public void crearFaq(String preg, String respuesta) {
        int y;
        int n = arrayPaneles.size() + 1;
        n--;
        if (n == 0) {
            y = 10;
        } else {
            y = arrayPaneles.get(n - 1).getY() + arrayPaneles.get(n - 1).getHeight() + 10;
        }
        arrayPaneles.add(n, (new ContenedorPreguntas(10, y, preg, respuesta)));
    }

    private int getPosition() {
        return arrayPaneles.size() - 1;
    }

    public Faqs() {
        arrayPaneles = new ArrayList<>();
        setBackground(Color.WHITE);
        scrScrollPane = new JScrollPane();
        scrScrollPane.setBounds(this.getX() / 2, 100, 656, 436);
        add(scrScrollPane);

        scrScrollPane.setBackground(Color.WHITE);
        scrScrollPane.setAutoscrolls(true);
        scrScrollPane.setBorder(null);
        scrScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jp_Panel = new JPanel();
        jp_Panel.setBounds(this.getX() / 2, 100, 656, 436);
        jp_Panel.setBackground(Color.WHITE);
        scrScrollPane.setViewportView(jp_Panel);
        jp_Panel.setLayout(null);
        jp_Panel.setPreferredSize(new Dimension(700, 436));

        crearFaq("¿Cómo puedo contactar con los clubes?",
                "Para ello debes registrarte para"
                        + " luego acceder a la parte de contacto, o bien, ubicar al administrador del club "
                        + "en la universidad");
        jp_Panel.add(arrayPaneles.get(getPosition()));

        arrayPaneles.get(getPosition()).agregarListener(this, 0);

        crearFaq("¿Cómo puedo registrarme?",
                "Debes ingresar todos los datos solicitados, respetando el formato de cada uno de ellos, y luego pulsar el botón de registrarse");
        jp_Panel.add(arrayPaneles.get(getPosition()));
        arrayPaneles.get(getPosition()).agregarListener(this, 1);


        crearFaq("¿Puedo unirme a proyectos sin estar en un club?",
                "Sí, puedes unirte a todos los proyectos que sean públicos. Puedes ver todos los proyectos públicos en el apartado de \"Proyectos abiertos\".");
        jp_Panel.add(arrayPaneles.get(getPosition()));
        arrayPaneles.get(getPosition()).agregarListener(this, 2);


        crearFaq("¿Cómo puedo crear un club?",
                "Debes dirigirte al aparto de \"Solicitar creación de club\" y llenar todos los datos. Luego, un administrador revisar tu solicitud y se aprobará o rechazará");
        jp_Panel.add(arrayPaneles.get(getPosition()));
        arrayPaneles.get(getPosition()).agregarListener(this, 3);

        crearFaq("¿Cómo puedo crear un proyecto?",
                "Para crear un proyecto, primero debes ser administrador. Luego, debes ir al apartado "
                        + "de crear proyecto dentro del club y llenar todos los campos que se solicitan.");
        jp_Panel.add(arrayPaneles.get(getPosition()));
        arrayPaneles.get(getPosition()).agregarListener(this, 4);

        crearFaq("¿Puedo pertenecer a más de un club?",
                "Sí, no hay ningún problema. Puedes unirte a todos los clubes que desees");
        jp_Panel.add(arrayPaneles.get(getPosition()));
        arrayPaneles.get(getPosition()).agregarListener(this, 5);

        crearFaq("¿Cómo puedo darme de baja de la aplicación?",
                "En la ventana de tu perfil, hay un botón que dice \\\"Eliminar cuenta\\\". Solo confirmas y listo.");
        jp_Panel.add(arrayPaneles.get(getPosition()));
        arrayPaneles.get(getPosition()).agregarListener(this, 6);

        JLabel titulo = new JLabel("FAQs");
        titulo.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titulo.setBounds(134, 29, 275, 49);
        this.add(titulo);

        pnlLinea = new Lineas(138, 82, 633, 2);
        pnlLinea.setLocation(134, 78);
        this.add(pnlLinea);

        btn_Contacto = new Boton("Contacto", this.getX() + 100, 556, 200, 30);
        btn_Contacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                FormularioAyuda ayuda = new FormularioAyuda(getModelo());
                ayuda.setVisible(true);
            }
        });
        this.add(btn_Contacto);

        lbl_Ayuda = new JLabel("¿Sigues necesitando ayuda?");
        lbl_Ayuda.setBounds(this.getX() + 112, 526, 200, 30);
        this.add(lbl_Ayuda);
    }
}