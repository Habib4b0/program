package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionViewAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionViewUpdateAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportDataSelectionSaveViewLookUp {

	private GtnFrameworkReportLayoutsConfig layoutConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getSaveViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Save View");
		view.setViewId(GtnFrameworkReportStringConstants.DS_SAVE_VIEW_LOOK_UP);
		view.setDefaultView(false);
		addComponentList(view, namespace);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList, namespace);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		vLayout.setComponentId(GtnFrameworkCommonConstants.SAVE_VIEW_LOOK_UP_ROOT_VERTICAL_LAYOUT);
		vLayout.setAddToParent(false);
		vLayout.setSpacing(true);
		vLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setGtnLayoutConfig(conf);
		vLayout.addComponentStyle("v-vertical-layout-report-save-lookup");
		componentList.add(vLayout);

		String rootId = "saveViewLookUp-RootVerticalLayout";

		GtnUIFrameworkComponentConfig horizontalLayoutType = layoutConfig.getHorizontalLayoutConfig("saveView-ViewType",
				rootId);
		horizontalLayoutType.setSpacing(true);
		horizontalLayoutType.setMargin(true);
		componentList.add(horizontalLayoutType);

		addTypeOptionGroup(componentList, horizontalLayoutType.getComponentId(), namespace);

		GtnUIFrameworkComponentConfig horizontalLayoutName = layoutConfig.getHorizontalLayoutConfig("saveView-ViewName",
				"saveViewLookUp-RootVerticalLayout");
		horizontalLayoutName.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		horizontalLayoutName.addComponentStyle("v-h-layout-report-save-lookup");
		componentList.add(horizontalLayoutName);

		addNameTextField(componentList, horizontalLayoutName.getComponentId(), namespace);

		GtnUIFrameworkComponentConfig horizontalLayoutButton = layoutConfig
				.getHorizontalLayoutConfig("saveView-ViewButtons", rootId);
		componentList.add(horizontalLayoutButton);

		addControlPopUpButtonLayout(componentList, horizontalLayoutButton.getComponentId(), namespace);
	}

	private void addTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig viewNameTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		viewNameTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		viewNameTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		viewNameTypeOptionGroup.setAddToParent(true);
		viewNameTypeOptionGroup.setParentComponentId(parentId);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(GtnFrameworkCommonConstants.A_PRIVATE, "Public"));
		comboConfig.setValuesFromService(false);
		viewNameTypeOptionGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		viewNameTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(viewNameTypeOptionGroup);
	}

	private void addNameTextField(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig saveViewNameTextField = new GtnUIFrameworkComponentConfig();
		saveViewNameTextField.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		saveViewNameTextField.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.SAVE_VIEW_NAME);
		saveViewNameTextField.setComponentName("View Name:");
		saveViewNameTextField.addComponentStyle("v-report-save-lookup");
		saveViewNameTextField.setAddToParent(true);
		saveViewNameTextField.setParentComponentId(parentId);

		componentList.add(saveViewNameTextField);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig saveViewAdd = new GtnUIFrameworkComponentConfig();
		saveViewAdd.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewAdd.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "saveViewAdd");
		saveViewAdd.setComponentName("ADD");
		saveViewAdd.addComponentStyle(
				GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		saveViewAdd.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		saveViewAdd.setParentComponentId(parentId);
		saveViewAdd.setAddToParent(true);

		GtnUIFrameWorkActionConfig addViewAction = new GtnUIFrameWorkActionConfig();
		addViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addViewAction.addActionParameter(GtnReportDataSelectionViewAddAction.class.getName());
		addViewAction.addActionParameter(GtnFrameworkReportStringConstants.DS_SAVE_VIEW_LOOK_UP);
		addViewAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.SAVE_VIEW_NAME);
		addViewAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		saveViewAdd.addGtnUIFrameWorkActionConfig(addViewAction);

		GtnUIFrameworkComponentConfig saveViewUpdate = new GtnUIFrameworkComponentConfig();
		saveViewUpdate.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewUpdate.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "saveViewUpdate");
		saveViewUpdate.setComponentName("UPDATE");
		saveViewUpdate.addComponentStyle(
				GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		saveViewUpdate.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		saveViewUpdate.setParentComponentId(parentId);
		saveViewUpdate.setAddToParent(true);
		saveViewUpdate.setEnable(false);

		GtnUIFrameWorkActionConfig updateViewAction = new GtnUIFrameWorkActionConfig();
		updateViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		updateViewAction.addActionParameter(GtnReportDataSelectionViewUpdateAction.class.getName());
		updateViewAction.addActionParameter(GtnFrameworkReportStringConstants.DS_SAVE_VIEW_LOOK_UP);
		updateViewAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.SAVE_VIEW_NAME);
		updateViewAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		saveViewUpdate.addGtnUIFrameWorkActionConfig(updateViewAction);

		GtnUIFrameworkComponentConfig saveViewCancel = new GtnUIFrameworkComponentConfig();
		saveViewCancel.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewCancel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "saveViewCancel");
		saveViewCancel.setComponentName("CANCEL");
		saveViewCancel.addComponentStyle(
				GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		saveViewCancel.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		saveViewCancel.setParentComponentId(parentId);
		saveViewCancel.setAddToParent(true);

		GtnUIFrameWorkActionConfig closePopupAction = new GtnUIFrameWorkActionConfig();
		closePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closePopupAction.addActionParameter(GtnFrameworkReportStringConstants.DS_SAVE_VIEW_LOOK_UP);
		saveViewCancel.addGtnUIFrameWorkActionConfig(closePopupAction);

		componentList.add(saveViewCancel);
		componentList.add(saveViewAdd);
		componentList.add(saveViewUpdate);

	}

}