package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkItemChangeIndicatorAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRebatePopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkRebateTableLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkUpdateSelectedRecordInTempTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkFieldEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPopulateFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkRebateTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnUIFrameworkFocusAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkRebateTabLoadAction;
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

public class GtnFrameworkContractDashboardRebateTabConfig {

	private static final String RESULT_TABLE = "_ResultTable";
	private static final String RULE_NO = "ruleNo";
	private static final String PARENT_RS_ID = "ParentRsID";
	private static final String NET_SALES_FORMULA_LOOKUP = "Net Sales Formula Lookup";
	private static final String DATE_FORMAT = "MM/dd/YYYY";
	private static final String COMBO_CALCULATION_TYPE = "Combo_CalculationType";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdRebateTabPrefix = mainNamspacePrefix + "RT";
		GtnUIFrameworkTabConfig cdRebateTabConfig = new GtnUIFrameworkTabConfig();
		cdRebateTabConfig.setComponentId(cdRebateTabPrefix + "rebateTab");
		cdRebateTabConfig.setTabCaption("Rebate");
		List<GtnUIFrameworkComponentConfig> cdRebateComponentList = new ArrayList<>();
		cdRebateTabConfig.setTabLayoutComponentConfigList(cdRebateComponentList);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getVerticalLayoutConfig(cdRebateTabConfig.getComponentId(), true, cdRebateTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		cdRebateComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addContractHeaderDetailDecissionLayout(cdRebateComponentList, cdRebateTabPrefix,
				gtnLayoutConfig.getComponentId());
		addContractHeaderFieldPanel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		List<String> populateFieldIdList = new ArrayList<>();
		addDeatilsLayout(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				populateFieldIdList);
		addProcessingLayout(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkRebateTabLoadAction.class.getName());
		customAction.addActionParameter(cdRebateTabPrefix + "DecissionLevel");
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.REBATE_TAB_AVL_RULE_TABLE);
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE);
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.REBATE_TAB_SEL_RULE_TABLE);
		customAction.addActionParameter(cdRebateTabPrefix + COMBO_CALCULATION_TYPE);
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(populateFieldIdList);
		customAction.addActionParameter(cdRebateTabPrefix + "MassUpdatePanel");
		customAction.addActionParameter(cdRebateTabPrefix + "removeBtn");
		customAction.addActionParameter(cdRebateTabPrefix + "excelButton");
		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(customAction);
		return cdRebateTabConfig;
	}

	private void addContractHeaderDetailDecissionLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdRebateTabPrefix + "HeaderDetailDecissionLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cdRebateComponentList.add(gtnLayoutConfig);

		addDecissionLevel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
		addDecissionView(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addContractHeaderFieldPanel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnHeaderLayoutConfig = commonConfig
				.getVerticalLayoutConfig(cdRebateTabPrefix + "Decission_Header_Layout", true, parent);
		gtnHeaderLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnHeaderLayoutConfig);

		GtnUIFrameworkComponentConfig gtnCurrentLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdRebateTabPrefix + "Decission_Header_Current_Pending_Layout", true,
				gtnHeaderLayoutConfig.getComponentId());
		gtnCurrentLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnCurrentLayoutConfig);
		addHeaderFieldRSIComponent(cdRebateComponentList, cdRebateTabPrefix, gtnCurrentLayoutConfig.getComponentId(),
				componentIdList);
		addHeaderFieldRPOComponent(cdRebateComponentList, cdRebateTabPrefix, gtnCurrentLayoutConfig.getComponentId(),
				componentIdList);
		itemsTabHistoryDataTableComponent(cdRebateComponentList, cdRebateTabPrefix,
				gtnHeaderLayoutConfig.getComponentId());
	}

	private void addDecissionLevel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "DecissionLevel";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateDecissionLevelConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		rebateDecissionLevelConfig.setAuthorizationIncluded(true);
		rebateDecissionLevelConfig.setComponentName("Level:");
		rebateDecissionLevelConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdRebateComponentList.add(rebateDecissionLevelConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.HEADER,
				GtnWsContractDashboardContants.DETAIL, GtnWsContractDashboardContants.PROCESSING_OPTION));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.HEADER);

		rebateDecissionLevelConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		customAction.addActionParameter(Arrays.asList(cdRebateTabPrefix + "Decission_Header_Layout",
				cdRebateTabPrefix + "Decission_Header_ProcessingOptions_View",
				cdRebateTabPrefix + "_" + GtnWsContractDashboardContants.DETAIL + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT,
				cdRebateTabPrefix + "_" + GtnWsContractDashboardContants.PROCESSING_OPTION.replace(" ", "") + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		rebateDecissionLevelConfig.addGtnUIFrameWorkActionConfig(customAction);
		GtnUIFrameWorkActionConfig tabLoadCustomAction = new GtnUIFrameWorkActionConfig();
		tabLoadCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadCustomAction.addActionParameter(GtnFrameworkRebateTabLoadAction.class.getName());
		rebateDecissionLevelConfig.addGtnUIFrameWorkActionConfig(tabLoadCustomAction);
	}

	private void addDecissionView(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "Decission_Header_ProcessingOptions_View";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("View:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.HISTORY,
				GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.PENDING));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.CURRENT);

		componentConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		customAction.addActionParameter(Arrays.asList(cdRebateTabPrefix + "Decission_Header_Current_Pending_Layout",
				cdRebateTabPrefix + "_Header_" + GtnWsContractDashboardContants.HISTORY + RESULT_TABLE
						+ GtnFrameworkCommonStringConstants.LAYOUT,
				cdRebateTabPrefix + "_" + GtnWsContractDashboardContants.PROCESSING_OPTION.replace(" ", "") + "_"
						+ GtnWsContractDashboardContants.HISTORY + RESULT_TABLE
						+ GtnFrameworkCommonStringConstants.LAYOUT,
				cdRebateTabPrefix + "Decission_" + GtnWsContractDashboardContants.PROCESSING_OPTION.replace(" ", "")
						+ "_Current_Pending_Layout"));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addHeaderFieldRSIComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdRebateTabPrefix + "_Header_RSI_Panel", true, parent);
		gtnPanelConfig.setComponentName("Rebate Schedule Information");
		cdRebateComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnVerticalLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdRebateTabPrefix + "RSI_verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnVerticalLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnVerticalLayoutConfig);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdRebateTabPrefix + "CssLayout_RSI", true, gtnVerticalLayoutConfig.getComponentId());
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdRebateComponentList.add(gtnLayoutConfig);

		addRebateScheduleId(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleNo(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleName(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleStatus(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleType(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateProgramType(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleCategory(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleStartDate(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleEndDate(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);

		addRsTradeClass(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addRebateScheduleDesignation(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRebateScheduleAliasID(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addParentRsId(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addParentRsName(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addRSTransactionRefId(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addRSTransactionRefName(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDeductionInclusion(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addUdc1(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addUdc2(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addUdc3(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addUdc4(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addUdc5(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addUdc6(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
	}

	private void addHeaderFieldRPOComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdRebateTabPrefix + "_Header_RPO_Panel", true, parent);
		gtnPanelConfig.setComponentName("Rebate Payment Options:");
		cdRebateComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnVerticalLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdRebateTabPrefix + "RPO_verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnVerticalLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnVerticalLayoutConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdRebateTabPrefix + "CssLayout_RPO", true, gtnVerticalLayoutConfig.getComponentId());
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdRebateComponentList.add(gtnLayoutConfig);

		addCalendar(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addRebateFrequency(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addPaymentLevel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addPaymentFrequency(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPaymentGracePeriod(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPaymentTerms(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addPaymentMethod(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addInterestBearingBasis(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addEvaluationRuleLevel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addEvaluationRuleType(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addEvaluationRule(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addInterestBearingIndicator(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCalculationRuleLevel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCalculationType(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCalculationRule(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCalculationLevel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void addRebateScheduleId(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Text_RebateScheduleID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rebateScheduleIdConfig.setAuthorizationIncluded(true);
		rebateScheduleIdConfig.setComponentName("Rebate Schedule ID");
		rebateScheduleIdConfig.setEnable(false);
		rebateScheduleIdConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateScheduleIdConfig);
		componentIdList.add(rebateScheduleIdConfig.getComponentId());

		GtnUIFrameworkValidationConfig rebateScheduleIdValConfig = new GtnUIFrameworkValidationConfig();
		rebateScheduleIdValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebateScheduleIdValConfig.setAttachLengthValidatior(true);
		rebateScheduleIdValConfig.setMinSize(0);
		rebateScheduleIdValConfig.setMaxLength(38);
		rebateScheduleIdValConfig
				.setLengthValidationMessage("Rebate Schedule ID length should be less than 38 characters");

		rebateScheduleIdConfig.setGtnUIFrameworkValidationConfig(rebateScheduleIdValConfig);
	}

	private void addRebateScheduleNo(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Text_RebateScheduleNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleNoConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rebateScheduleNoConfig.setAuthorizationIncluded(true);
		rebateScheduleNoConfig.setComponentName("Rebate Schedule No");
		rebateScheduleNoConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateScheduleNoConfig);
		componentIdList.add(rebateScheduleNoConfig.getComponentId());

		GtnUIFrameworkValidationConfig rebateScheduleNoValConfig = new GtnUIFrameworkValidationConfig();
		rebateScheduleNoValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebateScheduleNoValConfig.setAttachLengthValidatior(true);
		rebateScheduleNoValConfig.setMinSize(0);
		rebateScheduleNoValConfig.setMaxLength(50);
		rebateScheduleNoValConfig
				.setLengthValidationMessage("Rebate Schedule No length should be less than 50 characters");

		rebateScheduleNoConfig.setGtnUIFrameworkValidationConfig(rebateScheduleNoValConfig);
	}

	private void addRebateScheduleName(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdRebateTabPrefix + "Text_RebateScheduleName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rebateScheduleNameConfig.setAuthorizationIncluded(true);
		rebateScheduleNameConfig.setComponentName("Rebate Schedule Name");
		rebateScheduleNameConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateScheduleNameConfig);
		componentIdList.add(rebateScheduleNameConfig.getComponentId());

		GtnUIFrameworkValidationConfig rebateScheduleNameValConfig = new GtnUIFrameworkValidationConfig();
		rebateScheduleNameValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebateScheduleNameValConfig.setAttachLengthValidatior(true);
		rebateScheduleNameValConfig.setMinSize(0);
		rebateScheduleNameValConfig.setMaxLength(100);
		rebateScheduleNameValConfig
				.setLengthValidationMessage("Rebate Schedule ID length should be less than 100 characters");

		rebateScheduleNameConfig.setGtnUIFrameworkValidationConfig(rebateScheduleNameValConfig);
	}

	private void addRebateScheduleStatus(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateScheduleStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleStatusConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleStatusConfig.setAuthorizationIncluded(true);
		rebateScheduleStatusConfig.setComponentName("Rebate Schedule Status");
		rebateScheduleStatusConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateScheduleStatusConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = commonConfig.getComboBoxConfig(
				GtnWsContractDashboardContants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleStatusConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rebateScheduleStatusConfig.getComponentId());

		GtnUIFrameworkValidationConfig rebateScheduleStatusValConfig = new GtnUIFrameworkValidationConfig();
		rebateScheduleStatusValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		rebateScheduleStatusConfig.setGtnUIFrameworkValidationConfig(rebateScheduleStatusValConfig);
	}

	private void addRebateScheduleType(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateScheduleType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleTypeConfig.setAuthorizationIncluded(true);
		rebateScheduleTypeConfig.setComponentName("Rebate Schedule Type");
		cdRebateComponentList.add(rebateScheduleTypeConfig);
		rebateScheduleTypeConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = commonConfig.getComboBoxConfig(
				GtnWsContractDashboardContants.RS_TYPE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rebateScheduleTypeConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRebateProgramType(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateProgramType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateProgramTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rebateProgramTypeConfig.setAuthorizationIncluded(true);
		rebateProgramTypeConfig.setComponentName("Rebate Program Type");
		rebateProgramTypeConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateProgramTypeConfig);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = commonConfig.getComboBoxConfig(
				GtnWsContractDashboardContants.REBATE_PROGRAM_TYPE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateProgramTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rebateProgramTypeConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateProgramTypeConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRebateScheduleCategory(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateScheduleCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleCategoryConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleCategoryConfig.setAuthorizationIncluded(true);
		rebateScheduleCategoryConfig.setComponentName("Rebate Schedule Category");
		rebateScheduleCategoryConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateScheduleCategoryConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = commonConfig.getComboBoxConfig(
				GtnWsContractDashboardContants.RS_CATEGORY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleCategoryConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rebateScheduleCategoryConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleCategoryConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addRebateScheduleStartDate(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdRebateTabPrefix + "RSStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleStartDateConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		rebateScheduleStartDateConfig.setAuthorizationIncluded(true);
		rebateScheduleStartDateConfig.setComponentName("RS Start Date");
		rebateScheduleStartDateConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateScheduleStartDateConfig);
		componentIdList.add(rebateScheduleStartDateConfig.getComponentId());

		GtnUIFrameWorkActionConfig rebateScheduleStartDateBeforeValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		rebateScheduleStartDateBeforeValidationAction.addActionParameter("CDProcessView_ITIFPEndDate");
		rebateScheduleStartDateBeforeValidationAction.addActionParameter(1);
		rebateScheduleStartDateBeforeValidationAction.addActionParameter("Select RS Start date before ?");
		rebateScheduleStartDateBeforeValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig rebateScheduleStartDateAfterValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		rebateScheduleStartDateAfterValidationAction.addActionParameter("CDProcessView_ITIFPStartDate");
		rebateScheduleStartDateAfterValidationAction.addActionParameter(-1);
		rebateScheduleStartDateAfterValidationAction.addActionParameter("Select RS Start date After ?");
		rebateScheduleStartDateAfterValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameworkValidationConfig rebateScheduleStartDateValConfig = new GtnUIFrameworkValidationConfig();
		rebateScheduleStartDateValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleStartDateConfig.setGtnUIFrameworkValidationConfig(rebateScheduleStartDateValConfig);
		GtnUIFrameWorkActionConfig rebateScheduleStartDateValidationAction = new GtnUIFrameWorkActionConfig();
		rebateScheduleStartDateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rebateScheduleStartDateValidationAction
				.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		rebateScheduleStartDateValidationAction.addActionParameter(rebateScheduleStartDateConfig.getComponentId());
		rebateScheduleStartDateValidationAction.addActionParameter(new ArrayList<String>());
		rebateScheduleStartDateValidationAction
				.addActionParameter(Arrays.asList(cdRebateTabPrefix + "RebateScheduleEndDate"));
		rebateScheduleStartDateValidationAction.addActionParameter("Start Date");
		rebateScheduleStartDateValidationAction.addActionParameter("Start");
		GtnUIFrameworkDateFieldConfig rebateScheduleStartDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		rebateScheduleStartDateFieldConfig.addValueChangeActionConfig(rebateScheduleStartDateValidationAction);
		rebateScheduleStartDateFieldConfig.setValidationActionConfigList(Arrays
				.asList(rebateScheduleStartDateBeforeValidationAction, rebateScheduleStartDateAfterValidationAction));
		rebateScheduleStartDateConfig.setGtnDateFieldConfig(rebateScheduleStartDateFieldConfig);
	}

	private void addRebateScheduleEndDate(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "RebateScheduleEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleEndDateConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		rebateScheduleEndDateConfig.setAuthorizationIncluded(true);
		rebateScheduleEndDateConfig.setComponentName("RS End Date");
		cdRebateComponentList.add(rebateScheduleEndDateConfig);
		componentIdList.add(rebateScheduleEndDateConfig.getComponentId());
		GtnUIFrameWorkActionConfig rebateScheduleEndDateBeforeValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		rebateScheduleEndDateBeforeValidationAction.addActionParameter("CDProcessView_ITIFPEndDate");
		rebateScheduleEndDateBeforeValidationAction.addActionParameter(1);
		rebateScheduleEndDateBeforeValidationAction.addActionParameter("Select RS End date before ?");
		rebateScheduleEndDateBeforeValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig rebateScheduleEndDateAfterValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		rebateScheduleEndDateAfterValidationAction.addActionParameter("CDProcessView_ITIFPStartDate");
		rebateScheduleEndDateAfterValidationAction.addActionParameter(-1);
		rebateScheduleEndDateAfterValidationAction.addActionParameter("Select RS End date After ?");
		rebateScheduleEndDateAfterValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig ebateScheduleEndDateValidationAction = new GtnUIFrameWorkActionConfig();
		ebateScheduleEndDateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		ebateScheduleEndDateValidationAction
				.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		ebateScheduleEndDateValidationAction.addActionParameter(rebateScheduleEndDateConfig.getComponentId());
		ebateScheduleEndDateValidationAction.addActionParameter(Arrays.asList(cdRebateTabPrefix + "RSStartDate"));
		ebateScheduleEndDateValidationAction.addActionParameter(new ArrayList<String>());
		ebateScheduleEndDateValidationAction.addActionParameter("End Date");
		ebateScheduleEndDateValidationAction.addActionParameter("End");
		GtnUIFrameworkDateFieldConfig drebateScheduleEndDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		drebateScheduleEndDateFieldConfig.addValueChangeActionConfig(ebateScheduleEndDateValidationAction);
		drebateScheduleEndDateFieldConfig.setValidationActionConfigList(
				Arrays.asList(rebateScheduleEndDateBeforeValidationAction, rebateScheduleEndDateAfterValidationAction));
		rebateScheduleEndDateConfig.setGtnDateFieldConfig(drebateScheduleEndDateFieldConfig);
	}

	private void addRsTradeClass(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateScheduleTradeClass";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rsTradeClassConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rsTradeClassConfig.setAuthorizationIncluded(true);
		rsTradeClassConfig.setComponentName("Rebate Schedule Trade Class");
		cdRebateComponentList.add(rsTradeClassConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = commonConfig.getComboBoxConfig(
				GtnWsContractDashboardContants.RS_TRADE_CLASS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rsTradeClassConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rsTradeClassConfig.getComponentId());
	}

	private void addRebateScheduleDesignation(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateScheduleDesignation";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleDesignationConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleDesignationConfig.setAuthorizationIncluded(true);
		rebateScheduleDesignationConfig.setComponentName("Rebate Schedule Designation");
		cdRebateComponentList.add(rebateScheduleDesignationConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_DESIGNATION);
		rebateScheduleDesignationConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rebateScheduleDesignationConfig.getComponentId());
		GtnUIFrameWorkActionConfig cdRsDesignationEnableAction = new GtnUIFrameWorkActionConfig();
		cdRsDesignationEnableAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdRsDesignationEnableAction.addActionParameter(GtnUIFrameworkFieldEnableDisableAction.class.getName());
		cdRsDesignationEnableAction.addActionParameter(rebateScheduleDesignationConfig.getComponentId());
		cdRsDesignationEnableAction.addActionParameter(true);
		cdRsDesignationEnableAction.addActionParameter("child");
		cdRsDesignationEnableAction.setFieldValues(Arrays.asList(cdRebateTabPrefix + PARENT_RS_ID));
		rebateScheduleDesignationConfig.addGtnUIFrameWorkActionConfig(cdRsDesignationEnableAction);
	}

	private void addRebateScheduleAliasID(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "RebateScheduleAliasID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateScheduleAliasIDConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rebateScheduleAliasIDConfig.setAuthorizationIncluded(true);
		rebateScheduleAliasIDConfig.setComponentName("Rebate Schedule Alias ID");
		cdRebateComponentList.add(rebateScheduleAliasIDConfig);
		componentIdList.add(rebateScheduleAliasIDConfig.getComponentId());
		GtnUIFrameworkValidationConfig rebateScheduleAliasIDValConfig = new GtnUIFrameworkValidationConfig();
		rebateScheduleAliasIDValConfig.setAttachRegxValidatior(true);
		rebateScheduleAliasIDValConfig
				.setFormatString("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*");
		rebateScheduleAliasIDValConfig
				.setRegxValidationMessage("Rebate Schedule Alias Allowed Special characters are @,*,#,.,$,&,_,-");
		rebateScheduleAliasIDValConfig.setAttachLengthValidatior(true);
		rebateScheduleAliasIDValConfig.setMinSize(0);
		rebateScheduleAliasIDValConfig.setMaxLength(50);
		rebateScheduleAliasIDValConfig
				.setLengthValidationMessage("Rebate Schedule Alias length should be less than 50 characters");
		rebateScheduleAliasIDConfig.setGtnUIFrameworkValidationConfig(rebateScheduleAliasIDValConfig);
	}

	private void addParentRsId(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + PARENT_RS_ID;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig parentRsIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentRsIdConfig.setAuthorizationIncluded(true);
		parentRsIdConfig.setComponentName("Parent Rebate Schedule ID");
		cdRebateComponentList.add(parentRsIdConfig);
		componentIdList.add(parentRsIdConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		textboxConfig.setEnable(false);
		parentRsIdConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameWorkActionConfig parentRsIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
		parentRsIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		parentRsIdPopupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_RS_VIEW);
		parentRsIdPopupActionConfig.addActionParameter("Parent Rebate Schedule Lookup");
		parentRsIdPopupActionConfig.addActionParameter("70%");
		parentRsIdPopupActionConfig.addActionParameter(null);
		parentRsIdPopupActionConfig.addActionParameter(null);
		parentRsIdPopupActionConfig
				.addActionParameter(Arrays.asList(parentRsIdConfig.getComponentId(), Arrays.asList("rsId", "rsName"),
						Arrays.asList(cdRebateTabPrefix + PARENT_RS_ID, cdRebateTabPrefix + "ParentRsName"), null));
		parentRsIdConfig.addGtnUIFrameWorkActionConfig(parentRsIdPopupActionConfig);
	}

	private void addParentRsName(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "ParentRsName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig parentRsNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		parentRsNameConfig.setAuthorizationIncluded(true);
		parentRsNameConfig.setComponentName("Parent Rebate Schedule Name");
		cdRebateComponentList.add(parentRsNameConfig);
		componentIdList.add(parentRsNameConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig parentRsNameTextboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		parentRsNameTextboxConfig.setReadOnly(true);
		parentRsNameTextboxConfig.setEnable(false);
		parentRsNameConfig.setGtnTextBoxConfig(parentRsNameTextboxConfig);
	}

	private void addRSTransactionRefId(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "RSTransactionRefID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rSTransactionRefId = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		rSTransactionRefId.setAuthorizationIncluded(true);
		rSTransactionRefId.setComponentName("RS Transaction Ref ID");
		cdRebateComponentList.add(rSTransactionRefId);
		componentIdList.add(rSTransactionRefId.getComponentId());

		GtnUIFrameworkTextBoxConfig rSTransactionRefIdTextboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		rSTransactionRefIdTextboxConfig.setReadOnly(true);
		rSTransactionRefId.setGtnTextBoxConfig(rSTransactionRefIdTextboxConfig);

		GtnUIFrameWorkActionConfig rSTransactionRefIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
		rSTransactionRefIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		rSTransactionRefIdPopupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_RS_VIEW);
		rSTransactionRefIdPopupActionConfig.addActionParameter("Rebate Schedule Reference No");
		rSTransactionRefIdPopupActionConfig.addActionParameter("70%");
		rSTransactionRefIdPopupActionConfig.addActionParameter(null);
		rSTransactionRefIdPopupActionConfig.addActionParameter(null);
		rSTransactionRefIdPopupActionConfig.addActionParameter(Arrays.asList(rSTransactionRefId.getComponentId(),
				Arrays.asList("rsNo", "rsName"),
				Arrays.asList(cdRebateTabPrefix + "RSTransactionRefID", cdRebateTabPrefix + "RSTransactionRefName"),
				null));
		rSTransactionRefId.addGtnUIFrameWorkActionConfig(rSTransactionRefIdPopupActionConfig);
	}

	private void addRSTransactionRefName(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "RSTransactionRefName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rSTransactionRefNameConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rSTransactionRefNameConfig.setAuthorizationIncluded(true);
		rSTransactionRefNameConfig.setComponentName("RS Transaction Ref Name");
		cdRebateComponentList.add(rSTransactionRefNameConfig);
		componentIdList.add(rSTransactionRefNameConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig rSTransactionRefNameTextboxConfig = commonConfig.getTextBoxConfig(true, false,
				true);
		rSTransactionRefNameTextboxConfig.setReadOnly(true);
		rSTransactionRefNameTextboxConfig.setEnable(false);
		rSTransactionRefNameConfig.setGtnTextBoxConfig(rSTransactionRefNameTextboxConfig);
	}

	private void addDeductionInclusion(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_DeductionInclusion";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig deductionInclusionConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionInclusionConfig.setAuthorizationIncluded(true);
		deductionInclusionConfig.setComponentName("Deduction Inclusion");
		deductionInclusionConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(deductionInclusionConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.LOCKED_STATUS);
		deductionInclusionConfig.setGtnComboboxConfig(comboBoxConfig);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		deductionInclusionConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		componentIdList.add(deductionInclusionConfig.getComponentId());

	}

	private void addUdc1(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Udc1";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("UDC 1");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC1);
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc("UDC1");
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addUdc2(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Udc2";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("UDC 2");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC2);
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc("UDC2");
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addUdc3(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Udc3";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("UDC 3");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC3);
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc("UDC3");
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addUdc4(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Udc4";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("UDC 4");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC4);
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc("UDC4");
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addUdc5(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Udc5";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("UDC 5");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC5);
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc("UDC5");
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addUdc6(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Udc6";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("UDC 6");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_UDC6);
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc("UDC6");
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCalendar(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_Calendar";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Calendar");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_CALENDAR);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addRebateFrequency(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateFrequency";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rebateFrequencyConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rebateFrequencyConfig.setAuthorizationIncluded(true);
		rebateFrequencyConfig.setComponentName("Rebate Frequency");
		rebateFrequencyConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(rebateFrequencyConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.REBATE_FREQUENCY);
		rebateFrequencyConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(rebateFrequencyConfig.getComponentId());
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateFrequencyConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addPaymentLevel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_PaymentLevel";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Payment Level");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PAYMENT_LEVEL);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPaymentFrequency(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_PaymentFrequency";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig paymentFrequencyConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		paymentFrequencyConfig.setAuthorizationIncluded(true);
		paymentFrequencyConfig.setComponentName("Payment Frequency");
		paymentFrequencyConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(paymentFrequencyConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PAYMENT_FREQUENCY);
		paymentFrequencyConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(paymentFrequencyConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		paymentFrequencyConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPaymentGracePeriod(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Text_PaymentGracePeriod";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig paymentGracePeriodConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		paymentGracePeriodConfig.setAuthorizationIncluded(true);
		paymentGracePeriodConfig.setComponentName("Payment Grace Period");
		cdRebateComponentList.add(paymentGracePeriodConfig);
		componentIdList.add(paymentGracePeriodConfig.getComponentId());

		GtnUIFrameworkValidationConfig paymentGracePeriodValConfig = new GtnUIFrameworkValidationConfig();
		paymentGracePeriodValConfig.setAttachRegxValidatior(true);
		paymentGracePeriodValConfig.setFormatString("([0-9])*");
		paymentGracePeriodValConfig.setRegxValidationMessage("value can contain only numbers");
		paymentGracePeriodValConfig.setAttachLengthValidatior(true);
		paymentGracePeriodValConfig.setMinSize(0);
		paymentGracePeriodValConfig.setMaxLength(10);
		paymentGracePeriodValConfig
				.setLengthValidationMessage("Payment Grace Period length should be less than 10 characters");
		paymentGracePeriodConfig.setGtnUIFrameworkValidationConfig(paymentGracePeriodValConfig);
	}

	private void addPaymentTerms(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_PaymentTerms";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Payment Terms");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PAYMENT_TERMS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPaymentMethod(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_PaymentMethod";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig paymentComponentConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		paymentComponentConfig.setAuthorizationIncluded(true);
		paymentComponentConfig.setComponentName("Payment Method");
		paymentComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(paymentComponentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PAYMENT_METHOD);
		paymentComponentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(paymentComponentConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		paymentComponentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addInterestBearingBasis(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_InterestBearingBasis";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig nterestBearingBasisConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		nterestBearingBasisConfig.setAuthorizationIncluded(true);
		nterestBearingBasisConfig.setComponentName("Interest Bearing Basis");
		cdRebateComponentList.add(nterestBearingBasisConfig);

		GtnUIFrameworkComboBoxConfig interestBearingBasisComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		interestBearingBasisComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		interestBearingBasisComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.INTEREST_BEARING_BASIS);
		nterestBearingBasisConfig.setGtnComboboxConfig(interestBearingBasisComboBoxConfig);
		componentIdList.add(nterestBearingBasisConfig.getComponentId());
	}

	private void addEvaluationRuleLevel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_EvaluationRuleLevel";
		GtnUIFrameworkComponentConfig evaluationRuleLevelLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(evaluationRuleLevelLayoutConfig);

		GtnUIFrameworkComponentConfig evaluationRuleLevelConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, evaluationRuleLevelLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		evaluationRuleLevelConfig.setAuthorizationIncluded(true);
		evaluationRuleLevelConfig.setComponentName("Evaluation Rule Level");
		cdRebateComponentList.add(evaluationRuleLevelConfig);

		GtnUIFrameworkComboBoxConfig evaluationRuleLevelComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		evaluationRuleLevelComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		evaluationRuleLevelComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RULE_LEVEL);
		evaluationRuleLevelConfig.setGtnComboboxConfig(evaluationRuleLevelComboBoxConfig);
		componentIdList.add(evaluationRuleLevelConfig.getComponentId());
	}

	private void addEvaluationRuleType(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_EvaluationRuleType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Evaluation Rule Type");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.EVALUATION_RULE_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addEvaluationRule(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Text_EvaluationRule";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig evaluationRuleConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		evaluationRuleConfig.setAuthorizationIncluded(true);
		evaluationRuleConfig.setComponentName("Evaluation Rule/Association");
		cdRebateComponentList.add(evaluationRuleConfig);
		componentIdList.add(evaluationRuleConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig evaluationRuleTextboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		evaluationRuleTextboxConfig.setReadOnly(true);
		evaluationRuleConfig.setGtnTextBoxConfig(evaluationRuleTextboxConfig);

		GtnUIFrameWorkActionConfig evaluationRulePopupActionConfig = new GtnUIFrameWorkActionConfig();
		evaluationRulePopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		evaluationRulePopupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW);
		evaluationRulePopupActionConfig.addActionParameter(NET_SALES_FORMULA_LOOKUP);
		evaluationRulePopupActionConfig.addActionParameter("70%");
		evaluationRulePopupActionConfig.addActionParameter(null);
		evaluationRulePopupActionConfig.addActionParameter(null);
		evaluationRulePopupActionConfig.addActionParameter(Arrays.asList(evaluationRuleConfig.getComponentId(),
				Arrays.asList(RULE_NO), Arrays.asList(evaluationRuleConfig.getComponentId()), null, "Evaluation"));
		evaluationRuleConfig.addGtnUIFrameWorkActionConfig(evaluationRulePopupActionConfig);
	}

	private void addInterestBearingIndicator(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_InterestBearingIndicator";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig interestBearingIndicatorConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		interestBearingIndicatorConfig.setAuthorizationIncluded(true);
		interestBearingIndicatorConfig.setComponentName("Interest Bearing Indicator");
		cdRebateComponentList.add(interestBearingIndicatorConfig);

		GtnUIFrameworkComboBoxConfig interestBearingIndicatorComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		interestBearingIndicatorComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		interestBearingIndicatorComboBoxConfig
				.setComboBoxType(GtnWsContractDashboardContants.INTEREST_BEARING_INDICATOR);
		interestBearingIndicatorConfig.setGtnComboboxConfig(interestBearingIndicatorComboBoxConfig);
		componentIdList.add(interestBearingIndicatorConfig.getComponentId());
	}

	private void addCalculationRuleLevel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_CalculationRuleLevel";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig calculationRuleLevelConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		calculationRuleLevelConfig.setAuthorizationIncluded(true);
		calculationRuleLevelConfig.setComponentName("Calculation Rule Level");
		cdRebateComponentList.add(calculationRuleLevelConfig);

		GtnUIFrameworkComboBoxConfig calculationRuleLevelComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		calculationRuleLevelComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		calculationRuleLevelComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RULE_LEVEL);
		calculationRuleLevelConfig.setGtnComboboxConfig(calculationRuleLevelComboBoxConfig);
		componentIdList.add(calculationRuleLevelConfig.getComponentId());
	}

	private void addCalculationType(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + COMBO_CALCULATION_TYPE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig calculationTypeConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		calculationTypeConfig.setAuthorizationIncluded(true);
		calculationTypeConfig.setComponentName("Calculation Type");
		calculationTypeConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(calculationTypeConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CALCULATION_TYPE);
		calculationTypeConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(calculationTypeConfig.getComponentId());
		GtnUIFrameWorkActionConfig rebateTableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		rebateTableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rebateTableLoadActionConfig.addActionParameter(GtnFrameworkRebateTableLoadAction.class.getName());
		calculationTypeConfig.addGtnUIFrameWorkActionConfig(rebateTableLoadActionConfig);
		GtnUIFrameworkValidationConfig valCalcTypeConfig = new GtnUIFrameworkValidationConfig();
		valCalcTypeConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		calculationTypeConfig.setGtnUIFrameworkValidationConfig(valCalcTypeConfig);

	}

	private void addCalculationRule(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Text_CalculationRule";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig calculationRuleConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		calculationRuleConfig.setAuthorizationIncluded(true);
		calculationRuleConfig.setComponentName("Calculation Rule");
		cdRebateComponentList.add(calculationRuleConfig);
		componentIdList.add(calculationRuleConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig calculationRuleTextboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		calculationRuleTextboxConfig.setReadOnly(true);
		calculationRuleConfig.setGtnTextBoxConfig(calculationRuleTextboxConfig);

		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW);
		popupActionConfig.addActionParameter(NET_SALES_FORMULA_LOOKUP);
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(Arrays.asList(calculationRuleConfig.getComponentId(),
				Arrays.asList(RULE_NO), Arrays.asList(calculationRuleConfig.getComponentId()), null, "Calculation"));
		calculationRuleConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
	}

	private void addCalculationLevel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_CalculationLevel";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig calculationComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		calculationComponentConfig.setAuthorizationIncluded(true);
		calculationComponentConfig.setComponentName("Calculation Level");
		calculationComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdRebateComponentList.add(calculationComponentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CALCULATION_LEVEL);
		calculationComponentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(calculationComponentConfig.getComponentId());
		GtnUIFrameworkValidationConfig validationCalcConfig = new GtnUIFrameworkValidationConfig();
		validationCalcConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		calculationComponentConfig.setGtnUIFrameworkValidationConfig(validationCalcConfig);

	}

	private void itemsTabHistoryDataTableComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		String componentId = cdRebateTabPrefix + "_Header_" + GtnWsContractDashboardContants.HISTORY + RESULT_TABLE;
		GtnUIFrameworkComponentConfig cdRebateHistoryResultTableLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateHistoryResultTableLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateHistoryResultTableLayoutConfig.setVisible(false);
		cdRebateComponentList.add(cdRebateHistoryResultTableLayoutConfig);

		GtnUIFrameworkComponentConfig cdRebateHistoryResultTableComponentConfig = commonConfig
				.getUIFrameworkComponentConfig(componentId, true,
						cdRebateHistoryResultTableLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.PAGEDTABLE);
		cdRebateHistoryResultTableComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(cdRebateHistoryResultTableComponentConfig);

		GtnUIFrameworkPagedTableConfig cdRebateHistoryResultTableConfig = new GtnUIFrameworkPagedTableConfig();
		cdRebateHistoryResultTableComponentConfig.setGtnPagedTableConfig(cdRebateHistoryResultTableConfig);
		cdRebateHistoryResultTableConfig.setEditable(false);
		cdRebateHistoryResultTableConfig.setFilterBar(true);
		cdRebateHistoryResultTableConfig.setSelectable(true);
		cdRebateHistoryResultTableConfig.setPageLength(10);
		cdRebateHistoryResultTableConfig.setItemPerPage(10);
		cdRebateHistoryResultTableConfig.setSinkItemPerPageWithPageLength(false);
		cdRebateHistoryResultTableConfig
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRebateHistoryColumnType());
		cdRebateHistoryResultTableConfig
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRebateHistoryHeader());
		cdRebateHistoryResultTableConfig
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRebateHistoryColumn());
		cdRebateHistoryResultTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_HISTORY_TABLE_DATA);
		cdRebateHistoryResultTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_HISTORY_TABLE_DATA);
		cdRebateHistoryResultTableConfig.setModuleName("");

	}

	public void addDeatilsLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> populateFieldIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(cdRebateTabPrefix + "_"
				+ GtnWsContractDashboardContants.DETAIL + "_" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdRebateComponentList.add(gtnLayoutConfig);
		massUpdatePanel(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				populateFieldIdList);
		recordLayout(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
		rebateResultPanel(cdRebateComponentList, gtnLayoutConfig.getComponentId());
		addRemoveButtonLayout(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		String componentId = cdRebateTabPrefix + "removeBtn";
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdRebateComponentList.add(layoutConfig);
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				layoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Remove");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig removeButtonCheckSelectedActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeButtonCheckSelectedActionConfig
				.addActionParameter(GtnFrameworkUpdateSelectedRecordInTempTableAction.class.getName());
		removeButtonCheckSelectedActionConfig.addActionParameter(GtnWsContractDashboardContants.CHECK_SELECTED_ITEMS);

		GtnUIFrameWorkActionConfig removeButtonFailureActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		removeButtonFailureActionConfig.addActionParameter("Halt");
		removeButtonFailureActionConfig.addActionParameter("Please check mark a row for removal");

		GtnUIFrameWorkActionConfig removeButtonConfirmActionConfig = new GtnUIFrameWorkActionConfig();
		removeButtonConfirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		removeButtonConfirmActionConfig.addActionParameter("Remove Confirmation");
		removeButtonConfirmActionConfig
				.addActionParameter("Are you sure you want to remove the record from the Contract?");

		GtnUIFrameWorkActionConfig cdRebateRemoveAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdRebateRemoveAction.addActionParameter(GtnFrameworkUpdateSelectedRecordInTempTableAction.class.getName());
		cdRebateRemoveAction.addActionParameter(GtnWsContractDashboardContants.REMOVE_ITEMS);

		GtnUIFrameWorkActionConfig companyChangeIndicatorAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		companyChangeIndicatorAction.addActionParameter(GtnFrameworkItemChangeIndicatorAction.class.getName());

		GtnUIFrameWorkActionConfig cdRebateTabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdRebateTabLoadAction.addActionParameter(GtnFrameworkRebateTabLoadAction.class.getName());
		removeButtonConfirmActionConfig.addActionParameter(
				Arrays.asList(cdRebateRemoveAction, companyChangeIndicatorAction, cdRebateTabLoadAction));

		removeButtonCheckSelectedActionConfig.addActionParameter(removeButtonFailureActionConfig);
		removeButtonCheckSelectedActionConfig.addActionParameter(removeButtonConfirmActionConfig);

		componentConfig.addGtnUIFrameWorkActionConfig(removeButtonCheckSelectedActionConfig);
		addExcelButtonComponent(cdRebateComponentList, cdRebateTabPrefix, layoutConfig.getComponentId());
		addViewExcelButtonComponent(cdRebateComponentList, cdRebateTabPrefix, layoutConfig.getComponentId());
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "excelButton", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Rebate Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE);
		gtnExcelButtonConfig.setExcludeColumnsList(
				Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[0]));
		gtnExcelButtonConfig.setHelperTableMapedPropertyIdList(
				Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[1],
						GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[7]));
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addViewExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "excelButtonView", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Rebate Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE + "View");
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, List<String> populateFieldIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdRebateTabPrefix + "MassUpdatePanel", true, parent);
		gtnPanelConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdRebateTabPrefix + "MassUpdateLayout", true, gtnPanelConfig.getComponentId());
		cdRebateComponentList.add(gtnLayoutConfig);

		List<String> componentIdList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customRebateMassUpdateDisableAction = new GtnUIFrameWorkActionConfig();
		addMassCheck(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				customRebateMassUpdateDisableAction);
		addMassPopulateField(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList, populateFieldIdList);
		addPopulateButtonLayout(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addHiddenField(cdRebateComponentList, gtnLayoutConfig.getComponentId());
		customRebateMassUpdateDisableAction.setFieldValues(componentIdList);
	}

	private void addHiddenField(List<GtnUIFrameworkComponentConfig> componentList, String parent) {
		GtnUIFrameworkComponentConfig hiddenFieldComponentConfig = commonConfig
				.getHorizontalLayoutConfig(GtnFrameworkContractDashboardContants.HIDDEN_RS_LAYOUT, true, parent);
		componentList.add(hiddenFieldComponentConfig);

		GtnUIFrameworkComponentConfig hiddenComponent = commonConfig.getUIFrameworkComponentConfig(
				GtnFrameworkContractDashboardContants.HIDDEN_RS_COMPONENT, true,
				hiddenFieldComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		hiddenComponent.setAuthorizationIncluded(true);

		hiddenComponent.setVisible(false);
		componentList.add(hiddenComponent);
	}

	private void addMassCheck(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent, GtnUIFrameWorkActionConfig customAction) {
		String componentId = cdRebateTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massCheckConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		massCheckConfig.setAuthorizationIncluded(true);
		massCheckConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdRebateComponentList.add(massCheckConfig);

		GtnUIFrameworkOptionGroupConfig massCheckOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		massCheckOptionConfig.setValuesFromService(false);

		massCheckOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.ENABLE, GtnWsContractDashboardContants.DISABLE));
		massCheckOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.DISABLE);
		massCheckConfig.setGtnUIFrameworkOptionGroupConfig(massCheckOptionConfig);
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		massCheckConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addMassPopulateField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList, List<String> populateFieldIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				cdRebateTabPrefix + "PopulateField" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig populateFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "PopulateField_default_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		populateFieldConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(populateFieldConfig);
		populateFieldConfig.setEnable(false);
		GtnUIFrameworkComboBoxConfig populateFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		populateFieldComboBoxConfig
				.setItemValues(Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldDefault()));
		populateFieldConfig.setGtnComboboxConfig(populateFieldComboBoxConfig);
		componentIdList.add(populateFieldConfig.getComponentId());
		populateFieldIdList.add(populateFieldConfig.getComponentId());

		GtnUIFrameworkComponentConfig formulaFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "PopulateField_Formula_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		formulaFieldConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(formulaFieldConfig);
		formulaFieldConfig.setEnable(false);
		formulaFieldConfig.setVisible(false);
		GtnUIFrameworkComboBoxConfig formulaFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		formulaFieldComboBoxConfig
				.setItemValues(Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()));
		formulaFieldConfig.setGtnComboboxConfig(formulaFieldComboBoxConfig);
		componentIdList.add(formulaFieldConfig.getComponentId());
		populateFieldIdList.add(formulaFieldConfig.getComponentId());

		GtnUIFrameworkComponentConfig rebateFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "PopulateField_RebatePlan_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateFieldConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(rebateFieldConfig);
		rebateFieldConfig.setEnable(false);
		rebateFieldConfig.setVisible(false);
		GtnUIFrameworkComboBoxConfig rebateFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		rebateFieldComboBoxConfig.setItemValues(
				Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldRebatePlan()));
		rebateFieldConfig.setGtnComboboxConfig(rebateFieldComboBoxConfig);
		componentIdList.add(rebateFieldConfig.getComponentId());
		populateFieldIdList.add(rebateFieldConfig.getComponentId());

		GtnUIFrameworkComponentConfig deductionFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "PopulateField_DeductionCalendar_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		deductionFieldConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(deductionFieldConfig);
		deductionFieldConfig.setEnable(false);
		deductionFieldConfig.setVisible(false);
		GtnUIFrameworkComboBoxConfig deductionFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		deductionFieldComboBoxConfig.setItemValues(
				Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldDeduction()));
		deductionFieldConfig.setGtnComboboxConfig(deductionFieldComboBoxConfig);
		componentIdList.add(deductionFieldConfig.getComponentId());
		populateFieldIdList.add(deductionFieldConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter("4");
		customAction.addActionParameter("2");

		populateFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);
		populateFieldConfig.addGtnUIFrameWorkActionConfig(customAction);
		formulaFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);
		formulaFieldConfig.addGtnUIFrameWorkActionConfig(customAction);
		rebateFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);
		rebateFieldConfig.addGtnUIFrameWorkActionConfig(customAction);
		deductionFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);
		deductionFieldConfig.addGtnUIFrameWorkActionConfig(customAction);

		GtnUIFrameworkValidationConfig val1Config = new GtnUIFrameworkValidationConfig();
		val1Config.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		addComboField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[0],
				gtnLayoutConfig.getComponentId(), valConfig, GtnWsContractDashboardContants.STATUS);
		addField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[1],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD, valConfig);
		addField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[2],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD, valConfig);

		addComboField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[3],
				gtnLayoutConfig.getComponentId(), valConfig, "");
		GtnUIFrameworkComponentConfig formulaCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[4],
				gtnLayoutConfig.getComponentId(), val1Config);
		formulaCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(formulaCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW, "Formula No Lookup", "formulaNo", null));

		GtnUIFrameworkComponentConfig nsFormulaCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[5],
				gtnLayoutConfig.getComponentId(), val1Config);
		nsFormulaCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(nsFormulaCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW, NET_SALES_FORMULA_LOOKUP, "formulaName",
				null));
		GtnUIFrameworkComponentConfig nsRuleCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[6],
				gtnLayoutConfig.getComponentId(), val1Config);
		nsRuleCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(nsRuleCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW, "Net Sales Rule Lookup", RULE_NO, "Net Sales"));
		GtnUIFrameworkComponentConfig evaRuleCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[7],
				gtnLayoutConfig.getComponentId(), val1Config);
		evaRuleCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(evaRuleCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW, "Evaluation Rule Lookup", RULE_NO,
				"Evaluation"));
		GtnUIFrameworkComponentConfig calcRuleCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[8],
				gtnLayoutConfig.getComponentId(), val1Config);
		calcRuleCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(calcRuleCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW, "Calculation Rule Lookup", RULE_NO,
				"Calculation"));
		addField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[9],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX, val1Config);
		addField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldFormula()[10],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX, val1Config);
		addField(cdRebateComponentList, componentIdList, cdRebateTabPrefix,
				GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldRebatePlan()[3],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX, val1Config);
		GtnUIFrameworkComponentConfig rebateCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldRebatePlan()[4],
				gtnLayoutConfig.getComponentId(), val1Config);
		rebateCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(rebateCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW, "Rebate Plan ID Lookup", "rebatePlanNo", null));
		GtnUIFrameworkComponentConfig dcCompConfig = addLookUpField(cdRebateComponentList, componentIdList,
				cdRebateTabPrefix, GtnFrameworkContractDashboardContants.getRebateDetailsMassFieldDeduction()[3],
				gtnLayoutConfig.getComponentId(), val1Config);
		dcCompConfig.addGtnUIFrameWorkActionConfig(getPopupActionConfig(dcCompConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW, "Deduction Calendar Lookup", "dcNo", null));

	}

	private void addPopulateButtonLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdRebateTabPrefix + "PopulateButtonlayout", true, parent);
		cdRebateComponentList.add(layoutConfig);
		addPopulateButtonComponent(cdRebateComponentList, cdRebateTabPrefix, layoutConfig.getComponentId(),
				componentIdList);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig populateButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "PopulateButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateButtonConfig.setAuthorizationIncluded(true);
		populateButtonConfig.setComponentName("Populate");
		populateButtonConfig.setEnable(false);
		cdRebateComponentList.add(populateButtonConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkRebatePopulateAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter("4");
		customAction.addActionParameter("2");
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		customAction.addActionParameter(cdRebateTabPrefix + COMBO_CALCULATION_TYPE);
		populateButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateButtonConfig.getComponentId());
		GtnUIFrameworkComponentConfig populateAllButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "PopulateAllButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateAllButtonConfig.setComponentName("Populate All");
		populateAllButtonConfig.setEnable(false);
		cdRebateComponentList.add(populateAllButtonConfig);
		populateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateAllButtonConfig.getComponentId());
	}

	private void recordLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "record";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(componentId + "Css" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig layoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnLayoutConfig.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdRebateComponentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				layoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Record:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdRebateComponentList.add(componentConfig);

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
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkRebateTabTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_RS_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(componentConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.HIDDEN_RS_COMPONENT));
		componentConfig.addGtnUIFrameWorkActionConfig(tableLoadActionConfig);
	}

	private void rebateResultPanel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE;
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		gtnPanelConfig.setComponentName("Results");
		gtnPanelConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(componentConfig);
		GtnUIFrameworkPagedTableConfig cdRebateHistoryResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdRebateHistoryResultTable);
		cdRebateHistoryResultTable.setEditable(true);
		cdRebateHistoryResultTable.setFilterBar(true);
		cdRebateHistoryResultTable.setSelectable(false);
		cdRebateHistoryResultTable.setPageLength(5);
		cdRebateHistoryResultTable.setItemPerPage(5);
		cdRebateHistoryResultTable.setSinkItemPerPageWithPageLength(false);
		cdRebateHistoryResultTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRebateDetailsColumnType());
		cdRebateHistoryResultTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRebateDetailsColumnHeader());
		cdRebateHistoryResultTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRebateDetailsColumn());
		cdRebateHistoryResultTable
				.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.REBATES_HELPER_VALUE });
		cdRebateHistoryResultTable.setExtraColumnDataType(new Class[] { String.class });
		cdRebateHistoryResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_REBATE_DETAIL_TABLE_DATA);
		cdRebateHistoryResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_REBATE_DETAIL_TABLE_DATA);
		cdRebateHistoryResultTable
				.setColumnCheckBoxId(GtnFrameworkContractDashboardContants.getRebateDetailsColumn()[0]);
		cdRebateHistoryResultTable
				.setInvisibleFilterPropertyIds(GtnFrameworkContractDashboardContants.getRebateDetailsColumn()[0]);
		cdRebateHistoryResultTable.setCustomFilterConfigMap(getRebateTableFilterFieldMap());
		cdRebateHistoryResultTable.setEditableColumnList(
				Arrays.asList(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()));
		List<GtnUIFrameworkComponentConfig> editableList = new ArrayList<>();
		getTableEditableField(editableList);
		cdRebateHistoryResultTable.setEditableField(editableList);
		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkTableCheckAllAction.class.getName());
		checkAllAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		cdRebateHistoryResultTable.addColumnCheckActionConfig(checkAllAction);
		rebateViewResultTable(cdRebateComponentList, gtnLayoutConfig.getComponentId());
	}

	private void rebateViewResultTable(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE + "View";

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdRebateHistoryResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdRebateHistoryResultTable);
		cdRebateHistoryResultTable.setPageLength(10);
		cdRebateHistoryResultTable.setItemPerPage(10);
		cdRebateHistoryResultTable.setSinkItemPerPageWithPageLength(false);
		cdRebateHistoryResultTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRebateDetailsViewColumnType());
		cdRebateHistoryResultTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRebateDetailsViewHeader());
		cdRebateHistoryResultTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRebateDetailsViewColumn());
		cdRebateHistoryResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_REBATE_DETAIL_VIEW_TABLE_DATA);
		cdRebateHistoryResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_REBATE_DETAIL_VIEW_TABLE_DATA);
	}

	private void getTableEditableField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList) {
		GtnUIFrameworkComponentConfig checkRecordConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.CHECKBOX);
		cdRebateComponentList.add(checkRecordConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkFieldFactoryAction.class.getName());
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM_FIELD);
		customAction.addActionParameter(24);
		checkRecordConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(customAction));

		GtnUIFrameWorkActionConfig focusAction = new GtnUIFrameWorkActionConfig();
		focusAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		focusAction.addActionParameter(GtnUIFrameworkFocusAction.class.getName());

		addFieldFactoryComboField(cdRebateComponentList, customAction, GtnWsContractDashboardContants.STATUS,
				focusAction);
		addFieldFactoryField(cdRebateComponentList, GtnUIFrameworkComponentType.DATEFIELD, customAction, focusAction);
		addFieldFactoryField(cdRebateComponentList, GtnUIFrameworkComponentType.DATEFIELD, customAction, focusAction);

		addFieldFactoryField(cdRebateComponentList, GtnUIFrameworkComponentType.TEXTBOX, customAction, focusAction);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);
		GtnUIFrameworkComponentConfig rebatePlanNamecomponentConfig = commonConfig.getUIFrameworkComponentConfig("",
				false, null, GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNamecomponentConfig.setEnable(false);
		cdRebateComponentList.add(rebatePlanNamecomponentConfig);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);

		addFieldFactoryComboField(cdRebateComponentList, customAction, "", focusAction);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);
		addFieldFactoryField(cdRebateComponentList, GtnUIFrameworkComponentType.TEXTBOX, customAction, focusAction);
		addFieldFactoryLookUpField(cdRebateComponentList, customAction);
		addFieldFactoryField(cdRebateComponentList, GtnUIFrameworkComponentType.TEXTBOX, customAction, focusAction);
	}

	private void addProcessingLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnHeaderLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdRebateTabPrefix + "_" + GtnWsContractDashboardContants.PROCESSING_OPTION.replace(" ", "") + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT,
				true, parent);
		gtnHeaderLayoutConfig.setVisible(false);
		gtnHeaderLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnHeaderLayoutConfig);

		GtnUIFrameworkComponentConfig gtnCurrentLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdRebateTabPrefix + "Decission_" + GtnWsContractDashboardContants.PROCESSING_OPTION.replace(" ", "")
						+ "_Current_Pending_Layout",
				true, gtnHeaderLayoutConfig.getComponentId());
		gtnCurrentLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnCurrentLayoutConfig);
		addProcessingFieldComponent(cdRebateComponentList, cdRebateTabPrefix, gtnCurrentLayoutConfig.getComponentId(),
				componentIdList);
		addProcessingDualListBoxPanel(cdRebateComponentList, cdRebateTabPrefix,
				gtnCurrentLayoutConfig.getComponentId());
		rebateHistoryResultPanel(cdRebateComponentList, cdRebateTabPrefix, gtnHeaderLayoutConfig.getComponentId());
	}

	private void addProcessingFieldComponent(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdRebateTabPrefix + "_ProcessingField_Panel", true, parent);
		gtnPanelConfig.setComponentName("Processing Option");
		cdRebateComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnVerticalLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdRebateTabPrefix + "Processing_verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnVerticalLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(gtnVerticalLayoutConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdRebateTabPrefix + "CssLayout_ProcessingField", true, gtnVerticalLayoutConfig.getComponentId());
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdRebateComponentList.add(gtnLayoutConfig);

		addRebateProcessingType(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addValidationProfile(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);

		addProcessInterestBearingIndicator(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addProcessInterestBearingBasis(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void addRebateProcessingType(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_RebateProcessingType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Processing Type");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.REBATE_PROCESSING_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addValidationProfile(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_ValidationProfile";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Validation Profile");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_VALIDATION_PROFILE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addProcessInterestBearingIndicator(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_ProcessInterestBearingIndicator";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Interest Bearing Indicator");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.INTEREST_BEARING_INDICATOR);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addProcessInterestBearingBasis(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdRebateTabPrefix + "Combo_ProcessInterestBearingBasis";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Interest Bearing Basis");
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.INTEREST_BEARING_BASIS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addProcessingDualListBoxPanel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdRebateTabPrefix + "_ProcessingDualListBox_Panel", true, parent);
		gtnPanelConfig.setComponentName("Transaction Rules");
		cdRebateComponentList.add(gtnPanelConfig);

		addDualListBoxLayout(cdRebateComponentList, cdRebateTabPrefix, gtnPanelConfig.getComponentId());
	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		String componentId = cdRebateTabPrefix + "dualListBox";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		leftResultDataTable(cdRebateComponentList, gtnLayoutConfig.getComponentId());
		moveButtonsLayout(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
		rightResultDataTable(cdRebateComponentList, gtnLayoutConfig.getComponentId());
	}

	private void moveButtonsLayout(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				cdRebateTabPrefix + "dualListBoxButton" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);
		gtnLayoutConfig.setMargin(true);
		gtnLayoutConfig.setSpacing(true);
		gtnLayoutConfig.addComponentStyle("gtnGrid-DualList-Buttons");
		moveRight(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
		moveLeft(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
		moveAllRight(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
		moveAllLeft(cdRebateComponentList, cdRebateTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "moveRightBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setComponentName(" > ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdRebateComponentList.add(componentConfig);
	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "moveLeftBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" < ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdRebateComponentList.add(componentConfig);
	}

	private void moveAllRight(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "moveRightBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" >> ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdRebateComponentList.add(componentConfig);
	}

	private void moveAllLeft(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String cdRebateTabPrefix,
			String parent) {
		String componentId = cdRebateTabPrefix + "moveLeftBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName(" << ");
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		cdRebateComponentList.add(componentConfig);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.REBATE_TAB_AVL_RULE_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_530);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdRebateLeftResultDataTableConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		cdRebateLeftResultDataTableConfig.setComponentName("Available Rules");
		cdRebateLeftResultDataTableConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdRebateLeftResultDataTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_250);
		cdRebateComponentList.add(cdRebateLeftResultDataTableConfig);
		GtnUIFrameworkPagedTableConfig cdRebateLeftResultDataTable = new GtnUIFrameworkPagedTableConfig();
		cdRebateLeftResultDataTableConfig.setGtnPagedTableConfig(cdRebateLeftResultDataTable);
		cdRebateLeftResultDataTable.setSelectable(true);
		cdRebateLeftResultDataTable.setSinkItemPerPageWithPageLength(false);
		cdRebateLeftResultDataTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRebateProcessRulesColumnType());
		cdRebateLeftResultDataTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRebateProcessRulesHeader());
		cdRebateLeftResultDataTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRebateProcessRulesColumn());
		cdRebateLeftResultDataTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_LEFT_TABLE_DATA);
		cdRebateLeftResultDataTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_LEFT_TABLE_DATA);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.REBATE_TAB_SEL_RULE_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_530);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdRebateRightResultDataTableConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		cdRebateRightResultDataTableConfig.setAuthorizationIncluded(true);
		cdRebateRightResultDataTableConfig.setComponentName("Selected Rules");
		cdRebateRightResultDataTableConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdRebateRightResultDataTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_250);
		cdRebateComponentList.add(cdRebateRightResultDataTableConfig);
		GtnUIFrameworkPagedTableConfig cdRebateRightResultDataTable = new GtnUIFrameworkPagedTableConfig();
		cdRebateRightResultDataTableConfig.setGtnPagedTableConfig(cdRebateRightResultDataTable);
		cdRebateRightResultDataTable.setSelectable(true);
		cdRebateRightResultDataTable.setSinkItemPerPageWithPageLength(false);
		cdRebateRightResultDataTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRebateProcessRulesColumnType());
		cdRebateRightResultDataTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRebateProcessRulesHeader());
		cdRebateRightResultDataTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRebateProcessRulesColumn());
		cdRebateRightResultDataTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA);
		cdRebateRightResultDataTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA);

	}

	private void addField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList, List<String> componentIdList,
			String cdRebateTabPrefix, String componentId, String parent, GtnUIFrameworkComponentType componentType,
			GtnUIFrameworkValidationConfig valConfig) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "_" + componentId.replace(" ", "") + "_", true, parent, componentType);
		componentConfig.setVisible(false);
		cdRebateComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private GtnUIFrameworkComponentConfig addComboField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			List<String> componentIdList, String cdRebateTabPrefix, String componentId, String parent,
			GtnUIFrameworkValidationConfig valConfig, String listName) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "_" + componentId.replace(" ", "") + "_", true, parent,
				GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setVisible(false);
		cdRebateComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(listName);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		return componentConfig;
	}

	private GtnUIFrameworkComponentConfig addLookUpField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			List<String> componentIdList, String cdRebateTabPrefix, String componentId, String parent,
			GtnUIFrameworkValidationConfig valConfig) {

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdRebateTabPrefix + "_" + componentId.replace(" ", "") + "_" + "popup", true, parent,
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setVisible(false);
		cdRebateComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(false, false, true);
		textboxConfig.setReadOnly(true);
		componentConfig.setGtnTextBoxConfig(textboxConfig);
		return componentConfig;
	}

	private GtnUIFrameWorkActionConfig getPopupActionConfig(String componentId, String lookUpView, String lookUpName,
			String lookUpColumn, String lookUpfieldValue) {
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(lookUpView);
		popupActionConfig.addActionParameter(lookUpName);
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(Arrays.asList(componentId, Arrays.asList(lookUpColumn),
				Arrays.asList(componentId), null, lookUpfieldValue));
		return popupActionConfig;
	}

	private void addFieldFactoryField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			GtnUIFrameworkComponentType componentType, GtnUIFrameWorkActionConfig customAction,
			GtnUIFrameWorkActionConfig focusAction) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				componentType);
		cdRebateComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		componentConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
	}

	private void addFieldFactoryComboField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			GtnUIFrameWorkActionConfig customAction, String listName, GtnUIFrameWorkActionConfig focusAction) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(componentConfig);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(listName);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		componentConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
	}

	private void addFieldFactoryLookUpField(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			GtnUIFrameWorkActionConfig customAction) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		componentConfig.setAuthorizationIncluded(true);
		cdRebateComponentList.add(componentConfig);
		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		componentConfig.setGtnTextBoxConfig(textboxConfig);
		componentConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(customAction));
	}

	private void rebateHistoryResultPanel(List<GtnUIFrameworkComponentConfig> cdRebateComponentList,
			String cdRebateTabPrefix, String parent) {
		String componentId = cdRebateTabPrefix + "_" + GtnWsContractDashboardContants.PROCESSING_OPTION.replace(" ", "")
				+ "_" + GtnWsContractDashboardContants.HISTORY + RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdRebateComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdRebateComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdRebateHistoryResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdRebateHistoryResultTable);
		cdRebateHistoryResultTable.setEditable(false);
		cdRebateHistoryResultTable.setFilterBar(true);
		cdRebateHistoryResultTable.setSelectable(true);
		cdRebateHistoryResultTable.setPageLength(10);
		cdRebateHistoryResultTable.setItemPerPage(10);
		cdRebateHistoryResultTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRebateProHistoryColumnType());
		cdRebateHistoryResultTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRebateProHistoryHeader());
		cdRebateHistoryResultTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRebateProHistoryColumn());
		cdRebateHistoryResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_HISTORY_TABLE_DATA);
		cdRebateHistoryResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_HISTORY_TABLE_DATA);
		cdRebateHistoryResultTable.setModuleName("");

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getRebateTableFilterFieldMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[1],
				getFilterComboField(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[1],
						GtnWsContractDashboardContants.STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[7],
				getFilterComboField(GtnFrameworkContractDashboardContants.getRebateDetailsColumnEditable()[7], ""));
		return customFilterConfigMap;
	}

	private GtnUIFrameworkPagedTableCustomFilterConfig getFilterComboField(String propertyId, String listName) {
		GtnUIFrameworkPagedTableCustomFilterConfig cfpStatusFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		GtnUIFrameworkComponentConfig cfpStatusConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpStatusConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkComboBoxConfig cfpStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		cfpStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpStatusComboBoxConfig.setComboBoxType(listName);
		cfpStatusConfig.setGtnComboboxConfig(cfpStatusComboBoxConfig);
		cfpStatusFilterConfig.setPropertId(propertyId);
		cfpStatusFilterConfig.setGtnComponentConfig(cfpStatusConfig);
		cfpStatusFilterConfig.setGtnComponentType(cfpStatusConfig.getComponentType());
		return cfpStatusFilterConfig;
	}
}
