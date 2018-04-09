package com.stpl.gtn.gtn2o.registry.config.salesprojection;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkSalesProjectionTabConfig {

	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addSalesProjectionTabComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addSalesProjectionPanel(componentList, nameSpace);
	}

	private void addSalesProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionMainPanel = new GtnUIFrameworkComponentConfig();
		salesProjectionMainPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		salesProjectionMainPanel.setComponentId(nameSpace + "_" + "salesProjectionMainPanel");
		salesProjectionMainPanel.setAddToParent(false);
		salesProjectionMainPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionMainPanel);
		addSalesProjectionMainLayout(componentList, nameSpace);
	}

	private void addSalesProjectionMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionMainLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "salesProjectionMainLayout", true, nameSpace + "_" + "salesProjectionMainPanel");
		componentList.add(salesProjectionMainLayout);
		addTabSheet(componentList, nameSpace);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig tabLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "tabLayout", true, nameSpace + "_" + "salesProjectionMainLayout");
		componentList.add(tabLayout);

		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "tabSheet", true, nameSpace + "_" + "tabLayout",
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("tabSheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig displaySelectionTab = new GtnUIFrameworkTabConfig();
		displaySelectionTab.setComponentId(nameSpace + "_" + "displaySelectionTab");
		displaySelectionTab.setTabCaption("Display Selection");
		List<GtnUIFrameworkComponentConfig> displaySelectionComponentList = new ArrayList<>();
		displaySelectionTab.setTabLayoutComponentConfigList(displaySelectionComponentList);
		addDisplaySelection(displaySelectionComponentList, nameSpace);

		GtnUIFrameworkTabConfig filterTab = new GtnUIFrameworkTabConfig();
		filterTab.setComponentId(nameSpace + "_" + "filterTab");
		filterTab.setTabCaption("Filter");
		List<GtnUIFrameworkComponentConfig> filterComponentList = new ArrayList<>();
		filterTab.setTabLayoutComponentConfigList(filterComponentList);
		addFilterTab(filterComponentList, nameSpace);
		
		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList = new ArrayList<>();
		gtnTabSheetConfigList.add(displaySelectionTab);
		gtnTabSheetConfigList.add(filterTab);
		tabSheetConfig.setGtnTabSheetConfigList(gtnTabSheetConfigList);
//		addFilter(filterComponentList, nameSpace);

	}

	private void addDisplaySelection(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig displaySelectionLayout = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "displaySelectionLayout", false, null);
		displaySelectionLayout.setTabComponent(true);
		componentList.add(displaySelectionLayout);

		addFrequency(componentList, nameSpace);
//		addActualsProjections(componentList, nameSpace);
//		addProjectionPeriodOrder(componentList, nameSpace);
//		addDisplayFormat(componentList, nameSpace);
//		addHistory(componentList, nameSpace);
//		addVariables(componentList, nameSpace);
//		addUnitOfMeasure(componentList, nameSpace);
//		addCurrencyFormat(componentList, nameSpace);
	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig frequencyLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "freqLayout", true, nameSpace + "_" + "displaySelectionLayout");
		componentList.add(frequencyLayout);
		
		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		frequency.setComponentName("Frequency");
		frequency.setComponentId(nameSpace + "_" + "forecastFrequency");
		frequency.setAddToParent(true);
		frequency.setParentComponentId(nameSpace + "_" + "freqLayout");
		
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setComboBoxType("FORECAST_FREQUENCY");
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequency.setGtnComboboxConfig(frequencyConfig);
		componentList.add(frequency);
	}
	
	private void addFilterTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		
		GtnUIFrameworkComponentConfig filterLayout = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "filterLayout", false, null);
		filterLayout.setTabComponent(true);
		componentList.add(filterLayout);

		addCustomerLevel(componentList, nameSpace);
	}
	
	private void addCustomerLevel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		GtnUIFrameworkComponentConfig customerLevelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerLevelLayout", true, nameSpace + "_" + "filterLayout");
		componentList.add(customerLevelLayout);
		
		GtnUIFrameworkComponentConfig customerLevel = new GtnUIFrameworkComponentConfig();
		customerLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerLevel.setComponentName("Customer Level");
		customerLevel.setComponentId(nameSpace + "_" + "forecastFrequency");
		customerLevel.setAddToParent(true);
		customerLevel.setParentComponentId(nameSpace + "_" + "customerLevelLayout");
		
		GtnUIFrameworkComboBoxConfig customerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		customerLevelConfig.setComboBoxType("FORECAST_FREQUENCY");
		customerLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerLevel.setGtnComboboxConfig(customerLevelConfig);
		componentList.add(customerLevel);
	}
}
