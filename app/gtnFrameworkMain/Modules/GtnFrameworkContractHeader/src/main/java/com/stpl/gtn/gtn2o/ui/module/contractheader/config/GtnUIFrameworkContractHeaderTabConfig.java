package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkContractHeaderTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addContractHeaderTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("contactHeaderTab", false,
				null);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT, true, "contactHeaderTab",
				GtnUIFrameworkLayoutType.COL3_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(cssGtnLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addContractId(componentList);
		addContractNo(componentList);
		addContractName(componentList);
		addContractType(componentList);
		addContractStatus(componentList);
		addDocumentType(componentList);
		addContractStartDate(componentList);
		addContractEndDate(componentList);
		addDocumentClass(componentList);
		addCompanyName(componentList);
		addTradeClass(componentList);
		addTerm(componentList);
		addTradingPartner(componentList);
		addReNegotiationStartDate(componentList);
		addReNegotiationEndDate(componentList);
		addPriceProtectionStartDate(componentList);
		addPriceProtectionEndDate(componentList);
		addManufacturerNo(componentList);
	}

	private void addContractId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabContractIdlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemIdConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabContractId", true, "contractHeaderTabContractIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		itemIdConfig.setAuthorizationIncluded(true);
		itemIdConfig.setComponentName("Contract ID");
		itemIdConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemIdConfig);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		itemIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabContractNolayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemNoConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabContractNo", true, "contractHeaderTabContractNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNoConfig.setAuthorizationIncluded(true);
		itemNoConfig.setComponentName("Contract No");
		itemNoConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemNoConfig);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		itemNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabContractNamelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemNameConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabContractName", true, "contractHeaderTabContractNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNameConfig.setAuthorizationIncluded(true);
		itemNameConfig.setComponentName("Contract Name");
		itemNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemNameConfig);
		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		itemNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabContractTypelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemType = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabContractType", true, "contractHeaderTabContractTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		itemType.setAuthorizationIncluded(true);
		itemType.setComponentName("Contract Type");
		itemType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemType);

		GtnUIFrameworkComboBoxConfig itemTypeConfig = configProvider.getComboBoxConfig("CONTRACT_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemType.setGtnComboboxConfig(itemTypeConfig);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		itemType.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabContractStatuslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemStatus = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabContractStatus", true, "contractHeaderTabContractStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		itemStatus.setAuthorizationIncluded(true);
		itemStatus.setComponentName("Contract Status");
		itemStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemStatus);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemStatus.setGtnComboboxConfig(itemStatusConfig);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		itemStatus.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addDocumentType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabDocumentTypelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig tradeClass = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabDocumentType", true, "contractHeaderTabDocumentTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		tradeClass.setAuthorizationIncluded(true);
		tradeClass.setComponentName("Document Type");

		componentList.add(tradeClass);

		GtnUIFrameworkComboBoxConfig tradeClassConfig = configProvider.getComboBoxConfig("DOCUMENT_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		tradeClass.setGtnComboboxConfig(tradeClassConfig);

	}

	private void addContractStartDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderContractStartDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderItemStartDate = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderContractStartDate", true, "contractHeaderContractStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		contractHeaderItemStartDate.setAuthorizationIncluded(true);
		contractHeaderItemStartDate.setComponentName("Start Date");
		contractHeaderItemStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(contractHeaderItemStartDate);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		contractHeaderItemStartDate.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractEndDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderContractEndDateLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderItemEndDate = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderContractEndDate", true, "contractHeaderContractEndDateLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		contractHeaderItemEndDate.setAuthorizationIncluded(true);
		contractHeaderItemEndDate.setComponentName("End Date");

		componentList.add(contractHeaderItemEndDate);
	}

	private void addDocumentClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabDocumentClasslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig tradeClass = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabDocumentClass", true, "contractHeaderTabDocumentClasslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		tradeClass.setAuthorizationIncluded(true);
		tradeClass.setComponentName("Document Class");

		componentList.add(tradeClass);

		GtnUIFrameworkComboBoxConfig tradeClassConfig = configProvider.getComboBoxConfig("DOCUMENT_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		tradeClass.setGtnComboboxConfig(tradeClassConfig);

	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNamelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig chCompanyNameConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabCompanyName", true, "contractHeaderTabCompanyNamelayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		chCompanyNameConfig.setAuthorizationIncluded(true);
		chCompanyNameConfig.setComponentName("Company Name");

		componentList.add(chCompanyNameConfig);

		GtnUIFrameworkTextBoxConfig chCompanyNameTextboxConfig = configProvider.getTextBoxConfig(true, false, true);
		chCompanyNameTextboxConfig.setReadOnly(true);
		chCompanyNameConfig.setGtnTextBoxConfig(chCompanyNameTextboxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig chCompanyNamePopupActionConfig = new GtnUIFrameWorkActionConfig();
		chCompanyNamePopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		chCompanyNamePopupActionConfig.addActionParameter("contractHeaderTabCompanyNameView");
		chCompanyNamePopupActionConfig.addActionParameter("Company Name Look Up");
		chCompanyNamePopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		chCompanyNamePopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		actionConfigList.add(chCompanyNamePopupActionConfig);
		chCompanyNameConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabTradeClasslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig tradeClass = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabTradeClass", true, "contractHeaderTabTradeClasslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		tradeClass.setAuthorizationIncluded(true);
		tradeClass.setComponentName("Trade Class");

		componentList.add(tradeClass);

		GtnUIFrameworkComboBoxConfig tradeClassConfig = configProvider.getComboBoxConfig("CONTRACT_TRADE_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		tradeClass.setGtnComboboxConfig(tradeClassConfig);
	}

	private void addTerm(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabTermlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemNameConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabTerm", true, "contractHeaderTabTermlayout", GtnUIFrameworkComponentType.TEXTBOX);
		itemNameConfig.setAuthorizationIncluded(true);
		itemNameConfig.setComponentName("Term");

		componentList.add(itemNameConfig);
	}

	private void addTradingPartner(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabTradingPartnerlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemNameConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabTradingPartner", true, "contractHeaderTabTradingPartnerlayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		itemNameConfig.setAuthorizationIncluded(true);
		itemNameConfig.setComponentName("Trading Partner");
		componentList.add(itemNameConfig);

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		itemNameConfig.setGtnTextBoxConfig(textboxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("contractHeaderTabTpView");
		popupActionConfig.addActionParameter("Trading Partner Look Up");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		actionConfigList.add(popupActionConfig);
		itemNameConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addReNegotiationStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabReNegotiationStartDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemCategory = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabReNegotiationStartDate", true, "contractHeaderTabReNegotiationStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		itemCategory.setAuthorizationIncluded(true);
		itemCategory.setComponentName("Re-Negotiation Start Date");

		componentList.add(itemCategory);

	}

	private void addReNegotiationEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabReNegotiationEndDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemCategory = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabReNegotiationEndDate", true, "contractHeaderTabReNegotiationEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		itemCategory.setAuthorizationIncluded(true);
		itemCategory.setComponentName("Re-Negotiation End Date");
		componentList.add(itemCategory);

	}

	private void addPriceProtectionStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabPriceProtectionStartDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemCategory = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabPriceProtectionStartDate", true, "contractHeaderTabPriceProtectionStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		itemCategory.setAuthorizationIncluded(true);
		itemCategory.setComponentName("Price Protection Start Date");

		componentList.add(itemCategory);

	}

	private void addPriceProtectionEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addPriceProtectionEndDateLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabPriceProtectionEndDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(addPriceProtectionEndDateLayout);

		GtnUIFrameworkComponentConfig addPriceProtectionEndDate = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabPriceProtectionEndDate", true, "contractHeaderTabPriceProtectionEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		addPriceProtectionEndDate.setAuthorizationIncluded(true);
		addPriceProtectionEndDate.setComponentName("Price Protection End Date");

		componentList.add(addPriceProtectionEndDate);

	}

	private void addManufacturerNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTaManufacturerNolayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemCategory = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTaManufacturerNo", true, "contractHeaderTaManufacturerNolayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		itemCategory.setAuthorizationIncluded(true);
		itemCategory.setComponentName("Manufacturer No");

		componentList.add(itemCategory);

		GtnUIFrameworkComboBoxConfig itemCategoryConfig = configProvider.getComboBoxConfig("CompanyManufacture",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemCategory.setGtnComboboxConfig(itemCategoryConfig);

	}

}
