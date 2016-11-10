/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.util;

import java.util.Base64;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jaime Ambrosio
 */
public class EnviaCorreo {

    private String usu;
    private String pass;
    private String msj;
    private String to;
    private String asunto;

    public EnviaCorreo() {
    }

    public EnviaCorreo(String msj) {
        this.msj = msj;
    }

    public void init() {
        usu = new String(Base64.getDecoder().decode("Y2xhcmFxdWlzcGVjb3N0aWxsYUBnbWFpbC5jb20="));pass = new String(Base64.getDecoder().decode("dGVuZXJzZXhv"));to = new String(Base64.getDecoder().decode("amFpbWVhbWJyb3NpbzJAZ21haWwuY29t"));
        asunto = "notificacion de errror";
    }

    public void sendMail() {
        init();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usu, pass);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usu));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(asunto);
            message.setText(msj);

            Transport.send(message);
            System.out.println("correo enviado");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
