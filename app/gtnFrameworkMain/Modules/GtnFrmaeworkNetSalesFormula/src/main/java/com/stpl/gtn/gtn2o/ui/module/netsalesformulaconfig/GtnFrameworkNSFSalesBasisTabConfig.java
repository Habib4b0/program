package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfContractSelectionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSBPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSalesBasisAddAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSalesBasisDisplayAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkSetSelectionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfTabTableResetConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUIFrameWorkNsfNetSalesRulePopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUiFrameworkNsfSalesBasisTabFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfPopulateValidationAction;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFComboBoxTypeConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

public class GtnFrameworkNSFSalesBasisTabConfig {

	private GtnFrameworkComponentConfigProvider componentConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkTabConfig addSalesBasisTabConfig(String viewId) {

		String salesBasisTabId = viewId + "salesBasisTabMainLayout";
		GtnUIFrameworkTabConfig tabConfig = new GtnUIFrameworkTabConfig();
		tabConfig.setComponentId(salesBasisTabId);
		tabConfig.setTabCaption("Sales Basis");
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		tabConfig.setTabLayoutComponentConfigList(componentList);

		GtnUIFrameworkComponentConfig salesBasisTabLayout = componentConfigProvider
				.getVerticalLayoutConfig(tabConfig.getComponentId(), true, tabConfig.getComponentId());
		salesBasisTabLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		salesBasisTabLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_10);
		salesBasisTabLayout.setTabComponent(true);
		componentList.add(salesBasisTabLayout);

		salesBasisPanel(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addSearchCriteriaPanel(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addSalesBasisTabButtonLayout(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addAvailableContractsResultPanel(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addDisplayButton(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addAvailableCustomersResultPanel(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addSalesBasisTabAddButton(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addSelectedCustomersResultPanel(componentList, salesBasisTabLayout.getComponentId(), viewId);
		addResetRemoveExcelButtonLayout(componentList, salesBasisTabLayout.getComponentId(), viewId);
		return tabConfig;
	}

	private void salesBasisPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig salesBasisPanelConfig = componentConfigProvider
				.getPanelConfig(parentId + "salesBasisOptionsPanel", true, parentId);
		salesBasisPanelConfig.setComponentName("Sales Basis Options");
		salesBasisPanelConfig.setAuthorizationIncluded(true);
		componentList.add(salesBasisPanelConfig);

		GtnUIFrameworkComponentConfig salesBasisMainCssLayoutConfig = componentConfigProvider.getCssLayoutConfig(
				viewId + "salesBasisOptionMainLayout", true, salesBasisPanelConfig.getComponentId());
		salesBasisMainCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_11);
		componentList.add(salesBasisMainCssLayoutConfig);

		salesBasisComponents(componentList, salesBasisMainCssLayoutConfig.getComponentId(), viewId);

	}

	private void salesBasisComponents(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig salesBasisChildCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(viewId + "salesBasisChildCssLayout", true, parentId);
		salesBasisChildCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_20);
		componentList.add(salesBasisChildCssLayoutConfig);

		addContractSelection(componentList, salesBasisChildCssLayoutConfig.getComponentId(), viewId);
		addNetSaleRule(componentList, salesBasisChildCssLayoutConfig.getComponentId(), viewId);
	}

	private void addContractSelection(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "contractSelection";
		GtnUIFrameworkComponentConfig contractSelectionLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		contractSelectionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(contractSelectionLayout);

		GtnUIFrameworkComponentConfig contractSelectionConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, contractSelectionLayout.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
		contractSelectionConfig.setComponentName("Contract Selection");
		contractSelectionConfig.setAuthorizationIncluded(true);
		componentList.add(contractSelectionConfig);

		GtnUIFrameworkOptionGroupConfig contractSelectionOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		contractSelectionOptionGroupConfig.setValuesFromService(false);
		contractSelectionOptionGroupConfig.setItemValues(Arrays.asList(GtnFrameworkNSFConstants.getExistingContract(),
				GtnFrameworkNSFConstants.getSelectContract()));
		contractSelectionConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE,
				GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		contractSelectionConfig.setGtnUIFrameworkOptionGroupConfig(contractSelectionOptionGroupConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfContractSelectionAction.class.getName());
		customAction.addActionParameter(viewId);
		actionConfigList.add(customAction);
		contractSelectionConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addNetSaleRule(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + "netSalesRule";
		GtnUIFrameworkComponentConfig netSaleRuleLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		netSaleRuleLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(netSaleRuleLayout);

		GtnUIFrameworkComponentConfig netSalesRuleConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, netSaleRuleLayout.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		netSalesRuleConfig.setAuthorizationIncluded(true);
		netSalesRuleConfig.setComponentName(GtnFrameworkCommonConstants.NET_SALES_RULE);
		netSalesRuleConfig.addComponentStyle(GtnFrameworkCssConstants.SEARCHICON);
		componentList.add(netSalesRuleConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig netSaleRulePopupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(netSaleRulePopupActionConfig);
		netSaleRulePopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParamList = new ArrayList<>();
		popupActionParamList.add("CDRPopUpSearchSearchView");
		popupActionParamList.add(GtnFrameworkCommonConstants.NET_SALES_RULE);
		popupActionParamList.add("70%");

		netSaleRulePopupActionConfig.setActionParameterList(popupActionParamList);

		GtnUIFrameWorkActionConfig netSaleRulePopupDefaultActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> defaultDataMap = new HashMap<>();

		defaultDataMap.put("ruleType", 1704);
		defaultDataMap.put(GtnFrameworkCommonConstants.RULE_NO, "");
		defaultDataMap.put(GtnFrameworkCommonConstants.RULE_NAME, "");
		defaultDataMap.put("ruleCategory", null);
		defaultDataMap.put("cDRPopUpsearchResultTable", "");
		defaultDataMap.put("cDRRulePopUpruleDetailsattachResultTable", null);

		netSaleRulePopupDefaultActionConfig.addActionParameter(defaultDataMap);
		actionConfigList.add(netSaleRulePopupDefaultActionConfig);

		GtnUIFrameWorkActionConfig customSetCongigAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object customClassName = GtnUiFrameworkSetSelectionAction.class.getName();
		customSetCongigAction.setActionParameterList(Arrays.asList(customClassName));

		actionConfigList.add(customSetCongigAction);
		netSalesRuleConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		GtnUIFrameworkComponentConfig salesBasisTabSearchPanel = componentConfigProvider
				.getPanelConfig(viewId + "searchCriteriaPanel", true, parentId);
		salesBasisTabSearchPanel.setComponentName("Search Criteria");
		salesBasisTabSearchPanel.setAuthorizationIncluded(true);
		salesBasisTabSearchPanel.setEnable(false);
		componentList.add(salesBasisTabSearchPanel);
		addFieldLayout(componentList, salesBasisTabSearchPanel.getComponentId(), viewId);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig salesBasisTabSearchFieldMainLayout = componentConfigProvider
				.getCssLayoutConfig(viewId + "searchCriteriaMainlayout", true, parentId);
		salesBasisTabSearchFieldMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(salesBasisTabSearchFieldMainLayout);
		addFieldComponent(componentList, salesBasisTabSearchFieldMainLayout.getComponentId(), viewId);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		GtnUIFrameworkComponentConfig salesBasisTabSearchFieldLayout = componentConfigProvider
				.getCssLayoutConfig(viewId + "searchCriteriaFieldlayout", true, parentId);
		salesBasisTabSearchFieldLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(salesBasisTabSearchFieldLayout);

		addContractNo(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addContractName(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addContractHolder(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);

		addMarketType(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addCFPNo(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addCFPName(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);

		addIFPNo(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addIFPName(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addCompanyNo(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);

		addCompanyName(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addItemNo(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);
		addItemName(componentList, salesBasisTabSearchFieldLayout.getComponentId(), viewId);

	}

	private void addContractNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.CONTRACT_NO;
		GtnUIFrameworkComponentConfig salesBasisTabContractNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabContractNoLayout);

		GtnUIFrameworkComponentConfig contractNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabContractNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractNoConfig.setAuthorizationIncluded(true);
		contractNoConfig.setComponentName("Contract No");
		contractNoConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> contractNoConditionsList = new ArrayList<>();
		contractNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(contractNoConditionsList);
		contractNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(contractNoConfig);
	}

	private void addContractName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.CONTRACT_NAME;
		GtnUIFrameworkComponentConfig salesBasisTabContractNameLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabContractNameLayout);

		GtnUIFrameworkComponentConfig contractNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabContractNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractNameConfig.setAuthorizationIncluded(true);
		contractNameConfig.setComponentName("Contract Name");
		contractNameConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> contractNameConditionsList = new ArrayList<>();
		contractNameConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(contractNameConditionsList);
		contractNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(contractNameConfig);

	}

	private void addContractHolder(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.CONTRACT_HOLDER;
		GtnUIFrameworkComponentConfig salesBasisTabContractHolderLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabContractHolderLayout);

		GtnUIFrameworkComponentConfig contractHolderConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabContractHolderLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractHolderConfig.setAuthorizationIncluded(true);
		contractHolderConfig.setComponentName("Contract Holder");
		contractHolderConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> contractHolderConditionsList = new ArrayList<>();
		contractHolderConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(contractHolderConditionsList);
		contractHolderConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(contractHolderConfig);

	}

	private void addMarketType(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.MARKET_TYPE;
		GtnUIFrameworkComponentConfig salesBasisTabMarketTypeLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabMarketTypeLayout);

		GtnUIFrameworkComponentConfig marketType = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, salesBasisTabMarketTypeLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		marketType.setAuthorizationIncluded(true);
		marketType.setComponentName("Market Type");
		marketType.setEnable(false);
		componentList.add(marketType);

		GtnUIFrameworkComboBoxConfig salesBasisTabMarketTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.CONTRACT_TYPE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		marketType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		marketType.setGtnComboboxConfig(salesBasisTabMarketTypeConfig);
	}

	private void addCFPNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.CFP_NO;
		GtnUIFrameworkComponentConfig salesBasisTabCFPNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabCFPNoLayout);

		GtnUIFrameworkComponentConfig cfpNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, salesBasisTabCFPNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cfpNoConfig.setAuthorizationIncluded(true);
		cfpNoConfig.setComponentName("Company Family Plan No");
		cfpNoConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabCFPNoConditionsList = new ArrayList<>();
		salesBasisTabCFPNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(salesBasisTabCFPNoConditionsList);
		cfpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(cfpNoConfig);
	}

	private void addCFPName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.CFP_NAME;
		GtnUIFrameworkComponentConfig salesBasisTabCFPNameLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabCFPNameLayout);

		GtnUIFrameworkComponentConfig cfpNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, salesBasisTabCFPNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		cfpNameConfig.setAuthorizationIncluded(true);
		cfpNameConfig.setComponentName("Company Family Plan Name");
		cfpNameConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabCFPNoConditionsList = new ArrayList<>();
		salesBasisTabCFPNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig salesBasisTabCFPNoValidationConfig = new GtnUIFrameworkValidationConfig();
		salesBasisTabCFPNoValidationConfig.setConditionList(salesBasisTabCFPNoConditionsList);
		cfpNameConfig.setGtnUIFrameworkValidationConfig(salesBasisTabCFPNoValidationConfig);
		componentList.add(cfpNameConfig);

	}

	private void addIFPNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.IFP_NUMBER;
		GtnUIFrameworkComponentConfig salesBasisTabIFPNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabIFPNoLayout);

		GtnUIFrameworkComponentConfig ifpNoConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, salesBasisTabIFPNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		ifpNoConfig.setAuthorizationIncluded(true);
		ifpNoConfig.setComponentName("Item Family Plan No");
		ifpNoConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabIFPNoConditionsList = new ArrayList<>();
		salesBasisTabIFPNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setConditionList(salesBasisTabIFPNoConditionsList);
		ifpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(ifpNoConfig);

	}

	private void addIFPName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.IFP_NAME;
		GtnUIFrameworkComponentConfig salesBasisTabIFPNameLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabIFPNameLayout);

		GtnUIFrameworkComponentConfig ifpNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(componentId,
				true, salesBasisTabIFPNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		ifpNameConfig.setAuthorizationIncluded(true);
		ifpNameConfig.setComponentName("Item Family Plan Name");
		ifpNameConfig.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabIFPNoConditionsList = new ArrayList<>();
		salesBasisTabIFPNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig salesBasisTabIFPNoValidationConfig = new GtnUIFrameworkValidationConfig();
		salesBasisTabIFPNoValidationConfig.setConditionList(salesBasisTabIFPNoConditionsList);
		ifpNameConfig.setGtnUIFrameworkValidationConfig(salesBasisTabIFPNoValidationConfig);
		componentList.add(ifpNameConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO;
		GtnUIFrameworkComponentConfig salesBasisTabCompanyNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabCompanyNoLayout);

		GtnUIFrameworkComponentConfig salesBasisTabCompanyNo = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabCompanyNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		salesBasisTabCompanyNo.setAuthorizationIncluded(true);
		salesBasisTabCompanyNo.setComponentName("Company No");
		salesBasisTabCompanyNo.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabCompanyNoConditionsList = new ArrayList<>();
		salesBasisTabCompanyNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig salesBasisTabCompanyNoValidationConfig = new GtnUIFrameworkValidationConfig();
		salesBasisTabCompanyNoValidationConfig.setConditionList(salesBasisTabCompanyNoConditionsList);
		salesBasisTabCompanyNo.setGtnUIFrameworkValidationConfig(salesBasisTabCompanyNoValidationConfig);
		componentList.add(salesBasisTabCompanyNo);

	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME;
		GtnUIFrameworkComponentConfig salesBasisTabCompanyNameLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabCompanyNameLayout);

		GtnUIFrameworkComponentConfig salesBasisTabCompanyName = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabCompanyNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		salesBasisTabCompanyName.setAuthorizationIncluded(true);
		salesBasisTabCompanyName.setComponentName("Company Name");
		salesBasisTabCompanyName.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabCompanyNameConditionsList = new ArrayList<>();
		salesBasisTabCompanyNameConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig salesBasisTabCompanyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		salesBasisTabCompanyNameValidationConfig.setConditionList(salesBasisTabCompanyNameConditionsList);
		salesBasisTabCompanyName.setGtnUIFrameworkValidationConfig(salesBasisTabCompanyNameValidationConfig);
		componentList.add(salesBasisTabCompanyName);

	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + GtnFrameworkCommonConstants.ITEM_NO;
		GtnUIFrameworkComponentConfig salesBasisTabItemNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabItemNoLayout);

		GtnUIFrameworkComponentConfig salesBasisTabItemNo = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabItemNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		salesBasisTabItemNo.setAuthorizationIncluded(true);
		salesBasisTabItemNo.setComponentName("Item No");
		salesBasisTabItemNo.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabItemNoConditionsList = new ArrayList<>();
		salesBasisTabItemNoConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig salesBasisTabItemNoValidationConfig = new GtnUIFrameworkValidationConfig();
		salesBasisTabItemNoValidationConfig.setConditionList(salesBasisTabItemNoConditionsList);
		salesBasisTabItemNo.setGtnUIFrameworkValidationConfig(salesBasisTabItemNoValidationConfig);
		componentList.add(salesBasisTabItemNo);
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + GtnFrameworkCommonConstants.ITEM_NAME;
		GtnUIFrameworkComponentConfig salesBasisTabItemNameLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabItemNameLayout);

		GtnUIFrameworkComponentConfig salesBasisTabItemName = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabItemNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		salesBasisTabItemName.setAuthorizationIncluded(true);
		salesBasisTabItemName.setComponentName("Item Name");
		salesBasisTabItemName.setEnable(false);

		List<GtnUIFrameworkConditionalValidationType> salesBasisTabItemNameConditionsList = new ArrayList<>();
		salesBasisTabItemNameConditionsList.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		GtnUIFrameworkValidationConfig salesBasisTabItemNameValidationConfig = new GtnUIFrameworkValidationConfig();
		salesBasisTabItemNameValidationConfig.setConditionList(salesBasisTabItemNameConditionsList);
		salesBasisTabItemName.setGtnUIFrameworkValidationConfig(salesBasisTabItemNameValidationConfig);
		componentList.add(salesBasisTabItemName);
	}

	private void addSalesBasisTabButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String searchButtonLayoutId = viewId + "searchButtonlayout";
		GtnUIFrameworkComponentConfig salesBasisTabActionBtnLayout = componentConfigProvider
				.getCssLayoutConfig(searchButtonLayoutId, true, parentId);
		componentList.add(salesBasisTabActionBtnLayout);

		addResetButtonComponent(componentList, salesBasisTabActionBtnLayout.getComponentId(), viewId);
		addSearchButtonComponent(componentList, salesBasisTabActionBtnLayout.getComponentId(), viewId);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "searchCriteriaResetButton";
		GtnUIFrameworkComponentConfig salesBasisTabResetBtnLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabResetBtnLayout);

		GtnUIFrameworkComponentConfig salesBasisTabResetBtn = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		salesBasisTabResetBtn.setAuthorizationIncluded(true);
		salesBasisTabResetBtn.setComponentName("RESET");
		componentList.add(salesBasisTabResetBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkNSFConstants.getResetConfirmation());
		params.add(GtnFrameworkNSFConstants.getResetConfirmationMsg());

		params.add(Arrays.asList(viewId + GtnFrameworkCommonConstants.CONTRACT_NO,
				viewId + GtnFrameworkCommonConstants.CONTRACT_NAME,
				viewId + GtnFrameworkCommonConstants.CONTRACT_HOLDER, viewId + GtnFrameworkCommonConstants.MARKET_TYPE,
				viewId + GtnFrameworkCommonConstants.CFP_NO, viewId + GtnFrameworkCommonConstants.CFP_NAME,
				viewId + GtnFrameworkCommonConstants.IFP_NUMBER, viewId + GtnFrameworkCommonConstants.IFP_NAME,
				viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO,
				viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				viewId + GtnFrameworkCommonConstants.ITEM_NO, viewId + GtnFrameworkCommonConstants.ITEM_NAME));
		Object empty = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		params.add(Arrays.asList(empty, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		salesBasisTabResetBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String componentId = viewId + "salesBasisSearchButton";
		GtnUIFrameworkComponentConfig salesBasisTabSearchBtnLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabSearchBtnLayout);

		GtnUIFrameworkComponentConfig salesBasisSearchButton = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		salesBasisSearchButton.setAuthorizationIncluded(true);
		salesBasisSearchButton.setComponentName("SEARCH");
		componentList.add(salesBasisSearchButton);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(viewId + GtnFrameworkCommonConstants.CONTRACT_NO,
				viewId + GtnFrameworkCommonConstants.CONTRACT_NAME,
				viewId + GtnFrameworkCommonConstants.CONTRACT_HOLDER, viewId + GtnFrameworkCommonConstants.MARKET_TYPE,
				viewId + GtnFrameworkCommonConstants.CFP_NO, viewId + GtnFrameworkCommonConstants.CFP_NAME,
				viewId + GtnFrameworkCommonConstants.IFP_NUMBER, viewId + GtnFrameworkCommonConstants.IFP_NAME,
				viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO,
				viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				viewId + GtnFrameworkCommonConstants.ITEM_NO, viewId + GtnFrameworkCommonConstants.ITEM_NAME));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> salesBasisSearchBtnOnFailureList = new ArrayList<>();

		GtnUIFrameWorkActionConfig salesBasisSearchBtnAlertActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkNSFConstants.getNoSearchCriteria());
		alertParams.add(GtnFrameworkNSFConstants.getPleaseEnterSalesDeductionTabSearchCriteria());

		salesBasisSearchBtnAlertActionConfig.setActionParameterList(alertParams);
		salesBasisSearchBtnOnFailureList.add(salesBasisSearchBtnAlertActionConfig);
		validationActionConfig.addActionParameter(salesBasisSearchBtnOnFailureList);
		searchActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig salesBasisTabLoadDataTableActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(viewId + GtnFrameworkCommonConstants.AVAILABLE_CONTRACT_TABLE);

		salesBasisTabLoadDataTableActionConfig.setActionParameterList(actionParams);
		salesBasisTabLoadDataTableActionConfig.setFieldValues(Arrays.asList(
				viewId + GtnFrameworkCommonConstants.CONTRACT_NO, viewId + GtnFrameworkCommonConstants.CONTRACT_NAME,
				viewId + GtnFrameworkCommonConstants.CONTRACT_HOLDER, viewId + GtnFrameworkCommonConstants.MARKET_TYPE,
				viewId + GtnFrameworkCommonConstants.CFP_NO, viewId + GtnFrameworkCommonConstants.CFP_NAME,
				viewId + GtnFrameworkCommonConstants.IFP_NUMBER, viewId + GtnFrameworkCommonConstants.IFP_NAME,
				viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO,
				viewId + GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				viewId + GtnFrameworkCommonConstants.ITEM_NO, viewId + GtnFrameworkCommonConstants.ITEM_NAME));

		searchActionConfigList.add(salesBasisTabLoadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(viewId + GtnFrameworkCommonConstants.AVAILABLE_CONTRACT_TABLE);
		searchActionConfigList.add(notificationActionConfig);
		salesBasisSearchButton.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addAvailableContractsResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig availableContractPanelConfig = componentConfigProvider
				.getPanelConfig(viewId + "availableContractPanel", true, parentId);
		availableContractPanelConfig.setComponentName("Available Contracts");
		componentList.add(availableContractPanelConfig);

		addAvailableContractResultLayout(componentList, availableContractPanelConfig.getComponentId(), viewId);
	}

	private void addAvailableContractResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.AVAILABLE_CONTRACT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayoutConfig);
		addAvailableContractsPagedTableComponent(componentList, componentId, gtnLayoutConfig.getComponentId());
	}

	private void addAvailableContractsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String avalableContractTableId, String parentId) {

		GtnUIFrameworkComponentConfig avalableContractTableConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(avalableContractTableId, true, parentId,
						GtnUIFrameworkComponentType.PAGEDTABLE);
		avalableContractTableConfig.setAuthorizationIncluded(true);
		avalableContractTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		avalableContractTableConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(avalableContractTableConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setItemPerPage(10);
		searchResults.setPageLength(10);
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class, String.class, String.class });
		searchResults.setTableVisibleHeader(GtnFrameworkNSFConstants.getAvailableContractVisibleHeaders());
		searchResults.setTableColumnMappingId(GtnFrameworkNSFConstants.getAvailableContractVisibleColumns());
		searchResults.setExtraColumn(new Object[] { GtnFrameworkNSFConstants.getSystemid() });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("NScontractSearch");
		searchResults.setQueryName(GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_CONTRACTS_SEARCH_QUERY_NAME);
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);

		searchResults.setCustomFilterConfigMap(getAvalableContractTableCustomFilterConfig());
		avalableContractTableConfig.setGtnPagedTableConfig(searchResults);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getAvalableContractTableCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkCommonConstants.MARKET_TYPE };
		String[] listNameArray = { GtnFrameworkNSFComboBoxTypeConstants.CONTRACT_TYPE };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig salesBasisTabCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			salesBasisTabCustomFilterConfig.setPropertId(propertyIds[i]);
			salesBasisTabCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			customFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			customFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			salesBasisTabCustomFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(salesBasisTabCustomFilterConfig.getPropertId(), salesBasisTabCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

	private void addDisplayButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String diaplayButtonLayoutId = viewId + "displayButtonlayout";
		GtnUIFrameworkComponentConfig salesBasisTabDisplayBtnLayout = componentConfigProvider
				.getCssLayoutConfig(diaplayButtonLayoutId, true, parentId);
		componentList.add(salesBasisTabDisplayBtnLayout);

		String diaplayButtonComponentId = viewId + "displayButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider.getHorizontalLayoutConfig(
				diaplayButtonComponentId + GtnFrameworkCommonStringConstants.LAYOUT, true,
				salesBasisTabDisplayBtnLayout.getComponentId());
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig displayButtonConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				diaplayButtonComponentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		displayButtonConfig.setComponentName("DISPLAY");
		displayButtonConfig.setAuthorizationIncluded(true);
		componentList.add(displayButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfSalesBasisDisplayAction.class.getName());
		customAction.addActionParameter(viewId);
		actionConfigList.add(customAction);
		displayButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addAvailableCustomersResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig availableCustomersResultPanelConfig = componentConfigProvider
				.getPanelConfig(viewId + "availableCustomersResultPanel", true, parentId);
		availableCustomersResultPanelConfig.setComponentName("Available Customers");
		componentList.add(availableCustomersResultPanelConfig);
		addAvailableCustomersResultLayout(componentList, availableCustomersResultPanelConfig.getComponentId(), viewId);
	}

	private void addAvailableCustomersResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "availableCustomersTable";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayoutConfig);
		addAvailableCustomersPagedTableComponent(componentList, gtnLayoutConfig.getComponentId(), viewId);
	}

	private void addAvailableCustomersPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {
		String componentId = viewId + "availableCustomersTable";
		GtnUIFrameworkComponentConfig availableCustomersTableConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, parentId, GtnUIFrameworkComponentType.PAGEDTABLE);
		availableCustomersTableConfig.setAuthorizationIncluded(true);

		availableCustomersTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		availableCustomersTableConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(availableCustomersTableConfig);

		GtnUIFrameworkPagedTableConfig availableCustomersTable = new GtnUIFrameworkPagedTableConfig();
		availableCustomersTable.setEditable(false);
		availableCustomersTable.setFilterBar(true);
		availableCustomersTable.setSelectable(true);
		availableCustomersTable.setMultiSelect(true);
		availableCustomersTable.setItemPerPage(10);
		availableCustomersTable.setPageLength(10);
		availableCustomersTable.setSinkItemPerPageWithPageLength(false);
		availableCustomersTable.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, String.class, String.class, String.class });
		availableCustomersTable.setTableVisibleHeader(GtnFrameworkNSFConstants.getAvailableCustomerVisibleHeaders());
		availableCustomersTable.setTableColumnMappingId(GtnFrameworkNSFConstants.getAvailableCustomerVisibleColumns());
		availableCustomersTable.setExtraColumn(new Object[] { GtnFrameworkNSFConstants.getSystemid(),
				GtnFrameworkNSFConstants.getCfpContractDetailsSid() });
		availableCustomersTable.setExtraColumnDataType(new Class<?>[] { Integer.class, String.class });
		availableCustomersTable.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		availableCustomersTable.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		availableCustomersTable.setModuleName("NSAvailableCustomerSearch");
		availableCustomersTable.setQueryName(GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_CUSTOMERS_SEARCH_QUERY_NAME);
		availableCustomersTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		availableCustomersTableConfig.setGtnPagedTableConfig(availableCustomersTable);
	}

	private void addSalesBasisTabAddButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String addButtonLayoutId = viewId + "addButtonlayout";
		GtnUIFrameworkComponentConfig addCssLayoutConfig = componentConfigProvider.getCssLayoutConfig(addButtonLayoutId,
				true, parentId);
		componentList.add(addCssLayoutConfig);

		String addButtonComponentId = viewId + "addButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider.getHorizontalLayoutConfig(
				addButtonComponentId + GtnFrameworkCommonStringConstants.LAYOUT, true,
				addCssLayoutConfig.getComponentId());
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig addButtonConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				addButtonComponentId, true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setAuthorizationIncluded(true);
		addButtonConfig.setComponentName("ADD");
		componentList.add(addButtonConfig);
		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfSalesBasisAddAction.class.getName());
		customAction.addActionParameter(viewId);
		addActionConfigList.add(customAction);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);

	}

	private void addSelectedCustomersResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig selectedCustomersResultPanelConfig = componentConfigProvider
				.getPanelConfig(viewId + "selectedCustomersResultPanel", true, parentId);
		selectedCustomersResultPanelConfig.setComponentName("Selected Customers");
		componentList.add(selectedCustomersResultPanelConfig);
		addSelectedCustomersResultLayout(componentList, selectedCustomersResultPanelConfig.getComponentId(), viewId);
	}

	private void addSelectedCustomersResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig selectedCustomersMainlayoutConfig = componentConfigProvider
				.getVerticalLayoutConfig(viewId + "selectedCustomersMainlayout", true, parentId);
		componentList.add(selectedCustomersMainlayoutConfig);
		selectedCustomersMainlayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		selectedCustomersMainlayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_10);
		addSalesBasisTabMassUpdatePanel(componentList, selectedCustomersMainlayoutConfig.getComponentId(), viewId);
		addSelectedCustomersPagedTableComponent(componentList, selectedCustomersMainlayoutConfig.getComponentId(),
				viewId);

	}

	private void addSalesBasisTabMassUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		GtnUIFrameworkComponentConfig salesBasisTabMassUpdatePanel = componentConfigProvider
				.getPanelConfig(viewId + "massUpdatePanel", true, parentId);
		salesBasisTabMassUpdatePanel.setComponentName("Mass Update");
		salesBasisTabMassUpdatePanel.setAuthorizationIncluded(true);
		componentList.add(salesBasisTabMassUpdatePanel);

		GtnUIFrameworkComponentConfig salesBasisTabMassUpdateMainCssLayoutConfig = componentConfigProvider.getCssLayoutConfig(
				viewId + "salesBasisMassUpdateMainLayout", true, salesBasisTabMassUpdatePanel.getComponentId());
		salesBasisTabMassUpdateMainCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(salesBasisTabMassUpdateMainCssLayoutConfig);
		
		addSalesBasisTabMassUpdateLayout(componentList, salesBasisTabMassUpdateMainCssLayoutConfig.getComponentId(), viewId);
	}

	private void addSalesBasisTabMassUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String massUpdateCssLayoutId = viewId + "massUpdateCsslayout";
		GtnUIFrameworkComponentConfig salesBasisTabMassUpdateLayout = componentConfigProvider
				.getCssLayoutConfig(massUpdateCssLayoutId, true, parentId);
		salesBasisTabMassUpdateLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_20);
		componentList.add(salesBasisTabMassUpdateLayout);

		addSalesBasisTabMassUpdateOptionGroup(componentList, salesBasisTabMassUpdateLayout.getComponentId(), viewId);
		addSalesBasisTabMassUpdateDdlb(componentList, salesBasisTabMassUpdateLayout.getComponentId(), viewId);
		addSalesBasisTabMassUpdatePopUpField(componentList, salesBasisTabMassUpdateLayout.getComponentId(), viewId);
		addSalesBasisTabMassUpdatePopulateButton(componentList, salesBasisTabMassUpdateLayout.getComponentId(), viewId);
	}

	private void addSalesBasisTabMassUpdateOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String componentId = viewId + "massUpdateOptionGroup";
		GtnUIFrameworkComponentConfig salesBasisTabMassUpdateOptionGroupLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabMassUpdateOptionGroupLayout);

		GtnUIFrameworkComponentConfig salesBasisTabMassUpdateOptionGroup = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true,
						salesBasisTabMassUpdateOptionGroupLayout.getComponentId(),
						GtnUIFrameworkComponentType.OPTIONGROUP);
		salesBasisTabMassUpdateOptionGroup.setAuthorizationIncluded(true);
		salesBasisTabMassUpdateOptionGroup.setComponentName(GtnFrameworkNSFConstants.getEmpty());
		componentList.add(salesBasisTabMassUpdateOptionGroup);

		GtnUIFrameworkOptionGroupConfig salesBasisTabMassUpdateOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		salesBasisTabMassUpdateOptionGroupConfig.setValuesFromService(false);
		salesBasisTabMassUpdateOptionGroupConfig.setItemValues(
				Arrays.asList(GtnFrameworkNSFConstants.getEnable(), GtnFrameworkNSFConstants.getDisable()));
		salesBasisTabMassUpdateOptionGroup
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		salesBasisTabMassUpdateOptionGroupConfig.setDefaultSelection(GtnFrameworkNSFConstants.getDisable());
		salesBasisTabMassUpdateOptionGroup.setGtnUIFrameworkOptionGroupConfig(salesBasisTabMassUpdateOptionGroupConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		customAction.setFieldValues(Arrays.asList(viewId + "massUpdateDdlb", viewId + "massUpdateNetSalesRuleNo",
				viewId + "massUpdatePopulateButton"));
		actionConfigList.add(customAction);
		salesBasisTabMassUpdateOptionGroup.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addSalesBasisTabMassUpdateDdlb(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "massUpdateDdlb";
		GtnUIFrameworkComponentConfig salesBasisTabMassUpdateDdlbLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		salesBasisTabMassUpdateDdlbLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesBasisTabMassUpdateDdlbLayout);

		GtnUIFrameworkComponentConfig salesBasisTabMassUpdateDdlb = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, salesBasisTabMassUpdateDdlbLayout.getComponentId(),
						GtnUIFrameworkComponentType.COMBOBOX);
		salesBasisTabMassUpdateDdlb.setAuthorizationIncluded(true);
		salesBasisTabMassUpdateDdlb.setComponentName("Field");
		componentList.add(salesBasisTabMassUpdateDdlb);

		GtnUIFrameworkComboBoxConfig salesBasisTabMassUpdateDdlbConfig = new GtnUIFrameworkComboBoxConfig();
		salesBasisTabMassUpdateDdlbConfig.setItemValues(Arrays.asList(GtnFrameworkNSFConstants.getNetSalesRuleNo()));
		salesBasisTabMassUpdateDdlb.setGtnComboboxConfig(salesBasisTabMassUpdateDdlbConfig);

	}

	private void addSalesBasisTabMassUpdatePopUpField(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String componentId = viewId + "massUpdateNetSalesRuleNo";
		GtnUIFrameworkComponentConfig salesBasisTabMassPopupLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		salesBasisTabMassPopupLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(salesBasisTabMassPopupLayout);

		GtnUIFrameworkComponentConfig salesBasisTabMassPopup = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, salesBasisTabMassPopupLayout.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		salesBasisTabMassPopup.setAuthorizationIncluded(true);
		salesBasisTabMassPopup.setComponentName("Value");
		salesBasisTabMassPopup.addComponentStyle(GtnFrameworkCssConstants.SEARCHICON);
		salesBasisTabMassPopup.setVisible(true);
		componentList.add(salesBasisTabMassPopup);

		List<GtnUIFrameWorkActionConfig> salesBasisTabMassPopupActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		salesBasisTabMassPopupActionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParamList = new ArrayList<>();
		popupActionParamList.add(GtnFrameworkNSFConstants.getNetSalesRulePopupView());
		popupActionParamList.add(GtnFrameworkCommonConstants.NET_SALES_RULE);
		popupActionParamList.add("70%");
		popupActionParamList.add("70%");

		popupActionConfig.setActionParameterList(popupActionParamList);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> defaultDataMap = new HashMap<>();

		defaultDataMap.put("ruleType", 1704);
		defaultDataMap.put(GtnFrameworkCommonConstants.RULE_NO, "");
		defaultDataMap.put(GtnFrameworkCommonConstants.RULE_NAME, "");
		defaultDataMap.put("ruleCategory", null);
		defaultDataMap.put("cDRPopUpsearchResultTable", "");
		defaultDataMap.put("cDRRulePopUpruleDetailsattachResultTable", null);

		setDefaultActionConfig.addActionParameter(defaultDataMap);
		salesBasisTabMassPopupActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig customSetCongigAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object customClassName = GtnUiFrameworkSetSelectionAction.class.getName();
		customSetCongigAction.setActionParameterList(Arrays.asList(customClassName));

		salesBasisTabMassPopupActionConfigList.add(customSetCongigAction);
		salesBasisTabMassPopup.setGtnUIFrameWorkActionConfigList(salesBasisTabMassPopupActionConfigList);
	}

	private void addSalesBasisTabMassUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String componentId = viewId + "massUpdatePopulateButton";
		GtnUIFrameworkComponentConfig salesBasisTabMassUpdatePopulateBtnlayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(salesBasisTabMassUpdatePopulateBtnlayout);

		GtnUIFrameworkComponentConfig salesBasisTabMassUpdatePopulateBtn = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true,
						salesBasisTabMassUpdatePopulateBtnlayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		salesBasisTabMassUpdatePopulateBtn.setComponentName("POPULATE");
		salesBasisTabMassUpdatePopulateBtn.setAuthorizationIncluded(true);
		salesBasisTabMassUpdatePopulateBtn.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(salesBasisTabMassUpdatePopulateBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig populateValidationAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object customClassName = GtnUiFrameworkNsfPopulateValidationAction.class.getName();
		populateValidationAction.setActionParameterList(Arrays.asList(customClassName, true));

		actionConfigList.add(populateValidationAction);

		GtnUIFrameWorkActionConfig populateAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object populateActionClass = GtnUiFrameworkNsfSBPopulateAction.class.getName();
		populateAction.addActionParameter(populateActionClass);
		populateAction.addActionParameter(viewId);

		actionConfigList.add(populateAction);
		salesBasisTabMassUpdatePopulateBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSelectedCustomersPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String viewId) {

		String componentId = viewId + "selectedCustomersResultTable";
		GtnUIFrameworkComponentConfig selectedCustomersTableLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		selectedCustomersTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(selectedCustomersTableLayout);

		GtnUIFrameworkComponentConfig selectedCustomersTableConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, selectedCustomersTableLayout.getComponentId(),
						GtnUIFrameworkComponentType.PAGEDTABLE);
		selectedCustomersTableConfig.setAuthorizationIncluded(true);

		selectedCustomersTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig selectedCustomersTableValidationConfig = new GtnUIFrameworkValidationConfig();
		selectedCustomersTableValidationConfig.setMinSize(1);
		selectedCustomersTableConfig.setGtnUIFrameworkValidationConfig(selectedCustomersTableValidationConfig);

		componentList.add(selectedCustomersTableConfig);

		GtnUIFrameworkPagedTableConfig selectedCustomersTable = new GtnUIFrameworkPagedTableConfig();
		selectedCustomersTable.setEditable(false);
		selectedCustomersTable.setFilterBar(true);
		selectedCustomersTable.setSelectable(true);
		selectedCustomersTable.setItemPerPage(10);
		selectedCustomersTable.setPageLength(10);
		selectedCustomersTable.setSinkItemPerPageWithPageLength(false);
		selectedCustomersTable.setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class });

		selectedCustomersTable.setTableVisibleHeader(GtnFrameworkNSFConstants.getSelectedCustomerVisibleHeaders());
		selectedCustomersTable.setTableColumnMappingId(GtnFrameworkNSFConstants.getSelectedCustomerVisibleColumns());
		selectedCustomersTable.setExtraColumn(new Object[] { GtnFrameworkNSFConstants.getSystemid() });
		selectedCustomersTable.setExtraColumnDataType(new Class[] { Integer.class });
		selectedCustomersTable.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		selectedCustomersTable.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		selectedCustomersTable.setModuleName("NSSalesBasisSelectedCustomers");
		selectedCustomersTable.setQueryName(GtnWsNsfCommonConstants.GTN_NSF_SELECTED_CUSTOMERS_SEARCH_QUERY_NAME);
		selectedCustomersTable.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		selectedCustomersTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		selectedCustomersTable.setEditable(true);

		selectedCustomersTableConfig.setGtnPagedTableConfig(selectedCustomersTable);
		selectedCustomersTable.setEditableColumnList(Arrays.asList("checkRecordId", "ruleNo", "ruleName"));
		selectedCustomersTable.setEditableField(createTableFieldFactoryComponents());
	}

	private void addResetRemoveExcelButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String resetRemoveExcelButtonLayout = viewId + "resetRemoveExcelButtonLayout";
		GtnUIFrameworkComponentConfig resetRemoveExcelButtonLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(resetRemoveExcelButtonLayout, true, parentId);
		componentList.add(resetRemoveExcelButtonLayoutConfig);
		addSalesBasisTabResetButton(componentList, resetRemoveExcelButtonLayoutConfig.getComponentId(), viewId);
		addSalesBasisTabRemoveButton(componentList, resetRemoveExcelButtonLayoutConfig.getComponentId(), viewId);
		addSalesBasisTabExcelButton(componentList, resetRemoveExcelButtonLayoutConfig.getComponentId(), viewId);

	}

	private void addSalesBasisTabResetButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String componentId = viewId + "selectedCustomerTableResetButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedCustomerTableResetButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		selectedCustomerTableResetButtonConfig.setComponentName("RESET");
		selectedCustomerTableResetButtonConfig.setAuthorizationIncluded(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object populateActionClass = GtnUiFrameworkNsfTabTableResetConfirmationAction.class.getName();
		resetActionConfig.addActionParameter(populateActionClass);
		resetActionConfig.addActionParameter(viewId);
		resetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Selected Customers group box?");
		resetActionConfig.addActionParameter(true);
		actionConfigList.add(resetActionConfig);
		componentList.add(selectedCustomerTableResetButtonConfig);
		selectedCustomerTableResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addSalesBasisTabRemoveButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String componentId = viewId + "selectedCustomerTableRemoveButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedCustomerTableRemoveButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		selectedCustomerTableRemoveButtonConfig.setAuthorizationIncluded(true);
		selectedCustomerTableRemoveButtonConfig.setComponentName("REMOVE");
		componentList.add(selectedCustomerTableRemoveButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object populateActionClass = GtnUiFrameworkNsfRemoveAction.class.getName();
		removeActionConfig.addActionParameter(populateActionClass);
		removeActionConfig.addActionParameter(viewId);
		removeActionConfig.addActionParameter(true);

		actionConfigList.add(removeActionConfig);
		selectedCustomerTableRemoveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSalesBasisTabExcelButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		String componentId = viewId + "selectedCustomerTableExcelButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig selectedCustomerTableExcelButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.EXCEL_BUTTON);
		selectedCustomerTableExcelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(selectedCustomerTableExcelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfigProvider
				.getExcelBtnconfig("Sales_Basis", true, viewId + "selectedCustomersResultTable", false);
		gtnUIFrameworkExcelButtonConfig
			.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		selectedCustomerTableExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		selectedCustomerTableExcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	public List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents() {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();

		GtnUIFrameworkComponentConfig checkBoxFieldConfig = new GtnUIFrameworkComponentConfig();
		checkBoxFieldConfig.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		getFieldFactoryActionConfig(checkBoxFieldConfig);
		editableFields.add(checkBoxFieldConfig);

		GtnUIFrameworkComponentConfig ruleNoBoxFieldConfig = getSalesBasisTabNetSalesPopupConfig();
		getFieldFactoryActionConfig(ruleNoBoxFieldConfig);
		editableFields.add(ruleNoBoxFieldConfig);

		GtnUIFrameworkComponentConfig ruleNameBoxFieldConfig = getSalesBasisTabNetSalesPopupConfig();
		editableFields.add(ruleNameBoxFieldConfig);

		return editableFields;
	}

	private GtnUIFrameworkComponentConfig getSalesBasisTabNetSalesPopupConfig() {
		GtnUIFrameworkComponentConfig salesBasisTabFieldConfig = new GtnUIFrameworkComponentConfig();
		salesBasisTabFieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		GtnUIFrameWorkActionConfig salesBasisTabPopupActionConfig = new GtnUIFrameWorkActionConfig();
		salesBasisTabPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParamList = new ArrayList<>();
		popupActionParamList.add(GtnFrameworkNSFConstants.getNetSalesRulePopupView());
		popupActionParamList.add("Net Sales Rule");
		popupActionParamList.add("70%");
		salesBasisTabPopupActionConfig.setActionParameterList(popupActionParamList);
		salesBasisTabFieldConfig.addComponentStyle("searchicon");
		GtnUIFrameWorkActionConfig salesBasisTabSelectAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		salesBasisTabSelectAction.addActionParameter(GtnUIFrameWorkNsfNetSalesRulePopupSelectAction.class.getName());
		salesBasisTabFieldConfig.addGtnUIFrameWorkActionConfig(salesBasisTabPopupActionConfig);
		salesBasisTabFieldConfig.addGtnUIFrameWorkActionConfig(salesBasisTabSelectAction);
		return salesBasisTabFieldConfig;
	}

	private void getFieldFactoryActionConfig(GtnUIFrameworkComponentConfig fieldConfig) {
		List<GtnUIFrameWorkActionConfig> salesBasisTabFieldFactoryActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfSalesBasisTabFieldFactoryAction.class.getName());
		salesBasisTabFieldFactoryActionConfigList.add(customAction);
		fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(salesBasisTabFieldFactoryActionConfigList);
	}

}
