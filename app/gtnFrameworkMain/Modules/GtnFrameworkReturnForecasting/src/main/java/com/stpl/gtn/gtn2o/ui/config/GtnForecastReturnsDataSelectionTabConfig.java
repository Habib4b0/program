package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.dataselection.GtnFrameworkForecastReturnProdHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnForecastReturnsDataSelectionTabConfig {

	private GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();

	public void addDataSelectionTab(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dataSelectionRootLayout",
				GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(rootLayoutConfig);

		configureMainLayouts(componentList, rootLayoutConfig.getComponentId(), namespace);

	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkLayoutConfig dataSelectionMainLayout = new GtnUIFrameworkLayoutConfig();
		dataSelectionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig dataSelectionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		dataSelectionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataSelectionMainLayoutConfig.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dataSelectionTabMainLayout");
		dataSelectionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		dataSelectionMainLayoutConfig.setParentComponentId(parentId);
		dataSelectionMainLayoutConfig.setSpacing(Boolean.TRUE);
		dataSelectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dataSelectionMainLayoutConfig.setGtnLayoutConfig(dataSelectionMainLayout);
		componentList.add(dataSelectionMainLayoutConfig);
		addComponentList(componentList, "dataSelectionTabMainLayout", namespace);

	}

	private void addComponentList(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		addProjectionOptionPanel(componentList, parentId, namespace);
		addProfileModeOptionGroup(componentList, namespace);
		addPrivateViewCompanyProjectionname(componentList, namespace);
		addPublicViewBusinessUnitDescription(componentList, namespace);
		addTimePeriod(componentList, namespace);
		addProductSelectionLayout(componentList, parentId, namespace);

		GtnFrameworkForecastReturnProdHierarchyConfig productSelectiondsTab = new GtnFrameworkForecastReturnProdHierarchyConfig();
		componentList.addAll(productSelectiondsTab.getProductSelectionLayoutComponents(namespace));

	}

	private void addProjectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig selectionCriteriaPanel = new GtnUIFrameworkComponentConfig();
		selectionCriteriaPanel.setComponentName("Projection Options");
		selectionCriteriaPanel
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionOptions");
		selectionCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		selectionCriteriaPanel
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + parentId);
		selectionCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		selectionCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(selectionCriteriaPanel);

		GtnUIFrameworkLayoutConfig projectionOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionMainLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionOptions");
		projectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionMainLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setGtnLayoutConfig(projectionOptionMainLayout);
		componentList.add(projectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig modeLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabModeSelectionLayout");
		modeLayoutConfig.setAddToParent(Boolean.TRUE);
		modeLayoutConfig.setGtnLayoutConfig(modeSelectionlayout);
		modeLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkLayoutConfig fromToMainLayout = new GtnUIFrameworkLayoutConfig();
		fromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabFromToMainLayout");
		fromToLayoutConfig.setAddToParent(Boolean.TRUE);
		fromToLayoutConfig.setSpacing(Boolean.TRUE);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		fromToLayoutConfig.setGtnLayoutConfig(fromToMainLayout);
		componentList.add(fromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		privateViewCompanyProjectionNameLayout.setAddToParent(Boolean.TRUE);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCompanyProjectionNameLayoutConfig);
		privateViewCompanyProjectionNameLayout
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		componentList.add(privateViewCompanyProjectionNameLayout);

	}

	private void addProfileModeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		String compId = namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProfileOptionMode";
		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig(compId,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabModeSelectionLayout");
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig addModeTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addModeTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addModeTypeOptionGroup.setComponentId(compId);
		addModeTypeOptionGroup.setComponentName("Mode");
		addModeTypeOptionGroup.setAddToParent(Boolean.TRUE);
		addModeTypeOptionGroup.setParentComponentId(compId + GtnFrameworkCssConstants.HORIZONTAL);
		addModeTypeOptionGroup.setEnable(Boolean.FALSE);

		GtnUIFrameworkOptionGroupConfig gtnUIFrameworkOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		gtnUIFrameworkOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Add", "Search" }));
		gtnUIFrameworkOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		gtnUIFrameworkOptionGroupConfig.setEnable(Boolean.FALSE);
		addModeTypeOptionGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		addModeTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(gtnUIFrameworkOptionGroupConfig);

		componentList.add(addModeTypeOptionGroup);
	}

	private void addPrivateViewCompanyProjectionname(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig privateViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig privateViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		privateViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewsLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPrivateViewLayout");
		privateViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		privateViewsLayoutConfig.setSpacing(Boolean.TRUE);
		privateViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		privateViewsLayoutConfig.setGtnLayoutConfig(privateViewsLayout);
		componentList.add(privateViewsLayoutConfig);

		GtnUIFrameworkComponentConfig privateView = new GtnUIFrameworkComponentConfig();
		privateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateView.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPrivateViews");
		privateView.setComponentName("Private Views");
		privateView.setAddToParent(Boolean.TRUE);
		privateView.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPrivateViewLayout");

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(new Object[] { "dsTabPrivateViewSearchLookupView", "Private View", "795", "875" }));
		list.add(conf);

		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(Boolean.FALSE);
		privateView.setGtnTextBoxConfig(textboxConfig);

		privateView.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(privateView);

		GtnUIFrameworkLayoutConfig companyLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig companyLayoutConfig = new GtnUIFrameworkComponentConfig();
		companyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		companyLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabCompanyLayout");
		companyLayoutConfig.setAddToParent(Boolean.TRUE);
		companyLayoutConfig.setSpacing(Boolean.TRUE);
		companyLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		companyLayoutConfig.setGtnLayoutConfig(companyLayout);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		company.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "company");
		company.setComponentName("Company");
		company.setAddToParent(Boolean.TRUE);
		company.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabCompanyLayout");
		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP);
		company.setGtnComboboxConfig(companyTypeConfig);
		componentList.add(company);

		GtnUIFrameworkLayoutConfig projectionNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionNameLayoutConfig.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionNameLayout");
		projectionNameLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionNameLayoutConfig.setSpacing(Boolean.TRUE);
		projectionNameLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		projectionNameLayoutConfig.setGtnLayoutConfig(projectionNameLayout);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionName
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionName");
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionNameLayout");
		componentList.add(projectionName);
	}

	private void addPublicViewBusinessUnitDescription(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig publicViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPublicViewLayout");
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setEnable(Boolean.FALSE);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(publicViewsLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig publicView = new GtnUIFrameworkComponentConfig();
		publicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicView.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPublicViews");
		publicView.setComponentName("Public Views");
		publicView.setAddToParent(Boolean.TRUE);
		publicView.setEnable(Boolean.FALSE);
		publicView.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPublicViewLayout");

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(new Object[] { "publicViewSearchLookupView", "Public View", "795", "875" }));
		list.add(conf);
		publicView.setGtnUIFrameWorkActionConfigList(list);

		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(Boolean.FALSE);
		publicView.setGtnTextBoxConfig(textboxConfig);

		componentList.add(publicView);

		GtnUIFrameworkLayoutConfig businessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabBusinessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(Boolean.TRUE);
		businessUnitLayoutConfig.setSpacing(Boolean.TRUE);
		businessUnitLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		businessUnitLayoutConfig.setGtnLayoutConfig(businessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		businessUnit.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "businessUnit");
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(Boolean.TRUE);
		businessUnit.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabBusinessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.BUSINESS_UNIT_GLCOMP);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);

		componentList.add(businessUnit);

		GtnUIFrameworkLayoutConfig projectionDescLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionDescLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionDescLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionDescLayoutConfig.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionDescLayout");
		projectionDescLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionDescLayoutConfig.setSpacing(Boolean.TRUE);
		projectionDescLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		projectionDescLayoutConfig.setGtnLayoutConfig(projectionDescLayout);
		componentList.add(projectionDescLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionName.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionDescription");
		projectionName.setComponentName("Projection Description");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabProjectionDescLayout");
		componentList.add(projectionName);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig timePeriodOuterLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodOuterLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig timePeriodPanel = new GtnUIFrameworkComponentConfig();
		timePeriodPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		timePeriodPanel.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabTimePeriodOuterLayout");
		timePeriodPanel.setAddToParent(Boolean.TRUE);
		timePeriodPanel.setSpacing(Boolean.TRUE);
		timePeriodPanel.setGtnLayoutConfig(timePeriodOuterLayout);
		timePeriodPanel.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabFromToMainLayout");
		componentList.add(timePeriodPanel);

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabTimePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(Boolean.TRUE);
		panel.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabTimePeriodOuterLayout");
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		fromAndToperiodLayoutConfig.setSpacing(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromAndToperiodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		fromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabTimePeriod");
		fromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(fromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig fromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		fromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabFromPeriodLayout");
		fromPeriodLayoutConfig.setSpacing(Boolean.TRUE);
		fromPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromPeriodLayoutConfig.setGtnLayoutConfig(fromPeriodLayout);
		fromPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		fromPeriod.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabFromPeriod");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);

		fromPeriod.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabFromPeriodLayout");
		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		fromPeriodConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE);
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);
		fromPeriodConfig.setHasDefaultValue(true);

		List<GtnUIFrameWorkActionConfig> gtnUiFramwworkActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUiFramwworkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUiFramwworkActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUiFramwworkActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_FROM_TO_PERIOD_ACTION,
						namespace, "dsTabProfileOptionMode", "dsTabFromPeriod", "dsTabToPeriod" }));
		gtnUiFramwworkActionConfigList.add(gtnUiFramwworkActionConfig);
		fromPeriod.setGtnUIFrameWorkActionConfigList(gtnUiFramwworkActionConfigList);
		GtnUIFrameworkValidationConfig valConfigForFromPeriod = new GtnUIFrameworkValidationConfig();
		valConfigForFromPeriod.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		fromPeriod.setGtnUIFrameworkValidationConfig(valConfigForFromPeriod);

		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabToPeriodLayout");
		toPeriodLayoutConfig.setSpacing(Boolean.TRUE);
		toPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		toPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		toPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		toPeriod.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabToPeriod");
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(Boolean.TRUE);
		toPeriod.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabToPeriodLayout");
		toPeriod.setEnable(Boolean.FALSE);

		GtnUIFrameworkComboBoxConfig toPeriodTypeConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		toPeriodTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_TO_DATE);
		toPeriodTypeConfig.setHasDefaultValue(true);

		toPeriod.setGtnComboboxConfig(toPeriodTypeConfig);
		GtnUIFrameworkValidationConfig valConfigForToPeriod = new GtnUIFrameworkValidationConfig();
		valConfigForToPeriod.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		toPeriod.setGtnUIFrameworkValidationConfig(valConfigForToPeriod);
		componentList.add(toPeriod);
	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		/*
		 * 
		 * using same panel "productSelectionPanel" across many screen so
		 * component id should be same of the prod hire look up component id
		 */
		productSelectionPanel
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionPanel");
		productSelectionPanel
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + parentId);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionPanel.setSpacing(true);
		productSelectionPanel.setAddToParent(Boolean.TRUE);
		componentList.add(productSelectionPanel);

	}

}
