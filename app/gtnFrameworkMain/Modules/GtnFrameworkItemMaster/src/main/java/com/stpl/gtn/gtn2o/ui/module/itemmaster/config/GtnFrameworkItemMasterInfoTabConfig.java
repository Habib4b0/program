package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterNewFormulationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterBaseCPIBlurAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterBaselineAMPBlurAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkItemMasterInfoTabConfig {

	public void addAdditionalInfoTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkComponentConfig mainRootLayout = componentConfig.getRootLayoutConfig(
				"itemMasterAdditionalInformationTab", GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, true);
		componentList.add(mainRootLayout);

		GtnUIFrameworkComponentConfig mainCssLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT, true, mainRootLayout.getComponentId(),
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(mainCssLayout);

		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addDosesPerUnit(componentList, componentConfig);
		addShelfLife(componentList, componentConfig);
		addShelfLifeType(componentList, componentConfig);
		addLastLotExpirationDate(componentList, componentConfig);
		addAuthorizedGenericIndicator(componentList, componentConfig);
		addPediatricExclusiveIndicator(componentList, componentConfig);
		addClottingFactorIndicator(componentList, componentConfig);
		addDualPricingIndicator(componentList, componentConfig);
		addAuthorizedGenericStartDate(componentList, componentConfig);
		addPediatricStartDate(componentList, componentConfig);
		addClottingFactorStartDate(componentList, componentConfig);
		addDiscountinuationDate(componentList, componentConfig);
		addAuthorizedGenericEndDate(componentList, componentConfig);
		addPediatricEndDate(componentList, componentConfig);
		addClottingFactorEndDate(componentList, componentConfig);
		addDivestitureDate(componentList, componentConfig);
		addNewFormulationIndicator(componentList, componentConfig);
		addBaselineAMP(componentList, componentConfig);
		addAcquisitionDate(componentList, componentConfig);
		addNonFederalExpirationDate(componentList, componentConfig);
		addBaseCPI(componentList, componentConfig);
		addAcquiredAMP(componentList, componentConfig);
		addMarketTerminationDate(componentList, componentConfig);
		addNewFormStartDate(componentList, componentConfig);
		addBaseCPIPeriod(componentList, componentConfig);
		addAcquiredBAMP(componentList, componentConfig);
		addNewFormEndDate(componentList, componentConfig);
		addDRA(componentList, componentConfig);
		addOBRABAMP(componentList, componentConfig);
		addNewFormulation(componentList, componentConfig);

	}

	private void addDosesPerUnit(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig dosesPerUnitlayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabDosesPerUnitlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(dosesPerUnitlayout);

		GtnUIFrameworkComponentConfig dosePerUnitConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabDosesPerUnit", true, dosesPerUnitlayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		dosePerUnitConfig.setAuthorizationIncluded(true);
		dosePerUnitConfig.setComponentName("Doses Per Unit");

		GtnUIFrameworkTextBoxConfig dosePerUnitMaxLengthConfig = componentConfig.getTextBoxConfig(true, false, true);
		dosePerUnitConfig.setGtnTextBoxConfig(dosePerUnitMaxLengthConfig);

		GtnUIFrameworkValidationConfig dosePerUnitValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Doses Per Unit can only be Alphanumeric", GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		dosePerUnitConfig.setGtnUIFrameworkValidationConfig(dosePerUnitValidationConfig);

		componentList.add(dosePerUnitConfig);
	}

	private void addShelfLife(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig shelfLifelayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabShelfLifelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(shelfLifelayout);

		GtnUIFrameworkComponentConfig shellLifeConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabShelfLife", true, shelfLifelayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		shellLifeConfig.setAuthorizationIncluded(true);
		shellLifeConfig.setComponentName("Shelf Life");

		GtnUIFrameworkTextBoxConfig shellLifeMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		shellLifeMaxLengthConfig.setMaximumLength(30);
		shellLifeConfig.setGtnTextBoxConfig(shellLifeMaxLengthConfig);

		GtnUIFrameworkValidationConfig shellLifeValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Shelf Life can only be Alphanumeric", GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		shellLifeConfig.setGtnUIFrameworkValidationConfig(shellLifeValidationConfig);

		componentList.add(shellLifeConfig);
	}

	private void addShelfLifeType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig shelfLifeTypeLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabShelfLifeTypelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(shelfLifeTypeLayout);

		GtnUIFrameworkComponentConfig shelfLifeTypeConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabShelfLifeType", true, shelfLifeTypeLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		shelfLifeTypeConfig.setAuthorizationIncluded(true);
		shelfLifeTypeConfig.setComponentName("Shelf Life Type");
		componentList.add(shelfLifeTypeConfig);

		GtnUIFrameworkComboBoxConfig shelfLifeTypeComboBoxConfig = componentConfig.getComboBoxConfig("SHELF_LIFE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		shelfLifeTypeConfig.setGtnComboboxConfig(shelfLifeTypeComboBoxConfig);
	}

	private void addLastLotExpirationDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig lotExpirationDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabLastLotExpirationDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(lotExpirationDateLayout);

		GtnUIFrameworkComponentConfig lotExpirationDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabLastLotExpirationDate", true, lotExpirationDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		lotExpirationDateConfig.setAuthorizationIncluded(true);
		lotExpirationDateConfig.setComponentName("Last Lot Expiration Datee");
		componentList.add(lotExpirationDateConfig);

		GtnUIFrameworkDateFieldConfig lotExpirationDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		lotExpirationDateFieldConfig.setImmediate(true);
		lotExpirationDateConfig.setGtnDateFieldConfig(lotExpirationDateFieldConfig);

		GtnUIFrameworkValidationConfig lotExpirationDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		lotExpirationDateConfig.setGtnUIFrameworkValidationConfig(lotExpirationDateValidationConfig);
	}

	private void addAuthorizedGenericIndicator(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig authorizedGenericIndicatorLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabAuthorizedGenericIndicatorlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(authorizedGenericIndicatorLayout);

		GtnUIFrameworkComponentConfig authorizedGenericIndicatorConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabAuthorizedGenericIndicator", true, authorizedGenericIndicatorLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		authorizedGenericIndicatorConfig.setAuthorizationIncluded(true);
		authorizedGenericIndicatorConfig.setComponentName("Authorized Generic Indicator");
		authorizedGenericIndicatorConfig
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(authorizedGenericIndicatorConfig);

		GtnUIFrameworkOptionGroupConfig authorizedGenericIndicatorOptionGroupConfig = componentConfig
				.getOptionGroupConfig(Arrays.asList("Yes", "No"), "No", false);
		authorizedGenericIndicatorConfig
				.setGtnUIFrameworkOptionGroupConfig(authorizedGenericIndicatorOptionGroupConfig);
	}

	private void addPediatricExclusiveIndicator(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig pediatricExclusiveIndicatorLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPediatricExclusiveIndicatorlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(pediatricExclusiveIndicatorLayout);

		GtnUIFrameworkComponentConfig pediatricExclusiveIndicatorConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPediatricExclusiveIndicator", true,
				pediatricExclusiveIndicatorLayout.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		pediatricExclusiveIndicatorConfig.setAuthorizationIncluded(true);
		pediatricExclusiveIndicatorConfig.setComponentName("Pediatric Exclusive Indicator");
		pediatricExclusiveIndicatorConfig
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(pediatricExclusiveIndicatorConfig);

		GtnUIFrameworkOptionGroupConfig pediatricExclusiveIndicatorOptionGroupConfig = componentConfig
				.getOptionGroupConfig(Arrays.asList("Yes", "No"), "No", false);
		pediatricExclusiveIndicatorConfig
				.setGtnUIFrameworkOptionGroupConfig(pediatricExclusiveIndicatorOptionGroupConfig);
	}

	private void addClottingFactorIndicator(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig clottingFactorIndicatorLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabClottingFactorIndicatorlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(clottingFactorIndicatorLayout);

		GtnUIFrameworkComponentConfig clottingFactorIndicatorConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabClottingFactorIndicator", true, clottingFactorIndicatorLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		clottingFactorIndicatorConfig.setAuthorizationIncluded(true);
		clottingFactorIndicatorConfig.setComponentName("Clotting Factor Indicator");
		clottingFactorIndicatorConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(clottingFactorIndicatorConfig);

		GtnUIFrameworkOptionGroupConfig clottingFactorIndicatorOptionGroupConfig = componentConfig
				.getOptionGroupConfig(Arrays.asList("Yes", "No"), "No", false);
		clottingFactorIndicatorConfig.setGtnUIFrameworkOptionGroupConfig(clottingFactorIndicatorOptionGroupConfig);
	}

	private void addDualPricingIndicator(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig dualPricingIndicatorLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabDualPricingIndicatorlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(dualPricingIndicatorLayout);

		GtnUIFrameworkComponentConfig dualPricingIndicatorConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabDualPricingIndicator", true, dualPricingIndicatorLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		dualPricingIndicatorConfig.setAuthorizationIncluded(true);
		dualPricingIndicatorConfig.setComponentName("Dual Pricing Indicator");
		dualPricingIndicatorConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(dualPricingIndicatorConfig);

		GtnUIFrameworkOptionGroupConfig dualPricingIndicatorOptionGroupConfig = componentConfig
				.getOptionGroupConfig(Arrays.asList("Yes", "No"), "No", false);
		dualPricingIndicatorConfig.setGtnUIFrameworkOptionGroupConfig(dualPricingIndicatorOptionGroupConfig);
	}

	private void addAuthorizedGenericStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig authorizedGenericStartDatelayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabAuthorizedGenericStartDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(authorizedGenericStartDatelayout);
		GtnUIFrameworkComponentConfig authorizedGenericStartDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabAuthorizedGenericStartDate", true, authorizedGenericStartDatelayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		authorizedGenericStartDateConfig.setAuthorizationIncluded(true);
		authorizedGenericStartDateConfig.setComponentName("Authorized Generic Start Date");
		componentList.add(authorizedGenericStartDateConfig);

		GtnUIFrameworkDateFieldConfig authorizedGenericStartDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		authorizedGenericStartDateFieldConfig.setImmediate(true);
		authorizedGenericStartDateConfig.setGtnDateFieldConfig(authorizedGenericStartDateFieldConfig);

		GtnUIFrameworkValidationConfig validationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		authorizedGenericStartDateConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addPediatricStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig pediatricStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPediatricStartDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(pediatricStartDateLayout);

		GtnUIFrameworkComponentConfig pediatricStartDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPediatricStartDate", true, pediatricStartDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		pediatricStartDateConfig.setAuthorizationIncluded(true);
		pediatricStartDateConfig.setComponentName("Pediatric Start Date");
		componentList.add(pediatricStartDateConfig);

		GtnUIFrameworkDateFieldConfig pediatricStartFieldConfig = new GtnUIFrameworkDateFieldConfig();
		pediatricStartFieldConfig.setImmediate(true);
		pediatricStartDateConfig.setGtnDateFieldConfig(pediatricStartFieldConfig);

		GtnUIFrameworkValidationConfig pediatricStartDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		pediatricStartDateConfig.setGtnUIFrameworkValidationConfig(pediatricStartDateValidationConfig);
	}

	private void addClottingFactorStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig clottingFactorStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabClottingFactorStartDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(clottingFactorStartDateLayout);

		GtnUIFrameworkComponentConfig clottingFactorStartDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabClottingFactorStartDate", true, clottingFactorStartDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		clottingFactorStartDateConfig.setAuthorizationIncluded(true);
		clottingFactorStartDateConfig.setComponentName("Clotting Factor Start Date");
		componentList.add(clottingFactorStartDateConfig);

		GtnUIFrameworkDateFieldConfig clottingFactorStartDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		clottingFactorStartDateFieldConfig.setImmediate(true);
		clottingFactorStartDateConfig.setGtnDateFieldConfig(clottingFactorStartDateFieldConfig);

		GtnUIFrameworkValidationConfig validationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		clottingFactorStartDateConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addDiscountinuationDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig discountinuationDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabDiscountinuationDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(discountinuationDateLayout);

		GtnUIFrameworkComponentConfig discountinuationDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabDiscountinuationDate", true, discountinuationDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		discountinuationDateConfig.setAuthorizationIncluded(true);
		discountinuationDateConfig.setComponentName("Discontinuation Date");
		componentList.add(discountinuationDateConfig);

		GtnUIFrameworkDateFieldConfig discountinuationDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		discountinuationDateFieldConfig.setImmediate(true);
		discountinuationDateConfig.setGtnDateFieldConfig(discountinuationDateFieldConfig);

		GtnUIFrameworkValidationConfig discountinuationDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		discountinuationDateConfig.setGtnUIFrameworkValidationConfig(discountinuationDateValidationConfig);
	}

	private void addAuthorizedGenericEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig authorizedGenericEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabAuthorizedGenericEndDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(authorizedGenericEndDateLayout);

		GtnUIFrameworkComponentConfig authorizedGenericEndDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabAuthorizedGenericEndDate", true, authorizedGenericEndDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		authorizedGenericEndDateConfig.setAuthorizationIncluded(true);
		authorizedGenericEndDateConfig.setComponentName("Authorized Generic End Date");
		componentList.add(authorizedGenericEndDateConfig);

		GtnUIFrameworkDateFieldConfig authorizedGenericEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		authorizedGenericEndDateFieldConfig.setImmediate(true);
		authorizedGenericEndDateConfig.setGtnDateFieldConfig(authorizedGenericEndDateFieldConfig);

		GtnUIFrameworkValidationConfig authorizedGenericEndDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		authorizedGenericEndDateConfig.setGtnUIFrameworkValidationConfig(authorizedGenericEndDateValidationConfig);
	}

	private void addPediatricEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig pediatricEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabPediatricEndDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(pediatricEndDateLayout);

		GtnUIFrameworkComponentConfig pediatricEndDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabPediatricEndDate", true, pediatricEndDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		pediatricEndDateConfig.setAuthorizationIncluded(true);
		pediatricEndDateConfig.setComponentName("Pediatric End Date");
		componentList.add(pediatricEndDateConfig);

		GtnUIFrameworkDateFieldConfig pediatricEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		pediatricEndDateFieldConfig.setImmediate(true);
		pediatricEndDateConfig.setGtnDateFieldConfig(pediatricEndDateFieldConfig);

		GtnUIFrameworkValidationConfig pediatricEndDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		pediatricEndDateConfig.setGtnUIFrameworkValidationConfig(pediatricEndDateValidationConfig);
	}

	private void addClottingFactorEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig clottingFactorEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabClottingFactorEndDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(clottingFactorEndDateLayout);

		GtnUIFrameworkComponentConfig clottingFactorEndDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabClottingFactorEndDate", true, clottingFactorEndDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		clottingFactorEndDateConfig.setAuthorizationIncluded(true);
		clottingFactorEndDateConfig.setComponentName("Clotting Factor End Date");
		componentList.add(clottingFactorEndDateConfig);

		GtnUIFrameworkDateFieldConfig clottingFactorEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		clottingFactorEndDateFieldConfig.setImmediate(true);
		clottingFactorEndDateConfig.setGtnDateFieldConfig(clottingFactorEndDateFieldConfig);

		GtnUIFrameworkValidationConfig clottingFactorEndDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		clottingFactorEndDateConfig.setGtnUIFrameworkValidationConfig(clottingFactorEndDateValidationConfig);
	}

	private void addDivestitureDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig divestitureDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabDivestitureDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(divestitureDateLayout);

		GtnUIFrameworkComponentConfig divestitureDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabDivestitureDate", true, divestitureDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		divestitureDateConfig.setAuthorizationIncluded(true);
		divestitureDateConfig.setComponentName("Divestiture Date");
		componentList.add(divestitureDateConfig);

		GtnUIFrameworkDateFieldConfig divestitureDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		divestitureDateFieldConfig.setImmediate(true);
		divestitureDateConfig.setGtnDateFieldConfig(divestitureDateFieldConfig);

		GtnUIFrameworkValidationConfig divestitureDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		divestitureDateConfig.setGtnUIFrameworkValidationConfig(divestitureDateValidationConfig);
	}

	private void addNewFormulationIndicator(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig newFormulationIndicatorLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabNewFormulationIndicatorlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(newFormulationIndicatorLayout);

		GtnUIFrameworkComponentConfig newFormulationIndicatorConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabNewFormulationIndicator", true, newFormulationIndicatorLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		newFormulationIndicatorConfig.setAuthorizationIncluded(true);
		newFormulationIndicatorConfig.setComponentName("New Formulation Indicator");
		newFormulationIndicatorConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(newFormulationIndicatorConfig);

		GtnUIFrameworkOptionGroupConfig newFormulationIndicatorOptionGroupConfig = componentConfig
				.getOptionGroupConfig(Arrays.asList("Yes", "No"), "No", false);
		newFormulationIndicatorConfig.setGtnUIFrameworkOptionGroupConfig(newFormulationIndicatorOptionGroupConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		List<Object> actionparameter = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionparameter.add(GtnFrameworkItemMasterNewFormulationAction.class.getName());
		actionparameter.add(Arrays.asList("additionalInformationTabNewFormulationLookup",
				"itemInformationTabNewFormStartDate", "itemInformationTabBaseNewFormEndDate"));
		customAction.setActionParameterList(actionparameter);
		actionConfigList.add(customAction);
		newFormulationIndicatorConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addBaselineAMP(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig baselineAMPLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabBaselineAMPlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(baselineAMPLayout);

		GtnUIFrameworkComponentConfig baseLineAMPConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabBaselineAMP", true, baselineAMPLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		baseLineAMPConfig.setAuthorizationIncluded(true);
		baseLineAMPConfig.setComponentName("Baseline AMP");

		GtnUIFrameworkTextBoxConfig baseLineAMPMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		baseLineAMPMaxLengthConfig.setMaximumLength(50);
		baseLineAMPConfig.setGtnTextBoxConfig(baseLineAMPMaxLengthConfig);
                
                List<GtnUIFrameWorkActionConfig> actionBlurConfigBaseline = new ArrayList<>();
                GtnUIFrameWorkActionConfig blurActionForAMP = new GtnUIFrameWorkActionConfig();
                blurActionForAMP.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
                blurActionForAMP.addActionParameter(GtnFrameworkItemMasterBaselineAMPBlurAction.class.getName());
                blurActionForAMP.addActionParameter(Arrays.asList("additionalInformationTabBaselineAMP"));
                actionBlurConfigBaseline.add(blurActionForAMP);
                baseLineAMPMaxLengthConfig.setBlurActionConfigList(actionBlurConfigBaseline);

		GtnUIFrameworkValidationConfig baseLineAMPValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Baseline AMP can contain only Numbers", GtnFrameworkRegexStringConstants.NUMERIC_WITH_SIX_DECIMAL_PRECISION);
		baseLineAMPConfig.setGtnUIFrameworkValidationConfig(baseLineAMPValidationConfig);
		componentList.add(baseLineAMPConfig);
	}

	private void addAcquisitionDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig acquisitionDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabAcquisitionDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(acquisitionDateLayout);

		GtnUIFrameworkComponentConfig acquisitionDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabAcquisitionDate", true, acquisitionDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		acquisitionDateConfig.setAuthorizationIncluded(true);
		acquisitionDateConfig.setComponentName("Acquisition Date");
		componentList.add(acquisitionDateConfig);

		GtnUIFrameworkDateFieldConfig acquisitionDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		acquisitionDateFieldConfig.setImmediate(true);
		acquisitionDateConfig.setGtnDateFieldConfig(acquisitionDateFieldConfig);

		GtnUIFrameworkValidationConfig acquisitionDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		acquisitionDateConfig.setGtnUIFrameworkValidationConfig(acquisitionDateValidationConfig);
	}

	private void addNonFederalExpirationDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig nonFederalExpirationDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabNonFederalExpirationDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(nonFederalExpirationDateLayout);
		GtnUIFrameworkComponentConfig nonFederalExpirationDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabNonFederalExpirationDate", true, nonFederalExpirationDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		nonFederalExpirationDateConfig.setAuthorizationIncluded(true);
		nonFederalExpirationDateConfig.setComponentName("Non-Federal Expiration Date");
		componentList.add(nonFederalExpirationDateConfig);

		GtnUIFrameworkDateFieldConfig nonFederalExpirationDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		nonFederalExpirationDateFieldConfig.setImmediate(true);
		nonFederalExpirationDateConfig.setGtnDateFieldConfig(nonFederalExpirationDateFieldConfig);

		GtnUIFrameworkValidationConfig nonFederalExpirationDateLayoutValidationConfig = componentConfig
				.getValidationConfig(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
						GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED,
						GtnFrameworkRegexStringConstants.DATE_FORMAT);
		nonFederalExpirationDateConfig
				.setGtnUIFrameworkValidationConfig(nonFederalExpirationDateLayoutValidationConfig);
	}

	private void addBaseCPI(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig baseCPILayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabBaseCPIlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(baseCPILayout);

		GtnUIFrameworkComponentConfig baseCPIConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabBaseCPI", true, baseCPILayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		baseCPIConfig.setAuthorizationIncluded(true);
		baseCPIConfig.setComponentName("Base CPI");

		GtnUIFrameworkTextBoxConfig baseCPIMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		baseCPIMaxLengthConfig.setMaximumLength(50);
		baseCPIConfig.setGtnTextBoxConfig(baseCPIMaxLengthConfig);
                
                List<GtnUIFrameWorkActionConfig> actionBlurConfigBaseCpi = new ArrayList<>();
                GtnUIFrameWorkActionConfig blurActionForCPI = new GtnUIFrameWorkActionConfig();
                blurActionForCPI.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
                blurActionForCPI.addActionParameter(GtnFrameworkItemMasterBaseCPIBlurAction.class.getName());
                blurActionForCPI.addActionParameter(Arrays.asList("additionalInformationTabBaseCPI"));
                actionBlurConfigBaseCpi.add(blurActionForCPI);
                baseCPIMaxLengthConfig.setBlurActionConfigList(actionBlurConfigBaseCpi);

		GtnUIFrameworkValidationConfig baseCPIValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Base CPI can contain only Numbers", GtnFrameworkRegexStringConstants.NUMERIC_WITH_THREE_DECIMAL_PRECISION);
		baseCPIConfig.setGtnUIFrameworkValidationConfig(baseCPIValidationConfig);

		componentList.add(baseCPIConfig);
	}

	private void addAcquiredAMP(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig acquiredAMPLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabAcquiredAMPlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(acquiredAMPLayout);

		GtnUIFrameworkComponentConfig acquiredAMPConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabAcquiredAMP", true, acquiredAMPLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		acquiredAMPConfig.setAuthorizationIncluded(true);
		acquiredAMPConfig.setComponentName("Acquired AMP");

		GtnUIFrameworkTextBoxConfig acquiredAMPMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		acquiredAMPConfig.setGtnTextBoxConfig(acquiredAMPMaxLengthConfig);

		GtnUIFrameworkValidationConfig acquiredAMPValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Acquired AMP can only be Alphanumeric", GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		acquiredAMPConfig.setGtnUIFrameworkValidationConfig(acquiredAMPValidationConfig);

		componentList.add(acquiredAMPConfig);
	}

	private void addMarketTerminationDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig marketTerminationDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabMarketTerminationDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(marketTerminationDateLayout);

		GtnUIFrameworkComponentConfig marketTerminationDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabMarketTerminationDate", true, marketTerminationDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		marketTerminationDateConfig.setAuthorizationIncluded(true);
		marketTerminationDateConfig.setComponentName("Market Termination Date");
		componentList.add(marketTerminationDateConfig);

		GtnUIFrameworkDateFieldConfig marketTerminationDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		marketTerminationDateFieldConfig.setImmediate(true);
		marketTerminationDateConfig.setGtnDateFieldConfig(marketTerminationDateFieldConfig);

		GtnUIFrameworkValidationConfig marketTerminationDateValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		marketTerminationDateConfig.setGtnUIFrameworkValidationConfig(marketTerminationDateValidationConfig);
	}

	private void addNewFormStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig newFormStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabNewFormStartDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(newFormStartDateLayout);

		GtnUIFrameworkComponentConfig newFormStartDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabNewFormStartDate", true, newFormStartDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		newFormStartDateConfig.setAuthorizationIncluded(true);
		newFormStartDateConfig.setComponentName("New Formulation Start Date");
		newFormStartDateConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		newFormStartDateConfig.setEnable(false);

		GtnUIFrameworkDateFieldConfig newFormStartDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		newFormStartDateFieldConfig.setImmediate(true);
		newFormStartDateFieldConfig.setEnable(false);
		newFormStartDateConfig.setGtnDateFieldConfig(newFormStartDateFieldConfig);

		GtnUIFrameworkValidationConfig newFormStartDateLayoutValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		newFormStartDateConfig.setGtnUIFrameworkValidationConfig(newFormStartDateLayoutValidationConfig);
		componentList.add(newFormStartDateConfig);
	}

	private void addBaseCPIPeriod(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig baseCPIPeriodLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabBaseCPIPeriodlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(baseCPIPeriodLayout);

		GtnUIFrameworkComponentConfig baseCPIPeriodConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabBaseCPIPeriod", true, baseCPIPeriodLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		baseCPIPeriodConfig.setAuthorizationIncluded(true);
		baseCPIPeriodConfig.setComponentName("Base CPI Period");
		componentList.add(baseCPIPeriodConfig);

		GtnUIFrameworkDateFieldConfig baseCPIPeriodDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		baseCPIPeriodDateFieldConfig.setImmediate(true);
		baseCPIPeriodConfig.setGtnDateFieldConfig(baseCPIPeriodDateFieldConfig);

		GtnUIFrameworkValidationConfig baseCPIPeriodValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		baseCPIPeriodConfig.setGtnUIFrameworkValidationConfig(baseCPIPeriodValidationConfig);
	}

	private void addAcquiredBAMP(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig acquiredBAMPLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabAcquiredBAMPlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(acquiredBAMPLayout);

		GtnUIFrameworkComponentConfig acquiredBAMP = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabAcquiredBAMP", true, acquiredBAMPLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		acquiredBAMP.setAuthorizationIncluded(true);
		acquiredBAMP.setComponentName("Acquired BAMP");

		GtnUIFrameworkTextBoxConfig acquiredBAMPMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		acquiredBAMP.setGtnTextBoxConfig(acquiredBAMPMaxLengthConfig);

		GtnUIFrameworkValidationConfig acquiredBAMPValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				"Acquired BAMP can contains only Numbers", GtnFrameworkRegexStringConstants.NUMERIC_FORMAT);
		acquiredBAMP.setGtnUIFrameworkValidationConfig(acquiredBAMPValidationConfig);

		componentList.add(acquiredBAMP);
	}

	private void addNewFormEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig baseNewFormEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemInformationTabBaseNewFormEndDatelayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(baseNewFormEndDateLayout);

		GtnUIFrameworkComponentConfig baseNewFormEndDateConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemInformationTabBaseNewFormEndDate", true, baseNewFormEndDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		baseNewFormEndDateConfig.setAuthorizationIncluded(true);
		baseNewFormEndDateConfig.setComponentName("New Formulation End Date");
		baseNewFormEndDateConfig.setEnable(false);

		GtnUIFrameworkDateFieldConfig baseNewFormEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		baseNewFormEndDateFieldConfig.setImmediate(true);
		baseNewFormEndDateFieldConfig.setEnable(false);
		baseNewFormEndDateConfig.setGtnDateFieldConfig(baseNewFormEndDateFieldConfig);

		GtnUIFrameworkValidationConfig validationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		baseNewFormEndDateConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		componentList.add(baseNewFormEndDateConfig);
	}

	private void addDRA(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig draLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabDRAlayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(draLayout);

		GtnUIFrameworkComponentConfig draConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabDRA", true, draLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		draConfig.setAuthorizationIncluded(true);
		draConfig.setComponentName("DRA");

		GtnUIFrameworkTextBoxConfig draMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		draMaxLengthConfig.setMaximumLength(50);
		draConfig.setGtnTextBoxConfig(draMaxLengthConfig);

		GtnUIFrameworkValidationConfig draValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "DRA can contains only Numbers",
				GtnFrameworkRegexStringConstants.NUMERIC_FORMAT);
		draConfig.setGtnUIFrameworkValidationConfig(draValidationConfig);

		componentList.add(draConfig);
	}

	private void addOBRABAMP(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig obraBAMPLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabOBRABAMPLayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(obraBAMPLayout);

		GtnUIFrameworkComponentConfig obraBAMPConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabOBRABAMP", true, obraBAMPLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		obraBAMPConfig.setAuthorizationIncluded(true);
		obraBAMPConfig.setComponentName("OBRA BAMP");

		GtnUIFrameworkTextBoxConfig obraBAMPMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		obraBAMPMaxLengthConfig.setMaximumLength(50);
		obraBAMPConfig.setGtnTextBoxConfig(obraBAMPMaxLengthConfig);

		GtnUIFrameworkValidationConfig obraBAMPValidationConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "DRA can contains only Numbers",
				GtnFrameworkRegexStringConstants.NUMERIC_FORMAT);
		obraBAMPConfig.setGtnUIFrameworkValidationConfig(obraBAMPValidationConfig);

		componentList.add(obraBAMPConfig);
	}

	private void addNewFormulation(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig newFormulationLayout = componentConfig.getHorizontalLayoutConfig(
				"additionalInformationTabNewFormulationLayout", true,
				GtnFrameworkCommonConstants.ADDITIONAL_INFORMATION_TAB_LAYOUT);
		componentList.add(newFormulationLayout);

		GtnUIFrameworkComponentConfig newFormulationConfig = componentConfig.getUIFrameworkComponentConfig(
				"additionalInformationTabNewFormulationLookup", true, newFormulationLayout.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		newFormulationConfig.setAuthorizationIncluded(true);
		newFormulationConfig.setComponentName("New Formulation");
		newFormulationConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig entityCodeNoConfig = componentConfig.getTextBoxConfig(false, false, true);
		newFormulationConfig.setGtnTextBoxConfig(entityCodeNoConfig);
		newFormulationConfig.setEnable(false);
		componentList.add(newFormulationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("itemMasterNewFormulation");
		popupActionConfig.addActionParameter("New Formulation");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		actionConfigList.add(popupActionConfig);
		newFormulationConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}
}
