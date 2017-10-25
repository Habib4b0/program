package org.r2.etl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;
import org.r2.etl.common.controller.BPIETLException;

/**
 * This is the CommonUtils class for email notification ,log File Creation etc.
 * 
 * @author stpl
 * 
 */
public final class CommonUtils {

	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(CommonUtils.class);

	/**
	 * Constructor
	 */
	private CommonUtils() {

	}

	/**
	 * The method is used to get the File Path for the properties file.
	 * 
	 * @return logpath
	 * 
	 */
	public static String getFilePath()  {

		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		final URL resource = classLoader.getResource("");
		String logpath = resource.getPath();
		logpath = logpath.replace('/', '/');
		return logpath;

	}

	/**
	 * This method is used to create the log file.
	 */
	public static void createlog() throws BPIETLException {

		try {
	    
	        	File dir = new File(getPropertyFromDbresource("ETL_LOG_PATH"));
	     		if (!dir.exists()) {
	     			dir.mkdirs();
	     		}
	    if(getPropertyFromDbresource("DEBUG_MODE")!=null && getPropertyFromDbresource("DEBUG_MODE").equalsIgnoreCase("yes")){
	        	final BufferedReader reader = new BufferedReader(new FileReader(getPropertyFromDbresource("ETL_LOG_PATH")
					+FilePathUtil.TEMP_PATH));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			String date = simpleDateFormat.format(new Date());
			
			String finalPath = getPropertyFromDbresource("ETL_LOG_PATH")
					+ FilePathUtil.LOG_PATH
							.replace(".txt", "-" + date + ".txt");
			//System.out.print("Log path" + finalPath);
			File logfile = new File(finalPath);
			logfile.createNewFile();

			final PrintWriter writer = new PrintWriter(new FileWriter(logfile,
					true));
			String line = reader.readLine();
			 while (line != null) {
                 //line = line.trim(); // remove leading and trailing whitespace
             if (!line.equals("")) // don't write out blank lines
             {
                 line=line.replaceAll("\\x00", "");
                 writer.println(line);
                 line = reader.readLine();
             }
         
         }
			reader.close();
			writer.close();
	        }
		} catch (FileNotFoundException ex) {
			LOGGER.error(ex.getMessage());
			throw new BPIETLException(ex);
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
			throw new BPIETLException(ex);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new BPIETLException(ex);
		}
	}

	/**
	 * This method is used to clear the temp files for old logs.
	 */
	public static void cleartemp() throws BPIETLException {
		try {
			File dir = new File(getPropertyFromDbresource("ETL_LOG_PATH"));
     		if (!dir.exists()) {
     			dir.mkdirs();
     		}
			final PrintWriter writer = new PrintWriter(getPropertyFromDbresource("ETL_LOG_PATH")+FilePathUtil.TEMP_PATH);
			writer.print("");
			// writer.flush();
			writer.close();
		} catch (FileNotFoundException ex) {
			LOGGER.error(ex.getMessage());
			throw new BPIETLException(ex);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new BPIETLException(ex);
		}
	}

	/**
	 * This method is used to send mail with error log attachment for any error.
	 */
	public static void sendmail(final String interfacesName)
			throws BPIETLException {
		try {
			File dir = new File(getPropertyFromDbresource("ETL_LOG_PATH"));
     		if (!dir.exists()) {
     			dir.mkdirs();
     		}
        
			final BufferedReader reader = new BufferedReader(new FileReader(getPropertyFromDbresource("ETL_LOG_PATH")+
					FilePathUtil.TEMP_PATH));
			final PrintWriter writer = new PrintWriter(new FileWriter(
					getPropertyFromDbresource("ETL_LOG_PATH")+ FilePathUtil.ERROR_LOG_PATH));
			String line = reader.readLine();
			while (line != null) {
				writer.println(line);

				line = reader.readLine();
			}
			reader.close();
			writer.close();
			final Properties mailResource = new Properties();
			mailResource.load(new FileInputStream(
					FilePathUtil.ERROR_MAIL_FILE_NAME));
			final Properties props = new Properties();
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.host", mailResource.getProperty("MAIL_SERVER"));
			props.put("mail.smtp.port", mailResource.getProperty("MAIL_PORT"));

			final Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
					});

			final MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailResource
					.getProperty("SENDER_ADDRESS")));
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(mailResource.getProperty(
							"DESTINATION_ADDRESS").replaceAll(" ", ",")));
			message.setRecipients(
					Message.RecipientType.CC,
					InternetAddress.parse(mailResource.getProperty(
							"DESTINATION_CC").replaceAll(" ", ",")));

			message.setSubject(interfacesName
					+ "_ERROR_LOG["
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale
							.getDefault()).format(new Date()) + "]");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(Constants.MESSAGE_BODY);
			final Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// attach your file here
			messageBodyPart = new MimeBodyPart();
			final DataSource source = new FileDataSource(
					FilePathUtil.ERROR_LOG_PATH); // your
			// file
			messageBodyPart.setDataHandler(new DataHandler(source));

			messageBodyPart.setFileName(FilePathUtil.LOG_FILE_NAME);
			multipart.addBodyPart(messageBodyPart);

			// Put message in parts
			message.setContent(multipart);
			Transport.send(message);

			final File file = new File(FilePathUtil.ERROR_LOG_PATH);
			file.delete();

		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new BPIETLException(e);

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new BPIETLException(e);
		} catch (AddressException e) {
			LOGGER.error(e.getMessage());
			throw new BPIETLException(e);
		} catch (MessagingException e) {
			LOGGER.error(e.getMessage());
			throw new BPIETLException(e);
		}

	}

	/**
	 * This method is used to create the log file.
	 */
	public static void createErrorlog() throws BPIETLException {
		try {
			// final BufferedReader reader = new BufferedReader(new FileReader(
			// FilePathUtil.TEMP_PATH));
			// SimpleDateFormat simpleDateFormat = new
			// SimpleDateFormat("yyyy-MM-dd");
			// String date = simpleDateFormat.format(new Date());
			// String finalPath = FilePathUtil.LOG_PATH.replace(".txt",
			// "-"+date+".txt");
			// final PrintWriter writer = new PrintWriter(new
			// FileWriter(finalPath,true));
			// String line = reader.readLine();
			// while (line != null){
			// writer.println(line);
			// line = reader.readLine();
			// }
			// reader.close();
			// writer.close();
      
        	//System.out.print(dbResouce.getProperty("LOG_PATH"));
     		File dir = new File(getPropertyFromDbresource("ETL_LOG_PATH"));
     		if (!dir.exists()) {
     			dir.mkdirs();
     		}
     	   if(getPropertyFromDbresource("DEBUG_MODE")!=null && getPropertyFromDbresource("DEBUG_MODE").equalsIgnoreCase("yes")){
        	 final BufferedReader reader = new BufferedReader(new FileReader(getPropertyFromDbresource("ETL_LOG_PATH")+
					FilePathUtil.TEMP_PATH));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			String date = simpleDateFormat.format(new Date());
			
			String finalPath = getPropertyFromDbresource("ETL_LOG_PATH")
					+ FilePathUtil.ERROR_LOG_PATH.replace(".txt", "-" + date
							+ ".txt");
			final PrintWriter writer = new PrintWriter(new FileWriter(
					finalPath, true));
			String line = reader.readLine();
	
			
			 while (line != null) {
                 //line = line.trim(); // remove leading and trailing whitespace
             if (!line.equals("")) // don't write out blank lines
             {
                 line=line.replaceAll("\\x00", "");
                 writer.println(line);
                 line = reader.readLine();
             }
         
         }
			reader.close();
			writer.close();
         }
		} catch (FileNotFoundException ex) {
			LOGGER.error(ex.getMessage());
			throw new BPIETLException(ex);
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
			throw new BPIETLException(ex);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new BPIETLException(ex);
		}
	}

	static String getPropertyFromDbresource(String property) {
		final File file = new File("");
		String logpath = "";
		try {
			logpath = file.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Properties dbResouce = new Properties();
		final String path = logpath
				+ FilePathUtil.DATABASE_CONFIGURATION_FILE_NAME;
		try {
			dbResouce.load(new FileInputStream(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbResouce.getProperty(property);
	}

}
