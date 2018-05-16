package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkSaveViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Date date = null;
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		String company = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(1).toString())
				.getCaptionFromV8ComboBox();
		dataSelectionBean.setCompanyReport(Integer.parseInt(company));
		Integer businessUnit = (Integer) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString()).getValueFromComponent();
		dataSelectionBean.setBusinessUnitReport(businessUnit);
		String reportDataSource = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(3).toString())
				.getCaptionFromV8ComboBox();
		dataSelectionBean.setReportDataSource(reportDataSource);
		String fromPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(4).toString())
				.getCaptionFromV8ComboBox();
		dataSelectionBean.setFromPeriodReport(Integer.parseInt(fromPeriod));
		String toPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(5).toString())
				.getCaptionFromV8ComboBox();
		dataSelectionBean.setToPeriod(Integer.parseInt(toPeriod));
		GtnWsRecordBean customerHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(6).toString()).getComponentData().getCustomData();
		dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyBean);
		int customerRelationshipBuilderSid = (int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(7).toString()).getValueFromComponent();
		dataSelectionBean.setCustomerRelationshipBuilderSid(customerRelationshipBuilderSid);
		int customerRelationshipVersion = (int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(9).toString()).getValueFromComponent();
		dataSelectionBean.setCustomerRelationshipVersionNo(customerRelationshipVersion);
		int customerLevel = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(8).toString())
				.getValueFromComponent();
		dataSelectionBean.setCustomerHierarchyForecastLevel(customerLevel);
		LocalDate forecastEligibleDate = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(10).toString()).getFieldValue();
		if (forecastEligibleDate != null) {
			date = Date.from(forecastEligibleDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		dataSelectionBean.setForecastEligibleDate(date);

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParamsList.get(11).toString(), componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		ListDataProvider<GtnWsRecordBean> availableCustomerList = (ListDataProvider<GtnWsRecordBean>) leftTable
				.getDataProvider();
		List<GtnWsRecordBean> items = (List<GtnWsRecordBean>) availableCustomerList.getItems();
		dataSelectionBean.setAvailableCustomerHierarchyList(items);

		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		rightTable.expand(rightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedCustomerValues = rightTable.getTreeData().getRootItems();
		List<GtnWsRecordBean> selectedList = new ArrayList<>();
		for (GtnWsRecordBean gtnWsRecordBean : selectedCustomerValues) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
		dataSelectionBean.setSelectedCustomerHierarchyList(selectedList);
		GtnWsRecordBean productHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(12).toString()).getComponentData().getCustomData();
		dataSelectionBean.setProductHierarchyRecordBean(productHierarchyBean);
		int productRelationshipBuilderSid = (int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(13).toString()).getValueFromComponent();
		dataSelectionBean.setProductRelationshipBuilderSid(productRelationshipBuilderSid);
		int productRelationshipVersion = (int) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(15).toString()).getValueFromComponent();
		dataSelectionBean.setProductRelationshipVersionNo(productRelationshipVersion);
		int productLevel = (int) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(14).toString())
				.getValueFromComponent();
		dataSelectionBean.setProductHierarchyForecastLevel(productLevel);

		GtnUIFrameworkComponentData gtnUIFrameworkProductComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParamsList.get(16).toString(), componentId);
		GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkProductComponentData
				.getCustomData();
		Grid<GtnWsRecordBean> productLeftTable = productDualListBoxBean.getLeftTable();
		ListDataProvider<GtnWsRecordBean> availableProductList = (ListDataProvider<GtnWsRecordBean>) productLeftTable
				.getDataProvider();
		List<GtnWsRecordBean> productItems = (List<GtnWsRecordBean>) availableProductList.getItems();
		dataSelectionBean.setAvailableProductHierarchyList(productItems);

		TreeGrid<GtnWsRecordBean> productRightTable = productDualListBoxBean.getRightTable();
		productRightTable.expand(productRightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedProductValues = productRightTable.getTreeData().getRootItems();
		List<GtnWsRecordBean> selectedProductList = new ArrayList<>();
		for (GtnWsRecordBean gtnWsRecordBean : selectedProductValues) {
			selectedProductList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedProductList);
		}
		dataSelectionBean.setSelectedProductHierarchyList(selectedProductList);

		GtnUIFrameWorkActionConfig popupAction = new GtnUIFrameWorkActionConfig();
		popupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> params = new ArrayList<>();
		params.add("dsSaveViewLookUp");
		params.add("Save view");
		params.add(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		params.add(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		params.add(null);
		params.add(dataSelectionBean);
		popupAction.setActionParameterList(params);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, popupAction);

	}

	private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedVales,
			List<GtnWsRecordBean> selectedList) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedVales)) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
