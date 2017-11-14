package com.stpl.gtn.gtn2o.ui.module.processscheduler.config;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworProcessSchedulerLayoutsConfig {

	private static GtnFrameworProcessSchedulerLayoutsConfig commonConfig = null;

	private GtnFrameworProcessSchedulerLayoutsConfig() {

	}

	public static synchronized GtnFrameworProcessSchedulerLayoutsConfig getInstance() {
		if (commonConfig == null) {
			commonConfig = new GtnFrameworProcessSchedulerLayoutsConfig();
		}
		return commonConfig;
	}

	public GtnUIFrameWorkActionConfig getUIFrameworkActionConfig(final GtnUIFrameworkActionType type) {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(type);
		return actionConfig;
	}

	public GtnUIFrameworkComponentConfig getUIFrameworkLayoutComponentConfig(String componentId, boolean hasparent,
			String parentComponentId, GtnUIFrameworkLayoutType layoutType, GtnUIFrameworkComponentType componentType) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = getUIFrameworkComponentConfig(componentId, hasparent,
				parentComponentId, componentType);
		gtnLayoutConfig.setGtnLayoutConfig(getUIFrameworkLayoutConfig(layoutType));
		return gtnLayoutConfig;
	}

	//
	public GtnUIFrameworkComponentConfig getCssLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = getUIFrameworkLayoutComponentConfig(componentId, hasparent,
				parentComponentId, GtnUIFrameworkLayoutType.CSS_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayoutConfig.setSpacing(Boolean.TRUE);
		return gtnLayoutConfig;
	}

	//
	public GtnUIFrameworkComponentConfig getUIFrameworkComponentConfig(String componentId, boolean hasparent,
			String parentComponentId, GtnUIFrameworkComponentType componentType) {
		GtnUIFrameworkComponentConfig gtnComponentConfig = new GtnUIFrameworkComponentConfig();
		gtnComponentConfig.setComponentType(componentType);
		gtnComponentConfig.setComponentId(componentId);
		gtnComponentConfig.setParentComponentId(parentComponentId);
		gtnComponentConfig.setAddToParent(hasparent);
		return gtnComponentConfig;
	}

	public GtnUIFrameworkLayoutConfig getUIFrameworkLayoutConfig(final GtnUIFrameworkLayoutType layoutType) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(layoutType);
		return layout;
	}

	//
	public GtnUIFrameworkComponentConfig getPanelConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = getUIFrameworkComponentConfig(componentId, hasparent,
				parentComponentId, GtnUIFrameworkComponentType.PANEL);
		gtnPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		return gtnPanelConfig;
	}

	public GtnUIFrameworkComponentConfig getVerticalLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		return getUIFrameworkLayoutComponentConfig(componentId, hasparent, parentComponentId,
				GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
	}

	public GtnUIFrameworkComponentConfig getHorizontalLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		return getUIFrameworkLayoutComponentConfig(componentId, hasparent, parentComponentId,
				GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
	}

}
