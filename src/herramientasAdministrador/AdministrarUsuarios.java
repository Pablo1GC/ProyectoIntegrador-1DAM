package herramientasAdministrador;
/**
 * @author pablo
 */
import java.awt.*;

import javax.swing.*;

import ventanasEmergentes.Eliminar_Usuarios;
import ventanasEmergentes.AscenderAdministrador;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class AdministrarUsuarios extends Administrar_Abstract {
	private JComboBox cBox_ClubsyProy;
	private Eliminar_Usuarios eliminar_usr;
	private AscenderAdministrador ascender;

	private ArrayList<String> checkOpciones;
	private HashMap<String, Integer> clubs;
	private HashMap<String, Integer> proyectos;

	private ArrayList<Usuario> usuarios;

	boolean esTablaClub;
	int cod_asociado;


	public AdministrarUsuarios() {
		super();

		this.usuarios = new ArrayList<>();

		this.clubs = new HashMap<>();
		this.proyectos = new HashMap<>();

		this.agregarComboBoxCyP();
		this.agregarBotonEliminar();
		this.agregarBotonAdmin();
		this.agregarEtiquetasTabla();
		this.inicializarTabla();




		this.esTablaClub = false;
		this.cod_asociado = -1;
	}

	private void inicializarTabla() {
		table_Usuarios.setModel(new UsuarioTableModel(usuarios));
		establecerEspacioTabla();
		agregarListenerCheckbox();
	}




	private void establecerEspacioTabla(){
		table_Usuarios.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_Usuarios.getColumnModel().getColumn(1).setPreferredWidth(300);
		table_Usuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_Usuarios.getColumnModel().getColumn(3).setPreferredWidth(60);
	}

	public void agregarEtiquetasTabla() {
		JLabel lbl_Usuario = new JLabel("Username");
		lbl_Usuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Usuario.setForeground(Color.DARK_GRAY);
		lbl_Usuario.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
		lbl_Usuario.setBounds(80, 110, 168, 19);
		jp_tablaAdmin.add(lbl_Usuario);

		JLabel lbl_Nombre = new JLabel("Nombre");
		lbl_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nombre.setForeground(Color.DARK_GRAY);
		lbl_Nombre.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
		lbl_Nombre.setBounds(350, 110, 168, 19);
		jp_tablaAdmin.add(lbl_Nombre);

		JLabel lbl_Estudios = new JLabel("Fecha Alta");
		lbl_Estudios.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Estudios.setForeground(Color.DARK_GRAY);
		lbl_Estudios.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
		lbl_Estudios.setBounds(565, 110, 230, 19);
		jp_tablaAdmin.add(lbl_Estudios);

		// Si es la página de Solicitudes de Union cambiar ROL por Club/Proyecto
		lbl_Rol_Estudios = new JLabel("Rol");
		lbl_Rol_Estudios.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rol_Estudios.setForeground(Color.DARK_GRAY);
		lbl_Rol_Estudios.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 15));
		lbl_Rol_Estudios.setBounds(705, 110, 184, 19);
		jp_tablaAdmin.add(lbl_Rol_Estudios);


	}

	private void agregarComboBoxCyP() {
		checkOpciones = new ArrayList<>();
		cBox_ClubsyProy = new JComboBox();
		cBox_ClubsyProy.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {

					//	Si empieza por "Club:", obtener usuarios del club
					if(e.getItem().toString().charAt(0) == 'C'){
						cod_asociado = clubs.get(e.getItem().toString());
						controlador.getUsuariosEnClub(cod_asociado);
						esTablaClub = true;


					} else{
						cod_asociado = proyectos.get(e.getItem().toString());
						controlador.getUsuariosEnProyecto(cod_asociado);
						esTablaClub = false;
					}
				}
			}
		});
		cBox_ClubsyProy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cBox_ClubsyProy.setBorder(null);
		cBox_ClubsyProy.setModel(new DefaultComboBoxModel(checkOpciones.toArray()));
		cBox_ClubsyProy.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		cBox_ClubsyProy.setForeground(Color.WHITE);
		cBox_ClubsyProy.setBackground(Color.GRAY);
		cBox_ClubsyProy.setBounds(76, 48, 340, 27);
		jp_tablaAdmin.add(cBox_ClubsyProy);
	}
	
	public void agregarBotonAdmin() {
		btn_1 = new JButton("Ascender a Administrador");
		btn_1.setForeground(Color.WHITE);
		btn_1.setBackground(Color.RED);
		btn_1.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 10));
		btn_1.setBounds(76, 544, 172, 27);
		btn_1.addActionListener(e -> {
				ascender = new AscenderAdministrador(this);
				ascender.setVisible(true);
		});
		jp_tablaAdmin.add(btn_1);
	}

	public void agregarBotonEliminar() {
		btn_2 = new JButton("Eliminar Usuario");
		btn_2.setForeground(Color.WHITE);
		btn_2.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 10));
		btn_2.setBackground(Color.GRAY);
		btn_2.setBounds(700, 544, 172, 27);
		jp_tablaAdmin.add(btn_2);
		
		btn_2.addActionListener(e -> {
			eliminar_usr = new Eliminar_Usuarios(this);
			eliminar_usr.setVisible(true);

		});
	}

	public void agregarClubsAdministrados(HashMap<String, Integer> clubs){
		this.clubs = clubs;
		checkOpciones.addAll(clubs.keySet());

		// Refrescar combobox
		cBox_ClubsyProy.setModel(new DefaultComboBoxModel(checkOpciones.toArray()));
	}

	public void agregarProyectosAdministrados(HashMap<String, Integer> proyectos){
		this.proyectos = proyectos;
		checkOpciones.addAll(proyectos.keySet());

		// Refrescar combobox
		cBox_ClubsyProy.setModel(new DefaultComboBoxModel(checkOpciones.toArray()));
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;

		// Refrescar tabla
		table_Usuarios.setModel(new UsuarioTableModel(usuarios));
		establecerEspacioTabla();
		table_Usuarios.repaint();

	}

	public void eliminarUsuariosSeleccionados(){
		for (int row : ((UsuarioTableModel) table_Usuarios.getModel()).getFilasSeleccionadas()){
			int cod_registrado = usuarios.get(row).getCod_registrado();
			usuarios.remove(usuarios.get(row));

			if(esTablaClub){
				int cod_club = cod_asociado;
				modelo.eliminarUsuarioDeClub(cod_registrado, cod_club);

			} else {
				int cod_proyecto = cod_asociado;
				modelo.eliminarUsuarioDeProyecto(cod_registrado, cod_proyecto);
			}
		}
		setUsuarios(usuarios);
	}

	public void ascenderUsuariosSeleccionados(){

		for (int row : ((UsuarioTableModel) table_Usuarios.getModel()).getFilasSeleccionadas()){
			int cod_registrado = usuarios.get(row).getCod_registrado();
			usuarios.get(row).setAdmin(true);

			if(esTablaClub){
				int cod_club = cod_asociado;
				modelo.ascenderUsuarioClub(cod_registrado, cod_club);

			} else {
				int cod_proyecto = cod_asociado;
				modelo.ascenderUsuarioProyecto(cod_registrado, cod_proyecto);
			}
		}
		setUsuarios(usuarios);
	}

	/**
	 *  Obtiene las tablas de administrar usuarios, solo si es admin.
	 */
	public void obtenerTablaPorPrimeraVez(){
		if (modelo.userIsAdmin()){
			String primeraOpcion = checkOpciones.get(0);

			//	Si empieza por "Club:", obtener usuarios del club
			if(primeraOpcion.charAt(0) == 'C'){
				cod_asociado = clubs.get(primeraOpcion);
				controlador.getUsuariosEnClub(cod_asociado);
				esTablaClub = true;


			} else{
				cod_asociado = proyectos.get(primeraOpcion);
				controlador.getUsuariosEnProyecto(cod_asociado);
				esTablaClub = false;
			}

		}
	}

	private void agregarListenerCheckbox(){
		table_Usuarios.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] rows = table_Usuarios.getSelectedRows();
				int[] cols = table_Usuarios.getSelectedColumns();

				if (cols[0] != 4)
					return;

				for (int index : rows){
					Boolean estaSeleccionado = (Boolean) table_Usuarios.getModel().getValueAt(index, 4);

					if (estaSeleccionado != null){

						//	Invertir selección
						estaSeleccionado = !estaSeleccionado;

						table_Usuarios.getModel().setValueAt(estaSeleccionado, index, 4);
					}
				}

				table_Usuarios.repaint();
				table_Usuarios.getSelectionModel().clearSelection();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}



}
