package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkContracHeadertInfoTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addAdditionalInfoTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig("contractAdditionalInformationTab", false, null);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT, true,
				"contractAdditionalInformationTab", GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(cssGtnLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addInsideOwner(componentList);
		addInsidePhone(componentList);
		addInsideAuthor(componentList);
		addInsideAdditional(componentList);
		addInsideAdditionalName(componentList);
		addInsideAdditionalPhone(componentList);
		addOutsideOwner(componentList);
		addOutsidePhone(componentList);
		addOutsideAuthor(componentList);
		addOutsideAdditional(componentList);
		addOutsideAdditionalName(componentList);
		addOutsideAdditionalPhone(componentList);
		addAdvanceNoticeDays(componentList);
		addAffiliatedContractInfo(componentList);
		addShippingTerms(componentList);
		addProposalStartDate(componentList);
		addProposalEndDate(componentList);
		addOriginalStartDate(componentList);
		addOriginalEndDate(componentList);
		addAwardStatus(componentList);
		addLastUpdatedDate(componentList);
		addPriceEscalationClause(componentList);
		addExemptFromLowPrice(componentList);
		addPriceResetIndicator(componentList);
		addCancellationClause(componentList);
		addMostFavoredNation(componentList);
		addCategory(componentList);
		addCurrency(componentList);
		addMinimumOrder(componentList);
		addPaymentTerms(componentList);
	}

	private void addInsideOwner(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabInsideOwnerlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig insideOwnerConfig = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabInsideOwner", true, "contractInformationTabInsideOwnerlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		insideOwnerConfig.setAuthorizationIncluded(true);
		insideOwnerConfig.setComponentName("Inside Owner");

		componentList.add(insideOwnerConfig);
	}

	private void addInsidePhone(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabInsidePhonelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig insidePhoneConfig = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabInsidePhone", true, "contractInformationTabInsidePhonelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		insidePhoneConfig.setAuthorizationIncluded(true);
		insidePhoneConfig.setComponentName("Inside Phone");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider.getRegexOnlyValidationConfig(
				GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR,
				"Inside Phone " + GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR_MSG);
		insidePhoneConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(insidePhoneConfig);
	}

	private void addInsideAuthor(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabInsideAuthorlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig insideAuthor = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabInsideAuthor", true, "contractInformationTabInsideAuthorlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		insideAuthor.setAuthorizationIncluded(true);
		insideAuthor.setComponentName("Inside Author");
		componentList.add(insideAuthor);
	}

	private void addInsideAdditional(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabInsideAdditionallayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig insideAdditional = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabInsideAdditional", true, "contractInformationTabInsideAdditionallayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		insideAdditional.setAuthorizationIncluded(true);
		insideAdditional.setComponentName("Inside Additional");
		componentList.add(insideAdditional);
	}

	private void addInsideAdditionalName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabInsideAdditionalNamelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig insideAdditionalName = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabInsideAdditionalName", true, "contractInformationTabInsideAdditionalNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		insideAdditionalName.setAuthorizationIncluded(true);
		insideAdditionalName.setComponentName("Inside Additional Name");
		componentList.add(insideAdditionalName);
	}

	private void addInsideAdditionalPhone(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabInsideAdditionalPhonelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig insideAdditionalPhone = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabInsideAdditionalPhone", true,
				"contractInformationTabInsideAdditionalPhonelayout", GtnUIFrameworkComponentType.TEXTBOX);
		insideAdditionalPhone.setAuthorizationIncluded(true);
		insideAdditionalPhone.setComponentName("Inside Additional Phone");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider.getRegexOnlyValidationConfig(
				GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR,
				"Inside Additional Phone" + GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR_MSG);
		insideAdditionalPhone.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(insideAdditionalPhone);

	}

	private void addOutsideOwner(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOutsideOwnerlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig outsideOwner = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOutsideOwner", true, "contractInformationTabOutsideOwnerlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		outsideOwner.setAuthorizationIncluded(true);
		outsideOwner.setComponentName("Outside Owner");
		componentList.add(outsideOwner);
	}

	private void addOutsidePhone(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOutsidePhonelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig outsidePhone = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOutsidePhone", true, "contractInformationTabOutsidePhonelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		outsidePhone.setAuthorizationIncluded(true);
		outsidePhone.setComponentName("Outside Phone");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider.getRegexOnlyValidationConfig(
				GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR,
				"Outside Phone" + GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR_MSG);
		outsidePhone.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(outsidePhone);
	}

	private void addOutsideAuthor(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOutsideAuthorlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig outsideAuthor = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOutsideAuthor", true, "contractInformationTabOutsideAuthorlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		outsideAuthor.setAuthorizationIncluded(true);
		outsideAuthor.setComponentName("Outside Author");
		componentList.add(outsideAuthor);
	}

	private void addOutsideAdditional(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOutsideAdditionallayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig outsideAdditional = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOutsideAdditional", true, "contractInformationTabOutsideAdditionallayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		outsideAdditional.setAuthorizationIncluded(true);
		outsideAdditional.setComponentName("Outside Additional");
		componentList.add(outsideAdditional);
	}

	private void addOutsideAdditionalName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOutsideAdditionalNamelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig outsideAdditionalName = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOutsideAdditionalName", true,
				"contractInformationTabOutsideAdditionalNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		outsideAdditionalName.setAuthorizationIncluded(true);
		outsideAdditionalName.setComponentName("Outside Additional Name");
		componentList.add(outsideAdditionalName);
	}

	private void addOutsideAdditionalPhone(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOutsideAdditionalPhonelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig outsideAdditionalPhone = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOutsideAdditionalPhone", true,
				"contractInformationTabOutsideAdditionalPhonelayout", GtnUIFrameworkComponentType.TEXTBOX);
		outsideAdditionalPhone.setAuthorizationIncluded(true);
		outsideAdditionalPhone.setComponentName("Outside Additional Phone");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider.getRegexOnlyValidationConfig(
				GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR,
				"Outside Additional Phone" + GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR_MSG);
		outsideAdditionalPhone.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(outsideAdditionalPhone);
	}

	private void addAdvanceNoticeDays(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabAdvanceNoticeDayslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig advanceNoticeDays = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabAdvanceNoticeDays", true, "contractInformationTabAdvanceNoticeDayslayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		advanceNoticeDays.setAuthorizationIncluded(true);
		advanceNoticeDays.setComponentName("Advance Notice Days");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider.getRegexOnlyValidationConfig(
				GtnFrameworkRegexStringConstants.NUMERIC_FORMAT,
				"Advance Notice Days" + GtnFrameworkRegexStringConstants.ALLOWS_ONLY_NUMERIC);
		advanceNoticeDays.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(advanceNoticeDays);
	}

	private void addAffiliatedContractInfo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabAffiliatedContractInfolayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig affiliatedContractInfo = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabAffiliatedContractInfo", true,
				"contractInformationTabAffiliatedContractInfolayout", GtnUIFrameworkComponentType.TEXTBOX);
		affiliatedContractInfo.setAuthorizationIncluded(true);
		affiliatedContractInfo.setComponentName("Affiliated Contract Info");
		componentList.add(affiliatedContractInfo);
	}

	private void addShippingTerms(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabShippingTermslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig shippingTerms = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabShippingTerms", true, "contractInformationTabShippingTermslayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		shippingTerms.setAuthorizationIncluded(true);
		shippingTerms.setComponentName("Shipping Terms");

		componentList.add(shippingTerms);
	}

	private void addProposalStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabProposalStartDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig proposalStartDate = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabProposalStartDate", true, "contractInformationTabProposalStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		proposalStartDate.setAuthorizationIncluded(true);
		proposalStartDate.setComponentName("Proposal Start Date");

		componentList.add(proposalStartDate);
	}

	private void addProposalEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabProposalEndDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig proposalEndDate = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabProposalEndDate", true, "contractInformationTabProposalEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		proposalEndDate.setAuthorizationIncluded(true);
		proposalEndDate.setComponentName("Proposal End Date");
		componentList.add(proposalEndDate);
	}

	private void addOriginalStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOriginalStartDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig originalStartDate = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOriginalStartDate", true, "contractInformationTabOriginalStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		originalStartDate.setAuthorizationIncluded(true);
		originalStartDate.setComponentName("Original Start Date");
		componentList.add(originalStartDate);
	}

	private void addOriginalEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabOriginalEndDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig originalEndDate = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabOriginalEndDate", true, "contractInformationTabOriginalEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		originalEndDate.setAuthorizationIncluded(true);
		originalEndDate.setComponentName("Original End Date");
		componentList.add(originalEndDate);
	}

	private void addAwardStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabAwardStatuslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig awardStatus = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabAwardStatus", true, "contractInformationTabAwardStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		awardStatus.setAuthorizationIncluded(true);
		awardStatus.setComponentName("Award Status");

		GtnUIFrameworkComboBoxConfig itemStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		awardStatus.setGtnComboboxConfig(itemStatusConfig);

		componentList.add(awardStatus);
	}

	private void addLastUpdatedDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabLastUpdatedDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig lastUpdatedDate = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabLastUpdatedDate", true, "contractInformationTabLastUpdatedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		lastUpdatedDate.setAuthorizationIncluded(true);
		lastUpdatedDate.setComponentName("Last Updated Date");
		componentList.add(lastUpdatedDate);
	}

	private void addPriceEscalationClause(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabPriceEscalationClauselayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig priceEscalationClause = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabPriceEscalationClause", true,
				"contractInformationTabPriceEscalationClauselayout", GtnUIFrameworkComponentType.TEXTBOX);
		priceEscalationClause.setAuthorizationIncluded(true);
		priceEscalationClause.setComponentName("Price Escalation Clause");
		componentList.add(priceEscalationClause);
	}

	private void addExemptFromLowPrice(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabExemptFromLowPriceLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig exemptFromLowPrice = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabExemptFromLowPrice", true, "contractInformationTabExemptFromLowPriceLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		exemptFromLowPrice.setAuthorizationIncluded(true);
		exemptFromLowPrice.setComponentName("Exempt From Low Price");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider.getRegexOnlyValidationConfig(
				GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR,
				"Exempt From Low Price" + GtnFrameworkRegexStringConstants.NUMERIC_SPECIAL_CHAR_MSG);
		exemptFromLowPrice.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(exemptFromLowPrice);
	}

	private void addPriceResetIndicator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabPriceResetIndicatorLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig priceResetIndicator = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabPriceResetIndicator", true, "contractInformationTabPriceResetIndicatorLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		priceResetIndicator.setAuthorizationIncluded(true);
		priceResetIndicator.setComponentName("Price Reset Indicator");
		componentList.add(priceResetIndicator);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = configProvider.getComboBoxConfig("PRICE_RESET_INDICATOR",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceResetIndicator.setGtnComboboxConfig(itemStatusConfig);
	}

	private void addCancellationClause(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabCancellationClauseLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cancellationClause = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabCancellationClause", true, "contractInformationTabCancellationClauseLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		cancellationClause.setAuthorizationIncluded(true);
		cancellationClause.setComponentName("Cancellation Clause");
		componentList.add(cancellationClause);
	}

	private void addMostFavoredNation(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabMostFavoredNationLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig mostFavoredNation = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabMostFavoredNation", true, "contractInformationTabMostFavoredNationLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		mostFavoredNation.setAuthorizationIncluded(true);
		mostFavoredNation.setComponentName("Most Favored Nation");
		componentList.add(mostFavoredNation);
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabCategoryLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig category = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabCategory", true, "contractInformationTabCategoryLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		category.setAuthorizationIncluded(true);
		category.setComponentName("Category");
		componentList.add(category);
	}

	private void addCurrency(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabCurrencyLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig currency = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabCurrency", true, "contractInformationTabCurrencyLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		currency.setAuthorizationIncluded(true);
		currency.setComponentName("Currency");
		componentList.add(currency);
	}

	private void addMinimumOrder(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabMinimumOrderLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig minimumOrder = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabMinimumOrder", true, "contractInformationTabMinimumOrderLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		minimumOrder.setAuthorizationIncluded(true);
		minimumOrder.setComponentName("Minimum Order");
		componentList.add(minimumOrder);
	}

	private void addPaymentTerms(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractInformationTabPaymentTermsLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_INFORMATION_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig paymentTerms = configProvider.getUIFrameworkComponentConfig(
				"contractInformationTabPaymentTerms", true, "contractInformationTabPaymentTermsLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		paymentTerms.setAuthorizationIncluded(true);
		paymentTerms.setComponentName("Payment Terms");
		componentList.add(paymentTerms);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = configProvider.getComboBoxConfig("PAYMENT_TERMS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		paymentTerms.setGtnComboboxConfig(itemStatusConfig);

	}
}
