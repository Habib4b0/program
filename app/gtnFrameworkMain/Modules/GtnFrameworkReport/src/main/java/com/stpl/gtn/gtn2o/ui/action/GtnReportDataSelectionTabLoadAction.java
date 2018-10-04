package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId).getComponentData().getSharedPopupData();

			GtnWsRecordBean customerRecordBean = reportDataSelectionBean.getCustomerHierarchyRecordBean();
			GtnWsRecordBean productRecordBean = reportDataSelectionBean.getProductHierarchyRecordBean();

			if (reportDataSelectionBean.getPrivateViewName() != null) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabPrivateViews", componentId)
						.setV8PopupFieldValue(reportDataSelectionBean.getPrivateViewName());
			}

			if (reportDataSelectionBean.getPublicViewName() != null) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabPublicViews", componentId)
						.setV8PopupFieldValue(reportDataSelectionBean.getPublicViewName());
			}

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_company", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCompanyReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_businessUnit", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getBusinessUnitReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getReportDataSource());

			GtnUIFrameworkComponentData customerHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId).getComponentData();
			customerHierarchyData.setCustomData(customerRecordBean);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId)
					.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));

			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"dataSelectionTab_displaySelectionTabCustomView", componentId, Arrays.asList(""));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabCustomView", componentId)
					.loadV8ComboBoxComponentValue(String.valueOf(reportDataSelectionBean.getCustomViewMasterSid()));
			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboardTab_displaySelectionTabCustomView", componentId, Arrays.asList(""));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabCustomView", componentId)
					.loadV8ComboBoxComponentValue(String.valueOf(reportDataSelectionBean.getCustomViewMasterSid()));

			new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE, componentId,
					Arrays.asList(""));

			Integer hierarchyDefinitionSid = Integer.valueOf(String.valueOf(
					customerRecordBean.getPropertyValueByIndex(customerRecordBean.getProperties().size() - 1)));

			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
					.getComponentConfig().getGtnComboboxConfig();

			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

			GtnUIFrameworkComboBoxComponent customerRelationshipCombobox = new GtnUIFrameworkComboBoxComponent();
			customerRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId,
					Arrays.asList(hierarchyDefinitionSid));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());

			int relationshipValue = Integer.parseInt(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_RELATIONSHIP, componentId)
					.getCaptionFromV8ComboBox());

			GtnUIFrameworkComboBoxConfig customerRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION,
							componentId)
					.getComponentConfig().getGtnComboboxConfig();

			customerRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customerRelationshipVersionComboboxConfig
					.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

			GtnUIFrameworkComboBoxComponent customerRelationshipVersionCombobox = new GtnUIFrameworkComboBoxComponent();
			customerRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_RELATIONSHIP_VERSION, componentId,
					Arrays.asList(relationshipValue));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_CUSTOMER_SELECTION_LEVEL, componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerHierarchyForecastLevel());

			String dsCustomerTableId = "dataSelectionTab_customerDualListBox";
			AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsCustomerTableId,
					componentId);
			GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) abstractComponent.getData();

			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData
					.getCustomData();

			TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();

			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkHierarchyTreeBuilder = dualListBoxBean.getTreeBuilder();
			gtnUIFrameworkHierarchyTreeBuilder.buildTree(reportDataSelectionBean.getSelectedCustomerHierarchyList());
			gtnUIFrameworkHierarchyTreeBuilder.loadRightTreeTable(rightTable, 1);
			rightTable.getDataProvider().refreshAll();
			rightTable.markAsDirty();

			GtnUIFrameworkComponentData productHierarchyData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId).getComponentData();
			productHierarchyData.setCustomData(productRecordBean);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId)
					.setV8PopupFieldValue(productRecordBean.getPropertyValueByIndex(0));

			Integer productHierarchyDefinitionSid = Integer.valueOf(String
					.valueOf(productRecordBean.getPropertyValueByIndex(productRecordBean.getProperties().size() - 1)));

			GtnUIFrameworkComboBoxConfig productRelationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP,
							componentId)
					.getComponentConfig().getGtnComboboxConfig();

			productRelationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			productRelationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);

			GtnUIFrameworkComboBoxComponent productRelationshipCombobox = new GtnUIFrameworkComboBoxComponent();
			productRelationshipCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP, componentId,
					Arrays.asList(productHierarchyDefinitionSid));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP,
							componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getProductRelationshipBuilderSid());

			int productrelationshipValue = Integer.parseInt(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_RELATIONSHIP,
							componentId)
					.getCaptionFromV8ComboBox());

			GtnUIFrameworkComboBoxConfig productRelationshipVersionComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION,
							componentId)
					.getComponentConfig().getGtnComboboxConfig();

			productRelationshipVersionComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			productRelationshipVersionComboboxConfig
					.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

			GtnUIFrameworkComboBoxComponent productRelationshipVersionCombobox = new GtnUIFrameworkComboBoxComponent();
			productRelationshipVersionCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_PRODUCT_RELATIONSHIP_VERSION, componentId,
					Arrays.asList(productrelationshipValue));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_DATA_SELECTION_TAB_LEVEL, componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getProductHierarchyForecastLevel());

			String dsProductTableId = "dataSelectionTab_productdualListBoxComp";
			AbstractComponent dsProductAbstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dsProductTableId,
					componentId);
			GtnUIFrameworkComponentData dsProductDualListBoxData = (GtnUIFrameworkComponentData) dsProductAbstractComponent
					.getData();

			GtnFrameworkV8DualListBoxBean dsProductDualListBoxBean = (GtnFrameworkV8DualListBoxBean) dsProductDualListBoxData
					.getCustomData();

			TreeGrid<GtnWsRecordBean> dsProductRightTable = dsProductDualListBoxBean.getRightTable();

			GtnUIFrameworkHierarchyTreeBuilder gtnUIFrameworkProductHierarchyTreeBuilder = dsProductDualListBoxBean
					.getTreeBuilder();
			gtnUIFrameworkProductHierarchyTreeBuilder
					.buildTree(reportDataSelectionBean.getSelectedProductHierarchyList());
			gtnUIFrameworkProductHierarchyTreeBuilder.loadRightTreeTable(dsProductRightTable, 1);
			dsProductRightTable.getDataProvider().refreshAll();
			dsProductRightTable.markAsDirty();

			loadComparisonInReportingDashboard("dataSelectionTab_comparisonLookup", componentId,
					reportDataSelectionBean);

			GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
					GtnWsReportVariablesType.values().length - 1);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabVariable", componentId)
					.addAllItemsToMultiSelect(
							Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()),
							Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
									.collect(Collectors.toList()));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabVariable", componentId)
					.updateSelection(reportDataSelectionBean.getVariablesList());

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_landingScreenVariableBreakdownFrequencyConfig",
							componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getFrequency());

			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			actionConfig.setActionParameterList(Arrays.asList(GtnReportDataAssumptionsTabLoadAction.class.getName(),
					GtnFrameworkReportStringConstants.TAB_SHEET + "dataAssump",
					GtnFrameworkReportStringConstants.CURRENT_TAB,
					GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD, reportDataSelectionBean));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);

			loadFrequencyInReportingDashboard(componentId);

			loadVariableReportingDashboard(componentId);

			loadForecaseEligibleDateInReportingDashboard(componentId);

			loadComparisonInReportingDashboard("reportingDashboardTab_reportingDashboardComparisonConfig", componentId,
					reportDataSelectionBean);

		} catch (Exception exception) {
			gtnLogger.error("Error message", exception);
		}
	}

	private String getDisplayValue(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		if (comparisonProjectionBeanList.size() > 1) {
			return "MULTIPLE";
		}
		return comparisonProjectionBeanList.get(0).getProjectionName();
	}

	private void loadForecaseEligibleDateInReportingDashboard(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnReportForecastEligibleDateReloadInReportingDashboardAction.class.getName());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
	}

	private void loadFrequencyInReportingDashboard(String componentId) throws GtnFrameworkValidationFailedException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getFrequency());
	}

	private void loadVariableReportingDashboard(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnReportVariableReloadInReportingDashboardAction.class.getName());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
	}

	private void loadComparisonInReportingDashboard(String sourceComponentId, String componentId,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnUIFrameworkComponentData comparisonData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId, componentId).getComponentData();
		comparisonData.setCustomData(dataSelectionBean.getComparisonProjectionBeanList());
		if (dataSelectionBean.getComparisonProjectionBeanList() != null) {
			String displayValue = getDisplayValue(dataSelectionBean.getComparisonProjectionBeanList());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(sourceComponentId, componentId)
					.setV8PopupFieldValue(displayValue);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
