/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkBackAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCRValueChangeAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCVSaveValidationAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCustomerAddAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkOptionGroupChangeAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkRemoveAction;
import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tree.GtnUIFrameworkTreeConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCVAddConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getCustomViewAddView() {

		GtnUIFrameworkViewConfig addView = configProvider.getViewConfig("Add View", "V002", false);
		addComponentList(addView, addView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCustomViewInfoPanel(componentList, namespacePrefix);
		addCustomTreePanel(componentList, namespacePrefix);
		addSaveButtonLayout(componentList, namespacePrefix);
	}

	private void addCustomViewInfoPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig infoPanel = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFO_PANEL, false, null, GtnUIFrameworkComponentType.PANEL);
		infoPanel.setComponentName("Custom View Information");
		infoPanel.setAuthorizationIncluded(true);
		infoPanel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(infoPanel);
		addCustomViewInfoFieldLayout(componentList, namespacePrefix);
	}

	private void addCustomViewInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespacePrefix) {
		GtnUIFrameworkComponentConfig viewInfoVerticalLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFO_VERTICAL_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFO_PANEL);
		viewInfoVerticalLayout.setMargin(true);
		viewInfoVerticalLayout.setSpacing(true);
		viewInfoVerticalLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(viewInfoVerticalLayout);
		addFieldCSSLayout(componentList, namespacePrefix);
	}

	private void addFieldCSSLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig infoLayoutConfig = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFO_VERTICAL_LAYOUT, GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		infoLayoutConfig.setAddToParent(true);
		infoLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(infoLayoutConfig);
		addCustomViewInfoFieldComponent(componentList, namespacePrefix);
	}

	private void addCustomViewInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespacePrefix) {
		addTreeViewName(componentList, namespacePrefix);
		addCustomViewDescription(componentList, namespacePrefix);
		addCustomViewType(componentList, namespacePrefix);
		addCustomerRelation(componentList, namespacePrefix);
		addProductRelation(componentList, namespacePrefix);
		addScreenName(componentList, namespacePrefix);
	}

	private void addTreeViewName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig cvNameLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_NAME_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT);
		componentList.add(cvNameLayout);

		GtnUIFrameworkComponentConfig cvNameConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.TREE_VIEW_NAME, true,
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_NAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		cvNameConfig.setAuthorizationIncluded(true);
		cvNameConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		cvNameConfig.setComponentName("Custom View Name");

		cvNameConfig.setAddToParent(true);

		componentList.add(cvNameConfig);
	}

	private void addCustomViewDescription(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig cvDescLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT);
		componentList.add(cvDescLayout);

		GtnUIFrameworkComponentConfig cvDescConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION, true,
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		cvDescConfig.setAuthorizationIncluded(true);
		cvDescConfig.setComponentName("Custom View Description");
		cvDescConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		cvDescConfig.setAddToParent(true);

		componentList.add(cvDescConfig);
	}

	private void addCustomerRelation(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig cvCustRelationLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_RELATION_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT);
		componentList.add(cvCustRelationLayout);

		GtnUIFrameworkComponentConfig cvCustRelationConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.CUTOMER_RELATION, true,
				namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_RELATION_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		cvCustRelationConfig.setAuthorizationIncluded(true);
		cvCustRelationConfig.setComponentName("Customer Relation");
		cvCustRelationConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		cvCustRelationConfig.setAddToParent(true);

		componentList.add(cvCustRelationConfig);

		GtnUIFrameworkComboBoxConfig custRelationConfig = new GtnUIFrameworkComboBoxConfig();
		custRelationConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		custRelationConfig.setComboBoxType(GtnFrameworkCommonConstants.CUTOMER_RELATION);
		cvCustRelationConfig.setGtnComboboxConfig(custRelationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCRValueChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_LEVEL);
		customAction.addActionParameter(GtnFrameworkCVConstants.LEVEL_NAME);
		customAction.addActionParameter(Arrays.asList(String.class));
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_LEVEL);
		actionConfigList.add(customAction);
		cvCustRelationConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addProductRelation(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig cvProdRelationLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.PRODUCT_RELATION_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT);
		componentList.add(cvProdRelationLayout);

		GtnUIFrameworkComponentConfig prodRelationConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.PRODUCT_RELATION, true,
				namspacePrefix + GtnFrameworkCVConstants.PRODUCT_RELATION_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		prodRelationConfig.setAuthorizationIncluded(true);
		prodRelationConfig.setComponentName("Product Relation");
		prodRelationConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		prodRelationConfig.setAddToParent(true);

		componentList.add(prodRelationConfig);

		GtnUIFrameworkComboBoxConfig cvProdRelationConfig = new GtnUIFrameworkComboBoxConfig();
		cvProdRelationConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		cvProdRelationConfig.setComboBoxType(GtnFrameworkCommonConstants.PRODUCT_RELATION);
		prodRelationConfig.setGtnComboboxConfig(cvProdRelationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCRValueChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.PRODUCT_LEVEL);
		customAction.addActionParameter(GtnFrameworkCVConstants.LEVEL_NAME);
		customAction.addActionParameter(Arrays.asList(String.class));
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_LEVEL);
		actionConfigList.add(customAction);
		prodRelationConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addScreenName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig cvScreenNameLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.SCREEN_NAME_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT);
		componentList.add(cvScreenNameLayout);

		GtnUIFrameworkComponentConfig cvScreenNameConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SCREEN_NAME, true,
				namspacePrefix + GtnFrameworkCVConstants.SCREEN_NAME_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		cvScreenNameConfig.setAuthorizationIncluded(true);
		cvScreenNameConfig.setComponentName("Screen Name");
		cvScreenNameConfig.setEnable(false);

		cvScreenNameConfig.setAddToParent(true);

		componentList.add(cvScreenNameConfig);

		GtnUIFrameworkComboBoxConfig screenNameAddConfig = new GtnUIFrameworkComboBoxConfig();
		screenNameAddConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		screenNameAddConfig.setComboBoxType(GtnFrameworkCommonConstants.CV_MODULE_TYPE);
		screenNameAddConfig.setHasDefaultValue(true);
		screenNameAddConfig.setDefaultDesc("Forecasting");
		cvScreenNameConfig.setGtnComboboxConfig(screenNameAddConfig);

	}

	private void addCustomViewType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig cvTypeLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TYPE_COMP_LAYOUT, true,
				GtnFrameworkCVConstants.CUSTOM_VIEW_INFORMATION_LAYOUT);
		componentList.add(cvTypeLayout);

		GtnUIFrameworkComponentConfig customViewTypeOptionGroup = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE, true,
				namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TYPE_COMP_LAYOUT,
				GtnUIFrameworkComponentType.OPTIONGROUP);
		customViewTypeOptionGroup.setAuthorizationIncluded(true);
		customViewTypeOptionGroup.setAddToParent(true);
		customViewTypeOptionGroup.setComponentName("Custom View Type");
		customViewTypeOptionGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkOptionGroupConfig cvTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		cvTypeConfig.setValuesFromService(false);
		cvTypeConfig.setItemValues(Arrays.asList("Sales", "Discount"));

		customViewTypeOptionGroup.setComponentStyle(Arrays.asList("horizontal"));
		customViewTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(cvTypeConfig);

		componentList.add(customViewTypeOptionGroup);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig cvTypeAction = new GtnUIFrameWorkActionConfig();
		cvTypeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cvTypeAction.addActionParameter(GtnFrameworkOptionGroupChangeAction.class.getName());
		cvTypeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_HIER_LAYOUT);
		cvTypeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_MOVE_LAYOUT);
		cvTypeAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.PRODUCT_RELATION);
		cvTypeAction.addActionParameter(GtnFrameworkCVConstants.CV_TREE_PANEL);
		cvTypeAction.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_TREE_LAYOUT);
		cvTypeAction.addActionParameter(GtnFrameworkCVConstants.CV_TREE_VERTICAL_LAYOUT);
		cvTypeAction.addActionParameter(GtnFrameworkCVConstants.CV_TREE_CONSTRUCT_PANEL);
		cvTypeAction.addActionParameter(GtnFrameworkCVConstants.V002_CUSTOMER_TREE_LAYOUT);
		actionConfigList.add(cvTypeAction);
		customViewTypeOptionGroup.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig saveLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCVConstants.SAVE_BUTTON_LAYOUT, false, null);
		saveLayout.setSpacing(true);
		saveLayout.setMargin(true);
		saveLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(saveLayout);
		addSaveButtonComponent(componentList, namespacePrefix);
		addBackButtonComponent(componentList);
	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig saveButtonLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.CV_ADD_SAVE_BTN_LAYOUT, true, GtnFrameworkCVConstants.SAVE_BUTTON_LAYOUT);
		componentList.add(saveButtonLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"customViewAddSaveButton", true, GtnFrameworkCVConstants.CV_ADD_SAVE_BTN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("Save");

		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customCommonValidationAction = new GtnUIFrameWorkActionConfig();
		customCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction.addActionParameter(GtnFrameworkCVSaveValidationAction.class.getName());
		customCommonValidationAction
				.addActionParameter(new String[] { namespacePrefix + GtnFrameworkCommonConstants.TREE_VIEW_NAME,
						namespacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION,
						namespacePrefix + GtnFrameworkCommonConstants.CUTOMER_RELATION,
						namespacePrefix + GtnFrameworkCommonConstants.PRODUCT_RELATION });
		customCommonValidationAction.addActionParameter(namespacePrefix + GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE);
		customCommonValidationAction.addActionParameter(namespacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		customCommonValidationAction
				.addActionParameter(namespacePrefix + GtnFrameworkCommonConstants.CUSTOM_VIEW_SCREEN_NAME);
		actionConfigList.add(customCommonValidationAction);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.CV_ADD_BACK_BTN_LAYOUT, true, GtnFrameworkCVConstants.SAVE_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cvBackButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"customViewAddBackButton", true, GtnFrameworkCVConstants.CV_ADD_BACK_BTN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cvBackButtonConfig.setAuthorizationIncluded(true);
		cvBackButtonConfig.setComponentName("Back");

		componentList.add(cvBackButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig cvBackButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		cvBackButtonConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkBackAction.class.getName());
		alertParamsList.add(GtnFrameworkCVConstants.GTN_CUSTOM_VIEW_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCVConstants.GTN_CUSTOM_VIEW_MSG_BACK);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);
                alertParamsList.add(onSucessActionConfigList);
		cvBackButtonConfirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(cvBackButtonConfirmationActionConfig);
		cvBackButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCustomTreePanel(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig cvTreePanel = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCVConstants.CV_TREE_PANEL, false, null, GtnUIFrameworkComponentType.PANEL);
		cvTreePanel.setComponentName("Custom View Tree");
		cvTreePanel.setAuthorizationIncluded(true);
		cvTreePanel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cvTreePanel.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cvTreePanel);
		addTableCSSLayout(componentList, namespacePrefix);
	}

	private void addTableCSSLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig cvLayoutConfig = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnFrameworkCVConstants.CUSTOM_VIEW_TREE_LAYOUT, true, GtnFrameworkCVConstants.CV_TREE_PANEL,
				GtnUIFrameworkLayoutType.COL2_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		cvLayoutConfig.setAddToParent(true);
		cvLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cvLayoutConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_1000);
		componentList.add(cvLayoutConfig);
		addCustomViewTableLayout(componentList, namespacePrefix);
		addCustomViewTreePanel(componentList, namespacePrefix);
	}

	private void addCustomViewTableLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCVConstants.CV_TREE_VERTICAL_LAYOUT, true, GtnFrameworkCVConstants.CUSTOM_VIEW_TREE_LAYOUT);
        gtnLayout.setMargin(true);
        gtnLayout.setSpacing(true);
        gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_50);
        gtnLayout.setComponentHight(GtnFrameworkCssConstants.PIXEL_1000);
        componentList.add(gtnLayout);
        addCustomViewTreeStructureComponent(componentList, namespacePrefix);
    }

	private void addCustomViewTreeStructureComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespacePrefix) {
        addCustomerTreeComponent(componentList, namespacePrefix);
        addCustomerMoveButtonsComponent(componentList, namespacePrefix);
        addProductTreeComponent(componentList, namespacePrefix);
        addProductMoveButtonsComponent(componentList, namespacePrefix);
        addDeductionHierarchyTable(componentList, namespacePrefix);
        addDeductionMoveButtonsComponent(componentList, namespacePrefix);
    }

    private void addCustomerTreeComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_TREE_LAYOUT, true,
                GtnFrameworkCVConstants.CV_TREE_VERTICAL_LAYOUT);
        componentList.add(gtnLayout);

        GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_LEVEL, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_TREE_LAYOUT, GtnUIFrameworkComponentType.DATATABLE);
        searchResultConfig.setAuthorizationIncluded(true);
        searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
        searchResultConfig.setComponentName("Customer Hierarchy");
        List<String> tableStyle = new ArrayList<>();
        tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
        tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
        tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
        tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
        searchResultConfig.setComponentStyle(tableStyle);
        searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_400);
	searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_450);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig rbSearchResults = new GtnUIFrameworkPagedTableConfig();
		rbSearchResults.setEditable(false);
		rbSearchResults.setFilterBar(false);
		rbSearchResults.setSelectable(true);
		rbSearchResults.setTableColumnDataType(new Class<?>[] { String.class });
		rbSearchResults.setTableVisibleHeader(new String[] { GtnFrameworkCVConstants.LEVEL_NAME_HEADER });
		rbSearchResults.setTableColumnMappingId(new String[] { GtnFrameworkCVConstants.LEVEL_NAME });
		rbSearchResults.setPageLength(5);
		rbSearchResults.setExtraColumn(new String[] { GtnFrameworkCVConstants.LEVEL_NO_COLUMN,
				GtnFrameworkCVConstants.TREE_LEVEL_NO_COLUMN, GtnFrameworkCVConstants.HIERARCHY_INDICATOR_COLUMN,
				GtnFrameworkCVConstants.HIERARCHY_LEVEL_DEF_SID });
		rbSearchResults
				.setExtraColumnDataType(new Class<?>[] { Integer.class, Integer.class, String.class, Integer.class });
		searchResultConfig.setGtnPagedTableConfig(rbSearchResults);
	}

	private void addCustomerMoveButtonsComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.GTN_CUSTOMER_MOVE_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_TREE_LAYOUT);

		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_DUAL_LIST_BOX_BUTTON));

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customerAddButtonMoveRightButtonLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.CUSTOMER_ADD_BTN_MOVE_RIGHT_BTN_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.GTN_CUSTOMER_MOVE_LAYOUT);
		componentList.add(customerAddButtonMoveRightButtonLayout);

		GtnUIFrameworkComponentConfig customerAddButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnCustomerGreaterButton", true,
				GtnFrameworkCVConstants.CUSTOMER_ADD_BTN_MOVE_RIGHT_BTN_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		customerAddButtonConfig.setAuthorizationIncluded(true);
		customerAddButtonConfig.setComponentName(">");
		componentList.add(customerAddButtonConfig);

		GtnUIFrameWorkActionConfig addToTreeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addToTreeAction.addActionParameter(GtnFrameworkCustomerAddAction.class.getName());
		addToTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_LEVEL);
		addToTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		actionConfigList.add(addToTreeAction);
		customerAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig customerAddButtonMoveLeftButtonLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.CUSTOMER_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.GTN_CUSTOMER_MOVE_LAYOUT);
		componentList.add(customerAddButtonMoveLeftButtonLayout);

		GtnUIFrameworkComponentConfig customerRemoveButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnCustomerLesserButton", true,
				GtnFrameworkCVConstants.CUSTOMER_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT,
                GtnUIFrameworkComponentType.BUTTON);
        customerRemoveButtonConfig.setAuthorizationIncluded(true);
        customerRemoveButtonConfig.setComponentName("<");
        componentList.add(customerRemoveButtonConfig);

        GtnUIFrameWorkActionConfig removeFromTreeAction = configProvider
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
        removeFromTreeAction.addActionParameter(GtnFrameworkRemoveAction.class.getName());
        removeFromTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_LEVEL);
        removeFromTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
        removeFromTreeAction.addActionParameter("C");
        List<GtnUIFrameWorkActionConfig> removeActionConfigList = new ArrayList<>();
        removeActionConfigList.add(removeFromTreeAction);
        customerRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(removeActionConfigList);
    }

    private void addProductTreeComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.PRODUCT_TREE_LAYOUT, true,
                GtnFrameworkCVConstants.CV_TREE_VERTICAL_LAYOUT);
        componentList.add(gtnLayout);

        GtnUIFrameworkComponentConfig productTableConfig = configProvider.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCVConstants.PRODUCT_LEVEL, true,
                namspacePrefix + GtnFrameworkCVConstants.PRODUCT_TREE_LAYOUT, GtnUIFrameworkComponentType.DATATABLE);
        productTableConfig.setAuthorizationIncluded(true);
        productTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
        productTableConfig.setComponentName("Product Hierarchy");
        List<String> tableStyle = new ArrayList<>();
        tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
        tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
        tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
        tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
        productTableConfig.setComponentStyle(tableStyle);
        productTableConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_400);
	productTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_450);

		componentList.add(productTableConfig);

		GtnUIFrameworkPagedTableConfig cvProductTable = new GtnUIFrameworkPagedTableConfig();
		cvProductTable.setEditable(false);
		cvProductTable.setFilterBar(false);
		cvProductTable.setSelectable(true);
		cvProductTable.setDefaultDateFormat(null);
		cvProductTable.setSinkItemPerPageWithPageLength(false);
		cvProductTable.setTableColumnDataType(new Class<?>[] { String.class });
		cvProductTable.setTableVisibleHeader(new String[] { GtnFrameworkCVConstants.LEVEL_NAME_HEADER });
		cvProductTable.setTableColumnMappingId(new String[] { GtnFrameworkCVConstants.LEVEL_NAME });
		cvProductTable.setPageLength(5);
		cvProductTable.setExtraColumn(new String[] { GtnFrameworkCVConstants.LEVEL_NO_COLUMN,
				GtnFrameworkCVConstants.TREE_LEVEL_NO_COLUMN, GtnFrameworkCVConstants.HIERARCHY_INDICATOR_COLUMN,
				GtnFrameworkCVConstants.HIERARCHY_LEVEL_DEF_SID });
		cvProductTable
				.setExtraColumnDataType(new Class<?>[] { Integer.class, Integer.class, String.class, Integer.class });
		productTableConfig.setGtnPagedTableConfig(cvProductTable);
	}

	private void addProductMoveButtonsComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.GTN_PRODUCT_MOVE_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.PRODUCT_TREE_LAYOUT);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_DUAL_LIST_BOX_BUTTON));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig productAddButtonMoveRightButtonLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.PRODUCT_ADD_BUTTON_MOVE_RIGHT_BUTTON_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.GTN_PRODUCT_MOVE_LAYOUT);
		componentList.add(productAddButtonMoveRightButtonLayout);

		GtnUIFrameworkComponentConfig productAddButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnProductGreaterButton", true,
				GtnFrameworkCVConstants.PRODUCT_ADD_BUTTON_MOVE_RIGHT_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		productAddButtonConfig.setAuthorizationIncluded(true);
		productAddButtonConfig.setComponentName(">");
		componentList.add(productAddButtonConfig);

		GtnUIFrameWorkActionConfig addProductToTreeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addProductToTreeAction.addActionParameter(GtnFrameworkCustomerAddAction.class.getName());
		addProductToTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.PRODUCT_LEVEL);
		addProductToTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		actionConfigList.add(addProductToTreeAction);
		productAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig productAddButtonMoveLeftButtonLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.PRODUCT_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.GTN_PRODUCT_MOVE_LAYOUT);
		componentList.add(productAddButtonMoveLeftButtonLayout);

		GtnUIFrameworkComponentConfig productRemoveButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnProductLesserButton", true,
				GtnFrameworkCVConstants.PRODUCT_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		productRemoveButtonConfig.setAuthorizationIncluded(true);
		productRemoveButtonConfig.setComponentName("<");
		componentList.add(productRemoveButtonConfig);

		GtnUIFrameWorkActionConfig removeProductFromTreeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeProductFromTreeAction.addActionParameter(GtnFrameworkRemoveAction.class.getName());
		removeProductFromTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.PRODUCT_LEVEL);
		removeProductFromTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		removeProductFromTreeAction.addActionParameter("P");
		List<GtnUIFrameWorkActionConfig> removeActionConfigList = new ArrayList<>();
		removeActionConfigList.add(removeProductFromTreeAction);
		productRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(removeActionConfigList);
	}

	private void addCustomViewTree(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				namespacePrefix + GtnFrameworkCVConstants.CUSTOM_TREE_LAYOUT, true,
				GtnFrameworkCVConstants.CV_TREE_CONSTRUCT_PANEL);
		gtnLayout.setMargin(true);
		gtnLayout.setSpacing(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_50);
		gtnLayout.setComponentHight(GtnFrameworkCssConstants.PIXEL_1000);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig treeComponentConfig = new GtnUIFrameworkComponentConfig();

		treeComponentConfig.setComponentType(GtnUIFrameworkComponentType.TREE);
		treeComponentConfig.setAuthorizationIncluded(true);
		treeComponentConfig.setComponentId(namespacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		treeComponentConfig.setParentComponentId(namespacePrefix + GtnFrameworkCVConstants.CUSTOM_TREE_LAYOUT);
		treeComponentConfig.setAddToParent(true);

		GtnUIFrameworkTreeConfig treeConfig = new GtnUIFrameworkTreeConfig();
		treeConfig.setItemCaptionPropertyId(GtnFrameworkCVConstants.LEVEL_NAME);
		treeConfig.setSelectable(true);
		treeComponentConfig.setGtnUIFrameworkTreeConfig(treeConfig);
		componentList.add(treeComponentConfig);
	}

	private void addCustomViewTreePanel(List<GtnUIFrameworkComponentConfig> componentList, String namespacePrefix) {
		GtnUIFrameworkComponentConfig panel = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCVConstants.CV_TREE_CONSTRUCT_PANEL, true, GtnFrameworkCVConstants.CUSTOM_VIEW_TREE_LAYOUT,
				GtnUIFrameworkComponentType.PANEL);
		panel.setComponentName("Tree Structure");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_50);
		panel.setComponentHight(GtnFrameworkCssConstants.PIXEL_1000);
		componentList.add(panel);
		addCustomViewTree(componentList, namespacePrefix);
	}

	private void addDeductionHierarchyTable(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_HIER_LAYOUT, true,
				GtnFrameworkCVConstants.CV_TREE_VERTICAL_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionTableConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_LEVEL, true,
				namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_HIER_LAYOUT, GtnUIFrameworkComponentType.DATATABLE);
		deductionTableConfig.setAuthorizationIncluded(true);
		deductionTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		deductionTableConfig.setComponentName("Deduction Hierarchy");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		deductionTableConfig.setComponentStyle(tableStyle);
		deductionTableConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_400);
		deductionTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_450);

		componentList.add(deductionTableConfig);

		GtnUIFrameworkPagedTableConfig cvDeductionTable = new GtnUIFrameworkPagedTableConfig();
		cvDeductionTable.setEditable(false);
		cvDeductionTable.setFilterBar(false);
		cvDeductionTable.setSelectable(true);
		cvDeductionTable.setTableColumnDataType(new Class<?>[] { String.class });
		cvDeductionTable.setTableVisibleHeader(new String[] { GtnFrameworkCVConstants.LEVEL_NAME_HEADER });
		cvDeductionTable.setTableColumnMappingId(new String[] { GtnFrameworkCVConstants.LEVEL_NAME });
		cvDeductionTable.setPageLength(5);
		cvDeductionTable.setExtraColumn(new String[] { GtnFrameworkCVConstants.LEVEL_NO_COLUMN,
				GtnFrameworkCVConstants.TREE_LEVEL_NO_COLUMN, GtnFrameworkCVConstants.HIERARCHY_INDICATOR_COLUMN,
				GtnFrameworkCVConstants.HIERARCHY_LEVEL_DEF_SID });
		cvDeductionTable
				.setExtraColumnDataType(new Class<?>[] { Integer.class, Integer.class, String.class, Integer.class });
		deductionTableConfig.setGtnPagedTableConfig(cvDeductionTable);
	}

	private void addDeductionMoveButtonsComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_MOVE_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_HIER_LAYOUT);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_DUAL_LIST_BOX_BUTTON));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionAddButtonMoveRightButtonLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCVConstants.DEDUCTION_ADD_BUTTON_MOVE_RIGHT_BUTTON_LAYOUT, true,
						namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_MOVE_LAYOUT);
		componentList.add(deductionAddButtonMoveRightButtonLayout);

		GtnUIFrameworkComponentConfig customerAddButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnDeductionGreaterButton", true,
				GtnFrameworkCVConstants.DEDUCTION_ADD_BUTTON_MOVE_RIGHT_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		customerAddButtonConfig.setAuthorizationIncluded(true);
		customerAddButtonConfig.setComponentName(">");
		componentList.add(customerAddButtonConfig);

		GtnUIFrameWorkActionConfig addToTreeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addToTreeAction.addActionParameter(GtnFrameworkCustomerAddAction.class.getName());
		addToTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_LEVEL);
		addToTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		actionConfigList.add(addToTreeAction);
		customerAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig deductionAddButtonMoveLeftButtonLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCVConstants.DEDUCTION_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT, true,
				namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_MOVE_LAYOUT);
		componentList.add(deductionAddButtonMoveLeftButtonLayout);

		GtnUIFrameworkComponentConfig customerRemoveButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnDeductionLesserButton", true,
				GtnFrameworkCVConstants.DEDUCTION_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		customerRemoveButtonConfig.setAuthorizationIncluded(true);
		customerRemoveButtonConfig.setComponentName("<");
		componentList.add(customerRemoveButtonConfig);

		GtnUIFrameWorkActionConfig removeFromTreeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeFromTreeAction.addActionParameter(GtnFrameworkRemoveAction.class.getName());
		removeFromTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.DEDUCTION_LEVEL);
		removeFromTreeAction.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
		removeFromTreeAction.addActionParameter("D");
		List<GtnUIFrameWorkActionConfig> removeActionConfigList = new ArrayList<>();
		removeActionConfigList.add(removeFromTreeAction);
		customerRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(removeActionConfigList);
	}
}
