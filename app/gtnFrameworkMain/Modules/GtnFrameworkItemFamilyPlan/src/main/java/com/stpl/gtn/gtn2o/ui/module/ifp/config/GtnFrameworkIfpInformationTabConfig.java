package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpClassContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkIfpInformationTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addIFPInformationTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpInformationTab", false,
				null);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true, "ifpInformationTab");
		cssGtnLayout.getGtnLayoutConfig().setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(cssGtnLayout);
		addInfoFieldComponent(componentList);
	}

	private void addInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addInfoIFPId(componentList);
		addInfoIFPNo(componentList);
		addInfoIFPName(componentList);
		addInfoIFPStatus(componentList);
		addInfoIFPStartDate(componentList);
		addInfoIFPEndDate(componentList);
		addInfoIFPDesignation(componentList);
		addInfoParentIFPId(componentList);
		addInfoParentIFPName(componentList);
		addInfoIFPType(componentList);
		addInfoCreatedBy(componentList);
		addInfoIfpCategory(componentList);
		addInfoCreatedDate(componentList);
		addInfoModifiedBy(componentList);
		addInfoModifiedDate(componentList);
	}

	private void addInfoIFPId(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabIFPIdlayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInformationTabIFPIdConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPId", true, "ifpInformationTabIFPIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpInformationTabIFPIdConfig.setAuthorizationIncluded(true);
		ifpInformationTabIFPIdConfig.setComponentName("IFP ID");
		ifpInformationTabIFPIdConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		setDefaultTextBoxConfig(50, new GtnUIFrameworkTextBoxConfig(), ifpInformationTabIFPIdConfig, componentList);
	}

	private void addInfoIFPNo(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabIFPNolayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoNoConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPNo", true, "ifpInformationTabIFPNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpInfoNoConfig.setAuthorizationIncluded(true);
		ifpInfoNoConfig.setComponentName("IFP NO");
		ifpInfoNoConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		setDefaultTextBoxConfig(50, new GtnUIFrameworkTextBoxConfig(), ifpInfoNoConfig, componentList);
	}

	private void setDefaultTextBoxConfig(int maxLength, GtnUIFrameworkTextBoxConfig gtnMaxLengthTextBoxConfig,
			GtnUIFrameworkComponentConfig ifpInfoNoConfig, List<GtnUIFrameworkComponentConfig> componentList) {
		gtnMaxLengthTextBoxConfig.setMaximumLength(maxLength);
		ifpInfoNoConfig.setGtnTextBoxConfig(gtnMaxLengthTextBoxConfig);
		GtnUIFrameworkValidationConfig valicationConfig = new GtnUIFrameworkValidationConfig();
		valicationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpInfoNoConfig.setGtnUIFrameworkValidationConfig(valicationConfig);

		componentList.add(ifpInfoNoConfig);

	}

	private void addInfoIFPName(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabIFPNamelayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoNameConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPName", true, "ifpInformationTabIFPNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpInfoNameConfig.setAuthorizationIncluded(true);
		ifpInfoNameConfig.setComponentName("IFP Name");
		ifpInfoNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig gtnMaxLengthTextBoxConfig = new GtnUIFrameworkTextBoxConfig();
		gtnMaxLengthTextBoxConfig.setMaximumLength(100);
		ifpInfoNameConfig.setGtnTextBoxConfig(gtnMaxLengthTextBoxConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpInfoNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(ifpInfoNameConfig);
	}

	private void addInfoIFPStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabIFPStatuslayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoStatus = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPStatus", true, "ifpInformationTabIFPStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		ifpInfoStatus.setAuthorizationIncluded(true);
		ifpInfoStatus.setComponentName("IFP Status");
		ifpInfoStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(ifpInfoStatus);

		GtnUIFrameworkComboBoxConfig ifpInfoStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpInfoStatus.setGtnComboboxConfig(ifpInfoStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ifpInfoStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addInfoIFPStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationIFPStartDatelayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoInformationIfpStartDate = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationIFPStartDate", true, "ifpInformationIFPStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		ifpInfoInformationIfpStartDate.setAuthorizationIncluded(true);
		ifpInfoInformationIfpStartDate.setComponentName("IFP Start Date");
		ifpInfoInformationIfpStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(ifpInfoInformationIfpStartDate);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ifpInfoInformationIfpStartDate.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addInfoIFPEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationIFPEndDatelayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoInformationIfpEndDate = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationIFPEndDate", true, "ifpInformationIFPEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		ifpInfoInformationIfpEndDate.setAuthorizationIncluded(true);
		ifpInfoInformationIfpEndDate.setComponentName("IFP End Date");
		componentList.add(ifpInfoInformationIfpEndDate);
	}

	private void addInfoIFPType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"ifpInformationTabIFPTypelayout", true, GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpInfoType = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPType", true, "ifpInformationTabIFPTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		ifpInfoType.setAuthorizationIncluded(true);
		ifpInfoType.setComponentName("IFP Type");
		componentList.add(ifpInfoType);

		GtnUIFrameworkComboBoxConfig ifpInfoTypeConfig = new GtnUIFrameworkComboBoxConfig();
		ifpInfoTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpInfoTypeConfig.setComboBoxType("IFP_TYPE");
		ifpInfoType.setGtnComboboxConfig(ifpInfoTypeConfig);
	}

	private void addInfoIfpCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabIFPCategorylayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoCategory = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPCategory", true, "ifpInformationTabIFPCategorylayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		ifpInfoCategory.setAuthorizationIncluded(true);
		ifpInfoCategory.setComponentName("IFP Category");
		componentList.add(ifpInfoCategory);

		GtnUIFrameworkComboBoxConfig ifpInfoCategoryConfig = new GtnUIFrameworkComboBoxConfig();
		ifpInfoCategoryConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpInfoCategoryConfig.setComboBoxType("IFP_CATEGORY");
		ifpInfoCategory.setGtnComboboxConfig(ifpInfoCategoryConfig);

	}

	private void addInfoIFPDesignation(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"ifpInformationTabIFPDesignationlayout", true, GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpInfoType = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabIFPDesignation", true, "ifpInformationTabIFPDesignationlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		ifpInfoType.setAuthorizationIncluded(true);
		ifpInfoType.setComponentName("IFP Designation");
		componentList.add(ifpInfoType);

		GtnUIFrameworkComboBoxConfig ifpInfoTypeConfig = new GtnUIFrameworkComboBoxConfig();
		ifpInfoTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpInfoTypeConfig.setComboBoxType("IFP_DESIGNATION");
		ifpInfoType.setGtnComboboxConfig(ifpInfoTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_DESIGNATION_VALUE_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList("ifpInformationTabParentIFPId", "ifpInformationTabParentIFPName"));
		actionConfigList.add(customAction);
		ifpInfoType.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addInfoParentIFPId(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabParentIFPIdlayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);
		GtnUIFrameworkComponentConfig parentIfpid = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabParentIFPId", true, "ifpInformationTabParentIFPIdlayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentIfpid.setAuthorizationIncluded(true);
		parentIfpid.setComponentName("Parent IFP ID");
		parentIfpid.setEnable(false);
		componentList.add(parentIfpid);

		GtnUIFrameworkTextBoxConfig textConfig = new GtnUIFrameworkTextBoxConfig();
		textConfig.setEnable(false);
		parentIfpid.setGtnTextBoxConfig(textConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig ifpInfoIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
		ifpInfoIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		ifpInfoIdPopupActionConfig.addActionParameter("parentIfpView");
		ifpInfoIdPopupActionConfig.addActionParameter("Parent IFP No");
		ifpInfoIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		ifpInfoIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		/**
		 * To Reset Look Up
		 */
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<GtnUIFrameWorkActionConfig> popupChildActionConfigList = new ArrayList<>();
		List<Object> selectResetParams = new ArrayList<>();
		selectResetParams.add(Arrays.asList("parentIfpsearchResultTable", "parentifpId", "parentIfpNo", "parentIfpName",
				"parentIfpStatus", "parentIfpType"));
		selectResetParams.add(Arrays.asList(null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null));
		resetTableConfig.setActionParameterList(selectResetParams);
		popupChildActionConfigList.add(resetTableConfig);
		ifpInfoIdPopupActionConfig.setChildActionList(popupChildActionConfigList);

		actionConfigList.add(ifpInfoIdPopupActionConfig);
		parentIfpid.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addInfoParentIFPName(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabParentIFPNamelayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig parentIfpName = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabParentIFPName", true, "ifpInformationTabParentIFPNamelayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentIfpName.setAuthorizationIncluded(true);
		parentIfpName.setComponentName("Parent IFP Name");
		parentIfpName.setEnable(false);
		componentList.add(parentIfpName);

		GtnUIFrameworkTextBoxConfig textConfig = new GtnUIFrameworkTextBoxConfig();
		textConfig.setEnable(false);
		parentIfpName.setGtnTextBoxConfig(textConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig ifpInfoNamePopupActionConfig = new GtnUIFrameWorkActionConfig();
		ifpInfoNamePopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		ifpInfoNamePopupActionConfig.addActionParameter("parentIfpView");
		ifpInfoNamePopupActionConfig.addActionParameter("Parent IFP Name");
		ifpInfoNamePopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		ifpInfoNamePopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		/**
		 * To Reset Look Up
		 */
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<GtnUIFrameWorkActionConfig> popupChildActionConfigList = new ArrayList<>();
		List<Object> selectResetParams = new ArrayList<>();
		selectResetParams.add(Arrays.asList("parentIfpsearchResultTable", "parentifpId", "parentIfpNo", "parentIfpName",
				"parentIfpStatus", "parentIfpType"));
		selectResetParams.add(Arrays.asList(null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null));
		resetTableConfig.setActionParameterList(selectResetParams);
		popupChildActionConfigList.add(resetTableConfig);
		ifpInfoNamePopupActionConfig.setChildActionList(popupChildActionConfigList);
		actionConfigList.add(ifpInfoNamePopupActionConfig);
		parentIfpName.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addInfoCreatedBy(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabCreatedBylayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoIdConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabCreatedBy", true, "ifpInformationTabCreatedBylayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpInfoIdConfig.setAuthorizationIncluded(true);
		ifpInfoIdConfig.setComponentName("Created By");
		componentList.add(ifpInfoIdConfig);
	}

	private void addInfoCreatedDate(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationCreatedDatelayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoInformationIfpStartDate = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationCreatedDate", true, "ifpInformationCreatedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		ifpInfoInformationIfpStartDate.setAuthorizationIncluded(true);
		ifpInfoInformationIfpStartDate.setComponentName("Created Date");
		componentList.add(ifpInfoInformationIfpStartDate);
	}

	private void getIfpInfoCommonLayout(GtnUIFrameworkLayoutType horizontalLayout,
			GtnUIFrameworkComponentType layoutType, String componentId, String ifpInformationTabLayout,
			boolean addToParent, List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(horizontalLayout);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(layoutType);
		gtnLayout.setComponentId(componentId);
		gtnLayout.setParentComponentId(ifpInformationTabLayout);
		gtnLayout.setAddToParent(addToParent);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

	}

	private void addInfoModifiedBy(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationTabModifiedBylayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);

		GtnUIFrameworkComponentConfig ifpInfoIdConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationTabModifiedBy", true, "ifpInformationTabModifiedBylayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		ifpInfoIdConfig.setAuthorizationIncluded(true);
		ifpInfoIdConfig.setComponentName("Modified By");
		componentList.add(ifpInfoIdConfig);
	}

	private void addInfoModifiedDate(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpInfoCommonLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpInformationModifiedDatelayout", GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_LAYOUT, true,
				componentList);
		GtnUIFrameworkComponentConfig ifpInfoInformationIfpStartDate = configProvider.getUIFrameworkComponentConfig(
				"ifpInformationModifiedDate", true, "ifpInformationModifiedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		ifpInfoInformationIfpStartDate.setAuthorizationIncluded(true);
		ifpInfoInformationIfpStartDate.setComponentName("Modified Date");
		componentList.add(ifpInfoInformationIfpStartDate);
	}

}
