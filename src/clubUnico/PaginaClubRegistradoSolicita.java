/*
 * @Author:SamuelOrtega
 *
 */
package clubUnico;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class PaginaClubRegistradoSolicita extends PaginaClub_Abstract {

	protected JScrollPane sp_Proyectos;

	protected JPanel jp_ContenedorProyectos;

	private ArrayList<JPanel> arrayProyectos_Club;
	protected CajaProyectosClub proyecto;


	public PaginaClubRegistradoSolicita() {

		super();
		arrayProyectos_Club = new ArrayList<>();
		scrollPanePrincipal.setSize(921, 656);
		sp_Proyectos = new JScrollPane();
		jp_ContenedorProyectos = new JPanel();
		this.agregarTitulosCajas();
		this.agregarContenedorProyectos();
		this.agregarBtnSolicitarUnirme();
		scrollToTop();
	}

	public void agregarTitulosCajas() {

		JLabel lbl_TituloClub_en_Noticias = new JLabel("Proyectos abiertos");
		lbl_TituloClub_en_Noticias.setBounds(100, 263, 293, 25);
		lbl_TituloClub_en_Noticias.setFont(new Font("Roboto", Font.BOLD, 18));
		lbl_TituloClub_en_Noticias.setForeground(Color.RED);
		jp_BackGround.add(lbl_TituloClub_en_Noticias);

	}

	public void agregarProyecto(String titulo, String tema, int idProyecto) {
		int y;
		if (arrayProyectos_Club.size() > 0) {
			y = arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getY() + arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getHeight() + 20;
			int jp_AltoContent = arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getY()
					+ arrayProyectos_Club.get(arrayProyectos_Club.size() - 1).getHeight() + 15;
			jp_ContenedorProyectos.setPreferredSize(new Dimension(820, jp_AltoContent));
		} else {
			y = 20;
		}
		proyecto = new CajaProyectosClub(y, titulo, tema,idProyecto);
		arrayProyectos_Club.add(proyecto);
		jp_ContenedorProyectos.add(proyecto);
		int alt = proyecto.getHeight() + proyecto.getY() + 8;

		JSeparator separador = new JSeparator();
		separador.setBounds(10, alt, 754, 2);
		separador.setForeground(Color.BLACK);
		jp_ContenedorProyectos.add(separador);

	}
	public void agregarContenedorProyectos() {
		sp_Proyectos.setBounds(58, 295, 800, 196);
		sp_Proyectos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_Proyectos.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		sp_Proyectos.setAutoscrolls(true);
		jp_BackGround.add(sp_Proyectos);

		jp_ContenedorProyectos.setPreferredSize(new Dimension(500, 194));
		sp_Proyectos.setViewportView(jp_ContenedorProyectos);

		jp_ContenedorProyectos.setLayout(null);

	}


	public void agregarBtnSolicitarUnirme() {
		btnSolicitarUnirme.setVisible(true);
		jp_BtnSolicitarUnirme.setVisible(true);
	}

    //Reinicia el array de proyectos de un club anterior
    public void reiniciarProyectos() {
        arrayProyectos_Club.clear();
        jp_ContenedorProyectos.removeAll();
        jp_ContenedorProyectos.setBounds(10, 0, 0, 0);
    }



}