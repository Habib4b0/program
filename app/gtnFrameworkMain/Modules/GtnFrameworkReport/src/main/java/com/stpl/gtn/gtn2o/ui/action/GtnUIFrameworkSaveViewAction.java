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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
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
		dataSelectionBean.setCompanyReport(Integer.valueOf(company));
		int businessUnit = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setBusinessUnitReport(businessUnit);
		int reportDataSource = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(3).toString())
				.getIntegerFromV8ComboBox();
		dataSelectionBean.setReportDataSource(reportDataSource);
		String fromPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(4).toString())
				.getCaptionFromV8ComboBox();
		dataSelectionBean.setFromPeriodReport(fromPeriod == "" ? 0 : Integer.valueOf(fromPeriod));
		String toPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(5).toString())
				.getCaptionFromV8ComboBox();
		dataSelectionBean.setToPeriod(toPeriod == "" ? 0 : Integer.valueOf(toPeriod));
		GtnWsRecordBean customerHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(6).toString()).getComponentData().getCustomData();
		dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyBean);
		int customerRelationshipBuilderSid = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(7).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setCustomerRelationshipBuilderSid(customerRelationshipBuilderSid);
		int customerRelationshipVersion = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(9).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setCustomerRelationshipVersionNo(customerRelationshipVersion);
		int customerLevel = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(8).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setCustomerHierarchyForecastLevel(customerLevel);
		LocalDate forecastEligibleDate = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(10).toString()).getFieldValue();
		if (forecastEligibleDate != null) {
			date = Date.from(forecastEligibleDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		dataSelectionBean.setForecastEligibleDate(date);

		List<GtnWsRecordBean> selectedCustomerList = getSelectedList(actionParamsList.get(11).toString(), componentId);
		List<GtnWsRecordBean> selectedProductList = getSelectedList(actionParamsList.get(16).toString(), componentId);

		dataSelectionBean.setSelectedCustomerHierarchyList(selectedCustomerList);
		GtnWsRecordBean productHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(12).toString()).getComponentData().getCustomData();
		dataSelectionBean.setProductHierarchyRecordBean(productHierarchyBean);
		int productRelationshipBuilderSid = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(13).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setProductRelationshipBuilderSid(productRelationshipBuilderSid);
		int productRelationshipVersion = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(15).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setProductRelationshipVersionNo(productRelationshipVersion);
		int productLevel = Integer.parseInt(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(14).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setProductHierarchyForecastLevel(productLevel);

		dataSelectionBean.setSelectedProductHierarchyList(selectedProductList);

		int customViewName = Integer.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(17).toString()).getCaptionFromV8ComboBox());
		List<Object> selectedVariableList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(18).toString()).getSelectedListFromV8MultiSelect();
		List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = new ArrayList<>();
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(19).toString()).getComponentData()
				.getCustomData() != null) {
			comparisonProjectionBeanList = (List<GtnReportComparisonProjectionBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(19).toString()).getComponentData().getCustomData();
		}
		int frequency = Integer.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(20).toString()).getCaptionFromV8ComboBox());
		dataSelectionBean.setCustomView(customViewName);
		dataSelectionBean.setFrequency(frequency);
		dataSelectionBean.setComparisonProjectionBeanList(comparisonProjectionBeanList);
		dataSelectionBean.setVariablesList(selectedVariableList);

		GtnUIFrameWorkActionConfig popupAction = new GtnUIFrameWorkActionConfig();
		popupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> params = new ArrayList<>();
		params.add("dsSaveViewLookUp");
		params.add("Save view");
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(dataSelectionBean);
		popupAction.setActionParameterList(params);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, popupAction);

	}

	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId) {
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		rightTable.expand(rightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedvalues = rightTable.getTreeData().getRootItems();

		List<GtnWsRecordBean> selectedList = new ArrayList<>(10);
		for (GtnWsRecordBean gtnWsRecordBean : selectedvalues) {

			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
		return selectedList;
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
