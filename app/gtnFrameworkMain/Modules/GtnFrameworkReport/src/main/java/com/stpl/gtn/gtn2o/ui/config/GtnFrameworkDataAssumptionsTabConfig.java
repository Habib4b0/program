package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkDataAssumptionsTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addDataAssumptionsLayout(List<GtnUIFrameworkComponentConfig> componentList,String nameSpace) {

		addTabLayout(componentList,nameSpace);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList,String nameSpace) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DATA_ASSUMPTIONS_TAB_SHEET_LAYOUT, false, null);
		layoutConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(layoutConfig);

		addTabSheet(componentList,nameSpace);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList,String nameSpace) {
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
		addCurrentTab(currentTabTabConfigList,nameSpace);

		GtnUIFrameworkTabConfig subTab1 = new GtnUIFrameworkTabConfig();
		subTab1.setComponentId(GtnFrameworkReportStringConstants.SUB_TAB1);
		subTab1.setTabCaption("2016LE 1");
		List<GtnUIFrameworkComponentConfig> subTab1ConfigList = new ArrayList<>();
		subTab1.setTabLayoutComponentConfigList(subTab1ConfigList);
		addSubTab1(subTab1ConfigList,nameSpace);

		GtnUIFrameworkTabConfig subTab2 = new GtnUIFrameworkTabConfig();
		subTab2.setComponentId(GtnFrameworkReportStringConstants.SUB_TAB2);
		subTab2.setTabCaption("2016LE 2");
		List<GtnUIFrameworkComponentConfig> subTab2TabConfigList = new ArrayList<>();
		subTab2.setTabLayoutComponentConfigList(subTab2TabConfigList);
		addSubTab2(subTab2TabConfigList,nameSpace);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(currentTab);
		tabConfigList.add(subTab1);
		tabConfigList.add(subTab2);
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
		addNavigationButtonLayout(componentList, nameSpace+GtnFrameworkReportStringConstants.CURRENT_TAB,
				currentTabNavigationButtonLayout.getComponentId());
	}

	private void addDataAssumptionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig dataAssumptionsPagedTableComponent = configProvider.getUIFrameworkComponentConfig(
				"dataAssumptionsPagedTableComponent", true, parentId, GtnUIFrameworkComponentType.PAGEDTABLE);
		dataAssumptionsPagedTableComponent.setAuthorizationIncluded(true);
		dataAssumptionsPagedTableComponent.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		dataAssumptionsPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(dataAssumptionsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig dataAssumptionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		dataAssumptionsPagedTableConfig.setEditable(false);
		dataAssumptionsPagedTableConfig.setFilterBar(true);
		dataAssumptionsPagedTableConfig.setSelectable(true);
		dataAssumptionsPagedTableConfig.setItemPerPage(5);
		dataAssumptionsPagedTableConfig.setPageLength(5);
		dataAssumptionsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		dataAssumptionsPagedTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING,GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE,GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING});
		dataAssumptionsPagedTableConfig
				.setTableVisibleHeader(new String[] { "File", "Company", "Business Unit", "Type","Version","Active From","Active To","Active File?" });
		dataAssumptionsPagedTableConfig.setTableColumnMappingId(
				new Object[] {"file", "company", "businessUnit", "type","version","activeFrom","activeTo","activeFile?"});
		
		dataAssumptionsPagedTableComponent.setGtnPagedTableConfig(dataAssumptionsPagedTableConfig);

	}

	private void addSubTab1(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkReportStringConstants.SUB_TAB1, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);
		addDataAssumptionsPagedTableComponent(componentList, gtnLayout.getComponentId());

		GtnUIFrameworkComponentConfig subTab1NavigationButtonLayout = configProvider.getHorizontalLayoutConfig(
				"subTab1NavigationButtonLayout", true, GtnFrameworkReportStringConstants.SUB_TAB1);
		componentList.add(subTab1NavigationButtonLayout);
		addNavigationButtonLayout(componentList, nameSpace+GtnFrameworkReportStringConstants.SUB_TAB1,
				subTab1NavigationButtonLayout.getComponentId());
	}

	private void addSubTab2(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkReportStringConstants.SUB_TAB2, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);
		addDataAssumptionsPagedTableComponent(componentList, gtnLayout.getComponentId());

		GtnUIFrameworkComponentConfig subTab2NavigationButtonLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace+"subTab2NavigationButtonLayout", true, GtnFrameworkReportStringConstants.SUB_TAB2);
		componentList.add(subTab2NavigationButtonLayout);
		addNavigationButtonLayout(componentList, GtnFrameworkReportStringConstants.SUB_TAB2,
				subTab2NavigationButtonLayout.getComponentId());
	}

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace,
			String parentId) {

		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig.setComponentId(namespace +GtnFrameworkReportStringConstants.UNDERSCORE + "previousButtonConfig");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(true);
		previousButtonConfig.setParentComponentId(parentId);

		GtnUIFrameworkComponentConfig nextButtonConfig = new GtnUIFrameworkComponentConfig();
		nextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButtonConfig.setComponentId(namespace +GtnFrameworkReportStringConstants.UNDERSCORE + "nextButtonConfig");
		nextButtonConfig.setComponentName("NEXT");
		nextButtonConfig.setAddToParent(true);
		nextButtonConfig.setParentComponentId(parentId);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(namespace +GtnFrameworkReportStringConstants.UNDERSCORE + "closeButtonConfig");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(true);
		closeButtonConfig.setParentComponentId(parentId);

		componentList.add(previousButtonConfig);
		componentList.add(nextButtonConfig);
		componentList.add(closeButtonConfig);
	}
}
