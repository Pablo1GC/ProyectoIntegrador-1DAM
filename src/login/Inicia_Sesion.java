package login;

import utilidades.Boton;
import utilidades.Paginas;
import utilidades.Titulos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Inicia_Sesion extends BarraLateralLogin {
    protected JTextField txt_Usuario;
    protected Titulos lbl_IniciaSesion;
    protected JCheckBox chckbxPermanecerLogueado;
    protected Boton btn_Go;
    protected JLabel lbl_RecuperarContrasena;
    protected JLabel lbl_CrearCuenta;
    private JPasswordField pwd_Contrasea;
    private JLabel lbl_Respuesta;

    public Inicia_Sesion() {
        this.setVisible(true);

        lbl_IniciaSesion = new Titulos("Iniciar Sesión", 100, 109, 108, 15);
        lbl_IniciaSesion.setBounds(101, 111, 117, 19);
        lbl_IniciaSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl_IniciaSesion.setHorizontalTextPosition(SwingConstants.CENTER);

        add(lbl_IniciaSesion);

        txt_Usuario = new JTextField();
        txt_Usuario.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btn_Go.doClick();
                }
            }
        });

        txt_Usuario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Usuario.getText().equals("  Usuario")) {
                    txt_Usuario.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        faq.setFocusable(true);
        txt_Usuario.setBorder(null);
        txt_Usuario.setBackground(new Color(245, 245, 245));
        txt_Usuario.setText("  Usuario");
        txt_Usuario.setBounds(20, 161, 280, 30);
        txt_Usuario.setOpaque(true);
        add(txt_Usuario);
        txt_Usuario.setColumns(10);

        pwd_Contrasea = new JPasswordField();
        pwd_Contrasea.setText("Contraseña");
        pwd_Contrasea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btn_Go.doClick();
                }
            }
        });

        pwd_Contrasea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getPass().equals("Contraseña")) {
                    pwd_Contrasea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        pwd_Contrasea.setBorder(null);
        pwd_Contrasea.setBackground(new Color(245, 245, 245));
        pwd_Contrasea.setBounds(20, 210, 280, 30);
        add(pwd_Contrasea);

        chckbxPermanecerLogueado = new JCheckBox("Permanecer logueado");
        chckbxPermanecerLogueado.setBounds(20, 256, 199, 23);
        add(chckbxPermanecerLogueado);
        chckbxPermanecerLogueado.setBackground(new Color(190, 190, 200));

        btn_Go = new Boton("Acceder");
        btn_Go.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_Go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (chckbxPermanecerLogueado.isSelected()) {
                        contenedor.getControlador().permanecerLogeado(getUsr(), getPass());

                    }

                    contenedor.getControlador().login();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                limpiarCampos();
            }
        });
        btn_Go.setBounds(101, 313, 117, 25);


        add(btn_Go);
        lbl_RecuperarContrasena = new JLabel("¿No puedes iniciar sesión?");
        lbl_RecuperarContrasena.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_RecuperarContrasena.setFont(new Font("Tahoma", Font.HANGING_BASELINE, 9));
        lbl_RecuperarContrasena.setBounds(12, 695, 267, 15);
        add(lbl_RecuperarContrasena);
        lbl_RecuperarContrasena.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.ocultarIniciarSesion();
                contenedor.mostrarRecuperarContrasena();
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.RECUPERAR_CONTRASENA);
            }
        });

        lbl_CrearCuenta = new JLabel("Crear cuenta");
        lbl_CrearCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_CrearCuenta.setFont(new Font("Tahoma", Font.HANGING_BASELINE, 9));
        lbl_CrearCuenta.setBounds(12, 677, 146, 15);
        add(lbl_CrearCuenta);

        lbl_Respuesta = new JLabel("");
        lbl_Respuesta.setForeground(Color.RED);
        lbl_Respuesta.setBounds(53, 361, 226, 17);
        add(lbl_Respuesta);
        lbl_CrearCuenta.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.ocultarIniciarSesion();
                contenedor.mostrarCrearCuenta();
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.CREAR_CUENTA);
            }

        });

    }

    public JCheckBox getChckbxPermanecerLogueado() {
        return chckbxPermanecerLogueado;
    }

    /**
     * Este metodo conecta a la barra lateral con el JFrame que lo contiene (es como un setter)
     *
     * @param ventanaLogin es el JFrame que contiene al objeto que queremos conectar
     */
    public void conectarVentana(VentanaLogin ventanaLogin) {
        this.contenedor = ventanaLogin;
    }

    public String getUsr() {
        return txt_Usuario.getText();
    }

    public String getPass() {
        return String.valueOf(pwd_Contrasea.getPassword());
    }

    /**
     * Metodo que se utiliza para borrar los campos ya escritos
     */
    void limpiarCampos() {
        txt_Usuario.setText("");

        pwd_Contrasea.setText("");

    }

    public JLabel getRespuesta() {
        // TODO Auto-generated method stub
        return lbl_Respuesta;
    }

}
