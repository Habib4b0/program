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
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportComparisonOptionsLookup {
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getComparisonOptionsLookUpView(String namespace) {

		GtnUIFrameworkViewConfig comparisonOptionsLookupView = new GtnUIFrameworkViewConfig();
		comparisonOptionsLookupView.setViewName("Comparison Options");
		comparisonOptionsLookupView.setViewId("comparisonOptions");
		comparisonOptionsLookupView.setDefaultView(false);
		addComponentList(comparisonOptionsLookupView, namespace);
		return comparisonOptionsLookupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> comparisonOptionsLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(comparisonOptionsLookupComponentList);
		addRootLayout(comparisonOptionsLookupComponentList, namespace);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig comparisonOptionsLookupVLayout = new GtnUIFrameworkComponentConfig();
		comparisonOptionsLookupVLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonOptionsLookupVLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "rootLayout");
		comparisonOptionsLookupVLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsLookupVLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsLookupVLayout.setAddToParent(false);
		comparisonOptionsLookupVLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig comparisonOptionsLookupConf = new GtnUIFrameworkLayoutConfig();
		comparisonOptionsLookupConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		comparisonOptionsLookupVLayout.setGtnLayoutConfig(comparisonOptionsLookupConf);
		componentList.add(comparisonOptionsLookupVLayout);
		getRootComponentForPopUp(componentList, namespace);
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		gtnLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "rootLayout");
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		componentList.add(gtnLayout);

		addComparisonOptionsPanel(componentList, namespace);
		addMassUpdateRootPanel(componentList, namespace);
		addResultsPanel(componentList, gtnLayout.getComponentId());
		addControlButtonComponent(componentList, gtnLayout.getComponentId(),namespace);
	}

	private void addComparisonOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig comparisonOptionsPanelConfig = new GtnUIFrameworkComponentConfig();
		comparisonOptionsPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		comparisonOptionsPanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsPanel");
		comparisonOptionsPanelConfig.setComponentName("Comparison Options");
		comparisonOptionsPanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		comparisonOptionsPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsPanelConfig.setAddToParent(true);
		componentList.add(comparisonOptionsPanelConfig);
		addComparisonOptionsLayout(componentList, namespace);
	}

	private void addComparisonOptionsLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonOptionsLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonOptionsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonOptionsLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsLayout");
		comparisonOptionsLayoutConfig.setComponentName("Comparison Options Layout");
		comparisonOptionsLayoutConfig.setAddToParent(true);
		comparisonOptionsLayoutConfig.setComponentWidth("102%");
		comparisonOptionsLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsPanel");
		comparisonOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		comparisonOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		comparisonOptionsLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(comparisonOptionsLayoutConfig);

		addComparisonBasisComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
		addFrequencyComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
		addPeriodRangeFromComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
		addPeriodRangeToComponent(componentList, comparisonOptionsLayoutConfig.getComponentId(),namespace);
	}

	private void addComparisonBasisComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig comparisonBasisConfig = layoutsConfig
				.getHorizontalLayoutConfig("comparisonBasisConfig", parentId);
		componentList.add(comparisonBasisConfig);

		GtnUIFrameworkComponentConfig comparisonBasis = new GtnUIFrameworkComponentConfig();
		comparisonBasis.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		comparisonBasis.setComponentId("comparisonOptionsLookup_comparisonBasis");
		comparisonBasis.setComponentName("Comparison Basis: ");
		comparisonBasis.setAddToParent(true);
		comparisonBasis.setParentComponentId(comparisonBasisConfig.getComponentId());
		comparisonBasis.setEnable(false);

		componentList.add(comparisonBasis);
	}

	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig frequencyConfig = layoutsConfig.getHorizontalLayoutConfig("frequencyConfig",
				parentId);
		componentList.add(frequencyConfig);

		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		frequency.setComponentId("comparisonOptionsLookup_frequencyConfig");
		frequency.setComponentName("Frequency: ");
		frequency.setAddToParent(true);
		frequency.setParentComponentId(frequencyConfig.getComponentId());
		frequency.setEnable(false);

		componentList.add(frequency);
	}

	private void addPeriodRangeFromComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig periodRangeFromConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeFromConfig", parentId);
		componentList.add(periodRangeFromConfig);

		GtnUIFrameworkComponentConfig periodRangeFrom = new GtnUIFrameworkComponentConfig();
		periodRangeFrom.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeFrom.setComponentId("comparisonOptionsLookup_periodRangeFrom");
		periodRangeFrom.setComponentName("Period Range From: ");
		periodRangeFrom.setAddToParent(true);
		periodRangeFrom.setParentComponentId(periodRangeFromConfig.getComponentId());
		periodRangeFrom.setEnable(false);

		componentList.add(periodRangeFrom);
	}

	private void addPeriodRangeToComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig periodRangeToConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeToConfig", parentId);
		componentList.add(periodRangeToConfig);

		GtnUIFrameworkComponentConfig periodRangeTo = new GtnUIFrameworkComponentConfig();
		periodRangeTo.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeTo.setComponentId("comparisonOptionsLookup_periodRangeTo");
		periodRangeTo.setComponentName("Period Range To: ");
		periodRangeTo.setAddToParent(true);
		periodRangeTo.setParentComponentId(periodRangeToConfig.getComponentId());
		periodRangeTo.setEnable(false);

		componentList.add(periodRangeTo);
	}

	private void addMassUpdateRootPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig massUpdatePanelConfig = new GtnUIFrameworkComponentConfig();
		massUpdatePanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		massUpdatePanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdatePanel");
		massUpdatePanelConfig.setComponentName("Mass Update");
		massUpdatePanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		massUpdatePanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		massUpdatePanelConfig.setAddToParent(true);
		componentList.add(massUpdatePanelConfig);
		massUpdateLayout(componentList, namespace);
	}

	private void massUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig massUpdateLayoutConfig = new GtnUIFrameworkComponentConfig();
		massUpdateLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		massUpdateLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdateLayout");
		massUpdateLayoutConfig.setComponentName("MassUpdateLayout");
		massUpdateLayoutConfig.setAddToParent(true);
		massUpdateLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		massUpdateLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdatePanel");
		massUpdateLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		massUpdateLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(massUpdateLayoutConfig);

		addValueComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addComparisonComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addStartPeriodComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addEndPeriodComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
		addPopulateButtonComponent(componentList, massUpdateLayoutConfig.getComponentId(),namespace);
	}

	private void addValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig valueConfig = layoutsConfig.getHorizontalLayoutConfig("valueConfig", parentId);
		componentList.add(valueConfig);

		GtnUIFrameworkComponentConfig value = new GtnUIFrameworkComponentConfig();
		value.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		value.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"value");
		value.setComponentName("Value: ");
		value.setAddToParent(true);
		value.setParentComponentId(valueConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig valueLoadConfig = new GtnUIFrameworkComboBoxConfig();
		valueLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		valueLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		value.setGtnComboboxConfig(valueLoadConfig);
		componentList.add(value);
	}

	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig comparisonConfig = layoutsConfig.getHorizontalLayoutConfig("comparisonConfig",
				parentId);
		componentList.add(comparisonConfig);

		GtnUIFrameworkComponentConfig comparison = new GtnUIFrameworkComponentConfig();
		comparison.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparison.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"comparison");
		comparison.setComponentName("Comparison: ");
		comparison.setAddToParent(true);
		comparison.setParentComponentId(comparisonConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comparisonLoadConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comparisonLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		comparison.setGtnComboboxConfig(comparisonLoadConfig);
		componentList.add(comparison);
	}

	private void addStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig startPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("startPeriodConfig",
				parentId);
		componentList.add(startPeriodConfig);

		GtnUIFrameworkComponentConfig startPeriod = new GtnUIFrameworkComponentConfig();
		startPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		startPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"startPeriod");
		startPeriod.setComponentName("Start Period: ");
		startPeriod.setAddToParent(true);
		startPeriod.setParentComponentId(startPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig startPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		startPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		startPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		startPeriod.setGtnComboboxConfig(startPeriodLoadConfig);
		componentList.add(startPeriod);
	}

	private void addEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig endPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("endPeriodConfig",
				parentId);
		componentList.add(endPeriodConfig);

		GtnUIFrameworkComponentConfig endPeriod = new GtnUIFrameworkComponentConfig();
		endPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		endPeriod.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"endPeriod");
		endPeriod.setComponentName("End Period: ");
		endPeriod.setAddToParent(true);
		endPeriod.setParentComponentId(endPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig endPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		endPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		endPeriodLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		endPeriod.setGtnComboboxConfig(endPeriodLoadConfig);
		componentList.add(endPeriod);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig populateButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("populateButtonConfig", parentId);
		componentList.add(populateButtonConfig);

		GtnUIFrameworkComponentConfig populateButton = new GtnUIFrameworkComponentConfig();
		populateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		populateButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"populateButton");
		populateButton.setComponentName("POPULATE");
		populateButton.setAddToParent(true);
		populateButton.setParentComponentId(populateButtonConfig.getComponentId());

		componentList.add(populateButton);
	}

	private void addControlButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig controlButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("controlButtonConfig", parentId);
		componentList.add(controlButtonConfig);

		GtnUIFrameworkComponentConfig submitButton = new GtnUIFrameworkComponentConfig();
		submitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"submitButton");
		submitButton.setComponentName("SUBMIT");
		submitButton.setAddToParent(true);
		submitButton.setParentComponentId(controlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"resetButton");
		resetButton.setComponentName("RESET");
		resetButton.setAddToParent(true);
		resetButton.setParentComponentId(controlButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"closeButton");
		closeButton.setComponentName("CLOSE");
		closeButton.setAddToParent(true);
		closeButton.setParentComponentId(controlButtonConfig.getComponentId());

		componentList.add(submitButton);
		componentList.add(resetButton);
		componentList.add(closeButton);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig comparisonBreakdownResultsPanel = new GtnUIFrameworkComponentConfig();
		comparisonBreakdownResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		comparisonBreakdownResultsPanel.setComponentId("comparisonBreakdownResultsPanel");
		comparisonBreakdownResultsPanel.setParentComponentId(parentId);
		comparisonBreakdownResultsPanel.setAddToParent(true);
		comparisonBreakdownResultsPanel.setComponentName("Time Period");
		comparisonBreakdownResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(comparisonBreakdownResultsPanel);

		GtnUIFrameworkLayoutConfig comparisonBreakdownColLayout = new GtnUIFrameworkLayoutConfig();
		comparisonBreakdownColLayout.setLayoutType(GtnUIFrameworkLayoutType.COL2_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownResultsLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownResultsLayout.setComponentId("comparisonBreakdownResultsLayout");
		variableBreakdownResultsLayout.setParentComponentId(comparisonBreakdownResultsPanel.getComponentId());
		variableBreakdownResultsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownResultsLayout.setGtnLayoutConfig(comparisonBreakdownColLayout);
		variableBreakdownResultsLayout.setAddToParent(true);

		componentList.add(variableBreakdownResultsLayout);
        

		addLabelComponent(componentList, "comparisonBreakdownResultsLayout");
		addResultTable(componentList, "comparisonBreakdownResultsLayout");

	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		 	GtnUIFrameworkComponentConfig comparisonBreakdownLookupResultsPagedTableComponentLayout = layoutsConfig.getHorizontalLayoutConfig(
	                "comparisonBreakdownLookupResultsPagedTableComponentLayout", parentId);
	             comparisonBreakdownLookupResultsPagedTableComponentLayout.setComponentWidth("80%");
	                componentList.add(comparisonBreakdownLookupResultsPagedTableComponentLayout);
	                
			GtnUIFrameworkComponentConfig comparisonBreakdownLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
			comparisonBreakdownLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
			comparisonBreakdownLookupResultsPagedTableComponent.setComponentId(
					parentId + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonBreakdownResultsPagedTableComponent");
			comparisonBreakdownLookupResultsPagedTableComponent.setComponentName("Results");
			comparisonBreakdownLookupResultsPagedTableComponent.setParentComponentId(comparisonBreakdownLookupResultsPagedTableComponentLayout.getComponentId());
			comparisonBreakdownLookupResultsPagedTableComponent.setAddToParent(true);
	                comparisonBreakdownLookupResultsPagedTableComponent.setComponentWidth("1000px");
	                
			List<String> comparisonBreakdownLookupTableStyle = new ArrayList<>();
			comparisonBreakdownLookupTableStyle.add("filterbar");
			comparisonBreakdownLookupTableStyle.add("v-has-width");
			comparisonBreakdownLookupTableStyle.add("v-table-filterbar");
			comparisonBreakdownLookupTableStyle.add("table-header-normal");
			comparisonBreakdownLookupResultsPagedTableComponent.setComponentWidth("100%");
			comparisonBreakdownLookupResultsPagedTableComponent.setComponentStyle(comparisonBreakdownLookupTableStyle);

			componentList.add(comparisonBreakdownLookupResultsPagedTableComponent);

			GtnUIFrameworkPagedTableConfig comparisonBreakdownLookupResultsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
			comparisonBreakdownLookupResultsPagedTableConfig.setEditable(false);
			comparisonBreakdownLookupResultsPagedTableConfig.setFilterBar(true);
			comparisonBreakdownLookupResultsPagedTableConfig.setPageLength(10);
			comparisonBreakdownLookupResultsPagedTableConfig.setItemPerPage(10);
			comparisonBreakdownLookupResultsPagedTableConfig.setSelectable(true);
			comparisonBreakdownLookupResultsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
	                comparisonBreakdownLookupResultsPagedTableConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
	                comparisonBreakdownLookupResultsPagedTableConfig.setGridColumnHeader(GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_TABLE_HEADERS_SERVICE);
	                comparisonBreakdownLookupResultsPagedTableConfig.setEnableCheckBoxInGridHeader(true);
	                
			comparisonBreakdownLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
					GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
					GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
					GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

			comparisonBreakdownLookupResultsPagedTableConfig
					.setColumnHeaders(GtnFrameworkReportStringConstants.getComparisonBreakdownHeader());
			comparisonBreakdownLookupResultsPagedTableConfig.setTableColumnMappingId(
					new Object[] { "projectionName", "description", "marketType", "contractHolder", "contract", "brand" });

			comparisonBreakdownLookupResultsPagedTableComponent
					.setGtnPagedTableConfig(comparisonBreakdownLookupResultsPagedTableConfig);
		
	}
	
    private void addLabelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
        
        GtnUIFrameworkComponentConfig comparisonBreakdownLabelLayoutConfig = layoutsConfig.getHorizontalLayoutConfig(
                "comparisonBreakdownLabelLayoutConfig", parentId);
        comparisonBreakdownLabelLayoutConfig.setComponentWidth("20%");
        componentList.add(comparisonBreakdownLabelLayoutConfig);

        GtnUIFrameworkComponentConfig comparisonBreakdownLabelConfiglatestApproved = new GtnUIFrameworkComponentConfig();
        comparisonBreakdownLabelConfiglatestApproved.setComponentType(GtnUIFrameworkComponentType.LABEL);
        comparisonBreakdownLabelConfiglatestApproved.setComponentId("comparisonBreakdownLabelConfig");
        comparisonBreakdownLabelConfiglatestApproved.setParentComponentId(comparisonBreakdownLabelLayoutConfig.getComponentId());
        comparisonBreakdownLabelConfiglatestApproved.setAddToParent(true);
        comparisonBreakdownLabelConfiglatestApproved.setMargin(true);
        comparisonBreakdownLabelConfiglatestApproved.setComponentValue("Latest Approved");
        componentList.add(comparisonBreakdownLabelConfiglatestApproved);
        
        
    }
}
