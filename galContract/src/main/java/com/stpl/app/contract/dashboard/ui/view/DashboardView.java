package com.stpl.app.contract.dashboard.ui.view;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.dashboard.ui.form.DashBoardForm;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 * The Class DashboardView.
 */
public class DashboardView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardView.class);
    SessionDTO sessionDTO;
    
    /**
     * Instantiates a new dashboard view.
     */
    public DashboardView(final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        LOGGER.info("Entering DashboardView ");
        setSpacing(true);
        this.sessionDTO=sessionDTO;
        addComponent(new DashBoardForm(this.sessionDTO));
        LOGGER.info("End of DashboardView ");
    }

    /**
     * This view is navigated to. This method is always called before the view
     * is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        LOGGER.info("Entering  DashboardView enter method");
        LOGGER.info("End of DashboardView enter method");
    }
}
