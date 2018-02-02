package com.stpl.app.cff.bpm.service;

import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.server.VaadinService;
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
import org.slf4j.LoggerFactory;

public class MailWorkItemHandler implements WorkItemHandler {

	/**
	 * The Constant LOGGER.
	 */
	private static final org.slf4j.Logger LOGGER = LoggerFactory
			.getLogger(MailWorkItemHandler.class);

        @Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		Map<String, Object> map = workItem.getParameters();
		String to = (String) map.get("To");
		String from = (String) map.get("From");
		String subject = (String) map.get("Subject");
		String body = (String) map.get("Body");
		if (!to.isEmpty() && !from.isEmpty() && !subject.isEmpty()) {
			setMessage(body, subject, to);
		}
		manager.completeWorkItem(workItem.getId(), null);
	}

	public static void sendMail(String toAdd, String subject, StringBuffer text) {
		String filePath1 = CommonUtil.getJbossHome()+"/mailconfig/mailConfiguration.properties";
		Properties props = new Properties();
		InputStream is = null;

		// First try loading from the current directory
		try {
			File f = GtnFileUtil.getFile(filePath1);
			is = new FileInputStream(f);
		} catch (Exception e) {
			is = null;
			LOGGER.error(e.getMessage());
		}

		try {
			if (is == null) {
				// Try loading from classpath
				is = MailWorkItemHandler.class.getResourceAsStream("server.properties");
			}

			// Try loading properties from the file (if found)
			props.load(is);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		final String fromAddress = props.getProperty("fromAddress", "support@bpitechnologies.com");
		final String password = props.getProperty(GlobalConstants.getPassword(), GlobalConstants.getSupportPassword());

		String toAddress = toAdd;
		String delims = "[,]";
		String[] tokens = toAddress.split(delims);

		Properties properties = new Properties();

		// this is for office 365
		properties.put("name", "mail/Office365");
		properties.put("auth", "Container");
		properties.put("type", "javax.mail.Session");
		properties.put("mail.smtp.host", "smtp.office365.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.transport.protocol", "smtps");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromAddress, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));

			if (tokens.length > 1) {
				InternetAddress[] toAddr = new InternetAddress[tokens.length];
				// To get the array of addresses
				for (int i = 0; i < tokens.length; i++) {
					toAddr[i] = new InternetAddress(tokens[i]);
				}

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

		} catch (MessagingException e) {
			LOGGER.error(e.getMessage());
		}
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
		if (emailId != null && !emailId.equals(StringUtils.EMPTY)) {
			sendMail(emailId, subject, bodyText);
		}
	}

        @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
	}

}
