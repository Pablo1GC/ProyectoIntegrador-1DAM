package ventanasEmergentes;

/**
 * @author pablo
 */

import mvc.Modelo;
import utilidades.Paginas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioAyuda extends La_Ventana_Emergente_Abstract {

    private final JPanel contentPane = new JPanel();
    private JSeparator separator;
    private JLabel lbl_Titulo, lbl_Subtitulo1, lbl_Subtitulo2, lbl_Icono;
    private JTextField txt_field_Email;
    private JScrollPane sp_Texto;
    private JTextArea txt_AreaTexto;
    private JButton btn_Enviar;
    private Gracias_por_Contactar enviado;


    public FormularioAyuda(Modelo modelo) {
        this.agregarPanel();
        this.agregarTitulo();
        this.agregarSeparator();
        this.agregarSubtitulos();
        this.agregarTextos();
        this.agregarBoton();
        this.agregarIcono();
    }

    public void agregarPanel() {
        setSize(539, 355);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void agregarTitulo() {
        lbl_Titulo = new JLabel("¡Envíanos un mensaje!");
        lbl_Titulo.setForeground(Color.DARK_GRAY);
        lbl_Titulo.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 23));
        lbl_Titulo.setBounds(10, 20, 315, 32);
        contentPane.add(lbl_Titulo);
    }

    public void agregarSeparator() {
        separator = new JSeparator();
        separator.setForeground(Color.DARK_GRAY);
        separator.setBounds(10, 57, 400, 2);
        contentPane.add(separator);
    }

    public void agregarSubtitulos() {
        lbl_Subtitulo1 = new JLabel("Email de contacto");
        lbl_Subtitulo1.setFont(new Font("Open Sans", Font.BOLD, 15));
        lbl_Subtitulo1.setBounds(30, 65, 184, 13);
        contentPane.add(lbl_Subtitulo1);

        lbl_Subtitulo2 = new JLabel("¿En qué te podemos ayudar?");
        lbl_Subtitulo2.setFont(new Font("Open Sans", Font.BOLD, 15));
        lbl_Subtitulo2.setBounds(30, 116, 295, 19);
        contentPane.add(lbl_Subtitulo2);
    }

    public void agregarTextos() {
        txt_field_Email = new JTextField();
        txt_field_Email.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
        txt_field_Email.setBounds(40, 87, 370, 19);
        contentPane.add(txt_field_Email);
        txt_field_Email.setColumns(10);
        sp_Texto = new JScrollPane();
        sp_Texto.setBorder(new EmptyBorder(0, 0, 0, 0));
        sp_Texto.setBounds(40, 140, 370, 110);
        contentPane.add(sp_Texto);

        txt_AreaTexto = new JTextArea();
        txt_AreaTexto.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt_AreaTexto.getText().length() >= 600) {
                    e.consume();
                }
            }
        });
        sp_Texto.setViewportView(txt_AreaTexto);
        txt_AreaTexto.setWrapStyleWord(true);
        txt_AreaTexto.setLineWrap(true);
        txt_AreaTexto.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
        txt_AreaTexto.setBackground(Color.WHITE);
    }

    public void agregarIcono() {
        lbl_Icono = new JLabel("");
        lbl_Icono.setIcon(new ImageIcon(FormularioAyuda.class.getResource("/img/Icon_PersonaAyuda.png")));
        lbl_Icono.setBounds(441, 160, 64, 77);
        contentPane.add(lbl_Icono);
    }

    public void agregarBoton() {
        btn_Enviar = new JButton("Enviar");
        btn_Enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                String email = txt_field_Email.getText();
                String consulta = txt_AreaTexto.getText();
                try {
                Paginas.INICIAR_SESION.getModelo().enviarConsulta(email, consulta);
                    enviado = new Gracias_por_Contactar();
                    enviado.setVisible(true);
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
        btn_Enviar.setForeground(Color.WHITE);
        btn_Enviar.setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
        btn_Enviar.setBackground(Color.RED);
        btn_Enviar.setBounds(289, 270, 121, 32);
        contentPane.add(btn_Enviar);
    }
}
