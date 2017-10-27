/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui;

import com.stpl.app.forecastdashboard.ui.form.DashBoardGraph;
import com.stpl.portal.kernel.util.JavaConstants;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author maheshj
 */
public class GraphUI extends UI implements VaadinPortletSession.PortletListener {

    Navigator navi;
    LiferayIPC liferayIPC;

    /**
     * This method is used to register the navigations for different views.
     *
     * @param request the request
     */
    @Override
    protected void init(VaadinRequest request) {
        liferayIPC = new LiferayIPC();
        liferayIPC.extend(getUI());
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute("sessionId", sessionId);
        VaadinSession.getCurrent().setAttribute("userId", userId);
        navi = new Navigator(this, this);
        navi.addView("", new DashBoardGraph(liferayIPC));

    }

    @Override
    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
//        System.out.println("comming inside handle Render Request");
        PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

        response.setTitle("Tittle to be set");
//
//        PortletSession session = request.getPortletSession();
//        session.setAttribute("sessionValue", "5", PortletSession.APPLICATION_SCOPE);
//        //    response.se
//        System.out.println("comming out handle Render Request");
    }

    @Override
    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
    }

    @Override
    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
    }

    @Override
    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
    }
}
