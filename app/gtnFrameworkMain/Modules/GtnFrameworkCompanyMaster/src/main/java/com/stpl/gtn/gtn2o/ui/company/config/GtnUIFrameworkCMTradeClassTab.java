package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterAttachAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCompanyMasterTradeClassParentTabValidation;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkCMTradeClassTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addTradeClassTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_TAB, true, "companyTabsheetPanel");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setTabComponent(true);
		layoutConfig.setAuthorizationIncluded(true);
		componentList.add(layoutConfig);
		tradeClassInformationPanel(componentList);
		tradeClassResultPanel(componentList);
		tradeClassInformationRemoveButtonLayout(componentList);

	}

	private void tradeClassInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("tradeClassInformationPanel", true,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_TAB);
		panel.setComponentName("Trade Class Information");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_PANEL_LAYOUT, true, "tradeClassInformationPanel");
		layoutConfig.setComponentWidth("100%");
		componentList.add(layoutConfig);

		tradeClassInformationLayout(componentList);
		tradeClassInformationAttachButtonLayout(componentList);
	}

	private void tradeClassInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_LAYOUT, true,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_PANEL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);

		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		tradeClassInformationFields(componentList);
	}

	private void tradeClassInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addTradeClass(componentList);
		addTradeClassStartDate(componentList);
		addTradeClassEndDate(componentList);
	}

	private void addTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("tradeClasslayout", true,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_NAME, true, "tradeClasslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName("Trade Class");

		companyQualifierName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = configProvider
				.getComboBoxConfig("COMPANY_TRADE_CLASS", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);

	}

	private void addTradeClassStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("tradeClassStartDatelayout",
				true, GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig tradeClassStartDate = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_START_DATE, true, "tradeClassStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		tradeClassStartDate.setAuthorizationIncluded(true);
		tradeClassStartDate.setComponentName("Trade Class Start Date");

		tradeClassStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(tradeClassStartDate);
	}

	private void addTradeClassEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("tradeClassEndDatelayout",
				true, GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig tradeClassEndDate = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_END_DATE, true, "tradeClassEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		tradeClassEndDate.setAuthorizationIncluded(true);
		tradeClassEndDate.setComponentName("Trade Class End Date");

		componentList.add(tradeClassEndDate);

	}

	private void tradeClassInformationAttachButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"tradeClassAttachButtonLayout", true, GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_PANEL_LAYOUT);

		componentList.add(layoutConfig);
		tradeClassInformationAttachButton(componentList);
	}

	private void tradeClassInformationAttachButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmTradeAttachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"tradeClassAttachButton", true, "tradeClassAttachButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		cmTradeAttachButtonConfig.setComponentName("Attach");
		cmTradeAttachButtonConfig.setAuthorizationIncluded(true);

		componentList.add(cmTradeAttachButtonConfig);

		List<GtnUIFrameWorkActionConfig> cmTradeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		validAction.addActionParameter(GtnFrameworkCompanyMasterTradeClassParentTabValidation.class.getName());
		cmTradeActionConfigList.add(validAction);
		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnUIFrameworkCompanyMasterAttachAction.class.getName());
		actionParameter.add(GtnFrameworkCompanyStringContants.TRADE_CLASS_ATTACH_RESULT_TABLE);
		actionParameter.add(null);
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.TRADE_CLASS_NAME,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_START_DATE,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_END_DATE));
		actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.TRADE_CLASS_NAME,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_START_DATE,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_END_DATE));
		actionParameter.add(Arrays.asList(String.class, Date.class, Date.class));
		actionParameter.add(GtnFrameworkCompanyStringContants.TRADE_CLASS_NAME);
		actionParameter.add("CompanyTradeAttach");
		selectAction.setActionParameterList(actionParameter);
		cmTradeActionConfigList.add(selectAction);
		cmTradeAttachButtonConfig.setGtnUIFrameWorkActionConfigList(cmTradeActionConfigList);
	}

	private void tradeClassResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("tradeClassResultPanel", true,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_TAB);
		panel.setComponentName("Results");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		tradeClassResultLayout(componentList);
	}

	private void tradeClassResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("tradeClassResultLayout",
				true, "tradeClassResultPanel");
		layoutConfig.setComponentWidth("100%");
		componentList.add(layoutConfig);

		tradeClassResultDataTable(componentList);
	}

	private void tradeClassResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmTradeAttachResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.TRADE_CLASS_ATTACH_RESULT_TABLE, true, "tradeClassResultLayout",
				GtnUIFrameworkComponentType.DATATABLE);
		cmTradeAttachResultConfig.setAuthorizationIncluded(true);
		cmTradeAttachResultConfig.setComponentName("Results");
		cmTradeAttachResultConfig.setComponentHight("300px");
		cmTradeAttachResultConfig.setComponentWidth("100%");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		cmTradeAttachResultConfig.setComponentStyle(tableStyle);

		componentList.add(cmTradeAttachResultConfig);

		GtnUIFrameworkPagedTableConfig cmTradeAttachResults = new GtnUIFrameworkPagedTableConfig();
		cmTradeAttachResultConfig.setGtnPagedTableConfig(cmTradeAttachResults);
		cmTradeAttachResults.setEditable(true);
		cmTradeAttachResults.setFilterBar(true);
		cmTradeAttachResults.setSelectable(true);
		cmTradeAttachResults.setTableColumnDataType(new Class[] { String.class, Date.class, Date.class, String.class,
				Date.class, String.class, Date.class });
		cmTradeAttachResults.setTableVisibleHeader(new String[] { "Trade Class", "Trade Class Start Date",
				"Trade Class End Date", "Created By", "Created Date", "Modified By", "Modified Date" });
		cmTradeAttachResults.setTableColumnMappingId(new Object[] { GtnFrameworkCompanyStringContants.TRADE_CLASS_NAME,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_START_DATE,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_END_DATE, "createdBy", "createdDate", "modifiedBy",
				"modifiedDate" });

		cmTradeAttachResults.setExtraColumn(new Object[] { "tradeClassSid" });
		cmTradeAttachResults.setEditableColumnList(Arrays.asList(GtnFrameworkCompanyStringContants.TRADE_CLASS_NAME,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_START_DATE,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_END_DATE, "createdBy", "createdDate", "modifiedBy",
				"modifiedDate"));

		cmTradeAttachResults
				.setEditableField(createTableFieldFactoryComponents(cmTradeAttachResults.getEditableColumnList()));

	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			if (GtnFrameworkCompanyStringContants.getCmTradeTextfieldPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.TEXTBOX;
			} else if (GtnFrameworkCompanyStringContants.getCmTradeDatefieldPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
			}
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			boolean isReadOnly = !GtnFrameworkCompanyStringContants.getCmTradeEditablefieldList().contains(propertyId);
			if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.TEXTBOX)) {
				GtnUIFrameworkTextBoxConfig textConfigTradeClassTab = new GtnUIFrameworkTextBoxConfig();
				textConfigTradeClassTab.setReadOnly(isReadOnly);
				fieldConfig.setGtnTextBoxConfig(textConfigTradeClassTab);
			}

			else if (gtnUIFrameworkComponentType.equals(GtnUIFrameworkComponentType.DATEFIELD)) {
				fieldConfig.setEnable(!isReadOnly);
			}
			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	private void tradeClassInformationRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"tradeClassRemoveButtonLayout", true, GtnFrameworkCompanyStringContants.TRADE_CLASS_TAB);

		componentList.add(layoutConfig);
		tradeClassInformationRemoveButton(componentList);
		addExcelButtonComponent(componentList);

	}

	private void tradeClassInformationRemoveButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"tradeClassRemoveButton", true, "tradeClassRemoveButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName("Remove");
		attachButtonConfig.setAuthorizationIncluded(true);

		componentList.add(attachButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object removeValidationCustom = GtnFrameworkCompanyClassContants.IDENTIFIER_REMOVE_VALIDATION_CUSTOM;
		alertActionConfig.setActionParameterList(
				Arrays.asList(removeValidationCustom, GtnFrameworkCompanyStringContants.TRADE_CLASS_ATTACH_RESULT_TABLE,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_TRADECLASS_ONE_REMOVE,
						GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_TRADECLASS_REMOVE));
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG_TRADECLASS_REMOVE);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.REMOVE_ACTION);

		selectAction.addActionParameter(GtnFrameworkCompanyStringContants.TRADE_CLASS_ATTACH_RESULT_TABLE);
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
				GtnFrameworkCompanyStringContants.TRADE_CLASS_TAB + "excelButton", true,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_TAB, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"CompanyTradeClass", true, GtnFrameworkCompanyStringContants.TRADE_CLASS_ATTACH_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setTitleNeeded(true);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}
}
