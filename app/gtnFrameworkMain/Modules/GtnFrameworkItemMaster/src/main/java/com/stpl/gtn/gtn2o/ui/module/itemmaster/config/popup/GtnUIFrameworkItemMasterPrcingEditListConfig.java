package com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textarea.GtnUIFrameworkTextAreaConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterIdEditListDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkItemMasterPrcingEditListConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig view = componentConfig.getViewConfig("EditList", "pricingEditList", false);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		itemMasterEditListPanel(componentList, componentConfig);
		return view;
	}

	private void itemMasterEditListPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mainPanel = componentConfig.getPanelConfig("imPricingEditListPanel", false, null);
		mainPanel.setComponentName("Price Types");
		mainPanel.setAuthorizationIncluded(true);
		componentList.add(mainPanel);

		GtnUIFrameworkComponentConfig layoutConfig = componentConfig.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.IM_PRICING_EDIT_LIST_PANEL_LAYOUT, true, "imPricingEditListPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(layoutConfig);
		addResultLayout(componentList, componentConfig);
		addFieldPanel(componentList, componentConfig);
		addButtonLayout(componentList, componentConfig);

	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mainFieldPanel = componentConfig.getPanelConfig("imPricingFeildEditListPanel",
				true, GtnFrameworkCommonConstants.IM_PRICING_EDIT_LIST_PANEL_LAYOUT);
		mainFieldPanel.setComponentName("Price Type Setup");
		mainFieldPanel.setAuthorizationIncluded(true);
		componentList.add(mainFieldPanel);

		GtnUIFrameworkComponentConfig mainFieldLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_MAIN_LAYOUT, Boolean.TRUE,
				"imPricingFeildEditListPanel");
		mainFieldLayout.setSpacing(Boolean.TRUE);
		mainFieldLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		mainFieldLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(mainFieldLayout);

		GtnUIFrameworkComponentConfig innerLayoutConfig = componentConfig.getCssLayoutConfig(
				"edilListPricingFieldInnerLayout", Boolean.TRUE,
				GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_MAIN_LAYOUT);
		innerLayoutConfig.setSpacing(Boolean.TRUE);
		innerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		componentList.add(innerLayoutConfig);

		GtnUIFrameworkComponentConfig edilListPricingFieldLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_LAYOUT, Boolean.TRUE,
				"edilListPricingFieldInnerLayout", GtnUIFrameworkLayoutType.COL2_LAYOUT);
		componentList.add(edilListPricingFieldLayout);

		GtnUIFrameworkComponentConfig notesPricingMainLayout = componentConfig.getCssLayoutConfig(
				"notesPricingMainLayout", Boolean.TRUE,
				GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_MAIN_LAYOUT);
		notesPricingMainLayout.setMargin(true);
		notesPricingMainLayout.setSpacing(Boolean.TRUE);
		notesPricingMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(notesPricingMainLayout);

		addFieldComponent(componentList, componentConfig);

	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addPricingCodeQualifier(componentList, componentConfig);
		addPricingCodeQualifierName(componentList, componentConfig);
		addEffectiveDates(componentList, componentConfig);
		addEntityCode(componentList, componentConfig);
		addNotes(componentList, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListResultLayout", true, GtnFrameworkCommonConstants.IM_PRICING_EDIT_LIST_PANEL_LAYOUT);
		resultLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultLayout);
		pricingResultDataTable(componentList, componentConfig);
	}

	private void pricingResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingAttachResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_EDITATTACH_RESULT_TABLE, true, "pricingEditListResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		pricingAttachResultConfig.setAuthorizationIncluded(true);
		pricingAttachResultConfig.setComponentName("Results");
		pricingAttachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_350);
		pricingAttachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(pricingAttachResultConfig);

		GtnUIFrameworkPagedTableConfig pricingAttachResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"itemMasters", "imPricnigQualifierSearchQuery");
		pricingAttachResultConfig.setGtnPagedTableConfig(pricingAttachResults);
		pricingAttachResults.setEditable(false);
		pricingAttachResults.setItemPerPage(10);
		pricingAttachResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE });
		pricingAttachResults.setTableVisibleHeader(new String[] { "Pricing Code Qualifier",
				"Pricing Code Qualifier Name", "Effective Dates", "Specific Entity Code", "Notes", "Modifier By",
				"Modified Date", "Created By", "Created Date" });
		pricingAttachResults.setTableColumnMappingId(new Object[] { "pricingCodeQualifier", "pricingCodeQualifierName",
				"effective", "entityCode", "notes", "modifierBy", "modifiedDate", "createdBy", "createdDate" });
		pricingAttachResults.setExtraColumn(new Object[] { "pricingQualifierSid", "recordLockStatus" });
		pricingAttachResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_MASTER);
		pricingAttachResults.setItemClickListener(true);
		pricingAttachResultConfig.setGtnPagedTableConfig(pricingAttachResults);

		List<GtnUIFrameWorkActionConfig> pricingActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_IDENTIFIER_EDIT_LIST_CHCEK_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PRICING_EDITATTACH_RESULT_TABLE,
				GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER,
				GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_EFFECTIVE_DATES,
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_ENTITY_CODE,
				GtnFrameworkCommonConstants.PRICING_NOTES_TEXT_AREA,
				GtnFrameworkCommonConstants.PRICING_EDITLIST_SAVE_BUTTON));
		pricingActionConfigList.add(customAction);
		pricingAttachResultConfig.setGtnUIFrameWorkActionConfigList(pricingActionConfigList);

	}

	private void addPricingCodeQualifier(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig codeQualifierLayout = componentConfig.getHorizontalLayoutConfig(
				"edilListpricingCodeQualifierLayout", true, GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_LAYOUT);
		codeQualifierLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(codeQualifierLayout);

		GtnUIFrameworkComponentConfig pricingCodeQualifier = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER, true,
				"edilListpricingCodeQualifierLayout", GtnUIFrameworkComponentType.TEXTBOX);
		pricingCodeQualifier.setAuthorizationIncluded(true);
		pricingCodeQualifier.setComponentName("Pricing Code Qualifier");

		GtnUIFrameworkTextBoxConfig pricingCodeQualifierConfig = new GtnUIFrameworkTextBoxConfig();
		pricingCodeQualifierConfig.setImmediate(true);
		pricingCodeQualifier.setGtnTextBoxConfig(pricingCodeQualifierConfig);

		componentList.add(pricingCodeQualifier);

	}

	private void addPricingCodeQualifierName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig codeQualifierNameLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListpricingCodeQualifierNameLayout", true,
				GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_LAYOUT);
		codeQualifierNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(codeQualifierNameLayout);

		GtnUIFrameworkComponentConfig pricingCodeQualifierName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER_NAME, true,
				"pricingEditListpricingCodeQualifierNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		pricingCodeQualifierName.setAuthorizationIncluded(true);
		pricingCodeQualifierName.setComponentName("Pricing Code Qualifier Name");

		GtnUIFrameworkValidationConfig pricingCodeQualifierNameValidationConfig = new GtnUIFrameworkValidationConfig();
		pricingCodeQualifierNameValidationConfig.setMaxLength(5);
		pricingCodeQualifierName.setGtnUIFrameworkValidationConfig(pricingCodeQualifierNameValidationConfig);

		componentList.add(pricingCodeQualifierName);

	}

	private void addEffectiveDates(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig effectiveDatesLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListEffectiveDateslayout", true,
				GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_LAYOUT);
		effectiveDatesLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(effectiveDatesLayout);

		GtnUIFrameworkComponentConfig pricingEditListEffectiveDates = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_EFFECTIVE_DATES, true,
				effectiveDatesLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		pricingEditListEffectiveDates.setAuthorizationIncluded(true);
		pricingEditListEffectiveDates.setComponentName("Effective Dates");
		componentList.add(pricingEditListEffectiveDates);

		GtnUIFrameworkComboBoxConfig effectiveDatesConfig = new GtnUIFrameworkComboBoxConfig();
		effectiveDatesConfig.setItemValues(Arrays.asList("Yes", "No"));
		pricingEditListEffectiveDates.setGtnComboboxConfig(effectiveDatesConfig);
	}

	private void addEntityCode(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig entityCodeLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListEntityCodelayout", true, GtnFrameworkCommonConstants.EDIL_LIST_PRICING_FIELD_LAYOUT);
		entityCodeLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(entityCodeLayout);

		GtnUIFrameworkComponentConfig entityCode = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_ENTITY_CODE, true, entityCodeLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		entityCode.setComponentName("Entity Code");
		componentList.add(entityCode);

		GtnUIFrameworkComboBoxConfig entityCodeConfig = new GtnUIFrameworkComboBoxConfig();
		entityCodeConfig.setItemValues(Arrays.asList("Yes", "No"));
		entityCode.setGtnComboboxConfig(entityCodeConfig);
	}

	private void addNotes(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig notesLayout = componentConfig
				.getHorizontalLayoutConfig("pricingEditListNotesLayout", true, "notesPricingMainLayout");
		componentList.add(notesLayout);

		GtnUIFrameworkComponentConfig notesTextArea = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_NOTES_TEXT_AREA, true, notesLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTAREA);
		notesTextArea.setAuthorizationIncluded(true);
		notesTextArea.setComponentName("Notes");

		GtnUIFrameworkTextAreaConfig noteTextAreaConfig = new GtnUIFrameworkTextAreaConfig();
		noteTextAreaConfig.setInputPrompt(String.valueOf("<" + new Date() + ">"));
		noteTextAreaConfig.setEnable(true);
		noteTextAreaConfig.setImmediate(true);
		notesTextArea.setGtnTextAreaConfig(noteTextAreaConfig);
		componentList.add(notesTextArea);

	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig buttonLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_BUTTON_LAYOUT, true,
				GtnFrameworkCommonConstants.IM_PRICING_EDIT_LIST_PANEL_LAYOUT);
		buttonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		buttonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(buttonLayout);
		addSaveButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);
		addDeleteButtonComponent(componentList, componentConfig);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig pricingEditListSaveButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListSaveButtonLayout", true, GtnFrameworkCommonConstants.PRICING_EDIT_LIST_BUTTON_LAYOUT);
		componentList.add(pricingEditListSaveButtonLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICING_EDITLIST_SAVE_BUTTON, true, "pricingEditListSaveButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("Save");
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_PRICNIG_QUALIFIER_VALIDATION_ACTION);
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig saveAction = new GtnUIFrameWorkActionConfig();
		saveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_SAVE_ACTION);
		saveAction.addActionParameter(GtnFrameworkCommonConstants.PRICING_EDITATTACH_RESULT_TABLE);
		saveAction.addActionParameter("Pricing");
		saveAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER,
				GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_EFFECTIVE_DATES,
				GtnFrameworkCommonConstants.PRICING_EDIT_LIST_ENTITY_CODE,
				GtnFrameworkCommonConstants.PRICING_NOTES_TEXT_AREA,
				GtnFrameworkCommonConstants.PRICING_EDITLIST_SAVE_BUTTON));
		actionConfigList.add(saveAction);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig deleteBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListDeleteButtonLayout", true, GtnFrameworkCommonConstants.PRICING_EDIT_LIST_BUTTON_LAYOUT);
		componentList.add(deleteBtnLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"pricingEditListDeleteButton", true, "pricingEditListDeleteButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setAuthorizationIncluded(true);
		deleteButtonConfig.setComponentName("Delete");
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> deleteActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCommonConstants.PRICING_EDITATTACH_RESULT_TABLE);
		alertParams.add("Error");
		alertParams.add("Please select an Identifier from the list view to Delete.");

		alertActionConfig.setActionParameterList(alertParams);
		deleteActionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig deleteCustomAction = new GtnUIFrameWorkActionConfig();
		deleteCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteCustomAction.addActionParameter(GtnFrameworkItemMasterIdEditListDeleteValidationAction.class.getName());
		deleteCustomAction.addActionParameter(GtnFrameworkCommonConstants.PRICING_EDITATTACH_RESULT_TABLE);
		deleteCustomAction.addActionParameter("recordLockStatus");
		deleteCustomAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_DELETE_ACTION);
		deleteCustomAction.addActionParameter("Pricing");
		deleteCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER,
						GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER_NAME,
						GtnFrameworkCommonConstants.PRICING_EDIT_LIST_EFFECTIVE_DATES,
						GtnFrameworkCommonConstants.PRICING_EDIT_LIST_ENTITY_CODE,
						GtnFrameworkCommonConstants.PRICING_NOTES_TEXT_AREA,
						GtnFrameworkCommonConstants.PRICING_EDITLIST_SAVE_BUTTON));
		deleteActionConfigList.add(deleteCustomAction);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(deleteActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"pricingEditListResetButtonLayout", true, GtnFrameworkCommonConstants.PRICING_EDIT_LIST_BUTTON_LAYOUT);
		componentList.add(resetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"pricingEditListResetButton", true, "pricingEditListResetButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionConfig.addActionParameter(
				GtnFrameworkItemMasterClassContants.ITEM_MASTER_REFRESH_EDIT_LIST_PRICING_IDENTIFIER_VALIDATION_ACTION);
		resetActionConfigList.add(resetActionConfig);
		resetActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER,
						GtnFrameworkCommonConstants.PRICING_EDIT_LISTPRICING_CODE_QUALIFIER_NAME,
						GtnFrameworkCommonConstants.PRICING_EDIT_LIST_EFFECTIVE_DATES,
						GtnFrameworkCommonConstants.PRICING_EDIT_LIST_ENTITY_CODE,
						GtnFrameworkCommonConstants.PRICING_NOTES_TEXT_AREA));
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

}
