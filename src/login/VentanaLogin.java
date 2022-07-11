package login;

/**
 * @author Franco
 */

import mvc.Controlador;
import mvc.Modelo;
import mvc.Vista;
import utilidades.Paginas;
import ventanaPrincipal.BarraInvisible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class VentanaLogin extends JFrame implements Vista {
    public final static int ancho = 1190;
    public final static int alto = 720;
    protected static Modelo modelo;
    protected static Controlador controlador;
    protected BarraLateralLogin barraLateral;
    protected BarraInvisible barraInvisible;
    protected CrearCuenta crearCuenta;
    protected Contenido contenido;
    protected JLabel lbl_BotonCerrar, lbl_BotonMinimizar;

    public VentanaLogin() {
        Paginas.FAQS_SIN_LOGUEADO.conectarVentana(this);
        contenido = new Contenido();
        contenido.setContenedor(this);
        barraInvisible = new BarraInvisible(this);
        barraLateral = new BarraLateralLogin();
        crearCuenta = new CrearCuenta();
        crearCuenta.conectarVentana(this);
        this.setResizable(false);
        this.setUndecorated(true);
        agregarMinimizar();
        agregarCerrar();
        getContentPane().setLayout(null);
        getContentPane().add(barraInvisible);
        getContentPane().add(contenido);
        setBounds(0, 0, 1190, 720);
        revalidate();
        repaint();
        getContentPane().add(Paginas.INICIAR_SESION);

    }

    public VentanaLogin(Modelo modelo, Controlador controlador) {
        this();
        this.conectarPanel(contenido);
    }

    public VentanaLogin(Contenido panel) {
        this();
        conectarPanel(panel);
    }

    public VentanaLogin(BarraLateralLogin panel) {
        this();
        conectarBarraLateral(panel);
    }

    /**
     * Metodo que oculta el objeto RecuperarContraseña que hemos conectado con este JFrame
     */
    public void ocultarRecuperarContrasena() {
        Paginas.RECUPERAR_CONTRASENA.setVisible(false);

    }
    /**
     * Metodo que oculta el objeto CrearCuenta que hemos conectado con este JFrame
     */
    public void ocultarCrearCuenta() {
        Paginas.CREAR_CUENTA.setVisible(false);

    }
    /**
     * Metodo que oculta el objeto IniciarSesion que hemos conectado con este JFrame
     */
    public void ocultarIniciarSesion() {
        Paginas.INICIAR_SESION.setVisible(false);

    }
    /**
     * Metodo que muestra el objeto RecuperarContraseña que hemos conectado con este JFrame
     */
    public void mostrarRecuperarContrasena() {
        Paginas.RECUPERAR_CONTRASENA.setVisible(true);

    }
    /**
     * Metodo que muestra el objeto CrearCuenta que hemos conectado con este JFrame
     */
    public void mostrarCrearCuenta() {
        Paginas.CREAR_CUENTA.setVisible(true);

    }
    /**
     * Metodo que muestra el objeto IniciarSesion que hemos conectado con este JFrame
     */
    public void mostrarIniciarSesion() {
        Paginas.INICIAR_SESION.setVisible(true);

    }

    /**
     * Metodo que conecta al JFrame con un determinado panel, para ello borramos todo
     * lo que hay en el espacio que ocupara ese panel, repintamos,revalidamos y posteriormente
     * agregamos el panel
     * @param panel es el objeto que queremos hacer visible en el JFrame
     */
    public void conectarPanel(Contenido panel) {
        contenido.removeAll();
        contenido.repaint();
        contenido.revalidate();
        contenido.add(panel);
        panel.setFrame(this);

        getContentPane().add(contenido);
    }
    /**
     * Metodo que conecta al JFrame con un determinado panel de tipo BarraLAteralLogin, para ello borramos todo
     * lo que hay en el espacio que ocupara ese panel, repintamos,revalidamos y posteriormente
     * agregamos el panel
     * @param panel es el objeto que queremos hacer visible en el JFrame
     */
    public void conectarBarraLateral(BarraLateralLogin panel) {
        barraLateral.removeAll();
        barraLateral.repaint();
        barraLateral.revalidate();
        barraLateral.add(panel);
        panel.setFrame(this);

        getContentPane().add(barraLateral);
    }

    /**
     * Metodo que agrega a la pantalla la posibilidad de tener el icono de minimizar el JFrame
     */
    public void agregarMinimizar() {
        lbl_BotonMinimizar = new JLabel("_");
        lbl_BotonMinimizar.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 14));
        getContentPane().add(lbl_BotonMinimizar);
        lbl_BotonMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_BotonMinimizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setExtendedState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_BotonMinimizar.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_BotonMinimizar.setForeground(Color.white);
            }
        });
        lbl_BotonMinimizar.setForeground(Color.white);
        lbl_BotonMinimizar.setBounds(1145, -5, 25, 23);

    }
    /**
     * Metodo que agrega a la pantalla la posibilidad de tener el icono de cerrar el JFrame
     */
    public void agregarCerrar() {
        lbl_BotonCerrar = new JLabel("X");
        lbl_BotonCerrar.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 14));
        getContentPane().add(lbl_BotonCerrar);
        lbl_BotonCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_BotonCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modelo.cerrarConexion();
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_BotonCerrar.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_BotonCerrar.setForeground(Color.white);
            }
        });
        lbl_BotonCerrar.setForeground(Color.white);
        lbl_BotonCerrar.setBounds(1170, 0, 25, 23);
    }

    @Override
    public Modelo getModelo() {
        // TODO Auto-generated method stub
        return modelo;
    }

    public void setModelo(Modelo m) {
        modelo = m;
    }

    public Controlador getControlador() {
        return controlador;
    }

    /**
     * Metodo que conecta al JFrame y a los elementos de la barra lateral con el controlador
     * @param c es el Controlador que manejará los acciones sobre la vista
     */
    public void setControlador(Controlador c) {
        barraLateral.conectarVentana(this);
        Paginas.CONTENIDO_LOGIN.conectarVentana(this);
        Paginas.INICIAR_SESION.conectarVentana(this);
        Paginas.RECUPERAR_CONTRASENA.conectarVentana(this);
        Paginas.CREAR_CUENTA.conectarVentana(this);
        controlador = c;
    }

    public BarraLateralLogin devolverBarraIzquierda() {
        return barraLateral;
    }

    public Contenido getContenido() {
        return contenido;
    }


    // ------- Metodos para loguearse --------- //

    /**
     * @see mvc.Modelo
     * @see mvc.Modelo.Resultado
     * Metodo que actualiza elJFrame dependiendo de el resultado que reciba del modelo y da una orden
     * dependiendo del resultado recibido
     */
    public void actualizar() {
        Modelo.ResultadoLogin resultado = modelo.getResultado();
        switch (resultado) {
            case CORRECTO -> controlador.acceder();
            case INCORRECTO -> Paginas.INICIAR_SESION.getRespuesta().setText("Usuario o contraseña incorrectos");
            case CERRAR -> System.exit(0);
        }

    }

    public String getUsr() {
        return Paginas.INICIAR_SESION.getUsr();
    }

    public String getPass() {
        return Paginas.INICIAR_SESION.getPass();
    }

    public Contenido getContenidoPrincipal() {
        return contenido;
    }

    /**
     * @see ModeloImpl método escribirFicheroLoguear
     * Metodo que comprueba si el Checkbox del inicio esta checkeado o no
     * @return true si el checkbox está seleccionado, false en cualquier otro caso
     * @throws IOException puede dar errores de entrada o salida debido a que en caso de ser true,
     * se escribirian los datos en el fichero desde el modelo.
     */
    public boolean isChecked() throws IOException {
        if (Paginas.INICIAR_SESION.getChckbxPermanecerLogueado().isSelected()) {
            controlador.permanecerLogeado(Paginas.INICIAR_SESION.getUsr(), Paginas.INICIAR_SESION.getPass());
            return true;
        }
        return false;
    }
}
