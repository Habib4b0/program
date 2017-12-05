/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

/**
 *
 * @author Manikanda.Prabu
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.stpl.app.cff.logic.CommonLogic.LOGGER;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import java.io.IOException;

/**
 *
 * @author 
 */


public class OnDemandFileDownloader extends FileDownloader {

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
        LOGGER.debug("this.getResource(\"dl\");----" + getResource().getFilename());
        StreamResource resource = getResource();
        resource.setFilename(onDemandStreamResource.getFilename());
        LOGGER.debug("Current Buffer size :" + resource.getBufferSize());
        LOGGER.debug("Setting new Buffer size :" + BUFFER_SIZE);
        boolean falg=false;
        try {
           falg = super.handleConnectorRequest(request, response, path);
        } catch (IOException e) {
           LOGGER.error(e);
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
