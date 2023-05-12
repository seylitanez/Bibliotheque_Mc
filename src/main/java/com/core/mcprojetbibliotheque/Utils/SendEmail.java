package com.core.mcprojetbibliotheque.Utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
public class SendEmail {
	public void sendEmailMethode(String Recepteur,String Subject,String Text) {
		String to = Recepteur;
        String from = "mc.bibliotheque@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "mc.bibliotheque@gmail.com";
        final String password = "ucllypmuohaqxsyt";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", " 993");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	} 
	
	
	
	
}
