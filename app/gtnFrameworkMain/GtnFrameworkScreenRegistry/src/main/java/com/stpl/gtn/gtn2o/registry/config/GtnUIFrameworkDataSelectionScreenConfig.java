package com.stpl.gtn.gtn2o.registry.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.hierarchy.GtnFrameworkForecastCustomerHierarchyConfig;
import com.stpl.gtn.gtn2o.registry.config.hierarchy.GtnFrameworkForecastProdHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkDataSelectionScreenConfig {

	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getDataSelectionView(String nameSpace) {

		GtnUIFrameworkViewConfig dataSelectionView = configProvider.getViewConfig(nameSpace + "_" + "Data Selection",
				nameSpace + "_" + "V001", true);

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<GtnUIFrameworkComponentConfig>();
		dataSelectionView.setGtnComponentList(componentList);
		addRootLayout(componentList, nameSpace);
		return dataSelectionView;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig mainLayout = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "dataSelectionMainLayout", false, null);
		mainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		mainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(mainLayout);

		addProjectionOptionsPanel(componentList, nameSpace);
		addCustomerSelectionPanel(componentList, nameSpace);

		GtnFrameworkForecastCustomerHierarchyConfig customerSelectionConfig = new GtnFrameworkForecastCustomerHierarchyConfig();
		componentList.addAll(customerSelectionConfig.getCustomerSelectionLayoutCompoents(nameSpace));
		addProductSelectionPanel(componentList, nameSpace);

		GtnFrameworkForecastProdHierarchyConfig productSelectionConfig = new GtnFrameworkForecastProdHierarchyConfig();
		componentList.addAll(productSelectionConfig.getProductSelectionLayoutCompoents(nameSpace));

		addActionButtonLayout(componentList, nameSpace);
		addProjectionResultsPanel(componentList, nameSpace);
		addControlButtonLayout(componentList, nameSpace);
	}

	private void addProjectionOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionOptionsPanel = new GtnUIFrameworkComponentConfig();
		projectionOptionsPanel.setComponentName("Projection Options");
		projectionOptionsPanel.setComponentId(nameSpace + "_" + "projectionOptions");
		projectionOptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionsPanel.setParentComponentId(nameSpace + "_" + "dataSelectionMainLayout");
		projectionOptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionOptionsPanel.setAddToParent(Boolean.TRUE);
		projectionOptionsPanel.setSpacing(true);
		componentList.add(projectionOptionsPanel);

		GtnUIFrameworkLayoutConfig projectionOptionsMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionsMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionOptionsMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionsMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionsMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionsMainLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsMainLayout");
		projectionOptionsMainLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionsMainLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptions");
		projectionOptionsMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionsMainLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionsMainLayoutConfig.setGtnLayoutConfig(projectionOptionsMainLayout);
		componentList.add(projectionOptionsMainLayoutConfig);

		addProjectionOptionInnerLayout(componentList, nameSpace);
	}

	private void addProjectionOptionInnerLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsInnerLayout");
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptionsMainLayout");
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		addModeSelectionLayout(componentList, nameSpace);
		addFromToPeriodLayout(componentList, nameSpace);
		addProjectionSelectionLayout(componentList, nameSpace);
		addMode(componentList, nameSpace);
		addPrivateViewLookup(componentList, nameSpace);
		addCompany(componentList, nameSpace);
		addProjectionName(componentList, nameSpace);
		addPublicViewLookup(componentList, nameSpace);
		addBusinessUnit(componentList, nameSpace);
		addProjectionDescription(componentList, nameSpace);
		addTimePeriod(componentList, nameSpace);
	}

	private void addModeSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig modeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig modeSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeSelectionLayoutConfig.setComponentId(nameSpace + "_" + "modeSelectionLayout");
		modeSelectionLayoutConfig.setAddToParent(Boolean.TRUE);
		modeSelectionLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptionsInnerLayout");
		modeSelectionLayoutConfig.setGtnLayoutConfig(modeSelectionLayout);
		componentList.add(modeSelectionLayoutConfig);
	}

	private void addFromToPeriodLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig fromToLayout = new GtnUIFrameworkLayoutConfig();
		fromToLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setComponentId(nameSpace + "_" + "fromToLayout");
		fromToLayoutConfig.setAddToParent(Boolean.TRUE);
		fromToLayoutConfig.setSpacing(Boolean.TRUE);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptionsMainLayout");
		fromToLayoutConfig.setGtnLayoutConfig(fromToLayout);
		componentList.add(fromToLayoutConfig);
	}

	private void addProjectionSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionSelectionLayout = new GtnUIFrameworkLayoutConfig();
		projectionSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig projectionSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionSelectionLayoutConfig.setComponentId(nameSpace + "_" + "projectionSelectionLayout");
		projectionSelectionLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionSelectionLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptionsInnerLayout");
		projectionSelectionLayoutConfig.setGtnLayoutConfig(projectionSelectionLayout);
		componentList.add(projectionSelectionLayoutConfig);
	}

	private void addMode(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		String componentId = nameSpace + "_" + "modeGroup";
		GtnUIFrameworkComponentConfig modeLayoutConfig = configProvider.getHorizontalLayoutConfig(componentId, true,
				nameSpace + "_" + "modeSelectionLayout");
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkComponentConfig modeOptionRadioGroup = new GtnUIFrameworkComponentConfig();
		modeOptionRadioGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		modeOptionRadioGroup.setComponentId(nameSpace + "_" + "profileMode");
		modeOptionRadioGroup.setComponentName("Mode");
		modeOptionRadioGroup.setAddToParent(Boolean.TRUE);
		modeOptionRadioGroup.setParentComponentId(componentId);

		GtnUIFrameworkOptionGroupConfig modeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		modeOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Add", "Search" }));
		modeOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		modeOptionGroupConfig.setEnable(Boolean.TRUE);
		modeOptionRadioGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		modeOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(modeOptionGroupConfig);

		componentList.add(modeOptionRadioGroup);
	}

	private void addPrivateViewLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig privateViewLookupLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "privateViewLookupLayout", true, nameSpace + "_" + "projectionSelectionLayout");
		componentList.add(privateViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig privateViewLookup = new GtnUIFrameworkComponentConfig();
		privateViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateViewLookup.setComponentId(nameSpace + "_" + "privateViewLookup");
		privateViewLookup.setComponentName("Private Views");
		privateViewLookup.setAddToParent(Boolean.TRUE);
		privateViewLookup.setParentComponentId(nameSpace + "_" + "privateViewLookupLayout");
		componentList.add(privateViewLookup);
	}

	private void addCompany(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig companyLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "companyLayout", true, nameSpace + "_" + "projectionSelectionLayout");
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		company.setComponentId(nameSpace + "_" + "company");
		company.setComponentName("Company");
		company.setAddToParent(Boolean.TRUE);
		company.setParentComponentId(nameSpace + "_" + "companyLayout");

		GtnUIFrameworkComboBoxConfig companyConfig = new GtnUIFrameworkComboBoxConfig();
		companyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyConfig.setComboBoxType("CompanyMasterGLcomp");
		company.setGtnComboboxConfig(companyConfig);
		componentList.add(company);
	}

	private void addProjectionName(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "projectionNameLayout", true, nameSpace + "_" + "projectionSelectionLayout");
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionName.setComponentId(nameSpace + "_" + "projectionName");
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(nameSpace + "_" + "projectionNameLayout");
		componentList.add(projectionName);
	}

	private void addPublicViewLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig publicViewLookupLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "publicViewLayout", true, nameSpace + "_" + "projectionSelectionLayout");
		componentList.add(publicViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig publicViewLookup = new GtnUIFrameworkComponentConfig();
		publicViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicViewLookup.setComponentId(nameSpace + "_" + "publicView");
		publicViewLookup.setComponentName("Public Views");
		publicViewLookup.setAddToParent(Boolean.TRUE);
		publicViewLookup.setParentComponentId(nameSpace + "_" + "publicViewLayout");
		componentList.add(publicViewLookup);

	}

	private void addBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "businessUnitLayout", true, nameSpace + "_" + "projectionSelectionLayout");
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		businessUnit.setComponentId(nameSpace + "_" + "businessUnit");
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(Boolean.TRUE);
		businessUnit.setParentComponentId(nameSpace + "_" + "businessUnitLayout");

		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType("BusinessUnitGLcomp");
		// businessUnitConfig.setHasDefaultValue(true);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);
		componentList.add(businessUnit);

	}

	private void addProjectionDescription(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionDescriptionLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "projectionDescriptionLayout", true, nameSpace + "_" + "projectionSelectionLayout");
		componentList.add(projectionDescriptionLayoutConfig);

		GtnUIFrameworkComponentConfig projectionDescription = new GtnUIFrameworkComponentConfig();
		projectionDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionDescription.setComponentId(nameSpace + "_" + "projectionDescription");
		projectionDescription.setComponentName("Projection Description");
		projectionDescription.setAddToParent(Boolean.TRUE);
		projectionDescription.setParentComponentId(nameSpace + "_" + "projectionDescriptionLayout");
		componentList.add(projectionDescription);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig timePeriodOuterLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodOuterLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig timePeriodOuterLayoutConfig = new GtnUIFrameworkComponentConfig();
		timePeriodOuterLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		timePeriodOuterLayoutConfig.setComponentId(nameSpace + "_" + "timePeriodOuterLayout");
		timePeriodOuterLayoutConfig.setAddToParent(Boolean.TRUE);
		timePeriodOuterLayoutConfig.setSpacing(Boolean.TRUE);
		timePeriodOuterLayoutConfig.setParentComponentId(nameSpace + "_" + "fromToLayout");
		timePeriodOuterLayoutConfig.setGtnLayoutConfig(timePeriodOuterLayout);
		componentList.add(timePeriodOuterLayoutConfig);

		GtnUIFrameworkComponentConfig timePeriodPanel = new GtnUIFrameworkComponentConfig();
		timePeriodPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		timePeriodPanel.setComponentName("Time Period");
		timePeriodPanel.setComponentId(nameSpace + "_" + "timePeriodPanel");
		timePeriodPanel.setAddToParent(Boolean.TRUE);
		timePeriodPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		timePeriodPanel.setSpacing(Boolean.TRUE);
		timePeriodPanel.setParentComponentId(nameSpace + "_" + "timePeriodOuterLayout");
		componentList.add(timePeriodPanel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig timePeriodInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		timePeriodInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		timePeriodInnerLayoutConfig.setComponentId(nameSpace + "_" + "timePeriodInnerLayout");
		timePeriodInnerLayoutConfig.setSpacing(Boolean.TRUE);
		timePeriodInnerLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		timePeriodInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		timePeriodInnerLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		timePeriodInnerLayoutConfig.setParentComponentId(nameSpace + "_" + "timePeriodPanel");
		timePeriodInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(timePeriodInnerLayoutConfig);
		addFromPeriod(componentList, nameSpace);
		addToPeriod(componentList, nameSpace);

	}

	private void addFromPeriod(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "fromPeriodLayout", true, nameSpace + "_" + "timePeriodInnerLayout");
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		fromPeriod.setComponentId(nameSpace + "_" + "from");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);
		fromPeriod.setParentComponentId(nameSpace + "_" + "fromPeriodLayout");

		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		// fromPeriodConfig.setItemValues(Arrays.asList("a","b"));
		fromPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		fromPeriodConfig.setComboBoxType("TimePeriodFromDate");
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);
		componentList.add(fromPeriod);

		// fromPeriodConfig.setHasDefaultValue(true);

	}

	private void addToPeriod(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig.setComponentId(nameSpace + "_" + "toPeriodLayout");
		toPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		toPeriodLayoutConfig.setSpacing(Boolean.TRUE);
		toPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		toPeriodLayoutConfig.setParentComponentId(nameSpace + "_" + "timePeriodInnerLayout");
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		toPeriod.setComponentId(nameSpace + "_" + "to");
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(Boolean.TRUE);
		toPeriod.setParentComponentId(nameSpace + "_" + "toPeriodLayout");

		GtnUIFrameworkComboBoxConfig toPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		toPeriodConfig.setComboBoxType("TimePeriodToDate");
		toPeriod.setGtnComboboxConfig(toPeriodConfig);
		// toPeriodConfig.setHasDefaultValue(true);
		componentList.add(toPeriod);
	}

	private void addCustomerSelectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionPanel.setComponentName("Customer Selection");
		customerSelectionPanel.setComponentId(nameSpace + "_" + "customerSelectionPanel");
		customerSelectionPanel.setAddToParent(Boolean.TRUE);
		customerSelectionPanel.setParentComponentId(nameSpace + "_" + "dataSelectionMainLayout");
		customerSelectionPanel.setSpacing(Boolean.TRUE);
		customerSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		customerSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(customerSelectionPanel);
	}

	private void addProductSelectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		productSelectionPanel.setComponentId(nameSpace + "_" + "productSelectionPanel");
		productSelectionPanel.setAddToParent(Boolean.TRUE);
		productSelectionPanel.setParentComponentId(nameSpace + "_" + "dataSelectionMainLayout");
		productSelectionPanel.setSpacing(Boolean.TRUE);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(productSelectionPanel);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig actionButtonLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "actionButtonLayout", true, nameSpace + "_" + "dataSelectionMainLayout");
		actionButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(actionButtonLayout);

		addGenerateBtn(componentList, nameSpace);
		addSearchBtn(componentList, nameSpace);
		addResetBtn(componentList, nameSpace);
		addSaveViewBtn(componentList, nameSpace);
		addDeleteViewBtn(componentList, nameSpace);

	}

	private void addGenerateBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig generateBtn = new GtnUIFrameworkComponentConfig();
		generateBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateBtn.setComponentId(nameSpace + "_" + "dsGenerate");
		generateBtn.setComponentName("GENERATE");
		generateBtn.setParentComponentId(nameSpace + "_" + "actionButtonLayout");
		generateBtn.setAddToParent(true);

		componentList.add(generateBtn);

		GtnUIFrameWorkActionConfig gtnUIFrameWorkGeneratePopupAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGeneratePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter("forecastGenerateLookupView");
		gtnUIFrameWorkGeneratePopupAction.addActionParameter("Forecast Generate Lookup View");
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		generateBtn.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkGeneratePopupAction);
	}

	private void addSearchBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig searchBtn = new GtnUIFrameworkComponentConfig();
		searchBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchBtn.setComponentId(nameSpace + "_" + "searchBtn");
		searchBtn.setComponentName("SEARCH");
		searchBtn.setParentComponentId(nameSpace + "_" + "actionButtonLayout");
		searchBtn.setAddToParent(true);

		componentList.add(searchBtn);

	}

	private void addResetBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resetBtn = new GtnUIFrameworkComponentConfig();
		resetBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetBtn.setComponentId(nameSpace + "_" + "resetBtn");
		resetBtn.setComponentName("RESET");
		resetBtn.setParentComponentId(nameSpace + "_" + "actionButtonLayout");
		resetBtn.setAddToParent(true);
		componentList.add(resetBtn);
	}

	private void addSaveViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(nameSpace + "_" + "saveViewBtn");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(nameSpace + "_" + "actionButtonLayout");
		saveViewBtn.setAddToParent(true);
		componentList.add(saveViewBtn);
	}

	private void addDeleteViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig deleteViewBtn = new GtnUIFrameworkComponentConfig();
		deleteViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewBtn.setComponentId(nameSpace + "_" + "deleteViewBtn");
		deleteViewBtn.setComponentName("DELETE VIEW");
		deleteViewBtn.setParentComponentId(nameSpace + "_" + "actionButtonLayout");
		deleteViewBtn.setAddToParent(true);
		componentList.add(deleteViewBtn);
	}

	private void addProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionResultsPanel = new GtnUIFrameworkComponentConfig();
		projectionResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionResultsPanel.setComponentId(nameSpace + "_" + "projectionResultsPanel");
		projectionResultsPanel.setComponentName("Projection Results");
		projectionResultsPanel.setParentComponentId(nameSpace + "_" + "dataSelectionMainLayout");
		projectionResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionResultsPanel.setAddToParent(true);
		projectionResultsPanel.setSpacing(true);
		componentList.add(projectionResultsPanel);
		addResultLayout(componentList, nameSpace);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig resultLayoutConfig = new GtnUIFrameworkLayoutConfig();
		resultLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig resultLayout = new GtnUIFrameworkComponentConfig();
		resultLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultLayout.setComponentId(nameSpace + "_" + "projectionResultsLayout");
		resultLayout.setParentComponentId(nameSpace + "_" + "projectionResultsPanel");
		resultLayout.setAddToParent(true);
		resultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultLayout.setSpacing(true);
		resultLayout.setGtnLayoutConfig(resultLayoutConfig);
		componentList.add(resultLayout);
		addPagedTableComponent(componentList, nameSpace);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionResultsTable = new GtnUIFrameworkComponentConfig();
		projectionResultsTable.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		projectionResultsTable.setComponentId(nameSpace + "_" + "projectionResultsTable");
		projectionResultsTable.setAddToParent(true);
		projectionResultsTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionResultsTable.setParentComponentId(nameSpace + "_" + "projectionResultsLayout");

		GtnUIFrameworkPagedTableConfig projectionResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		projectionResultsTableConfig.setEditable(false);
		projectionResultsTableConfig.setFilterBar(true);
		projectionResultsTableConfig.setSelectable(true);
		projectionResultsTableConfig.setItemPerPage(10);
		projectionResultsTableConfig.setSinkItemPerPageWithPageLength(false);
		projectionResultsTableConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
						GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
						GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
						GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
						GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
						GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		projectionResultsTableConfig.setTableVisibleHeader(new String[] { "Projection Name", "Description",
				"Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By",
				"Created Date", "Modified Date", "Company", "Business Unit" });
		projectionResultsTableConfig.setTableColumnMappingId(new String[] { "projactionName", "description",
				"customerHierarchy", "customerLevel", "productHierarchy", "productLevel", "createdBy", "createdDate",
				"modifiedDate", "company", "businessUnit" });
		projectionResultsTable.setGtnPagedTableConfig(projectionResultsTableConfig);
		componentList.add(projectionResultsTable);

	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig controlButtonLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "controlButtonLayout", true, nameSpace + "_" + "dataSelectionMainLayout");
		controlButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(controlButtonLayout);

		addResetCtrlBtn(componentList, nameSpace);
		addEditBtn(componentList, nameSpace);
		addViewBtn(componentList, nameSpace);
		addDeleteBtn(componentList, nameSpace);

	}
	
	private void addResetCtrlBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		GtnUIFrameworkComponentConfig resetCtrlBtn = new GtnUIFrameworkComponentConfig();
		resetCtrlBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetCtrlBtn.setComponentId(nameSpace + "_" + "resetCtrlBtn");
		resetCtrlBtn.setComponentName("RESET");
		resetCtrlBtn.setParentComponentId(nameSpace + "_" + "controlButtonLayout");
		resetCtrlBtn.setAddToParent(true);
		componentList.add(resetCtrlBtn);
	}
	
	private void addEditBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		GtnUIFrameworkComponentConfig editBtn = new GtnUIFrameworkComponentConfig();
		editBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		editBtn.setComponentId(nameSpace + "_" + "editBtn");
		editBtn.setComponentName("EDIT");
		editBtn.setParentComponentId(nameSpace + "_" + "controlButtonLayout");
		editBtn.setAddToParent(true);
		componentList.add(editBtn);
	}
	
	private void addViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		GtnUIFrameworkComponentConfig viewBtn = new GtnUIFrameworkComponentConfig();
		viewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		viewBtn.setComponentId(nameSpace + "_" + "viewBtn");
		viewBtn.setComponentName("VIEW");
		viewBtn.setParentComponentId(nameSpace + "_" + "controlButtonLayout");
		viewBtn.setAddToParent(true);
		componentList.add(viewBtn);
	}
	
	private void addDeleteBtn(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		GtnUIFrameworkComponentConfig deleteBtn = new GtnUIFrameworkComponentConfig();
		deleteBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteBtn.setComponentId(nameSpace + "_" + "deleteBtn");
		deleteBtn.setComponentName("DELETE");
		deleteBtn.setParentComponentId(nameSpace + "_" + "controlButtonLayout");
		deleteBtn.setAddToParent(true);
		componentList.add(deleteBtn);
	}

}
