/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkReportCustomViewEditAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private String screenName;

	public GtnFrameworkReportCustomViewEditAction() {
		super();
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Do nothing
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String selectedItem;
		this.screenName = gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString();
		if (screenName.contains(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_DS)) {
			selectedItem = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent("dataSelectionTab_displaySelectionTabCustomView", componentId)
					.getCaptionFromV8ComboBox();
		} else if (screenName.contains(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_RD)) {
			selectedItem = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
					"reportingDashboardTab_displaySelectionTabCustomView", componentId).getCaptionFromV8ComboBox();
		} else {
			selectedItem = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW)
					.getV8StringFromField();
		}

		if (!"".equals(selectedItem) && !"0".equals(selectedItem)) {
			GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Edit");
			GtnUIFrameworkGlobalUI.addSessionProperty("customSid", selectedItem);
			loadScreen(selectedItem, gtnUIFrameWorkActionConfig);
			GtnUIFrameworkBaseComponent deleteButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(screenName + "customViewDelete");
			deleteButton.setEnable(true);
		} else {
			GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Add");
			GtnUIFrameworkBaseComponent deleteButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(screenName + "customViewDelete");
			deleteButton.setEnable(false);
		}
	}

	private void loadScreen(String selectedItem, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		loadTreeGrid(selectedItem, gtnUIFrameWorkActionConfig);

	}

	private void loadViewName(String customViewName) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(screenName + "_hierarchyName").setHasValue(customViewName);
	}

	private void loadVariableType(String viewType) {
		String[] list = viewType.split("~");
		if (list.length == 3) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(screenName + "_custom_Variable_Type_OptionGroup")
					.setHasValue(list[1]);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(screenName + "_custom_Variable_OptionGroup")
					.setHasValue(list[2]);
		}
	}

	private void loadTreeGrid(String selectedItem, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		loadFields(selectedItem, gtnUIFrameWorkActionConfig);

	}

	private void removeFromLeftTable(GtnWsRecordBean currentTempData, char hierarchyType) {
		switch (hierarchyType) {
		case 'C':
			removeLeftCustomer(currentTempData);
			break;
		case 'P':
			removeLeftProduct(currentTempData);
			break;
		case 'D':
			removeLeftDeduction(currentTempData);
			break;
		case 'V':
			removeLeftVariables(currentTempData);
			break;
		}

	}

	private void removeLeftVariables(GtnWsRecordBean currentTempData) {
		removeFromGrid(screenName + "customViewLookupVariableTable", currentTempData);

	}

	@SuppressWarnings("unchecked")
	private void removeFromGrid(String string, GtnWsRecordBean currentTempData) {
		Grid<GtnWsRecordBean> gridComponent = (Grid<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(string).getComponent();

		Optional<GtnWsRecordBean> itemList = ((ListDataProvider<GtnWsRecordBean>) gridComponent.getDataProvider())
				.getItems().stream().filter(recordBean -> recordBean.getStringPropertyByIndex(0)
						.equals(currentTempData.getStringPropertyByIndex(0)))
				.findFirst();
		itemList.ifPresent(leftData -> {
			GtnUIFrameworkBaseComponent grid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string);
			currentTempData.setPropertyValueByIndex(1, leftData.getPropertyValueByIndex(1));
			currentTempData.setPropertyValueByIndex(2, leftData.getPropertyValueByIndex(2));
			grid.removeItemsFromGrid(leftData);
		});
	}

	private void removeLeftDeduction(GtnWsRecordBean currentTempData) {
		removeFromGrid(screenName + "customViewLookupDeductionTable", currentTempData);

	}

	private void removeLeftProduct(GtnWsRecordBean currentTempData) {
		removeFromGrid(screenName + "customViewLookupProductTable", currentTempData);

	}

	private void removeLeftCustomer(GtnWsRecordBean currentTempData) {
		removeFromGrid(screenName + "customViewLookupCustomerTable", currentTempData);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadFields(String customSid, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest generalRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();

		cvRequest.setCvSysId(Integer.parseInt(customSid));
		cvRequest.setCustomViewType("reportStatic");
		generalRequest.setGtnWsCustomViewRequest(cvRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CUSTOM_VIEW_GET_TREE_DATA,
				generalRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
		@SuppressWarnings("unchecked")
		TreeGrid<GtnWsRecordBean> treeGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1))
				.getTreeGrid();

		TreeData<GtnWsRecordBean> treeData = new TreeData<>();
		treeGrid.setTreeData(treeData);
		if (cvResponse != null) {
			customizeData(cvResponse, treeGrid);
			loadViewName(cvResponse.getCustomViewName());
			loadVariableType(cvResponse.getCustomViewType());
		}

	}

	public TreeData<GtnWsRecordBean> customizeData(GtnWsCustomViewResponse cvResponse,
			TreeGrid<GtnWsRecordBean> treeGrid) {
		TreeData<GtnWsRecordBean> treeData = treeGrid.getTreeData();
		Optional.ofNullable(cvResponse.getCvTreeNodeList()).ifPresent(beans -> {

			GtnWsRecordBean parent = null;
			for (GtnWsRecordBean bean : beans) {
				char indicator = bean.getStringPropertyByIndex(3).toUpperCase().charAt(0);
				treeData.addItem(parent, bean);
				expandTree(treeGrid, parent);
				if (indicator != 'V' || bean.getStringPropertyByIndex(0).equalsIgnoreCase("Variables")) {
					parent = bean;
				}
				removeFromLeftTable(bean, indicator);

			}
		});
		return treeData;

	}

	private void expandTree(TreeGrid<GtnWsRecordBean> treeGrid, GtnWsRecordBean parent) {
		if (parent != null) {
			treeGrid.expand(parent);
		}

	}

}
