package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewDataBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomViewEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		String id = sourceComponentId + "_" + "reportingDashboardTab_displaySelectionTabCustomView";
		GtnUIFrameworkBaseComponent baseComboBoxComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id);
		String selectedItem = baseComboBoxComponent.getV8StringFromField();
		if (!"".equals(selectedItem) && !"0".equals(selectedItem)) {
			loadScreen(selectedItem, gtnUIFrameWorkActionConfig);

		}
	}

	private void loadScreen(String selectedItem, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
				.withCustomViewBean().build();
		GtnWsReportCustomViewDataBean dataBean = new GtnWsReportCustomViewDataBean();
		dataBean.setCustomViewName(selectedItem);
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().setCustomViewDataBean(dataBean);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportEndPointUrlConstants.LOAD_CUSTOM_VIEW_DATA,
				GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsReportCustomViewDataBean viewDataBean = response.getGtnReportResponse().getReportBean().getCustomViewBean()
				.getCustomViewDataBean();
		loadTreeGrid(viewDataBean, gtnUIFrameWorkActionConfig);
		loadViewName(viewDataBean.getCustomViewName());

	}

	private void loadViewName(String customViewName) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookup_hierarchyName")
				.setHasValue(customViewName);
	}

	private void loadTreeGrid(GtnWsReportCustomViewDataBean viewDataBean,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		TreeGrid<GtnWsRecordBean> treeGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1))
				.getTreeGrid();
		TreeData<GtnWsRecordBean> treeData = new TreeData<>();
		GtnWsRecordBean parent = null;
		GtnWsCustomTreeData data = viewDataBean.getCustomTreeData().getChild();
		String[] headerIdArray = ((GtnUIFrameworkComponentData) treeGrid.getData()).getCurrentComponentConfig()
				.getGtnUIFrameWorkGridConfig().getColumnHeadersId();
		while (data != null) {
			GtnWsRecordBean currentTempData = new GtnWsRecordBean();
			currentTempData.addProperties(data.getLevelName());
			currentTempData.addProperties(data.getLevelNo());
			currentTempData.addProperties(data.getHierarchyType().toString());
			treeData.addItem(parent, currentTempData);
			currentTempData.setRecordHeader(Arrays.asList(headerIdArray));
			removeFromLeftTable(currentTempData, data.getHierarchyType());
			parent = currentTempData;
			if (data.getVariableList() != null && data.getVariableList().isEmpty()) {
				treeData.addItems(parent, getVariableList(data.getVariableList()));
			}
			data = data.getChild();
		}

		treeGrid.setTreeData(treeData);
	}

	private void removeFromLeftTable(GtnWsRecordBean currentTempData, GtnWsHierarchyType hierarchyType) {
		switch (hierarchyType) {
		case CUSTOMER:
			removeLeftCustomer(currentTempData);
			break;
		case PRODUCT:
			removeLeftProduct(currentTempData);
			break;
		case DEDUCTION:
			removeLeftDeduction(currentTempData);
			break;
		case VARIABLES:
			removeLeftVariables(currentTempData);
			break;
		default:
			break;
		}

	}

	private void removeLeftVariables(GtnWsRecordBean currentTempData) {
		removeFromGrid("reportCustomViewLookupcustomViewLookupVariableTable", currentTempData);

	}

	private void removeFromGrid(String string, GtnWsRecordBean currentTempData) {
		Grid<GtnWsRecordBean> gridComponent = (Grid<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(string).getComponent();

		Optional<GtnWsRecordBean> itemList = ((ListDataProvider<GtnWsRecordBean>) gridComponent.getDataProvider())
				.getItems().stream().filter(recordBean -> recordBean.getStringPropertyByIndex(0)
						.equals(currentTempData.getStringPropertyByIndex(0)))
				.findFirst();
		itemList.ifPresent(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string)::removeItemsFromGrid);
	}

	private void removeLeftDeduction(GtnWsRecordBean currentTempData) {
		removeFromGrid("reportCustomViewLookupcustomViewLookupDeductionTable", currentTempData);

	}

	private void removeLeftProduct(GtnWsRecordBean currentTempData) {
		removeFromGrid("reportCustomViewLookupcustomViewLookupProductTable", currentTempData);

	}

	private void removeLeftCustomer(GtnWsRecordBean currentTempData) {
		removeFromGrid("reportCustomViewLookupcustomViewLookupCustomerTable", currentTempData);

	}

	private List<GtnWsRecordBean> getVariableList(List<GtnWsReportVariablesType> variableList) {
		return variableList.stream().map(variable -> {
			GtnWsRecordBean currentTempData = new GtnWsRecordBean();
			currentTempData.addProperties(variable.toString());
			currentTempData.addProperties(1);
			currentTempData.addProperties(GtnWsHierarchyType.VARIABLES.toString());
			return currentTempData;
		}).collect(Collectors.toList());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
