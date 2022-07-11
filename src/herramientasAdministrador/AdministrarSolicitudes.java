package herramientasAdministrador;

/**
 * @author pablo
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import utilidades.Paginas;
import ventanasEmergentes.solicitudUnionCancelar;
import ventanasEmergentes.solicitudUnionConfirmar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdministrarSolicitudes extends Administrar_Abstract {
    protected JButton btn_Clubs, btn_Proyectos;
    private solicitudUnionConfirmar confirmar;
    private solicitudUnionCancelar cancelar;

    // Para diferenciar entre las consultas de club o proyecto
    private boolean estasEnClub;



    public AdministrarSolicitudes() {
        super();
        this.agregarEtiquetasTabla();
        this.agregarBotonClubs();
        this.agregarBotonProyectos();
        this.agregarBotonEliminar();
        this.agregarBotonAdmin();
        this.estasEnClub = true;
        table_Usuarios.setModel(new SolicitudesTableModel());


        model = (DefaultTableModel) table_Usuarios.getModel();

        /**Oculta las dos últimas columnas(id club e id usuario)*/
            table_Usuarios.removeColumn(table_Usuarios.getColumnModel().getColumn(7));
            table_Usuarios.removeColumn(table_Usuarios.getColumnModel().getColumn(6));

        /**Damos el ancho a cada columna*/
            table_Usuarios.getColumnModel().getColumn(1).setPreferredWidth(160);
            table_Usuarios.getColumnModel().getColumn(5).setPreferredWidth(20);
            table_Usuarios.getColumnModel().getColumn(3).setPreferredWidth(160);


    }

    public void agregarBotonClubs() {
        btn_Clubs = new JButton("Clubs");
        btn_Clubs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Cambiar la info de la tabla a clubs
                estasEnClub = true;
                btn_Proyectos.setBackground(Color.GRAY);
                btn_Clubs.setBackground(Color.RED);
                obtenerDatosTabla();
                lbl_Rol_Estudios.setText("CLUB");
                controlador.cambiarContenido(Paginas.ADMINISTRAR_SOLICITUDES);
            }
        });
        btn_Clubs.setForeground(Color.WHITE);
        btn_Clubs.setBackground(Color.RED);
        btn_Clubs.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 10));
        btn_Clubs.setBounds(76, 44, 100, 27);
        jp_tablaAdmin.add(btn_Clubs);
    }

    public void agregarBotonProyectos() {
        btn_Proyectos = new JButton("Proyectos");
        btn_Proyectos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                estasEnClub = false;
                btn_Proyectos.setBackground(Color.RED);
                btn_Clubs.setBackground(Color.GRAY);
                obtenerDatosTabla2();
                lbl_Rol_Estudios.setText("PROYECTO");
            }
        });
        btn_Proyectos.setForeground(Color.WHITE);
        btn_Proyectos.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 10));
        btn_Proyectos.setBackground(Color.GRAY);
        btn_Proyectos.setBounds(200, 44, 100, 27);
        jp_tablaAdmin.add(btn_Proyectos);
    }

    public void agregarBotonAdmin() {
        btn_1 = new JButton("Aceptar");
        btn_1.addActionListener(e -> {
            {
                confirmar = new solicitudUnionConfirmar(this);
                confirmar.setVisible(true);
            }
        });
        btn_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controlador.cambiarContenido(Paginas.ADMINISTRAR_SOLICITUDES);
            }
        });

        btn_1.setForeground(Color.WHITE);
        btn_1.setBackground(Color.RED);
        btn_1.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 10));
        btn_1.setBounds(76, 544, 172, 27);
        jp_tablaAdmin.add(btn_1);
    }

    /**
     * Le pasa al modelo los usuarios seleccionados para que los acepte
     */
    public void confirmAceptarMiembro() {
        ArrayList<Integer> seleccion = new ArrayList<>();

        /**Selecciona todas las filas que tengan el checkbox marcado*/
        for (int i = 0; i < table_Usuarios.getRowCount(); i++) {
            boolean estaSeleccionado = (boolean) model.getValueAt(i, 5);

            if (estaSeleccionado) {
                seleccion.add(i);
            }
        }

        int[] seleccionadosUsr = new int[seleccion.size()];
        int[] seleccionadosClub = new int[seleccion.size()];

        /**Recoge el id del club/proyecto y el id del usuario de todas las filas seleccionadas,
         *  los guarda en arrays y se los pasa al modelo*/

        for (int i = 0; i < seleccion.size(); i++) {
            seleccionadosUsr[i] = (int) model.getValueAt(seleccion.get(i), 7);
            seleccionadosClub[i] = (int) model.getValueAt(seleccion.get(i), 6);
        }
        if (estasEnClub) {
            getModelo().aceptarUsuariosClub(seleccionadosUsr, seleccionadosClub);
            obtenerDatosTabla();
        } else {
            getModelo().aceptarUsuariosProyecto(seleccionadosUsr, seleccionadosClub);
            obtenerDatosTabla2();
        }
    }

    /**
     * Le pasa al modelo los usuarios seleccionados para que los elimine
     */

    public void confirmRechazarMiembro() {
        ArrayList<Integer> seleccion = new ArrayList<>();

        /**Selecciona todas las filas que tengan el checkbox marcado*/
        for (int i = 0; i < table_Usuarios.getRowCount(); i++) {
            boolean estaSeleccionado = (boolean) model.getValueAt(i, 5);

            if (estaSeleccionado) {
                seleccion.add(i);
            }
        }

        int[] seleccionadosUsr = new int[seleccion.size()];
        int[] seleccionadosClub = new int[seleccion.size()];

        /**Recoge el id del club/proyecto y el id del usuario de todas las filas seleccionadas,
         *  los guarda en arrays y se los pasa al modelo*/

        for (int i = 0; i < seleccion.size(); i++) {
            seleccionadosUsr[i] = (int) model.getValueAt(seleccion.get(i), 7);
            seleccionadosClub[i] = (int) model.getValueAt(seleccion.get(i), 6);
        }
        if (estasEnClub) {
            getModelo().rechazarUsuariosClub(seleccionadosUsr, seleccionadosClub);
            obtenerDatosTabla();
        } else {
            getModelo().rechazarUsuariosProyecto(seleccionadosUsr, seleccionadosClub);
            obtenerDatosTabla2();
        }
    }

    public void agregarBotonEliminar() {
        btn_2 = new JButton("Rechazar");
        btn_2.addActionListener(e -> {
            {
                cancelar = new solicitudUnionCancelar(this);
                cancelar.setVisible(true);
            }
        });

        btn_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controlador.cambiarContenido(Paginas.ADMINISTRAR_SOLICITUDES);
            }
        });

        btn_2.setForeground(Color.WHITE);
        btn_2.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 10));
        btn_2.setBackground(Color.GRAY);
        btn_2.setBounds(700, 544, 172, 27);
        jp_tablaAdmin.add(btn_2);
    }

    public void obtenerDatosTabla() {
        getModelo().imprimirTablaUnionClub();
    }

    public void obtenerDatosTabla2() {
        getModelo().imprimirTablaUnionProyecto();
    }

    public void pintarTabla(String username, String nombre, String estudios, String club, String fecha, int codClub, int codUser) {
        model.addRow(new Object[]{username, nombre, estudios, club, fecha, false, codClub, codUser});
    }

    public void reiniciar() {
        int rowCount = model.getRowCount();

        // Borra todas las columnas una por una
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void agregarEtiquetasTabla() {
        JLabel lbl_Usuario = new JLabel("USUARIO");
        lbl_Usuario.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Usuario.setForeground(Color.DARK_GRAY);
        lbl_Usuario.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
        lbl_Usuario.setBounds(55, 110, 168, 19);
        jp_tablaAdmin.add(lbl_Usuario);

        JLabel lbl_Nombre = new JLabel("NOMBRE");
        lbl_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Nombre.setForeground(Color.DARK_GRAY);
        lbl_Nombre.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
        lbl_Nombre.setBounds(210, 110, 168, 19);
        jp_tablaAdmin.add(lbl_Nombre);

        JLabel lbl_Estudios = new JLabel("ESTUDIOS");
        lbl_Estudios.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Estudios.setForeground(Color.DARK_GRAY);
        lbl_Estudios.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
        lbl_Estudios.setBounds(325, 110, 230, 19);
        jp_tablaAdmin.add(lbl_Estudios);

        // Si es la p�gina de Solicitudes de Union cambiar ROL por Club/Proyecto
        lbl_Rol_Estudios = new JLabel("CLUB");
        lbl_Rol_Estudios.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Rol_Estudios.setForeground(Color.DARK_GRAY);
        lbl_Rol_Estudios.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
        lbl_Rol_Estudios.setBounds(500, 110, 184, 19);
        jp_tablaAdmin.add(lbl_Rol_Estudios);

        // Si es la p�gina de Solicitudes de Union cambiar FechaAlta por FechaSolicitud
        JLabel lbl_Fecha = new JLabel("FECHA SOLICITUD");
        lbl_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Fecha.setForeground(Color.DARK_GRAY);
        lbl_Fecha.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
        lbl_Fecha.setBounds(685, 110, 141, 19);
        jp_tablaAdmin.add(lbl_Fecha);
    }

}

