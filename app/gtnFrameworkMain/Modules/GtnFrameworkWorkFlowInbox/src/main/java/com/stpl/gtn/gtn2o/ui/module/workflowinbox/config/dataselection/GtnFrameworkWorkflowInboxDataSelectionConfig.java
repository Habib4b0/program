package com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.dataselection;

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
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworPopulatefromTableAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkAddJSListenerAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureApprovedByAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureBusinesLookUpAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureBusinessProcessDdlbAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureHistoryButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkConfigureOpenButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkSelectBtnRecordClickTableAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkUpdateTableJSListenerAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkflowResultTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.crud.GtnFrameworkConfigureSaveProfilelookupAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxTableConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.vaadin.server.Page;

public class GtnFrameworkWorkflowInboxDataSelectionConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {

		GtnUIFrameworkViewConfig view = configProvider
				.getViewConfig(GtnFrameworkWorkflowInboxClassConstants.SEARCH_VIEW, "V001", true);
		addComponentList(view);

		GtnUIFrameWorkActionConfig updateTablejSListenerAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		updateTablejSListenerAction.addActionParameter(GtnFrameworkUpdateTableJSListenerAction.class.getName());
		updateTablejSListenerAction
				.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		view.addViewAction(updateTablejSListenerAction);

		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		configureRootLayouts(componentList);
	}

	private void configureRootLayouts(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig workFlowRootComponentConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWROOTLAYOUT, false, null);
		workFlowRootComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(workFlowRootComponentConfig);

		configureMainLayouts(componentList, workFlowRootComponentConfig.getComponentId());
		configureForecastingandARPDetailSearchLayouts(componentList, workFlowRootComponentConfig.getComponentId());
		configureReturnsDetailSearchLayouts(componentList, workFlowRootComponentConfig.getComponentId());
		configureArmDetailSearchLayouts(componentList, workFlowRootComponentConfig.getComponentId());
		addControlButtonLayout(componentList, workFlowRootComponentConfig.getComponentId());
		addResultsLayout(componentList, workFlowRootComponentConfig.getComponentId());
		addBottomControlButtonLayout(componentList, workFlowRootComponentConfig.getComponentId());

	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig workFlowMainComponentConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_MAINLAYOUT, true, parentComponentId);
		workFlowMainComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(workFlowMainComponentConfig);

		GtnUIFrameworkComponentConfig summarySearchPanelComponentConfig = configProvider.getPanelConfig(
				GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL, true,
				workFlowMainComponentConfig.getComponentId());
		summarySearchPanelComponentConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH);
		summarySearchPanelComponentConfig.setAuthorizationIncluded(true);
		summarySearchPanelComponentConfig.setSpacing(true);
		componentList.add(summarySearchPanelComponentConfig);

		GtnUIFrameworkComponentConfig workFlowMainInnerComponentConfig = addworkflowMainInnerComponentConfig(
				componentList, summarySearchPanelComponentConfig);

		GtnUIFrameworkComponentConfig summarySearchInnerComponentConfig = addsummarySearchInnerLayoutConfig(
				componentList, workFlowMainInnerComponentConfig);

		GtnUIFrameworkComponentConfig summarySearchARMInnerComponentConfig = addsummarySearchARMInnerLayoutConfig(
				componentList, workFlowMainInnerComponentConfig);

		GtnUIFrameworkComponentConfig summarySearchcompanybusinessInnerComponentConfig = addsummarySearchcompanybusinessInnerLayoutConfig(
				componentList, workFlowMainInnerComponentConfig);

		addmainlayoutComponents(componentList, summarySearchInnerComponentConfig, summarySearchARMInnerComponentConfig,
				summarySearchcompanybusinessInnerComponentConfig);

	}

	private GtnUIFrameworkComponentConfig addworkflowMainInnerComponentConfig(
			List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig summarySearchPanelComponentConfig) {
		GtnUIFrameworkComponentConfig workFlowMainInnerComponentConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_MAIN_INNERLAYOUT, true,
				summarySearchPanelComponentConfig.getComponentId());
		componentList.add(workFlowMainInnerComponentConfig);
		return workFlowMainInnerComponentConfig;
	}

	private GtnUIFrameworkComponentConfig addsummarySearchInnerLayoutConfig(
			List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig workFlowMainInnerComponentConfig) {
		GtnUIFrameworkLayoutConfig summarySearchInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		summarySearchInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig summarySearchInnerComponentConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT, true,
				workFlowMainInnerComponentConfig.getComponentId(), GtnUIFrameworkComponentType.LAYOUT);
		summarySearchInnerComponentConfig.setSpacing(true);
		summarySearchInnerComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		summarySearchInnerComponentConfig.setGtnLayoutConfig(summarySearchInnerLayoutConfig);
		componentList.add(summarySearchInnerComponentConfig);
		return summarySearchInnerComponentConfig;
	}

	private GtnUIFrameworkComponentConfig addsummarySearchARMInnerLayoutConfig(
			List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig workFlowMainInnerComponentConfig) {
		GtnUIFrameworkLayoutConfig summarySearchARMInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		summarySearchARMInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig summarySearchARMInnerComponentConfig = configProvider
				.getUIFrameworkComponentConfig(
						GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT, true,
						workFlowMainInnerComponentConfig.getComponentId(), GtnUIFrameworkComponentType.LAYOUT);
		summarySearchARMInnerComponentConfig.setSpacing(true);
		summarySearchARMInnerComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		summarySearchARMInnerComponentConfig.setGtnLayoutConfig(summarySearchARMInnerLayoutConfig);
		componentList.add(summarySearchARMInnerComponentConfig);
		return summarySearchARMInnerComponentConfig;
	}

	private GtnUIFrameworkComponentConfig addsummarySearchcompanybusinessInnerLayoutConfig(
			List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig workFlowMainInnerComponentConfig) {
		GtnUIFrameworkLayoutConfig summarySearchcompanybusinessInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		summarySearchcompanybusinessInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig summarySearchcompanybusinessInnerComponentConfig = new GtnUIFrameworkComponentConfig();
		summarySearchcompanybusinessInnerComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		summarySearchcompanybusinessInnerComponentConfig.setComponentId(
				GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT);
		summarySearchcompanybusinessInnerComponentConfig.setVisible(false);
		summarySearchcompanybusinessInnerComponentConfig.setAddToParent(true);
		summarySearchcompanybusinessInnerComponentConfig.setSpacing(true);
		summarySearchcompanybusinessInnerComponentConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		summarySearchcompanybusinessInnerComponentConfig
				.setParentComponentId(workFlowMainInnerComponentConfig.getComponentId());
		summarySearchcompanybusinessInnerComponentConfig
				.setGtnLayoutConfig(summarySearchcompanybusinessInnerLayoutConfig);
		componentList.add(summarySearchcompanybusinessInnerComponentConfig);
		return summarySearchcompanybusinessInnerComponentConfig;
	}

	private void addmainlayoutComponents(List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig summarySearchInnerComponentConfig,
			GtnUIFrameworkComponentConfig summarySearchARMInnerComponentConfig,
			GtnUIFrameworkComponentConfig summarySearchcompanybusinessInnerComponentConfig) {
		addbusinessProcess(componentList, summarySearchInnerComponentConfig.getComponentId());
		addprivateView(componentList, summarySearchInnerComponentConfig.getComponentId());
		addpublicView(componentList, summarySearchInnerComponentConfig.getComponentId());
		addworkflowID(componentList, summarySearchInnerComponentConfig.getComponentId());
		addworkflowName(componentList, summarySearchInnerComponentConfig.getComponentId());
		addworkflowDesc(componentList, summarySearchInnerComponentConfig.getComponentId());
		addCompany(componentList, summarySearchcompanybusinessInnerComponentConfig.getComponentId());
		addbusinessUnit(componentList, summarySearchcompanybusinessInnerComponentConfig.getComponentId());
		addcreatedFrom(componentList, summarySearchARMInnerComponentConfig.getComponentId());
		addcreatedTo(componentList, summarySearchARMInnerComponentConfig.getComponentId());
		addcreatedBy(componentList, summarySearchARMInnerComponentConfig.getComponentId());
		addapprovedFrom(componentList, summarySearchARMInnerComponentConfig.getComponentId());
		addapprovedTo(componentList, summarySearchARMInnerComponentConfig.getComponentId());
		addapprovedBy(componentList, summarySearchARMInnerComponentConfig.getComponentId());
		addworkflowSid(componentList, summarySearchARMInnerComponentConfig.getComponentId());
	}

	private void addbusinessProcess(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig businessProcessComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS_LAYOUT, true, parentComponentId);
		businessProcessComponentConfig.setSpacing(true);
		componentList.add(businessProcessComponentConfig);

		GtnUIFrameworkComponentConfig businessProcessComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS, true,
				businessProcessComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		businessProcessComponent.setAuthorizationIncluded(true);
		businessProcessComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESSPROCESS_NAME);
		businessProcessComponent.setAddToParent(true);
		businessProcessComponent.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		businessProcessComponent.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameworkComboBoxConfig businessProcessComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		businessProcessComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessProcessComboBoxConfig
				.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_BUSINESS_PROCESS);
		businessProcessComponent.setGtnComboboxConfig(businessProcessComboBoxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customCommonValidationAction = new GtnUIFrameWorkActionConfig();
		customCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction.addActionParameter(GtnFrameworkConfigureBusinessProcessDdlbAction.class.getName());
		actionConfigList.add(customCommonValidationAction);
		businessProcessComponent.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(businessProcessComponent);

	}

	private void addprivateView(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig privateViewComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.PRIVATEVIEW_LAYOUT, true, parentComponentId);
		privateViewComponentConfig.setSpacing(true);
		componentList.add(privateViewComponentConfig);

		GtnUIFrameworkComponentConfig privateViewComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.PRIVATEVIEW, true, privateViewComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		privateViewComponent.setAuthorizationIncluded(true);
		privateViewComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.PRIVATEVIEW_NAME);

		GtnUIFrameworkTextBoxConfig privatetextboxConfig = new GtnUIFrameworkTextBoxConfig();
		privatetextboxConfig.setEnable(true);
		privatetextboxConfig.setRequired(true);
		privatetextboxConfig.setReadOnly(true);
		privateViewComponent.setGtnTextBoxConfig(privatetextboxConfig);

		List<GtnUIFrameWorkActionConfig> privateactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig privatepopupActionConfig = new GtnUIFrameWorkActionConfig();
		privatepopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		privatepopupActionConfig
				.addActionParameter("private" + GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCH_POPUP);
		privatepopupActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCHPOPUP);
		privatepopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		privatepopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		privateactionConfigList.add(privatepopupActionConfig);
		GtnUIFrameWorkActionConfig privatecustomCommonValidationAction = new GtnUIFrameWorkActionConfig();
		privatecustomCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		privatecustomCommonValidationAction
				.addActionParameter(GtnFrameworkConfigureBusinesLookUpAction.class.getName());
		privatecustomCommonValidationAction.addActionParameter("private");
		privateactionConfigList.add(privatecustomCommonValidationAction);
		privateViewComponent.setGtnUIFrameWorkActionConfigList(privateactionConfigList);
		componentList.add(privateViewComponent);
	}

	private void addpublicView(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig publicViewComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW_LAYOUT, true, parentComponentId);
		publicViewComponentConfig.setSpacing(true);
		componentList.add(publicViewComponentConfig);

		GtnUIFrameworkComponentConfig publicViewComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW, true, publicViewComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		publicViewComponent.setAuthorizationIncluded(true);
		publicViewComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW_NAME);

		GtnUIFrameworkTextBoxConfig publictextboxConfig = new GtnUIFrameworkTextBoxConfig();
		publictextboxConfig.setEnable(true);
		publictextboxConfig.setRequired(true);
		publictextboxConfig.setReadOnly(true);
		publicViewComponent.setGtnTextBoxConfig(publictextboxConfig);

		List<GtnUIFrameWorkActionConfig> publicactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig publicpopupActionConfig = new GtnUIFrameWorkActionConfig();
		publicpopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		publicpopupActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW_SEARCHPOPUP);
		publicpopupActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCHPOPUP);
		publicpopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		publicactionConfigList.add(publicpopupActionConfig);
		GtnUIFrameWorkActionConfig publiccustomCommonValidationAction = new GtnUIFrameWorkActionConfig();
		publiccustomCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		publiccustomCommonValidationAction.addActionParameter(GtnFrameworkConfigureBusinesLookUpAction.class.getName());
		publiccustomCommonValidationAction.addActionParameter("public");
		publicactionConfigList.add(publiccustomCommonValidationAction);
		publicViewComponent.setGtnUIFrameWorkActionConfigList(publicactionConfigList);
		componentList.add(publicViewComponent);
	}

	private void addworkflowID(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig workflowIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID_LAYOUT, true, parentComponentId);
		workflowIdComponentConfig.setSpacing(true);
		componentList.add(workflowIdComponentConfig);

		GtnUIFrameworkComponentConfig workflowIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID, true, workflowIdComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		workflowIdComponent.setAuthorizationIncluded(true);
		workflowIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID_NAME);

		componentList.add(workflowIdComponent);

	}

	private void addworkflowName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig workflowNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAME_LAYOUT, true, parentComponentId);
		workflowNameComponentConfig.setSpacing(true);
		componentList.add(workflowNameComponentConfig);

		GtnUIFrameworkComponentConfig workflowNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAME, true,
				workflowNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		workflowNameComponent.setAuthorizationIncluded(true);
		workflowNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWCOMPONENT_NAME);

		componentList.add(workflowNameComponent);
	}

	private void addworkflowDesc(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig workflowDescComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESC_LAYOUT, true, parentComponentId);
		workflowDescComponentConfig.setSpacing(true);
		componentList.add(workflowDescComponentConfig);

		GtnUIFrameworkComponentConfig workflowDescComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESC, true,
				workflowDescComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		workflowDescComponent.setAuthorizationIncluded(true);
		workflowDescComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCRIPTION_NAME);

		componentList.add(workflowDescComponent);
	}

	private void addcreatedFrom(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig createdFromComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM_LAYOUT, true, parentComponentId);
		createdFromComponentConfig.setSpacing(true);
		componentList.add(createdFromComponentConfig);

		GtnUIFrameworkComponentConfig createdFromComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM, true, createdFromComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		createdFromComponent.setAuthorizationIncluded(true);
		createdFromComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM_NAME);

		componentList.add(createdFromComponent);

	}

	private void addcreatedTo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig createdToComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CREATEDTO_LAYOUT, true, parentComponentId);
		createdToComponentConfig.setSpacing(true);
		componentList.add(createdToComponentConfig);

		GtnUIFrameworkComponentConfig createdToComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CREATEDTO, true, createdToComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		createdToComponent.setAuthorizationIncluded(true);
		createdToComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CREATEDTO_NAME);

		componentList.add(createdToComponent);
	}

	private void addcreatedBy(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig createdByComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBY_LAYOUT, true, parentComponentId);
		createdByComponentConfig.setSpacing(true);
		componentList.add(createdByComponentConfig);

		GtnUIFrameworkComponentConfig createdByComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBY, true, createdByComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		createdByComponent.setAuthorizationIncluded(true);
		createdByComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY_NAME);

		GtnUIFrameworkTextBoxConfig createdByboxConfig = new GtnUIFrameworkTextBoxConfig();
		createdByboxConfig.setEnable(true);
		createdByboxConfig.setRequired(true);
		createdByboxConfig.setReadOnly(false);
		createdByComponent.setGtnTextBoxConfig(createdByboxConfig);

		List<GtnUIFrameWorkActionConfig> createdByactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig createdBypopupActionConfig = new GtnUIFrameWorkActionConfig();
		createdBypopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		createdBypopupActionConfig
				.addActionParameter("createdBy" + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP);
		createdBypopupActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY_NAME);
		createdBypopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		createdBypopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		createdByactionConfigList.add(createdBypopupActionConfig);
		GtnUIFrameWorkActionConfig createdBycustomCommonValidationAction = new GtnUIFrameWorkActionConfig();
		createdBycustomCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		createdBycustomCommonValidationAction.addActionParameter(GtnFrameworkConfigureApprovedByAction.class.getName());
		createdBycustomCommonValidationAction.addActionParameter("createdBy");
		createdByactionConfigList.add(createdBycustomCommonValidationAction);
		createdByComponent.setGtnUIFrameWorkActionConfigList(createdByactionConfigList);
		componentList.add(createdByComponent);
	}

	private void addapprovedFrom(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig approvedFromComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM_LAYOUT, true, parentComponentId);
		approvedFromComponentConfig.setSpacing(true);
		componentList.add(approvedFromComponentConfig);

		GtnUIFrameworkComponentConfig approvedFromComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM, true,
				approvedFromComponentConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		approvedFromComponent.setAuthorizationIncluded(true);
		approvedFromComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM_NAME);

		componentList.add(approvedFromComponent);

	}

	private void addapprovedTo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig approvedToComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO_LAYOUT, true, parentComponentId);
		approvedToComponentConfig.setSpacing(true);
		componentList.add(approvedToComponentConfig);

		GtnUIFrameworkComponentConfig approvedToComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO, true, approvedToComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		approvedToComponent.setAuthorizationIncluded(true);
		approvedToComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO_NAME);

		componentList.add(approvedToComponent);
	}

	private void addapprovedBy(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig approvedByComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY_LAYOUT, true, parentComponentId);
		approvedByComponentConfig.setSpacing(true);
		componentList.add(approvedByComponentConfig);

		GtnUIFrameworkComponentConfig approvedByComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY, true, approvedByComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		approvedByComponent.setAuthorizationIncluded(true);
		approvedByComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY_NAME);

		GtnUIFrameworkTextBoxConfig approvedByboxConfig = new GtnUIFrameworkTextBoxConfig();
		approvedByboxConfig.setEnable(true);
		approvedByboxConfig.setRequired(true);
		approvedByboxConfig.setReadOnly(false);
		approvedByComponent.setGtnTextBoxConfig(approvedByboxConfig);

		List<GtnUIFrameWorkActionConfig> approvedByactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig approvedBypopupActionConfig = new GtnUIFrameWorkActionConfig();
		approvedBypopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		approvedBypopupActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY
				+ GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP);
		approvedBypopupActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY_NAME);
		approvedBypopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		approvedBypopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		approvedByactionConfigList.add(approvedBypopupActionConfig);

		GtnUIFrameWorkActionConfig approvedBycustomCommonValidationAction = new GtnUIFrameWorkActionConfig();
		approvedBycustomCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		approvedBycustomCommonValidationAction
				.addActionParameter(GtnFrameworkConfigureApprovedByAction.class.getName());
		approvedBycustomCommonValidationAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY);
		approvedByactionConfigList.add(approvedBycustomCommonValidationAction);
		approvedByComponent.setGtnUIFrameWorkActionConfigList(approvedByactionConfigList);
		componentList.add(approvedByComponent);
	}

	private void addworkflowSid(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig workflowSidComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID_LAYOUT, true, parentComponentId);
		workflowSidComponentConfig.setSpacing(true);
		componentList.add(workflowSidComponentConfig);

		GtnUIFrameworkComponentConfig workflowSidComponent = new GtnUIFrameworkComponentConfig();
		workflowSidComponent.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		workflowSidComponent.setAuthorizationIncluded(true);
		workflowSidComponent.setComponentId(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID);
		workflowSidComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID_NAME);
		workflowSidComponent.setVisible(false);
		workflowSidComponent.setAddToParent(true);
		workflowSidComponent.setParentComponentId(workflowSidComponentConfig.getComponentId());
		componentList.add(workflowSidComponent);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkWorkflowInboxClassConstants.BUTTONMAIN_LAYOUT);
		gtnLayout.setParentComponentId(parentComponentId);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addsearchButton(componentList);
		addresetButton(componentList);
		addsaveprofileButton(componentList);
	}

	private void addsearchButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonComponentConfig = new GtnUIFrameworkComponentConfig();
		searchButtonComponentConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonComponentConfig.setAuthorizationIncluded(true);
		searchButtonComponentConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.SEARCH_BTN);
		searchButtonComponentConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SEARCH_NAME);
		searchButtonComponentConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.BUTTONMAIN_LAYOUT);
		searchButtonComponentConfig.setAddToParent(true);
		componentList.add(searchButtonComponentConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.ERROR);
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.BUSINESSPROCESS_MANDATORY);
		alertActionConfig.setActionParameterList(alertParamsList);

		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.PRIVATEVIEW,
				GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW, GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAME,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESC,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM, GtnFrameworkWorkflowInboxClassConstants.CREATEDTO,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBY, GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM,
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO,
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY));
		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		actionConfigList.add(notificationActionConfig);

		GtnUIFrameWorkActionConfig jSListenerAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		jSListenerAction.addActionParameter(GtnFrameworkAddJSListenerAction.class.getName());
		jSListenerAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS);
		jSListenerAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN);
		jSListenerAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.OPENBTN);
		jSListenerAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		actionConfigList.add(jSListenerAction);

		searchButtonComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addresetButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resetButtonComponentConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.RESET_BTN, true,
				GtnFrameworkWorkflowInboxClassConstants.BUTTONMAIN_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButtonComponentConfig.setAuthorizationIncluded(true);
		resetButtonComponentConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESET);

		componentList.add(resetButtonComponentConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkWorkflowInboxClassConstants.GTN_WORKFLOW_CONFIRMATION_MSG_RESET_HEADER);
		params.add(GtnFrameworkWorkflowInboxClassConstants.GTN_WORKFLOW_CONFIRMATION_MSG_RESET);
		params.add(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS,
				GtnFrameworkWorkflowInboxClassConstants.PRIVATEVIEW, GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAME,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESC,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM, GtnFrameworkWorkflowInboxClassConstants.CREATEDTO,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBY, GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM,
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO, GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTID, GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNO, GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME, GtnFrameworkWorkflowInboxClassConstants.ITEMNO,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAME,
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONLEVEL,
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONVALUE,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTTYPE, GtnFrameworkWorkflowInboxClassConstants.COMPANYID,
				GtnFrameworkWorkflowInboxClassConstants.ITEMID,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDRETURNS,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNORETURNS,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMERETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNORETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAMERETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ITEMIDRETURNS,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUSARM,
				GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDARM,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNOARM,
				GtnFrameworkWorkflowInboxClassConstants.CUSTOMERNOARM,
				GtnFrameworkWorkflowInboxClassConstants.CUSTOMERNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.BRANDIDARM,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNOARM, GtnFrameworkWorkflowInboxClassConstants.ITEMNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.BRANDNAMEARM, GtnFrameworkWorkflowInboxClassConstants.GLDATEARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVELARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNOARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYARM,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITARM));

		Object para = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		params.add(Arrays.asList(null, para, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null));
		resetActionConfig.setActionParameterList(params);
		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL,
				GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL,
				GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL };
		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.FALSE);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);

		resetActionConfigList.add(resetActionConfig);
		resetActionConfigList.add(visibleAction);
		resetButtonComponentConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);
	}

	private void addsaveprofileButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig saveViewBtn = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.DSSAVEVIEW, true,
				GtnFrameworkWorkflowInboxClassConstants.BUTTONMAIN_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setAuthorizationIncluded(true);
		saveViewBtn.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SAVEPROFILE);

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig viewAlertActionConfig = new GtnUIFrameWorkActionConfig();
		viewAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> viewAlertParams = new ArrayList<>();
		viewAlertParams.add(GtnFrameworkWorkflowInboxClassConstants.SAVEPROFILENOSEARCH);
		viewAlertParams.add(GtnFrameworkWorkflowInboxClassConstants.SAVEPROFILENOSEARCHPLEASEENTER);
		viewAlertActionConfig.setActionParameterList(viewAlertParams);
		GtnUIFrameWorkActionConfig confi = new GtnUIFrameWorkActionConfig();
		confi.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		confi.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.DSSAVEVIEWLOOKUP);
		confi.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWPOPUP);
		list.add(confi);
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWNAME,
				GtnFrameworkWorkflowInboxClassConstants.EMPTY_STRING);
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		list.add(resetTableConfig);
		GtnUIFrameWorkActionConfig viewNameAction = new GtnUIFrameWorkActionConfig();
		viewNameAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewNameAction.addActionParameter(GtnFrameworkConfigureSaveProfilelookupAction.class.getName());
		viewNameAction.setFieldValues(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.PRIVATEVIEW,
				GtnFrameworkWorkflowInboxClassConstants.PUBLICVIEW,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWUPDATE,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWADD,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWNAME,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWTYPE, GtnFrameworkWorkflowInboxClassConstants.PUBLIC,
				GtnFrameworkWorkflowInboxClassConstants.PRIVATE));
		list.add(viewNameAction);
		saveViewBtn.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(saveViewBtn);
	}

	private void addResultsLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkLayoutConfig resultMainLayoutConfig = new GtnUIFrameworkLayoutConfig();
		resultMainLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig resultMainComponentConfig = new GtnUIFrameworkComponentConfig();
		resultMainComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultMainComponentConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.RESULTMAIN_LAYOUT);
		resultMainComponentConfig.setAddToParent(true);
		resultMainComponentConfig.setParentComponentId(parentComponentId);
		resultMainComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultMainComponentConfig.setGtnLayoutConfig(resultMainLayoutConfig);
		componentList.add(resultMainComponentConfig);

		GtnUIFrameworkComponentConfig resultMainPanelConfig = new GtnUIFrameworkComponentConfig();
		resultMainPanelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESULTS);
		resultMainPanelConfig.setAuthorizationIncluded(true);
		resultMainPanelConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.RESULTMAIN_PANEL);
		resultMainPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultMainPanelConfig.setParentComponentId(resultMainComponentConfig.getComponentId());
		resultMainPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		resultMainPanelConfig.setAddToParent(true);
		resultMainPanelConfig.setSpacing(true);
		componentList.add(resultMainPanelConfig);

		GtnUIFrameworkLayoutConfig resultMainPanelInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		resultMainPanelInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig resultMainPanelInnerComponentConfig = new GtnUIFrameworkComponentConfig();
		resultMainPanelInnerComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultMainPanelInnerComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.RESULTMAINPANEL_INNERLAYOUT);
		resultMainPanelInnerComponentConfig.setAddToParent(true);
		resultMainPanelInnerComponentConfig.setSpacing(true);
		resultMainPanelInnerComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		resultMainPanelInnerComponentConfig.setParentComponentId(resultMainPanelConfig.getComponentId());
		resultMainPanelInnerComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultMainPanelInnerComponentConfig.setGtnLayoutConfig(resultMainPanelInnerLayoutConfig);
		componentList.add(resultMainPanelInnerComponentConfig);

		addPagedTableComponent(componentList, resultMainPanelInnerComponentConfig.getComponentId());

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId(parentComponentId);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setSpacing(true);
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(false);
		searchResults.setSinkItemPerPageWithPageLength(true);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSelectable(true);

		String pageParameters = null;
		String projectionMasterSid = null;
		pageParameters = Page.getCurrent().getLocation().getQuery();

		if (pageParameters != null) {

			String[] parameters = pageParameters.split("&");

			HashMap<String, String> hm = new HashMap<>();

			for (String para : parameters) {
				String[] paraStr = para.split(GtnFrameworkWorkflowInboxClassConstants.EQUAL);
				hm.put(paraStr[0], paraStr[1]);
			}

			projectionMasterSid = hm.get(GtnFrameworkWorkflowInboxClassConstants.PROJECTIONMASTER_SID);
		}
		if (projectionMasterSid != null) {

			searchResults.setTableColumnDataType(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxGcmSearchTableColumnsDataType());
			searchResults.setTableVisibleHeader(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchTableHeaders());
			searchResults.setExtraColumn(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchExtraColumns());
			searchResults.setExtraColumnDataType(GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxForecastingSearchTableExtracolumnsDataType());
			searchResults.setTableColumnMappingId(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchTableColumns());

		} else {

			searchResults.setTableColumnDataType(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableColumnsDataType());
			searchResults.setTableVisibleHeader(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableHeaders());
			searchResults.setExtraColumn(new Object[] { GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID,
					GtnFrameworkWorkflowInboxClassConstants.NOOFAPPROVALS,
					GtnFrameworkWorkflowInboxClassConstants.APPROVALLEVEL,
					GtnFrameworkWorkflowInboxClassConstants.CREATEDBYID });
			searchResults.setTableColumnMappingId(
					GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableColumns());
		}

		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWINBOX);
		searchResults.setQueryName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHQUERY);
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CONTRACT_WORKFLOW_SEARCH);
		searchResults.setCustomFilterConfigMap(getCustomFilterConfig());

		GtnUIFrameWorkActionConfig fetchActionConfig = new GtnUIFrameWorkActionConfig();
		fetchActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fetchActionConfig.addActionParameter(GtnFrameworkConfigureOpenButtonAction.class.getName());
		fetchActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		fetchActionConfig.addActionParameter(Boolean.TRUE);
		if (projectionMasterSid != null) {
			fetchActionConfig.addActionParameter(projectionMasterSid);
		}
		searchResultConfig.addGtnUIFrameWorkActionConfig(fetchActionConfig);
		searchResults.setItemClickListener(true);

		if (projectionMasterSid == null) {
			GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			enableAction.addActionParameter(GtnFrameworkWorkflowResultTableItemClickAction.class.getName());
			searchResultConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(enableAction));
		}
		searchResultConfig.setGtnPagedTableConfig(searchResults);
		componentList.add(searchResultConfig);

	}

	private void addBottomControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkWorkflowInboxClassConstants.BOTTOMBUTTONMAINLAYOUT);
		gtnLayout.setParentComponentId(parentComponentId);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addhistoryButton(componentList);
		addopenButton(componentList);
		addviewButton(componentList);
		addexcelButton(componentList);
	}

	private void addhistoryButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig historyButtonComponentConfig = new GtnUIFrameworkComponentConfig();
		historyButtonComponentConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		historyButtonComponentConfig.setAuthorizationIncluded(true);
		historyButtonComponentConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYBTN);
		historyButtonComponentConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.HISTORY);
		historyButtonComponentConfig
				.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.BOTTOMBUTTONMAINLAYOUT);
		historyButtonComponentConfig.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.ERROR);
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.PLEASE_SELECT_RECORD_VIEWHISTORY);

		alertActionConfig.setActionParameterList(alertParamsList);
		list.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confi = new GtnUIFrameWorkActionConfig();
		confi.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		confi.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.HISTORYLOOKUP);
		confi.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_HISTORY);
		confi.addActionParameter(GtnFrameworkCssConstants.PERCENT_85);
		confi.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		list.add(confi);

		GtnUIFrameWorkActionConfig searchBtnActionConfig = new GtnUIFrameWorkActionConfig();
		searchBtnActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchBtnActionConfig.addActionParameter(GtnFrameworkSelectBtnRecordClickTableAction.class.getName());

		list.add(searchBtnActionConfig);

		GtnUIFrameWorkActionConfig historycustomCommonValidationAction = new GtnUIFrameWorkActionConfig();
		historycustomCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		historycustomCommonValidationAction
				.addActionParameter(GtnFrameworkConfigureHistoryButtonAction.class.getName());
		list.add(historycustomCommonValidationAction);

		GtnUIFrameWorkActionConfig popActionConfig = new GtnUIFrameWorkActionConfig();
		popActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		popActionConfig.addActionParameter(GtnFrameworPopulatefromTableAction.class.getName());
		list.add(popActionConfig);
		componentList.add(historyButtonComponentConfig);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHRESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID));
		list.add(loadDataTableActionConfig);
		historyButtonComponentConfig.setGtnUIFrameWorkActionConfigList(list);
	}

	private void addopenButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig openLayoutConfig = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig openComponentConfig = new GtnUIFrameworkComponentConfig();
		openComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		openComponentConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.OPEN_LAYOUT);
		openComponentConfig.setAddToParent(true);
		openComponentConfig.setSpacing(true);
		openComponentConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.BOTTOMBUTTONMAINLAYOUT);
		openComponentConfig.setGtnLayoutConfig(openLayoutConfig);
		componentList.add(openComponentConfig);

		GtnUIFrameworkComponentConfig openComponent = new GtnUIFrameworkComponentConfig();
		openComponent.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		openComponent.setAuthorizationIncluded(true);
		openComponent.setComponentId(GtnFrameworkWorkflowInboxClassConstants.OPENBTN);
		openComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.OPEN);
		openComponent.setEnable(false);
		openComponent.setAddToParent(true);
		openComponent.setParentComponentId(openComponentConfig.getComponentId());
		componentList.add(openComponent);

	}

	private void addviewButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig viewLayoutConfig = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig viewComponentConfig = new GtnUIFrameworkComponentConfig();
		viewComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		viewComponentConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.VIEW_LAYOUT);
		viewComponentConfig.setAddToParent(true);
		viewComponentConfig.setSpacing(true);
		viewComponentConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.BOTTOMBUTTONMAINLAYOUT);
		viewComponentConfig.setGtnLayoutConfig(viewLayoutConfig);
		componentList.add(viewComponentConfig);

		GtnUIFrameworkComponentConfig viewComponent = new GtnUIFrameworkComponentConfig();
		viewComponent.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		viewComponent.setAuthorizationIncluded(true);
		viewComponent.setComponentId(GtnFrameworkWorkflowInboxClassConstants.VIEWBTN);
		viewComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.VIEW);
		viewComponent.setEnable(false);
		viewComponent.setAddToParent(true);
		viewComponent.setParentComponentId(viewComponentConfig.getComponentId());
		componentList.add(viewComponent);

	}

	private void addexcelButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		excelButtonConfig.setAddToParent(true);
		excelButtonConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.BOTTOMBUTTONMAINLAYOUT);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonConfig.setExportFileName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DETAILS);
		gtnUIFrameworkExcelButtonConfig.setExportFromTable(true);
		gtnUIFrameworkExcelButtonConfig
				.setExportTableId(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE);
		gtnUIFrameworkExcelButtonConfig.setWriteFileInWebService(false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void configureForecastingandARPDetailSearchLayouts(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig forecastingdetailSearchPanelComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastingdetailSearchPanelComponentConfig
				.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DETAILSEARCH);
		forecastingdetailSearchPanelComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL);
		forecastingdetailSearchPanelComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastingdetailSearchPanelComponentConfig.setParentComponentId(parentComponentId);
		forecastingdetailSearchPanelComponentConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		forecastingdetailSearchPanelComponentConfig.setAuthorizationIncluded(true);
		forecastingdetailSearchPanelComponentConfig.setAddToParent(true);
		forecastingdetailSearchPanelComponentConfig.setSpacing(true);
		forecastingdetailSearchPanelComponentConfig.setVisible(false);
		componentList.add(forecastingdetailSearchPanelComponentConfig);

		GtnUIFrameworkLayoutConfig forecastingDetailSearchInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		forecastingDetailSearchInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig forecastingDetailSearchInnerComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastingDetailSearchInnerComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastingDetailSearchInnerComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL_INNERLAYOUT);
		forecastingDetailSearchInnerComponentConfig.setAddToParent(true);
		forecastingDetailSearchInnerComponentConfig.setSpacing(true );
		forecastingDetailSearchInnerComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		forecastingDetailSearchInnerComponentConfig
				.setParentComponentId(forecastingdetailSearchPanelComponentConfig.getComponentId());
		forecastingDetailSearchInnerComponentConfig.setGtnLayoutConfig(forecastingDetailSearchInnerLayoutConfig);
		componentList.add(forecastingDetailSearchInnerComponentConfig);

		addForecastingARPComponents(componentList, forecastingDetailSearchInnerComponentConfig);

	}

	private void addForecastingARPComponents(List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig forecastingDetailSearchInnerComponentConfig) {
		addcontractId(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addcontractNo(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addcompanyNo(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addcompanyName(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addbusinessunitId(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addbusinessunitNo(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addbusinessunitName(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addcontractName(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		additemNo(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		additemName(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		adddeductionLevel(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		adddeductionValue(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addcontractType(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		addcompanyID(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
		additemId(componentList, forecastingDetailSearchInnerComponentConfig.getComponentId());
	}

	private void addcontractId(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig contractIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDLAYOUT, true, parentComponentId);
		contractIdComponentConfig.setSpacing(true);
		componentList.add(contractIdComponentConfig);

		GtnUIFrameworkComponentConfig contractIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTID, true, contractIdComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractIdComponent.setAuthorizationIncluded(true);
		contractIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_ID);

		componentList.add(contractIdComponent);

	}

	private void addcontractNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig contractNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO_LAYOUT, true, parentComponentId);
		contractNoComponentConfig.setSpacing(true);
		componentList.add(contractNoComponentConfig);

		GtnUIFrameworkComponentConfig contractNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO, true, contractNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractNoComponent.setAuthorizationIncluded(true);
		contractNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_NO);

		componentList.add(contractNoComponent);
	}

	private void addcompanyNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig companyNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNO_LAYOUT, true, parentComponentId);
		companyNoComponentConfig.setSpacing(true);
		componentList.add(companyNoComponentConfig);

		GtnUIFrameworkComponentConfig companyNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNO, true, companyNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoComponent.setAuthorizationIncluded(true);
		companyNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY_NO);

		componentList.add(companyNoComponent);
	}

	private void addcompanyName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig companyNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME_LAYOUT, true, parentComponentId);
		companyNameComponentConfig.setSpacing(true);
		componentList.add(companyNameComponentConfig);

		GtnUIFrameworkComponentConfig companyNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME, true, companyNameComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameComponent.setAuthorizationIncluded(true);
		companyNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY_NAME);

		componentList.add(companyNameComponent);

	}

	private void addbusinessunitId(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig businessUnitIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID_LAYOUT, true, parentComponentId);
		businessUnitIdComponentConfig.setSpacing(true);
		componentList.add(businessUnitIdComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID, true,
				businessUnitIdComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitIdComponent.setAuthorizationIncluded(true);
		businessUnitIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_ID);

		componentList.add(businessUnitIdComponent);
	}

	private void addbusinessunitNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig businessUnitNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO_LAYOUT, true, parentComponentId);
		businessUnitNoComponentConfig.setSpacing(true);
		componentList.add(businessUnitNoComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO, true,
				businessUnitNoComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitNoComponent.setAuthorizationIncluded(true);
		businessUnitNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NO);

		componentList.add(businessUnitNoComponent);
	}

	private void addbusinessunitName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig businessUnitNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME_LAYOUT, true, parentComponentId);
		businessUnitNameComponentConfig.setSpacing(true);
		componentList.add(businessUnitNameComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME, true,
				businessUnitNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitNameComponent.setAuthorizationIncluded(true);
		businessUnitNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NAME);

		componentList.add(businessUnitNameComponent);

	}

	private void addcontractName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig contractNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME_LAYOUT, true, parentComponentId);
		contractNameComponentConfig.setSpacing(true);
		componentList.add(contractNameComponentConfig);

		GtnUIFrameworkComponentConfig contractNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME, true,
				contractNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		contractNameComponent.setAuthorizationIncluded(true);
		contractNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_NAME);

		componentList.add(contractNameComponent);
	}

	private void additemNo(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig itemNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNO_LAYOUT, true, parentComponentId);
		itemNoComponentConfig.setSpacing(true);
		componentList.add(itemNoComponentConfig);

		GtnUIFrameworkComponentConfig itemNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNO, true, itemNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNoComponent.setAuthorizationIncluded(true);
		itemNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_NO);

		componentList.add(itemNoComponent);
	}

	private void additemName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig itemNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAME_LAYOUT, true, parentComponentId);
		itemNameComponentConfig.setSpacing(true);
		componentList.add(itemNameComponentConfig);

		GtnUIFrameworkComponentConfig itemNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAME, true, itemNameComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNameComponent.setAuthorizationIncluded(true);
		itemNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_NAME);

		componentList.add(itemNameComponent);

	}

	private void adddeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig deductionLevelComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVEL_LAYOUT, true, parentComponentId);
		deductionLevelComponentConfig.setSpacing(true);

		componentList.add(deductionLevelComponentConfig);

		GtnUIFrameworkComponentConfig deductionLevelComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONLEVEL, true,
				deductionLevelComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionLevelComponent.setAuthorizationIncluded(true);
		deductionLevelComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_LEVEL);

		GtnUIFrameworkComboBoxConfig deductionLevelComboBoxConfig = new GtnUIFrameworkComboBoxConfig();

		deductionLevelComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionLevelComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DEDUCTION_LEVELS);

		deductionLevelComponent.setGtnComboboxConfig(deductionLevelComboBoxConfig);

		List<GtnUIFrameWorkActionConfig> gtnUiFramwworkActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUiFramwworkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUiFramwworkActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUiFramwworkActionConfig.addActionParameter(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_INBOX_DEDUCTION_LEVEL_VALUE_ACTION);

		gtnUiFramwworkActionConfigList.add(gtnUiFramwworkActionConfig);
		deductionLevelComponent.setGtnUIFrameWorkActionConfigList(gtnUiFramwworkActionConfigList);

		componentList.add(deductionLevelComponent);
	}

	private void adddeductionValue(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig deductionValueComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUE_LAYOUT, true, parentComponentId);
		deductionValueComponentConfig.setSpacing(true);
		componentList.add(deductionValueComponentConfig);

		GtnUIFrameworkComponentConfig deductionValueComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONVALUE, true,
				deductionValueComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionValueComponent.setAuthorizationIncluded(true);
		deductionValueComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_VALUE);

		GtnUIFrameworkComboBoxConfig deductionValueComboBoxConfig = new GtnUIFrameworkComboBoxConfig();

		deductionValueComponent.setGtnComboboxConfig(deductionValueComboBoxConfig);
		componentList.add(deductionValueComponent);
	}

	private void addcontractType(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig contractTypeComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTTYPE_LAYOUT, true, parentComponentId);
		contractTypeComponentConfig.setSpacing(true);
		componentList.add(contractTypeComponentConfig);

		GtnUIFrameworkComponentConfig contractTypeComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTTYPE, true,
				contractTypeComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		contractTypeComponent.setAuthorizationIncluded(true);
		contractTypeComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACTTYPE_NAME);

		GtnUIFrameworkComboBoxConfig contractTypeComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		contractTypeComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		contractTypeComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_TYPE);
		contractTypeComponent.setGtnComboboxConfig(contractTypeComboBoxConfig);
		componentList.add(contractTypeComponent);

	}

	private void addcompanyID(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig companyIDComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYID_LAYOUT, true, parentComponentId);
		companyIDComponentConfig.setSpacing(true);
		componentList.add(companyIDComponentConfig);

		GtnUIFrameworkComponentConfig companyIDComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYID, true, companyIDComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIDComponent.setAuthorizationIncluded(true);
		companyIDComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY_ID);

		componentList.add(companyIDComponent);
	}

	private void additemId(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig itemIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMID_LAYOUT, true, parentComponentId);
		itemIdComponentConfig.setSpacing(true);
		componentList.add(itemIdComponentConfig);

		GtnUIFrameworkComponentConfig itemIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMID, true, itemIdComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemIdComponent.setAuthorizationIncluded(true);
		itemIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_ID);

		componentList.add(itemIdComponent);
	}

	private void configureReturnsDetailSearchLayouts(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig returnsdetailSearchPanelComponentConfig = new GtnUIFrameworkComponentConfig();
		returnsdetailSearchPanelComponentConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DETAIL_SEARCH);
		returnsdetailSearchPanelComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL);
		returnsdetailSearchPanelComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		returnsdetailSearchPanelComponentConfig.setParentComponentId(parentComponentId);
		returnsdetailSearchPanelComponentConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		returnsdetailSearchPanelComponentConfig.setAuthorizationIncluded(true);
		returnsdetailSearchPanelComponentConfig.setAddToParent(true);
		returnsdetailSearchPanelComponentConfig.setSpacing(true);
		returnsdetailSearchPanelComponentConfig.setVisible(false);
		componentList.add(returnsdetailSearchPanelComponentConfig);

		GtnUIFrameworkLayoutConfig returnsDetailSearchInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		returnsDetailSearchInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig returnsDetailSearchInnerComponentConfig = new GtnUIFrameworkComponentConfig();
		returnsDetailSearchInnerComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		returnsDetailSearchInnerComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL_INNERLAYOUT);
		returnsDetailSearchInnerComponentConfig.setAddToParent(true);
		returnsDetailSearchInnerComponentConfig.setSpacing(true);
		returnsDetailSearchInnerComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		returnsDetailSearchInnerComponentConfig
				.setParentComponentId(returnsdetailSearchPanelComponentConfig.getComponentId());
		returnsDetailSearchInnerComponentConfig.setGtnLayoutConfig(returnsDetailSearchInnerLayoutConfig);
		componentList.add(returnsDetailSearchInnerComponentConfig);

		addbusinessunitIdReturns(componentList, returnsDetailSearchInnerComponentConfig.getComponentId());
		addbusinessunitNoReturns(componentList, returnsDetailSearchInnerComponentConfig.getComponentId());
		addbusinessunitNameReturns(componentList, returnsDetailSearchInnerComponentConfig.getComponentId());
		additemNoReturns(componentList, returnsDetailSearchInnerComponentConfig.getComponentId());
		additemNameReturns(componentList, returnsDetailSearchInnerComponentConfig.getComponentId());
		additemIdReturns(componentList, returnsDetailSearchInnerComponentConfig.getComponentId());

	}

	private void addbusinessunitIdReturns(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig businessUnitIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID_LAYOUT, true, parentComponentId);
		businessUnitIdComponentConfig.setSpacing(true);
		componentList.add(businessUnitIdComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDRETURNS, true,
				businessUnitIdComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitIdComponent.setAuthorizationIncluded(true);
		businessUnitIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_ID);

		componentList.add(businessUnitIdComponent);

	}

	private void addbusinessunitNoReturns(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig businessUnitNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO_LAYOUT, true, parentComponentId);
		businessUnitNoComponentConfig.setSpacing(true);
		componentList.add(businessUnitNoComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNORETURNS, true,
				businessUnitNoComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitNoComponent.setAuthorizationIncluded(true);
		businessUnitNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NO);

		componentList.add(businessUnitNoComponent);
	}

	private void addbusinessunitNameReturns(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig businessUnitNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME_LAYOUT, true, parentComponentId);
		businessUnitNameComponentConfig.setSpacing(true);
		componentList.add(businessUnitNameComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMERETURNS, true,
				businessUnitNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitNameComponent.setAuthorizationIncluded(true);
		businessUnitNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NAME);

		componentList.add(businessUnitNameComponent);
	}

	private void additemNoReturns(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig itemNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNO_LAYOUT, true, parentComponentId);
		itemNoComponentConfig.setSpacing(true);
		componentList.add(itemNoComponentConfig);

		GtnUIFrameworkComponentConfig itemNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNORETURNS, true, itemNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNoComponent.setAuthorizationIncluded(true);
		itemNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_NO);

		componentList.add(itemNoComponent);

	}

	private void additemNameReturns(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig itemNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAME_LAYOUT, true, parentComponentId);
		itemNameComponentConfig.setSpacing(true);
		componentList.add(itemNameComponentConfig);

		GtnUIFrameworkComponentConfig itemNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAMERETURNS, true, itemNameComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNameComponent.setAuthorizationIncluded(true);
		itemNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_NAME);

		componentList.add(itemNameComponent);
	}

	private void additemIdReturns(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig itemIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMID_LAYOUT, true, parentComponentId);
		itemIdComponentConfig.setSpacing(true);
		componentList.add(itemIdComponentConfig);

		GtnUIFrameworkComponentConfig itemIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMIDRETURNS, true, itemIdComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemIdComponent.setAuthorizationIncluded(true);
		itemIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_ID);

		componentList.add(itemIdComponent);
	}

	private void configureArmDetailSearchLayouts(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig armdetailSearchPanelComponentConfig = new GtnUIFrameworkComponentConfig();
		armdetailSearchPanelComponentConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DETAIL_SEARCH);
		armdetailSearchPanelComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL);
		armdetailSearchPanelComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		armdetailSearchPanelComponentConfig.setParentComponentId(parentComponentId);
		armdetailSearchPanelComponentConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		armdetailSearchPanelComponentConfig.setAuthorizationIncluded(true);
		armdetailSearchPanelComponentConfig.setAddToParent(true);
		armdetailSearchPanelComponentConfig.setSpacing(true);
		armdetailSearchPanelComponentConfig.setVisible(false);
		componentList.add(armdetailSearchPanelComponentConfig);

		GtnUIFrameworkLayoutConfig armDetailSearchInnerLayoutConfig = new GtnUIFrameworkLayoutConfig();
		armDetailSearchInnerLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig armDetailSearchInnerComponentConfig = new GtnUIFrameworkComponentConfig();
		armDetailSearchInnerComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		armDetailSearchInnerComponentConfig
				.setComponentId(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL_INNERLAYOUT);
		armDetailSearchInnerComponentConfig.setAddToParent(true);
		armDetailSearchInnerComponentConfig.setSpacing(true);
		armDetailSearchInnerComponentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		armDetailSearchInnerComponentConfig.setParentComponentId(armdetailSearchPanelComponentConfig.getComponentId());
		armDetailSearchInnerComponentConfig.setGtnLayoutConfig(armDetailSearchInnerLayoutConfig);
		componentList.add(armDetailSearchInnerComponentConfig);

		addARMDetailSearchComponents(componentList, armDetailSearchInnerComponentConfig);

	}

	private void addARMDetailSearchComponents(List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkComponentConfig armDetailSearchInnerComponentConfig) {
		addworkflowStatusArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addadjustmentType(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addcontractIdArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addcontractNoArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addcustomerNoArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addcustomerNameArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addbrandIdArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addcontractNameArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		additemNoArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		additemNameArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addbrandNameArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		addglDateArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		adddeductionLevelArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		adddeductionValueArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		adddeductionNoArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
		adddeductionNameArm(componentList, armDetailSearchInnerComponentConfig.getComponentId());
	}

	private void addworkflowStatusArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig workflowStatusComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUS_LAYOUT, true, parentComponentId);
		workflowStatusComponentConfig.setSpacing(true);
		componentList.add(workflowStatusComponentConfig);

		GtnUIFrameworkComponentConfig workflowStatusComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUSARM, true,
				workflowStatusComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		workflowStatusComponent.setAuthorizationIncluded(true);
		workflowStatusComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_STATUS);

		GtnUIFrameworkComboBoxConfig workflowStatusComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		workflowStatusComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		workflowStatusComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUS);
		workflowStatusComponent.setGtnComboboxConfig(workflowStatusComboBoxConfig);
		componentList.add(workflowStatusComponent);

	}

	private void addadjustmentType(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig adjustmentTypeComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE_LAYOUT, true, parentComponentId);
		adjustmentTypeComponentConfig.setSpacing(true);
		componentList.add(adjustmentTypeComponentConfig);

		GtnUIFrameworkComponentConfig adjustmentTypeComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE, true,
				adjustmentTypeComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentTypeComponent.setAuthorizationIncluded(true);
		adjustmentTypeComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENT_TYPE);

		GtnUIFrameworkComboBoxConfig adjustmentTypeComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		adjustmentTypeComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentTypeComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPEDDLB);
		adjustmentTypeComboBoxConfig.setDefaultValue("-Select Value-");
		adjustmentTypeComponent.setGtnComboboxConfig(adjustmentTypeComboBoxConfig);
		componentList.add(adjustmentTypeComponent);
	}

	private void addcontractIdArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig contractIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDLAYOUT, true, parentComponentId);
		contractIdComponentConfig.setSpacing(true);
		componentList.add(contractIdComponentConfig);

		GtnUIFrameworkComponentConfig contractIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDARM, true, contractIdComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractIdComponent.setAuthorizationIncluded(true);
		contractIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_ID);

		componentList.add(contractIdComponent);
	}

	private void addcontractNoArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig contractNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO_LAYOUT, true, parentComponentId);
		contractNoComponentConfig.setSpacing(true);
		componentList.add(contractNoComponentConfig);

		GtnUIFrameworkComponentConfig contractNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNOARM, true, contractNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		contractNoComponent.setAuthorizationIncluded(true);
		contractNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_NO);

		componentList.add(contractNoComponent);

	}

	private void addcustomerNoArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig customerNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CUSTOMERNO_LAYOUT, true, parentComponentId);
		customerNoComponentConfig.setSpacing(true);
		componentList.add(customerNoComponentConfig);

		GtnUIFrameworkComponentConfig customerNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CUSTOMERNOARM, true, customerNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		customerNoComponent.setAuthorizationIncluded(true);
		customerNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CUSTOMER_NO);

		componentList.add(customerNoComponent);
	}

	private void addcustomerNameArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig customerNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CUSTOMERNAME_LAYOUT, true, parentComponentId);
		customerNameComponentConfig.setSpacing(true);
		componentList.add(customerNameComponentConfig);

		GtnUIFrameworkComponentConfig customerNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CUSTOMERNAMEARM, true,
				customerNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		customerNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CUSTOMER_NAME);

		componentList.add(customerNameComponent);
	}

	private void addbrandIdArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig brandIdComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BRANDID_LAYOUT, true, parentComponentId);
		brandIdComponentConfig.setSpacing(true);
		componentList.add(brandIdComponentConfig);

		GtnUIFrameworkComponentConfig brandIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BRANDIDARM, true, brandIdComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		brandIdComponent.setAuthorizationIncluded(true);
		brandIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BRAND_ID);

		componentList.add(brandIdComponent);

	}

	private void addcontractNameArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig contractNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME_LAYOUT, true, parentComponentId);
		contractNameComponentConfig.setSpacing(true);
		componentList.add(contractNameComponentConfig);

		GtnUIFrameworkComponentConfig workflowNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAMEARM, true,
				contractNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		workflowNameComponent.setAuthorizationIncluded(true);
		workflowNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CONTRACT_NAME);

		componentList.add(workflowNameComponent);
	}

	private void additemNoArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig itemNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNO_LAYOUT, true, parentComponentId);
		itemNoComponentConfig.setSpacing(true);
		componentList.add(itemNoComponentConfig);

		GtnUIFrameworkComponentConfig itemNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNOARM, true, itemNoComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNoComponent.setAuthorizationIncluded(true);
		itemNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_NO);

		itemNoComponent.setComponentWsFieldId("itemNoArm");
		componentList.add(itemNoComponent);
	}

	private void additemNameArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig itemNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAME_LAYOUT, true, parentComponentId);
		itemNameComponentConfig.setSpacing(true);
		componentList.add(itemNameComponentConfig);

		GtnUIFrameworkComponentConfig workflowIdComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAMEARM, true, itemNameComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		workflowIdComponent.setAuthorizationIncluded(true);
		workflowIdComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ITEM_NAME);

		componentList.add(workflowIdComponent);

	}

	private void addbrandNameArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig brandNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BRANDNAME_LAYOUT, true, parentComponentId);
		brandNameComponentConfig.setSpacing(true);
		componentList.add(brandNameComponentConfig);

		GtnUIFrameworkComponentConfig brandNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BRANDNAMEARM, true, brandNameComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		brandNameComponent.setAuthorizationIncluded(true);
		brandNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BRAND_NAME);

		componentList.add(brandNameComponent);
	}

	private void addglDateArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig workflowDescComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.GLDATE_LAYOUT, true, parentComponentId);
		workflowDescComponentConfig.setSpacing(true);
		componentList.add(workflowDescComponentConfig);

		GtnUIFrameworkComponentConfig glDateComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.GLDATEARM, true, workflowDescComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		glDateComponent.setAuthorizationIncluded(true);
		glDateComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.GL_DATE);

		componentList.add(glDateComponent);
	}

	private void adddeductionLevelArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig deductionLevelComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVEL_LAYOUT, true, parentComponentId);
		deductionLevelComponentConfig.setSpacing(true);
		componentList.add(deductionLevelComponentConfig);

		GtnUIFrameworkComponentConfig deductionLevelComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVELARM, true,
				deductionLevelComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionLevelComponent.setAuthorizationIncluded(true);
		deductionLevelComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_LEVEL);

		GtnUIFrameworkComboBoxConfig deductionLevelComboBoxConfig = new GtnUIFrameworkComboBoxConfig();

		deductionLevelComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionLevelComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_LEVELS);

		deductionLevelComponent.setGtnComboboxConfig(deductionLevelComboBoxConfig);

		componentList.add(deductionLevelComponent);

	}

	private void adddeductionValueArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig deductionValueComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUE_LAYOUT, true, parentComponentId);
		deductionValueComponentConfig.setSpacing(true);
		componentList.add(deductionValueComponentConfig);

		GtnUIFrameworkComponentConfig deductionValueComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM, true,
				deductionValueComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		deductionValueComponent.setAuthorizationIncluded(true);
		deductionValueComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_VALUE);

		GtnUIFrameworkComboBoxConfig deductionValueComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		deductionValueComponent.setGtnComboboxConfig(deductionValueComboBoxConfig);

		GtnUIFrameworkComboBoxConfig deductionValueARMComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		deductionValueARMComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionValueARMComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUE_ARMDDLB);
		deductionValueComponent.setGtnComboboxConfig(deductionValueARMComboBoxConfig);

		componentList.add(deductionValueComponent);
	}

	private void adddeductionNoArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig deductionNoComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNO_LAYOUT, true, parentComponentId);
		deductionNoComponentConfig.setSpacing(true);
		componentList.add(deductionNoComponentConfig);

		GtnUIFrameworkComponentConfig deductionNoComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNOARM, true,
				deductionNoComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		deductionNoComponent.setAuthorizationIncluded(true);
		deductionNoComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_NO);

		componentList.add(deductionNoComponent);
	}

	private void adddeductionNameArm(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig deductionNameComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNAME_LAYOUT, true, parentComponentId);
		deductionNameComponentConfig.setSpacing(true);
		componentList.add(deductionNameComponentConfig);

		GtnUIFrameworkComponentConfig deductionNameComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNAMEARM, true,
				deductionNameComponentConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		deductionNameComponent.setAuthorizationIncluded(true);
		deductionNameComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_NAME);

		componentList.add(deductionNameComponent);
	}

	private void addCompany(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig companyARMComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYARM_LAYOUT, true, parentComponentId);
		companyARMComponentConfig.setSpacing(true);
		componentList.add(companyARMComponentConfig);

		GtnUIFrameworkComponentConfig companyARMComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYARM, true, companyARMComponentConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		companyARMComponent.setAuthorizationIncluded(true);
		companyARMComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY);

		GtnUIFrameworkComboBoxConfig companyARMComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		companyARMComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyARMComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.COMPANYARMDDLB);
		companyARMComponent.setGtnComboboxConfig(companyARMComboBoxConfig);
		componentList.add(companyARMComponent);

	}

	private void addbusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig businessUnitARMComponentConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITARM_LAYOUT, true, parentComponentId);
		businessUnitARMComponentConfig.setSpacing(true);
		componentList.add(businessUnitARMComponentConfig);

		GtnUIFrameworkComponentConfig businessUnitARMComponent = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITARM, true,
				businessUnitARMComponentConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		businessUnitARMComponent.setAuthorizationIncluded(true);
		businessUnitARMComponent.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT);

		GtnUIFrameworkComboBoxConfig businessUnitARMComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitARMComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitARMComboBoxConfig.setComboBoxType(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITARMDDLB);
		businessUnitARMComponent.setGtnComboboxConfig(businessUnitARMComboBoxConfig);
		componentList.add(businessUnitARMComponent);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkWorkflowInboxClassConstants.STATUS,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBY, GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY,
				GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUSARM };
		String[] listNameArray = { GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUS,
				GtnFrameworkWorkflowInboxClassConstants.USERS, GtnFrameworkWorkflowInboxClassConstants.USERS,
				GtnFrameworkWorkflowInboxClassConstants.ARM_ADJUSTMENT_TYPE,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUS };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig wfMainCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			wfMainCustomFilterConfig.setPropertId(propertyIds[i]);
			wfMainCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig wfMainCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			wfMainCustomFilterComponentConfig
					.setComponentId(GtnFrameworkWorkflowInboxClassConstants.CUSTOMFILTERCOMBOBOX);
			wfMainCustomFilterComponentConfig
					.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CUSTOMFILTERCOMBOBOX);
			GtnUIFrameworkComboBoxConfig comboBoxConfig;

			if (propertyIds[i].equals(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY)
					|| propertyIds[i].equals(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY)) {
				comboBoxConfig = configProvider.getComboBoxConfig(listNameArray[i],
						GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
			} else {
				comboBoxConfig = configProvider.getComboBoxConfig(listNameArray[i],
						GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

			}
			comboBoxConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			wfMainCustomFilterComponentConfig.setGtnComboboxConfig(comboBoxConfig);
			wfMainCustomFilterConfig.setGtnComponentConfig(wfMainCustomFilterComponentConfig);
			customFilterConfigMap.put(wfMainCustomFilterConfig.getPropertId(), wfMainCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

}
