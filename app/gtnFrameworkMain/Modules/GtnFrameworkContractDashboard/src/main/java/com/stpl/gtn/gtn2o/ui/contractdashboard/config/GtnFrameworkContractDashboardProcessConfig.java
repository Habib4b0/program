package com.stpl.gtn.gtn2o.ui.contractdashboard.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkWorkflowInboxRefreshAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkContractDashBoardSubmitAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnFrameworkProcessAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkBackButtonAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkCloseButtonAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process.GtnUIFrameworkContractWorkflowUpdateAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardAliasTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardCompaniesTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardCompanyAdditionTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardInfomationTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardItemAdditionTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardItemsTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardPrcingTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab.GtnFrameworkContractDashboardRebateTabConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetLoadType;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardProcessConfig {

	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getProcessView() {
		GtnUIFrameworkViewConfig view = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_PROCESS_VIEW,
				GtnFrameworkContractDashboardContants.CD_PROCESS_VIEW, false);
		List<List<String>> pageParameterList = GtnUIFrameworkGlobalUI.getPageParameterList();
		if (pageParameterList != null && !pageParameterList.isEmpty() && !pageParameterList.get(0).isEmpty()
				&& !pageParameterList.get(0).contains("debug")) {
			view.setDefaultView(true);
			GtnUIFrameWorkActionConfig workflowProcessActionConfig = commonConfig
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			workflowProcessActionConfig.addActionParameter(GtnFrameworkProcessAction.class.getName());
			workflowProcessActionConfig.addActionParameter(pageParameterList);
			workflowProcessActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_PROCESS_VIEW);
			workflowProcessActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.TAB_SHEET_ID);
			view.addViewAction(workflowProcessActionConfig);
		}

		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addContractDashboardContractInfoPanel(componentList, commonConfig);
		addContractDashboardProcessPanel(componentList, namspacePrefix);

	}

	private void addContractDashboardProcessPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "ProcessPanel", false, null);
		componentList.add(panel);
		addContractDashboardProcessLayout(componentList, namspacePrefix, panel.getComponentId());
	}

	private void addContractDashboardContractInfoPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig) {
		GtnUIFrameworkComponentConfig contractDashboardInfoPanel = commonConfig
				.getPanelConfig("contractInformationPanel", false, null);
		contractDashboardInfoPanel.setComponentName("contract Information");
		componentList.add(contractDashboardInfoPanel);
		addContractInfoFieldLayout(componentList, commonConfig, contractDashboardInfoPanel.getComponentId());
	}

	private void addContractInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig, String parentId) {

		GtnUIFrameworkComponentConfig contractInfoLayoutConfig = commonConfig.getGtnCssLayoutConfig(
				GtnFrameworkContractDashboardContants.CONTRACTINFOFIELDLAYOUT, true, parentId,
				GtnUIFrameworkLayoutType.COL4_LAYOUT);
		componentList.add(contractInfoLayoutConfig);
		addContractInfoFieldComponent(componentList, commonConfig);
	}

	private void addContractInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig) {
		addContractInfoContractId(componentList, commonConfig);
		addContractInfoContractNo(componentList, commonConfig);
		addContractInfoContractName(componentList, commonConfig);
		addContractInfoContractType(componentList, commonConfig);
	}

	private void addContractInfoContractId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig) {

		GtnUIFrameworkComponentConfig contractIdLayout = commonConfig.getHorizontalLayoutConfig("contractIdlayout",
				true, GtnFrameworkContractDashboardContants.CONTRACTINFOFIELDLAYOUT);
		componentList.add(contractIdLayout);
		GtnUIFrameworkComponentConfig contractId = commonConfig.getUIFrameworkComponentConfig("contractId", true,
				contractIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractId.setComponentName("Contract ID");

		GtnUIFrameworkTextBoxConfig contractIdConfig = new GtnUIFrameworkTextBoxConfig();
		contractIdConfig.setEnable(false);
		contractId.setGtnTextBoxConfig(contractIdConfig);
		componentList.add(contractId);
	}

	private void addContractInfoContractNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig) {

		GtnUIFrameworkComponentConfig contractNoLayout = commonConfig.getHorizontalLayoutConfig("contractNolayout",
				true, GtnFrameworkContractDashboardContants.CONTRACTINFOFIELDLAYOUT);
		componentList.add(contractNoLayout);

		GtnUIFrameworkComponentConfig contractNo = commonConfig.getUIFrameworkComponentConfig("contractNo", true,
				contractNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractNo.setComponentName("Contract No");

		GtnUIFrameworkTextBoxConfig contractNoConfig = new GtnUIFrameworkTextBoxConfig();
		contractNoConfig.setEnable(false);
		contractNo.setGtnTextBoxConfig(contractNoConfig);

		componentList.add(contractNo);
	}

	private void addContractInfoContractName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig) {

		GtnUIFrameworkComponentConfig contractNameLayout = commonConfig.getHorizontalLayoutConfig("contractNamelayout",
				true, GtnFrameworkContractDashboardContants.CONTRACTINFOFIELDLAYOUT);
		componentList.add(contractNameLayout);

		GtnUIFrameworkComponentConfig contractName = commonConfig.getUIFrameworkComponentConfig("contractName", true,
				contractNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractName.setComponentName("Contract Name");

		GtnUIFrameworkTextBoxConfig contractNameConfig = new GtnUIFrameworkTextBoxConfig();
		contractNameConfig.setEnable(false);
		contractName.setGtnTextBoxConfig(contractNameConfig);

		componentList.add(contractName);
	}

	private void addContractInfoContractType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider commonConfig) {

		GtnUIFrameworkComponentConfig contractTypeLayout = commonConfig.getHorizontalLayoutConfig("contractTypelayout",
				true, GtnFrameworkContractDashboardContants.CONTRACTINFOFIELDLAYOUT);
		componentList.add(contractTypeLayout);

		GtnUIFrameworkComponentConfig contractType = commonConfig.getUIFrameworkComponentConfig("contractType", true,
				contractTypeLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractType.setComponentName("Contract Type");
		GtnUIFrameworkTextBoxConfig contractTypeConfig = new GtnUIFrameworkTextBoxConfig();
		contractTypeConfig.setEnable(false);
		contractType.setGtnTextBoxConfig(contractTypeConfig);
		componentList.add(contractType);
	}

	private void addContractDashboardProcessLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig processMainLayout = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "ProcessLayout", true, parent);
		processMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		processMainLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(processMainLayout);
		addErrorDisplay(componentList, processMainLayout.getComponentId());
		addProcessTabSheetComponent(componentList, namspacePrefix, processMainLayout.getComponentId());
		addSubmitButtonLayout(componentList, namspacePrefix, processMainLayout.getComponentId());
	}

	private void addErrorDisplay(List<GtnUIFrameworkComponentConfig> componentList, String parent) {
		String componentId = "errorDisplay";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.LABEL);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.ERROR);
		componentConfig.setVisible(false);
		componentList.add(componentConfig);
	}

	private void addProcessTabSheetComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		String componentId = GtnFrameworkContractDashboardContants.TAB_SHEET_ID;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayoutConfig);
		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TABSHEET);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		addProcessTab(namspacePrefix, tabConfigList);

		componentConfig.setGtnTabSheetConfigList(tabConfigList);

		componentList.add(componentConfig);
	}

	private void addProcessTab(String namspacePrefix, List<GtnUIFrameworkTabConfig> tabConfigList) {
		tabConfigList.add(new GtnFrameworkContractDashboardInfomationTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardAliasTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardCompanyAdditionTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardCompaniesTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardItemAdditionTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardItemsTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardPrcingTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(new GtnFrameworkContractDashboardRebateTabConfig().getTabConfig(namspacePrefix));
		tabConfigList.add(getNotesTab());
	}

	private GtnUIFrameworkTabConfig getNotesTab() {
		GtnUIFrameworkTabConfig tabConfig = new GtnUIFrameworkTabConfig();
		tabConfig.setComponentId("notesTab");
		tabConfig.setTabCaption("Notes");
		tabConfig.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		tabConfig.setTabLayoutComponentConfigList(componentList);
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(tabConfig.getComponentId());
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.NOTES_TAB);

		GtnUIFrameworkNotesTabConfig config = new GtnUIFrameworkNotesTabConfig();
		config.setValidFormats(
				Arrays.asList("docx", "doc", "ppt", "xls", "xlsx", "pdf", "txt", "csv", "jpg", "jpeg", "pptx"));

		layoutConfig.setNotesTabConfig(config);

		componentList.add(layoutConfig);
		return tabConfig;
	}

	private void addSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT, true, parent);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);  
		componentList.add(gtnLayout);
		addBackButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
		addSubmitButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
		addWithdrowButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
		addApproveButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
		addRejectButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
		addCancelButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
		addCloseButtonComponent(componentList, gtnLayout.getComponentId(), namspacePrefix);
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnBack01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_BACK_BTN, true, namspacePrefix + "gtnBack01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Back");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig confirmActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Any unsaved information will not be saved. Do you want to proceed?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig backActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		backActionConfig.addActionParameter(GtnUIFrameworkBackButtonAction.class.getName());
		backActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_MAIN_VIEW);
		successActionConfigList.add(backActionConfig);
		GtnUIFrameWorkActionConfig closeActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		closeActionConfig.addActionParameter(GtnUIFrameworkCloseButtonAction.class.getName());
		successActionConfigList.add(closeActionConfig);
		GtnUIFrameWorkActionConfig navigateActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigateActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		successActionConfigList.add(navigateActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);

		buttonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnClose01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_CLOSE_BTN, true, namspacePrefix + "gtnClose01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Close");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig closeActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		closeActionConfig.addActionParameter(GtnUIFrameworkCloseButtonAction.class.getName());
		closeActionConfig.addActionParameter("close");

		buttonConfig.addGtnUIFrameWorkActionConfig(closeActionConfig);

	}

	private void addSubmitButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnSubmit01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_SUBMIT_BTN, true, namspacePrefix + "gtnSubmit01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Submit");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig submitActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		submitActionConfig.addActionParameter(GtnFrameworkContractDashBoardSubmitAction.class.getName());
		submitActionConfig.addActionParameter(GtnWsContractDashboardContants.AUTHENTICATION_ERROR);
		submitActionConfig.addActionParameter(GtnWsContractDashboardContants.AUTHENTICATION_ERROR_SUBMIT_MESSAGE);
		submitActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		submitActionConfig.addActionParameter(GtnWsContractDashboardContants.SUBMIT_CONFIRM_MESSAGE);
		submitActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		submitActionConfig.addActionParameter(GtnWsContractDashboardContants.SUBMIT_FAIL_MESSAGE);
		GtnUIFrameWorkActionConfig submitWorkflowInboxRefreshAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		submitWorkflowInboxRefreshAction.addActionParameter(GtnFrameworkWorkflowInboxRefreshAction.class.getName());
		buttonConfig.addGtnUIFrameWorkActionConfig(submitActionConfig);
		buttonConfig.addGtnUIFrameWorkActionConfig(submitWorkflowInboxRefreshAction);
	}

	private void addWithdrowButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnWithdraw01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_WITHDRAW_BTN, true, namspacePrefix + "gtnWithdraw01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Withdrawal");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig workflowActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		workflowActionConfig.addActionParameter(GtnUIFrameworkContractWorkflowUpdateAction.class.getName());
		workflowActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.WITHDRAWN);
		workflowActionConfig.addActionParameter(GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
				+ GtnWsContractDashboardContants.WITHDRAW_CONTRACT);
		workflowActionConfig.addActionParameter("Workflow withdrawn ");
		workflowActionConfig.addActionParameter("Workflow Id [WORKFLOW_ID] withdrawn successfully");
		workflowActionConfig.addActionParameter("Contract Withdrawn");
		workflowActionConfig.addActionParameter("The Contract not withdrawn properly");
		GtnUIFrameWorkActionConfig withDrawWorkflowInboxRefreshAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		withDrawWorkflowInboxRefreshAction.addActionParameter(GtnFrameworkWorkflowInboxRefreshAction.class.getName());
		GtnUIFrameWorkActionConfig confirmActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter("Confirm Withdraw");
		confirmActionConfig.addActionParameter("Are you sure you want to withdraw the Contract ?");
		confirmActionConfig.addActionParameter(Arrays.asList(workflowActionConfig, withDrawWorkflowInboxRefreshAction));

		buttonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);
	}

	private void addApproveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnApprove01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_APPROVE_BTN, true, namspacePrefix + "gtnApprove01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Approve");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig workflowActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		workflowActionConfig.addActionParameter(GtnUIFrameworkContractWorkflowUpdateAction.class.getName());
		workflowActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.APPROVED);
		workflowActionConfig.addActionParameter(GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
				+ GtnWsContractDashboardContants.APPROVE_CONTRACT);
		workflowActionConfig.addActionParameter("Approved Information");
		workflowActionConfig.addActionParameter("Workflow Id [WORKFLOW_ID] approved successfully");
		workflowActionConfig.addActionParameter("Contract Approval");
		workflowActionConfig.addActionParameter("The Contract not approved properly");
		GtnUIFrameWorkActionConfig approveWorkflowInboxRefreshAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		approveWorkflowInboxRefreshAction.addActionParameter(GtnFrameworkWorkflowInboxRefreshAction.class.getName());
		GtnUIFrameWorkActionConfig confirmActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter("Approve Confirmation");
		confirmActionConfig.addActionParameter("Are you sure you want to approve the Contract?");
		confirmActionConfig.addActionParameter(Arrays.asList(workflowActionConfig, approveWorkflowInboxRefreshAction));
		buttonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);
	}

	private void addRejectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnReject01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_REJECT_BTN, true, namspacePrefix + "gtnReject01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Reject");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig workflowActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		workflowActionConfig.addActionParameter(GtnUIFrameworkContractWorkflowUpdateAction.class.getName());
		workflowActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.REJECTED);
		workflowActionConfig.addActionParameter(GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
				+ GtnWsContractDashboardContants.REJECT_CONTRACT);
		workflowActionConfig.addActionParameter("Rejected Information ");
		workflowActionConfig.addActionParameter("Workflow Id [WORKFLOW_ID] rejected successfully");
		workflowActionConfig.addActionParameter("Contract Rejection");
		workflowActionConfig.addActionParameter("The Contract not rejected properly");
		GtnUIFrameWorkActionConfig rejectWorkflowInboxRefreshAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rejectWorkflowInboxRefreshAction.addActionParameter(GtnFrameworkWorkflowInboxRefreshAction.class.getName());
		GtnUIFrameWorkActionConfig confirmActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter("Confirm Reject");
		confirmActionConfig.addActionParameter("Are you sure you want to reject the Contract ?");
		confirmActionConfig.addActionParameter(Arrays.asList(workflowActionConfig, rejectWorkflowInboxRefreshAction));
		buttonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);
	}

	private void addCancelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parent, String namspacePrefix) {
                GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(namspacePrefix + "gtnCancel01Layout", true,
				GtnFrameworkContractDashboardContants.CONTRACT_DASHBOARD_SUBMIT_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig buttonConfig = commonConfig.getUIFrameworkComponentConfig(
				GtnWsContractDashboardContants.CONTRACT_CANCEL_BTN, true, namspacePrefix + "gtnCancel01Layout", GtnUIFrameworkComponentType.BUTTON);
		buttonConfig.setComponentName("Cancel");
		buttonConfig.setAuthorizationIncluded(true);
		componentList.add(buttonConfig);

		GtnUIFrameWorkActionConfig workflowActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		workflowActionConfig.addActionParameter(GtnUIFrameworkContractWorkflowUpdateAction.class.getName());
		workflowActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CANCELED);
		workflowActionConfig.addActionParameter(GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
				+ GtnWsContractDashboardContants.CANCEL_CONTRACT);
		workflowActionConfig.addActionParameter("Cancel Information");
		workflowActionConfig.addActionParameter("Workflow Id [WORKFLOW_ID] cancelled successfully");
		workflowActionConfig.addActionParameter("Contract Cancel");
		workflowActionConfig.addActionParameter("The Contract not cancelled properly");
		GtnUIFrameWorkActionConfig confirmActionConfig = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter("Confirm Cancel");
		confirmActionConfig.addActionParameter("Are you sure you want to cancel the Contract ?");
		confirmActionConfig.addActionParameter(Arrays.asList(workflowActionConfig));
		buttonConfig.addGtnUIFrameWorkActionConfig(confirmActionConfig);
	}

}
