/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.home.ui;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.security.StplSecurity;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.view.FixedDollarAdjustMainView;
import com.stpl.app.accountclose.ui.view.FixedDollarAdjustWorkflowView;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.HelperListUtil;
import com.stpl.app.accountclose.utils.SessionUtil;
import com.stpl.portal.kernel.util.JavaConstants;
import com.stpl.portal.util.PortalUtil;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.util.Arrays;
import java.util.HashMap;
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
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class FixedDollarAdjustUI extends UI implements VaadinPortletSession.PortletListener {

    Navigator navigator;
    PortletMode customMode;
    SessionDTO session;
    String pageParameters;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FixedDollarAdjustUI.class);
    CommonLogic logic = new CommonLogic();

    @Override
    protected void init(VaadinRequest request) {
        try {
            addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
            navigator = new Navigator(this, this);
            String userId = request.getRemoteUser();
            final String sessionId = request.getWrappedSession().getId();
            VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
            VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
            StplSecurity.getUserName();
            pageParameters = Page.getCurrent().getLocation().getQuery();
            String accClosureId = null;
            String workflowId = null;
            String workflowStatus = null;
            String workflowApprove = null;
            SessionUtil sessionUtil = new SessionUtil();
            session = sessionUtil.createSession();
            session.setUserId(userId);
            FixedDollarAdjustWorkflowView fixedDollarAdjustWorkflowView = null;
            if (pageParameters != null) {
                String[] parameters = pageParameters.split("&");
                String parametersFromAccClosure[] = null;
                String parametersFromWorkflow[] = null;
                String parametersWorkflowStatus[] = null;
                String parametersWorkflowApprove[] = null;
                String parametersCanApprove[] = null;
                String user[] = null;
                String accoutingClosureId[] = null;
                parametersFromAccClosure = parameters[0].split("=");
                parametersFromWorkflow = parameters[1].split("=");
                parametersWorkflowStatus = parameters[2].split("=");
                parametersWorkflowApprove = parameters[3].split("=");
                parametersCanApprove = parameters[4].split("=");
                accoutingClosureId = parameters[5].split("=");

                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put(parametersFromAccClosure[0], parametersFromAccClosure[1]);
                hm.put(parametersWorkflowStatus[0], parametersWorkflowStatus[1]);
                hm.put(parametersFromWorkflow[0], parametersFromWorkflow[1]);
                hm.put(parametersWorkflowApprove[0], parametersWorkflowApprove[1]);
                hm.put(parametersCanApprove[0], parametersCanApprove[1]);
                hm.put(accoutingClosureId[0], accoutingClosureId[1]);
                accClosureId = hm.get("accClosureIdMasterSid");
                workflowId = hm.get("workflowId");
                workflowStatus = hm.get("workflowStatus");
                workflowApprove = hm.get("userType");
                session.setAcctCloserMasterId(Integer.valueOf(accClosureId));
                session.setWorkflowId(Integer.valueOf(workflowId));
                session.setWorkflow(logic.getWorkflow(Integer.valueOf(workflowId)));
                session.setWorkflowStatus(workflowStatus);
                session.setWorkflowCanApprove(workflowApprove);
                fixedDollarAdjustWorkflowView = new FixedDollarAdjustWorkflowView(session);
            } else {
            }
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("fixeddollaradjustment");
            navigator.addView(FixedDollarAdjustMainView.NAME, new FixedDollarAdjustMainView(session));
            if (fixedDollarAdjustWorkflowView != null) {
                navigator.addView(FixedDollarAdjustWorkflowView.NAME, fixedDollarAdjustWorkflowView);
            }
            if (accClosureId != null) {
                getUI().getNavigator().navigateTo(FixedDollarAdjustWorkflowView.NAME + "/" + pageParameters);
            } else {
                getUI().getNavigator().navigateTo(FixedDollarAdjustMainView.NAME);
            }

            customMode = new PortletMode("config");

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

            if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
                VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
                portletsession.addPortletListener(this);
            }
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param uI
     */
    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        try {
            HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
            String accClosureId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("accClosureIdMasterSid");
            if (accClosureId != null) {
                getUI().getNavigator().navigateTo(FixedDollarAdjustWorkflowView.NAME + "/" + pageParameters);
            } else {
                getUI().getNavigator().navigateTo(FixedDollarAdjustMainView.NAME);
            }
            PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
            CommonUtils.setPortalConfig(portletConfig);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
    }

    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
    }

    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
    }
}
