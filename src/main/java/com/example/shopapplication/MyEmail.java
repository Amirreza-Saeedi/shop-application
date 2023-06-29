//package com.example.shopapplication;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//public class MyEmail {
//    public static String sender = "amirrezasaeedi3@gmail.com";
//    public static String host = "smtp.gmail.com";
//
//    public void sendEmail(String message, String subject, String recipient) {
////        final String username = "amirrezasaeedi3@gmail.com";
////        final String password = "reza12141618";
//        final String username = "smjm446688@gmail.com";
//        final String password = "javad1383";
//        try {
//            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
//            properties.put("mail.smtp.host", host);
//            properties.put("mail.smtp.port", "587");
//
//            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setFrom(new InternetAddress(sender));
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            mimeMessage.setSubject(subject);
//            mimeMessage.setText(message);
//
//            Transport.send(mimeMessage);
//            System.out.println("Mail successfully sent");
//        } catch (MessagingException e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        new MyEmail().sendEmail("hello", "My Hello", "saeediamirreza4@gmail.com");
//    }
//}

package com.example.shopapplication;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import static org.simplejavamail.mailer.MailerBuilder.buildMailer;

public class MyEmail {
    public static void main(String[] args) {
        final String from = "amirrezasaeedi3@gmail.com";
        final String to = "saeediamirreza4@gamil.com";
        final String pass = "reza12141618";

        Email email = EmailBuilder.startingBlank()
                .from(from)
                .to(to)
                .withSubject("Email Subject")
                .withPlainText("Email Body")
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.mailtrap.io", 2525, from, pass)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer();

        mailer.sendMail(email);

    }
//    public static void main(String[] args) throws EmailException {
//        String to = "recipient@gmail.com"; // recipient's email address
//        String from = "sender@gmail.com"; // your email address
//        String password = "yourpassword"; // your email password
//
//        // Create a new EmailBuilder instance
//        Email email = EmailBuilder.startingBlank()
//                .from(from)
//                .to(to)
//                .withSubject("Test Email")
//                .withHTML("<h1>This is a test email from EmailBuilder class</h1>")
//                .build();
//
//        // Set the email properties
//        email.setHostName("smtp.gmail.com");
//        email.setSmtpPort(587);
//        email.setAuthentication(from, password);
//        email.setTLS(true);
//
//        // Send the email
//        email.send();
//    }
}