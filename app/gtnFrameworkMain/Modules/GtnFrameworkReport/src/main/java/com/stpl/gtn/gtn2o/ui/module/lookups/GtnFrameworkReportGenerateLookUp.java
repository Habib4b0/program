package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionReGenerateAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkDataAssumptionsTabConfig;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportDataSelectionTabConfig;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportingDashboardTabConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkReportGenerateLookUp {

	public GtnUIFrameworkViewConfig getGtnReportGenerateLookUpView(String namespace) {

		GtnUIFrameworkViewConfig reportGenerateLookupVew = new GtnUIFrameworkViewConfig();
		reportGenerateLookupVew.setViewName(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD);
		reportGenerateLookupVew.setViewId(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		reportGenerateLookupVew.setDefaultView(false);
		reportGenerateLookupVew.setReplicable(true);
		addComponentList(reportGenerateLookupVew, namespace);
		reportGenerateLookupVew.addViewAction(getDataSelectionTabAction());
		return reportGenerateLookupVew;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String reportGenerateLookupNmespace) {

		List<GtnUIFrameworkComponentConfig> reportGenerateLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(reportGenerateLookupComponentList);

		GtnUIFrameworkComponentConfig reportGenerateLookupLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportGenerateLookupLayoutConfig.setComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		reportGenerateLookupLayoutConfig.setComponentWidth("100%");
		reportGenerateLookupLayoutConfig.setAddToParent(false);
		reportGenerateLookupLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportGenerateLookupLayoutConfig.setSpacing(true);
		reportGenerateLookupLayoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig reportGenerateLookupLayout = new GtnUIFrameworkLayoutConfig();
		reportGenerateLookupLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportGenerateLookupLayoutConfig.setGtnLayoutConfig(reportGenerateLookupLayout);
		reportGenerateLookupComponentList.add(reportGenerateLookupLayoutConfig);

		addTabLayout(reportGenerateLookupComponentList, reportGenerateLookupNmespace);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig reportGenerateLookupLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportGenerateLookupLayoutConfig.setComponentId("projectionDetailsTabsheetLayout");
		reportGenerateLookupLayoutConfig.setComponentWidth("100%");
		reportGenerateLookupLayoutConfig.setAddToParent(true);
		reportGenerateLookupLayoutConfig.setParentComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		reportGenerateLookupLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportGenerateLookupLayoutConfig.setSpacing(true);
		reportGenerateLookupLayoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		reportGenerateLookupLayoutConfig.setGtnLayoutConfig(layout);
		componentList.add(reportGenerateLookupLayoutConfig);

		addTabSheet(componentList, namespace);

	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId(GtnFrameworkCommonConstants.TAB_SHEET+"Main");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setComponentHight("1010px");
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("projectionDetailsTabsheetLayout");
		tabSheetConfig.setSpacing(true);

		GtnUIFrameworkTabConfig dataSelection = new GtnUIFrameworkTabConfig();
		dataSelection
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dataSelectionRootLayout");
		dataSelection.setTabCaption("Data Selection");
                dataSelection.setTabIndex(0);
		List<GtnUIFrameworkComponentConfig> dataSelectionTabConfigList = new ArrayList<>();
		new GtnFrameworkReportDataSelectionTabConfig().addDataSelectionTab(dataSelectionTabConfigList,
				"dataSelectionTab");
		dataSelection.setTabLayoutComponentConfigList(dataSelectionTabConfigList);

		GtnUIFrameworkTabConfig dataAssumptions = new GtnUIFrameworkTabConfig();
		dataAssumptions.setComponentId("dataAssumptionsLayout");
		dataAssumptions.setTabCaption("Data Assumptions");
                dataAssumptions.setTabIndex(1);
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList = new ArrayList<>();
		dataAssumptions.setTabLayoutComponentConfigList(dataAssumptionsTabConfigList);
		new GtnFrameworkDataAssumptionsTabConfig().addDataAssumptionsLayout(dataAssumptionsTabConfigList,
				"dataAssumptionsTab");

		GtnUIFrameworkTabConfig reportingDashboard = new GtnUIFrameworkTabConfig();
		reportingDashboard.setComponentId("reportingDashboardRootLayout");
		reportingDashboard.setTabCaption("Reporting Dashboard");
                reportingDashboard.setTabIndex(2);
		List<GtnUIFrameworkComponentConfig> reportingDashboardTabConfigList = new ArrayList<>();
		reportingDashboard.setTabLayoutComponentConfigList(reportingDashboardTabConfigList);
		new GtnFrameworkReportingDashboardTabConfig().addReportingDashboardLayout(reportingDashboardTabConfigList,
				"reportingDashboardTab");

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(dataSelection);
		tabConfigList.add(dataAssumptions);
		tabConfigList.add(reportingDashboard);

		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);
		componentList.add(tabSheetConfig);
		
		GtnUIFrameWorkActionConfig regenerateAction = new GtnUIFrameWorkActionConfig();
		regenerateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		regenerateAction.addActionParameter(GtnReportDataSelectionReGenerateAction.class.getName());
		tabSheetConfig.addGtnUIFrameWorkActionConfig(regenerateAction);
	}

	private GtnUIFrameWorkActionConfig getDataSelectionTabAction() {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkDataSelectionTabAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkDataSelectionTabAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkDataSelectionTabAction.addActionParameter(GtnReportDataSelectionTabLoadAction.class.getName());
		gtnUIFrameWorkDataSelectionTabAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		return gtnUIFrameWorkDataSelectionTabAction;
	}
}
