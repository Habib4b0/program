/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.saveview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingSaveViewLookup {

	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public GtnUIFrameworkViewConfig getSaveViewLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Save View");
		view.setViewId("dsSaveViewLookUp");
		view.setDefaultView(false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		vLayout.setComponentId("saveViewLookUp-RootVerticalLayout");
		vLayout.setAddToParent(false);
		vLayout.setSpacing(Boolean.TRUE);
		vLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setGtnLayoutConfig(conf);
		componentList.add(vLayout);

		String rootId = "saveViewLookUp-RootVerticalLayout";

		GtnUIFrameworkComponentConfig horizontalLayoutType = layoutConfig.getHorizontalLayoutConfig("saveView-ViewType",
				rootId);
		horizontalLayoutType.setSpacing(true);
		horizontalLayoutType.setMargin(true);
		componentList.add(horizontalLayoutType);

		addTypeOptionGroup(componentList, horizontalLayoutType.getComponentId());

		GtnUIFrameworkComponentConfig horizontalLayoutName = layoutConfig.getHorizontalLayoutConfig("saveView-ViewName",
				"saveViewLookUp-RootVerticalLayout");
		componentList.add(horizontalLayoutName);

		addNameTextField(componentList, horizontalLayoutName.getComponentId());

		GtnUIFrameworkComponentConfig horizontalLayoutButton = layoutConfig
				.getHorizontalLayoutConfig("saveView-ViewButtons", rootId);
		componentList.add(horizontalLayoutButton);

		addControlPopUpButtonLayout(componentList, horizontalLayoutButton.getComponentId());
	}

	private void addTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig saveViewTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		saveViewTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		saveViewTypeOptionGroup.setComponentId("saveViewType");
		saveViewTypeOptionGroup.setAddToParent(true);
		saveViewTypeOptionGroup.setParentComponentId(parentId);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { "Private", "Public" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		saveViewTypeOptionGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		saveViewTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(saveViewTypeOptionGroup);
	}

	private void addNameTextField(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig saveViewNameTextField = new GtnUIFrameworkComponentConfig();
		saveViewNameTextField.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		saveViewNameTextField.setComponentId("saveViewName");
		saveViewNameTextField.setComponentName("View Name:");
		saveViewNameTextField.setAddToParent(true);
		saveViewNameTextField.setParentComponentId(parentId);
		componentList.add(saveViewNameTextField);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig saveViewCancelButton = new GtnUIFrameworkComponentConfig();
		saveViewCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewCancelButton.setComponentId("saveViewCancel");
		saveViewCancelButton.setComponentName("CANCEL");
		saveViewCancelButton.setParentComponentId(parentId);
		saveViewCancelButton.setAddToParent(true);
		componentList.add(saveViewCancelButton);

		GtnUIFrameworkComponentConfig saveViewAddButton = new GtnUIFrameworkComponentConfig();
		saveViewAddButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewAddButton.setComponentId("saveViewAdd");
		saveViewAddButton.setComponentName("ADD");
		saveViewAddButton.setParentComponentId(parentId);
		saveViewAddButton.setAddToParent(true);
		componentList.add(saveViewAddButton);

		GtnUIFrameworkComponentConfig saveViewUpdateButton = new GtnUIFrameworkComponentConfig();
		saveViewUpdateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewUpdateButton.setComponentId("saveViewUpdate");
		saveViewUpdateButton.setComponentName("UPDATE");
		saveViewUpdateButton.setParentComponentId(parentId);
		saveViewUpdateButton.setAddToParent(true);
		componentList.add(saveViewUpdateButton);

	}
}
