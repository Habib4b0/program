package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterAttachAction;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCMIdentifierTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addIdentifierTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(GtnFrameworkCompanyStringContants.IDENTIFIER_TAB, false, null);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth("100%");

		componentList.add(layoutConfig);
		identifierInformationPanel(componentList);
		identifierResultPanel(componentList);
		identifierInformationRemoveButtonLayout(componentList);

	}

	private void identifierInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("identifierInformationPanel", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_TAB);
		panel.setComponentName("Identifier Information");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_PANEL_LAYOUT, true,
				"identifierInformationPanel");
		layoutConfig.setComponentWidth("100%");
		componentList.add(layoutConfig);

		identifierInformationLayout(componentList);
		identifierInformationAttachButtonLayout(componentList);
	}

	private void identifierInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_LAYOUT, true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_PANEL_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);

		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		identifierInformationFields(componentList);
	}

	private void identifierInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addCompanyQualifierName(componentList);
		addCompanyIdentifier(componentList);
		addCompanyStatus(componentList);
		addCompanyIdentifierStartDate(componentList);
		addCompanyIdentifierEndDate(componentList);
	}

	private void addCompanyQualifierName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"identifierCompanyQualifierNamelayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.COMPANY_QUALIFIER_NAME, true, "identifierCompanyQualifierNamelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setComponentName("Company Qualifier Name");
		companyQualifierName.setAuthorizationIncluded(true);

		companyQualifierName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = configProvider.getComboBoxConfig("CompanyIdentifier",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyQualifierNameConfig.setItemValues(Arrays.asList("Edit List"));
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.IDENTIFIER_EDIT_LIST_ACTION);
		customAction.addActionParameter("identifierEditList");
		customAction.addActionParameter("Company Identifier Setup Pop-up");
		customAction.addActionParameter("editattachResultTable");
		actionConfigList.add(customAction);
		companyQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCompanyIdentifier(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"identifierInformationCompanyIdentifierlayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"identifierInformationCompanyIdentifier", true, "identifierInformationCompanyIdentifierlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Company Identifier");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, true, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		companyIdentifierConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(companyIdentifierConfig);

	}

	private void addCompanyIdentifierStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyIdentifierStartDatelayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"companyIdentifierStartDate", true, "companyIdentifierStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Start Date");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, true, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		companyIdentifierConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(companyIdentifierConfig);

	}

	private void addCompanyIdentifierEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyIdentifierEndDatelayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"companyIdentifierEndDate", true, "companyIdentifierEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("End Date");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, true, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"identifierInformationIdentifierStatuslayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				"identifierInformationCompanyStatus", true, "identifierInformationIdentifierStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Identifier Status");
		companyStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCompanyStringContants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void identifierInformationAttachButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"identifierAttachButtonLayout", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFORMATION_PANEL_LAYOUT);
		componentList.add(layoutConfig);
		identifierInformationAttachButton(componentList);
	}

	private void identifierInformationAttachButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"identifierAttachButton", true, "identifierAttachButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Attach");
		attachButtonConfig.setAuthorizationIncluded(true);

		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_IDENTIFIER_VALIDATION_ACTION);
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnUIFrameworkCompanyMasterAttachAction.class.getName());
		actionParameter.add(GtnFrameworkCompanyStringContants.IDENTIFIER_ATTACH_RESULT_TABLE);
		actionParameter.add(null);
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.QUALIFIER_NAME,
				GtnFrameworkCompanyStringContants.IDENTIFIER, GtnFrameworkCompanyStringContants.IDENTIFIER_STATUS,
				GtnFrameworkCompanyStringContants.START_DATE, GtnFrameworkCompanyStringContants.END_DATE));
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.COMPANY_QUALIFIER_NAME,
				"identifierInformationCompanyIdentifier", "identifierInformationCompanyStatus",
				"companyIdentifierStartDate", "companyIdentifierEndDate"));
		actionParameter.add(Arrays.asList(String.class, String.class, Integer.class, Date.class, Date.class));
		actionParameter.add(GtnFrameworkCompanyStringContants.COMPANY_QUALIFIER_NAME);
		actionParameter.add("IdentifierAttach");
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void identifierResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("identifierResultPanel", true,
				GtnFrameworkCompanyStringContants.IDENTIFIER_TAB);
		panel.setComponentName("Results");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		identifierResultLayout(componentList);
	}

	private void identifierResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("identifierResultLayout",
				true, "identifierResultPanel");
		layoutConfig.setComponentWidth("100%");

		componentList.add(layoutConfig);

		identifierResultDataTable(componentList);
	}

	private void identifierResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmIdentifierResultDataTableConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.IDENTIFIER_ATTACH_RESULT_TABLE, true, "identifierResultLayout",
				GtnUIFrameworkComponentType.DATATABLE);
		cmIdentifierResultDataTableConfig.setAuthorizationIncluded(true);
		cmIdentifierResultDataTableConfig.setComponentName("Results");
		cmIdentifierResultDataTableConfig.setComponentHight("300px");
		cmIdentifierResultDataTableConfig.setComponentWidth("100%");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		cmIdentifierResultDataTableConfig.setComponentStyle(tableStyle);

		componentList.add(cmIdentifierResultDataTableConfig);

		GtnUIFrameworkPagedTableConfig cmIdentifierResultDataTable = new GtnUIFrameworkPagedTableConfig();
		cmIdentifierResultDataTableConfig.setGtnPagedTableConfig(cmIdentifierResultDataTable);
		cmIdentifierResultDataTable.setEditable(true);
		cmIdentifierResultDataTable.setFilterBar(true);
		cmIdentifierResultDataTable.setSelectable(true);
		cmIdentifierResultDataTable.setTableColumnDataType(new Class[] { String.class, String.class, Integer.class,
				Date.class, Date.class, String.class, Date.class, String.class, Date.class });
		cmIdentifierResultDataTable
				.setTableVisibleHeader(GtnFrameworkCompanyStringContants.getIdentifierTabTableHeaderList());
		cmIdentifierResultDataTable
				.setTableColumnMappingId(GtnFrameworkCompanyStringContants.getIdentifierTabTableColumnList());
		cmIdentifierResultDataTable.setExtraColumn(
				new Object[] { "compQualifierSid", GtnFrameworkCompanyStringContants.IDENTIFIER_STATUS_VALUE });
		cmIdentifierResultDataTable.setExtraColumnDataType(new Class[] { Integer.class, String.class });
		cmIdentifierResultDataTable.setEditableColumnList(Arrays.asList(
				GtnFrameworkCompanyStringContants.QUALIFIER_NAME, GtnFrameworkCompanyStringContants.IDENTIFIER,
				GtnFrameworkCompanyStringContants.IDENTIFIER_STATUS, GtnFrameworkCompanyStringContants.START_DATE,
				GtnFrameworkCompanyStringContants.END_DATE, GtnFrameworkCompanyStringContants.CREATED_BY,
				GtnFrameworkCompanyStringContants.CREATED_DATE, GtnFrameworkCompanyStringContants.MODIFIED_BY,
				GtnFrameworkCompanyStringContants.MODIFIED_DATE));
		cmIdentifierResultDataTable.setEditableField(
				createTableFieldFactoryComponents(cmIdentifierResultDataTable.getEditableColumnList()));

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCompanyStringContants.IDENTIFIER_STATUS);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("customFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
		customFilterComponentConfig.getGtnComboboxConfig().setComboBoxType("STATUS");
		customFilterComponentConfig.getGtnComboboxConfig()
				.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		cmIdentifierResultDataTable.setCustomFilterConfigMap(customFilterConfigMap);
	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			if (GtnFrameworkCompanyStringContants.getCmIdentifierTextfieldPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
			} else if (GtnFrameworkCompanyStringContants.getCmIdentifierDatefieldPropertiesList()
					.contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
			}
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			boolean isReadOnly = !GtnFrameworkCompanyStringContants.getCmIdentifierEditablefieldList()
					.contains(propertyId);
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
				GtnUIFrameworkComboBoxConfig companyFamilyPlanStatusConfig = new GtnUIFrameworkComboBoxConfig();
				companyFamilyPlanStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				companyFamilyPlanStatusConfig.setComboBoxType("STATUS");
				companyFamilyPlanStatusConfig.setReadOnly(isReadOnly);
				fieldConfig.setGtnComboboxConfig(companyFamilyPlanStatusConfig);

			} else if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.TEXTBOX)) {
				GtnUIFrameworkTextBoxConfig textConfigIdentifierTab = new GtnUIFrameworkTextBoxConfig();
				textConfigIdentifierTab.setReadOnly(isReadOnly);
				fieldConfig.setGtnTextBoxConfig(textConfigIdentifierTab);
			}

			else if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.DATEFIELD)) {
				GtnUIFrameworkDateFieldConfig dateConfigIdentifierTab = new GtnUIFrameworkDateFieldConfig();
				dateConfigIdentifierTab.setReadOnly(isReadOnly);
				fieldConfig.setGtnDateFieldConfig(dateConfigIdentifierTab);
			}

			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	private void identifierInformationRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"identifierRemoveButtonLayout", true, GtnFrameworkCompanyStringContants.IDENTIFIER_TAB);
		componentList.add(layoutConfig);
		identifierInformationRemoveButton(componentList);
		addExcelButtonComponent(componentList);
	}

	private void identifierInformationRemoveButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"identifierRemoveButton", true, "identifierRemoveButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Remove");
		attachButtonConfig.setAuthorizationIncluded(true);

		componentList.add(attachButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object removeValidation = GtnFrameworkCompanyClassContants.IDENTIFIER_REMOVE_VALIDATION_CUSTOM;
		alertActionConfig.setActionParameterList(
				Arrays.asList(removeValidation, GtnFrameworkCompanyStringContants.IDENTIFIER_ATTACH_RESULT_TABLE,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_REMOVE,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_ONE_REMOVE));
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG_REMOVE_HEADER);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG_IDENTIFIER_REMOVE);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.REMOVE_ACTION);

		selectAction.addActionParameter(GtnFrameworkCompanyStringContants.IDENTIFIER_ATTACH_RESULT_TABLE);
		onSucessActionConfigList.add(selectAction);
		actionConfigList.add(confirmationActionConfig);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	/**
	 *
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"dentifierExcelButton", true, GtnFrameworkCompanyStringContants.IDENTIFIER_TAB,
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig("Identifier",
				true, GtnFrameworkCompanyStringContants.IDENTIFIER_ATTACH_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setTitleNeeded(true);
		gtnUIFrameworkExcelButtonConfig
				.setHelperTableMapedPropertyIdList(Arrays.asList(GtnFrameworkCompanyStringContants.IDENTIFIER_STATUS));
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

}
