package com.raunak.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    public void sendMail(String content) {

        // Recipient's email ID needs to be mentioned.
        String to = "myemail@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "x@sfkjk.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            /**
             * Called when password authentication is needed.  Subclasses should
             * override the default implementation, which returns null. <p>
             * <p/>
             * Note that if this method uses a dialog to prompt the user for this
             * information, the dialog needs to block until the user supplies the
             * information.  This method can not simply return after showing the
             * dialog.
             *
             * @return The PasswordAuthentication collected from the
             * user, or null if none is provided.
             */
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("x@rgk.com", "defew");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // message.addRecipients(Message.RecipientType.TO, to);
            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            if (content.startsWith("<!DOCTYPE html"))
                message.setContent(content, "text/html");
            else
                message.setText(content);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
