/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup;

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Yuvaraj.Rajangam
 */
public class GtnUIFrameworkItemMasterNewFormulationPopupConfig {

	public GtnUIFrameworkViewConfig getNfPopupSearchView() {
		GtnFrameworkComponentConfigProvider componentConfigNfPopup = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig itemMasterNfPopupSearchView = componentConfigNfPopup.getViewConfig(
				GtnFrameworkItemMasterStringContants.ITEM_MASTER_NEW_FORMULATION,
				GtnFrameworkItemMasterStringContants.ITEM_MASTER_NEW_FORMULATION, false);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
		itemMasterNfPopupSearchView.addViewAction(customAction);
		addComponentList(itemMasterNfPopupSearchView, componentConfigNfPopup);
		return itemMasterNfPopupSearchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addNfPopupSearchCriteriaPanel(componentList, componentConfig);
		addNfPopupButtonLayout(componentList, componentConfig);
		addNfPopupResultPanel(componentList, componentConfig);
		addActionButtonLayout(componentList, componentConfig);

	}

	private void addNfPopupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterNfPopupSearchPanel = componentConfig
				.getPanelConfig("nfPopupitemSearchCriteriaPanel", false, null);
		itemMasterNfPopupSearchPanel.setComponentName("Search Criteria");
		itemMasterNfPopupSearchPanel.setAuthorizationIncluded(true);
		componentList.add(itemMasterNfPopupSearchPanel);

		addFieldLayout(componentList, componentConfig);
	}

	private void addNfPopupResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterNfPopupResultPanelConfig = componentConfig
				.getPanelConfig("nfPopupItemResultPanel", false, null);
		itemMasterNfPopupResultPanelConfig.setComponentName("Results");
		itemMasterNfPopupResultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(itemMasterNfPopupResultPanelConfig);

		addNfPopupResultLayout(componentList, componentConfig);
	}

	private void addNfPopupResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig resultTableNfPopupLayout = componentConfig
				.getHorizontalLayoutConfig("nfPopupItemResultlayout", true, "nfPopupItemResultPanel");
		resultTableNfPopupLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableNfPopupLayout);

		addPagedTableComponent(componentList, componentConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterNfPopupSearchLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT,
				true, GtnFrameworkItemMasterStringContants.NF_POPUP + "itemSearchCriteriaPanel",
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(itemMasterNfPopupSearchLayout);
		addNfPopupFieldComponent(componentList, componentConfig);
	}

	private void addNfPopupButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterNfPopupButtonLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT,
				false, null);
		itemMasterNfPopupButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		itemMasterNfPopupButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(itemMasterNfPopupButtonLayout);

		addSearchNfPopupButtonComponent(componentList, componentConfig);
		addResetNfPopupButtonComponent(componentList, componentConfig);

	}

	private void addNfPopupFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addNfPopupSystemId(componentList, componentConfig);
		addNfPopupItemId(componentList, componentConfig);
		addNfPopupItemNo(componentList, componentConfig);
		addNfPopupItemName(componentList, componentConfig);
		addNfPopupItemDesc(componentList, componentConfig);
		addNfPopupItemStatus(componentList, componentConfig);
		addNfPopupItemType(componentList, componentConfig);
		addNfPopupTherapyClass(componentList, componentConfig);
		addNfPopupNDC9(componentList, componentConfig);
		addNfPopupForm(componentList, componentConfig);
		addNfPopupItemQualifierName(componentList, componentConfig);
		addNfPopupItemIdentifier(componentList, componentConfig);
		addNfPopupBrand(componentList, componentConfig);
		addNfPopupNDC8(componentList, componentConfig);
		addNfPopupItemStrength(componentList, componentConfig);
		addNfPopupItemBatchId(componentList, componentConfig);
		addNfPopupOrganizationKey(componentList, componentConfig);
	}

	private void addNfPopupSystemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig systemIdLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemSearchSystemIdlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(systemIdLayout);

		GtnUIFrameworkComponentConfig systemIdConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SYSTEM_ID, true,
				systemIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		systemIdConfig.setAuthorizationIncluded(true);
		systemIdConfig.setComponentName("System ID");

		GtnUIFrameworkTextBoxConfig systemIdMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		systemIdMaxLengthConfig.setMaximumLength(38);
		systemIdConfig.setGtnTextBoxConfig(systemIdMaxLengthConfig);

		GtnUIFrameworkValidationConfig systemIdValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				"System ID " + GtnFrameworkRegexStringConstants.ALPHANUMERIC_ERROR_MSG,
				GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		systemIdConfig.setGtnUIFrameworkValidationConfig(systemIdValidationConfig);

		componentList.add(systemIdConfig);
	}

	private void addNfPopupItemId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdLayout = componentConfig.getHorizontalLayoutConfig("nfPopupitemIdlayout",
				true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemIdLayout);

		GtnUIFrameworkComponentConfig itemIdConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				true, itemIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
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

	private void addNfPopupItemNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNfPopupNoLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemNolayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemNfPopupNoLayout);

		GtnUIFrameworkComponentConfig itemNfPopupNoConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				true, itemNfPopupNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemNfPopupNoConfig.setAuthorizationIncluded(true);
		itemNfPopupNoConfig.setComponentName("Item No");

		GtnUIFrameworkTextBoxConfig itemNoMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemNoMaxLengthConfig.setMaximumLength(50);
		itemNfPopupNoConfig.setGtnTextBoxConfig(itemNoMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemNoValidationConfig = new GtnUIFrameworkValidationConfig();
		itemNoValidationConfig.setMaxLength(50);
		itemNoValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemNfPopupNoConfig.setGtnUIFrameworkValidationConfig(itemNoValidationConfig);

		componentList.add(itemNfPopupNoConfig);
	}

	private void addNfPopupItemName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNameLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemNamelayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		itemNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(itemNameLayout);

		GtnUIFrameworkComponentConfig itemNameConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				true, itemNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
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

	private void addNfPopupItemDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemDescLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemDesclayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemDescLayout);

		GtnUIFrameworkComponentConfig itemDescConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				true, itemDescLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
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

	private void addNfPopupItemStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemStatusLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemStatuslayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemStatusLayout);

		GtnUIFrameworkComponentConfig iMasterSearchItemStatus = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				true, itemStatusLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		iMasterSearchItemStatus.setAuthorizationIncluded(true);
		iMasterSearchItemStatus.setComponentName("Item Status");

		GtnUIFrameworkTextBoxConfig itemStatusMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		itemStatusMaxLengthConfig.setMaximumLength(250);
		iMasterSearchItemStatus.setGtnTextBoxConfig(itemStatusMaxLengthConfig);

		GtnUIFrameworkValidationConfig itemStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		itemStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		iMasterSearchItemStatus.setGtnUIFrameworkValidationConfig(itemStatusValidationConfig);

		componentList.add(iMasterSearchItemStatus);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		iMasterSearchItemStatus.setGtnComboboxConfig(itemStatusConfig);
	}

	private void addNfPopupItemType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemTypeLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemTypelayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemTypeLayout);

		GtnUIFrameworkComponentConfig iMasterSearchItemType = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE,
				true, itemTypeLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
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

	private void addNfPopupTherapyClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig therapyClassLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopuptherapyClasslayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(therapyClassLayout);

		GtnUIFrameworkComponentConfig therapyClassConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.THERAPEUTIC_CLASS, true,
				therapyClassLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		therapyClassConfig.setAuthorizationIncluded(true);
		therapyClassConfig.setComponentName("Therapy Class");

		GtnUIFrameworkValidationConfig therapyClassValidationConfig = new GtnUIFrameworkValidationConfig();
		therapyClassValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		therapyClassConfig.setGtnUIFrameworkValidationConfig(therapyClassValidationConfig);

		componentList.add(therapyClassConfig);
	}

	private void addNfPopupNDC9(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc9Layout = componentConfig.getHorizontalLayoutConfig("nfPopupndc9layout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(ndc9Layout);

		GtnUIFrameworkComponentConfig ndc9 = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9, true,
				ndc9Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
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

	private void addNfPopupForm(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemFormLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemFormlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemFormLayout);

		GtnUIFrameworkComponentConfig itemForm = componentConfig.getUIFrameworkComponentConfig("nfPopupform", true,
				itemFormLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		itemForm.setAuthorizationIncluded(true);
		itemForm.setComponentName("Form");
		componentList.add(itemForm);

		GtnUIFrameworkComboBoxConfig itemFormConfig = componentConfig.getComboBoxConfig("FORM",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig itemFormValidationConfig = new GtnUIFrameworkValidationConfig();
		itemFormValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemForm.setGtnUIFrameworkValidationConfig(itemFormValidationConfig);

		itemForm.setGtnComboboxConfig(itemFormConfig);

	}

	private void addNfPopupItemQualifierName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemNfPopupQualifierNameLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemQualifierNamelayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemNfPopupQualifierNameLayout);

		GtnUIFrameworkComponentConfig iMasterNfPopupSearchQualifierName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME,
				true, itemNfPopupQualifierNameLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		iMasterNfPopupSearchQualifierName.setAuthorizationIncluded(true);
		iMasterNfPopupSearchQualifierName.setComponentName("Item Qualifier");
		componentList.add(iMasterNfPopupSearchQualifierName);

		GtnUIFrameworkComboBoxConfig itemQualifierNameConfig = componentConfig.getComboBoxConfig("ItemQualifier",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig itemQualifierNameValidationConfig = new GtnUIFrameworkValidationConfig();
		itemQualifierNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		iMasterNfPopupSearchQualifierName.setGtnUIFrameworkValidationConfig(itemQualifierNameValidationConfig);

		iMasterNfPopupSearchQualifierName.setGtnComboboxConfig(itemQualifierNameConfig);
		// ITEM_MASTER_ITEM_IDENTIFIER_VALUECHANGE_ACTION
		List<GtnUIFrameWorkActionConfig> itemQualifierNameActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig itemQualifierNameCustomAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemQualifierNameCustomAction
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_ITEM_IDENTIFIER_VALUECHANGE_ACTION);
		itemQualifierNameCustomAction.addActionParameter(iMasterNfPopupSearchQualifierName.getComponentId());
		itemQualifierNameCustomAction.addActionParameter("nfPopupIMasterSearchItemIdentifier");
		itemQualifierNameActionConfigList.add(itemQualifierNameCustomAction);
		iMasterNfPopupSearchQualifierName.setGtnUIFrameWorkActionConfigList(itemQualifierNameActionConfigList);

	}

	private void addNfPopupItemIdentifier(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdentifierLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemIdentifierlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemIdentifierLayout);

		GtnUIFrameworkComponentConfig itemIdentifier = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER,
				true, itemIdentifierLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		itemIdentifier.setAuthorizationIncluded(true);
		itemIdentifier.setComponentName("Item Identifier");

		GtnUIFrameworkTextBoxConfig itemIdentifierConfig = componentConfig.getTextBoxConfig(true, false, true);
		itemIdentifier.setGtnTextBoxConfig(itemIdentifierConfig);

		GtnUIFrameworkValidationConfig itemIdentifierValidationConfig = new GtnUIFrameworkValidationConfig();
		itemIdentifierValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemIdentifier.setGtnUIFrameworkValidationConfig(itemIdentifierValidationConfig);

		componentList.add(itemIdentifier);

	}

	private void addNfPopupBrand(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemMasterBrandLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemMasterBrandlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemMasterBrandLayout);

		GtnUIFrameworkComponentConfig itemMasterBrand = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.BRAND, true,
				itemMasterBrandLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
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

	private void addNfPopupNDC8(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig ndc8Layout = componentConfig.getHorizontalLayoutConfig("nfPopupndc8layout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(ndc8Layout);

		GtnUIFrameworkComponentConfig ndc8 = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, true,
				ndc8Layout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
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

	private void addNfPopupItemStrength(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemStrengthLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemStrengthlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(itemStrengthLayout);

		GtnUIFrameworkComponentConfig itemStrength = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.STRENGTH, true,
				itemStrengthLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
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

	private void addNfPopupItemBatchId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig batchIdLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemBatchIdlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(batchIdLayout);

		GtnUIFrameworkComponentConfig batchId = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID,
				true, batchIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		batchId.setComponentName("Batch ID");

		GtnUIFrameworkTextBoxConfig batchIdConfig = componentConfig.getTextBoxConfig(true, false, true);
		batchId.setGtnTextBoxConfig(batchIdConfig);

		GtnUIFrameworkValidationConfig batchIdValidationConfig = new GtnUIFrameworkValidationConfig();
		batchIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		batchId.setGtnUIFrameworkValidationConfig(batchIdValidationConfig);

		componentList.add(batchId);

	}

	private void addNfPopupOrganizationKey(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig batchIdLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupitemOrganizationlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_CRITERIALAYOUT);
		componentList.add(batchIdLayout);

		GtnUIFrameworkComponentConfig organizationKey = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ORGANIZATION_KEY,
				true, batchIdLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		organizationKey.setComponentName("Organization Key");

		GtnUIFrameworkComboBoxConfig organizationConfig = componentConfig.getComboBoxConfig("OrganizationKey",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig organizationKeyConfig = new GtnUIFrameworkValidationConfig();
		organizationKeyConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		organizationKey.setGtnUIFrameworkValidationConfig(organizationKeyConfig);

		organizationKey.setGtnComboboxConfig(organizationConfig);

		componentList.add(organizationKey);

	}

	private void addSearchNfPopupButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupgtnSearch01layout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
		componentList.add(searchBtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"nfPopupgtnSearch01", true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		searchValidationActionConfig.setFieldValues(Arrays.asList(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9,
				"form",
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME,
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.BRAND,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ORGANIZATION_KEY

		));

		GtnUIFrameWorkActionConfig alertNfPopupActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertNfPopupParamsList = new ArrayList<>();
		alertNfPopupParamsList.add("Search Criteria ");
		alertNfPopupParamsList.add("Please enter Search Criteria");

		alertNfPopupActionConfig.setActionParameterList(alertNfPopupParamsList);
		searchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertNfPopupActionConfig)));
		searchActionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig itemNfPopupQualifierValidationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemNfPopupQualifierValidationActionConfig
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_LANDING_SCREEN_VALIDATION_ACTION);
		searchActionConfigList.add(itemNfPopupQualifierValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataNfPopupTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataNfPopupTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataNfPopupTableActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.NF_POPUP
				+ GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		loadDataNfPopupTableActionConfig.setFieldValues(Arrays.asList(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				GtnFrameworkItemMasterStringContants.NF_POPUP + "form",
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME,
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID));
		loadDataNfPopupTableActionConfig.setFieldDescription(Arrays.asList(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.BRAND,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9,
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ORGANIZATION_KEY));
		searchActionConfigList.add(loadDataNfPopupTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.NF_POPUP
				+ GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		searchActionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetNfPopupButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig resetNfPopupBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupgtnReset01layout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
		componentList.add(resetNfPopupBtnLayout);

		GtnUIFrameworkComponentConfig resetNfPopupBtnConfig = componentConfig.getUIFrameworkComponentConfig(
				"nfPopupgtnReset01", true, resetNfPopupBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		resetNfPopupBtnConfig.setComponentName("RESET");
		resetNfPopupBtnConfig.setAuthorizationIncluded(true);
		componentList.add(resetNfPopupBtnConfig);

		List<GtnUIFrameWorkActionConfig> resetNfPopupBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		resetActionConfig
				.addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				null);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE,
				null);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9,
				null);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + "form", null);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP
				+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME, null);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.BRAND, null);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8,
				null);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.STRENGTH, null);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ORGANIZATION_KEY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(GtnFrameworkItemMasterStringContants.NF_POPUP
				+ GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE, null);
		resetActionConfig.addActionParameter(resetMap);

		resetActionConfig.addActionParameter(paramsList);
		resetNfPopupBtnActionConfigList.add(resetActionConfig);
		resetNfPopupBtnConfig.setGtnUIFrameWorkActionConfigList(resetNfPopupBtnActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE,
				true, "nfPopupItemResultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"itemMaster", "imNewFormulationSearchQuery");
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { "System ID", "Item ID", "Item No", "Item Name", "Item Desc",
				"Item Status", "Item Type", "Therapy Class", "Brand", "NDC 9", "NDC 8", "Form", "Strength", "Batch ID",
				"Organization Key" });
		searchResults.setTableColumnMappingId(new Object[] {
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ITEM_SYSTEM_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.THERAPEUTIC_CLASS,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.BRAND,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8,
				GtnFrameworkItemMasterStringContants.NF_POPUP + "form",
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.STRENGTH,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID,
				GtnFrameworkItemMasterStringContants.NF_POPUP
						+ GtnFrameworkCommonConstants.I_MASTER_SEARCH_ORGANIZATION_KEY });

		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_MASTER);

		searchResults.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false,
				null);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);

		addSelectButtonComponent(componentList, componentConfig);
		addCloseButtonComponent(componentList, componentConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
			GtnFrameworkComponentConfigProvider componentConfig) {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customNfPopupFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkItemMasterStringContants.getConfigPropertyIds();
		String[] listNameArray = GtnFrameworkItemMasterStringContants.getConfigListNameArray();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customNfPopupFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			GtnUIFrameworkComponentConfig customNfPopupFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			GtnUIFrameworkComboBoxConfig filterConfig = componentConfig.getComboBoxConfig(listNameArray[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customNfPopupFilterComponentConfig.setComponentId("customFilterComboBox");
			customNfPopupFilterComponentConfig.setComponentName("customFilterComboBox");
			filterConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customNfPopupFilterComponentConfig.setGtnComboboxConfig(filterConfig);
			customNfPopupFilterConfig.setGtnComponentConfig(customNfPopupFilterComponentConfig);
			customNfPopupFilterConfig.setPropertId(propertyIds[i]);
			customNfPopupFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);

			customNfPopupFilterConfigMap.put(customNfPopupFilterConfig.getPropertId(), customNfPopupFilterConfig);

		}
		return customNfPopupFilterConfigMap;
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig selectLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupgtnAddButtonlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(selectLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"nfPopupparentCompanySelectButton", true, selectLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Select");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(GtnFrameworkItemMasterStringContants.NF_POPUP
				+ GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
		actionParameterList.add("additionalInformationTabNewFormulationLookup");
		actionParameterList.add(Arrays.asList(
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID));
		actionParameterList.add(Arrays.asList("additionalInformationTabNewFormulationLookup"));
		selectAction.setActionParameterList(actionParameterList);
		selectActionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkItemMasterStringContants.ITEM_MASTER_NEW_FORMULATION);
		selectActionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig closeLayout = componentConfig.getHorizontalLayoutConfig(
				"nfPopupgtnAddButtonlayout", true,
				GtnFrameworkItemMasterStringContants.NF_POPUP + GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(closeLayout);

		GtnUIFrameworkComponentConfig closeButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"nfPopupparentCompanyCloseButton", true, closeLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setAuthorizationIncluded(true);
		closeButtonConfig.setComponentName("Close");
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkItemMasterStringContants.ITEM_MASTER_NEW_FORMULATION);
		closeActionConfigList.add(closeAction);
		closeButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}

}
