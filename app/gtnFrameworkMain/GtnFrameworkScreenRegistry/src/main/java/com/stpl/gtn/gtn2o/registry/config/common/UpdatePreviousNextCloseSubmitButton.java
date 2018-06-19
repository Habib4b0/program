package com.stpl.gtn.gtn2o.registry.config.common;

import java.util.List;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class UpdatePreviousNextCloseSubmitButton {
	
	
	
	public void addCommonButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig commonButtonLayout = new GtnUIFrameworkComponentConfig();
		commonButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		commonButtonLayout.setAuthorizationIncluded(true);
		commonButtonLayout.setComponentId(nameSpace + "_" + "commonButtonLayout");
		commonButtonLayout.setAddToParent(Boolean.TRUE);
		commonButtonLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		layoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		commonButtonLayout.setGtnLayoutConfig(layoutConfig);
		commonButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(commonButtonLayout);
	}

	public void addUpdateButton(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig updateButtonConfig = new GtnUIFrameworkComponentConfig();
		updateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButtonConfig.setComponentId(nameSpace + "_" + "updateButton");
		updateButtonConfig.setComponentName("UPDATE");
		updateButtonConfig.setAddToParent(Boolean.TRUE);
		updateButtonConfig.setParentComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.COMMON_BUTTON_LAYOUT_PARENT_ID);
		
		componentList.add(updateButtonConfig);
	}
		
	public void addPreviousButton(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig.setComponentId(nameSpace + "_" + "previousButton");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(Boolean.TRUE);
		previousButtonConfig.setParentComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.COMMON_BUTTON_LAYOUT_PARENT_ID);
		
		componentList.add(previousButtonConfig);
	}
	
	public void addNextButton(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig nextButtonConfig = new GtnUIFrameworkComponentConfig();
		nextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButtonConfig.setComponentId(nameSpace + "_" + "nextButton");
		nextButtonConfig.setComponentName("NEXT");
		nextButtonConfig.setAddToParent(Boolean.TRUE);
		nextButtonConfig.setParentComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.COMMON_BUTTON_LAYOUT_PARENT_ID);
		
		componentList.add(nextButtonConfig);
	}
	
	public void addCloseButton(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(nameSpace + "_" + "closeButton");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(Boolean.TRUE);
		closeButtonConfig.setParentComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.COMMON_BUTTON_LAYOUT_PARENT_ID);
		
		componentList.add(closeButtonConfig);
	}
	
	public void addSubmitButton(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig submitButtonConfig = new GtnUIFrameworkComponentConfig();
		submitButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButtonConfig.setComponentId(nameSpace + "_" + "submitButton");
		submitButtonConfig.setComponentName("SUBMIT");
		submitButtonConfig.setAddToParent(Boolean.TRUE);
		submitButtonConfig.setParentComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.COMMON_BUTTON_LAYOUT_PARENT_ID);	
	
		componentList.add(submitButtonConfig);
	}
}
