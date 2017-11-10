package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkCompanyItemAdditionSearchAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkSearchFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.duallistboxaction.GtnFrameworkItemAdditionTableRecordMoveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkItemAdditionTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardItemAdditionTabConfig {

	private static final String VALUE = "Value";
	private static final String LEFT_RESULT_TABLE = "leftResultTable";
	private static final String RIGHT_RESULT_TABLE = "rightResultTable";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdItemAdditionTabPrefix = mainNamspacePrefix + "IADT";
		GtnUIFrameworkTabConfig cdItemAdditionTabConfig = new GtnUIFrameworkTabConfig();
		cdItemAdditionTabConfig.setComponentId(cdItemAdditionTabPrefix + "itemAdditionTab");
		cdItemAdditionTabConfig.setTabCaption("Item Addition");
		List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList = new ArrayList<>();
		cdItemAdditionTabConfig.setTabLayoutComponentConfigList(cdItemAdditionComponentList);
		GtnUIFrameworkComponentConfig scrollableHorizontalLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				cdItemAdditionTabConfig.getComponentId(), true, cdItemAdditionTabConfig.getComponentId());
		scrollableHorizontalLayoutConfig.setTabComponent(true);
		cdItemAdditionComponentList.add(scrollableHorizontalLayoutConfig);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdItemAdditionTabConfig.getComponentId() + "verticalLayout", true,
				scrollableHorizontalLayoutConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdItemAdditionComponentList.add(gtnLayoutConfig);
		addEditTableLayout(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		viewResultDataTable(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemAdditionTabLoadAction.class.getName());
		customAction.addActionParameter(cdItemAdditionTabPrefix + "editCssLayout");
		customAction.addActionParameter(
				cdItemAdditionTabPrefix + "rightResultTableView" + GtnFrameworkCommonStringConstants.LAYOUT);
		customAction.addActionParameter(cdItemAdditionTabPrefix + RIGHT_RESULT_TABLE);
		scrollableHorizontalLayoutConfig.addGtnUIFrameWorkActionConfig(customAction);
		return cdItemAdditionTabConfig;
	}

	private void addEditTableLayout(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdItemAdditionTabPrefix + "editCssLayout", true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);
		addSearchFieldLayout(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		addDualListBoxLayout(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addSearchFieldLayout(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdItemAdditionTabPrefix + "searchCssLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		cdItemAdditionComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchType(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		addSearchFields(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addSearchValue(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addSearchButtonComponent(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void addSearchType(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig searchTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		searchTypeConfig.setAuthorizationIncluded(true);
		searchTypeConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdItemAdditionComponentList.add(searchTypeConfig);

		GtnUIFrameworkOptionGroupConfig searchTypeOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		searchTypeOptionConfig.setValuesFromService(false);

		searchTypeOptionConfig
				.setItemValues(Arrays.asList(GtnWsContractDashboardContants.ITEM, GtnWsContractDashboardContants.IFP));
		searchTypeOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.IFP);

		searchTypeConfig.setGtnUIFrameworkOptionGroupConfig(searchTypeOptionConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkSearchFieldValueChangeAction.class.getName());
		customAction.addActionParameter(
				Arrays.asList(cdItemAdditionTabPrefix + "Combo_IFP", cdItemAdditionTabPrefix + "Combo_Item"));
		customAction.addActionParameter(GtnWsContractDashboardContants.IFP);
		actionConfigList.add(customAction);
		searchTypeConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addSearchFields(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				cdItemAdditionTabPrefix + "SearchField" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig ifpConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Combo_IFP", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		ifpConfig.setAuthorizationIncluded(true);
		ifpConfig.setComponentName("Search Field");
		cdItemAdditionComponentList.add(ifpConfig);

		GtnUIFrameworkComboBoxConfig ifpComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		ifpComboBoxConfig.setItemValues(
				GtnWsContractDashboardContants.getItemAdditionMappedFieldValue(GtnWsContractDashboardContants.IFP));
		ifpConfig.setGtnComboboxConfig(ifpComboBoxConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ifpConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkSearchFieldValueChangeAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(GtnWsContractDashboardContants
				.getItemAdditionMappedFieldValue(GtnWsContractDashboardContants.IFP).get(0));

		ifpConfig.addGtnUIFrameWorkActionConfig(customAction);

		GtnUIFrameworkComponentConfig itemConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Combo_Item", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemConfig.setAuthorizationIncluded(true);
		itemConfig.setComponentName("Search Field");
		itemConfig.setVisible(false);
		cdItemAdditionComponentList.add(itemConfig);

		GtnUIFrameworkComboBoxConfig itemComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		itemComboBoxConfig.setItemValues(
				GtnWsContractDashboardContants.getItemAdditionMappedFieldValue(GtnWsContractDashboardContants.ITEM));
		itemConfig.setGtnComboboxConfig(itemComboBoxConfig);
		itemConfig.setGtnUIFrameworkValidationConfig(valConfig);
		itemConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addSearchValue(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				cdItemAdditionTabPrefix + VALUE + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig ifpNoConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_IFPNo", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpNoConfig.setAuthorizationIncluded(true);
		ifpNoConfig.setComponentName(VALUE);
		cdItemAdditionComponentList.add(ifpNoConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(ifpNoConfig.getComponentId());

		GtnUIFrameworkComponentConfig ifpNameConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_IFPName", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpNameConfig.setAuthorizationIncluded(true);
		ifpNameConfig.setComponentName(VALUE);
		ifpNameConfig.setVisible(false);
		cdItemAdditionComponentList.add(ifpNameConfig);
		ifpNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(ifpNameConfig.getComponentId());

		GtnUIFrameworkComponentConfig ndc8Config = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_NDC8", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		ndc8Config.setAuthorizationIncluded(true);
		ndc8Config.setComponentName(VALUE);
		ndc8Config.setVisible(false);
		cdItemAdditionComponentList.add(ndc8Config);
		ndc8Config.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(ndc8Config.getComponentId());

		GtnUIFrameworkComponentConfig ndc9Config = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_NDC9", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		ndc9Config.setAuthorizationIncluded(true);
		ndc9Config.setComponentName(VALUE);
		ndc9Config.setVisible(false);
		cdItemAdditionComponentList.add(ndc9Config);
		ndc9Config.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(ndc9Config.getComponentId());

		GtnUIFrameworkComponentConfig brandNameConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_BrandName", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		brandNameConfig.setAuthorizationIncluded(true);
		brandNameConfig.setComponentName(VALUE);
		brandNameConfig.setVisible(false);
		cdItemAdditionComponentList.add(brandNameConfig);
		brandNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(brandNameConfig.getComponentId());

		GtnUIFrameworkComponentConfig itemNameConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_ItemName", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNameConfig.setAuthorizationIncluded(true);
		itemNameConfig.setComponentName(VALUE);
		itemNameConfig.setVisible(false);
		cdItemAdditionComponentList.add(itemNameConfig);
		itemNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(itemNameConfig.getComponentId());

		GtnUIFrameworkComponentConfig itemNoConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_ItemNo", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNoConfig.setAuthorizationIncluded(true);
		itemNoConfig.setComponentName(VALUE);
		itemNoConfig.setVisible(false);
		cdItemAdditionComponentList.add(itemNoConfig);
		itemNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(itemNoConfig.getComponentId());

		GtnUIFrameworkComponentConfig itemDescriptionConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Text_ItemDescription", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemDescriptionConfig.setAuthorizationIncluded(true);
		itemDescriptionConfig.setComponentName(VALUE);
		itemDescriptionConfig.setVisible(false);
		cdItemAdditionComponentList.add(itemDescriptionConfig);
		itemDescriptionConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(itemDescriptionConfig.getComponentId());

		GtnUIFrameworkComponentConfig formConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Combo_Form", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		formConfig.setAuthorizationIncluded(true);
		formConfig.setComponentName(VALUE);
		formConfig.setVisible(false);
		cdItemAdditionComponentList.add(formConfig);
		GtnUIFrameworkValidationConfig val1Config = new GtnUIFrameworkValidationConfig();
		val1Config.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		formConfig.setGtnUIFrameworkValidationConfig(val1Config);
		GtnUIFrameworkComboBoxConfig formComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		formComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		formComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.FORM);
		formConfig.setGtnComboboxConfig(formComboBoxConfig);
		componentIdList.add(formConfig.getComponentId());

		GtnUIFrameworkComponentConfig strengthConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Combo_Strength", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		strengthConfig.setAuthorizationIncluded(true);
		strengthConfig.setComponentName(VALUE);
		strengthConfig.setVisible(false);
		cdItemAdditionComponentList.add(strengthConfig);
		strengthConfig.setGtnUIFrameworkValidationConfig(val1Config);
		GtnUIFrameworkComboBoxConfig strengthComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		strengthComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		strengthComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STRENGTH);
		strengthConfig.setGtnComboboxConfig(strengthComboBoxConfig);
		componentIdList.add(strengthConfig.getComponentId());

		GtnUIFrameworkComponentConfig therapeuticClassConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemAdditionTabPrefix + "Combo_TherapeuticClass", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		therapeuticClassConfig.setAuthorizationIncluded(true);
		therapeuticClassConfig.setComponentName(VALUE);
		therapeuticClassConfig.setVisible(false);
		cdItemAdditionComponentList.add(therapeuticClassConfig);
		therapeuticClassConfig.setGtnUIFrameworkValidationConfig(val1Config);
		GtnUIFrameworkComboBoxConfig therapeuticClassComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		therapeuticClassComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		therapeuticClassComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.THERAPEUTIC_CLASS);
		therapeuticClassConfig.setGtnComboboxConfig(therapeuticClassComboBoxConfig);
		componentIdList.add(therapeuticClassConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdItemAdditionTabPrefix + "SearchBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_100);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setComponentName("Search");
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		cdItemAdditionComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig searchAction = new GtnUIFrameWorkActionConfig();
		searchAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchAction.addActionParameter(GtnUIFrameworkCompanyItemAdditionSearchAction.class.getName());
		searchAction.addActionParameter(componentIdList);
		searchAction.addActionParameter(cdItemAdditionTabPrefix + "SearchType");
		searchAction.addActionParameter(cdItemAdditionTabPrefix + "Combo_");
		searchAction.addActionParameter("IFP No");
		searchAction.addActionParameter(GtnFrameworkContractDashboardContants.ERROR_DISPLAY_LABEL);
		searchAction.addActionParameter(Arrays.asList(GtnFrameworkContractDashboardContants.MSG_SEARCH_FIELD,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_TEXT_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_COMBO_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_COMBO_VALUE,
				GtnFrameworkContractDashboardContants.MSG_SEARCH_COMBO_VALUE));
		searchAction.addActionParameter(cdItemAdditionTabPrefix + LEFT_RESULT_TABLE);
		componentConfig.addGtnUIFrameWorkActionConfig(searchAction);
	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "dualListBox";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		leftResultDataTable(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveButtonsLayout(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		rightResultDataTable(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void moveButtonsLayout(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdItemAdditionTabPrefix + "dualListBoxButton" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);
		gtnLayoutConfig.setMargin(true);
		gtnLayoutConfig.setSpacing(true);
		gtnLayoutConfig.addComponentStyle("gtnGrid-DualList-Buttons");
		moveRight(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveLeft(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveAllRight(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
		moveAllLeft(cdItemAdditionComponentList, cdItemAdditionTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "moveRightBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" > ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdItemAdditionComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(cdItemAdditionTabPrefix + LEFT_RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig failureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("Error");
		failureActionConfig.addActionParameter("Please select a Item to add");

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkItemAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdItemAdditionTabPrefix + LEFT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_RIGHT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkItemAdditionTabLoadAction.class.getName());

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(moveAction, tabLoadAction));

		componentConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "moveLeftBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" < ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdItemAdditionComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(cdItemAdditionTabPrefix + RIGHT_RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig failureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("Error");
		failureActionConfig.addActionParameter("Please select a Item to remove");

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkItemAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdItemAdditionTabPrefix + RIGHT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_LEFT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkItemAdditionTabLoadAction.class.getName());

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(moveAction, tabLoadAction));

		componentConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void moveAllRight(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "moveRightBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig itemMoveRightConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		itemMoveRightConfig.setAuthorizationIncluded(true);
		itemMoveRightConfig.setComponentName(" >> ");
		itemMoveRightConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdItemAdditionComponentList.add(itemMoveRightConfig);

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkItemAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdItemAdditionTabPrefix + LEFT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_ALL_RIGHT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkItemAdditionTabLoadAction.class.getName());

		itemMoveRightConfig.addGtnUIFrameWorkActionConfig(moveAction);
		itemMoveRightConfig.addGtnUIFrameWorkActionConfig(tabLoadAction);
	}

	private void moveAllLeft(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "moveLeftBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig itemMoveLeftConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		itemMoveLeftConfig.setAuthorizationIncluded(true);
		itemMoveLeftConfig.setComponentName(" << ");
		itemMoveLeftConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdItemAdditionComponentList.add(itemMoveLeftConfig);

		GtnUIFrameWorkActionConfig moveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveAction.addActionParameter(GtnFrameworkItemAdditionTableRecordMoveAction.class.getName());
		moveAction.addActionParameter(cdItemAdditionTabPrefix + RIGHT_RESULT_TABLE);
		moveAction.addActionParameter(GtnWsContractDashboardContants.ITEM_ADDITION_MOVE_ALL_LEFT);

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkItemAdditionTabLoadAction.class.getName());

		itemMoveLeftConfig.addGtnUIFrameWorkActionConfig(moveAction);
		itemMoveLeftConfig.addGtnUIFrameWorkActionConfig(tabLoadAction);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + LEFT_RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemAdditionLeftTableConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		cdItemAdditionLeftTableConfig.setAuthorizationIncluded(true);
		cdItemAdditionLeftTableConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		cdItemAdditionLeftTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		cdItemAdditionComponentList.add(cdItemAdditionLeftTableConfig);

		GtnUIFrameworkValidationConfig cdItemAdditionLeftTableValConfig = new GtnUIFrameworkValidationConfig();
		cdItemAdditionLeftTableValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdItemAdditionLeftTableConfig.setGtnUIFrameworkValidationConfig(cdItemAdditionLeftTableValConfig);

		GtnUIFrameworkPagedTableConfig cdItemAdditionLeftTable = new GtnUIFrameworkPagedTableConfig();
		cdItemAdditionLeftTableConfig.setGtnPagedTableConfig(cdItemAdditionLeftTable);
		cdItemAdditionLeftTable.setFilterBar(true);
		cdItemAdditionLeftTable.setSelectable(true);
		cdItemAdditionLeftTable.setSinkItemPerPageWithPageLength(false);
		cdItemAdditionLeftTable.setTableColumnDataType(GtnWsContractDashboardContants.getItemAdditionColumnType());
		cdItemAdditionLeftTable.setTableVisibleHeader(GtnWsContractDashboardContants.getItemAdditionHeader());
		cdItemAdditionLeftTable.setTableColumnMappingId(GtnWsContractDashboardContants.getItemAdditionColumn());
		cdItemAdditionLeftTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_LEFT_TABLE_DATA);
		cdItemAdditionLeftTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_LEFT_TABLE_DATA);

	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + RIGHT_RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemAdditionRightTableConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		cdItemAdditionRightTableConfig.setAuthorizationIncluded(true);
		cdItemAdditionRightTableConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		cdItemAdditionRightTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		cdItemAdditionComponentList.add(cdItemAdditionRightTableConfig);

		GtnUIFrameworkValidationConfig cdItemAdditionRightTableValConfig = new GtnUIFrameworkValidationConfig();
		cdItemAdditionRightTableValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdItemAdditionRightTableConfig.setGtnUIFrameworkValidationConfig(cdItemAdditionRightTableValConfig);

		GtnUIFrameworkPagedTableConfig cdItemAdditionRightTable = new GtnUIFrameworkPagedTableConfig();
		cdItemAdditionRightTableConfig.setGtnPagedTableConfig(cdItemAdditionRightTable);
		cdItemAdditionRightTable.setFilterBar(true);
		cdItemAdditionRightTable.setSelectable(true);
		cdItemAdditionRightTable.setSinkItemPerPageWithPageLength(false);
		cdItemAdditionRightTable.setTableColumnDataType(GtnWsContractDashboardContants.getItemAdditionColumnType());
		cdItemAdditionRightTable.setTableVisibleHeader(GtnWsContractDashboardContants.getItemAdditionHeader());
		cdItemAdditionRightTable.setTableColumnMappingId(GtnWsContractDashboardContants.getItemAdditionColumn());
		cdItemAdditionRightTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_RIGHT_TABLE_DATA);
		cdItemAdditionRightTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_RIGHT_TABLE_DATA);

	}

	private void viewResultDataTable(List<GtnUIFrameworkComponentConfig> cdItemAdditionComponentList,
			String cdItemAdditionTabPrefix, String parent) {
		String componentId = cdItemAdditionTabPrefix + "rightResultTableView";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdItemAdditionComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdItemAdditionComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig viewResultDataTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(viewResultDataTable);
		viewResultDataTable.setPageLength(10);
		viewResultDataTable.setItemPerPage(10);
		viewResultDataTable.setSinkItemPerPageWithPageLength(false);
		viewResultDataTable.setTableColumnDataType(GtnWsContractDashboardContants.getItemAdditionColumnType());
		viewResultDataTable.setTableVisibleHeader(GtnWsContractDashboardContants.getItemAdditionHeader());
		viewResultDataTable.setTableColumnMappingId(GtnWsContractDashboardContants.getItemAdditionColumn());
		viewResultDataTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_RIGHT_TABLE_DATA);
		viewResultDataTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEM_ADDITION_RIGHT_TABLE_DATA);

	}
}
