package mvc;

/**
 * @author Samuel
 */

import login.BarraLateralLogin;
import login.Contenido;
import login.VentanaLogin;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;
import ventanaPrincipal.VentanaPP;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;

public class ControladorImpl implements Controlador {
    protected Vista vista;
    protected Modelo modelo;
    protected VentanaLogin login;
    protected VentanaPP ventanaPP;

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    // Relacionado con la vista

    public void setLogin(VentanaLogin login) {
        this.login = login;
    }

    public void setVentanaPP(VentanaPP ventana) {
        this.ventanaPP = ventana;
    }

    public void cambiarContenido(ContenidoPrincipal panel) {
        ventanaPP.remove(ventanaPP.getContenido());
        ventanaPP.conectarPanel(panel);
        ventanaPP.repaint();
        ventanaPP.revalidate();
    }

    public void cambiarContenidoLogin(Contenido panel) {
        login.remove(login.getContenido());
        login.conectarPanel(panel);
        login.repaint();
        login.revalidate();
    }

    public void cambiarBarraLateralLogin(BarraLateralLogin panel) {
        login.remove(login.devolverBarraIzquierda());
        login.conectarBarraLateral(panel);
        login.repaint();
        login.revalidate();
    }

    // LOGIN
    public void acceder() {
        login.setVisible(false);
        VentanaPP frame = new VentanaPP(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setControlador(this);
        frame.setModelo(modelo);
        modelo.obtenerInfoPerfilUsuario(modelo.getUsername());
        frame.getBarraLateral().setNombreUsuario();

        setVentanaPP(frame);
        frame.seleccionarOpcion(2);
        ventanaPP.setVisible(true);

    }


    public void login() throws IOException {

        try {
            String usr = login.getUsr();
            String pass = login.getPass();
            boolean permanecerLogueado = login.isChecked();
            modelo.login(usr, pass, permanecerLogueado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        ventanaPP.setVisible(false);
        Paginas.CONTENIDO_LOGIN.setBounds(0, 0, 920, 720);
        modelo.borrarFicheroLoguear();
        login.conectarPanel(Paginas.CONTENIDO_LOGIN);
        login.setVisible(true);

        modelo.setAdmin(false);
        Paginas.restablecerPaginas();


    }
    // --------------------------- //

    public void getUsuariosEnClub(int cod_club) {
        modelo.getUsuariosEnClub(cod_club);
    }

    public void getUsuariosEnProyecto(int cod_proyecto) {
        modelo.getUsuariosEnProyecto(cod_proyecto);
    }

    @Override
    public void crearProyecto(String titulo, String lbl_descripcion, String email, boolean publico, int idClub) {
        getModelo().crearProyecto(titulo, lbl_descripcion, email, publico, idClub);
    }

    @Override
    public boolean recuperarContrasena(String email) {
        return modelo.recuperarContrasena(email);

    }

    @Override
    public void permanecerLogeado(String usr, String pass) {
        getModelo().escribirFicheroLoguear(usr, pass);

    }

    @Override
    public void enviarEmailCrearClub(String Email, String Nombre, String Descripcion, Object Localizacion, String URLimagen) {
        modelo.enviarEmailCrearClub(Email,Nombre,Descripcion,Localizacion,URLimagen);
    }

    @Override
    public boolean userIsAdmin() {
        return modelo.userIsAdmin();
    }

    @Override
    public void crearCuenta() {
        String nombre = Paginas.CREAR_CUENTA.getNombre();
        String apellido1 = Paginas.CREAR_CUENTA.getApellido1();
        String apellido2 = Paginas.CREAR_CUENTA.getApellido2();
        String usuario = Paginas.CREAR_CUENTA.getUser();
        String contrasena = Paginas.CREAR_CUENTA.getPasswordCrearCuenta();
        String email = Paginas.CREAR_CUENTA.getEmail();
        Date fecha = Paginas.CREAR_CUENTA.getDate();
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

        System.out.println(usuario);
        modelo.crearCuenta(usuario, nombre, apellido1, apellido2, email, contrasena, sqlDate);

    }

    public void eliminarCuenta() {
        modelo.eliminarCuenta();
    }

    public void abandonarClub(int cod_club) {
        modelo.abandonarClub(cod_club);
    }

    public void obtenerResumenMisClubs() {
        modelo.obtenerResumenMisClubs();
    }

    public void abandonarProyecto(int cod_proyecto) {
        modelo.abandonarProyecto(cod_proyecto);
    }

    public void obtenerResumenMisProyectos() {
        modelo.obtenerResumenMisProyectos();
    }

    public boolean comprobarContrasena(String contrasena) {
        return modelo.comprobarContrasena(contrasena);
    }

    public void actualizarContrasena(String nuevaContrasena) {
        modelo.actualizarContrasena(nuevaContrasena);
    }

    public void getSolicitudesPendientesClubs() {
        modelo.getSolicitudesPendientesClubs();
    }

    public void getSolicitudesPendientesProyecto() {
        modelo.getSolicitudesPendientesProyecto();
    }


}
