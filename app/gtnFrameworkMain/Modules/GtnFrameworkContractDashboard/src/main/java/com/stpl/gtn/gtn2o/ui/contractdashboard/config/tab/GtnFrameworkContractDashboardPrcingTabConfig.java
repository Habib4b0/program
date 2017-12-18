package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyPopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractPriceProtectionTabAddLineAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkPricingTableExcelExportAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameWorkContractTableRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkBasePriceValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkFieldEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPopulateFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPriceProtectionTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPricingTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryComponentCreationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnUIFrameworkFocusAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkPricingTabLoadAction;
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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardPrcingTabConfig {
	private static final String BASE_PRICE = "BasePrice";
	private static final String DATE_FORMAT = "MM/dd/YYYY";
	private static final String MASS_UPDATE_PANEL = "MassUpdatePanel";
	private static final String TEXT_PPS_NAME = "Text_PPSName";
	private static final String TEXT_PPSID = "Text_PPSID";
	private static final String EXCEL_BUTTON = "excelButton";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdPricingTabPrefix = mainNamspacePrefix + "PT";
		GtnUIFrameworkTabConfig cdPrcingTabConfig = new GtnUIFrameworkTabConfig();
		cdPrcingTabConfig.setComponentId(cdPricingTabPrefix + "prcingTab");
		cdPrcingTabConfig.setTabCaption("Pricing");
		List<GtnUIFrameworkComponentConfig> cdPricingComponentList = new ArrayList<>();
		cdPrcingTabConfig.setTabLayoutComponentConfigList(cdPricingComponentList);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getVerticalLayoutConfig(cdPrcingTabConfig.getComponentId(), true, cdPrcingTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		cdPricingComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addContractHeaderDetailDecissionLayout(cdPricingComponentList, cdPricingTabPrefix,
				gtnLayoutConfig.getComponentId());
		addContractHeaderFieldPanel(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDeatilsLayout(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
		addPricingLayout(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPricingTabLoadAction.class.getName());
		customAction.addActionParameter(cdPricingTabPrefix + "DecissionLevel");
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE);
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(cdPricingTabPrefix + MASS_UPDATE_PANEL);
		customAction.addActionParameter(cdPricingTabPrefix + "PPlvlMassUpdatePanel");
		customAction.addActionParameter(cdPricingTabPrefix + EXCEL_BUTTON);
		customAction.addActionParameter(cdPricingTabPrefix + "PPlvlexcelButton");

		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(customAction);
		return cdPrcingTabConfig;
	}

	private void addContractHeaderDetailDecissionLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdPricingTabPrefix + "HeaderDetailDecissionLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cdPricingComponentList.add(gtnLayoutConfig);

		addDecissionLevel(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
		addDecissionView(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addContractHeaderFieldPanel(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdPricingTabPrefix + "Decission_Header_FieldPanel", true, parent);
		cdPricingComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdPricingTabPrefix + "verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(gtnLayoutConfig);

		addHeaderFieldComponent(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		itemsTabHistoryDataTableComponent(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addDecissionLevel(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		String componentId = cdPricingTabPrefix + "DecissionLevel";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig decissionLevelConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		decissionLevelConfig.setAuthorizationIncluded(true);
		decissionLevelConfig.setComponentName("Level:");
		decissionLevelConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdPricingComponentList.add(decissionLevelConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.HEADER,
				GtnWsContractDashboardContants.DETAIL, GtnWsContractDashboardContants.PRICE_PROTECTION));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.HEADER);

		decissionLevelConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		customAction.addActionParameter(Arrays.asList(cdPricingTabPrefix + "Decission_Header_FieldPanel",
				cdPricingTabPrefix + "Decission_Header_View",
				cdPricingTabPrefix + "_" + GtnWsContractDashboardContants.DETAIL + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT,
				cdPricingTabPrefix + "_" + GtnWsContractDashboardContants.PRICE_PROTECTION.replace(" ", "") + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		decissionLevelConfig.addGtnUIFrameWorkActionConfig(customAction);
		GtnUIFrameWorkActionConfig tabLoadCustomAction = new GtnUIFrameWorkActionConfig();
		tabLoadCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadCustomAction.addActionParameter(GtnFrameworkPricingTabLoadAction.class.getName());
		decissionLevelConfig.addGtnUIFrameWorkActionConfig(tabLoadCustomAction);
	}

	private void addDecissionView(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent) {
		String componentId = cdPricingTabPrefix + "Decission_Header_View";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("View:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(Arrays.asList(GtnWsContractDashboardContants.HISTORY,
				GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.PENDING));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.CURRENT);

		componentConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		customAction.addActionParameter(Arrays.asList(
				cdPricingTabPrefix + "CssLayout_" + GtnWsContractDashboardContants.CURRENT + "_"
						+ GtnWsContractDashboardContants.PENDING + "_",
				cdPricingTabPrefix + "_" + GtnWsContractDashboardContants.HISTORY + "_ResultTable"
						+ GtnFrameworkCommonStringConstants.LAYOUT));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addHeaderFieldComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdPricingTabPrefix + "CssLayout_" + GtnWsContractDashboardContants.CURRENT + "_"
						+ GtnWsContractDashboardContants.PENDING + "_", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdPricingComponentList.add(gtnLayoutConfig);

		addPriceScheduleId(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceScheduleNo(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceScheduleName(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceScheduleStatus(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceScheduleStartDate(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceScheduleEndDate(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPriceScheduleDesignation(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addParentPsId(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addParentPsName(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addPriceScheduleType(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addCreatedBy(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCreatedDate(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addPriceScheduleCategory(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addModifiedBy(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addModifiedDate(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addPsTradeClass(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
	}

	private void addPriceScheduleId(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Text_PriceScheduleID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule ID");
		componentConfig.setEnable(false);
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPriceScheduleNo(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Text_PriceScheduleNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule No");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addPriceScheduleName(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdPricingTabPrefix + "Text_PriceScheduleName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Name");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPriceScheduleStatus(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Combo_PriceScheduleStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Status");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdPricingComponentList.add(componentConfig);

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

	private void addPriceScheduleStartDate(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {

		String componentId = cdPricingTabPrefix + "PriceScheduleStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdPriceScheduleStartDateConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		cdPriceScheduleStartDateConfig.setAuthorizationIncluded(true);
		cdPriceScheduleStartDateConfig.setComponentName("Price Schedule Start Date");
		cdPriceScheduleStartDateConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdPricingComponentList.add(cdPriceScheduleStartDateConfig);
		componentIdList.add(cdPriceScheduleStartDateConfig.getComponentId());

		GtnUIFrameWorkActionConfig priceScheduleStartDateBeforeValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		priceScheduleStartDateBeforeValidationAction.addActionParameter("CDProcessView_ITIFPEndDate");
		priceScheduleStartDateBeforeValidationAction.addActionParameter(1);
		priceScheduleStartDateBeforeValidationAction.addActionParameter("Select PS Start date before ?");
		priceScheduleStartDateBeforeValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig priceScheduleStartDateAfterValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		priceScheduleStartDateAfterValidationAction.addActionParameter("CDProcessView_ITIFPStartDate");
		priceScheduleStartDateAfterValidationAction.addActionParameter(-1);
		priceScheduleStartDateAfterValidationAction.addActionParameter("Select PS Start date After ?");
		priceScheduleStartDateAfterValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameworkValidationConfig priceScheduleStartDateValConfig = new GtnUIFrameworkValidationConfig();
		priceScheduleStartDateValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdPriceScheduleStartDateConfig.setGtnUIFrameworkValidationConfig(priceScheduleStartDateValConfig);
		GtnUIFrameWorkActionConfig priceScheduleStartDateValidationAction = new GtnUIFrameWorkActionConfig();
		priceScheduleStartDateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		priceScheduleStartDateValidationAction
				.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		priceScheduleStartDateValidationAction.addActionParameter(cdPriceScheduleStartDateConfig.getComponentId());
		priceScheduleStartDateValidationAction.addActionParameter(new ArrayList<String>());
		priceScheduleStartDateValidationAction
				.addActionParameter(Arrays.asList(cdPricingTabPrefix + "PriceScheduleEndDate"));
		priceScheduleStartDateValidationAction.addActionParameter("Start Date");
		priceScheduleStartDateValidationAction.addActionParameter("Start");
		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(priceScheduleStartDateValidationAction);
		dateFieldConfig.setValidationActionConfigList(Arrays.asList(priceScheduleStartDateBeforeValidationAction,
				priceScheduleStartDateAfterValidationAction));
		cdPriceScheduleStartDateConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addPriceScheduleEndDate(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "PriceScheduleEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdPriceScheduleEndDateConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		cdPriceScheduleEndDateConfig.setAuthorizationIncluded(true);
		cdPriceScheduleEndDateConfig.setComponentName("Price Schedule End Date");
		cdPricingComponentList.add(cdPriceScheduleEndDateConfig);
		componentIdList.add(cdPriceScheduleEndDateConfig.getComponentId());

		GtnUIFrameWorkActionConfig cdPriceScheduleEndDateBeforeValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		cdPriceScheduleEndDateBeforeValidationAction.addActionParameter("CDProcessView_ITIFPEndDate");
		cdPriceScheduleEndDateBeforeValidationAction.addActionParameter(1);
		cdPriceScheduleEndDateBeforeValidationAction.addActionParameter("Select PS End date before ?");
		cdPriceScheduleEndDateBeforeValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig cdPriceScheduleEndDateAfterValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		cdPriceScheduleEndDateAfterValidationAction.addActionParameter("CDProcessView_ITIFPStartDate");
		cdPriceScheduleEndDateAfterValidationAction.addActionParameter(-1);
		cdPriceScheduleEndDateAfterValidationAction.addActionParameter("Select PS End date After ?");
		cdPriceScheduleEndDateAfterValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig cdPriceScheduleEndDateValidationAction = new GtnUIFrameWorkActionConfig();
		cdPriceScheduleEndDateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPriceScheduleEndDateValidationAction
				.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		cdPriceScheduleEndDateValidationAction.addActionParameter(cdPriceScheduleEndDateConfig.getComponentId());
		cdPriceScheduleEndDateValidationAction
				.addActionParameter(Arrays.asList(cdPricingTabPrefix + "PriceScheduleStartDate"));
		cdPriceScheduleEndDateValidationAction.addActionParameter(new ArrayList<String>());
		cdPriceScheduleEndDateValidationAction.addActionParameter("End Date");
		cdPriceScheduleEndDateValidationAction.addActionParameter("End");

		GtnUIFrameworkDateFieldConfig cdPriceScheduleEndDateDateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		cdPriceScheduleEndDateDateFieldConfig.addValueChangeActionConfig(cdPriceScheduleEndDateValidationAction);
		cdPriceScheduleEndDateDateFieldConfig.setValidationActionConfigList(Arrays
				.asList(cdPriceScheduleEndDateBeforeValidationAction, cdPriceScheduleEndDateAfterValidationAction));
		cdPriceScheduleEndDateConfig.setGtnDateFieldConfig(cdPriceScheduleEndDateDateFieldConfig);
	}

	private void addPriceScheduleDesignation(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Combo_PriceScheduleDesignation";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Designation");
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PS_DESIGNATION);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkFieldEnableDisableAction.class.getName());
		customAction.addActionParameter(componentConfig.getComponentId());
		customAction.addActionParameter(true);
		customAction.addActionParameter("child");
		customAction.setFieldValues(Arrays.asList(cdPricingTabPrefix + TEXT_PPSID, cdPricingTabPrefix + TEXT_PPS_NAME));
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addParentPsId(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + TEXT_PPSID;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Parent Price Schedule ID");
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		textboxConfig.setEnable(false);
		componentConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_PS_VIEW);
		popupActionConfig.addActionParameter("Parent Price Schedule Lookup");
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig
				.addActionParameter(Arrays.asList(componentConfig.getComponentId(), Arrays.asList("psId", "psName"),
						Arrays.asList(cdPricingTabPrefix + TEXT_PPSID, cdPricingTabPrefix + TEXT_PPS_NAME), null));
		componentConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
	}

	private void addParentPsName(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + TEXT_PPS_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Parent Price Schedule Name");
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		textboxConfig.setEnable(false);
		componentConfig.setGtnTextBoxConfig(textboxConfig);
	}

	private void addPriceScheduleType(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Combo_PriceScheduleType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Type");
		cdPricingComponentList.add(componentConfig);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PS_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Text_CreatedBy";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Created By");
		componentConfig.setEnable(false);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "CreatedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Created Date");
		componentConfig.setEnable(false);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPriceScheduleCategory(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Combo_PriceScheduleCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Category");
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PS_CATEGORY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Text_ModifiedBy";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Modified By");
		componentConfig.setEnable(false);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "ModifiedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Modified Date");
		componentConfig.setEnable(false);
		cdPricingComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addPsTradeClass(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "Combo_PriceScheduleTradeClass";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Trade Class");
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PS_TRADE_CLASS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void itemsTabHistoryDataTableComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		String componentId = cdPricingTabPrefix + "_" + GtnWsContractDashboardContants.HISTORY + "_ResultTable";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setPageLength(10);
		attachResults.setItemPerPage(10);
		attachResults.setSinkItemPerPageWithPageLength(false);
		attachResults.setTableColumnDataType(GtnFrameworkContractDashboardContants.getPricingHistoryColumnType());
		attachResults.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getPricingHistoryHeader());
		attachResults.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getPricingHistoryColumn());
		attachResults.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_HISTORY_TABLE_DATA);
		attachResults.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_HISTORY_TABLE_DATA);
		attachResults.setModuleName("");

	}

	public void addDeatilsLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent) {

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(cdPricingTabPrefix + "_"
				+ GtnWsContractDashboardContants.DETAIL + "_" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdPricingComponentList.add(gtnLayoutConfig);
		massUpdatePanel(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
		recordLayout(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
		psResultPanel(cdPricingComponentList, gtnLayoutConfig.getComponentId());
		addExcelButtonLayout(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdPricingTabPrefix + MASS_UPDATE_PANEL, true, parent);
		cdPricingComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdPricingTabPrefix + "MassUpdateLayout", true, gtnPanelConfig.getComponentId());
		cdPricingComponentList.add(gtnLayoutConfig);

		List<String> componentIdList = new ArrayList<>();
		GtnUIFrameWorkActionConfig cdPricingMassEnableDisableAction = new GtnUIFrameWorkActionConfig();
		addMassCheck(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				cdPricingMassEnableDisableAction);
		addMassPopulateField(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPopulateButtonLayout(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addHiddenField(cdPricingComponentList, gtnLayoutConfig.getComponentId());
		cdPricingMassEnableDisableAction.setFieldValues(componentIdList);
	}

	private void addHiddenField(List<GtnUIFrameworkComponentConfig> componentList, String parent) {
		GtnUIFrameworkComponentConfig hiddenFieldComponentConfig = commonConfig
				.getHorizontalLayoutConfig(GtnFrameworkContractDashboardContants.HIDDEN_PS_LAYOUT, true, parent);
		componentList.add(hiddenFieldComponentConfig);

		GtnUIFrameworkComponentConfig hiddenComponent = commonConfig.getUIFrameworkComponentConfig(
				GtnFrameworkContractDashboardContants.HIDDEN_PS_COMPONENT, true,
				hiddenFieldComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		hiddenComponent.setAuthorizationIncluded(true);

		hiddenComponent.setVisible(false);
		componentList.add(hiddenComponent);
	}

	private void addMassCheck(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent, GtnUIFrameWorkActionConfig cdPricingMassEnableDisableAction) {
		String componentId = cdPricingTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdPricingMassCheckConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		cdPricingMassCheckConfig.setAuthorizationIncluded(true);
		cdPricingMassCheckConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdPricingComponentList.add(cdPricingMassCheckConfig);

		GtnUIFrameworkOptionGroupConfig cdPricingMassCheckOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		cdPricingMassCheckOptionConfig.setValuesFromService(false);

		cdPricingMassCheckOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.ENABLE, GtnWsContractDashboardContants.DISABLE));
		cdPricingMassCheckOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.DISABLE);
		cdPricingMassCheckConfig.setGtnUIFrameworkOptionGroupConfig(cdPricingMassCheckOptionConfig);
		cdPricingMassEnableDisableAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		cdPricingMassCheckConfig.addGtnUIFrameWorkActionConfig(cdPricingMassEnableDisableAction);
	}

	private void addMassPopulateField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "PopulateField";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdPricingPopulateFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdPricingPopulateFieldConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(cdPricingPopulateFieldConfig);
		cdPricingPopulateFieldConfig.setEnable(false);
		GtnUIFrameworkComboBoxConfig populateFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		populateFieldComboBoxConfig
				.setItemValues(Arrays.asList(GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()));
		cdPricingPopulateFieldConfig.setGtnComboboxConfig(populateFieldComboBoxConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdPricingPopulateFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig cdPricingPopulateValueChangeAction = new GtnUIFrameWorkActionConfig();
		cdPricingPopulateValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPricingPopulateValueChangeAction
				.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		cdPricingPopulateValueChangeAction.addActionParameter(componentIdList);
		cdPricingPopulateValueChangeAction.addActionParameter("1");
		cdPricingPopulateValueChangeAction.addActionParameter("2");
		cdPricingPopulateFieldConfig.addGtnUIFrameWorkActionConfig(cdPricingPopulateValueChangeAction);
		componentIdList.add(cdPricingPopulateFieldConfig.getComponentId());
		GtnUIFrameworkValidationConfig cdPricingPopulateValConfig = new GtnUIFrameworkValidationConfig();
		cdPricingPopulateValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cdPricingPopulateValConfig.setAttachRegxValidatior(true);
		cdPricingPopulateValConfig.setFormatString(GtnFrameworkContractDashboardContants.REGEX_NUM);
		cdPricingPopulateValConfig.setRegxValidationMessage(GtnFrameworkContractDashboardContants.REGEX_ERROR);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()[0],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD, valConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()[1],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD, valConfig);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()[2],
				gtnLayoutConfig.getComponentId(), valConfig, GtnWsContractDashboardContants.STATUS);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()[3],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX, cdPricingPopulateValConfig);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()[4],
				gtnLayoutConfig.getComponentId(), valConfig, GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPtDetailMassPopulateField()[5],
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX, cdPricingPopulateValConfig);
	}

	private void addPopulateButtonLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdPricingTabPrefix + "PopulateButtonlayout", true, parent);
		cdPricingComponentList.add(layoutConfig);
		addPopulateButtonComponent(cdPricingComponentList, cdPricingTabPrefix, layoutConfig.getComponentId(),
				componentIdList);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig populateButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "PopulateButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateButtonConfig.setAuthorizationIncluded(true);
		populateButtonConfig.setComponentName("Populate");
		populateButtonConfig.setEnable(false);
		cdPricingComponentList.add(populateButtonConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyPopulateAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter("1");
		customAction.addActionParameter("2");
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		populateButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateButtonConfig.getComponentId());
		GtnUIFrameworkComponentConfig populateAllButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "PopulateAllButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateAllButtonConfig.setComponentName("Populate All");
		populateAllButtonConfig.setEnable(false);
		cdPricingComponentList.add(populateAllButtonConfig);
		populateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateAllButtonConfig.getComponentId());
	}

	private void recordLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent) {
		String componentId = cdPricingTabPrefix + "record";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(componentId + "Css" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdPricingTabRecordLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnLayoutConfig.getComponentId());
		cdPricingTabRecordLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdPricingComponentList.add(cdPricingTabRecordLayoutConfig);

		GtnUIFrameworkComponentConfig cdPricingTabRecordComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, cdPricingTabRecordLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		cdPricingTabRecordComponentConfig.setAuthorizationIncluded(true);
		cdPricingTabRecordComponentConfig.setComponentName("Record:");
		cdPricingTabRecordComponentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdPricingComponentList.add(cdPricingTabRecordComponentConfig);

		GtnUIFrameworkOptionGroupConfig cdPricingTabRecordOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		cdPricingTabRecordOptionConfig.setValuesFromService(false);
		cdPricingTabRecordOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.HISTORY,
						GtnWsContractDashboardContants.FUTURE, GtnWsContractDashboardContants.PENDING));
		cdPricingTabRecordOptionConfig.setIsMultiSelect(true);
		cdPricingTabRecordOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.CURRENT);
		cdPricingTabRecordComponentConfig.setGtnUIFrameworkOptionGroupConfig(cdPricingTabRecordOptionConfig);

		GtnUIFrameWorkActionConfig cdPricingTabRecordTableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		cdPricingTabRecordTableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPricingTabRecordTableLoadActionConfig.addActionParameter(GtnUIFrameworkPricingTabTableAction.class.getName());
		cdPricingTabRecordTableLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE);
		cdPricingTabRecordTableLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_PS_COMPONENT);
		cdPricingTabRecordTableLoadActionConfig
				.setFieldValues(Arrays.asList(cdPricingTabRecordComponentConfig.getComponentId(),
						GtnFrameworkContractDashboardContants.HIDDEN_PS_COMPONENT));
		cdPricingTabRecordComponentConfig.addGtnUIFrameWorkActionConfig(cdPricingTabRecordTableLoadActionConfig);
	}

	private void psResultPanel(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE;
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		gtnPanelConfig.setComponentName("Results");
		gtnPanelConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(true);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(false);
		attachResults.setPageLength(5);
		attachResults.setItemPerPage(5);
		attachResults.setSinkItemPerPageWithPageLength(false);
		attachResults.setTableColumnDataType(GtnFrameworkContractDashboardContants.getPriceDetailColumnType());
		attachResults.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getPriceDetailColumnHeader());
		attachResults.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getPriceDetailColumn());
		attachResults.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_DETAIL_TABLE_DATA);
		attachResults.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_DETAIL_TABLE_DATA);
		attachResults.setColumnCheckBoxId(GtnFrameworkContractDashboardContants.getPriceDetailColumn()[0]);
		attachResults.setInvisibleFilterPropertyIds(GtnFrameworkContractDashboardContants.getPriceDetailColumn()[0]);
		attachResults.setCustomFilterConfigMap(getDetailsTableFilterFieldMap());
		attachResults.setEditableColumnList(
				Arrays.asList(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()));
		List<GtnUIFrameworkComponentConfig> editableFieldList = new ArrayList<>(
				attachResults.getEditableColumnList().size());
		getTableEditableField(editableFieldList);
		attachResults.setEditableField(editableFieldList);
		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkTableCheckAllAction.class.getName());
		checkAllAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		attachResults.addColumnCheckActionConfig(checkAllAction);
		psViewResultTable(cdPricingComponentList, gtnLayoutConfig.getComponentId());
	}

	private void psViewResultTable(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE + "View";

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setPageLength(10);
		attachResults.setItemPerPage(10);
		attachResults.setSinkItemPerPageWithPageLength(false);
		attachResults.setTableColumnDataType(GtnFrameworkContractDashboardContants.getPriceDetailViewColumnType());
		attachResults.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getPriceDetailViewColumnHeader());
		attachResults.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getPriceDetailViewColumn());
		attachResults.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_DETAIL_VIEW_TABLE_DATA);
		attachResults.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_DETAIL_VIEW_TABLE_DATA);
		
		
	}

	private void addExcelButtonLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		String componentId = cdPricingTabPrefix + "exlBtn";
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdPricingComponentList.add(layoutConfig);
		addExcelButtonComponent(cdPricingComponentList, cdPricingTabPrefix, layoutConfig.getComponentId());
		addViewExcelButtonComponent(cdPricingComponentList, cdPricingTabPrefix, layoutConfig.getComponentId());
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + EXCEL_BUTTON, true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Price Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE);
		gtnExcelButtonConfig.setExcludeColumnsList(Arrays.asList("checkRecordId"));
		gtnExcelButtonConfig.setHelperTableMapedPropertyIdList(
				Arrays.asList(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[1],
						GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[4]));
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addViewExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "excelButtonView", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Price Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE + "View");
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void getTableEditableField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList) {
		GtnUIFrameworkComponentConfig checkRecordConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.CHECKBOX);
		checkRecordConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(checkRecordConfig);
		GtnUIFrameWorkActionConfig cdPricingFieldFactoryAction = new GtnUIFrameWorkActionConfig();
		cdPricingFieldFactoryAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPricingFieldFactoryAction.addActionParameter(GtnFrameworkFieldFactoryAction.class.getName());
		cdPricingFieldFactoryAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM_FIELD);
		cdPricingFieldFactoryAction.addActionParameter(16);
		checkRecordConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(cdPricingFieldFactoryAction));
		GtnUIFrameWorkActionConfig focusAction = new GtnUIFrameWorkActionConfig();
		focusAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		focusAction.addActionParameter(GtnUIFrameworkFocusAction.class.getName());

		addFieldFactoryComboField(cdPricingComponentList, cdPricingFieldFactoryAction,
				GtnWsContractDashboardContants.STATUS, focusAction);
		addFieldFactoryField(cdPricingComponentList, GtnUIFrameworkComponentType.DATEFIELD, cdPricingFieldFactoryAction,
				focusAction);
		addFieldFactoryField(cdPricingComponentList, GtnUIFrameworkComponentType.DATEFIELD, cdPricingFieldFactoryAction,
				focusAction);
		addFieldFactoryComboField(cdPricingComponentList, cdPricingFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME, focusAction);
		GtnUIFrameworkValidationConfig cdPricingFieldFactoryValConfig = new GtnUIFrameworkValidationConfig();
		cdPricingFieldFactoryValConfig.setAttachRegxValidatior(true);
		cdPricingFieldFactoryValConfig.setFormatString(GtnFrameworkContractDashboardContants.REGEX_NUM);
		cdPricingFieldFactoryValConfig.setRegxValidationMessage(GtnFrameworkContractDashboardContants.REGEX_ERROR);
		GtnUIFrameworkComponentConfig priceCompConfig = addFieldFactoryField(cdPricingComponentList,
				GtnUIFrameworkComponentType.TEXTBOX, cdPricingFieldFactoryAction, focusAction);
		priceCompConfig.setGtnUIFrameworkValidationConfig(cdPricingFieldFactoryValConfig);
		priceCompConfig.setEnable(true);
		GtnUIFrameWorkActionConfig cdPricingFieldFactoryComponentCreateAction = new GtnUIFrameWorkActionConfig();
		cdPricingFieldFactoryComponentCreateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPricingFieldFactoryComponentCreateAction
				.addActionParameter(GtnFrameworkFieldFactoryComponentCreationAction.class.getName());
		cdPricingFieldFactoryComponentCreateAction
				.addActionParameter(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[4]);
		cdPricingFieldFactoryComponentCreateAction
				.addActionParameter(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[5]);
		cdPricingFieldFactoryComponentCreateAction.setComponentConfig(priceCompConfig);
		priceCompConfig.setFieldFactoryComponentCreateActionConfig(cdPricingFieldFactoryComponentCreateAction);

		GtnUIFrameworkComponentConfig sugPriceCompConfig = addFieldFactoryField(cdPricingComponentList,
				GtnUIFrameworkComponentType.TEXTBOX, cdPricingFieldFactoryAction, focusAction);
		sugPriceCompConfig.setGtnUIFrameworkValidationConfig(cdPricingFieldFactoryValConfig);
	}

	public void addPricingLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String cdPricingTabPrefix,
			String parent) {

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdPricingTabPrefix + "_" + GtnWsContractDashboardContants.PRICE_PROTECTION.replace(" ", "") + "_"
						+ GtnFrameworkCommonStringConstants.LAYOUT,
				true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdPricingComponentList.add(gtnLayoutConfig);
		String newNamspacePrefix = cdPricingTabPrefix + "PPlvl";
		pricingMassUpdatePanel(cdPricingComponentList, newNamspacePrefix, gtnLayoutConfig.getComponentId());
		pricingRecordLayout(cdPricingComponentList, newNamspacePrefix, gtnLayoutConfig.getComponentId());
		pricingResultPanel(cdPricingComponentList, gtnLayoutConfig.getComponentId());
		addAddLineButtonComponent(cdPricingComponentList);
		addRemoveLineButtonComponent(cdPricingComponentList);
		addCopyLineButtonComponent(cdPricingComponentList);
		addPricingExcelButtonLayout(cdPricingComponentList, newNamspacePrefix, gtnLayoutConfig.getComponentId());
	}

	private void pricingMassUpdatePanel(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdPricingTabPrefix + MASS_UPDATE_PANEL, true, parent);
		cdPricingComponentList.add(gtnPanelConfig);
		gtnPanelConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdPricingTabPrefix + "MassUpdateLayout", true, gtnPanelConfig.getComponentId());
		cdPricingComponentList.add(gtnLayoutConfig);

		List<String> componentIdList = new ArrayList<>();
		GtnUIFrameWorkActionConfig pricingMassCheckEnableDisableAction = new GtnUIFrameWorkActionConfig();
		addPricingMassCheck(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				pricingMassCheckEnableDisableAction);
		addPricingMassPopulateField(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPricingPopulateButtonLayout(cdPricingComponentList, cdPricingTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addPricingHiddenField(cdPricingComponentList, gtnLayoutConfig.getComponentId());
		pricingMassCheckEnableDisableAction.setFieldValues(componentIdList);
	}

	private void addPricingHiddenField(List<GtnUIFrameworkComponentConfig> componentList, String parent) {
		GtnUIFrameworkComponentConfig hiddenFieldComponentConfig = commonConfig.getHorizontalLayoutConfig(
				GtnFrameworkContractDashboardContants.HIDDEN_PRICING_PS_LAYOUT, true, parent);
		componentList.add(hiddenFieldComponentConfig);

		GtnUIFrameworkComponentConfig hiddenComponent = commonConfig.getUIFrameworkComponentConfig(
				GtnFrameworkContractDashboardContants.HIDDEN_PRICING_PS_COMPONENT, true,
				hiddenFieldComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		hiddenComponent.setAuthorizationIncluded(true);

		hiddenComponent.setVisible(false);
		componentList.add(hiddenComponent);
	}

	private void addPricingMassCheck(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, GtnUIFrameWorkActionConfig pricingMassCheckEnableDisableAction) {
		String componentId = cdPricingTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig pricingMassCheckConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		pricingMassCheckConfig.setAuthorizationIncluded(true);
		pricingMassCheckConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdPricingComponentList.add(pricingMassCheckConfig);

		GtnUIFrameworkOptionGroupConfig pricingMassCheckMassCheckOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		pricingMassCheckMassCheckOptionConfig.setValuesFromService(false);

		pricingMassCheckMassCheckOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.ENABLE, GtnWsContractDashboardContants.DISABLE));
		pricingMassCheckMassCheckOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.DISABLE);
		pricingMassCheckConfig.setGtnUIFrameworkOptionGroupConfig(pricingMassCheckMassCheckOptionConfig);
		pricingMassCheckEnableDisableAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		pricingMassCheckConfig.addGtnUIFrameWorkActionConfig(pricingMassCheckEnableDisableAction);
	}

	private void addPricingMassPopulateField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		String componentId = cdPricingTabPrefix + "PopulateField";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig pricingMassPopulateFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		pricingMassPopulateFieldConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(pricingMassPopulateFieldConfig);
		pricingMassPopulateFieldConfig.setEnable(false);
		GtnUIFrameworkComboBoxConfig populateFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		populateFieldComboBoxConfig
				.setItemValues(Arrays.asList(GtnFrameworkContractDashboardContants.getPpMassPopulateField()));
		pricingMassPopulateFieldConfig.setGtnComboboxConfig(populateFieldComboBoxConfig);

		GtnUIFrameworkValidationConfig pricingMassPopulateFieldValConfig = new GtnUIFrameworkValidationConfig();
		pricingMassPopulateFieldValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		pricingMassPopulateFieldConfig.setGtnUIFrameworkValidationConfig(pricingMassPopulateFieldValConfig);

		GtnUIFrameWorkActionConfig pricingMassPopulateFieldValueChangeAction = new GtnUIFrameWorkActionConfig();
		pricingMassPopulateFieldValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingMassPopulateFieldValueChangeAction
				.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		pricingMassPopulateFieldValueChangeAction.addActionParameter(componentIdList);
		pricingMassPopulateFieldValueChangeAction.addActionParameter("1");
		pricingMassPopulateFieldValueChangeAction.addActionParameter("2");
		pricingMassPopulateFieldConfig.addGtnUIFrameWorkActionConfig(pricingMassPopulateFieldValueChangeAction);
		componentIdList.add(pricingMassPopulateFieldConfig.getComponentId());

		GtnUIFrameworkValidationConfig pricingMassPopulateFieldRegexValConfig = new GtnUIFrameworkValidationConfig();
		pricingMassPopulateFieldRegexValConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		pricingMassPopulateFieldRegexValConfig.setAttachRegxValidatior(true);
		pricingMassPopulateFieldRegexValConfig.setFormatString(GtnFrameworkContractDashboardContants.REGEX_NUM);
		pricingMassPopulateFieldRegexValConfig
				.setRegxValidationMessage(GtnFrameworkContractDashboardContants.REGEX_ERROR);

		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[1], gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD, pricingMassPopulateFieldValConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[2], gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD, pricingMassPopulateFieldValConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[19], gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD, pricingMassPopulateFieldValConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[16], gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX, pricingMassPopulateFieldRegexValConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[15], gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX, pricingMassPopulateFieldRegexValConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[4], gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX, pricingMassPopulateFieldRegexValConfig);

		addLookUpField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[5], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig);
		addLookUpField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[26], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig);

		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[0], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.STATUS);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[7], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.LOCKED_STATUS);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[17], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.LOCKED_STATUS);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[25], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.LOCKED_STATUS);

		GtnUIFrameworkComponentConfig basePriceConfig = addComboField(cdPricingComponentList, componentIdList,
				cdPricingTabPrefix, GtnFrameworkContractDashboardContants.getPpMassPopulateField()[6],
				gtnLayoutConfig.getComponentId(), pricingMassPopulateFieldValConfig,
				GtnWsContractDashboardContants.BASE_PRICE_TYPE);

		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix + BASE_PRICE, "PriceType",
				gtnLayoutConfig.getComponentId(), pricingMassPopulateFieldValConfig,
				GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix + BASE_PRICE, "Date",
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD,
				pricingMassPopulateFieldValConfig);
		addField(cdPricingComponentList, componentIdList, cdPricingTabPrefix + BASE_PRICE, "Manual",
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX,
				pricingMassPopulateFieldRegexValConfig);

		GtnUIFrameWorkActionConfig basePriceChangeAction = new GtnUIFrameWorkActionConfig();
		basePriceChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		basePriceChangeAction.addActionParameter(GtnUIFrameworkBasePriceValueChangeAction.class.getName());
		basePriceChangeAction.addActionParameter(componentIdList);
		basePriceChangeAction.addActionParameter("10");
		basePriceChangeAction.addActionParameter("11");
		basePriceConfig.addGtnUIFrameWorkActionConfig(basePriceChangeAction);

		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[3], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[9], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[22], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME);

		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[20], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_TOLERANCE_INTERVAL);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[13], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_TOLERANCE_FRERQUENCY);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[14], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_TOLERANCE_TYPE);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[23], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.LOCKED_STATUS);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[18], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.RESET_TYPE);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[12], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_TOLERANCE_INTERVAL);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[21], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.PRICE_TOLERANCE_FRERQUENCY);
		addComboField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[10], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig, GtnWsContractDashboardContants.LOCKED_STATUS);
		addLookUpField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[24], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig);
		addLookUpField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[11], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig);
		addLookUpField(cdPricingComponentList, componentIdList, cdPricingTabPrefix,
				GtnFrameworkContractDashboardContants.getPpMassPopulateField()[8], gtnLayoutConfig.getComponentId(),
				pricingMassPopulateFieldValConfig);
	}

	private void addField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, List<String> componentIdList,
			String cdPricingTabPrefix, String componentId, String parent, GtnUIFrameworkComponentType componentType,
			GtnUIFrameworkValidationConfig valConfig) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "_" + componentId.replace(" ", "") + "_", true, parent, componentType);
		componentConfig.setVisible(false);
		cdPricingComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private GtnUIFrameworkComponentConfig addComboField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			List<String> componentIdList, String cdPricingTabPrefix, String componentId, String parent,
			GtnUIFrameworkValidationConfig valConfig, String listName) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "_" + componentId.replace(" ", "") + "_", true, parent,
				GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setVisible(false);
		cdPricingComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(listName);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		return componentConfig;
	}

	private void addLookUpField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			List<String> componentIdList, String cdPricingTabPrefix, String componentId, String parent,
			GtnUIFrameworkValidationConfig valConfig) {

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "_" + componentId.replace(" ", "") + "_" + "popup", true, parent,
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setVisible(false);
		cdPricingComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(false, false, true);
		textboxConfig.setReadOnly(false);
		componentConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW);
		popupActionConfig.addActionParameter("Net Sales Formula Lookup");
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(Arrays.asList(componentConfig.getComponentId(),
				Arrays.asList("formulaName"), Arrays.asList(componentConfig.getComponentId()), null));
		componentConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
	}

	private GtnUIFrameworkComponentConfig addFieldFactoryField(
			List<GtnUIFrameworkComponentConfig> cdPricingComponentList, GtnUIFrameworkComponentType componentType,
			GtnUIFrameWorkActionConfig customAction, GtnUIFrameWorkActionConfig focusAction) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				componentType);
		cdPricingComponentList.add(componentConfig);
		componentConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		componentConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
		return componentConfig;
	}

	private GtnUIFrameworkComponentConfig addFieldFactoryComboField(
			List<GtnUIFrameworkComponentConfig> cdPricingComponentList, GtnUIFrameWorkActionConfig customAction,
			String listName, GtnUIFrameWorkActionConfig focusAction) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(componentConfig);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(listName);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		componentConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
		return componentConfig;
	}

	private void addFieldFactoryLookUpField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			GtnUIFrameWorkActionConfig customAction) {
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		componentConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(componentConfig);
		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		componentConfig.setGtnTextBoxConfig(textboxConfig);
		componentConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(customAction));
	}

	private void addPricingPopulateButtonLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdPricingTabPrefix + "PopulateButtonlayout", true, parent);
		cdPricingComponentList.add(layoutConfig);
		addPricingPopulateButtonComponent(cdPricingComponentList, cdPricingTabPrefix, layoutConfig.getComponentId(),
				componentIdList);
	}

	private void addPricingPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig populateButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "PopulateButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateButtonConfig.setAuthorizationIncluded(true);
		populateButtonConfig.setComponentName("Populate");
		populateButtonConfig.setEnable(false);
		cdPricingComponentList.add(populateButtonConfig);
		GtnUIFrameWorkActionConfig cdPricingPopulateAction = new GtnUIFrameWorkActionConfig();
		cdPricingPopulateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPricingPopulateAction.addActionParameter(GtnFrameworkCompanyPopulateAction.class.getName());
		cdPricingPopulateAction.addActionParameter(componentIdList);
		cdPricingPopulateAction.addActionParameter("1");
		cdPricingPopulateAction.addActionParameter("2");
		cdPricingPopulateAction.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
		cdPricingPopulateAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM);
		cdPricingPopulateAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		populateButtonConfig.addGtnUIFrameWorkActionConfig(cdPricingPopulateAction);
		componentIdList.add(populateButtonConfig.getComponentId());
		GtnUIFrameworkComponentConfig populateAllButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "PopulateAllButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateAllButtonConfig.setComponentName("Populate All");
		populateAllButtonConfig.setEnable(false);
		cdPricingComponentList.add(populateAllButtonConfig);
		populateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addGtnUIFrameWorkActionConfig(cdPricingPopulateAction);
		componentIdList.add(populateAllButtonConfig.getComponentId());
	}

	private void pricingRecordLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		String componentId = cdPricingTabPrefix + "record";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(componentId + "Css" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig pricingRecordLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnLayoutConfig.getComponentId());
		pricingRecordLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdPricingComponentList.add(pricingRecordLayoutConfig);

		GtnUIFrameworkComponentConfig pricingRecordConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, pricingRecordLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		pricingRecordConfig.setAuthorizationIncluded(true);
		pricingRecordConfig.setComponentName("Record:");
		pricingRecordConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdPricingComponentList.add(pricingRecordConfig);

		GtnUIFrameworkOptionGroupConfig pricingRecordOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		pricingRecordOptionConfig.setValuesFromService(false);
		pricingRecordOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.CURRENT, GtnWsContractDashboardContants.HISTORY,
						GtnWsContractDashboardContants.FUTURE, GtnWsContractDashboardContants.PENDING));
		pricingRecordOptionConfig.setIsMultiSelect(true);
		pricingRecordOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.CURRENT);
		pricingRecordConfig.setGtnUIFrameworkOptionGroupConfig(pricingRecordOptionConfig);

		GtnUIFrameWorkActionConfig pricingRecordLoadActionConfig = new GtnUIFrameWorkActionConfig();
		pricingRecordLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingRecordLoadActionConfig.addActionParameter(GtnUIFrameworkPriceProtectionTabTableAction.class.getName());
		pricingRecordLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
		pricingRecordLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_PRICING_PS_COMPONENT);
		pricingRecordLoadActionConfig.setFieldValues(Arrays.asList(pricingRecordConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.HIDDEN_PRICING_PS_COMPONENT));
		pricingRecordConfig.addGtnUIFrameWorkActionConfig(pricingRecordLoadActionConfig);
	}

	private void pricingResultPanel(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE;
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		gtnPanelConfig.setComponentName("Results");
		gtnPanelConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdPricingResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdPricingResultTable);
		cdPricingResultTable.setEditable(true);
		cdPricingResultTable.setFilterBar(true);
		cdPricingResultTable.setSelectable(false);
		cdPricingResultTable.setPageLength(5);
		cdPricingResultTable.setItemPerPage(5);
		cdPricingResultTable.setSinkItemPerPageWithPageLength(false);
		
		cdPricingResultTable.setColumnToAlign(GtnFrameworkContractDashboardContants.getPriceProtectionColumnAlignmentHeader());
		cdPricingResultTable.setColumnAlignment(GtnFrameworkContractDashboardContants.getPriceProtectionColumnAlignment());
		
		cdPricingResultTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getPriceProtectionColumnType());
		cdPricingResultTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getPriceProtectionColumnHeader());
		cdPricingResultTable.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getPriceProtectionColumn());
		cdPricingResultTable.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.PP_STATUS_PENDING,
				GtnFrameworkContractDashboardContants.MEASUREMENT_PRICE_PENDING,
				GtnFrameworkContractDashboardContants.BASE_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_BASE_PRICE_PENDING,
				GtnFrameworkContractDashboardContants.SUBSEQUENT_PERIOD_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_SUBSEQUENT_PERIOD_PRICE_PENDING,
				GtnFrameworkContractDashboardContants.PRICE_TOLERANCE_INTERVAL_PENDING,
				GtnFrameworkContractDashboardContants.PRICE_TOLERANCE_FREQUENCY_PENDING,
				GtnFrameworkContractDashboardContants.PRICE_TOLERANCE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.RESET_ELIGIBLE_PENDING,
				GtnFrameworkContractDashboardContants.RESET_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.RESET_INTERVAL_PENDING,
				GtnFrameworkContractDashboardContants.RESET_FREQUENCY_PENDING,
				GtnFrameworkContractDashboardContants.RESET_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_RESET_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_PRICE_TYPE_PENDING,
			    GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING3,
				GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING4,
				GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING5,
				GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING6
		});
		cdPricingResultTable.setExtraColumnDataType(new Class[] { String.class, String.class,String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class,String.class,Integer.class,Date.class,String.class
		});
		cdPricingResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_TABLE_DATA);
		cdPricingResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_TABLE_DATA);
		cdPricingResultTable.setColumnCheckBoxId(GtnFrameworkContractDashboardContants.getPriceProtectionColumn()[0]);
		cdPricingResultTable.setInvisibleFilterPropertyIds(
				GtnFrameworkContractDashboardContants.getPriceProtectionColumn()[0],
				GtnFrameworkContractDashboardContants.getPriceProtectionColumn()[12]);
		cdPricingResultTable.setCustomFilterConfigMap(getPricingTableFilterFieldMap());
		cdPricingResultTable.setEditableColumnList(
				Arrays.asList(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()));
		List<GtnUIFrameworkComponentConfig> editableFieldList = new ArrayList<>(
				cdPricingResultTable.getEditableColumnList().size());
		getPricingTableEditableField(editableFieldList);
		cdPricingResultTable.setEditableField(editableFieldList);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameWorkContractTableRecordTypeAction.class.getName());
		customAction.addActionParameter(componentConfig.getComponentId());
		cdPricingResultTable.setRecordTypeManageActionConfig(customAction);
		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkTableCheckAllAction.class.getName());
		checkAllAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		cdPricingResultTable.addColumnCheckActionConfig(checkAllAction);
		pricingViewResultTable(cdPricingComponentList, gtnLayoutConfig.getComponentId());
	}

	
		
	
	private void pricingViewResultTable(List<GtnUIFrameworkComponentConfig> cdPricingComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE + "View";

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdPricingComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig cdPricingViewResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(cdPricingViewResultTable);
		cdPricingViewResultTable.setPageLength(10);
		cdPricingViewResultTable.setItemPerPage(10);
		cdPricingViewResultTable.setSinkItemPerPageWithPageLength(false);
		cdPricingViewResultTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getPriceProtectionViewColumnType());
		cdPricingViewResultTable
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getPriceProtectionViewHeader());
		cdPricingViewResultTable
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getPriceProtectionViewColumn());
		cdPricingViewResultTable.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.PP_STATUS_PENDING,
				GtnFrameworkContractDashboardContants.MEASUREMENT_PRICE_PENDING,
				GtnFrameworkContractDashboardContants.BASE_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_BASE_PRICE_PENDING,
				GtnFrameworkContractDashboardContants.SUBSEQUENT_PERIOD_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_SUBSEQUENT_PERIOD_PRICE_PENDING,
				GtnFrameworkContractDashboardContants.PRICE_TOLERANCE_INTERVAL_PENDING,
				GtnFrameworkContractDashboardContants.PRICE_TOLERANCE_FREQUENCY_PENDING,
				GtnFrameworkContractDashboardContants.PRICE_TOLERANCE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.RESET_ELIGIBLE_PENDING,
				GtnFrameworkContractDashboardContants.RESET_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.RESET_INTERVAL_PENDING,
				GtnFrameworkContractDashboardContants.RESET_FREQUENCY_PENDING,
				GtnFrameworkContractDashboardContants.RESET_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_RESET_PRICE_TYPE_PENDING,
				GtnFrameworkContractDashboardContants.NET_PRICE_TYPE_PENDING,
			    GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING3,
				GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING4,
				GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING5,
				GtnFrameworkContractDashboardContants.EXTRACOLUMN_PENDING6
		});
		cdPricingViewResultTable.setExtraColumnDataType(new Class[] { String.class, String.class,String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class,String.class,Integer.class,Date.class,String.class
		});
		cdPricingViewResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_VIEW_TABLE_DATA);
		cdPricingViewResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_VIEW_TABLE_DATA);
	
	}

	private void addPricingExcelButtonLayout(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		String componentId = cdPricingTabPrefix + "exlBtn";
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdPricingComponentList.add(layoutConfig);
		addPricingExcelButtonComponent(cdPricingComponentList, cdPricingTabPrefix, layoutConfig.getComponentId());
		addPricingViewExcelButtonComponent(cdPricingComponentList, cdPricingTabPrefix, layoutConfig.getComponentId());
	}

	private void addPricingExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + EXCEL_BUTTON, true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Price Protection");
		gtnExcelButtonConfig.setExportFromTable(false);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
		gtnExcelButtonConfig.setExcludeColumnsList(Arrays.asList("checkRecordId"));
		gtnExcelButtonConfig.setLoadDataServiceUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_PRICING_PROTECTION_EXCEL_DATA);
		gtnExcelButtonConfig.setServiceType(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		excelAction.addActionParameter(GtnFrameworkPricingTableExcelExportAction.class.getName());
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addPricingViewExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdPricingComponentList,
			String cdPricingTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdPricingTabPrefix + "excelButtonView", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdPricingComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Price Protection");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE + "View");
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void getPricingTableEditableField(List<GtnUIFrameworkComponentConfig> cdPricingComponentList) {
		GtnUIFrameworkComponentConfig checkRecordConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.CHECKBOX);
		cdPricingComponentList.add(checkRecordConfig);
		GtnUIFrameWorkActionConfig pricingTableFieldFactoryAction = new GtnUIFrameWorkActionConfig();
		pricingTableFieldFactoryAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingTableFieldFactoryAction.addActionParameter(GtnFrameworkFieldFactoryAction.class.getName());
		pricingTableFieldFactoryAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM_FIELD);
		pricingTableFieldFactoryAction.addActionParameter(35);
		checkRecordConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(pricingTableFieldFactoryAction));

		GtnUIFrameWorkActionConfig pricingTableFieldFactoryFocusAction = new GtnUIFrameWorkActionConfig();
		pricingTableFieldFactoryFocusAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingTableFieldFactoryFocusAction.addActionParameter(GtnUIFrameworkFocusAction.class.getName());

		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.STATUS, pricingTableFieldFactoryFocusAction);
		addFieldFactoryField(cdPricingComponentList, GtnUIFrameworkComponentType.DATEFIELD,
				pricingTableFieldFactoryAction, pricingTableFieldFactoryFocusAction);
		addFieldFactoryField(cdPricingComponentList, GtnUIFrameworkComponentType.DATEFIELD,
				pricingTableFieldFactoryAction, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME, pricingTableFieldFactoryFocusAction);

	GtnUIFrameworkValidationConfig pricingTableFieldFactoryRegexConfig = new GtnUIFrameworkValidationConfig();
		pricingTableFieldFactoryRegexConfig.setAttachRegxValidatior(true);
		pricingTableFieldFactoryRegexConfig.setFormatString("([|0-9]*.[0-9]{1,6})");
		pricingTableFieldFactoryRegexConfig.setRegxValidationMessage(GtnFrameworkContractDashboardContants.REGEX_ERROR);
		GtnUIFrameworkComponentConfig nepCompConfig = addFieldFactoryField(cdPricingComponentList,
				GtnUIFrameworkComponentType.TEXTBOX, pricingTableFieldFactoryAction,
				pricingTableFieldFactoryFocusAction);
		nepCompConfig.setGtnUIFrameworkValidationConfig(pricingTableFieldFactoryRegexConfig);
		addFieldFactoryLookUpField(cdPricingComponentList, pricingTableFieldFactoryAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.BASE_PRICE_TYPE, pricingTableFieldFactoryFocusAction);
		GtnUIFrameworkComponentConfig compConfig = addFieldFactoryComboField(cdPricingComponentList,
				pricingTableFieldFactoryAction, GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME,
				pricingTableFieldFactoryFocusAction);// depends
		// on
		// previous
		// BASE_PRICE_TYPE

		GtnUIFrameWorkActionConfig pricingTableFieldFactoryFieldFactoryComponentCreateAction = new GtnUIFrameWorkActionConfig();
		pricingTableFieldFactoryFieldFactoryComponentCreateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingTableFieldFactoryFieldFactoryComponentCreateAction
				.addActionParameter(GtnFrameworkFieldFactoryComponentCreationAction.class.getName());
		pricingTableFieldFactoryFieldFactoryComponentCreateAction
				.addActionParameter(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[7]);
		pricingTableFieldFactoryFieldFactoryComponentCreateAction
				.addActionParameter(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[8]);
		pricingTableFieldFactoryFieldFactoryComponentCreateAction.setComponentConfig(compConfig);
		compConfig
				.setFieldFactoryComponentCreateActionConfig(pricingTableFieldFactoryFieldFactoryComponentCreateAction);

		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.LOCKED_STATUS, pricingTableFieldFactoryFocusAction);
		addFieldFactoryLookUpField(cdPricingComponentList, pricingTableFieldFactoryAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.LOCKED_STATUS, pricingTableFieldFactoryFocusAction);
		addFieldFactoryLookUpField(cdPricingComponentList, pricingTableFieldFactoryAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_TOLERANCE_INTERVAL, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_TOLERANCE_FRERQUENCY, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_TOLERANCE_TYPE, pricingTableFieldFactoryFocusAction);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setAttachRegxValidatior(true);
		valConfig.setFormatString("\\$?([0-9]?.?[0-9]){1,6}%?");
		valConfig.setRegxValidationMessage(GtnFrameworkContractDashboardContants.REGEX_ERROR);
		GtnUIFrameworkComponentConfig priceTolCompConfig = addFieldFactoryField(cdPricingComponentList,
				GtnUIFrameworkComponentType.TEXTBOX, pricingTableFieldFactoryAction,
				pricingTableFieldFactoryFocusAction);
		 priceTolCompConfig.setComponentStyle(GtnFrameworkContractDashboardContants.getPriceProtectionTextRightJustified());
		priceTolCompConfig.setGtnUIFrameworkValidationConfig(valConfig);
		GtnUIFrameworkComponentConfig incChangeCompConfig = addFieldFactoryField(cdPricingComponentList,
				GtnUIFrameworkComponentType.TEXTBOX, pricingTableFieldFactoryAction,
				pricingTableFieldFactoryFocusAction);
		incChangeCompConfig.setGtnUIFrameworkValidationConfig(valConfig);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.LOCKED_STATUS, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.RESET_TYPE, pricingTableFieldFactoryFocusAction);
		addFieldFactoryField(cdPricingComponentList, GtnUIFrameworkComponentType.DATEFIELD,
				pricingTableFieldFactoryAction, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_TOLERANCE_INTERVAL, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_TOLERANCE_FRERQUENCY, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME, pricingTableFieldFactoryFocusAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.LOCKED_STATUS, pricingTableFieldFactoryFocusAction);
		addFieldFactoryLookUpField(cdPricingComponentList, pricingTableFieldFactoryAction);
		addFieldFactoryComboField(cdPricingComponentList, pricingTableFieldFactoryAction,
				GtnWsContractDashboardContants.LOCKED_STATUS, pricingTableFieldFactoryFocusAction);
		addFieldFactoryLookUpField(cdPricingComponentList, pricingTableFieldFactoryAction);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getDetailsTableFilterFieldMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[1],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[1],
						GtnWsContractDashboardContants.STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[4],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[4],
						GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME));
		return customFilterConfigMap;
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getPricingTableFilterFieldMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[1],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[1],
						GtnWsContractDashboardContants.STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[4],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[4],
						GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[7],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[7],
						GtnWsContractDashboardContants.BASE_PRICE_TYPE));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[9],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[9],
						GtnWsContractDashboardContants.LOCKED_STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[11],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[11],
						GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[12],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[12],
						GtnWsContractDashboardContants.LOCKED_STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[14],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[14],
						GtnWsContractDashboardContants.PRICE_TOLERANCE_INTERVAL));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[15],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[15],
						GtnWsContractDashboardContants.PRICE_TOLERANCE_FRERQUENCY));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[16],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[16],
						GtnWsContractDashboardContants.PRICE_TOLERANCE_TYPE));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[19],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[19],
						GtnWsContractDashboardContants.LOCKED_STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[20],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[20],
						GtnWsContractDashboardContants.RESET_TYPE));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[22],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[22],
						GtnWsContractDashboardContants.PRICE_TOLERANCE_INTERVAL));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[23],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[23],
						GtnWsContractDashboardContants.PRICE_TOLERANCE_FRERQUENCY));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[24],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[24],
						GtnWsContractDashboardContants.PRICE_CODE_QUALIFIER_NAME));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[25],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[25],
						GtnWsContractDashboardContants.LOCKED_STATUS));
		customFilterConfigMap.put(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[27],
				getFilterComboField(GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[27],
						GtnWsContractDashboardContants.LOCKED_STATUS));
		return customFilterConfigMap;
	}

	private GtnUIFrameworkPagedTableCustomFilterConfig getFilterComboField(String propertyId, String listName) {
		GtnUIFrameworkPagedTableCustomFilterConfig cdPrcingFilterFieldConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		GtnUIFrameworkComponentConfig cfpStatusConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComboBoxConfig cdPrcingFilterFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		cdPrcingFilterFieldComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cdPrcingFilterFieldComboBoxConfig.setComboBoxType(listName);
		cfpStatusConfig.setGtnComboboxConfig(cdPrcingFilterFieldComboBoxConfig);
		cdPrcingFilterFieldConfig.setPropertId(propertyId);
		cdPrcingFilterFieldConfig.setGtnComponentConfig(cfpStatusConfig);
		cdPrcingFilterFieldConfig.setGtnComponentType(cfpStatusConfig.getComponentType());
		return cdPrcingFilterFieldConfig;
	}

	private void addAddLineButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addLineButtonLayout = commonConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.ADD_LINE, true, "CDProcessView_PT_PriceProtection_Layout");
		addLineButtonLayout.setVisible(true);
		componentList.add(addLineButtonLayout);

		GtnUIFrameworkComponentConfig addLineButtonConfig = commonConfig.getUIFrameworkComponentConfig(null, true,
				GtnFrameworkCommonConstants.ADD_LINE, GtnUIFrameworkComponentType.BUTTON);
		addLineButtonConfig.setComponentName("Add Line");
		addLineButtonConfig.setVisible(true);
		componentList.add(addLineButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addLineCustomAction = new GtnUIFrameWorkActionConfig();
		addLineCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addLineCustomAction.addActionParameter(GtnFrameworkContractPriceProtectionTabAddLineAction.class.getName());
		addLineCustomAction.addActionParameter("contractAddLineQuery");
		addLineCustomAction.addActionParameter("A");
		actionConfigList.add(addLineCustomAction);

		addLineButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRemoveLineButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig removeLineButtonLayout = commonConfig
				.getVerticalLayoutConfig("gtnRemoveLineButtonlayout", true, GtnFrameworkCommonConstants.ADD_LINE);
		componentList.add(removeLineButtonLayout);

		GtnUIFrameworkComponentConfig removeLineButtonConfig = commonConfig.getUIFrameworkComponentConfig(null, true,
				"gtnRemoveLineButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		removeLineButtonConfig.setComponentName("Remove Line");
		componentList.add(removeLineButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeLineCustomAction = new GtnUIFrameWorkActionConfig();
		removeLineCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeLineCustomAction.addActionParameter(GtnFrameworkContractPriceProtectionTabAddLineAction.class.getName());
		removeLineCustomAction.addActionParameter("getDeleteLineQuery");
		removeLineCustomAction.addActionParameter("D");
		actionConfigList.add(removeLineCustomAction);

		removeLineButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCopyLineButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig copyLineButtonLayout = commonConfig
				.getVerticalLayoutConfig("gtnCopyLineButtonlayout", true, GtnFrameworkCommonConstants.ADD_LINE);
		componentList.add(copyLineButtonLayout);

		GtnUIFrameworkComponentConfig copyLineButtonConfig = commonConfig.getUIFrameworkComponentConfig(null, true,
				"gtnCopyLineButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		copyLineButtonConfig.setComponentName("Copy Line");
		componentList.add(copyLineButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig copyLineCustomAction = new GtnUIFrameWorkActionConfig();
		copyLineCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyLineCustomAction.addActionParameter(GtnFrameworkContractPriceProtectionTabAddLineAction.class.getName());
		copyLineCustomAction.addActionParameter("contractCopyLineQuery");
		copyLineCustomAction.addActionParameter("A");
		actionConfigList.add(copyLineCustomAction);

		copyLineButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}
}
