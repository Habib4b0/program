/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class OnDemandFileDownloader extends FileDownloader {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OnDemandFileDownloader.class);
    public static final int BUFFER_SIZE = 1024 * 4;

    /**
     * Provide both the {@link StreamSource} and the filename in an on-demand
     * way.
     */
    public interface OnDemandStreamResource extends StreamResource.StreamSource {

        String getFilename();

    }

    private static final long serialVersionUID = 1L;
    private final OnDemandStreamResource onDemandStreamResource;

    public OnDemandFileDownloader(OnDemandStreamResource resource) throws IOException {
        super(new StreamResource(resource, ""));
        this.onDemandStreamResource = checkNotNull(resource,
                "The given on-demand stream resource may never be null!");
    }

    @Override
    public boolean handleConnectorRequest(VaadinRequest request, VaadinResponse response, String path)
            throws IOException {
        
        LOGGER.info("this.getResource(\"dl\");----{}",getResource().getFilename());
        StreamResource resource = getResource();
        resource.setFilename(onDemandStreamResource.getFilename());
        LOGGER.info("Current Buffer size :{}",resource.getBufferSize());
        LOGGER.info("Setting new Buffer size :{}",BUFFER_SIZE);
        boolean falg=super.handleConnectorRequest(request, response, path);
        try{
            falg = super.handleConnectorRequest(request, response, path);
        } catch (Exception e) {
            System.out.println("No record Found to Export :"+e);
               LOGGER.info(e.getMessage());
        }
        return falg;
    }

    private StreamResource getResource() {
        return (StreamResource) this.getResource("dl");
    }

    private OnDemandStreamResource checkNotNull(OnDemandStreamResource resource, String msg) throws IOException {
        if (resource == null) {
            throw new IOException(msg);
        }
        return resource;
    }

    
}
