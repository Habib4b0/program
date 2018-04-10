package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkDataAssumptionsTabConfig;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportDataSelectionTabConfig;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportingDashboardTabConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkReportGenerateLookUp {

	public GtnUIFrameworkViewConfig getGtnReportGenerateLookUpView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Report Generate Lookup View");
		view.setViewId(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		view.setDefaultView(false);
		view.setReplicable(true);
		addComponentList(view, namespace);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(false);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabLayout(componentList, namespace);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("projectionDetailsTabsheetLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabSheet(componentList, namespace);

	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId(GtnFrameworkCommonConstants.TAB_SHEET);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("projectionDetailsTabsheetLayout");
		tabSheetConfig.setSpacing(true);

		GtnUIFrameworkTabConfig dataSelection = new GtnUIFrameworkTabConfig();
		dataSelection
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dataSelectionRootLayout");
		dataSelection.setTabCaption("Data Selection");
		List<GtnUIFrameworkComponentConfig> dataSelectionTabConfigList = new ArrayList<>();
		new GtnFrameworkReportDataSelectionTabConfig().addDataSelectionTab(dataSelectionTabConfigList, "dataSelectionTab");
		dataSelection.setTabLayoutComponentConfigList(dataSelectionTabConfigList);

		GtnUIFrameworkTabConfig dataAssumptions = new GtnUIFrameworkTabConfig();
		dataAssumptions.setComponentId("dataAssumptionsLayout");
		dataAssumptions.setTabCaption("Data Assumptions");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList = new ArrayList<>();
		dataAssumptions.setTabLayoutComponentConfigList(dataAssumptionsTabConfigList);
		new GtnFrameworkDataAssumptionsTabConfig().addDataAssumptionsLayout(dataAssumptionsTabConfigList,"dataAssumptionsTab");

		GtnUIFrameworkTabConfig reportingDashboard = new GtnUIFrameworkTabConfig();
		reportingDashboard.setComponentId("reportingDashboardRootLayout");
		reportingDashboard.setTabCaption("Reporting Dashboard");
		List<GtnUIFrameworkComponentConfig> reportingDashboardTabConfigList = new ArrayList<>();
		reportingDashboard.setTabLayoutComponentConfigList(reportingDashboardTabConfigList);
		new GtnFrameworkReportingDashboardTabConfig().addReportingDashboardLayout(reportingDashboardTabConfigList,"reportingDashboardTab");

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(dataSelection);
		tabConfigList.add(dataAssumptions);
		tabConfigList.add(reportingDashboard);

		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		componentList.add(tabSheetConfig);
	}
}
