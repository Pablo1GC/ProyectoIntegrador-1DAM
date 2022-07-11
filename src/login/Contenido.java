package login;

import mvc.Controlador;
import mvc.Modelo;
import mvc.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * @author Franco
 */
public class Contenido extends JPanel implements Vista {
    protected static Modelo modelo;
    protected static Controlador controlador;
    protected static VentanaLogin frame;
    protected VentanaLogin contenedor;
    private JLabel lbl_imagen;

    public Contenido() {
        this.setBackground(Color.red);
        this.setBounds(323, 0, 870, 720);
        setLayout(null);
        lbl_imagen = new JLabel("");

        ImageIcon ima = new ImageIcon(Contenido.class.getResource("/img/Index_Imagen_Principal.jpg"));

        lbl_imagen.setIcon(ima);
        lbl_imagen.setLocation(-120, 0);
        lbl_imagen.setSize(990, 720);
        add(lbl_imagen);
    }

    public static void setFrame(VentanaLogin f) {
        frame = f;
    }

    /**
     * Este metodo conecta a la barra lateral con el JFrame que lo contiene (es como un setter)
     *
     * @param contenedor es el JFrame que contiene al objeto que queremos conectar
     */
    public void conectarVentana(VentanaLogin contenedor) {
        this.contenedor = contenedor;

    }

    public VentanaLogin getContenedor() {
        return contenedor;
    }

    public void setContenedor(VentanaLogin contenedor) {
        this.contenedor = contenedor;
    }

    @Override
    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo m) {
        this.modelo = m;

    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador c) {
        this.controlador = c;
    }

}