/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui;

import com.stpl.app.accountclose.gtnbalancereport.ui.view.GtnBalanceMainView;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.HelperListUtil;
import com.stpl.portal.kernel.util.JavaConstants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class GtnBalanceUI extends UI {

    Navigator navigator;
    PortletMode customMode;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(GtnBalanceUI.class);

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        navigator = new Navigator(this, this);
        PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        PortletRequest portletRequest = (PortletRequest) request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("gtnBalanceReport");
        try {
            navigator.addView(GtnBalanceMainView.NAME, GtnBalanceMainView.class);
            navigator.setErrorView(GtnBalanceMainView.class);
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
        customMode = new PortletMode("config");
        if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
        }

    }

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        getUI().getNavigator().navigateTo(GtnBalanceMainView.NAME);
    }

    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
    }

    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
    }

    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
    }

}
