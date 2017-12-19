/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.ifs.util.GtnFileUtil;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUploader.
 *
 * @author lokeshwari
 */
public class FileUploader implements Receiver {

	/**
	 * The output stream.
	 */
	private FileOutputStream outputStream;
	/**
	 * The file.
	 */
	public File file;
	/**
	 * The basepath.
	 */
	private String basepath;
	/**
	 * The module name.
	 */
	private String moduleName;
	/**
	 * The move back.
	 */
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(FileUploader.class);

	/**
	 * The user id.
	 */
	final private String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);

	/**
	 * The Constructor.
	 *
	 * @param filePath
	 *            the file path
	 * @param moduleName
	 *            the module name
	 */
	public FileUploader(final String filePath, final String moduleName) {
		this.basepath = filePath;
		this.moduleName = moduleName;
	}

	/**
	 * Receiving the uploaded file.
	 *
	 * @param filename
	 *            the filename
	 * @param mimeType
	 *            the mime type
	 * @return the output stream
	 */
	@Override
	public OutputStream receiveUpload(final String filename, final String mimeType) {
		LOGGER.debug("Entering receiveUpload method ");

		try {
			if (!StringUtils.EMPTY.equalsIgnoreCase(filename)) {
				final File dir = com.stpl.ifs.util.CommonUtil.getFilePath("../../../../var/Attachments/"
						+ File.separator + moduleName + File.separator + userId + File.separator);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				file = GtnFileUtil.getFile(dir, filename);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
				outputStream = new FileOutputStream(file);
				return outputStream;
			}
		} catch (final java.io.FileNotFoundException e) {
			LOGGER.error(e);
			new Notification("Could not open file ", e.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return null;
		} catch (Exception ex) {
			LOGGER.error(ex);
			new Notification("Could not create ", ex.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return null;
		}
		LOGGER.debug("End of receiveUpload method");
		return new NullOutputStream();

	}

	/**
	 * To remove unwanted memory space occupied by object.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
        @Override
	protected void finalize() throws Throwable {
		LOGGER.debug("Entering finalize method ");

		try {

			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException ex) {
			LOGGER.error(ex);
		}
		LOGGER.debug("End of finalize method");
		super.finalize();
	}

	/**
	 * Gets the output stream.
	 *
	 * @return the outputStream
	 */
	public FileOutputStream getOutputStream() {
		return outputStream;
	}

	/**
	 * Sets the output stream.
	 *
	 * @param outputStream
	 *            the outputStream to set
	 */
	public void setOutputStream(final FileOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file
	 *            the file to set
	 */
	public void setFile(final File file) {
		this.file = file;
	}

	/**
	 * Gets the basepath.
	 *
	 * @return the basepath
	 */
	public String getBasepath() {
		return basepath;
	}

	/**
	 * Sets the basepath.
	 *
	 * @param basepath
	 *            the basepath to set
	 */
	public void setBasepath(final String basepath) {
		this.basepath = basepath;
	}

	/**
	 * Gets the module name.
	 *
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Sets the module name.
	 *
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}
}
