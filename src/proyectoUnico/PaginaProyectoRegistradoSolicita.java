/*
 * @Author:SamuelOrtega
 *
 */
package proyectoUnico;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utilidades.Paginas;
import ventanasEmergentes.ProyectoUnido;

public class PaginaProyectoRegistradoSolicita extends PaginaProyectos {

    protected JPanel jp_Contenedor_Iconos_Abajo;
    protected JPanel jp_BtnSolicitarUnirme;
    private JButton btn_SolicitarUnirme, btn_SolicitudPendiente;
    private ProyectoUnido unido;

    public PaginaProyectoRegistradoSolicita() {
        super();
        jp_Contenedor_Iconos_Abajo = new JPanel();
        scrollPanePrincipalProyecto.setSize(921, 656);
        jp_BtnSolicitarUnirme = new JPanel();
        btn_SolicitarUnirme = new JButton("Solicitar Unirme");
        btn_SolicitudPendiente = new JButton("Solicitud pendiente");
        this.agregarBtnSolicitarUnirme();
    }

    public void agregarBtnSolicitarUnirme() {
        jp_BtnSolicitarUnirme.setBounds(367, 467, 183, 42);
        jp_BackGroundProyecto.add(jp_BtnSolicitarUnirme);
        jp_BtnSolicitarUnirme.setLayout(null);


        btn_SolicitarUnirme.setForeground(Color.WHITE);
        btn_SolicitarUnirme.setFont(new Font("Dialog", Font.PLAIN, 15));
        btn_SolicitarUnirme.setBorder(null);
        btn_SolicitarUnirme.setBackground(Color.RED);
        btn_SolicitarUnirme.setBounds(0, 0, 183, 42);
        btn_SolicitarUnirme.setVisible(false);
        jp_BtnSolicitarUnirme.add(btn_SolicitarUnirme);


        btn_SolicitudPendiente.setForeground(Color.WHITE);
        btn_SolicitudPendiente.setFont(new Font("Dialog", Font.PLAIN, 15));
        btn_SolicitudPendiente.setBorder(null);
        btn_SolicitudPendiente.setBackground(Color.GRAY);
        btn_SolicitudPendiente.setBounds(22, 0, 134, 42);
        btn_SolicitudPendiente.setEnabled(false);
        btn_SolicitudPendiente.setVisible(false);
        jp_BtnSolicitarUnirme.add(btn_SolicitudPendiente);

        btn_SolicitarUnirme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unido = new ProyectoUnido();
                unido.setVisible(true);
                ocultarBotonSolicitar();
                getModelo().solicitarUnirseProyecto(idProyecto);
            }
        });
    }

    public void agregarIconosFinales(String nombreClub, String mail, int nMiembros) {
        jp_Contenedor_Iconos_Abajo.setBackground(Color.WHITE);
        jp_Contenedor_Iconos_Abajo.setBounds(48, 345, 790, 67);
        jp_BackGroundProyecto.add(jp_Contenedor_Iconos_Abajo);
        jp_Contenedor_Iconos_Abajo.setLayout(null);


        lbl_iconoUbi.setIcon(new ImageIcon(
                PaginaProyectoRegistradoSolicita.class.getResource("/img/icons8-derechos-de-autor-50.png")));
        lbl_iconoUbi.setBounds(42, 11, 100, 55);
        jp_Contenedor_Iconos_Abajo.add(lbl_iconoUbi);

        lbl_txtUbi.setText(nombreClub);
        lbl_txtUbi.setFont(new Font("Roboto", Font.PLAIN, 12));
        lbl_txtUbi.setBounds(98, 26, 140, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtUbi);


        lbl_iconoCorreo.setIcon(
                new ImageIcon(PaginaProyectoRegistradoSolicita.class.getResource("/img/icons8-nuevo-post-35.png")));
        lbl_iconoCorreo.setBounds(304, 11, 100, 55);
        jp_Contenedor_Iconos_Abajo.add(lbl_iconoCorreo);

        lbl_txtCorreo.setText(mail);
        lbl_txtCorreo.setFont(new Font("Dialog", Font.PLAIN, 15));
        lbl_txtCorreo.setBounds(344, 26, 203, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtCorreo);


        lbl_txtIntegrantes.setIcon(new ImageIcon(
                PaginaProyectos.class.getResource("/img/icons8-conferencia-de-primer-plano-seleccionado-50.png")));
        lbl_txtIntegrantes.setBounds(587, 26, 50, 29);
        jp_Contenedor_Iconos_Abajo.add(lbl_txtIntegrantes);

        lblNewLabel_4_5.setText("Nº miembros: " + nMiembros);
        lblNewLabel_4_5.setFont(new Font("Roboto", Font.PLAIN, 15));
        lblNewLabel_4_5.setBounds(647, 31, 170, 18);
        jp_Contenedor_Iconos_Abajo.add(lblNewLabel_4_5);
    }

    public void establecerInfoProyectoAbierto(String nombre, String descripcion, String clubPadre, String Contacto, int miembros) {
        if (nombre.length() < 40) {
            lbl_NombreProyecto.setBounds(-21, 10, 554, 29);
            lbl_NombreProyecto.setFont(new Font("Roboto", Font.BOLD, 25));
        } else {
            lbl_NombreProyecto.setBounds(-20, 10, 554, 29);
            lbl_NombreProyecto.setFont(new Font("Roboto", Font.BOLD, 18));
        }
        agregarIconosFinales(clubPadre, Contacto, miembros);
        lbl_NombreProyecto.setText(nombre);
        txt_DescripcionProyecto.setText(descripcion);
    }

    /**
     * Si aún no has solicitado unirte, te dará la opción
     */
    public void mostrarBotonSolicitar() {
        jp_BtnSolicitarUnirme.setBackground(Color.RED);
        btn_SolicitarUnirme.setVisible(true);
        btn_SolicitudPendiente.setVisible(false);
    }

    /**
     * Si ya has solicitado unirte, no te dará la opción
     */

    public void ocultarBotonSolicitar() {
        jp_BtnSolicitarUnirme.setBackground(Color.GRAY);
        btn_SolicitarUnirme.setVisible(false);
        btn_SolicitudPendiente.setVisible(true);
        btn_SolicitudPendiente.setEnabled(false);
    }

    /**
     * Muestra parte de la info del proyecto para los que NO son miembros
     */
    public void imprProyecto(int id) {
        controlador.getModelo().obtenerDatosProyecto(id);
        controlador.cambiarContenido(Paginas.PAGINA_PROYECTO_MIEMBRO);
    }

    /**
     * Muestra toda la info del proyecto para los que son miembros
     */
    public void imprProyectoRegistrado(int id) {
        controlador.getModelo().obtenerDatosProyecto(id);
        controlador.cambiarContenido(Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA);
    }
}
