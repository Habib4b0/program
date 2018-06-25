package com.stpl.gtn.gtn2o.ui.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportLayoutsConfig {
	public GtnUIFrameworkComponentConfig getHorizontalLayoutConfig(String compId, String parentLayout) {
		GtnUIFrameworkComponentConfig profileOptionLayoutConf = new GtnUIFrameworkComponentConfig();
		profileOptionLayoutConf.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		profileOptionLayoutConf.setComponentId(compId + GtnFrameworkCssConstants.HORIZONTAL);
		profileOptionLayoutConf.setComponentName(compId + GtnFrameworkCssConstants.HORIZONTAL);
		profileOptionLayoutConf.setParentComponentId(parentLayout);
		profileOptionLayoutConf.setAddToParent(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		profileOptionLayoutConf.setGtnLayoutConfig(conf);
		return profileOptionLayoutConf;
	}

	public GtnUIFrameworkComponentConfig getPanelConfig(String compId, String parentLayout) {
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId(compId + "Panel");
		searchCriteriaPanel.setParentComponentId(parentLayout);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		return searchCriteriaPanel;
	}

	public GtnUIFrameworkComponentConfig getVerticalLayoutConfig(String compId, String parentId) {
		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		vLayout.setComponentId(compId + "VerticalLayout");
		vLayout.setParentComponentId(parentId);
		vLayout.setAddToParent(true);
		vLayout.setSpacing(true);
		vLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setGtnLayoutConfig(conf);
		return vLayout;
	}

	public GtnUIFrameworkComponentConfig getCssLayoutConfig(String compId, String parentId) {
		GtnUIFrameworkLayoutConfig selectionCriteriaMainLayout = new GtnUIFrameworkLayoutConfig();
		selectionCriteriaMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig selectionCriteriaMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		selectionCriteriaMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		selectionCriteriaMainLayoutConfig.setComponentId(compId + "CssLayout");
		selectionCriteriaMainLayoutConfig.setAddToParent(true);
		selectionCriteriaMainLayoutConfig.setSpacing(true);
		selectionCriteriaMainLayoutConfig.setParentComponentId(parentId);
		selectionCriteriaMainLayoutConfig.setGtnLayoutConfig(selectionCriteriaMainLayout);
		return selectionCriteriaMainLayoutConfig;
	}

	public GtnUIFrameworkComponentConfig getRootLayoutConfig(String compId, GtnUIFrameworkLayoutType layoutType) {
		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		vLayout.setComponentId(compId);
		vLayout.setAddToParent(false);
		vLayout.setTabComponent(true);
		vLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(layoutType);
		vLayout.setGtnLayoutConfig(conf);
		return vLayout;
	}
}