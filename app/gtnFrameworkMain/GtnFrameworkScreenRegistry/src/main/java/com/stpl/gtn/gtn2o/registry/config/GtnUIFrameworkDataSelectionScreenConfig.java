package com.stpl.gtn.gtn2o.registry.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkNewToOldArchitectureGenerateAction;
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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

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
		
		dataSelectionView.addViewAction(loadForecastEligibleDate());
		
		return dataSelectionView;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig mainLayout = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "dataSelectionMainLayout", false, null);
		mainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		mainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(mainLayout);

		addProjectionOptionsPanel(componentList,mainLayout.getComponentId(), nameSpace);
		addCustomerSelectionPanel(componentList,mainLayout.getComponentId(), nameSpace);

		GtnFrameworkForecastCustomerHierarchyConfig customerSelectionConfig = new GtnFrameworkForecastCustomerHierarchyConfig();
		componentList.addAll(customerSelectionConfig.getCustomerSelectionLayoutCompoents(nameSpace));
		addProductSelectionPanel(componentList, nameSpace);

		GtnFrameworkForecastProdHierarchyConfig productSelectionConfig = new GtnFrameworkForecastProdHierarchyConfig();
		componentList.addAll(productSelectionConfig.getProductSelectionLayoutComponents(nameSpace));

		addActionButtonLayout(componentList,mainLayout.getComponentId(), nameSpace);
		addProjectionResultsPanel(componentList,mainLayout.getComponentId(), nameSpace);
		addControlButtonLayout(componentList,mainLayout.getComponentId(), nameSpace);
	}

	private void addProjectionOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionOptionsPanel = new GtnUIFrameworkComponentConfig();
		projectionOptionsPanel.setComponentName("Projection Options");
		projectionOptionsPanel.setComponentId(nameSpace + "_" + "projectionOptions");
		projectionOptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionsPanel.setParentComponentId(parentComponentId);
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

		addProjectionOptionInnerLayout(componentList,projectionOptionsMainLayoutConfig.getComponentId(), nameSpace);
	}

	private void addProjectionOptionInnerLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(nameSpace + "_" + "projectionOptionsInnerLayout");
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(parentComponentId);
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		addModeSelectionLayout(componentList,projectionOptionInnerLayoutConfig.getComponentId(), nameSpace);
		addFromToPeriodLayout(componentList,parentComponentId, nameSpace);
		addProjectionSelectionLayout(componentList,projectionOptionInnerLayoutConfig.getComponentId(), nameSpace);
		addMode(componentList, nameSpace);
		addTimePeriod(componentList, nameSpace);
	}

	private void addModeSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig modeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig modeSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeSelectionLayoutConfig.setComponentId(nameSpace + "_" + "modeSelectionLayout");
		modeSelectionLayoutConfig.setAddToParent(Boolean.TRUE);
		modeSelectionLayoutConfig.setParentComponentId(parentComponentId);
		modeSelectionLayoutConfig.setGtnLayoutConfig(modeSelectionLayout);
		componentList.add(modeSelectionLayoutConfig);
	}

	private void addFromToPeriodLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig fromToLayout = new GtnUIFrameworkLayoutConfig();
		fromToLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setComponentId(nameSpace + "_" + "fromToLayout");
		fromToLayoutConfig.setAddToParent(Boolean.TRUE);
		fromToLayoutConfig.setSpacing(Boolean.TRUE);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(parentComponentId);
		fromToLayoutConfig.setGtnLayoutConfig(fromToLayout);
		componentList.add(fromToLayoutConfig);
	}

	private void addProjectionSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig projectionSelectionLayout = new GtnUIFrameworkLayoutConfig();
		projectionSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig projectionSelectionLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionSelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionSelectionLayoutConfig.setComponentId(nameSpace + "_" + "projectionSelectionLayout");
		projectionSelectionLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionSelectionLayoutConfig.setParentComponentId(parentComponentId);
		projectionSelectionLayoutConfig.setGtnLayoutConfig(projectionSelectionLayout);
		componentList.add(projectionSelectionLayoutConfig);
		
		addPrivateViewLookup(componentList,projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addCompany(componentList,projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addProjectionName(componentList,projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addPublicViewLookup(componentList,projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addBusinessUnit(componentList,projectionSelectionLayoutConfig.getComponentId(), nameSpace);
		addProjectionDescription(componentList,projectionSelectionLayoutConfig.getComponentId(), nameSpace);
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
		modeOptionRadioGroup.setAddToParent(Boolean.TRUE);
		modeOptionRadioGroup.setParentComponentId(componentId);

		GtnUIFrameworkOptionGroupConfig modeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		modeOptionGroupConfig.setItemValues(Arrays.asList( "Add", "Search" ));
		modeOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		modeOptionGroupConfig.setEnable(Boolean.TRUE);
		modeOptionRadioGroup
				.setComponentStyle(Arrays.asList( GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE ));
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

		optionChangeActionConfig.setActionParameterList(popupActionParam);
                
                modeOptionRadioGroup.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addPrivateViewLookup(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig privateViewLookupLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "privateViewLookupLayout", true, parentComponentId);
		componentList.add(privateViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig privateViewLookup = new GtnUIFrameworkComponentConfig();
		privateViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		privateViewLookup.setComponentId(nameSpace + "_" + "privateViewLookup");
		privateViewLookup.setComponentName("Private Views");
		privateViewLookup.setAddToParent(Boolean.TRUE);
		privateViewLookup.setParentComponentId(nameSpace + "_" + "privateViewLookupLayout");
		componentList.add(privateViewLookup);
		
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("Private_lookup");
		popupActionParam.add("Private View");
		popupActionParam.add("90%");
		popupActionParam.add("100%");

		popupActionConfig.setActionParameterList(popupActionParam);
		privateViewLookup.addGtnUIFrameWorkActionConfig(popupActionConfig);
		
	}

	private void addCompany(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig companyLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "companyLayout", true, parentComponentId);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		company.setComponentId(nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID);
		company.setComponentName( GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID);
		company.setAddToParent(Boolean.TRUE);
		company.setParentComponentId(nameSpace + "_" + "companyLayout");
		 company.setCustomReference("integerId");

		GtnUIFrameworkComboBoxConfig companyConfig = new GtnUIFrameworkComboBoxConfig();
		companyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyConfig.setComboBoxType("CompanyMasterGLcomp");
		company.setGtnComboboxConfig(companyConfig);
		componentList.add(company);
	}

	private void addProjectionName(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "projectionNameLayout", true, parentComponentId);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionName.setComponentId(nameSpace + "_" + "projectionName");
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(nameSpace + "_" + "projectionNameLayout");
		componentList.add(projectionName);
	}

	private void addPublicViewLookup(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig publicViewLookupLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "publicViewLayout", true, parentComponentId);
		componentList.add(publicViewLookupLayoutConfig);

		GtnUIFrameworkComponentConfig publicViewLookup = new GtnUIFrameworkComponentConfig();
		publicViewLookup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		publicViewLookup.setComponentId(nameSpace + "_" + "publicView");
		publicViewLookup.setComponentName("Public Views");
		publicViewLookup.setAddToParent(Boolean.TRUE);
		publicViewLookup.setParentComponentId(nameSpace + "_" + "publicViewLayout");
		componentList.add(publicViewLookup);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("Public_lookup");
		popupActionParam.add("Public View");
		popupActionParam.add("90%");
		popupActionParam.add("100%");

		popupActionConfig.setActionParameterList(popupActionParam);
		publicViewLookup.addGtnUIFrameWorkActionConfig(popupActionConfig);

	}

	private void addBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "businessUnitLayout", true, parentComponentId);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		businessUnit.setComponentId(nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID);
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(Boolean.TRUE);
		businessUnit.setParentComponentId(nameSpace + "_" + "businessUnitLayout");
		 businessUnit.setCustomReference("integerId");

		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType("BusinessUnitGLcomp");
		businessUnit.setGtnComboboxConfig(businessUnitConfig);
		componentList.add(businessUnit);

	}

	private void addProjectionDescription(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionDescriptionLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "projectionDescriptionLayout", true, parentComponentId);
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
		addFromPeriod(componentList,timePeriodInnerLayoutConfig.getComponentId(), nameSpace);
		addToPeriod(componentList,timePeriodInnerLayoutConfig.getComponentId(), nameSpace);

	}

	private void addFromPeriod(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "fromPeriodLayout", true, parentComponentId);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		fromPeriod.setComponentId(nameSpace + "_" + "from");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);
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

	private void addToPeriod(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig.setComponentId(nameSpace + "_" + "toPeriodLayout");
		toPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		toPeriodLayoutConfig.setSpacing(Boolean.TRUE);
		toPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		toPeriodLayoutConfig.setParentComponentId(parentComponentId);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		toPeriod.setComponentId(nameSpace + "_" + "to");
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(Boolean.TRUE);
		toPeriod.setParentComponentId(nameSpace + "_" + "toPeriodLayout");
		toPeriod.setEnable(false);

		GtnUIFrameworkComboBoxConfig toPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodConfig.setItemCaptionValues(new ArrayList<>());
		toPeriodConfig.setItemValues(new ArrayList<>());
		toPeriodConfig.setHasDefaultValue(true);
		toPeriodConfig.setDefaultDesc("next");
		toPeriod.setGtnComboboxConfig(toPeriodConfig);
		

		GtnUIFrameWorkActionConfig fromAndToPeriodAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
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

	private void addCustomerSelectionPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionPanel.setComponentName("Customer Selection");
		customerSelectionPanel.setComponentId(nameSpace + "_" + "customerSelectionPanel");
		customerSelectionPanel.setAddToParent(Boolean.TRUE);
		customerSelectionPanel.setParentComponentId(parentComponentId);
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

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig actionButtonLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "actionButtonLayout", true, parentComponentId);
		actionButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(actionButtonLayout);

		addGenerateBtn(componentList,actionButtonLayout.getComponentId(), nameSpace);
		addSearchBtn(componentList, actionButtonLayout.getComponentId(),nameSpace);
		addResetBtn(componentList, actionButtonLayout.getComponentId(),nameSpace);
		addSaveViewBtn(componentList, actionButtonLayout.getComponentId(),nameSpace);
		addDeleteViewBtn(componentList, actionButtonLayout.getComponentId(),nameSpace);

	}

	private void addGenerateBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig generateBtn = new GtnUIFrameworkComponentConfig();
		generateBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateBtn.setComponentId(nameSpace + "_" + "dsGenerate");
		generateBtn.setComponentName("GENERATE");
		generateBtn.setParentComponentId(parentComponentId);
		generateBtn.setAddToParent(true);

		componentList.add(generateBtn);

		GtnUIFrameWorkActionConfig generateAction = new GtnUIFrameWorkActionConfig();
		generateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		generateAction.addActionParameter(GtnFrameworkNewToOldArchitectureGenerateAction.class.getName());
		generateAction.addActionParameter("Commercial Forecasting_customerDualListBox");
		generateAction.addActionParameter("Commercial Forecasting_productDualListBox");
		generateAction.addActionParameter("forecastLandingScreen_customerHierarchy");
		generateAction.addActionParameter("Commercial_Forecasting_customerSelectionRelationship");
		generateAction.addActionParameter("Commercial_Forecasting_customerRelationshipVersion");
		generateAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastLevel");
		generateAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastEligibilityDate");
		generateAction.addActionParameter("Commercial Forecasting_prodhierarchyName");
		generateAction.addActionParameter("Commercial Forecasting_prodrelationship");
		generateAction.addActionParameter("Commercial Forecasting_prodforecastLevel");
		generateAction.addActionParameter("Commercial_Forecasting_productRelationshipVersion");
		generateAction.addActionParameter("Commercial Forecasting_productGroup");
		generateAction.addActionParameter("Commercial Forecasting_company");
		generateAction.addActionParameter("Commercial Forecasting_businessUnit");
		generateAction.addActionParameter("Commercial Forecasting_from");
		generateAction.addActionParameter("Commercial Forecasting_productLevel");
		generateAction.addActionParameter("Commercial Forecasting_to");
		generateAction.addActionParameter("Commercial Forecasting_projectionName");
		generateAction.addActionParameter("Commercial Forecasting_projectionDescription");
		generateAction.addActionParameter("Commercial Forecasting_customerGroup");
		generateAction.addActionParameter("Commercial_Forecasting_customerSelectionLevel");
		generateAction.addActionParameter("Commercial Forecasting_privateViewLookup");
		generateAction.addActionParameter("Commercial Forecasting_publicView");
		generateBtn.addGtnUIFrameWorkActionConfig(generateAction);
	}

	private void addSearchBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig searchBtn = new GtnUIFrameworkComponentConfig();
		searchBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchBtn.setComponentId(nameSpace + "_" + "searchBtn");
		searchBtn.setComponentName("SEARCH");
		searchBtn.setParentComponentId(parentComponentId);
		searchBtn.setAddToParent(true);
                List<GtnUIFrameWorkActionConfig> actionConfigListSearch = new ArrayList<>();
                GtnUIFrameWorkActionConfig loadDataSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
                loadDataSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
                loadDataSearchTableActionConfig.setActionParameterList(
                Arrays.asList(new Object[]{nameSpace+"_"+"projectionResultsTable"}));
                loadDataSearchTableActionConfig.setFieldValues(
                        Arrays.asList(new String[]{nameSpace + "_" + "to",nameSpace + "_" + "from",
                            nameSpace+"_"+"projectionName",
                            nameSpace+"_"+"projectionDescription",
                            }));
                actionConfigListSearch.add(loadDataSearchTableActionConfig);
                searchBtn.setGtnUIFrameWorkActionConfigList(actionConfigListSearch);
		searchBtn.setEnable(false);
		componentList.add(searchBtn);

	}

	private void addResetBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig resetBtn = new GtnUIFrameworkComponentConfig();
		resetBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetBtn.setComponentId(nameSpace + "_" + "resetBtn");
		resetBtn.setComponentName("RESET");
		resetBtn.setParentComponentId(parentComponentId);
		resetBtn.setAddToParent(true);
		componentList.add(resetBtn);
		
		
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkScreenRegisteryConstants.GTN_NEWARCHITECTURE_CONFIRMATION_MSG_RESET_HEADER);
		params.add(GtnFrameworkScreenRegisteryConstants.GTN_VALIDATION_MSG_RESET);
		params.add(Arrays.asList(
				nameSpace + "_" + "profileMode",
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
				nameSpace + "_" + "projectionName",
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
				nameSpace + "_" + "projectionDescription",
				nameSpace + "_" + "privateViewLookup",
				nameSpace + "_" + "publicView"
				
				));
		
		params.add(Arrays.asList(
				"Add",
				0,
				GtnFrameworkCommonStringConstants.STRING_EMPTY,
				0,
				GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY
				
				
				));
		
		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);
		resetBtn.setGtnUIFrameWorkActionConfigList(resetActionConfigList);
		
	}

	private void addSaveViewBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(nameSpace + "_" + "saveViewBtn");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(parentComponentId);
		saveViewBtn.setAddToParent(true);
		componentList.add(saveViewBtn);
	}

	private void addDeleteViewBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig deleteViewBtn = new GtnUIFrameworkComponentConfig();
		deleteViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewBtn.setComponentId(nameSpace + "_" + "deleteViewBtn");
		deleteViewBtn.setComponentName("DELETE VIEW");
		deleteViewBtn.setParentComponentId(parentComponentId);
		deleteViewBtn.setAddToParent(true);
		deleteViewBtn.setEnable(false);
		componentList.add(deleteViewBtn);
	}

	private void addProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
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
		projectionResultsTableConfig.setColumnHeaders(Arrays.asList( "Projection Name", "Description",
				"Customer Hierarchy", "Customer Level", "Product Hierarchy", "Product Level", "Created By",
				"Created Date", "Modified Date", "Company", "Business Unit" ));
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
		String[] propertyIds = {"projactionName", "description",
				"customerHierarchy", "customerLevel", "productHierarchy", "productLevel", "createdBy", "createdDate",
				"modifiedDate", GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,  
                                GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID};
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,  GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,  GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8
				,GtnUIFrameworkComponentType.DATEFIELDVAADIN8,GtnUIFrameworkComponentType.DATEFIELDVAADIN8,  GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,  GtnUIFrameworkComponentType.TEXTBOX_VAADIN8};
		
		for (int i = 0; i < propertyIds.length; i++) {
			
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfig.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfig.setGtnComponentType(componentType[i]);
			customFilterConfigMap.put(pagedTableCustomFilterConfig.getPropertId(), pagedTableCustomFilterConfig);
			
		}
		return customFilterConfigMap;
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig controlButtonLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "controlButtonLayout", true, parentComponentId);
		controlButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		componentList.add(controlButtonLayout);

		addResetCtrlBtn(componentList,controlButtonLayout.getComponentId(), nameSpace);
		addEditBtn(componentList,controlButtonLayout.getComponentId(), nameSpace);
		addViewBtn(componentList,controlButtonLayout.getComponentId(), nameSpace);
		addDeleteBtn(componentList,controlButtonLayout.getComponentId(), nameSpace);

	}
	
	private void addResetCtrlBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace){
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
	
	private void addEditBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace){
		GtnUIFrameworkComponentConfig editBtn = new GtnUIFrameworkComponentConfig();
		editBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		editBtn.setComponentId(nameSpace + "_" + "editBtn");
		editBtn.setComponentName("EDIT");
		editBtn.setParentComponentId(parentComponentId);
		editBtn.setAddToParent(true);
		editBtn.setEnable(false);;
		componentList.add(editBtn);
	}
	
	private void addViewBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace){
		GtnUIFrameworkComponentConfig viewBtn = new GtnUIFrameworkComponentConfig();
		viewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		viewBtn.setComponentId(nameSpace + "_" + "viewBtn");
		viewBtn.setComponentName("VIEW");
		viewBtn.setParentComponentId(parentComponentId);
		viewBtn.setAddToParent(true);
		viewBtn.setEnable(false);
		componentList.add(viewBtn);
	}
	
	private void addDeleteBtn(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace){
		GtnUIFrameworkComponentConfig deleteBtn = new GtnUIFrameworkComponentConfig();
		deleteBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteBtn.setComponentId(nameSpace + "_" + "deleteBtn");
		deleteBtn.setComponentName("DELETE");
		deleteBtn.setParentComponentId(parentComponentId);
		deleteBtn.setAddToParent(true);
		deleteBtn.setEnable(false);
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
