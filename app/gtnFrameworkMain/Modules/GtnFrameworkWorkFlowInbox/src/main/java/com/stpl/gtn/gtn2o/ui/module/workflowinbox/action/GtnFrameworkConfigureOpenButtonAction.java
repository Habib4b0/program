package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowIdMap;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowMap;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowPortletMap;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowuserTypeMap;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;

public class GtnFrameworkConfigureOpenButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkConfigureOpenButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkConfigureOpenButtonAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean = gtnUIFrameWorkActionConfig.getActionParameter().getItemId();
		GtnWsWorkflowInboxBean projMasterBean = new GtnWsWorkflowInboxBean();
		GtnUIFrameworkBaseComponent opencomponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.OPENBTN);
		GtnUIFrameworkBaseComponent viewcomponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN);
		String userType = null;
		String portletName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		try {
			generalWSRequest.setUserId(String.valueOf(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkWorkflowInboxClassConstants.USERID)));
			String workflowId = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID));
			if (workflowId == null) {
				return;
			}
			String status = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.STATUS));
			String adjustmentTypeName = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPENAME));
			String adjustmentType = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE));
			String workflowSid = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID));
			String noOfApprovals = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.NOOFAPPROVALS));
			String approvalLevel = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.APPROVALLEVEL));
			String createdById = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CREATEDBYID));
			String projectionMasterSid = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PROJECTIONMASTER_SID));
			String customerHierSid = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERSID));
			String customerHierarchyLevel = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERLEVEL));
			String custRelationshipBuilderSid = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CUSTRELATIONSHIPSID));
			String productHierarchyLevel = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PRODHIERLEVEL));
			String prodRelationshipBuilderSid = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PRODRELATIONSID));
			String configurationType = String.valueOf(gtnWsRecordBean.getPropertyValue("configurationType"));

			projMasterBean.setProjectionMasterSid(Integer.valueOf(projectionMasterSid));

			String key = workflowId.replaceAll("\\d", "");

			projMasterBean.setWorkflowId(workflowId);

			boolean isSubmitter = createdById.equals(userId);
			if (isSubmitter) {
				userType = GtnFrameworkWorkflowInboxClassConstants.CREATOR;
			} else {
				loadWebService(generalWSRequest, projMasterBean);
				userType = GtnFrameworkWorkflowuserTypeMap.valueOf(key).getInput();
			}

			String furl;
			if (Page.getCurrent().getLocation().getPort() == -1) {
				furl = GtnFrameworkWorkflowInboxClassConstants.HTTPS + Page.getCurrent().getLocation().getHost()
						+ GtnFrameworkWorkflowMap.valueOf(key).getInput();
			} else {
				furl = GtnFrameworkWorkflowInboxClassConstants.HTTP + Page.getCurrent().getLocation().getHost() + ":"
						+ Page.getCurrent().getLocation().getPort() + GtnFrameworkWorkflowMap.valueOf(key).getInput();
			}

			BrowserWindowOpener opener = new BrowserWindowOpener(furl);
			opener.setFeatures(GtnFrameworkWorkflowInboxClassConstants.BROWSER_HEIGHTWIDTH);
			opener.setFeatures(GtnFrameworkWorkflowInboxClassConstants.BROWSER_SCROLLBARS);

			if (isSubmitter && (GtnFrameworkWorkflowInboxClassConstants.WITHDRAWN.equalsIgnoreCase(status)
					|| GtnFrameworkWorkflowInboxClassConstants.REJECTED.equalsIgnoreCase(status)
					|| GtnFrameworkWorkflowInboxClassConstants.PENDING.equals(status))) {

				GtnUIFrameworkGlobalUI.addChildComponent(GtnFrameworkWorkflowInboxClassConstants.OPEN_LAYOUT,
						Arrays.asList(opencomponent.getComponentConfig()));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.OPENBTN)
						.browserExtend(opener);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN)
						.setComponentEnable(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.OPENBTN)
						.setComponentEnable(true);

			} else {

				GtnUIFrameworkGlobalUI.addChildComponent(GtnFrameworkWorkflowInboxClassConstants.VIEW_LAYOUT,
						Arrays.asList(viewcomponent.getComponentConfig()));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN)
						.browserExtend(opener);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN)
						.setComponentEnable(true);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.OPENBTN)
						.setComponentEnable(false);
			}

			opener.setParameter(GtnFrameworkWorkflowIdMap.valueOf(key).getInput(), projectionMasterSid);
			portletName = GtnFrameworkWorkflowPortletMap.valueOf(key).getInput();
			opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID, workflowSid);
			opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWISTATUS, status);
			opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.USERTYPE, userType);
			opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.NOOFAPPROVALS, String.valueOf(noOfApprovals));
			opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.APPROVALLEVEL, String.valueOf(approvalLevel));
			opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PORTLETNAME, portletName);

			if (workflowId.startsWith(GtnFrameworkWorkflowInboxClassConstants.ARM)) {
				String adjType = adjustmentTypeName.trim().replaceAll(" ", "~");
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE, adjType);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.SELECTED_ADJUSTMENTTYPE,
						adjustmentType.replaceAll(" ", "~").contains("&")
								? adjustmentType.replaceAll(" ", "~").replace("&", "///&")
								: adjustmentType.replaceAll(" ", "~"));
				String configType = configurationType.trim().replaceAll(" ", "~");
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CONFIGURATION_TYPE, configType);
			}

			if (workflowId.startsWith(GtnFrameworkWorkflowInboxClassConstants.CF)) {
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERSID, customerHierSid);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERLEVEL, customerHierarchyLevel);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTRELATIONSHIPSID,
						custRelationshipBuilderSid);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODHIERLEVEL, productHierarchyLevel);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODRELATIONSID,
						prodRelationshipBuilderSid);
			}

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}

	}

	private void loadWebService(GtnWsGeneralRequest generalWSRequest, GtnWsWorkflowInboxBean projMasterBean) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCommonWorkflowRequest forecastRequest = new GtnWsCommonWorkflowRequest();
		String url;
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		request.setGtnWsGeneralRequest(generalWSRequest);
		request.setGtnWSCommonWorkflowRequest(forecastRequest);
		request.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsWorkFlowConstants.GTN_WS_OPEN_VIEW_SAVE_SERVICE
						+ GtnFrameworkCommonStringConstants.WORKFLOW_MODULE_NAME,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		response.getGtnWSCommonWorkflowResponse().getProcessInstanceId();
		request.getGtnWSCommonWorkflowRequest()
				.setProcessInstanceId(response.getGtnWSCommonWorkflowResponse().getProcessInstanceId());
		request.setGtnWSCommonWorkflowRequest(forecastRequest);
		request.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);

		GtnUIFrameworkWebserviceResponse bpmresponse;
		url = GtnWsWorkFlowConstants.GTN_WS_BPM_OPEN_VIEW_SAVE_SERVICE
				+ GtnWsWorkFlowConstants.GTN_WS_BPM_OPEN_VIEW_SAVE_SERVICE_URI;
		bpmresponse = wsclient.callGtnWebServiceUrl(url, GtnFrameworkCommonStringConstants.GTN_BPM, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		bpmresponse.getGtnWSCommonWorkflowResponse().isRoleMatched();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
