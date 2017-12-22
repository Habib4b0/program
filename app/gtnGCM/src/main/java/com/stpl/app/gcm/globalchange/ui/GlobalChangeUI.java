/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.ui;

import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.globalchange.ui.view.GlobalChangeUIMainView;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.vaadin.annotations.JavaScript;
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
@JavaScript("js/WorkflowInboxListener.js")
public class GlobalChangeUI extends UI implements VaadinPortletSession.PortletListener {

    Navigator navigator;
    PortletMode customMode;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(GlobalChangeUI.class);

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        navigator = new Navigator(this, this);

        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
        CommonUtils.beforeUnloadCloseUi(this);
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("gcm");
        helperListUtil.loadValuesWithListName("workflowinbox");
        try {
            navigator.addView(GlobalChangeUIMainView.NAME, new GlobalChangeUIMainView());
            navigator.setErrorView(new GlobalChangeUIMainView());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        customMode = new PortletMode("config");
        if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
            VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
            portletsession.addPortletListener(this);
        }
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                // Find the final cause
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) {
                        cause += t.getClass().getName();
                    }

                }
                LOGGER.error(cause);
                // Do the default error handling (optional)
            }
        });

    }

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        CommonUtils.setPortalConfig(portletConfig);
        getUI().getNavigator().navigateTo(GlobalChangeUIMainView.NAME);
    }

    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
        //empty
    }

    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
        // empty
    }

    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
        //empty
    }

}
