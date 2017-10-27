/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.workflow.utils;

import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.vaadin.ui.Upload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha.Ravi
 */
public class FileUploader implements Upload.Receiver {

    private FileOutputStream outputStream;
    public File file;
    public static final String FILE_PATH = "../../../../var/Attachments/";
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

            if (!filename.isEmpty()) {

                File dir = new File(FilenameUtils.getName(FILE_PATH + moduleName));
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = new File(dir, FilenameUtils.getName(filename));
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                outputStream = new FileOutputStream(file);
            }
        } catch (final java.io.FileNotFoundException e) {
            LOGGER.error(e);
            return null;
        } catch (IOException ex) {
            LOGGER.error(ex);
            return null;
        }
        return outputStream;

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
            LOGGER.error(ex);
        } finally {
            super.finalize();
        }
    }

}
