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
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
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
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			String missingViewName = "Missing Tree View Name";
			noLevelChoosedAction.addActionParameter(missingViewName);
			noLevelChoosedAction.addActionParameter("Missing Required Field");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, noLevelChoosedAction);
			throw new GtnFrameworkSkipActionException("No Name found");

		}

	}

	private void saveCustomView(String componentId, List<Object> paramList) throws GtnFrameworkGeneralException {
		TreeGrid<GtnWsRecordBean> treeComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) paramList.get(2), componentId).getTreeGrid();
		TreeData<GtnWsRecordBean> treeData = treeComponent.getTreeData();
		if (validateEmptyTree(treeData)) {
			return;
		}
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest reportCustomViewRequest = new GtnWsCustomViewRequest();
		request.setGtnWsCustomViewRequest(reportCustomViewRequest);
		String[] fields = (String[]) paramList.get(1);
		reportCustomViewRequest.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
		String customViewName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[0]).getV8StringFromField();
		// String customViewDescription =
		// GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[1])
		// .getStringFromField();
		int customerRelationSid = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[1])
				.getIntegerFromV8ComboBox();
		int productRelationSid = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[2])
				.getIntegerFromV8ComboBox();
		String rowType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fields[3]).getV8StringFromField();
		String customViewType = "report" + rowType;
		reportCustomViewRequest.setCustomViewName(customViewName);
		reportCustomViewRequest.setCustomViewDescription(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		reportCustomViewRequest.setCustomerRelationshipSid(customerRelationSid);
		reportCustomViewRequest.setProductRelationshipSid(productRelationSid);
		reportCustomViewRequest.setCustomViewType(customViewType);

		if (String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).equalsIgnoreCase("Edit")) {
			reportCustomViewRequest.setCvSysId(
					Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("customSid"))));
		}
		// GtnUIFrameworkBaseComponent cvTreeBaseComponent = GtnUIFrameworkGlobalUI
		// .getVaadinBaseComponent(paramList.get(3).toString());
		List<GtnWsRecordBean> treeNodeList = new ArrayList<>();
		// GtnWsCustomTreeData customTreeData = buildCustomTreeData(treeData);
		getAllTreeNodes(treeData, treeNodeList, treeData.getRootItems());
		reportCustomViewRequest.setCvTreeNodeList(treeNodeList);
		// reportCustomViewRequest.setGtnWsCustomTreeData(customTreeData);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CHECK_CUSTOM_VIEW_SAVE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
		if (cvResponse.isSuccess()) {
			saveCustomView(componentId, customViewName, reportCustomViewRequest,(String) paramList.get(3));
		} else {
			GtnUIFrameWorkActionConfig cvSaveAlertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			cvSaveAlertAction.addActionParameter(cvResponse.getMessageType());
			cvSaveAlertAction.addActionParameter(cvResponse.getMessage());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cvSaveAlertAction);
		}

	}

	public List<GtnWsRecordBean> getAllTreeNodes(TreeData<GtnWsRecordBean> treeData, List<GtnWsRecordBean> treeNodeList,
			List<GtnWsRecordBean> parentItems) throws GtnFrameworkValidationFailedException {

		for (GtnWsRecordBean gtnWsRecordBean : parentItems) {

			treeNodeList.add(gtnWsRecordBean);
			getAllTreeNodes(treeData, treeNodeList, treeData.getChildren(gtnWsRecordBean));
		}
		return treeNodeList;
	}

	public boolean validateEmptyTree(TreeData<GtnWsRecordBean> treeData) throws GtnFrameworkGeneralException {
		if (treeData.getRootItems() == null || treeData.getRootItems().isEmpty()) {
			GtnUIFrameworkGlobalUI.showMessageBox("Error", GtnUIFrameworkActionType.ALERT_ACTION, "Error",
					"Please make a tree to save");
			return true;
		}
		return false;
	}

	private void saveCustomView(String componentId, String customViewName, GtnWsCustomViewRequest cvRequest,String tabName)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
                confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Save record " + customViewName + " ?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
		saveActionConfig.addActionParameter(cvRequest);
		saveActionConfig.addActionParameter(tabName);
		successActionConfigList.add(saveActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
                GtnUIFrameworkActionExecutor.executeSingleAction(componentId,confirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private GtnWsCustomTreeData buildCustomTreeData(TreeData<GtnWsRecordBean> treeData) {
		GtnWsCustomTreeData apexBean = new GtnWsCustomTreeData();
		List<GtnWsRecordBean> rootItems = treeData.getRootItems();
		addChildBeans(rootItems, treeData, apexBean);
		return apexBean;
	}

	private void addChildBeans(List<GtnWsRecordBean> childItems, TreeData<GtnWsRecordBean> treeData,
			GtnWsCustomTreeData parentBean) {
		List<GtnWsReportVariablesType> variableList = new ArrayList<>();
		for (GtnWsRecordBean bean : childItems) {
			if (bean.getStringPropertyByIndex(2).equals(GtnWsHierarchyType.VARIABLES.toString())
					&& !bean.getStringPropertyByIndex(0).equals(GtnWsReportVariablesType.VARIABLES.toString())) {
				variableList.add(GtnWsReportVariablesType.fromString(bean.getStringPropertyByIndex(0)));
			} else {
				GtnWsCustomTreeData tempBean = new GtnWsCustomTreeData();
				tempBean.setLevelName(bean.getStringPropertyByIndex(0));
				tempBean.setLevelNo(bean.getIntegerPropertyByIndex(1));
				tempBean.setHierarchyType(GtnWsHierarchyType.fromString(bean.getStringPropertyByIndex(2)));
				tempBean.setCurrentTreeLevelNo(parentBean.getCurrentTreeLevelNo() + 1);
				tempBean.setHierarchySid(bean.getIntegerPropertyByIndex(3));
				parentBean.setChild(tempBean);
				addChildBeans(treeData.getChildren(bean), treeData, tempBean);
			}

		}
		parentBean.setVariableList(variableList);
	}
}
