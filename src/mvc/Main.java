package mvc;

/**
 * @author Brian
 */

import login.VentanaLogin;
import utilidades.Paginas;
import ventanaPrincipal.ContenidoPrincipal;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ModeloImpl modelo = new ModeloImpl();

        Paginas.RECUPERAR_CONTRASENA.setModelo(modelo);
        ControladorImpl controlador = new ControladorImpl();
        Paginas.RECUPERAR_CONTRASENA.setControlador(controlador);
        controlador.setModelo(modelo);

        ContenidoPrincipal.setControlador(controlador);
        ContenidoPrincipal.setModelo(modelo);


        VentanaLogin ventana = new VentanaLogin();

        ventana.setControlador(controlador);
        modelo.setLogin(ventana);
        controlador.setLogin(ventana);
        ventana.setModelo(modelo);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modelo.comprobarPermanecerLogueado();
    }
}

