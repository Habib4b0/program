package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCMIdentifierResetAction;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textarea.GtnUIFrameworkTextAreaConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkCMIdentifierEditList {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("EditList", "identifierEditList", false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addErrorBanner(componentList);
		companyMasterEditListPanel(componentList);
		identifierResultDataTable(componentList);
		addFieldLayout(componentList);
		addButtonLayout(componentList);

	}

	private void addErrorBanner(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig errorBannerConfig = configProvider.getUIFrameworkComponentConfig(
				"companyErrorBanner", false, null, GtnUIFrameworkComponentType.ERROR_BANNER);
		errorBannerConfig.setAuthorizationIncluded(true);
		componentList.add(errorBannerConfig);
	}

	private void companyMasterEditListPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("cmIdentifierEditListPanel", false, null);
		panel.setComponentName("Identifier Information");
		panel.setAuthorizationIncluded(true);
		panel.setSpacing(true);
		panel.setMargin(true);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCompanyStringContants.IDENTIFIER_EDIT_LIST_PANEL_LAYOUT, true, "cmIdentifierEditListPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(layoutConfig);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig projectionOptionMainLayoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCompanyStringContants.EDIT_LIST_MAIN_LAYOUT, true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_EDIT_LIST_PANEL_LAYOUT);
		projectionOptionMainLayoutConfig.setSpacing(true);
		projectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionMainLayoutConfig.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWELVE);
		componentList.add(projectionOptionMainLayoutConfig);

		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = configProvider.getCssLayoutConfig(
				"editListFieldInnerLayout", true, GtnFrameworkCompanyStringContants.EDIT_LIST_MAIN_LAYOUT);
		projectionOptionInnerLayoutConfig.setSpacing(true);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		componentList.add(projectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL2_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = configProvider
				.getUIFrameworkComponentConfig(GtnFrameworkCompanyStringContants.EDIT_LIST_FIELD_LAYOUT, true,
						"editListFieldInnerLayout", GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCompanyProjectionNameLayoutConfig);
		componentList.add(privateViewCompanyProjectionNameLayout);

		GtnUIFrameworkComponentConfig fromToLayoutConfig = configProvider.getCssLayoutConfig("notesMainLayout",
				true, GtnFrameworkCompanyStringContants.EDIT_LIST_MAIN_LAYOUT);
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setMargin(true);
		fromToLayoutConfig.setSpacing(true);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(fromToLayoutConfig);

		addFieldComponent(componentList);

	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addIdentifierCodeQualifier(componentList);
		addEffectiveDates(componentList);
		addIdentifierCodeQualifierName(componentList);
		addNotes(componentList);
	}

	private void identifierResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("editListResultLayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_EDIT_LIST_PANEL_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.EDIT_ATTACH_RESULT_TABLE, true, "editListResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		attachResultConfig.setAuthorizationIncluded(true);
		attachResultConfig.setComponentName("Results");
		attachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_350);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		attachResultConfig.setComponentStyle(tableStyle);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				String.class, Date.class, String.class, Date.class });
		attachResults
				.setTableVisibleHeader(new String[] { "Identifier Code Qualifier", "Identifier Code Qualifier Name",
						"Effective Dates", "Notes", "Modified By", "Modified Date", "Created By", "Created Date" });
		attachResults.setTableColumnMappingId(new Object[] { "identifierCodeQualifier", "identifierCodeQualifierName",
				"effectiveDates", "notes", "modifiedBy", "modifiedDate", "createdBy", "createdDate" });
		attachResults.setExtraColumn(new Object[] { "identifierQualifierSid", "recordLockStatus" });
		attachResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		attachResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		attachResults.setModuleName("companyMasters");
		attachResults.setQueryName("qualifiereditListQuery");
		attachResults.setItemClickListener(true);
		attachResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		attachResultConfig.setGtnPagedTableConfig(attachResults);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.IDENTIFIER_EDIT_LIST_ITEM_CLICK_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCompanyStringContants.EDIT_ATTACH_RESULT_TABLE,
				GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER,
				GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER_NAME,
				GtnFrameworkCompanyStringContants.EDIT_LIST_EFFECTIVE_DATE,
				GtnFrameworkCompanyStringContants.EDIT_LIST_NOTES_TEXTAREA, "editListSaveButton"));
		actionConfigList.add(customAction);
		attachResultConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addIdentifierCodeQualifier(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"edilListidentifierCodeQualifierLayout", true,
				GtnFrameworkCompanyStringContants.EDIT_LIST_FIELD_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig identifierCodeQualifier = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER, true,
				"edilListidentifierCodeQualifierLayout", GtnUIFrameworkComponentType.TEXTBOX);
		identifierCodeQualifier.setAuthorizationIncluded(true);
		identifierCodeQualifier.setComponentName("Identifier Code Qualifier");

		componentList.add(identifierCodeQualifier);

	}

	private void addIdentifierCodeQualifierName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"editListidentifierCodeQualifierNameLayout", true,
				GtnFrameworkCompanyStringContants.EDIT_LIST_FIELD_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig identifierCodeQualifierName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER_NAME, true,
				"editListidentifierCodeQualifierNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		identifierCodeQualifierName.setAuthorizationIncluded(true);
		identifierCodeQualifierName.setComponentName("Identifier Code Qualifier Name");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(GtnWsNumericConstants.FIVE);
		identifierCodeQualifierName.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(identifierCodeQualifierName);

	}

	private void addEffectiveDates(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"editListEffectiveDateslayout", true, GtnFrameworkCompanyStringContants.EDIT_LIST_FIELD_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig editListEffectiveDates = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.EDIT_LIST_EFFECTIVE_DATE, true, "editListEffectiveDateslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		editListEffectiveDates.setAuthorizationIncluded(true);
		editListEffectiveDates.setComponentName("Effective Dates");

		componentList.add(editListEffectiveDates);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setItemValues(Arrays.asList("Yes", "No"));
		editListEffectiveDates.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addNotes(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("editListNotesLayout", true,
				"notesMainLayout");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig notesTextArea = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.EDIT_LIST_NOTES_TEXTAREA, true, "editListNotesLayout",
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

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCompanyStringContants.EDIT_LIST_BUTTON_LAYOUT, true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_EDIT_LIST_PANEL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth("100%");
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWELVE);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList);
		addDeleteButtonComponent(componentList);
		addResetButtonComponent(componentList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig saveButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"editListSaveButton", true, GtnFrameworkCompanyStringContants.EDIT_LIST_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setComponentName("SAVE");
		saveButtonConfig.setAuthorizationIncluded(true);
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig saveAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveAction.addActionParameter(GtnFrameworkCompanyClassContants.IDENTIFIER_EDIT_LIST_SAVE_ACTION);
		actionConfigList.add(saveAction);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deleteButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"editListDeleteButton", true, GtnFrameworkCompanyStringContants.EDIT_LIST_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setComponentName("DELETE");
		deleteButtonConfig.setAuthorizationIncluded(true);

		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCompanyStringContants.EDIT_ATTACH_RESULT_TABLE);
		alertParams.add("Error");
		alertParams.add("Please select an Identifier from the list view to Delete.");

		alertActionConfig.setActionParameterList(alertParams);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig deleteCustomAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteCustomAction.addActionParameter(GtnFrameworkCompanyClassContants.IDENTIFIER_DELETE_VALIDATION_CUSTOM);
		deleteCustomAction.addActionParameter(GtnFrameworkCompanyStringContants.EDIT_ATTACH_RESULT_TABLE);
		deleteCustomAction.addActionParameter("recordLockStatus");
		actionConfigList.add(deleteCustomAction);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"editListResetButton", true, GtnFrameworkCompanyStringContants.EDIT_LIST_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);

		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetConfirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Confirmation");
		params.add("Are you sure you want to reset the page to default/previous values?");

		List<GtnUIFrameWorkActionConfig> resetCustomActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetCustomActionConfig = new GtnUIFrameWorkActionConfig();
		resetCustomActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetCustomActionConfig.addActionParameter(GtnFrameworkCMIdentifierResetAction.class.getName());
		resetCustomActionConfig
				.addActionParameter(Arrays.asList(GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER,
						GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER_NAME,
						GtnFrameworkCompanyStringContants.EDIT_LIST_EFFECTIVE_DATE,
						GtnFrameworkCompanyStringContants.EDIT_LIST_NOTES_TEXTAREA));
		resetCustomActionList.add(resetCustomActionConfig);

		params.add(resetCustomActionList);

		resetConfirmationActionConfig.setActionParameterList(params);

		actionConfigList.add(resetConfirmationActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
