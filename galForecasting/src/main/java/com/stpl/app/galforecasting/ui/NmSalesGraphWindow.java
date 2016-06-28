/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui;

import com.stpl.app.galforecasting.utils.StplWindow;
import com.vaadin.ui.Component;
import elemental.events.KeyboardEvent.KeyCode;
import org.jboss.logging.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(NmSalesGraphWindow.class);

    /**
     * The Constructor.
     *
     * @param component the component
     * @param title the title
     */
    public NmSalesGraphWindow(final Component component, final String title) {
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
        setCloseShortcut(KeyCode.ESC);
        LOGGER.info("End of init method");

    }
}
