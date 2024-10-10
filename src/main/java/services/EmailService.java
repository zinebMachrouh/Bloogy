package services;

import utils.Config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    public void sendEmail(String to, String subject, String body) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", Config.get("SMTP_HOST"));
        props.put("mail.smtp.port", Config.get("SMTP_PORT"));

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Config.get("SMTP_USERNAME"), Config.get("SMTP_PASSWORD"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Config.get("FROM_EMAIL")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new Exception("Failed to send email: " + e.getMessage());
        }
    }
}