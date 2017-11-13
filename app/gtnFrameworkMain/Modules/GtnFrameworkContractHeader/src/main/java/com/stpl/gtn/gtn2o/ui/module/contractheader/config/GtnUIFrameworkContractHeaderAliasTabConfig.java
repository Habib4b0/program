package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderAttachAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation.GtnUIFrameworkContractHeaderAliasAttachValidationAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkContractHeaderAliasTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addAliasTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TAB, false, null);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		layoutConfig.getGtnLayoutConfig().setMargin(true);
		layoutConfig.getGtnLayoutConfig().setSpacing(true);
		layoutConfig.setMargin(true);
		layoutConfig.setSpacing(true);
		componentList.add(layoutConfig);
		contractAliasInformationPanel(componentList);
		contractAliasResultPanel(componentList);
		contractAliasRemoveButtonLayout(componentList);

	}

	private void contractAliasInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("contractAliasInformationPanel", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TAB);
		panel.setComponentName("Identifier Information");
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig("contractAliasInformationPanelLayout", true, "contractAliasInformationPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		layoutConfig.getGtnLayoutConfig().setMargin(true);
		layoutConfig.getGtnLayoutConfig().setSpacing(true);
		layoutConfig.setMargin(true);
		layoutConfig.setSpacing(true);
		componentList.add(layoutConfig);

		contractAliasInformationLayout(componentList);
		contractAliasAttachButtonLayout(componentList);
	}

	private void contractAliasInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT, true,
				"contractAliasInformationPanelLayout", GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);

		componentList.add(layoutConfig);
		contractAliasFields(componentList);
	}

	private void contractAliasFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addTradingPartnerNo(componentList);
		addContractAliasNo(componentList);
		addStartDate(componentList);
		addContractAliasType(componentList);
		addContractAliasName(componentList);
		addEndDate(componentList);
	}

	private void addTradingPartnerNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractAliasTradingPartnerLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractAliasConfig = configProvider.getUIFrameworkComponentConfig(
				"aliasTabTradingPartner", true, "contractAliasTradingPartnerLayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		contractAliasConfig.setAuthorizationIncluded(true);
		contractAliasConfig.setComponentName("Trading Partner");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		contractAliasConfig.setGtnTextBoxConfig(textboxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("aliasTabTpView");
		popupActionConfig.addActionParameter("Trading Partner Look Up");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		actionConfigList.add(popupActionConfig);
		contractAliasConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(contractAliasConfig);

	}

	private void addContractAliasNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractAliasContractAliasNolayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractAliasConfig = configProvider.getUIFrameworkComponentConfig(
				"contractAliasContractAliasNo", true, "contractAliasContractAliasNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		contractAliasConfig.setAuthorizationIncluded(true);
		contractAliasConfig.setComponentName("Contract Alias No");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, true, true);

		contractAliasConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(contractAliasConfig);
		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		contractAliasConfig.setGtnUIFrameworkValidationConfig(valConfig);

	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractAliasStartDatelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractAliasConfig = configProvider.getUIFrameworkComponentConfig(
				"contractAliasStartDate", true, "contractAliasStartDatelayout", GtnUIFrameworkComponentType.DATEFIELD);
		contractAliasConfig.setAuthorizationIncluded(true);
		contractAliasConfig.setComponentName("Start Date");
		contractAliasConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, true, true));

		componentList.add(contractAliasConfig);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		contractAliasConfig.setGtnUIFrameworkValidationConfig(valConfig);

	}

	private void addEndDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("contractAliasEndDatelayout",
				true, GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractAliasConfig = configProvider.getUIFrameworkComponentConfig(
				"contractAliasEndDate", true, "contractAliasEndDatelayout", GtnUIFrameworkComponentType.DATEFIELD);
		contractAliasConfig.setAuthorizationIncluded(true);
		contractAliasConfig.setComponentName("End Date");
		contractAliasConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, true, true));

		componentList.add(contractAliasConfig);

	}

	private void addContractAliasType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractAliasContractAliasTypelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemStatus = configProvider.getUIFrameworkComponentConfig(
				"contractAliasContractAliasType", true, "contractAliasContractAliasTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		itemStatus.setAuthorizationIncluded(true);
		itemStatus.setComponentName("Contract Alias Type");

		componentList.add(itemStatus);

		GtnUIFrameworkComboBoxConfig itemStatusConfig = configProvider.getComboBoxConfig("CONTRACT_ALIAS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		itemStatus.setGtnComboboxConfig(itemStatusConfig);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		itemStatus.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractAliasName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractAliasContractAliasNamelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractAliasConfig = configProvider.getUIFrameworkComponentConfig(
				"contractAliasContractAliasName", true, "contractAliasContractAliasNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		contractAliasConfig.setAuthorizationIncluded(true);
		contractAliasConfig.setComponentName("Contract Alias Name");
		contractAliasConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, true, true));

		componentList.add(contractAliasConfig);

	}

	private void contractAliasAttachButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				"identifierAttachButtonLayout", true, GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TAB);
		componentList.add(layoutConfig);
		contractAliasAttachButton(componentList);
	}

	private void contractAliasAttachButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"identifierAttachButton", true, "identifierAttachButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName("Attach");

		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkContractHeaderAliasAttachValidationAction.class.getName());
		actionConfigList.add(customAction);
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnUIFrameworkContractHeaderAttachAction.class.getName());
		selectAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE);
		selectAction.addActionParameter(null);
		selectAction.addActionParameter(Arrays.asList(new String[] { "contractAliasNo", "aliasStartDate",
				"aliasEndDate", "contractAliasType", "contractAliasName" }));
		selectAction.addActionParameter(
				Arrays.asList(new String[] { "contractAliasContractAliasNo", "contractAliasStartDate",
						"contractAliasEndDate", "contractAliasContractAliasType", "contractAliasContractAliasName" }));
		selectAction
				.addActionParameter(Arrays.asList(String.class, Date.class, Date.class, String.class, String.class));

		actionConfigList.add(selectAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void contractAliasResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("contractAliasResultPanel", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TAB);
		panel.setComponentName("Results");
		componentList.add(panel);
		contractAliasResultLayout(componentList);
	}

	private void contractAliasResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig("contractAliasResultLayout",
				true, "contractAliasResultPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		layoutConfig.getGtnLayoutConfig().setSpacing(true);
		layoutConfig.getGtnLayoutConfig().setMargin(true);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		componentList.add(layoutConfig);

		identifierResultDataTable(componentList);
	}

	private void identifierResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE, true,
				"contractAliasResultLayout", GtnUIFrameworkComponentType.DATATABLE);
		attachResultConfig.setAuthorizationIncluded(true);
		attachResultConfig.setComponentName("Results");
		attachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		attachResultConfig.setComponentStyle(tableStyleList);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		attachResultConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setPageLength(5);

		attachResults.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, Date.class, Date.class });
		attachResults.setTableVisibleHeader(new String[] { "Contract Alias No", "Contract Alias Name",
				"Contract Alias Type", "Alias Start Date", "Alias End Date" });
		attachResults.setTableColumnMappingId(new Object[] { "contractAliasNo", "contractAliasName",
				"contractAliasType", "aliasStartDate", "aliasEndDate" });
		attachResults.setExtraColumn(
				new Object[] { "contractAliasTypeSid", "contractAliasTradingPartnerSid", "contractAliasMasterSid" });

	}

	private void contractAliasRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnUIFrameworkContractHeaderStringContants.IDENTIFIER_REMOVE_BUTTON_LAYOUT, true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TAB);

		componentList.add(layoutConfig);
		contractAliasRemoveButton(componentList);
	}

	private void contractAliasRemoveButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"identifierRemoveButton", true,
				GtnUIFrameworkContractHeaderStringContants.IDENTIFIER_REMOVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName("Remove");
		componentList.add(attachButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkCommonStringConstants.ERROR);
		alertParamsList
				.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_ALIAS_ITEM_SELECT_VALIDATION);

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.REMOVE_ACTION);

		selectAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE);
		actionConfigList.add(selectAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		addExcelButtonComponent(componentList);

	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"aliasTabExcelButton", true, GtnUIFrameworkContractHeaderStringContants.IDENTIFIER_REMOVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"Contract Alias", true, GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE,
				false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}
}
