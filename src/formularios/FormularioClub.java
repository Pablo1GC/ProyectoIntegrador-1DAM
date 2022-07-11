/**
 * @author José Mora
 */
package formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import utilidades.AreaTexto;
import utilidades.Boton;
import utilidades.CamposTexto;
import utilidades.Lineas;
import utilidades.Opciones;
import utilidades.Titulos;
import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.ClubCreado;

public class FormularioClub extends ContenidoPrincipal {

    private CamposTexto txt_Nombre;
    private Titulos lbl_Nombre;
    private JLabel lbl_Titulo;
    private Lineas pnlLinea;
    private Opciones cmbLocalizacion;
    private Titulos lbl_Localizacion;
    private CamposTexto txtEmail;
    private Titulos lbl_Email;
    private AreaTexto txtAreaDescripcion;
    private Titulos lbl_Descripcion;
    private JLabel lbl_FotoLogo;
    private JLabel lbl_Logo;
    private Boton btn_Seleccionar;
    private Boton btn_Solicitar;
    private ClubCreado crear;

    String direccion;


    public FormularioClub() {
        setBounds(269, 64, 921, 656);
        setLayout(null);
        setBackground(Color.WHITE);

        agregarLabels();
        agregarTextFields();
        agregarBotones();


        //  Línea separadora de título
        pnlLinea = new Lineas(132, 48, 600, 1);
        add(pnlLinea);

        cmbLocalizacion = new Opciones(521, 100, 211, 22);
        cmbLocalizacion.setModel(new DefaultComboBoxModel(
                new String[]{"Villaviciosa de Odón", "Valencia", "Canarias", "Alcobendas"}));
        add(cmbLocalizacion);

        lbl_FotoLogo = new JLabel("");
        lbl_FotoLogo.setIcon(new ImageIcon(FormularioClub.class.getResource("/img/foto.png")));
        lbl_FotoLogo.setBounds(521, 366, 67, 20);
        add(lbl_FotoLogo);

    }

    /**
     * Verifica que no haya ningún campo de texto obligatorio vacío
     */
    private void comprobarCamposVacios() {

        if (txt_Nombre.getText().length() > 0
                && txtAreaDescripcion.getText().length() > 0
                && txtEmail.getText().length() > 0) {
            btn_Solicitar.setEnabled(true);
            btn_Solicitar.setBackground(Color.RED);
        } else {
            btn_Solicitar.setEnabled(false);
            btn_Solicitar.setBackground(Color.LIGHT_GRAY);
        }

    }

    private void agregarBotones(){
        btn_Seleccionar = new Boton("Seleccionar archivo", 571, 361, 161, 26);

        //  Seleccionar una foto al presionar el botón
        btn_Seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Imagen", "jpg", "png", "gif", "jpeg");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Escogiste abrir este archovo: " +
                            chooser.getSelectedFile().getName());
                    direccion = chooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
        add(btn_Seleccionar);

        btn_Solicitar = new Boton("Solicitar creación", 571, 461, 161, 42);
        btn_Solicitar.setBackground(Color.LIGHT_GRAY);
        btn_Solicitar.setEnabled(false);
        btn_Solicitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getControlador().enviarEmailCrearClub(txtEmail.getText(), txt_Nombre.getText(), txtAreaDescripcion.getText(), cmbLocalizacion.getSelectedItem(), direccion);
                txtAreaDescripcion.setText("");
                txtEmail.setText("");
                txt_Nombre.setText("");
                cmbLocalizacion.setSelectedIndex(0);
                crear = new ClubCreado();
                crear.setVisible(true);
                btn_Solicitar.setEnabled(false);
                btn_Solicitar.setBackground(Color.LIGHT_GRAY);
            }
        });
        add(btn_Solicitar);
    }

    private void agregarLabels(){
        lbl_Titulo = new Titulos("Formulario  de creación de Club", 132, 15, 345, 26);
        lbl_Titulo.setBounds(132, 15, 422, 26);
        lbl_Titulo.setFont(new Font("Tahoma", Font.BOLD, 21));
        add(lbl_Titulo);

        lbl_Nombre = new Titulos("Nombre", 132, 77, 80, 20);
        add(lbl_Nombre);

        lbl_Localizacion = new Titulos("Localización", 521, 78, 124, 19);
        add(lbl_Localizacion);

        lbl_Descripcion = new Titulos("Descripción", 132, 155, 113, 26);
        add(lbl_Descripcion);

        lbl_Email = new Titulos("Email de contacto", 132, 338, 152, 26);
        add(lbl_Email);

        lbl_Logo = new Titulos("Logo", 521, 341, 102, 20);
        add(lbl_Logo);

    }

    private void agregarTextFields(){
        txt_Nombre = new CamposTexto(132, 101, 294, 22);
        add(txt_Nombre);

        //  Verifica que solo se escriban 32 caracteres como máximo
        txt_Nombre.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Nombre.getText().length() > 32) {
                    e.consume();
                }
            }
        });

        txtAreaDescripcion = new AreaTexto(132, 184, 600, 117);
        add(txtAreaDescripcion);

        //  Verifica que solo se escriban 500 caracteres como máximo
        txtAreaDescripcion.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtAreaDescripcion.getText().length() > 500) {
                    e.consume();
                }
            }
        });


        txtEmail = new CamposTexto(132, 364, 294, 22);
        txtEmail.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });

        //  Verifica que solo se escriban 32 caracteres como máximo
        txtEmail.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtEmail.getText().length() > 32) {
                    e.consume();
                }
            }
        });
        txt_Nombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });
        txtAreaDescripcion.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });
        add(txtEmail);
    }


}
