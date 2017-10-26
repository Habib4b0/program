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
    public CustomerGroupSearchMain(SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("CustomerGroupSearchMain Constructor method Started");
        this.sessionDTO = sessionDTO;
        abstractSearchForm = new AbstractSearchForm(ConstantsUtils.CUSTOMER_GROUP_MASTER, sessionDTO);
        addComponent(abstractSearchForm);
        setSpacing(true);
        setStyleName("bootstrap");
        LOGGER.debug("CustomerGroupSearchMain Constructor method Ended");

    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("CustomerGroupSearchMain Enter method Started");
        try {
            abstractSearchForm.removeOnViewChange();
            setStyleName("bootstrap");
            setSpacing(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("CustomerGroupSearchMain Enter method Ended");
    }
}
