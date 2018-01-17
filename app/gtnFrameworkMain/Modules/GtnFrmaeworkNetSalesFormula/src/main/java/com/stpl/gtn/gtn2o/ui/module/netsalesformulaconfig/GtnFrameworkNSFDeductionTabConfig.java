package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnFrameworkNsfDeductionTabCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeductionMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeductionPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeductionsTabAddAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfFormulaTypeChangeAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkSetSelectionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfTabTableResetConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUIFrameWorkNsfNetSalesRulePopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUiFrameworkNsfDeductionsTabFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfPopulateValidationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFComboBoxTypeConstants;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

public class GtnFrameworkNSFDeductionTabConfig {

	private GtnFrameworkComponentConfigProvider componentConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkTabConfig addDeductionTabConfig(String viewId) {

		String deductionTabId = viewId + "deductionTabMainLayout";
		GtnUIFrameworkTabConfig tabConfig = new GtnUIFrameworkTabConfig();
		tabConfig.setComponentId(deductionTabId);
		tabConfig.setTabCaption("Deductions");
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		tabConfig.setTabLayoutComponentConfigList(componentList);

		GtnUIFrameworkComponentConfig deductionTabMainLayout = componentConfigProvider
				.getVerticalLayoutConfig(tabConfig.getComponentId(), true, tabConfig.getComponentId());
		deductionTabMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		deductionTabMainLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_10);
		deductionTabMainLayout.setTabComponent(true);
		componentList.add(deductionTabMainLayout);

		deductionOptionsPanel(componentList, deductionTabMainLayout.getComponentId(), viewId);
		addDeductionTabSearchCriteriaPanel(componentList, deductionTabMainLayout.getComponentId(), viewId);
		addDeductionTabButtonLayout(componentList, deductionTabMainLayout.getComponentId(), viewId);
		addDeductionTabAvailableDeductionsResultPanel(componentList, deductionTabMainLayout.getComponentId(), viewId);
		addDeductionTabAvailableDeductionButtonLayout(componentList, deductionTabMainLayout.getComponentId(), viewId);
		addDeductionTabSelectedDeductionsResultPanel(componentList, deductionTabMainLayout.getComponentId(), viewId);
		addResetRemoveExcelButtonLayout(componentList, deductionTabMainLayout.getComponentId(), viewId);
		return tabConfig;
	}

	private void deductionOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		GtnUIFrameworkComponentConfig deductionOptionsPanelConfig = componentConfigProvider
				.getPanelConfig(parentId + "deductionOptionsPanel", true, parentId);
		deductionOptionsPanelConfig.setComponentName("Deduction Options");
		deductionOptionsPanelConfig.setAuthorizationIncluded(true);
		componentList.add(deductionOptionsPanelConfig);

		deductionOptionsLayout(componentList, deductionOptionsPanelConfig.getComponentId(), viewId);

	}

	private void deductionOptionsLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig deductionOptionsMainCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(viewId + "deductionOptionsMainLayout", true, parentId);

		deductionOptionsMainCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(deductionOptionsMainCssLayoutConfig);

		GtnUIFrameworkComponentConfig deductionOptionsinnerCssLayoutConfig = componentConfigProvider.getCssLayoutConfig(
				viewId + "deductionOptionsInnerLayout", true, deductionOptionsMainCssLayoutConfig.getComponentId());

		deductionOptionsinnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(deductionOptionsinnerCssLayoutConfig);

		componentList.add(deductionOptionsinnerCssLayoutConfig);
		deductionOptionsFields(componentList, deductionOptionsinnerCssLayoutConfig.getComponentId(), viewId);
	}

	private void deductionOptionsFields(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		addFormulaType(componentList, parentId, viewId);
	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + "formulaType";
		GtnUIFrameworkComponentConfig deductionTabFormulaTypeLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(deductionTabFormulaTypeLayout);

		GtnUIFrameworkComponentConfig formulaType = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, deductionTabFormulaTypeLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		formulaType.setAuthorizationIncluded(true);
		formulaType.setComponentName("Formula Type");
		formulaType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(formulaType);

		GtnUIFrameworkComboBoxConfig formulaTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.NS_FORMULA_TYPE,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		formulaType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		formulaType.setGtnComboboxConfig(formulaTypeConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfFormulaTypeChangeAction.class.getName());
		customAction.addActionParameter(viewId);
		actionConfigList.add(customAction);
		formulaType.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addDeductionTabSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		GtnUIFrameworkComponentConfig deductionTabSearchCriteriaPanel = componentConfigProvider
				.getPanelConfig(viewId + "deductionsTabSearchCriteriaPanel", true, parentId);
		deductionTabSearchCriteriaPanel.setComponentName("Search Criteria");
		deductionTabSearchCriteriaPanel.setAuthorizationIncluded(true);
		deductionTabSearchCriteriaPanel.setEnable(false);
		componentList.add(deductionTabSearchCriteriaPanel);
		addFieldLayout(componentList, deductionTabSearchCriteriaPanel.getComponentId(), viewId);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig deductionTabSearchMainCssLayout = componentConfigProvider
				.getCssLayoutConfig(viewId + "deductionsTabSearchCriteriaMainlayout", true, parentId);
		deductionTabSearchMainCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(deductionTabSearchMainCssLayout);
		addFieldComponent(componentList, deductionTabSearchMainCssLayout.getComponentId(), viewId);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig deductionTabSearchCssLayout = componentConfigProvider
				.getCssLayoutConfig(viewId + "deductionsTabsearchCriteriaFieldlayout", true, parentId);
		deductionTabSearchCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(deductionTabSearchCssLayout);

		String parentComponentId = deductionTabSearchCssLayout.getComponentId();

		addContractNo(componentList, parentComponentId, viewId);
		addContractName(componentList, parentComponentId, viewId);
		addContractHolder(componentList, parentComponentId, viewId);

		addMarketType(componentList, parentComponentId, viewId);
		addCFPNo(componentList, parentComponentId, viewId);
		addIFPNo(componentList, parentComponentId, viewId);

		addPriceScheduleNo(componentList, parentComponentId, viewId);
		addDeductionNo(componentList, parentComponentId, viewId);
		addCFPName(componentList, parentComponentId, viewId);

		addIFPName(componentList, parentComponentId, viewId);
		addPriceScheduleName(componentList, parentComponentId, viewId);
		addDeductionName(componentList, parentComponentId, viewId);

		addDeductionType(componentList, parentComponentId, viewId);
		addDeductionSubType(componentList, parentComponentId, viewId);
		addDeductionCategory(componentList, parentComponentId, viewId);

		addDeductionAlias(componentList, parentComponentId, viewId);

	}

	private void addContractNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NO;
		GtnUIFrameworkComponentConfig deductionTabContractNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(deductionTabContractNoLayout);

		GtnUIFrameworkComponentConfig contractNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, deductionTabContractNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractNoConfig.setAuthorizationIncluded(true);
		contractNoConfig.setComponentName("Contract No");
		contractNoConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		contractNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(contractNoConfig);
	}

	private void addContractName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NAME;
		GtnUIFrameworkComponentConfig deductionTabContractNameLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(deductionTabContractNameLayout);

		GtnUIFrameworkComponentConfig deductionTabContractNameConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, deductionTabContractNameLayout.getComponentId(),
						GtnUIFrameworkComponentType.TEXTBOX);
		deductionTabContractNameConfig.setAuthorizationIncluded(true);
		deductionTabContractNameConfig.setComponentName("Contract Name");
		deductionTabContractNameConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> contractNameConditionsList = new ArrayList<>();
		contractNameConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(contractNameConditionsList);
		deductionTabContractNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(deductionTabContractNameConfig);

	}

	private void addContractHolder(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_HOLDER;
		GtnUIFrameworkComponentConfig deductionTabContractHolderLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(deductionTabContractHolderLayout);

		GtnUIFrameworkComponentConfig contractHolderConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, deductionTabContractHolderLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractHolderConfig.setAuthorizationIncluded(true);
		contractHolderConfig.setComponentName("Contract Holder");
		contractHolderConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		contractHolderConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(contractHolderConfig);

	}

	private void addMarketType(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABMARKET_TYPE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig marketType = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		marketType.setAuthorizationIncluded(true);
		marketType.setComponentName("Market Type");
		marketType.setEnable(false);
		componentList.add(marketType);

		GtnUIFrameworkComboBoxConfig marketTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.CONTRACT_TYPE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		marketType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		marketType.setGtnComboboxConfig(marketTypeConfig);
	}

	private void addCFPNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NO;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cfpNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cfpNoConfig.setAuthorizationIncluded(true);
		cfpNoConfig.setComponentName("Company Family Plan No");
		cfpNoConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		cfpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(cfpNoConfig);
	}

	private void addIFPNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NO;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig ifpNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		ifpNoConfig.setAuthorizationIncluded(true);
		ifpNoConfig.setComponentName("Item Family Plan No");
		ifpNoConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		ifpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(ifpNoConfig);

	}

	private void addPriceScheduleNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NUMBER;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig psNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		psNoConfig.setAuthorizationIncluded(true);
		psNoConfig.setComponentName("Price Schedule No");
		psNoConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		psNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(psNoConfig);

	}

	private void addDeductionNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NUMBER;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		deductionNoConfig.setAuthorizationIncluded(true);
		deductionNoConfig.setComponentName("Deduction No");
		deductionNoConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		deductionNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(deductionNoConfig);

	}

	private void addCFPName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cfpNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cfpNameConfig.setAuthorizationIncluded(true);
		cfpNameConfig.setComponentName("Company Family Plan Name");
		cfpNameConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		cfpNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(cfpNameConfig);

	}

	private void addIFPName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig ifpNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		ifpNameConfig.setAuthorizationIncluded(true);
		ifpNameConfig.setComponentName("Item Family Plan Name");
		ifpNameConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		ifpNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(ifpNameConfig);
	}

	private void addPriceScheduleName(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig psNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		psNameConfig.setAuthorizationIncluded(true);
		psNameConfig.setComponentName("Price Schedule Name");
		psNameConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		psNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(psNameConfig);

	}

	private void addDeductionName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		deductionNameConfig.setAuthorizationIncluded(true);
		deductionNameConfig.setComponentName("Deduction Name");
		deductionNameConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		deductionNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(deductionNameConfig);

	}

	private void addDeductionType(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_TYPE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionType = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionType.setAuthorizationIncluded(true);
		deductionType.setComponentName("Deduction Type");
		componentList.add(deductionType);

		GtnUIFrameworkComboBoxConfig deductionTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.RS_TYPE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		deductionType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		deductionType.setGtnComboboxConfig(deductionTypeConfig);
	}

	private void addDeductionSubType(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_SUB_TYPE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionSubType = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionSubType.setAuthorizationIncluded(true);
		deductionSubType.setComponentName("Deduction Sub-Type");
		componentList.add(deductionSubType);

		GtnUIFrameworkComboBoxConfig deductionSubTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.REBATE_PROGRAM_TYPE,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		deductionSubType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		deductionSubType.setGtnComboboxConfig(deductionSubTypeConfig);
	}

	private void addDeductionCategory(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_CATEGORY;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionCategoryConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionCategoryConfig.setAuthorizationIncluded(true);
		deductionCategoryConfig.setComponentName("Deduction Category");
		componentList.add(deductionCategoryConfig);

		GtnUIFrameworkComboBoxConfig deductionCategoryComboConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.RS_CATEGORY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		deductionCategoryConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		deductionCategoryConfig.setGtnComboboxConfig(deductionCategoryComboConfig);
	}

	private void addDeductionAlias(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_ALIAS;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionAliasConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		deductionAliasConfig.setAuthorizationIncluded(true);
		deductionAliasConfig.setComponentName("Deduction Alias");
		deductionAliasConfig.setEnable(false);
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(conditionsList);
		deductionAliasConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(deductionAliasConfig);

	}

	private void addDeductionTabButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String searchButtonLayoutId = viewId + "deductionTabsearchButtonlayout";
		GtnUIFrameworkComponentConfig searchCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(searchButtonLayoutId, true, parentId);
		componentList.add(searchCssLayoutConfig);

		addResetButtonComponent(componentList, searchCssLayoutConfig.getComponentId(), viewId);
		addSearchButtonComponent(componentList, searchCssLayoutConfig.getComponentId(), viewId);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "deductionTabsearchCriteriaResetButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig searchCriteriaResetButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		searchCriteriaResetButtonConfig.setComponentName(GtnFrameworkCommonConstants.RESET);
		searchCriteriaResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkNSFConstants.getResetConfirmation());
		params.add(GtnFrameworkNSFConstants.getResetConfirmationMsg());

		params.add(Arrays.asList(viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NO,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NAME,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_HOLDER,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABMARKET_TYPE,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NO,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NO,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NUMBER,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NUMBER,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NAME,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NAME,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NAME,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NAME,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_TYPE,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_SUB_TYPE,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_CATEGORY,
				viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_ALIAS));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, null, GtnFrameworkCommonStringConstants.STRING_EMPTY));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchCriteriaResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String componentId = viewId + "deductionTabSearchButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionTabSearchButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		deductionTabSearchButtonConfig.setComponentName("SEARCH");
		deductionTabSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(deductionTabSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfigFormulaType = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfigFormulaType
				.setFieldValues(Arrays.asList(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE));
		validationActionConfigFormulaType.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig validationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList(viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NO,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_HOLDER,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABMARKET_TYPE,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NO,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NO,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NUMBER,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NUMBER,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_TYPE,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_SUB_TYPE,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_CATEGORY,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_ALIAS));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> deductionTabSearchBtnOnFailureListFormulaType = new ArrayList<>();

		GtnUIFrameWorkActionConfig deductionTabSearchBtnAlertActionConfigFormulaType = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkNSFConstants.getFormulaTypeIsMandatory());
		alertParamsList.add(GtnFrameworkNSFConstants.getPleaseSelectFormulaType());

		deductionTabSearchBtnAlertActionConfigFormulaType.setActionParameterList(alertParamsList);
		deductionTabSearchBtnOnFailureListFormulaType.add(deductionTabSearchBtnAlertActionConfigFormulaType);

		List<GtnUIFrameWorkActionConfig> deductionTabSearchBtnOnFailureList = new ArrayList<>();

		GtnUIFrameWorkActionConfig deductionTabSearchBtnAlertActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkNSFConstants.getNoSearchCriteria());
		alertParams.add(GtnFrameworkNSFConstants.getPleaseEnterSalesDeductionTabSearchCriteria());

		deductionTabSearchBtnAlertActionConfig.setActionParameterList(alertParams);
		deductionTabSearchBtnOnFailureList.add(deductionTabSearchBtnAlertActionConfig);

		validationActionConfigFormulaType.addActionParameter(deductionTabSearchBtnOnFailureListFormulaType);
		validationActionConfig.addActionParameter(deductionTabSearchBtnOnFailureList);

		searchActionConfigList.add(validationActionConfigFormulaType);
		searchActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig deductionTabSearchBtnLoadDataTableAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(viewId + GtnFrameworkCommonConstants.AVAILABLE_DEDUCTIONS_TABLE);

		deductionTabSearchBtnLoadDataTableAction.setActionParameterList(actionParams);
		deductionTabSearchBtnLoadDataTableAction
				.setFieldValues(Arrays.asList(viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NO,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCONTRACT_HOLDER,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABMARKET_TYPE,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NO,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NO,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NUMBER,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NUMBER,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABCFP_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TABIFP_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_PS_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_NAME,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_TYPE,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_SUB_TYPE,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_CATEGORY,
						viewId + GtnFrameworkCommonConstants.DEDUCTIONS_TAB_DEDUCTION_ALIAS));

		searchActionConfigList.add(deductionTabSearchBtnLoadDataTableAction);

		GtnUIFrameWorkActionConfig notificationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(viewId + GtnFrameworkCommonConstants.AVAILABLE_DEDUCTIONS_TABLE);
		searchActionConfigList.add(notificationActionConfig);
		deductionTabSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addDeductionTabAvailableDeductionsResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		GtnUIFrameworkComponentConfig availableDeductionsPanelConfig = componentConfigProvider
				.getPanelConfig(viewId + "availableDeductionsPanel", true, parentId);
		availableDeductionsPanelConfig.setComponentName("Available Deductions");
		componentList.add(availableDeductionsPanelConfig);
		addAvailableDeductionsResultLayout(componentList, availableDeductionsPanelConfig.getComponentId(), viewId);
	}

	private void addAvailableDeductionsResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.AVAILABLE_DEDUCTIONS_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayoutConfig);
		addAvailableDeductionsPagedTableComponent(componentList, componentId, gtnLayoutConfig.getComponentId());
	}

	private void addAvailableDeductionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String avalableContractTableId, String parentId) {

		GtnUIFrameworkComponentConfig avalableDeductionTableConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(avalableContractTableId, true, parentId,
						GtnUIFrameworkComponentType.PAGEDTABLE);
		avalableDeductionTableConfig.setAuthorizationIncluded(true);

		avalableDeductionTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		avalableDeductionTableConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(avalableDeductionTableConfig);

		GtnUIFrameworkPagedTableConfig avalableDeductionTable = componentConfigProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"NScontractSearch", GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_DEDUCTIONS_SEARCH_QUERY_NAME);
		avalableDeductionTable.setEditable(false);
		avalableDeductionTable.setMultiSelect(true);
		avalableDeductionTable.setItemPerPage(5);
		avalableDeductionTable.setPageLength(15);
		avalableDeductionTable.setSinkItemPerPageWithPageLength(false);
		avalableDeductionTable.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class, });
		avalableDeductionTable.setTableVisibleHeader(GtnFrameworkNSFConstants.getAvailableDeductionsVisibleHeaders());
		avalableDeductionTable.setTableColumnMappingId(GtnFrameworkNSFConstants.getAvailableDeductionsVisibleColumns());
		avalableDeductionTable.setExtraColumn(new Object[] { GtnFrameworkNSFConstants.getDeductionType(),
				GtnFrameworkNSFConstants.getDeductionSubType(), GtnFrameworkNSFConstants.getDeductionCategory(), });
		avalableDeductionTable.setExtraColumnDataType(new Class<?>[] { Integer.class, Integer.class, Integer.class });
		avalableDeductionTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		avalableDeductionTable.setCustomFilterConfigMap(getDeductionTabCustomFilterConfig());
		avalableDeductionTableConfig.setGtnPagedTableConfig(avalableDeductionTable);
	}

	private void addDeductionTabAvailableDeductionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String searchButtonLayoutId = viewId + "availableDeductionButtonlayout";
		GtnUIFrameworkComponentConfig availableDeductionCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(searchButtonLayoutId, true, parentId);
		componentList.add(availableDeductionCssLayoutConfig);

		addAvailableDeductionAddButtonComponent(componentList, availableDeductionCssLayoutConfig.getComponentId(),
				viewId);
		addAvailableDeductionResetButtonComponent(componentList, availableDeductionCssLayoutConfig.getComponentId(),
				viewId);
	}

	private void addAvailableDeductionResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String componentId = viewId + "availableDeductionResetButton";
		GtnUIFrameworkComponentConfig availableDeductionResetButtonLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(availableDeductionResetButtonLayout);

		GtnUIFrameworkComponentConfig availableDeductionResetButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, availableDeductionResetButtonLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		availableDeductionResetButtonConfig.setAuthorizationIncluded(true);
		availableDeductionResetButtonConfig.setComponentName(GtnFrameworkCommonConstants.RESET);
		componentList.add(availableDeductionResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkNSFConstants.getResetConfirmation());
		params.add(GtnFrameworkNSFConstants.getResetConfirmationMsgForAvailableDeduction());

		params.add(Arrays.asList(viewId + GtnFrameworkCommonConstants.AVAILABLE_DEDUCTIONS_TABLE));
		Object tableDefaultValue = null;
		params.add(Arrays.asList(tableDefaultValue));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		availableDeductionResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addAvailableDeductionAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {
		String componentId = viewId + "availableDeductionAddButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig availableDeductionAddButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		availableDeductionAddButtonConfig.setAuthorizationIncluded(true);
		availableDeductionAddButtonConfig.setComponentName("ADD");
		componentList.add(availableDeductionAddButtonConfig);
		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfDeductionsTabAddAction.class.getName());
		customAction.addActionParameter(viewId);
		addActionConfigList.add(customAction);
		availableDeductionAddButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);
	}

	private void addDeductionTabSelectedDeductionsResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		GtnUIFrameworkComponentConfig selectedDeductionsResultPanelConfig = componentConfigProvider
				.getPanelConfig(viewId + "selectedDeductionsResultPanel", true, parentId);
		selectedDeductionsResultPanelConfig.setComponentName("Selected Deductions");
		componentList.add(selectedDeductionsResultPanelConfig);
		addSelectedDeductionsResultLayout(componentList, selectedDeductionsResultPanelConfig.getComponentId(), viewId);
	}

	private void addSelectedDeductionsResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig selectedDeductionsMainlayoutConfig = componentConfigProvider
				.getVerticalLayoutConfig(viewId + "selectedDeductionsMainlayout", true, parentId);
		selectedDeductionsMainlayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		selectedDeductionsMainlayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_10);
		componentList.add(selectedDeductionsMainlayoutConfig);
		addMassUpdatePanel(componentList, selectedDeductionsMainlayoutConfig.getComponentId(), viewId);
		addSelectedDeductionsPagedTableComponent(componentList, selectedDeductionsMainlayoutConfig.getComponentId(),
				viewId);

	}

	private void addMassUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig massUpdatePanelConfig = componentConfigProvider
				.getPanelConfig(viewId + "selectedDeductionsmassUpdatePanel", true, parentId);
		massUpdatePanelConfig.setComponentName("Mass Update");
		massUpdatePanelConfig.setAuthorizationIncluded(true);
		componentList.add(massUpdatePanelConfig);
		addMassUpdateLayout(componentList, massUpdatePanelConfig.getComponentId(), viewId);
	}

	private void addMassUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String massUpdateCssLayoutId = viewId + "selectedDeductionssmassUpdateCsslayout";
		GtnUIFrameworkComponentConfig massUpdateCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(massUpdateCssLayoutId, true, parentId);
		massUpdateCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_20);
		componentList.add(massUpdateCssLayoutConfig);

		addMassUpdateOptionGroup(componentList, massUpdateCssLayoutConfig.getComponentId(), viewId);
		addMassUpdateDdlb(componentList, massUpdateCssLayoutConfig.getComponentId(), viewId);
		addMassUpdatePopUpField(componentList, massUpdateCssLayoutConfig.getComponentId(), viewId);
		addMassUpdateAddSubtractField(componentList, massUpdateCssLayoutConfig.getComponentId(), viewId);
		addMassUpdatePopulateButton(componentList, massUpdateCssLayoutConfig.getComponentId(), viewId);
	}

	private void addMassUpdateOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "selectedDeductionsmassUpdateOptionGroup";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massUpdateConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		massUpdateConfig.setAuthorizationIncluded(true);
		massUpdateConfig.setComponentName(GtnFrameworkNSFConstants.getEmpty());
		componentList.add(massUpdateConfig);

		GtnUIFrameworkOptionGroupConfig massUpdateOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		massUpdateOptionGroupConfig.setValuesFromService(false);
		massUpdateOptionGroupConfig.setItemValues(
				Arrays.asList(GtnFrameworkNSFConstants.getEnable(), GtnFrameworkNSFConstants.getDisable()));
		massUpdateConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		massUpdateOptionGroupConfig.setDefaultSelection(GtnFrameworkNSFConstants.getDisable());
		massUpdateConfig.setGtnUIFrameworkOptionGroupConfig(massUpdateOptionGroupConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		customAction.setFieldValues(Arrays.asList(viewId + "selectedDeductionsmassUpdateDdlb",
				viewId + "selectedDeductionsmassUpdateNetSalesRuleNo", viewId + "selectedDeductionsAddSubtractDdlb",
				viewId + "selectedDeductionsmassUpdatePopulateButton"));
		actionConfigList.add(customAction);
		massUpdateConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addMassUpdateDdlb(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + "selectedDeductionsmassUpdateDdlb";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massUpdateDdlbConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateDdlbConfig.setAuthorizationIncluded(true);
		massUpdateDdlbConfig.setComponentName("Field");
		componentList.add(massUpdateDdlbConfig);

		GtnUIFrameworkComboBoxConfig massUpdateComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		massUpdateComboBoxConfig.setItemValues(Arrays.asList(GtnFrameworkNSFConstants.getAddSubtractIndicator(),
				GtnFrameworkNSFConstants.getNetSalesRuleNo()));
		massUpdateDdlbConfig.setGtnComboboxConfig(massUpdateComboBoxConfig);
		massUpdateDdlbConfig.setEnable(false);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfDeductionMassFieldValueChangeAction.class.getName());
		customAction.addActionParameter(viewId);
		actionConfigList.add(customAction);
		massUpdateDdlbConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addMassUpdatePopUpField(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "selectedDeductionsmassUpdateNetSalesRuleNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig netSalesRuleConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		netSalesRuleConfig.setAuthorizationIncluded(true);
		netSalesRuleConfig.setComponentName("Value");
		netSalesRuleConfig.addComponentStyle(GtnFrameworkCssConstants.SEARCHICON);
		netSalesRuleConfig.setVisible(false);
		netSalesRuleConfig.setEnable(false);
		componentList.add(netSalesRuleConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParamList = new ArrayList<>();
		popupActionParamList.add("CDRPopUpSearchSearchView");
		popupActionParamList.add("Net Sales Rule");
		popupActionParamList.add("70%");
		popupActionParamList.add("70%");

		popupActionConfig.setActionParameterList(popupActionParamList);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> defaultDataMap = new HashMap<>();

		defaultDataMap.put("ruleType", 1704);
		defaultDataMap.put("ruleNo", "");
		defaultDataMap.put("ruleName", "");
		defaultDataMap.put("ruleCategory", null);
		defaultDataMap.put("cDRPopUpsearchResultTable", "");
		defaultDataMap.put("cDRRulePopUpruleDetailsattachResultTable", null);

		setDefaultActionConfig.addActionParameter(defaultDataMap);
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig customSetCongigAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customSetCongigAction.addActionParameter(GtnUiFrameworkSetSelectionAction.class.getName());

		actionConfigList.add(customSetCongigAction);
		netSalesRuleConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addMassUpdateAddSubtractField(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "selectedDeductionsAddSubtractDdlb";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massUpdateDdlbConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateDdlbConfig.setAuthorizationIncluded(true);
		massUpdateDdlbConfig.setComponentName("Value");
		massUpdateDdlbConfig.setVisible(true);
		massUpdateDdlbConfig.setEnable(false);
		componentList.add(massUpdateDdlbConfig);

		GtnUIFrameworkComboBoxConfig massUpdateComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		massUpdateComboBoxConfig.setItemValues(
				Arrays.asList(GtnFrameworkNSFConstants.getAdd(), GtnFrameworkNSFConstants.getSubtract()));
		massUpdateDdlbConfig.setGtnComboboxConfig(massUpdateComboBoxConfig);
	}

	private void addMassUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "selectedDeductionsmassUpdatePopulateButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massUpdatePopulateButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		massUpdatePopulateButtonConfig.setAuthorizationIncluded(true);
		massUpdatePopulateButtonConfig.setComponentName("POPULATE");
		massUpdatePopulateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		massUpdatePopulateButtonConfig.setEnable(false);
		componentList.add(massUpdatePopulateButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig populateValidationAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object customClassName = GtnUiFrameworkNsfPopulateValidationAction.class.getName();
		populateValidationAction.setActionParameterList(Arrays.asList(customClassName, false));

		actionConfigList.add(populateValidationAction);

		GtnUIFrameWorkActionConfig populateAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object populateActionClass = GtnUiFrameworkNsfDeductionPopulateAction.class.getName();
		populateAction.addActionParameter(populateActionClass);
		populateAction.addActionParameter(viewId);

		actionConfigList.add(populateAction);
		massUpdatePopulateButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSelectedDeductionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String componentId = viewId + "selectedDeductionsResultTable";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedDeductionsResultTableConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.PAGEDTABLE);
		selectedDeductionsResultTableConfig.setAuthorizationIncluded(true);
		selectedDeductionsResultTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		selectedDeductionsResultTableConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(selectedDeductionsResultTableConfig);

		GtnUIFrameworkPagedTableConfig selectedDeductionsResultTable = componentConfigProvider.getPagedTableConfig(true,
				true, GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWsNsfCommonConstants.GTN_NSF_SELECTED_DEDUCTIONS_SEARCH_QUERY_NAME,
				GtnWsNsfCommonConstants.GTN_NSF_SELECTED_DEDUCTIONS_SEARCH_QUERY_NAME);
		selectedDeductionsResultTable.setEditable(false);
		selectedDeductionsResultTable.setItemPerPage(5);
		selectedDeductionsResultTable.setPageLength(15);
		selectedDeductionsResultTable.setSinkItemPerPageWithPageLength(false);
		selectedDeductionsResultTable.setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class,
				String.class, String.class, String.class, String.class });

		selectedDeductionsResultTable
				.setTableVisibleHeader(GtnFrameworkNSFConstants.getSelectedDeductionsVisibleHeaders());
		selectedDeductionsResultTable
				.setTableColumnMappingId(GtnFrameworkNSFConstants.getSelectedDeductionsVisibleColumns());
		selectedDeductionsResultTable.setExtraColumn(new Object[] { GtnFrameworkNSFConstants.getSystemid() });
		selectedDeductionsResultTable.setExtraColumnDataType(new Class<?>[] { Integer.class });
		selectedDeductionsResultTable.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		selectedDeductionsResultTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		selectedDeductionsResultTable.setEditable(true);
		selectedDeductionsResultTable.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
				GtnFrameworkCommonConstants.INDICATOR, "netSalesRuleNo", "netSalesRuleName", "ruleNo", "ruleName"));
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterMap = getSelectedDeductionFilterConfigMap();
		selectedDeductionsResultTable.setCustomFilterConfigMap(customFilterMap);
		selectedDeductionsResultTable.setEditableField(createTableFieldFactoryComponents());

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkNsfDeductionTabCheckAllAction.class.getName());
		actionConfigList.add(checkAllAction);
		selectedDeductionsResultTable.setColumnCheckActionConfigList(actionConfigList);
		selectedDeductionsResultTableConfig.setGtnPagedTableConfig(selectedDeductionsResultTable);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getSelectedDeductionFilterConfigMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterMap = getDeductionTabCustomFilterConfig();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.INDICATOR);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customFilterConfig.setGtnComponentConfig(getIndicatorComboBoxConfig());
		customFilterMap.put(GtnFrameworkCommonConstants.INDICATOR, customFilterConfig);
		return customFilterMap;
	}

	private void addResetRemoveExcelButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String resetRemoveExcelButtonLayout = viewId + "selectedDeductionsresetRemoveExcelButtonLayout";
		GtnUIFrameworkComponentConfig resetRemoveExcelButtonLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(resetRemoveExcelButtonLayout, true, parentId);
		componentList.add(resetRemoveExcelButtonLayoutConfig);
		addResetButton(componentList, resetRemoveExcelButtonLayoutConfig.getComponentId(), viewId);
		addRemoveButton(componentList, resetRemoveExcelButtonLayoutConfig.getComponentId(), viewId);
		addExcelButton(componentList, resetRemoveExcelButtonLayoutConfig.getComponentId(), viewId);

	}

	private void addResetButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + "selectedDeductionsTableResetButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedDeductionsTableResetButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		selectedDeductionsTableResetButtonConfig.setAuthorizationIncluded(true);
		selectedDeductionsTableResetButtonConfig.setComponentName(GtnFrameworkCommonConstants.RESET);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object populateActionClass = GtnUiFrameworkNsfTabTableResetConfirmationAction.class.getName();
		resetActionConfig.addActionParameter(populateActionClass);
		resetActionConfig.addActionParameter(viewId);
		resetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Selected Deductions group box?");
		resetActionConfig.addActionParameter(false);
		actionConfigList.add(resetActionConfig);
		componentList.add(selectedDeductionsTableResetButtonConfig);
		selectedDeductionsTableResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRemoveButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + "selectedDeductionsTableRemoveButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedDeductionsTableRemoveButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		selectedDeductionsTableRemoveButtonConfig.setAuthorizationIncluded(true);
		selectedDeductionsTableRemoveButtonConfig.setComponentName("REMOVE");
		componentList.add(selectedDeductionsTableRemoveButtonConfig);

		List<GtnUIFrameWorkActionConfig> removeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Remove Confirmation");
		alertParamsList
				.add("Are you sure you want to remove the selected value from the ‘Selected Deductions’ list view?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig removeActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object populateActionClass = GtnUiFrameworkNsfRemoveAction.class.getName();
		removeActionConfig.addActionParameter(populateActionClass);
		removeActionConfig.addActionParameter(viewId);
		removeActionConfig.addActionParameter(false);
		onSucessActionConfigList.add(removeActionConfig);
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		removeActionConfigList.add(confirmationActionConfig);
		selectedDeductionsTableRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(removeActionConfigList);

	}

	private void addExcelButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + "selectedDeductionsExcelButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedDeductionsTableExcelButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.EXCEL_BUTTON);
		selectedDeductionsTableExcelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(selectedDeductionsTableExcelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfigProvider
				.getExcelBtnconfig("Deductions", true, viewId + "selectedDeductionsResultTable", false);
		gtnUIFrameworkExcelButtonConfig
				.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		selectedDeductionsTableExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		selectedDeductionsTableExcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getDeductionTabCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> deductionTabCustomFilterConfigMap = new HashMap<>();
		String[] deductionTabPropertyIds = { "deductionType", "deductionSubType", "deductionCategory",
				GtnFrameworkCommonConstants.MARKET_TYPE };
		String[] deductionTabListNameArray = { GtnFrameworkNSFComboBoxTypeConstants.RS_TYPE,
				GtnFrameworkNSFComboBoxTypeConstants.REBATE_PROGRAM_TYPE,
				GtnFrameworkNSFComboBoxTypeConstants.RS_CATEGORY, GtnFrameworkNSFComboBoxTypeConstants.CONTRACT_TYPE };
		for (int i = 0; i < deductionTabPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig deductionTabCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			deductionTabCustomFilterConfig.setPropertId(deductionTabPropertyIds[i]);
			deductionTabCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig deductionTabCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			deductionTabCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			deductionTabCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			deductionTabCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			deductionTabCustomFilterComponentConfig.getGtnComboboxConfig()
					.setComboBoxType(deductionTabListNameArray[i]);
			deductionTabCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			deductionTabCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			deductionTabCustomFilterConfig.setGtnComponentConfig(deductionTabCustomFilterComponentConfig);
			deductionTabCustomFilterConfigMap.put(deductionTabCustomFilterConfig.getPropertId(),
					deductionTabCustomFilterConfig);

		}
		return deductionTabCustomFilterConfigMap;
	}

	public List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents() {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();

		GtnUIFrameworkComponentConfig checkBoxFieldConfig = new GtnUIFrameworkComponentConfig();
		checkBoxFieldConfig.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		getFieldFactoryActionConfig(checkBoxFieldConfig);
		editableFields.add(checkBoxFieldConfig);

		GtnUIFrameworkComponentConfig indicatorFieldConfig = getIndicatorComboBoxConfig();
		getFieldFactoryActionConfig(indicatorFieldConfig);
		editableFields.add(indicatorFieldConfig);

		GtnUIFrameworkComponentConfig ruleNoBoxFieldConfig = getNetSalesPopupConfig();
		getFieldFactoryActionConfig(ruleNoBoxFieldConfig);
		editableFields.add(ruleNoBoxFieldConfig);

		GtnUIFrameworkComponentConfig ruleNameBoxFieldConfig = getNetSalesPopupConfig();
		editableFields.add(ruleNameBoxFieldConfig);

		GtnUIFrameworkComponentConfig ruleNoFieldConfig = getNetSalesPopupConfig();
		getFieldFactoryActionConfig(ruleNoFieldConfig);
		editableFields.add(ruleNoFieldConfig);

		GtnUIFrameworkComponentConfig ruleNameFieldConfig = getNetSalesPopupConfig();
		editableFields.add(ruleNameFieldConfig);

		return editableFields;
	}

	private GtnUIFrameworkComponentConfig getIndicatorComboBoxConfig() {
		GtnUIFrameworkComponentConfig indicatorFieldConfig = new GtnUIFrameworkComponentConfig();
		indicatorFieldConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setItemValues(Arrays.asList("+", "-"));
		comboBoxConfig.setItemCaptionValues(Arrays.asList("Add", "Subtract"));
		indicatorFieldConfig.setGtnComboboxConfig(comboBoxConfig);
		return indicatorFieldConfig;
	}

	private GtnUIFrameworkComponentConfig getNetSalesPopupConfig() {
		GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
		fieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParamList = new ArrayList<>();
		popupActionParamList.add(GtnFrameworkNSFConstants.getNetSalesRulePopupView());
		popupActionParamList.add("Net Sales Rule");
		popupActionParamList.add("70%");
		popupActionConfig.setActionParameterList(popupActionParamList);
		fieldConfig.addComponentStyle("searchicon");
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnUIFrameWorkNsfNetSalesRulePopupSelectAction.class.getName());
		fieldConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
		fieldConfig.addGtnUIFrameWorkActionConfig(selectAction);
		return fieldConfig;
	}

	private void getFieldFactoryActionConfig(GtnUIFrameworkComponentConfig fieldConfig) {
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfDeductionsTabFieldFactoryAction.class.getName());
		actionConfigList.add(customAction);
		fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);
	}
}
