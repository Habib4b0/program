/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.deductioncalendar.ui.view.DeductionCalendarView;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sibi
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet") 
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=Global Files",
        "javax.portlet.name=DeductionCalendar",
        "javax.portlet.display-name=Deduction Calendar",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class DeductionCalendarUI extends UI {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeductionCalendarUI.class);
    /**
     * Navigator
     */
    Navigator navigator;
    
    SessionDTO sessionDTO=new SessionDTO();

    @Override
    protected void init(VaadinRequest request) {
        try {
            LOGGER.info("Enter Inside DeductionCalendar UI");
            CommonUIUtils.beforeUnloadCloseUi(this,sessionDTO, ConstantUtil.DEDUCTION_CALENDAR);
            setId("DeductionCal");
            addStyleName(ConstantsUtils.BOOTSTRAP);
            addStyleName(ConstantsUtils.BOOTSTRAP_BB);
            navigator = new Navigator(this, this);
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("deductioncalender");
            final String userId = request.getRemoteUser();
            final String sessionId = request.getWrappedSession().getId();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID,userId);
            sessionDTO.setUserId(userId);
            sessionDTO.setUiSessionId(sessionId);
            LOGGER.info("USER_ID: "+userId);
            LOGGER.info("SESSION_ID: "+sessionId);
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            navigator.addView(AbstractSearchView.NAME, new AbstractSearchView(ConstantUtil.DEDUCTION_CALENDAR,sessionDTO));
            navigator.addView(DeductionCalendarView.NAME, new DeductionCalendarView(sessionDTO));
            navigator.setErrorView(new AbstractSearchView(ConstantUtil.DEDUCTION_CALENDAR,sessionDTO));
            
            

            UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
                @Override
                /**
                 * Error event handling.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // Find the final cause

                    final StringBuilder cause = new StringBuilder("The Exception occured because of------>");

                    for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                        if (t.getCause() == null) // We're at final cause
                        {
                            cause.append(t.getClass().getName());
                            LOGGER.error("",t);
                        }
                    }
                    LOGGER.error("",cause);
                }
            });
      LOGGER.info("Ending DeductionCalendar UI");
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}
