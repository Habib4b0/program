/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.priceschedule.config;

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
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSDesignationChangeAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkPSInformationTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addpriceScheduleInfoTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psInfoLayoutConfig = configProvider
				.getVerticalLayoutConfig("priceScheduleInfoTab", false, null);
		psInfoLayoutConfig.setTabComponent(true);
		psInfoLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psInfoLayoutConfig);

		GtnUIFrameworkComponentConfig priceScheduleMainCssConfig = configProvider
				.getCssLayoutConfig("priceScheduleMainCssLayout", true, "priceScheduleInfoTab");
		List<String> priceScheduleMainCssCStyleList = new ArrayList<>();
		priceScheduleMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		priceScheduleMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		priceScheduleMainCssConfig.setComponentStyle(priceScheduleMainCssCStyleList);
		componentList.add(priceScheduleMainCssConfig);

		priceScheduleInfoInformationLayout(componentList);
	}

	private void priceScheduleInfoInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleInfoInformationLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT, true, "priceScheduleMainCssLayout");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		priceScheduleInfoInformationLayout.setComponentStyle(styleList);
		componentList.add(priceScheduleInfoInformationLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addPriceScheduleId(componentList);
		addPriceScheduleNo(componentList);
		addPriceScheduleName(componentList);
		addPriceScheduleStatus(componentList);
		addPriceScheduleStartDate(componentList);
		addPriceScheduleEndDate(componentList);
		addPriceScheduleDesignation(componentList);
		addParentPriceScheduleID(componentList);
		addParentPriceScheduleName(componentList);
		addPriceScheduleType(componentList);
		addCreatedBy(componentList);
		addCreatedDate(componentList);
		addPriceScheduleCategory(componentList);
		addModifiedBy(componentList);
		addModifiedDate(componentList);
		addPriceScheduleTradeClass(componentList);
	}

	private void addPriceScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleIdLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleIdLayout);

		GtnUIFrameworkComponentConfig priceScheduleId = configProvider.getUIFrameworkComponentConfig("priceScheduleId1",
				true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleId.setAuthorizationIncluded(true);
		priceScheduleId.setComponentName("Price Schedule ID");
		priceScheduleId.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(priceScheduleId);

		GtnUIFrameworkValidationConfig priceScheduleIdValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		priceScheduleIdValidationConfig.setConditionList(conditions);
		priceScheduleId.setGtnUIFrameworkValidationConfig(priceScheduleIdValidationConfig);
	}

	private void addPriceScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleNoLayout);

		GtnUIFrameworkComponentConfig priceScheduleNo = configProvider.getUIFrameworkComponentConfig("priceScheduleNo1",
				true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleNo.setAuthorizationIncluded(true);
		priceScheduleNo.setComponentName("Price Schedule No");
		priceScheduleNo.addComponentStyle(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY);

		GtnUIFrameworkValidationConfig priceScheduleNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		priceScheduleNoValidationConfig.setConditionList(conditionsList);
		priceScheduleNo.setGtnUIFrameworkValidationConfig(priceScheduleNoValidationConfig);

		componentList.add(priceScheduleNo);
	}

	private void addPriceScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig priceScheduleNameLayoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleNameLayoutConfig);

		GtnUIFrameworkComponentConfig priceScheduleName = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleName1", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleName.setAuthorizationIncluded(true);
		priceScheduleName.setComponentName("Price Schedule Name");
		priceScheduleName.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		GtnUIFrameworkValidationConfig priceScheduleNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditionsConfig = new ArrayList<>();
		conditionsConfig.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		priceScheduleNameValidationConfig.setConditionList(conditionsConfig);
		priceScheduleName.setGtnUIFrameworkValidationConfig(priceScheduleNameValidationConfig);
		componentList.add(priceScheduleName);
	}

	private void addPriceScheduleStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleStatusLayoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleStatusLayoutConfig);

		GtnUIFrameworkComponentConfig priceScheduleStatus = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleStatus1", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		priceScheduleStatus.setAuthorizationIncluded(true);
		priceScheduleStatus.setComponentName("Price Schedule Status");
		priceScheduleStatus.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(priceScheduleStatus);

		GtnUIFrameworkComboBoxConfig priceScheduleStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceScheduleStatus.setGtnComboboxConfig(priceScheduleStatusConfig);

		GtnUIFrameworkValidationConfig priceScheduleStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleStatusValidationConfig.setConditionList(conditionsList);
		priceScheduleStatus.setGtnUIFrameworkValidationConfig(priceScheduleStatusValidationConfig);
	}

	private void addPriceScheduleStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleStartDateLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_START_DATE_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleStartDateLayout);

		GtnUIFrameworkComponentConfig priceScheduleStartDate = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleStartDate", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_START_DATE_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		priceScheduleStartDate.setAuthorizationIncluded(true);
		priceScheduleStartDate.setComponentName("Price Schedule Start Date");
		priceScheduleStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));

		GtnUIFrameworkValidationConfig priceScheduleStartDateValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleStartDateValidationConfig.setConditionList(conditions);
		priceScheduleStartDate.setGtnUIFrameworkValidationConfig(priceScheduleStartDateValidationConfig);

		componentList.add(priceScheduleStartDate);
	}

	private void addPriceScheduleEndDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig priceScheduleEndDateLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_END_DATE_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleEndDateLayout);

		GtnUIFrameworkComponentConfig priceScheduleEndDate = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleEndDate", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_END_DATE_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		priceScheduleEndDate.setAuthorizationIncluded(true);
		priceScheduleEndDate.setComponentName("Price Schedule End Date");

		GtnUIFrameworkValidationConfig priceScheduleEndDateValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleEndDateValidationConfig.setConditionList(conditions);
		priceScheduleEndDate.setGtnUIFrameworkValidationConfig(priceScheduleEndDateValidationConfig);

		componentList.add(priceScheduleEndDate);
	}

	private void addPriceScheduleDesignation(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleDesignationLayout = configProvider.getHorizontalLayoutConfig(
				"PriceScheduleDesignationlayout", true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleDesignationLayout);

		GtnUIFrameworkComponentConfig priceScheduleDesignation = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleDesignation", true, "PriceScheduleDesignationlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		priceScheduleDesignation.setAuthorizationIncluded(true);
		priceScheduleDesignation.setComponentName("Price Schedule Designation");

		GtnUIFrameWorkActionConfig priceScheduleDesignationChangeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		priceScheduleDesignationChangeAction.addActionParameter(GtnFrameworkPSDesignationChangeAction.class.getName());
		priceScheduleDesignation.addGtnUIFrameWorkActionConfig(priceScheduleDesignationChangeAction);
		componentList.add(priceScheduleDesignation);

		GtnUIFrameworkComboBoxConfig priceScheduleDesignationConfig = configProvider.getComboBoxConfig("PS_DESIGNATION",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceScheduleDesignation.setGtnComboboxConfig(priceScheduleDesignationConfig);

		GtnUIFrameworkValidationConfig priceScheduleDesignationValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleDesignationValidationConfig.setConditionList(conditions);
		priceScheduleDesignation.setGtnUIFrameworkValidationConfig(priceScheduleDesignationValidationConfig);
	}

	private void addParentPriceScheduleID(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPriceScheduleIDLayout = configProvider.getHorizontalLayoutConfig(
				"parentPriceScheduleIDLayout", true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(parentPriceScheduleIDLayout);

		GtnUIFrameworkComponentConfig parentPriceScheduleID = configProvider.getUIFrameworkComponentConfig(
				"parentPriceScheduleID", true, "parentPriceScheduleIDLayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentPriceScheduleID.setAuthorizationIncluded(true);
		parentPriceScheduleID.setComponentName("Parent Price Schedule ID");
		parentPriceScheduleID.setComponentStyle(Arrays.asList("searchicon"));
		GtnUIFrameworkTextBoxConfig parentPriceScheduleIDConfig = configProvider.getTextBoxConfig(false, false, true);
		parentPriceScheduleID.setGtnTextBoxConfig(parentPriceScheduleIDConfig);
		componentList.add(parentPriceScheduleID);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("parentPricePopUpSearchView");
		popupActionParam.add("Parent Price Schedule ID");
		popupActionParam.add("70%");
		popupActionParam.add("70%");
		popupActionConfig.setActionParameterList(popupActionParam);

		parentPriceScheduleID.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addParentPriceScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPriceScheduleNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(parentPriceScheduleNameLayout);

		GtnUIFrameworkComponentConfig parentPriceScheduleName = configProvider.getUIFrameworkComponentConfig(
				"parentPriceScheduleName", true, GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		parentPriceScheduleName.setAuthorizationIncluded(true);
		parentPriceScheduleName.setComponentName("Parent Price Schedule Name");
		GtnUIFrameworkTextBoxConfig parentPriceScheduleNameConfig = configProvider.getTextBoxConfig(false, false, true);
		parentPriceScheduleName.setGtnTextBoxConfig(parentPriceScheduleNameConfig);
		GtnUIFrameworkValidationConfig parentPriceScheduleNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		parentPriceScheduleNameValidationConfig.setConditionList(conditions);
		parentPriceScheduleName.setGtnUIFrameworkValidationConfig(parentPriceScheduleNameValidationConfig);

		componentList.add(parentPriceScheduleName);

	}

	private void addPriceScheduleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleTypeLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleTypeLayout);

		GtnUIFrameworkComponentConfig priceScheduleType = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleType1", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		priceScheduleType.setAuthorizationIncluded(true);
		priceScheduleType.setComponentName("Price Schedule Type");
		componentList.add(priceScheduleType);

		GtnUIFrameworkComboBoxConfig priceScheduleTypeConfig = configProvider.getComboBoxConfig("PS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceScheduleType.setGtnComboboxConfig(priceScheduleTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		priceScheduleType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig createdByLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.CREATED_BY_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(createdByLayout);

		GtnUIFrameworkComponentConfig createdBy = configProvider.getUIFrameworkComponentConfig("createdBy", true,
				GtnFrameworkCommonConstants.CREATED_BY_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		createdBy.setAuthorizationIncluded(true);
		createdBy.setComponentName("Created By");

		GtnUIFrameworkTextBoxConfig createdByConfig = configProvider.getTextBoxConfig(true, false, true);
		createdBy.setGtnTextBoxConfig(createdByConfig);

		GtnUIFrameworkValidationConfig createdByValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		createdByValidationConfig.setConditionList(conditions);
		createdBy.setGtnUIFrameworkValidationConfig(createdByValidationConfig);

		componentList.add(createdBy);

	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig createdDateLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.CREATED_DATE_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(createdDateLayout);

		GtnUIFrameworkComponentConfig createdDateConfig = configProvider.getUIFrameworkComponentConfig("createdDate",
				true, GtnFrameworkCommonConstants.CREATED_DATE_LAYOUT, GtnUIFrameworkComponentType.DATEFIELD);
		createdDateConfig.setAuthorizationIncluded(true);
		createdDateConfig.setComponentName("Created Date");

		GtnUIFrameworkValidationConfig createdDateValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		createdDateValidationConfig.setConditionList(conditions);
		createdDateConfig.setGtnUIFrameworkValidationConfig(createdDateValidationConfig);

		componentList.add(createdDateConfig);

	}

	private void addPriceScheduleCategory(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig priceScheduleCategoryLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_CATEGORY_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(priceScheduleCategoryLayout);

		GtnUIFrameworkComponentConfig priceScheduleCategory = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleCategory", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_CATEGORY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		priceScheduleCategory.setAuthorizationIncluded(true);
		priceScheduleCategory.setComponentName("Price Schedule Category");
		componentList.add(priceScheduleCategory);

		GtnUIFrameworkComboBoxConfig priceScheduleCategoryConfig = configProvider.getComboBoxConfig("PS_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceScheduleCategory.setGtnComboboxConfig(priceScheduleCategoryConfig);

		GtnUIFrameworkValidationConfig priceScheduleCategoryValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleCategoryValidationConfig.setConditionList(conditions);
		priceScheduleCategory.setGtnUIFrameworkValidationConfig(priceScheduleCategoryValidationConfig);
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig modifiedByLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.MODIFIED_BY_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(modifiedByLayout);

		GtnUIFrameworkComponentConfig modifiedByConfig = configProvider.getUIFrameworkComponentConfig("modifiedBy",
				true, GtnFrameworkCommonConstants.MODIFIED_BY_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		modifiedByConfig.setAuthorizationIncluded(true);
		modifiedByConfig.setComponentName("Modified By");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		modifiedByConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameworkValidationConfig modifiedByValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		modifiedByValidationConfig.setConditionList(conditionsList);
		modifiedByConfig.setGtnUIFrameworkValidationConfig(modifiedByValidationConfig);

		componentList.add(modifiedByConfig);

	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig modifiedDateLayoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.MODIFIED_DATE_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(modifiedDateLayoutConfig);

		GtnUIFrameworkComponentConfig modifiedDateConfig = configProvider.getUIFrameworkComponentConfig("modifiedDate",
				true, GtnFrameworkCommonConstants.MODIFIED_DATE_LAYOUT, GtnUIFrameworkComponentType.DATEFIELD);
		modifiedDateConfig.setAuthorizationIncluded(true);
		modifiedDateConfig.setComponentName("Modified Date");

		GtnUIFrameworkValidationConfig modifiedDateValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditionsList = new ArrayList<>();
		conditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		modifiedDateValidationConfig.setConditionList(conditionsList);
		modifiedDateConfig.setGtnUIFrameworkValidationConfig(modifiedDateValidationConfig);

		componentList.add(modifiedDateConfig);

	}

	private void addPriceScheduleTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psTradeClassLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TRADE_CLASS_LAYOUT, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(psTradeClassLayout);

		GtnUIFrameworkComponentConfig psTradeClass = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleTradeClass", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_TRADE_CLASS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		psTradeClass.setAuthorizationIncluded(true);
		psTradeClass.setComponentName("Price Schedule Trade Class");
		componentList.add(psTradeClass);

		GtnUIFrameworkComboBoxConfig psTradeClassConfig = configProvider.getComboBoxConfig("PS_TRADE_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		psTradeClass.setGtnComboboxConfig(psTradeClassConfig);

		GtnUIFrameworkValidationConfig psTradeClassValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		psTradeClassValidationConfig.setConditionList(conditions);
		psTradeClass.setGtnUIFrameworkValidationConfig(psTradeClassValidationConfig);
	}

}
