package com.stpl.gtn.gtn2o.ui.module.itemgroup.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.GtnFrameworkItemGrpTempTableClearAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;

public class GtnFrameworkItemGrpAddConfig {

	public GtnUIFrameworkViewConfig getItemGrpAddView() {
		GtnFrameworkComponentConfigProvider addViewComponentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig itemGroupAddView = addViewComponentConfig.getViewConfig("Add View", "V002", false);
		addComponentList(itemGroupAddView, addViewComponentConfig);
		return itemGroupAddView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		List<GtnUIFrameworkComponentConfig> addViewComponentList = new ArrayList<>();
		view.setGtnComponentList(addViewComponentList);
		addItemGrpInfoPanel(addViewComponentList, addViewComponentConfig);
		addSaveButtonLayout(addViewComponentList, addViewComponentConfig);
	}

	private void addItemGrpInfoPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig itemGroupInfoPanel = addViewComponentConfig
				.getPanelConfig(GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_PANEL, false, null);
		itemGroupInfoPanel.setComponentName("Item Group Information");
		itemGroupInfoPanel.setAuthorizationIncluded(true);
		componentList.add(itemGroupInfoPanel);

		addItemGrpInfoFieldLayout(componentList, addViewComponentConfig);
		addCustomerSearchPanel(componentList, addViewComponentConfig);
		addButtonLayout(componentList, addViewComponentConfig);
		addAvailableResultPanel(componentList, addViewComponentConfig);
		addAvailableTableActionButtonLayout(componentList, addViewComponentConfig);
		addSelectedResultPanel(componentList, addViewComponentConfig);
		addSelectedTableActionButtonLayout(componentList, addViewComponentConfig);
	}

	private void addItemGrpInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig itemGroupInfoLayout = addViewComponentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_LAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_PANEL, GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		itemGroupInfoLayout.setComponentStyle(styleList);
		componentList.add(itemGroupInfoLayout);

		addItemGrpInfoFieldComponent(componentList, addViewComponentConfig);
	}

	private void addItemGrpInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		addItemGrpName(componentList, addViewComponentConfig);
		addItemGrpNo(componentList, addViewComponentConfig);
		addItemGrpDesc(componentList, addViewComponentConfig);
		addItemGrpCompany(componentList, addViewComponentConfig);

	}

	private void addItemGrpName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemGrpNamelayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpNamelayout", true, GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewItemGrpNamelayout);

		GtnUIFrameworkComponentConfig adddViewItemGroupNameConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("itemGrpInformationItemGrpName", true,
						addViewItemGrpNamelayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		adddViewItemGroupNameConfig.setAuthorizationIncluded(true);
		adddViewItemGroupNameConfig.setComponentName("Item Group Name");
		adddViewItemGroupNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(adddViewItemGroupNameConfig);

		GtnUIFrameworkValidationConfig itemGrpNameValidationConfig = new GtnUIFrameworkValidationConfig();
		itemGrpNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		adddViewItemGroupNameConfig.setGtnUIFrameworkValidationConfig(itemGrpNameValidationConfig);

	}

	private void addItemGrpNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemGrpNolayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpNolayout", true, GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewItemGrpNolayout);

		GtnUIFrameworkComponentConfig addViewItemGroupNoConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpInformationItemGrpNo", true, addViewItemGrpNolayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		addViewItemGroupNoConfig.setAuthorizationIncluded(true);
		addViewItemGroupNoConfig.setComponentName("Item Group No");
		addViewItemGroupNoConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(addViewItemGroupNoConfig);

		GtnUIFrameworkValidationConfig itemGrpNoValidationConfig = new GtnUIFrameworkValidationConfig();
		itemGrpNoValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewItemGroupNoConfig.setGtnUIFrameworkValidationConfig(itemGrpNoValidationConfig);

	}

	private void addItemGrpDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemGrpDesclayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpDesclayout", true, GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewItemGrpDesclayout);

		GtnUIFrameworkComponentConfig addViewItemGroupDescConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpInformationItemGrpDesc", true, addViewItemGrpDesclayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		addViewItemGroupDescConfig.setAuthorizationIncluded(true);
		addViewItemGroupDescConfig.setComponentName("Item Group Description");
		addViewItemGroupDescConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(addViewItemGroupDescConfig);

		GtnUIFrameworkValidationConfig itemGrpDescValidationConfig = new GtnUIFrameworkValidationConfig();
		itemGrpDescValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewItemGroupDescConfig.setGtnUIFrameworkValidationConfig(itemGrpDescValidationConfig);

	}

	private void addItemGrpCompany(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemGrpComlayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpInformationItemGrpCompanylayout", true,
				GtnFrameworkCommonConstants.ITEM_GRP_INFORMATION_LAYOUT);
		componentList.add(addViewItemGrpComlayout);

		GtnUIFrameworkComponentConfig addViewItemGroupCompConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpInformationItemGrpCompany", true, addViewItemGrpComlayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		addViewItemGroupCompConfig.setAuthorizationIncluded(true);
		addViewItemGroupCompConfig.setComponentName("Company");
		addViewItemGroupCompConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(addViewItemGroupCompConfig);

		GtnUIFrameworkComboBoxConfig addViewIgComComboConfig = addViewComponentConfig.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewItemGroupCompConfig.setGtnComboboxConfig(addViewIgComComboConfig);

		GtnUIFrameworkValidationConfig compValidationConfig = new GtnUIFrameworkValidationConfig();
		compValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewItemGroupCompConfig.setGtnUIFrameworkValidationConfig(compValidationConfig);
	}

	private void addCustomerSearchPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchPanelConfig = addViewComponentConfig
				.getPanelConfig("itemGrpCustomerSearchPanel", false, null);
		addViewSearchPanelConfig.setComponentName("Item Search");
		componentList.add(addViewSearchPanelConfig);

		addCustomerSearchFieldLayout(componentList, addViewComponentConfig);
	}

	private void addCustomerSearchFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGroupInfoLayout = addViewComponentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT, true, "itemGrpCustomerSearchPanel",
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		itemGroupInfoLayout.setComponentStyle(styleList);
		componentList.add(itemGroupInfoLayout);

		addCustomerSearchFieldComponent(componentList, addViewComponentConfig);
	}

	private void addCustomerSearchFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		addItemType(componentList, addViewComponentConfig);
		addItemDesc(componentList, addViewComponentConfig);
		addBrand(componentList, addViewComponentConfig);
		addStrength(componentList, addViewComponentConfig);
		addItemNo(componentList, addViewComponentConfig);
		addTherapeuticClass(componentList, addViewComponentConfig);
		addForm(componentList, addViewComponentConfig);

	}

	private void addItemType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemTypelayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpInformationTabCustomerTypelayout", true,
				GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewItemTypelayout);

		GtnUIFrameworkComponentConfig addViewItemTypeConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_TYPE, true,
				addViewItemTypelayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		addViewItemTypeConfig.setAuthorizationIncluded(true);
		addViewItemTypeConfig.setComponentName("Item Type");
		componentList.add(addViewItemTypeConfig);

		GtnUIFrameworkComboBoxConfig addViewItmTypeComboConfig = addViewComponentConfig.getComboBoxConfig("ITEM_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewItemTypeConfig.setGtnComboboxConfig(addViewItmTypeComboConfig);

		GtnUIFrameworkValidationConfig itemTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		itemTypeValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewItemTypeConfig.setGtnUIFrameworkValidationConfig(itemTypeValidationConfig);
	}

	private void addItemDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemDesclayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpDesclayout", true, GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewItemDesclayout);

		GtnUIFrameworkComponentConfig addViewItemDescConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_I_GRP_DESC, true, addViewItemDesclayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		addViewItemDescConfig.setAuthorizationIncluded(true);
		addViewItemDescConfig.setComponentName(GtnFrameworkCommonConstants.ITEM_DESC_HEADER);
		componentList.add(addViewItemDescConfig);

		GtnUIFrameworkValidationConfig addViewValidationConfig = new GtnUIFrameworkValidationConfig();
		addViewValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewItemDescConfig.setGtnUIFrameworkValidationConfig(addViewValidationConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemNolayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpInformationTabCustomerNolayout", true,
				GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewItemNolayout);

		GtnUIFrameworkComponentConfig addViewItemNoConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_NO, true,
				addViewItemNolayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addViewItemNoConfig.setAuthorizationIncluded(true);
		addViewItemNoConfig.setComponentName(GtnFrameworkCommonConstants.ITEM_NO_HEADER);
		componentList.add(addViewItemNoConfig);

		GtnUIFrameworkValidationConfig addViewItemNoValidationConfig = new GtnUIFrameworkValidationConfig();
		addViewItemNoValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewItemNoConfig.setGtnUIFrameworkValidationConfig(addViewItemNoValidationConfig);

	}

	private void addBrand(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewBrandlayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpInformationTabBrandlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewBrandlayout);

		GtnUIFrameworkComponentConfig addViewBrandConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_BRAND, true, addViewBrandlayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		addViewBrandConfig.setAuthorizationIncluded(true);
		addViewBrandConfig.setComponentName(GtnFrameworkCommonConstants.BRAND_IFP);
		componentList.add(addViewBrandConfig);

		GtnUIFrameworkComboBoxConfig addViewBrandComboConfig = addViewComponentConfig.getComboBoxConfig("Brands",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewBrandConfig.setGtnComboboxConfig(addViewBrandComboConfig);

		GtnUIFrameworkValidationConfig brandValidationConfig = new GtnUIFrameworkValidationConfig();
		brandValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewBrandConfig.setGtnUIFrameworkValidationConfig(brandValidationConfig);
	}

	private void addStrength(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewItemStrengthlayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpInformationTabStrengthlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewItemStrengthlayout);

		GtnUIFrameworkComponentConfig addViewItemStrengthConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_STRENGTH, true,
				addViewItemStrengthlayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		addViewItemStrengthConfig.setAuthorizationIncluded(true);
		addViewItemStrengthConfig.setComponentName(GtnFrameworkCommonConstants.STRENGTH_IFP);
		componentList.add(addViewItemStrengthConfig);

		GtnUIFrameworkValidationConfig addViewStrengthValidationConfig = new GtnUIFrameworkValidationConfig();
		addViewStrengthValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addViewItemStrengthConfig.setGtnUIFrameworkValidationConfig(addViewStrengthValidationConfig);

	}

	private void addTherapeuticClass(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewThreapilayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpInformationTabTherapeuticClasslayout", true,
				GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewThreapilayout);

		GtnUIFrameworkComponentConfig addViewTherapiConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_THERAPEUTIC_CLASS, true,
				addViewThreapilayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		addViewTherapiConfig.setAuthorizationIncluded(true);
		addViewTherapiConfig.setComponentName(GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL);
		componentList.add(addViewTherapiConfig);

		GtnUIFrameworkComboBoxConfig addViewThreapiComboConfig = addViewComponentConfig
				.getComboBoxConfig("THERAPEUTIC_CLASS", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewTherapiConfig.setGtnComboboxConfig(addViewThreapiComboConfig);

		GtnUIFrameworkValidationConfig threapiValidationConfig = new GtnUIFrameworkValidationConfig();
		threapiValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewTherapiConfig.setGtnUIFrameworkValidationConfig(threapiValidationConfig);

	}

	private void addForm(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewFormlayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"iGrpInformationTabFormlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_CUSTOMER_SEARCH_LAYOUT);
		componentList.add(addViewFormlayout);

		GtnUIFrameworkComponentConfig addViewFormConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_FORM, true, addViewFormlayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		addViewFormConfig.setAuthorizationIncluded(true);
		addViewFormConfig.setComponentName("Form");
		componentList.add(addViewFormConfig);

		GtnUIFrameworkComboBoxConfig addViewFormComboConfig = addViewComponentConfig.getComboBoxConfig("FORM",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		addViewFormConfig.setGtnComboboxConfig(addViewFormComboConfig);

		GtnUIFrameworkValidationConfig formValidationConfig = new GtnUIFrameworkValidationConfig();
		formValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addViewFormConfig.setGtnUIFrameworkValidationConfig(formValidationConfig);

	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig iGroupAddvwSearchCriteriaButtonLayout = addViewComponentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_BUTTONLAYOUT, false, null);
		iGroupAddvwSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		iGroupAddvwSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		iGroupAddvwSearchCriteriaButtonLayout.setSpacing(true);
		iGroupAddvwSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(iGroupAddvwSearchCriteriaButtonLayout);

		addSearchButtonComponent(componentList, addViewComponentConfig);
		addResetButtonComponent(componentList, addViewComponentConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"gtnSearch01layout", true, GtnFrameworkCommonConstants.ITEM_GRPSEARCH_BUTTONLAYOUT);
		componentList.add(addViewSearchBtnLayout);

		GtnUIFrameworkComponentConfig addViewSearchButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"gtnSearch01", true, addViewSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addViewSearchButtonConfig.setComponentName("SEARCH");
		addViewSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addViewSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addViewSearchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		addViewSearchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		addViewSearchValidationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_I_GRP_DESC,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_NO,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_BRAND,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_STRENGTH,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_THERAPEUTIC_CLASS,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_FORM));

		GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_SEARCH_ERROR);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_NO_SEARCH_FOUND);
		searchAlertActionConfig.setActionParameterList(alertParamsList);
		addViewSearchValidationActionConfig.setActionParameterList(Arrays
				.asList(new Object[] { GtnUIFrameworkValidationType.OR, Arrays.asList(searchAlertActionConfig) }));
		searchActionConfigList.add(addViewSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig availableDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		availableDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		availableDataTableActionConfig
				.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRP_AVAILABLESEARCH_RESULT_TABLE);
		availableDataTableActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_I_GRP_DESC,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_NO,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_BRAND,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_STRENGTH,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_THERAPEUTIC_CLASS,
						GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_FORM));

		searchActionConfigList.add(availableDataTableActionConfig);

		GtnUIFrameWorkActionConfig searchNotificationActionConfig = new GtnUIFrameWorkActionConfig();
		searchNotificationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchNotificationActionConfig
				.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_TABLE_EMPTY_VALIDATION_ACTION);
		searchNotificationActionConfig
				.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRP_AVAILABLESEARCH_RESULT_TABLE);
		searchNotificationActionConfig
				.addActionParameter(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_NO_RESULTS_FOUND_MSG);
		searchActionConfigList.add(searchNotificationActionConfig);

		addViewSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewResetBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpgtnReset01layout", true, GtnFrameworkCommonConstants.ITEM_GRPSEARCH_BUTTONLAYOUT);
		componentList.add(addViewResetBtnLayout);

		GtnUIFrameworkComponentConfig addViewResetButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpgtnReset01", true, addViewResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addViewResetButtonConfig.setComponentName("RESET");
		addViewResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addViewResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig
				.addActionParameter(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_CONFIRMATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_CONFIRMATION_MSG_RESET);

		String[] resetIdArray = { GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_I_GRP_DESC,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_NO,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_BRAND,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_STRENGTH,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_THERAPEUTIC_CLASS,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_FORM };
		List<String> resetIdList = Arrays.asList(resetIdArray);

		Object[] resetValueArray = { null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				null, null };
		List<Object> resetValueList = Arrays.asList(resetValueArray);

		resetActionConfig.addActionParameter(resetIdList);
		resetActionConfig.addActionParameter(resetValueList);

		resetActionConfigList.add(resetActionConfig);
		addViewResetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addAvailableResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig itemGroupSearchResultConfig = addViewComponentConfig
				.getPanelConfig("itemGrpAvailableResultPanel", false, null);
		itemGroupSearchResultConfig.setComponentName("Results");
		itemGroupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(itemGroupSearchResultConfig);
		addAvailableResultLayout(componentList, addViewComponentConfig);
	}

	private void addAvailableResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewResultTableLayout = addViewComponentConfig
				.getHorizontalLayoutConfig("itemGrpAvailableResultlayout", true, "itemGrpAvailableResultPanel");
		addViewResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(addViewResultTableLayout);

		addAvailableResultTableComponent(componentList, addViewComponentConfig);
	}

	private void addAvailableResultTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSearchResultConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_AVAILABLESEARCH_RESULT_TABLE, true, "itemGrpAvailableResultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		addViewSearchResultConfig.setAuthorizationIncluded(true);
		addViewSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(addViewSearchResultConfig);

		GtnUIFrameworkPagedTableConfig addViewSearchResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		addViewSearchResultsTableConfig.setEditable(false);
		addViewSearchResultsTableConfig.setFilterBar(true);
		addViewSearchResultsTableConfig.setSelectable(true);
		addViewSearchResultsTableConfig.setMultiSelect(true);
		addViewSearchResultsTableConfig.setSinkItemPerPageWithPageLength(false);
		addViewSearchResultsTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE });
		addViewSearchResultsTableConfig.setTableVisibleHeader(new String[] { "Item ID", "Item No", "Item Code",
				"Item Name", GtnFrameworkCommonConstants.ITEM_DESC_HEADER, "Item Start Date", "Item End Date",
				"Item Status", GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP,
				"Form", GtnFrameworkCommonConstants.STRENGTH_IFP, "Package Size Code", "Package Size Intro Date",
				"UPPS", "Manufacturer ID", "Manufacturer No", "Manufacturer Name", "Labeler Code", "Organization Key",
				"Acquisition Date", "Authorized Generic", "Authorized Generic Start Date",
				"Authorized Generic End Date", "First Sale Date", "Item Type Indicator", "Item Class ", "Item Type ",
				"Market Termination Date", "New Formulation Indicator", "New Formulation", "New Formulation Start Date",
				"New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
				"Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
				"Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
				"Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6",
				"Acquired AMP", "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date",
				"Last Lot Expiration Date", "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP",
				"Base Year CPI" });
		addViewSearchResultsTableConfig.setTableColumnMappingId(new Object[] { "itemId", "itemNo", "itemCode",
				"itemName", "itemDesc", "itemStartDate", "itemEndDate", "itemStatus", "therapeuticClass", "brand",
				"form", "strength", "packageSizeCode", "packageSizeIntroDate", "upps", "manufacturerId",
				"manufacturerNo", "manufacturerName", "labelerCode", "organizationKey", "acquisitionDate",
				"authorizedGeneric", "authorizedGenericStartDate", "authorizedGenericEndDate", "firstSaleDate",
				"itemTypeIndicator", "itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator",
				"newFormulation", "newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
				"pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator",
				"clottingFactorStartDate", "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife",
				"shelfLifeType", "dualPricingIndicator", "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6",
				"acquiredAmp", "acquiredBamp", "obraBamp", "dra", "dosesPerUnit", "discontinuationDate",
				"lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode", "baselineAmp",
				"baseYearCpi" });
		addViewSearchResultsTableConfig.setExtraColumn(new Object[] { "itemMasterSid" });
		addViewSearchResultsTableConfig.setExtraColumnDataType(new Class<?>[] { Integer.class });
		addViewSearchResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		addViewSearchResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		addViewSearchResultsTableConfig.setQueryName("cGrpAddTabSearchQuery");
		addViewSearchResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_GROUPS);
		addViewSearchResultConfig.setGtnPagedTableConfig(addViewSearchResultsTableConfig);
	}

	private void addAvailableTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddViewAddActionBtnLayout = addViewComponentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT, false, null);
		itemGrpAddViewAddActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		itemGrpAddViewAddActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		itemGrpAddViewAddActionBtnLayout.setSpacing(true);
		itemGrpAddViewAddActionBtnLayout.setMargin(true);
		componentList.add(itemGrpAddViewAddActionBtnLayout);

		addButtonComponent(componentList, addViewComponentConfig);
		addAllButtonComponent(componentList, addViewComponentConfig);
		addAvailableExcelButtonComponent(componentList, addViewComponentConfig);
	}

	private void addButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddViewAddBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpAvailableTablegtnAdd01layout", true,
				GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpAddViewAddBtnLayout);

		GtnUIFrameworkComponentConfig addViewAddButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpAvailableTablegtnAdd01", true, itemGrpAddViewAddBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		addViewAddButtonConfig.setAuthorizationIncluded(true);
		addViewAddButtonConfig.setComponentName("ADD");
		componentList.add(addViewAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_ADD_BUTTON_ACTION);
		addActionConfigList.add(addActionConfig);
		addViewAddButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);
	}

	private void addAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddViewAddAllBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpAvailableTablegtnAddAll01layout", true,
				GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpAddViewAddAllBtnLayout);

		GtnUIFrameworkComponentConfig addViewAddAllButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpAvailableTablegtnAddAll01", true, itemGrpAddViewAddAllBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		addViewAddAllButtonConfig.setComponentName("ADD ALL");
		addViewAddAllButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addViewAddAllButtonConfig);

		List<GtnUIFrameWorkActionConfig> addViewAddAllActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addAllActionConfig = new GtnUIFrameWorkActionConfig();
		addAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addAllActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_ADD_ALL_ACTION);
		addAllActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_I_GRP_DESC,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_NO,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_BRAND,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_STRENGTH,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_THERAPEUTIC_CLASS,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_FORM));
		addViewAddAllActionConfigList.add(addAllActionConfig);
		addViewAddAllButtonConfig.setGtnUIFrameWorkActionConfigList(addViewAddAllActionConfigList);
	}

	private void addAvailableExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewExcelBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpAvailableTablegtnExcellayout", true,
				GtnFrameworkCommonConstants.AVAILABLE_TABLE_ACTION_BUTTONLAYOUT);
		addViewExcelBtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(addViewExcelBtnLayout);

		GtnUIFrameworkComponentConfig addViewExcelButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpAvailableTablegtnExcelBtn", true, addViewExcelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);

		GtnUIFrameworkExcelButtonConfig addViewExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		addViewExcelButtonInput.setExportFileName("Available Item Results");
		addViewExcelButtonInput.setTitleNeeded(true);
		addViewExcelButtonInput.setExportFromTable(true);
		addViewExcelButtonInput.setExportTableId(GtnFrameworkCommonConstants.ITEM_GRP_AVAILABLESEARCH_RESULT_TABLE);

		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		resultTableExcelAction.addActionParameter(addViewExcelButtonInput);

		addViewExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(addViewExcelButtonInput);
		addViewExcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(addViewExcelButtonConfig);

	}

	private void addSelectedResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGroupSelectedTablePanelConfig = addViewComponentConfig
				.getPanelConfig("itemGrpSelectedResultPanel", false, null);
		itemGroupSelectedTablePanelConfig.setComponentName("Selected Items");
		itemGroupSelectedTablePanelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(itemGroupSelectedTablePanelConfig);
		addSelectedResultLayout(componentList, addViewComponentConfig);
	}

	private void addSelectedResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSelectedResultTableLayout = addViewComponentConfig
				.getHorizontalLayoutConfig("itemGrpSelectedResultlayout", true, "itemGrpSelectedResultPanel");
		addViewSelectedResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(addViewSelectedResultTableLayout);
		addSelectedPagedTableComponent(componentList, addViewComponentConfig);
	}

	private void addSelectedPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSelectedResultConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("itemGrpSelectedResultTable", true, "itemGrpSelectedResultlayout",
						GtnUIFrameworkComponentType.PAGEDTABLE);
		addViewSelectedResultConfig.setAuthorizationIncluded(true);
		addViewSelectedResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(addViewSelectedResultConfig);

		GtnUIFrameworkPagedTableConfig addViewSelectedResultTableConfig = new GtnUIFrameworkPagedTableConfig();
		addViewSelectedResultTableConfig.setEditable(false);
		addViewSelectedResultTableConfig.setFilterBar(true);
		addViewSelectedResultTableConfig.setSelectable(true);
		addViewSelectedResultTableConfig.setMultiSelect(true);
		addViewSelectedResultTableConfig.setSinkItemPerPageWithPageLength(false);
		addViewSelectedResultTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE });
		addViewSelectedResultTableConfig.setTableVisibleHeader(new String[] { "Item ID", "Item No", "Item Code",
				"Item Name", GtnFrameworkCommonConstants.ITEM_DESC_HEADER, "Item Start Date", "Item End Date",
				"Item Status", GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP,
				"Form", GtnFrameworkCommonConstants.STRENGTH_IFP, "Package Size Code", "Package Size Intro Date",
				"UPPS", "Manufacturer ID", "Manufacturer No", "Manufacturer Name", "Labeler Code", "Organization Key",
				"Acquisition Date", "Authorized Generic", "Authorized Generic Start Date",
				"Authorized Generic End Date", "First Sale Date", "Item Type Indicator", "Item Class ", "Item Type ",
				"Market Termination Date", "New Formulation Indicator", "New Formulation", "New Formulation Start Date",
				"New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
				"Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
				"Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
				"Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6",
				"Acquired AMP", "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date",
				"Last Lot Expiration Date", "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP",
				"Base Year CPI" });
		addViewSelectedResultTableConfig.setTableColumnMappingId(new Object[] { "itemId", "itemNo", "itemCode",
				"itemName", "itemDesc", "itemStartDate", "itemEndDate", "itemStatus", "therapeuticClass", "brand",
				"form", "strength", "packageSizeCode", "packageSizeIntroDate", "upps", "manufacturerId",
				"manufacturerNo", "manufacturerName", "labelerCode", "organizationKey", "acquisitionDate",
				"authorizedGeneric", "authorizedGenericStartDate", "authorizedGenericEndDate", "firstSaleDate",
				"itemTypeIndicator", "itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator",
				"newFormulation", "newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
				"pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator",
				"clottingFactorStartDate", "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife",
				"shelfLifeType", "dualPricingIndicator", "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6",
				"acquiredAmp", "acquiredBamp", "obraBamp", "dra", "dosesPerUnit", "discontinuationDate",
				"lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode", "baselineAmp",
				"baseYearCpi" });
		addViewSelectedResultTableConfig.setExtraColumn(new Object[] { "itemMasterSid" });
		addViewSelectedResultTableConfig.setExtraColumnDataType(new Class<?>[] { Integer.class });
		addViewSelectedResultTableConfig.setCountUrl(GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE
				+ GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SELECTED_TABLE_SERVICE);
		addViewSelectedResultTableConfig.setResultSetUrl(GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE
				+ GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SELECTED_TABLE_SERVICE);
		addViewSelectedResultTableConfig.setModuleName("itemGroupsMaster");
		addViewSelectedResultTableConfig.setQueryName("itemGrpAddTabSelectedSearchQuery");
		addViewSelectedResultTableConfig
				.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_GROUPS);
		addViewSelectedResultConfig.setGtnPagedTableConfig(addViewSelectedResultTableConfig);
	}

	private void addSelectedTableActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddViewRemoveActionBtnLayout = addViewComponentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT, false, null);
		itemGrpAddViewRemoveActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		itemGrpAddViewRemoveActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		itemGrpAddViewRemoveActionBtnLayout.setSpacing(true);
		itemGrpAddViewRemoveActionBtnLayout.setMargin(true);
		componentList.add(itemGrpAddViewRemoveActionBtnLayout);

		addRemoveButtonComponent(componentList, addViewComponentConfig);
		addRemoveAllButtonComponent(componentList, addViewComponentConfig);
		addSelectedExcelButtonComponent(componentList, addViewComponentConfig);
	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddViewRemoveBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpSelectedTablegtnRemove01layout", true,
				GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpAddViewRemoveBtnLayout);

		GtnUIFrameworkComponentConfig addViewRemoveButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpSelectedTablegtnRemove01", true, itemGrpAddViewRemoveBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		addViewRemoveButtonConfig.setAuthorizationIncluded(true);
		addViewRemoveButtonConfig.setComponentName("REMOVE");
		componentList.add(addViewRemoveButtonConfig);

		List<GtnUIFrameWorkActionConfig> removeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_REMOVE_ACTION);
		removeActionConfigList.add(addActionConfig);
		addViewRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(removeActionConfigList);
	}

	private void addRemoveAllButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddViewRemoveAllBtnLayout = addViewComponentConfig
				.getHorizontalLayoutConfig("itemGrpSelectedTablegtnRemoveAll01layout", true,
						GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpAddViewRemoveAllBtnLayout);

		GtnUIFrameworkComponentConfig addViewRemoveAllButtonConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("itemGrpSelectedTablegtnRemoveAll01", true,
						itemGrpAddViewRemoveAllBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addViewRemoveAllButtonConfig.setComponentName("REMOVE ALL");
		addViewRemoveAllButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addViewRemoveAllButtonConfig);

		List<GtnUIFrameWorkActionConfig> removeAllactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_REMOVE_ALL_ACTION);
		removeAllactionConfigList.add(addActionConfig);
		addViewRemoveAllButtonConfig.setGtnUIFrameWorkActionConfigList(removeAllactionConfigList);

	}

	private void addSelectedExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig addViewSelectedExcelBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpSelectedTablegtnExcellayout", true,
				GtnFrameworkCommonConstants.ITEM_GRP_SELECTED_TABLE_ACTION_BUTTONLAYOUT);
		addViewSelectedExcelBtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(addViewSelectedExcelBtnLayout);

		GtnUIFrameworkComponentConfig addViewSelectedExcelButtonConfig = addViewComponentConfig
				.getUIFrameworkComponentConfig("itemGrpSelectedgtnExcelBtn", true,
						addViewSelectedExcelBtnLayout.getComponentId(), GtnUIFrameworkComponentType.EXCEL_BUTTON);
		addViewSelectedExcelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig selectedExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		selectedExcelButtonInput.setIsTreeTable(false);
		selectedExcelButtonInput.setExportFileName("Selected Item Results");
		selectedExcelButtonInput.setTitleNeeded(true);
		selectedExcelButtonInput.setExportFromTable(true);
		selectedExcelButtonInput.setExportTableId("itemGrpSelectedResultTable");

		GtnUIFrameWorkActionConfig selectedResultTableExcelAction = new GtnUIFrameWorkActionConfig();
		selectedResultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		selectedResultTableExcelAction.addActionParameter(selectedExcelButtonInput);

		addViewSelectedExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(selectedExcelButtonInput);
		addViewSelectedExcelButtonConfig
				.setGtnUIFrameWorkActionConfigList(Arrays.asList(selectedResultTableExcelAction));
		componentList.add(addViewSelectedExcelButtonConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkLayoutConfig itemGrpSaveBtnlayout = new GtnUIFrameworkLayoutConfig();
		itemGrpSaveBtnlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		itemGrpSaveBtnlayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig itemGrpSaveBtnLayoutConfig = new GtnUIFrameworkComponentConfig();
		itemGrpSaveBtnLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		itemGrpSaveBtnLayoutConfig.setComponentId(GtnFrameworkCommonConstants.ITEM_GRPSAVE_BUTTONLAYOUT);
		itemGrpSaveBtnLayoutConfig.setSpacing(true);
		itemGrpSaveBtnLayoutConfig.setMargin(true);
		itemGrpSaveBtnLayoutConfig.setAddToParent(false);
		itemGrpSaveBtnLayoutConfig.setGtnLayoutConfig(itemGrpSaveBtnlayout);
		componentList.add(itemGrpSaveBtnLayoutConfig);
		addBackButtonComponent(componentList, addViewComponentConfig);
		addSaveButtonComponent(componentList, addViewComponentConfig);
		addActionResetButtonComponent(componentList, addViewComponentConfig);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig itemGrpBackBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpAddBackButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRPSAVE_BUTTONLAYOUT);
		componentList.add(itemGrpBackBtnLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpAddBackButton", true, itemGrpBackBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setComponentName("BACK");
		backButtonConfig.setAuthorizationIncluded(true);
		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> backBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Confirmation");
		alertParamsList.add("Any unsaved information will not be saved. Do you want to proceed?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig itemGrpTempTableClearCustomAction = new GtnUIFrameWorkActionConfig();
		itemGrpTempTableClearCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemGrpTempTableClearCustomAction.addActionParameter(GtnFrameworkItemGrpTempTableClearAction.class.getName());
		onSucessActionConfigList.add(itemGrpTempTableClearCustomAction);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		backBtnActionConfigList.add(confirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(backBtnActionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {

		GtnUIFrameworkComponentConfig itemGrpSaveBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"itemGrpAddSaveButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRPSAVE_BUTTONLAYOUT);
		componentList.add(itemGrpSaveBtnLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"itemGrpAddSaveButton", true, itemGrpSaveBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setComponentName("SAVE");
		saveButtonConfig.setAuthorizationIncluded(true);
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> saveActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig itemGrpNameValidationAction = new GtnUIFrameWorkActionConfig();
		itemGrpNameValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemGrpNameValidationAction
				.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_NAME_VALIDATION_ACTION);
		saveActionConfigList.add(itemGrpNameValidationAction);

		GtnUIFrameWorkActionConfig customValidationAction = new GtnUIFrameWorkActionConfig();
		customValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customValidationAction.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_VALIDATION_ACTION);
		saveActionConfigList.add(customValidationAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Confirmation");
		alertParamsList.add("Save record ");
		alertParamsList.add("itemGrpInformationItemGrpName");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_SAVE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		saveActionConfigList.add(confirmationActionConfig);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(saveActionConfigList);
	}

	private void addActionResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider addViewComponentConfig) {
		GtnUIFrameworkComponentConfig itemGrpResetBtnLayout = addViewComponentConfig.getHorizontalLayoutConfig(
				"ItemGrpADDResetButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRPSAVE_BUTTONLAYOUT);
		componentList.add(itemGrpResetBtnLayout);

		GtnUIFrameworkComponentConfig itemGroupResetButtonConfig = addViewComponentConfig.getUIFrameworkComponentConfig(
				"ItemGrpADDResetButton", true, itemGrpResetBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		itemGroupResetButtonConfig.setComponentName("RESET");
		itemGroupResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(itemGroupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> itemGroupResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> itemGroupResetAlertParamsList = new ArrayList<>();
		itemGroupResetAlertParamsList.add("Reset Confirmation");
		itemGroupResetAlertParamsList.add("Are you sure you want to reset the values in the Search Criteria group box?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_RESET_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		itemGroupResetAlertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(itemGroupResetAlertParamsList);

		itemGroupResetActionConfigList.add(confirmationActionConfig);

		itemGroupResetButtonConfig.setGtnUIFrameWorkActionConfigList(itemGroupResetActionConfigList);

	}

}
