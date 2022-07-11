package mvc;
/**
 * @author Jose
 *
 * Base de todas las clases de Contenido Principal
 *
 * @see ventanaPrincipal.ContenidoPrincipal
 */

public interface Vista {

	public Modelo getModelo();
	
	public static void setModelo(Modelo m) {
	}

	public default Controlador getControlador() {
		return null;
	}

	public static void setControlador(Controlador c) {
	}
	
}
