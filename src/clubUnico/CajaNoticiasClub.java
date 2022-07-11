package clubUnico;

import javax.swing.*;
import java.awt.*;


public class CajaNoticiasClub extends JPanel {
    protected JPanel jp_CajitaNoticia_2_1;
    protected JLabel lbl_TituloNoticia_1, lbl_AutorNoticia_1, lbl_FechaNoticia_1;
    protected JTextArea txt_Noticia_1;

    public CajaNoticiasClub(int y, String titulo, String noticia, String autor, String fecha) {
        this.setLayout(null);
        this.anadirCajita(y);
        this.anadirAutor(autor);
        this.anadirTitulo(titulo);
        this.anadirFecha(fecha);
        this.anadirTema(noticia);
    }

    public void anadirCajita(int y) {
        jp_CajitaNoticia_2_1 = new JPanel();
        jp_CajitaNoticia_2_1.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(10, y, 754, 58);
        add(jp_CajitaNoticia_2_1);
    }

    public void anadirTitulo(String titulo) {
        lbl_TituloNoticia_1 = new JLabel(titulo);
        lbl_TituloNoticia_1.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_TituloNoticia_1.setBounds(10, 0, 128, 14);
        lbl_TituloNoticia_1.setVisible(true);
        this.add(lbl_TituloNoticia_1);
    }

    public void anadirTema(String noticia) {
        txt_Noticia_1 = new JTextArea();
        txt_Noticia_1.setWrapStyleWord(true);
        txt_Noticia_1.setText(noticia);
        txt_Noticia_1.setFont(new Font("Dialog", Font.PLAIN, 10));
        txt_Noticia_1.setEditable(false);
        txt_Noticia_1.setBorder(null);
        txt_Noticia_1.setBounds(10, 21, 724, 37);
        this.add(txt_Noticia_1);
    }

    public void anadirAutor(String autor) {
        lbl_AutorNoticia_1 = new JLabel(autor);
        lbl_AutorNoticia_1.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_AutorNoticia_1.setBounds(579, 0, 91, 14);
        this.add(lbl_AutorNoticia_1);
    }

    public void anadirFecha(String fecha) {
        lbl_FechaNoticia_1 = new JLabel(fecha);
        lbl_FechaNoticia_1.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_FechaNoticia_1.setBounds(670, 1, 64, 14);
        this.add(lbl_FechaNoticia_1);
    }


}