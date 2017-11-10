package com.stpl.gtn.gtn2o.ui.module.commercial.config.dataselection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingDataSelectionConfig {

	private final GtnFrameworkCommercialForecastingLayoutsConfig layoutsConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public GtnUIFrameworkViewConfig getSearchView() throws GtnFrameworkGeneralException {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Search View");
		view.setViewId("V001");
		view.setDefaultView(true);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) throws GtnFrameworkGeneralException {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		configureMainLayouts(componentList);
	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig dataSelectionMainLayout = new GtnUIFrameworkLayoutConfig();
		dataSelectionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig dataSelectionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		dataSelectionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataSelectionMainLayoutConfig.setComponentId("dataSelectionMainLayout");
		dataSelectionMainLayoutConfig.setAddToParent(Boolean.FALSE);
		dataSelectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dataSelectionMainLayoutConfig.setGtnLayoutConfig(dataSelectionMainLayout);
		componentList.add(dataSelectionMainLayoutConfig);

		addProjectionOptionPanel(componentList, dataSelectionMainLayoutConfig.getComponentId());
		addProfileModeOptionGroup(componentList);

		addCustomerSelectionLayout(componentList, dataSelectionMainLayoutConfig.getComponentId());
		addProductSelectionLayout(componentList, dataSelectionMainLayoutConfig.getComponentId());

		addControlButtonLayout(componentList);
		addResultsPanel(componentList, dataSelectionMainLayoutConfig.getComponentId());
		addProjectionButtonLayout(componentList);
	}

	private void addProjectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig selectionCriteriaPanel = new GtnUIFrameworkComponentConfig();
		selectionCriteriaPanel.setComponentName("Projection Options");
		selectionCriteriaPanel.setComponentId("projectionOptions");
		selectionCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		selectionCriteriaPanel.setParentComponentId(parentComponentId);
		selectionCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		selectionCriteriaPanel.setAddToParent(Boolean.TRUE);
		selectionCriteriaPanel.setSpacing(true);
		componentList.add(selectionCriteriaPanel);

		GtnUIFrameworkLayoutConfig projectionOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionMainLayoutConfig.setComponentId("projectionOptionMainLayout");
		projectionOptionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setParentComponentId("projectionOptions");
		projectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionMainLayoutConfig.setGtnLayoutConfig(projectionOptionMainLayout);
		componentList.add(projectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId("projectionOptionInnerLayout");
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(projectionOptionMainLayoutConfig.getComponentId());
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeLayoutConfig.setComponentId("modeSelectionLayout");
		modeLayoutConfig.setAddToParent(Boolean.TRUE);
		modeLayoutConfig.setGtnLayoutConfig(modeSelectionlayout);
		modeLayoutConfig.setParentComponentId(projectionOptionInnerLayoutConfig.getComponentId());
		modeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkLayoutConfig fromToMainLayout = new GtnUIFrameworkLayoutConfig();
		fromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setComponentId("fromToMainLayout");
		fromToLayoutConfig.setAddToParent(Boolean.TRUE);
		fromToLayoutConfig.setSpacing(Boolean.TRUE);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(projectionOptionMainLayoutConfig.getComponentId());
		fromToLayoutConfig.setGtnLayoutConfig(fromToMainLayout);
		componentList.add(fromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setComponentId("projectionSelectionlayout1");
		privateViewCompanyProjectionNameLayout.setAddToParent(Boolean.TRUE);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCompanyProjectionNameLayoutConfig);
		privateViewCompanyProjectionNameLayout.setParentComponentId(projectionOptionInnerLayoutConfig.getComponentId());
		privateViewCompanyProjectionNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(privateViewCompanyProjectionNameLayout);

		addPrivateViewCompanyProjectionName(componentList, privateViewCompanyProjectionNameLayout.getComponentId());
		addPublicViewBusinessUnitDescription(componentList, privateViewCompanyProjectionNameLayout.getComponentId());
		addTimePeriod(componentList, fromToLayoutConfig.getComponentId());

	}

	private void addProfileModeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList) {

		String compId = "profileOptionMode";
		GtnUIFrameworkComponentConfig hLConfig = layoutsConfig.getHorizontalLayoutConfig(compId, "modeSelectionLayout");
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig addModeTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addModeTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addModeTypeOptionGroup.setComponentId(compId);
		addModeTypeOptionGroup.setComponentName("Mode");
		addModeTypeOptionGroup.setAddToParent(true);
		addModeTypeOptionGroup.setParentComponentId(compId + GtnFrameworkCssConstants.HORIZONTAL);

		GtnUIFrameworkOptionGroupConfig gtnUIFrameworkOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		gtnUIFrameworkOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Add", "Search" }));
		gtnUIFrameworkOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		addModeTypeOptionGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		addModeTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(gtnUIFrameworkOptionGroupConfig);

		componentList.add(addModeTypeOptionGroup);
	}

	private void addPrivateViewCompanyProjectionName(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkLayoutConfig privateViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig privateViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		privateViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewsLayoutConfig.setComponentId("privateViewLayout");
		privateViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		privateViewsLayoutConfig.setSpacing(Boolean.TRUE);
		privateViewsLayoutConfig.setParentComponentId(parentComponentId);
		privateViewsLayoutConfig.setGtnLayoutConfig(privateViewsLayout);
		componentList.add(privateViewsLayoutConfig);

		GtnUIFrameworkComponentConfig privateView = new GtnUIFrameworkComponentConfig();
		privateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateView.setComponentId("privateViews");
		privateView.setComponentName("Private Views");
		privateView.setAddToParent(Boolean.TRUE);
		privateView.setParentComponentId(privateViewsLayoutConfig.getComponentId());
		privateView.setComponentWsFieldId("privateViews");

		List<GtnUIFrameWorkActionConfig> privateViewActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig privateViewActionConfig = new GtnUIFrameWorkActionConfig();
		privateViewActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		privateViewActionConfig.setActionParameterList(Arrays.asList(new Object[] { "privateViewSearchLookupView",
				"Private View", GtnFrameworkCssConstants.SEVEN_NINE_FIVE, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		privateViewActionConfigList.add(privateViewActionConfig);
		privateView.setGtnUIFrameWorkActionConfigList(privateViewActionConfigList);
		componentList.add(privateView);

		GtnUIFrameworkLayoutConfig companyLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig companyLayoutConfig = new GtnUIFrameworkComponentConfig();
		companyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		companyLayoutConfig.setComponentId("companyLayout");
		companyLayoutConfig.setAddToParent(Boolean.TRUE);
		companyLayoutConfig.setSpacing(Boolean.TRUE);
		companyLayoutConfig.setParentComponentId(parentComponentId);
		companyLayoutConfig.setGtnLayoutConfig(companyLayout);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		company.setComponentId("company");
		company.setComponentName("Company");
		company.setAddToParent(Boolean.TRUE);
		company.setParentComponentId(companyLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP);
		company.setGtnComboboxConfig(companyTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnFrameworkCommercialForecastingProductHierarchyConfig productSelection = new GtnFrameworkCommercialForecastingProductHierarchyConfig();
		actionConfigList.add(productSelection.addDualListBoxCustomAction(Arrays.asList(new String[] { "company" })));
		company.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(company);

		GtnUIFrameworkLayoutConfig projectionNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionNameLayoutConfig.setComponentId("projectionNameLayout");
		projectionNameLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionNameLayoutConfig.setSpacing(Boolean.TRUE);
		projectionNameLayoutConfig.setParentComponentId(parentComponentId);
		projectionNameLayoutConfig.setGtnLayoutConfig(projectionNameLayout);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionName.setComponentId("projectionName");
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(projectionNameLayoutConfig.getComponentId());

		componentList.add(projectionName);
	}

	private void addPublicViewBusinessUnitDescription(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkLayoutConfig publicViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId("publicViewLayout");
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setParentComponentId(parentComponentId);
		publicViewsLayoutConfig.setGtnLayoutConfig(publicViewsLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig publicView = new GtnUIFrameworkComponentConfig();
		publicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicView.setComponentId("publicViews");
		publicView.setComponentName("Public Views");
		publicView.setAddToParent(Boolean.TRUE);
		publicView.setParentComponentId(publicViewsLayoutConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> publicViewLookupConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig publicViewLookupConfig = new GtnUIFrameWorkActionConfig();
		publicViewLookupConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		publicViewLookupConfig.setActionParameterList(Arrays.asList(new Object[] { "publicViewSearchLookupView",
				"Public View", GtnFrameworkCssConstants.SEVEN_NINE_FIVE, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		publicViewLookupConfigList.add(publicViewLookupConfig);

		publicView.setGtnUIFrameWorkActionConfigList(publicViewLookupConfigList);

		componentList.add(publicView);

		GtnUIFrameworkLayoutConfig businessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig.setComponentId("businessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(Boolean.TRUE);
		businessUnitLayoutConfig.setSpacing(Boolean.TRUE);
		businessUnitLayoutConfig.setParentComponentId(parentComponentId);
		businessUnitLayoutConfig.setGtnLayoutConfig(businessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		businessUnit.setComponentId("businessUnit");
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(Boolean.TRUE);
		businessUnit.setParentComponentId(businessUnitLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.BUSINESS_UNIT_GLCOMP);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnFrameworkCommercialForecastingProductHierarchyConfig productSelection = new GtnFrameworkCommercialForecastingProductHierarchyConfig();
		actionConfigList
				.add(productSelection.addDualListBoxCustomAction(Arrays.asList(new String[] { "businessUnit" })));
		businessUnit.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(businessUnit);

		GtnUIFrameworkLayoutConfig projectionDescLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionDescLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionDescLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionDescLayoutConfig.setComponentId("projectionDescLayout");
		projectionDescLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionDescLayoutConfig.setSpacing(Boolean.TRUE);
		projectionDescLayoutConfig.setParentComponentId(parentComponentId);
		projectionDescLayoutConfig.setGtnLayoutConfig(projectionDescLayout);
		componentList.add(projectionDescLayoutConfig);

		GtnUIFrameworkComponentConfig projectionDescription = new GtnUIFrameworkComponentConfig();
		projectionDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionDescription.setComponentId("projectionDescription");
		projectionDescription.setComponentName("Projection Description");
		projectionDescription.setAddToParent(Boolean.TRUE);
		projectionDescription.setParentComponentId(projectionDescLayoutConfig.getComponentId());

		componentList.add(projectionDescription);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId("timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(Boolean.TRUE);
		panel.setParentComponentId(parentComponentId);
		panel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromAndToperiodLayoutConfig.setComponentId("timePeriodInnerLayout");
		fromAndToperiodLayoutConfig.setSpacing(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromAndToperiodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		fromAndToperiodLayoutConfig.setParentComponentId(panel.getComponentId());
		fromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(fromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig fromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		fromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromPeriodLayoutConfig.setComponentId("fromPeriodLayout");
		fromPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromPeriodLayoutConfig.setGtnLayoutConfig(fromPeriodLayout);
		fromPeriodLayoutConfig.setParentComponentId(fromAndToperiodLayoutConfig.getComponentId());
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		fromPeriod.setComponentId("fromPeriod");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);
		fromPeriod.setParentComponentId(fromPeriodLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE);
		fromPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);
		fromPeriodConfig.setHasDefaultValue(true);
		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig.setComponentId("toPeriodLayout");
		toPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		toPeriodLayoutConfig.setParentComponentId(fromAndToperiodLayoutConfig.getComponentId());
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		toPeriod.setComponentId("toPeriod");
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(Boolean.TRUE);
		toPeriod.setEnable(Boolean.FALSE);
		toPeriod.setParentComponentId(toPeriodLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig toPeriodTypeConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_TO_DATE);
		toPeriodTypeConfig.setHasDefaultValue(true);
		toPeriodTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		toPeriod.setGtnComboboxConfig(toPeriodTypeConfig);
		componentList.add(toPeriod);
	}

	private void addCustomerSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig customerSelectionPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionPanel.setComponentName("Customer Selection");
		customerSelectionPanel.setComponentId("customerSelectionPanel");
		customerSelectionPanel.setParentComponentId(parentComponentId);
		customerSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		customerSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionPanel.setSpacing(true);
		customerSelectionPanel.setAddToParent(Boolean.TRUE);
		componentList.add(customerSelectionPanel);

		GtnFrameworkCommercialForecastingCustomerHierarchyConfig customerHierarchy = new GtnFrameworkCommercialForecastingCustomerHierarchyConfig();
		componentList.addAll(
				customerHierarchy.getCustomerSelectionLayoutComponents(customerSelectionPanel.getComponentId()));
	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		productSelectionPanel.setComponentId("productSelectionPanel");
		productSelectionPanel.setParentComponentId(parentComponentId);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionPanel.setSpacing(true);
		productSelectionPanel.setAddToParent(Boolean.TRUE);
		componentList.add(productSelectionPanel);

		GtnFrameworkCommercialForecastingProductHierarchyConfig productHierarchy = new GtnFrameworkCommercialForecastingProductHierarchyConfig();
		componentList
				.addAll(productHierarchy.getProductSelectionLayoutComponents(productSelectionPanel.getComponentId()));

	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig controlMainLayouts = layoutConfig.getHorizontalLayoutConfig("dsControlMainLayout",
				"dataSelectionMainLayout");
		controlMainLayouts.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		controlMainLayouts.setSpacing(true);
		controlMainLayouts.setMargin(true);
		componentList.add(controlMainLayouts);

		GtnUIFrameworkComponentConfig controlLayouts = layoutConfig.getHorizontalLayoutConfig("dsControlLayout",
				controlMainLayouts.getComponentId());
		componentList.add(controlLayouts);

		GtnUIFrameworkComponentConfig generateBtn = new GtnUIFrameworkComponentConfig();
		generateBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateBtn.setComponentId("dsGenerate");
		generateBtn.setComponentName("GENERATE");
		generateBtn.setParentComponentId(controlLayouts.getComponentId());
		generateBtn.setAddToParent(true);
		componentList.add(generateBtn);
		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkGeneratePopupAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGeneratePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		gtnUIFrameWorkGeneratePopupAction.setActionParameterList(
				Arrays.asList(new Object[] { "gtnCommercialForecastingProjectionDetailsPopup", "Projection Name",
						GtnFrameworkCssConstants.HUNDRED_PERCENTAGE, GtnFrameworkCssConstants.EIGHTY_PERCENTAGE }));

		gtnUIFrameworkComponentConfigList.add(gtnUIFrameWorkGeneratePopupAction);
		generateBtn.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentConfigList);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId("dsSearch");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(controlLayouts.getComponentId());
		searchButton.setAddToParent(Boolean.TRUE);
		searchButton.setEnable(Boolean.FALSE);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("dsReset");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlLayouts.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);

		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId("dsSaveView");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(controlLayouts.getComponentId());
		saveViewBtn.setAddToParent(true);
		componentList.add(saveViewBtn);

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList(new Object[] { "dsSaveViewLookUp", "Save view",
				GtnFrameworkCssConstants.FOUR_HUNDEREND, GtnFrameworkCssConstants.TWO_FOUR_FIVE }));
		list.add(conf);
		saveViewBtn.setGtnUIFrameWorkActionConfigList(list);

		GtnUIFrameworkComponentConfig deleteViewButton = new GtnUIFrameworkComponentConfig();
		deleteViewButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewButton.setComponentId("dsDeleteView");
		deleteViewButton.setComponentName("DELETE VIEW");
		deleteViewButton.setParentComponentId(controlLayouts.getComponentId());
		deleteViewButton.setAddToParent(Boolean.TRUE);
		deleteViewButton.setEnable(Boolean.FALSE);
		componentList.add(deleteViewButton);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("resultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		searchCriteriaPanel.setSpacing(true);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList, searchCriteriaPanel.getComponentId());

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId("resultLayout");
		searchCriteriaPanel.setGtnLayoutConfig(conf);
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setAddToParent(true);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setSpacing(true);
		componentList.add(searchCriteriaPanel);
		addPagedTableComponent(componentList, searchCriteriaPanel.getComponentId());
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("dssearchResultTable");
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId(parentComponentId);
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
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_SERACH_TABLE_COLUMNS_DATA_TYPE);
		searchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_SERACH_TABLE_HERDERS);
		searchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECAST_SERACH_TABLE_COLUMNS);

		searchResults.setTableDateColumnObject(new String[] { "createdDate", "lastModifiedDate" });
		searchResults.setTableDateColumnFormat(new String[] { "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy HH:mm:ss" });
		searchResults.setModuleName("commercialForecasting");
		searchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addProjectionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

		GtnUIFrameworkComponentConfig controlMainLayouts = layoutConfig
				.getHorizontalLayoutConfig("projectionControlButtonLayout", "dataSelectionMainLayout");
		controlMainLayouts.setSpacing(Boolean.TRUE);
		controlMainLayouts.setMargin(Boolean.TRUE);
		componentList.add(controlMainLayouts);

		GtnUIFrameworkComponentConfig controlLayouts = layoutConfig.getHorizontalLayoutConfig("dsCrudLayout",
				controlMainLayouts.getComponentId());
		controlLayouts.setSpacing(Boolean.TRUE);
		componentList.add(controlLayouts);

		GtnUIFrameworkComponentConfig crudResetButton = new GtnUIFrameworkComponentConfig();
		crudResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudResetButton.setComponentId("crudReset");
		crudResetButton.setComponentName("RESET");
		crudResetButton.setParentComponentId(controlLayouts.getComponentId());
		crudResetButton.setAddToParent(Boolean.TRUE);
		crudResetButton.setEnable(Boolean.FALSE);
		componentList.add(crudResetButton);

		GtnUIFrameworkComponentConfig crudEditBtn = new GtnUIFrameworkComponentConfig();
		crudEditBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudEditBtn.setComponentId("crudEdit");
		crudEditBtn.setComponentName("EDIT");
		crudEditBtn.setParentComponentId(controlLayouts.getComponentId());
		crudEditBtn.setAddToParent(Boolean.TRUE);
		crudEditBtn.setEnable(Boolean.FALSE);
		componentList.add(crudEditBtn);

		GtnUIFrameworkComponentConfig crudViewButton = new GtnUIFrameworkComponentConfig();
		crudViewButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudViewButton.setComponentId("crudView");
		crudViewButton.setComponentName("VIEW");
		crudViewButton.setParentComponentId(controlLayouts.getComponentId());
		crudViewButton.setAddToParent(Boolean.TRUE);
		crudViewButton.setEnable(Boolean.FALSE);
		componentList.add(crudViewButton);

		GtnUIFrameworkComponentConfig crudDeleteBtn = new GtnUIFrameworkComponentConfig();
		crudDeleteBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		crudDeleteBtn.setComponentId("crudDelete");
		crudDeleteBtn.setComponentName("DELETE");
		crudDeleteBtn.setParentComponentId(controlLayouts.getComponentId());
		crudDeleteBtn.setAddToParent(Boolean.TRUE);
		crudDeleteBtn.setEnable(Boolean.FALSE);
		componentList.add(crudDeleteBtn);

	}
}
