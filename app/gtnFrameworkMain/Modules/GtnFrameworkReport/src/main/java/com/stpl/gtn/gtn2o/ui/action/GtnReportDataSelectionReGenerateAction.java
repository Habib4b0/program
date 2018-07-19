package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionReGenerateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBeanPersist = new GtnWsReportDataSelectionBean();
		dataSelectionBeanPersist = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		List<GtnWsRecordBean> selectedCustomerList = getSelectedList("dataSelectionTab_customerDualListBox",
				componentId);
		boolean isCustomerChanged = areListsEqual(selectedCustomerList,
				dataSelectionBean.getSelectedCustomerHierarchyList());

		List<GtnWsRecordBean> selectedProductList = getSelectedList("dataSelectionTab_productdualListBoxComp",
				componentId);
		boolean isProductChanged = areListsEqual(selectedProductList,
				dataSelectionBean.getSelectedProductHierarchyList());

		List<GtnReportComparisonProjectionBean> comparisonProjectionsList = (List<GtnReportComparisonProjectionBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_comparisonLookup", componentId).getComponentData()
				.getCustomData();
		boolean isComparisonProjectionChanged = comparisonProjectionsList == null
				&& dataSelectionBean.getComparisonProjectionBeanList() == null ? false
						: areComparisonListsEqual(comparisonProjectionsList,
								dataSelectionBean.getComparisonProjectionBeanList());

		List<Object> variableList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabVariable", componentId)
				.getSelectedListFromV8MultiSelect();
		boolean isVariablesChanged = areVariablesListsEqual(variableList, dataSelectionBean.getVariablesList());

		String customViewName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabCustomView", componentId)
				.getCaptionFromV8ComboBox();
		boolean isCustomView = isUpdated(customViewName, String.valueOf(dataSelectionBean.getCustomViewMasterSid()));

		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_landingScreenVariableBreakdownFrequencyConfig", componentId)
				.getCaptionFromV8ComboBox();
		boolean isFrequencyChanged = isUpdated(frequency, String.valueOf(dataSelectionBean.getFrequency()));

		String company = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_company", componentId)
				.getCaptionFromV8ComboBox();
		boolean isCompanyChanged = isUpdated(company, String.valueOf(dataSelectionBean.getCompanyReport()));

		String businessUnit = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_businessUnit", componentId).getCaptionFromV8ComboBox();
		boolean isBusinessUnitChanged = isUpdated(businessUnit,
				String.valueOf(dataSelectionBean.getBusinessUnitReport()));

		String reportDataSource = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", componentId).getCaptionFromV8ComboBox();
		boolean isReportDataSourceChanged = isUpdated(reportDataSource,
				String.valueOf(dataSelectionBean.getReportDataSource()));

		String fromPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
				.getCaptionFromV8ComboBox();
		String fromPeriodValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId).getStringCaptionFromV8ComboBox();
		boolean isFromPeriodChanged = isUpdated(fromPeriod, String.valueOf(dataSelectionBean.getFromPeriodReport()));

		updateCustomer(isCustomerChanged, dataSelectionBean, selectedCustomerList, componentId);
		updateProduct(isProductChanged, dataSelectionBean, selectedProductList, componentId);
		updateComparisonProjection(isComparisonProjectionChanged, dataSelectionBean, comparisonProjectionsList,
				componentId);
		updateCustomView(isCustomView, dataSelectionBean, customViewName, componentId);
		updateFrequency(isFrequencyChanged, dataSelectionBean, frequency, componentId);
		updateVariables(isVariablesChanged, variableList, dataSelectionBean, componentId);
		updateCompany(isCompanyChanged, company, dataSelectionBean);
		updateBusinessUnit(isBusinessUnitChanged, businessUnit, dataSelectionBean);
		updateReportDataSource(isReportDataSourceChanged, reportDataSource, dataSelectionBean);
		updateFromPeriod(isFromPeriodChanged, fromPeriod, dataSelectionBean, componentId, fromPeriodValue);
		if (isCustomerChanged || isProductChanged || isComparisonProjectionChanged || isCustomView || isFrequencyChanged
				|| isReportDataSourceChanged || isFromPeriodChanged) {

			List<GtnUIFrameWorkActionConfig> onSuccessActionList = new ArrayList<>();
			List<GtnUIFrameWorkActionConfig> onFailureActionList = new ArrayList<>();
			GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CONFIRMATION_ACTION);

			alertAction.addActionParameter("Update confirmation");
			alertAction.addActionParameter(
					"Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?");
			alertAction.addActionParameter(onSuccessActionList);
			alertAction.addActionParameter(onFailureActionList);
			
			GtnUIFrameWorkActionConfig callRegenerateActionSuccessConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);

			callRegenerateActionSuccessConfig
					.addActionParameter(GtnFrameworkReportDataSelectionRegenerateConfirmationAction.class.getName());
			callRegenerateActionSuccessConfig.addActionParameter(dataSelectionBean);
                        callRegenerateActionSuccessConfig.addActionParameter("YES");

			onSuccessActionList.add(callRegenerateActionSuccessConfig);
			GtnUIFrameWorkActionConfig callRegenerateActionFailureConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);

			callRegenerateActionFailureConfig
					.addActionParameter(GtnFrameworkReportDataSelectionRegenerateConfirmationAction.class.getName());
			callRegenerateActionFailureConfig.addActionParameter(dataSelectionBeanPersist);
			callRegenerateActionFailureConfig.addActionParameter("NO");
                        
			onFailureActionList.add(callRegenerateActionFailureConfig);
			
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
			
		}
	}

	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId) {
		GtnUIFrameworkComponentData selectedTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		GtnFrameworkV8DualListBoxBean selectedDualListBoxBean = (GtnFrameworkV8DualListBoxBean) selectedTableComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> selectedRightTable = selectedDualListBoxBean.getRightTable();
		selectedRightTable.expand(selectedRightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedValues = selectedRightTable.getTreeData().getRootItems();

		List<GtnWsRecordBean> selectedRecordList = new ArrayList<>(10);
		for (GtnWsRecordBean gtnWsRecordBean : selectedValues) {

			selectedRecordList.add(gtnWsRecordBean);
			addSelectedValues(selectedRightTable, gtnWsRecordBean, selectedRecordList);
		}
		return selectedRecordList;
	}

	private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedvalues,
			List<GtnWsRecordBean> selectedList) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedvalues)) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
	}

	private boolean areListsEqual(List<GtnWsRecordBean> newSelectedList, List<GtnWsRecordBean> oldSelectedList) {
		if (newSelectedList.size() == oldSelectedList.size()) {
			Set<GtnWsRecordBean> selectedCustomerSet = new HashSet<>(newSelectedList);
			Set<GtnWsRecordBean> customerSelectedSet = new HashSet<>(oldSelectedList);
			selectedCustomerSet.addAll(customerSelectedSet);
			if (selectedCustomerSet.size() > newSelectedList.size()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean areComparisonListsEqual(List<GtnReportComparisonProjectionBean> comparisonProjectionsList,
			List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		int comparisonProjectionListSize = comparisonProjectionBeanList != null ? comparisonProjectionBeanList.size()
				: 0;
		int dsComparisonProjectionListSize = comparisonProjectionsList != null ? comparisonProjectionsList.size() : 0;
		if (dsComparisonProjectionListSize == comparisonProjectionListSize) {
			Set<GtnReportComparisonProjectionBean> selectedCustomerSet = new HashSet<>(comparisonProjectionsList);
			Set<GtnReportComparisonProjectionBean> customerSelectedSet = new HashSet<>(comparisonProjectionBeanList);
			selectedCustomerSet.addAll(customerSelectedSet);
			if (selectedCustomerSet.size() > comparisonProjectionsList.size()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean areVariablesListsEqual(List<Object> variableList, List<Object> variablesList) {
		int variableListSize = variablesList != null ? variablesList.size() : 0;
		int dsvariableListSize = variableList != null ? variableList.size() : 0;
		if (dsvariableListSize == variableListSize) {
			Set<Object> selectedVariablesSet = new HashSet<>(variableList);
			Set<Object> dsSelectedVariables = new HashSet<>(variablesList);
			selectedVariablesSet.addAll(dsSelectedVariables);
			if (selectedVariablesSet.size() > variableList.size()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean isUpdated(String customViewName, String customViewMasterSid) {
		if (customViewName.equals(customViewMasterSid)) {
			return false;
		}
		return true;
	}

	private void updateCustomer(boolean isCustomerChanged, GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnWsRecordBean> selectedCustomerList, String componentId)
			throws GtnFrameworkValidationFailedException {
		Date forecastEligibleDate = null;
		if (isCustomerChanged) {
			GtnWsRecordBean customerHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId).getComponentData()
					.getCustomData();
			dataSelectionBean.setCustomerHierarchySid(Integer.valueOf(String.valueOf(
					customerHierarchyBean.getPropertyValueByIndex(customerHierarchyBean.getProperties().size() - 1))));
			dataSelectionBean.setCustomerRelationshipBuilderSid(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionRelationship", componentId)
					.getCaptionFromV8ComboBox())));
			dataSelectionBean.setCustomerHierarchyVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerRelationshipVersion", componentId)
					.getStringCaptionFromV8ComboBox()));
			dataSelectionBean.setCustomerRelationshipVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerRelationshipVersion", componentId)
					.getCaptionFromV8ComboBox()));
			dataSelectionBean.setCustomerHierarchyForecastLevel(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionLevel", componentId)
					.getCaptionFromV8ComboBox()));
			LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionForecastEligibilityDate", componentId)
					.getFieldValue();
			if (date != null) {
				forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
			dataSelectionBean.setForecastEligibleDate(forecastEligibleDate);
			dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyBean);
			dataSelectionBean.setSelectedCustomerHierarchyList(selectedCustomerList);
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboardTab_filterTabCustomerLevel", componentId, Arrays.asList(""));
		}
	}

	private void updateProduct(boolean isProductChanged, GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnWsRecordBean> selectedProductList, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (isProductChanged) {
			GtnWsRecordBean productHierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId).getComponentData()
					.getCustomData();
			dataSelectionBean.setProductHierarchySid(Integer.valueOf(String.valueOf(
					productHierarchyBean.getPropertyValueByIndex(productHierarchyBean.getProperties().size() - 1))));
			dataSelectionBean.setProductRelationshipBuilderSid(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_relationship", componentId).getCaptionFromV8ComboBox()));
			dataSelectionBean.setProductHierarchyVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_productRelationshipVersion", componentId)
					.getStringCaptionFromV8ComboBox()));
			dataSelectionBean.setProductRelationshipVersionNo(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_productRelationshipVersion", componentId)
					.getCaptionFromV8ComboBox()));
			dataSelectionBean.setProductHierarchyForecastLevel(Integer.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_level", componentId).getCaptionFromV8ComboBox()));
			dataSelectionBean.setProductHierarchyRecordBean(productHierarchyBean);
			dataSelectionBean.setSelectedProductHierarchyList(selectedProductList);
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboardTab_filterOptionsTabProductLevel", componentId, Arrays.asList(""));
		}
	}

	private void updateComparisonProjection(boolean isComparisonProjectionChanged,
			GtnWsReportDataSelectionBean dataSelectionBean,
			List<GtnReportComparisonProjectionBean> comparisonProjectionsList, String componentId) {
		if (isComparisonProjectionChanged) {
			dataSelectionBean.setComparisonProjectionBeanList(comparisonProjectionsList);
			GtnUIFrameworkComponentData comparisonData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
					.getComponentData();
			comparisonData.setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
			if (dataSelectionBean.getComparisonProjectionBeanList() != null) {
				String displayValue = getDisplayValue(dataSelectionBean.getComparisonProjectionBeanList());
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("reportingDashboardTab_reportingDashboardComparisonConfig", componentId)
						.setV8PopupFieldValue(displayValue);
			}
			updateComparisonBaasis(comparisonProjectionsList,componentId);
		}
	}

	private String getDisplayValue(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		if (comparisonProjectionBeanList.size() > 1) {
			return "MULTIPLE";
		}
		return comparisonProjectionBeanList.get(0).getProjectionName();
	}

	private void updateCustomView(boolean isCustomView, GtnWsReportDataSelectionBean dataSelectionBean,
			String customViewName, String componentId) throws GtnFrameworkValidationFailedException {
		if (isCustomView) {
			dataSelectionBean.setCustomViewMasterSid(Integer.valueOf(customViewName));
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboardTab_displaySelectionTabCustomView", componentId, Arrays.asList(""));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabCustomView", componentId)
					.loadV8ComboBoxComponentValue(customViewName);
		}
	}

	private void updateVariables(boolean isVariablesChanged, List<Object> variableList,
			GtnWsReportDataSelectionBean dataSelectionBean, String componentId)
			throws GtnFrameworkValidationFailedException {
		if (isVariablesChanged) {
			dataSelectionBean.setVariablesList(variableList);
			GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
					GtnWsReportVariablesType.values().length - 1);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable", componentId)
					.addAllItemsToMultiSelect(
							Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()),
							Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable", componentId)
					.updateSelection(variableList);
		}
	}

	private void updateFrequency(boolean isFrequencyChanged, GtnWsReportDataSelectionBean dataSelectionBean,
			String frequency, String componentId) throws GtnFrameworkValidationFailedException {
		if (isFrequencyChanged) {
			dataSelectionBean.setFrequency(Integer.valueOf(frequency));
			dataSelectionBean.setFrequencyName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_landingScreenVariableBreakdownFrequencyConfig",
							componentId)
					.getStringCaptionFromV8ComboBox());
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequency));
		}
	}

	private void updateCompany(boolean isCompanyChanged, String company,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		if (isCompanyChanged) {
			dataSelectionBean.setCompanyReport(Integer.valueOf(company));
		}
	}

	private void updateBusinessUnit(boolean isBusinessUnitChanged, String businessUnit,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		if (isBusinessUnitChanged) {
			dataSelectionBean.setBusinessUnitReport(Integer.valueOf(businessUnit));
		}
	}

	private void updateReportDataSource(boolean isReportDataSourceChanged, String reportDataSource,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		if (isReportDataSourceChanged) {
			dataSelectionBean.setReportDataSource(Integer.valueOf(reportDataSource));
		}
	}

	private void updateFromPeriod(boolean isFromPeriodChanged, String fromPeriod,
			GtnWsReportDataSelectionBean dataSelectionBean, String componentId, String fromPeriodValue) throws GtnFrameworkGeneralException {
		if (isFromPeriodChanged) {
			dataSelectionBean.setFromPeriodReport(Integer.valueOf(fromPeriod));
			 loadPeriodRangeFrom(componentId, fromPeriodValue);
			 loadPeriodRangeTo(componentId, fromPeriodValue);
		}
	}

	private void loadPeriodRangeTo(String componentId, String fromPeriodValue) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig toPeriodLoadAction = new GtnUIFrameWorkActionConfig();
		toPeriodLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		toPeriodLoadAction.addActionParameter(GtnFrameworkLoadToInDataSelectionAction.class.getName());
		toPeriodLoadAction.addActionParameter(fromPeriodValue);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, toPeriodLoadAction);
	}

	private void loadPeriodRangeFrom(String componentId, String fromPeriodValue) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig fromPeriodLoadAction = new GtnUIFrameWorkActionConfig();
		fromPeriodLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fromPeriodLoadAction.addActionParameter(GtnFrameworkLoadFromInDataSelectionAction.class.getName());
		fromPeriodLoadAction.addActionParameter(fromPeriodValue);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, fromPeriodLoadAction);
	}
	
	private void updateComparisonBaasis(List<GtnReportComparisonProjectionBean> comparisonProjectionsList,
			String componentId) {
		int initialCapacity = 4 + comparisonProjectionsList.size();
		List<String> inputForComparisonBasisList = new ArrayList<>(initialCapacity);
		inputForComparisonBasisList.add("Actuals");
		inputForComparisonBasisList.add("Accruals");
		inputForComparisonBasisList.add("Current Projection");
		comparisonProjectionsList.stream().forEach((comparisonProjectionBeans) -> {
			inputForComparisonBasisList.add(comparisonProjectionBeans.getProjectionName());
		});
		List idList = IntStream.range(1, initialCapacity).boxed().collect(Collectors.toList());
		GtnUIFrameworkComboBoxConfig comparisonBasisComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis",
						componentId)
				.getComponentConfig().getGtnComboboxConfig();
		comparisonBasisComboboxConfig.setItemCaptionValues(inputForComparisonBasisList);
		comparisonBasisComboboxConfig.setItemValues(idList);

		GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
		combobox.reloadComponentFromParent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				"reportingDashboard_displaySelectionTabComparisonBasis", componentId, Arrays.asList(""));
	}

	
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
