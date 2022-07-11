package herramientasAdministrador;

import javax.swing.table.DefaultTableModel;

/**
 * Clase utilizada para definir la estructura de la tabla
 * Solicitudes de unión.
 *
 * @see AdministrarSolicitudes
 */
public class SolicitudesTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    Class[] columnTypes = new Class[]{
            String.class, String.class, String.class, String.class, String.class, Boolean.class, Integer.class, Integer.class
    };

    @Override
    public int getColumnCount() {
        return 8;
    }


    /**
     * Siempre devuelve un string vacío, ya que no queremos que aparezca
     * un nombre. Lo pondremos con labels en {@link AdministrarSolicitudes}
     * @param columnIndex  índice de la columna
     * @return String con un espacio
     */
    @Override
    public String getColumnName(int columnIndex) {
        return " ";
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 5)
            return true;

        return false;
    }

    public Class getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }
}