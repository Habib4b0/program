/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.home.ui;

import com.stpl.app.accountclose.ui.view.AccrualDetailsMainView;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
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
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class AccrualDetailsUI extends UI implements VaadinPortletSession.PortletListener {

    Navigator navigator;
    PortletMode customMode;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AccrualDetailsUI.class);

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        navigator = new Navigator(this, this);
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
        try {
            navigator.addView(AccrualDetailsMainView.NAME, new AccrualDetailsMainView());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        customMode = new PortletMode("config");
        if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
            VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
            portletsession.addPortletListener(this);
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

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        CommonUtils.setPortalConfig(portletConfig);
        getUI().getNavigator().navigateTo(AccrualDetailsMainView.NAME);
    }

    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
    }

    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
    }

    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
    }
}
