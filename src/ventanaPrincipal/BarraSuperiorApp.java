package ventanaPrincipal;
/**
 * @author pablo
 */
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BarraSuperiorApp extends JPanel{
	
	protected final int width = 1190;
	protected final int height = 65;
	protected final int posicionX = 0;
	protected final int posicionY = 0;

	public BarraSuperiorApp () {
		setBackground(Color.RED);
		this.setBounds(posicionX, posicionY, width, height);
		this.setLayout(null);
		
		JLabel lbl_IconoUEClubs = new JLabel("");
		lbl_IconoUEClubs.setIcon(new ImageIcon(BarraSuperiorApp.class.getResource("/img/output-onlinepngtools.png")));
		lbl_IconoUEClubs.setBounds(10, 10, 225, 45);
		add(lbl_IconoUEClubs);
		this.setVisible(true);

	}
}
