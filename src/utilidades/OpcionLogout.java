package utilidades;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ventanaPrincipal.BarraLateral;
import ventanaPrincipal.ContenidoPrincipal;
import ventanaPrincipal.VentanaPP;

public class OpcionLogout extends JPanel{
	private BarraLateral contenedor;
	private JLabel lbl_nombreBoton;
	private ContenidoPrincipal jp_contenedorAsociado;
	
	private int indice;

	public void conectarVentana(BarraLateral contenedor) {
		this.contenedor = contenedor;
		indice = aumentarNumeroDeOpciones();
	}

	public ContenidoPrincipal getContenedorAsociado() {
		return jp_contenedorAsociado;
	}

	public void setContenedorAsociado(ContenidoPrincipal contenedorAsociado) {
		this.jp_contenedorAsociado = contenedorAsociado;
	}

	public OpcionLogout(String nombreBoton, String iconoBoton, int PosicionY) {
		
		setBackground(Color.LIGHT_GRAY);

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				contenedor.getVentanaPrincipal().getContenido().getControlador().logout();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(contenedor.getOpcionSeleccionada() != indice)
					setBackground(Color.gray);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(contenedor.getOpcionSeleccionada() != indice)
					setBackground(new Color(190, 190, 200));
			}
		});

		setLayout(null);
		setBounds(0, PosicionY, 270, 35);
		setBackground(new Color(190, 190, 200));

		lbl_nombreBoton = new JLabel(nombreBoton);
		lbl_nombreBoton.setIconTextGap(10);
		lbl_nombreBoton.setIcon(new ImageIcon(VentanaPP.class.getResource(iconoBoton)));
		lbl_nombreBoton.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		lbl_nombreBoton.setBounds(25, 0, 235, 35);
		add(lbl_nombreBoton);
	}
	
	private int aumentarNumeroDeOpciones() {
		return contenedor.aumentarNumeroDeOpciones();
	}
	
	public int getIndice() {
		return indice;
	}

}
