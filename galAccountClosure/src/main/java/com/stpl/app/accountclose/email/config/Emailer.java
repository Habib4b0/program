/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.email.config;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class Emailer {
    
    private static final Logger LOGGER = Logger.getLogger(Emailer.class);

    final static String from = "support@bpitechnologies.com";
    final static String password = "MyVibes$$$";
    static Properties properties = new Properties();

    static {
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
    }
    /**
     * Test Mail Logic for Mail Server Configuration
     * 
     * @param toAddress
     * @param subject
     * @param body
     * @param smtpType
     * @return success
     */
    public static String testMail(String toAddress, String subject, String body, String smtpType) {
        String ret = "success";
        if ("Yes".equals(smtpType) && smtpType!=null) {
            LOGGER.info("Checking mail sender for SMTP mail server");
            try {
                LOGGER.info("Sending Mail....");
                Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);
                LOGGER.info("Mail sent succesfully.!");
            } catch (Exception ex) {
                LOGGER.error(ex);
                ret = "error";
            }
        } else {
            LOGGER.info("Checking mail sender for Non-SMTP mail server");
        }
        return ret;
    }
}
