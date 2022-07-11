package mvc;
/**
 * @author Jose
 */
import login.BarraLateralLogin;
import login.Contenido;
import ventanaPrincipal.ContenidoPrincipal;

import java.io.IOException;

public interface Controlador {	
	
	public Vista getVista();
	
	public void setVista(Vista vista);
	
	public Modelo getModelo();
	
	public void setModelo(Modelo modelo);

	/**
	 * Cambia de vista mientras se haya iniciado sesión.
	 *
	 * @param panel La vista que se quiere colocar.
	 */
	public void cambiarContenido(ContenidoPrincipal panel);

	/**
	 * Cambia de vista cuando no se haya iniciado sesión.
	 *
	 * @param panel La vista que se quiere colocar.
	 */
	public void cambiarContenidoLogin(Contenido panel);

	/**
	 * Cambia de estado la barra lateral cuando no
	 * se ha iniciado sesión (Iniciar sesión, registrarse,
	 * No recuerda su contraseña).
	 *
	 * @param panel
	 */
	public void cambiarBarraLateralLogin(BarraLateralLogin panel);

	/**
	 * Obtiene la información del usuario cuando
	 * ha iniciado sesión exitosamente.
	 */
	public void acceder();

	/**
	 * Comprueba los datos e inicia sesión.
	 */
	public void login() throws IOException;

	public boolean comprobarContrasena(String contrasena);

	public void actualizarContrasena(String nuevaContrasena);

	public void logout();

	public void crearCuenta();

    public void eliminarCuenta();

    public boolean userIsAdmin();

    public void permanecerLogeado(String usr, String pass);

	public boolean recuperarContrasena(String text);

	public void abandonarClub(int id_club);

	public void obtenerResumenMisClubs();

	public void abandonarProyecto(int idProyecto);

	public void obtenerResumenMisProyectos();

	public void getSolicitudesPendientesClubs();

	public void getSolicitudesPendientesProyecto();

	public void getUsuariosEnClub(int cod_club);

	public void getUsuariosEnProyecto(int cod_proyecto);

	public  void crearProyecto( String titulo, String lbl_descripcion,String email, boolean privado,int idClub);

	public void enviarEmailCrearClub(String Email, String Nombre, String Descripcion, Object Localizacion, String URLimagen);



}
