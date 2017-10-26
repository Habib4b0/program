/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.calendar.ui.view.CalendarSearchView;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class CalendarUI extends UI {

    Navigator navigator;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CalendarUI.class);

    SessionDTO sessionDTO = new SessionDTO();

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        CommonUIUtil.beforeUnloadCloseUi(this, sessionDTO);
        navigator = new Navigator(this, this);
        final String userId = request.getRemoteUser();
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
        sessionDTO.setUserId(userId);
        sessionDTO.setSessionId(sessionId);
        LOGGER.info("USER_ID: " + userId);
        LOGGER.info("SESSION_ID: " + sessionId);
        try {
            navigator.addView(CalendarSearchView.NAME, new CalendarSearchView(sessionDTO));
            navigator.setErrorView(CalendarSearchView.class);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                // Find the final cause
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) // We're at final cause
                    {

                        cause += t.getClass().getName() + " " + t.getMessage();
                    }

                }

                LOGGER.error(cause);
                // Do the default error handling (optional)
            }
        });
    }

}
