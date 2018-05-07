package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
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
		request.getGtnReportRequest().getReportBean().getCustomViewBean().setCustomTreeData(apexBean);
		request.getGtnReportRequest().getReportBean().getCustomViewBean().setCustomViewName(
				(String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textField, componentId).getFieldValue());
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportEndPointUrlConstants.SAVE_CUSTOM_TREE, GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
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
			if (bean.getStringPropertyByIndex(3).equals(GtnWsHierarchyType.VARIABLES.toString())) {
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
