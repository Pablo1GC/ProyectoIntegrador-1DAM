package clubUnico;

import mvc.Modelo;
import proyectoUnico.PaginaProyectoRegistradoSolicita;
import proyectoUnico.PaginaProyectos;
import utilidades.Paginas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CajaProyectosClub extends JPanel {
    protected JPanel jp_Contenedor_de_Proyecto_1, jp_ContenedorBtnVerProyecto_1;
    protected JButton btn_VerProyecto_1;
    protected JTextArea txt_DefinicionProyecto_1;
    protected JLabel lbl_tituloNoticia_1;
    protected int idProyecto;


    protected static Modelo.TipoDeUsuario tipoDeUsuario;

    public CajaProyectosClub(int y, String titulo, String tema, int codProyecto) {
        this.idProyecto = codProyecto;
        this.setLayout(null);
        this.anadirCajita(y);
        this.anadirContBotonVerProyecto();
        this.anadirBotonVerProyecto();
        this.anadirDefinicionProyecto(tema);
        this.anadirTituloNoticia(titulo);
    }


    public void anadirCajita(int y) {
        jp_Contenedor_de_Proyecto_1 = new JPanel();
        jp_Contenedor_de_Proyecto_1.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(10, y, 754, 58);
    }

    public void anadirContBotonVerProyecto() {
        jp_ContenedorBtnVerProyecto_1 = new JPanel();
        jp_ContenedorBtnVerProyecto_1.setLayout(null);
        jp_ContenedorBtnVerProyecto_1.setBorder(new LineBorder(new Color(255, 0, 0), 0));
        jp_ContenedorBtnVerProyecto_1.setBackground(Color.WHITE);
        jp_ContenedorBtnVerProyecto_1.setBounds(586, 40, 138, 14);
        this.add(jp_ContenedorBtnVerProyecto_1);

    }


    public void anadirBotonVerProyecto() {
        btn_VerProyecto_1 = new JButton("Ver proyecto");
        btn_VerProyecto_1.setForeground(Color.WHITE);
        btn_VerProyecto_1.setFont(new Font("Dialog", Font.BOLD, 10));
        btn_VerProyecto_1.setBorder(null);
        btn_VerProyecto_1.setBackground(Color.RED);
        btn_VerProyecto_1.setBounds(22, 0, 95, 15);

        /*
            Ya que al crear la caja de proyectos de un club, se conoce el controlador,
            podemos crear el listener del botón ver más directamente
         */
        btn_VerProyecto_1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.getControlador().cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
                PaginaProyectoRegistradoSolicita proyecto = new PaginaProyectoRegistradoSolicita();
                proyecto.imprProyecto(idProyecto);
                Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.getModelo().obtenerTipoDeUsuarioProyecto(idProyecto);


                switch (tipoDeUsuario){
                    case MIEMBRO -> {
                        proyecto.imprProyectoRegistrado(idProyecto);
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.getControlador().cambiarContenido(Paginas.PAGINA_PROYECTO_MIEMBRO);
                    }
                    case OBSERVADOR -> {
                        proyecto.imprProyectoRegistrado(idProyecto);
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.getControlador().cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.mostrarBotonSolicitar();
                    }
                    case SOLICITA -> {
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.getControlador().cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
                        Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.ocultarBotonSolicitar();
                    }
                }
                PaginaProyectos.setIdProyecto(idProyecto);
            }
        });
        jp_ContenedorBtnVerProyecto_1.add(btn_VerProyecto_1);
    }

    public void anadirTituloNoticia(String titulo) {
        lbl_tituloNoticia_1 = new JLabel(titulo);
        lbl_tituloNoticia_1.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_tituloNoticia_1.setBounds(10, 0, 240, 14);
        this.add(lbl_tituloNoticia_1);
    }

    public void anadirDefinicionProyecto(String tema) {
        txt_DefinicionProyecto_1 = new JTextArea();
        txt_DefinicionProyecto_1.setWrapStyleWord(true);
        txt_DefinicionProyecto_1.setText(tema);
        txt_DefinicionProyecto_1.setFont(new Font("Dialog", Font.PLAIN, 10));
        txt_DefinicionProyecto_1.setEditable(false);
        txt_DefinicionProyecto_1.setBorder(null);
        txt_DefinicionProyecto_1.setBounds(10, 21, 724, 37);
        this.add(txt_DefinicionProyecto_1);
    }

    public static void setTipoDeUsuario(Modelo.TipoDeUsuario Miembro) {
        tipoDeUsuario = Miembro;
    }
}


