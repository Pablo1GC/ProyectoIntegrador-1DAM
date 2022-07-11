/**
 * @author Jos� Mora
 */
package ventanasEmergentes;

import usuarioRegistrado.PerfilUsuario;
import utilidades.Boton;
import utilidades.Lineas;
import utilidades.Titulos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CambiarClave extends JDialog {
    private final JPanel jp_ContentPane = new JPanel();
    private String contrasenaNueva;
    private Titulos lbl_Cambia;
    private Lineas linea;
    private Titulos lbl_Actual;
    private CamposContrasenas txt_Actual;
    private Titulos lbl_Nueva;
    private CamposContrasenas txt_Nueva;
    private Titulos lbl_Confirma;
    private CamposContrasenas txt_Confirma;
    private Boton btn_Cambiar;
    private JLabel lbl_Foto;
    private JLabel lblError;
    private ClaveCambiada alertCambia;
    private PerfilUsuario contenedor;


    public CambiarClave(PerfilUsuario contenedor) {
        this.contenedor = contenedor;
        setResizable(false);
        int x = 162;
        setBounds(350, 130, 600, 400);
        jp_ContentPane.setBackground(Color.WHITE);
        setContentPane(jp_ContentPane);
        jp_ContentPane.setLayout(null);

        lbl_Cambia = new Titulos("Cambia tu contraseña", x, 25, 345, 26);
        lbl_Cambia.setFont(new Font("Tahoma", Font.BOLD, 18));
        getContentPane().add(lbl_Cambia);

        linea = new Lineas(x, 49, 260, 2);
        getContentPane().add(linea);

        lbl_Actual = new Titulos("Contraseña actual", x, 65, 345, 26);
        lbl_Actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lbl_Actual);

        txt_Actual = new CamposContrasenas(x, 101, 260, 22);
        getContentPane().add(txt_Actual);

        lbl_Nueva = new Titulos("Nueva contraseña", x, 134, 345, 26);
        lbl_Nueva.setBounds(x, 134, 139, 26);
        lbl_Nueva.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lbl_Nueva);

        txt_Nueva = new CamposContrasenas(x, 170, 260, 22);
        getContentPane().add(txt_Nueva);

        lbl_Confirma = new Titulos("Confirme la contraseña", x, 199, 168, 26);
        lbl_Confirma.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lbl_Confirma);

        txt_Confirma = new CamposContrasenas(x, 230, 260, 22);
        getContentPane().add(txt_Confirma);

        btn_Cambiar = new Boton("Cambiar", 261, 282, 161, 26);
        btn_Cambiar.setEnabled(false);
        btn_Cambiar.setBackground(Color.LIGHT_GRAY);
        btn_Cambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comprobarClave();
                txt_Actual.setText("");
                txt_Confirma.setText("");
                txt_Nueva.setText("");
                btn_Cambiar.setBackground(Color.LIGHT_GRAY);
                btn_Cambiar.setEnabled(false);
            }
        });
        getContentPane().add(btn_Cambiar);

        lbl_Foto = new JLabel("");
        lbl_Foto.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Foto.setIcon(new ImageIcon(CambiarClave.class.getResource("/img/cambio (1).png")));
        lbl_Foto.setBounds(x, 263, 86, 64);
        jp_ContentPane.add(lbl_Foto);

        lblError = new JLabel("Las contraseñas no coinciden");
        lblError.setVisible(false);
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblError.setBounds(193, 338, 204, 14);
        jp_ContentPane.add(lblError);

        txt_Actual.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });
        txt_Confirma.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });
        txt_Nueva.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });

    }

    private void comprobarCamposVacios() {

        String contrasenaActual = String.valueOf(txt_Actual.getPassword());
        if (contrasenaActual.length() > 0
                && String.valueOf(txt_Confirma.getPassword()).length() > 0
                && String.valueOf(txt_Nueva.getPassword()).length() > 0) {
            btn_Cambiar.setEnabled(true);
            btn_Cambiar.setBackground(Color.RED);
        } else {
            btn_Cambiar.setEnabled(false);
            btn_Cambiar.setBackground(Color.LIGHT_GRAY);
        }
    }

    public void comprobarClave() {
        contrasenaNueva = String.valueOf(txt_Confirma.getPassword());
        if (String.valueOf(txt_Confirma.getPassword()).equals(String.valueOf(txt_Nueva.getPassword()))) {

            //  Si la contraseña es correcta...
            if(contenedor.getControlador().comprobarContrasena(String.valueOf(txt_Actual.getPassword()))){
                contenedor.getControlador().actualizarContrasena(contrasenaNueva);
                lblError.setVisible(false);
                dispose();
                alertCambia = new ClaveCambiada();

                alertCambia.setVisible(true);
            } else {
                lblError.setText("La contraseña actual no es correcta");
                lblError.setVisible(true);
            }
        }else{
            lblError.setText("Las contraseñas no coinciden");
                lblError.setVisible(true);
            }
    }
}
