package com.stpl.gtn.gtn2o.ui.module.cfp.config;

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
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpClassContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCfpInformationTabConfig {

	public void addCFPInformationTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkComponentConfig mainLayout = componentConfig.getRootLayoutConfig("cfpInformationTab",
				GtnUIFrameworkLayoutType.CSS_LAYOUT, true);
		componentList.add(mainLayout);

		GtnUIFrameworkComponentConfig cssGtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT, true, "cfpInformationTab");
		cssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(cssGtnLayout);

		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCFPId(componentList, componentConfig);
		addCFPNo(componentList, componentConfig);
		addCFPName(componentList, componentConfig);
		addCFPStatus(componentList, componentConfig);
		addCFPStartDate(componentList, componentConfig);
		addCFPEndDate(componentList, componentConfig);
		addCFPType(componentList, componentConfig);
		addCfpCategory(componentList, componentConfig);
		addCFPTradeClass(componentList, componentConfig);
		addCFPDesignation(componentList, componentConfig);
		addParentCFPId(componentList, componentConfig);
		addParentCFPSId(componentList, componentConfig);
		addParentCFPName(componentList, componentConfig);
		addCFPSalesInclusion(componentList, componentConfig);
		addCreatedBy(componentList, componentConfig);
		addCreatedDate(componentList, componentConfig);
		addModifiedBy(componentList, componentConfig);
		addModifiedDate(componentList, componentConfig);
	}

	private void addCFPId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpIdLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPIdlayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpIdLayout);

		GtnUIFrameworkComponentConfig cfpId = componentConfig.getUIFrameworkComponentConfig("cfpInformationTabCFPId",
				true, "cfpInformationTabCFPIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		cfpId.setComponentName("CFP ID");
		cfpId.setAuthorizationIncluded(true);
		cfpId.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		componentList.add(cfpId);

		GtnUIFrameworkValidationConfig cfpIdValidationConfig = new GtnUIFrameworkValidationConfig();
		cfpIdValidationConfig.setMaxLength(50);
		cfpIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cfpId.setGtnUIFrameworkValidationConfig(cfpIdValidationConfig);

	}

	private void addCFPNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpNoLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPNolayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpNoLayout);

		GtnUIFrameworkComponentConfig cfpNoConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPNo", true, "cfpInformationTabCFPNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		cfpNoConfig.setComponentName("CFP No");
		cfpNoConfig.setAuthorizationIncluded(true);
		cfpNoConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		componentList.add(cfpNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setMaxLength(50);
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cfpNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addCFPName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig cfpNameLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPNamelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpNameLayout);

		GtnUIFrameworkComponentConfig cfpNameConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPName", true, "cfpInformationTabCFPNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		cfpNameConfig.setComponentName("CFP Name");
		cfpNameConfig.setAuthorizationIncluded(true);
		cfpNameConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);

		componentList.add(cfpNameConfig);

		GtnUIFrameworkValidationConfig cfpNameValidationConfig = new GtnUIFrameworkValidationConfig();
		cfpNameValidationConfig.setMaxLength(50);
		cfpNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cfpNameConfig.setGtnUIFrameworkValidationConfig(cfpNameValidationConfig);

	}

	private void addCFPStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpStatusLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPStatuslayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpStatusLayout);

		GtnUIFrameworkComponentConfig cfpStatus = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPStatus", true, "cfpInformationTabCFPStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpStatus.setAuthorizationIncluded(true);
		cfpStatus.setComponentName("CFP Status");
		cfpStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(cfpStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig companyStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		companyStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cfpStatus.setGtnUIFrameworkValidationConfig(companyStatusValidationConfig);

	}

	private void addCFPStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig cfpStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationCFPStartDatelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpStartDateLayout);

		GtnUIFrameworkComponentConfig cfpStartDate = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationCFPStartDate", true, "cfpInformationCFPStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		cfpStartDate.setAuthorizationIncluded(true);
		cfpStartDate.setComponentName("CFP Start Date");
		cfpStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(cfpStartDate);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		cfpStartDate.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addCFPEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig cfpEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationCFPEndDatelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpEndDateLayout);

		GtnUIFrameworkComponentConfig cfpEndDate = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationCFPEndDate", true, "cfpInformationCFPEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		cfpEndDate.setAuthorizationIncluded(true);
		cfpEndDate.setComponentName("CFP End Date");
		componentList.add(cfpEndDate);
	}

	private void addCFPType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpTypeLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPTypelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpTypeLayout);

		GtnUIFrameworkComponentConfig cfpType = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPType", true, "cfpInformationTabCFPTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpType.setAuthorizationIncluded(true);
		cfpType.setComponentName("CFP Type");
		componentList.add(cfpType);

		GtnUIFrameworkComboBoxConfig cfpTypeConfig = componentConfig.getComboBoxConfig("CFP_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpType.setGtnComboboxConfig(cfpTypeConfig);
	}

	private void addCfpCategory(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpCategoryLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPCategorylayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpCategoryLayout);

		GtnUIFrameworkComponentConfig cfpCategory = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPCategory", true, "cfpInformationTabCFPCategorylayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpCategory.setAuthorizationIncluded(true);
		cfpCategory.setComponentName("CFP Category");
		componentList.add(cfpCategory);

		GtnUIFrameworkComboBoxConfig cfpCategoryConfig = componentConfig.getComboBoxConfig("CFP_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpCategory.setGtnComboboxConfig(cfpCategoryConfig);

	}

	private void addCFPTradeClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpTradeClassLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPTradeClasslayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpTradeClassLayout);

		GtnUIFrameworkComponentConfig cfpTradeClass = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPTradeClass", true, "cfpInformationTabCFPTradeClasslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpTradeClass.setAuthorizationIncluded(true);
		cfpTradeClass.setComponentName("CFP Trade Class");
		componentList.add(cfpTradeClass);

		GtnUIFrameworkComboBoxConfig cfpTradeClassConfig = componentConfig.getComboBoxConfig("CFP_TRADE_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpTradeClass.setGtnComboboxConfig(cfpTradeClassConfig);

	}

	private void addCFPDesignation(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpDesignationLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPDesignationlayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpDesignationLayout);

		GtnUIFrameworkComponentConfig cfpDesignation = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPDesignation", true, "cfpInformationTabCFPDesignationlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpDesignation.setAuthorizationIncluded(true);
		cfpDesignation.setComponentName("CFP Designation");
		componentList.add(cfpDesignation);

		GtnUIFrameworkComboBoxConfig cfpDesignationConfig = componentConfig.getComboBoxConfig("CFP_DESIGNATION",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpDesignation.setGtnComboboxConfig(cfpDesignationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_DESIGNATION_VALUE_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList("cfpInformationTabParentCFPId", "cfpInformationTabParentCFPName"));
		actionConfigList.add(customAction);
		cfpDesignation.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addParentCFPId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCFPIdLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabParentCFPIdlayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(parentCFPIdLayout);

		GtnUIFrameworkComponentConfig parentCFPId = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabParentCFPId", true, "cfpInformationTabParentCFPIdlayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentCFPId.setAuthorizationIncluded(true);
		parentCFPId.setComponentName("Parent CFP ID");
		parentCFPId.setEnable(false);
		componentList.add(parentCFPId);

		GtnUIFrameworkTextBoxConfig parentCFPIdConfig = new GtnUIFrameworkTextBoxConfig();
		parentCFPIdConfig.setEnable(false);
		parentCFPId.setGtnTextBoxConfig(parentCFPIdConfig);

		List<GtnUIFrameWorkActionConfig> parentCFPIdActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig parentCFPIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
		parentCFPIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		parentCFPIdPopupActionConfig.addActionParameter("parentCfpView");
		parentCFPIdPopupActionConfig.addActionParameter("Parent CFP No");
		parentCFPIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		parentCFPIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);

		GtnUIFrameWorkActionConfig setDeafaultAction = new GtnUIFrameWorkActionConfig();
		setDeafaultAction.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<GtnUIFrameWorkActionConfig> popupChildActionConfigList = new ArrayList<>();
		List<Object> selectResetParams = new ArrayList<>();
		selectResetParams.add(Arrays.asList("parentCfpsearchResultTable",
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID, "parentCfpPopupCFPName",
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID, "parentCfpPopupCFPStatus", "parentCfpPopupCFPType",
				"parentCfpSearchcompanyId", "parentCfpSearchcompanyNo", "parentCfpSearchcompanyName"));
		selectResetParams.add(Arrays.asList(null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		setDeafaultAction.setActionParameterList(selectResetParams);
		popupChildActionConfigList.add(setDeafaultAction);
		parentCFPIdPopupActionConfig.setChildActionList(popupChildActionConfigList);
		parentCFPIdActionConfigList.add(parentCFPIdPopupActionConfig);
		// To disable Select Button when a popup is opened
		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new String[] { "parentCfpSelectButton" };
		disableAction.setActionParameterList(Arrays.asList(disableField));
		parentCFPIdActionConfigList.add(disableAction);
		parentCFPId.setGtnUIFrameWorkActionConfigList(parentCFPIdActionConfigList);

	}

	private void addParentCFPSId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCompanySID = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabParentCFSPId", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		parentCompanySID.setAuthorizationIncluded(true);
		parentCompanySID.setComponentName("Parent CFP SID");
		parentCompanySID.setVisible(false);
		parentCompanySID.setEnable(false);
		componentList.add(parentCompanySID);
		GtnUIFrameworkTextBoxConfig textConfig = new GtnUIFrameworkTextBoxConfig();
		textConfig.setEnable(false);
		parentCompanySID.setGtnTextBoxConfig(textConfig);
	}

	private void addParentCFPName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig parentCFPNameLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabParentCFPNamelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(parentCFPNameLayout);

		GtnUIFrameworkComponentConfig parentCFPName = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabParentCFPName", true, "cfpInformationTabParentCFPNamelayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentCFPName.setAuthorizationIncluded(true);
		parentCFPName.setComponentName("Parent CFP Name");
		componentList.add(parentCFPName);

		GtnUIFrameworkTextBoxConfig parentCFPNameConfig = componentConfig.getTextBoxConfig(false, false, true);
		parentCFPNameConfig.setReadOnly(true);
		parentCFPName.setGtnTextBoxConfig(parentCFPNameConfig);

		List<GtnUIFrameWorkActionConfig> parentCFPNameActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("parentCfpView");
		popupActionConfig.addActionParameter("Parent CFP Name");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		GtnUIFrameWorkActionConfig parentCFPNameresetAction = new GtnUIFrameWorkActionConfig();
		parentCFPNameresetAction.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<GtnUIFrameWorkActionConfig> popupChildActionConfigList = new ArrayList<>();
		List<Object> selectResetParams = new ArrayList<>();
		selectResetParams.add(Arrays.asList("parentCfpsearchResultTable",
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID, "parentCfpPopupCFPName",
				GtnFrameworkCommonConstants.PARENT_CFP_POPUP_CFP_ID, "parentCfpPopupCFPStatus", "parentCfpPopupCFPType",
				"parentCfpSearchcompanyId", "parentCfpSearchcompanyNo", "parentCfpSearchcompanyName"));
		selectResetParams.add(Arrays.asList(null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		parentCFPNameresetAction.setActionParameterList(selectResetParams);
		popupChildActionConfigList.add(parentCFPNameresetAction);
		popupActionConfig.setChildActionList(popupChildActionConfigList);
		parentCFPNameActionConfigList.add(popupActionConfig);

		parentCFPName.setGtnUIFrameWorkActionConfigList(parentCFPNameActionConfigList);
	}

	private void addCFPSalesInclusion(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpSalesInclusionLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCFPSalesInclusionlayout", true,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(cfpSalesInclusionLayout);

		GtnUIFrameworkComponentConfig cfpSalesInclusion = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCFPSalesInclusion", true, "cfpInformationTabCFPSalesInclusionlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpSalesInclusion.setAuthorizationIncluded(true);
		cfpSalesInclusion.setComponentName("Sales Inclusion");
		cfpSalesInclusion.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkComboBoxConfig cfpSalesInclusionConfig = componentConfig.getComboBoxConfig("LOCKED_STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpSalesInclusion.setGtnComboboxConfig(cfpSalesInclusionConfig);

		GtnUIFrameworkValidationConfig cfpSalesInclusionValConfig = new GtnUIFrameworkValidationConfig();
		cfpSalesInclusionValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cfpSalesInclusion.setGtnUIFrameworkValidationConfig(cfpSalesInclusionValConfig);

		componentList.add(cfpSalesInclusion);
	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig createdByLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabCreatedBylayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(createdByLayout);

		GtnUIFrameworkComponentConfig createdBy = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabCreatedBy", true, "cfpInformationTabCreatedBylayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		createdBy.setAuthorizationIncluded(true);
		createdBy.setComponentName("Created By");
		componentList.add(createdBy);

		GtnUIFrameworkTextBoxConfig createdByConfig = new GtnUIFrameworkTextBoxConfig();
		createdByConfig.setEnable(false);
		createdBy.setGtnTextBoxConfig(createdByConfig);
	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig createdDateLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationCreatedDatelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(createdDateLayout);

		GtnUIFrameworkComponentConfig createdDate = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationCreatedDate", true, "cfpInformationCreatedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		createdDate.setAuthorizationIncluded(true);
		createdDate.setComponentName("Created Date");
		createdDate.setEnable(false);
		componentList.add(createdDate);
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig modifiedByLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationTabModifiedBylayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(modifiedByLayout);

		GtnUIFrameworkComponentConfig modifiedBy = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationTabModifiedBy", true, "cfpInformationTabModifiedBylayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		modifiedBy.setAuthorizationIncluded(true);
		modifiedBy.setComponentName("Modified By");

		GtnUIFrameworkTextBoxConfig modifiedByConfig = new GtnUIFrameworkTextBoxConfig();
		modifiedByConfig.setEnable(false);
		modifiedBy.setGtnTextBoxConfig(modifiedByConfig);

		componentList.add(modifiedBy);
	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig modifiedDateLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpInformationModifiedDatelayout", true, GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_LAYOUT);
		componentList.add(modifiedDateLayout);

		GtnUIFrameworkComponentConfig modifiedDate = componentConfig.getUIFrameworkComponentConfig(
				"cfpInformationModifiedDate", true, "cfpInformationModifiedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		modifiedDate.setAuthorizationIncluded(true);
		modifiedDate.setComponentName("Modified Date");
		modifiedDate.setEnable(false);
		componentList.add(modifiedDate);
	}

}
