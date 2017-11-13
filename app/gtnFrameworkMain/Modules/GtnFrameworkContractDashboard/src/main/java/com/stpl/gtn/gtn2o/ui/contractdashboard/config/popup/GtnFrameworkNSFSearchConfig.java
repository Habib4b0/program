package com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkNSFSearchConfig {

	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig nsfSearchView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW,
				GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW, false);
		addComponentList(nsfSearchView, nsfSearchView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		nsfSearchView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		nsfSearchView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return nsfSearchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> nsfSearchComponentList = new ArrayList<>();
		view.setGtnComponentList(nsfSearchComponentList);
		addPopupMainPanel(nsfSearchComponentList, namspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		nsfSearchComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		nsfSearchComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(nsfSearchComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(nsfSearchComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResultPanel(nsfSearchComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(nsfSearchComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		nsfSearchComponentList.add(panelConfig);
		addFieldLayout(nsfSearchComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		nsfSearchComponentList.add(layoutConfig);
		addSelectButtonComponent(nsfSearchComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(nsfSearchComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setComponentName("SELECT");
		selectBtnConfig.setAuthorizationIncluded(true);
		nsfSearchComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig nsfSearchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		nsfSearchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		nsfSearchValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		nsfSearchValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig nsfSearchSelectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nsfSearchSelectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		nsfSearchSelectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		nsfSearchSelectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW);
		nsfSearchSelectAction.addActionParameter(8);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW);

		nsfSearchValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		nsfSearchValidationActionConfig.addActionParameter(Arrays.asList(nsfSearchSelectAction, closeAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(nsfSearchValidationActionConfig);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		nsfSearchComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_FORMULA_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		nsfSearchComponentList.add(layoutConfig);
		addSearchButtonComponent(nsfSearchComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
		addResetButtonComponent(nsfSearchComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		nsfSearchComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig nsfSearchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		nsfSearchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		nsfSearchValidationActionConfig.setFieldValues(componentIdList);
		nsfSearchValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig nsfFailureActionConfig = new GtnUIFrameWorkActionConfig();
		nsfFailureActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		nsfFailureActionConfig.addActionParameter("No Search Criteria ");
		nsfFailureActionConfig.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig nsfTableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		nsfTableLoadActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		nsfTableLoadActionConfig
				.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		nsfTableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		nsfSearchValidationActionConfig.addActionParameter(Arrays.asList(nsfFailureActionConfig));
		nsfSearchValidationActionConfig
				.addActionParameter(Arrays.asList(nsfTableLoadActionConfig, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(nsfSearchValidationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		nsfSearchComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig nsfResetActionConfig = new GtnUIFrameWorkActionConfig();
		nsfResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		nsfResetActionConfig.addActionParameter("Confirmation");
		nsfResetActionConfig
				.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		nsfResetActionConfig.addActionParameter(componentIdList);
		nsfResetActionConfig.addActionParameter(Arrays.asList(null, "", "", ""));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(nsfResetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		nsfSearchComponentList.add(gtnLayout);
		addFieldComponent(nsfSearchComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		nsfSearchComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig nsfSearchTableConfig = new GtnUIFrameworkPagedTableConfig();
		nsfSearchTableConfig.setEditable(false);
		nsfSearchTableConfig.setFilterBar(true);
		nsfSearchTableConfig.setSelectable(true);
		nsfSearchTableConfig.setMultiSelect(false);
		nsfSearchTableConfig.setPageLength(10);
		nsfSearchTableConfig.setItemPerPage(10);
		nsfSearchTableConfig.setSinkItemPerPageWithPageLength(false);
		nsfSearchTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getNsfLookupColumnType());
		nsfSearchTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getNsfLookupHeader());
		nsfSearchTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getNsfLookupColumn());
		nsfSearchTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_NSF_LOOKUP_TABLE_DATA);
		nsfSearchTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_NSF_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(nsfSearchTableConfig);
		nsfSearchComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		addFormulaType(nsfSearchComponentList, namspacePrefix, componentIdList, parent);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addFormulaId(nsfSearchComponentList, namspacePrefix, componentIdList, parent, validationConfig);
		addFormulaNo(nsfSearchComponentList, namspacePrefix, componentIdList, parent, validationConfig);
		addFormulaName(nsfSearchComponentList, namspacePrefix, componentIdList, parent, validationConfig);
	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		String componentId = namspacePrefix + "FormulaType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsfSearchComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Net Sales Formula Type");
		nsfSearchComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.NS_FORMULA_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();

		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsfSearchComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Net Sales Formula ID");
		nsfSearchComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsfSearchComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Net Sales Formula No");
		nsfSearchComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> nsfSearchComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsfSearchComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Net Sales Formula Name");
		nsfSearchComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
