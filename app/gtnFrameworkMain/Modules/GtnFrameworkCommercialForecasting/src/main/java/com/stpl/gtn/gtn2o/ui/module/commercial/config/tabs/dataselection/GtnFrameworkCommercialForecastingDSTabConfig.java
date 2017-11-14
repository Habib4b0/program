/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.dataselection;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingDSTabConfig {

	private final GtnFrameworkCommercialForecastingLayoutsConfig layoutsConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public void addDataSelectionTab(List<GtnUIFrameworkComponentConfig> componentList)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutsConfig.getRootLayoutConfig("commercialForecastingDataSelectionRootLayout",
				GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(rootLayoutConfig);

		configureMainLayouts(componentList, rootLayoutConfig.getComponentId());
	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList, String parentId)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkLayoutConfig dataSelectionMainLayout = new GtnUIFrameworkLayoutConfig();
		dataSelectionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig dataSelectionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		dataSelectionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataSelectionMainLayoutConfig.setComponentId("dataSelectionTabMainLayout");
		dataSelectionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		dataSelectionMainLayoutConfig.setParentComponentId(parentId);
		dataSelectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dataSelectionMainLayoutConfig.setGtnLayoutConfig(dataSelectionMainLayout);
		componentList.add(dataSelectionMainLayoutConfig);
		addComponentList(componentList, dataSelectionMainLayoutConfig.getComponentId());

	}

	private void addComponentList(List<GtnUIFrameworkComponentConfig> componentList, String parentId)
			throws GtnFrameworkGeneralException {

		addProjectionOptionPanel(componentList, parentId);
		addProfileModeOptionGroup(componentList);
		addPrivateViewCompanyProjectionName(componentList);
		addPublicViewBusinessUnitDescription(componentList);
		addTimePeriod(componentList);
		addCustomerSelectionLayout(componentList, parentId);
		addProductSelectionLayout(componentList, parentId);

		GtnFrameworkCommercialForecastingProdHierarchyTabConfig productHierarchy = new GtnFrameworkCommercialForecastingProdHierarchyTabConfig();
		componentList.addAll(productHierarchy.getProductSelectionLayoutComponents());

		GtnFrameworkCommercialForecastingCustHierarchyTabConfig customerHierarchy = new GtnFrameworkCommercialForecastingCustHierarchyTabConfig();

		componentList.addAll(customerHierarchy.getCustomerSelectionLayoutComponents());

	}

	private void addProjectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig selectionCriteriaPanel = new GtnUIFrameworkComponentConfig();
		selectionCriteriaPanel.setComponentName("Projection Options");
		selectionCriteriaPanel.setComponentId("dsTabProjectionOptions");
		selectionCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		selectionCriteriaPanel.setParentComponentId(parentId);
		selectionCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		selectionCriteriaPanel.setAddToParent(Boolean.TRUE);
		selectionCriteriaPanel.setSpacing(true);
		componentList.add(selectionCriteriaPanel);

		GtnUIFrameworkLayoutConfig projectionOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionMainLayoutConfig.setComponentId("dsTabProjectionOptionMainLayout");
		projectionOptionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setParentComponentId("dsTabProjectionOptions");
		projectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionMainLayoutConfig.setGtnLayoutConfig(projectionOptionMainLayout);
		componentList.add(projectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId("dsTabProjectionOptionInnerLayout");
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId("dsTabProjectionOptionMainLayout");
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeLayoutConfig.setComponentId("dsTabModeSelectionLayout");
		modeLayoutConfig.setAddToParent(Boolean.TRUE);
		modeLayoutConfig.setGtnLayoutConfig(modeSelectionlayout);
		modeLayoutConfig.setParentComponentId("dsTabProjectionOptionInnerLayout");
		modeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkLayoutConfig fromToMainLayout = new GtnUIFrameworkLayoutConfig();
		fromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig.setComponentId("dsTabFromToMainLayout");
		fromToLayoutConfig.setAddToParent(Boolean.TRUE);
		fromToLayoutConfig.setSpacing(Boolean.TRUE);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId("dsTabProjectionOptionMainLayout");
		fromToLayoutConfig.setGtnLayoutConfig(fromToMainLayout);
		componentList.add(fromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setComponentId("dsTabProjectionSelectionlayout1");
		privateViewCompanyProjectionNameLayout.setAddToParent(Boolean.TRUE);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCompanyProjectionNameLayoutConfig);
		privateViewCompanyProjectionNameLayout.setParentComponentId("dsTabProjectionOptionInnerLayout");
		privateViewCompanyProjectionNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(privateViewCompanyProjectionNameLayout);

	}

	private void addProfileModeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList) {

		String compId = "dsTabProfileOptionMode";
		GtnUIFrameworkComponentConfig hLConfig = layoutsConfig.getHorizontalLayoutConfig(compId,
				"dsTabModeSelectionLayout");
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

	private void addPrivateViewCompanyProjectionName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig privateViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig privateViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		privateViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewsLayoutConfig.setComponentId("dsTabPrivateViewLayout");
		privateViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		privateViewsLayoutConfig.setSpacing(Boolean.TRUE);
		privateViewsLayoutConfig.setParentComponentId("dsTabProjectionSelectionlayout1");
		privateViewsLayoutConfig.setGtnLayoutConfig(privateViewsLayout);
		componentList.add(privateViewsLayoutConfig);

		GtnUIFrameworkComponentConfig privateView = new GtnUIFrameworkComponentConfig();
		privateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateView.setComponentId("dsTabPrivateViews");
		privateView.setComponentName("Private Views");
		privateView.setAddToParent(Boolean.TRUE);
		privateView.setParentComponentId("dsTabPrivateViewLayout");
		privateView.setComponentWsFieldId("privateViews");

		componentList.add(privateView);

		GtnUIFrameworkLayoutConfig companyLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig companyLayoutConfig = new GtnUIFrameworkComponentConfig();
		companyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		companyLayoutConfig.setComponentId("dsTabCompanyLayout");
		companyLayoutConfig.setAddToParent(Boolean.TRUE);
		companyLayoutConfig.setSpacing(Boolean.TRUE);
		companyLayoutConfig.setParentComponentId("dsTabProjectionSelectionlayout1");
		companyLayoutConfig.setGtnLayoutConfig(companyLayout);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		company.setComponentId("dsTabCompany");
		company.setComponentName("Company");
		company.setAddToParent(Boolean.TRUE);
		company.setParentComponentId("dsTabCompanyLayout");

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP);
		company.setGtnComboboxConfig(companyTypeConfig);

		componentList.add(company);

		GtnUIFrameworkLayoutConfig projectionNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionNameLayoutConfig.setComponentId("dsTabProjectionNameLayout");
		projectionNameLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionNameLayoutConfig.setSpacing(Boolean.TRUE);
		projectionNameLayoutConfig.setParentComponentId("dsTabProjectionSelectionlayout1");
		projectionNameLayoutConfig.setGtnLayoutConfig(projectionNameLayout);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionName.setComponentId("dsTabProjectionName");
		projectionName.setComponentName("Projection Name");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId("dsTabProjectionNameLayout");

		componentList.add(projectionName);
	}

	private void addPublicViewBusinessUnitDescription(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig publicViewsLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId("dsTabPublicViewLayout");
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setParentComponentId("dsTabProjectionSelectionlayout1");
		publicViewsLayoutConfig.setGtnLayoutConfig(publicViewsLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig publicView = new GtnUIFrameworkComponentConfig();
		publicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicView.setComponentId("dsTabPublicViews");
		publicView.setComponentName("Public Views");
		publicView.setAddToParent(Boolean.TRUE);
		publicView.setParentComponentId("dsTabPublicViewLayout");

		componentList.add(publicView);

		GtnUIFrameworkLayoutConfig businessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig.setComponentId("dsTabBusinessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(Boolean.TRUE);
		businessUnitLayoutConfig.setSpacing(Boolean.TRUE);
		businessUnitLayoutConfig.setParentComponentId("dsTabProjectionSelectionlayout1");
		businessUnitLayoutConfig.setGtnLayoutConfig(businessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		businessUnit.setComponentId("dsTabBusinessUnit");
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(Boolean.TRUE);
		businessUnit.setParentComponentId("dsTabBusinessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.BUSINESS_UNIT_GLCOMP);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);

		componentList.add(businessUnit);

		GtnUIFrameworkLayoutConfig projectionDescLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionDescLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionDescLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionDescLayoutConfig.setComponentId("dsTabProjectionDescLayout");
		projectionDescLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionDescLayoutConfig.setSpacing(Boolean.TRUE);
		projectionDescLayoutConfig.setParentComponentId("dsTabProjectionSelectionlayout1");
		projectionDescLayoutConfig.setGtnLayoutConfig(projectionDescLayout);
		componentList.add(projectionDescLayoutConfig);

		GtnUIFrameworkComponentConfig projectionDescription = new GtnUIFrameworkComponentConfig();
		projectionDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionDescription.setComponentId("dsTabProjectionDescription");
		projectionDescription.setComponentName("Projection Description");
		projectionDescription.setAddToParent(Boolean.TRUE);
		projectionDescription.setParentComponentId("dsTabProjectionDescLayout");

		componentList.add(projectionDescription);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId("dsTabTimePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(Boolean.TRUE);
		panel.setParentComponentId("dsTabFromToMainLayout");
		panel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromAndToperiodLayoutConfig.setComponentId("dsTabTimePeriodInnerLayout");
		fromAndToperiodLayoutConfig.setSpacing(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromAndToperiodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromAndToperiodLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		fromAndToperiodLayoutConfig.setParentComponentId("dsTabTimePeriod");
		fromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(fromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig fromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		fromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromPeriodLayoutConfig.setComponentId("dsTabFromPeriodLayout");
		fromPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		fromPeriodLayoutConfig.setGtnLayoutConfig(fromPeriodLayout);
		fromPeriodLayoutConfig.setParentComponentId("dsTabTimePeriodInnerLayout");
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		fromPeriod.setComponentId("dsTabFromPeriod");
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);

		fromPeriod.setParentComponentId("dsTabFromPeriodLayout");
		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE);
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);
		fromPeriodConfig.setHasDefaultValue(true);
		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig.setComponentId("dsTabToPeriodLayout");
		toPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		toPeriodLayoutConfig.setParentComponentId("dsTabTimePeriodInnerLayout");
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		toPeriod.setComponentId("dsTabToPeriod");
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(Boolean.TRUE);
		toPeriod.setEnable(Boolean.FALSE);

		toPeriod.setParentComponentId("dsTabToPeriodLayout");

		GtnUIFrameworkComboBoxConfig toPeriodTypeConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodTypeConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_TO_DATE);
		toPeriodTypeConfig.setHasDefaultValue(true);
		toPeriod.setGtnComboboxConfig(toPeriodTypeConfig);
		componentList.add(toPeriod);
	}

	private void addCustomerSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig customerSelectionPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionPanel.setComponentName("Customer Selection");
		customerSelectionPanel.setComponentId("dsTabCustomerSelectionPanel");
		customerSelectionPanel.setParentComponentId(parentId);
		customerSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		customerSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionPanel.setSpacing(true);
		customerSelectionPanel.setAddToParent(Boolean.TRUE);
		componentList.add(customerSelectionPanel);

	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		productSelectionPanel.setComponentId("dsTabProductSelectionPanel");
		productSelectionPanel.setParentComponentId(parentId);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionPanel.setSpacing(true);
		productSelectionPanel.setAddToParent(Boolean.TRUE);
		componentList.add(productSelectionPanel);

	}

}
