package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkCompanyInformationTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addCompanyInformationTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("companyInformationTab",
				false, null);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT, true, "companyInformationTab",
				GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		componentList.add(cssGtnLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCompanyId(componentList);
		addCompanyNo(componentList);
		addCompanyName(componentList);
		addCompanyStatus(componentList);
		addCompanyStartDate(componentList);
		addCompanyEndDate(componentList);
		addCompanyCategory(componentList);
		addCompanyGroup(componentList);
		addCompanyType(componentList);
		addOrganizationKey(componentList);
		addSource(componentList);
		addFinancialSystem(componentList);
		addSystemId(componentList);
		addUdc1(componentList);
		addUdc2(componentList);
		addUdc3(componentList);
		addUdc4(componentList);
		addUdc5(componentList);
		addUdc6(componentList);
		addCreatedBy(componentList);
		addCreatedDate(componentList);
		addModifiedBy(componentList);
		addModifiedDate(componentList);
		addCreatedByName(componentList);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyIdlayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyId", true, "companyInformationTabCompanyIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Company ID");
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(companyIdConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyNolayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyNo", true, "companyInformationTabCompanyNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentName("Company No");
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(GtnWsNumericConstants.FIVE);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyNamelayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyName", true, "companyInformationTabCompanyNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentName("Company Name");
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(companyNameConfig);
	}

	private void addCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyStatuslayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyStatus", true, "companyInformationTabCompanyStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentName("Company Status");
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addCompanyStartDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationCompanyStartDatelayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyStartDate = configProvider.getUIFrameworkComponentConfig(
				"companyInformationCompanyStartDate", true, "companyInformationCompanyStartDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyStartDate.setAuthorizationIncluded(true);
		companyInformationCompanyStartDate.setComponentName("Company Start Date");
		companyInformationCompanyStartDate
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(companyInformationCompanyStartDate);
	}

	private void addCompanyEndDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationCompanyEndDatelayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyEndDate = configProvider.getUIFrameworkComponentConfig(
				"companyInformationCompanyEndDate", true, "companyInformationCompanyEndDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyEndDate.setAuthorizationIncluded(true);
		companyInformationCompanyEndDate.setComponentName("Company End Date");

		componentList.add(companyInformationCompanyEndDate);
	}

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyTypelayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyType", true, "companyInformationTabCompanyTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Company Type");
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_CUSTOM_COMPANY_TYPE_VALUE_CHANGE);
		actionConfigList.add(customAction);
		companyType.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCompanyCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyCategorylayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyCategory = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyCategory", true, "companyInformationTabCompanyCategorylayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyCategory.setAuthorizationIncluded(true);
		companyCategory.setComponentName("Company Category");

		componentList.add(companyCategory);

		GtnUIFrameworkComboBoxConfig companyCategoryConfig = configProvider.getComboBoxConfig("COMPANY_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyCategory.setGtnComboboxConfig(companyCategoryConfig);

	}

	private void addCompanyGroup(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCompanyGrouplayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyGroup = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCompanyGroup", true, "companyInformationTabCompanyGrouplayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyGroup.setAuthorizationIncluded(true);
		companyGroup.setComponentName("Company Group");

		componentList.add(companyGroup);

		GtnUIFrameworkComboBoxConfig companyGroupConfig = configProvider.getComboBoxConfig("COMPANY_GROUP",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyGroup.setGtnComboboxConfig(companyGroupConfig);

	}

	private void addOrganizationKey(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabOrganizationKeylayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabOrganizationKey", true, "companyInformationTabOrganizationKeylayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Organization Key");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("ORGANIZATION_KEY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addSource(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabSourcelayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabSource", true, "companyInformationTabSourcelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Source");

		componentList.add(companyIdConfig);
	}

	private void addFinancialSystem(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabFinancialSystemlayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabFinancialSystem", true, "companyInformationTabFinancialSystemlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Financial System");

		componentList.add(companyIdConfig);
	}

	private void addSystemId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabSystemIdlayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabSystemId", true, "companyInformationTabSystemIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("System Id");

		componentList.add(companyIdConfig);
	}

	private void addUdc1(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabUdc1layout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabUdc1", true, "companyInformationTabUdc1layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC1");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMP_UDC1",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addUdc2(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabUdc2layout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabUdc2", true, "companyInformationTabUdc2layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC2");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMP_UDC2",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addUdc3(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabUdc3layout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabUdc3", true, "companyInformationTabUdc3layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("UDC3");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMP_UDC3",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addUdc4(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabUdc4layout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabUdc4", true, "companyInformationTabUdc4layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("UDC4");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMP_UDC4",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addUdc5(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabUdc5layout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabUdc5", true, "companyInformationTabUdc5layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC5");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMP_UDC5",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addUdc6(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabUdc6layout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabUdc6", true, "companyInformationTabUdc6layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("UDC6");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMP_UDC6",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addCreatedBy(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCreatedBylayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCreatedBy", true, "companyInformationTabCreatedBylayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Created By");

		componentList.add(companyIdConfig);
	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationCreatedDatelayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyStartDate = configProvider.getUIFrameworkComponentConfig(
				"companyInformationCreatedDate", true, "companyInformationCreatedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyStartDate.setAuthorizationIncluded(true);
		companyInformationCompanyStartDate.setComponentName("Created Date");

		componentList.add(companyInformationCompanyStartDate);
	}

	private void addModifiedBy(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabModifiedBylayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabModifiedBy", true, "companyInformationTabModifiedBylayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Modified By");

		componentList.add(companyIdConfig);
	}

	private void addModifiedDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationModifiedDatelayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyInformationCompanyStartDate = configProvider.getUIFrameworkComponentConfig(
				"companyInformationModifiedDate", true, "companyInformationModifiedDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyInformationCompanyStartDate.setAuthorizationIncluded(true);
		companyInformationCompanyStartDate.setComponentName("Modified Date");

		componentList.add(companyInformationCompanyStartDate);
	}

	private void addCreatedByName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationTabCreatedByIDlayout", true, GtnFrameworkCompanyStringContants.COMPANY_INFO_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationTabCreatedByID", true, "companyInformationTabCreatedByIDlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setVisible(false);

		componentList.add(companyIdConfig);
	}

}
