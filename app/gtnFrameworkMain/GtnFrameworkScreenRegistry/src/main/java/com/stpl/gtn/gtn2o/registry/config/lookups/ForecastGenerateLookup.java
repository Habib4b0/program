package com.stpl.gtn.gtn2o.registry.config.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.config.GtnUIFrameworkDataSelectionScreenConfig;
import com.stpl.gtn.gtn2o.registry.config.additionalinformation.GtnFrameworkAdditionalInformationTabConfig;
import com.stpl.gtn.gtn2o.registry.config.dataassumptions.GtnFrameworkDataAssumptionsTabConfig;
import com.stpl.gtn.gtn2o.registry.config.discountprojection.GtnFrameworkDiscountProjectionTabConfig;
import com.stpl.gtn.gtn2o.registry.config.projectionvariance.CommercialForecastingProjectionVarianceMain;
import com.stpl.gtn.gtn2o.registry.config.salesprojection.GtnFrameworkSalesProjectionTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;

public class ForecastGenerateLookup {
	public GtnUIFrameworkViewConfig getGtnForecastGenerateLookUpView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Forecasting Generate Lookup View");
		view.setViewId("forecastGenerateLookupView");
		view.setDefaultView(false);
		view.setReplicable(true);
		addComponentList(view, namespace);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("forecastingTabsheetMainLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(false);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabLayout(componentList, namespace);

	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("forecastingTabsheetLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId("forecastingTabsheetMainLayout");
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabSheet(componentList, namespace);

	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("tabSheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("forecastingTabsheetLayout");
		tabSheetConfig.setSpacing(true);

		GtnUIFrameworkTabConfig dataSelection = new GtnUIFrameworkTabConfig();
		dataSelection.setComponentId(namespace + "_" + "dataSelectionTab");
		dataSelection.setTabCaption("Data Selection");
		GtnUIFrameworkDataSelectionScreenConfig dataSelectionConfig = new GtnUIFrameworkDataSelectionScreenConfig();
		List<GtnUIFrameworkComponentConfig> dataSelectionTabConfigList = dataSelectionConfig.getDataSelectionView("CFDataSelection").getGtnComponentList();
		dataSelection.setTabLayoutComponentConfigList(dataSelectionTabConfigList);
		new GtnUIFrameworkDataSelectionScreenConfig().getDataSelectionView(namespace);
		
		
		GtnUIFrameworkTabConfig dataAssumptions = new GtnUIFrameworkTabConfig();
		dataAssumptions.setComponentId(namespace + "_" + "dataAssumptionsTab");
		dataAssumptions.setTabCaption("Data Assumptions");
		List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList = new ArrayList<>();
		dataAssumptions.setTabLayoutComponentConfigList(dataAssumptionsTabConfigList);
		new GtnFrameworkDataAssumptionsTabConfig().addDataAssumptionsTabComponents(dataAssumptionsTabConfigList, dataAssumptions.getComponentId());
		
		GtnUIFrameworkTabConfig salesProjection = new GtnUIFrameworkTabConfig();
		salesProjection.setComponentId(namespace + "_" + "salesProjectionTab");
		salesProjection.setTabCaption("Sales Projection");
		List<GtnUIFrameworkComponentConfig> salesProjectionTabConfigList = new ArrayList<>();
		new GtnFrameworkSalesProjectionTabConfig().addSalesProjectionTabComponents(salesProjectionTabConfigList, salesProjection.getComponentId());
		salesProjection.setTabLayoutComponentConfigList(salesProjectionTabConfigList);
		
		GtnUIFrameworkTabConfig discountProjection = new GtnUIFrameworkTabConfig();
		discountProjection.setComponentId(namespace + "_" + "discountProjectionTab");
		discountProjection.setTabCaption("Discount Projection");
		List<GtnUIFrameworkComponentConfig> discountProjectionTabConfigList = new ArrayList<>();
		discountProjection.setTabLayoutComponentConfigList(discountProjectionTabConfigList);
		new GtnFrameworkDiscountProjectionTabConfig().addDiscountProjectionComponents(discountProjectionTabConfigList, discountProjection.getComponentId());
		
		GtnUIFrameworkTabConfig projectionVariance = new GtnUIFrameworkTabConfig();
		projectionVariance.setComponentId(namespace + "_" + "projectionVarianceTab");
		projectionVariance.setTabCaption("Projection Variance");
		List<GtnUIFrameworkComponentConfig> projectionVarianceTabConfigList = new ArrayList<>();
		projectionVariance.setTabLayoutComponentConfigList(projectionVarianceTabConfigList);
		new CommercialForecastingProjectionVarianceMain().addProjectionVarianceTabComponents(projectionVarianceTabConfigList, projectionVariance.getComponentId());
		
		GtnUIFrameworkTabConfig additionalInformation = new GtnUIFrameworkTabConfig();
		additionalInformation.setComponentId(namespace + "_" + "additionalInformationTab");
		additionalInformation.setTabCaption("Additional Information");
		List<GtnUIFrameworkComponentConfig> additionalInformationTabConfigList = new ArrayList<>();
		additionalInformation.setTabLayoutComponentConfigList(additionalInformationTabConfigList);
		new GtnFrameworkAdditionalInformationTabConfig().addAdditionalInformationComponents(additionalInformationTabConfigList, additionalInformation.getComponentId());

		List<GtnUIFrameworkTabConfig> tabList = new ArrayList<>();
		tabList.add(dataSelection);
		tabList.add(dataAssumptions);
		tabList.add(salesProjection);
		tabList.add(discountProjection);
		tabList.add(projectionVariance);
		tabList.add(additionalInformation);

		tabSheetConfig.setGtnTabSheetConfigList(tabList);

		componentList.add(tabSheetConfig);

	}

}
