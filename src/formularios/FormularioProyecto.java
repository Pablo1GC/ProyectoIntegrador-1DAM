/**
 * @author Jos� Mora
 */
package formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import clubUnico.PaginaClub_Abstract;
import utilidades.*;
import ventanaPrincipal.ContenidoPrincipal;
import ventanasEmergentes.Proyecto_Creado_Con_Exito;

public class FormularioProyecto extends ContenidoPrincipal {
	private Titulos lbl_Titulo;
	private CamposTexto txt_Nombre;
	private Titulos lbl_Nombre;
	private AreaTexto txt_AreaDescripcion;
	private Titulos lbl_Descripcion;
	private CamposTexto txt_Email;
	private Titulos lbl_Email;
	private Lineas pnlLinea;
	private JLabel lbl_Publico;
	private JCheckBox chkPublicoONo;
	private JButton btn_Crear;
	private Proyecto_Creado_Con_Exito creado;


	public FormularioProyecto() {

		setBounds(269, 64, 921, 656);
		setLayout(null);
		setBackground(Color.WHITE);

		agregarLabels();
		agregarTextFields();
		agregarBotones();

		//  Línea separadora de título
		pnlLinea = new Lineas(132, 48, 600, 1);
		add(pnlLinea);

		// Checkbox que indica si el proyecto es público o privado
		chkPublicoONo = new CheckCaja(283, 432, 21, 23);
		add(chkPublicoONo);


	}

	/**
	 * Verifica que no haya ningún campo de texto obligatorio vacío
	 */
	private void comprobarCamposVacios() {

		if (txt_Nombre.getText().length() > 0 && txt_AreaDescripcion.getText().length() > 0
				&& txt_Email.getText().length() > 0) {
			btn_Crear.setEnabled(true);
			btn_Crear.setBackground(Color.RED);
		} else {
			btn_Crear.setEnabled(false);
			btn_Crear.setBackground(Color.LIGHT_GRAY);
		}
	}

	private void agregarLabels(){
		lbl_Titulo = new Titulos("Formulario  de creación de proyecto", 132, 15, 397, 26);
		lbl_Titulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		add(lbl_Titulo);

		lbl_Nombre = new Titulos("Nombre", 132, 77, 80, 20);
		add(lbl_Nombre);

		lbl_Descripcion = new Titulos("Descripción", 132, 155, 113, 26);
		add(lbl_Descripcion);

		lbl_Email = new Titulos("Email de contacto", 132, 338, 152, 26);
		add(lbl_Email);

		lbl_Publico = new Titulos("Proyecto público", 132, 431, 144, 20);
		add(lbl_Publico);
	}

	private void agregarTextFields(){
		txt_Nombre = new CamposTexto(132, 101, 340, 22);
		add(txt_Nombre);

		//  Verifica que solo se escriban 32 caracteres como máximo
		txt_Nombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txt_Nombre.getText().length() > 32) {
					e.consume();
				}
			}
		});

		txt_AreaDescripcion = new AreaTexto(132, 184, 600, 117);
		add(txt_AreaDescripcion);


		//  Verifica que solo se escriban 500 caracteres como máximo
		txt_AreaDescripcion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txt_AreaDescripcion.getText().length() > 500) {
					e.consume();
				}
			}
		});



		txt_Email = new CamposTexto(132, 364, 294, 22);
		add(txt_Email);


		//  Verifica que solo se escriban 500 caracteres como máximo
		txt_Email.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txt_Email.getText().length() > 500) {
					e.consume();
				}
			}
		});


		//	Comprobar todos los campos cada vez que el usuario termina de escribir
		txt_Email.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				comprobarCamposVacios();
			}

		});
		txt_Nombre.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				comprobarCamposVacios();
			}

		});
		txt_AreaDescripcion.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				comprobarCamposVacios();
			}

		});
	}

	private void agregarBotones(){
		btn_Crear = new Boton("Crear proyecto", 571, 461, 161, 42);
		btn_Crear.setEnabled(false);
		btn_Crear.setBackground(Color.LIGHT_GRAY);
		btn_Crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProyecto();
				txt_AreaDescripcion.setText("");
				txt_Email.setText("");
				txt_Nombre.setText("");
				chkPublicoONo.setSelected(false);
				creado = new Proyecto_Creado_Con_Exito();
				creado.setVisible(true);
				btn_Crear.setBackground(Color.LIGHT_GRAY);
				btn_Crear.setEnabled(false);
			}
		});
		add(btn_Crear);
	}


	public void crearProyecto(){
		getControlador().crearProyecto(txt_Nombre.getText(),txt_AreaDescripcion.getText(),txt_Email.getText(), chkPublicoONo.isSelected() , PaginaClub_Abstract.getIdClub());
	}
}
