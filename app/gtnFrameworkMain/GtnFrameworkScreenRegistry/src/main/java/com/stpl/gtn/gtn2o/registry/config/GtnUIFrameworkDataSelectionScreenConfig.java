package com.stpl.gtn.gtn2o.registry.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.hierarchy.GtnFrameworkForecastCustomerHierarchyConfig;
import com.stpl.gtn.gtn2o.registry.config.hierarchy.GtnFrameworkForecastProdHierarchyConfig;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkDataSelectionScreenConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	private String[] propertyIds = { "projactionName", "description", "customerHierarchy", "customerLevel",
			"productHierarchy", "productLevel", "createdBy", "createdDate", "modifiedDate",
			GtnFrameworkCommonConstants.SCREEN_REGISTRY_COMPANY,
			GtnFrameworkCommonConstants.SCREEN_REGISTRY_BUSINESSUNIT };
	private GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.CALENDAR_FIELD, GtnUIFrameworkComponentType.CALENDAR_FIELD,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getDataSelectionView(String nameSpace) {

		GtnUIFrameworkViewConfig dataSelectionView = configProvider.getViewConfig(nameSpace + "_" + "Data Selection",
				nameSpace + "_" + "V001", true);

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		dataSelectionView.setGtnComponentList(componentList);
		addRootLayout(componentList, nameSpace);

		dataSelectionView.addViewAction(loadForecastEligibleDate());

		return dataSelectionView;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig mainLayout = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "dataSelectionMainLayout", false, null);
		mainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		mainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(mainLayout);

		addProjectionOptionsPanel(componentList, mainLayout.getComponentId(), nameSpace);
		addCustomerSelectionPanel(componentList, mainLayout.getComponentId(), nameSpace);

		GtnFrameworkForecastCustomerHierarchyConfig customerSelectionConfig = new GtnFrameworkForecastCustomerHierarchyConfig();
		componentList.addAll(customerSelectionConfig.getCustomerSelectionLayoutCompoents(nameSpace));
		addProductSelectionPanel(componentList, nameSpace);

		GtnFrameworkForecastProdHierarchyConfig productSelectionConfig = new GtnFrameworkForecastProdHierarchyConfig();
		componentList.addAll(productSelectionConfig.getProductSelectionLayoutComponents(nameSpace));

		addActionButtonLayout(componentList, mainLayout.getComponentId(), nameSpace);
		addProjectionResultsPanel(componentList, mainLayout.getComponentId(), nameSpace);
		addControlButtonLayout(componentList, mainLayout.getComponentId(), nameSpace);
	}

	private void addProjectionOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig projectionOptionsPanel = new GtnUIFrameworkComponentConfig();
		projectionOptionsPanel.setComponentName("Projection Options");
		projectionOptionsPanel.setComponentId(nameSpace + "_" + "projectionOptions");
		projectionOptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionsPanel.setParentComponentId(parentComponentId);
		projectionOptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionOptionsPanel.setAddToParent(true);
		projectionOptionsPanel.setSpacing(true);
		componentList.add(projectionOptionsPanel);

		GtnUIFrameworkLayoutConfig projectionOptionsMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionsMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionOptionsMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionsMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionsMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionsMainLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsMainLayout");
		projectionOptionsMainLayoutConfig.setAddToParent(true);
		projectionOptionsMainLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptions");
		projectionOptionsMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionsMainLayoutConfig.setSpacing(true);
		projectionOptionsMainLayoutConfig.setGtnLayoutConfig(projectionOptionsMainLayout);
		componentList.add(projectionOptionsMainLayoutConfig);

		addProjectionOptionInnerLayout(componentList, projectionOptionsMainLayoutConfig.getComponentId(), nameSpace);
	}

	private void addProjectionOptionInnerLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsInnerLayout");
		projectionOptionInnerLayoutConfig.setAddToParent(true);
		projectionOptionInnerLayoutConfig.setSpacing(true);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(parentComponentId);
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		addModeSelectionLayout(componentList, projectionOptionInnerLayoutConfig.getComponentId(), nameSpace);
		addFromToPeriodLayout(componentList, parentComponentId, nameSpace);
		addProjectionSelectionLayout(componentList, projectionOptionInnerLayoutConfig.getComponentId(), nameSpace);
		addMode(componentList, nameSpace);
		addTimePeriod(componentList, nameSpace);
	}

	private void addModeSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkLayoutConfig modeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig modeSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeSelectionLayoutConfig.setComponentId(nameSpace + "_" + "modeSelectionLayout");
		modeSelectionLayoutConfig.setAddToParent(true);
		modeSelectionLayoutConfig.setParentComponentId(parentComponentId);
		modeSelectionLayoutConfig.setGtnLayoutConfig(modeSelectionLayout);
		componentList.add(modeSelectionLayoutConfig);
	}

	private void addFromToPeriodLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkLayoutConfig fromToLayout = new GtnUIFrameworkLayoutConfig();
		fromToLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setComponentId(nameSpace + "_" + "fromToLayout");
		fromToLayoutConfig.setAddToParent(true);
		fromToLayoutConfig.setSpacing(true);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(parentComponentId);
		fromToLayoutConfig.setGtnLayoutConfig(fromToLayout);
		componentList.add(fromToLayoutConfig);
	}

	private void addProjectionSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionSelectionLayout = new GtnUIFrameworkLayoutConfig();
		projectionSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig projectionSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionSelectionLayoutConfig.setComponentId(nameSpace + "_" + "projectionSelectionLayout");
		projectionSelectionLayoutConfig.setAddToParent(true);
		projectionSelectionLayoutConfig.setParentComponentId(parentComponentId);
		projectionSelectionLayoutConfig.setGtnLayoutConfig(projectionSelectionLayout);
		componentList.add(projectionSelectionLayoutConfig);

		addPrivateViewLookup(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addCompany(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addProjectionName(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addPublicViewLookup(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addBusinessUnit(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addProjectionDescription(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
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
		modeOptionRadioGroup.setAddToParent(true);
		modeOptionRadioGroup.setParentComponentId(componentId);

		GtnUIFrameworkOptionGroupConfig modeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		modeOptionGroupConfig.setItemValues(Arrays.asList("Add", "Search"));
		modeOptionGroupConfig.setValuesFromService(false);
		modeOptionGroupConfig.setEnable(true);
		modeOptionRadioGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		modeOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(modeOptionGroupConfig);

		componentList.add(modeOptionRadioGroup);
	}

	private void addPrivateViewLookup(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig privateViewLookupLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "privateViewLookupLayout", true, parentComponentId);
		componentList.add(privateViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig privateViewLookup = new GtnUIFrameworkComponentConfig();
		privateViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateViewLookup.setComponentId(nameSpace + "_" + "privateViewLookup");
		privateViewLookup.setComponentName("Private Views");
		privateViewLookup.setAddToParent(true);
		privateViewLookup.setParentComponentId(nameSpace + "_" + "privateViewLookupLayout");
		componentList.add(privateViewLookup);
	}

	private void addCompany(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig companyLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "companyLayout", true, parentComponentId);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		company.setComponentId(nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_COMPANY);
		company.setComponentName("Company");
		company.setAddToParent(true);
		company.setParentComponentId(nameSpace + "_" + "companyLayout");

		GtnUIFrameworkComboBoxConfig companyConfig = new GtnUIFrameworkComboBoxConfig();
		companyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyConfig.setComboBoxType("CompanyMasterGLcomp");
		company.setGtnComboboxConfig(companyConfig);
		componentList.add(company);
	}

	private void addProjectionName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "projectionNameLayout", true, parentComponentId);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionName.setComponentId(nameSpace + "_" + "projectionName");
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(true);
		projectionName.setParentComponentId(nameSpace + "_" + "projectionNameLayout");
		componentList.add(projectionName);
	}

	private void addPublicViewLookup(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig publicViewLookupLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "publicViewLayout", true, parentComponentId);
		componentList.add(publicViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig publicViewLookup = new GtnUIFrameworkComponentConfig();
		publicViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicViewLookup.setComponentId(nameSpace + "_" + "publicView");
		publicViewLookup.setComponentName("Public Views");
		publicViewLookup.setAddToParent(true);
		publicViewLookup.setParentComponentId(nameSpace + "_" + "publicViewLayout");
		componentList.add(publicViewLookup);

	}

	private void addBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "businessUnitLayout", true, parentComponentId);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		businessUnit.setComponentId(nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_BUSINESSUNIT);
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(true);
		businessUnit.setParentComponentId(nameSpace + "_" + "businessUnitLayout");

		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType("BusinessUnitGLcomp");
		businessUnit.setGtnComboboxConfig(businessUnitConfig);
		componentList.add(businessUnit);

	}

	private void addProjectionDescription(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig projectionDescriptionLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "projectionDescriptionLayout", true, parentComponentId);
		componentList.add(projectionDescriptionLayoutConfig);

		GtnUIFrameworkComponentConfig projectionDescription = new GtnUIFrameworkComponentConfig();
		projectionDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionDescription.setComponentId(nameSpace + "_" + "projectionDescription");
		projectionDescription.setComponentName("Projection Description");
		projectionDescription.setAddToParent(true);
		projectionDescription.setParentComponentId(nameSpace + "_" + "projectionDescriptionLayout");
		componentList.add(projectionDescription);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig timePeriodOuterLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodOuterLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig timePeriodOuterLayoutConfig = new GtnUIFrameworkComponentConfig();
		timePeriodOuterLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		timePeriodOuterLayoutConfig.setComponentId(nameSpace + "_" + "timePeriodOuterLayout");
		timePeriodOuterLayoutConfig.setAddToParent(true);
		timePeriodOuterLayoutConfig.setSpacing(true);
		timePeriodOuterLayoutConfig.setParentComponentId(nameSpace + "_" + "fromToLayout");
		timePeriodOuterLayoutConfig.setGtnLayoutConfig(timePeriodOuterLayout);
		componentList.add(timePeriodOuterLayoutConfig);

		GtnUIFrameworkComponentConfig timePeriodPanel = new GtnUIFrameworkComponentConfig();
		timePeriodPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		timePeriodPanel.setComponentName("Time Period");
		timePeriodPanel.setComponentId(nameSpace + "_" + "timePeriodPanel");
		timePeriodPanel.setAddToParent(true);
		timePeriodPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		timePeriodPanel.setSpacing(true);
		timePeriodPanel.setParentComponentId(nameSpace + "_" + "timePeriodOuterLayout");
		componentList.add(timePeriodPanel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig timePeriodInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		timePeriodInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		timePeriodInnerLayoutConfig.setComponentId(nameSpace + "_" + "timePeriodInnerLayout");
		timePeriodInnerLayoutConfig.setSpacing(true);
		timePeriodInnerLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		timePeriodInnerLayoutConfig.setAddToParent(true);
		timePeriodInnerLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		timePeriodInnerLayoutConfig.setParentComponentId(nameSpace + "_" + "timePeriodPanel");
		timePeriodInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(timePeriodInnerLayoutConfig);
		addFromPeriod(componentList, timePeriodInnerLayoutConfig.getComponentId(), nameSpace);
		addToPeriod(componentList, timePeriodInnerLayoutConfig.getComponentId(), nameSpace);

	}

	private void addFromPeriod(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "fromPeriodLayout", true, parentComponentId);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		fromPeriod.setComponentId(nameSpace + "_" + "from");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(true);
		fromPeriod.setParentComponentId(nameSpace + "_" + "fromPeriodLayout");

		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		fromPeriodConfig.setComboBoxType("TimePeriodFromDate");
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);
		componentList.add(fromPeriod);
	}

	private void addToPeriod(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig.setComponentId(nameSpace + "_" + "toPeriodLayout");
		toPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		toPeriodLayoutConfig.setSpacing(true);
		toPeriodLayoutConfig.setAddToParent(true);
		toPeriodLayoutConfig.setParentComponentId(parentComponentId);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		toPeriod.setComponentId(nameSpace + "_" + "to");
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(true);
		toPeriod.setParentComponentId(nameSpace + "_" + "toPeriodLayout");

		GtnUIFrameworkComboBoxConfig toPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		toPeriodConfig.setComboBoxType("TimePeriodToDate");
		toPeriod.setGtnComboboxConfig(toPeriodConfig);
		componentList.add(toPeriod);
	}

	private void addCustomerSelectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionPanel.setComponentName("Customer Selection");
		customerSelectionPanel.setComponentId(nameSpace + "_" + "customerSelectionPanel");
		customerSelectionPanel.setAddToParent(true);
		customerSelectionPanel.setParentComponentId(parentComponentId);
		customerSelectionPanel.setSpacing(true);
		customerSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		customerSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(customerSelectionPanel);
	}

	private void addProductSelectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		productSelectionPanel.setComponentId(nameSpace + "_" + "productSelectionPanel");
		productSelectionPanel.setAddToParent(true);
		productSelectionPanel.setParentComponentId(nameSpace + "_" + "dataSelectionMainLayout");
		productSelectionPanel.setSpacing(true);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(productSelectionPanel);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig actionButtonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "actionButtonLayout", true, parentComponentId);
		actionButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(actionButtonLayout);

		addGenerateBtn(componentList, actionButtonLayout.getComponentId(), nameSpace);
		addSearchBtn(componentList, actionButtonLayout.getComponentId(), nameSpace);
		addResetBtn(componentList, actionButtonLayout.getComponentId(), nameSpace);
		addSaveViewBtn(componentList, actionButtonLayout.getComponentId(), nameSpace);
		addDeleteViewBtn(componentList, actionButtonLayout.getComponentId(), nameSpace);

	}

	private void addGenerateBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig generateBtn = new GtnUIFrameworkComponentConfig();
		generateBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateBtn.setComponentId(nameSpace + "_" + "dsGenerate");
		generateBtn.setComponentName("GENERATE");
		generateBtn.setParentComponentId(parentComponentId);
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

	private void addSearchBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig searchBtn = new GtnUIFrameworkComponentConfig();
		searchBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchBtn.setComponentId(nameSpace + "_" + "searchBtn");
		searchBtn.setComponentName("SEARCH");
		searchBtn.setParentComponentId(parentComponentId);
		searchBtn.setAddToParent(true);

		componentList.add(searchBtn);

	}

	private void addResetBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig resetBtn = new GtnUIFrameworkComponentConfig();
		resetBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetBtn.setComponentId(nameSpace + "_" + "resetBtn");
		resetBtn.setComponentName("RESET");
		resetBtn.setParentComponentId(parentComponentId);
		resetBtn.setAddToParent(true);
		componentList.add(resetBtn);
	}

	private void addSaveViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(nameSpace + "_" + "saveViewBtn");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(parentComponentId);
		saveViewBtn.setAddToParent(true);
		componentList.add(saveViewBtn);
	}

	private void addDeleteViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deleteViewBtn = new GtnUIFrameworkComponentConfig();
		deleteViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewBtn.setComponentId(nameSpace + "_" + "deleteViewBtn");
		deleteViewBtn.setComponentName("DELETE VIEW");
		deleteViewBtn.setParentComponentId(parentComponentId);
		deleteViewBtn.setAddToParent(true);
		componentList.add(deleteViewBtn);
	}

	private void addProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig projectionResultsPanel = new GtnUIFrameworkComponentConfig();
		projectionResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionResultsPanel.setComponentId(nameSpace + "_" + "projectionResultsPanel");
		projectionResultsPanel.setComponentName("Projection Results");
		projectionResultsPanel.setParentComponentId(parentComponentId);
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
		projectionResultsTable.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
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
		projectionResultsTableConfig.setColumnHeaders(Arrays.asList("Projection Name", "Description",
				"Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By",
				"Created Date", "Modified Date", "Company", "Business Unit"));
		projectionResultsTableConfig.setTableColumnMappingId(new String[] { "projactionName", "description",
				"customerHierarchy", "customerLevel", "productHierarchy", "productLevel", "createdBy", "createdDate",
				"modifiedDate", GtnFrameworkCommonConstants.SCREEN_REGISTRY_COMPANY,
				GtnFrameworkCommonConstants.SCREEN_REGISTRY_BUSINESSUNIT });

		projectionResultsTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		projectionResultsTable.setGtnPagedTableConfig(projectionResultsTableConfig);
		componentList.add(projectionResultsTable);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>(
				propertyIds.length);
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfig.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfig.setGtnComponentType(componentType[i]);
			customFilterConfigMap.put(pagedTableCustomFilterConfig.getPropertId(), pagedTableCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig controlButtonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "controlButtonLayout", true, parentComponentId);
		controlButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(controlButtonLayout);

		addResetCtrlBtn(componentList, controlButtonLayout.getComponentId(), nameSpace);
		addEditBtn(componentList, controlButtonLayout.getComponentId(), nameSpace);
		addViewBtn(componentList, controlButtonLayout.getComponentId(), nameSpace);
		addDeleteBtn(componentList, controlButtonLayout.getComponentId(), nameSpace);

	}

	private void addResetCtrlBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig resetCtrlBtn = new GtnUIFrameworkComponentConfig();
		resetCtrlBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetCtrlBtn.setComponentId(nameSpace + "_" + "resetCtrlBtn");
		resetCtrlBtn.setComponentName("RESET");
		resetCtrlBtn.setParentComponentId(parentComponentId);
		resetCtrlBtn.setAddToParent(true);
		componentList.add(resetCtrlBtn);
	}

	private void addEditBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig editBtn = new GtnUIFrameworkComponentConfig();
		editBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		editBtn.setComponentId(nameSpace + "_" + "editBtn");
		editBtn.setComponentName("EDIT");
		editBtn.setParentComponentId(parentComponentId);
		editBtn.setAddToParent(true);
		componentList.add(editBtn);
	}

	private void addViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig viewBtn = new GtnUIFrameworkComponentConfig();
		viewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		viewBtn.setComponentId(nameSpace + "_" + "viewBtn");
		viewBtn.setComponentName("VIEW");
		viewBtn.setParentComponentId(parentComponentId);
		viewBtn.setAddToParent(true);
		componentList.add(viewBtn);
	}

	private void addDeleteBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deleteBtn = new GtnUIFrameworkComponentConfig();
		deleteBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteBtn.setComponentId(nameSpace + "_" + "deleteBtn");
		deleteBtn.setComponentName("DELETE");
		deleteBtn.setParentComponentId(parentComponentId);
		deleteBtn.setAddToParent(true);
		componentList.add(deleteBtn);
	}

	// Load Forecast eligible date
	private GtnUIFrameWorkActionConfig loadForecastEligibleDate() {
		GtnUIFrameWorkActionConfig loadDateAction = new GtnUIFrameWorkActionConfig();
		loadDateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadDateAction.addActionParameter(GtnForecastEligibleDateLoadAction.class.getName());
		loadDateAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastEligibilityDate");
		return loadDateAction;
	}

}
