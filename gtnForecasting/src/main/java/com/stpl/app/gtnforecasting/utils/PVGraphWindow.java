/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.vaadin.ui.Component;
import elemental.events.KeyboardEvent;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PVGraphWindow extends StplWindow {

    private static final Logger LOGGER = Logger.getLogger(NmPPAGraphWindow.class);

    public PVGraphWindow(final Component component, final String title) {
        super(title);

        LOGGER.debug("Entering NmSalesGraphWindow");
        init(component);
        LOGGER.debug("End of NmSalesGraphWindow ");

    }

    /**
     * Inits the.
     *
     * @param component the component
     */
    public void init(final Component component) {

        LOGGER.debug("Entering init method");
        center();
        setClosable(true);
        setModal(true);
        setWidth("1200px");
        setHeight("800px");
        setContent(component);
        setCloseShortcut(KeyboardEvent.KeyCode.ESC);
        LOGGER.debug("End of init method");

    }
}
