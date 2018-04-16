package com.stpl.gtn.gtn2o.registry.config.projectionvariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.label.GtnUIFrameworkLabelConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class DisplaySelectionFilterOptionTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig tabLayout = configProvider.getHorizontalLayoutConfig(nameSpace + "_" + "tabLayout", true, parentComponentId);
		tabLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		tabLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabLayout);

		GtnUIFrameworkComponentConfig tabSheet = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "tabSheet", true, nameSpace + "_" + "tabLayout",
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheet.setComponentName("Tab Sheet");
		tabSheet.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheet);

		GtnUIFrameworkTabConfig displaySelectionTab = new GtnUIFrameworkTabConfig();
		displaySelectionTab.setComponentId(nameSpace + "_" + "displaySelectionTab");
		displaySelectionTab.setTabCaption("Display Selection");
		List<GtnUIFrameworkComponentConfig> displaySelectionComponentList = new ArrayList<>();
		displaySelectionTab.setTabLayoutComponentConfigList(displaySelectionComponentList);
		addDisplaySelectionTab(displaySelectionComponentList, nameSpace);

		GtnUIFrameworkTabConfig filterOptionTab = new GtnUIFrameworkTabConfig();
		filterOptionTab.setComponentId(nameSpace + "_" + "filterTab");
		filterOptionTab.setTabCaption("Filter Option");
		List<GtnUIFrameworkComponentConfig> filterComponentList = new ArrayList<>();
		filterOptionTab.setTabLayoutComponentConfigList(filterComponentList);
		addFilterTab(filterComponentList, nameSpace);

		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList = new ArrayList<>();
		gtnTabSheetConfigList.add(displaySelectionTab);
		gtnTabSheetConfigList.add(filterOptionTab);
		tabSheet.setGtnTabSheetConfigList(gtnTabSheetConfigList);
	}

	//method for display selection tab Layout 
	private void addDisplaySelectionTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig projectionVarianceMainPanel = new GtnUIFrameworkComponentConfig();
		projectionVarianceMainPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionVarianceMainPanel.setComponentId(nameSpace + "_" + "projectionVarianceSubMainPanel");
		projectionVarianceMainPanel.setAddToParent(false);
		projectionVarianceMainPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(projectionVarianceMainPanel);
		
	
		GtnUIFrameworkComponentConfig displaySelectionLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "displaySelectionLayout", true, nameSpace + "_" + "projectionVarianceSubMainPanel");
		displaySelectionLayoutConfig.setComponentWidth("170%");
		displaySelectionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		displaySelectionLayoutConfig.setAddToParent(true);
		
		componentList.add(displaySelectionLayoutConfig);

		addDisplaySelectionInnerLayout(componentList,displaySelectionLayoutConfig.getComponentId(), nameSpace);		
	}

	private void addDisplaySelectionInnerLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		
		
		GtnUIFrameworkLayoutConfig displaySelectionInnerMainLayout = new GtnUIFrameworkLayoutConfig();
		displaySelectionInnerMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		//displaySelectionInnerMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig displaySelectionInnerMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		displaySelectionInnerMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		displaySelectionInnerMainLayoutConfig.setComponentId(nameSpace + "_" + "displaySelectionInnerMainLayout");
		displaySelectionInnerMainLayoutConfig.setAddToParent(Boolean.TRUE);
		displaySelectionInnerMainLayoutConfig.setSpacing(Boolean.TRUE);
		displaySelectionInnerMainLayout.setMargin(Boolean.TRUE);
		displaySelectionInnerMainLayoutConfig.setParentComponentId(parentComponentId);
		displaySelectionInnerMainLayoutConfig.setGtnLayoutConfig(displaySelectionInnerMainLayout);
		
		
//--------Vertical Inside 1 -> Horizontal 1
		
		GtnUIFrameworkLayoutConfig displaySelectionInnerLayout1 = new GtnUIFrameworkLayoutConfig();
		displaySelectionInnerLayout1.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig displaySelectionInnerLayoutConfig1 = new GtnUIFrameworkComponentConfig();
		displaySelectionInnerLayoutConfig1.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		displaySelectionInnerLayoutConfig1.setComponentId(nameSpace + "_" + "displaySelectionInnerLayout1");
		displaySelectionInnerLayoutConfig1.setAddToParent(Boolean.TRUE);
		displaySelectionInnerLayoutConfig1.setSpacing(Boolean.TRUE);
		displaySelectionInnerLayout1.setMargin(Boolean.TRUE);
//		displaySelectionInnerLayoutConfig1.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
//		displaySelectionInnerLayoutConfig1.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		displaySelectionInnerLayoutConfig1.setParentComponentId(nameSpace + "_" + "displaySelectionInnerMainLayout");
		displaySelectionInnerLayoutConfig1.setGtnLayoutConfig(displaySelectionInnerLayout1);
		
//--------Vertical Inside 1 -> Horizontal 2
		
		GtnUIFrameworkLayoutConfig displaySelectionInnerLayout2 = new GtnUIFrameworkLayoutConfig();
		displaySelectionInnerLayout2.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig displaySelectionInnerLayoutConfig2 = new GtnUIFrameworkComponentConfig();
		displaySelectionInnerLayoutConfig2.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		displaySelectionInnerLayoutConfig2.setComponentId(nameSpace + "_" + "displaySelectionInnerLayout2");
		displaySelectionInnerLayoutConfig2.setAddToParent(Boolean.TRUE);
		displaySelectionInnerLayoutConfig2.setSpacing(Boolean.TRUE);
		displaySelectionInnerLayout2.setMargin(Boolean.TRUE);
//		displaySelectionInnerLayoutConfig2.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
//		displaySelectionInnerLayoutConfig2.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		displaySelectionInnerLayoutConfig2.setParentComponentId(nameSpace + "_" + "displaySelectionInnerMainLayout");
		displaySelectionInnerLayoutConfig2.setGtnLayoutConfig(displaySelectionInnerLayout2);
		
//--------Vertical Inside 1 -> Horizontal 3
		
		GtnUIFrameworkLayoutConfig displaySelectionInnerLayout3 = new GtnUIFrameworkLayoutConfig();
		displaySelectionInnerLayout3.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig displaySelectionInnerLayoutConfig3 = new GtnUIFrameworkComponentConfig();
		displaySelectionInnerLayoutConfig3.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		displaySelectionInnerLayoutConfig3.setComponentId(nameSpace + "_" + "displaySelectionInnerLayout3");
		displaySelectionInnerLayoutConfig3.setAddToParent(Boolean.TRUE);
		displaySelectionInnerLayoutConfig3.setSpacing(Boolean.TRUE);
		displaySelectionInnerLayout3.setMargin(Boolean.TRUE);
//		displaySelectionInnerLayoutConfig3.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
//		displaySelectionInnerLayoutConfig3.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		displaySelectionInnerLayoutConfig3.setParentComponentId(nameSpace + "_" + "displaySelectionInnerMainLayout");
		displaySelectionInnerLayoutConfig3.setGtnLayoutConfig(displaySelectionInnerLayout3);
		

		
	
		componentList.add(displaySelectionInnerMainLayoutConfig);
		componentList.add(displaySelectionInnerLayoutConfig1);
		componentList.add(displaySelectionInnerLayoutConfig2);
		componentList.add(displaySelectionInnerLayoutConfig3);
		
		
		
		addComparisonComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);	
		
		addlevelLabelComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		addLevelComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		addComparisonBasisComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		
		addVariableCatagoryComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		addProjectionPeriodOrderComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		addDateRangeComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		addFromComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);
		addDisplayFormatComponent(componentList, displaySelectionInnerLayoutConfig1.getComponentId(), nameSpace);	
		
		addFrequencyComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		addDiscountLevelLabelComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		addDiscountLevelComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		addVariablesComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		addPivotViewComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		addToComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		addUnitOfMeasureComponent(componentList, displaySelectionInnerLayoutConfig2.getComponentId(), nameSpace);
		
		addCurrencyFormatComponent(componentList, displaySelectionInnerLayoutConfig3.getComponentId(), nameSpace);
		
		
	}
	
	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig comparisonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "comparisonLayout", true, parentComponentId);
		comparisonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		comparisonLayout.setSpacing(true);
		componentList.add(comparisonLayout);

		GtnUIFrameworkComponentConfig comparison = new GtnUIFrameworkComponentConfig();
		comparison.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		comparison.setComponentName("Comparison:");
		comparison.setComponentId(nameSpace + "_" + "comparison");
		
		comparison.setParentComponentId(comparisonLayout.getComponentId());
		comparison.setAddToParent(true);
		comparison.setComponentValue("-Select One-");
		componentList.add(comparison);
	}
	
	
	private void addlevelLabelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig levelLabelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "levelLabelLayout", true, parentComponentId);
		levelLabelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		levelLabelLayout.setSpacing(true);
		componentList.add(levelLabelLayout);

		GtnUIFrameworkComponentConfig levelLabel = new GtnUIFrameworkComponentConfig();
		levelLabel.setComponentType(GtnUIFrameworkComponentType.LABEL);
		levelLabel.setComponentValue("Level:");
		levelLabel.setComponentId(nameSpace + "_" + "levelLable");
		levelLabel.setParentComponentId(levelLabelLayout.getComponentId());
		levelLabel.setAddToParent(true);
		
		GtnUIFrameworkLabelConfig levelLabelConfig = new GtnUIFrameworkLabelConfig();
		levelLabelConfig.setEnable(true);
		levelLabel.setGtnLabelConfig(levelLabelConfig);
		
		
		componentList.add(levelLabel);

	}
	
	private void addLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig actualsProjectionLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "levelLayout", true, parentComponentId);
		actualsProjectionLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(actualsProjectionLayoutConfig);

		GtnUIFrameworkComponentConfig levelOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "level", true, nameSpace + "_" + "levelLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		//levelOptionRadioGroup.setComponentName("Level:");
		levelOptionRadioGroup.setSpacing(true);

		GtnUIFrameworkOptionGroupConfig levelOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		levelOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Total", "Detail"}));
		levelOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		levelOptionGroupConfig.setEnable(Boolean.TRUE);
		levelOptionRadioGroup.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		levelOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(levelOptionGroupConfig);

		componentList.add(levelOptionRadioGroup);
	}

	
	private void addComparisonBasisComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig comparisonBasisLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "comparisonBasisLayout", true, parentComponentId);
		comparisonBasisLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		comparisonBasisLayout.setSpacing(true);
		componentList.add(comparisonBasisLayout);

		GtnUIFrameworkComponentConfig comparisonBasis = new GtnUIFrameworkComponentConfig();
		comparisonBasis.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparisonBasis.setComponentName("Comparion Basis:");
		comparisonBasis.setComponentId(nameSpace + "_" + "comparisonBasis");
		comparisonBasis.setParentComponentId(comparisonBasisLayout.getComponentId());
		comparisonBasis.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig comparisonBasisConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonBasisConfig.setComboBoxType("FORECAST_FREQUENCY");
		comparisonBasisConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comparisonBasis.setGtnComboboxConfig(comparisonBasisConfig);
		
		componentList.add(comparisonBasis);
	}
	private void addVariableCatagoryComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig variableCatagroyLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "variableCatagroyLayout", true, parentComponentId);
		variableCatagroyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		variableCatagroyLayout.setSpacing(true);
		componentList.add(variableCatagroyLayout);

		GtnUIFrameworkComponentConfig variableCatagroy = new GtnUIFrameworkComponentConfig();
		variableCatagroy.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableCatagroy.setComponentName("Variable Catagory:");
		variableCatagroy.setComponentId(nameSpace + "_" + "variableCatagroy");
		variableCatagroy.setParentComponentId(variableCatagroyLayout.getComponentId());
		variableCatagroy.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig variableCatagroyConfig = new GtnUIFrameworkComboBoxConfig();
		variableCatagroyConfig.setComboBoxType("FORECAST_FREQUENCY");
		variableCatagroyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableCatagroy.setGtnComboboxConfig(variableCatagroyConfig);
		
		componentList.add(variableCatagroy);

	}
	
	private void addProjectionPeriodOrderComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig projectionPeriodOrderLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "projectionPeriodOrderLayout", true, parentComponentId);
		projectionPeriodOrderLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionPeriodOrderLayoutConfig);

		GtnUIFrameworkComponentConfig projectionPeriodOrderLayoutOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "projectionPeriodOrder", true, nameSpace + "_" + "projectionPeriodOrderLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodOrderLayoutOptionRadioGroup.setComponentName("Projection Period Order:");
		projectionPeriodOrderLayoutOptionRadioGroup.setSpacing(true);
		
		GtnUIFrameworkOptionGroupConfig projectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionPeriodOrderOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending"}));
		projectionPeriodOrderOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionPeriodOrderOptionGroupConfig.setEnable(Boolean.TRUE);
		projectionPeriodOrderLayoutOptionRadioGroup.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		projectionPeriodOrderLayoutOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(projectionPeriodOrderOptionGroupConfig);

		componentList.add(projectionPeriodOrderLayoutOptionRadioGroup);
	}
	
	
	private void addDateRangeComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig dateRangeLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "dateRangeLayout", true, parentComponentId);
		dateRangeLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		dateRangeLayout.setSpacing(true);
		componentList.add(dateRangeLayout);

		GtnUIFrameworkComponentConfig dateRange = new GtnUIFrameworkComponentConfig();
		dateRange.setComponentType(GtnUIFrameworkComponentType.LABEL);
		dateRange.setComponentValue("Date Range:");
		dateRange.setComponentId(nameSpace + "_" + "dateRange");
		dateRange.setParentComponentId(dateRangeLayout.getComponentId());
		dateRange.setAddToParent(true);
		
		GtnUIFrameworkLabelConfig dateRangeLabelConfig = new GtnUIFrameworkLabelConfig();
		dateRangeLabelConfig.setEnable(true);
		dateRange.setGtnLabelConfig(dateRangeLabelConfig);
		
		
		componentList.add(dateRange);

	}
	
	
	private void addFromComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig fromLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "fromLayout", true, parentComponentId);
		fromLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		fromLayout.setSpacing(true);
		componentList.add(fromLayout);

		GtnUIFrameworkComponentConfig from = new GtnUIFrameworkComponentConfig();
		from.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		from.setComponentName("From:");
		from.setComponentId(nameSpace + "_" + "from");
		from.setParentComponentId(fromLayout.getComponentId());
		from.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig fromConfig = new GtnUIFrameworkComboBoxConfig();
		fromConfig.setComboBoxType("FORECAST_FREQUENCY");
		fromConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		from.setGtnComboboxConfig(fromConfig);
		
		componentList.add(from);

	}
	
	private void addDisplayFormatComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig displayFormatLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "displayFormatLayout", true, parentComponentId);
		displayFormatLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(displayFormatLayout);
		
		GtnUIFrameworkComponentConfig displayFormat = new GtnUIFrameworkComponentConfig();
		displayFormat.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		displayFormat.setComponentName("Display Format:");
		displayFormat.setComponentId(nameSpace + "_" + "displayFormat");
		displayFormat.setParentComponentId(displayFormatLayout.getComponentId());
		displayFormat.setAddToParent(true);
		componentList.add(displayFormat);

		GtnUIFrameworkCheckedComboBoxConfig displayFormatConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		displayFormatConfig.setCheckedComboBoxType("FORECAST_FREQUENCY");
		displayFormatConfig.setDefaultValue("-Select-");
		displayFormatConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		displayFormat.setGtnCheckedComboboxConfig(displayFormatConfig);
	}
		
	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig frequencyLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "frequencyLayout", true, parentComponentId);
		frequencyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		frequencyLayout.setSpacing(true);
		componentList.add(frequencyLayout);

		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		frequency.setComponentName("Frequency:");
		frequency.setComponentId(nameSpace + "_" + "frequency");
		frequency.setParentComponentId(frequencyLayout.getComponentId());
		frequency.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setComboBoxType("FORECAST_FREQUENCY");
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequency.setGtnComboboxConfig(frequencyConfig);
		
		componentList.add(frequency);
	}

	private void addDiscountLevelLabelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig discountLevelLableLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "discountLevelLableLayout", true, parentComponentId);
		discountLevelLableLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		discountLevelLableLayout.setSpacing(true);
		componentList.add(discountLevelLableLayout);

		GtnUIFrameworkComponentConfig discountLevelLable = new GtnUIFrameworkComponentConfig();
		discountLevelLable.setComponentType(GtnUIFrameworkComponentType.LABEL);
		discountLevelLable.setComponentValue("Discount Level:");
		discountLevelLable.setComponentId(nameSpace + "_" + "discountLevelLable");
		discountLevelLable.setParentComponentId(discountLevelLableLayout.getComponentId());
		discountLevelLable.setAddToParent(true);
		
		GtnUIFrameworkLabelConfig discountLevelLableConfig = new GtnUIFrameworkLabelConfig();
		discountLevelLableConfig.setEnable(true);
		discountLevelLable.setGtnLabelConfig(discountLevelLableConfig);
		
		
		componentList.add(discountLevelLable);

	}

	
	private void addDiscountLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig discountLevelLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "discountLevelLayout", true, parentComponentId);
		discountLevelLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(discountLevelLayoutConfig);

		GtnUIFrameworkComponentConfig discountLevelLayoutOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "level", true, nameSpace + "_" + "discountLevelLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		//discountLevelLayoutOptionRadioGroup.setComponentName("Discount Level:");
		discountLevelLayoutOptionRadioGroup.setSpacing(true);
		discountLevelLayoutOptionRadioGroup.setMargin(true);

		GtnUIFrameworkOptionGroupConfig disocountLevelOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		disocountLevelOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Total Discount", "Program Category", "Program"}));
		disocountLevelOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		disocountLevelOptionGroupConfig.setEnable(Boolean.TRUE);
		discountLevelLayoutOptionRadioGroup.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		discountLevelLayoutOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(disocountLevelOptionGroupConfig);

		componentList.add(discountLevelLayoutOptionRadioGroup);
	}
	
	private void addVariablesComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig variablesLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "variablesLayout", true, parentComponentId);
		variablesLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		variablesLayout.setSpacing(true);
		componentList.add(variablesLayout);

		GtnUIFrameworkComponentConfig variables = new GtnUIFrameworkComponentConfig();
		variables.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variables.setComponentName("Variables:");
		variables.setComponentId(nameSpace + "_" + "variables");
		variables.setParentComponentId(variablesLayout.getComponentId());
		variables.setAddToParent(true);
		variables.setSpacing(true);
		variables.setMargin(true);
		
		GtnUIFrameworkComboBoxConfig variablesConfig = new GtnUIFrameworkComboBoxConfig();
		variablesConfig.setComboBoxType("FORECAST_FREQUENCY");
		variablesConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variables.setGtnComboboxConfig(variablesConfig);
		
		componentList.add(variables);

	}

	private void addPivotViewComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig pivotViewLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "pivotViewLayout", true, parentComponentId);
		pivotViewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(pivotViewLayoutConfig);

		GtnUIFrameworkComponentConfig pivotViewOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "pivotView", true, nameSpace + "_" + "pivotViewLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		pivotViewOptionRadioGroup.setComponentName("Pivot View:");
		pivotViewOptionRadioGroup.setSpacing(true);

		GtnUIFrameworkOptionGroupConfig pivotOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		pivotOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Period", "Variable"}));
		pivotOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		pivotOptionGroupConfig.setEnable(Boolean.TRUE);
		pivotViewOptionRadioGroup.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		pivotViewOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(pivotOptionGroupConfig);

		componentList.add(pivotViewOptionRadioGroup);
	}
	
	private void addToComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig toLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "toLayout", true, parentComponentId);
		toLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		toLayout.setSpacing(true);
		componentList.add(toLayout);

		GtnUIFrameworkComponentConfig to = new GtnUIFrameworkComponentConfig();
		to.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		to.setComponentName("To:");
		to.setComponentId(nameSpace + "_" + "to");
		to.setParentComponentId(toLayout.getComponentId());
		to.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig toConfig = new GtnUIFrameworkComboBoxConfig();
		toConfig.setComboBoxType("FORECAST_FREQUENCY");
		toConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		to.setGtnComboboxConfig(toConfig);
		
		componentList.add(to);

	}
	
	private void addUnitOfMeasureComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig unitOfMeasureLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "unitOfMeasureLayout", true, parentComponentId);
		unitOfMeasureLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		unitOfMeasureLayout.setSpacing(true);
		componentList.add(unitOfMeasureLayout);

		GtnUIFrameworkComponentConfig unitOfMeasure = new GtnUIFrameworkComponentConfig();
		unitOfMeasure.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		unitOfMeasure.setComponentName("Unit of Measure:");
		unitOfMeasure.setComponentId(nameSpace + "_" + "unitOfMeasure");
		unitOfMeasure.setParentComponentId(unitOfMeasureLayout.getComponentId());
		unitOfMeasure.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig unitOfMeasureConfig = new GtnUIFrameworkComboBoxConfig();
		unitOfMeasureConfig.setComboBoxType("FORECAST_FREQUENCY");
		unitOfMeasureConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		unitOfMeasure.setGtnComboboxConfig(unitOfMeasureConfig);
		
		componentList.add(unitOfMeasure);

	}
	

	private void addCurrencyFormatComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig currencyFormatLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "currencyFormatLayout", true, parentComponentId);
		currencyFormatLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		currencyFormatLayout.setSpacing(true);
		componentList.add(currencyFormatLayout);

		GtnUIFrameworkComponentConfig currencyFormat = new GtnUIFrameworkComponentConfig();
		currencyFormat.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		currencyFormat.setComponentName("Currency Format:");
		currencyFormat.setComponentId(nameSpace + "_" + "currencyFormat");
		currencyFormat.setParentComponentId(currencyFormatLayout.getComponentId());
		currencyFormat.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig currencyFormatConfig = new GtnUIFrameworkComboBoxConfig();
		currencyFormatConfig.setComboBoxType("FORECAST_FREQUENCY");
		currencyFormatConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		currencyFormat.setGtnComboboxConfig(currencyFormatConfig);
		
		componentList.add(currencyFormat);
	}

	private void addFilterTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig filterLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "filterLayoutConfig", false, null);
		filterLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		filterLayoutConfig.setTabComponent(true);
		componentList.add(filterLayoutConfig);

		addFilterInnerLayout(componentList,filterLayoutConfig.getComponentId(), nameSpace);	
	}

	private void addFilterInnerLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkLayoutConfig filterInnerLayout = new GtnUIFrameworkLayoutConfig();
		filterInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		filterInnerLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfig.setComponentId(nameSpace + "_" + "filterInnerLayout");
		filterInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfig.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		filterInnerLayoutConfig.setParentComponentId(parentComponentId);
		filterInnerLayoutConfig.setGtnLayoutConfig(filterInnerLayout);
		componentList.add(filterInnerLayoutConfig);
		
		addCustomerLevel(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		addProductLevel(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		addDeductionLevel(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		addSalesInclusion(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		addCustomerFilter(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);	
		addProductFilter(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		addDeductionFilter(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		addDeductionInclusion(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
	}

	private void addCustomerLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerLevelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customerLevelLayout", true, parentComponentId);
		customerLevelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerLevelLayout);
		
		GtnUIFrameworkComponentConfig customerLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "customerLevel", true, customerLevelLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerLevel.setComponentName("Customer Level");

		GtnUIFrameworkComboBoxConfig customerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		customerLevelConfig.setComboBoxType("FORECAST_FREQUENCY");
		customerLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerLevel.setGtnComboboxConfig(customerLevelConfig);
		componentList.add(customerLevel);
	}

	private void addProductLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig productLevelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productLevelLayout", true, parentComponentId);
		productLevelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productLevelLayout);
		
		GtnUIFrameworkComponentConfig productLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "productLevel", true, productLevelLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productLevel.setComponentName("Product Level");

		GtnUIFrameworkComboBoxConfig productLevelConfig = new GtnUIFrameworkComboBoxConfig();
		productLevelConfig.setComboBoxType("FORECAST_FREQUENCY");
		productLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productLevel.setGtnComboboxConfig(productLevelConfig);
		componentList.add(productLevel);
	}

	private void addDeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionLevelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionLevelLayout", true, parentComponentId);
		deductionLevelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionLevelLayout);
		
		GtnUIFrameworkComponentConfig deductionLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "deductionLevel", true, deductionLevelLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionLevel.setComponentName("Deduction Level");

		GtnUIFrameworkComboBoxConfig deductionLevelConfig = new GtnUIFrameworkComboBoxConfig();
		deductionLevelConfig.setComboBoxType("FORECAST_FREQUENCY");
		deductionLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionLevel.setGtnComboboxConfig(deductionLevelConfig);
		componentList.add(deductionLevel);
	}
	private void addSalesInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig salesInclusionLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "salesInclusionLayout", true, parentComponentId);
		salesInclusionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesInclusionLayout);
		
		GtnUIFrameworkComponentConfig salesInclusion = new GtnUIFrameworkComponentConfig();
		salesInclusion.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		salesInclusion.setComponentName("Sales Inclusion");
		salesInclusion.setComponentId(nameSpace + "_" + "salesInclusion");
		salesInclusion.setParentComponentId(salesInclusionLayout.getComponentId());
		salesInclusion.setAddToParent(true);
		componentList.add(salesInclusion);

		GtnUIFrameworkCheckedComboBoxConfig salesInclusionConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		salesInclusionConfig.setCheckedComboBoxType("FORECAST_FREQUENCY");
		salesInclusionConfig.setDefaultValue("-Select One-");
		salesInclusionConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		salesInclusion.setGtnCheckedComboboxConfig(salesInclusionConfig);
	}

	private void addCustomerFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customerFilterLayout", true, parentComponentId);
		customerFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerFilterLayout);
		
		GtnUIFrameworkComponentConfig customerFilter = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "customerFilter", true, customerFilterLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		customerFilter.setComponentName("Customer Filter");
		componentList.add(customerFilter);

		GtnUIFrameworkCheckedComboBoxConfig customerFilterConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		customerFilterConfig.setCheckedComboBoxType("FORECAST_FREQUENCY");
		customerFilterConfig.setDefaultValue("-Select-");
		customerFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerFilter.setGtnCheckedComboboxConfig(customerFilterConfig);
	}

	private void addProductFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig productFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productFilterLayout", true, parentComponentId);
		productFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productFilterLayout);
		
		GtnUIFrameworkComponentConfig productFilter = new GtnUIFrameworkComponentConfig();
		productFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		productFilter.setComponentName("Product Filter");
		productFilter.setComponentId(nameSpace + "_" + "productFilter");
		productFilter.setParentComponentId(productFilterLayout.getComponentId());
		productFilter.setAddToParent(true);
		componentList.add(productFilter);

		GtnUIFrameworkCheckedComboBoxConfig productFilterConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		productFilterConfig.setCheckedComboBoxType("FORECAST_FREQUENCY");
		productFilterConfig.setDefaultValue("-Select-");
		productFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productFilter.setGtnCheckedComboboxConfig(productFilterConfig);
	}

	private void addDeductionFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionFilterLayout", true, parentComponentId);
		deductionFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionFilterLayout);
		
		GtnUIFrameworkComponentConfig deductionFilter = new GtnUIFrameworkComponentConfig();
		deductionFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionFilter.setComponentName("Deduction Filter");
		deductionFilter.setComponentId(nameSpace + "_" + "deductionFilter");
		deductionFilter.setParentComponentId(deductionFilterLayout.getComponentId());
		deductionFilter.setAddToParent(true);
		componentList.add(deductionFilter);

		GtnUIFrameworkCheckedComboBoxConfig deductionFilterConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionFilterConfig.setCheckedComboBoxType("FORECAST_FREQUENCY");
		deductionFilterConfig.setDefaultValue("-Select-");
		deductionFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionFilter.setGtnCheckedComboboxConfig(deductionFilterConfig);
	}

	private void addDeductionInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionInclusionLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionInclusionLayout", true, parentComponentId);
		deductionInclusionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionInclusionLayout);
		
		GtnUIFrameworkComponentConfig deductionInclusion = new GtnUIFrameworkComponentConfig();
		deductionInclusion.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionInclusion.setComponentName("Deduction Inclusion");
		deductionInclusion.setComponentId(nameSpace + "_" + "deductionInclusion");
		deductionInclusion.setParentComponentId(deductionInclusionLayout.getComponentId());
		deductionInclusion.setAddToParent(true);
		componentList.add(deductionInclusion);

		GtnUIFrameworkCheckedComboBoxConfig deductionInclusionConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionInclusionConfig.setCheckedComboBoxType("FORECAST_FREQUENCY");
		deductionInclusionConfig.setDefaultValue("-Select One-");
		deductionInclusionConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionInclusion.setGtnCheckedComboboxConfig(deductionInclusionConfig);
	}
}
