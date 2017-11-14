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
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkItemMasterIdentifierTabConfig {

	public void addIdentifierTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

		GtnUIFrameworkComponentConfig itemIdentifierMainLayoutConfig = componentConfig.getRootLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_MASTERIDENTIFIER_TAB, GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, true);
		itemIdentifierMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(itemIdentifierMainLayoutConfig);
		itemMasterIdentifierInformationPanel(componentList, componentConfig);
		itemMasterIdentifierResultPanel(componentList, componentConfig);
		identifierInformationRemoveButtonLayout(componentList, componentConfig);

	}

	private void itemMasterIdentifierInformationPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdentifierMainPanel = componentConfig.getPanelConfig(
				"itemMasterIdentifierInformationPanel", true, GtnFrameworkCommonConstants.ITEM_MASTERIDENTIFIER_TAB);
		itemIdentifierMainPanel.setComponentName("Identifier Information");
		componentList.add(itemIdentifierMainPanel);

		GtnUIFrameworkComponentConfig layoutConfig = componentConfig.getVerticalLayoutConfig(
				"itemMasterIdentifierInformationPanelLayout", true, itemIdentifierMainPanel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		itemMasterIdentifierInformationLayout(componentList, componentConfig);
		identifierInformationAttachButtonLayout(componentList, componentConfig);
	}

	private void itemMasterIdentifierInformationLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig imInformationConfig = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT, true,
				"itemMasterIdentifierInformationPanelLayout", GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(imInformationConfig);
		identifierInformationFields(componentList, componentConfig);
	}

	private void identifierInformationFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addItemQualifierName(componentList, componentConfig);
		addItemIdentifier(componentList, componentConfig);
		addItemIdentifierStatus(componentList, componentConfig);
		addItemIdentifierStartDate(componentList, componentConfig);
		addItemIdentifierEndDate(componentList, componentConfig);
		addEntityCodeNo(componentList, componentConfig);
		addEntityCodeName(componentList, componentConfig);
	}

	private void addItemQualifierName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemQualifierNameLayout = componentConfig.getHorizontalLayoutConfig(
				"identifierItemQualifierNamelayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(itemQualifierNameLayout);

		GtnUIFrameworkComponentConfig itemQualifierName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IDENTIFIER_ITEM_QUALIFIER_NAME, true, "identifierItemQualifierNamelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		itemQualifierName.setAuthorizationIncluded(true);
		itemQualifierName.setComponentName("Item Qualifier Name");
		itemQualifierName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemQualifierName);

		GtnUIFrameworkComboBoxConfig itemQualifierNameConfig = componentConfig.getComboBoxConfig("ItemQualifier",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemQualifierName.setGtnComboboxConfig(itemQualifierNameConfig);
		itemQualifierNameConfig.setItemValues(Arrays.asList("Edit List"));

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemQualifierName.setGtnUIFrameworkValidationConfig(valConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig editActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_ACTION);
		editActionConfig.addActionParameter("identifierEditList");
		editActionConfig.addActionParameter("Identifier Setup Pop-up");
		editActionConfig.addActionParameter("editattachResultTable");
		actionConfigList.add(editActionConfig);
		itemQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addItemIdentifier(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemIdentifierLayout = componentConfig.getHorizontalLayoutConfig(
				"identifierInformationItemIdentifierlayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(itemIdentifierLayout);

		GtnUIFrameworkComponentConfig itemIdentifier = componentConfig.getUIFrameworkComponentConfig(
				"identifierInformationItemIdentifier", true, itemIdentifierLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemIdentifier.setAuthorizationIncluded(true);
		itemIdentifier.setComponentName("Item Identifier");

		GtnUIFrameworkTextBoxConfig itemIdentifierConfig = componentConfig.getTextBoxConfig(true, true, true);
		itemIdentifier.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		itemIdentifierConfig.setMaximumLength(50);
		itemIdentifier.setGtnTextBoxConfig(itemIdentifierConfig);
		componentList.add(itemIdentifier);

		GtnUIFrameworkValidationConfig itemIdentifierValConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				"Item Identifier can only be Alphanumeric", GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		itemIdentifier.setGtnUIFrameworkValidationConfig(itemIdentifierValConfig);

	}

	private void addItemIdentifierStartDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig identifierStartDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemIdentifierStartDatelayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(identifierStartDateLayout);

		GtnUIFrameworkComponentConfig itemIdentifier = componentConfig.getUIFrameworkComponentConfig(
				"itemIdentifierStartDate", true, identifierStartDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		itemIdentifier.setAuthorizationIncluded(true);
		itemIdentifier.setComponentName("Identifier Start Date");
		itemIdentifier.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(itemIdentifier);

		GtnUIFrameworkDateFieldConfig itemIdentifierFieldConfig = new GtnUIFrameworkDateFieldConfig();
		itemIdentifierFieldConfig.setImmediate(true);
		itemIdentifier.setGtnDateFieldConfig(itemIdentifierFieldConfig);

		GtnUIFrameworkValidationConfig itemIdentifierValConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "Date format not recognized",
				GtnFrameworkRegexStringConstants.DATE_FORMAT);
		itemIdentifier.setGtnUIFrameworkValidationConfig(itemIdentifierValConfig);

	}

	private void addItemIdentifierEndDate(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig endDateLayout = componentConfig.getHorizontalLayoutConfig(
				"itemIdentifierEndDatelayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		endDateLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(endDateLayout);

		GtnUIFrameworkComponentConfig endDate = componentConfig.getUIFrameworkComponentConfig("itemIdentifierEndDate",
				true, endDateLayout.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		endDate.setAuthorizationIncluded(true);
		endDate.setComponentName("Identifier End Date");

		GtnUIFrameworkDateFieldConfig endDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		endDateFieldConfig.setImmediate(true);
		endDate.setGtnDateFieldConfig(endDateFieldConfig);

		GtnUIFrameworkValidationConfig endDateValConfig = componentConfig.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "Date format not recognized",
				GtnFrameworkRegexStringConstants.DATE_FORMAT);
		endDate.setGtnUIFrameworkValidationConfig(endDateValConfig);

		componentList.add(endDate);

	}

	private void addItemIdentifierStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig identifierStatusLayout = componentConfig.getHorizontalLayoutConfig(
				"identifierInformationIdentifierStatuslayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(identifierStatusLayout);

		GtnUIFrameworkComponentConfig itemStatus = componentConfig.getUIFrameworkComponentConfig(
				"identifierInformationIdentifierStatus", true, identifierStatusLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemStatus.setAuthorizationIncluded(true);
		itemStatus.setComponentName(GtnFrameworkCommonConstants.IDENTIFIER_STATUS_ITEM);
		itemStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(itemStatus);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = componentConfig.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemStatus.setGtnComboboxConfig(itemStatusConfig);

		GtnUIFrameworkValidationConfig itemStatusValConfig = new GtnUIFrameworkValidationConfig();
		itemStatusValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		itemStatus.setGtnUIFrameworkValidationConfig(itemStatusValConfig);
	}

	private void addEntityCodeNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig entityCodeNoLayout = componentConfig.getHorizontalLayoutConfig(
				"itemIdentifierEntityCodeNolayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(entityCodeNoLayout);

		GtnUIFrameworkComponentConfig entityCodeNo = componentConfig.getUIFrameworkComponentConfig(
				"itemIdentifierEntityCodeNo", true, entityCodeNoLayout.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		entityCodeNo.setAuthorizationIncluded(true);
		entityCodeNo.setComponentName("Entity Code No");

		GtnUIFrameworkTextBoxConfig entityCodeNoConfig = componentConfig.getTextBoxConfig(true, true, true);
		entityCodeNoConfig.setReadOnly(true);
		entityCodeNo.setGtnTextBoxConfig(entityCodeNoConfig);

		componentList.add(entityCodeNo);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("itemIdentifierparentCompanyView");
		popupActionConfig.addActionParameter("Parent Company No");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_100);
		popupActionConfig.addActionParameter(null);
		actionConfigList.add(popupActionConfig);
		entityCodeNo.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEntityCodeName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig entityCodeNameLayout = componentConfig.getHorizontalLayoutConfig(
				"itemIdentifierEntityCodeNamelayout", true,
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(entityCodeNameLayout);

		GtnUIFrameworkComponentConfig entityCodeName = componentConfig.getUIFrameworkComponentConfig(
				"itemIdentifierEntityCodeName", true, entityCodeNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		entityCodeName.setAuthorizationIncluded(true);
		entityCodeName.setComponentName("Entity Code Name");

		GtnUIFrameworkTextBoxConfig entityCodeNameConfig = componentConfig.getTextBoxConfig(false, true, true);
		entityCodeName.setGtnTextBoxConfig(entityCodeNameConfig);

		componentList.add(entityCodeName);

	}

	private void identifierInformationAttachButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig attachButtonConfig = componentConfig.getHorizontalLayoutConfig(
				"identifierAttachButtonLayout", true, GtnFrameworkCommonConstants.ITEM_MASTERIDENTIFIER_TAB);
		componentList.add(attachButtonConfig);
		identifierInformationAttachButton(componentList, componentConfig);
	}

	private void identifierInformationAttachButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig attachButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"identifierAttachButton", true, "identifierAttachButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Attach");
		attachButtonConfig.setAuthorizationIncluded(true);
		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_IDENTIFIER_VALIDATION);
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig selectAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_ATTACH_ACTION);
		selectAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		selectAction.addActionParameter(null);
		selectAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.ITEM_IRT_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.ITEM_IDENTIFIER, GtnFrameworkCommonConstants.START_DATE,
				GtnFrameworkCommonConstants.END_DATE, GtnFrameworkCommonConstants.IDENTIFIER_STATUS,
				GtnFrameworkCommonConstants.ENTITY_CODE, GtnFrameworkCommonConstants.ENTITY_CODE_NAME));
		selectAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.IDENTIFIER_ITEM_QUALIFIER_NAME,
				"identifierInformationItemIdentifier", "itemIdentifierStartDate", "itemIdentifierEndDate",
				"identifierInformationIdentifierStatus", "itemIdentifierEntityCodeNo", "itemIdentifierEntityCodeName"));
		selectAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING));
		selectAction.addActionParameter(GtnFrameworkCommonConstants.IDENTIFIER_ITEM_QUALIFIER_NAME);
		selectAction.addActionParameter(Arrays.asList("identifierStatusDes"));
		actionConfigList.add(selectAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void itemMasterIdentifierResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig panel = componentConfig.getPanelConfig("itemMasterIdentifierResultPanel", true,
				GtnFrameworkCommonConstants.ITEM_MASTERIDENTIFIER_TAB);
		panel.setComponentName("Results");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		itemMasterIdentifierResultLayout(componentList, componentConfig);
	}

	private void itemMasterIdentifierResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig layoutConfig = componentConfig
				.getHorizontalLayoutConfig("itemMasterIdentifierResultLayout", true, "itemMasterIdentifierResultPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		identifierResultDataTable(componentList, componentConfig);
	}

	private void identifierResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig imAttachResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE, true,
				"itemMasterIdentifierResultLayout", GtnUIFrameworkComponentType.DATATABLE);
		imAttachResultConfig.setAuthorizationIncluded(true);
		imAttachResultConfig.setComponentName("Results");
		imAttachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		imAttachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(imAttachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		imAttachResultConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);

		attachResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		attachResults.setTableVisibleHeader(new String[] { "Item Qualifier Name", "Item Identifier",
				GtnFrameworkCommonConstants.IDENTIFIER_STATUS_ITEM, "Start Date", "End Date", "Entity Code No",
				"Entity Code Name", "Modified By", "Modified Date", "Created By", "Created Date",
				GtnFrameworkCommonConstants.IDENTIFIER_STATUS_ITEM });
		attachResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.ITEM_IRT_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.ITEM_IDENTIFIER, GtnFrameworkCommonConstants.IDENTIFIER_STATUS,
				GtnFrameworkCommonConstants.START_DATE, GtnFrameworkCommonConstants.END_DATE,
				GtnFrameworkCommonConstants.ENTITY_CODE, GtnFrameworkCommonConstants.ENTITY_CODE_NAME, "modifiedBy",
				"modifiedDate", "createdBy", "createdDate", "identifierStatusDes" });
		attachResults.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.ITEM_IRT_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.ITEM_IDENTIFIER, GtnFrameworkCommonConstants.IDENTIFIER_STATUS,
				GtnFrameworkCommonConstants.START_DATE, GtnFrameworkCommonConstants.END_DATE,
				GtnFrameworkCommonConstants.ENTITY_CODE, GtnFrameworkCommonConstants.ENTITY_CODE_NAME, "modifiedBy",
				"modifiedDate", "createdBy", "createdDate"));
		attachResults.setEditableField(
				createTableFieldFactoryComponents(attachResults.getEditableColumnList(), componentConfig));
		attachResults.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));
		attachResults.setExtraColumn(new Object[] { "itemQulaifierSid", "itemIdentifierSid" });
		attachResults.setEditable(true);

	}

	private void identifierInformationRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig layoutConfig = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.IDENTIFIER_REMOVE_BUTTON_LAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_MASTERIDENTIFIER_TAB);
		componentList.add(layoutConfig);
		identifierInformationRemoveButton(componentList, componentConfig);
		addExcelButtonComponent(componentList, componentConfig);
	}

	private void identifierInformationRemoveButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig attachButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"identifierRemoveButton", true, GtnFrameworkCommonConstants.IDENTIFIER_REMOVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName("Remove");
		componentList.add(attachButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		alertActionConfig.addActionParameter(
				GtnFrameworkItemMasterClassContants.ITEM_MASTER_PRICING_RESULTS_TABLE_VALIDATION_ACTION);
		alertActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		alertActionConfig.addActionParameter(
				GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_REMOVE);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG_REMOVE_HEADER);
		alertParamsList.add(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_CONFIRMATION_MSG_IDENTIFIER_REMOVE);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.REMOVE_ACTION);
		selectAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE);
		onSucessActionConfigList.add(selectAction);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(confirmationActionConfig);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
			GtnFrameworkComponentConfigProvider componentConfig) {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.IDENTIFIER_STATUS);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = componentConfig.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("customFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		comboBoxConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
		customFilterComponentConfig.setGtnComboboxConfig(comboBoxConfig);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		return customFilterConfigMap;
	}

	/**
	 * 
	 * @param componentList
	 * @param componentConfig
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig excelButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"gtnIdentifierExcelButton", true, GtnFrameworkCommonConstants.IDENTIFIER_REMOVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfig.getExcelBtnconfig(
				"Identifier", true, GtnFrameworkCommonConstants.ITEM_MASTER_IDENTIFIERATTACH_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig
				.setHelperTableMapedPropertyIdList(Arrays.asList(GtnFrameworkCommonConstants.IDENTIFIER_STATUS));
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds,
			GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType checkRecord = "checkRecordId".equals(propertyId)
					? GtnUIFrameworkComponentType.CHECKBOX : GtnUIFrameworkComponentType.COMBOBOX;
			GtnUIFrameworkComponentType itemMaster = GtnFrameworkItemMasterStringContants
					.getItemMasterItemIdentifierDatefieldproperties().contains(propertyId)
							? GtnUIFrameworkComponentType.DATEFIELD : checkRecord;
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnFrameworkItemMasterStringContants
					.getItemMasterItemIdentifierTextfeildproperties().contains(propertyId)
							? GtnUIFrameworkComponentType.TEXTBOX : itemMaster;
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			fieldConfig.setEnable(GtnFrameworkItemMasterStringContants.getItemMasterItemIdentifierEditablefieldlist()
					.contains(propertyId));
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig companyFamilyPlanStatusConfig = componentConfig.getComboBoxConfig(
						GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				fieldConfig.setGtnComboboxConfig(companyFamilyPlanStatusConfig);
			}
			editableFields.add(fieldConfig);
		}

		return editableFields;
	}
}
