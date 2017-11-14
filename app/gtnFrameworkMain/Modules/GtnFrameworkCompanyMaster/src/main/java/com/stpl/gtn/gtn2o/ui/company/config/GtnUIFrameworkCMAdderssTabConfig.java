package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkCMAdderssTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addAddressTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout1 = configProvider.getCssLayoutConfig("parentCssLayout", false, null);
		gtnLayout1.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout1.addComponentStyle("gtnFrameworkCol-5");
		gtnLayout1.addComponentStyle("stpl-padding-10");
		gtnLayout1.setAuthorizationIncluded(true);
		gtnLayout1.setTabComponent(true);
		gtnLayout1.setMargin(true);
		componentList.add(gtnLayout1);

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT, true, "parentCssLayout");
		gtnLayout.addComponentStyle("gtnGrid-single-ln-layout-1");
		componentList.add(gtnLayout);

		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addAdderss1(componentList);
		addCity(componentList);
		addZipCode(componentList);
		addCountry(componentList);
		addAddress2(componentList);
		addState(componentList);
		addRegionCode(componentList);
	}

	private void addAdderss1(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalAddress1Layout",
				true, GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"addressTabAdderss1", true, "horizontalAddress1Layout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Address1");
		companyIdConfig.setAuthorizationIncluded(true);

		componentList.add(companyIdConfig);
	}

	private void addCity(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalCityLayout", true,
				GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig("addressTabCity",
				true, "horizontalCityLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("City");
		companyIdConfig.setAuthorizationIncluded(true);

		componentList.add(companyIdConfig);
	}

	private void addZipCode(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalZipLayout", true,
				GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"addressTabZipCode", true, "horizontalZipLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Zip Code");
		companyIdConfig.setAuthorizationIncluded(true);

		componentList.add(companyIdConfig);
	}

	private void addCountry(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalCountryLayout",
				true, GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig("addressTabCountry",
				true, "horizontalCountryLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentName("Country");
		companyStatus.setAuthorizationIncluded(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("COUNTRY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addAddress2(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalAddress2Layout",
				true, GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"addressTabAddress2", true, "horizontalAddress2Layout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Address2");
		companyIdConfig.setAuthorizationIncluded(true);

		componentList.add(companyIdConfig);
	}

	private void addState(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalStateLayout",
				true, GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig("addressTabState",
				true, "horizontalStateLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentName("State");
		companyStatus.setAuthorizationIncluded(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRegionCode(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("horizontalTabRegionLayout",
				true, GtnFrameworkCompanyStringContants.ADDRESS_TAB_CSS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"addressTabRegionCode", true, "horizontalTabRegionLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Region Code");
		companyIdConfig.setAuthorizationIncluded(true);

		componentList.add(companyIdConfig);
	}
}
