package com.stpl.gtn.gtn2o.registry.config.common;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class DisplaySelectionTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addDisplaySelectionTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig displaySelectionPanel = configProvider
				.getPanelConfig(nameSpace + "_" + "displaySelectionPanel", false, null);
		componentList.add(displaySelectionPanel);

		GtnUIFrameworkComponentConfig dispalySelectionLayout = new GtnUIFrameworkComponentConfig();
		dispalySelectionLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dispalySelectionLayout.setComponentId(nameSpace + "_" + "displaySelectionLayout");
		dispalySelectionLayout.setComponentWidth("107%");
		dispalySelectionLayout.setAddToParent(true);
		dispalySelectionLayout.setParentComponentId(displaySelectionPanel.getComponentId());

		GtnUIFrameworkLayoutConfig dispalySelectionLayoutConfig = new GtnUIFrameworkLayoutConfig();
		dispalySelectionLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		dispalySelectionLayout.setGtnLayoutConfig(dispalySelectionLayoutConfig);
		componentList.add(dispalySelectionLayout);
	}

	public void addFrequencyHistory(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig frequencyHistoryLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "frequencyHistoryLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.DISPLAY_SELECTION_TAB_LAYOUT_PARENT_ID);
		frequencyHistoryLayout.setSpacing(true);
		componentList.add(frequencyHistoryLayout);

		addFrequency(componentList, frequencyHistoryLayout.getComponentId(), nameSpace);
		addHistory(componentList, frequencyHistoryLayout.getComponentId(), nameSpace);
	}

	public void addActualsProjDiscountVariables(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig actualsProjVariablesLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "actualsProjDiscountVariablesLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.DISPLAY_SELECTION_TAB_LAYOUT_PARENT_ID);
		actualsProjVariablesLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		actualsProjVariablesLayout.setSpacing(true);
		componentList.add(actualsProjVariablesLayout);

		addActualsProjection(componentList, actualsProjVariablesLayout.getComponentId(), nameSpace);
		addDiscountProjectionVariables(componentList, actualsProjVariablesLayout.getComponentId(), nameSpace);
	}

	public void addActualsProjSalesVariables(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig actualsProjVariablesLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "actualsProjSalesVariablesLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.DISPLAY_SELECTION_TAB_LAYOUT_PARENT_ID);
		actualsProjVariablesLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		actualsProjVariablesLayout.setSpacing(true);
		componentList.add(actualsProjVariablesLayout);

		addActualsProjection(componentList, actualsProjVariablesLayout.getComponentId(), nameSpace);
		addSalesProjectionVariables(componentList, actualsProjVariablesLayout.getComponentId(), nameSpace);
	}

	public void addProjPeriodOrderUnitOfmeasure(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projPeriodOrderUnitOfmeasureLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "projPeriodOrderUnitOfmeasureLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.DISPLAY_SELECTION_TAB_LAYOUT_PARENT_ID);
		projPeriodOrderUnitOfmeasureLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		projPeriodOrderUnitOfmeasureLayout.setSpacing(true);
		componentList.add(projPeriodOrderUnitOfmeasureLayout);

		addProjectionPeriodOrder(componentList, projPeriodOrderUnitOfmeasureLayout.getComponentId(), nameSpace);
		addUnitOfMeasure(componentList, projPeriodOrderUnitOfmeasureLayout.getComponentId(), nameSpace);
	}

	public void addDisplayCurrencyFormat(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig displayCurrencyFormatLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "displayCurrencyFormatLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.DISPLAY_SELECTION_TAB_LAYOUT_PARENT_ID);
		displayCurrencyFormatLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		displayCurrencyFormatLayout.setSpacing(true);
		componentList.add(displayCurrencyFormatLayout);

		addDisplayFormat(componentList, displayCurrencyFormatLayout.getComponentId(), nameSpace);
		addCurrencyFormat(componentList, displayCurrencyFormatLayout.getComponentId(), nameSpace);
	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig commonFrequencyLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "freqLayout", true, parentComponentId);
		commonFrequencyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		commonFrequencyLayout.setSpacing(true);
		componentList.add(commonFrequencyLayout);

		GtnUIFrameworkComponentConfig commonFrequency = new GtnUIFrameworkComponentConfig();
		commonFrequency.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		commonFrequency.setComponentName("Frequency:");
		commonFrequency.setComponentId(nameSpace + "_" + "frequency");
		commonFrequency.setParentComponentId(commonFrequencyLayout.getComponentId());
		commonFrequency.setAddToParent(true);
		componentList.add(commonFrequency);

		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		commonFrequency.setGtnComboboxConfig(frequencyConfig);

	}

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig historyLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "historyLayout", true, parentComponentId);
		historyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(historyLayout);

		GtnUIFrameworkComponentConfig history = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "history", true, historyLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		history.setComponentName("History:");
		componentList.add(history);

		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		historyConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		historyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		history.setGtnComboboxConfig(historyConfig);
	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig actualsProjectionLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "actualsProjectionLayout", true, parentComponentId);
		actualsProjectionLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(actualsProjectionLayoutConfig);

		GtnUIFrameworkComponentConfig actualsProjectionOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "actualsProjection", true, nameSpace + "_" + "actualsProjectionLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		actualsProjectionOptionRadioGroup.setComponentName("Actuals/Projection:");

		GtnUIFrameworkOptionGroupConfig actualsProjectionOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		actualsProjectionOptionGroupConfig.setItemValues(Arrays.asList("Actuals", "Projection", "Both"));
		actualsProjectionOptionGroupConfig.setValuesFromService(false);
		actualsProjectionOptionGroupConfig.setEnable(true);
		actualsProjectionOptionRadioGroup
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		actualsProjectionOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(actualsProjectionOptionGroupConfig);

		componentList.add(actualsProjectionOptionRadioGroup);
	}

	private void addSalesProjectionVariables(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionVariablesLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "variablesLayout", true, parentComponentId);
		salesProjectionVariablesLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesProjectionVariablesLayoutConfig);

		GtnUIFrameworkComponentConfig salesProjectionVariablesOptionGroup = new GtnUIFrameworkComponentConfig();
		salesProjectionVariablesOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		salesProjectionVariablesOptionGroup.setComponentId(nameSpace + "_" + "variables");
		salesProjectionVariablesOptionGroup.setComponentName("Variables:");
		salesProjectionVariablesOptionGroup.setAddToParent(true);
		salesProjectionVariablesOptionGroup.setParentComponentId(salesProjectionVariablesLayoutConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionVariablesOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionVariablesOptionGroupConfig
				.setItemValues(Arrays.asList("Sales", "Units", "Product Growth", "Account Growth"));
		salesProjectionVariablesOptionGroupConfig.setValuesFromService(false);
		salesProjectionVariablesOptionGroupConfig.setEnable(true);
		salesProjectionVariablesOptionGroupConfig.setIsMultiSelect(true);
		salesProjectionVariablesOptionGroup
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		salesProjectionVariablesOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(salesProjectionVariablesOptionGroupConfig);

		componentList.add(salesProjectionVariablesOptionGroup);
	}

	private void addDiscountProjectionVariables(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig discountProjectionVariablesLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "variablesLayout", true, parentComponentId);
		discountProjectionVariablesLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(discountProjectionVariablesLayoutConfig);

		GtnUIFrameworkComponentConfig discountProjectionVariablesOptionGroup = new GtnUIFrameworkComponentConfig();
		discountProjectionVariablesOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		discountProjectionVariablesOptionGroup.setComponentId(nameSpace + "_" + "variables");
		discountProjectionVariablesOptionGroup.setComponentName("Variables:");
		discountProjectionVariablesOptionGroup.setAddToParent(true);
		discountProjectionVariablesOptionGroup
				.setParentComponentId(discountProjectionVariablesLayoutConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig discountProjectionVariablesOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		discountProjectionVariablesOptionGroupConfig
				.setItemValues(Arrays.asList("Discount Rate", "Rebate Per Unit", "Discount Amount", "Growth"));
		discountProjectionVariablesOptionGroupConfig.setValuesFromService(false);
		discountProjectionVariablesOptionGroupConfig.setEnable(true);
		discountProjectionVariablesOptionGroupConfig.setIsMultiSelect(true);
		discountProjectionVariablesOptionGroup
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		discountProjectionVariablesOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(discountProjectionVariablesOptionGroupConfig);

		componentList.add(discountProjectionVariablesOptionGroup);
	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig projectionPeriodLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "projectionPeriodLayout", true, parentComponentId);
		projectionPeriodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig projectionPeriodRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "projectionPeriod", true, projectionPeriodLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodRadioGroup.setComponentName("Projection Period Order:");

		GtnUIFrameworkOptionGroupConfig projectionPeriodRadioGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionPeriodRadioGroupConfig.setItemValues(Arrays.asList("Ascending", "Descending"));
		projectionPeriodRadioGroupConfig.setValuesFromService(false);
		projectionPeriodRadioGroupConfig.setEnable(true);
		projectionPeriodRadioGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		projectionPeriodRadioGroup.setGtnUIFrameworkOptionGroupConfig(projectionPeriodRadioGroupConfig);

		componentList.add(projectionPeriodRadioGroup);
	}

	private void addCurrencyFormat(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig currencyFormatLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "currencyFormatLayout", true, parentComponentId);
		currencyFormatLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(currencyFormatLayoutConfig);

		GtnUIFrameworkComponentConfig currencyFormat = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "currencyFormat", true, currencyFormatLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		currencyFormat.setComponentName("Currency Format:");
		componentList.add(currencyFormat);

		GtnUIFrameworkComboBoxConfig currencyFormatConfig = new GtnUIFrameworkComboBoxConfig();
		currencyFormatConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		currencyFormatConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		currencyFormat.setGtnComboboxConfig(currencyFormatConfig);
	}

	public void addUnitOfMeasure(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig unitOfMeasureLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "unitOfMeasureLayout", true, parentComponentId);
		unitOfMeasureLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(unitOfMeasureLayoutConfig);

		GtnUIFrameworkComponentConfig unitOfMeasure = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "unitOfMeasure", true, unitOfMeasureLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		unitOfMeasure.setComponentName("Unit Of Measure:");
		componentList.add(unitOfMeasure);

		GtnUIFrameworkComboBoxConfig unitOfMeasureConfig = new GtnUIFrameworkComboBoxConfig();
		unitOfMeasureConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		unitOfMeasureConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		unitOfMeasure.setGtnComboboxConfig(unitOfMeasureConfig);
	}

	public void addDisplayFormat(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig displayFormatLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "displayFormatLayout", true, parentComponentId);
		displayFormatLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(displayFormatLayoutConfig);

		GtnUIFrameworkComponentConfig displayFormat = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "displayFormat", true, displayFormatLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		displayFormat.setComponentName("Display Format:");
		componentList.add(displayFormat);

		GtnUIFrameworkCheckedComboBoxConfig displayFormatConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		displayFormatConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		displayFormatConfig.setDefaultValue("-Select-");
		displayFormatConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		displayFormat.setGtnCheckedComboboxConfig(displayFormatConfig);
	}
}