package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportChartLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportComparisonLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportComparisonOptionsLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportCustomViewLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportCustomertHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDSCustomertHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDSProductHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDashboardComparisonLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDashboardSaveProfileViewLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDataSelectionComparisonLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDataSelectionPrivateViewLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDataSelectionPublicViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDataSelectionSaveViewLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportGenerateLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportPrivateViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportProductHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportProfileLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportPublicViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportVariableBreakdownLookup;

public class GtnFrameworkReportConfig {
	public GtnUIFrameworkRootConfig getGtnReportRootConfig() {

		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();

		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>(25);

		viewList.add(new GtnFrameworkReportLandingScreenConfig()
				.getLandingScreenView(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN));

		viewList.add(new GtnFrameworkReportProductHierarchyLookUp()
				.getProdHierarchyLookUpView(GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP));

		viewList.add(new GtnFrameworkReportPublicViewSearchLookUp()
				.getPublicViewLookUpView(GtnFrameworkReportStringConstants.REPORT_PUBLIC_VIEW_SEARCH_LOOKUP));

		viewList.add(new GtnFrameworkReportPrivateViewSearchLookUp()
				.getPrivateViewLookUpView(GtnFrameworkReportStringConstants.REPORT_PRIVATE_VIEW_SEARCH_LOOKUP));

		viewList.add(new GtnFrameworkReportDataSelectionSaveViewLookUp()
				.getSaveViewLookUpView(GtnFrameworkReportStringConstants.REPORT_SAVE_VIEW_LOOKUP));

		viewList.add(new GtnFrameworkReportGenerateLookUp()
				.getGtnReportGenerateLookUpView(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP));

		viewList.add(new GtnFrameworkReportComparisonLookup().getReportComparisonLookupView());

		viewList.add(new GtnFrameworkReportProfileLookUp()
				.getReportProfileLookupView(GtnFrameworkReportStringConstants.REPORT_PROFILE_LOOKUP));

		viewList.add(new GtnFrameworkReportCustomViewLookup()
				.getCustomViewLookUpViewLandingScreen(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP));

		viewList.add(new GtnFrameworkReportCustomViewLookup()
				.getCustomViewLookUpViewDataSelection(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_DS));

		viewList.add(new GtnFrameworkReportCustomViewLookup().getCustomViewLookUpViewReportDashboard(
				GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_RD));

		viewList.add(new GtnFrameworkReportCustomertHierarchyLookUp()
				.getCustHierarchyLookUpView(GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_LOOKUP));

		viewList.add(new GtnFrameworkReportDSCustomertHierarchyLookUp()
				.getCustHierarchyLookUpView(GtnFrameworkReportStringConstants.REPORT_DS_CUSTOMER_HIERARCHY_LOOKUP));

		viewList.add(new GtnFrameworkReportDSProductHierarchyLookUp()
				.getProdHierarchyLookUpView(GtnFrameworkReportStringConstants.REPORT_DS_PRODUCT_HIERARCHY_LOOKUP));

		viewList.add(new GtnFrameworkReportVariableBreakdownLookup()
				.getVariableBreakdownLookUpView(GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB));

		viewList.add(new GtnFrameworkReportComparisonOptionsLookup().getComparisonOptionsLookUpView(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS));

		viewList.add(new GtnFrameworkReportDashboardSaveProfileViewLookUp()
				.getSaveViewLookUpView(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_SAVE_PROFILE));

		viewList.add(new GtnFrameworkReportDashboardComparisonLookup().getReportComparisonLookupView());

		viewList.add(new GtnFrameworkReportDataSelectionComparisonLookup().getReportDSComparisonLookupView());

		viewList.add(new GtnFrameworkReportDataSelectionPrivateViewLookUp()
				.getDsPrivateViewLookUpView(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW));

		viewList.add(new GtnFrameworkReportDataSelectionPublicViewSearchLookUp()
				.getPublicViewLookUpView(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW));

		viewList.add(new GtnFrameworkReportChartLookup().getReportChartViewConfig());

		rootConfig.setGtnViewConfigList(viewList);

		return rootConfig;
	}
}
