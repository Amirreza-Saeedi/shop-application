//package com.example.shopapplication;
//
//        import javax.mail.*;
//        import javax.mail.internet.InternetAddress;
//        import javax.mail.internet.MimeMessage;
//        import java.util.Properties;
//
//public class MyEmail {
//    public static String sender = "amirrezasaeedi3@gmail.com";
//    public static String host = "localhost";
//    public void sendEmail(String message, String subject, String recipient) {
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
////        Properties properties = new Properties();
////        properties.put("mail.stmp.auth*", "true");
////        properties.put("mail.stmp.starttls.enable*", "true");
////        properties.put("mail.stmp.host*", "stmp.gmail.com");
////        properties.put("mail.stmp.port*", "587");
//
//        Session session = Session.getDefaultInstance(properties);
////        Session session = Session.getInstance(properties, new Authenticator() {
////            @Override
////            protected PasswordAuthentication getPasswordAuthentication() {
////                return super.getPasswordAuthentication();
////            }
////        });
//
//        try {
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
