/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galworkflow.util;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author rohitvignesh.s
 */
public class FileUploader implements Receiver {
    private FileOutputStream outputStream;
    public File file;
    public static final String FILE_PATH="../../../../var/Attachments/";
    String moduleName=StringUtils.EMPTY;
    private static final Logger LOGGER = Logger.getLogger(FileUploader.class);

    public FileUploader(String moduleName) {
        this.moduleName=moduleName;
    }

    /**
     * To get the uploaded file
     * @param filename
     * @param mimeType
     * @return 
     */
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {

        	if(!filename.isEmpty()){
        		
            File dir = new File(FILE_PATH+moduleName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file = new File(dir, filename);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            }
        } catch (final java.io.FileNotFoundException e) {
             LOGGER.error(e);
            new Notification("Could not open file ", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        } catch (IOException ex) {
             LOGGER.error(ex);
            new Notification("Could not create ", ex.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        }
        return outputStream;
    }

    /**
     * method should be called at the end
     */
    protected void finalize() throws Throwable{
        try {

            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
            new Notification("IOException ", ex.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
        } finally {
            super.finalize();
        }
    }
}
