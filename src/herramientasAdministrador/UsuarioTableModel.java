package herramientasAdministrador;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return usuarios.size();
    }


    /**
     * Siempre devuelve un string vacío, ya que no queremos que aparezca
     * un nombre. Lo pondremos con labels en {@link AdministrarSolicitudes}
     * @param columnIndex  índice de la columna
     * @return String con un espacio
     */
    public String getColumnName(int columnIndex) {
        return " ";
    }

    Class[] columnTypes = new Class[]{
            String.class, String.class, String.class, String.class, Boolean.class
    };

    public Class getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);

        switch (columnIndex) {
            case 0: return usuario.getUsername();
            case 1: return usuario.getNombre();
            case 2: return usuario.getFecha_alta();
            case 3: return usuario.isAdmin() ? "Admin" : "Miembro";
            case 4: return usuario.isAdmin() ? null    : usuario.isSelected();
        }
        return null;

    }

    public void setValueAt(Object value, int rowIndex, int columnIndex){
        Usuario usuario = usuarios.get(rowIndex);

        switch (columnIndex) {
            case 4: usuario.setSelected((boolean) value);
        }
    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public ArrayList<Integer> getFilasSeleccionadas(){
        ArrayList<Integer> filas = new ArrayList<>();

        for (int i = 0; i < usuarios.size() ; i++){
            if (usuarios.get(i).isSelected())
                filas.add(i);
        }

        return filas;
    }


}