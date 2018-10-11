package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkSaveViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkSaveViewAction.class);

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
		String company = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setCompanyReport(checkForStringZero(company));
		String businessUnit = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString()).getCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setBusinessUnitReport(checkForStringZero(businessUnit));
		String reportDataSource = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(3).toString()).getCaptionFromV8ComboBox())
				.orElseGet(String::new);
		dataSelectionBean.setReportDataSource(checkForStringZero(reportDataSource));
		dataSelectionBean.setFromPeriodReport(checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(4).toString()).getCaptionFromV8ComboBox())));
		dataSelectionBean.setToPeriod(checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(5).toString()).getCaptionFromV8ComboBox())));
		GtnWsRecordBean customerHierarchyBean = null;
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(6).toString()).getComponentData()
				.getCustomData() != null)
			customerHierarchyBean = ((GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(6).toString()).getComponentData().getCustomData());
		dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyBean);
		dataSelectionBean.setCustomerRelationshipBuilderSid((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(7).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setCustomerRelationshipVersionNo((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(9).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setCustomerHierarchyForecastLevel((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(8).toString()).getCaptionFromV8ComboBox()))));
		LocalDate forecastEligibleDate = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(10).toString()).getFieldValue();
		if (forecastEligibleDate != null) {
			date = Date.from(forecastEligibleDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		dataSelectionBean.setForecastEligibleDate(date);

		List<GtnWsRecordBean> selectedCustomerList = getSelectedList(actionParamsList.get(11).toString(), componentId);
		List<GtnWsRecordBean> selectedProductList = getSelectedList(actionParamsList.get(16).toString(), componentId);

		dataSelectionBean.setSelectedCustomerHierarchyList(selectedCustomerList);

		GtnWsRecordBean productHierarchyBean = null;
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(12).toString()).getComponentData()
				.getCustomData() != null)
			productHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(12).toString()).getComponentData().getCustomData();
		dataSelectionBean.setProductHierarchyRecordBean(productHierarchyBean);
		dataSelectionBean.setProductRelationshipBuilderSid((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(13).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setProductRelationshipVersionNo((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(15).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setProductHierarchyForecastLevel((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(14).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setSelectedProductHierarchyList(selectedProductList);

		Optional<List<Object>> selectedVariableListOptionalList = Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(18).toString()).getSelectedListFromV8MultiSelect());
		List<Object> selectedVariableList = selectedVariableListOptionalList.isPresent()
				? selectedVariableListOptionalList.get()
				: null;
		List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = new ArrayList<>();
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(19).toString()).getComponentData()
				.getCustomData() != null) {
			comparisonProjectionBeanList = (List<GtnReportComparisonProjectionBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(19).toString()).getComponentData().getCustomData();
		}
		dataSelectionBean.setCustomView(checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(17).toString()).getCaptionFromV8ComboBox())));
		dataSelectionBean.setFrequency((checkIfNotNull(Optional.ofNullable(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(20).toString()).getCaptionFromV8ComboBox()))));
		dataSelectionBean.setComparisonProjectionBeanList(comparisonProjectionBeanList);
		dataSelectionBean.setVariablesList(selectedVariableList);
		dataSelectionBean.setPrivateViewName(getViewName("reportLandingScreen_privateViews"));
		dataSelectionBean.setPublicViewName(getViewName("reportLandingScreen_publicViews"));

		String viewId = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).getViewId();
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(
						"variableBreakdownResultsLayout_comparisonLookupResultsPagedTableComponent", viewId)
				.getComponent();
		if (abstractComponent != null && abstractComponent.getData() != null) {
			GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
			if (gridComponent.getCustomData() instanceof List) {
				List<GtnReportVariableBreakdownLookupBean> gtnReportVariableBreakdownLookupBeanList = (List<GtnReportVariableBreakdownLookupBean>) gridComponent
						.getCustomData();
				dataSelectionBean.setVariableBreakdownSaveList(gtnReportVariableBreakdownLookupBeanList);
			}
		}

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
		gtnLogger.info("privateViewName--------->" + dataSelectionBean.getPrivateViewName());
		gtnLogger.info("publicViewName----------->" + dataSelectionBean.getPublicViewName());

		if (privateAndPublicViewValidation(dataSelectionBean)) {
			String viewName = !"".equals(dataSelectionBean.getPrivateViewName())
					? dataSelectionBean.getPrivateViewName()
					: "";
			viewName = !"".equals(viewName) ? viewName : dataSelectionBean.getPublicViewName();
			dataSelectionBean.setViewId(getViewId(dataSelectionBean.getPrivateViewName()));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportSaveViewLookUp_saveViewName", componentId)
					.loadV8FieldValue(viewName);
			GtnUIFrameWorkActionConfig updateEnableAction = new GtnUIFrameWorkActionConfig();
			updateEnableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
			updateEnableAction.addActionParameter("reportSaveViewLookUp_saveViewUpdate");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, updateEnableAction);

			GtnUIFrameWorkActionConfig updateDisableAction = new GtnUIFrameWorkActionConfig();
			updateDisableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
			updateDisableAction.addActionParameter("reportSaveViewLookUp_saveViewAdd");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, updateDisableAction);
		}
	}

	private Integer checkIfNotNull(Optional input) {
		return input.isPresent() && !"".equals(input.get().toString()) ? Integer.valueOf(input.get().toString()) : null;
	}

	private Integer checkForStringZero(String value) {
		return value == "0" ? 0 : Integer.parseInt(value);
	}

	private String getViewName(String viewComponentId) {
		String viewName = Optional
				.ofNullable(String
						.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewComponentId).getV8PopupFieldValue()))
				.orElseGet(String::new);

		return StringUtils.isBlank(viewName) ? null : viewName;
	}

	private int getViewId(String privateViewName) {
		GtnWsRecordBean viewRecord;
		if (!"".equals(privateViewName))
			viewRecord = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportLandingScreen_privateViews").getComponentData().getCustomData();
		else
			viewRecord = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportLandingScreen_publicViews").getComponentData().getCustomData();
		return Integer
				.parseInt(String.valueOf(viewRecord.getPropertyValueByIndex(viewRecord.getProperties().size() - 2)));
	}

	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId) {
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		if (gtnUIFrameworkComponentData.getCustomData() == null)
			return Collections.emptyList();
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

	private boolean privateAndPublicViewValidation(GtnWsReportDataSelectionBean dataSelectionBean) {
		return ((!"".equals(dataSelectionBean.getPrivateViewName()) && dataSelectionBean.getPrivateViewName() != null)
				|| (!"".equals(dataSelectionBean.getPublicViewName())
						&& dataSelectionBean.getPublicViewName() != null));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
