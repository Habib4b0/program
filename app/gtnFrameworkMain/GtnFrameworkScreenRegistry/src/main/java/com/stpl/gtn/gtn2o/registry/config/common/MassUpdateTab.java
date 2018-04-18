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

public class MassUpdateTab {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	public void addMassUpdateTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace){
		GtnUIFrameworkComponentConfig massUpdateLayout=new GtnUIFrameworkComponentConfig();
		massUpdateLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		massUpdateLayout.setComponentId(nameSpace+"-"+"massUpdateLayout");
		
		GtnUIFrameworkLayoutConfig massUpdateLayoutConfig=new GtnUIFrameworkLayoutConfig();
		massUpdateLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		massUpdateLayout.setGtnLayoutConfig(massUpdateLayoutConfig);
		componentList.add(massUpdateLayout);		
				
		addFieldComboBox(componentList, massUpdateLayout.getComponentId(), nameSpace);
		addValueTextBox(componentList, massUpdateLayout.getComponentId(), nameSpace);
		addStartPeriodComboBox(componentList, massUpdateLayout.getComponentId(), nameSpace);
		addEndPeriodComboBox(componentList, massUpdateLayout.getComponentId(), nameSpace);
		addPopulateButton(componentList, massUpdateLayout.getComponentId(), nameSpace);		
	}

	private void addFieldComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig fieldLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "fieldLayout", true, parentComponentId);
		fieldLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		fieldLayout.setSpacing(true);
		componentList.add(fieldLayout);
		
		GtnUIFrameworkComponentConfig fieldComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "masssUpdateField", true, fieldLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		fieldComboBox.setComponentName("Field:");
		componentList.add(fieldComboBox);

		GtnUIFrameworkComboBoxConfig fieldConfig = new GtnUIFrameworkComboBoxConfig();
		fieldConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		fieldConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		fieldComboBox.setGtnComboboxConfig(fieldConfig);
	}

	private void addValueTextBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig valueLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "valueLayout", true, parentComponentId);
		valueLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		valueLayout.setSpacing(true);
		componentList.add(valueLayout);
		
		GtnUIFrameworkComponentConfig valueTextBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "masssUpdateValue", true, valueLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		valueTextBox.setComponentName("Value:");
		componentList.add(valueTextBox);
	}

	private void addStartPeriodComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig startPeriodLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "startPeriodLayout", true, parentComponentId);
		startPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		startPeriodLayout.setSpacing(true);
		componentList.add(startPeriodLayout);
		
		GtnUIFrameworkComponentConfig startPeriodComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "masssUpdateStartPeriod", true, startPeriodLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		startPeriodComboBox.setComponentName("Start Period:");
		componentList.add(startPeriodComboBox);

		GtnUIFrameworkComboBoxConfig startPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		startPeriodConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		startPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		startPeriodComboBox.setGtnComboboxConfig(startPeriodConfig);
	}

	private void addEndPeriodComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig endPeriodLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "endPeriodLayout", true, parentComponentId);
		endPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		endPeriodLayout.setSpacing(true);
		componentList.add(endPeriodLayout);
		
		GtnUIFrameworkComponentConfig endPeriodComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "masssUpdateEndPeriod", true, endPeriodLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		endPeriodComboBox.setComponentName("End Period:");
		componentList.add(endPeriodComboBox);

		GtnUIFrameworkComboBoxConfig endPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		endPeriodConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		endPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		endPeriodComboBox.setGtnComboboxConfig(endPeriodConfig);
	}

	private void addPopulateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig poulateLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "poulateLayout", true, parentComponentId);
		poulateLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		poulateLayout.setSpacing(true);
		componentList.add(poulateLayout);
		
		GtnUIFrameworkComponentConfig populateButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "massUpadtePopulate", true, poulateLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		populateButton.setComponentName("POPULATE");
		componentList.add(populateButton);
	}

}
