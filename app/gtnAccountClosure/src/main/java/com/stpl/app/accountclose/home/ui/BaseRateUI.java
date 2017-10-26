/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.home.ui;

import com.stpl.app.accountclose.bpm.persistance.WorkflowPersistance;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.view.BaseRateMainView;
import com.stpl.app.accountclose.ui.view.BaseRateWorkflowView;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
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
import java.util.List;
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
public class BaseRateUI extends UI implements VaadinPortletSession.PortletListener {

    Navigator navigator;
    PortletMode customMode;
    SessionDTO session;
    String pageParameters;
    CommonLogic logic = new CommonLogic();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseRateUI.class);

    /**
     *
     * @param request
     */
    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        pageParameters = Page.getCurrent().getLocation().getQuery();
        String accClosureId = null;
        String workflowId = null;
        String workflowStatus = null;
        String workflowApprove = null;
        SessionUtil sessionUtil = new SessionUtil();
        session = sessionUtil.createSession();
        BaseRateWorkflowView baseRateWorkflowView = null;
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
        session.setUserId(userId);
        if (pageParameters != null) {
            LOGGER.info("pageParameters-----" + pageParameters);
            String[] parameters = pageParameters.split("&");
            String parametersFromAccClosure[] = null;
            String parametersFromWorkflow[] = null;
            String parametersWorkflowStatus[] = null;
            String parametersWorkflowApprove[] = null;
            String parametersCanApprove[] = null;
            String accountClosureId[] = null;
            parametersFromAccClosure = parameters[0].split("=");
            parametersFromWorkflow = parameters[2].split("=");
            parametersWorkflowStatus = parameters[1].split("=");
            parametersWorkflowApprove = parameters[3].split("=");
            parametersCanApprove = parameters[4].split("=");
            accountClosureId = parameters[5].split("=");
            LOGGER.info("parametersFromProjection-----" + parametersFromAccClosure);
            LOGGER.info("parametersFromWorkflow-----" + parametersFromWorkflow);
            LOGGER.info("parametersWorkflowStatus-----" + parametersWorkflowStatus);
            LOGGER.info("parametersWorkflowApprove-----" + parametersWorkflowApprove);
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put(parametersFromAccClosure[0], parametersFromAccClosure[1]);
            hm.put(parametersWorkflowStatus[0], parametersWorkflowStatus[1]);
            hm.put(parametersFromWorkflow[0], parametersFromWorkflow[1]);
            hm.put(parametersWorkflowApprove[0], parametersWorkflowApprove[1]);
            hm.put(parametersCanApprove[0], parametersCanApprove[1]);
            hm.put(accountClosureId[0], accountClosureId[1]);
            LOGGER.info("parametersWorkflowApprove[0]-----" + Arrays.asList(parametersFromAccClosure));
            LOGGER.info("parametersWorkflowApprove[0]-----" + Arrays.asList(parametersWorkflowStatus));
            LOGGER.info("parametersWorkflowApprove[0]-----" + Arrays.asList(parametersFromWorkflow));
            LOGGER.info("parametersWorkflowApprove[0]-----" + Arrays.asList(parametersWorkflowApprove));
            LOGGER.info("parametersCanApprove[0]-----" + Arrays.asList(parametersCanApprove));
            accClosureId = hm.get("accClosureIdMasterSid");
            workflowId = hm.get("workflowId");
            workflowStatus = hm.get("workflowStatus");
            workflowApprove = hm.get("userType");
            LOGGER.info("accClosureId: " + accClosureId);
            session.setAcctCloserMasterId(Integer.valueOf(accClosureId));
            List list = WorkflowPersistance.selectWFInstanceInfo(Integer.valueOf(accClosureId));
                Long processId = 0L;
                if (list != null && !list.isEmpty()) {
                    processId = Long.valueOf(list.get(0).toString());
                }

                session.setProcessId(processId);
            try {
                session.setWorkflowId(Integer.valueOf(workflowId));
                session.setWorkflow(logic.getWorkflow(Integer.valueOf(workflowId)));
                session.setWorkflowStatus(workflowStatus);
                session.setWorkflowCanApprove(workflowApprove);
                baseRateWorkflowView = new BaseRateWorkflowView(session);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else {
            LOGGER.info("pageParameters----- IS NULL");
        }
        navigator = new Navigator(this, this);

        try {
            navigator.addView(BaseRateMainView.NAME, new BaseRateMainView(session));
            if (baseRateWorkflowView != null) {
                navigator.addView(BaseRateWorkflowView.NAME, baseRateWorkflowView);
            }
            if (accClosureId != null) {
                getUI().getNavigator().navigateTo(BaseRateWorkflowView.NAME + "/" + pageParameters);
            } else {
                getUI().getNavigator().navigateTo(BaseRateMainView.NAME);
            }
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
                    if (t.getCause() == null) 
                    {
                        
                        cause += t.getClass().getName();
                    }
                }
                LOGGER.error(cause);
            }
        });
    }
    /**
     *
     * @param request
     * @param response
     * @param uI
     */
    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        try {
            LOGGER.info("handleRenderRequest");
            HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
            String accClosureId = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("accClosureIdMasterSid");
            if (accClosureId != null) {
                getUI().getNavigator().navigateTo(BaseRateWorkflowView.NAME + "/" + pageParameters);
            } else {
                getUI().getNavigator().navigateTo(BaseRateMainView.NAME);
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
