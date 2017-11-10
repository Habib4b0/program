package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkForecastReturnLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;

public class GtnFrameworkForecastReturnSaveViewLookUp {
	private GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();

	public GtnUIFrameworkViewConfig getSaveViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Save View");
		view.setViewId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsSaveViewLookUp");
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
		horizontalLayoutName.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(horizontalLayoutName);

		addNameTextField(componentList, horizontalLayoutName.getComponentId());

		GtnUIFrameworkComponentConfig horizontalLayoutButton = layoutConfig
				.getHorizontalLayoutConfig("saveView-ViewButtons", rootId);
		componentList.add(horizontalLayoutButton);

		addControlPopUpButtonLayout(componentList, horizontalLayoutButton.getComponentId(), namespace);
	}

	private void addTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setComponentId(GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(parentId);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(GtnFrameworkCommonConstants.A_PRIVATE, "Public"));
		comboConfig.setValuesFromService(Boolean.FALSE);
		addHierarchyTypeOptionGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addNameTextField(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyTypeOptionGroup.setComponentId(GtnFrameworkCommonConstants.SAVE_VIEW_NAME);
		addHierarchyTypeOptionGroup.setComponentName("View Name:");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(parentId);

		GtnUIFrameworkValidationConfig valConfigForViewName = new GtnUIFrameworkValidationConfig();
		valConfigForViewName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkValidationConfig(valConfigForViewName);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig saveViewCancel = new GtnUIFrameworkComponentConfig();
		saveViewCancel.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewCancel.setComponentId("saveViewCancel");
		saveViewCancel.setComponentName("CANCEL");
		saveViewCancel.setParentComponentId(parentId);
		saveViewCancel.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigCloseList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsSaveViewLookUp");
		actionConfigCloseList.add(closeAction);
		saveViewCancel.setGtnUIFrameWorkActionConfigList(actionConfigCloseList);

		componentList.add(saveViewCancel);

		GtnUIFrameworkComponentConfig saveViewAdd = new GtnUIFrameworkComponentConfig();
		saveViewAdd.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewAdd.setComponentId("saveViewAdd");
		saveViewAdd.setComponentName("ADD");
		saveViewAdd.setParentComponentId(parentId);
		saveViewAdd.setAddToParent(true);
		componentList.add(saveViewAdd);
		List<GtnUIFrameWorkActionConfig> saveConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig viewValidationActionConfig = new GtnUIFrameWorkActionConfig();

		viewValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		viewValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.SAVE_VIEW_NAME));

		GtnUIFrameWorkActionConfig viewAlertActionConfig = new GtnUIFrameWorkActionConfig();
		viewAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> viewAlertParams = new ArrayList<>();
		viewAlertParams.add("Invalid view name");
		viewAlertParams.add("Enter the view name");

		viewAlertActionConfig.setActionParameterList(viewAlertParams);
		viewValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(viewAlertActionConfig)));
		saveConfigList.add(viewValidationActionConfig);

		GtnUIFrameWorkActionConfig saveAction = new GtnUIFrameWorkActionConfig();

		saveAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_VIEW_SAVE_ACTION);
		saveAction.addActionParameter("add");
		saveAction.setFieldValues(Arrays.asList("returnsForecastMainScreenDataSelection_projectionName",
				"returnsForecastMainScreenDataSelection_projectionDescription",
				"returnsForecastMainScreenDataSelection_company", "returnsForecastMainScreenDataSelection_businessUnit",
				"returns", "returnsForecastMainScreenDataSelection_fromPeriod",
				"returnsForecastMainScreenDataSelection_toPeriod",
				"returnsForecastMainScreenDataSelection_productHierarchy",
				"returnsForecastMainScreenDataSelection_forecastLevel",
				"returnsForecastMainScreenDataSelection_productGroup",
				"returnsForecastMainScreenDataSelection_productLevelComboBox",
				"returnsForecastMainScreenDataSelection_relationShipCombobox",
				"returnsForecastMainScreenDataSelection_dualListBoxComp", GtnFrameworkCommonConstants.SAVE_VIEW_NAME,
				"saveViewType", "returnsForecastMainScreenDataSelection_dataSelectionMainLayout", "Private",
				"returnsForecastMainScreenDataSelection_dsSaveViewLookUp"));

		saveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		saveConfigList.add(saveAction);

		saveViewAdd.setGtnUIFrameWorkActionConfigList(saveConfigList);
		GtnUIFrameworkComponentConfig saveViewUpdate = new GtnUIFrameworkComponentConfig();
		saveViewUpdate.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewUpdate.setComponentId("saveViewUpdate");
		saveViewUpdate.setComponentName("UPDATE");
		saveViewUpdate.setParentComponentId(parentId);
		saveViewUpdate.setAddToParent(true);
		componentList.add(saveViewUpdate);

		List<GtnUIFrameWorkActionConfig> updateConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig updateAction = new GtnUIFrameWorkActionConfig();

		updateAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_VIEW_SAVE_ACTION);
		updateAction.addActionParameter("update");
		updateAction.setFieldValues(Arrays.asList(
				"returnsForecastMainScreenDataSelection_projectionName",
				"returnsForecastMainScreenDataSelection_projectionDescription",
				"returnsForecastMainScreenDataSelection_company", "returnsForecastMainScreenDataSelection_businessUnit",
				"returns", "returnsForecastMainScreenDataSelection_fromPeriod",
				"returnsForecastMainScreenDataSelection_toPeriod",
				"returnsForecastMainScreenDataSelection_productHierarchy",
				"returnsForecastMainScreenDataSelection_forecastLevel",
				"returnsForecastMainScreenDataSelection_productGroup",
				"returnsForecastMainScreenDataSelection_productLevelComboBox",
				"returnsForecastMainScreenDataSelection_relationShipCombobox",
				"returnsForecastMainScreenDataSelection_dualListBoxComp", GtnFrameworkCommonConstants.SAVE_VIEW_NAME,
				"saveViewType", "returnsForecastMainScreenDataSelection_dataSelectionMainLayout", "Private",
				"returnsForecastMainScreenDataSelection_dsSaveViewLookUp"));

		updateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		updateConfigList.add(updateAction);
		saveViewUpdate.setGtnUIFrameWorkActionConfigList(updateConfigList);

	}
}