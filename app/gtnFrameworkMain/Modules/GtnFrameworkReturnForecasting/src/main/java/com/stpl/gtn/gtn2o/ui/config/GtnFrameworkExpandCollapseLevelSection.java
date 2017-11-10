package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxSourceSubsetType;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkExpandCollapseLevelSection {

	private GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();
	private String moduleName = null;

	public List<GtnUIFrameworkComponentConfig> getRootExpandCollapseSectionLayout(
			List<GtnUIFrameworkComponentConfig> componentList, String parentId, String moduleName) {
		this.moduleName = moduleName;
		GtnUIFrameworkComponentConfig rootVerticalLayout = layoutConfig
				.getHorizontalLayoutConfig(moduleName + "ExpandCollapseSection", parentId);
		componentList.add(rootVerticalLayout);
		addDiviedExpandCollapseComponents(componentList, rootVerticalLayout.getComponentId());

		return componentList;
	}

	private void addDiviedExpandCollapseComponents(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig cssLayoutLn4ForLevelDdlb = layoutConfig
				.getCssLayoutConfig(moduleName + "cssLayoutLn4ForLevelDdlb", parentId);

		componentList.add(cssLayoutLn4ForLevelDdlb);

		levelDdlb(componentList, cssLayoutLn4ForLevelDdlb.getComponentId());

		GtnUIFrameworkComponentConfig cssLayoutLn4ForExpandAndCollapseBtn = layoutConfig
				.getCssLayoutConfig(moduleName + "cssLayoutLn4ForExpandAndCollapseBtn", parentId);

		componentList.add(cssLayoutLn4ForExpandAndCollapseBtn);

		expandCollapseButton(componentList, cssLayoutLn4ForExpandAndCollapseBtn.getComponentId());

		GtnUIFrameworkComponentConfig cssLayoutLn4ForLevelFilterDdlb = layoutConfig
				.getCssLayoutConfig(moduleName + "cssLayoutLn4ForLevelFilterDdlb", parentId);

		componentList.add(cssLayoutLn4ForLevelFilterDdlb);

		levelFilterFilterFilter(componentList, cssLayoutLn4ForLevelFilterDdlb.getComponentId());
	}

	private void levelDdlb(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig levelDdlbCssLayout = layoutConfig
				.getCssLayoutConfig(moduleName + GtnFrameworkCommonConstants.LEVEL_DDLB, parentId);
		levelDdlbCssLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(levelDdlbCssLayout);

		GtnUIFrameworkComponentConfig levelDdlbHorizontalLayout = layoutConfig.getHorizontalLayoutConfig(
				moduleName + GtnFrameworkCommonConstants.LEVEL_DDLB, levelDdlbCssLayout.getComponentId());
		componentList.add(levelDdlbHorizontalLayout);

		GtnUIFrameworkComponentConfig levelDdlb = new GtnUIFrameworkComponentConfig();
		levelDdlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);

		levelDdlb.setComponentId(moduleName + GtnFrameworkCommonConstants.LEVEL_DDLB);// forecastReturnslevelDdlb
		levelDdlb.setComponentName("Level :");
		levelDdlb.setParentComponentId(levelDdlbHorizontalLayout.getComponentId());
		levelDdlb.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig levelDdlbConfig = new GtnUIFrameworkComboBoxConfig();
		levelDdlbConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelDdlbConfig.setComboBoxType("productLevelComboBox");
		levelDdlb.setGtnComboboxConfig(levelDdlbConfig);
		levelDdlbConfig.setSourceType(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX);
		levelDdlbConfig.setSubsetType(GtnUIFrameworkComboboxSourceSubsetType.AFTER_SELECTED);
		levelDdlbConfig.setInclusionOfSelected(Boolean.TRUE);
		levelDdlbConfig.setSourceComboboxId("returnsForecastTabSheet_forecastLevel");
		levelDdlbConfig.setExclusionOfLast(Boolean.TRUE);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		levelDdlb.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(levelDdlb);

	}

	private void expandCollapseButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig expandAndCollapseLayout = layoutConfig
				.getHorizontalLayoutConfig(moduleName + "expandCollapseBtn", parentId);
		componentList.add(expandAndCollapseLayout);

		GtnUIFrameworkComponentConfig expandButton = new GtnUIFrameworkComponentConfig();
		expandButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentId(moduleName + "expandBtn");
		expandButton.setComponentName("EXPAND");
		expandButton.setParentComponentId(expandAndCollapseLayout.getComponentId());
		expandButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfigForLevelDDLB = new GtnUIFrameWorkActionConfig();

		validationActionConfigForLevelDDLB.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfigForLevelDDLB
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB));

		GtnUIFrameWorkActionConfig alertActionConfigForLevelDDLB = new GtnUIFrameWorkActionConfig();
		alertActionConfigForLevelDDLB.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsForForLevelDDLB = new ArrayList<>();
		alertParamsForForLevelDDLB.add(" No Level Selected ");
		alertParamsForForLevelDDLB.add("Please select a Level from the drop down.");

		List<GtnUIFrameWorkActionConfig> alertParamsListForLevelDDLB = new ArrayList<>();
		alertParamsListForLevelDDLB.add(alertActionConfigForLevelDDLB);

		actionConfig.add(validationActionConfigForLevelDDLB);
		// ----------------------------------------------------------------------------------------
		alertActionConfigForLevelDDLB.setActionParameterList(alertParamsForForLevelDDLB);
		validationActionConfigForLevelDDLB.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.AND, alertParamsListForLevelDDLB }));

		GtnUIFrameWorkActionConfig tableLoadAction = new GtnUIFrameWorkActionConfig();
		tableLoadAction.setActionType(GtnUIFrameworkActionType.TREE_TABLE_EXPAND_ACTION);
		actionConfig.add(tableLoadAction);
		tableLoadAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB }));
		expandButton.setGtnUIFrameWorkActionConfigList(actionConfig);

		componentList.add(expandButton);

		GtnUIFrameworkComponentConfig collapseButton = new GtnUIFrameworkComponentConfig();
		collapseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentId(moduleName + "collapseBtn");
		collapseButton.setComponentName("COLLAPSE");
		collapseButton.setParentComponentId(expandAndCollapseLayout.getComponentId());
		collapseButton.setAddToParent(Boolean.TRUE);
		List<GtnUIFrameWorkActionConfig> collapseActionConfig = new ArrayList<>();

		collapseActionConfig.add(validationActionConfigForLevelDDLB);

		// --------------------------------------------------------------------------------
		GtnUIFrameWorkActionConfig collapseTableLoadAction = new GtnUIFrameWorkActionConfig();
		collapseTableLoadAction.setActionType(GtnUIFrameworkActionType.TREE_TABLE_COLLAPSE_ACTION);
		collapseActionConfig.add(collapseTableLoadAction);
		collapseTableLoadAction.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommonConstants.RESULT_TABLE, GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB }));
		collapseButton.setGtnUIFrameWorkActionConfigList(collapseActionConfig);
		componentList.add(collapseButton);

	}

	private void levelFilterFilterFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig levelFilterDdlbCssLayout = layoutConfig
				.getCssLayoutConfig(moduleName + GtnFrameworkCommonConstants.LEVEL_FILTER_DDLB, parentId);
		levelFilterDdlbCssLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(levelFilterDdlbCssLayout);

		GtnUIFrameworkComponentConfig levelFilterDdlbHorizontalLayout = layoutConfig.getHorizontalLayoutConfig(
				moduleName + GtnFrameworkCommonConstants.LEVEL_FILTER_DDLB, levelFilterDdlbCssLayout.getComponentId());
		componentList.add(levelFilterDdlbHorizontalLayout);

		GtnUIFrameworkComponentConfig levelFilterDdlb = new GtnUIFrameworkComponentConfig();
		levelFilterDdlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		levelFilterDdlb.setComponentId(moduleName + GtnFrameworkCommonConstants.LEVEL_FILTER_DDLB);
		levelFilterDdlb.setComponentName("Level Filter:");
		levelFilterDdlb.setParentComponentId(levelFilterDdlbHorizontalLayout.getComponentId());
		levelFilterDdlb.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig levelFilterDdlbConfig = new GtnUIFrameworkComboBoxConfig();
		levelFilterDdlbConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelFilterDdlbConfig.setComboBoxType("productLevelComboBox");
		levelFilterDdlb.setGtnComboboxConfig(levelFilterDdlbConfig);
		levelFilterDdlbConfig.setSourceComboboxId("returnsForecastTabSheet_forecastLevel");
		levelFilterDdlbConfig.setSourceType(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX);
		levelFilterDdlbConfig.setSubsetType(GtnUIFrameworkComboboxSourceSubsetType.AFTER_SELECTED);
		levelFilterDdlbConfig.setInclusionOfSelected(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig tableLoadAction = new GtnUIFrameWorkActionConfig();
		tableLoadAction.setActionType(GtnUIFrameworkActionType.TREE_TEBLE_LEVEL_FILTER);
		actionConfig.add(tableLoadAction);
		tableLoadAction.setActionParameterList(Arrays
				.asList(new Object[] { GtnFrameworkCommonConstants.RESULT_TABLE, "forecastReturnslevelFilterDdlb" }));
		levelFilterDdlb.setGtnUIFrameWorkActionConfigList(actionConfig);
		componentList.add(levelFilterDdlb);

	}

}
