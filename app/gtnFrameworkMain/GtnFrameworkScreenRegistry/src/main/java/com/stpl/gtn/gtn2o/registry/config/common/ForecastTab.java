package com.stpl.gtn.gtn2o.registry.config.common;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class ForecastTab {
	
	
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addForecastTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig forecastLayout=new GtnUIFrameworkComponentConfig();
		forecastLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastLayout.setComponentId(nameSpace+"_"+"forecastLayout");
		GtnUIFrameworkLayoutConfig forecastLayoutConfig=new GtnUIFrameworkLayoutConfig();
		forecastLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		forecastLayout.setGtnLayoutConfig(forecastLayoutConfig);
		componentList.add(forecastLayout);
	}


	public void addMethodologyComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		GtnUIFrameworkComponentConfig methodologyLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "methodologyLayout", true, nameSpace + GtnFrameworkScreenRegisteryConstants.FORECAST_TAB_PARENT_ID);
		methodologyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		methodologyLayout.setSpacing(true);
		componentList.add(methodologyLayout);		
		
		GtnUIFrameworkComponentConfig methodologyComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "forecastMethodology", true, methodologyLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		methodologyComboBox.setComponentName("Methodology:");
		componentList.add(methodologyComboBox);

		GtnUIFrameworkComboBoxConfig methodologyConfig = new GtnUIFrameworkComboBoxConfig();
		methodologyConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		methodologyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		methodologyComboBox.setGtnComboboxConfig(methodologyConfig);

	}

	public void addAllocationBasisComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		GtnUIFrameworkComponentConfig allocationBasisLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "allocationBasisLayout", true, nameSpace + GtnFrameworkScreenRegisteryConstants.FORECAST_TAB_PARENT_ID);
		allocationBasisLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		allocationBasisLayout.setSpacing(true);
		componentList.add(allocationBasisLayout);	
		
		GtnUIFrameworkComponentConfig allocationBasisComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "forecastAllocationBasis", true, allocationBasisLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		allocationBasisComboBox.setComponentName("Allocation Basis:");
		componentList.add(allocationBasisComboBox);

		GtnUIFrameworkComboBoxConfig allocationBasisConfig = new GtnUIFrameworkComboBoxConfig();
		allocationBasisConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		allocationBasisConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		allocationBasisComboBox.setGtnComboboxConfig(allocationBasisConfig);

	}

	public void addStartPeriodForecastComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			 String nameSpace) {
		GtnUIFrameworkComponentConfig startPeriodForecastLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "startPeriodForecastLayout", true, nameSpace + GtnFrameworkScreenRegisteryConstants.FORECAST_TAB_PARENT_ID);
		startPeriodForecastLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		startPeriodForecastLayout.setSpacing(true);
		componentList.add(startPeriodForecastLayout);	
		
		GtnUIFrameworkComponentConfig startPeriodComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "forecastStartPeriod", true, startPeriodForecastLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		startPeriodComboBox.setComponentName("Start Period:");
		componentList.add(startPeriodComboBox);

		GtnUIFrameworkComboBoxConfig startPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		startPeriodConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		startPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		startPeriodComboBox.setGtnComboboxConfig(startPeriodConfig);
	}

	public void addEndPeriodForecastComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			 String nameSpace) {
		GtnUIFrameworkComponentConfig endPeriodForecastLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "endPeriodForecastLayout", true, nameSpace + GtnFrameworkScreenRegisteryConstants.FORECAST_TAB_PARENT_ID);
		endPeriodForecastLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		endPeriodForecastLayout.setSpacing(true);
		componentList.add(endPeriodForecastLayout);	
		
		GtnUIFrameworkComponentConfig endPeriodComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "forecastEndPeriod", true, endPeriodForecastLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		endPeriodComboBox.setComponentName("End Period:");
		componentList.add(endPeriodComboBox);

		GtnUIFrameworkComboBoxConfig endPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		endPeriodConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		endPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		endPeriodComboBox.setGtnComboboxConfig(endPeriodConfig);
	}

	public void addCalculateButton(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		GtnUIFrameworkComponentConfig calculateLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "calculateLayout", true, nameSpace + GtnFrameworkScreenRegisteryConstants.FORECAST_TAB_PARENT_ID);
		calculateLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		calculateLayout.setSpacing(true);
		componentList.add(calculateLayout);	
		
		GtnUIFrameworkComponentConfig calculateButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "forecastCalculate", true, calculateLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		calculateButton.setComponentName("CALCULATE");
		componentList.add(calculateButton);
		
	}
}
