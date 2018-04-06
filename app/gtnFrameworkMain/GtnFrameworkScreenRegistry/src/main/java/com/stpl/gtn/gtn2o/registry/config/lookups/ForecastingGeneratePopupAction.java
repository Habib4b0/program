package com.stpl.gtn.gtn2o.registry.config.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.config.GtnFrameworkDataAssumptionsTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;

public class ForecastingGeneratePopupAction {
	public GtnUIFrameworkViewConfig getGtnReportGenerateLookUpView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Forecasting Generate Lookup View");
		view.setViewId("Forecasting Generate Lookup View");
		view.setDefaultView(false);
		view.setReplicable(true);
		addComponentList(view, namespace);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("forecastingTabsheetMainLayout");
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
		layoutConfig.setComponentId("forecastingTabsheetLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId("forecastingTabsheetMainLayout");
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
		tabSheetConfig.setComponentId("tabSheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("forecastingTabsheetLayout");
		tabSheetConfig.setSpacing(true);

		GtnUIFrameworkTabConfig dataAssumptions = new GtnUIFrameworkTabConfig();
		dataAssumptions.setComponentId("dataAssumptionsLayout");
		dataAssumptions.setTabCaption("Data Assumptions");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList = new ArrayList<>();
		dataAssumptions.setTabLayoutComponentConfigList(dataAssumptionsTabConfigList);
		new GtnFrameworkDataAssumptionsTabConfig().addDataAssumptionsLayout(dataAssumptionsTabConfigList,"dataAssumptionsTab");

		List<GtnUIFrameworkTabConfig> tabList = new ArrayList<>();
		tabList.add(dataAssumptions);

		tabSheetConfig.setGtnTabSheetConfigList(tabList);

		componentList.add(tabSheetConfig);

	}

}
