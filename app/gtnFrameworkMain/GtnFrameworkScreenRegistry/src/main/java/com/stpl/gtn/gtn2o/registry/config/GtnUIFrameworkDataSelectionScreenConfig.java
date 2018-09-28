package com.stpl.gtn.gtn2o.registry.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkDeleteViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastingCCPTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkSaveViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkScreenRegistryResetAction;
import com.stpl.gtn.gtn2o.registry.action.GtnLandingScreenFromAndToPeriodLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnModeOptionValueChangeAction;
import com.stpl.gtn.gtn2o.registry.config.hierarchy.GtnFrameworkForecastCustomerHierarchyConfig;
import com.stpl.gtn.gtn2o.registry.config.hierarchy.GtnFrameworkForecastProdHierarchyConfig;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkDataSelectionScreenConfig {

	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkComponentConfig getHorizontalLayoutConfig(String compId, String parentLayout) {
		GtnUIFrameworkComponentConfig profOptionLayoutConf = new GtnUIFrameworkComponentConfig();
		profOptionLayoutConf.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		profOptionLayoutConf.setComponentId(compId + GtnFrameworkCssConstants.HORIZONTAL);
		profOptionLayoutConf.setComponentName(compId + GtnFrameworkCssConstants.HORIZONTAL);
		profOptionLayoutConf.setParentComponentId(parentLayout);
		profOptionLayoutConf.setAddToParent(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		profOptionLayoutConf.setGtnLayoutConfig(conf);
		return profOptionLayoutConf;
	}

	public GtnUIFrameworkViewConfig getDataSelectionView(String nameSpace) {

		GtnUIFrameworkViewConfig dataSelectionView = configProvider.getViewConfig(nameSpace + "_" + "Data Selection",
				nameSpace + "_" + "V001", true);

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		dataSelectionView.setGtnComponentList(componentList);
		addRootLayout(componentList, nameSpace);

		dataSelectionView.addViewAction(loadForecastEligibleDate(nameSpace));

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

		if (nameSpace.contains(GtnFrameworkScreenRegisteryConstants.LANDING_SCREN_NAMESPACE)) {
			addActionButtonLayout(componentList, mainLayout.getComponentId(), nameSpace);
			addProjectionResultsPanel(componentList, mainLayout.getComponentId(), nameSpace);
			addControlButtonLayout(componentList, mainLayout.getComponentId(), nameSpace);
		}
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
		projectionOptionsMainLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionsMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionsMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionsMainLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsMainLayout");
		projectionOptionsMainLayoutConfig.setAddToParent(true);
		projectionOptionsMainLayoutConfig.addComponentStyle("v-report-overflow-auto");
		projectionOptionsMainLayoutConfig.setParentComponentId(nameSpace + "_" + "projectionOptions");
		projectionOptionsMainLayoutConfig.setSpacing(true);
		projectionOptionsMainLayoutConfig.setGtnLayoutConfig(projectionOptionsMainLayout);
		componentList.add(projectionOptionsMainLayoutConfig);

		addProjectionOptionInnerLayout(componentList, projectionOptionsMainLayoutConfig.getComponentId(), nameSpace);
	}

	private void addProjectionOptionInnerLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsInnerLayout");
		projectionOptionInnerLayoutConfig.setAddToParent(true);
		projectionOptionInnerLayoutConfig.setSpacing(true);
		projectionOptionInnerLayoutConfig.setParentComponentId(parentComponentId);
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		addModeSelectionLayout(componentList, projectionOptionInnerLayoutConfig.getComponentId(), nameSpace);
		addFromToPeriodLayout(componentList, parentComponentId, nameSpace);
		addProjectionSelectionLayout(componentList, projectionOptionInnerLayoutConfig.getComponentId(), nameSpace);
		addMode(componentList, nameSpace);
	}

	private void addModeSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkLayoutConfig modeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig modeSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeSelectionLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		modeSelectionLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		modeSelectionLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_40);
		modeSelectionLayoutConfig.setComponentId(nameSpace + "_" + "modeSelectionLayout");
		modeSelectionLayoutConfig.setAddToParent(true);
		modeSelectionLayoutConfig.setParentComponentId(parentComponentId);
		modeSelectionLayoutConfig.setGtnLayoutConfig(modeSelectionLayout);
		componentList.add(modeSelectionLayoutConfig);
	}

	private void addFromToPeriodLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + "timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setAddToParent(true);
		panel.setComponentHight("100px");
		panel.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		panel.setParentComponentId(parentComponentId);
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig fromToLayout = new GtnUIFrameworkLayoutConfig();
		fromToLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setComponentId(nameSpace + "_" + "fromToLayout");

		fromToLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromToLayoutConfig.setAddToParent(true);
		fromToLayoutConfig.setSpacing(true);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		fromToLayoutConfig
				.setParentComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + "timePeriod");
		fromToLayoutConfig.setGtnLayoutConfig(fromToLayout);
		componentList.add(fromToLayoutConfig);

		addFromPeriod(componentList, fromToLayoutConfig.getComponentId(), nameSpace);
		addToPeriod(componentList, fromToLayoutConfig.getComponentId(), nameSpace);
	}

	private void addProjectionSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionSelectionLayout = new GtnUIFrameworkLayoutConfig();
		projectionSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig projectionSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionSelectionLayoutConfig.setComponentId(nameSpace + "_" + "projectionSelectionLayout");
		projectionSelectionLayoutConfig.setAddToParent(true);
		projectionSelectionLayoutConfig.setParentComponentId(parentComponentId);
		projectionSelectionLayoutConfig.setGtnLayoutConfig(projectionSelectionLayout);
		componentList.add(projectionSelectionLayoutConfig);

		addPrivateCompanyLayout(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addPublicBusinessUnitLayout(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addFrequencyDeductionLayout(componentList, projectionSelectionLayoutConfig.getComponentId(), nameSpace);

	}

	private void addPrivateCompanyLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig privateViewLayoutConfig = configProvider.getGtnCssLayoutConfig(
				nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + "privateCompanyLayout", true,
				parentComponentId, GtnUIFrameworkLayoutType.CSS_LAYOUT);
		privateViewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(privateViewLayoutConfig);

		addPrivateViewLookup(componentList, privateViewLayoutConfig.getComponentId(), nameSpace);
		addCompany(componentList, privateViewLayoutConfig.getComponentId(), nameSpace);
		addProjectionName(componentList, privateViewLayoutConfig.getComponentId(), nameSpace);
	}

	private void addPublicBusinessUnitLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig publicViewLayoutConfig = configProvider.getGtnCssLayoutConfig(
				nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + "publicBusinessUnitLayout", true,
				parentComponentId, GtnUIFrameworkLayoutType.CSS_LAYOUT);
		publicViewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(publicViewLayoutConfig);

		addPublicViewLookup(componentList, publicViewLayoutConfig.getComponentId(), nameSpace);
		addBusinessUnit(componentList, publicViewLayoutConfig.getComponentId(), nameSpace);
		addProjectionDescription(componentList, publicViewLayoutConfig.getComponentId(), nameSpace);
	}

	private void addFrequencyDeductionLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig frequencyLayoutConfig = configProvider.getGtnCssLayoutConfig(
				nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + "frequencyDeductionLayout", true,
				parentComponentId, GtnUIFrameworkLayoutType.CSS_LAYOUT);
		frequencyLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(frequencyLayoutConfig);

		addFrequency(componentList, frequencyLayoutConfig.getComponentId(), nameSpace);
		addDeductionLevel(componentList, frequencyLayoutConfig.getComponentId(), nameSpace);
	}

	private void addDeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionLevelConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionLayout", true, parentComponentId);
		componentList.add(deductionLevelConfig);

		GtnUIFrameworkComponentConfig deductionComponent = new GtnUIFrameworkComponentConfig();
		deductionComponent.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionComponent.setComponentId(nameSpace + "_" + "deductionLevel");
		deductionComponent.setComponentName("Deduction Level");
		deductionComponent.setAddToParent(true);
		deductionComponent.setParentComponentId(nameSpace + "_" + "deductionLayout");
		deductionComponent.setCustomReference(GtnFrameworkScreenRegisteryConstants.INTEGER_ID);

		GtnUIFrameworkComboBoxConfig deductionConfig = new GtnUIFrameworkComboBoxConfig();
		deductionConfig.setHasDefaultValue(true);
		deductionConfig.setDefaultDesc("next");
		deductionConfig.setActualWsContext(GtnWebServiceUrlConstants.GTN_DEDUCTION_LEVEL_COMBOBOX_SERVICE);
		deductionConfig.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);
		deductionConfig.setActualWsUrl(GtnFrameworkScreenRegisteryConstants.SEARCH_RESULTS_URL);
		deductionConfig.setLoadingUrl(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL);
		deductionConfig.setComboBoxType("dataSelectionDeduction");
		deductionConfig.setActualWsModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
		deductionComponent.setGtnComboboxConfig(deductionConfig);
		componentList.add(deductionComponent);
	}

	private void addMode(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		String componentId = nameSpace + "_" + "modeGroup";
		GtnUIFrameworkComponentConfig modeLayoutConfig = configProvider.getHorizontalLayoutConfig(componentId, true,
				nameSpace + "_" + "modeSelectionLayout");
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkComponentConfig modeOptionRadioGroup = new GtnUIFrameworkComponentConfig();
		modeOptionRadioGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		modeOptionRadioGroup.setComponentId(nameSpace + "_" + GtnFrameworkForecastingStringConstants.PROFILE_MODE);
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

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig optionChangeActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(optionChangeActionConfig);
		optionChangeActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add(GtnModeOptionValueChangeAction.class.getName());
		popupActionParam.add(nameSpace + "_" + GtnFrameworkForecastingStringConstants.PROFILE_MODE);
		popupActionParam.add(nameSpace + "_" + "dsGenerate");
		popupActionParam.add(nameSpace + "_" + "searchBtn");
		popupActionParam.add(nameSpace + "_" + "resetBtn");
		popupActionParam.add(nameSpace + "_" + "saveViewBtn");
		popupActionParam.add(nameSpace + "_" + "deleteViewBtn");
		popupActionParam.add(nameSpace + "_" + "resetCtrlBtn");
		popupActionParam.add(nameSpace + "_" + "editBtn");
		popupActionParam.add(nameSpace + "_" + "viewBtn");
		popupActionParam.add(nameSpace + "_" + "deleteBtn");
		popupActionParam.add(nameSpace + "_" + GtnFrameworkForecastingStringConstants.TO);
		popupActionParam.add(nameSpace + "_" + GtnFrameworkForecastingStringConstants.FROM);

		optionChangeActionConfig.setActionParameterList(popupActionParam);

		modeOptionRadioGroup.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addPrivateViewLookup(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig privateViewLookupLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "privateViewLookupLayout", true, parentComponentId);
		componentList.add(privateViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig privateViewLookup = new GtnUIFrameworkComponentConfig();
		privateViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		privateViewLookup.setComponentId(nameSpace + "_" + "privateViewLookup");
		privateViewLookup.setComponentName("Private Views");
		privateViewLookup.setAddToParent(true);
		privateViewLookup.setParentComponentId(nameSpace + "_" + "privateViewLookupLayout");
		componentList.add(privateViewLookup);

		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("Private_lookup");
		popupActionParam.add("Private View");
		popupActionParam.add("90%");
		popupActionParam.add("100%");

		popupActionConfig.setActionParameterList(popupActionParam);
		privateViewLookup.addGtnUIFrameWorkActionConfig(popupActionConfig);

	}

	private void addCompany(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig companyLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "companyLayout", true, parentComponentId);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		company.setComponentId(nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID);
		company.setComponentName(GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID);
		company.setAddToParent(true);
		company.setParentComponentId(nameSpace + "_" + "companyLayout");
		company.setCustomReference(GtnFrameworkScreenRegisteryConstants.INTEGER_ID);

		GtnUIFrameworkComboBoxConfig companyConfig = new GtnUIFrameworkComboBoxConfig();
		companyConfig.setLoadingUrl(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL);
		companyConfig.setComboBoxType("CompanyMasterGLcomp");
		companyConfig.setActualWsUrl(GtnFrameworkScreenRegisteryConstants.SEARCH_RESULTS_URL);
		companyConfig.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);
		companyConfig.setActualWsContext(GtnWebServiceUrlConstants.GTN_DEDUCTION_LEVEL_COMBOBOX_SERVICE);
		companyConfig.setActualWsModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
		company.setGtnComboboxConfig(companyConfig);
		componentList.add(company);

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig frequencyLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "frequencyLayout", true, parentComponentId);
		componentList.add(frequencyLayoutConfig);

		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		frequency.setComponentId(nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_FREQUENCY_COMBOX_ID);
		frequency.setComponentName(GtnFrameworkScreenRegisteryConstants.ADD_FREQUENCY_COMBOX_NAME);
		frequency.setAddToParent(true);
		frequency.setParentComponentId(nameSpace + "_" + "frequencyLayout");
		frequency.setCustomReference(GtnFrameworkScreenRegisteryConstants.INTEGER_ID);
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setLoadingUrl(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL);
		frequencyConfig.setHasDefaultValue(true);
		frequencyConfig.setDefaultDesc("Quarterly");
		frequencyConfig.setComboBoxType("frequency");
		frequencyConfig.setActualWsUrl(GtnFrameworkScreenRegisteryConstants.SEARCH_RESULTS_URL);
		frequencyConfig.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);
		frequencyConfig.setActualWsContext(GtnWebServiceUrlConstants.GTN_DEDUCTION_LEVEL_COMBOBOX_SERVICE);
		frequencyConfig.setActualWsModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
		frequency.setGtnComboboxConfig(frequencyConfig);
		componentList.add(frequency);
	}

	private void addProjectionName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "projectionNameLayout", true, parentComponentId);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionName
				.setComponentId(nameSpace + "_" + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME);
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
		publicViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		publicViewLookup.setComponentId(nameSpace + "_" + GtnFrameworkForecastingStringConstants.PUBLIC_VIEW);
		publicViewLookup.setComponentName("Public Views");
		publicViewLookup.setAddToParent(true);
		publicViewLookup.setParentComponentId(nameSpace + "_" + "publicViewLayout");
		componentList.add(publicViewLookup);

		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("Public_lookup");
		popupActionParam.add("Public View");
		popupActionParam.add("90%");
		popupActionParam.add("100%");

		popupActionConfig.setActionParameterList(popupActionParam);
		publicViewLookup.addGtnUIFrameWorkActionConfig(popupActionConfig);

	}

	private void addBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "businessUnitLayout", true, parentComponentId);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		businessUnit
				.setComponentId(nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID);
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(true);
		businessUnit.setParentComponentId(nameSpace + "_" + "businessUnitLayout");
		businessUnit.setCustomReference(GtnFrameworkScreenRegisteryConstants.INTEGER_ID);

		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL);
		businessUnitConfig.setComboBoxType("BusinessUnitGLcomp");
		businessUnitConfig.setActualWsUrl(GtnFrameworkScreenRegisteryConstants.SEARCH_RESULTS_URL);
		businessUnitConfig.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);
		businessUnitConfig.setActualWsContext(GtnWebServiceUrlConstants.GTN_DEDUCTION_LEVEL_COMBOBOX_SERVICE);
		businessUnitConfig.setActualWsModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
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
		projectionDescription.setComponentId(
				nameSpace + "_" + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_DESCRIPTION);
		projectionDescription.setComponentName("Description");
		projectionDescription.setAddToParent(true);
		projectionDescription.setParentComponentId(nameSpace + "_" + "projectionDescriptionLayout");
		componentList.add(projectionDescription);
	}

	private void addFromPeriod(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "fromPeriodLayout", true, parentComponentId);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		fromPeriod.setComponentId(nameSpace + "_" + GtnFrameworkForecastingStringConstants.FROM);
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(true);
		fromPeriod.setParentComponentId(nameSpace + "_" + "fromPeriodLayout");
		fromPeriod.setCustomReference(GtnFrameworkScreenRegisteryConstants.INTEGER_ID);

		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setItemCaptionValues(new ArrayList<>());
		fromPeriodConfig.setItemValues(new ArrayList<>());
		fromPeriodConfig.setHasDefaultValue(true);
		fromPeriodConfig.setDefaultDesc("next");
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
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		toPeriod.setComponentId(nameSpace + "_" + GtnFrameworkForecastingStringConstants.TO);
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(true);
		toPeriod.setParentComponentId(nameSpace + "_" + "toPeriodLayout");
		toPeriod.setEnable(false);

		GtnUIFrameworkComboBoxConfig toPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodConfig.setHasDefaultValue(true);
		toPeriodConfig.setDefaultDesc("next");
		toPeriod.setGtnComboboxConfig(toPeriodConfig);

		GtnUIFrameWorkActionConfig fromAndToPeriodAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		fromAndToPeriodAction.addActionParameter(GtnLandingScreenFromAndToPeriodLoadAction.class.getName());
		fromAndToPeriodAction.addActionParameter("/GtnWsPeriodConfigurationWebService");
		fromAndToPeriodAction.addActionParameter("/gtnPeriodConfigurationController/loadDate");
		fromAndToPeriodAction.addActionParameter("periodConfiguration");
		fromAndToPeriodAction.addActionParameter(nameSpace + "_" + GtnFrameworkForecastingStringConstants.FROM);
		fromAndToPeriodAction.addActionParameter(nameSpace + "_" + GtnFrameworkForecastingStringConstants.TO);

		toPeriod.setReloadActionConfig(fromAndToPeriodAction);
		toPeriod.setReloadLogicActionClassName(GtnLandingScreenFromAndToPeriodLoadAction.class.getName());

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

		List<GtnUIFrameWorkActionConfig> actionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig newArchGenerateAction = new GtnUIFrameWorkActionConfig();
		newArchGenerateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		newArchGenerateAction.addActionParameter(GtnFrameworkForecastingCCPTableLoadAction.class.getName());
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMERDUALLISTBOX);
		newArchGenerateAction.addActionParameter(nameSpace + "_productDualListBox");
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_RELATIONSHIP);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_RELATIONSHIP);//
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_FORECAST_LEVEL);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_SELECTION_FORECASTELIGIBILITY_DATE);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.PROD_HIER_NAME);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.PROD_RELATIONSHIP);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.PROD_FORECAST_LEVEL);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_RELATIONSHIP);//
		newArchGenerateAction.addActionParameter("Commercial Forecasting_company");
		newArchGenerateAction.addActionParameter(nameSpace + "_businessUnit");
		newArchGenerateAction.addActionParameter(nameSpace + "_from");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_to");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_projectionName");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_projectionDescription");
		newArchGenerateAction.addActionParameter(nameSpace + "_frequency");
		newArchGenerateAction.addActionParameter(nameSpace + "_deductionLevel");
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_LEVEL);
		newArchGenerateAction.addActionParameter(nameSpace + "_productLevel");
		newArchGenerateAction.addActionParameter(nameSpace + "_customerGroup");
		newArchGenerateAction.addActionParameter(nameSpace + "_productGroup");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_salesCustomView");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_deductionCustomView");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_privateViewLookup");
		newArchGenerateAction.addActionParameter("Commercial Forecasting_publicView");
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_RELATION_VERSION);
		newArchGenerateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.PROD_RELATION_VERSION);
		actionList.add(newArchGenerateAction);
	}

	private void addSearchBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig searchBtn = new GtnUIFrameworkComponentConfig();
		searchBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchBtn.setComponentId(nameSpace + "_" + "searchBtn");
		searchBtn.setComponentName("SEARCH");
		searchBtn.setParentComponentId(parentComponentId);
		searchBtn.setAuthorizationIncluded(true);
		searchBtn.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> actionConfigListSearch = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataSearchTableActionConfig.setActionParameterList(Arrays.asList(
				new Object[] { nameSpace + "_" + GtnFrameworkForecastingStringConstants.PROJECTION_RESULTS_TABLE }));
		loadDataSearchTableActionConfig.setFieldValues(Arrays.asList(
				new String[] { nameSpace + "_" + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME,
						nameSpace + "_" + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_DESCRIPTION,
						nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
						nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
						nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY, nameSpace + "_" + "prodhierarchyName",
						nameSpace + "_" + "customerGroup", nameSpace + "_" + GtnFrameworkForecastingStringConstants.FROM, nameSpace + "_" + GtnFrameworkForecastingStringConstants.TO }));
		actionConfigListSearch.add(loadDataSearchTableActionConfig);
		searchBtn.setGtnUIFrameWorkActionConfigList(actionConfigListSearch);
		searchBtn.setEnable(false);
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

		GtnUIFrameWorkActionConfig confirmResetAction = new GtnUIFrameWorkActionConfig();
		confirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmResetAction.addActionParameter("Confirm Reset");
		confirmResetAction.addActionParameter("Are you sure you want to reset the page to default values?");
		List<GtnUIFrameWorkActionConfig> onSuccessActionConfigList = new ArrayList<>();
		confirmResetAction.addActionParameter(onSuccessActionConfigList);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionConfig.setActionParameterList(getParameters(nameSpace));

		onSuccessActionConfigList.add(resetActionConfig);

		resetActionConfigList.add(confirmResetAction);
		resetBtn.setGtnUIFrameWorkActionConfigList(resetActionConfigList);
	}

	private List<Object> getParameters(String nameSpace) {

		return Arrays.asList(GtnFrameworkScreenRegistryResetAction.class.getName(),
				"Commercial Forecasting_privateViewLookup",
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				nameSpace + "_" + "projectionName",
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
				nameSpace + "_" + "projectionDescription", nameSpace + "_" + "publicView",
				nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY, nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_RELATIONSHIP,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_FORECAST_LEVEL, nameSpace + "_" + "customerGroup",
				nameSpace + "_" + "customerDualListBox", nameSpace + "_" + "prodhierarchyName",
				nameSpace + "_" + "prodrelationship", nameSpace + "_" + "prodforecastLevel",
				nameSpace + "_" + "productDualListBox", nameSpace + "_" + "productGroup",
				nameSpace + "_" + "profileMode"

		);

	}

	private void addSaveViewBtn(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(nameSpace + "_" + "saveViewBtn");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(parentComponentId);
		saveViewBtn.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> saveViewActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig saveViewDataSelectionValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		saveViewDataSelectionValidationActionConfig.setFieldValues(Arrays.asList(
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_DESCRIPTION,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.FROM,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.TO, nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_RELATIONSHIP, nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_FORECAST_LEVEL,

				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_LEVEL, nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_RELATION_VERSION,
				nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_SELECTION_FORECASTELIGIBILITY_DATE, nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMERDUALLISTBOX,
				nameSpace + GtnFrameworkForecastingStringConstants.PROD_HIER_NAME, nameSpace + GtnFrameworkForecastingStringConstants.PROD_RELATIONSHIP,

				nameSpace + GtnFrameworkForecastingStringConstants.PROD_RELATION_VERSION, nameSpace + GtnFrameworkForecastingStringConstants.PROD_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.SCREEN_REGISTRY_CF_CUST_SEL_REL,

				GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTLEVEL,
				GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTDUALLISTBOX,
				GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_FREQUENCY,
				GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTGROUP,
				GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTGROUP));

		GtnUIFrameWorkActionConfig saveViewDataSelectionAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("No search criteria were found. Please enter search criteria and try again.");
		saveViewDataSelectionAlertActionConfig.setActionParameterList(alertParams);

		saveViewDataSelectionValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(saveViewDataSelectionAlertActionConfig)));
		saveViewActionConfigList.add(saveViewDataSelectionValidationActionConfig);

		GtnUIFrameWorkActionConfig saveViewAction = new GtnUIFrameWorkActionConfig();
		saveViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveViewAction.setActionParameterList(Arrays.asList(GtnFrameworkSaveViewAction.class.getName(),
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_DESCRIPTION,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.FROM,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.TO, nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_RELATIONSHIP, nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_FORECAST_LEVEL,

				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE_CUSTOMER_SELECTION_LEVEL, nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_RELATION_VERSION,
				nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_SELECTION_FORECASTELIGIBILITY_DATE, nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMERDUALLISTBOX,
				nameSpace + GtnFrameworkForecastingStringConstants.PROD_HIER_NAME, nameSpace + GtnFrameworkForecastingStringConstants.PROD_RELATIONSHIP,

				nameSpace + GtnFrameworkForecastingStringConstants.PROD_RELATION_VERSION, nameSpace + GtnFrameworkForecastingStringConstants.PROD_FORECAST_LEVEL,

				nameSpace + "_productLevel", nameSpace + "_productDualListBox", nameSpace + "_frequency",

				nameSpace + "_productGroup", nameSpace + "_customerGroup"

		));
		saveViewActionConfigList.add(saveViewAction);
		saveViewBtn.setGtnUIFrameWorkActionConfigList(saveViewActionConfigList);
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
		deleteViewBtn.setEnable(false);

		GtnUIFrameWorkActionConfig confirmDeleteAction = new GtnUIFrameWorkActionConfig();
		confirmDeleteAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmDeleteAction.addActionParameter("Confirmation");
		confirmDeleteAction.addActionParameter("Are you sure you want to delete the view?");
		List<GtnUIFrameWorkActionConfig> onSuccessDeleteActionConfigList = new ArrayList<>();
		confirmDeleteAction.addActionParameter(onSuccessDeleteActionConfigList);

		GtnUIFrameWorkActionConfig deleteViewAction = new GtnUIFrameWorkActionConfig();
		deleteViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteViewAction.addActionParameter(GtnFrameworkDeleteViewAction.class.getName());
		deleteViewAction.addActionParameter(nameSpace + "_" + "privateViewLookup");
		deleteViewAction.addActionParameter(nameSpace + "_" + GtnFrameworkForecastingStringConstants.PUBLIC_VIEW);
		deleteViewAction.addActionParameter(nameSpace);
		onSuccessDeleteActionConfigList.add(deleteViewAction);

		deleteViewBtn.addGtnUIFrameWorkActionConfig(confirmDeleteAction);

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
		projectionResultsTable
				.setComponentId(nameSpace + "_" + GtnFrameworkForecastingStringConstants.PROJECTION_RESULTS_TABLE);
		projectionResultsTable.setAddToParent(true);
		projectionResultsTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionResultsTable.setParentComponentId(nameSpace + "_" + "projectionResultsLayout");
		projectionResultsTable.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);

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
				"modifiedDate", GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID });
		projectionResultsTableConfig.setCountUrl(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL);
		projectionResultsTableConfig.setResultSetUrl(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL);
		projectionResultsTableConfig.setPagedTableWsUrl("/forecastingPagedTableSearch");
		projectionResultsTableConfig
				.setRegisteredWebContext(GtnWebServiceUrlConstants.GTN_DEDUCTION_LEVEL_COMBOBOX_SERVICE);
		projectionResultsTableConfig.setModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
		projectionResultsTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		projectionResultsTable.setGtnPagedTableConfig(projectionResultsTableConfig);
		componentList.add(projectionResultsTable);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkScreenRegisteryConstants.PROJECTION_NAME,
				GtnFrameworkScreenRegisteryConstants.DESCRIPTION,
				GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY,
				GtnFrameworkScreenRegisteryConstants.CUSTOMER_LEVEL,
				GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY,
				GtnFrameworkScreenRegisteryConstants.PRODUCT_LEVEL, GtnFrameworkScreenRegisteryConstants.CREATED_BY,
				GtnFrameworkScreenRegisteryConstants.CREATED_DATE, GtnFrameworkScreenRegisteryConstants.MODIFIED_DATE,
				GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID };
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

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
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		params.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);
		params.add(Arrays.asList(nameSpace + "_" + GtnFrameworkForecastingStringConstants.PROJECTION_RESULTS_TABLE));
		params.add(Arrays.asList(new Object[] { "" }));
		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);
		resetCtrlBtn.setGtnUIFrameWorkActionConfigList(resetActionConfigList);
		resetCtrlBtn.setEnable(false);
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
		editBtn.setEnable(false);
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
		viewBtn.setEnable(false);
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
		deleteBtn.setEnable(false);
		componentList.add(deleteBtn);

		GtnUIFrameWorkActionConfig confirmDeleteAction = new GtnUIFrameWorkActionConfig();
		confirmDeleteAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmDeleteAction.addActionParameter("Confirmation");
		confirmDeleteAction.addActionParameter("Are you sure you want to delete the projection?");
		List<GtnUIFrameWorkActionConfig> onSuccessDeleteActionConfigList = new ArrayList<>();
		confirmDeleteAction.addActionParameter(onSuccessDeleteActionConfigList);

		deleteBtn.addGtnUIFrameWorkActionConfig(confirmDeleteAction);
	}

	private GtnUIFrameWorkActionConfig loadForecastEligibleDate(String nameSpace) {
		GtnUIFrameWorkActionConfig loadDateAction = new GtnUIFrameWorkActionConfig();
		loadDateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadDateAction.addActionParameter(GtnForecastEligibleDateLoadAction.class.getName());
		loadDateAction.addActionParameter(nameSpace + "_" + GtnFrameworkForecastingStringConstants.FROM);
		loadDateAction.addActionParameter(nameSpace + GtnFrameworkForecastingStringConstants.CUSTOMER_SELECTION_FORECASTELIGIBILITY_DATE);
		return loadDateAction;
	}

}
