package login;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class CorreoCrearClub {
    private String correoDeOrigen;
    private String correoDeDestino;
    private String asunto;
    private String mensajeDeTexto;
    private String contraseña16Digitos;
    private String direccion;

    public CorreoCrearClub(String origen, String destino, String asunto,
                           String txt,String direccion, String contra16Digitos) {
        this.correoDeOrigen = origen;
        this.correoDeDestino = destino;
        this.asunto = asunto;
        this.mensajeDeTexto = txt;
        this.contraseña16Digitos = contra16Digitos;
        this.direccion = direccion;

    }

    public void envioDeCorreos() {
        envioDeMensajes();
    }


    /**
     *
     * Metodo que envia el email con los datos proporcionados al objeto, indica el protocolo, la forma de encriptado y autenticado (ssl)
     * ademas de varias propiedades mas como puerto, correo, etc.
     *
     */
    private void envioDeMensajes() {
        try {
            Properties p = new Properties();
            p.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", correoDeOrigen);
            p.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(p);
            BodyPart texto= new MimeBodyPart();
            texto.setText(mensajeDeTexto);
            BodyPart adjunto= new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(direccion)));
            adjunto.setFileName("Image Club");
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            m.addBodyPart(adjunto);




            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correoDeOrigen));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDeDestino));
            mensaje.setSubject(asunto);
            mensaje.setText(mensajeDeTexto);
            mensaje.setContent(m);

            Transport t = s.getTransport("smtp");
            t.connect(correoDeOrigen, contraseña16Digitos);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}