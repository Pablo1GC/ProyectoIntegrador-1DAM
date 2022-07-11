package login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import utilidades.Boton;
import utilidades.Paginas;
import utilidades.Titulos;
import ventanasEmergentes.Cambios_guardado;

import java.awt.Font;

/**
 * @author Franco
 */
public class RecuperarContrasena extends BarraLateralLogin {
    private VentanaLogin contenedor;
    private Titulos lbl_recuperarContrasena;
    private Boton btn_Go;
    private JTextField txt_email;
    private JLabel lbl_IniciarSesion;
    private JLabel lbl_CrearCuenta;

    private Cambios_guardado todoBien;



    public RecuperarContrasena() {
        super();
        faq.setVisible(false);
        faq.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.getControlador().cambiarContenidoLogin(Paginas.FAQS_SIN_LOGUEADO);
            }
        });
        this.setVisible(true);
        lbl_recuperarContrasena = new Titulos("Recuperar contraseña", 82, 110, 175, 30);
        btn_Go = new Boton("Enviar código");
        btn_Go.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(getControlador().recuperarContrasena(txt_email.getText())){
                    todoBien = new Cambios_guardado();
                    todoBien.getLblNewLabel().setText("Se ha enviado en email a su correo");
                    todoBien.setVisible(true);
                }else{
                    todoBien = new Cambios_guardado();
                    todoBien.getLblNewLabel().setText("Se ha enviado en email a su correo, quede a la espera");
                    todoBien.setVisible(true);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btn_Go.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        txt_email = new JTextField("Introduzca su email");

        txt_email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                txt_email.setText("");
            }
        });
        txt_email.setBorder(null);
        txt_email.setBackground(new Color(245, 245, 245));
        txt_email.setText("Introduzca su email");
        txt_email.setBounds(20, 161, 280, 30);
        txt_email.setOpaque(true);
        txt_email.setVisible(true);
        txt_email.setBounds(13, 174, 287, 19);
        txt_email.setColumns(10);
        txt_email.setText("Ingrese su email por favor");
        btn_Go.setBounds(86, 228, 130, 25);

        faq.setBounds(280, 35, 20, 20);
        lbl_recuperarContrasena.setBounds(61, 110, 204, 30);
        lbl_Logo.setBounds(12, 12, 245, 70);
        lbl_recuperarContrasena.setText("Recuperar contraseña");

        this.add(lbl_Logo);
        this.add(txt_email);
        this.add(btn_Go);
        this.add(faq);
        this.add(lbl_recuperarContrasena);
        setLayout(null);

        lbl_IniciarSesion = new JLabel("¿Ya tienes cuenta?");
        lbl_IniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_IniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.ocultarRecuperarContrasena();
                contenedor.mostrarIniciarSesion();
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.INICIAR_SESION);
            }
        });
        lbl_IniciarSesion.setFont(new Font("Tahoma", Font.ITALIC, 9));
        lbl_IniciarSesion.setBounds(12, 695, 267, 15);
        add(lbl_IniciarSesion);

        lbl_CrearCuenta = new JLabel("Crear cuenta");
        lbl_CrearCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_CrearCuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.ocultarRecuperarContrasena();
                contenedor.mostrarCrearCuenta();
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.CREAR_CUENTA);
            }
        });
        lbl_CrearCuenta.setFont(new Font("Tahoma", Font.ITALIC, 9));
        lbl_CrearCuenta.setBounds(12, 679, 146, 15);
        add(lbl_CrearCuenta);

    }
    /**
     * Este metodo conecta a la barra lateral con el JFrame que lo contiene (es como un setter)
     *
     * @param ventanaLogin es el JFrame que contiene al objeto que queremos conectar
     */
    public void conectarVentana(VentanaLogin ventanaLogin) {
        this.contenedor = ventanaLogin;
    }

}
