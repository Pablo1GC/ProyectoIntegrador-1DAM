package proyectoUnico;

import javax.swing.*;
import java.awt.*;

public class NoticiaProyecto extends JPanel {
    protected JPanel jp_CajitaNoticia_2;
    protected JTextArea txt_Noticia;
    protected JLabel lbl_AutorNoticia, lbl_FechaNoticia, lbl_TituloNoticia;

    public NoticiaProyecto(int y, String titulo, String club, String autor, String fecha, String contenido) {
        this.setLayout(null);
        this.anadirCajita(y);
        this.anadirAutor(autor);
        this.anadirTitulo(titulo);
        this.anadirFecha(fecha);
        this.anadirContenido(contenido);
    }

    public void anadirCajita(int y) {
        jp_CajitaNoticia_2 = new JPanel();
        jp_CajitaNoticia_2.setLayout(null);
        this.setBounds(10, y, 754, 48);
        this.setBackground(Color.WHITE);
        add(jp_CajitaNoticia_2);
    }

    public void anadirTitulo(String titulo) {
        lbl_TituloNoticia = new JLabel(titulo);
        lbl_TituloNoticia.setBounds(10, 0, 150, 18);
        lbl_TituloNoticia.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_TituloNoticia.setVisible(true);
        this.add(lbl_TituloNoticia);
    }

    public void anadirContenido(String contenido) {
        txt_Noticia = new JTextArea();
        txt_Noticia.setWrapStyleWord(true);
        txt_Noticia.setText(contenido);
        txt_Noticia.setFont(new Font("Roboto", Font.PLAIN, 10));
        txt_Noticia.setEditable(false);
        txt_Noticia.setBorder(null);
        txt_Noticia.setBounds(10, 21, 724, 37);
        this.add(txt_Noticia);
    }

    public void anadirAutor(String autor) {
        lbl_AutorNoticia = new JLabel(autor);
        lbl_AutorNoticia.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_AutorNoticia.setBounds(579, 0, 91, 14);
        this.add(lbl_AutorNoticia);
    }

    public void anadirFecha(String fecha) {
        lbl_FechaNoticia = new JLabel(fecha);
        lbl_FechaNoticia.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl_FechaNoticia.setBounds(670, 1, 64, 14);
        this.add(lbl_FechaNoticia);
    }
}

