/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import org.jboss.logging.Logger;

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
        final String sessionId = request.getWrappedSession().getId();
        sessionDTO.setUserId(userId);
        sessionDTO.setSessionId(sessionId);
        try {
            navigator.addView(CalendarMainView.NAME, new CalendarMainView(sessionDTO));
            navigator.addView(CalendarEditView.NAME, new CalendarEditView(sessionDTO));
            navigator.setErrorView(CalendarMainView.class);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
}
