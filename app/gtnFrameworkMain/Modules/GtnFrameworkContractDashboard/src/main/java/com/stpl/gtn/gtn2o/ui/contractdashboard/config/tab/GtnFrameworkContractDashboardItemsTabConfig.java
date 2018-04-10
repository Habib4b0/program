package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import static com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE;
import static com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkCompanyPopulateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkContractDateValidationAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkItemChangeIndicatorAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkUpdateSelectedRecordInTempTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkFieldEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkItemsTabTableAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkPopulateFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnFrameworkTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory.GtnUIFrameworkFocusAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkItemsTabLoadAction;
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

public class GtnFrameworkContractDashboardItemsTabConfig {
	private static final String DATE_FORMAT = "MM/dd/YYYY";
	private static final String TEXT_PIFPID = "Text_PIFPID";
	private static final String TEXT_PIFP_NAME = "Text_PIFPName";
	private static final String CHECK_RECORD_ID = "checkRecordId";
	private static final String IFP_STATUS = "ifpStatus";
	private static final String DECISSION_LEVEL = "DecissionLevel";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String cdItemsTabPrefix = mainNamspacePrefix + "IT";
		GtnUIFrameworkTabConfig cdItemsTabConfig = new GtnUIFrameworkTabConfig();
		cdItemsTabConfig.setComponentId(cdItemsTabPrefix + "itemsTab");
		cdItemsTabConfig.setTabCaption("Items");
		List<GtnUIFrameworkComponentConfig> cdItemsComponentList = new ArrayList<>();
		cdItemsTabConfig.setTabLayoutComponentConfigList(cdItemsComponentList);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getVerticalLayoutConfig(cdItemsTabConfig.getComponentId(), true, cdItemsTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		cdItemsComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addContractHeaderDetailDecissionLayout(cdItemsComponentList, cdItemsTabPrefix,
				gtnLayoutConfig.getComponentId());
		addContractHeaderFieldPanel(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addDeatilsLayout(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkItemsTabLoadAction.class.getName());
		customAction.addActionParameter(cdItemsTabPrefix + DECISSION_LEVEL);
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE);
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter(cdItemsTabPrefix + "MassUpdatePanel");
		customAction.addActionParameter(cdItemsTabPrefix + "removeBtn");
		customAction.addActionParameter(cdItemsTabPrefix + "excelButton");
		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(customAction);
		return cdItemsTabConfig;
	}

	private void addContractHeaderDetailDecissionLayout(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdItemsTabPrefix + "HeaderDetailDecissionLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cdItemsComponentList.add(gtnLayoutConfig);

		addDecissionLevel(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
		addDecissionView(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addContractHeaderFieldPanel(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(cdItemsTabPrefix + "Decission_Header_FieldPanel", true, parent);
		cdItemsComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cdItemsTabPrefix + "verticalFieldLayout", true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdItemsComponentList.add(gtnLayoutConfig);

		addHeaderFieldComponent(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		itemsTabHistoryDataTableComponent(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void addDecissionLevel(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent) {
		String componentId = cdItemsTabPrefix + DECISSION_LEVEL;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Level:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameworkOptionGroupConfig componentOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		componentOptionConfig.setValuesFromService(false);
		componentOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.HEADER, GtnWsContractDashboardContants.DETAIL));
		componentOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.HEADER);

		componentConfig.setGtnUIFrameworkOptionGroupConfig(componentOptionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		customAction.addActionParameter(Arrays.asList(cdItemsTabPrefix + "Decission_Header_FieldPanel",
				cdItemsTabPrefix + "Decission_Header_View", cdItemsTabPrefix + "_"
						+ GtnWsContractDashboardContants.DETAIL + "_" + GtnFrameworkCommonStringConstants.LAYOUT));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
		GtnUIFrameWorkActionConfig tabLoadCustomAction = new GtnUIFrameWorkActionConfig();
		tabLoadCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadCustomAction.addActionParameter(GtnFrameworkItemsTabLoadAction.class.getName());
		tabLoadCustomAction.addActionParameter(cdItemsTabPrefix + DECISSION_LEVEL);
		componentConfig.addGtnUIFrameWorkActionConfig(tabLoadCustomAction);
	}

	private void addDecissionView(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent) {
		String componentId = cdItemsTabPrefix + "Decission_Header_View";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("View:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdItemsComponentList.add(componentConfig);

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
				cdItemsTabPrefix + "CssLayout_" + GtnWsContractDashboardContants.CURRENT + "_"
						+ GtnWsContractDashboardContants.PENDING + "_",
				cdItemsTabPrefix + "_" + GtnWsContractDashboardContants.HISTORY + "_ResultTable"
						+ GtnFrameworkCommonStringConstants.LAYOUT));
		customAction.addActionParameter("0");
		customAction.addActionParameter("0");
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addHeaderFieldComponent(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(cdItemsTabPrefix + "CssLayout_"
				+ GtnWsContractDashboardContants.CURRENT + "_" + GtnWsContractDashboardContants.PENDING + "_", true,
				parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdItemsComponentList.add(gtnLayoutConfig);

		addIfpId(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpNo(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpName(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpStatus(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpStartDate(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpEndDate(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpDesignation(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addParentIfpId(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addParentIfpName(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpType(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCreatedBy(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addCreatedDate(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addIfpCategory(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addModifiedBy(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
		addModifiedDate(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);
	}

	private void addIfpId(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Text_IFPID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP ID");
		componentConfig.setEnable(false);
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addIfpNo(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Text_IFPNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemsIfpnoConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdItemsIfpnoConfig.setAuthorizationIncluded(true);
		cdItemsIfpnoConfig.setComponentName("IFP No");
		cdItemsIfpnoConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdItemsComponentList.add(cdItemsIfpnoConfig);
		componentIdList.add(cdItemsIfpnoConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdItemsIfpnoValConfig = new GtnUIFrameworkValidationConfig();
		cdItemsIfpnoValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cdItemsIfpnoConfig.setGtnUIFrameworkValidationConfig(cdItemsIfpnoValConfig);
	}

	private void addIfpName(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {

		String componentId = cdItemsTabPrefix + "Text_IFPName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemsIfpNameConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cdItemsIfpNameConfig.setAuthorizationIncluded(true);
		cdItemsIfpNameConfig.setComponentName("IFP Name");
		cdItemsIfpNameConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdItemsComponentList.add(cdItemsIfpNameConfig);
		componentIdList.add(cdItemsIfpNameConfig.getComponentId());

		GtnUIFrameworkValidationConfig cdItemsIfpNameValConfig = new GtnUIFrameworkValidationConfig();
		cdItemsIfpNameValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		cdItemsIfpNameConfig.setGtnUIFrameworkValidationConfig(cdItemsIfpNameValConfig);
	}

	private void addIfpStatus(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Combo_IFPStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Status");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdItemsComponentList.add(componentConfig);

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

	private void addIfpStartDate(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {

		String componentId = cdItemsTabPrefix + "IFPStartDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Start Date");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameWorkActionConfig cdItemsIfpStartDateValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		cdItemsIfpStartDateValidationAction.addActionParameter("CDProcessView_CTCFPEndDate");
		cdItemsIfpStartDateValidationAction.addActionParameter(1);
		cdItemsIfpStartDateValidationAction.addActionParameter("Select IFP Start date before ?");
		cdItemsIfpStartDateValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig cdItemsIfpStartDateAfterValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		cdItemsIfpStartDateAfterValidationAction.addActionParameter("CDProcessView_CTCFPStartDate");
		cdItemsIfpStartDateAfterValidationAction.addActionParameter(-1);
		cdItemsIfpStartDateAfterValidationAction.addActionParameter("Select IFP Start date After ?");
		cdItemsIfpStartDateAfterValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
		GtnUIFrameWorkActionConfig cdItemsIfpStartDateValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cdItemsIfpStartDateValidationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdItemsIfpStartDateValidationActionConfig
				.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		cdItemsIfpStartDateValidationActionConfig.addActionParameter(componentConfig.getComponentId());
		cdItemsIfpStartDateValidationActionConfig.addActionParameter(new ArrayList<String>());
		cdItemsIfpStartDateValidationActionConfig.addActionParameter(Arrays.asList(cdItemsTabPrefix + "IFPEndDate"));
		cdItemsIfpStartDateValidationActionConfig.addActionParameter("Start Date");
		cdItemsIfpStartDateValidationActionConfig.addActionParameter("Start");
		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(cdItemsIfpStartDateValidationActionConfig);
		dateFieldConfig.setValidationActionConfigList(
				Arrays.asList(cdItemsIfpStartDateValidationAction, cdItemsIfpStartDateAfterValidationAction));
		componentConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addIfpEndDate(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "IFPEndDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP End Date");
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameWorkActionConfig cdItemsIfpEndDateValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		cdItemsIfpEndDateValidationAction.addActionParameter("CDProcessView_CTCFPEndDate");
		cdItemsIfpEndDateValidationAction.addActionParameter(1);
		cdItemsIfpEndDateValidationAction.addActionParameter("Select IFP End date before ?");
		cdItemsIfpEndDateValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig cdItemsIfpEndDateAfterValidationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DATE_COMPARE_VALIDATE_ACTION);
		cdItemsIfpEndDateAfterValidationAction.addActionParameter("CDProcessView_CTCFPStartDate");
		cdItemsIfpEndDateAfterValidationAction.addActionParameter(-1);
		cdItemsIfpEndDateAfterValidationAction.addActionParameter("Select IFP End date After ?");
		cdItemsIfpEndDateAfterValidationAction.addActionParameter(DATE_FORMAT);

		GtnUIFrameWorkActionConfig cdItemsIfpEndDateValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cdItemsIfpEndDateValidationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdItemsIfpEndDateValidationActionConfig
				.addActionParameter(GtnFrameworkContractDateValidationAction.class.getName());
		cdItemsIfpEndDateValidationActionConfig.addActionParameter(componentConfig.getComponentId());
		cdItemsIfpEndDateValidationActionConfig.addActionParameter(Arrays.asList(cdItemsTabPrefix + "IFPStartDate"));
		cdItemsIfpEndDateValidationActionConfig.addActionParameter(new ArrayList<String>());
		cdItemsIfpEndDateValidationActionConfig.addActionParameter("End Date");
		cdItemsIfpEndDateValidationActionConfig.addActionParameter("End");
		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(cdItemsIfpEndDateValidationActionConfig);
		dateFieldConfig.setValidationActionConfigList(
				Arrays.asList(cdItemsIfpEndDateValidationAction, cdItemsIfpEndDateAfterValidationAction));
		componentConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addIfpDesignation(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Combo_IFPDesignation";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Designation");
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.IFP_DESIGNATION);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkFieldEnableDisableAction.class.getName());
		customAction.addActionParameter(componentConfig.getComponentId());
		customAction.addActionParameter(Boolean.TRUE);
		customAction.addActionParameter("child");
		customAction.setFieldValues(Arrays.asList(cdItemsTabPrefix + TEXT_PIFPID, cdItemsTabPrefix + TEXT_PIFP_NAME));
		componentConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addParentIfpId(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + TEXT_PIFPID;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemsParentIfpIdConfig = commonConfig.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		cdItemsParentIfpIdConfig.setAuthorizationIncluded(true);
		cdItemsParentIfpIdConfig.setComponentName("Parent IFP ID");
		cdItemsComponentList.add(cdItemsParentIfpIdConfig);
		componentIdList.add(cdItemsParentIfpIdConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig cdItemsParentIfpIdTextboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		cdItemsParentIfpIdTextboxConfig.setReadOnly(true);
		cdItemsParentIfpIdTextboxConfig.setEnable(false);
		cdItemsParentIfpIdConfig.setGtnTextBoxConfig(cdItemsParentIfpIdTextboxConfig);

		GtnUIFrameWorkActionConfig cdItemsParentIfpIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
		cdItemsParentIfpIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		cdItemsParentIfpIdPopupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);
		cdItemsParentIfpIdPopupActionConfig.addActionParameter("Item Family Plan Search");
		cdItemsParentIfpIdPopupActionConfig.addActionParameter("70%");
		cdItemsParentIfpIdPopupActionConfig.addActionParameter(null);
		cdItemsParentIfpIdPopupActionConfig.addActionParameter(null);
		cdItemsParentIfpIdPopupActionConfig.addActionParameter(
				Arrays.asList(cdItemsParentIfpIdConfig.getComponentId(), Arrays.asList("ifpId", "ifpName"),
						Arrays.asList(cdItemsTabPrefix + TEXT_PIFPID, cdItemsTabPrefix + TEXT_PIFP_NAME), null));
		cdItemsParentIfpIdConfig.addGtnUIFrameWorkActionConfig(cdItemsParentIfpIdPopupActionConfig);
	}

	private void addParentIfpName(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + TEXT_PIFP_NAME;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemsParentIfpNameConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		cdItemsParentIfpNameConfig.setAuthorizationIncluded(true);
		cdItemsParentIfpNameConfig.setComponentName("Parent IFP Name");
		cdItemsComponentList.add(cdItemsParentIfpNameConfig);
		componentIdList.add(cdItemsParentIfpNameConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig cdItemsParentIfpNameTextboxConfig = commonConfig.getTextBoxConfig(true, false,
				true);
		cdItemsParentIfpNameTextboxConfig.setReadOnly(true);
		cdItemsParentIfpNameTextboxConfig.setEnable(false);
		cdItemsParentIfpNameConfig.setGtnTextBoxConfig(cdItemsParentIfpNameTextboxConfig);

		GtnUIFrameWorkActionConfig cdItemsParentIfpNamePopupActionConfig = new GtnUIFrameWorkActionConfig();
		cdItemsParentIfpNamePopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		cdItemsParentIfpNamePopupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);
		cdItemsParentIfpNamePopupActionConfig.addActionParameter("Item Family Plan Search");
		cdItemsParentIfpNamePopupActionConfig.addActionParameter("70%");
		cdItemsParentIfpNamePopupActionConfig.addActionParameter(null);
		cdItemsParentIfpNamePopupActionConfig.addActionParameter(null);
		cdItemsParentIfpNamePopupActionConfig.addActionParameter(
				Arrays.asList(cdItemsParentIfpNameConfig.getComponentId(), Arrays.asList("ifpId", "ifpName"),
						Arrays.asList(cdItemsTabPrefix + TEXT_PIFPID, cdItemsTabPrefix + TEXT_PIFP_NAME), null));
		cdItemsParentIfpNameConfig.addGtnUIFrameWorkActionConfig(cdItemsParentIfpNamePopupActionConfig);
	}

	private void addIfpType(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Combo_IFPType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("IFP Type");
		cdItemsComponentList.add(componentConfig);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.IFP_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Text_CreatedBy";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Created By");
		componentConfig.setEnable(false);
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "CreatedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Created Date");
		componentConfig.setEnable(false);
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addIfpCategory(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Combo_IFPCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Category");
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.IFP_CATEGORY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "Text_ModifiedBy";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Modified By");
		componentConfig.setEnable(false);
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "ModifiedDate";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Modified Date");
		componentConfig.setEnable(false);
		cdItemsComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void itemsTabHistoryDataTableComponent(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent) {
		String componentId = cdItemsTabPrefix + "_" + GtnWsContractDashboardContants.HISTORY + "_ResultTable";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setPageLength(10);
		attachResults.setItemPerPage(10);
		attachResults.setSinkItemPerPageWithPageLength(false);
		attachResults.setTableColumnDataType(GtnFrameworkContractDashboardContants.getItemHistoryColumnType());
		attachResults.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getItemHistoryHeader());
		attachResults.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getItemHistoryColumn());
		attachResults.setCountUrl(GTN_WS_IFP_SERVICE + GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE);
		attachResults.setResultSetUrl(GTN_WS_IFP_SERVICE + GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE);
		attachResults.setModuleName("");

	}

	public void addDeatilsLayout(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent) {

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(cdItemsTabPrefix + "_"
				+ GtnWsContractDashboardContants.DETAIL + "_" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setVisible(false);
		cdItemsComponentList.add(gtnLayoutConfig);
		massUpdatePanel(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
		reordLayout(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
		itemsResultPanel(cdItemsComponentList, gtnLayoutConfig.getComponentId());
		addRemoveButtonLayout(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId());
	}

	private void massUpdatePanel(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(cdItemsTabPrefix + "MassUpdatePanel",
				true, parent);
		cdItemsComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(cdItemsTabPrefix + "MassUpdateLayout", true, gtnPanelConfig.getComponentId());
		cdItemsComponentList.add(gtnLayoutConfig);

		List<String> componentIdList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customItemsMassUpdateDisableAction = new GtnUIFrameWorkActionConfig();
		addMassCheck(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(),
				customItemsMassUpdateDisableAction);
		addMassPopulateField(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(), componentIdList);

		addPopulateButtonLayout(cdItemsComponentList, cdItemsTabPrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		addHiddenField(cdItemsComponentList, gtnLayoutConfig.getComponentId());
		customItemsMassUpdateDisableAction.setFieldValues(componentIdList);
	}

	private void addHiddenField(List<GtnUIFrameworkComponentConfig> componentList, String parent) {
		GtnUIFrameworkComponentConfig hiddenFieldComponentConfig = commonConfig
				.getHorizontalLayoutConfig(GtnFrameworkContractDashboardContants.HIDDEN_IFP_LAYOUT, true, parent);
		componentList.add(hiddenFieldComponentConfig);

		GtnUIFrameworkComponentConfig hiddenComponent = commonConfig.getUIFrameworkComponentConfig(
				GtnFrameworkContractDashboardContants.HIDDEN_IFP_COMPONENT, true,
				hiddenFieldComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		hiddenComponent.setAuthorizationIncluded(true);

		hiddenComponent.setVisible(false);
		componentList.add(hiddenComponent);
	}

	private void addMassCheck(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, GtnUIFrameWorkActionConfig customAction) {
		String componentId = cdItemsTabPrefix + "SearchType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig massCheckConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		massCheckConfig.setAuthorizationIncluded(true);
		massCheckConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdItemsComponentList.add(massCheckConfig);

		GtnUIFrameworkOptionGroupConfig massCheckOptionConfig = new GtnUIFrameworkOptionGroupConfig();
		massCheckOptionConfig.setValuesFromService(false);

		massCheckOptionConfig.setItemValues(
				Arrays.asList(GtnWsContractDashboardContants.ENABLE, GtnWsContractDashboardContants.DISABLE));
		massCheckOptionConfig.setDefaultSelection(GtnWsContractDashboardContants.DISABLE);
		massCheckConfig.setGtnUIFrameworkOptionGroupConfig(massCheckOptionConfig);
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		massCheckConfig.addGtnUIFrameWorkActionConfig(customAction);
	}

	private void addMassPopulateField(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent, List<String> componentIdList) {
		String componentId = cdItemsTabPrefix + "PopulateField";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig cdItemsMassPopulateFieldConfig = commonConfig.getUIFrameworkComponentConfig(
				componentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		cdItemsMassPopulateFieldConfig.setAuthorizationIncluded(true);
		cdItemsComponentList.add(cdItemsMassPopulateFieldConfig);
		cdItemsMassPopulateFieldConfig.setEnable(false);
		GtnUIFrameworkComboBoxConfig populateFieldComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		populateFieldComboBoxConfig.setItemValues(Arrays.asList("IFP Start Date", "IFP End Date", "IFP Status"));
		cdItemsMassPopulateFieldConfig.setGtnComboboxConfig(populateFieldComboBoxConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cdItemsMassPopulateFieldConfig.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameWorkActionConfig cdItemsMassPopulateAction = new GtnUIFrameWorkActionConfig();
		cdItemsMassPopulateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdItemsMassPopulateAction.addActionParameter(GtnUIFrameworkPopulateFieldValueChangeAction.class.getName());
		cdItemsMassPopulateAction.addActionParameter(componentIdList);
		cdItemsMassPopulateAction.addActionParameter("1");
		cdItemsMassPopulateAction.addActionParameter("2");
		cdItemsMassPopulateFieldConfig.addGtnUIFrameWorkActionConfig(cdItemsMassPopulateAction);
		componentIdList.add(cdItemsMassPopulateFieldConfig.getComponentId());
		GtnUIFrameworkComponentConfig cfpStartDateConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "_IFPStartDate_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		cdItemsComponentList.add(cfpStartDateConfig);
		cfpStartDateConfig.setVisible(false);
		cfpStartDateConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(cfpStartDateConfig.getComponentId());

		GtnUIFrameworkComponentConfig cfpEndDateConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "_IFPEndDate_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		cfpEndDateConfig.setVisible(false);
		cdItemsComponentList.add(cfpEndDateConfig);
		cfpEndDateConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentIdList.add(cfpEndDateConfig.getComponentId());

		GtnUIFrameworkComponentConfig cfpStatusConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "Combo_IFPStatus_", true, gtnLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpStatusConfig.setVisible(false);
		cdItemsComponentList.add(cfpStatusConfig);
		cfpStatusConfig.setGtnUIFrameworkValidationConfig(valConfig);
		GtnUIFrameworkComboBoxConfig cfpStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		cfpStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cfpStatusComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		cfpStatusConfig.setGtnComboboxConfig(cfpStatusComboBoxConfig);
		componentIdList.add(cfpStatusConfig.getComponentId());
	}

	private void addPopulateButtonLayout(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(cdItemsTabPrefix + "PopulateButtonlayout", true, parent);
		cdItemsComponentList.add(layoutConfig);
		addPopulateButtonComponent(cdItemsComponentList, cdItemsTabPrefix, layoutConfig.getComponentId(),
				componentIdList);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig populateButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "PopulateButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateButtonConfig.setAuthorizationIncluded(true);
		populateButtonConfig.setComponentName("Populate");
		populateButtonConfig.setEnable(false);
		cdItemsComponentList.add(populateButtonConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyPopulateAction.class.getName());
		customAction.addActionParameter(componentIdList);
		customAction.addActionParameter("1");
		customAction.addActionParameter("2");
		customAction.addActionParameter(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM);
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		populateButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateButtonConfig.getComponentId());
		GtnUIFrameworkComponentConfig populateAllButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "PopulateAllButton", true, parent, GtnUIFrameworkComponentType.BUTTON);
		populateAllButtonConfig.setAuthorizationIncluded(true);
		populateAllButtonConfig.setComponentName("Populate All");
		populateAllButtonConfig.setEnable(false);
		cdItemsComponentList.add(populateAllButtonConfig);
		populateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		populateAllButtonConfig.addGtnUIFrameWorkActionConfig(customAction);
		componentIdList.add(populateAllButtonConfig.getComponentId());
	}

	private void reordLayout(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String cdItemsTabPrefix,
			String parent) {
		String componentId = cdItemsTabPrefix + "record";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(componentId + "Css" + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig layoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnLayoutConfig.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdItemsComponentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				layoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Record:");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		cdItemsComponentList.add(componentConfig);

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
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkItemsTabTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_IFP_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(componentConfig.getComponentId(),
				GtnFrameworkContractDashboardContants.HIDDEN_IFP_COMPONENT));
		componentConfig.addGtnUIFrameWorkActionConfig(tableLoadActionConfig);
	}

	private void itemsResultPanel(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE;
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		gtnPanelConfig.setComponentName("Results");
		cdItemsComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdItemsComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
                componentConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig itemsResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(itemsResultTable);
		itemsResultTable.setEditable(true);
		itemsResultTable.setFilterBar(true);
		itemsResultTable.setSelectable(true);
		itemsResultTable.setPageLength(5);
		itemsResultTable.setItemPerPage(5);
		itemsResultTable.setSinkItemPerPageWithPageLength(false);
		itemsResultTable.setTableColumnDataType(GtnFrameworkContractDashboardContants.getItemDetailColumnType());
		itemsResultTable.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getItemDetailHeader());
		itemsResultTable.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getItemDetailColumn());
		itemsResultTable.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.ITEMS_HELPER_VALUE });
		itemsResultTable.setExtraColumnDataType(new Class[] { String.class });
		itemsResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEMS_DETAIL_TABLE_DATA);
		itemsResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEMS_DETAIL_TABLE_DATA);
		itemsResultTable.setColumnCheckBoxId(CHECK_RECORD_ID);
		itemsResultTable.setCustomFilterConfigMap(getTableFilterFieldMap());
		itemsResultTable.setInvisibleFilterPropertyIds(CHECK_RECORD_ID);
		itemsResultTable
				.setEditableColumnList(Arrays.asList(CHECK_RECORD_ID, IFP_STATUS, "ifpStartDate", "ifpEndDate"));
		List<GtnUIFrameworkComponentConfig> editableFieldList = new ArrayList<>(
				itemsResultTable.getEditableColumnList().size());

		getTableEditableField(editableFieldList);
		itemsResultTable.setEditableField(editableFieldList);
		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFrameworkTableCheckAllAction.class.getName());
		checkAllAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ALL_ITEM);
		itemsResultTable.addColumnCheckActionConfig(checkAllAction);
		itemsViewResultTable(cdItemsComponentList, gtnLayoutConfig.getComponentId());
	}

	private void itemsViewResultTable(List<GtnUIFrameworkComponentConfig> cdItemsComponentList, String parent) {
		String componentId = GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE + "View";

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				parent, GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameworkPagedTableConfig itemsViewResultTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(itemsViewResultTable);
		itemsViewResultTable.setPageLength(10);
		itemsViewResultTable.setItemPerPage(10);
		itemsViewResultTable
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getItemDetailViewColumnType());
		itemsViewResultTable.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getItemDetailViewHeader());
		itemsViewResultTable.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getItemDetailViewColumn());
		itemsViewResultTable.setExtraColumn(new Object[] { GtnFrameworkContractDashboardContants.ITEMS_HELPER_VALUE });
		itemsViewResultTable.setExtraColumnDataType(new Class[] { String.class });
		itemsViewResultTable.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEMS_DETAIL_VIEW_TABLE_DATA);
		itemsViewResultTable.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CD_ITEMS_DETAIL_VIEW_TABLE_DATA);
	}

	private void addRemoveButtonLayout(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent) {
		String componentId = cdItemsTabPrefix + "removeBtn";
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdItemsComponentList.add(layoutConfig);
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				layoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Remove");
		cdItemsComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig checkSelectedActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkSelectedActionConfig.addActionParameter(GtnFrameworkUpdateSelectedRecordInTempTableAction.class.getName());
		checkSelectedActionConfig.addActionParameter(GtnWsContractDashboardContants.CHECK_SELECTED_ITEMS);

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
		removeActionConfig.addActionParameter(GtnWsContractDashboardContants.REMOVE_ITEMS);

		GtnUIFrameWorkActionConfig companyChangeIndicatorAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		companyChangeIndicatorAction.addActionParameter(GtnFrameworkItemChangeIndicatorAction.class.getName());

		GtnUIFrameWorkActionConfig tabLoadAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tabLoadAction.addActionParameter(GtnFrameworkItemsTabLoadAction.class.getName());
		confirmActionConfig
				.addActionParameter(Arrays.asList(removeActionConfig, companyChangeIndicatorAction, tabLoadAction));

		checkSelectedActionConfig.addActionParameter(failureActionConfig);
		checkSelectedActionConfig.addActionParameter(confirmActionConfig);

		componentConfig.addGtnUIFrameWorkActionConfig(checkSelectedActionConfig);
		addExcelButtonComponent(cdItemsComponentList, cdItemsTabPrefix, layoutConfig.getComponentId());
		addViewExcelButtonComponent(cdItemsComponentList, cdItemsTabPrefix, layoutConfig.getComponentId());
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "excelButton", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdItemsComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Item Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE);
		gtnExcelButtonConfig.setExcludeColumnsList(Arrays.asList(CHECK_RECORD_ID));
		gtnExcelButtonConfig.setHelperTableMapedPropertyIdList(Arrays.asList(IFP_STATUS));
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addViewExcelButtonComponent(List<GtnUIFrameworkComponentConfig> cdItemsComponentList,
			String cdItemsTabPrefix, String parent) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				cdItemsTabPrefix + "excelButtonView", true, parent, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		cdItemsComponentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Item Details");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setExportTableId(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE + "View");
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void getTableEditableField(List<GtnUIFrameworkComponentConfig> cdItemsComponentList) {
		GtnUIFrameworkComponentConfig checkRecordConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.CHECKBOX);
		cdItemsComponentList.add(checkRecordConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkFieldFactoryAction.class.getName());
		customAction.addActionParameter(GtnWsContractDashboardContants.POPULATE_ITEM_FIELD);
		customAction.addActionParameter(18);
		checkRecordConfig.setGtnUIFrameWorkItemClickActionConfigList(Arrays.asList(customAction));

		GtnUIFrameWorkActionConfig focusAction = new GtnUIFrameWorkActionConfig();
		focusAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		focusAction.addActionParameter(GtnUIFrameworkFocusAction.class.getName());

		GtnUIFrameworkComponentConfig ifpStatusConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.COMBOBOX);
		cdItemsComponentList.add(ifpStatusConfig);
		ifpStatusConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		ifpStatusConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
		GtnUIFrameworkComboBoxConfig ifpStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		ifpStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpStatusComboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		ifpStatusConfig.setGtnComboboxConfig(ifpStatusComboBoxConfig);

		GtnUIFrameworkComponentConfig ifpStartDateConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.DATEFIELD);
		cdItemsComponentList.add(ifpStartDateConfig);
		ifpStartDateConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		ifpStartDateConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
		GtnUIFrameworkComponentConfig ifpEndDateConfig = commonConfig.getUIFrameworkComponentConfig("", false, null,
				GtnUIFrameworkComponentType.DATEFIELD);
		cdItemsComponentList.add(ifpEndDateConfig);
		ifpEndDateConfig.setGtnUIFrameWorkFocusActionConfigList(Arrays.asList(focusAction));
		ifpEndDateConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(customAction));
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
		cfpStatusFilterConfig.setPropertId(IFP_STATUS);
		cfpStatusFilterConfig.setGtnComponentConfig(cfpStatusConfig);
		cfpStatusFilterConfig.setGtnComponentType(cfpStatusConfig.getComponentType());
		customFilterConfigMap.put(cfpStatusFilterConfig.getPropertId(), cfpStatusFilterConfig);
		return customFilterConfigMap;
	}
}
