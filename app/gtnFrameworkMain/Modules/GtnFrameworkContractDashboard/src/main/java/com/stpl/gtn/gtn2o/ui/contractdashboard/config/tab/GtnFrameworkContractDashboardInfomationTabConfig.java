package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import static com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE;
import static com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkInfoTabLevelValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkInfoTabViewValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPopulateFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkContractInformationTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardInfomationTabConfig {

	private static final String DEC_HEADER_PENDING_HEADER_CURRENT_CSS_LAYOUT = "Dec_Header_Pending_Header_Current_CssLayout";
	private static final String DEC_DETAIL_PENDING_DETAIL_CURRENT_CSS_LAYOUT = "Dec_Detail_Pending_Detail_Current_CssLayout";
	private static final String RESULT_HEADER_HISTORY_DETAIL_HISTORY_TABLE_LAYOUT = "Result_Header_History_Detail_History_TableLayout";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdInformationTabPrefix = mainNamspacePrefix + "DIT";
		GtnUIFrameworkTabConfig cdInformationTabConfig = new GtnUIFrameworkTabConfig();
		cdInformationTabConfig.setComponentId(cdInformationTabPrefix + "contactHeaderTab");
		cdInformationTabConfig.setTabCaption("Contract Header");
		List<GtnUIFrameworkComponentConfig> cdInformationComponentList = new ArrayList<>();
		cdInformationTabConfig.setTabLayoutComponentConfigList(cdInformationComponentList);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdInformationTabConfig.getComponentId(), true, cdInformationTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		cdInformationComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addContractHeaderDetailDecissionLayout(cdInformationComponentList, cdInformationTabPrefix,
				gtnLayoutConfig.getComponentId());
		addContractHeaderDetailFieldPanel(cdInformationComponentList, cdInformationTabPrefix,
				gtnLayoutConfig.getComponentId(), componentIdList);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkContractInformationTabLoadAction.class.getName());
		customAction.addActionParameter(componentIdList);
		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(customAction);
		return cdInformationTabConfig;
	}

	private void addContractHeaderDetailDecissionLayout(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdInformationTabPrefix + "HeaderDetailDecissionLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cdInformationComponentList.add(gtnLayoutConfig);

		addDecissionLevel(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId());
		addDecissionView(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addContractHeaderDetailFieldPanel(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdInformationTabPrefix + "HeaderDetailFieldPanel", true, parent);
		cdInformationComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdInformationTabPrefix + "verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdInformationComponentList.add(gtnLayoutConfig);

		addHeaderFieldComponent(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDeatilsFieldComponent(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		contractTabHistoryDataTableComponent(cdInformationComponentList, cdInformationTabPrefix,
				gtnLayoutConfig.getComponentId());
	}

	private void addDecissionLevel(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent) {
		String componentId = cdInformationTabPrefix + "DecissionLevel";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig levelComponentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		levelComponentConfig.setAuthorizationIncluded(true);
		levelComponentConfig.setComponentName("Level:");
		levelComponentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdInformationComponentList.add(levelComponentConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.HEADER, GtnWsContractDashboardContants.DETAIL));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.HEADER);

		levelComponentConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		visibleAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		visibleAction
				.addActionParameter(Arrays.asList(cdInformationTabPrefix + DEC_HEADER_PENDING_HEADER_CURRENT_CSS_LAYOUT,
						cdInformationTabPrefix + DEC_DETAIL_PENDING_DETAIL_CURRENT_CSS_LAYOUT,
						cdInformationTabPrefix + RESULT_HEADER_HISTORY_DETAIL_HISTORY_TABLE_LAYOUT));
		visibleAction.addActionParameter("0");
		visibleAction.addActionParameter("0");

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkInfoTabLevelValueChangeAction.class.getName());
		customAction.addActionParameter(cdInformationTabPrefix + "DecissionView");
		customAction.addActionParameter(GtnWsContractDashboardContants.HISTORY);
		customAction.addActionParameter(GtnWsContractDashboardContants.CURRENT);
		customAction.addActionParameter(visibleAction);
		customAction.addActionParameter(cdInformationTabPrefix + RESULT_HEADER_HISTORY_DETAIL_HISTORY_TABLE_LAYOUT);
		levelComponentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addDecissionView(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent) {
		String componentId = cdInformationTabPrefix + "DecissionView";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig viewComponentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		viewComponentConfig.setAuthorizationIncluded(true);
		viewComponentConfig.setComponentName("View:");
		viewComponentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdInformationComponentList.add(viewComponentConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.HISTORY,
				GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.PENDING));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.CURRENT);

		viewComponentConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkInfoTabViewValueChangeAction.class.getName());
		customAction
				.addActionParameter(Arrays.asList(cdInformationTabPrefix + DEC_HEADER_PENDING_HEADER_CURRENT_CSS_LAYOUT,
						cdInformationTabPrefix + DEC_DETAIL_PENDING_DETAIL_CURRENT_CSS_LAYOUT,
						cdInformationTabPrefix + RESULT_HEADER_HISTORY_DETAIL_HISTORY_TABLE_LAYOUT));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		customAction.addActionParameter(cdInformationTabPrefix + "DecissionLevel");
		viewComponentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addHeaderFieldComponent(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdInformationTabPrefix + DEC_HEADER_PENDING_HEADER_CURRENT_CSS_LAYOUT, true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdInformationComponentList.add(gtnLayoutConfig);
		addContractId(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addContractNo(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addContractName(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addContractType(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addContractStatus(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDocumentType(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addContractStartDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addContractEndDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDocumentClass(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCompanyName(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addTradeClass(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addTerm(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addTradingPartner(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addReNegotiationStartDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addReNegotiationEndDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceProtectionStartDate(cdInformationComponentList, cdInformationTabPrefix,
				gtnLayoutConfig.getComponentId(), componentIdList);
		addPriceProtectionEndDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addManufacturerNo(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void addContractId(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_ContractID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdInformationContractIdConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdInformationContractIdConfig.setAuthorizationIncluded(true);
		cdInformationContractIdConfig.setComponentName("Contract ID");
		cdInformationContractIdConfig.setEnable(false);
		cdInformationContractIdConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(cdInformationContractIdConfig);
		componentIdList.add(cdInformationContractIdConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdInformationContractIdValConfig = new GtnUIFrameworkValidationConfig();
		cdInformationContractIdValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdInformationContractIdValConfig.setAttachLengthValidatior(true);
		cdInformationContractIdValConfig.setMinSize(0);
		cdInformationContractIdValConfig.setMaxLength(38);
		cdInformationContractIdValConfig
				.setLengthValidationMessage("Contract ID length should be less than 38 characters");
		cdInformationContractIdConfig.setGtnUIFrameworkValidationConfig(cdInformationContractIdValConfig);
	}

	private void addContractNo(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_ContractNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdInformationContractNoConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdInformationContractNoConfig.setAuthorizationIncluded(true);
		cdInformationContractNoConfig.setComponentName("Contract No");
		cdInformationContractNoConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(cdInformationContractNoConfig);
		componentIdList.add(cdInformationContractNoConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdInformationContractNoValConfig = new GtnUIFrameworkValidationConfig();
		cdInformationContractNoValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdInformationContractNoValConfig.setAttachLengthValidatior(true);
		cdInformationContractNoValConfig.setMinSize(0);
		cdInformationContractNoValConfig.setMaxLength(50);
		cdInformationContractNoValConfig
				.setLengthValidationMessage("Contract No length should be less than 50 characters");

		cdInformationContractNoConfig.setGtnUIFrameworkValidationConfig(cdInformationContractNoValConfig);
	}

	private void addContractName(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdInformationTabPrefix + "Text_ContractName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdInformationContractNameConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdInformationContractNameConfig.setAuthorizationIncluded(true);
		cdInformationContractNameConfig.setComponentName("Contract Name");
		cdInformationContractNameConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(cdInformationContractNameConfig);
		componentIdList.add(cdInformationContractNameConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdInformationContractNameValConfig = new GtnUIFrameworkValidationConfig();
		cdInformationContractNameValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdInformationContractNameValConfig.setAttachLengthValidatior(true);
		cdInformationContractNameValConfig.setMinSize(0);
		cdInformationContractNameValConfig.setMaxLength(100);
		cdInformationContractNameValConfig
				.setLengthValidationMessage("Contract Name length should be less than 100 characters");

		cdInformationContractNameConfig.setGtnUIFrameworkValidationConfig(cdInformationContractNameValConfig);
	}

	private void addContractType(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_ContractType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig contractTypeComponentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		contractTypeComponentConfig.setAuthorizationIncluded(true);
		contractTypeComponentConfig.setComponentName("Contract Type");
		contractTypeComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(contractTypeComponentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CONTRACT_TYPE);
		contractTypeComponentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(contractTypeComponentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		contractTypeComponentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractStatus(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_ContractStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Contract Status");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addDocumentType(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_DocumentType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Document Type");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.DOCUMENT_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());

	}

	private void addContractStartDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdInformationTabPrefix + "ContractStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Contract Start Date");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig dateValidationAction = new GtnUIFrameWorkActionConfig();
		dateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValidationAction.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		dateValidationAction.addActionParameter(componentConfig.getComponentId());
		dateValidationAction.addActionParameter(new ArrayList<String>());
		dateValidationAction.addActionParameter(Arrays.asList(cdInformationTabPrefix + "ContractEndDate"));
		dateValidationAction.addActionParameter("Start Date");
		dateValidationAction.addActionParameter("Start");
		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(dateValidationAction);
		componentConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addContractEndDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "ContractEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Contract End Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameWorkActionConfig dateValidationAction = new GtnUIFrameWorkActionConfig();
		dateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValidationAction.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		dateValidationAction.addActionParameter(componentConfig.getComponentId());
		dateValidationAction.addActionParameter(Arrays.asList(cdInformationTabPrefix + "ContractStartDate"));
		dateValidationAction.addActionParameter(new ArrayList<String>());
		dateValidationAction.addActionParameter("End Date");
		dateValidationAction.addActionParameter("End");
		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(dateValidationAction);
		componentConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addDocumentClass(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_DocumentClass";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Document Class");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.DOCUMENT_CLASS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_CompanyName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdInformationCompanyNameConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		cdInformationCompanyNameConfig.setAuthorizationIncluded(true);
		cdInformationCompanyNameConfig.setComponentName("Company Name");
		cdInformationComponentList.add(cdInformationCompanyNameConfig);
		componentIdList.add(cdInformationCompanyNameConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		cdInformationCompanyNameConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameWorkActionConfig cdInformationCompanyNamePopupAction = new GtnUIFrameWorkActionConfig();
		cdInformationCompanyNamePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		cdInformationCompanyNamePopupAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_TP_VIEW);
		cdInformationCompanyNamePopupAction.addActionParameter("Company Name Look Up");
		cdInformationCompanyNamePopupAction.addActionParameter("70%");
		cdInformationCompanyNamePopupAction.addActionParameter(null);
		cdInformationCompanyNamePopupAction.addActionParameter(null);
		cdInformationCompanyNamePopupAction.addActionParameter(
				Arrays.asList(cdInformationCompanyNameConfig.getComponentId(), Arrays.asList("companyName"),
						Arrays.asList(cdInformationCompanyNameConfig.getComponentId()), null));
		cdInformationCompanyNameConfig.addGtnUIFrameWorkActionConfig(cdInformationCompanyNamePopupAction);
	}

	private void addTradeClass(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_TradeClass";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Trade Class");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CONTRACT_TRADE_CLASS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addTerm(List<GtnUIFrameworkComponentConfig> cdInformationComponentList, String cdInformationTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_Term";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Term");
		componentConfig.setEnable(false);
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addTradingPartner(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_TradingPartner";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdInformationTradingPartnerConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		cdInformationTradingPartnerConfig.setAuthorizationIncluded(true);
		cdInformationTradingPartnerConfig.setComponentName("Trading Partner");
                cdInformationTradingPartnerConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdInformationComponentList.add(cdInformationTradingPartnerConfig);
		componentIdList.add(cdInformationTradingPartnerConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		cdInformationTradingPartnerConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameWorkActionConfig cdInformationTradingPartnerPopupAction = new GtnUIFrameWorkActionConfig();
		cdInformationTradingPartnerPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		cdInformationTradingPartnerPopupAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_TP_VIEW);
		cdInformationTradingPartnerPopupAction.addActionParameter("Trading Partner Look Up");
		cdInformationTradingPartnerPopupAction.addActionParameter("70%");
		cdInformationTradingPartnerPopupAction.addActionParameter(null);
		cdInformationTradingPartnerPopupAction.addActionParameter(null);
		cdInformationTradingPartnerPopupAction.addActionParameter(
				Arrays.asList(cdInformationTradingPartnerConfig.getComponentId(), Arrays.asList("companyName"),
						Arrays.asList(cdInformationTradingPartnerConfig.getComponentId()), null));
		cdInformationTradingPartnerConfig.addGtnUIFrameWorkActionConfig(cdInformationTradingPartnerPopupAction);

	}

	private void addReNegotiationStartDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "ReNegotiationStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Re-Negotiation Start Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addReNegotiationEndDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "ReNegotiationEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Re-Negotiation End Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPriceProtectionStartDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "PriceProtectionStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Protection Start Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPriceProtectionEndDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "PriceProtectionEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Protection End Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addManufacturerNo(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_ManufacturerNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Manufacturer No");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.COMPANY_MENUFACTURER);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addDeatilsFieldComponent(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdInformationTabPrefix + DEC_DETAIL_PENDING_DETAIL_CURRENT_CSS_LAYOUT, true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayoutConfig.setVisible(false);
		cdInformationComponentList.add(gtnLayoutConfig);

		addInsideOwner(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addInsidePhone(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addInsideAuthor(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addInsideAdditional(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addInsideAdditionalName(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addInsideAdditionalPhone(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOutsideOwner(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOutsidePhone(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOutsideAuthor(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOutsideAdditional(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOutsideAdditionalName(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOutsideAdditionalPhone(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addAdvanceNoticeDays(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addAffiliatedContractInfo(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addShippingTerms(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addProposalStartDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addProposalEndDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOriginalStartDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addOriginalEndDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addAwardStatus(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addLastUpdatedDate(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceEscalationClause(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addExemptFromLowPrice(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceResetIndicator(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCancellationClause(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addMostFavoredNation(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCategory(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCurrency(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addMinimumOrder(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPaymentTerms(cdInformationComponentList, cdInformationTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void addInsideOwner(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_InsideOwner";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Inside Owner");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addInsidePhone(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_InsidePhone";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Inside Phone");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addInsideAuthor(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_InsideAuthor";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Inside Author");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addInsideAdditional(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_InsideAdditional";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Inside Additional");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addInsideAdditionalName(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_InsideAdditionalName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Inside Additional Name");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addInsideAdditionalPhone(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_InsideAdditionalPhone";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Inside Additional Phone");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOutsideOwner(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_OutsideOwner";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Outside Owner");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOutsidePhone(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_OutsidePhone";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Outside Phone");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOutsideAuthor(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_OutsideAuthor";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Outside Author");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOutsideAdditional(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_OutsideAdditional";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Outside Additional");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOutsideAdditionalName(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_OutsideAdditionalName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Outside Additional Name");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOutsideAdditionalPhone(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_OutsideAdditionalPhone";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Outside Additional Phone");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addAdvanceNoticeDays(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_AdvanceNoticeDays";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Advance Notice Days");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addAffiliatedContractInfo(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_AffiliatedContractInfo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Affiliated Contract Info");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addShippingTerms(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_ShippingTerms";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Shipping Terms");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addProposalStartDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "ProposalStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Proposal Start Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addProposalEndDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "ProposalEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Proposal End Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOriginalStartDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "OriginalStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Original Start Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addOriginalEndDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "OriginalEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Original End Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addAwardStatus(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_AwardStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Award Status");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addLastUpdatedDate(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "LastUpdatedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Last Updated Date");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPriceEscalationClause(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_PriceEscalationClause";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Escalation Clause");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addExemptFromLowPrice(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_ExemptFromLowPrice";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Exempt From Low Price");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPriceResetIndicator(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_PriceResetIndicator";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Reset Indicator");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PRICE_RESET_INDICATOR);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCancellationClause(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_CancellationClause";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Cancellation Clause");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addMostFavoredNation(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_MostFavoredNation";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Most Favored Nation");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_Category";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Category");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCurrency(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_Currency";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Currency");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addMinimumOrder(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Text_MinimumOrder";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Minimum Order");
		cdInformationComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPaymentTerms(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdInformationTabPrefix + "Combo_PaymentTerms";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Payment Terms");
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PAYMENT_TERMS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void contractTabHistoryDataTableComponent(List<GtnUIFrameworkComponentConfig> cdInformationComponentList,
			String cdInformationTabPrefix, String parent) {
		String componentId = cdInformationTabPrefix + "Result_Header_History_Detail_History_Table";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdInformationComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdInformationComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdInformationHistoryDataTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdInformationHistoryDataTable);
		cdInformationHistoryDataTable.setFilterBar(true);
		cdInformationHistoryDataTable.setSelectable(true);
		cdInformationHistoryDataTable.setSinkItemPerPageWithPageLength(false);
		cdInformationHistoryDataTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getCdHistoryColumnType());
		cdInformationHistoryDataTable.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getCdHistoryHeader());
		cdInformationHistoryDataTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getCdHistoryColumn());
		cdInformationHistoryDataTable
				.setCountUrl(GTN_WS_IFP_SERVICE + GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE);
		cdInformationHistoryDataTable
				.setResultSetUrl(GTN_WS_IFP_SERVICE + GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE);

	}
}
