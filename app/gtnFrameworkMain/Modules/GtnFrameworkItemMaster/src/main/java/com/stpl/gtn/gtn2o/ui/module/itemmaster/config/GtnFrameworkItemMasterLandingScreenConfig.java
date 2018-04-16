package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

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
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkItemMasterLandingScreenConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig itemMasterSearchView = componentConfig.getViewConfig("Search View", "V001", true);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
		itemMasterSearchView.addViewAction(customAction);
		addComponentList(itemMasterSearchView, componentConfig);
		return itemMasterSearchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList, componentConfig);
		addButtonLayout(componentList, componentConfig);
		addResultPanel(componentList, componentConfig);
		addActionButtonLayout(componentList, componentConfig);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterSearchPanel = componentConfig.getPanelConfig("itemSearchCriteriaPanel",
				false, null);
		itemMasterSearchPanel.setComponentName("Search Criteria");
		itemMasterSearchPanel.setAuthorizationIncluded(true);
		componentList.add(itemMasterSearchPanel);

		addFieldLayout(componentList, componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterResultPanelConfig = componentConfig.getPanelConfig("ItemResultPanel",
				false, null);
		itemMasterResultPanelConfig.setComponentName("Results");
		itemMasterResultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(itemMasterResultPanelConfig);

		addResultLayout(componentList, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig resultTableLayout = componentConfig.getHorizontalLayoutConfig("ItemResultlayout",
				true, "ItemResultPanel");
		resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableLayout);

		addPagedTableComponent(componentList, componentConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterSearchLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT, true, "itemSearchCriteriaPanel",
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(itemMasterSearchLayout);
		addFieldComponent(componentList, componentConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT, false, null);
		itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		itemMasterButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(itemMasterButtonLayout);

		addSearchButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);

	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addSystemId(componentList, componentConfig);
		addItemId(componentList, componentConfig);
		addItemNo(componentList, componentConfig);
		addItemName(componentList, componentConfig);
		addItemDesc(componentList, componentConfig);
		addItemStatus(componentList, componentConfig);
		addItemType(componentList, componentConfig);
		addTherapyClass(componentList, componentConfig);
		addNDC9(componentList, componentConfig);
		addForm(componentList, componentConfig);
		addItemQualifierName(componentList, componentConfig);
		addItemIdentifier(componentList, componentConfig);
		addBrand(componentList, componentConfig);
		addNDC8(componentList, componentConfig);
		addItemStrength(componentList, componentConfig);
		addItemBatchId(componentList, componentConfig);
	}

	private void addSystemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig systemIdLayout = componentConfig.getHorizontalLayoutConfig(
				"itemSearchSystemIdlayout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(systemIdLayout);

		GtnUIFrameworkComponentConfig systemIdConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_SYSTEM_ID, true, systemIdLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		systemIdConfig.setAuthorizationIncluded(true);
		systemIdConfig.setComponentName("System ID");

		GtnUIFrameworkTextBoxConfig systemIdMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		systemIdMaxLengthConfig.setMaximumLength(38);

		systemIdConfig.setGtnTextBoxConfig(systemIdMaxLengthConfig);

		GtnUIFrameworkValidationConfig systemIdValidationConfig = new GtnUIFrameworkValidationConfig();
		systemIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		systemIdConfig.setGtnUIFrameworkValidationConfig(systemIdValidationConfig);

		componentList.add(systemIdConfig);
	}

	private void addItemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdLayout = componentConfig.getHorizontalLayoutConfig("itemIdlayout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemIdLayout);

		GtnUIFrameworkComponentConfig itemIdConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID, true, itemIdLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemIdConfig.setAuthorizationIncluded(true);
		itemIdConfig.setComponentName("Item ID");

		GtnUIFrameworkTextBoxConfig itemIdMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemIdMaxLengthConfig.setMaximumLength(38);
		itemIdConfig.setGtnTextBoxConfig(itemIdMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemIdValidationConfig = new GtnUIFrameworkValidationConfig();
		itemIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemIdConfig.setGtnUIFrameworkValidationConfig(itemIdValidationConfig);
		componentList.add(itemIdConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNoLayout = componentConfig.getHorizontalLayoutConfig("itemNolayout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemNoLayout);

		GtnUIFrameworkComponentConfig itemNoConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO, true, itemNoLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNoConfig.setAuthorizationIncluded(true);
		itemNoConfig.setComponentName("Item No");
		itemNoConfig.setParentComponentId("itemNolayout");

		GtnUIFrameworkTextBoxConfig itemNoMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemNoMaxLengthConfig.setMaximumLength(50);
		itemNoConfig.setGtnTextBoxConfig(itemNoMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemNoValidationConfig = new GtnUIFrameworkValidationConfig();
		itemNoValidationConfig.setMaxLength(50);
		itemNoValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemNoConfig.setGtnUIFrameworkValidationConfig(itemNoValidationConfig);

		componentList.add(itemNoConfig);
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNameLayout = componentConfig.getHorizontalLayoutConfig("itemNamelayout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		itemNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(itemNameLayout);

		GtnUIFrameworkComponentConfig itemNameConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME, true, itemNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNameConfig.setAuthorizationIncluded(true);
		itemNameConfig.setComponentName("Item Name");

		GtnUIFrameworkTextBoxConfig itemNameMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemNameMaxLengthConfig.setMaximumLength(100);
		itemNameConfig.setGtnTextBoxConfig(itemNameMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemNameValidationConfig = new GtnUIFrameworkValidationConfig();
		itemNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemNameConfig.setGtnUIFrameworkValidationConfig(itemNameValidationConfig);

		componentList.add(itemNameConfig);
	}

	private void addItemDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemDescLayout = componentConfig.getHorizontalLayoutConfig("itemDesclayout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemDescLayout);

		GtnUIFrameworkComponentConfig itemDescConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC, true, itemDescLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemDescConfig.setAuthorizationIncluded(true);
		itemDescConfig.setComponentName("Item Desc");

		GtnUIFrameworkTextBoxConfig itemDescMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemDescMaxLengthConfig.setMaximumLength(250);
		itemDescConfig.setGtnTextBoxConfig(itemDescMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemDescValidationConfig = new GtnUIFrameworkValidationConfig();
		itemDescValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemDescConfig.setGtnUIFrameworkValidationConfig(itemDescValidationConfig);

		componentList.add(itemDescConfig);
	}

	private void addItemStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemStatusLayout = componentConfig.getHorizontalLayoutConfig("itemStatuslayout",
				true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemStatusLayout);

		GtnUIFrameworkComponentConfig iMasterSearchItemStatus = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS, true, itemStatusLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		iMasterSearchItemStatus.setAuthorizationIncluded(true);
		iMasterSearchItemStatus.setComponentName("Item Status");

		GtnUIFrameworkValidationConfig itemStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		itemStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		iMasterSearchItemStatus.setGtnUIFrameworkValidationConfig(itemStatusValidationConfig);

		componentList.add(iMasterSearchItemStatus);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		iMasterSearchItemStatus.setGtnComboboxConfig(itemStatusConfig);
	}

	private void addItemType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemTypeLayout = componentConfig.getHorizontalLayoutConfig("itemTypelayout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemTypeLayout);

		GtnUIFrameworkComponentConfig iMasterSearchItemType = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, true, itemTypeLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		iMasterSearchItemType.setAuthorizationIncluded(true);
		iMasterSearchItemType.setComponentName("Item Type");
		componentList.add(iMasterSearchItemType);

		GtnUIFrameworkComboBoxConfig itemTypeConfig = componentConfig.getComboBoxConfig("ITEM_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig itemTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		itemTypeValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		iMasterSearchItemType.setGtnUIFrameworkValidationConfig(itemTypeValidationConfig);

		iMasterSearchItemType.setGtnComboboxConfig(itemTypeConfig);
	}

	private void addTherapyClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig therapyClassLayout = componentConfig.getHorizontalLayoutConfig(
				"therapyClasslayout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(therapyClassLayout);

		GtnUIFrameworkComponentConfig therapyClassConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.THERAPEUTIC_CLASS, true, therapyClassLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		therapyClassConfig.setAuthorizationIncluded(true);
		therapyClassConfig.setComponentName("Therapy Class");

		GtnUIFrameworkValidationConfig therapyClassValidationConfig = new GtnUIFrameworkValidationConfig();
		therapyClassValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		therapyClassConfig.setGtnUIFrameworkValidationConfig(therapyClassValidationConfig);

		componentList.add(therapyClassConfig);
	}

	private void addNDC9(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc9Layout = componentConfig.getHorizontalLayoutConfig("ndc9layout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(ndc9Layout);

		GtnUIFrameworkComponentConfig ndc9 = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9, true, ndc9Layout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		ndc9.setAuthorizationIncluded(true);
		ndc9.setComponentName("NDC 9");
		componentList.add(ndc9);

		GtnUIFrameworkComboBoxConfig ndc9Config = componentConfig.getComboBoxConfig("Ndc9ItemId",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig ndc9ValidationConfig = new GtnUIFrameworkValidationConfig();
		ndc9ValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ndc9.setGtnUIFrameworkValidationConfig(ndc9ValidationConfig);

		ndc9.setGtnComboboxConfig(ndc9Config);

	}

	private void addForm(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemFormLayout = componentConfig.getHorizontalLayoutConfig("itemFormlayout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemFormLayout);

		GtnUIFrameworkComponentConfig itemLandingScreenForm = componentConfig.getUIFrameworkComponentConfig("form", true,
				itemFormLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		itemLandingScreenForm.setAuthorizationIncluded(true);
		itemLandingScreenForm.setComponentName("Form");
		componentList.add(itemLandingScreenForm);

		GtnUIFrameworkComboBoxConfig itemFormConfig = componentConfig.getComboBoxConfig("FORM",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig itemFormValidationConfig = new GtnUIFrameworkValidationConfig();
		itemFormValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemLandingScreenForm.setGtnUIFrameworkValidationConfig(itemFormValidationConfig);

		itemLandingScreenForm.setGtnComboboxConfig(itemFormConfig);

	}

	private void addItemQualifierName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemQualifierNameLayout = componentConfig.getHorizontalLayoutConfig(
				"itemQualifierNamelayout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemQualifierNameLayout);

		GtnUIFrameworkComponentConfig iMasterSearchQualifierName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME, true,
				itemQualifierNameLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		iMasterSearchQualifierName.setComponentName("Item Qualifier");
		iMasterSearchQualifierName.setAuthorizationIncluded(true);
		componentList.add(iMasterSearchQualifierName);

		GtnUIFrameworkComboBoxConfig itemQualifierNameConfig = componentConfig.getComboBoxConfig("ItemQualifier",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig itemQualifierNameValidationConfig = new GtnUIFrameworkValidationConfig();
		itemQualifierNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		iMasterSearchQualifierName.setGtnUIFrameworkValidationConfig(itemQualifierNameValidationConfig);

		iMasterSearchQualifierName.setGtnComboboxConfig(itemQualifierNameConfig);
		// ITEM_MASTER_ITEM_IDENTIFIER_VALUECHANGE_ACTION
		List<GtnUIFrameWorkActionConfig> itemQualifierNameActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig itemQualifierNameCustomAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemQualifierNameCustomAction
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_ITEM_IDENTIFIER_VALUECHANGE_ACTION);
		itemQualifierNameCustomAction.addActionParameter(GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME);
		itemQualifierNameCustomAction.addActionParameter(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER);
		itemQualifierNameActionConfigList.add(itemQualifierNameCustomAction);
		iMasterSearchQualifierName.setGtnUIFrameWorkActionConfigList(itemQualifierNameActionConfigList);

	}

	private void addItemIdentifier(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdentifierLayout = componentConfig.getHorizontalLayoutConfig(
				"itemIdentifierlayout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemIdentifierLayout);

		GtnUIFrameworkComponentConfig itemIdentifier = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER, true,
				itemIdentifierLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemIdentifier.setAuthorizationIncluded(true);
		itemIdentifier.setComponentName("Item Identifier");

		GtnUIFrameworkTextBoxConfig itemIdentifierConfig = componentConfig.getTextBoxConfig(false, false, true);
		itemIdentifier.setGtnTextBoxConfig(itemIdentifierConfig);

		GtnUIFrameworkValidationConfig itemIdentifierValidationConfig = new GtnUIFrameworkValidationConfig();
		itemIdentifierValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemIdentifier.setGtnUIFrameworkValidationConfig(itemIdentifierValidationConfig);

		componentList.add(itemIdentifier);

	}

	private void addBrand(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterBrandLayout = componentConfig.getHorizontalLayoutConfig(
				"itemMasterBrandlayout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemMasterBrandLayout);

		GtnUIFrameworkComponentConfig itemMasterBrand = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.BRAND, true, itemMasterBrandLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemMasterBrand.setAuthorizationIncluded(true);
		itemMasterBrand.setComponentName("Brand");
		componentList.add(itemMasterBrand);

		GtnUIFrameworkComboBoxConfig brandConfig = componentConfig.getComboBoxConfig("Brands",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig brandValidationConfig = new GtnUIFrameworkValidationConfig();
		brandValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemMasterBrand.setGtnUIFrameworkValidationConfig(brandValidationConfig);

		itemMasterBrand.setGtnComboboxConfig(brandConfig);

	}

	private void addNDC8(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc8Layout = componentConfig.getHorizontalLayoutConfig("ndc8layout", true,
				GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(ndc8Layout);

		GtnUIFrameworkComponentConfig ndc8 = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, true, ndc8Layout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		ndc8.setAuthorizationIncluded(true);
		ndc8.setComponentName("NDC 8");
		componentList.add(ndc8);

		GtnUIFrameworkComboBoxConfig ndc8Config = componentConfig.getComboBoxConfig("NDC8",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig ndc8ValidationConfig = new GtnUIFrameworkValidationConfig();
		ndc8ValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ndc8.setGtnUIFrameworkValidationConfig(ndc8ValidationConfig);

		ndc8.setGtnComboboxConfig(ndc8Config);

	}

	private void addItemStrength(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemStrengthLayout = componentConfig.getHorizontalLayoutConfig(
				"itemStrengthlayout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemStrengthLayout);

		GtnUIFrameworkComponentConfig itemStrength = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.STRENGTH, true, itemStrengthLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemStrength.setAuthorizationIncluded(true);
		itemStrength.setComponentName("Strength");
		componentList.add(itemStrength);

		GtnUIFrameworkComboBoxConfig itemStrengthConfig = componentConfig.getComboBoxConfig("STRENGTH",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig itemStrengthValidationConfig = new GtnUIFrameworkValidationConfig();
		itemStrengthValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemStrength.setGtnUIFrameworkValidationConfig(itemStrengthValidationConfig);

		itemStrength.setGtnComboboxConfig(itemStrengthConfig);

	}

	private void addItemBatchId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig batchIdLayout = componentConfig.getHorizontalLayoutConfig("itemBatchIdlayout",
				true, GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(batchIdLayout);

		GtnUIFrameworkComponentConfig batchId = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID, true, batchIdLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		batchId.setAuthorizationIncluded(true);
		batchId.setComponentName("Batch ID");

		GtnUIFrameworkTextBoxConfig batchIdConfig = componentConfig.getTextBoxConfig(true, false, true);
		batchId.setGtnTextBoxConfig(batchIdConfig);

		GtnUIFrameworkValidationConfig batchIdValidationConfig = new GtnUIFrameworkValidationConfig();
		batchIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		batchId.setGtnUIFrameworkValidationConfig(batchIdValidationConfig);

		componentList.add(batchId);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSearch01layout",
				true, GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
		componentList.add(searchBtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSearch01",
				true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		searchValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9, "form",
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER, GtnFrameworkCommonConstants.BRAND,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID));

		GtnUIFrameWorkActionConfig alertActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		searchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
		searchActionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig itemQualifierValidationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemQualifierValidationActionConfig
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_LANDING_SCREEN_VALIDATION_ACTION);
		searchActionConfigList.add(itemQualifierValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				"form", GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER, GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID));
		loadDataTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.BRAND,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9));
		searchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		searchActionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnReset01layout",
				true, GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
		componentList.add(resetBtnLayout);

		GtnUIFrameworkComponentConfig resetBtnConfig = componentConfig.getUIFrameworkComponentConfig("gtnReset01", true,
				resetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		resetBtnConfig.setComponentName("RESET");
		resetBtnConfig.setAuthorizationIncluded(true);
		componentList.add(resetBtnConfig);

		List<GtnUIFrameWorkActionConfig> resetBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		resetActionConfig
				.addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS, null);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.THERAPEUTIC_CLASS, GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9, null);
		resetMap.put("form", null);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME, null);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.BRAND, null);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, null);
		resetMap.put(GtnFrameworkCommonConstants.STRENGTH, null);
		resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE, null);
		resetActionConfig.addActionParameter(resetMap);

		resetActionConfig.addActionParameter(paramsList);
		resetBtnActionConfigList.add(resetActionConfig);
		resetBtnConfig.setGtnUIFrameWorkActionConfigList(resetBtnActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE, true, "ItemResultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"itemMaster", "SearchQuery");
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(
				new String[] { "System ID", "Item ID", "Item No", "Item Name", "Item Desc", "Item Status", "Item Type",
						"Therapy Class", "Brand", "NDC 9", "NDC 8", "Form", "Strength", "Batch ID" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				GtnFrameworkCommonConstants.BRAND, GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, "form", GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID });

		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_MASTER);

		searchResults.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));
		searchResults.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID);
		editActionConfig.addActionParameter(Boolean.TRUE);
		actionConfigList.add(editActionConfig);
		searchResults.setGtnUIFrameWorkActionConfigList(actionConfigList);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, null);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);

		addAddButtonComponent(componentList, componentConfig);
		addEditButtonComponent(componentList, componentConfig);
		addViewButtonComponent(componentList, componentConfig);
		addExcelButtonComponent(componentList, componentConfig);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig addBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnAddButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(addBtnLayout);

		GtnUIFrameworkComponentConfig adddButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnAddButton",
				true, addBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		adddButtonConfig.setComponentName("ADD");
		adddButtonConfig.setAuthorizationIncluded(true);
		componentList.add(adddButtonConfig);

		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
                
		GtnUIFrameWorkActionConfig configurePricingTableActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		configurePricingTableActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_PRICING_TABLE_CONFIGURE_ACTION);
		addActionConfigList.add(configurePricingTableActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");

		addActionConfigList.add(navigationActionConfig);
		GtnUIFrameWorkActionConfig editActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_ADD_ACTION);
		addActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB);
		addActionConfigList.add(tabAction);

		adddButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig editBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnEditButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(editBtnLayout);

		GtnUIFrameworkComponentConfig editButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnEditButton",
				true, editBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		editButtonConfig.setComponentName("EDIT");
		editButtonConfig.setAuthorizationIncluded(true);
		componentList.add(editButtonConfig);

		List<GtnUIFrameWorkActionConfig> editBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams1List = new ArrayList<>();
		alertParams1List.add(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		alertParams1List.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_EDIT_MSG_HEADER);
		alertParams1List.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_EDIT);

		alertActionConfig.setActionParameterList(alertParams1List);
		editBtnActionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG_EDIT);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		onSucessActionConfigList.add(navigationActionConfig);

		/**
		 * Loading in Edit mode
		 */
		GtnUIFrameWorkActionConfig editActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID);
		editActionConfig.addActionParameter(Boolean.TRUE);
		onSucessActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB);
		onSucessActionConfigList.add(tabAction);

		confirmationActionConfig.setActionParameterList(alertParamsList);
		editBtnActionConfigList.add(confirmationActionConfig);
		editButtonConfig.setGtnUIFrameWorkActionConfigList(editBtnActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig viewBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"ItemMasterGtnViewButtonlayout", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(viewBtnLayout);

		GtnUIFrameworkComponentConfig viewButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"ItemMasterGtnViewButton", true, viewBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		viewButtonConfig.setComponentName("VIEW");
		viewButtonConfig.setAuthorizationIncluded(true);
		componentList.add(viewButtonConfig);

		List<GtnUIFrameWorkActionConfig> viewActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VIEW_MSG_HEADER);
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VIEW);

		alertActionConfig.setActionParameterList(alertParamsList);
		viewActionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		viewActionConfigList.add(navigationActionConfig);

		/**
		 * Loading in view mode
		 */
		GtnUIFrameWorkActionConfig editActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID);
		editActionConfig.addActionParameter(Boolean.FALSE);
		viewActionConfigList.add(editActionConfig);
		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_INFORMATION_TAB);
		viewActionConfigList.add(tabAction);
		viewButtonConfig.setGtnUIFrameWorkActionConfigList(viewActionConfigList);
	}

	/**
	 *
	 * @param componentList
	 * @param componentConfig
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig excelBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnExcelButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		excelBtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(excelBtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = componentConfig.getUIFrameworkComponentConfig(null, true,
				excelBtnLayout.getComponentId(), GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfig.getExcelBtnconfig(
				"Item Master", true, GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setTitleNeeded(true);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
			GtnFrameworkComponentConfigProvider componentConfig) {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkItemMasterStringContants.getCustomFilterPropertyIds();
		String[] listNameArray = GtnFrameworkItemMasterStringContants.getCustomFilterListNameArray();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			GtnUIFrameworkComboBoxConfig filterConfig = componentConfig.getComboBoxConfig(listNameArray[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			filterConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.setGtnComboboxConfig(filterConfig);
			customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);

			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}

}
