package com.stpl.gtn.gtn2o.ui.config.dataselection;

import static com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants.FORECAST_LEVEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.action.duallistbox.GtnFrameworkReturnsDualListBoxRightTableRefreshAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkForecastReturnLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsTableConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkForecastReturnDataSelectionConfig {

	private final GtnFrameworkForecastReturnLayoutsConfig layoutsConfig = new GtnFrameworkForecastReturnLayoutsConfig();

	public GtnUIFrameworkViewConfig getSearchView(String namespace) throws GtnFrameworkGeneralException {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "Search View");
		view.setViewId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "V001");
		view.setDefaultView(true);
		addComponentList(view, namespace);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();

		view.setGtnComponentList(componentList);

		configureMainLayouts(componentList, namespace);

		addProjectionOptionPanel(componentList, namespace);
		addProfileModeOptionGroup(componentList, namespace, this);
		addPrivateViewCompanyProjectionname(componentList, namespace);
		addPublicViewBusinessUnitDescription(componentList, namespace);
		addTimePeriod(componentList, namespace);
		addProductSelectionLayout(componentList, namespace);

		GtnFrameworkForecastReturnProdHierarchyConfig productSelection = new GtnFrameworkForecastReturnProdHierarchyConfig();
		componentList.addAll(productSelection.getProductSelectionLayoutComponents(namespace));

		addControlButtonLayout(componentList, namespace);
		addResultsPanel(componentList, namespace);
		addProjectionButtonLayout(componentList, namespace);

	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig dataSelectionMainLayout = new GtnUIFrameworkLayoutConfig();
		dataSelectionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig dataSelectionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		dataSelectionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataSelectionMainLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DATA_SELECTION_MAIN_LAYOUT);
		dataSelectionMainLayoutConfig.setAddToParent(Boolean.FALSE);
		dataSelectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dataSelectionMainLayoutConfig.setGtnLayoutConfig(dataSelectionMainLayout);
		componentList.add(dataSelectionMainLayoutConfig);

	}

	private void addProjectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig selectionCriteriaPanel = new GtnUIFrameworkComponentConfig();
		selectionCriteriaPanel.setComponentName("Projection Options");
		selectionCriteriaPanel
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionOptions");
		selectionCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		selectionCriteriaPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DATA_SELECTION_MAIN_LAYOUT);
		selectionCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		selectionCriteriaPanel.setAddToParent(Boolean.TRUE);
		selectionCriteriaPanel.setSpacing(true);
		componentList.add(selectionCriteriaPanel);

		GtnUIFrameworkLayoutConfig projectionOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionMainLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionMainLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionOptions");
		projectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionMainLayoutConfig.setGtnLayoutConfig(projectionOptionMainLayout);
		componentList.add(projectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "modeSelectionLayout");
		modeLayoutConfig.setAddToParent(Boolean.TRUE);
		modeLayoutConfig.setGtnLayoutConfig(modeSelectionlayout);
		modeLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		modeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkLayoutConfig fromToMainLayout = new GtnUIFrameworkLayoutConfig();
		fromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "fromToMainLayout");
		fromToLayoutConfig.setAddToParent(Boolean.TRUE);
		fromToLayoutConfig.setSpacing(Boolean.TRUE);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		fromToLayoutConfig.setGtnLayoutConfig(fromToMainLayout);
		componentList.add(fromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		privateViewCompanyProjectionNameLayout.setAddToParent(Boolean.TRUE);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCompanyProjectionNameLayoutConfig);
		privateViewCompanyProjectionNameLayout
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		privateViewCompanyProjectionNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(privateViewCompanyProjectionNameLayout);

	}

	private void addPrivateViewCompanyProjectionname(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig privateViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig privateViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		privateViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewsLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewLayout");
		privateViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		privateViewsLayoutConfig.setSpacing(Boolean.TRUE);
		privateViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		privateViewsLayoutConfig.setGtnLayoutConfig(privateViewsLayout);
		componentList.add(privateViewsLayoutConfig);

		GtnUIFrameworkComponentConfig privateView = new GtnUIFrameworkComponentConfig();
		privateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateView.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		privateView.setComponentName("Private Views");
		privateView.setAddToParent(Boolean.TRUE);
		privateView
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewLayout");
		privateView.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		Object privateViewLookup = "Private View";
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewSearchLookupView",
						privateViewLookup, "795", "875"));
		list.add(conf);

		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateSearchResultTable",
				null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewName", "");
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		list.add(resetTableConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.addActionParameter(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewSelectButton");
		list.add(disableAction);

		privateView.setGtnUIFrameWorkActionConfigList(list);

		componentList.add(privateView);

		GtnUIFrameworkLayoutConfig companyLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig companyLayoutConfig = new GtnUIFrameworkComponentConfig();
		companyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		companyLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "companyLayout");
		companyLayoutConfig.setAddToParent(Boolean.TRUE);
		companyLayoutConfig.setSpacing(Boolean.TRUE);
		companyLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		companyLayoutConfig.setGtnLayoutConfig(companyLayout);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		company.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY);
		company.setComponentName("Company");
		company.setAddToParent(Boolean.TRUE);
		company.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "companyLayout");

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP);
		company.setGtnComboboxConfig(companyTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnFrameworkForecastReturnProdHierarchyConfig productSelection = new GtnFrameworkForecastReturnProdHierarchyConfig();
		actionConfigList.add(productSelection.addDualListBoxCustomAction(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY),
				namespace));
		company.setGtnUIFrameWorkActionConfigList(actionConfigList);
		GtnUIFrameworkValidationConfig valConfigForCompany = new GtnUIFrameworkValidationConfig();
		valConfigForCompany.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		company.setGtnUIFrameworkValidationConfig(valConfigForCompany);
		componentList.add(company);

		GtnUIFrameworkLayoutConfig projectionNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionNameLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionNameLayout");
		projectionNameLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionNameLayoutConfig.setSpacing(Boolean.TRUE);
		projectionNameLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		projectionNameLayoutConfig.setGtnLayoutConfig(projectionNameLayout);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionName.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_NAME);
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionNameLayout");

		GtnUIFrameworkValidationConfig valConfigForProjectionName = new GtnUIFrameworkValidationConfig();
		valConfigForProjectionName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		projectionName.setGtnUIFrameworkValidationConfig(valConfigForProjectionName);

		componentList.add(projectionName);
	}

	private void addPublicViewBusinessUnitDescription(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig publicViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "publicViewLayout");
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(publicViewsLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig publicView = new GtnUIFrameworkComponentConfig();
		publicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicView.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		publicView.setComponentName("Public Views");
		publicView.setAddToParent(Boolean.TRUE);
		publicView.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "publicViewLayout");

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		Object publicViewLookup = "Public View";
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "publicViewSearchLookupView",
						publicViewLookup, "795", "875"));
		list.add(conf);

		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "publicSearchResultTable", null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "viewName", "");
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		list.add(resetTableConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.addActionParameter(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "publicViewSelectButton");
		list.add(disableAction);

		publicView.setGtnUIFrameWorkActionConfigList(list);

		componentList.add(publicView);

		GtnUIFrameworkLayoutConfig businessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "businessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(Boolean.TRUE);
		businessUnitLayoutConfig.setSpacing(Boolean.TRUE);
		businessUnitLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		businessUnitLayoutConfig.setGtnLayoutConfig(businessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		businessUnit.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT);
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(Boolean.TRUE);
		businessUnit
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "businessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.BUSINESS_UNIT_GLCOMP);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnFrameworkForecastReturnProdHierarchyConfig productSelection = new GtnFrameworkForecastReturnProdHierarchyConfig();
		actionConfigList.add(productSelection.addDualListBoxCustomAction(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT),
				namespace));
		businessUnit.setGtnUIFrameWorkActionConfigList(actionConfigList);
		GtnUIFrameworkValidationConfig valConfigForBusinessUnit = new GtnUIFrameworkValidationConfig();
		valConfigForBusinessUnit.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		businessUnit.setGtnUIFrameworkValidationConfig(valConfigForBusinessUnit);
		componentList.add(businessUnit);

		GtnUIFrameworkLayoutConfig projectionDescLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionDescLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionDescLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionDescLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionDescLayout");
		projectionDescLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionDescLayoutConfig.setSpacing(Boolean.TRUE);
		projectionDescLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		projectionDescLayoutConfig.setGtnLayoutConfig(projectionDescLayout);
		componentList.add(projectionDescLayoutConfig);

		GtnUIFrameworkComponentConfig projectionDescription = new GtnUIFrameworkComponentConfig();
		projectionDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionDescription.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION);
		projectionDescription.setComponentName("Projection Description");
		projectionDescription.setAddToParent(Boolean.TRUE);
		projectionDescription.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionDescLayout");

		GtnUIFrameworkValidationConfig valConfigForProjectionDescription = new GtnUIFrameworkValidationConfig();
		valConfigForProjectionDescription
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		projectionDescription.setGtnUIFrameworkValidationConfig(valConfigForProjectionDescription);

		componentList.add(projectionDescription);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(Boolean.TRUE);
		panel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "fromToMainLayout");
		panel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		fromAndToperiodLayoutConfig.setSpacing(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromAndToperiodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		fromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "timePeriod");
		fromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(fromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig fromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		fromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "fromPeriodLayout");
		fromPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromPeriodLayoutConfig.setGtnLayoutConfig(fromPeriodLayout);
		fromPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		fromPeriod.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);

		fromPeriod.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "fromPeriodLayout");
		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		fromPeriodConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE);
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);
		fromPeriodConfig.setHasDefaultValue(true);
		Object fromPeriodDdlb = GtnFrameworkCommonConstants.FROM_PERIOD;
		List<GtnUIFrameWorkActionConfig> gtnUiFramwworkActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUiFramwworkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUiFramwworkActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUiFramwworkActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_FROM_TO_PERIOD_ACTION, namespace,
						GtnFrameworkCommonConstants.PROFILE_OPTION_MODE, fromPeriodDdlb,
						GtnFrameworkCommonConstants.TO_PERIOD));
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
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "toPeriodLayout");
		toPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		toPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		toPeriod.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD);
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(Boolean.TRUE);
		toPeriod.setEnable(Boolean.FALSE);

		toPeriod.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "toPeriodLayout");

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

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		productSelectionPanel
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionPanel");
		productSelectionPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DATA_SELECTION_MAIN_LAYOUT);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionPanel.setSpacing(true);
		productSelectionPanel.setAddToParent(Boolean.TRUE);
		componentList.add(productSelectionPanel);

	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();
		GtnUIFrameworkComponentConfig controlMainLayouts = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsControlMainLayout",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.DATA_SELECTION_MAIN_LAYOUT);
		controlMainLayouts.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		controlMainLayouts.setSpacing(true);
		controlMainLayouts.setMargin(true);
		componentList.add(controlMainLayouts);

		GtnUIFrameworkComponentConfig controlLayouts = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsControlLayout",
				controlMainLayouts.getComponentId());
		componentList.add(controlLayouts);

		GtnUIFrameworkComponentConfig generateBtn = new GtnUIFrameworkComponentConfig();
		generateBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateBtn.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsGenerate");
		generateBtn.setComponentName("GENERATE");
		generateBtn.setParentComponentId(controlLayouts.getComponentId());
		generateBtn.setAddToParent(true);

		componentList.add(generateBtn);

		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentConfigList = new ArrayList<>();

		List<GtnUIFrameWorkActionConfig> popupChildActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customPopupActionConfig = new GtnUIFrameWorkActionConfig();
		Object popupChildActionlist = popupChildActionList;
		customPopupActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customPopupActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_POPUP_CHANGE_ACTION,
						GtnFrameworkForecastConstantCommon.ADD_MODE, namespace, popupChildActionlist));

		customPopupActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_NAME));

		GtnUIFrameWorkActionConfig gtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		Object generateButton = "dataSelectionGenerateButton";
		gtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkGenerateActionConfig.setActionParameterList(Arrays.asList(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_GENERATE_ACTION, GtnFrameworkCommonConstants.RETURNS,
				"product", "Quarterly",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				"returnsForecastMainScreenDataSelection_fromPeriod", "returnsForecastMainScreenDataSelection_toPeriod",
				generateButton, GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT));

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		Object action = "generate";
		selectAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DATA_SELECTION_TAB_ACTION,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET, namespace, action,
						GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
						GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT));

		selectAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_NAME, "dsTabPrivateViews",
				GtnFrameworkCommonConstants.PRIVATE_VIEWS, "dsTabPublicViews", GtnFrameworkCommonConstants.PUBLIC_VIEWS,
				GtnFrameworkCommonConstants.COMPANY, GtnFrameworkCommonConstants.COMPANY,
				GtnFrameworkCommonConstants.BUSINESS_UNIT, GtnFrameworkCommonConstants.BUSINESS_UNIT,
				"dsTabProjectionName", "dsTabProjectionDescription", GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION,
				"dsTabFromPeriod", GtnFrameworkCommonConstants.FROM_PERIOD, "dsTabToPeriod",
				GtnFrameworkCommonConstants.TO_PERIOD, GtnFrameworkCommonConstants.PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX, GtnFrameworkCommonConstants.FORECAST_LEVEL,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_FILTER_DDLB,
				GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL, GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB,
				GtnFrameworkCommonConstants.PRODUCT_GROUP, GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX,
				GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP));

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add("Selection Criteria ");
		alertParams.add("Not all required fields have been populated. Please try again. ");

		List<GtnUIFrameWorkActionConfig> alertParamsConfig = new ArrayList<>();
		alertParamsConfig.add(alertActionConfig);

		alertActionConfig.setActionParameterList(alertParams);
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);
		validationActionConfig.addActionParameter(alertParamsConfig);
		GtnUIFrameWorkActionConfig tabChangeView = new GtnUIFrameWorkActionConfig();
		tabChangeView.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabChangeView.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		tabChangeView.addActionParameter(GtnFrameworkCommonConstants.RETURNS_PROJECTION_ROOT_LAYOUT);
		gtnUIFrameworkComponentConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig customBusinessValidationAction = new GtnUIFrameWorkActionConfig();
		customBusinessValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customBusinessValidationAction
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_BUSINESS_UNIT_VALIDATION_ACTION);
		customBusinessValidationAction
				.setFieldValues(Arrays.asList("returnsForecastMainScreenDataSelection_businessUnit",
						"returnsForecastMainScreenDataSelection_company"));

		GtnUIFrameWorkActionConfig viewEnableAction = new GtnUIFrameWorkActionConfig();
		Object massUpdateLayout = GtnFrameworkCommonConstants.MASS_UPDATE_CSS_LAYOUT;
		viewEnableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		viewEnableAction.setActionParameterList(
				Arrays.asList(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DATA_SELECTION_TAB_M,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SUBMIT,
						GtnFrameworkCommonConstants.FORECAST_TAB_CSS_LAYOUT, massUpdateLayout));

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_APPROVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_REJECT,
				"returnsForecastTabSheet_dsTabCancel", "returnsForecastTabSheet_dsTabWithdraw" };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.FALSE);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);

		GtnUIFrameWorkActionConfig dualListBoxRightTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxRightTableLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList
				.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_ACTION);
		actionParameterList.add("generate");
		actionParameterList.add(namespace);
		actionParameterList.add(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET);
		dualListBoxRightTableLoadAction.setActionParameterList(actionParameterList);

		GtnUIFrameWorkActionConfig saveDataSelectionPropertiesOnEditAction = new GtnUIFrameWorkActionConfig();
		saveDataSelectionPropertiesOnEditAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveDataSelectionPropertiesOnEditAction.addActionParameter(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_DS_PROPERTIES_SAVE_ON_EDIT_ACTION);
		saveDataSelectionPropertiesOnEditAction
				.addActionParameter(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		saveDataSelectionPropertiesOnEditAction.setFieldValues(Arrays.asList(
				GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_FILTER_DDLB,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB, GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_PROJECTION_GENERATE_BTN));

		GtnUIFrameWorkActionConfig genearateResetTableConfig = new GtnUIFrameWorkActionConfig();
		genearateResetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> genearateResetSelectMap = new HashMap<>();
		List<Object> genearateSelectResetParams = new ArrayList<>();
		genearateResetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROFILE_OPTION_MODE, "Add");
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS,
				"");
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY, null);
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				"");
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS,
				"");
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				null);
		genearateResetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION, "");
		genearateResetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY, "");
		genearateResetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX, null);
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL,
				null);
		genearateResetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRODUCT_GROUP,
				"");
		genearateResetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX, null);

		genearateResetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE, null);
		genearateSelectResetParams.add(genearateResetSelectMap);
		genearateResetTableConfig.setActionParameterList(genearateSelectResetParams);

		gtnUIFrameworkComponentConfigList.add(customBusinessValidationAction);

		gtnUIFrameworkComponentConfigList.add(customPopupActionConfig);

		popupChildActionList.add(gtnUIFrameWorkGenerateActionConfig);

		popupChildActionList.add(selectAction);

		popupChildActionList.add(dualListBoxRightTableLoadAction);

		popupChildActionList.add(tabChangeView);

		popupChildActionList.add(saveDataSelectionPropertiesOnEditAction);

		popupChildActionList.add(viewEnableAction);

		popupChildActionList.add(visibleAction);

		gtnUIFrameworkComponentConfigList.add(genearateResetTableConfig);

		GtnUIFrameWorkActionConfig refreshRightDualListBoxActionConfig = new GtnUIFrameWorkActionConfig();
		Object actionClass = GtnFrameworkReturnsDualListBoxRightTableRefreshAction.class.getName();
		refreshRightDualListBoxActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		refreshRightDualListBoxActionConfig.setActionParameterList(Arrays.asList(actionClass, namespace
				+ GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP));
		gtnUIFrameworkComponentConfigList.add(refreshRightDualListBoxActionConfig);

		generateBtn.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentConfigList);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsSearch");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(controlLayouts.getComponentId());
		searchButton.setAddToParent(Boolean.TRUE);
		searchButton.setEnable(Boolean.FALSE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameWorkActionConfig resetTableActionConfig = new GtnUIFrameWorkActionConfig();

		resetTableActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> reset = new HashMap<>();
		List<Object> searchResetParams = new ArrayList<>();
		reset.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE, null);
		searchResetParams.add(reset);
		resetTableActionConfig.setActionParameterList(searchResetParams);
		actionConfigList.add(resetTableActionConfig);

		GtnUIFrameWorkActionConfig searchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		searchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRODUCT_GROUP,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD));

		GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> searchAlertParams = new ArrayList<>();
		searchAlertParams.add("No Search Criteria ");
		searchAlertParams.add("No search criteria were found. Please enter search criteria and try again.");

		searchAlertActionConfig.setActionParameterList(searchAlertParams);
		searchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(searchAlertActionConfig)));
		actionConfigList.add(searchValidationActionConfig);

		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.FORECAST_LEVEL));
		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		Object msg = "There were no records matching the search criteria.  Please try again.";
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		notificationActionConfig
				.setActionParameterList(
						Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SEARCH_ALERT_ACTION,
								namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
										+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE,
								msg, "No matching Records"));

		actionConfigList.add(notificationActionConfig);

		searchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsReset");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlLayouts.getComponentId());
		resetButton.setAddToParent(true);

		componentList.add(resetButton);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig.addActionParameter("Confirm Reset");
		resetActionConfig.addActionParameter("Are you sure you want to reset the page to default values?");

		List<String> componentIdList = new ArrayList<>();
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_NAME);
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION);
		componentIdList
				.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY);
		componentIdList.add(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT);
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY);
		componentIdList.add(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRODUCT_GROUP);
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX);
		componentIdList.add(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL);
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROFILE_OPTION_MODE);
		componentIdList.add(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		componentIdList.add(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		componentIdList.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX);

		List<Object> componentValueList = new ArrayList<>();
		componentValueList.add("");
		componentValueList.add("");
		componentValueList.add(null);
		componentValueList.add(null);
		componentValueList.add("");
		componentValueList.add("");
		componentValueList.add(null);
		componentValueList.add(null);
		componentValueList.add(null);
		componentValueList.add("Add");
		componentValueList.add("");
		componentValueList.add("");
		componentValueList.add(null);

		resetActionConfig.addActionParameter(componentIdList);
		resetActionConfig.addActionParameter(componentValueList);

		resetActionConfigList.add(resetActionConfig);
		resetButton.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsSaveView");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(controlLayouts.getComponentId());
		saveViewBtn.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();

		GtnUIFrameWorkActionConfig viewValidationActionConfig = new GtnUIFrameWorkActionConfig();
		viewValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		viewValidationActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION));

		GtnUIFrameWorkActionConfig viewAlertActionConfig = new GtnUIFrameWorkActionConfig();
		viewAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> viewAlertParams = new ArrayList<>();
		viewAlertParams.add("No Search Criteria ");
		viewAlertParams.add("No search criteria were found. Please enter search criteria and try again.");

		viewAlertActionConfig.setActionParameterList(viewAlertParams);
		viewValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(viewAlertActionConfig)));
		list.add(viewValidationActionConfig);

		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsSaveViewLookUp");
		conf.addActionParameter("Save view");
		list.add(conf);

		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put("saveViewName", "");
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		list.add(resetTableConfig);

		GtnUIFrameWorkActionConfig viewNameAction = new GtnUIFrameWorkActionConfig();
		viewNameAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewNameAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SAVE_VIEW_ACTION);
		viewNameAction.setFieldValues(Arrays.asList("returnsForecastMainScreenDataSelection_publicViews",
				"returnsForecastMainScreenDataSelection_privateViews", "saveViewUpdate", "saveViewAdd", "saveViewName",
				"saveViewType", "Public", "Private"));

		list.add(viewNameAction);

		saveViewBtn.setGtnUIFrameWorkActionConfigList(list);

		componentList.add(saveViewBtn);

		GtnUIFrameworkComponentConfig deleteViewButton = new GtnUIFrameworkComponentConfig();
		deleteViewButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsDeleteView");
		deleteViewButton.setComponentName("DELETE VIEW");
		deleteViewButton.setParentComponentId(controlLayouts.getComponentId());
		deleteViewButton.setAddToParent(Boolean.TRUE);
		deleteViewButton.setEnable(Boolean.FALSE);
		componentList.add(deleteViewButton);
		List<GtnUIFrameWorkActionConfig> configList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> deleteActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkDeleteActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkDeleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkDeleteActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DELETE_VIEW_ACTION);
		deleteActionList.add(gtnUIFrameWorkDeleteActionConfig);
		gtnUIFrameWorkDeleteActionConfig
				.setFieldValues(Arrays.asList("returnsForecastMainScreenDataSelection_dataSelectionMainLayout"));
		GtnUIFrameWorkActionConfig deleteConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		deleteConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);

		List<Object> deleteConfirmationAlertParams = new ArrayList<>();
		deleteConfirmationAlertParams.add("Confirm Deletion");
		deleteConfirmationAlertParams.add("Are you sure you want to delete record?");
		deleteConfirmationAlertParams.add(deleteActionList);

		deleteConfirmationActionConfig.setActionParameterList(deleteConfirmationAlertParams);

		configList.add(deleteConfirmationActionConfig);

		deleteViewButton.setGtnUIFrameWorkActionConfigList(configList);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DATA_SELECTION_MAIN_LAYOUT);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		searchCriteriaPanel.setSpacing(true);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList, namespace);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");
		searchCriteriaPanel.setGtnLayoutConfig(conf);
		searchCriteriaPanel
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultsPanel");
		searchCriteriaPanel.setAddToParent(true);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setSpacing(true);
		componentList.add(searchCriteriaPanel);
		addPagedTableComponent(componentList, namespace);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setPagedTableLogicClassName(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_DATA_SELECTION_SEARCH_ACTION);
		searchResultConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setSpacing(true);
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(Boolean.FALSE);
		searchResults.setFilterBar(Boolean.TRUE);
		searchResults.setSelectable(Boolean.FALSE);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSelectable(Boolean.TRUE);

		searchResults.setTableColumnDataType(
				GtnForecastReturnsTableConstants.getGtnReturnsForecastSerachTableColumnsDataType());
		searchResults.setTableVisibleHeader(GtnForecastReturnsTableConstants.getGtnReturnsForecastSerachTableHeaders());
		searchResults
				.setTableColumnMappingId(GtnForecastReturnsTableConstants.getGtnReturnsForecastSerachTableColumns());
		searchResults.setCountUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_SEARCH_SERVICE);
		searchResults.setResultSetUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_SEARCH_SERVICE);
		searchResults.setTableDateColumnObject(new String[] { "createdDate", "lastModifiedDate" });
		searchResults.setTableDateColumnFormat(new String[] { "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy HH:mm:ss" });
		searchResults.setModuleName("forecastingReturns");
		searchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addProjectionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();
		GtnUIFrameworkComponentConfig controlMainLayouts = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "projectionControlButtonLayout",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.DATA_SELECTION_MAIN_LAYOUT);
		controlMainLayouts.setSpacing(Boolean.TRUE);
		controlMainLayouts.setMargin(Boolean.TRUE);
		componentList.add(controlMainLayouts);

		GtnUIFrameworkComponentConfig controlLayouts = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsCrudLayout",
				controlMainLayouts.getComponentId());
		controlLayouts.setSpacing(Boolean.TRUE);
		componentList.add(controlLayouts);

		GtnUIFrameworkComponentConfig crudResetButton = new GtnUIFrameworkComponentConfig();
		crudResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudResetButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "crudReset");
		crudResetButton.setComponentName("RESET");
		crudResetButton.setParentComponentId(controlLayouts.getComponentId());
		crudResetButton.setAddToParent(Boolean.TRUE);
		crudResetButton.setEnable(Boolean.FALSE);

		componentList.add(crudResetButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		String resetValue = null;
		GtnUIFrameWorkActionConfig resetTableActionConfig = new GtnUIFrameWorkActionConfig();
		resetTableActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetTableActionConfig.addActionParameter("Confirm Reset");
		resetTableActionConfig.addActionParameter("Are you sure you want to reset the page to default values?");
		resetTableActionConfig.addActionParameter(Arrays.asList(namespace
				+ GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE));
		resetTableActionConfig.addActionParameter(Arrays.asList(resetValue));
		actionConfigList.add(resetTableActionConfig);

		crudResetButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig crudEditBtn = new GtnUIFrameworkComponentConfig();
		crudEditBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudEditBtn.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "crudEdit");
		crudEditBtn.setComponentName("EDIT");
		crudEditBtn.setParentComponentId(controlLayouts.getComponentId());
		crudEditBtn.setAddToParent(Boolean.TRUE);
		crudEditBtn.setEnable(Boolean.FALSE);

		componentList.add(crudEditBtn);

		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentConfigList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> popupChildActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE);
		alertParams.add(GtnFrameworkCommonConstants.SELECT_RECORD);
		alertParams.add(GtnFrameworkCommonConstants.NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);

		alertActionConfig.setActionParameterList(alertParams);

		gtnUIFrameworkComponentConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig customPopupActionConfig = new GtnUIFrameWorkActionConfig();
		customPopupActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customPopupActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_POPUP_CHANGE_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE,
						namespace, popupChildActionList, GtnFrameworkForecastConstantCommon.EDIT_MODE));

		GtnUIFrameWorkActionConfig gtnUIFrameWorkEditActionConfig = new GtnUIFrameWorkActionConfig();
		Object mode = GtnFrameworkForecastConstantCommon.EDIT_MODE;
		gtnUIFrameworkComponentConfigList.add(customPopupActionConfig);

		gtnUIFrameWorkEditActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkEditActionConfig
				.setActionParameterList(Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_EDIT_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE,
						GtnFrameworkCommonConstants.RETURNS, mode, "Product"));
		gtnUIFrameWorkEditActionConfig.setFieldValues(Arrays.asList(
				GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_FILTER_DDLB,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB, GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_PROJECTION_GENERATE_BTN));
		popupChildActionList.add(gtnUIFrameWorkEditActionConfig);
		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		Object privateView = "returnsForecastTabSheet_dsTabPrivateViews";
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.setActionParameterList(Arrays.asList("returnsForecastTabSheet_dsTabProfileOptionMode",
				"returnsForecastTabSheet_dsTabPublicViews", privateView));
		popupChildActionList.add(disableAction);

		GtnUIFrameWorkActionConfig viewEnableAction = new GtnUIFrameWorkActionConfig();
		Object forecastCssLayout = "forecastTabCssLayout";
		viewEnableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		viewEnableAction.setActionParameterList(
				Arrays.asList(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DATA_SELECTION_TAB_M,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
						"returnsForecastTabSheet_dsTabSubmit", forecastCssLayout,
						GtnFrameworkCommonConstants.MASS_UPDATE_CSS_LAYOUT));
		popupChildActionList.add(viewEnableAction);

		GtnUIFrameWorkActionConfig tabChange = new GtnUIFrameWorkActionConfig();
		tabChange.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabChange.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		tabChange.addActionParameter(GtnFrameworkCommonConstants.RETURNS_PROJECTION_ROOT_LAYOUT);
		popupChildActionList.add(tabChange);

		GtnUIFrameWorkActionConfig dualListBoxRightTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxRightTableLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList
				.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_ACTION);
		actionParameterList.add("edit");
		actionParameterList.add(namespace);
		actionParameterList.add(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET);
		dualListBoxRightTableLoadAction.setActionParameterList(actionParameterList);
		popupChildActionList.add(dualListBoxRightTableLoadAction);

		GtnUIFrameWorkActionConfig saveDataSelectionPropertiesOnEditAction = new GtnUIFrameWorkActionConfig();
		saveDataSelectionPropertiesOnEditAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveDataSelectionPropertiesOnEditAction.addActionParameter(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_DS_PROPERTIES_SAVE_ON_EDIT_ACTION);
		saveDataSelectionPropertiesOnEditAction
				.addActionParameter(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		saveDataSelectionPropertiesOnEditAction.setFieldValues(Arrays.asList(
				GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_FILTER_DDLB,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB, GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_PROJECTION_GENERATE_BTN));
		popupChildActionList.add(saveDataSelectionPropertiesOnEditAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_APPROVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_REJECT,
				"returnsForecastTabSheet_dsTabCancel", "returnsForecastTabSheet_dsTabWithdraw" };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.FALSE);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		popupChildActionList.add(visibleAction);

		crudEditBtn.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentConfigList);

		GtnUIFrameworkComponentConfig crudViewButton = new GtnUIFrameworkComponentConfig();
		crudViewButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudViewButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "crudView");
		crudViewButton.setComponentName("VIEW");
		crudViewButton.setParentComponentId(controlLayouts.getComponentId());
		crudViewButton.setAddToParent(Boolean.TRUE);
		crudViewButton.setEnable(Boolean.FALSE);

		componentList.add(crudViewButton);

		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentViewConfigList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> popupViewChildActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig viewAlertActionConfig = new GtnUIFrameWorkActionConfig();
		viewAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> viewAlertParams = new ArrayList<>();
		viewAlertParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE);
		viewAlertParams.add(GtnFrameworkCommonConstants.SELECT_RECORD);
		viewAlertParams.add(GtnFrameworkCommonConstants.NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);

		viewAlertActionConfig.setActionParameterList(viewAlertParams);

		gtnUIFrameworkComponentViewConfigList.add(viewAlertActionConfig);

		GtnUIFrameWorkActionConfig viewCustomPopupActionConfig = new GtnUIFrameWorkActionConfig();
		viewCustomPopupActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewCustomPopupActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_POPUP_CHANGE_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE,
						namespace, popupViewChildActionList, GtnFrameworkForecastConstantCommon.VIEW_MODE));

		viewCustomPopupActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_NAME));

		GtnUIFrameWorkActionConfig gtnUIFrameWorkViewActionConfig = new GtnUIFrameWorkActionConfig();
		Object moduleName = GtnFrameworkCommonConstants.RETURNS;
		gtnUIFrameworkComponentViewConfigList.add(viewCustomPopupActionConfig);

		gtnUIFrameWorkViewActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkViewActionConfig
				.setActionParameterList(Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_EDIT_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE,
						moduleName, GtnFrameworkForecastConstantCommon.VIEW_MODE, "Product"));
		gtnUIFrameWorkViewActionConfig.setFieldValues(Arrays.asList(
				GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_FILTER_DDLB,
				GtnFrameworkCommonConstants.FORECAST_RETURNSLEVEL_DDLB, GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_PROJECTION_GENERATE_BTN));

		popupViewChildActionList.add(gtnUIFrameWorkViewActionConfig);
		GtnUIFrameWorkActionConfig viewDisableAction = new GtnUIFrameWorkActionConfig();
		Object massUpdateLayout = GtnFrameworkCommonConstants.MASS_UPDATE_CSS_LAYOUT;
		viewDisableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		viewDisableAction.setActionParameterList(
				Arrays.asList(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DATA_SELECTION_TAB_M,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SUBMIT,
						GtnFrameworkCommonConstants.FORECAST_TAB_CSS_LAYOUT, massUpdateLayout));
		popupViewChildActionList.add(viewDisableAction);

		GtnUIFrameWorkActionConfig viewVisibleAction = new GtnUIFrameWorkActionConfig();
		viewVisibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] viewVisibleFields = new String[] {
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_APPROVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_REJECT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_CANCEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_WITHDRAW };

		List<Object> viewVisibleParameters = new ArrayList<>();
		viewVisibleParameters.add(Boolean.FALSE);
		viewVisibleParameters.add(Arrays.asList(viewVisibleFields));
		viewVisibleAction.setActionParameterList(viewVisibleParameters);
		popupViewChildActionList.add(viewVisibleAction);

		GtnUIFrameWorkActionConfig tabChangeView = new GtnUIFrameWorkActionConfig();
		tabChangeView.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabChangeView.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		tabChangeView.addActionParameter(GtnFrameworkCommonConstants.RETURNS_PROJECTION_ROOT_LAYOUT);
		popupViewChildActionList.add(tabChangeView);

		GtnUIFrameWorkActionConfig dualListBoxRightTableLoadActionOnView = new GtnUIFrameWorkActionConfig();
		dualListBoxRightTableLoadActionOnView.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameterListView = new ArrayList<>();
		actionParameterListView
				.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_ACTION);
		actionParameterListView.add("view");
		actionParameterListView.add(namespace);
		actionParameterListView.add(GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET);
		dualListBoxRightTableLoadActionOnView.setActionParameterList(actionParameterListView);
		popupViewChildActionList.add(dualListBoxRightTableLoadActionOnView);

		crudViewButton.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentViewConfigList);

		GtnUIFrameworkComponentConfig crudDeleteBtn = new GtnUIFrameworkComponentConfig();
		crudDeleteBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudDeleteBtn.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "crudDelete");
		crudDeleteBtn.setComponentName("DELETE");
		crudDeleteBtn.setParentComponentId(controlLayouts.getComponentId());
		crudDeleteBtn.setAddToParent(Boolean.TRUE);
		crudDeleteBtn.setEnable(Boolean.FALSE);

		componentList.add(crudDeleteBtn);
		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentDeleteConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteAlertActionConfig = new GtnUIFrameWorkActionConfig();
		deleteAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> deleteAlertParams = new ArrayList<>();
		deleteAlertParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE);
		deleteAlertParams.add(GtnFrameworkCommonConstants.SELECT_RECORD);
		deleteAlertParams.add(GtnFrameworkCommonConstants.NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);

		deleteAlertActionConfig.setActionParameterList(deleteAlertParams);

		gtnUIFrameworkComponentDeleteConfigList.add(deleteAlertActionConfig);

		List<GtnUIFrameWorkActionConfig> configList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkDeleteActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();

		resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> deleteResetMap = new HashMap<>();
		List<Object> deleteResetParams = new ArrayList<>();
		deleteResetMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE, null);
		deleteResetParams.add(deleteResetMap);
		resetActionConfig.setActionParameterList(deleteResetParams);

		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.FORECAST_LEVEL));

		gtnUIFrameWorkDeleteActionConfig.setActionType(GtnUIFrameworkActionType.DELETE_ACTION);
		gtnUIFrameWorkDeleteActionConfig.setActionParameterList(Arrays
				.asList(new Object[] { GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_DELETE_SERVICE,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE,
						"", true, 17 }));
		configList.add(gtnUIFrameWorkDeleteActionConfig);
		configList.add(resetActionConfig);
		configList.add(loadDataTableActionConfig);
		GtnUIFrameWorkActionConfig deleteConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		deleteConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);

		List<Object> deleteConfirmationAlertParams = new ArrayList<>();
		deleteConfirmationAlertParams.add("Confirm Deletion");
		deleteConfirmationAlertParams.add("Are you sure you want to delete record?");

		deleteConfirmationAlertParams.add(configList);

		deleteConfirmationActionConfig.setActionParameterList(deleteConfirmationAlertParams);

		gtnUIFrameworkComponentDeleteConfigList.add(deleteConfirmationActionConfig);

		crudDeleteBtn.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentDeleteConfigList);

	}

	public void addProfileModeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace,
			GtnFrameworkForecastReturnDataSelectionConfig gtnFrameworkForecastReturnDataSelectionConfig) {
		String compId = namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROFILE_OPTION_MODE;
		GtnUIFrameworkComponentConfig hLConfig = gtnFrameworkForecastReturnDataSelectionConfig.layoutsConfig
				.getHorizontalLayoutConfig(compId,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "modeSelectionLayout");
		componentList.add(hLConfig);
		GtnUIFrameworkComponentConfig addModeTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addModeTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addModeTypeOptionGroup.setComponentId(compId);
		addModeTypeOptionGroup.setComponentName("Mode");
		addModeTypeOptionGroup.setAddToParent(true);
		addModeTypeOptionGroup.setParentComponentId(compId + GtnFrameworkCssConstants.HORIZONTAL);
		GtnUIFrameworkOptionGroupConfig gtnUIFrameworkOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		gtnUIFrameworkOptionGroupConfig.setItemValues(Arrays.asList("Add", "Search"));
		gtnUIFrameworkOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		addModeTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		addModeTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(gtnUIFrameworkOptionGroupConfig);
		List<GtnUIFrameWorkActionConfig> gtnUiFramwworkActionConfigList = new ArrayList<>();
		Object generateBtn = "dsGenerate";
		GtnUIFrameWorkActionConfig gtnUiFramwworkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUiFramwworkActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUiFramwworkActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DATA_SELECTION_RESET_ACTION, namespace,
						GtnFrameworkCommonConstants.PROFILE_OPTION_MODE, GtnFrameworkCommonConstants.FROM_PERIOD,
						GtnFrameworkCommonConstants.TO_PERIOD, generateBtn, "dsSearch", "dsReset", "dsSaveView",
						"dsDeleteView", "crudReset", "crudEdit", "crudView", "crudDelete"));
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS,
				"");
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY, null);
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PROJECTION_NAME,
				"");
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS,
				"");
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT,
				null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_DESCRIPTION, "");
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY, "");
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX, null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + FORECAST_LEVEL, null);
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRODUCT_GROUP,
				"");
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX, null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP, null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DSSEARCH_RESULT_TABLE, null);
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		gtnUiFramwworkActionConfigList.add(gtnUiFramwworkActionConfig);
		gtnUiFramwworkActionConfigList.add(resetTableConfig);
		addModeTypeOptionGroup.setGtnUIFrameWorkActionConfigList(gtnUiFramwworkActionConfigList);
		componentList.add(addModeTypeOptionGroup);
	}
}
