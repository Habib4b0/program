package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewDataBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.TreeData;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomTreeSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String textField = (String) parameterList.get(1);
		String customTreeGridId = (String) parameterList.get(2);
		TreeGrid<GtnWsRecordBean> treeComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(customTreeGridId, componentId).getTreeGrid();
		TreeData<GtnWsRecordBean> treeData = treeComponent.getTreeData();
		GtnWsCustomTreeData apexBean = buildCustomTreeData(treeData);

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
				.withCustomViewBean().build();
		GtnWsReportCustomViewDataBean customViewDataBean = new GtnWsReportCustomViewDataBean();
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().setCustomViewDataBean(customViewDataBean);
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().getCustomViewDataBean()
				.setCustomTreeData(apexBean);
		String customViewName = (String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textField, componentId)
				.getFieldValue();
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().getCustomViewDataBean()
				.setCustomViewName(customViewName);
		validateTreeSave(customViewDataBean, customViewName, componentId);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportEndPointUrlConstants.SAVE_CUSTOM_TREE, GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnFrameworkUIBuildCustomTreeAction.class.getName());
		actionConfig.addActionParameter(customViewName);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);

	}

	private void validateTreeSave(GtnWsReportCustomViewDataBean customViewDataBean, String customViewName,
			String componentId) throws GtnFrameworkGeneralException {
		validateEmptyTree(customViewDataBean, componentId);
		validateEmptyViewName(customViewName, componentId);
		validateIsNameTaken(customViewName, componentId);
	}

	private void validateIsNameTaken(String customViewName, String componentId) throws GtnFrameworkGeneralException {
		List<String> savedCustomViewList = new GtnUIFrameworkWebServiceClient()
				.callGtnWebServiceUrl(GtnWsReportEndPointUrlConstants.LOAD_CUSTOM_VIEW,
						GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, new GtnUIFrameworkWebserviceRequest(),
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
				.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList();
		Optional<String> sameCustomView = savedCustomViewList.stream()
				.filter(viewName -> viewName.equals(customViewName)).findFirst();
		if (sameCustomView.isPresent()) {
			GtnUIFrameWorkActionConfig noLevelChoosedAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			noLevelChoosedAction
					.addActionParameter("That view name is taken. Please enter a new Custom Tree View Name");
			noLevelChoosedAction.addActionParameter("Name Already Taken");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, noLevelChoosedAction);
			throw new GtnFrameworkSkipActionException("Name Already available");
		}
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

	private void validateEmptyTree(GtnWsReportCustomViewDataBean customViewDataBean, String componentId)
			throws GtnFrameworkGeneralException {
		if (customViewDataBean.getCustomTreeData().getChild() == null) {
			GtnUIFrameWorkActionConfig noLevelChoosedAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			noLevelChoosedAction.addActionParameter("Please add at least one level to the Tree Structure list view");
			noLevelChoosedAction.addActionParameter("Missing Level");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, noLevelChoosedAction);
			throw new GtnFrameworkSkipActionException("Can't Save . No level available");
		}
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
			if (bean.getStringPropertyByIndex(2).equals(GtnWsHierarchyType.VARIABLES.toString())) {
				variableList.add(GtnWsReportVariablesType.fromString(bean.getStringPropertyByIndex(0)));
			} else {
				GtnWsCustomTreeData tempBean = new GtnWsCustomTreeData();
				tempBean.setLevelName(bean.getStringPropertyByIndex(0));
				tempBean.setLevelNo(bean.getIntegerPropertyByIndex(1));
				tempBean.setHierarchyType(GtnWsHierarchyType.fromString(bean.getStringPropertyByIndex(2)));
				tempBean.setCurrentTreeLevelNo(parentBean.getCurrentTreeLevelNo() + 1);
				parentBean.setChild(tempBean);
				addChildBeans(treeData.getChildren(bean), treeData, tempBean);
			}

		}
		parentBean.setVariableList(variableList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
