package ventanaPrincipal;

/**
 * @author pablo
 */

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import utilidades.OpcionLogout;
import utilidades.Paginas;

public class BarraLateral extends JPanel {
    private JLabel lbl_Workspace, lbl_Comunidad, lbl_Administrador, lbl_NombreUser;
    private JSeparator separator;
    private OpcionesMenu jp_MiPerfil, jp_MisC_Y_P, jp_SolisPendientes, jp_ClubsUni, jp_ProyectosAbiertos, jp_Ayuda,
            jp_NuevoPost, jp_SolisUnion, jp_AdminUser;
    OpcionLogout jp_Logout;

    private OpcionesMenu opcionSeleccionada;



    private VentanaPP ventanaPrincipal;

    private int numeroDeOpciones;

    private final HashMap<Integer, OpcionesMenu> indice = new HashMap<>();

    private final int width = 270;
    private final int height = 655;
    private final int posicionX = 0;
    private final int posicionY = 65;



    public BarraLateral(VentanaPP ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.setBounds(posicionX, posicionY, width, height);
        this.setBackground(new Color(190, 190, 200));
        this.setLayout(null);
        this.agregarLabelNombre();
        this.agregarSeparator();
        this.agregarEtiquetas();
        this.agregarBotones();
        this.inicializarHashMap();
        this.conectarOpciones();
        this.numeroDeOpciones = 0;
        this.setVisible(true);
        this.relacionarPaginas();

    }

    /**
     * Constructor cuando tengamos link la BBDD
     */
//	public BarraLateral(String nombre) {
//		this.setBounds(posicionX, posicionY, width, height);
//		this.setBackground(new Color(190, 190, 200));
//		this.setLayout(null);
//		this.agregarNombre(nombre);
//		this.agregarSeparator();
//		this.agregarEtiquetas();
//		this.agregarBotones();
//		this.setVisible(true);
//	}
    public void agregarLabelNombre() {
        lbl_NombreUser = new JLabel();
        lbl_NombreUser.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
        lbl_NombreUser.setIconTextGap(10);
        lbl_NombreUser.setIcon(new ImageIcon(VentanaPP.class.getResource("/img/Icon_User.png")));
        lbl_NombreUser.setBounds(25, 10, 235, 31);
        this.add(lbl_NombreUser);
        this.setVisible(true);
    }

    public void agregarSeparator() {
        separator = new JSeparator();
        separator.setForeground(Color.DARK_GRAY);
        separator.setBounds(0, 49, 270, 2);
        this.add(separator);
    }

    public void agregarEtiquetas() {
        lbl_Workspace = new JLabel("Workspace");
        lbl_Workspace.setForeground(Color.DARK_GRAY);
        lbl_Workspace.setIconTextGap(10);
        lbl_Workspace.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
        lbl_Workspace.setBounds(10, 50, 235, 31);
        this.add(lbl_Workspace);

        lbl_Comunidad = new JLabel("Comunidad");
        lbl_Comunidad.setForeground(Color.DARK_GRAY);
        lbl_Comunidad.setIconTextGap(10);
        lbl_Comunidad.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
        lbl_Comunidad.setBounds(10, 190, 235, 31);
        this.add(lbl_Comunidad);

        if (ventanaPrincipal.getControlador().userIsAdmin()) {
            lbl_Administrador = new JLabel("Administrador");
            lbl_Administrador.setForeground(Color.DARK_GRAY);
            lbl_Administrador.setIconTextGap(10);
            lbl_Administrador.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
            lbl_Administrador.setBounds(10, 335, 235, 31);
            this.add(lbl_Administrador);
        }


    }

    public void agregarBotones() {
        jp_MiPerfil = new OpcionesMenu("Mi Perfil", "/img/Icon_MiUsuario.png", 80);
        jp_MisC_Y_P = new OpcionesMenu("Mis clubs y proyectos", "/img/Icon_MisCyP.png", 115);
        jp_SolisPendientes = new OpcionesMenu("Solicitudes pendientes", "/img/Icon_Pendientes.png", 150);
        jp_ClubsUni = new OpcionesMenu("Clubs universidad", "/img/Icon_Club.png", 225);
        jp_ProyectosAbiertos = new OpcionesMenu("Proyectos abiertos", "/img/Icon_Proyectos.png", 260);
        jp_Ayuda = new OpcionesMenu("Contacto", "/img/Icon_Contacto.png", 295);
        jp_NuevoPost = new OpcionesMenu("Nuevo Post", "/img/Icon_NuevoPost.png", 370);
        jp_SolisUnion = new OpcionesMenu("Solicitudes de unión", "/img/Icon_SolisUnion.png", 405);
        jp_AdminUser = new OpcionesMenu("Administrar usuarios", "/img/Icon_AdminUser.png", 440);
        jp_Logout = new OpcionLogout("Cerrar Sesión", "/img/Icon_Logout.png", 619);


        this.add(jp_MiPerfil);
        this.add(jp_MisC_Y_P);
        this.add(jp_SolisPendientes);
        this.add(jp_ClubsUni);
        this.add(jp_ProyectosAbiertos);
        this.add(jp_Ayuda);
        if (ventanaPrincipal.getControlador().userIsAdmin()) {
            this.add(jp_NuevoPost);
            this.add(jp_SolisUnion);
            this.add(jp_AdminUser);
        }
        this.add(jp_Logout);

    }

    public void seleccionarOpcion(int opcion) {
        if (opcionSeleccionada != null) {
            opcionSeleccionada.setBackground(null);
        }

        opcionSeleccionada = indice.get(opcion);
        opcionSeleccionada.setBackground(Color.white);
        ventanaPrincipal.remove(ventanaPrincipal.getContenido());


        ventanaPrincipal.conectarPanel(opcionSeleccionada.getContenedorAsociado());
        ventanaPrincipal.revalidate();
        ventanaPrincipal.repaint();


    }

    public int getOpcionSeleccionada() {
        return opcionSeleccionada.getIndice();
    }

    private void conectarOpciones() {
        for (Map.Entry<Integer, OpcionesMenu> entry : indice.entrySet()) {
            OpcionesMenu val = entry.getValue();

            val.conectarVentana(this);

        }
    }

    private void inicializarHashMap() {
        indice.put(1, jp_MiPerfil);
        indice.put(2, jp_MisC_Y_P);
        indice.put(3, jp_SolisPendientes);
        indice.put(4, jp_ClubsUni);
        indice.put(5, jp_ProyectosAbiertos);
        indice.put(6, jp_Ayuda);
        indice.put(7, jp_NuevoPost);
        indice.put(8, jp_SolisUnion);
        indice.put(9, jp_AdminUser);


    }

    public void setVentanaPrincipal(VentanaPP ventana) {
        this.ventanaPrincipal = ventana;
    }

    public VentanaPP getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public int aumentarNumeroDeOpciones() {
        numeroDeOpciones++;
        return numeroDeOpciones;
    }


    public void relacionarPaginas() {
        jp_MiPerfil.setContenedorAsociado(Paginas.PERFIL_DE_USUARIO);
        jp_MisC_Y_P.setContenedorAsociado(Paginas.MIS_CLUBS);
        jp_SolisPendientes.setContenedorAsociado(Paginas.SOLICITUDES_PENDIENTES_CLUBS);
        jp_ClubsUni.setContenedorAsociado(Paginas.CLUBS_UNIVERSIDAD);
        jp_ProyectosAbiertos.setContenedorAsociado(Paginas.PROYECTOS_ABIERTOS);
        jp_Ayuda.setContenedorAsociado(Paginas.FAQS_LOGEADO);
        jp_NuevoPost.setContenedorAsociado(Paginas.NUEVO_POST);
        jp_SolisUnion.setContenedorAsociado(Paginas.ADMINISTRAR_SOLICITUDES);
        Paginas.ADMINISTRAR_SOLICITUDES.obtenerDatosTabla();
        jp_AdminUser.setContenedorAsociado(Paginas.ADMINISTRAR_USUARIOS);
        jp_Logout.conectarVentana(this);
    }

    public void setNombreUsuario() {

        String nombre = ventanaPrincipal.getModelo().getNombreUsuario();
        String apellido = ventanaPrincipal.getModelo().getApellido1();

        lbl_NombreUser.setText(nombre + " " + apellido);


    }
}
