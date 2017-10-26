/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.ui;

import com.stpl.app.gtnworkflow.ui.view.InboxDashboardView;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.app.gtnworkflow.util.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.vaadin.annotations.JavaScript;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinPortletSession.PortletListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author satheesh
 */
@JavaScript("js/WorkflowInboxListener.js")
public class WorkflowInboxUI extends UI implements PortletListener {

    private static final long serialVersionUID = -1742854830628481543L;
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(WorkflowInboxUI.class);
    Navigator navigator;
    PortletMode customMode;

    @Override
    protected void init(VaadinRequest request) {

        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        String userId = request.getRemoteUser();
        String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
        String pageUrl = Page.getCurrent().getLocation().toString();
        VaadinSession.getCurrent().setAttribute("pageUrl", pageUrl);
        CommonUtils.beforeUnloadCloseUi(this);
        HelperListUtil hs = HelperListUtil.getInstance();
        hs.loadValuesWithListName("workflowinbox");
        HelperListUtil.getInstance().loadBusinessUnitValues();
        LOGGER.info("USER_ID: "+userId);
        LOGGER.info("SESSION_ID: "+sessionId);
        navigator = new Navigator(this, this);
        /**
         * Registering navigation views
         */
        InboxDashboardView dashboardView = new InboxDashboardView();
        navigator.addView(InboxDashboardView.NAME, dashboardView);
        navigator.setErrorView(dashboardView);
        customMode = new PortletMode("config");
        
        CommonUtils.setUserInfo();
        if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
            VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
            portletsession.addPortletListener(this);
        }
    }

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        getUI().getNavigator().navigateTo(InboxDashboardView.NAME);
    }

    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {

    }

    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {

    }

    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {

    }
}
