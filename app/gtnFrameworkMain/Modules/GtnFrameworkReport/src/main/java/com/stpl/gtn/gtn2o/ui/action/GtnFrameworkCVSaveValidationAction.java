/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.vaadin.data.TreeData;
import com.vaadin.ui.TreeGrid;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCVSaveValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> paramList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String[] inputFields = (String[]) paramList.get(1);

		String customViewName = (String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inputFields[0], componentId)
				.getFieldValue();
		validateEmptyViewName(customViewName, componentId);

		saveCustomView(componentId, paramList);
	}

	private void validateEmptyViewName(String customViewName, String componentId) throws GtnFrameworkGeneralException {
		if (customViewName == null || customViewName.equals("")) {
			GtnUIFrameWorkActionConfig noLevelChoosedAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			String missingViewName = "Missing Tree View Name";
			noLevelChoosedAction.addActionParameter("Missing Required Field");
			noLevelChoosedAction.addActionParameter(missingViewName);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, noLevelChoosedAction);
			throw new GtnFrameworkSkipActionException("No Name found");

		}

	}

	private void saveCustomView(String componentId, List<Object> paramList) throws GtnFrameworkGeneralException {
		TreeGrid<GtnWsRecordBean> treeComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) paramList.get(2), componentId).getTreeGrid();
		TreeData<GtnWsRecordBean> treeData = treeComponent.getTreeData();
		String[] fields = (String[]) paramList.get(1);
		String customViewName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[0]).getV8StringFromField();
		int customerRelationSid = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[1])
				.getIntegerFromV8ComboBox();
		int productRelationSid = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[2])
				.getIntegerFromV8ComboBox();
		String variableType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[3]).getV8StringFromField();
		String rowType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[4]).getV8StringFromField();

		validateEmptyTree(treeData, componentId);
		validateTreeStructre(treeData, componentId, rowType, variableType);

		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest reportCustomViewRequest = new GtnWsCustomViewRequest();
		request.setGtnWsCustomViewRequest(reportCustomViewRequest);
		reportCustomViewRequest.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
		String separator = "~";
		String customViewType = "report" + separator + variableType + separator + rowType;
		reportCustomViewRequest.setCustomViewName(customViewName);
		reportCustomViewRequest.setCustomViewDescription(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		reportCustomViewRequest.setCustomerRelationshipSid(customerRelationSid);
		reportCustomViewRequest.setProductRelationshipSid(productRelationSid);
		reportCustomViewRequest.setCustomViewType(customViewType);
		boolean isSelectButton = (boolean) paramList.get(4);
		if (!isSelectButton
				&& String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).equalsIgnoreCase("Edit")) {
			reportCustomViewRequest.setCvSysId(
					Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("customSid"))));
		}
		List<GtnWsRecordBean> treeNodeList = new ArrayList<>();
		getAllTreeNodes(treeData, treeNodeList, treeData.getRootItems());
		reportCustomViewRequest.setCvTreeNodeList(treeNodeList);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CHECK_CUSTOM_VIEW_SAVE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
		if (cvResponse.isSuccess()) {
			saveCustomView(componentId, customViewName, reportCustomViewRequest, paramList);
		} else if (isSelectButton) {
			selectButtonAction(componentId, (GtnUIFrameWorkActionConfig) paramList.get(5));
		} else {
			GtnUIFrameWorkActionConfig cvSaveAlertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			cvSaveAlertAction.addActionParameter(cvResponse.getMessageType());
			cvSaveAlertAction.addActionParameter(cvResponse.getMessage());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cvSaveAlertAction);
		}

	}

	private void validateTreeStructre(TreeData<GtnWsRecordBean> treeData, String componentId, String rowType,
			String variableType) throws GtnFrameworkGeneralException {
		if (rowType.equals("Rows") && variableType.equals("Expandable")) {
			List<GtnWsRecordBean> rootItems = treeData.getRootItems();
			if (!treeContainsVariables(treeData, componentId, rootItems)) {
				GtnUIFrameWorkActionConfig wrongVariableAlertAction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.ALERT_ACTION);
				wrongVariableAlertAction.addActionParameter("Variables level");
				wrongVariableAlertAction.addActionParameter(
						"When Variable Position = Rows and Variable Type = Expandable, the ‘Variables’ level must be included within the Tree Structure list view.");
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, wrongVariableAlertAction);
				throw new GtnFrameworkSkipActionException("Expandable is not selected");
			}
		}
	}

	private boolean treeContainsVariables(TreeData<GtnWsRecordBean> treeData, String componentId,
			List<GtnWsRecordBean> parentItems) {
		boolean isExpandable = false;
		for (GtnWsRecordBean gtnWsRecordBean : parentItems) {
			if ("Variables".equals(gtnWsRecordBean.getStringPropertyByIndex(0))) {
				isExpandable = true;
				break;
			}
			isExpandable = treeContainsVariables(treeData, componentId, treeData.getChildren(gtnWsRecordBean));
		}
		return isExpandable;
	}

	public List<GtnWsRecordBean> getAllTreeNodes(TreeData<GtnWsRecordBean> treeData, List<GtnWsRecordBean> treeNodeList,
			List<GtnWsRecordBean> parentItems) throws GtnFrameworkValidationFailedException {

		for (GtnWsRecordBean gtnWsRecordBean : parentItems) {

			treeNodeList.add(gtnWsRecordBean);
			getAllTreeNodes(treeData, treeNodeList, treeData.getChildren(gtnWsRecordBean));
		}
		return treeNodeList;
	}

	public void validateEmptyTree(TreeData<GtnWsRecordBean> treeData, String componentId)
			throws GtnFrameworkGeneralException {
		if (treeData.getRootItems() == null || treeData.getRootItems().isEmpty()) {
			GtnUIFrameWorkActionConfig noLevelChoosedAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			noLevelChoosedAction.addActionParameter("Missing Level");
			noLevelChoosedAction.addActionParameter("Please add at least one level to the Tree Structure list view.");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, noLevelChoosedAction);
			throw new GtnFrameworkSkipActionException("Tree is not Constructed");
		}
	}

	private void saveCustomView(String componentId, String customViewName, GtnWsCustomViewRequest cvRequest,
			List<Object> paramList) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Save record " + customViewName + " ?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
		saveActionConfig.addActionParameter(cvRequest);
		saveActionConfig.addActionParameter((String) paramList.get(3));
		successActionConfigList.add(saveActionConfig);
		if (paramList.size() > 5) {
			for (int i = 5; i < paramList.size(); i++) {
				GtnUIFrameWorkActionConfig actionConfig = (GtnUIFrameWorkActionConfig) paramList.get(i);
				successActionConfigList.add(actionConfig);
			}
		}
		confirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
	}

	private void selectButtonAction(String componentId, GtnUIFrameWorkActionConfig closeActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig selectActionConfig = new GtnUIFrameWorkActionConfig();
		selectActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, selectActionConfig);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closeActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
