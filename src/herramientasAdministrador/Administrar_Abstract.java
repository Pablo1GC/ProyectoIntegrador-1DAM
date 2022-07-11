package herramientasAdministrador;
/**
 * @author pablo
 */
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ventanaPrincipal.ContenidoPrincipal;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public abstract class Administrar_Abstract extends ContenidoPrincipal {

    protected JPanel jp_tablaAdmin;
    protected JButton btn_1, btn_2;
    protected JScrollPane scrollPane;
    protected JTable table_Usuarios;

    protected DefaultTableModel model;
    protected JLabel lbl_Rol_Estudios;

    public Administrar_Abstract() {
        this.agregarTablaAdmin();
        this.agregarSeparator();
        this.agregarScrollTabla();
        this.agregarFondoTabla();
        this.setVisible(true);
    }

    public void agregarTablaAdmin() {
        jp_tablaAdmin = new JPanel();
        jp_tablaAdmin.setBackground(Color.WHITE);
        jp_tablaAdmin.setBounds(0, 0, 921, 656);
        jp_tablaAdmin.setLayout(null);
        this.add(jp_tablaAdmin);
        this.setVisible(true);
    }



    public void agregarSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.DARK_GRAY);
        separator.setBounds(76, 135, 800, 2);
        jp_tablaAdmin.add(separator);
    }

    public void agregarScrollTabla() {
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBorder(null);
        scrollPane.setBounds(76, 150, 800, 372);
        jp_tablaAdmin.add(scrollPane);

    }

    public void agregarFondoTabla() {

        table_Usuarios = new JTable();
        table_Usuarios.setSelectionForeground(new Color(255, 255, 255));
        table_Usuarios.setForeground(new Color(0, 0, 0));
        table_Usuarios.setShowHorizontalLines(false);
        table_Usuarios.setShowGrid(false);
        table_Usuarios.setRowHeight(30);
        table_Usuarios.setGridColor(Color.LIGHT_GRAY);
        table_Usuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table_Usuarios.setBorder(null);
        table_Usuarios.setBackground(new Color(255, 255, 255));
        table_Usuarios.setShowVerticalLines(false);
        table_Usuarios.setSelectionBackground(new Color(255, 0, 0));




		//Para centrar los datos de la tabla
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table_Usuarios.setDefaultRenderer(String.class, centerRenderer);



		table_Usuarios.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		table_Usuarios.setBounds(0, 0, 800, 372);
		scrollPane.setViewportView(table_Usuarios);
	}
}
