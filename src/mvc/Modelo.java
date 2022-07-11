package mvc;

/**
 * @author Franco
 */

import clubes.CartaClubsUniversidad;

import java.util.ArrayList;

public interface Modelo {

    public boolean userIsAdmin();

    public void setAdmin(boolean isAdmin);

    public void login(String usr, String passwd, boolean permanecerLogueado);

    public String getUsername();

    public String getApellido1();

    public String getApellido2();

    /**
     * Obtiene los datos de perfil de un usuario
     *
     * @param username Nickname.
     */
    public void obtenerInfoPerfilUsuario(String username);

    public void crearCuenta(String usr, String nombre, String ape1, String ape2, String email, String pass, java.sql.Date fecha);

    /**
     * En {@link usuarioRegistrado.PerfilUsuario}, establece los
     * nuevos datos del usuario (Email y estudios).
     *
     * @param email Email
     * @param estudios Estudios
     */
    public void actualizarDatosUsuario(String email, String estudios);

    public String getNombreUsuario();

    public void imprimirTablaUnionClub();

    public void imprimirTablaUnionProyecto();

    public ArrayList<CartaClubsUniversidad> getCartasClubs();

    public ResultadoLogin getResultado();

    public boolean enviarConsulta(String email, String consulta);

    public Vista getVista();

    public void setVista(Vista vista);

    public void obtenerDatosClub(int idClub);

    public void obtenerDatosProyecto(int idProyecto);

    public void obtenerResumenMisClubs();

    public void obtenerResumenMisProyectos();

    public void getSolicitudesPendientesClubs();

    public void getSolicitudesPendientesProyecto();

    public void obtenerCartaProyectosAbiertos();

    public void establecerDatosUsuario();

    public void actualizarContrasena(String nuevaContrasena);

    public void crearNuevoPostClub(int cod_club, String titulo, String contenido);

    public void crearNuevoPostProyecto(int cod_proyecto, String titulo, String contenido);

    public void eliminarCuenta();

    public void abandonarClub(int cod_proyecto);

    public void abandonarProyecto(int cod_proyecto);

    public boolean comprobarContrasena(String contrasena);

    /**
     * Obtiene todos los usuarios en un club, para poder administrarlos
     * @param cod_club
     */
    public void getUsuariosEnClub(int cod_club);

    /**
     * Obtiene todos los usuarios en un proyecto, para poder administrarlos
     * @param cod_proyecto
     */
    public void getUsuariosEnProyecto(int cod_proyecto);

    public void aceptarUsuariosClub(int[] selectionUsr, int[] selectionClub);

    public void rechazarUsuariosClub(int[] selectionUsr, int[] selectionClub);

    public void aceptarUsuariosProyecto(int[] selectionUsr, int[] selectionClub);

    public void rechazarUsuariosProyecto(int[] selectionUsr, int[] selectionClub);

    /**
     * Guardar si el usuario es miembro de un club, solicita unirse, o es administrador del club.
     * @param idClub
     */
    public void obtenerTipoDeUsuarioClub(int idClub);

    /**
     * Guardar si el usuario es miembro de un proyecto, solicita unirse, o es administrador del proyecto.
     * @param idProyecto
     */
    public void obtenerTipoDeUsuarioProyecto(int idProyecto);

    public void esAdminClub(int idClub);

    /**
     * Convierte en administrador de un club a un usuario.
     *
     * @param cod_registrado Código del usuario
     * @param cod_club Código del club
     */
    public void ascenderUsuarioClub(int cod_registrado, int cod_club);

    /**
     * Convierte en administrador de un proyecto a un usuario.
     *
     * @param cod_registrado Código del usuario
     * @param cod_proyecto Código del proyecto
     */
    public void ascenderUsuarioProyecto(int cod_registrado, int cod_proyecto);

    public void solicitarUnirseClub(int idClub);

    public void solicitarUnirseProyecto(int idProyecto);

    public void eliminarUsuarioDeClub(int cod_registrado, int cod_club);

    public void eliminarUsuarioDeProyecto(int cod_registrado, int cod_proyecto);

    public void crearProyecto( String titulo, String lbl_descripcion,String email, boolean privado,int idClub);

    public boolean recuperarContrasena(String email);

    /**
     * Guarda los datos del usuario en un fichero para poder mantenerse logueado.
     *
     * @param usr Username
     * @param pass Contraseña
     */
    public void escribirFicheroLoguear(String usr, String pass);

    public void borrarFicheroLoguear();

    /**
     * Comprueba si el usuario seleccionó el checkbox de permanecer logueado.
     */
    public void comprobarPermanecerLogueado();

    public void enviarEmailCrearClub(String email, String nombre, String descripcion, Object localizacion, String urLimagen);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void cerrarConexion();


    // Establece los resultados posibles al iniciar sesión.
    public static enum ResultadoLogin {
        CORRECTO, CERRAR, INCORRECTO
    }


    /**
        Si el usuario es miembro o solicita unirse a un club o proyecto,
        p solo es alguien que observa sin unirse.
     */
    public static enum TipoDeUsuario{
        MIEMBRO, OBSERVADOR, SOLICITA
    }

}
