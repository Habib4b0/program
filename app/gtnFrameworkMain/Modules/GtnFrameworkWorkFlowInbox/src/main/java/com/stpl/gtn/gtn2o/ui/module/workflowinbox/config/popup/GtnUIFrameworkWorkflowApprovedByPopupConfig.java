package com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkFlowPopupTableLoadAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnUIFrameworkWorkflowApprovedByPopupConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView(String tabName) {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY_NAME,
				tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP, false);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList, tabName);
		return view;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(tabName + GtnFrameworkWorkflowInboxClassConstants.ROOTLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addFieldPanel(componentList, tabName);
		addButtonLayout(componentList, tabName);
		addResultPanel(componentList, tabName);
		addActionButtonLayout(componentList, tabName);

	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHCRITERIA_PANEL, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ROOTLAYOUT);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SEARCH_CRITERIA);
		componentList.add(panelConfig);
		addFieldARMLayout(componentList, tabName);
	}

	private void addFieldARMLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkLayoutConfig layoutArm = new GtnUIFrameworkLayoutConfig();
		layoutArm.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayoutArm = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.ARMSEARCHCRITERIA_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHCRITERIA_PANEL,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayoutArm.setGtnLayoutConfig(layoutArm);
		componentList.add(gtnLayoutArm);
		addFieldARMComponent(componentList, tabName);
	}

	private void addFieldARMComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		addUserName(componentList, tabName);
		addUserFirstName(componentList, tabName);
		addUserLastName(componentList, tabName);
	}

	private void addUserName(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayoutUserName = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.USERNAME_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ARMSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayoutUserName);

		GtnUIFrameworkComponentConfig firstNameArmConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.USERNAME_ID, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.USERNAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		firstNameArmConfig.setAuthorizationIncluded(true);
		firstNameArmConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.USER_NAME);
		firstNameArmConfig.setVisible(false);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		firstNameArmConfig.setGtnUIFrameworkValidationConfig(valConfig);

		componentList.add(firstNameArmConfig);
	}

	private void addUserFirstName(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayoutUserFirstName = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ARMSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayoutUserFirstName);

		GtnUIFrameworkComponentConfig firstNameArmConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME_ID, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		firstNameArmConfig.setAuthorizationIncluded(true);
		firstNameArmConfig.setComponentName(tabName + GtnFrameworkWorkflowInboxClassConstants.FIRST_NAME);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		firstNameArmConfig.setGtnUIFrameworkValidationConfig(valConfig);

		componentList.add(firstNameArmConfig);
	}

	private void addUserLastName(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayoutUserLastName = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.LASTTNAME_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ARMSEARCHCRITERIA_LAYOUT);
		gtnLayoutUserLastName.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayoutUserLastName);

		GtnUIFrameworkComponentConfig userlastNameConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.LASTNAME_ID, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.LASTTNAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		userlastNameConfig.setAuthorizationIncluded(true);
		userlastNameConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.LAST_NAME);
		userlastNameConfig.setAddToParent(true);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		userlastNameConfig.setGtnUIFrameworkValidationConfig(valConfig);

		componentList.add(userlastNameConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.BUTTON_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList, tabName);
		addResetButtonComponent(componentList, tabName);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNSEARCH_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNSEARCH, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNSEARCH_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SEARCH);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList(tabName + GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME_ID,
						tabName + GtnFrameworkWorkflowInboxClassConstants.LASTNAME_ID,
						tabName + GtnFrameworkWorkflowInboxClassConstants.USERNAME_ID));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.INVALID_SEARCH);
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.THERE_ARE_NO_USERS);

		alertActionConfig.setActionParameterList(alertParamsList);

		Object or = GtnUIFrameworkValidationType.OR;
		validationActionConfig.setActionParameterList(Arrays.asList(or, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig
				.addActionParameter(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(tabName + GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME_ID,
						tabName + GtnFrameworkWorkflowInboxClassConstants.LASTNAME_ID,
						tabName + GtnFrameworkWorkflowInboxClassConstants.USERNAME_ID));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig
				.addActionParameter(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE);
		actionConfigList.add(notificationActionConfig);

		GtnUIFrameWorkActionConfig approvedBycustomCommonValidationAction = new GtnUIFrameWorkActionConfig();
		approvedBycustomCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		approvedBycustomCommonValidationAction
				.addActionParameter(GtnFrameworkWorkFlowPopupTableLoadAction.class.getName());
		approvedBycustomCommonValidationAction
				.addActionParameter(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE);
		actionConfigList.add(approvedBycustomCommonValidationAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESULTS);
		panelConfig.setComponentId(tabName + GtnFrameworkWorkflowInboxClassConstants.RESULT_PANEL);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(tabName + GtnFrameworkWorkflowInboxClassConstants.ROOTLAYOUT);
		componentList.add(panelConfig);
		addResultLayout(componentList, tabName);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.RESULT_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.RESULT_PANEL);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList, tabName);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {

		GtnUIFrameworkComponentConfig wfUserPopupResultConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.RESULT_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		wfUserPopupResultConfig.setAuthorizationIncluded(true);
		wfUserPopupResultConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESULTS);
		wfUserPopupResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		wfUserPopupResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		wfUserPopupResultConfig.setComponentStyle(tableStyleList);

		componentList.add(wfUserPopupResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { GtnFrameworkWorkflowInboxClassConstants.LAST_NAME,
				GtnFrameworkWorkflowInboxClassConstants.FIRST_NAME });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkWorkflowInboxClassConstants.LASTNAME,
				GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME });
		wfUserPopupResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.ACTIONBUTTON_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList, tabName);
		addCloseButtonComponent(componentList, tabName);
		addBottomResetButtonComponent(componentList, tabName);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNRESET_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNRESET, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNRESET_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESET_LOWERCASE);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add(GtnFrameworkWorkflowInboxClassConstants.GTN_CREATED_BY_VALIDATION_MSG_RESET_HEADER);
		paramsList.add(GtnFrameworkWorkflowInboxClassConstants.GTN_CREATED_BY_VALIDATION_MSG_RESET);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(tabName + GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(tabName + GtnFrameworkWorkflowInboxClassConstants.LASTNAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(tabName + GtnFrameworkWorkflowInboxClassConstants.USERNAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		paramsList.add(resetMap);

		resetActionConfig.setActionParameterList(paramsList);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.SELECTBUTTON_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ACTIONBUTTON_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.SELECTBUTTON, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.SELECTBUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SELECT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE);
		actionParameterList.add(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP);
		actionParameterList.add(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.FULLNAME));
		actionParameterList.add(Arrays.asList(tabName));
		selectAction.setActionParameterList(actionParameterList);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.CLOSEBUTTON_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ACTIONBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.CLOSEBUTTON, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.CLOSEBUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CLOSE);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addBottomResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNBOTTOMRESET_LAYOUT, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.ACTIONBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNBOTTOMRESET, true,
				tabName + GtnFrameworkWorkflowInboxClassConstants.GTNBOTTOMRESET_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESET_LOWERCASE);
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> paramsList = new ArrayList<>();
		paramsList.add(GtnFrameworkWorkflowInboxClassConstants.GTN_CREATED_BY_VALIDATION_MSG_RESET_HEADER);
		paramsList.add(GtnFrameworkWorkflowInboxClassConstants.GTN_CREATED_BY_VALIDATION_MSG_RESET);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		resetSelectMap.put(tabName + GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE, null);
		paramsList.add(resetSelectMap);
		resetTableConfig.setActionParameterList(paramsList);
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		actionConfigList.add(resetTableConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
