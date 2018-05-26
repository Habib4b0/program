package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkDataAssumptionsTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addDataAssumptionsLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		addTabLayout(componentList, nameSpace);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_LAYOUT, false, null);
		layoutConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(layoutConfig);

		addTabSheet(componentList, nameSpace);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.TAB_SHEET, true,
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
		addCurrentTab(currentTabTabConfigList, nameSpace);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(currentTab);

		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addCurrentTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkReportStringConstants.CURRENT_TAB, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);
		addDataAssumptionsPagedTableComponent(componentList, gtnLayout.getComponentId());

		GtnUIFrameworkComponentConfig currentTabNavigationButtonLayout = configProvider.getHorizontalLayoutConfig(
				"currentTabNavigationButtonLayout", true, GtnFrameworkReportStringConstants.CURRENT_TAB);
		componentList.add(currentTabNavigationButtonLayout);
		addNavigationButtonLayout(componentList, nameSpace + GtnFrameworkReportStringConstants.CURRENT_TAB,
				currentTabNavigationButtonLayout.getComponentId());
	}

	private void addDataAssumptionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig dataAssumptionsPagedTableComponent = configProvider.getUIFrameworkComponentConfig(
				"dataAssumptionsPagedTableComponent", true, parentId, GtnUIFrameworkComponentType.PAGED_GRID);
		dataAssumptionsPagedTableComponent.setAuthorizationIncluded(true);
		dataAssumptionsPagedTableComponent.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		dataAssumptionsPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		dataAssumptionsPagedTableComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(dataAssumptionsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig dataAssumptionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();

		dataAssumptionsPagedTableConfig.setColumnHeaders(Arrays.asList(new String[] { "File", "Company",
				"Business Unit", "Type", "Version", "Active From", "From Period", "To Period" }));
		dataAssumptionsPagedTableConfig.setTableColumnMappingId(new Object[] { "file", "company", "businessUnit",
				"type", "version", "activeFrom", "fromPeriod", "toPeriod" });
		dataAssumptionsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE });
		dataAssumptionsPagedTableConfig.setCountUrl("/gtnReport/gtnWsReportLoadDataAssumptions");
		dataAssumptionsPagedTableConfig.setResultSetUrl("/gtnReport/gtnWsReportLoadDataAssumptions");
		dataAssumptionsPagedTableConfig.setRefreshAtStart(true);
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
				GtnUIFrameworkComponentType.CALENDAR_FIELD, GtnUIFrameworkComponentType.CALENDAR_FIELD,
				GtnUIFrameworkComponentType.CALENDAR_FIELD };

		String[] dataAssumptionsComboboxIds = { "" };
		String[] dataAssumptionsComboBoxType = { "" };
		int comboboxStartIndex = 0;
		for (int i = 0; i < dataAssumptionsPropertyIDs.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig dataAssumptionsTableFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			dataAssumptionsTableFilterConfig.setPropertId(dataAssumptionsPropertyIDs[i]);
			dataAssumptionsTableFilterConfig.setGtnComponentType(componentType[i]);
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

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace,
			String parentId) {

		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "previousButtonConfig");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(true);
		previousButtonConfig.setParentComponentId(parentId);

		GtnUIFrameworkComponentConfig nextButtonConfig = new GtnUIFrameworkComponentConfig();
		nextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButtonConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "nextButtonConfig");
		nextButtonConfig.setComponentName("NEXT");
		nextButtonConfig.setAddToParent(true);
		nextButtonConfig.setParentComponentId(parentId);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButtonConfig");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(true);
		closeButtonConfig.setParentComponentId(parentId);

		componentList.add(previousButtonConfig);
		componentList.add(nextButtonConfig);
		componentList.add(closeButtonConfig);
	}
}
