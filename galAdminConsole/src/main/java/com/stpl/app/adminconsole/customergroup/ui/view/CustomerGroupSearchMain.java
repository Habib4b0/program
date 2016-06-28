/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.view;

import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerGroupSearchMain.
 *
 * @author vishalakshi
 */
public class CustomerGroupSearchMain extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CustomerGroupSearchMain.class);

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;

    AbstractSearchForm abstractSearchForm;
    SessionDTO sessionDTO;

    /**
     * Instantiates a new customer group search main.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public CustomerGroupSearchMain(SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("CustomerGroupSearchMain Constructor method Started");
        this.sessionDTO = sessionDTO;
        addComponent(new AbstractSearchForm(ConstantsUtils.CUSTOMER_GROUP_MASTER, sessionDTO));
        setSpacing(true);
        setStyleName("bootstrap");
        LOGGER.info("CustomerGroupSearchMain Constructor method Ended");

    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        LOGGER.info("CustomerGroupSearchMain Enter method Started");
        try {

            setStyleName("bootstrap");
            setSpacing(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("CustomerGroupSearchMain Enter method Ended");
    }
}
