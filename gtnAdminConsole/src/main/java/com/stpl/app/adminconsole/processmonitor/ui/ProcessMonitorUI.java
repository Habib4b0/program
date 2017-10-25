/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processmonitor.ui.view.ProcessMonitorMainView;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class ProcessMonitorUI extends UI {

    Navigator navigator;

    private static final Logger LOGGER = Logger.getLogger(ProcessMonitorUI.class);

    SessionDTO sessionDTO = new SessionDTO();

    @Override
    protected void init(VaadinRequest request) {
        LOGGER.info("Inside ProcessMonitor UI INIT()");
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        CommonUIUtil.beforeUnloadCloseUi(this, sessionDTO);
        navigator = new Navigator(this, this);
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        sessionDTO.setUserId(userId);
        sessionDTO.setSessionId(sessionId);
        LOGGER.info("USER_ID: "+userId);
        LOGGER.info("SESSION_ID: "+sessionId);
        try {

            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            navigator.addView(ProcessMonitorMainView.NAME, new ProcessMonitorMainView(sessionDTO));

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
          LOGGER.info("Ends ProcessMonitor UI INIT()");
    }
}
