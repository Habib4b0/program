package com.stpl.app.contract.dashboard.ui;

import com.stpl.app.contract.bpm.logic.WorkflowLogic;
import com.stpl.app.contract.bpm.persistance.WorkflowPersistance;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.dashboard.ui.view.DashboardEditView;
import com.stpl.app.contract.dashboard.ui.view.DashboardView;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.ifs.ui.DateToStringConverterFactory;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.util.HashMap;
import org.jboss.logging.Logger;

@SuppressWarnings("serial")
/**
 * Method used for UI named as DashboardUI
 *
 * @author gopinath
 *
 */
public class DashboardUI extends UI {

    /**
     * LOGGER for Logging
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardUI.class);

    SessionDTO sessionDto = new SessionDTO();
    private static final WorkflowLogic WORKFLOW_LOGIC = new WorkflowLogic();
    String pageParameters;

    @Override
    /**
     * Method used for init
     */
    protected void init(final VaadinRequest request) {
        boolean isViaWorkflow = false;
        try {
            /**
             * Variable used for userId
             */
            final String userId = request.getRemoteUser();
            /**
             * Variable used for sessionId
             */
            LOGGER.info("Entering init method");
            setId("CONTRACTDASHBOARD");
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            CommonUIUtils.beforeUnloadCloseUi(this, sessionDto);
            final String sessionId = request.getWrappedSession().getId();
            VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
            VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
            VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
            StplSecurity.getUserName();
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("contractDashboard");
            LOGGER.info("USER_ID   : "+userId);
            LOGGER.info("SESSION_ID: "+sessionId);
            pageParameters = Page.getCurrent().getLocation().getQuery();
            String contractMasterSid = null;
            String workflowId = null;
            String workflowStatus = null;
            String userType = null;
            if (pageParameters != null) {

                String[] parameters = pageParameters.split("&");
                String parametersFromProjection[] = null;
                String parametersFromWorkflow[] = null;
                String parametersWorkflowStatus[] = null;
                String parametersContractMasterSid[] = null;
                String parametersUserType[] = null;
                String noOfApproval[] = null;
                String approvalLevel[] = null;
                parametersFromProjection = parameters[0].split("=");
                parametersWorkflowStatus = parameters[NumericConstants.ONE].split("=");
                parametersFromWorkflow = parameters[NumericConstants.TWO].split("=");
                parametersContractMasterSid = parameters[NumericConstants.THREE].split("=");
                noOfApproval = parameters[NumericConstants.FIVE].split("=");
                parametersUserType = parameters[NumericConstants.SIX].split("=");
                approvalLevel = parameters[NumericConstants.SEVEN].split("=");

                HashMap<String, String> hm = new HashMap();
                hm.put(parametersFromProjection[0], parametersFromProjection[NumericConstants.ONE]);
                hm.put(parametersWorkflowStatus[0], parametersWorkflowStatus[NumericConstants.ONE]);
                hm.put(parametersFromWorkflow[0], parametersFromWorkflow[NumericConstants.ONE]);
                hm.put(parametersContractMasterSid[0], parametersContractMasterSid[NumericConstants.ONE]);
                hm.put(parametersUserType[0], parametersUserType[NumericConstants.ONE]);
                hm.put(noOfApproval[0], noOfApproval[NumericConstants.ONE]);
                hm.put(approvalLevel[0], approvalLevel[NumericConstants.ONE]);

                contractMasterSid = hm.get("contractMasterSid");
                workflowId = hm.get("workflowId");
                workflowStatus = hm.get("workflowStatus");
                userType = hm.get("userType");
                sessionDto.setWorkflow(WORKFLOW_LOGIC.getWorkflowMaster(Integer.valueOf(workflowId)));
                sessionDto.setProcessIntanceId(WorkflowPersistance.selectWFInstanceInfo(sessionDto.getWorkflow().getContractMasterSid(), sessionDto.getWorkflow().getContractStructure()));
                sessionDto.setUserType(userType);
                sessionDto.setWorkflowStatus(workflowStatus);
                /**
                 * setting the contract system id , cfp, ifp, ps,rs Systems IDs
                 */
                LOGGER.info("Redirecting from WorkflowInbox with contractMasterSid :" + contractMasterSid);
                Integer[] contractStructure = WorkflowLogic.siplitContractStructure(sessionDto.getWorkflow().getContractStructure());
                sessionDto.setContractSystemId(sessionDto.getWorkflow().getContractMasterSid());
                sessionDto.setCfpSystemId(contractStructure[0]);
                sessionDto.setIfpSystemId(contractStructure[NumericConstants.ONE]);
                sessionDto.setPsSystemId(contractStructure[NumericConstants.TWO]);
                sessionDto.setRsSystemId(contractStructure[NumericConstants.THREE]);
                sessionDto.setUserId(userId);
                sessionDto.setEdit(Constants.N);
                try {
                    if (WorkflowConstants.getApproverConstant().equals(userType)) {
                        sessionDto.setEdit(Constants.N);
                    } else if (WorkflowConstants.getCreatorConstant().equals(userType) && WorkflowConstants.getRejectedStatus().equals(workflowStatus)) {
                        sessionDto.setEdit(Constants.Y);
                    }
                    isViaWorkflow = true;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            final Navigator navigator = new Navigator(this, this);
            if (isViaWorkflow) {
                navigator.addView(DashboardEditView.WORFLOW_NAME, new DashboardEditView(sessionDto));
            } else {
                navigator.addView(DashboardView.NAME, new DashboardView(sessionDto));
                navigator.addView(DashboardEditView.NAME, new DashboardEditView(sessionDto));
            }

            UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
                @Override
                /**
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    final StringBuffer cause = new StringBuffer("The Exception occured because of------>");
                    LOGGER.error(event.getThrowable().getMessage());
                    for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {

                        if (t.getCause() == null) {

                            cause.append(t.getClass().getName());
                            LOGGER.error(t.getMessage());
                        }
                    }
                    LOGGER.error(cause);

                }
            });
            LOGGER.info("End of init method");
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
        } catch (Exception ex) {
            LOGGER.error(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
        }
    }
}
