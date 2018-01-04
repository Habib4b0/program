/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRSProcessingOptionConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addCompanyAdditionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkRSConstants.PROCESSING_OPTION_TAB, false, null);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getCssLayoutConfig("addressTabLayout", true,
				GtnFrameworkRSConstants.PROCESSING_OPTION_TAB);
		componentList.add(cssGtnLayout);
		companyAdditionFields(componentList);
	}

	private void companyAdditionFields(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT, true,
				GtnFrameworkRSConstants.PROCESSING_OPTION_TAB);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(layoutConfig);

		addSearchLayout(componentList);
		addDualListBoxLayout(componentList);
	}

	private void addSearchLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		identifierInformationFields(componentList);
	}

	private void identifierInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addRebateProcessingType(componentList);
		addValidationProfile(componentList);
		addInterestBearingIndicator(componentList);
		addInterestBearingBasis(componentList);
	}

	private void addInterestBearingBasis(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPCompanyAdditionInterestBearingBasislayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				"CFPCompanyAdditionInterestBearingBasis", true, "CFPCompanyAdditionInterestBearingBasislayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName("Interest Bearing Basis");

		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();

		companyQualifierNameConfig.setItemValues(Arrays.asList(""));
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);
	}

	private void addInterestBearingIndicator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPCompanyAdditionInterestBearingIndicatorlayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_INTEREST_BEARING_INDICATOR, true,
				"CFPCompanyAdditionInterestBearingIndicatorlayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName("Interest Bearing Indicator");

		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();

		companyQualifierNameConfig.setItemValues(Arrays.asList(""));
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);
	}

	private void addRebateProcessingType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPCompanyAdditionRebateProcessingTypelayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				"CFPCompanyAdditionRebateProcessingType", true, "CFPCompanyAdditionRebateProcessingTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName("Rebate Processing Type");

		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();
		companyQualifierNameConfig.setItemValues(Arrays.asList(""));
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);
	}

	private void addValidationProfile(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPCompanyAdditionValidationProfilelayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				"CFPCompanyAdditionValidationProfile", true, "CFPCompanyAdditionValidationProfilelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName("Validation Profile");

		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();
		companyQualifierNameConfig.setItemValues(Arrays.asList(""));
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);
	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT);

		componentList.add(layoutConfig);
		leftResultLayout(componentList);
		moveButtons(componentList);
		rightResultLayout(componentList);
	}

	private void moveButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);

		List<String> styles = new ArrayList<>();
		styles.add("gtnGrid-DualList-Buttons");
		layoutConfig.setComponentStyle(styles);
		layoutConfig.getGtnLayoutConfig().setMargin(true);
		layoutConfig.getGtnLayoutConfig().setSpacing(true);
		componentList.add(layoutConfig);
		moveRight(componentList);
		moveLeft(componentList);
		moveAllRight(componentList);
		moveAllLeft(componentList);
	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoverightButtonLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoverightButtons", true, "CFPcompanyAdditionmoverightButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName(" > ");
		attachButtonConfig.setComponentWidth("53px");
		componentList.add(attachButtonConfig);

	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoveLeftButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoveLeftButtons", true, "CFPcompanyAdditionmoveLeftButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName(" < ");
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentWidth("53px");
		componentList.add(attachButtonConfig);

	}

	private void moveAllRight(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoveAllrightButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoveAllrightButtons", true, "CFPcompanyAdditionmoveAllrightButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName(" >> ");
		attachButtonConfig.setComponentWidth("53px");
		componentList.add(attachButtonConfig);

	}

	private void moveAllLeft(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoveAllLeftButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoveAllLeftButtons", true, "CFPcompanyAdditionmoveAllLeftButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setComponentName(" << ");
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentWidth("53px");
		componentList.add(attachButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.setActionParameterList(Arrays.asList(
				new Object[] { "com.stpl.gtn.gtn2o.ui.module.cfp.config.action.GtnFrameworkCFPMoveAllLeftAction" }));

		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void leftResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("CFPleftResultLayout",
				true, GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT);
		layoutConfig.setComponentWidth("530px");
		componentList.add(layoutConfig);

		leftResultDataTable(componentList);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rsProcessingOptionLeftTableConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPleftResultTable", true, "CFPleftResultLayout", GtnUIFrameworkComponentType.DATATABLE);
		rsProcessingOptionLeftTableConfig.setAuthorizationIncluded(true);
		rsProcessingOptionLeftTableConfig.setComponentHight("400px");
		rsProcessingOptionLeftTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		rsProcessingOptionLeftTableConfig.setComponentStyle(tableStyle);

		componentList.add(rsProcessingOptionLeftTableConfig);

		GtnUIFrameworkPagedTableConfig rsProcessingOptionLeftTable = configProvider.getPagedTableConfig(true, true,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_SEARCH_SERVICE,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_SEARCH_SERVICE,
				"priceScheduleItemAddition", "priceScheduleItemAddition");
		rsProcessingOptionLeftTableConfig.setGtnPagedTableConfig(rsProcessingOptionLeftTable);
		rsProcessingOptionLeftTable.setEditable(false);
		rsProcessingOptionLeftTable.setTableColumnDataType(new Class[] { String.class, String.class, String.class });
		rsProcessingOptionLeftTable.setTableVisibleHeader(new String[] { "Rule No", "Rule Name", "Version" });
		rsProcessingOptionLeftTable.setTableColumnMappingId(new Object[] { "ifpId", "ifpNo", "ifpName" });
		rsProcessingOptionLeftTable.setExtraColumn(new Object[] { "systemId" });
		rsProcessingOptionLeftTable.setExtraColumnDataType(new Class[] { String.class });
	}

	private void rightResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("CFPrightResultLayout",
				true, GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT);
		layoutConfig.setComponentWidth("530px");

		componentList.add(layoutConfig);

		rightResultDataTable(componentList);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rsProcessingOptionRightTableConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPrightResultTable", true, "CFPrightResultLayout", GtnUIFrameworkComponentType.DATATABLE);
		rsProcessingOptionRightTableConfig.setAuthorizationIncluded(true);
		rsProcessingOptionRightTableConfig.setComponentHight("400px");
		rsProcessingOptionRightTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		rsProcessingOptionRightTableConfig.setComponentStyle(tableStyle);

		componentList.add(rsProcessingOptionRightTableConfig);

		GtnUIFrameworkPagedTableConfig rsProcessingOptionRightTable = configProvider.getPagedTableConfig(true, true,
				"/cfp/companyAdditionRightTableSearch", "/cfp/companyAdditionRightTableSearch", "companyFamilyPlan",
				"");
		rsProcessingOptionRightTableConfig.setGtnPagedTableConfig(rsProcessingOptionRightTable);
		rsProcessingOptionRightTable.setEditable(false);
		rsProcessingOptionRightTable.setTableColumnDataType(new Class[] { String.class, String.class, String.class });
		rsProcessingOptionRightTable.setTableVisibleHeader(new String[] { "Rule No", "Rule Name", "Version" });
		rsProcessingOptionRightTable.setTableColumnMappingId(new Object[] { "ifpId", "ifpNo", "ifpName" });
		rsProcessingOptionRightTable.setExtraColumn(new Object[] { "companyMasterSid" });
		rsProcessingOptionRightTable.setExtraColumnDataType(new Class[] { Integer.class });
	}
}
