package com.stpl.app.adminconsole.bpm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.jboss.logging.Logger;

import com.vaadin.server.VaadinService;

public class MailWorkItemHandler implements WorkItemHandler {

    private static final Logger LOGGER = Logger.getLogger(MailWorkItemHandler.class);

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        Map<String, Object> map = workItem.getParameters();
        String to = (String) map.get("To");
        String from = (String) map.get("From");
        String subject = (String) map.get("Subject");
        String body = (String) map.get("Body");

        LOGGER.info("To: " + map.get("To"));
        LOGGER.info("From: " + map.get("From"));
        LOGGER.info("Subject: " + map.get("Subject"));
        LOGGER.info("Body: " + map.get("Body"));
        //TODO use javax.mail send email
        if (!to.isEmpty() && !from.isEmpty() && !subject.isEmpty()) {
            setMessage(body, subject, to);
        }
        manager.completeWorkItem(workItem.getId(), null);
    }

    public static void sendMail(String toAdd, String subject, StringBuffer text) {
        String path = (VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null
                ? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : StringUtils.EMPTY);
        String filePath1 = "/../../../mailconfig/mailConfiguration.properties";
        Properties props = new Properties();
        InputStream is = null;

        // First try loading from the current directory
        try {
            File f = new File(path + filePath1);
            is = new FileInputStream(f);
        } catch (Exception e) {
            is = null;
        }

        try {
            if (is == null) {
                // Try loading from classpath
                is = MailWorkItemHandler.class.getResourceAsStream("server.properties");
            }

            // Try loading properties from the file (if found)
            props.load(is);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        final String fromAddress = props.getProperty("fromAddress", "mani.rgm448@gmail.com");
        final String password = props.getProperty("password", "M@ni@newlife7");

        String toAddress = toAdd;
        String delims = "[,]";
        String[] tokens = toAddress.split(delims);
        for (int i = 0; i < tokens.length; i++) {
            LOGGER.info(tokens[i]);
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromAddress, password);
                    }
                });

        try {
            LOGGER.info("Sending Mail...");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));

            if (tokens.length > 1) {
                InternetAddress[] toAddr = new InternetAddress[tokens.length];
                // To get the array of addresses
                for (int i = 0; i < tokens.length; i++) {
                    toAddr[i] = new InternetAddress(tokens[i]);
                }
                LOGGER.info(Message.RecipientType.TO);
                for (int i = 0; i < toAddr.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddr[i]);
                }
            } else {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            }

            message.setSubject(subject);
            String bodyText = text.toString();
            message.setContent(bodyText, "text/html");
            Transport.send(message);
            LOGGER.info("Mail Sent Succesfully");

        } catch (MessagingException e) {
            LOGGER.error(e);
        }
    }

    public static void main(String[] args) {
        sendMail("manikanta.prasad@sysbiz.com", "TEst", new StringBuffer("Test"));
    }

    public static void setMessage(String text, String subject, String emailId) {
        String by = "BPI Workflow";
        StringBuffer bodyText = new StringBuffer();
        bodyText.append("Hi,<br>");
        bodyText.append("<br>");
        bodyText.append(text);
        bodyText.append("<br>");
        bodyText.append("Regards,<br>");
        bodyText.append(by).append(".");
        if (emailId != null && !emailId.equals("")) {
            sendMail(emailId, subject, bodyText);
        }
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

}
