package ventanaPrincipal;
/**
 * @author Franco y Pablo
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BarraInvisible extends JPanel {
	private static final long serialVersionUID = 1L;
	int xMouse, yMouse;
	JFrame contenedor;
	JPanel jp_contenedorX, jp_contenedorMin;
	JLabel lbl_cerrarIcon, lbl_minimizarIcon;

	public void conectarVentana(JFrame contenedor) {
		this.contenedor = contenedor;
	}

	public BarraInvisible(JFrame VentanaContenedora) {
		contenedor = VentanaContenedora;
//		setOpaque(true);
		setBackground(new Color(0,0,0,0));
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				contenedor.setLocation(x - xMouse, y - yMouse);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		this.setBounds(0, 0, VentanaPP.ancho, 44);
		setLayout(null);

		jp_contenedorX = new JPanel();
		jp_contenedorX.setBounds(1162, 0, 28, 28);
		jp_contenedorX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		contenedorX.setOpaque(false);
		jp_contenedorX.setBackground(new Color(0,0,0,0));
		this.add(jp_contenedorX);
		jp_contenedorX.setLayout(null);
	}
}