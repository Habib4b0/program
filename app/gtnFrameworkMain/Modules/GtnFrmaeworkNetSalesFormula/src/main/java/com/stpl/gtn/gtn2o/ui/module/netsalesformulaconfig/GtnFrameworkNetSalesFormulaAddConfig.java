/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSaveAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfSaveConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfRuleSaveUniqueValidationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfSaveValidationAction;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFComboBoxTypeConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author STPL
 */
public class GtnFrameworkNetSalesFormulaAddConfig {

	private GtnFrameworkComponentConfigProvider componentConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getAddView() {

		GtnUIFrameworkViewConfig netSalesFormulaAddView = componentConfigProvider.getViewConfig(
				GtnFrameworkNSFConstants.getNsfAddView(), GtnFrameworkNSFConstants.getNsfAddView(), false);
		addComponentList(netSalesFormulaAddView,
				netSalesFormulaAddView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return netSalesFormulaAddView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String viewId) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addFormulaInformationPanel(componentList, viewId);
		addTabLayout(componentList, viewId);

		addSaveButtonLayout(componentList, viewId);
	}

	private void addFormulaInformationPanel(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {

		GtnUIFrameworkComponentConfig formulaInformationPanel = componentConfigProvider
				.getPanelConfig(viewId + "formulaInformationPanel", false, null);
		formulaInformationPanel.setComponentName("Formula Information");
		formulaInformationPanel.setAuthorizationIncluded(true);
		componentList.add(formulaInformationPanel);
		addFieldLayout(componentList, formulaInformationPanel.getComponentId(), viewId);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig formulaInformationCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(viewId + "formulaInformationMainLayout", true, parentId);
		formulaInformationCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(formulaInformationCssLayoutConfig);

		addFieldComponent(componentList, formulaInformationCssLayoutConfig.getComponentId(), viewId);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		GtnUIFrameworkComponentConfig formulaInformationLayoutCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(viewId + "formulaInformationLayout", true, parentId);
		formulaInformationLayoutCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		componentList.add(formulaInformationLayoutCssLayoutConfig);

		addFormulaID(componentList, formulaInformationLayoutCssLayoutConfig.getComponentId(), viewId);
		addFormulaNo(componentList, formulaInformationLayoutCssLayoutConfig.getComponentId(), viewId);
		addFormulaName(componentList, formulaInformationLayoutCssLayoutConfig.getComponentId(), viewId);
		addFormulaCategory(componentList, formulaInformationLayoutCssLayoutConfig.getComponentId(), viewId);
	}

	private void addFormulaID(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + "formulaId";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig formulaIdConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		formulaIdConfig.setAuthorizationIncluded(true);
		formulaIdConfig.setComponentName("Formula ID");
		formulaIdConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);

		componentList.add(formulaIdConfig);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + "formulaNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig formulaNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		formulaNoConfig.setAuthorizationIncluded(true);
		formulaNoConfig.setComponentName("Formula No");
		formulaNoConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);

		GtnUIFrameworkValidationConfig formulaNoConfigValidationConfig = new GtnUIFrameworkValidationConfig();
		formulaNoConfigValidationConfig.setMaxLength(5);
		formulaNoConfig.setGtnUIFrameworkValidationConfig(formulaNoConfigValidationConfig);

		componentList.add(formulaNoConfig);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + "formulaName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig formulaNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		formulaNameConfig.setAuthorizationIncluded(true);
		formulaNameConfig.setComponentName("Formula Name");
		formulaNameConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);

		componentList.add(formulaNameConfig);
	}

	private void addFormulaCategory(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + "formulaCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig formulaCategoryConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		formulaCategoryConfig.setAuthorizationIncluded(true);
		formulaCategoryConfig.setComponentName("Formula Category");

		GtnUIFrameworkComboBoxConfig netSalesFormulaTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.NS_FORMULA_CATEGORY,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		formulaCategoryConfig.setGtnComboboxConfig(netSalesFormulaTypeConfig);

		componentList.add(formulaCategoryConfig);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {
		String componentId = viewId + "netSalesFormulaTabsheet";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, false, null);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig nsfTabsheetConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TABSHEET);
		nsfTabsheetConfig.setAuthorizationIncluded(true);
		nsfTabsheetConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		addTabs(viewId, tabConfigList);

		nsfTabsheetConfig.setGtnTabSheetConfigList(tabConfigList);

		componentList.add(nsfTabsheetConfig);

	}

	private void addTabs(String viewId, List<GtnUIFrameworkTabConfig> tabConfigList) {
		tabConfigList.add(new GtnFrameworkNSFSalesBasisTabConfig().addSalesBasisTabConfig(viewId));
		tabConfigList.add(new GtnFrameworkNSFDeductionTabConfig().addDeductionTabConfig(viewId));
	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {
		String saveBackButtonLayout = viewId + "saveBackButtonLayout";
		GtnUIFrameworkComponentConfig crudCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(saveBackButtonLayout, false, null);
		componentList.add(crudCssLayoutConfig);

		addSaveButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addBackButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "saveButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig saveButtonConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("SAVE");
		componentList.add(saveButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfSaveValidationAction.class.getName());
		customAction.addActionParameter(viewId);
		actionConfigList.add(customAction);
		GtnUIFrameWorkActionConfig uniqueValidationAction = new GtnUIFrameWorkActionConfig();
		uniqueValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		uniqueValidationAction.addActionParameter(GtnUiFrameworkNsfRuleSaveUniqueValidationAction.class.getName());
		uniqueValidationAction.addActionParameter(viewId);
		actionConfigList.add(uniqueValidationAction);
		GtnUIFrameWorkActionConfig customConfirmationAction = new GtnUIFrameWorkActionConfig();
		customConfirmationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customConfirmationAction.addActionParameter(GtnUiFrameworkNsfSaveConfirmationAction.class.getName());
		customConfirmationAction.addActionParameter(viewId);
		actionConfigList.add(customConfirmationAction);
		saveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String componentId = viewId + "backButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig backButtonConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("BACK");
		componentList.add(backButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(" Save?");
		alertParamsList.add(
				" Do you want to save this Net Sales Formula record before returning to the Landing Screen?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> onFailureActionConfig = new ArrayList<>();
                GtnUIFrameWorkActionConfig backButtonActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

        navigationActionConfig.addActionParameter(GtnFrameworkNSFConstants.getEmpty());
        backButtonActionConfig.addActionParameter(GtnUiFrameworkNsfSaveConfirmationAction.class.getName());
        backButtonActionConfig.addActionParameter(viewId);
        onSucessActionConfig.add(backButtonActionConfig);
		onSucessActionConfig.add(navigationActionConfig);
		onFailureActionConfig.add(navigationActionConfig);
		alertParamsList.add(onSucessActionConfig);
        alertParamsList.add(onFailureActionConfig);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}
}
