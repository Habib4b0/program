package com.stpl.gtn.gtn2o.registry.config.dataassumptions;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
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
		dataAssumptionsRootLayout.setComponentId(nameSpace + "_" + "dataAssumptionsRootLayout");
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
		dataAssumptionsPanelLayout.setComponentId(nameSpace + "_" + "dataAssumptionsPanelLayout");
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
		dataAssumptionsResultsTable.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		dataAssumptionsResultsTable.setAuthorizationIncluded(true);
		dataAssumptionsResultsTable.setComponentName("Data Assumptions Paged Table"); /* Ask */
		dataAssumptionsResultsTable.setComponentId(nameSpace + "_" + "dataAssumptionsResultsTable");
		dataAssumptionsResultsTable.setAddToParent(Boolean.TRUE);
		dataAssumptionsResultsTable.setParentComponentId(parentComponentId);
		dataAssumptionsResultsTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

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
		dataAssumptionsPagedTableConfig.setTableVisibleHeader(new String[] { "File", "Company", "Business Unit", "Type",
				"Version", "Active From", "From Period", "To Period" });
		dataAssumptionsPagedTableConfig.setTableColumnMappingId(new Object[] { "file", "company", "businessUnit",
				"type", "version", "activeFrom", "fromPeriod", "toPeriod" });
		dataAssumptionsResultsTable.setGtnPagedTableConfig(dataAssumptionsPagedTableConfig);
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

	private void addDataAssumptionsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		gtnLogger.info("started executing addDataAssumptionsButtonLayout()");
		GtnUIFrameworkComponentConfig dataAssumptionsButtonLayout = new GtnUIFrameworkComponentConfig();
		dataAssumptionsButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataAssumptionsButtonLayout.setAuthorizationIncluded(true);
		dataAssumptionsButtonLayout.setComponentId(nameSpace + "_" + "dataAssumptionsButtonLayout");
		dataAssumptionsButtonLayout.setAddToParent(Boolean.TRUE);
		dataAssumptionsButtonLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		layoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		dataAssumptionsButtonLayout.setGtnLayoutConfig(layoutConfig);
		dataAssumptionsButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(dataAssumptionsButtonLayout);

		addDataAssumptionsButtonComponent(componentList, dataAssumptionsButtonLayout.getComponentId(), nameSpace);
	}

	private void addDataAssumptionsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		gtnLogger.info("started executing addDataAssumptionsButtonComponent()");
		GtnUIFrameworkComponentConfig updateButtonConfig = new GtnUIFrameworkComponentConfig();
		updateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButtonConfig.setComponentId(nameSpace + "_" + "updateButton");
		updateButtonConfig.setComponentName("UPDATE");
		updateButtonConfig.setAddToParent(Boolean.TRUE);
		updateButtonConfig.setParentComponentId(parentComponentId);

		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig.setComponentId(nameSpace + "_" + "previousButton");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(Boolean.TRUE);
		previousButtonConfig.setParentComponentId(parentComponentId);

		GtnUIFrameworkComponentConfig nextButtonConfig = new GtnUIFrameworkComponentConfig();
		nextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButtonConfig.setComponentId(nameSpace + "_" + "nextButton");
		nextButtonConfig.setComponentName("NEXT");
		nextButtonConfig.setAddToParent(Boolean.TRUE);
		nextButtonConfig.setParentComponentId(parentComponentId);
		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(nameSpace + "_" + "closeButton");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(Boolean.TRUE);
		closeButtonConfig.setParentComponentId(parentComponentId);

		GtnUIFrameworkComponentConfig submitButtonConfig = new GtnUIFrameworkComponentConfig();
		submitButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButtonConfig.setComponentId(nameSpace + "_" + "submitButton");
		submitButtonConfig.setComponentName("SUBMIT");
		submitButtonConfig.setAddToParent(Boolean.TRUE);
		submitButtonConfig.setParentComponentId(parentComponentId);

		componentList.add(updateButtonConfig);
		componentList.add(previousButtonConfig);
		componentList.add(nextButtonConfig);
		componentList.add(closeButtonConfig);
		componentList.add(submitButtonConfig);

	}
}
