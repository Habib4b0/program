package com.stpl.gtn.gtn2o.ui.module.commercial.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.dataselection.GtnFrameworkCommercialForecastingDataSelectionConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.customer.GtnFrameworkCommercialForecastingCustomerGroupLookUp;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.customer.GtnFrameworkCommercialForecastingCustomerGroupTabLookUp;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.customer.GtnFrameworkCommercialForecastingCustomerHierarchyLookUp;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.customer.GtnFrameworkCommercialForecastingCustomerHierarchyTabLookUp;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.privateview.GtnFrameworkCommercialForecastingPrivateViewLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product.GtnFrameworkCommercialForecastingProductGroupLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product.GtnFrameworkCommercialForecastingProductGroupTabLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product.GtnFrameworkCommercialForecastingProductHierarchyLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product.GtnFrameworkCommercialForecastingProductHierarchyTabLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.publicview.GtnFrameworkCommercialForecastingPublicViewLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.saveview.GtnFrameworkCommercialForecastingSaveViewLookup;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.GtnFrameworkCommercialForecastingTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.GtnFrameworkCustomViewPopup;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingConfig {

	public GtnUIFrameworkRootConfig getGtnCommercialForecastingRootConfig() throws GtnFrameworkGeneralException {

		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();

		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();

		viewList.add(new GtnFrameworkCommercialForecastingDataSelectionConfig().getSearchView());

		viewList.add(new GtnFrameworkCommercialForecastingProductHierarchyLookup().getProductHierarchyLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingProductGroupLookup().getProductGroupLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingCustomerGroupLookUp().getCustomerGroupLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingCustomerHierarchyLookUp().getCustHierarchyLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingPrivateViewLookup().getPrivateViewLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingPublicViewLookup().getPublicViewLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingSaveViewLookup().getSaveViewLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingProductHierarchyTabLookup().getProductHierarchyLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingProductGroupTabLookup().getProductGroupTabLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingTabConfig()
				.getGtnCommercialForecastingProjectionDetailsLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingCustomerHierarchyTabLookUp().getCustHierarchyLookUpView());

		viewList.add(new GtnFrameworkCommercialForecastingCustomerGroupTabLookUp().getCustomerGroupTabLookUpView());

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("salesProjectionTab"));

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("salesProjectionResultsTab"));

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("discountProjectionTab"));

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("ppaProjectionResultsTab"));

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("projectionResultsTab"));

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("projectVarianceTab"));

		viewList.add(new GtnFrameworkCustomViewPopup().getCustomViewLookUpView("discountProjectionResultsTab"));
		rootConfig.setGtnViewConfigList(viewList);

		return rootConfig;
	}

}
