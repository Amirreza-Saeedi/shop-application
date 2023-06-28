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

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyEmail {

    public static void main(String[] args) {

        // Recipient's email ID needs to be mentioned.
        String to = "saeediamirreza4@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "amirrezasaeedi3@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "2525");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "reza12141618");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}