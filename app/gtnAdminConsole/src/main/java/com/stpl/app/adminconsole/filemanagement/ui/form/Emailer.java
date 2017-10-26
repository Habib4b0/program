/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.filemanagement.ui.form;
import static com.stpl.app.adminconsole.filemanagement.ui.form.Emailer.properties;
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
 * @author manikanta
 *
 * Class created to send mail using java mail
 */
public class Emailer {
    private static final Logger LOGGER = Logger.getLogger(Emailer.class);

     final static String from = "support@bpitechnologies.com";
    final static String password = "MyVibes$$$";
    final static Properties properties = new Properties();
public Emailer(){
    
}
    
    static {

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
    }

    public static String sendMail(String toAddress, String subject, String body,boolean htmlText,String ccAddress) {
        String ret = "success";
        try {
            LOGGER.debug("Sending Mail....");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            if(!ccAddress.isEmpty()){
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddress,false));
            }
            message.setSubject(subject);
            if (!htmlText) {
                message.setText(body);
            } else {
                message.setContent(body, "text/html");
            }
            Transport.send(message);
            LOGGER.debug("Mail sent succesfully.!");
        } catch (Exception e) {
            LOGGER.error(e);
            ret = "error";
        }
        return ret;
    }

}