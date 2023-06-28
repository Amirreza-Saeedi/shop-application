package com.example.shopapplication;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void main(String[] args) {
        String to = "amirrezasaeedi6@gmail.com"; // recipient's email address
        String from = "amirrezasaeedi3@gmail.com"; // sender's email address
        String host = "localhost"; // SMTP server hostname

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            message.setSubject("This is the subject of the email");

            // Set the content of the message
            message.setText("This is the actual message");

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
