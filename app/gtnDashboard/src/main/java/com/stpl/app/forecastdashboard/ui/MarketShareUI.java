package com.stpl.app.forecastdashboard.ui;

import com.stpl.app.forecastdashboard.ui.form.MarketShareForm;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class MarketShareUI extends UI {
    /* The Constant LOGGER. */

    private static final Logger LOGGER = Logger.getLogger(MarketShareUI.class);

    @Override
    protected void init(VaadinRequest request) {

        Navigator navigator;
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute("sessionId", sessionId);
        VaadinSession.getCurrent().setAttribute("userId", userId);
        navigator = new Navigator(this, this);
        try {
            navigator.addView("", new MarketShareForm());
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(" Exception occured at MS UI");
        }
    }
}
