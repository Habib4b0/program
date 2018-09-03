package com.stpl.gtn.gtn2o.registry.config.common;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class FilterTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	
	public void addDiscountProjectionFilterTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig discountProjFilterLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "discountProjFilterLayoutConfig", false, null);
		discountProjFilterLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		discountProjFilterLayoutConfig.setTabComponent(true);
		componentList.add(discountProjFilterLayoutConfig);

		GtnUIFrameworkLayoutConfig discountProjFiilterInnerLayoutFilterTab = new GtnUIFrameworkLayoutConfig();
		discountProjFiilterInnerLayoutFilterTab.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		discountProjFiilterInnerLayoutFilterTab.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfigFilterTab = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfigFilterTab.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfigFilterTab.setComponentId(nameSpace + "_" + "discountProjFiilterInnerLayout");
		filterInnerLayoutConfigFilterTab.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfigFilterTab.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfigFilterTab.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfigFilterTab.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		filterInnerLayoutConfigFilterTab.setParentComponentId(discountProjFilterLayoutConfig.getComponentId());
		filterInnerLayoutConfigFilterTab.setGtnLayoutConfig(discountProjFiilterInnerLayoutFilterTab);
		componentList.add(filterInnerLayoutConfigFilterTab);
		
	}

	public void addSalesProjectionFilterTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig salesProjFilterLayoutConfigFilterTab = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "salesProjFilterLayoutConfig", false, null);
		salesProjFilterLayoutConfigFilterTab.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		salesProjFilterLayoutConfigFilterTab.setTabComponent(true);
		componentList.add(salesProjFilterLayoutConfigFilterTab);

		GtnUIFrameworkLayoutConfig salesProjFilterInnerLayoutFilterTab = new GtnUIFrameworkLayoutConfig();
		salesProjFilterInnerLayoutFilterTab.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		salesProjFilterInnerLayoutFilterTab.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfigFilterTab = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfigFilterTab.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfigFilterTab.setComponentId(nameSpace + "_" + "salesProjFilterInnerLayout");
		filterInnerLayoutConfigFilterTab.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfigFilterTab.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfigFilterTab.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfigFilterTab.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		filterInnerLayoutConfigFilterTab.setParentComponentId(salesProjFilterLayoutConfigFilterTab.getComponentId());
		filterInnerLayoutConfigFilterTab.setGtnLayoutConfig(salesProjFilterInnerLayoutFilterTab);
		componentList.add(filterInnerLayoutConfigFilterTab);
		
	}

	public void addCustomerLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerLevelLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customerLevelLayout", true, parentComponentId);
		customerLevelLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerLevelLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig customerLevelFilterTab = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "customerLevel", true, customerLevelLayoutFilterTab.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerLevelFilterTab.setComponentName("Customer Level:");

		GtnUIFrameworkComboBoxConfig customerLevelConfigFilterTab = new GtnUIFrameworkComboBoxConfig();
		customerLevelConfigFilterTab.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		customerLevelConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerLevelFilterTab.setGtnComboboxConfig(customerLevelConfigFilterTab);
		componentList.add(customerLevelFilterTab);
	}

	public void addProductLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig productLevelLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productLevelLayout", true, parentComponentId);
		productLevelLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productLevelLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig productLevelFilterTab = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "productLevel", true, productLevelLayoutFilterTab.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productLevelFilterTab.setComponentName("Product Level:");

		GtnUIFrameworkComboBoxConfig productLevelConfigFilterTab = new GtnUIFrameworkComboBoxConfig();
		productLevelConfigFilterTab.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		productLevelConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productLevelFilterTab.setGtnComboboxConfig(productLevelConfigFilterTab);
		componentList.add(productLevelFilterTab);
	}

	public void addDeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionLevelLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionLevelLayout", true, parentComponentId);
		deductionLevelLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionLevelLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig deductionLevelFilterTab = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "deductionLevel", true, deductionLevelLayoutFilterTab.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionLevelFilterTab.setComponentName("Deduction Level:");

		GtnUIFrameworkComboBoxConfig deductionLevelConfigFilterTab = new GtnUIFrameworkComboBoxConfig();
		deductionLevelConfigFilterTab.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionLevelConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionLevelFilterTab.setGtnComboboxConfig(deductionLevelConfigFilterTab);
		componentList.add(deductionLevelFilterTab);
	}

	public void addCustomerFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customerFilterLayout", true, parentComponentId);
		customerFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerFilterLayout);
		
		GtnUIFrameworkComponentConfig customerFilter = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "customerFilter", true, customerFilterLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		customerFilter.setComponentName("Customer Filter:");
		componentList.add(customerFilter);

		GtnUIFrameworkCheckedComboBoxConfig customerFilterConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		customerFilterConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		customerFilterConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		customerFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerFilter.setGtnCheckedComboboxConfig(customerFilterConfig);
	}

	public void addProductFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig productFilterLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productFilterLayout", true, parentComponentId);
		productFilterLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productFilterLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig productFilterFilterTab = new GtnUIFrameworkComponentConfig();
		productFilterFilterTab.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		productFilterFilterTab.setComponentName("Product Filter:");
		productFilterFilterTab.setComponentId(nameSpace + "_" + "productFilter");
		productFilterFilterTab.setParentComponentId(productFilterLayoutFilterTab.getComponentId());
		productFilterFilterTab.setAddToParent(true);
		componentList.add(productFilterFilterTab);

		GtnUIFrameworkCheckedComboBoxConfig productFilterConfigFilterTab = new GtnUIFrameworkCheckedComboBoxConfig();
		productFilterConfigFilterTab.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		productFilterConfigFilterTab.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		productFilterConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productFilterFilterTab.setGtnCheckedComboboxConfig(productFilterConfigFilterTab);
	}

	public void addDeductionFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionFilterLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionFilterLayout", true, parentComponentId);
		deductionFilterLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionFilterLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig deductionFilterFilterTab = new GtnUIFrameworkComponentConfig();
		deductionFilterFilterTab.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionFilterFilterTab.setComponentName("Deduction Filter:");
		deductionFilterFilterTab.setComponentId(nameSpace + "_" + "deductionFilter");
		deductionFilterFilterTab.setParentComponentId(deductionFilterLayoutFilterTab.getComponentId());
		deductionFilterFilterTab.setAddToParent(true);
		componentList.add(deductionFilterFilterTab);

		GtnUIFrameworkCheckedComboBoxConfig deductionFilterConfigFilterTab = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionFilterConfigFilterTab.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionFilterConfigFilterTab.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		deductionFilterConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionFilterFilterTab.setGtnCheckedComboboxConfig(deductionFilterConfigFilterTab);
	}

	public void addDeductionInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionInclusionLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionInclusionLayout", true, parentComponentId);
		deductionInclusionLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionInclusionLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig deductionInclusionFilterTab = new GtnUIFrameworkComponentConfig();
		deductionInclusionFilterTab.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionInclusionFilterTab.setComponentName("Deduction Inclusion:");
		deductionInclusionFilterTab.setComponentId(nameSpace + "_" + "deductionInclusion");
		deductionInclusionFilterTab.setParentComponentId(deductionInclusionLayoutFilterTab.getComponentId());
		deductionInclusionFilterTab.setAddToParent(true);
		componentList.add(deductionInclusionFilterTab);

		GtnUIFrameworkCheckedComboBoxConfig deductionInclusionConfigFilterTab = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionInclusionConfigFilterTab.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionInclusionConfigFilterTab.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		deductionInclusionConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionInclusionFilterTab.setGtnCheckedComboboxConfig(deductionInclusionConfigFilterTab);
	}
	
	public void addSalesInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig salesInclusionLayoutFilterTab = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "salesInclusionLayout", true, parentComponentId);
		salesInclusionLayoutFilterTab.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesInclusionLayoutFilterTab);
		
		GtnUIFrameworkComponentConfig salesInclusionFilterTab = new GtnUIFrameworkComponentConfig();
		salesInclusionFilterTab.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		salesInclusionFilterTab.setComponentName("Sales Inclusion:");
		salesInclusionFilterTab.setComponentId(nameSpace + "_" + "salesInclusion");
		salesInclusionFilterTab.setParentComponentId(salesInclusionLayoutFilterTab.getComponentId());
		salesInclusionFilterTab.setAddToParent(true);
		componentList.add(salesInclusionFilterTab);

		GtnUIFrameworkCheckedComboBoxConfig salesInclusionConfigFilterTab = new GtnUIFrameworkCheckedComboBoxConfig();
		salesInclusionConfigFilterTab.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		salesInclusionConfigFilterTab.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		salesInclusionConfigFilterTab.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		salesInclusionFilterTab.setGtnCheckedComboboxConfig(salesInclusionConfigFilterTab);
	}
}
