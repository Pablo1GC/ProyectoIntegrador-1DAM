package herramientasAdministrador;

/**
 * Clase usada para almacenar información del usuario
 * en la tabla {@link UsuarioTableModel}.
 */
public class Usuario {
    private String  username;
    private String  nombre;
    private String  fecha_alta;
    private boolean admin;
    private int cod_registrado;
    private boolean isSelected;


    public Usuario(int cod_registrado, String username, String nombre, String fecha_alta, boolean esAdmin){
        this.username       = username;
        this.nombre         = nombre;
        this.fecha_alta     = fecha_alta;
        this.admin          = esAdmin;
        this.cod_registrado = cod_registrado;
    }


    //  Si está seleccionado en la tabla
    public boolean isSelected() {return isSelected;}

    public void setSelected(boolean selected) {isSelected = selected;}

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public int getCod_registrado() { return cod_registrado;}

    public void setAdmin(boolean b) {
        this.admin = b;
    }
}
