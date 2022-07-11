package login;

/**
 * @author Franco
 */

import com.toedter.calendar.JDateChooser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import utilidades.Boton;
import utilidades.Paginas;
import utilidades.TextFieldsCustom;
import utilidades.Titulos;
import ventanasEmergentes.Cambios_guardado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Properties;

public class CrearCuenta extends BarraLateralLogin {
    private final JPasswordField txt_Contrasena;
    private final TextFieldsCustom txt_Email;
    private final TextFieldsCustom txt_Nombre;
    private final TextFieldsCustom txt_Apellido1;
    private final TextFieldsCustom txt_Apellido2;
    private final JPasswordField txt_ConfirmarPassword;
    private final Titulos lbl_IniciaSesion_1;
    private final JTextField txt_Usuario_1;
    private final JLabel lbl_inciarSesion;
    private final JLabel lbl_recuperarContrasena;
    private final Boton btn_Registrarse;
    private final JLabel lbl_RecuperarContrasena;
    private final JLabel lbl_IniciarSesion;

    public Cambios_guardado getGuardar() {
        return guardar;
    }

    private Cambios_guardado guardar;
    JDateChooser dateChooser = new JDateChooser();


    public CrearCuenta() {
        super();
        guardar = new Cambios_guardado();
        this.setVisible(true);
        lbl_recuperarContrasena = new JLabel();
        lbl_inciarSesion = new JLabel();
        txt_Usuario_1 = new JTextField();
        txt_Usuario_1.setText("Usuario");

        txt_Usuario_1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Usuario_1.getText().equals("Usuario")) {
                    txt_Usuario_1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Usuario_1.getText().equals("")) {
                    txt_Usuario_1.setText("Usuario");
                }
            }
        });
        txt_Usuario_1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Usuario_1.getText().length() >= 32) {
                    e.consume();
                }
            }
        });
        lbl_IniciaSesion_1 = new Titulos("Crear cuenta", 110, 121, 93, 30);
        lbl_IniciaSesion_1.setSize(119, 30);

        lbl_IniciaSesion_1.setText("Crear cuenta");
        txt_Usuario_1.setBounds(new Rectangle(12, 183, 299, 30));
        txt_Usuario_1.setLocation(12, 183);
        UtilDateModel model = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        txt_Email = new TextFieldsCustom("Correo electrónico");
        txt_Email.setBounds(12, 224, 299, 30);
        txt_Email.setFocusable(true);
        txt_Email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Email.getText().equals("Correo electrónico")) {
                    txt_Email.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Email.getText().equals("")) {
                    txt_Email.setText("Correo electrónico");
                }
            }
        });

        txt_Email.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Email.getText().length() >= 50) {
                    e.consume();
                }
            }
        });

        txt_Nombre = new TextFieldsCustom("Nombre");
        txt_Nombre.setBounds(12, 265, 299, 30);

        txt_Nombre.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Nombre.getText().length() >= 32) {
                    e.consume();
                }
            }
        });

        txt_Nombre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Nombre.getText().equals("Nombre")) {
                    txt_Nombre.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Nombre.getText().equals("")) {
                    txt_Nombre.setText("Nombre");
                }
            }
        });

        txt_Apellido1 = new TextFieldsCustom("Apellido1");
        txt_Apellido1.setBounds(12, 306, 143, 30);

        txt_Apellido1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Apellido1.getText().length() >= 32) {
                    e.consume();
                }
            }
        });

        txt_Apellido1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Apellido1.getText().equals("Apellido1")) {
                    txt_Apellido1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Apellido1.getText().equals("")) {
                    txt_Apellido1.setText("Apellido1");
                }
            }
        });

        txt_Apellido2 = new TextFieldsCustom("Apellido2");
        txt_Apellido2.setBounds(165, 306, 146, 30);
        txt_Apellido2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Apellido2.getText().equals("Apellido2")) {
                    txt_Apellido2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Apellido2.getText().equals("")) {
                    txt_Apellido2.setText("Apellido2");
                }
            }
        });

        txt_Apellido2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Apellido2.getText().length() >= 32) {
                    e.consume();
                }
            }
        });


        txt_Contrasena = new JPasswordField("Contraseña");
        txt_Contrasena.setText("Contraseña");
        txt_Contrasena.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Contrasena.getText().equals("Contraseña")) {
                    txt_Contrasena.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Contrasena.getText().equals("")) {
                    txt_Contrasena.setText("Contraseña");
                }
            }
        });
        txt_Contrasena.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Contrasena.getText().length() >= 32) {
                    e.consume();
                }
            }
        });

        txt_ConfirmarPassword = new JPasswordField("Repita su contraseña");
        txt_ConfirmarPassword.setText("Repita su contraseña");
        txt_ConfirmarPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_ConfirmarPassword.getText().equals("Repita su contraseña")) {
                    txt_ConfirmarPassword.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_ConfirmarPassword.getText().equals("")) {
                    txt_ConfirmarPassword.setText("Repita su contraseña");
                }
            }
        });

        txt_ConfirmarPassword.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_ConfirmarPassword.getText().length() >= 32) {
                    e.consume();
                }
            }
        });

        lbl_IniciaSesion_1.setLocation(110, 121);
        removeAll();
        lbl_IniciaSesion_1.setText("Crear cuenta");
        txt_Contrasena.setBounds(12, 388, 299, 30);
        txt_ConfirmarPassword.setBounds(12, 429, 299, 30);

        lbl_inciarSesion.setText("¿Ya tienes cuenta?");
        lbl_recuperarContrasena.setText("¿No puedes iniciar sesión?");
        txt_Email.setColumns(10);
        add(lbl_IniciaSesion_1);
        add(txt_Email);
        add(txt_Contrasena);
        add(txt_Apellido2);
        add(txt_ConfirmarPassword);
        add(lbl_Logo);
        add(faq);
        add(txt_ConfirmarPassword);
        add(txt_Usuario_1);
        add(txt_Nombre);
        add(txt_Apellido1);
        add(txt_Apellido2);
        add(lbl_inciarSesion);
        add(lbl_recuperarContrasena);

        dateChooser.setForeground(new Color(102, 102, 204));
        dateChooser.setBackground(new Color(245, 245, 245));
        dateChooser.setBorder(null);
        dateChooser.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateChooser.getCalendarButton().setBorder(null);
        dateChooser.getCalendarButton().setIcon(new ImageIcon(CrearCuenta.class.getResource("/img/calendar (1).png")));
        dateChooser.getCalendarButton().setBackground(new Color(245, 245, 245));
        dateChooser.setToolTipText("DD/MM/YYYY");
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(165, 347, 149, 30);

        add(dateChooser);

        btn_Registrarse = new Boton("Registrarse");
        btn_Registrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_Registrarse.setBounds(110, 483, 105, 21);
        btn_Registrarse.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.getControlador().crearCuenta();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
        add(btn_Registrarse);

        lbl_RecuperarContrasena = new JLabel("¿No puedes iniciar sesión?");
        lbl_RecuperarContrasena.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_RecuperarContrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.ocultarCrearCuenta();
                contenedor.mostrarRecuperarContrasena();
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.RECUPERAR_CONTRASENA);
            }
        });
        lbl_RecuperarContrasena.setFont(new Font("Tahoma", Font.ITALIC, 9));
        lbl_RecuperarContrasena.setBounds(12, 695, 267, 15);
        add(lbl_RecuperarContrasena);

        lbl_IniciarSesion = new JLabel("¿Ya tienes cuenta?");
        lbl_IniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_IniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.ocultarCrearCuenta();
                contenedor.mostrarIniciarSesion();
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.INICIAR_SESION);
            }
        });
        lbl_IniciarSesion.setFont(new Font("Tahoma", Font.ITALIC, 9));
        lbl_IniciarSesion.setBounds(12, 677, 146, 15);
        add(lbl_IniciarSesion);

        JLabel lbl_FechaN = new JLabel("Fecha de nacimiento: ");
        lbl_FechaN.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 12));
        lbl_FechaN.setBounds(12, 352, 137, 14);
        add(lbl_FechaN);
        lbl_inciarSesion.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.INICIAR_SESION);
            }
        });
        lbl_recuperarContrasena.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                contenedor.getControlador().cambiarBarraLateralLogin(Paginas.RECUPERAR_CONTRASENA);
            }
        });
    }
/**
 * Este metodo conecta a la barra lateral con el JFrame que lo contiene (es como un setter)
 * @param ventanaLogin es el JFrame que contiene al objeto que queremos conectar
 */
    public void conectarVentana(VentanaLogin ventanaLogin) {
        this.contenedor = ventanaLogin;
    }

    /**
     * Metodo que compara la contraseña de los dos campos en crear cuenta
     * @return devuelve el la contraseña en caso de ser true la condicion
     * o devuelve null en cualquier otro caso.
     */
    public String getPasswordCrearCuenta() {
        if (txt_Contrasena.getText().equals(txt_ConfirmarPassword.getText())) {
            return txt_Contrasena.getText();

        } else {
            System.out.println("Las contraseñas no coinciden");
            return null;
        }
    }

    public String getApellido2() {
        return txt_Apellido2.getText();
    }

    public String getApellido1() {
        return txt_Apellido1.getText();
    }

    public String getUser() {
        return txt_Usuario_1.getText();
    }

    public String getNombre() {
        return txt_Nombre.getText();
    }

    public String getEmail() {
        return txt_Email.getText();
    }

    public Date getDate() {
        return dateChooser.getDate();
    }

    /**
     * Metodo que se utiliza cuando uno de los campos rellenados tiene un dato ya existente
     * en la base de datos, y activa la ventana emergente avisandolo
     * @param error se refiere al dato que se encuentra ya en la base de datos.
     */
    public void datoExistente(String error) {
        Paginas.CREAR_CUENTA.getGuardar().setSize(520, 300);
        Paginas.CREAR_CUENTA.getGuardar().getLblNewLabel().setSize(500, 60);
        Paginas.CREAR_CUENTA.getGuardar().getLblNewLabel().setText(error);
        Paginas.CREAR_CUENTA.getGuardar().getLblNewLabel_1().setIcon(new ImageIcon(Cambios_guardado.class.getResource("/img/icons8-error-50.png")));

        Paginas.CREAR_CUENTA.getGuardar().setVisible(true);
        ;
    }
}