/**
 * Esta clase contiene las preguntas que serán utilizadas
 * en la clase Faqs.
 *
 * @see faqs.Faqs
 */

package faqs;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ContenedorPreguntas extends JPanel {
	protected JLabel lbl_Pregunta;
	protected JTextArea txt_Respuesta;
	protected JButton btn_Desplegable;
	protected int estado;

	public void transparente(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		btn.setBackground(null);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}

	public ContenedorPreguntas(int x, int y, String preg, String text) {
		this.setBounds(x, y, 627, 47);
		this.setBackground(UIManager.getColor("Button.background"));
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

//		Se crea la pregunta

		lbl_Pregunta = new JLabel(preg);
		lbl_Pregunta.setBounds(10, 8, 560, 26);
		lbl_Pregunta.setFont(new Font("Tahoma", Font.PLAIN, 21));
		add(lbl_Pregunta);

//		Se crea la respuesta

		txt_Respuesta = new JTextArea();
		txt_Respuesta.setBackground(UIManager.getColor("Button.background"));
		txt_Respuesta.setVisible(false);
		txt_Respuesta.setWrapStyleWord(true);
		txt_Respuesta.setLineWrap(true);
		txt_Respuesta.setText(text);
		txt_Respuesta.setEditable(false);
		txt_Respuesta.setBounds(20, 39, 501, 40);
		add(txt_Respuesta);

//		Se crea el botón

		transparente(btn_Desplegable = new JButton(""));
		btn_Desplegable.setFocusPainted(false);
		btn_Desplegable.setBorder(null);
		btn_Desplegable.setBorderPainted(false);
		btn_Desplegable.setIcon(new ImageIcon(Faqs.class.getResource("/img/flecha-hacia-abajo.png")));
		btn_Desplegable.setBounds(528, 11, 89, 23);
		this.estado = 0;
		add(btn_Desplegable);

	}
	
	public void agregarListener(Faqs faqs, int num) {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				faqs.accionarBoton(num);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btn_Desplegable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				faqs.accionarBoton(num);
			}
		});

	}
}