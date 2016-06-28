/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.home.ui;

import com.stpl.app.accountclose.ui.view.ProcessMonitorMainView;
import com.stpl.app.accountclose.utils.Constants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import org.jboss.logging.Logger;

/**
 *
 * @author mihirkumar.b
 */
public class ProcessMonitorUI extends UI {

    Navigator navigator;
    private static final Logger LOGGER = Logger.getLogger(ProcessSchedulerUI.class);

    /**
     *
     * @param request
     */
    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        navigator = new Navigator(this, this);
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
        try {

            navigator.addView(ProcessMonitorMainView.NAME, new ProcessMonitorMainView());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) {

                        cause += t.getClass().getName();
                    }
                }
                LOGGER.error(cause);
            }
        });
    }

}
