package com.stpl.gtn.gtn2o.ui.module.forecasting.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.config.lookups.ForecastGenerateLookup;
import com.stpl.gtn.gtn2o.registry.config.lookups.GtnFrameworkCustomerAndProductGroupLookup;
import com.stpl.gtn.gtn2o.registry.config.lookups.GtnFrameworkForecastCustomertHierarchyLookUp;
import com.stpl.gtn.gtn2o.registry.config.lookups.GtnFrameworkForecastProductHierarchyLookUp;
import com.stpl.gtn.gtn2o.registry.config.lookups.PrivatePublicViewLookup;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.forecasting.config.constants.GtnUIFrameworkCommercialForecastingConstants;

public class GtnFrameworkCommercialForecastingConfig {

	public GtnUIFrameworkRootConfig getForecastingRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkForecastingLandingScreenConfig().getSearchView());
		viewList.add(new ForecastGenerateLookup()
                        .getGtnForecastGenerateLookUpView(GtnUIFrameworkCommercialForecastingConstants.COMMERCIAL_FORECASTING));
		viewList.add(new GtnFrameworkForecastProductHierarchyLookUp()
                        .getProdHierarchyLookUpView(GtnUIFrameworkCommercialForecastingConstants.COMMERCIAL_FORECASTING));
		viewList.add(new GtnFrameworkForecastCustomertHierarchyLookUp()
                        .getCustHierarchyLookUpView(GtnUIFrameworkCommercialForecastingConstants.COMMERCIAL_FORECASTING));
		viewList.add(new PrivatePublicViewLookup()
                . getPrivateViewLookUpView(GtnUIFrameworkCommercialForecastingConstants.COMMERCIAL_FORECASTING));
		viewList.add(new GtnFrameworkCustomerAndProductGroupLookup()
                . getCustProdGroupLookUpView(GtnUIFrameworkCommercialForecastingConstants.CUSTOMER_GROUP));
		viewList.add(new GtnFrameworkCustomerAndProductGroupLookup()
                . getCustProdGroupLookUpView(GtnUIFrameworkCommercialForecastingConstants.PRODUCT_GROUP));
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
