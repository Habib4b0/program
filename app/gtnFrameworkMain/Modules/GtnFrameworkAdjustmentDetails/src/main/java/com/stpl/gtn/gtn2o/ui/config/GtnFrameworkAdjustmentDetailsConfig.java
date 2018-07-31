/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config;

import com.stpl.gtn.gtn2o.ui.config.searchConfig.GtnFrameworkAdjustmentDetailsSearchConfig;
import com.stpl.gtn.gtn2o.ui.config.popups.GtnFrameworkAdjustmentDetailsSaveViewPopupConfig;
import com.stpl.gtn.gtn2o.ui.config.popups.redemptionperiod.GtnFrameworkRedemptionPeriodLookup;
import com.stpl.gtn.gtn2o.ui.config.popups.privatePublicView.GtnFrameworkPrivatePublicViewLookup;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsConfig {

    public GtnUIFrameworkRootConfig getItemMasterRootConfig() {
        GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
        List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
        viewList.add(new GtnFrameworkAdjustmentDetailsSearchConfig().getSearchView());
        viewList.add(new GtnFrameworkAdjustmentDetailsSaveViewPopupConfig().getSaveView());
        viewList.add(new GtnFrameworkPrivatePublicViewLookup().getPrivateView());
        viewList.add(new GtnFrameworkRedemptionPeriodLookup().getRedemptionPeriod());
        rootConfig.setGtnViewConfigList(viewList);
        return rootConfig;
    }
}
