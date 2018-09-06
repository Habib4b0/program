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

	public void addDiscountProjectionFilterTabLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {

		GtnUIFrameworkComponentConfig discountProjFilterLayoutCommonConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "discountProjFilterLayoutConfig", false, null);
		discountProjFilterLayoutCommonConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		discountProjFilterLayoutCommonConfig.setTabComponent(true);
		componentList.add(discountProjFilterLayoutCommonConfig);

		GtnUIFrameworkLayoutConfig discountProjFiilterInnerCommonLayout = new GtnUIFrameworkLayoutConfig();
		discountProjFiilterInnerCommonLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		discountProjFiilterInnerCommonLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutCommonConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutCommonConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutCommonConfig.setComponentId(nameSpace + "_" + "discountProjFiilterInnerLayout");
		filterInnerLayoutCommonConfig.setAddToParent(true);
		filterInnerLayoutCommonConfig.setSpacing(true);
		filterInnerLayoutCommonConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutCommonConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		filterInnerLayoutCommonConfig.setParentComponentId(discountProjFilterLayoutCommonConfig.getComponentId());
		filterInnerLayoutCommonConfig.setGtnLayoutConfig(discountProjFiilterInnerCommonLayout);
		componentList.add(filterInnerLayoutCommonConfig);

	}

	public void addSalesProjectionFilterTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig salesProjFilterLayoutCommonConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "salesProjFilterLayoutConfig", false, null);
		salesProjFilterLayoutCommonConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		salesProjFilterLayoutCommonConfig.setTabComponent(true);
		componentList.add(salesProjFilterLayoutCommonConfig);

		GtnUIFrameworkLayoutConfig salesProjFilterInnerCommonLayout = new GtnUIFrameworkLayoutConfig();
		salesProjFilterInnerCommonLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		salesProjFilterInnerCommonLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutCommonConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutCommonConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutCommonConfig.setComponentId(nameSpace + "_" + "salesProjFilterInnerLayout");
		filterInnerLayoutCommonConfig.setAddToParent(true);
		filterInnerLayoutCommonConfig.setSpacing(true);
		filterInnerLayoutCommonConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutCommonConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		filterInnerLayoutCommonConfig.setParentComponentId(salesProjFilterLayoutCommonConfig.getComponentId());
		filterInnerLayoutCommonConfig.setGtnLayoutConfig(salesProjFilterInnerCommonLayout);
		componentList.add(filterInnerLayoutCommonConfig);

	}

	public void addCustomerLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customerLevelCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customerLevelLayout", true, parentComponentId);
		customerLevelCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customerLevelCommonLayout);

		GtnUIFrameworkComponentConfig customerCommonLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "customerLevel", true, customerLevelCommonLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerCommonLevel.setComponentName("Customer Level:");

		GtnUIFrameworkComboBoxConfig customerLevelCommonConfig = new GtnUIFrameworkComboBoxConfig();
		customerLevelCommonConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		customerLevelCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerCommonLevel.setGtnComboboxConfig(customerLevelCommonConfig);
		componentList.add(customerCommonLevel);
	}

	public void addProductLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig productLevelCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productLevelLayout", true, parentComponentId);
		productLevelCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productLevelCommonLayout);

		GtnUIFrameworkComponentConfig productCommonLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "productLevel", true, productLevelCommonLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productCommonLevel.setComponentName("Product Level:");

		GtnUIFrameworkComboBoxConfig productLevelCommonConfig = new GtnUIFrameworkComboBoxConfig();
		productLevelCommonConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		productLevelCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productCommonLevel.setGtnComboboxConfig(productLevelCommonConfig);
		componentList.add(productCommonLevel);
	}

	public void addDeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionLevelCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionLevelLayout", true, parentComponentId);
		deductionLevelCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionLevelCommonLayout);

		GtnUIFrameworkComponentConfig deductionCommonLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "deductionLevel", true, deductionLevelCommonLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionCommonLevel.setComponentName("Deduction Level:");

		GtnUIFrameworkComboBoxConfig deductionLevelCommonConfig = new GtnUIFrameworkComboBoxConfig();
		deductionLevelCommonConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionLevelCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionCommonLevel.setGtnComboboxConfig(deductionLevelCommonConfig);
		componentList.add(deductionCommonLevel);
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
		GtnUIFrameworkComponentConfig productFilterCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "productFilterLayout", true, parentComponentId);
		productFilterCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(productFilterCommonLayout);

		GtnUIFrameworkComponentConfig productCommonFilter = new GtnUIFrameworkComponentConfig();
		productCommonFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		productCommonFilter.setComponentName("Product Filter:");
		productCommonFilter.setComponentId(nameSpace + "_" + "productFilter");
		productCommonFilter.setParentComponentId(productFilterCommonLayout.getComponentId());
		productCommonFilter.setAddToParent(true);
		componentList.add(productCommonFilter);

		GtnUIFrameworkCheckedComboBoxConfig productFilterCommonConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		productFilterCommonConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		productFilterCommonConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		productFilterCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productCommonFilter.setGtnCheckedComboboxConfig(productFilterCommonConfig);
	}

	public void addDeductionFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionFilterCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionFilterLayout", true, parentComponentId);
		deductionFilterCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionFilterCommonLayout);

		GtnUIFrameworkComponentConfig deductionCommonFilter = new GtnUIFrameworkComponentConfig();
		deductionCommonFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionCommonFilter.setComponentName("Deduction Filter:");
		deductionCommonFilter.setComponentId(nameSpace + "_" + "deductionFilter");
		deductionCommonFilter.setParentComponentId(deductionFilterCommonLayout.getComponentId());
		deductionCommonFilter.setAddToParent(true);
		componentList.add(deductionCommonFilter);

		GtnUIFrameworkCheckedComboBoxConfig deductionFilterCommonConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionFilterCommonConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionFilterCommonConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		deductionFilterCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionCommonFilter.setGtnCheckedComboboxConfig(deductionFilterCommonConfig);
	}

	public void addDeductionInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig deductionInclusionCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "deductionInclusionLayout", true, parentComponentId);
		deductionInclusionCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(deductionInclusionCommonLayout);

		GtnUIFrameworkComponentConfig deductionCommonInclusion = new GtnUIFrameworkComponentConfig();
		deductionCommonInclusion.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionCommonInclusion.setComponentName("Deduction Inclusion:");
		deductionCommonInclusion.setComponentId(nameSpace + "_" + "deductionInclusion");
		deductionCommonInclusion.setParentComponentId(deductionInclusionCommonLayout.getComponentId());
		deductionCommonInclusion.setAddToParent(true);
		componentList.add(deductionCommonInclusion);

		GtnUIFrameworkCheckedComboBoxConfig deductionInclusionCommonConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionInclusionCommonConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		deductionInclusionCommonConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		deductionInclusionCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionCommonInclusion.setGtnCheckedComboboxConfig(deductionInclusionCommonConfig);
	}

	public void addSalesInclusion(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig salesInclusionCommonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "salesInclusionLayout", true, parentComponentId);
		salesInclusionCommonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesInclusionCommonLayout);

		GtnUIFrameworkComponentConfig salesCommonInclusion = new GtnUIFrameworkComponentConfig();
		salesCommonInclusion.setComponentType(GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		salesCommonInclusion.setComponentName("Sales Inclusion:");
		salesCommonInclusion.setComponentId(nameSpace + "_" + "salesInclusion");
		salesCommonInclusion.setParentComponentId(salesInclusionCommonLayout.getComponentId());
		salesCommonInclusion.setAddToParent(true);
		componentList.add(salesCommonInclusion);

		GtnUIFrameworkCheckedComboBoxConfig salesInclusionCommonConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		salesInclusionCommonConfig.setCheckedComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		salesInclusionCommonConfig.setDefaultValue(GtnFrameworkScreenRegisteryConstants.COMBOX_DEFAULT_VALUE);
		salesInclusionCommonConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		salesCommonInclusion.setGtnCheckedComboboxConfig(salesInclusionCommonConfig);
	}
}
