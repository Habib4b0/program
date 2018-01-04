/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.component.notestab.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.Upload.Receiver;

public class FileUploader implements Receiver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected File file;

	public static final String FILE_PATH = System.getProperty(GtnFrameworkCommonStringConstants.GTNFRAMEWORK_BASE_PATH)
			+ "/Attachments/";
	private String moduleName = "";
	public static final String BACK_SLASH = "/";
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(FileUploader.class);

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
	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream outputStream = null;
		try {

			if (!filename.isEmpty()) {

				File dir = GtnFileNameUtils.getFile(FILE_PATH + moduleName);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				file = GtnFileNameUtils.getFile(dir, filename);
				Files.deleteIfExists(file.toPath());
				if (!file.createNewFile()) {
					gtnLogger.error("Unable to create New File in Path >" + file.toPath());
				}

				outputStream = new FileOutputStream(file);
			}
		} catch (final java.io.FileNotFoundException e) {
			new Notification("Could not open file ", e.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return null;
		} catch (IOException ex) {
			new Notification("Could not create ", ex.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return null;
		}
		return outputStream;
	}

}
