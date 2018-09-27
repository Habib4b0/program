/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.workflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.ifs.util.CommonUtil;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.FinishedListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha.Ravi
 */
public class FileUploader implements Upload.Receiver, FinishedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploader.class);
    private FileOutputStream outputStream;
    public static final String FILE_PATH = "../../../../var/Attachments/";
    private String moduleName = StringUtils.EMPTY;

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

            if (!filename.isEmpty()) {

                File dir = new CommonUtil().getFileName(FILE_PATH + moduleName);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new CommonUtil().getFileName(dir, filename);
                if (file.exists()) {
                    boolean val = file.delete();
                    LOGGER.debug("FILE DELETED {}", val);
                }
                boolean val = file.createNewFile();
                LOGGER.debug("FILE CREATED {}", val);
                outputStream = new FileOutputStream(file);
            }
        } catch (final java.io.FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            return null;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
        return outputStream;

    }

    @Override
    public void uploadFinished(Upload.FinishedEvent event) {
        try {

            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
