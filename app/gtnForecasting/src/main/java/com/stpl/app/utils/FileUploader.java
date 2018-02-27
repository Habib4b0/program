/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.ifs.util.CommonUtil;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.Upload.Receiver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Manasa
 */
public class FileUploader implements Receiver {

    private FileOutputStream outputStream;
    private File file;
    public static final String FILE_PATH = getFilePath();
    private String moduleName = StringUtils.EMPTY;
    private boolean isFileExists;
    private boolean isFileCreated;
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
        try {

            if (!filename.isEmpty() && !"null".equals(FILE_PATH)) {
                File dir = CommonUtil.getFilePath(FILE_PATH + moduleName);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = CommonUtil.getFile(dir, filename);
                if (file.exists()) {
                    isFileExists=file.delete();
                }
                isFileCreated=file.createNewFile();
                outputStream = new FileOutputStream(file);
                LOGGER.info("File deleted successfully= {}",isFileExists);
                LOGGER.info("File created successfully= {}",isFileCreated);
            }
        } catch (final java.io.FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            new Notification("Could not open file ", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            new Notification("Could not create ", ex.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        }
        return outputStream;

    }

    public static String getFilePath() {
        StringBuilder path = new StringBuilder(System.getProperty("com.stpl.gtnframework.base.path"));
        path.append("Attachments");
        path.append('/');
        return path.toString();
    }

    /**
     * method should be called at the end
     */
    @Override
    protected void finalize() throws Throwable {
        try {

            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            new Notification("IOException ", ex.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
        } finally {
            super.finalize();
        }
    }
}
