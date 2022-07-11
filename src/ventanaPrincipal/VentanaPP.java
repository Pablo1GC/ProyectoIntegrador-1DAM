package ventanaPrincipal;
/**
 * @author pablo
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import login.Contenido;
import mvc.Controlador;
import mvc.Modelo;
import mvc.ModeloImpl;
import mvc.Vista;
import utilidades.Paginas;


public class VentanaPP extends JFrame implements Vista{
	public final static int ancho = 1190;
	public final static int alto = 720;
	
	private ventanaPrincipal.BarraInvisible barraSuperior;
	private ContenidoPrincipal contenedorPrincipal;
	private BarraSuperiorApp barraSuperiorApp;

	private BarraLateral barraLateral;
	private JLabel lbl_BotonCerrar, lbl_BotonMinimizar;
	
	private Modelo modelo;
	private Controlador controlador;
 

	public VentanaPP(Controlador controlador) {
		this.controlador = controlador;
		setResizable(false);
		setUndecorated(true);
		setBounds(0, 0, ancho, alto);
		
		barraSuperior = new BarraInvisible(this);
		barraSuperiorApp = new BarraSuperiorApp();
		barraLateral = new BarraLateral(this);
		barraLateral.setVentanaPrincipal(this);
		contenedorPrincipal = new ContenidoPrincipal();
		
		agregarMinimizar();
		agregarCerrar();
		
		getContentPane().add(barraSuperior);
		getContentPane().add(barraSuperiorApp);
		getContentPane().add(barraLateral);
		
	}
	


	
	public BarraLateral getBarraLateral() {
		return barraLateral;
	}

	public void agregarMinimizar() {
		lbl_BotonMinimizar = new JLabel("_");
		lbl_BotonMinimizar.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 14));
		this.add(lbl_BotonMinimizar);
		lbl_BotonMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_BotonMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(Frame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_BotonMinimizar.setForeground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_BotonMinimizar.setForeground(Color.white);
			}
		});
		lbl_BotonMinimizar.setForeground(Color.white);
		lbl_BotonMinimizar.setBounds(1145, -5, 25, 23);
		
	}

	public void agregarCerrar() {
		lbl_BotonCerrar = new JLabel("X");
		lbl_BotonCerrar.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 14));
		this.add(lbl_BotonCerrar);
		lbl_BotonCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_BotonCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelo.cerrarConexion();
				System.exit(0);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_BotonCerrar.setForeground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_BotonCerrar.setForeground(Color.white);
			}
		});
		lbl_BotonCerrar.setForeground(Color.white);
		lbl_BotonCerrar.setBounds(1170, 0, 25, 23);
	}


	
	
	public void conectarPanel(ContenidoPrincipal panel) {
		contenedorPrincipal.removeAll();
		Paginas.SOLICITUDES_PENDIENTES_CLUBS.reiniciarSolicitudes();
		Paginas.SOLICITUDES_PENDIENTES_CLUBS.getModelo().getSolicitudesPendientesClubs();
		Paginas.SOLICITUDES_PENDIENTES_PROYECTOS.reiniciarSolicitudes();
		Paginas.SOLICITUDES_PENDIENTES_PROYECTOS.getModelo().getSolicitudesPendientesProyecto();
		Paginas.MIS_PROYECTOS.reiniciarMisProyectos();
		Paginas.MIS_PROYECTOS.getControlador().obtenerResumenMisProyectos();
		Paginas.MIS_CLUBS.reiniciarMisClubs();
		Paginas.MIS_CLUBS.getControlador().obtenerResumenMisClubs();
		contenedorPrincipal.repaint();
		contenedorPrincipal.revalidate();
		contenedorPrincipal.add(panel);
		panel.setFrame(this);
		
		getContentPane().add(contenedorPrincipal);
		
	}
	
	public void seleccionarOpcion(int index) {
		barraLateral.seleccionarOpcion(index);
	}

	@Override
	public Modelo getModelo() {
		return modelo;
	}

	public Controlador getControlador() {
		return controlador;
	}

	
	public void setModelo(Modelo m) {
		this.modelo = m;
	}
	
	public void setControlador(Controlador c) {
		this.controlador = c;
	}
	
	public ContenidoPrincipal getContenido() {
		return contenedorPrincipal;
	}
	


	
	
}
