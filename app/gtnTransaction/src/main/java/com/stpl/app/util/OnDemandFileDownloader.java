/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.util;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import java.io.IOException;
import org.jboss.logging.Logger;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class OnDemandFileDownloader extends FileDownloader {
    private static final Logger LOGGER = Logger.getLogger(OnDemandFileDownloader.class.getName());

    public static final int BUFFER_SIZE = 1024 * 4;

    /**
     * Provide both the {@link StreamSource} and the filename in an on-demand
     * way.
     */
    public interface OnDemandStreamResource extends StreamSource {

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
        
        LOGGER.debug("this.getResource(\"dl\");----" + getResource().getFilename());
        StreamResource resource = getResource();
        resource.setFilename(onDemandStreamResource.getFilename());
        LOGGER.debug("Current Buffer size :"+resource.getBufferSize());
        LOGGER.debug("Setting new Buffer size :"+BUFFER_SIZE);
        boolean falg=super.handleConnectorRequest(request, response, path);
        try{
            falg = super.handleConnectorRequest(request, response, path);
        } catch (Exception e) {
            LOGGER.debug("No record Found to Export :"+e);
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
