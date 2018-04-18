package com.stpl.gtn.gtn2o.registry.config.common;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GenerateResetButton {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	public void addGenerateResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		
		GtnUIFrameworkComponentConfig generateResetButtonButtonLayout = new GtnUIFrameworkComponentConfig();
		generateResetButtonButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		generateResetButtonButtonLayout.setAuthorizationIncluded(true);
		generateResetButtonButtonLayout.setComponentId(nameSpace + "_" + "generateResetButtonButtonLayout");
		generateResetButtonButtonLayout.setAddToParent(Boolean.TRUE);
		generateResetButtonButtonLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		layoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		generateResetButtonButtonLayout.setGtnLayoutConfig(layoutConfig);
		generateResetButtonButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(generateResetButtonButtonLayout);

		addGenerateResetButtonButton(componentList, generateResetButtonButtonLayout.getComponentId(), nameSpace);
	}

	private void addGenerateResetButtonButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {		
		GtnUIFrameworkComponentConfig generateButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "generateButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		generateButton.setComponentName("GENERATE");

		GtnUIFrameworkComponentConfig resetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "resetButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentName("RESET");

		componentList.add(generateButton);
		componentList.add(resetButton);
	}
}
