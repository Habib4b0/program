package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkReportVariableBreakdownLookup {
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getVariableBreakdownLookUpView(String namespace) {

		GtnUIFrameworkViewConfig variableBreakdownView = new GtnUIFrameworkViewConfig();
		variableBreakdownView.setViewName("Variable Breakdown");
		variableBreakdownView.setViewId("variableBreakdown");
		variableBreakdownView.setDefaultView(false);
		addComponentList(variableBreakdownView, namespace);
		return variableBreakdownView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addVariableBreakdownMainLayout(componentList, namespace);
	}

	private void addVariableBreakdownMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig variableBreakdownLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownLayout");
		variableBreakdownLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownLayout.setAddToParent(false);
		variableBreakdownLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig variableBreakdownConfig = new GtnUIFrameworkLayoutConfig();
		variableBreakdownConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		variableBreakdownLayout.setGtnLayoutConfig(variableBreakdownConfig);
		componentList.add(variableBreakdownLayout);
		getVariableBreakdownPopupComponents(componentList, namespace);
	}

	public void getVariableBreakdownPopupComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownPopupConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig variableBreakdownPopupLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownPopupLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		variableBreakdownPopupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownPopupConfig.setGtnLayoutConfig(variableBreakdownPopupLayout);
		variableBreakdownPopupConfig.setAddToParent(true);
		variableBreakdownPopupConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownPopupConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "variableBreakdownPopupConfig");
		variableBreakdownPopupConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownLayout");
		variableBreakdownPopupConfig.setSpacing(true);
		variableBreakdownPopupConfig.setMargin(true);
		componentList.add(variableBreakdownPopupConfig);
		
		addVariableBreakdownMassUpdateRootPanel(componentList, namespace);
		addVariableBreakdownFrequencyAndHistoryComponent(componentList, variableBreakdownPopupConfig.getComponentId(),namespace);
		addVariableBreakdownResultsPanel(componentList, variableBreakdownPopupConfig.getComponentId());
		addVariableBreakdownControlButtonComponent(componentList, variableBreakdownPopupConfig.getComponentId(),namespace);
	}

	private void addVariableBreakdownFrequencyAndHistoryComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownFrequencyAndHistoryConfig = layoutsConfig.getHorizontalLayoutConfig("resultLayoutConfig",
				parentId);
		componentList.add(variableBreakdownFrequencyAndHistoryConfig);

		GtnUIFrameworkLayoutConfig variableBreakdownFrequencyAndHistoryLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownFrequencyAndHistoryLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownFrequencyAndHistoryResultLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentId("variableBreakdownFrequencyAndHistoryResultLayout");
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentName("variableBreakdownFrequencyAndHistoryResultLayout");
		variableBreakdownFrequencyAndHistoryResultLayout.setAddToParent(true);
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownFrequencyAndHistoryResultLayout.setParentComponentId(variableBreakdownFrequencyAndHistoryConfig.getComponentId());
		variableBreakdownFrequencyAndHistoryResultLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		variableBreakdownFrequencyAndHistoryResultLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		variableBreakdownFrequencyAndHistoryResultLayout.setGtnLayoutConfig(variableBreakdownFrequencyAndHistoryLayout);
		componentList.add(variableBreakdownFrequencyAndHistoryResultLayout);

		GtnUIFrameworkComponentConfig variableBreakdownFrequencyLayoutConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownFrequencyLayoutConfig", variableBreakdownFrequencyAndHistoryResultLayout.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownFrequencyConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownFrequencyConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownFrequencyConfig.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownFrequencyConfig");
		variableBreakdownFrequencyConfig.setComponentName("Frequency: ");
		variableBreakdownFrequencyConfig.setAddToParent(true);
		variableBreakdownFrequencyConfig.setParentComponentId(variableBreakdownFrequencyLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownFrequencyLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownFrequencyLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableBreakdownFrequencyLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownFrequencyConfig.setGtnComboboxConfig(variableBreakdownFrequencyLoadConfig);

		GtnUIFrameworkComponentConfig variableBreakdownHistoryLayoutConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownHistoryLayoutConfig", variableBreakdownFrequencyAndHistoryResultLayout.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownHistoryConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownHistoryConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownHistoryConfig.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownHistoryConfig");
		variableBreakdownHistoryConfig.setComponentName("History: ");
		variableBreakdownHistoryConfig.setAddToParent(true);
		variableBreakdownHistoryConfig.setParentComponentId(variableBreakdownHistoryLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownHistoryLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownHistoryLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableBreakdownHistoryLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownHistoryConfig.setGtnComboboxConfig(variableBreakdownHistoryLoadConfig);

		componentList.add(variableBreakdownFrequencyLayoutConfig);
		componentList.add(variableBreakdownFrequencyConfig);
		componentList.add(variableBreakdownHistoryLayoutConfig);
		componentList.add(variableBreakdownHistoryConfig);

	}

	private void addVariableBreakdownMassUpdateRootPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownMassUpdateRootPanelConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownMassUpdateRootPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		variableBreakdownMassUpdateRootPanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateRootPanelConfig");
		variableBreakdownMassUpdateRootPanelConfig.setComponentName("Mass Update");
		variableBreakdownMassUpdateRootPanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "variableBreakdownPopupConfig");
		variableBreakdownMassUpdateRootPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownMassUpdateRootPanelConfig.setAddToParent(true);
		componentList.add(variableBreakdownMassUpdateRootPanelConfig);
		addVariableBreakdownMassUpdateLayout(componentList, namespace);
	}

	private void addVariableBreakdownMassUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig variableBreakdownMassUpdateLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownMassUpdateLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownMassUpdateConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownMassUpdateConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownMassUpdateConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateConfig");
		variableBreakdownMassUpdateConfig.setComponentName("MassUpdateLayout");
		variableBreakdownMassUpdateConfig.setAddToParent(true);
		variableBreakdownMassUpdateConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownMassUpdateConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateRootPanelConfig");
		variableBreakdownMassUpdateConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		variableBreakdownMassUpdateConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		variableBreakdownMassUpdateConfig.setGtnLayoutConfig(variableBreakdownMassUpdateLayout);
		componentList.add(variableBreakdownMassUpdateConfig);

		addVariableBreakdownValueComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownFileorProjectionComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownStartPeriodComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownEndPeriodComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
		addVariableBreakdownPopulateButtonComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),namespace);
	}

	private void addVariableBreakdownValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownValueConfig = layoutsConfig.getHorizontalLayoutConfig("variableBreakdownValueConfig", parentId);
		componentList.add(variableBreakdownValueConfig);

		GtnUIFrameworkComponentConfig variableBreakdownValue = new GtnUIFrameworkComponentConfig();
		variableBreakdownValue.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownValue.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownValue");
		variableBreakdownValue.setComponentName("Value: ");
		variableBreakdownValue.setAddToParent(true);
		variableBreakdownValue.setParentComponentId(variableBreakdownValueConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownValueLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownValueLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownValueLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownValue.setGtnComboboxConfig(variableBreakdownValueLoadConfig);
		componentList.add(variableBreakdownValue);
	}

	private void addVariableBreakdownFileorProjectionComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownValueFileorProjectionConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownValueFileorProjectionConfig", parentId);
		componentList.add(variableBreakdownValueFileorProjectionConfig);

		GtnUIFrameworkComponentConfig variableBreakdownValueFileorProjection = new GtnUIFrameworkComponentConfig();
		variableBreakdownValueFileorProjection.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownValueFileorProjection.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownValueFileorProjection");
		variableBreakdownValueFileorProjection.setComponentName("File/Projection: ");
		variableBreakdownValueFileorProjection.setAddToParent(true);
		variableBreakdownValueFileorProjection.setParentComponentId(variableBreakdownValueFileorProjectionConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownValueFileorProjectionLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownValueFileorProjectionLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownValueFileorProjectionLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownValueFileorProjection.setGtnComboboxConfig(variableBreakdownValueFileorProjectionLoadConfig);
		componentList.add(variableBreakdownValueFileorProjection);
	}

	private void addVariableBreakdownStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownStartPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("variableBreakdownStartPeriodConfig",
				parentId);
		componentList.add(variableBreakdownStartPeriodConfig);

		GtnUIFrameworkComponentConfig variableBreakdownStartPeriod = new GtnUIFrameworkComponentConfig();
		variableBreakdownStartPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownStartPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownStartPeriod");
		variableBreakdownStartPeriod.setComponentName("Start Period: ");
		variableBreakdownStartPeriod.setAddToParent(true);
		variableBreakdownStartPeriod.setParentComponentId(variableBreakdownStartPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownStartPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownStartPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownStartPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownStartPeriod.setGtnComboboxConfig(variableBreakdownStartPeriodLoadConfig);
		componentList.add(variableBreakdownStartPeriod);
	}

	private void addVariableBreakdownEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownEndPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("variableBreakdownEndPeriodConfig",
				parentId);
		componentList.add(variableBreakdownEndPeriodConfig);

		GtnUIFrameworkComponentConfig variableBreakdownEndPeriod = new GtnUIFrameworkComponentConfig();
		variableBreakdownEndPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownEndPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownEndPeriod");
		variableBreakdownEndPeriod.setComponentName("End Period: ");
		variableBreakdownEndPeriod.setAddToParent(true);
		variableBreakdownEndPeriod.setParentComponentId(variableBreakdownEndPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownEndPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownEndPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownEndPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		variableBreakdownEndPeriod.setGtnComboboxConfig(variableBreakdownEndPeriodLoadConfig);
		componentList.add(variableBreakdownEndPeriod);
	}

	private void addVariableBreakdownPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownPopulateButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownPopulateButtonConfig", parentId);
		componentList.add(variableBreakdownPopulateButtonConfig);

		GtnUIFrameworkComponentConfig variableBreakdownPopulateButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownPopulateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownPopulateButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownPopulateButton");
		variableBreakdownPopulateButton.setComponentName("POPULATE");
		variableBreakdownPopulateButton.setAddToParent(true);
		variableBreakdownPopulateButton.setParentComponentId(variableBreakdownPopulateButtonConfig.getComponentId());

		componentList.add(variableBreakdownPopulateButton);
	}

	private void addVariableBreakdownControlButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownControlButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownControlButtonConfig", parentId);
		componentList.add(variableBreakdownControlButtonConfig);

		GtnUIFrameworkComponentConfig variableBreakdownSubmitButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownSubmitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownSubmitButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownSubmitButton");
		variableBreakdownSubmitButton.setComponentName("SUBMIT");
		variableBreakdownSubmitButton.setAddToParent(true);
		variableBreakdownSubmitButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownResetButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownResetButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownResetButton");
		variableBreakdownResetButton.setComponentName("RESET");
		variableBreakdownResetButton.setAddToParent(true);
		variableBreakdownResetButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownCloseButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownCloseButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"variableBreakdownCloseButton");
		variableBreakdownCloseButton.setComponentName("CLOSE");
		variableBreakdownCloseButton.setAddToParent(true);
		variableBreakdownCloseButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		componentList.add(variableBreakdownSubmitButton);
		componentList.add(variableBreakdownResetButton);
		componentList.add(variableBreakdownCloseButton);
	}

	private void addVariableBreakdownResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig variableBreakdownResultsPanel = layoutsConfig.getPanelConfig("variableBreakdownResultsPanel", parentId);
		variableBreakdownResultsPanel.setComponentName("Results");
		variableBreakdownResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(variableBreakdownResultsPanel);

		addPagedTableComponent(componentList, variableBreakdownResultsPanel.getComponentId());

	}
	
	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig variableBreakdownLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		variableBreakdownLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		variableBreakdownLookupResultsPagedTableComponent.setComponentId("comparisonLookupResultsPagedTableComponent");
		variableBreakdownLookupResultsPagedTableComponent.setComponentName("Results");
		variableBreakdownLookupResultsPagedTableComponent.setParentComponentId(parentId);
		variableBreakdownLookupResultsPagedTableComponent.setAddToParent(true);

		List<String> variableBreakdownLookupTableStyle = new ArrayList<>();
		variableBreakdownLookupTableStyle.add("filterbar");
		variableBreakdownLookupTableStyle.add("v-has-width");
		variableBreakdownLookupTableStyle.add("v-table-filterbar");
		variableBreakdownLookupTableStyle.add("table-header-normal");
		variableBreakdownLookupResultsPagedTableComponent.setComponentWidth("100%");
		variableBreakdownLookupResultsPagedTableComponent.setComponentStyle(variableBreakdownLookupTableStyle);

		componentList.add(variableBreakdownLookupResultsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig variableBreakdownLookupResultsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		variableBreakdownLookupResultsPagedTableConfig.setEditable(false);
		variableBreakdownLookupResultsPagedTableConfig.setFilterBar(true);
		variableBreakdownLookupResultsPagedTableConfig.setPageLength(10);
		variableBreakdownLookupResultsPagedTableConfig.setItemPerPage(10);
		variableBreakdownLookupResultsPagedTableConfig.setSelectable(true);
		variableBreakdownLookupResultsPagedTableConfig.setSinkItemPerPageWithPageLength(false);

		variableBreakdownLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		variableBreakdownLookupResultsPagedTableConfig.setTableVisibleHeader(new String[] { "Projection Name", "Description",
				"Market Type", "Contract Holder", "Contract", "Brand" });
		variableBreakdownLookupResultsPagedTableConfig.setTableColumnMappingId(
				new Object[] { "projectionName", "description", "marketType", "contractHolder", "contract", "brand" });

		variableBreakdownLookupResultsPagedTableComponent.setGtnPagedTableConfig(variableBreakdownLookupResultsPagedTableConfig);
	}
}
