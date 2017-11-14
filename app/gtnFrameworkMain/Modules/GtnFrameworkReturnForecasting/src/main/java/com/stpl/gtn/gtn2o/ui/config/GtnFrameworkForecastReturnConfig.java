package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.dataselection.GtnFrameworkForecastReturnDataSelectionConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkForecastReturnPrivateViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkForecastReturnProductGroupLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkForecastReturnProductHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkForecastReturnProjectionDetailsLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkForecastReturnPublicViewSearchLookUp;
import com.stpl.gtn.gtn2o.ui.module.lookups.GtnFrameworkForecastReturnSaveViewLookUp;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkForecastReturnConfig {

	public GtnUIFrameworkRootConfig getGtnForecastReturnRootConfig() throws GtnFrameworkGeneralException {

		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();

		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();

		viewList.add(new GtnFrameworkForecastReturnDataSelectionConfig()
				.getSearchView(GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		viewList.add(new GtnFrameworkForecastReturnProductHierarchyLookUp()
				.getProdHierarchyLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		viewList.add(new GtnFrameworkForecastReturnPublicViewSearchLookUp()
				.getPublicViewLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		viewList.add(new GtnFrameworkForecastReturnPrivateViewSearchLookUp()
				.getPrivateViewLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		viewList.add(new GtnFrameworkForecastReturnProductGroupLookUp()
				.getProductGroupLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		viewList.add(new GtnFrameworkForecastReturnSaveViewLookUp()
				.getSaveViewLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		viewList.add(new GtnFrameworkForecastReturnProductHierarchyLookUp()
				.getProdHierarchyLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET));

		viewList.add(new GtnFrameworkForecastReturnProductGroupLookUp()
				.getProductGroupLookUpView(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET));

		viewList.add(new GtnFrameworkForecastReturnProjectionDetailsLookUp()
				.getGtnForecastingReturnProjectionDetailsLookUpView(
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION));

		rootConfig.setGtnViewConfigList(viewList);

		return rootConfig;
	}

	public GtnUIFrameworkViewConfig getGtnForecastReturnRootConfigForWorkflow() throws GtnFrameworkGeneralException {
		GtnUIFrameworkViewConfig viewConfig = new GtnFrameworkForecastReturnProjectionDetailsLookUp()
				.getGtnForecastingReturnProjectionDetailsLookUpView(
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_MAIN_SCREEN_DATA_SELECTION);
		viewConfig.setReplicable(false);
		return viewConfig;

	}
}
