package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetLoadType;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.dataselection.GtnFrameworkCommercialForecastingDSTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.discountprojection.GtnFrameworkCommercialForecastingDPRTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.discountprojection.GtnFrameworkCommercialForecastingDPTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.ppa.GtnFrameworkCommercialForecastingPPATabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.projectionresults.GtnFrameworkCommercialForecastingPRTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.projectionvariance.GtnFrameworkCommercialForecastingPVTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.salesprojection.GtnFrameworkCommercialForecastingSPRTabConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.salesprojection.GtnFrameworkCommercialForecastingSPTabConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCommercialForecastingTabConfig {

	public GtnUIFrameworkViewConfig getGtnCommercialForecastingProjectionDetailsLookUpView()
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Projection Name ");
		view.setViewId("gtnCommercialForecastingProjectionDetailsPopup");
		view.setDefaultView(false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) throws GtnFrameworkGeneralException {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("projectionDetailsTabsheetMainLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(false);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabLayout(componentList);
		addControlButtonLayout(componentList);

	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("projectionDetailsTabsheetLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId("projectionDetailsTabsheetMainLayout");
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabSheet(componentList);

	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("commercialForecastingTabSheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("projectionDetailsTabsheetLayout");
		tabSheetConfig.setSpacing(true);


		GtnUIFrameworkTabConfig dataSelection = new GtnUIFrameworkTabConfig();
		dataSelection.setComponentId("commercialForecastingDataSelectionRootLayout");
		dataSelection.setTabCaption("Data Selection");
		dataSelection.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		List<GtnUIFrameworkComponentConfig> dsTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingDSTabConfig()
				.addDataSelectionTab(dsTabLayoutComponentConfigList);
		dataSelection.setTabLayoutComponentConfigList(dsTabLayoutComponentConfigList);

		GtnUIFrameworkTabConfig salesProjection = new GtnUIFrameworkTabConfig();
		salesProjection.setComponentId("commercialForecastingSalesProjectionRootLayout");
		salesProjection.setTabCaption("Sales Projection");
		salesProjection.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> salesProjectionTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingSPTabConfig()
				.addSalesProjectionTab(salesProjectionTabLayoutComponentConfigList);
		salesProjection.setTabLayoutComponentConfigList(salesProjectionTabLayoutComponentConfigList);
		
		GtnUIFrameworkTabConfig salesProjectionReults = new GtnUIFrameworkTabConfig();
		salesProjectionReults.setComponentId("commercialForecastingSalesProjectionRootLayout");
		salesProjectionReults.setTabCaption("Sales Projection Results");
		salesProjectionReults.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> salesProjectionResultsTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingSPRTabConfig().addSalesProjectionResultsTab(salesProjectionResultsTabLayoutComponentConfigList);
		salesProjectionReults.setTabLayoutComponentConfigList(salesProjectionResultsTabLayoutComponentConfigList);
		
		GtnUIFrameworkTabConfig discountProjection = new GtnUIFrameworkTabConfig();
		discountProjection.setComponentId("commercialForecastingDiscountProjectionRootLayout");
		discountProjection.setTabCaption("Discount Projection");
		discountProjection.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> discountProjectionTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingDPTabConfig()
				.addDiscountProjectionTab(discountProjectionTabLayoutComponentConfigList);
		discountProjection.setTabLayoutComponentConfigList(discountProjectionTabLayoutComponentConfigList);
		
		GtnUIFrameworkTabConfig discountProjectionResults = new GtnUIFrameworkTabConfig();
		discountProjectionResults.setComponentId("commercialForecastingDiscountProjectionRootLayout");
		discountProjectionResults.setTabCaption("Discount Projection Results");
		discountProjectionResults.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> discountProjectionResultsTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingDPRTabConfig()
				.addDiscountProjectionResultsTab(discountProjectionResultsTabLayoutComponentConfigList);
		discountProjectionResults.setTabLayoutComponentConfigList(discountProjectionResultsTabLayoutComponentConfigList);
		
		GtnUIFrameworkTabConfig ppaProjectionResults = new GtnUIFrameworkTabConfig();
		ppaProjectionResults.setComponentId("commercialForecastingPPAProjectionResultsRootLayout");
		ppaProjectionResults.setTabCaption("PPA Projection Results");
		ppaProjectionResults.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> ppaProjectionResultsTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingPPATabConfig().addPPAProjectionResultsTab(ppaProjectionResultsTabLayoutComponentConfigList);
		ppaProjectionResults.setTabLayoutComponentConfigList(ppaProjectionResultsTabLayoutComponentConfigList);
		
		GtnUIFrameworkTabConfig projectionReults = new GtnUIFrameworkTabConfig();
		projectionReults.setComponentId("commercialForecastingProjectionResultsRootLayout");
		projectionReults.setTabCaption("Projection Results");
		projectionReults.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> projectionResultsTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingPRTabConfig().addProjectionResultsTab(projectionResultsTabLayoutComponentConfigList);
		projectionReults.setTabLayoutComponentConfigList(projectionResultsTabLayoutComponentConfigList);
		
		GtnUIFrameworkTabConfig projectionVariance = new GtnUIFrameworkTabConfig();
		projectionVariance.setComponentId("commercialForecastingProjectionVarianceRootLayout");
		projectionVariance.setTabCaption("Projection Variance");
		projectionVariance.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> projectionVarianceTabLayoutComponentConfigList = new ArrayList<>();
		new GtnFrameworkCommercialForecastingPVTabConfig().addProjectionVarianceTab(projectionVarianceTabLayoutComponentConfigList);
		projectionVariance.setTabLayoutComponentConfigList(projectionVarianceTabLayoutComponentConfigList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(dataSelection);
		tabConfigList.add(salesProjection);
		tabConfigList.add(salesProjectionReults);
		tabConfigList.add(discountProjection);
		tabConfigList.add(discountProjectionResults);
		tabConfigList.add(ppaProjectionResults);
		tabConfigList.add(projectionReults);
		tabConfigList.add(projectionVariance);

		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		componentList.add(tabSheetConfig);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("commercialForecastingTabSheetControlButtonLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId("projectionDetailsTabsheetMainLayout");
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);

		componentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig saveButton = new GtnUIFrameworkComponentConfig();
		saveButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveButton.setComponentId("dsTabSave");
		saveButton.setComponentName("SAVE");
		saveButton.setParentComponentId("commercialForecastingTabSheetControlButtonLayout");
		saveButton.setAddToParent(true);
		componentList.add(saveButton);

		GtnUIFrameworkComponentConfig updateButton = new GtnUIFrameworkComponentConfig();
		updateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButton.setComponentId("dsTabUpdate");
		updateButton.setComponentName("UPDATE");
		updateButton.setParentComponentId("commercialForecastingTabSheetControlButtonLayout");
		updateButton.setAddToParent(true);
		componentList.add(updateButton);

		GtnUIFrameworkComponentConfig nextButton = new GtnUIFrameworkComponentConfig();
		nextButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButton.setComponentId("dsTabNext");
		nextButton.setComponentName("NEXT");
		nextButton.setParentComponentId("commercialForecastingTabSheetControlButtonLayout");
		nextButton.setAddToParent(true);
		componentList.add(nextButton);

		GtnUIFrameworkComponentConfig previousButton = new GtnUIFrameworkComponentConfig();
		previousButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButton.setComponentId("dsTabPrevious");
		previousButton.setComponentName("PREVIOUS");
		previousButton.setParentComponentId("commercialForecastingTabSheetControlButtonLayout");
		previousButton.setAddToParent(true);
		componentList.add(previousButton);

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId("dsTabClose");
		closeButton.setComponentName("CLOSE");
		closeButton.setParentComponentId("commercialForecastingTabSheetControlButtonLayout");
		closeButton.setAddToParent(true);
		componentList.add(closeButton);

		GtnUIFrameworkComponentConfig submitButton = new GtnUIFrameworkComponentConfig();
		submitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButton.setComponentId("dsTabSubmit");
		submitButton.setComponentName("SUBMIT");
		submitButton.setParentComponentId("commercialForecastingTabSheetControlButtonLayout");
		submitButton.setAddToParent(true);
		componentList.add(submitButton);
	}
}
