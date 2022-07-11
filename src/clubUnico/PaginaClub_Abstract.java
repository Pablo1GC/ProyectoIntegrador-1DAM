/*
 * @Author:SamuelOrtega
 *
 */
package clubUnico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.ClubUnido;

public abstract class PaginaClub_Abstract extends ContenidoPrincipal {

    protected JScrollPane scrollPanePrincipal;
    protected JPanel jp_BackGround;
    protected JPanel jp_Descripcion;
    protected JLabel lbl_Icono;
    protected JTextArea txt_Descripcion;
    protected JPanel jp_NombreClub;
    protected JSeparator separator_principal;
    protected JPanel jp_Contenedor_Iconos_Abajo;
    protected JPanel jp_ContenedorProyectos;
    private JLabel lbl_NombreClub;
    JLabel lbl_iconoCorreo;
    JLabel lbl_txtIntegrantes;
    JLabel lblNewLabel_4_5;
    JLabel lbl_txtCorreo;
    JLabel lbl_iconoUbi;
    JLabel lbl_txtUbi;

    private ClubUnido unido;
    protected JPanel jp_BtnSolicitarUnirme;
    protected JButton btnSolicitarUnirme, btnSoliPendiente;

    protected ArrayList<JPanel> arrayNoticias;

    // Debe ser estático para poder acceder a él desde afuera
    protected static int idClub;

    public PaginaClub_Abstract() {
        arrayNoticias = new ArrayList<>();
        jp_Contenedor_Iconos_Abajo = new JPanel();
        lbl_Icono = new JLabel();
        lbl_iconoCorreo = new JLabel();
        lbl_txtIntegrantes = new JLabel();
        lblNewLabel_4_5 = new JLabel();
        lbl_txtCorreo = new JLabel();
        lbl_iconoUbi = new JLabel();
        lbl_txtUbi = new JLabel();
        this.setBackground(new Color(190, 190, 200));
        this.setLayout(null);
        this.agregarScrollPanelBase();
        this.agregarPanelBaseDelScrollPrincipal();
        this.agregarContenedorDescripcionClub();
        this.agregarNombreDelClub();
        this.agregarSeparadorPrincipal();
        this.setVisible(true);
        this.scrollToTop();
        this.agregarBotonesSolicitudes();
  }


    public void agregarScrollPanelBase() {
        jp_BackGround = new JPanel();
        jp_ContenedorProyectos = new JPanel();
        scrollPanePrincipal = new JScrollPane();
        scrollPanePrincipal.setBackground(Color.WHITE);
        scrollPanePrincipal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePrincipal.setAutoscrolls(true);
        scrollPanePrincipal.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
        scrollPanePrincipal.setBounds(0, 0, 910, 618);
        this.add(scrollPanePrincipal);
    }

    public void agregarPanelBaseDelScrollPrincipal() {
        jp_BackGround = new JPanel();
        jp_BackGround.setBackground(Color.WHITE);
        scrollPanePrincipal.setViewportView(jp_BackGround);
        jp_BackGround.setLayout(null);
    }

    public void agregarIconoClub(InputStream imagen) {
        try {
            lbl_Icono.setIcon(new ImageIcon(ImageIO.read(imagen)));
            imagen.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lbl_Icono.setBounds(-68, 11, 317, 211);
        jp_BackGround.add(lbl_Icono);
    }

    public void agregarContenedorDescripcionClub() {
        jp_Descripcion = new JPanel();
        jp_Descripcion.setBounds(280, 85, 561, 137);
        jp_Descripcion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, Color.GRAY));
        jp_BackGround.add(jp_Descripcion);
        jp_Descripcion.setLayout(null);
        txt_Descripcion = new JTextArea();
        txt_Descripcion.setLineWrap(true);
        txt_Descripcion.setMargin(new Insets(2, 10, 2, 10));
        txt_Descripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.GRAY));
        txt_Descripcion.setFont(new Font("Dialog", Font.PLAIN, 18));
        txt_Descripcion.setWrapStyleWord(true);
        txt_Descripcion.setEditable(false);
        txt_Descripcion.setBounds(0, 0, 561, 137);
        jp_Descripcion.add(txt_Descripcion);
    }

    public void agregarNombreDelClub() {
        jp_NombreClub = new JPanel();
        jp_NombreClub.setBounds(430, 11, 300, 51);
        jp_NombreClub.setBackground(Color.WHITE);
        jp_NombreClub.setAlignmentX(CENTER_ALIGNMENT);
        jp_NombreClub.setBorder(new LineBorder(new Color(128, 128, 128), 50, true));
        jp_BackGround.add(jp_NombreClub);
        jp_NombreClub.setLayout(null);

        lbl_NombreClub = new JLabel();
        lbl_NombreClub.setForeground(Color.WHITE);
        lbl_NombreClub.setFont(new Font("Roboto", Font.BOLD, 25));
        lbl_NombreClub.setBackground(Color.WHITE);
        lbl_NombreClub.setBounds(0, 11, 300, 29);
        lbl_NombreClub.setHorizontalAlignment(SwingConstants.CENTER);

        jp_NombreClub.add(lbl_NombreClub);
    }

    public void agregarSeparadorPrincipal() {
        separator_principal = new JSeparator();
        separator_principal.setBounds(58, 255, 816, 12);
        separator_principal.setForeground(Color.RED);
        jp_BackGround.add(separator_principal);
    }

    public void establecerInfoClub(String titulo, String descripcion, String contacto, String localizacion, InputStream foto, int nMiembros) {
        if (titulo.length()<20){
            lbl_NombreClub.setBounds(0, 11, 300, 29);
            lbl_NombreClub.setFont(new Font("Roboto", Font.BOLD, 25));

        }else {
            jp_NombreClub.setBounds(380, 11, 350, 51);
            lbl_NombreClub.setBounds(0, 11, 350, 29);
            lbl_NombreClub.setFont(new Font("Roboto", Font.BOLD, 18));
        }
        lbl_NombreClub.setText(titulo);
        txt_Descripcion.setText(descripcion);
        agregarIconosFinales(contacto, localizacion, nMiembros);
        agregarIconoClub(foto);
    }

    //  Agrega los íconos de ubicación, correo y número de miembros
    public void agregarIconosFinales(String contacto, String localizacion, int nMiembros) {
        int y = 500;
        jp_Contenedor_Iconos_Abajo.setBackground(Color.WHITE);
        jp_Contenedor_Iconos_Abajo.setBounds(58, 915, 790, 67);
        jp_BackGround.add(jp_Contenedor_Iconos_Abajo);
        jp_BackGround.setLayout(null);

        lbl_iconoUbi.setIcon(new ImageIcon(PaginaClubClubDeAdministrador.class.getResource("/img/ubica.png")));
        lbl_iconoUbi.setBounds(52, y, 57, 55);
        jp_BackGround.add(lbl_iconoUbi);

        lbl_txtUbi.setText(localizacion);
        lbl_txtUbi.setFont(new Font("Roboto", Font.PLAIN, 15));
        lbl_txtUbi.setBounds(98, y + 20, 100, 29);
        jp_BackGround.add(lbl_txtUbi);

        lbl_iconoCorreo.setIcon(new ImageIcon(PaginaClubClubDeAdministrador.class.getResource("/img/icons8-nuevo-post-35.png")));
        lbl_iconoCorreo.setBounds(265, y + 12, 97, 44);
        jp_BackGround.add(lbl_iconoCorreo);

        lbl_txtCorreo.setText(contacto);
        lbl_txtCorreo.setFont(new Font("Roboto", Font.PLAIN, 15));
        lbl_txtCorreo.setBounds(309, y + 20, 300, 29);
        jp_BackGround.add(lbl_txtCorreo);

        lbl_txtIntegrantes.setIcon(new ImageIcon(PaginaClub_Abstract.class.getResource("/img/icons8-conferencia-de-primer-plano-seleccionado-50.png")));
        lbl_txtIntegrantes.setBounds(587 + 70, y + 20, 50, 29);
        jp_BackGround.add(lbl_txtIntegrantes);

        lblNewLabel_4_5.setFont(new Font("Roboto", Font.PLAIN, 15));
        lblNewLabel_4_5.setText("Nº Miembros: " + nMiembros);
        lblNewLabel_4_5.setBounds(647 + 70, y + 25, 114, 18);
        jp_BackGround.add(lblNewLabel_4_5);





        // Establecer la posición correcta de los íconos finales cuando el
        // usuario es miembro del club.

        y = 0;
        jp_Contenedor_Iconos_Abajo.setBounds(58, 938, 790, 67);
        jp_Contenedor_Iconos_Abajo.setLayout(null);
        jp_BackGround.add(jp_Contenedor_Iconos_Abajo);

        lbl_iconoUbi.setBounds(0, y, 57, 55);
        jp_Contenedor_Iconos_Abajo.add(lbl_iconoUbi);

        lbl_txtUbi.setText(localizacion);
        lbl_txtUbi.setBounds(46, y + 20, 100, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtUbi);

        lbl_iconoCorreo.setBounds(213, y + 12, 97, 44);
        jp_Contenedor_Iconos_Abajo.add(lbl_iconoCorreo);

        lbl_txtCorreo.setBounds(252, y + 20, 300, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtCorreo);

        lbl_txtIntegrantes.setBounds(535 + 70, y + 20, 50, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtIntegrantes);


        lblNewLabel_4_5.setBounds(590 + 70, y + 25, 114, 18);
        jp_Contenedor_Iconos_Abajo.add(lblNewLabel_4_5);
    }



    /*
        "invokeLater" permite que el scrollbar se actualize solo después de que
        las cartas hayan sido añadidas. De esta manera el scrollbar empezará
        al inicio de la página, y no al final.
    */
    public void scrollToTop() {
        javax.swing.SwingUtilities.invokeLater(() -> scrollPanePrincipal.getVerticalScrollBar().setValue(0));
    }

    //  Agrega los botones de Solicitar Unirme y Solicitud Pendiente
    public void agregarBotonesSolicitudes() {
        jp_BtnSolicitarUnirme = new JPanel();
        jp_BtnSolicitarUnirme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jp_BtnSolicitarUnirme.setBackground(Color.WHITE);
        jp_BtnSolicitarUnirme.setBorder(new LineBorder(new Color(255, 0, 0), 0));
        jp_BtnSolicitarUnirme.setBounds(404, 569, 135, 44);
        jp_BackGround.add(jp_BtnSolicitarUnirme);
        jp_BtnSolicitarUnirme.setLayout(null);

        btnSolicitarUnirme = new JButton("Solicitar Unirme");
        btnSolicitarUnirme.setForeground(Color.WHITE);
        btnSolicitarUnirme.setFont(new Font("Dialog", Font.PLAIN, 15));
        btnSolicitarUnirme.setBorder(null);
        btnSolicitarUnirme.setBackground(Color.RED);
        btnSolicitarUnirme.setBounds(0, 0, 134, 44);
        jp_BtnSolicitarUnirme.add(btnSolicitarUnirme);
        btnSolicitarUnirme.setVisible(false);

        btnSoliPendiente = new JButton("Solicitud Pendiente");
        btnSoliPendiente.setForeground(Color.WHITE);
        btnSoliPendiente.setFont(new Font("Dialog", Font.PLAIN, 15));
        btnSoliPendiente.setBorder(null);
        btnSoliPendiente.setBackground(Color.RED);
        btnSoliPendiente.setBounds(0, 0, 134, 44);
        jp_BtnSolicitarUnirme.add(btnSoliPendiente);
        btnSoliPendiente.setVisible(false);
        btnSolicitarUnirme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unido = new ClubUnido();
                unido.setVisible(true);
                getModelo().solicitarUnirseClub(idClub);
                ocultarBotonSolicitar();
            }
        });
        jp_BtnSolicitarUnirme.setVisible(false);
        System.out.println(idClub);
    }

    public void mostrarBotonSolicitar() {
        jp_BtnSolicitarUnirme.setBackground(Color.RED);
        btnSolicitarUnirme.setVisible(true);
        btnSolicitarUnirme.setBackground(Color.RED);
        btnSoliPendiente.setVisible(false);
        btnSoliPendiente.setBackground(Color.gray);
    }

    public void ocultarBotonSolicitar() {
        jp_BtnSolicitarUnirme.setBackground(Color.RED);
        btnSolicitarUnirme.setVisible(false);
        btnSolicitarUnirme.setBackground(Color.RED);
        btnSoliPendiente.setVisible(true);
        btnSoliPendiente.setBackground(Color.gray);
        btnSoliPendiente.setEnabled(false);
    }


    public static void setIdClub(int id) {
        idClub = id;
    }

    public static int getIdClub() {
        return idClub;
    }
}
