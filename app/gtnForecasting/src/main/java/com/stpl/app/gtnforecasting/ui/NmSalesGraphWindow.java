/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.utils.StplWindow;
import com.vaadin.ui.Component;
import elemental.events.KeyboardEvent.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class NmSalesGraphWindow.
 *
 * @author lokeshwari
 */
public class NmSalesGraphWindow extends StplWindow {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NmSalesGraphWindow.class);

    /**
     * The Constructor.
     *
     * @param component the component
     * @param title the title
     */
    public NmSalesGraphWindow(final Component component, final String title) {
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
        setCloseShortcut(KeyCode.ESC);
        LOGGER.debug("End of init method");

    }
}
