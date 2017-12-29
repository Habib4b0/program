/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.util;

import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static org.apache.commons.io.output.NullOutputStream.NULL_OUTPUT_STREAM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.GtnFileUtil;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;

/**
 * The Class FileUploader.
 *
 * @author Vinodhini
 */
public class FileUploader implements Receiver {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
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
	private final static String MOVEBACK = "../";
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(FileUploader.class);
	final String userId = (String) VaadinSession.getCurrent().getAttribute(USER_ID.getConstant());
	public boolean upload = true;

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
			final File dir = CommonUtil
					.getFilePath(basepath + File.separator + MOVEBACK + MOVEBACK + MOVEBACK + File.separator
							+ "Documents" + File.separator + moduleName + File.separator + userId + File.separator);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (StringUtils.isNotBlank(filename) && isUpload()) {

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
			return NULL_OUTPUT_STREAM;
		} catch (IOException ex) {
			LOGGER.error(ex);
			new Notification("Could not create ", ex.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return NULL_OUTPUT_STREAM;
		}
		LOGGER.debug("End of receiveUpload method");
		return NULL_OUTPUT_STREAM;
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

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}
}
