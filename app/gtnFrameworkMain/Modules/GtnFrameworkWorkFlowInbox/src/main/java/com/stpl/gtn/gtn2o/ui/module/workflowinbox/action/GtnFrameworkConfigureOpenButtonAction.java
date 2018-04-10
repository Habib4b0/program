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
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		String createdById = null;
		String status = String
				.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.STATUS));
		String adjustmentTypeName = null;
		String adjustmentType = null;
		String workflowSid = null;
		String noOfApprovals = null;
		String approvalLevel = null;
		String projectionMasterSid = null;
		String customerHierSid = null;
		String productHierSid = null;
		String customerHierarchyLevel = null;
		String custRelationshipBuilderSid = null;
		String productHierarchyLevel = null;
		String prodRelationshipBuilderSid = null;
		String configurationType = null;
		String projectionMasterSidGcm = null;
		String workflowSidGcm = null;
		String noOfApprovalsGcm = null;
		String approvalLevelGcm = null;
		String customerHierSidGcm = null;
		String productHierSidGcm = null;
		String customerHierarchyLevelGcm = null;
		String custRelationshipBuilderSidGcm = null;
		String productHierarchyLevelGcm = null;
		String prodRelationshipBuilderSidGcm = null;

		try {

			String projIdfromDataselection = String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(3));

			generalWSRequest.setUserId(String.valueOf(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkWorkflowInboxClassConstants.USERID)));
			String workflowId = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID));
			if (workflowId == null) {
				return;
			}

			if (projIdfromDataselection.contains(GtnFrameworkWorkflowInboxClassConstants.RECORD_BEAN)) {
				createdById = String
						.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CREATEDBYID));
				adjustmentTypeName = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPENAME));
				adjustmentType = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE));
				workflowSid = String
						.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID));
				noOfApprovals = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.NOOFAPPROVALS));
				approvalLevel = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.APPROVALLEVEL));
				projectionMasterSid = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PROJECTIONMASTER_SID));
				customerHierSid = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERSID));
				productHierSid = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PRODUCTHIERSID));
				customerHierarchyLevel = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERLEVEL));
				custRelationshipBuilderSid = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CUSTRELATIONSHIPSID));
				productHierarchyLevel = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PRODHIERLEVEL));
				prodRelationshipBuilderSid = String.valueOf(
						gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.PRODRELATIONSID));
				configurationType = String.valueOf(gtnWsRecordBean.getPropertyValue("configurationType"));

				projMasterBean.setProjectionMasterSid(Integer.parseInt(projectionMasterSid));

			} else {
				createdById = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(19));
				projectionMasterSidGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(18));
				workflowSidGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(10));
				noOfApprovalsGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(11));
				approvalLevelGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(12));
				customerHierSidGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(13));
				productHierSidGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(20));
				customerHierarchyLevelGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(14));
				custRelationshipBuilderSidGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(15));
				productHierarchyLevelGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(16));
				prodRelationshipBuilderSidGcm = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(17));

			}

			String key = workflowId.replaceAll("\\d", "");
			String portletName = GtnFrameworkWorkflowPortletMap.valueOf(key).getInput();

			projMasterBean.setWorkflowId(workflowId);

			boolean isSubmitter = createdById.equals(userId);
			if (isSubmitter) {
				userType = GtnFrameworkWorkflowInboxClassConstants.CREATOR;
			} else {
				loadWebService(generalWSRequest, projMasterBean);
				userType = GtnFrameworkWorkflowuserTypeMap.valueOf(key).getInput();
			}
			GtnUIFrameworkWebserviceResponse friendlyUrlresponse = loadWebServiceforFriendlyUrl(generalWSRequest,
					projMasterBean);
			String furl = getfUrl(key, friendlyUrlresponse);

			BrowserWindowOpener opener = getBrowserOpener(furl);

			checkSubmitter(isSubmitter, status, opencomponent, opener, viewcomponent);

			if (projIdfromDataselection.contains(GtnFrameworkWorkflowInboxClassConstants.RECORD_BEAN)) {
				opener.setParameter(GtnFrameworkWorkflowIdMap.valueOf(key).getInput(), projectionMasterSid);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID, workflowSid);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWISTATUS, status);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.USERTYPE, userType);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.NOOFAPPROVALS,
						String.valueOf(noOfApprovals));
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.APPROVALLEVEL,
						String.valueOf(approvalLevel));
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PORTLETNAME, portletName);
				if (workflowId.startsWith(GtnFrameworkWorkflowInboxClassConstants.CF)) {
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERSID, customerHierSid);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODUCTHIERSID, productHierSid);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERLEVEL,
							customerHierarchyLevel);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTRELATIONSHIPSID,
							custRelationshipBuilderSid);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODHIERLEVEL, productHierarchyLevel);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODRELATIONSID,
							prodRelationshipBuilderSid);
				}
			} else {
				opener.setParameter(GtnFrameworkWorkflowIdMap.valueOf(key).getInput(), projectionMasterSidGcm);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID, workflowSidGcm);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWISTATUS, status);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.USERTYPE, userType);
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.NOOFAPPROVALS,
						String.valueOf(noOfApprovalsGcm));
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.APPROVALLEVEL,
						String.valueOf(approvalLevelGcm));
				opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PORTLETNAME, portletName);
				if (workflowId.startsWith(GtnFrameworkWorkflowInboxClassConstants.CF)) {
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERSID, customerHierSidGcm);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODUCTHIERSID, productHierSidGcm);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTOMERHIERLEVEL,
							customerHierarchyLevelGcm);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.CUSTRELATIONSHIPSID,
							custRelationshipBuilderSidGcm);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODHIERLEVEL,
							productHierarchyLevelGcm);
					opener.setParameter(GtnFrameworkWorkflowInboxClassConstants.PRODRELATIONSID,
							prodRelationshipBuilderSidGcm);
				}
			}

			getARM(workflowId, adjustmentTypeName, opener, adjustmentType, configurationType);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}

	}

	private BrowserWindowOpener getBrowserOpener(String furl) {
		BrowserWindowOpener opener = new BrowserWindowOpener(furl);
		opener.setFeatures(GtnFrameworkWorkflowInboxClassConstants.BROWSER_HEIGHTWIDTH);
		opener.setFeatures(GtnFrameworkWorkflowInboxClassConstants.BROWSER_SCROLLBARS);
		return opener;
	}

	private String getfUrl(String key, GtnUIFrameworkWebserviceResponse friendlyUrlresponse) {
		String furl;
		if (Page.getCurrent().getLocation().getPort() == -1) {
			furl = GtnFrameworkWorkflowInboxClassConstants.HTTPS + Page.getCurrent().getLocation().getHost()
					+ GtnFrameworkWorkflowInboxClassConstants.WEB_GUEST
					+ friendlyUrlresponse.getGtnWSCommonWorkflowResponse().getFriendlyUrl()
					+ GtnFrameworkWorkflowMap.valueOf(key).getInput();
		} else {
			furl = GtnFrameworkWorkflowInboxClassConstants.HTTP + Page.getCurrent().getLocation().getHost() + ":"
					+ Page.getCurrent().getLocation().getPort() + GtnFrameworkWorkflowInboxClassConstants.WEB_GUEST
					+ friendlyUrlresponse.getGtnWSCommonWorkflowResponse().getFriendlyUrl()
					+ GtnFrameworkWorkflowMap.valueOf(key).getInput();
		}
		return furl;
	}

	private void getARM(String workflowId, String adjustmentTypeName, BrowserWindowOpener opener, String adjustmentType,
			String configurationType) {
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
	}

	private void checkSubmitter(boolean isSubmitter, String status, GtnUIFrameworkBaseComponent opencomponent,
			BrowserWindowOpener opener, GtnUIFrameworkBaseComponent viewcomponent) throws GtnFrameworkGeneralException {
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

	private GtnUIFrameworkWebserviceResponse loadWebServiceforFriendlyUrl(GtnWsGeneralRequest generalWSRequest,
			GtnWsWorkflowInboxBean projMasterBean) {
		GtnUIFrameworkWebserviceRequest friendlyUrlRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCommonWorkflowRequest friendlyUrlcommonRequest = new GtnWsCommonWorkflowRequest();
		friendlyUrlRequest.setGtnWsGeneralRequest(generalWSRequest);
		friendlyUrlRequest.setGtnWSCommonWorkflowRequest(friendlyUrlcommonRequest);
		friendlyUrlRequest.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);
		GtnUIFrameworkWebserviceResponse friendlyUrlresponse = new GtnUIFrameworkWebServiceClient()
				.callGtnWebServiceUrl(
						GtnWsWorkFlowConstants.GTN_WS_OPEN_VIEW_SAVE_SERVICE
								+ GtnFrameworkCommonStringConstants.FETCH_PORTLET_ID,
						friendlyUrlRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		friendlyUrlRequest.getGtnWSCommonWorkflowRequest()
				.setFriendlyUrl(friendlyUrlresponse.getGtnWSCommonWorkflowResponse().getFriendlyUrl());
		friendlyUrlRequest.setGtnWSCommonWorkflowRequest(friendlyUrlcommonRequest);
		friendlyUrlRequest.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);
		return friendlyUrlresponse;
	}
    
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
