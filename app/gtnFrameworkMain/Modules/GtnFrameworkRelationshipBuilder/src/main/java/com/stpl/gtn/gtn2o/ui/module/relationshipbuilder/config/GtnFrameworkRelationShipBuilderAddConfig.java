/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkHierarchyChangeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkResetAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkSaveAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkVersionChangeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.GtnFrameworkRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRelationShipBuilderAddConfig {
	private final GtnFrameworkComponentConfigProvider gtnConfigFactory = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getAddView(String viewName) {
		GtnUIFrameworkViewConfig viewConfig = gtnConfigFactory.getViewConfig(viewName, viewName, false);
		addComponentList(viewConfig, viewConfig.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return viewConfig;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRelationShipBuilderMainPanel(componentList, namspacePrefix);
	}

	private void addRelationShipBuilderMainPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {

		GtnUIFrameworkComponentConfig panel = gtnConfigFactory.getPanelConfig(namspacePrefix + "mainPanel", false,
				null);
		componentList.add(panel);
		addRelationShipBuilderMainLayout(componentList, namspacePrefix);
	}

	private void addRelationShipBuilderMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getVerticalLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT, true, namspacePrefix + "mainPanel");
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.getGtnLayoutConfig().setMargin(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addErrorDisplay(componentList, namspacePrefix);
		addSelectionOptionPanel(componentList, namspacePrefix);
		addResultPanel(componentList, namspacePrefix);
		addActionButtonLayout(componentList, namspacePrefix);
	}

	private void addErrorDisplay(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig errorLabelComponentConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "errorDisplay", true, namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT,
				GtnUIFrameworkComponentType.LABEL);
		errorLabelComponentConfig.setAuthorizationIncluded(true);
		errorLabelComponentConfig.addComponentStyle(GtnFrameworkCssConstants.ERROR);
		errorLabelComponentConfig.setVisible(false);
		componentList.add(errorLabelComponentConfig);
	}

	private void addSelectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig panel = gtnConfigFactory.getPanelConfig(namspacePrefix + "selectionOptionPanel",
				true, namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
		panel.setComponentName("Relationship:");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		addFieldLayout(componentList, namspacePrefix);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panelConfig = gtnConfigFactory.getPanelConfig(namspacePrefix + "resultPanel",
				true, namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
		panelConfig.setComponentName("Relationship Builder :");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addResultLayout(componentList, namspacePrefix);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT, true, namspacePrefix + "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT, true,
				namspacePrefix + "selectionOptionPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add("gtnGrid-single-ln-layout-3");
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, namspacePrefix);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		addRelationshipName(componentList, namspacePrefix);
		addRelationshipDescription(componentList, namspacePrefix);
		addHierarchyName(componentList, namspacePrefix);
		addRelationshipType(componentList, namspacePrefix);
		addVersionNo(componentList, namspacePrefix);
		addStartDate(componentList, namspacePrefix);
		addBuildType(componentList, namspacePrefix);
	}

	private void addRelationshipName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "relationshipNameLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_NAME, true,
				namspacePrefix + "relationshipNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Relationship Name");
		companyIdConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		companyIdConfig.setGtnTextBoxConfig(gtnConfigFactory.getTextBoxConfig(true, true, true));
		componentList.add(companyIdConfig);
	}

	private void addRelationshipDescription(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "relationshipDescLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_DESC, true,
				namspacePrefix + "relationshipDescLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Relationship Description");
		companyIdConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		companyIdConfig.setGtnTextBoxConfig(gtnConfigFactory.getTextBoxConfig(true, true, true));
		componentList.add(companyIdConfig);
	}

	private void addHierarchyName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "hierarchyNameLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME, true,
				namspacePrefix + "hierarchyNameLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Hierarchy Name");
		companyStatus.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkHierarchyChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.VERSION_NO);
		actionConfigList.add(customAction);
		companyStatus.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRelationshipType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "RelationshipTypeLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_TYPE, true,
				namspacePrefix + "RelationshipTypeLayout", GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setAuthorizationIncluded(true);
		addHierarchyTypeOptionGroup.setComponentName("Relationship Type");

		GtnUIFrameworkOptionGroupConfig companyQualifierNameConfig = new GtnUIFrameworkOptionGroupConfig();
		companyQualifierNameConfig.setValuesFromService(false);
		companyQualifierNameConfig.setItemValues(Arrays.asList("Primary", "Secondary"));

		addHierarchyTypeOptionGroup.setComponentStyle(Arrays.asList("horizontal"));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(companyQualifierNameConfig);

		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addVersionNo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "versionNoLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.VERSION_NO, true, namspacePrefix + "versionNoLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Hierarchy Version No");
		companyType.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		companyType.addComponentStyle("break-caption-100");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setItemValues(Arrays.asList(" "));
		companyTypeConfig.setRequired(true);
		companyTypeConfig.setRequiredMessage("Please select Version No");
		companyType.setGtnComboboxConfig(companyTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkVersionChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT);
		customAction.addActionParameter(namspacePrefix + "resultTable");
		customAction.addActionParameter("value");
		customAction.addActionParameter("selectedId");
		customAction.addActionParameter("totalLevels");
		actionConfigList.add(customAction);
		companyType.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "startDateLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig startDate = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.START_DATE, true, namspacePrefix + "startDateLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		startDate.setAuthorizationIncluded(true);
		startDate.setComponentName("Start Date");
		startDate.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		componentList.add(startDate);
	}

	private void addBuildType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(
				namspacePrefix + "buildTypeLayout", true,
				namspacePrefix + GtnFrameworkCommonConstants.SELECTION_OPTIONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.BUILD_TYPE, true, namspacePrefix + "buildTypeLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setAuthorizationIncluded(true);
		addHierarchyTypeOptionGroup.setComponentName("Build Type");

		GtnUIFrameworkOptionGroupConfig companyQualifierNameConfig = new GtnUIFrameworkOptionGroupConfig();
		companyQualifierNameConfig.setValuesFromService(false);
		companyQualifierNameConfig.setItemValues(Arrays.asList("Manual", "Automatic"));

		addHierarchyTypeOptionGroup.setComponentStyle(Arrays.asList("horizontal"));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(companyQualifierNameConfig);

		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, true,
				namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addBackButtonComponent(componentList, namspacePrefix);
		addSaveButtonComponent(componentList, namspacePrefix);
		addResetButtonComponent(componentList, namspacePrefix);
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(namspacePrefix + "gtnBack01Layout", true,
                		namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnBackButton", true,
				namspacePrefix + "gtnBack01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("BACK");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Any unsaved information will not be saved. Do you want to proceed?");

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		confirmActionConfig.addActionParameter(Arrays.asList(navigationActionConfig));
		searchButtonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);
	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(namspacePrefix + "gtnSave01Layout", true,
                		namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "getSaveButton", true,
				namspacePrefix + "gtnSave01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SAVE");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkSaveAction.class.getName());
		customAction.addActionParameter(namspacePrefix);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_DESC);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.VERSION_NO);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.START_DATE);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.BUILD_TYPE);
		customAction.addActionParameter(namspacePrefix + "rbTree");
		customAction.addActionParameter(namspacePrefix + "errorDisplay");
		customAction.addActionParameter("selectedRelationship");
		actionConfigList.add(customAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory.getHorizontalLayoutConfig(namspacePrefix + "gtnReset01Layout", true,
                		namspacePrefix + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namspacePrefix + "getResetButton", true,
				namspacePrefix + "gtnReset01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("RESET");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> confirmActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmAction = new GtnUIFrameWorkActionConfig();
		confirmAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmAction.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmAction.addActionParameter("Are you sure you want to reset the page to default/previous values?");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkResetAction.class.getName());
		resetActionConfig.addActionParameter(namspacePrefix + "searchResultTable");
		resetActionConfig.addActionParameter(9);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.VERSION_NO);
		resetActionConfig.addActionParameter("selectedRelationship");
		resetActionConfig.addActionParameter("selectedId");
		resetActionConfig.addActionParameter("totalLevels");
		resetActionConfig.addActionParameter(namspacePrefix);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.RESULTLAYOUT);
		resetActionConfig.addActionParameter("rbTree");
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_TYPE);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.BUILD_TYPE);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_NAME);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.RELATIONSHIP_DESC);
		resetActionConfig.addActionParameter(GtnFrameworkCommonConstants.START_DATE);
		resetActionConfig.addActionParameter("resultTable");
		resetActionConfig.addActionParameter("value");
		resetActionConfig.addActionParameter("Edit Error");
		resetActionConfig.addActionParameter("Please select a record to edit");
		resetActionConfig.addActionParameter(GtnFrameworkRelationshipBuilderConstants.RELATIONSHIP_BUILDER_SCREEN_CRUD);
		resetActionConfig.addActionParameter(false);
		resetActionConfig.addActionParameter("getSaveButton");
		resetActionConfig.addActionParameter("getResetButton");
		resetActionConfig.addActionParameter("removeFromTreeBtn");
		actionConfigList.add(resetActionConfig);
		confirmAction.addActionParameter(actionConfigList);
		confirmActionConfigList.add(confirmAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(confirmActionConfigList);

	}
}
