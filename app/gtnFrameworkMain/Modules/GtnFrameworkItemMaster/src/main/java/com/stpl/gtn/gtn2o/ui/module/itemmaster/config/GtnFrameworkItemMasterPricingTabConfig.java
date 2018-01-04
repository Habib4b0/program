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
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterFocusListnerAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingAttachAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingFieldFactoryUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterItemPriceBlurAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;

public class GtnFrameworkItemMasterPricingTabConfig {

	public void addPricingTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

		GtnUIFrameworkComponentConfig pricingMainLayoutConfig = componentConfig.getRootLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_TAB, GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, true);
		pricingMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(pricingMainLayoutConfig);

		itemMasterPricingPanel(componentList, componentConfig);
		itemMasterPricingResultPanel(componentList, componentConfig);
		itemPricingRemoveButtonLayout(componentList, componentConfig);
		addExcelButtonComponent(componentList, componentConfig);

	}

	private void itemMasterPricingPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingPanel = componentConfig.getPanelConfig("itemMasterPricingPanel", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_TAB);
		pricingPanel.setComponentName("Price Information");
		pricingPanel.setAuthorizationIncluded(true);
		componentList.add(pricingPanel);

		GtnUIFrameworkComponentConfig layoutConfig = componentConfig
				.getVerticalLayoutConfig("itemMasterPricingPanelLayout", true, pricingPanel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		itemMasterPricingLayout(componentList, componentConfig);
		itemPricingAttachButtonLayout(componentList, componentConfig);
	}

	private void itemMasterPricingLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingPanelLayoutConfig = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT, true, "itemMasterPricingPanelLayout",
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(pricingPanelLayoutConfig);

		itemPricingFields(componentList, componentConfig);
	}

	private void itemPricingFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addItemPricingQualifierName(componentList, componentConfig);
		addItemPrice(componentList, componentConfig);
		addItemPricingStatus(componentList, componentConfig);
		addItemUom(componentList, componentConfig);
		addItemPricingStartDate(componentList, componentConfig);
		addItemPricingEndDate(componentList, componentConfig);
		addEntityCodeNo(componentList, componentConfig);
		addEntityCodeName(componentList, componentConfig);
	}

	private void addItemPricingQualifierName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingQualifierNameLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingQualifierNamelayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(pricingQualifierNameLayout);

		GtnUIFrameworkComponentConfig pricingQualifierName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER_NAME, true,
				pricingQualifierNameLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		pricingQualifierName.setAuthorizationIncluded(true);
		pricingQualifierName.setComponentName("Pricing Qualifier Name");
		pricingQualifierName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(pricingQualifierName);

		GtnUIFrameworkComboBoxConfig itemQualifierNameConfig = componentConfig.getComboBoxConfig("ItemPricingQualifier",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemQualifierNameConfig.setItemValues(Arrays.asList("Edit List"));
		pricingQualifierName.setGtnComboboxConfig(itemQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig editActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_ACTION);
		editActionConfig.addActionParameter("pricingEditList");
		editActionConfig.addActionParameter("Pricing Identifier Setup Pop-up");
		editActionConfig.addActionParameter("pricingEditattachResultTable");
		actionConfigList.add(editActionConfig);
		pricingQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		pricingQualifierName.setGtnUIFrameworkValidationConfig(valConfig);

	}

	private void addItemPrice(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemPriceLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingItemPricelayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(itemPriceLayout);

		GtnUIFrameworkComponentConfig itemPricing = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkItemMasterStringContants.ITEM_PRICING_ITEM_PRICE, true, "itemPricingItemPricelayout", GtnUIFrameworkComponentType.TEXTBOX);
		itemPricing.setAuthorizationIncluded(true);
		itemPricing.setComponentName("Item Price");
		itemPricing.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig itemPricingConfig = componentConfig.getTextBoxConfig(true, true, true);
		itemPricing.setGtnTextBoxConfig(itemPricingConfig);

		componentList.add(itemPricing);
                
                List<GtnUIFrameWorkActionConfig> actionBlurConfigBaseline = new ArrayList<>();
		GtnUIFrameWorkActionConfig blurActionForAMP = new GtnUIFrameWorkActionConfig();
		blurActionForAMP.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		blurActionForAMP.addActionParameter(GtnFrameworkItemMasterItemPriceBlurAction.class.getName());
		blurActionForAMP.addActionParameter(Arrays.asList(GtnFrameworkItemMasterStringContants.ITEM_PRICING_ITEM_PRICE));
		actionBlurConfigBaseline.add(blurActionForAMP);
		itemPricingConfig.setBlurActionConfigList(actionBlurConfigBaseline);

		GtnUIFrameworkValidationConfig itemPricingValConfig = componentConfig.getValidationConfig(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
                        "Item Price should be numeric", GtnFrameworkRegexStringConstants.NUMERIC_WITH_SIX_DECIMAL_PRECISION);

		itemPricing.setGtnUIFrameworkValidationConfig(itemPricingValConfig);

	}

	private void addItemPricingStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingStatusLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingIdentifierStatuslayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(pricingStatusLayout);

		GtnUIFrameworkComponentConfig pricingStatus = componentConfig.getUIFrameworkComponentConfig(
				"itemPricingIdentifierStatus", true, pricingStatusLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		pricingStatus.setAuthorizationIncluded(true);
		pricingStatus.setComponentName(GtnFrameworkCommonConstants.PRICING_STATUS);
		pricingStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(pricingStatus);

		GtnUIFrameworkComboBoxConfig pricingStatusConfig = componentConfig.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		pricingStatus.setGtnComboboxConfig(pricingStatusConfig);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		pricingStatus.setGtnUIFrameworkValidationConfig(valConfig);

	}

	private void addItemUom(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingItemUOMLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingItemUOMlayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(pricingItemUOMLayout);

		GtnUIFrameworkComponentConfig itemUOM = componentConfig.getUIFrameworkComponentConfig("itemPricingItemUOM",
				true, pricingItemUOMLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		itemUOM.setAuthorizationIncluded(true);
		itemUOM.setComponentName(GtnFrameworkCommonConstants.ITEM_UOM);
		itemUOM.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemUOM);

		GtnUIFrameworkComboBoxConfig itemUOMConfig = componentConfig.getComboBoxConfig("UOM",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemUOM.setGtnComboboxConfig(itemUOMConfig);

		GtnUIFrameworkValidationConfig itemUomValConfig = new GtnUIFrameworkValidationConfig();
		itemUomValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		itemUOM.setGtnUIFrameworkValidationConfig(itemUomValConfig);

	}

	private void addItemPricingStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingStartDatelayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(pricingStartDateLayout);

		GtnUIFrameworkComponentConfig pricingStartDate = componentConfig.getUIFrameworkComponentConfig(
				"itemPricingStartDate", true, pricingStartDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		pricingStartDate.setAuthorizationIncluded(true);
		pricingStartDate.setComponentName("Start Date");
		pricingStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(pricingStartDate);

		GtnUIFrameworkDateFieldConfig pricingStartDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		pricingStartDateFieldConfig.setImmediate(true);
		pricingStartDate.setGtnDateFieldConfig(pricingStartDateFieldConfig);

		GtnUIFrameworkValidationConfig pricingStartDateValConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "Date format not recognized",
				GtnFrameworkRegexStringConstants.DATE_FORMAT);
		pricingStartDate.setGtnUIFrameworkValidationConfig(pricingStartDateValConfig);

	}

	private void addItemPricingEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingEndDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingEndDatelayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(pricingEndDateLayout);

		GtnUIFrameworkComponentConfig pricingEndDate = componentConfig.getUIFrameworkComponentConfig(
				"itemPricingEndDate", true, pricingEndDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		pricingEndDate.setAuthorizationIncluded(true);
		pricingEndDate.setComponentName("End Date");

		GtnUIFrameworkDateFieldConfig pricingEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		pricingEndDateFieldConfig.setImmediate(true);
		pricingEndDate.setGtnDateFieldConfig(pricingEndDateFieldConfig);

		GtnUIFrameworkValidationConfig pricingEndDateValConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "Date format not recognized",
				GtnFrameworkRegexStringConstants.DATE_FORMAT);
		pricingEndDate.setGtnUIFrameworkValidationConfig(pricingEndDateValConfig);
		componentList.add(pricingEndDate);

	}

	private void addEntityCodeNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig entityCodeNoLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingEntityCodeNolayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(entityCodeNoLayout);

		GtnUIFrameworkComponentConfig entityCodeNo = componentConfig.getUIFrameworkComponentConfig(
				"itemPricingEntityCodeNo", true, "itemPricingEntityCodeNolayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		entityCodeNo.setAuthorizationIncluded(true);
		entityCodeNo.setComponentName("Entity Code");

		GtnUIFrameworkTextBoxConfig entityCodeNoTextboxConfig = componentConfig.getTextBoxConfig(true, true, true);
		entityCodeNoTextboxConfig.setReadOnly(true);
		entityCodeNo.setGtnTextBoxConfig(entityCodeNoTextboxConfig);

		componentList.add(entityCodeNo);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("itemPricingparentCompanyView");
		popupActionConfig.addActionParameter("Parent Company No");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_100);
		popupActionConfig.addActionParameter(null);
		actionConfigList.add(popupActionConfig);
		entityCodeNo.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEntityCodeName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig entityCodeNameLayout = componentConfig.getHorizontalLayoutConfig(
				"itemPricingEntityCodeNamelayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_LAYOUT);
		componentList.add(entityCodeNameLayout);

		GtnUIFrameworkComponentConfig entityCodeName = componentConfig.getUIFrameworkComponentConfig(
				"itemPricingEntityCodeName", true, "itemPricingEntityCodeNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		entityCodeName.setAuthorizationIncluded(true);
		entityCodeName.setComponentName("Entity Code Name");

		GtnUIFrameworkTextBoxConfig entityCodeNameConfig = componentConfig.getTextBoxConfig(false, true, true);
		entityCodeName.setGtnTextBoxConfig(entityCodeNameConfig);
		componentList.add(entityCodeName);

	}

	private void itemPricingAttachButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig attachButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingAttachButtonLayout", true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_TAB);
		attachButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(attachButtonLayout);

		itemPricingAttachButton(componentList, componentConfig);
	}

	private void itemPricingAttachButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig attachButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"pricingAttachButton", true, "pricingAttachButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Attach");
		attachButtonConfig.setAuthorizationIncluded(true);
		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_PRICING_VALIDATION);
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig attachAction = new GtnUIFrameWorkActionConfig();
		attachAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		attachAction.addActionParameter(GtnFrameworkItemMasterPricingAttachAction.class.getName());
		attachAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER_NAME,
				GtnFrameworkItemMasterStringContants.ITEM_PRICING_ITEM_PRICE, "itemPricingIdentifierStatus", "itemPricingItemUOM", "itemPricingStartDate",
				"itemPricingEndDate", "itemPricingEntityCodeNo", "itemPricingEntityCodeName"));
		actionConfigList.add(attachAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void itemMasterPricingResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig panel = componentConfig.getPanelConfig("itemMasterPricingResultPanel", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_TAB);
		panel.setComponentName("Results");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);

		itemMasterPricingResultLayout(componentList, componentConfig);
	}

	private void itemMasterPricingResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingResultLayout = componentConfig
				.getHorizontalLayoutConfig("itemMasterPricingResultLayout", true, "itemMasterPricingResultPanel");
		pricingResultLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(pricingResultLayout);

		pricingResultDataTable(componentList, componentConfig);
	}

	private void pricingResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig attachResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE, true,
				"itemMasterPricingResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		attachResultConfig.setAuthorizationIncluded(true);
		attachResultConfig.setComponentName("Results");
		attachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		attachResultConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setTableColumnDataType(
				new Class[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_INTEGER, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
						GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		attachResults.setTableVisibleHeader(new String[] { "Pricing Qualifier Name", "Item Price",
				GtnFrameworkCommonConstants.PRICING_STATUS, GtnFrameworkCommonConstants.ITEM_UOM, "Start Date",
				"End Date", "Entity Code", "Source", "Modified By", "Modified Date", "Created By", "Created Date",
				GtnFrameworkCommonConstants.PRICING_STATUS, GtnFrameworkCommonConstants.ITEM_UOM });
		attachResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PRICING_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.ITEM_PRICE, GtnFrameworkCommonConstants.PRICING_STATUS_ID,
				GtnFrameworkCommonConstants.ITEM_UOM_PARAM, GtnFrameworkCommonConstants.START_DATE,
				GtnFrameworkCommonConstants.END_DATE, GtnFrameworkCommonConstants.ENTITY_CODE, "source", "modifiedBy",
				"modifiedDate", "createdBy", "createdDate", "pricingStatusDes", "itemUOMDes" });
		attachResults.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));
		attachResults.setExtraColumn(new Object[] { "itemQualifierSid", "itemPricingSid", "isFocused" });
		attachResults.setExtraColumnDataType(new Class[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_INTEGER, Boolean.class });
		attachResults.setCountUrl(GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
				+ GtnWsItemMasterContants.GTN_WS_PRICING_TABLE_LOAD_SERVICE);
		attachResults.setResultSetUrl(GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
				+ GtnWsItemMasterContants.GTN_WS_PRICING_TABLE_LOAD_SERVICE);
		attachResults.setEditable(true);
		attachResults.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.PRICING_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.ITEM_PRICE, GtnFrameworkCommonConstants.PRICING_STATUS_ID,
				GtnFrameworkCommonConstants.ITEM_UOM_PARAM, GtnFrameworkCommonConstants.START_DATE,
				GtnFrameworkCommonConstants.END_DATE, GtnFrameworkCommonConstants.ENTITY_CODE, "source", "modifiedBy",
				"modifiedDate", "createdBy", "createdDate"));
		attachResults.setEditableField(
				createTableFieldFactoryComponents(attachResults.getEditableColumnList(), componentConfig));
	}

	private void itemPricingRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig layoutConfig = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICING_REMOVE_BUTTON_LAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_MASTER_PRICING_TAB);
		componentList.add(layoutConfig);
		itemPricingRemoveButton(componentList, componentConfig);
	}

	private void itemPricingRemoveButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig attachButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"pricingRemoveButton", true, GtnFrameworkCommonConstants.PRICING_REMOVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName("Remove");
		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		Object removeMsg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_REMOVE;
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		alertActionConfig.setActionParameterList(
				Arrays.asList(GtnFrameworkItemMasterClassContants.ITEM_MASTER_PRICING_RESULTS_TABLE_VALIDATION_ACTION,
						GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE, removeMsg));
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG_REMOVE_HEADER);
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG_PRICE_REMOVE);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.REMOVE_ACTION);
		selectAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE);
		onSucessActionConfigList.add(selectAction);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(confirmationActionConfig);
		
		GtnUIFrameWorkActionConfig customActionConfig = new GtnUIFrameWorkActionConfig();
		customActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionConfig.addActionParameter(GtnFrameworkItemMasterPricingDeleteAction.class.getName());
		customActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE);
		actionConfigList.add(customActionConfig);
		
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	/**
	 * 
	 * @param componentList
	 * @param componentConfig
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig excelButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"gtnPricingExcelButton", true, GtnFrameworkCommonConstants.PRICING_REMOVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfig.getExcelBtnconfig("Pricing",
				true, GtnFrameworkCommonConstants.ITEM_MASTER_PRICINGATTACH_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setHelperTableMapedPropertyIdList(Arrays
				.asList(GtnFrameworkCommonConstants.PRICING_STATUS_ID, GtnFrameworkCommonConstants.ITEM_UOM_PARAM));
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
			GtnFrameworkComponentConfigProvider componentConfig) {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkCommonConstants.PRICING_STATUS_ID,
				GtnFrameworkCommonConstants.ITEM_UOM_PARAM };
		String[] listNameArray = { GtnFrameworkCommonConstants.STATUS, "UOM" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			GtnUIFrameworkComboBoxConfig comboBoxConfig = componentConfig.getComboBoxConfig(listNameArray[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			comboBoxConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.setGtnComboboxConfig(comboBoxConfig);
			customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds,
			GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = getComponentType(propertyId);
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			if (!GtnFrameworkItemMasterStringContants.getItemMasterItemPricingEditablefieldList()
					.contains(propertyId)) {
				fieldConfig.setEnable(false);
			} else {
				GtnUIFrameWorkActionConfig valueChangeAction = componentConfig.getUIFrameworkActionConfig(
						GtnUIFrameworkActionType.CUSTOM_ACTION,
						GtnFrameworkItemMasterPricingFieldFactoryUpdateAction.class.getName());

				GtnUIFrameWorkActionConfig focusAction = componentConfig.getUIFrameworkActionConfig(
						GtnUIFrameworkActionType.CUSTOM_ACTION,
						GtnFrameworkItemMasterFocusListnerAction.class.getName(), "isFocused");
                                
				fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(valueChangeAction));
				fieldConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));

			}
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig companyFamilyPlanStatusConfig = new GtnUIFrameworkComboBoxConfig();
				companyFamilyPlanStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				if (GtnFrameworkCommonConstants.ITEM_UOM_PARAM.equals(propertyId)) {
					companyFamilyPlanStatusConfig.setComboBoxType("UOM");
				} else if (GtnFrameworkCommonConstants.PRICING_STATUS_ID.equals(propertyId)) {
					companyFamilyPlanStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.STATUS);

				}
				fieldConfig.setGtnComboboxConfig(companyFamilyPlanStatusConfig);
			}
			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	private GtnUIFrameworkComponentType getComponentType(String propertyId) {
		GtnUIFrameworkComponentType gtnUIFrameworkComponentType;

		if (GtnFrameworkItemMasterStringContants.getItemMasterItemPricingTextfieldList().contains(propertyId)) {
			gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
		} else if (GtnFrameworkItemMasterStringContants.getItemMasterItemPricingDatefieldPropertiesList()
				.contains(propertyId)) {
			gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
		} else if ("checkRecordId".equals(propertyId)) {
			gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.CHECKBOX;
		} else {
			gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
		}

		return gtnUIFrameworkComponentType;

	}

}
