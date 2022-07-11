package ventanaPrincipal;

/**
 * @author pablo
 *
 * Base de todas nuestras "vistas". Permite cambiar de
 * pantalla al tener todas vinculadas.
 *
 */
import javax.swing.JPanel;

import mvc.Controlador;
import mvc.Modelo;
import mvc.Vista;

public class ContenidoPrincipal extends JPanel implements Vista{
	protected static Modelo modelo;
	protected static Controlador controlador;
	protected static VentanaPP frame;


	

	public ContenidoPrincipal() {
		setBounds(269, 64, 921, 656);
		setLayout(null);

	}
	
	
	
	


	public static void setModelo(Modelo m) {
		modelo = m;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public Controlador getControlador() {
		return controlador;
	}
	
	public static void setControlador(Controlador c) {
		controlador = c;
	}
	
	public static void setFrame(VentanaPP f) {
		frame = f;
	}
	
	public VentanaPP getFrame() {
		return this.frame;
	}








}
