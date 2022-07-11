package usuarioRegistrado;

/**
 * @author Brian Da Silva
 */

import utilidades.Boton;
import utilidades.CircleBorder;
import utilidades.Paginas;
import utilidades.SeparatorOficial;
import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.CambiarClave;
import ventanasEmergentes.Cambios_guardado;
import ventanasEmergentes.Eliminar_Cuenta;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class PerfilUsuario extends ContenidoPrincipal {
    private JTextField txt__Nombre;
    private JPanel jp_Imagen;
    private Boton btn_GuardarCambios;
    private Boton btn_EliminarCuenta;
    private Boton btn_CambiarContrasena;
    private JLabel lbl_Estudios;
    private JTextField txt_Emal;
    private JLabel lbl_Email;
    private JLabel lbl_Nombre;
    private JTextField txt__Username;
    private JLabel lbl_Username;
    private JSeparator separator;
    private JLabel lbl_Perfil;
    private Eliminar_Cuenta eliminar;
    private JTextField txt_Estudios;
    private Cambios_guardado guardar;
    private CambiarClave clave;

    private AbstractBorder borde;

    public PerfilUsuario() {
        borde = new CircleBorder();
        this.setBounds(270, 65, 920, 659);
        this.setBackground(Color.white);
        setLayout(null);

        JLabel lbl_PerfilDeUsuario = new JLabel("Perfil de Usuario");
        lbl_PerfilDeUsuario.setFont(new Font("Dialog", Font.BOLD, 21));
        lbl_PerfilDeUsuario.setBounds(173, 16, 215, 49);
        add(lbl_PerfilDeUsuario);

        separator = new SeparatorOficial();
        separator.setForeground(Color.BLACK);
        separator.setBounds(173, 65, 474, 14);
        add(separator);

        lbl_Username = new JLabel("Username");
        lbl_Username.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Username.setBounds(173, 87, 107, 49);
        add(lbl_Username);

        txt__Username = new JTextField();
        txt__Username.setEditable(false);

        txt__Username.setBounds(173, 147, 264, 21);
        add(txt__Username);
        txt__Username.setColumns(10);
        txt__Username.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt__Username.getText().length() >= 32) {
                    e.consume();
                }
            }
        });

        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Nombre.setBounds(173, 179, 107, 49);
        add(lbl_Nombre);

        txt__Nombre = new JTextField();

        txt__Nombre.setColumns(10);
        txt__Nombre.setEditable(false);
        txt__Nombre.setBounds(173, 239, 264, 21);
        add(txt__Nombre);
        txt__Nombre.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt__Nombre.getText().length() >= 32) {
                    e.consume();
                }
            }
        });


        lbl_Email = new JLabel("Email");
        lbl_Email.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Email.setBounds(173, 271, 107, 49);
        add(lbl_Email);

        txt_Emal = new JTextField();

        txt_Emal.setColumns(10);
        txt_Emal.setBounds(173, 331, 264, 21);
        add(txt_Emal);

        txt_Emal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Emal.getText().length() >= 32) {
                    e.consume();
                }
            }
        });

        lbl_Estudios = new JLabel("Estudios");
        lbl_Estudios.setFont(new Font("Dialog", Font.BOLD, 18));
        lbl_Estudios.setBounds(173, 363, 107, 49);
        add(lbl_Estudios);

        lbl_Estudios.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (lbl_Estudios.getText().length() >= 50) {
                    e.consume();
                }
            }
        });

        btn_CambiarContrasena = new Boton("Cambiar Contraseña");
        btn_CambiarContrasena.setText("Cambiar Contraseña");
        clave = new CambiarClave(this);
        btn_CambiarContrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                clave.setVisible(true);
            }
        });
        btn_CambiarContrasena.setBackground(Color.GRAY);

        btn_CambiarContrasena.setBounds(173, 481, 157, 21);
        add(btn_CambiarContrasena);

        btn_EliminarCuenta = new Boton("Eliminar Cuenta");
        eliminar = new Eliminar_Cuenta(this);
        btn_EliminarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                eliminar.setVisible(true);

            }
        });
        btn_EliminarCuenta.setBackground(Color.GRAY);
        btn_EliminarCuenta.setBounds(375, 481, 130, 21);
        add(btn_EliminarCuenta);

        btn_GuardarCambios = new Boton("Guardar Cambios");
        btn_GuardarCambios.setBounds(653, 471, 157, 40);
        add(btn_GuardarCambios);

        btn_GuardarCambios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar = new Cambios_guardado();
                guardar.setVisible(false);
                getModelo().actualizarDatosUsuario(txt_Emal.getText(), txt_Estudios.getText());
            }
        });

        jp_Imagen = new JPanel();
        jp_Imagen.setBounds(613, 179, 240, 240);
        jp_Imagen.setBackground(Color.WHITE);
        jp_Imagen.setLayout(null);

        add(jp_Imagen);
        lbl_Perfil = new JLabel();
        lbl_Perfil.setBounds(0, 0, 240, 240);
        lbl_Perfil.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/img/Perfil.png")));
        lbl_Perfil.setBorder(borde);
        jp_Imagen.add(lbl_Perfil);


        txt_Estudios = new JTextField();

        txt_Estudios.setColumns(10);
        txt_Estudios.setBounds(173, 414, 264, 21);
        add(txt_Estudios);

    }

    public CambiarClave getClave() {
        return clave;
    }

    public Cambios_guardado getGuardar() {
        return guardar;
    }

    public void establecerDatos(String username, String nombre, String email, InputStream foto, String estudios) {
        txt__Username.setText(username);
        txt__Nombre.setText(nombre);
        txt_Emal.setText(email);
        txt_Estudios.setText(estudios);
        try {
            lbl_Perfil.setIcon(new ImageIcon(ImageIO.read(foto)));
        } catch (Exception e) {
        }
    }

    public void datoExistente(String error) {
        Paginas.PERFIL_DE_USUARIO.getGuardar().setSize(520, 300);
        Paginas.PERFIL_DE_USUARIO.getGuardar().getLblNewLabel().setSize(500, 60);
        Paginas.PERFIL_DE_USUARIO.getGuardar().getLblNewLabel().setText(error);
        Paginas.PERFIL_DE_USUARIO.getGuardar().getLblNewLabel_1().setIcon(new ImageIcon(Cambios_guardado.class.getResource("/img/icons8-error-50.png")));
        Paginas.PERFIL_DE_USUARIO.getGuardar().setVisible(true);
        ;
    }
}
