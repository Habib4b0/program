package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import static com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE;
import static com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyChangeIndicatorAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyPopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkUpdateSelectedRecordInTempTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkCompaniesTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkFieldEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPopulateFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnUIFrameworkFocusAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkCompaniesTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardCompaniesTabConfig {

	private static final String TEXT_PCFPID = "Text_PCFPID";
	private static final String TEXT_PCFP_NAME = "Text_PCFPName";
	private static final String CFP_STATUS = "cfpStatus";
	private static final String CHECK_RECORD_ID = "checkRecordId";
	private static final String DATE_FORMAT = "MM/dd/YYYY";
	private static final String DECISSION_LEVEL = "DecissionLevel";

	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdCompaniesTabPrefix = mainNamspacePrefix + "CT";
		GtnUIFrameworkTabConfig cdCompaniesTabConfig = new GtnUIFrameworkTabConfig();
		cdCompaniesTabConfig.setComponentId(cdCompaniesTabPrefix + "companiesTab");
		cdCompaniesTabConfig.setTabCaption("Companies");
		List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList = new ArrayList<>();
		cdCompaniesTabConfig.setTabLayoutComponentConfigList(cdCompaniesComponentList);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdCompaniesTabConfig.getComponentId(), true, cdCompaniesTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		cdCompaniesComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addContractHeaderDetailDecissionLayout(cdCompaniesComponentList, cdCompaniesTabPrefix,
				gtnLayoutConfig.getComponentId());
		addContractHeaderFieldPanel(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDeatilsLayout(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId());

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompaniesTabLoadAction.class.getName());
		customAction.addActionParameter(cdCompaniesTabPrefix + DECISSION_LEVEL);
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE);
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(cdCompaniesTabPrefix + "MassUpdatePanel");
		customAction.addActionParameter(cdCompaniesTabPrefix + "removeBtn");
		customAction.addActionParameter(cdCompaniesTabPrefix + "excelButton");

		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(customAction);
		return cdCompaniesTabConfig;
	}

	private void addContractHeaderDetailDecissionLayout(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdCompaniesTabPrefix + "HeaderDetailDecissionLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		addDecisionLevel(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId());
		addDecissionView(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addContractHeaderFieldPanel(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdCompaniesTabPrefix + "Decission_Header_FieldPanel", true, parent);
		cdCompaniesComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdCompaniesTabPrefix + "verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		addHeaderFieldComponent(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		companiesTabHistoryDataTableComponent(cdCompaniesComponentList, cdCompaniesTabPrefix,
				gtnLayoutConfig.getComponentId());
	}

	private void addDecisionLevel(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		String componentId = cdCompaniesTabPrefix + DECISSION_LEVEL;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig decissionLevelConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		decissionLevelConfig.setAuthorizationIncluded(true);
		decissionLevelConfig.setComponentName("Level:");
		decissionLevelConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdCompaniesComponentList.add(decissionLevelConfig);

		GtnUIFrameworkOptionGroupConfig decisionLevelOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		decisionLevelOptionConfig.setValuesFromService(false);
		decisionLevelOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.HEADER, GtnWsContractDashboardContants.DETAIL));
		decisionLevelOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.HEADER);

		decissionLevelConfig.setGtnUIFrameworkOptionGroupConfig(decisionLevelOptionConfig);

		GtnUIFrameWorkActionConfig decissionLevelValueChangeAction = new GtnUIFrameWorkActionConfig();
		decissionLevelValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		decissionLevelValueChangeAction
				.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		decissionLevelValueChangeAction.addActionParameter(Arrays.asList(
				cdCompaniesTabPrefix + "Decission_Header_FieldPanel", cdCompaniesTabPrefix + "Decission_Header_View",
				cdCompaniesTabPrefix + "_" + GtnWsContractDashboardContants.DETAIL + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT));
		decissionLevelValueChangeAction.addActionParameter("0");
		decissionLevelValueChangeAction.addActionParameter("0");
		decissionLevelConfig.addGtnUIFrameWorkActionConfig(decissionLevelValueChangeAction);
		GtnUIFrameWorkActionConfig tabLoadCustomAction = new GtnUIFrameWorkActionConfig();
		tabLoadCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadCustomAction.addActionParameter(GtnFrameworkCompaniesTabLoadAction.class.getName());
		tabLoadCustomAction.addActionParameter(cdCompaniesTabPrefix + DECISSION_LEVEL);
		decissionLevelConfig.addGtnUIFrameWorkActionConfig(tabLoadCustomAction);
	}

	private void addDecissionView(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		String componentId = cdCompaniesTabPrefix + "Decission_Header_View";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig decisionViewConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		decisionViewConfig.setAuthorizationIncluded(true);
		decisionViewConfig.setComponentName("View:");
		decisionViewConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdCompaniesComponentList.add(decisionViewConfig);

		GtnUIFrameworkOptionGroupConfig decisionViewOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		decisionViewOptionConfig.setValuesFromService(false);
		decisionViewOptionConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.HISTORY,
				GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.PENDING));
		decisionViewOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.CURRENT);

		decisionViewConfig.setGtnUIFrameworkOptionGroupConfig(decisionViewOptionConfig);

		GtnUIFrameWorkActionConfig decisionViewValueChangeAction = new GtnUIFrameWorkActionConfig();
		decisionViewValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		decisionViewValueChangeAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		decisionViewValueChangeAction.addActionParameter(Arrays.asList(
				cdCompaniesTabPrefix + "CssLayout_" + GtnWsContractDashboardContants.CURRENT + "_"
						+ GtnWsContractDashboardContants.PENDING + "_",
				cdCompaniesTabPrefix + "_" + GtnWsContractDashboardContants.HISTORY + "_ResultTable"
						+ GtnFrameworkCommonStringConstants.LAYOUT));
		decisionViewValueChangeAction.addActionParameter("0");
		decisionViewValueChangeAction.addActionParameter("0");
		decisionViewConfig.addGtnUIFrameWorkActionConfig(decisionViewValueChangeAction);
	}

	private void addHeaderFieldComponent(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdCompaniesTabPrefix + "CssLayout_" + GtnWsContractDashboardContants.CURRENT + "_"
						+ GtnWsContractDashboardContants.PENDING + "_", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		addCfpId(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCfpNo(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCfpName(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCfpStatus(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCfpStartDate(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCfpEndDate(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCfpType(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);

		addCfpCategory(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCfpTradeClass(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCfpDesignation(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addParentCfpId(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addParentCfpName(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addSalesInclusion(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCreatedBy(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCreatedDate(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addModifiedBy(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addModifiedDate(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void addCfpId(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Text_CFPID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP ID");
		componentConfig.setEnable(false);
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCfpNo(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Text_CFPNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompaniesCfpNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdCompaniesCfpNoConfig.setAuthorizationIncluded(true);
		cdCompaniesCfpNoConfig.setComponentName("CFP No");
		cdCompaniesCfpNoConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdCompaniesComponentList.add(cdCompaniesCfpNoConfig);
		componentIdList.add(cdCompaniesCfpNoConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdCompaniesCfpNoValConfig = new GtnUIFrameworkValidationConfig();
		cdCompaniesCfpNoValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cdCompaniesCfpNoConfig.setGtnUIFrameworkValidationConfig(cdCompaniesCfpNoValConfig);
	}

	private void addCfpName(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, List<String> componentIdList) {

		String componentId = cdCompaniesTabPrefix + "Text_CFPName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompaniesCfpNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdCompaniesCfpNameConfig.setAuthorizationIncluded(true);
		cdCompaniesCfpNameConfig.setComponentName("CFP Name");
		cdCompaniesCfpNameConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdCompaniesComponentList.add(cdCompaniesCfpNameConfig);
		componentIdList.add(cdCompaniesCfpNameConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdCompaniesCfpNameValConfig = new GtnUIFrameworkValidationConfig();
		cdCompaniesCfpNameValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cdCompaniesCfpNameConfig.setGtnUIFrameworkValidationConfig(cdCompaniesCfpNameValConfig);
	}

	private void addCfpStatus(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Combo_CFPStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig contractCompanyCfpStatusConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		contractCompanyCfpStatusConfig.setAuthorizationIncluded(true);
		contractCompanyCfpStatusConfig.setComponentName("CFP Status");
		contractCompanyCfpStatusConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdCompaniesComponentList.add(contractCompanyCfpStatusConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		contractCompanyCfpStatusConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(contractCompanyCfpStatusConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		contractCompanyCfpStatusConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addCfpStartDate(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdCompaniesTabPrefix + "CFPStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP Start Date");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig contractStartDateValidation = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		contractStartDateValidation.addActionParameter("CDProcessView_DITContractStartDate");
		contractStartDateValidation.addActionParameter(-1);
		contractStartDateValidation.addActionParameter("Select CFP Start date After ?");
		contractStartDateValidation.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig contractStartDateBeforeValidation = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		contractStartDateValidation.addActionParameter("CDProcessView_DITContractEndDate");
		contractStartDateValidation.addActionParameter(1);
		contractStartDateValidation.addActionParameter("Select CFP Start date Before ?");
		contractStartDateValidation.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig dateValidationAction = new GtnUIFrameWorkActionConfig();
		dateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValidationAction.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		dateValidationAction.addActionParameter(componentConfig.getComponentId());
		dateValidationAction.addActionParameter(new ArrayList<String>());
		dateValidationAction.addActionParameter(Arrays.asList(cdCompaniesTabPrefix + "CFPEndDate"));
		dateValidationAction.addActionParameter("Start Date");
		dateValidationAction.addActionParameter("Start");

		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(dateValidationAction);
		dateFieldConfig.setValidationActionConfigList(
				Arrays.asList(contractStartDateValidation, contractStartDateBeforeValidation));
		componentConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addCfpEndDate(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "CFPEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP End Date");
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameWorkActionConfig contractEnddateValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		contractEnddateValidationAction.addActionParameter("CDProcessView_DITContractEndDate");
		contractEnddateValidationAction.addActionParameter(1);
		contractEnddateValidationAction.addActionParameter("Select CFP End date before ?");
		contractEnddateValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig contractEndDateAfterValidation = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		contractEndDateAfterValidation.addActionParameter("CDProcessView_DITContractStartDate");
		contractEndDateAfterValidation.addActionParameter(-1);
		contractEndDateAfterValidation.addActionParameter("Select CFP End date After ?");
		contractEndDateAfterValidation.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig dateValidationAction = new GtnUIFrameWorkActionConfig();
		dateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValidationAction.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		dateValidationAction.addActionParameter(componentConfig.getComponentId());
		dateValidationAction.addActionParameter(Arrays.asList(cdCompaniesTabPrefix + "CFPStartDate"));
		dateValidationAction.addActionParameter(new ArrayList<String>());
		dateValidationAction.addActionParameter("End Date");
		dateValidationAction.addActionParameter("End");
		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(dateValidationAction);
		dateFieldConfig.setValidationActionConfigList(
				Arrays.asList(contractEnddateValidationAction, contractEndDateAfterValidation));
		componentConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addCfpType(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Combo_CFPType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP Type");
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CFP_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCfpCategory(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Combo_CFPCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP Category");
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CFP_CATEGORY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCfpTradeClass(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Combo_CFPTradeClass";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP Trade Class");
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CFP_TRADE_CLASS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCfpDesignation(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Combo_CFPDesignation";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("CFP Designation");
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CFP_DESIGNATION);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkFieldEnableDisableAction.class.getName());
		customAction.addActionParameter(componentConfig.getComponentId());
		customAction.addActionParameter(true);
		customAction.addActionParameter("child");
		customAction.setFieldValues(
				Arrays.asList(cdCompaniesTabPrefix + TEXT_PCFPID, cdCompaniesTabPrefix + TEXT_PCFP_NAME));
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addParentCfpId(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + TEXT_PCFPID;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompaniesParentCfpIdConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		cdCompaniesParentCfpIdConfig.setAuthorizationIncluded(true);
		cdCompaniesParentCfpIdConfig.setComponentName("Parent CFP ID");
		cdCompaniesComponentList.add(cdCompaniesParentCfpIdConfig);
		componentIdList.add(cdCompaniesParentCfpIdConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig cdCompaniesParentCfpIdTextboxConfig = commonConfig.getTextBoxConfig(true, false,
				true);
		cdCompaniesParentCfpIdTextboxConfig.setReadOnly(true);
		cdCompaniesParentCfpIdTextboxConfig.setEnable(false);
		cdCompaniesParentCfpIdConfig.setGtnTextBoxConfig(cdCompaniesParentCfpIdTextboxConfig);

		GtnUIFrameWorkActionConfig cdCompaniesParentCfpIdPopupAction = new GtnUIFrameWorkActionConfig();
		cdCompaniesParentCfpIdPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		cdCompaniesParentCfpIdPopupAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_CFP_VIEW);
		cdCompaniesParentCfpIdPopupAction.addActionParameter("Company Family Plan Search");
		cdCompaniesParentCfpIdPopupAction.addActionParameter("70%");
		cdCompaniesParentCfpIdPopupAction.addActionParameter(null);
		cdCompaniesParentCfpIdPopupAction.addActionParameter(null);
		cdCompaniesParentCfpIdPopupAction.addActionParameter(Arrays.asList(
				cdCompaniesParentCfpIdConfig.getComponentId(), Arrays.asList("cfpId", "cfpName"),
				Arrays.asList(cdCompaniesTabPrefix + TEXT_PCFPID, cdCompaniesTabPrefix + TEXT_PCFP_NAME), null));
		cdCompaniesParentCfpIdConfig.addGtnUIFrameWorkActionConfig(cdCompaniesParentCfpIdPopupAction);
	}

	private void addParentCfpName(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + TEXT_PCFP_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompaniesParentCfpNameConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		cdCompaniesParentCfpNameConfig.setAuthorizationIncluded(true);
		cdCompaniesParentCfpNameConfig.setComponentName("Parent CFP Name");
		cdCompaniesComponentList.add(cdCompaniesParentCfpNameConfig);
		componentIdList.add(cdCompaniesParentCfpNameConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig cdCompaniesParentCfpNameTextboxConfig = commonConfig.getTextBoxConfig(true, false,
				true);
		cdCompaniesParentCfpNameTextboxConfig.setReadOnly(true);
		cdCompaniesParentCfpNameTextboxConfig.setEnable(false);
		cdCompaniesParentCfpNameConfig.setGtnTextBoxConfig(cdCompaniesParentCfpNameTextboxConfig);

		GtnUIFrameWorkActionConfig cdCompaniesParentCfpNamePopupAction = new GtnUIFrameWorkActionConfig();
		cdCompaniesParentCfpNamePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		cdCompaniesParentCfpNamePopupAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_CFP_VIEW);
		cdCompaniesParentCfpNamePopupAction.addActionParameter("Company Family Plan Search");
		cdCompaniesParentCfpNamePopupAction.addActionParameter("70%");
		cdCompaniesParentCfpNamePopupAction.addActionParameter(null);
		cdCompaniesParentCfpNamePopupAction.addActionParameter(null);
		cdCompaniesParentCfpNamePopupAction.addActionParameter(Arrays.asList(
				cdCompaniesParentCfpNameConfig.getComponentId(), Arrays.asList("cfpId", "cfpName"),
				Arrays.asList(cdCompaniesTabPrefix + TEXT_PCFPID, cdCompaniesTabPrefix + TEXT_PCFP_NAME), null));
		cdCompaniesParentCfpNameConfig.addGtnUIFrameWorkActionConfig(cdCompaniesParentCfpNamePopupAction);
	}

	private void addSalesInclusion(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Combo_SalesInclusion";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Sales Inclusion");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.LOCKED_STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		componentIdList.add(componentConfig.getComponentId());

	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Text_CreatedBy";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Created By");
		componentConfig.setEnable(false);
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "CreatedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Created Date");
		componentConfig.setEnable(false);
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "Text_ModifiedBy";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Modified By");
		componentConfig.setEnable(false);
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "ModifiedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Modified Date");
		componentConfig.setEnable(false);
		cdCompaniesComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void companiesTabHistoryDataTableComponent(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		String componentId = cdCompaniesTabPrefix + "_" + GtnWsContractDashboardContants.HISTORY + "_ResultTable";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdCompaniesResultsTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdCompaniesResultsTable);
		cdCompaniesResultsTable.setEditable(false);
		cdCompaniesResultsTable.setFilterBar(true);
		cdCompaniesResultsTable.setSelectable(true);
		cdCompaniesResultsTable.setPageLength(10);
		cdCompaniesResultsTable.setItemPerPage(10);
		cdCompaniesResultsTable.setSinkItemPerPageWithPageLength(false);
		cdCompaniesResultsTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getCompanyHistoryColumnType());
		cdCompaniesResultsTable.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getCompanyHistoryHeader());
		cdCompaniesResultsTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getCompanyHistoryColumn());
		cdCompaniesResultsTable.setCountUrl(GTN_WS_IFP_SERVICE + GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE);
		cdCompaniesResultsTable
				.setResultSetUrl(GTN_WS_IFP_SERVICE + GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE);
		cdCompaniesResultsTable.setModuleName("");

	}

	public void addDeatilsLayout(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(cdCompaniesTabPrefix + "_"
				+ GtnWsContractDashboardContants.DETAIL + "_" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdCompaniesComponentList.add(gtnLayoutConfig);
		massUpdatePanel(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId());
		reordLayout(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId());
		companiesResultPanel(cdCompaniesComponentList, gtnLayoutConfig.getComponentId());
		addRemoveButtonLayout(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdCompaniesTabPrefix + "MassUpdatePanel", true, parent);
		cdCompaniesComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdCompaniesTabPrefix + "MassUpdateLayout", true, gtnPanelConfig.getComponentId());
		cdCompaniesComponentList.add(gtnLayoutConfig);

		List<String> componentIdList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customCompaniesMassUpdateDisableAction = new GtnUIFrameWorkActionConfig();
		addMassCheck(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				customCompaniesMassUpdateDisableAction);

		addMassPopulateField(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPopulateButtonLayout(cdCompaniesComponentList, cdCompaniesTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addHiddenField(cdCompaniesComponentList, gtnLayoutConfig.getComponentId());
		customCompaniesMassUpdateDisableAction.setFieldValues(componentIdList);

	}

	private void addHiddenField(List<GtnUIFrameworkComponentConfig> componentList, String parent) {
		GtnUIFrameworkComponentConfig hiddenFieldComponentConfig = commonConfig
				.getHorizontalLayoutConfig(GtnFrameworkContractDashboardContants.HIDDEN_LAYOUT, true, parent);
		componentList.add(hiddenFieldComponentConfig);

		GtnUIFrameworkComponentConfig hiddenComponent = commonConfig.getUIFrameworkComponentConfig(
				GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT, true,
				hiddenFieldComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		hiddenComponent.setAuthorizationIncluded(true);

		hiddenComponent.setVisible(false);
		componentList.add(hiddenComponent);
	}

	private void addMassCheck(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent, GtnUIFrameWorkActionConfig customAction) {
		String componentId = cdCompaniesTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massCheckConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		massCheckConfig.setAuthorizationIncluded(true);
		massCheckConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdCompaniesComponentList.add(massCheckConfig);

		GtnUIFrameworkOptionGroupConfig massCheckOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		massCheckOptionConfig.setValuesFromService(false);

		massCheckOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.ENABLE, GtnWsContractDashboardContants.DISABLE));
		massCheckOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.DISABLE);
		massCheckConfig.setGtnUIFrameworkOptionGroupConfig(massCheckOptionConfig);
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		massCheckConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addMassPopulateField(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdCompaniesTabPrefix + "PopulateField";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdCompaniesPopulateFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdCompaniesPopulateFieldConfig.setAuthorizationIncluded(true);
		cdCompaniesComponentList.add(cdCompaniesPopulateFieldConfig);
		cdCompaniesPopulateFieldConfig.setEnable(false);
		GtnUIFrameworkComboBoxConfig populateFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		populateFieldComboBoxConfig.setItemValues(Arrays.asList("CFP Start Date", "CFP End Date", "CFP Status"));
		cdCompaniesPopulateFieldConfig.setGtnComboboxConfig(populateFieldComboBoxConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdCompaniesPopulateFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig cdCompaniesPopulateAction = new GtnUIFrameWorkActionConfig();
		cdCompaniesPopulateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdCompaniesPopulateAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		cdCompaniesPopulateAction.addActionParameter(componentIdList);
		cdCompaniesPopulateAction.addActionParameter("1");
		cdCompaniesPopulateAction.addActionParameter("2");
		cdCompaniesPopulateFieldConfig.addGtnUIFrameWorkActionConfig(cdCompaniesPopulateAction);
		componentIdList.add(cdCompaniesPopulateFieldConfig.getComponentId());
		GtnUIFrameworkComponentConfig cfpStartDateConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "_CFPStartDate_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		cfpStartDateConfig.setAuthorizationIncluded(true);
		cdCompaniesComponentList.add(cfpStartDateConfig);
		cfpStartDateConfig.setVisible(false);
		cfpStartDateConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(cfpStartDateConfig.getComponentId());

		GtnUIFrameworkComponentConfig cfpEndDateConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "_CFPEndDate_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		cfpEndDateConfig.setAuthorizationIncluded(true);
		cfpEndDateConfig.setVisible(false);
		cdCompaniesComponentList.add(cfpEndDateConfig);
		cfpEndDateConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(cfpEndDateConfig.getComponentId());

		GtnUIFrameworkComponentConfig cfpStatusConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "Combo_CFPStatus_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpStatusConfig.setAuthorizationIncluded(true);
		cfpStatusConfig.setVisible(false);
		cdCompaniesComponentList.add(cfpStatusConfig);
		cfpStatusConfig.setGtnUIFrameworkValidationConfig(valConfig);
		GtnUIFrameworkComboBoxConfig cfpStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		cfpStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpStatusComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		cfpStatusConfig.setGtnComboboxConfig(cfpStatusComboBoxConfig);
		componentIdList.add(cfpStatusConfig.getComponentId());
	}

	private void addPopulateButtonLayout(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdCompaniesTabPrefix + "PopulateButtonlayout", true, parent);
		cdCompaniesComponentList.add(layoutConfig);
		addPopulateButtonComponent(cdCompaniesComponentList, cdCompaniesTabPrefix, layoutConfig.getComponentId(),
				componentIdList);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig populateButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "PopulateButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateButtonConfig.setComponentName("Populate");
		populateButtonConfig.setAuthorizationIncluded(true);
		populateButtonConfig.setEnable(false);
		cdCompaniesComponentList.add(populateButtonConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyPopulateAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter("1");
		customAction.addActionParameter("2");
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_COMPANY);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_COMPANY);

		populateButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateButtonConfig.getComponentId());
		GtnUIFrameworkComponentConfig populateAllButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "PopulateAllButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateAllButtonConfig.setComponentName("Populate All");
		populateAllButtonConfig.setEnable(false);
		populateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		cdCompaniesComponentList.add(populateAllButtonConfig);
		populateAllButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateAllButtonConfig.getComponentId());
	}

	private void reordLayout(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String cdCompaniesTabPrefix,
			String parent) {
		String componentId = cdCompaniesTabPrefix + "record";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(componentId + "Css" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		cdCompaniesComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig layoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnLayoutConfig.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdCompaniesComponentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				layoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Record:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.HISTORY,
						GtnWsContractDashboardContants.FUTURE, GtnWsContractDashboardContants.PENDING));
		componentOptionConfig.setIsMultiSelect(true);
		componentOptionConfig.setDefaultSelection(GtnFrameworkContractDashboardContants.STRINGUTILS_EMPTY);
		componentConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkCompaniesTabTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(componentConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT));
		componentConfig.addGtnUIFrameWorkActionConfig(tableLoadActionConfig);
	}

	private void companiesResultPanel(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE;
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		gtnPanelConfig.setComponentName("Results");
		cdCompaniesComponentList.add(gtnPanelConfig);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdCompaniesComponentList.add(gtnLayoutConfig);
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdCompaniesResultsTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdCompaniesResultsTable);
		cdCompaniesResultsTable.setEditable(true);
		cdCompaniesResultsTable.setFilterBar(true);
		cdCompaniesResultsTable.setSelectable(true);
		cdCompaniesResultsTable.setPageLength(5);
		cdCompaniesResultsTable.setItemPerPage(5);
		cdCompaniesResultsTable.setSinkItemPerPageWithPageLength(false);
		cdCompaniesResultsTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getCompanyDetailColumnType());
		cdCompaniesResultsTable.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getCompanyDetailHeader());
		cdCompaniesResultsTable.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getCompanyDetailColumn());
		cdCompaniesResultsTable
				.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.CFP_PENDING_STATUS });
		cdCompaniesResultsTable.setExtraColumnDataType(new Class[] { String.class });
		cdCompaniesResultsTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANIES_DETAIL_TABLE_DATA);
		cdCompaniesResultsTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANIES_DETAIL_TABLE_DATA);
		cdCompaniesResultsTable.setColumnCheckBoxId(CHECK_RECORD_ID);
		cdCompaniesResultsTable.setInvisibleFilterPropertyIds(CHECK_RECORD_ID);
		cdCompaniesResultsTable.setCustomFilterConfigMap(getTableFilterFieldMap());

		cdCompaniesResultsTable
				.setEditableColumnList(Arrays.asList(CHECK_RECORD_ID, CFP_STATUS, "cfpStartDate", "cfpEndDate", "cfpEligibleDate"));
		List<GtnUIFrameworkComponentConfig> editableList = new ArrayList<>(
				cdCompaniesResultsTable.getEditableColumnList().size());
		getTableEditableField(editableList);
		cdCompaniesResultsTable.setEditableField(editableList);

		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkTableCheckAllAction.class.getName());
		checkAllAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_COMPANY);
		cdCompaniesResultsTable.addColumnCheckActionConfig(checkAllAction);

		companiesViewResultTable(cdCompaniesComponentList, gtnLayoutConfig.getComponentId());
	}

	private void companiesViewResultTable(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE + "View";

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdCompaniesResultsTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdCompaniesResultsTable);
		cdCompaniesResultsTable.setPageLength(10);
		cdCompaniesResultsTable.setItemPerPage(10);
		cdCompaniesResultsTable.setSinkItemPerPageWithPageLength(false);
		cdCompaniesResultsTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getCompanyDetailViewColumnType());
		cdCompaniesResultsTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getCompanyDetailViewHeader());
		cdCompaniesResultsTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getCompanyDetailViewColumn());
		cdCompaniesResultsTable
				.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.CFP_PENDING_STATUS });
		cdCompaniesResultsTable.setExtraColumnDataType(new Class[] { String.class });
		cdCompaniesResultsTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANIES_DETAIL_VIEW_TABLE_DATA);
		cdCompaniesResultsTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANIES_DETAIL_VIEW_TABLE_DATA);
	}

	private void addRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		String componentId = cdCompaniesTabPrefix + "removeBtn";
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdCompaniesComponentList.add(layoutConfig);
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				layoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setComponentName("Remove");
		componentConfig.setAuthorizationIncluded(true);
		cdCompaniesComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig checkSelectedActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkSelectedActionConfig.addActionParameter(GtnFrameworkUpdateSelectedRecordInTempTableAction.class.getName());
		checkSelectedActionConfig.addActionParameter(GtnWsContractDashboardContants.CHECK_SELECTED_COMPANIES);

		GtnUIFrameWorkActionConfig failureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("Halt");
		failureActionConfig.addActionParameter("Please check mark a row for removal");

		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter("Remove Confirmation");
		confirmActionConfig.addActionParameter("Are you sure you want to remove the record from the Contract?");

		GtnUIFrameWorkActionConfig removeActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeActionConfig.addActionParameter(GtnFrameworkUpdateSelectedRecordInTempTableAction.class.getName());
		removeActionConfig.addActionParameter(GtnWsContractDashboardContants.REMOVE_COMPANIES);

		GtnUIFrameWorkActionConfig companyChangeIndicatorAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		companyChangeIndicatorAction.addActionParameter(GtnFrameworkCompanyChangeIndicatorAction.class.getName());

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkCompaniesTabLoadAction.class.getName());
		confirmActionConfig
				.addActionParameter(Arrays.asList(removeActionConfig, companyChangeIndicatorAction, tabLoadAction));

		checkSelectedActionConfig.addActionParameter(failureActionConfig);
		checkSelectedActionConfig.addActionParameter(confirmActionConfig);

		componentConfig.addGtnUIFrameWorkActionConfig(checkSelectedActionConfig);
		addExcelButtonComponent(cdCompaniesComponentList, cdCompaniesTabPrefix, layoutConfig.getComponentId());
		addViewExcelButtonComponent(cdCompaniesComponentList, cdCompaniesTabPrefix, layoutConfig.getComponentId());
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "excelButton", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdCompaniesComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Company Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE);
		gtnExcelButtonConfig.setExcludeColumnsList(Arrays.asList(CHECK_RECORD_ID));
		gtnExcelButtonConfig.setHelperTableMapedPropertyIdList(Arrays.asList(CFP_STATUS));
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addViewExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList,
			String cdCompaniesTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdCompaniesTabPrefix + "excelButtonView", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdCompaniesComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Company Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig
				.setExportTableId(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE + "View");
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void getTableEditableField(List<GtnUIFrameworkComponentConfig> cdCompaniesComponentList) {
		GtnUIFrameworkComponentConfig checkRecordConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.CHECKBOX);
		cdCompaniesComponentList.add(checkRecordConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkFieldFactoryAction.class.getName());
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_COMPANY_FIELD);
		customAction.addActionParameter(20);
		checkRecordConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(customAction));

		GtnUIFrameWorkActionConfig focusAction = new GtnUIFrameWorkActionConfig();
		focusAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		focusAction.addActionParameter(GtnUIFrameworkFocusAction.class.getName());

		GtnUIFrameworkComponentConfig cfpStatusConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		cdCompaniesComponentList.add(cfpStatusConfig);
		cfpStatusConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		cfpStatusConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
		GtnUIFrameworkComboBoxConfig cfpStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		cfpStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpStatusComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		cfpStatusConfig.setGtnComboboxConfig(cfpStatusComboBoxConfig);

		GtnUIFrameworkComponentConfig cfpStartDateConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.DATEFIELD);
		cdCompaniesComponentList.add(cfpStartDateConfig);
		cfpStartDateConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		cfpStartDateConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
		GtnUIFrameworkComponentConfig cfpEndDateConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.DATEFIELD);
		cdCompaniesComponentList.add(cfpEndDateConfig);
		cfpEndDateConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		cfpEndDateConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
                GtnUIFrameworkComponentConfig eligibleDateConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.DATEFIELD);
		cdCompaniesComponentList.add(eligibleDateConfig);
		eligibleDateConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		eligibleDateConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getTableFilterFieldMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig cfpStatusFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		GtnUIFrameworkComponentConfig cfpStatusConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComboBoxConfig cfpStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		cfpStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpStatusComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		cfpStatusConfig.setGtnComboboxConfig(cfpStatusComboBoxConfig);
		cfpStatusFilterConfig.setPropertId(CFP_STATUS);
		cfpStatusFilterConfig.setGtnComponentConfig(cfpStatusConfig);
		cfpStatusFilterConfig.setGtnComponentType(cfpStatusConfig.getComponentType());
		customFilterConfigMap.put(cfpStatusFilterConfig.getPropertId(), cfpStatusFilterConfig);
		return customFilterConfigMap;
	}
}
