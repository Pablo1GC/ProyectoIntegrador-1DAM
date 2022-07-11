/**
 * @author Jos� Mora
 */
package formularios;

import utilidades.*;
import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.Publicacion_Exitosa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class NuevoPost extends ContenidoPrincipal {
    private Titulos lbl_Titulo;
    private Titulos lbl_Titulo_2;
    private Titulos lbl_PublicarEN;
    private Titulos lbl_Contenido;
    private Opciones cmbPublicarEn;
    private CamposTexto txt_Titulo;
    private AreaTexto txt_AreaContenido;
    private Lineas pnlLinea;
    private Boton btn_Publicar;
    private Publicacion_Exitosa publicado;

    private ArrayList<String> opcionesClubsYProyectos;
    private HashMap<String, Integer> clubs;
    private HashMap<String, Integer> proyectos;


    public NuevoPost() {
        opcionesClubsYProyectos = new ArrayList<>();
        setBounds(269, 64, 921, 656);
        setLayout(null);
        setBackground(Color.WHITE);

        agregarLabels();
        agregarTextFields();
        agregarBotonPublicar();
        agregarComboBoxCyP();


        //  Línea separadora de título
        pnlLinea = new Lineas(132, 48, 600, 1);
        add(pnlLinea);


        cmbPublicarEn.setBounds(132, 105, 290, 20);
        add(cmbPublicarEn);



    }

    private void agregarLabels(){
        lbl_Titulo = new Titulos("Nuevo Post", 132, 15, 121, 26);
        lbl_Titulo.setBounds(132, 15, 149, 26);
        lbl_Titulo.setFont(new Font("Tahoma", Font.BOLD, 21));
        add(lbl_Titulo);

        lbl_PublicarEN = new Titulos("Publicar en", 132, 77, 121, 20);
        add(lbl_PublicarEN);

        lbl_Titulo_2 = new Titulos("Título", 132, 154, 80, 20);
        add(lbl_Titulo_2);

        lbl_Contenido = new Titulos("Contenido de la noticia", 132, 155, 113, 26);
        lbl_Contenido.setSize(197, 26);
        lbl_Contenido.setLocation(132, 232);
        add(lbl_Contenido);
    }

    private void agregarTextFields(){
        txt_Titulo = new CamposTexto(132, 177, 294, 22);
        add(txt_Titulo);


        //  Verifica que se escriban 60 caracteres como máximo
        txt_Titulo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_Titulo.getText().length() > 60) {
                    e.consume();
                }
            }
        });



        txt_AreaContenido = new AreaTexto(132, 184, 600, 117);
        txt_AreaContenido.setLocation(132, 259);
        add(txt_AreaContenido);


        //  Verifica que se escriban 4000 caracteres como máximo
        txt_AreaContenido.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_AreaContenido.getText().length() > 4000) {
                    e.consume();
                }
            }
        });

        txt_AreaContenido.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });
        txt_Titulo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                comprobarCamposVacios();
            }

        });
    }

    private void agregarBotonPublicar(){
        btn_Publicar = new Boton("Publicar", 571, 419, 161, 42);
        btn_Publicar.setEnabled(false);
        btn_Publicar.setBackground(Color.LIGHT_GRAY);
        btn_Publicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //  Si se selecciona un proyecto...
                if (cmbPublicarEn.getSelectedItem().toString().charAt(0) == 'P') {
                    System.out.println(proyectos.get(cmbPublicarEn.getSelectedItem()) + txt_Titulo.getText() + txt_AreaContenido.getText());
                    getModelo().crearNuevoPostProyecto((int) (proyectos.get(cmbPublicarEn.getSelectedItem())), txt_Titulo.getText(), txt_AreaContenido.getText());
                    Paginas.MIS_PROYECTOS.reiniciarMisProyectos();
                    getControlador().obtenerResumenMisProyectos();

                } else {
                    getModelo().crearNuevoPostClub((int) (clubs.get(cmbPublicarEn.getSelectedItem())), txt_Titulo.getText(), txt_AreaContenido.getText());
                    Paginas.MIS_CLUBS.reiniciarMisClubs();
                    getControlador().obtenerResumenMisClubs();

                }
                txt_AreaContenido.setText("");
                txt_Titulo.setText("");
                publicado = new Publicacion_Exitosa();
                publicado.setVisible(true);
                btn_Publicar.setBackground(Color.LIGHT_GRAY);
                btn_Publicar.setEnabled(false);
            }
        });
        add(btn_Publicar);
    }


    public void agregarClubsAdministrados(HashMap<String, Integer> clubs) {
        this.clubs = clubs;
        opcionesClubsYProyectos.addAll(clubs.keySet());

        // Refrescar combobox
        cmbPublicarEn.setModel(new DefaultComboBoxModel(opcionesClubsYProyectos.toArray()));
    }

    public void agregarProyectosAdministrados(HashMap<String, Integer> proyectos) {
        this.proyectos = proyectos;
        opcionesClubsYProyectos.addAll(proyectos.keySet());

        // Refrescar combobox
        cmbPublicarEn.setModel(new DefaultComboBoxModel(opcionesClubsYProyectos.toArray()));
    }

    private void agregarComboBoxCyP() {
        opcionesClubsYProyectos = new ArrayList<>();
        cmbPublicarEn = new Opciones(132, 108, 211, 22);

        cmbPublicarEn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cmbPublicarEn.setModel(new DefaultComboBoxModel(opcionesClubsYProyectos.toArray()));
    }

    /**
     * Verifica que no haya ningún campo de texto obligatorio vacío
     */
    private void comprobarCamposVacios() {
        if (txt_AreaContenido.getText().length() > 0 && txt_Titulo.getText().length() > 0) {
            btn_Publicar.setEnabled(true);
            btn_Publicar.setBackground(Color.RED);
        } else {
            btn_Publicar.setEnabled(false);
            btn_Publicar.setBackground(Color.LIGHT_GRAY);
        }
    }
}
