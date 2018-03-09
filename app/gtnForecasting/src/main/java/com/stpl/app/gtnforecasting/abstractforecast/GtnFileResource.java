/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import static com.stpl.app.gtnforecasting.abstractforecast.AbsAdditionalInformation.LOGGER;
import com.vaadin.server.DownloadStream;
import com.vaadin.server.FileResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Hazihabibullah.Syed
 */
class GtnFileResource extends FileResource {

    public GtnFileResource(File sourceFile) {
        super(sourceFile);
    }

    @Override
    public DownloadStream getStream() {
        try {
            final DownloadStream stream = new DownloadStream(
                    new FileInputStream(getSourceFile()), getMIMEType(),
                    getFilename());
            stream.setParameter("Content-Disposition", "attachment;filename=" + getFilename());
            stream.setParameter("Cache-Control", "private,no-cache,no-store");
            stream.setCacheTime(1000);
            return stream;
        } catch (FileNotFoundException ex) {
            LOGGER.error("Error while downloading ", ex);
        }
        return null;
    }
}
