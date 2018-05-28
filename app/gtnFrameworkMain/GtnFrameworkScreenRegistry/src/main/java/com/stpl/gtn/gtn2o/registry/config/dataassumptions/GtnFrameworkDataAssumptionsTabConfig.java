package com.stpl.gtn.gtn2o.registry.config.dataassumptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.common.UpdatePreviousNextCloseSubmitButton;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkDataAssumptionsTabConfig {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDataAssumptionsTabConfig.class);
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addDataAssumptionsTabComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addDataAssumptionsRootLayout(componentList, nameSpace);
	}

	private void addDataAssumptionsRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig dataAssumptionsRootLayout = new GtnUIFrameworkComponentConfig();
		dataAssumptionsRootLayout.setComponentId(nameSpace + "_" + "rootLayout");
		dataAssumptionsRootLayout.setComponentWidth("100%");
		dataAssumptionsRootLayout.setAddToParent(false);
		dataAssumptionsRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataAssumptionsRootLayout.setSpacing(true);
		dataAssumptionsRootLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		dataAssumptionsRootLayout.setGtnLayoutConfig(layout);
		componentList.add(dataAssumptionsRootLayout);

		addDataAssumptionsPanel(componentList, dataAssumptionsRootLayout.getComponentId(), nameSpace);
		addDataAssumptionsButtonLayout(componentList, dataAssumptionsRootLayout.getComponentId(), nameSpace);

	}

	private void addDataAssumptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig dataAssumptionsPanel = new GtnUIFrameworkComponentConfig();
		dataAssumptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		dataAssumptionsPanel.setComponentName("File Information");
		dataAssumptionsPanel.setComponentId(nameSpace + "_" + "fileInformation");
		dataAssumptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dataAssumptionsPanel.setAddToParent(Boolean.TRUE);
		dataAssumptionsPanel.setParentComponentId(parentComponentId);

		dataAssumptionsPanel.setSpacing(true);
		componentList.add(dataAssumptionsPanel);
		
		addDataAssumptionsPanelLayout(componentList, dataAssumptionsPanel.getComponentId(), nameSpace);
	}

	private void addDataAssumptionsPanelLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		gtnLogger.info("started executing addDataAssumptionsPanelLayout()");
		GtnUIFrameworkComponentConfig dataAssumptionsPanelLayout = new GtnUIFrameworkComponentConfig();
		dataAssumptionsPanelLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataAssumptionsPanelLayout.setAuthorizationIncluded(true);
		dataAssumptionsPanelLayout.setComponentId(nameSpace + "_" + "panelLayout");
		dataAssumptionsPanelLayout.setAddToParent(Boolean.TRUE);
		dataAssumptionsPanelLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		layoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		dataAssumptionsPanelLayout.setGtnLayoutConfig(layoutConfig);
		dataAssumptionsPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(dataAssumptionsPanelLayout);

		addDataAssumptionsPagedTableComponent(componentList, dataAssumptionsPanelLayout.getComponentId(), nameSpace);

		addDataAssumptionsExcelButtonComponent(componentList, dataAssumptionsPanelLayout.getComponentId(), nameSpace);
		
	}

	private void addDataAssumptionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig dataAssumptionsResultsTable = new GtnUIFrameworkComponentConfig();
		dataAssumptionsResultsTable.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		dataAssumptionsResultsTable.setAuthorizationIncluded(true);
		dataAssumptionsResultsTable.setComponentName("Data Assumptions Paged Table");
		dataAssumptionsResultsTable.setComponentId(nameSpace + "_" + "resultsTable");
		dataAssumptionsResultsTable.setAddToParent(Boolean.TRUE);
		dataAssumptionsResultsTable.setParentComponentId(parentComponentId);
		dataAssumptionsResultsTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		dataAssumptionsResultsTable.setComponentStyle(tableStyle);
		
		componentList.add(dataAssumptionsResultsTable);

		GtnUIFrameworkPagedTableConfig dataAssumptionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		dataAssumptionsPagedTableConfig.setEditable(false);
		dataAssumptionsPagedTableConfig.setFilterBar(true);
		dataAssumptionsPagedTableConfig.setSelectable(true);
		dataAssumptionsPagedTableConfig.setItemPerPage(5);
		dataAssumptionsPagedTableConfig.setPageLength(5);
		dataAssumptionsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		dataAssumptionsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE });
		dataAssumptionsPagedTableConfig.setColumnHeaders(Arrays.asList( "File", "Company", "Business Unit", "Type",
				"Version", "Active From", "From Period", "To Period" ));
		dataAssumptionsPagedTableConfig.setTableColumnMappingId(new Object[] { "file", "company", "businessUnit",
				"type", "version", "activeFrom", "fromPeriod", "toPeriod" });
		dataAssumptionsPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		dataAssumptionsResultsTable.setGtnPagedTableConfig(dataAssumptionsPagedTableConfig);
	}
	
	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = {"file", "company", "businessUnit",
				"type", "version", "activeFrom", "fromPeriod", "toPeriod"};
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8
				,GtnUIFrameworkComponentType.CALENDAR_FIELD,GtnUIFrameworkComponentType.CALENDAR_FIELD,  GtnUIFrameworkComponentType.CALENDAR_FIELD};
		
		for (int i = 0; i < propertyIds.length; i++) {
			
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfig.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfig.setGtnComponentType(componentType[i]);
			customFilterConfigMap.put(pagedTableCustomFilterConfig.getPropertId(), pagedTableCustomFilterConfig);
			
		}
		return customFilterConfigMap;
	}

	private void addDataAssumptionsExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		gtnLogger.info("started executing addDataAssumptionsExcelButtonComponent()");

		GtnUIFrameworkComponentConfig excelBtnLayout = configProvider.getHorizontalLayoutConfig(nameSpace + "_" + "excelButtonlayout", true, parentComponentId);
		componentList.add(excelBtnLayout);

		GtnUIFrameworkComponentConfig excelButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "excelButton", true, excelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButton.setAuthorizationIncluded(true);
		componentList.add(excelButton);

	}

	private void addDataAssumptionsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		UpdatePreviousNextCloseSubmitButton buttonLayout=new UpdatePreviousNextCloseSubmitButton();
		buttonLayout.addCommonButtonLayout(componentList,  parentComponentId, nameSpace);
		buttonLayout.addUpdateButton(componentList, nameSpace);
		buttonLayout.addPreviousButton(componentList, nameSpace);
		buttonLayout.addNextButton(componentList, nameSpace);
		buttonLayout.addCloseButton(componentList, nameSpace);
		buttonLayout.addSubmitButton(componentList, nameSpace);
		
	}

	
}
