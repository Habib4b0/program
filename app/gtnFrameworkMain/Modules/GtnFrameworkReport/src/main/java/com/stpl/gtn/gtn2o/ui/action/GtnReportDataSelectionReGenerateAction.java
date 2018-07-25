package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	
		if (isCustomerChanged || isProductChanged || isComparisonProjectionChanged || isCustomView || isFrequencyChanged
				|| isReportDataSourceChanged || isFromPeriodChanged) {

			List<GtnUIFrameWorkActionConfig> onSuccessActionList = new ArrayList<>();
			List<GtnUIFrameWorkActionConfig> onFailureActionList = new ArrayList<>();
			GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.INFO_ACTION);

			alertAction.addActionParameter("Information");
			alertAction.addActionParameter(
					"You have changed the set of CCP’s that included in this report. The report will now update to reflect these changes.");
			alertAction.addActionParameter(onSuccessActionList);
			alertAction.addActionParameter(onFailureActionList);
			
			GtnUIFrameWorkActionConfig callRegenerateActionSuccessConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);

			callRegenerateActionSuccessConfig
					.addActionParameter(GtnFrameworkReportDataSelectionRegenerateConfirmationAction.class.getName());
			callRegenerateActionSuccessConfig.addActionParameter(dataSelectionBean);
                        callRegenerateActionSuccessConfig.addActionParameter("OK");

                        callRegenerateActionSuccessConfig.addActionParameter(selectedCustomerList);
                        callRegenerateActionSuccessConfig.addActionParameter(selectedProductList);
                        callRegenerateActionSuccessConfig.addActionParameter(comparisonProjectionsList);
                        callRegenerateActionSuccessConfig.addActionParameter(customViewName);
                        callRegenerateActionSuccessConfig.addActionParameter(frequency);
                        callRegenerateActionSuccessConfig.addActionParameter(variableList);
                        callRegenerateActionSuccessConfig.addActionParameter(company);
                        callRegenerateActionSuccessConfig.addActionParameter(businessUnit);
                        callRegenerateActionSuccessConfig.addActionParameter(reportDataSource);
                        callRegenerateActionSuccessConfig.addActionParameter(fromPeriod);
                        callRegenerateActionSuccessConfig.addActionParameter(fromPeriodValue);
                        
                        callRegenerateActionSuccessConfig.addActionParameter(isCustomerChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isProductChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isComparisonProjectionChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isCustomView);
                        callRegenerateActionSuccessConfig.addActionParameter(isFrequencyChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isVariablesChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isCompanyChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isBusinessUnitChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isReportDataSourceChanged);
                        callRegenerateActionSuccessConfig.addActionParameter(isFromPeriodChanged);
                        
			onSuccessActionList.add(callRegenerateActionSuccessConfig);
//			GtnUIFrameWorkActionConfig callRegenerateActionFailureConfig = new GtnUIFrameWorkActionConfig(
//					GtnUIFrameworkActionType.CUSTOM_ACTION);
//
//			callRegenerateActionFailureConfig
//					.addActionParameter(GtnFrameworkReportDataSelectionRegenerateConfirmationAction.class.getName());
//			callRegenerateActionFailureConfig.addActionParameter(dataSelectionBeanPersist);
//			callRegenerateActionFailureConfig.addActionParameter("NO");
//                        
//			onFailureActionList.add(callRegenerateActionFailureConfig);
			
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

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
