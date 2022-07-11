/*
 * @Author:SamuelOrtega
 *
 */
package proyectoUnico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import ventanaPrincipal.ContenidoPrincipal;

public abstract class PaginaProyectos extends ContenidoPrincipal {

    protected JPanel contentPane;
    protected JPanel jp_Bg;
    protected JPanel panel;
    protected JScrollPane scrollPanePrincipalProyecto;
    protected JPanel jp_BackGroundProyecto;
    protected JPanel jp_DescripcionProyecto;
    protected JTextArea txt_DescripcionProyecto;
    protected JPanel jp_NombreProyecto;
    protected JSeparator separator_principal;
    protected JScrollPane sp_Noticias;
    protected JPanel jp_ContenedorCrearProyecto;
    protected JSeparator separator_deNoticias_Y_Proyectos;
    protected JScrollPane sp_Proyectos;
    protected JPanel jp_Contenedor_Iconos_Abajo;
    protected JPanel jp_ContenedorProyectos;
    protected JPanel PaneBtnSolicitarUnirme;
    protected JLabel lbl_NombreClub;
    protected JLabel lbl_NombreProyecto;
    JLabel lbl_iconoUbi;
    JLabel lbl_txtUbi;
    JLabel lbl_iconoCorreo;
    JLabel lbl_txtCorreo;
    JLabel lbl_txtIntegrantes;
    JLabel lblNewLabel_4_5;
    protected static int idProyecto;

    public PaginaProyectos() {
        jp_Contenedor_Iconos_Abajo = new JPanel();
        lbl_iconoUbi = new JLabel("");
        lbl_txtUbi = new JLabel("");
        lbl_iconoCorreo = new JLabel("");
        lbl_txtCorreo = new JLabel("");
        lbl_txtIntegrantes = new JLabel("");
        lblNewLabel_4_5 = new JLabel("");
        jp_NombreProyecto = new JPanel();
        lbl_NombreProyecto = new JLabel();


        this.setBackground(new Color(190, 190, 200));
        this.setLayout(null);
        this.agregarScrollPanelBase();
        this.agregarPanelBaseDelScrollPrincipal();
        this.agregarNombreDelClub();
        this.agregarConenedorDescripcionClub();
        this.agregarSeparadorPrincipal();
        this.setVisible(true);
    }

    public void agregarScrollPanelBase() {
        jp_BackGroundProyecto = new JPanel();
        scrollPanePrincipalProyecto = new JScrollPane();
        scrollPanePrincipalProyecto.setBackground(Color.WHITE);
        scrollPanePrincipalProyecto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePrincipalProyecto.setAutoscrolls(true);
        scrollPanePrincipalProyecto.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
        scrollPanePrincipalProyecto.setBounds(0, 0, 910, 618);
        this.add(scrollPanePrincipalProyecto);
    }

    public void agregarPanelBaseDelScrollPrincipal() {
        jp_BackGroundProyecto.setBackground(Color.WHITE);
        scrollPanePrincipalProyecto.setViewportView(jp_BackGroundProyecto);
        jp_BackGroundProyecto.setPreferredSize(new Dimension(800, 450));
//		jp_BackGround.setPreferredSize(new Dimension(600, 990));
        jp_BackGroundProyecto.setLayout(null);
    }


    public void agregarConenedorDescripcionClub() {
        jp_DescripcionProyecto = new JPanel();
        jp_DescripcionProyecto.setBounds(197, 97, 561, 132);
        jp_DescripcionProyecto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, Color.GRAY));
        jp_BackGroundProyecto.add(jp_DescripcionProyecto);
        jp_DescripcionProyecto.setLayout(null);

        txt_DescripcionProyecto = new JTextArea();
        txt_DescripcionProyecto.setLineWrap(true);
        txt_DescripcionProyecto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.GRAY));
        txt_DescripcionProyecto.setFont(new Font("Roboto", Font.PLAIN, 18));
        txt_DescripcionProyecto.setWrapStyleWord(true);
        txt_DescripcionProyecto.setEditable(false);
        txt_DescripcionProyecto.setBounds(0, 0, 561, 132);
        jp_DescripcionProyecto.add(txt_DescripcionProyecto);
    }

    public void agregarNombreDelClub() {
        jp_NombreProyecto.setBounds(188, 24, 554, 51);
        jp_NombreProyecto.setBackground(Color.WHITE);
        jp_NombreProyecto.setBorder(new LineBorder(new Color(128, 128, 128), 50, true));
        jp_BackGroundProyecto.add(jp_NombreProyecto);
        jp_NombreProyecto.setLayout(null);

        lbl_NombreProyecto.setForeground(Color.WHITE);
        lbl_NombreProyecto.setFont(new Font("Roboto", Font.BOLD, 29));
        lbl_NombreProyecto.setBounds(-21, 10, 554, 29);
        lbl_NombreProyecto.setHorizontalAlignment(SwingConstants.CENTER);
        jp_NombreProyecto.add(lbl_NombreProyecto);
    }

    public void agregarSeparadorPrincipal() {
        separator_principal = new JSeparator();
        separator_principal.setBounds(58, 255, 816, 12);
        separator_principal.setForeground(Color.RED);
        jp_BackGroundProyecto.add(separator_principal);
    }

    /**
     * Establece toda la info genérica del prooyecto y ajusta el tamaño del título según su longitud
     */
    public void establecerInfoProyecto(String nombre, String club, String descripcion, String mail, int nMiembros) {
        if (nombre.length() < 40) {
            lbl_NombreProyecto.setBounds(-21, 10, 554, 29);
            lbl_NombreProyecto.setFont(new Font("Roboto", Font.BOLD, 25));

        } else {
            lbl_NombreProyecto.setBounds(-20, 10, 554, 29);
            lbl_NombreProyecto.setFont(new Font("Roboto", Font.BOLD, 18));
        }
        lbl_NombreProyecto.setText(nombre);
        txt_DescripcionProyecto.setText(descripcion);
        agregarIconosFinales(club, mail, nMiembros);
    }

    public void agregarIconosFinales(String nombreClub, String mail, int nMiembros) {
        jp_Contenedor_Iconos_Abajo.setBackground(Color.WHITE);
        jp_Contenedor_Iconos_Abajo.setBounds(48, 535, 790, 67);
        jp_BackGroundProyecto.add(jp_Contenedor_Iconos_Abajo);
        jp_Contenedor_Iconos_Abajo.setLayout(null);


        lbl_iconoUbi.setIcon(new ImageIcon(PaginaProyectoRegistradoSolicita.class.getResource("/img/icons8-derechos-de-autor-50.png")));
        lbl_iconoUbi.setBounds(42, 11, 100, 55);
        jp_Contenedor_Iconos_Abajo.add(lbl_iconoUbi);

        lbl_txtUbi.setText(nombreClub);
        lbl_txtUbi.setFont(new Font("Roboto", Font.PLAIN, 12));
        lbl_txtUbi.setBounds(98, 26, 140, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtUbi);


        lbl_iconoCorreo.setIcon(new ImageIcon(PaginaProyectoRegistradoSolicita.class.getResource("/img/icons8-nuevo-post-35.png")));
        lbl_iconoCorreo.setBounds(304, 11, 100, 55);
        jp_Contenedor_Iconos_Abajo.add(lbl_iconoCorreo);

        lbl_txtCorreo.setText(mail);
        lbl_txtCorreo.setFont(new Font("Dialog", Font.PLAIN, 15));
        lbl_txtCorreo.setBounds(344, 26, 203, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtCorreo);


        lbl_txtIntegrantes.setIcon(new ImageIcon(PaginaProyectos.class.getResource("/img/icons8-conferencia-de-primer-plano-seleccionado-50.png")));
        lbl_txtIntegrantes.setBounds(587, 26, 50, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtIntegrantes);

        lblNewLabel_4_5.setText("Nº miembros: " + nMiembros);
        lblNewLabel_4_5.setFont(new Font("Roboto", Font.PLAIN, 15));
        lblNewLabel_4_5.setBounds(647, 31, 114, 18);
        jp_Contenedor_Iconos_Abajo.add(lblNewLabel_4_5);
    }

    public static void setIdProyecto(int id) {
        idProyecto = id;
    }

}
