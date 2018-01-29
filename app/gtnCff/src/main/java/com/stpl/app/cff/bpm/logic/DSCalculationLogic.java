package com.stpl.app.cff.bpm.logic;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.stpl.app.cff.bpm.service.BPMProcessBean;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.util.StringConstantsUtil;
import java.util.List;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cff.GtnWsCFFSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsCFFSubmitBean;
import com.stpl.app.cff.ui.dataSelection.logic.RelationShipFilterLogic;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;
import org.kie.api.task.model.TaskSummary;
import org.slf4j.LoggerFactory;

public class DSCalculationLogic {

	/**
	 * The Constant LOGGER.
	 */
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DSCalculationLogic.class);

	


	public static boolean isValidWorkflowUser(User userModel, List<String> roleList, long processIntanceId) {
		boolean returnflag = false;
		TaskSummary taskSummary = null;
		try {

                    LOGGER.debug("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processIntanceId);
                        if(taskSummary == null){
                           LOGGER.debug("taskSummary id:" + taskSummary.getId());                        
                           return true;
                        }
                        LOGGER.debug(StringConstantsUtil.TASK_SUMMARY + taskSummary.getName());
			LOGGER.debug(StringConstantsUtil.TASK_SUMMARY + taskSummary.getId());
			List<String> userRoles = BPMProcessBean.getPotentialOwners(taskSummary.getId(), roleList);
			LOGGER.debug("userRoles :" + userRoles);
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(userModel.getUserId());
			if (userRoles == null || userRoles.isEmpty()) {
				return returnflag;
			}
			for (Role role : roles) {
				if (userRoles.contains(role.getName())) {
					returnflag = true;
					break;
				}
			}
		} catch (Exception e) {
                    LOGGER.error(e.getMessage());
		}

		return returnflag;
	}

	

	public static GtnWsCommonWorkflowResponse startWorkflow(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitBean = new GtnWsCFFSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsCFFSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_START_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        session.setProcessId(workFlowResponse.getProcessInstanceId());
        return workFlowResponse;
    }

	public static GtnWsCommonWorkflowResponse startAndCompleteTask(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitBean = new GtnWsCFFSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        submitBean.setProcessId(session.getProcessId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsCFFSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_COMPLETE_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        session.setProcessId(workFlowResponse.getProcessInstanceId());
        return workFlowResponse;
    }

    public static String getProcessVariableLog(Long processId, String processVariableName) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitProcessBean = new GtnWsCFFSubmitBean();
        submitProcessBean.setProcessId(processId);
        submitProcessBean.setVariableName(processVariableName);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        submitRequest.setGtnWsCFFSubmitBean(submitProcessBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_GET_VARIABLE,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        return workFlowResponse.getProcessVariable();
    }
    
    public static GtnWsCommonWorkflowResponse isValidWorkflowUser(String userId,Long processId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceUserRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitUserRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitUserBean = new GtnWsCFFSubmitBean();
        GtnWsGeneralRequest validUserRequest = new GtnWsGeneralRequest();
        validUserRequest.setUserId(userId);
        submitUserRequest.setGtnWsCFFSubmitBean(submitUserBean);
        submitUserRequest.setGtnWsGeneralRequest(validUserRequest);
        submitUserRequest.setProcessId(processId);
        gtnUIFrameworkWebserviceUserRequest.setGtnCffsubmitRequest(submitUserRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_IS_VALID_USER,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceUserRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        return workFlowResponse;
    }
}
