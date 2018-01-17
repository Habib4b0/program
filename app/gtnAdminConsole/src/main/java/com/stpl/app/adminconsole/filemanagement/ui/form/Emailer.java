/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.filemanagement.ui.form;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementDTO;
import static com.stpl.app.adminconsole.filemanagement.ui.form.Emailer.properties;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.util.List;
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

    public static final String FROM = "support@bpitechnologies.com";
    final static Properties properties = new Properties();

    public Emailer() {
        LOGGER.debug("Emailer");
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
                    return new PasswordAuthentication(FROM, GlobalConstants.getSupportPassword());
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
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
    
    public static String sendMailonFileActivation(boolean htmlText , List<FileManagementDTO> processList) {
        String ret = "success";
        try {
            for (final FileManagementDTO fileManagementDTO : processList) {

                LOGGER.debug("Sending Mail....");
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, GlobalConstants.getSupportPassword());
                    }
                });
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fileManagementDTO.getSuccessTo()));
                if (!fileManagementDTO.getSuccessCC().isEmpty()) {
                    message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(fileManagementDTO.getSuccessCC(), false));
                }
                message.setSubject(fileManagementDTO.getSuccessSubject());
                if (!htmlText) {
                    message.setText(fileManagementDTO.getSuccessText());
                } else {
                    message.setContent(fileManagementDTO.getSuccessText(), "text/html");
                }
                Transport.send(message);
                LOGGER.debug("Mail sent succesfully.!");
            }
        } catch (Exception e) {
            LOGGER.error(e);
            ret = "error";
        }
        return ret;
    }

}