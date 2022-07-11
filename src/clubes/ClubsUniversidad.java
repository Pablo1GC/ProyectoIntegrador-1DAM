/**
 * @author Jos� Mora
 */
package clubes;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import ventanaPrincipal.ContenidoPrincipal;

public class ClubsUniversidad extends ContenidoPrincipal {
	private ArrayList<CartaClubsUniversidad> arrayCartas;
	private JScrollPane scrScrollPane;
	private JPanel jp_Contenedor;
	
	public ClubsUniversidad() {
		setBackground(Color.WHITE);
		scrScrollPane = new JScrollPane();
		scrScrollPane.setBounds(0, 0, 922, 658);
		add(scrScrollPane);
		arrayCartas = new ArrayList<>();
		jp_Contenedor = new JPanel();
		scrScrollPane.setViewportView(jp_Contenedor);
		jp_Contenedor.setLayout(null);
		jp_Contenedor.setBackground(Color.WHITE);
	}


	//	Método para posicionar la carta en filas de 3.
	public void posicionarCartas() {
		
		int x = 36;
		int y = 27;		
		int diferenciaX = 280;
		int diferenciaY = 300;
		int cnt = 1;
		
		//	A la primera, colocarla en la posición inicial
		arrayCartas.get(0).setLocation(x, y);
		arrayCartas.get(0).setContenidoPrincipal(this);
		arrayCartas.get(0).setListenerVerMas();
		jp_Contenedor.add(arrayCartas.get(0));
		cnt++;
		
		for (int i = 1; i < arrayCartas.size() ; i++, cnt++) {
			
			//	Si es la cuarta de la fila, ponerla más abajo y en la posición inicial de X
			if (cnt == 4) {
				y += diferenciaY;
				x = 36;
				cnt = 1;
			} 
			//	Si no hay que bajarla, hay que moverla a la derecha
			else {
				x += diferenciaX;
			}		
			//	A cada una le damos la posición calculada y la agregamos al panel
			arrayCartas.get(i).setLocation(x, y);
			arrayCartas.get(i).setContenidoPrincipal(this);
			arrayCartas.get(i).setListenerVerMas();
			jp_Contenedor.add(arrayCartas.get(i));
		}		
	}

	
	public void establecerCartas() {
		this.arrayCartas = this.modelo.getCartasClubs();
		posicionarCartas();
			
		int posUltimaCarta 	  = arrayCartas.get(arrayCartas.size() - 1).getY();
		int tamanoUltimaCarta = arrayCartas.get(arrayCartas.size() - 1).getHeight();
		
		int altoContenido = posUltimaCarta + tamanoUltimaCarta + 58;
		jp_Contenedor.setPreferredSize(new Dimension(0, altoContenido));


		/*
			"invokeLater" permite que el scrollbar se actualize solo después de que
			las cartas hayan sido añadidas. De esta manera el scrollbar empezará
			al inicio de la página, y no al final.
		 */
		javax.swing.SwingUtilities.invokeLater(() -> scrScrollPane.getVerticalScrollBar().setValue(0));
	}
}
