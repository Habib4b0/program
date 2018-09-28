package com.stpl.gtn.gtn2o.registry.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkDeleteViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastEditAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastingCCPTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkNewToOldArchitectureDeleteAction;
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

public class GtnUIFrameworkDataSelectionScreenConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkComponentConfig getHorizontalLayoutConfig(String compId, String parentLayout) {
		GtnUIFrameworkComponentConfig profileOptionLayoutConf = new GtnUIFrameworkComponentConfig();
		profileOptionLayoutConf.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		profileOptionLayoutConf.setComponentId(compId + GtnFrameworkCssConstants.HORIZONTAL);
		profileOptionLayoutConf.setComponentName(compId + GtnFrameworkCssConstants.HORIZONTAL);
		profileOptionLayoutConf.setParentComponentId(parentLayout);
		profileOptionLayoutConf.setAddToParent(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		profileOptionLayoutConf.setGtnLayoutConfig(conf);
		return profileOptionLayoutConf;
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
		deductionComponent.setCustomReference("integerId");

		GtnUIFrameworkComboBoxConfig deductionConfig = new GtnUIFrameworkComboBoxConfig();
		deductionConfig.setHasDefaultValue(true);
		deductionConfig.setDefaultDesc("next");
		deductionConfig.setActualWsContext("/GtnSearchWebService");
		deductionConfig.setModuleName("serviceRegistry");
		deductionConfig.setActualWsUrl("/gtnSearch");
		deductionConfig.setLoadingUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		deductionConfig.setComboBoxType("dataSelectionDeduction");
		deductionConfig.setActualWsModuleName("generalSearch");
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

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig optionChangeActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(optionChangeActionConfig);
		optionChangeActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add(GtnModeOptionValueChangeAction.class.getName());
		popupActionParam.add(nameSpace + "_" + "profileMode");
		popupActionParam.add(nameSpace + "_" + "dsGenerate");
		popupActionParam.add(nameSpace + "_" + "searchBtn");
		popupActionParam.add(nameSpace + "_" + "resetBtn");
		popupActionParam.add(nameSpace + "_" + "saveViewBtn");
		popupActionParam.add(nameSpace + "_" + "deleteViewBtn");
		popupActionParam.add(nameSpace + "_" + "resetCtrlBtn");
		popupActionParam.add(nameSpace + "_" + "editBtn");
		popupActionParam.add(nameSpace + "_" + "viewBtn");
		popupActionParam.add(nameSpace + "_" + "deleteBtn");
		popupActionParam.add(nameSpace + "_" + "to");
		popupActionParam.add(nameSpace + "_" + "from");

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
		company.setComponentName("Company");
		company.setAddToParent(true);
		company.setParentComponentId(nameSpace + "_" + "companyLayout");
		company.setCustomReference("integerId");

		GtnUIFrameworkComboBoxConfig companyConfig = new GtnUIFrameworkComboBoxConfig();
		companyConfig.setLoadingUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		companyConfig.setComboBoxType("CompanyMasterGLcomp");
		companyConfig.setActualWsUrl("/gtnSearch");
		companyConfig.setModuleName("serviceRegistry");
		companyConfig.setActualWsContext("/GtnSearchWebService");
		companyConfig.setActualWsModuleName("generalSearch");
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
		frequency.setCustomReference("integerId");

		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setHasDefaultValue(true);
		frequencyConfig.setDefaultDesc("Quarterly");
		frequencyConfig.setLoadingUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		frequencyConfig.setComboBoxType("frequency");
		frequencyConfig.setActualWsUrl("/gtnSearch");
		frequencyConfig.setModuleName("serviceRegistry");
		frequencyConfig.setActualWsContext("/GtnSearchWebService");
		frequencyConfig.setActualWsModuleName("generalSearch");
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
		publicViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		publicViewLookup.setComponentId(nameSpace + "_" + "publicView");
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
		businessUnit.setCustomReference("integerId");

		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		businessUnitConfig.setComboBoxType("BusinessUnitGLcomp");
		businessUnitConfig.setActualWsUrl("/gtnSearch");
		businessUnitConfig.setModuleName("serviceRegistry");
		businessUnitConfig.setActualWsContext("/GtnSearchWebService");
		businessUnitConfig.setActualWsModuleName("generalSearch");
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

	private void addFromPeriod(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "fromPeriodLayout", true, parentComponentId);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		fromPeriod.setComponentId(nameSpace + "_" + "from");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(true);
		fromPeriod.setParentComponentId(nameSpace + "_" + "fromPeriodLayout");
		fromPeriod.setCustomReference("integerId");

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
		toPeriod.setComponentId(nameSpace + "_" + "to");
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
		fromAndToPeriodAction.addActionParameter(nameSpace + "_" + "from");
		fromAndToPeriodAction.addActionParameter(nameSpace + "_" + "to");

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

		GtnUIFrameWorkActionConfig generateAction = new GtnUIFrameWorkActionConfig();
		generateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		generateAction.addActionParameter(GtnFrameworkForecastingCCPTableLoadAction.class.getName());
		generateAction.addActionParameter(nameSpace + "_customerDualListBox");
		generateAction.addActionParameter(nameSpace + "_productDualListBox");
		generateAction.addActionParameter(nameSpace + "_customerHierarchy");
		generateAction.addActionParameter(nameSpace + "_customerSelectionRelationship");
		generateAction.addActionParameter(nameSpace + "_customerSelectionRelationship");
		generateAction.addActionParameter(nameSpace + "_customerSelectionForecastLevel");
		generateAction.addActionParameter(nameSpace + "_customerSelectionForecastEligibilityDate");
		generateAction.addActionParameter(nameSpace + "_prodhierarchyName");
		generateAction.addActionParameter(nameSpace + "_prodrelationship");
		generateAction.addActionParameter(nameSpace + "_prodforecastLevel");
		generateAction.addActionParameter(nameSpace + "_customerSelectionRelationship");//
		generateAction.addActionParameter("Commercial Forecasting_company");
		generateAction.addActionParameter(nameSpace + "_businessUnit");
		generateAction.addActionParameter(nameSpace + "_from");
		generateAction.addActionParameter("Commercial Forecasting_to");
		generateAction.addActionParameter("Commercial Forecasting_projectionName");
		generateAction.addActionParameter("Commercial Forecasting_projectionDescription");
		generateAction.addActionParameter(nameSpace + "_frequency");
		generateAction.addActionParameter(nameSpace + "_deductionLevel");
		generateAction.addActionParameter(nameSpace + "_customerSelectionLevel");
		generateAction.addActionParameter(nameSpace + "_productLevel");
		generateAction.addActionParameter(nameSpace + "_customerGroup");
		generateAction.addActionParameter(nameSpace + "_productGroup");
		generateAction.addActionParameter("Commercial Forecasting_salesCustomView");
		generateAction.addActionParameter("Commercial Forecasting_deductionCustomView");
		generateAction.addActionParameter("Commercial Forecasting_privateViewLookup");
		generateAction.addActionParameter("Commercial Forecasting_publicView");
		generateAction.addActionParameter(nameSpace + "_customerRelationshipVersion");
		generateAction.addActionParameter(nameSpace + "_productRelationshipVersion");
		actionList.add(generateAction);
		generateBtn.setGtnUIFrameWorkActionConfigList(actionList);

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
		loadDataSearchTableActionConfig
				.setActionParameterList(Arrays.asList(new Object[] { nameSpace + "_" + "projectionResultsTable" }));
		loadDataSearchTableActionConfig.setFieldValues(Arrays
				.asList(new String[] { nameSpace + "_" + "projectionName", nameSpace + "_" + "projectionDescription",
						nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
						nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
						nameSpace + "_customerHierarchy", nameSpace + "_" + "prodhierarchyName",
						nameSpace + "_" + "customerGroup", nameSpace + "_" + "from", nameSpace + "_" + "to" }));
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
				"Commercial Forecasting" + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				"Commercial Forecasting" + "_" + "projectionName",
				"Commercial Forecasting" + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
				"Commercial Forecasting" + "_" + "projectionDescription", "Commercial Forecasting" + "_" + "publicView",
				nameSpace + "_customerHierarchy", "Commercial_Forecasting_customerSelectionRelationship",
				"Commercial_Forecasting_customerSelectionForecastLevel",
				"Commercial Forecasting" + "_" + "customerGroup",
				"Commercial Forecasting" + "_" + "customerDualListBox",
				"Commercial Forecasting" + "_" + "prodhierarchyName",
				"Commercial Forecasting" + "_" + "prodrelationship",
				"Commercial Forecasting" + "_" + "prodforecastLevel",
				"Commercial Forecasting" + "_" + "productDualListBox", "Commercial Forecasting" + "_" + "productGroup",
				"Commercial Forecasting" + "_" + "profileMode"

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
						+ GtnFrameworkForecastingStringConstants.COMPANY,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.BUSINESS_UNIT,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "to", nameSpace + "_customerHierarchy",
				nameSpace + "_customerSelectionRelationship", nameSpace + "_customerSelectionForecastLevel",

				nameSpace + "_customerSelectionLevel", nameSpace + "_customerRelationshipVersion",
				nameSpace + "_customerSelectionForecastEligibilityDate", "Commercial Forecasting_customerDualListBox",
				"Commercial Forecasting_prodhierarchyName", "Commercial Forecasting_prodrelationship",

				nameSpace + "_productRelationshipVersion", "Commercial Forecasting_prodforecastLevel",

				"Commercial Forecasting_productLevel", "Commercial Forecasting_productDualListBox",
				"Commercial Forecasting_frequency", "Commercial Forecasting_productGroup",
				"Commercial Forecasting_customerGroup"));

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
						+ GtnFrameworkForecastingStringConstants.COMPANY,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.BUSINESS_UNIT,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME,
				nameSpace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "to", nameSpace + "_customerHierarchy",
				nameSpace + "_customerSelectionRelationship", nameSpace + "_customerSelectionForecastLevel",

				nameSpace + "_customerSelectionLevel", nameSpace + "_customerRelationshipVersion",
				nameSpace + "_customerSelectionForecastEligibilityDate", nameSpace + "_customerDualListBox",
				nameSpace + "_prodhierarchyName", nameSpace + "_prodrelationship",

				nameSpace + "_productRelationshipVersion", nameSpace + "_prodforecastLevel",

				nameSpace + "_productLevel", nameSpace + "_productDualListBox", nameSpace + "_frequency",

				nameSpace + "_productGroup", nameSpace + "_customerGroup"));
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
		deleteViewAction.addActionParameter(nameSpace + "_" + "publicView");
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
		projectionResultsTable.setComponentId(nameSpace + "_" + "projectionResultsTable");
		projectionResultsTable.setAddToParent(true);
		projectionResultsTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionResultsTable.setParentComponentId(nameSpace + "_" + "projectionResultsLayout");
		projectionResultsTable.setModuleName("serviceRegistry");

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
		projectionResultsTableConfig.setCountUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		projectionResultsTableConfig.setResultSetUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		projectionResultsTableConfig.setPagedTableWsUrl("/forecastingPagedTableSearch");
		projectionResultsTableConfig.setRegisteredWebContext("/GtnSearchWebService");
		projectionResultsTableConfig.setModuleName("generalSearch");
		projectionResultsTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		projectionResultsTable.setGtnPagedTableConfig(projectionResultsTableConfig);
		componentList.add(projectionResultsTable);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { "projactionName", "description", "customerHierarchy", "customerLevel",
				"productHierarchy", "productLevel", "createdBy", "createdDate", "modifiedDate",
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
		params.add(Arrays.asList(nameSpace + "_" + "projectionResultsTable"));
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

		GtnUIFrameWorkActionConfig forecastEditAction = new GtnUIFrameWorkActionConfig();
		forecastEditAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastEditAction.addActionParameter(GtnFrameworkForecastEditAction.class.getName());
		forecastEditAction.addActionParameter(nameSpace + "_" + "projectionResultsTable");
		forecastEditAction.addActionParameter("Edit");
		forecastEditAction.addActionParameter("Commercial Forecasting_deductionLevel");
		forecastEditAction.addActionParameter("Commercial Forecasting_frequency");
		editBtn.addGtnUIFrameWorkActionConfig(forecastEditAction);

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

		GtnUIFrameWorkActionConfig forecastViewAction = new GtnUIFrameWorkActionConfig();
		forecastViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastViewAction.addActionParameter(GtnFrameworkForecastEditAction.class.getName());
		forecastViewAction.addActionParameter(nameSpace + "_" + "projectionResultsTable");
		forecastViewAction.addActionParameter("View");
		forecastViewAction.addActionParameter("Commercial Forecasting_deductionLevel");
		forecastViewAction.addActionParameter("Commercial Forecasting_frequency");
		viewBtn.addGtnUIFrameWorkActionConfig(forecastViewAction);
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

		GtnUIFrameWorkActionConfig deleteAction = new GtnUIFrameWorkActionConfig();
		deleteAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteAction.addActionParameter(GtnFrameworkNewToOldArchitectureDeleteAction.class.getName());
		deleteAction.addActionParameter(nameSpace + "_" + "projectionResultsTable");
		onSuccessDeleteActionConfigList.add(deleteAction);
		deleteBtn.addGtnUIFrameWorkActionConfig(confirmDeleteAction);
	}

	private GtnUIFrameWorkActionConfig loadForecastEligibleDate(String nameSpace) {
		GtnUIFrameWorkActionConfig loadDateAction = new GtnUIFrameWorkActionConfig();
		loadDateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadDateAction.addActionParameter(GtnForecastEligibleDateLoadAction.class.getName());
		loadDateAction.addActionParameter(nameSpace + "_" + "from");
		loadDateAction.addActionParameter(nameSpace + "_customerSelectionForecastEligibilityDate");
		return loadDateAction;
	}

}
