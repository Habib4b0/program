/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.GtnFrameworkRelationshipBuilderConstants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRelationShipBuilderConfig {

	public GtnUIFrameworkRootConfig getRelationShipBuilderRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		viewList.add(new GtnFrameworkRelationshipBuilderSearchConfig().getSearchView());
		viewList.add(new GtnFrameworkRelationShipBuilderAddConfig()
				.getAddView(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD));
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
