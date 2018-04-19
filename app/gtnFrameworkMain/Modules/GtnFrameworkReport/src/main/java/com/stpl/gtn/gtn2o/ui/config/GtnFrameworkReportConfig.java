package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportPrivateViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportProductHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportGenerateLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportProfileLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDashboardSaveProfileViewLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportComparisonLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportComparisonOptionsLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportPublicViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportDataSelectionSaveViewLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportVariableBreakdownLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportCustomViewLookup;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkReportCustomertHierarchyLookUp;

public class GtnFrameworkReportConfig {
	public GtnUIFrameworkRootConfig getGtnReportRootConfig(){

		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();

		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();

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

		viewList.add(new GtnFrameworkReportGenerateLookUp().getGtnReportGenerateLookUpView(
				GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP));

		viewList.add(new GtnFrameworkReportComparisonLookup().getReportComparisonLookupView());

		viewList.add(new GtnFrameworkReportProfileLookUp()
				.getReportProfileLookupView(GtnFrameworkReportStringConstants.REPORT_PROFILE_LOOKUP));

		viewList.add(new GtnFrameworkReportCustomViewLookup()
				.getCustomViewLookUpView(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP));

		viewList.add(new GtnFrameworkReportCustomertHierarchyLookUp()
				.getCustHierarchyLookUpView(GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_LOOKUP));

		viewList.add(new GtnFrameworkReportVariableBreakdownLookup()
				.getVariableBreakdownLookUpView(GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB));

		viewList.add(new GtnFrameworkReportComparisonOptionsLookup().getComparisonOptionsLookUpView(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS));

		viewList.add(new GtnFrameworkReportDashboardSaveProfileViewLookUp()
				.getSaveViewLookUpView(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_SAVE_PROFILE));

		rootConfig.setGtnViewConfigList(viewList);

		return rootConfig;
	}
}
