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
    public File file;
    public static String FILE_PATH = getFilePath();
    String moduleName = StringUtils.EMPTY;

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
                    file.delete();
                }
                file.createNewFile();
                outputStream = new FileOutputStream(file);
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
        String path = "";
        String jbossHome = System.getProperty("jboss.home.dir");
        String ftppath[] = jbossHome.split("jboss-7.1.1");
        path = ftppath[0];
        return path;
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
