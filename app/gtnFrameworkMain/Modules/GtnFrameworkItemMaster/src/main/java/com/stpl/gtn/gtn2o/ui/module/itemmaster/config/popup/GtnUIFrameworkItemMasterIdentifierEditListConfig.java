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

public class GtnUIFrameworkItemMasterIdentifierEditListConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig view = componentConfig.getViewConfig("EditList", "identifierEditList", false);

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		itemMasterEditListPanel(componentList, componentConfig);
		return view;
	}

	private void itemMasterEditListPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig panel = componentConfig.getPanelConfig("imIdentifierEditListPanel", false, null);
		panel.setComponentName("Identifier Information");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = componentConfig.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.IM_IDENTIFIER_EDIT_LIST_PANEL_LAYOUT, true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		addResultLayout(componentList, componentConfig);
		addFieldPanel(componentList, componentConfig);
		addButtonLayout(componentList, componentConfig);

	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig panel = componentConfig.getPanelConfig("imIdentifierFeildEditListPanel", true,
				GtnFrameworkCommonConstants.IM_IDENTIFIER_EDIT_LIST_PANEL_LAYOUT);
		panel.setComponentName("Item Identifier Setup");
		panel.setAuthorizationIncluded(true);
		panel.setSpacing(true);
		panel.setMargin(true);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig mainLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.EDIL_LIST_FIELD_MAIN_LAYOUT, Boolean.TRUE, panel.getComponentId());
		mainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainLayout.setSpacing(Boolean.TRUE);
		mainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(mainLayout);

		GtnUIFrameworkComponentConfig edilListFieldInnerLayout = componentConfig.getCssLayoutConfig(
				"edilListFieldInnerLayout", Boolean.TRUE, GtnFrameworkCommonConstants.EDIL_LIST_FIELD_MAIN_LAYOUT);
		edilListFieldInnerLayout.setSpacing(Boolean.TRUE);
		edilListFieldInnerLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		componentList.add(edilListFieldInnerLayout);

		GtnUIFrameworkComponentConfig edilListFieldLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.EDIL_LIST_FIELD_LAYOUT, Boolean.TRUE, "edilListFieldInnerLayout",
				GtnUIFrameworkLayoutType.COL2_LAYOUT);
		componentList.add(edilListFieldLayout);

		GtnUIFrameworkComponentConfig notesMainLayout = componentConfig.getCssLayoutConfig("notesMainLayout",
				Boolean.TRUE, GtnFrameworkCommonConstants.EDIL_LIST_FIELD_MAIN_LAYOUT);
		notesMainLayout.setMargin(true);
		notesMainLayout.setSpacing(Boolean.TRUE);
		notesMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(notesMainLayout);

		addFieldComponent(componentList, componentConfig);

	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		addIdentifierCodeQualifier(componentList, componentConfig);
		addEffectiveDates(componentList, componentConfig);
		addIdentifierCodeQualifierName(componentList, componentConfig);
		addEntityCode(componentList, componentConfig);
		addNotes(componentList, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig gtnLeditListResultLayoutayout = componentConfig.getHorizontalLayoutConfig(
				"editListResultLayout", true, GtnFrameworkCommonConstants.IM_IDENTIFIER_EDIT_LIST_PANEL_LAYOUT);
		gtnLeditListResultLayoutayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLeditListResultLayoutayout);

		identifierResultDataTable(componentList, componentConfig);
	}

	private void identifierResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig attachResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EDITATTACH_RESULT_TABLE, true, "editListResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		attachResultConfig.setAuthorizationIncluded(true);
		attachResultConfig.setComponentName("Results");
		attachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_350);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig identifierAttachResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"itemMasters", "imQualifierSearchQuery");
		identifierAttachResults.setEditable(false);
		identifierAttachResults.setItemPerPage(10);
		identifierAttachResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE });
		identifierAttachResults.setTableVisibleHeader(new String[] { "Identifier Code Qualifier",
				"Identifier Code Qualifier Name", "Effective Dates", "Specific Entity Code", "Notes", "Modifier By",
				"Modified Date", "Created By", "Created Date" });
		identifierAttachResults
				.setTableColumnMappingId(new Object[] { "identifierCodeQualifier", "identifierCodeQualifierName",
						"effective", "entityCode", "notes", "modifierBy", "modifiedDate", "createdBy", "createdDate" });
		identifierAttachResults.setExtraColumn(new Object[] { "identifierQualifierSid", "recordLockStatus" });
		identifierAttachResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_MASTER);
		identifierAttachResults.setItemClickListener(true);
		attachResultConfig.setGtnPagedTableConfig(identifierAttachResults);

		List<GtnUIFrameWorkActionConfig> attachActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction
				.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_IDENTIFIER_EDIT_LIST_CHCEK_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.EDITATTACH_RESULT_TABLE,
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER,
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.EDIT_LIST_EFFECTIVE_DATES,
				GtnFrameworkCommonConstants.EDIT_LIST_ENTITY_CODE, GtnFrameworkCommonConstants.NOTES_TEXT_AREA,
				GtnFrameworkCommonConstants.IDENTIFIER_EDITLIST_SAVE_BUTTON));
		attachActionConfigList.add(customAction);
		attachResultConfig.setGtnUIFrameWorkActionConfigList(attachActionConfigList);

	}

	private void addIdentifierCodeQualifier(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig codeQualifierLayout = componentConfig.getHorizontalLayoutConfig(
				"edilListidentifierCodeQualifierLayout", true, GtnFrameworkCommonConstants.EDIL_LIST_FIELD_LAYOUT);
		codeQualifierLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(codeQualifierLayout);

		GtnUIFrameworkComponentConfig identifierCodeQualifier = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER, true,
				"edilListidentifierCodeQualifierLayout", GtnUIFrameworkComponentType.TEXTBOX);
		identifierCodeQualifier.setAuthorizationIncluded(true);
		identifierCodeQualifier.setComponentName("Identifier Code Qualifier");

		GtnUIFrameworkTextBoxConfig identifierCodeQualifierConfig = new GtnUIFrameworkTextBoxConfig();
		identifierCodeQualifierConfig.setImmediate(true);
		identifierCodeQualifier.setGtnTextBoxConfig(identifierCodeQualifierConfig);

		componentList.add(identifierCodeQualifier);

	}

	private void addIdentifierCodeQualifierName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig codeQualifierNameLayout = componentConfig.getHorizontalLayoutConfig(
				"editListidentifierCodeQualifierNameLayout", true, GtnFrameworkCommonConstants.EDIL_LIST_FIELD_LAYOUT);
		codeQualifierNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(codeQualifierNameLayout);

		GtnUIFrameworkComponentConfig identifierCodeQualifierName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER_NAME, true,
				"editListidentifierCodeQualifierNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		identifierCodeQualifierName.setAuthorizationIncluded(true);
		identifierCodeQualifierName.setComponentName("Identifier Code Qualifier Name");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		identifierCodeQualifierName.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(identifierCodeQualifierName);

	}

	private void addEffectiveDates(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig effectiveDatesLayout = componentConfig.getHorizontalLayoutConfig(
				"editListEffectiveDateslayout", true, GtnFrameworkCommonConstants.EDIL_LIST_FIELD_LAYOUT);
		effectiveDatesLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(effectiveDatesLayout);

		GtnUIFrameworkComponentConfig editListEffectiveDates = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EDIT_LIST_EFFECTIVE_DATES, true, "editListEffectiveDateslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		editListEffectiveDates.setAuthorizationIncluded(true);
		editListEffectiveDates.setComponentName("Effective Dates");
		componentList.add(editListEffectiveDates);

		GtnUIFrameworkComboBoxConfig effectiveDatesConfig = new GtnUIFrameworkComboBoxConfig();
		effectiveDatesConfig.setItemValues(Arrays.asList("Yes", "No"));
		editListEffectiveDates.setGtnComboboxConfig(effectiveDatesConfig);
	}

	private void addEntityCode(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig entityCodeLayout = componentConfig.getHorizontalLayoutConfig(
				"editListEntityCodelayout", true, GtnFrameworkCommonConstants.EDIL_LIST_FIELD_LAYOUT);
		entityCodeLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(entityCodeLayout);

		GtnUIFrameworkComponentConfig entityCode = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EDIT_LIST_ENTITY_CODE, true, "editListEntityCodelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		entityCode.setAuthorizationIncluded(true);
		entityCode.setComponentName("Entity Code");
		componentList.add(entityCode);

		GtnUIFrameworkComboBoxConfig entityCodeConfig = new GtnUIFrameworkComboBoxConfig();
		entityCodeConfig.setItemValues(Arrays.asList("Yes", "No"));
		entityCode.setGtnComboboxConfig(entityCodeConfig);
	}

	private void addNotes(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig notesMainLayout = componentConfig.getHorizontalLayoutConfig("editListNotesLayout",
				true, "notesMainLayout");
		notesMainLayout.setSpacing(true);
		notesMainLayout.setMargin(true);
		componentList.add(notesMainLayout);

		GtnUIFrameworkComponentConfig notesTextArea = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.NOTES_TEXT_AREA, true, "editListNotesLayout",
				GtnUIFrameworkComponentType.TEXTAREA);
		notesTextArea.setAuthorizationIncluded(true);
		notesTextArea.setComponentName("Notes");

		GtnUIFrameworkTextAreaConfig gtnTextAreaConfig = new GtnUIFrameworkTextAreaConfig();
		gtnTextAreaConfig.setInputPrompt(String.valueOf("<" + new Date() + ">"));
		gtnTextAreaConfig.setEnable(true);
		gtnTextAreaConfig.setImmediate(true);
		notesTextArea.setGtnTextAreaConfig(gtnTextAreaConfig);
		componentList.add(notesTextArea);

	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig editListButtonLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.EDIT_LIST_BUTTON_LAYOUT, true,
				GtnFrameworkCommonConstants.IM_IDENTIFIER_EDIT_LIST_PANEL_LAYOUT);
		editListButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		editListButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(editListButtonLayout);
		addSaveButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);
		addDeleteButtonComponent(componentList, componentConfig);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig editListSaveLayout = componentConfig.getHorizontalLayoutConfig(
				"editListSaveButtonLayout", true, GtnFrameworkCommonConstants.EDIT_LIST_BUTTON_LAYOUT);
		componentList.add(editListSaveLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IDENTIFIER_EDITLIST_SAVE_BUTTON, true, "editListSaveButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("Save");
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_INDENFIER_QUALIFIER_VALIDATION_ACTION);
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig saveAction = new GtnUIFrameWorkActionConfig();
		saveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_SAVE_ACTION);
		saveAction.addActionParameter(GtnFrameworkCommonConstants.EDITATTACH_RESULT_TABLE);
		saveAction.addActionParameter("Identifier");
		saveAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER,
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.EDIT_LIST_EFFECTIVE_DATES,
				GtnFrameworkCommonConstants.EDIT_LIST_ENTITY_CODE, GtnFrameworkCommonConstants.NOTES_TEXT_AREA));

		actionConfigList.add(saveAction);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig deleteButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"editListDeleteButtonLayout", true, GtnFrameworkCommonConstants.EDIT_LIST_BUTTON_LAYOUT);
		componentList.add(deleteButtonLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"editListDeleteButton", true, "editListDeleteButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setAuthorizationIncluded(true);
		deleteButtonConfig.setComponentName("Delete");
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCommonConstants.EDITATTACH_RESULT_TABLE);
		alertParams.add("Error");
		alertParams.add("Please select an Identifier from the list view to Delete.");

		alertActionConfig.setActionParameterList(alertParams);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig deleteCustomAction = new GtnUIFrameWorkActionConfig();
		deleteCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteCustomAction.addActionParameter(GtnFrameworkItemMasterIdEditListDeleteValidationAction.class.getName());
		deleteCustomAction.addActionParameter(GtnFrameworkCommonConstants.EDITATTACH_RESULT_TABLE);
		deleteCustomAction.addActionParameter("recordLockStatus");
		deleteCustomAction.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_DELETE_ACTION);
		deleteCustomAction.addActionParameter("Identifier");
		deleteCustomAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER,
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.EDIT_LIST_EFFECTIVE_DATES,
				GtnFrameworkCommonConstants.EDIT_LIST_ENTITY_CODE, GtnFrameworkCommonConstants.NOTES_TEXT_AREA, GtnFrameworkCommonConstants.IDENTIFIER_EDITLIST_SAVE_BUTTON));
		actionConfigList.add(deleteCustomAction);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig gtnLayout = componentConfig.getHorizontalLayoutConfig("editListResetButtonLayout",
				true, GtnFrameworkCommonConstants.EDIT_LIST_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"editListResetButton", true, "editListResetButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionConfig.addActionParameter(
				GtnFrameworkItemMasterClassContants.ITEM_MASTER_REFRESH_EDIT_LIST_PRICING_IDENTIFIER_VALIDATION_ACTION);
		resetActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER,
				GtnFrameworkCommonConstants.EDIT_LISTIDENTIFIER_CODE_QUALIFIER_NAME,
				GtnFrameworkCommonConstants.EDIT_LIST_EFFECTIVE_DATES,
				GtnFrameworkCommonConstants.EDIT_LIST_ENTITY_CODE, GtnFrameworkCommonConstants.NOTES_TEXT_AREA,
				GtnFrameworkCommonConstants.IDENTIFIER_EDITLIST_SAVE_BUTTON));
		actionConfigList.add(resetActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
