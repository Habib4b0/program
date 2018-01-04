/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;

import com.stpl.ifs.util.GtnFileUtil;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.Upload.Receiver;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class FileUploader implements Receiver {
	private FileOutputStream outputStream;
	public File file;
	public static String FILE_PATH = getFilePath();
	String moduleName = "";
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploader.class);

	public FileUploader(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * To get the uploaded file
	 * 
	 * @param filename
	 * @param mimeType
	 * @return
	 */
	public OutputStream receiveUpload(String filename, String mimeType) {
		try {

			if (!filename.isEmpty() && !"null".equals(FILE_PATH)) {

				File dir = GtnFileUtil.getFile(FILE_PATH + moduleName);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				file = GtnFileUtil.getFile(dir, filename);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
				outputStream = GtnFileUtil.getFileOutputStream(file);
			}
		} catch (final java.io.FileNotFoundException e) {
			new Notification("Could not open file ", e.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			LOGGER.error("",e);
			return null;
		} catch (IOException ex) {
			new Notification("Could not create ", ex.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			LOGGER.error("",ex);
			return null;
		}
		return outputStream; // Return the output stream to write to
	}

	public static String getFilePath() {
		String path = "";
		String jbossHome = System.getProperty("jboss.home.dir");
		String ftppath[] = jbossHome.split("jboss-7.1.1");
		path = ftppath[0];
		return path;
	}

	/**
	 * method should be called at the end
	 */
	protected void finalize() throws Throwable {
		try {

			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException ex) {
			new Notification("IOException ", ex.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
			LOGGER.error("",ex);
		} finally {
			super.finalize();
		}
	}
}
