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

		GtnUIFrameworkLayoutConfig discountProjFiilterInnerLayout = new GtnUIFrameworkLayoutConfig();
		discountProjFiilterInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		discountProjFiilterInnerLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfig.setComponentId(nameSpace + "_" + "discountProjFiilterInnerLayout");
		filterInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfig.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		filterInnerLayoutConfig.setParentComponentId(discountProjFilterLayoutConfig.getComponentId());
		filterInnerLayoutConfig.setGtnLayoutConfig(discountProjFiilterInnerLayout);
		componentList.add(filterInnerLayoutConfig);
		
	}

	public void addSalesProjectionFilterTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig salesProjFilterLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "salesProjFilterLayoutConfig", false, null);
		salesProjFilterLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		salesProjFilterLayoutConfig.setTabComponent(true);
		componentList.add(salesProjFilterLayoutConfig);

		GtnUIFrameworkLayoutConfig salesProjFilterInnerLayout = new GtnUIFrameworkLayoutConfig();
		salesProjFilterInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		salesProjFilterInnerLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfig.setComponentId(nameSpace + "_" + "salesProjFilterInnerLayout");
		filterInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfig.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		filterInnerLayoutConfig.setParentComponentId(salesProjFilterLayoutConfig.getComponentId());
		filterInnerLayoutConfig.setGtnLayoutConfig(salesProjFilterInnerLayout);
		componentList.add(filterInnerLayoutConfig);
		
	}

	public void addCustomerLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerLevelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customerLevelLayout", true, parentComponentId);
		customerLevelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerLevelLayout);
		
		GtnUIFrameworkComponentConfig customerLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "customerLevel", true, customerLevelLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerLevel.setComponentName("Customer Level:");

		GtnUIFrameworkComboBoxConfig customerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		customerLevelConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		customerLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerLevel.setGtnComboboxConfig(customerLevelConfig);
		componentList.add(customerLevel);
	}

	public void addProductLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig productLevelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productLevelLayout", true, parentComponentId);
		productLevelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productLevelLayout);
		
		GtnUIFrameworkComponentConfig productLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "productLevel", true, productLevelLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productLevel.setComponentName("Product Level:");

		GtnUIFrameworkComboBoxConfig productLevelConfig = new GtnUIFrameworkComboBoxConfig();
		productLevelConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		productLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productLevel.setGtnComboboxConfig(productLevelConfig);
		componentList.add(productLevel);
	}

	public void addDeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionLevelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionLevelLayout", true, parentComponentId);
		deductionLevelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionLevelLayout);
		
		GtnUIFrameworkComponentConfig deductionLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "deductionLevel", true, deductionLevelLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionLevel.setComponentName("Deduction Level:");

		GtnUIFrameworkComboBoxConfig deductionLevelConfig = new GtnUIFrameworkComboBoxConfig();
		deductionLevelConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionLevel.setGtnComboboxConfig(deductionLevelConfig);
		componentList.add(deductionLevel);
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
		GtnUIFrameworkComponentConfig productFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productFilterLayout", true, parentComponentId);
		productFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productFilterLayout);
		
		GtnUIFrameworkComponentConfig productFilter = new GtnUIFrameworkComponentConfig();
		productFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		productFilter.setComponentName("Product Filter:");
		productFilter.setComponentId(nameSpace + "_" + "productFilter");
		productFilter.setParentComponentId(productFilterLayout.getComponentId());
		productFilter.setAddToParent(true);
		componentList.add(productFilter);

		GtnUIFrameworkCheckedComboBoxConfig productFilterConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		productFilterConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		productFilterConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		productFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productFilter.setGtnCheckedComboboxConfig(productFilterConfig);
	}

	public void addDeductionFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionFilterLayout", true, parentComponentId);
		deductionFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionFilterLayout);
		
		GtnUIFrameworkComponentConfig deductionFilter = new GtnUIFrameworkComponentConfig();
		deductionFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionFilter.setComponentName("Deduction Filter:");
		deductionFilter.setComponentId(nameSpace + "_" + "deductionFilter");
		deductionFilter.setParentComponentId(deductionFilterLayout.getComponentId());
		deductionFilter.setAddToParent(true);
		componentList.add(deductionFilter);

		GtnUIFrameworkCheckedComboBoxConfig deductionFilterConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionFilterConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionFilterConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		deductionFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionFilter.setGtnCheckedComboboxConfig(deductionFilterConfig);
	}

	public void addDeductionInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionInclusionLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionInclusionLayout", true, parentComponentId);
		deductionInclusionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionInclusionLayout);
		
		GtnUIFrameworkComponentConfig deductionInclusion = new GtnUIFrameworkComponentConfig();
		deductionInclusion.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionInclusion.setComponentName("Deduction Inclusion:");
		deductionInclusion.setComponentId(nameSpace + "_" + "deductionInclusion");
		deductionInclusion.setParentComponentId(deductionInclusionLayout.getComponentId());
		deductionInclusion.setAddToParent(true);
		componentList.add(deductionInclusion);

		GtnUIFrameworkCheckedComboBoxConfig deductionInclusionConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionInclusionConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionInclusionConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		deductionInclusionConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionInclusion.setGtnCheckedComboboxConfig(deductionInclusionConfig);
	}
	
	public void addSalesInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig salesInclusionLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "salesInclusionLayout", true, parentComponentId);
		salesInclusionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesInclusionLayout);
		
		GtnUIFrameworkComponentConfig salesInclusion = new GtnUIFrameworkComponentConfig();
		salesInclusion.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		salesInclusion.setComponentName("Sales Inclusion:");
		salesInclusion.setComponentId(nameSpace + "_" + "salesInclusion");
		salesInclusion.setParentComponentId(salesInclusionLayout.getComponentId());
		salesInclusion.setAddToParent(true);
		componentList.add(salesInclusion);

		GtnUIFrameworkCheckedComboBoxConfig salesInclusionConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		salesInclusionConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		salesInclusionConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		salesInclusionConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		salesInclusion.setGtnCheckedComboboxConfig(salesInclusionConfig);
	}
}
