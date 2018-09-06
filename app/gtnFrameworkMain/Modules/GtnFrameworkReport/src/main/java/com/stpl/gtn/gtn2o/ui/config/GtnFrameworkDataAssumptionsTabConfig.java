package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import static com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportDataSelectionTabConfig.getResetParameters;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnFrameworkReportTabChangeAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkDataAssumptionsTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addDataAssumptionsLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		addTabLayout(componentList, nameSpace);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		
		GtnUIFrameworkComponentConfig verticalLayoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_MAIN_LAYOUT, true, "projectionDetailsTabsheetLayout");
		verticalLayoutConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(verticalLayoutConfig);
		
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_LAYOUT, true, GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_MAIN_LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(layoutConfig);

		addTabSheet(componentList, nameSpace);
		addControlButtonLayout(componentList, nameSpace);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.TAB_SHEET+"dataAssump", true,
				GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_LAYOUT,
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig currentTab = new GtnUIFrameworkTabConfig();
		currentTab.setComponentId(GtnFrameworkReportStringConstants.CURRENT_TAB);
		currentTab.setTabCaption("Current");
		List<GtnUIFrameworkComponentConfig> currentTabTabConfigList = new ArrayList<>();
		currentTab.setTabLayoutComponentConfigList(currentTabTabConfigList);
		addCurrentTab(currentTabTabConfigList,currentTab.getComponentId()+ nameSpace);
                
                GtnUIFrameworkTabConfig dataAssumptionsTab0 = new GtnUIFrameworkTabConfig();
		dataAssumptionsTab0.setComponentId(GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD+"0");
		dataAssumptionsTab0.setTabCaption("dataAssumptionsTab0");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTab0ConfigList = new ArrayList<>();
		dataAssumptionsTab0.setTabLayoutComponentConfigList(dataAssumptionsTab0ConfigList);
		addCurrentTab(dataAssumptionsTab0ConfigList, dataAssumptionsTab0.getComponentId()+nameSpace);
                
                GtnUIFrameworkTabConfig dataAssumptionsTab1 = new GtnUIFrameworkTabConfig();
		dataAssumptionsTab1.setComponentId(GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD+"1");
		dataAssumptionsTab1.setTabCaption("dataAssumptionsTab1");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTab1ConfigList = new ArrayList<>();
		dataAssumptionsTab1.setTabLayoutComponentConfigList(dataAssumptionsTab1ConfigList);
		addCurrentTab(dataAssumptionsTab1ConfigList, dataAssumptionsTab1.getComponentId()+nameSpace);
                
                
                GtnUIFrameworkTabConfig dataAssumptionsTab2 = new GtnUIFrameworkTabConfig();
		dataAssumptionsTab2.setComponentId(GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD+"2");
		dataAssumptionsTab2.setTabCaption("dataAssumptionsTab2");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTab2ConfigList = new ArrayList<>();
		dataAssumptionsTab2.setTabLayoutComponentConfigList(dataAssumptionsTab2ConfigList);
		addCurrentTab(dataAssumptionsTab2ConfigList, dataAssumptionsTab2.getComponentId()+nameSpace);
                
                GtnUIFrameworkTabConfig dataAssumptionsTab3 = new GtnUIFrameworkTabConfig();
		dataAssumptionsTab3.setComponentId(GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD+"3");
		dataAssumptionsTab3.setTabCaption("dataAssumptionsTab3");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTab3ConfigList = new ArrayList<>();
		dataAssumptionsTab3.setTabLayoutComponentConfigList(dataAssumptionsTab3ConfigList);
		addCurrentTab(dataAssumptionsTab3ConfigList, dataAssumptionsTab3.getComponentId()+nameSpace);
                
                GtnUIFrameworkTabConfig dataAssumptionsTab4 = new GtnUIFrameworkTabConfig();
		dataAssumptionsTab4.setComponentId(GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_LOAD+"4");
		dataAssumptionsTab4.setTabCaption("dataAssumptionsTab4");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTab4ConfigList = new ArrayList<>();
		dataAssumptionsTab4.setTabLayoutComponentConfigList(dataAssumptionsTab4ConfigList);
		addCurrentTab(dataAssumptionsTab4ConfigList, dataAssumptionsTab4.getComponentId()+nameSpace);
                
               
                
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(currentTab);
                tabConfigList.add(dataAssumptionsTab0);
                tabConfigList.add(dataAssumptionsTab1);
                tabConfigList.add(dataAssumptionsTab2);
                tabConfigList.add(dataAssumptionsTab3);
                tabConfigList.add(dataAssumptionsTab4);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);
                


	}

	private void addCurrentTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(nameSpace, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);
		addDataAssumptionsPagedTableComponent(componentList, gtnLayout.getComponentId());
                addExcelButtonComponent(componentList, nameSpace, gtnLayout.getComponentId());
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig currentTabNavigationButtonLayout = configProvider.getHorizontalLayoutConfig(
				"currentTabNavigationButtonLayout", true, GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_MAIN_LAYOUT);
		currentTabNavigationButtonLayout.addComponentStyle("stpl-margin-left-30");
		componentList.add(currentTabNavigationButtonLayout);
		addNavigationButtonLayout(componentList, nameSpace + GtnFrameworkReportStringConstants.CURRENT_TAB,
				currentTabNavigationButtonLayout.getComponentId());
	}

	private void addDataAssumptionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig dataAssumptionsPagedTableComponent = configProvider.getUIFrameworkComponentConfig(
				"dataAssumptionsPagedTableComponent"+parentId, true, parentId, GtnUIFrameworkComponentType.PAGED_GRID);
		dataAssumptionsPagedTableComponent.setAuthorizationIncluded(true);
		dataAssumptionsPagedTableComponent.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		dataAssumptionsPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		dataAssumptionsPagedTableComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(dataAssumptionsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig dataAssumptionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();

		dataAssumptionsPagedTableConfig.setColumnHeaders(Arrays.asList(new String[] { "File", "Company",
				"Business Unit", "Type", "Version", "Active From", "Active To","Active File?" }));
              		dataAssumptionsPagedTableConfig.setTableColumnMappingId(new Object[] { "file", "company", "businessUnit",
				"type", "version", "activeFrom", "toPeriod" ,"activeFile" });
		dataAssumptionsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		dataAssumptionsPagedTableConfig.setCountUrl("/gtnReport/gtnWsReportLoadDataAssumptions");
		dataAssumptionsPagedTableConfig.setResultSetUrl("/gtnReport/gtnWsReportLoadDataAssumptions");
		dataAssumptionsPagedTableConfig.setRefreshAtStart(true);
		dataAssumptionsPagedTableConfig.setItemsPerPageAlignCentre(false);
		dataAssumptionsPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		dataAssumptionsPagedTableComponent.setGtnPagedTableConfig(dataAssumptionsPagedTableConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		
		String[] dataAssumptionsPropertyIDs = GtnFrameworkReportStringConstants
				.getReportDataAssumptionsFilterPropertyId();
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> dataAssumptionsFilterConfigMap = new HashMap<>(
				dataAssumptionsPropertyIDs.length);
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.DATEFIELDVAADIN8,GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
                                GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };
		String[] dataAssumptionsComboboxIds = { "" };
		String[] dataAssumptionsComboBoxType = { "" };
		int comboboxStartIndex = 0;
		for (int i = 0; i < dataAssumptionsPropertyIDs.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig dataAssumptionsTableFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			dataAssumptionsTableFilterConfig.setPropertId(dataAssumptionsPropertyIDs[i]);
			dataAssumptionsTableFilterConfig.setGtnComponentType(componentType[i]); 
                        GtnUIFrameworkComponentConfig componentList = new GtnUIFrameworkComponentConfig();
                        componentList.addComponentStyle("v-textfield-custom-report");
                         componentList.addDateFieldStyle("v-report-data-assumption-date-field");
                        dataAssumptionsTableFilterConfig.setGtnComponentConfig(componentList);
			if ((comboboxStartIndex < dataAssumptionsComboboxIds.length)
					&& dataAssumptionsPropertyIDs[i].equals(dataAssumptionsComboboxIds[comboboxStartIndex])) {
				GtnUIFrameworkComponentConfig dataAssumptionsSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				dataAssumptionsSearchFilterConfig.setComponentId("customFilterComboBox");
				dataAssumptionsSearchFilterConfig.setComponentName("customFilterComboBox");
				dataAssumptionsSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				dataAssumptionsSearchFilterConfig.getGtnComboboxConfig()
						.setComboBoxType(dataAssumptionsComboBoxType[comboboxStartIndex]);
				dataAssumptionsSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				dataAssumptionsTableFilterConfig.setGtnComponentConfig(dataAssumptionsSearchFilterConfig);
				comboboxStartIndex++;
			}
			dataAssumptionsFilterConfigMap.put(dataAssumptionsTableFilterConfig.getPropertId(),
					dataAssumptionsTableFilterConfig);
		}
		return dataAssumptionsFilterConfigMap;
	}
        private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {

		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "excelButtonConfig");
		excelButtonConfig.setParentComponentId(parentId);
		excelButtonConfig.setAddToParent(true);                
		excelButtonConfig.setAuthorizationIncluded(true);
                excelButtonConfig.setDefaultFocus(true);  
                excelButtonConfig.addComponentStyle("v-report-data-assumption-excel");
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonInput.setIsTreeTable(true);
                gtnUIFrameworkExcelButtonInput.setExportFileName("Report");		
                gtnUIFrameworkExcelButtonInput.setExportTableId("dataAssumptionsPagedTableComponentcurrentTabdataAssumptionsTab"); 
                
		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.PAGEDGRID_EXCEL_EXPORT_ACTION);
		resultTableExcelAction.addActionParameter(gtnUIFrameworkExcelButtonInput);

		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonInput);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(excelButtonConfig);
	}

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace,
			String parentId) {

		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "previousButtonConfig");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(true);
		previousButtonConfig.setParentComponentId(parentId);
		GtnUIFrameWorkActionConfig previousAction = new GtnUIFrameWorkActionConfig();
		previousAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		previousAction.addActionParameter(GtnFrameworkReportTabChangeAction.class.getName());
		previousAction.addActionParameter("tabSheetMain");
		previousAction.addActionParameter("0");	
		previousButtonConfig.addGtnUIFrameWorkActionConfig(previousAction);
		
		GtnUIFrameworkComponentConfig nextButtonConfig = new GtnUIFrameworkComponentConfig();
		nextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButtonConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "nextButtonConfig");
		nextButtonConfig.setComponentName("NEXT");
		nextButtonConfig.setAddToParent(true);
		nextButtonConfig.setParentComponentId(parentId);
		GtnUIFrameWorkActionConfig nextAction = new GtnUIFrameWorkActionConfig();
		nextAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nextAction.addActionParameter(GtnFrameworkReportTabChangeAction.class.getName());
		nextAction.addActionParameter("tabSheetMain");
		nextAction.addActionParameter("2");		
		nextButtonConfig.addGtnUIFrameWorkActionConfig(nextAction);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButtonConfig");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(true);
		closeButtonConfig.setParentComponentId(parentId);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
               GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkReportStringConstants.CONFIRMATION);
		alertParamsList.add("Are you sure you want to close this Report?");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		GtnUIFrameWorkActionConfig closeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeActionConfig.addActionParameter(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		onSucessActionConfigList.add(closeActionConfig);
		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		GtnUIFrameWorkActionConfig resetLandingScreenAction = new GtnUIFrameWorkActionConfig();
		resetLandingScreenAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetLandingScreenAction.setActionParameterList(getResetParameters());
		onSucessActionConfigList.add(resetLandingScreenAction);
		closeButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		
		componentList.add(previousButtonConfig);
		componentList.add(nextButtonConfig);
		componentList.add(closeButtonConfig);
	}
}
