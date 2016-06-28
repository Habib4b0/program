/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.vaadin.ui.Component;
import elemental.events.KeyboardEvent;
import org.jboss.logging.Logger;

/**
 *
 * @author lokeshwari
 */
public class ChannelSPRGraphWindow extends StplWindow {

    private static final Logger LOGGER = Logger.getLogger(ChannelSPRGraphWindow.class);

    public ChannelSPRGraphWindow(final Component component, final String title) {
        super(title);

        LOGGER.info("Entering NmSalesGraphWindow");
        init(component);
        LOGGER.info("End of NmSalesGraphWindow ");

    }

    /**
     * Inits the.
     *
     * @param component the component
     */
    public void init(final Component component) {

        LOGGER.info("Entering init method");
        center();
        setClosable(true);
        setModal(true);
        setWidth("1200px");
        setHeight("800px");
        setContent(component);
        setCloseShortcut(KeyboardEvent.KeyCode.ESC);
        LOGGER.info("End of init method");
    }
}
