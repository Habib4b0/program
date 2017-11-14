/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkCFPPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkDCPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkFormulaNoPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkIFPPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkNSFSearchConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkNSRPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkPSPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkRPPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkRSPoupupConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup.GtnFrameworkTPPoupupConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractDashboardRootConfig {

	public GtnUIFrameworkRootConfig getContractDashboardRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		GtnUIFrameworkViewConfig viewConfig = new GtnFrameworkContractDashboardProcessConfig().getProcessView();
		if (!viewConfig.isDefaultView()) {
			viewList.add(new GtnFrameworkContractDashboardMainConfig().getMainView());
		}
		viewList.add(viewConfig);
		viewList.add(new GtnFrameworkTPPoupupConfig().getView());
		viewList.add(new GtnFrameworkCFPPoupupConfig().getView());
		viewList.add(new GtnFrameworkIFPPoupupConfig().getView());
		viewList.add(new GtnFrameworkNSFSearchConfig().getView());
		viewList.add(new GtnFrameworkPSPoupupConfig().getView());
		viewList.add(new GtnFrameworkRSPoupupConfig().getView());
		viewList.add(new GtnFrameworkNSRPoupupConfig().getView());
		viewList.add(new GtnFrameworkFormulaNoPoupupConfig().getView());
		viewList.add(new GtnFrameworkRPPoupupConfig().getView());
		viewList.add(new GtnFrameworkDCPoupupConfig().getView());
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
