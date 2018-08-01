package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.chart.GtnFrameworkGridToBarChartAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportChartLookup {
	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getReportChartViewConfig() {
		GtnUIFrameworkViewConfig chartLookupRootView = configProvider.getViewConfig("Chart Lookup",
				GtnFrameworkReportStringConstants.REPORT_CHART_LOOKUP_VIEW, false);
		chartLookupRootView.setResetAllowed(true);
		addComponentList(chartLookupRootView);
		return chartLookupRootView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig chartLookupRootView) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		chartLookupRootView.setGtnComponentList(componentList);
		addChart(componentList, GtnFrameworkReportStringConstants.REPORT_CHART_LOOKUP_VIEW);
	}

	private void addChart(List<GtnUIFrameworkComponentConfig> componentList, String reportChartLookupView) {
		GtnUIFrameworkComponentConfig chartTypeLayoutConfig = configProvider.getHorizontalLayoutConfig(
				reportChartLookupView + GtnFrameworkReportStringConstants.UNDERSCORE + "chartTypeLayout", false, null);
		chartTypeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		chartTypeLayoutConfig.addComponentStyle("stpl-margin-top-15");
		chartTypeLayoutConfig.addComponentStyle("stpl-margin-bottom-15");
		chartTypeLayoutConfig.setComponentWidth("100%");
		componentList.add(chartTypeLayoutConfig);

		GtnUIFrameworkComponentConfig chart = new GtnUIFrameworkComponentConfig();
		chart.setComponentType(GtnUIFrameworkComponentType.BAR_CHART);
		chart.setComponentName("Chart");
		chart.setComponentId(reportChartLookupView + GtnFrameworkReportStringConstants.UNDERSCORE + "barChart");
		chart.setAddToParent(true);
		chart.setParentComponentId(
				reportChartLookupView + GtnFrameworkReportStringConstants.UNDERSCORE + "chartTypeLayout");
		chart.setDefaultFocus(true);
		componentList.add(chart);
		GtnUIFrameWorkActionConfig chartLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		chartLoadAction.addActionParameter(GtnFrameworkGridToBarChartAction.class.getName());
		chartLoadAction.addActionParameter("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);
		chart.addGtnUIFrameWorkActionConfig(chartLoadAction);
	}
}
