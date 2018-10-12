package com.stpl.gtn.gtn2o.ui.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportProfileDataToPojoAction;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.ComboBox;

public class GtnReportingDashboardReportProfileLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportingDashboardReportProfileLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("Inside GtnReportingDashboardReportProfileLoadAction");

		try {

			GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean;

			List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String viewId = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId).getParentViewId();

			GtnUIFrameworkBaseComponent reportBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(1).toString(), viewId);

			GtnWsRecordBean recordBean = (GtnWsRecordBean) reportBaseComponent.getComponentData().getCustomData();
			String viewData = (String) recordBean.getPropertyValueByIndex(5);
			GtnReportingDashboardSaveProfileLookupBean currentViewData = new GtnReportingDashboardSaveProfileLookupBean();

			currentViewData = new GtnReportProfileDataToPojoAction()
					.saveDataSelectionDataToReportProfilePojo(currentViewData, viewId);

			reportProfileSaveLookupBean = convertJsonToObject(GtnReportingDashboardSaveProfileLookupBean.class,
					viewData);
			if (validateReportProfile(currentViewData, reportProfileSaveLookupBean, viewId)) {
				loadDataToComponent(reportProfileSaveLookupBean, actionParamList, viewId);
			}

		} catch (Exception ex) {
			gtnLogger.error("Error message", ex);
		}
	}

	private void loadDataToComponent(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean,
			List<Object> actionParamList, String viewId) throws GtnFrameworkValidationFailedException {
		try {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(2).toString(), viewId)
					.loadV8MultiSelectValue();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(2).toString(), viewId)
					.updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabVariable())
							.isPresent() ? reportProfileSaveLookupBean.getDisplaySelectionTabVariable()
									: new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString(), viewId)
					.loadV8ComboBoxComponentValue(Optional
							.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabFrequency()).isPresent()
									? Integer.parseInt(reportProfileSaveLookupBean.getDisplaySelectionTabFrequency())
									: 0);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(7).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabCustomViewCombobox())
									.isPresent()
											? reportProfileSaveLookupBean.getDisplaySelectionTabCustomViewCombobox()
											: "0");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(8).toString(), viewId)
					.loadV8MultiSelectValue();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(8).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory())
							.isPresent() ? reportProfileSaveLookupBean.getDisplaySelectionTabVariableCategory()
									: new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(9).toString(), viewId)
					.loadV8ComboBoxComponentValue(Optional
							.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabAnnualTotals()).isPresent()
									? reportProfileSaveLookupBean.getDisplaySelectionTabAnnualTotals() : "Yes");

			setPeriodRangeFromAndTo(reportProfileSaveLookupBean, actionParamList, viewId);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(10).toString(), viewId)
					.loadV8ComboBoxComponentValue(Optional
							.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabCustomerLevel()).isPresent()
									? reportProfileSaveLookupBean.getFilterOptionsTabCustomerLevel() : "0");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(11).toString(), viewId)
					.loadV8MultiSelectValue();

			GtnUIFrameworkActionExecutor.executeSingleAction(viewId, getreportDashboardReportProfileFilterReloadAction(
					actionParamList.get(11).toString(), "C", GtnFrameworkReportStringConstants.REPORT_DASHBOARD_TAB));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(11).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabCustomerFilter()).isPresent()
							? reportProfileSaveLookupBean.getFilterOptionsTabCustomerFilter() : new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(12).toString(), viewId)
					.loadV8ComboBoxComponentValue(
							Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabProductLevel())
									.isPresent() ? reportProfileSaveLookupBean.getFilterOptionsTabProductLevel() : "0");

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(13).toString(), viewId)
					.loadV8MultiSelectValue();

			GtnUIFrameworkActionExecutor.executeSingleAction(viewId, getreportDashboardReportProfileFilterReloadAction(
					actionParamList.get(13).toString(), "P", GtnFrameworkReportStringConstants.REPORT_DASHBOARD_TAB));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(13).toString(), viewId)
					.updateSelection(Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabProductFilter())
							.isPresent() ? reportProfileSaveLookupBean.getFilterOptionsTabProductFilter()
									: new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(14).toString(), viewId)
					.loadV8ComboBoxComponentValue(Optional
							.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionLevel()).isPresent()
									? reportProfileSaveLookupBean.getFilterOptionsTabDeductionLevel() : "0");

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(15).toString(), viewId)
					.loadV8MultiSelectValue();

			GtnUIFrameworkActionExecutor.executeSingleAction(viewId, getreportDashboardReportProfileFilterReloadAction(
					actionParamList.get(15).toString(), "D", GtnFrameworkReportStringConstants.REPORT_DASHBOARD_TAB));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(15).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionFilter()).isPresent()
							? reportProfileSaveLookupBean.getFilterOptionsTabDeductionFilter() : new ArrayList<>());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(16).toString(), viewId)
					.loadV8MultiSelectValue();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(16).toString(), viewId).updateSelection(
					Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabSalesInclusion()).isPresent()
							? reportProfileSaveLookupBean.getFilterOptionsTabSalesInclusion() : new ArrayList<>());

			loadDataToComponentExtended(reportProfileSaveLookupBean, actionParamList, viewId);
		} catch (Exception ex) {
			gtnLogger.info("Exception in Loading the Filter Values" + ex);
		}
	}

	private void loadDataToComponentExtended(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean,
			List<Object> actionParamList, String viewId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(17).toString(), viewId)
				.loadV8MultiSelectValue();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(17).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getFilterOptionsTabDeductionInclusion()).isPresent()
						? reportProfileSaveLookupBean.getFilterOptionsTabDeductionInclusion() : new ArrayList<>());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(18).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsVariableAndVarianceSequencing())
								.isPresent()
										? Integer.parseInt(
												reportProfileSaveLookupBean
														.getReportOptionsVariableAndVarianceSequencing())
										: 0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(19).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsHeaderSequencing()).isPresent()
								? Integer.parseInt(reportProfileSaveLookupBean.getReportOptionsHeaderSequencing()) : 0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(20).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsViewOptions()).isPresent()
								? Integer.parseInt(reportProfileSaveLookupBean.getReportOptionsViewOptions()) : 0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(21).toString(), viewId)
				.loadV8MultiSelectValue();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(21).toString(), viewId).updateSelection(
				Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsDisplayFormat()).isPresent()
						? reportProfileSaveLookupBean.getReportOptionsDisplayFormat() : new ArrayList<>());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(23).toString(), viewId)
				.loadV8ComboBoxComponentValue(
						Optional.ofNullable(reportProfileSaveLookupBean.getReportOptionsCurrencyDisplay()).isPresent()
								? Integer.parseInt(reportProfileSaveLookupBean.getReportOptionsCurrencyDisplay()) : 0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId).getComponentData()
				.setCustomData(
						Optional.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList())
								.isPresent() ? reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList()
										: new ArrayList<>());

		if (getDisplayValue(reportProfileSaveLookupBean) == null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId)
					.setV8PopupFieldValue(" ");
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(24).toString(), viewId)
					.setV8PopupFieldValue(getDisplayValue(reportProfileSaveLookupBean));
		}

		reloadComparisonBasisOnProfileLoad(reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList(),
				actionParamList, viewId);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(6).toString(), viewId)
				.loadV8ComboBoxComponentValue(Optional
						.ofNullable(reportProfileSaveLookupBean.getDisplaySelectionTabComparisonBasis()).isPresent()
								? Integer.parseInt(reportProfileSaveLookupBean.getDisplaySelectionTabComparisonBasis())
								: 0);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(25).toString(), viewId).getComponentData()
				.setCustomData(Optional.ofNullable(reportProfileSaveLookupBean.getVariableBreakdownLookupBeanSaveList())
						.isPresent() ? reportProfileSaveLookupBean.getVariableBreakdownLookupBeanSaveList()
								: new ArrayList<>());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(26).toString(), viewId).getComponentData()
				.setCustomData(
						Optional.ofNullable(reportProfileSaveLookupBean.getComparisonBreakdownLookupBeanSaveList())
								.isPresent() ? reportProfileSaveLookupBean.getComparisonBreakdownLookupBeanSaveList()
										: new ArrayList<>());
	}

	private GtnUIFrameWorkActionConfig getreportDashboardReportProfileFilterReloadAction(String componentId,
			String indicator, String nameSpace) {
		GtnUIFrameWorkActionConfig reportDashboardReportProfileReloadFilterAction = new GtnUIFrameWorkActionConfig();
		reportDashboardReportProfileReloadFilterAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportDashboardReportProfileReloadFilterAction.addActionParameter(GtnReportFilterReloadAction.class.getName());
		reportDashboardReportProfileReloadFilterAction.addActionParameter(componentId);
		reportDashboardReportProfileReloadFilterAction.addActionParameter(indicator);

		reportDashboardReportProfileReloadFilterAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_TAB_CUSTOMER_LEVEL);
		reportDashboardReportProfileReloadFilterAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_OPTIONS_TAB_PRODUCT_LEVEL);
		reportDashboardReportProfileReloadFilterAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_OPTIONS_TAB_DEDUCTION_LEVEL);
		reportDashboardReportProfileReloadFilterAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_OPTIONS_TAB_CUSTOMER_FILTER);
		reportDashboardReportProfileReloadFilterAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_OPTIONS_TAB_PRODUCT_FILTER);
		reportDashboardReportProfileReloadFilterAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_OPTIONS_TAB_DEDUCTION_FILTER);

		return reportDashboardReportProfileReloadFilterAction;
	}

	private boolean validateReportProfile(GtnReportingDashboardSaveProfileLookupBean currentViewData,
			GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean, String componentId)
			throws GtnFrameworkGeneralException {
		String message = "";
		if (!currentViewData.getCustomerHierarchy().equals(reportProfileSaveLookupBean.getCustomerHierarchy())
				|| !currentViewData.getProductHierarchy().equals(reportProfileSaveLookupBean.getProductHierarchy())) {
			message = "Different Hierarchy";
		} else if (currentViewData.getCustomerRelationSid() != reportProfileSaveLookupBean.getCustomerRelationSid()
				|| currentViewData.getProductRelationSid() != reportProfileSaveLookupBean.getProductRelationSid()) {
			message = "Different Relationship";
		} else if (currentViewData.getCustomerLevelNo() != reportProfileSaveLookupBean.getCustomerLevelNo()
				|| currentViewData.getProductLevelNo() != reportProfileSaveLookupBean.getProductLevelNo()) {
			message = "Different Level";
		}
		if (!message.isEmpty()) {
			String header = message + " was selected";
			String messageBody = "Due to the changes that were made to the Data Selection screen, the previously created Custom Views cannot be recreated.";
			GtnUIFrameWorkActionConfig config = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
			config.addActionParameter(header);
			config.addActionParameter(messageBody);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, config);

			GtnUIFrameworkBaseComponent popup = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportProfileConfig", componentId);
			popup.setV8PopupFieldValue("");
			popup.getComponentData().setCustomData(null);
		}
		return message.isEmpty();
	}

	private void setPeriodRangeFromAndTo(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean,
			List<Object> actionParamList, String viewId) throws GtnFrameworkValidationFailedException {

		if (reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeFrom() != null) {

			ComboBox combo = (ComboBox) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(3).toString(), viewId).getComponent();

			List itemId = (List) ((ListDataProvider) (combo.getDataProvider())).getItems();

			if (itemId.get(1) instanceof String) {

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(3).toString(), viewId)
						.loadV8ComboBoxComponentValue(
								reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeFrom());

			}
			if (itemId.get(1) instanceof Integer) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(3).toString(), viewId)
						.loadV8ComboBoxComponentValue(
								Integer.valueOf(reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeFrom()));

			}

		}
		if (reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeTo() != null) {

			ComboBox combo = (ComboBox) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(4).toString(), viewId).getComponent();

			List itemId = (List) ((ListDataProvider) (combo.getDataProvider())).getItems();

			if (itemId.get(1) instanceof String) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString(), viewId)
						.loadV8ComboBoxComponentValue(
								reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeTo());
			}
			if (itemId.get(1) instanceof Integer) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString(), viewId)
						.loadV8ComboBoxComponentValue(
								Integer.valueOf(reportProfileSaveLookupBean.getDisplaySelectionTabPeriodRangeTo()));

			}

		}
	}

	private GtnReportingDashboardSaveProfileLookupBean convertJsonToObject(
			Class<GtnReportingDashboardSaveProfileLookupBean> reportProfileSaveLookupBean, String viewData)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(viewData, reportProfileSaveLookupBean);
	}

	private Object getDisplayValue(GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean) {
		if (reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList() == null) {
			return null;
		}

		if (reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList().size() > 1) {
			return "MULTIPLE";
		} else {
			return reportProfileSaveLookupBean.getDisplaySelectionComparisonLookupBeanList().get(0).getProjectionName();
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void reloadComparisonBasisOnProfileLoad(
			List<GtnReportComparisonProjectionBean> displaySelectionComparisonLookupBeanList,
			List<Object> actionParamList, String componentId) {
		int totalListSize = 4 + displaySelectionComparisonLookupBeanList.size();
		List<String> inputForComparisonBasisList = new ArrayList<>(totalListSize);
		inputForComparisonBasisList.add("Actuals");
		inputForComparisonBasisList.add("Accruals");
		inputForComparisonBasisList.add("Current Projection");
		displaySelectionComparisonLookupBeanList.stream()
				.forEach(comparisonProjectionBeans -> inputForComparisonBasisList
						.add(comparisonProjectionBeans.getProjectionName()));
		List idList = IntStream.range(1, totalListSize).boxed().collect(Collectors.toList());
		GtnUIFrameworkComboBoxConfig comparisonBasisComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(6).toString(), componentId).getComponentConfig()
				.getGtnComboboxConfig();
		comparisonBasisComboboxConfig.setItemCaptionValues(inputForComparisonBasisList);
		comparisonBasisComboboxConfig.setItemValues(idList);

		GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
		combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION, actionParamList.get(6).toString(),
				componentId, Arrays.asList(""));
	}

}