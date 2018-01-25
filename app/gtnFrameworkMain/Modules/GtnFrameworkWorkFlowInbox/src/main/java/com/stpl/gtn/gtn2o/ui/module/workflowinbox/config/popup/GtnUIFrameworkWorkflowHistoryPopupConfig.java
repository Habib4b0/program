package com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkDownloadattachmentAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkFetchHistorytoAttachmentAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkWorkflowHistoryPopupConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_HISTORY,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYLOOKUP, false);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList);
		return view;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkWorkflowInboxClassConstants.HISTORYROOTLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addFieldPanel(componentList);
		addParentTableLayout(componentList);
		addHistoryDetailsPanel(componentList);
		addAttachmentDetailsPanel(componentList);
		addActionButtonLayout(componentList);
	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_PANEL, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYROOTLAYOUT);
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SUMMARY);
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addFieldLayout(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_PANEL,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCompanyId(componentList);
		addCompanyNo(componentList);
		addCompanyName(componentList);
		addBusinessUnitId(componentList);
		addBusinessUnitNo(componentList);
		addBusinessUnitName(componentList);
		addBusinessProcess(componentList);
		addWorkflowId(componentList);
		addWorkflowName(componentList);
		addWorkflowDesc(componentList);
		addWorkflowDesc1(componentList);

	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYIDLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYID_HISTORY, true,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYIDLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY_ID);

		companyIdConfig.setEnable(false);

		componentList.add(companyIdConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNOLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNO, true,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNOLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY_NO);

		companyNoConfig.setVisible(false);
		companyNoConfig.setEnable(false);
		componentList.add(companyNoConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNAMELAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME, true,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNAMELAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.COMPANY_NAME);

		companyNameConfig.setVisible(false);
		companyNameConfig.setEnable(false);
		componentList.add(companyNameConfig);
	}

	private void addBusinessUnitId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessUnitIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID, true,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitIdConfig.setAuthorizationIncluded(true);
		businessUnitIdConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_ID);

		businessUnitIdConfig.setEnable(false);
		componentList.add(businessUnitIdConfig);
	}

	private void addBusinessUnitNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNOLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessUnitNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO, true,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNOLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitNoConfig.setAuthorizationIncluded(true);
		businessUnitNoConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NO);

		businessUnitNoConfig.setVisible(false);
		businessUnitNoConfig.setEnable(false);
		componentList.add(businessUnitNoConfig);
	}

	private void addBusinessUnitName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMELAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessUnitNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME, true,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMELAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		businessUnitNameConfig.setAuthorizationIncluded(true);
		businessUnitNameConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NAME);

		businessUnitNameConfig.setVisible(false);
		businessUnitNameConfig.setEnable(false);

		componentList.add(businessUnitNameConfig);
	}

	private void addBusinessProcess(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSPROCESSLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessProcessConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSPROCESSTEXT_FIELD, true,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSPROCESSLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		businessProcessConfig.setAuthorizationIncluded(true);
		businessProcessConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.BUSINESSPROCESS_NAME);

		businessProcessConfig.setEnable(false);

		componentList.add(businessProcessConfig);
	}

	private void addWorkflowId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWIDLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig workflowIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID_POPUP, true,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWIDLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		workflowIdConfig.setAuthorizationIncluded(true);
		workflowIdConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID_NAME);

		workflowIdConfig.setEnable(false);

		componentList.add(workflowIdConfig);
	}

	private void addWorkflowName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAMELAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig workflowNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAME_POPUP, true,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAMELAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		workflowNameConfig.setAuthorizationIncluded(true);
		workflowNameConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWCOMPONENT_NAME);

		workflowNameConfig.setEnable(false);
		componentList.add(workflowNameConfig);
	}

	private void addWorkflowDesc(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCLAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig workflowDescConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESC, true,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCLAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		workflowDescConfig.setAuthorizationIncluded(true);
		workflowDescConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESC);

		workflowDescConfig.setVisible(false);
		workflowDescConfig.setEnable(false);

		componentList.add(workflowDescConfig);
	}

	private void addWorkflowDesc1(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCONELAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig workflowDesc1Config = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCONE, true,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCONELAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		workflowDesc1Config.setAuthorizationIncluded(true);
		workflowDesc1Config.setComponentName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESCONE);

		workflowDesc1Config.setVisible(false);
		workflowDesc1Config.setEnable(false);

		componentList.add(workflowDesc1Config);
	}

	private void addParentTableLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.PARENTRESULT_LAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
	}

	private void addHistoryDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.HISTORY_DETAILS);
		panelConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYRESULT_PANEL);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.PARENTRESULT_LAYOUT);
		componentList.add(panelConfig);
		addHistoryResultLayout(componentList);
	}

	private void addAttachmentDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENT_DETAILS);
		panelConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTRESULT_PANEL);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.PARENTRESULT_LAYOUT);
		componentList.add(panelConfig);
		addAttachmentResultLayout(componentList);
	}

	private void addHistoryResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.HISTORYDETAILSRESULT_LAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYRESULT_PANEL);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addPagedHistoryTableComponent(componentList);
	}

	private void addAttachmentResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTDETAILSRESULT_LAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTRESULT_PANEL);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addPagedAttachmentTableComponent(componentList);
	}

	private void addPagedHistoryTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig historysearchResultConfig = new GtnUIFrameworkComponentConfig();
		historysearchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		historysearchResultConfig.setAuthorizationIncluded(true);
		historysearchResultConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHRESULT_TABLE);
		historysearchResultConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.HISTORY_DETAILS);
		historysearchResultConfig
				.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYDETAILSRESULT_LAYOUT);
		historysearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		historysearchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		historysearchResultConfig.setAddToParent(true);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		historysearchResultConfig.setComponentStyle(tableStyleList);

		componentList.add(historysearchResultConfig);

		GtnUIFrameworkPagedTableConfig historysearchResults = new GtnUIFrameworkPagedTableConfig();
		historysearchResults.setEditable(false);
		historysearchResults.setFilterBar(true);
		historysearchResults.setSelectable(true);
		historysearchResults
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkWorkflowInboxClassConstants.JAVA_LANG_STRING,
						GtnFrameworkWorkflowInboxClassConstants.JAVA_UTIL_DATE,
						GtnFrameworkWorkflowInboxClassConstants.JAVA_LANG_STRING,
						GtnFrameworkWorkflowInboxClassConstants.JAVA_LANG_STRING,
						GtnFrameworkWorkflowInboxClassConstants.JAVA_LANG_STRING });
		historysearchResults.setTableVisibleHeader(new String[] {
				GtnFrameworkWorkflowInboxClassConstants.STATUS_LOWERCASE,
				GtnFrameworkWorkflowInboxClassConstants.MODIFIED_DATE,
				GtnFrameworkWorkflowInboxClassConstants.MODIFIED_BY, GtnFrameworkWorkflowInboxClassConstants.NOTES,
				GtnFrameworkWorkflowInboxClassConstants.ATTACHMENT});
		historysearchResults.setExtraColumn(new Object[] { GtnFrameworkWorkflowInboxClassConstants.FILENAME });
		historysearchResults.setTableColumnMappingId(new Object[] { GtnFrameworkWorkflowInboxClassConstants.STATUS,
				GtnFrameworkWorkflowInboxClassConstants.MODIFIEDDATE,
				GtnFrameworkWorkflowInboxClassConstants.MODIFIEDBY,
				GtnFrameworkWorkflowInboxClassConstants.NOTES_LOWERCASE,
				GtnFrameworkWorkflowInboxClassConstants.ATTACHMENT_LINK });
		historysearchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		historysearchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		historysearchResults
				.setTableDateColumnObject(new String[] { GtnFrameworkWorkflowInboxClassConstants.MODIFIEDDATE });
		historysearchResults.setTableDateColumnFormat(new String[] { "MM/dd/yyyy HH:mm:ss" });
		historysearchResults.setModuleName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWINBOX);
		historysearchResults.setQueryName(GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHQUERY);
		historysearchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.WF_HISTORYBUTTON_SEARCH);
		historysearchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		historysearchResults.setDoubleClickEnable(true);

		List<GtnUIFrameWorkActionConfig> fetchList = new ArrayList<>();
		GtnUIFrameWorkActionConfig fetchActionConfig = new GtnUIFrameWorkActionConfig();
		fetchActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fetchActionConfig.addActionParameter(GtnFrameworkFetchHistorytoAttachmentAction.class.getName());
		fetchActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHRESULT_TABLE);
		fetchActionConfig.addActionParameter(true);
		fetchList.add(fetchActionConfig);
		historysearchResults.setGtnUIFrameWorkActionConfigList(fetchList);

		historysearchResultConfig.setGtnPagedTableConfig(historysearchResults);
	}

	private void addPagedAttachmentTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentId(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTSEARCHRESULT_TABLE);
		searchResultConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENT_DETAILS);
		searchResultConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTDETAILSRESULT_LAYOUT);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		searchResultConfig.setAddToParent(true);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyleList);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig wfHistoryPopupResultConfig = new GtnUIFrameworkPagedTableConfig();
		wfHistoryPopupResultConfig.setEditable(false);
		wfHistoryPopupResultConfig.setFilterBar(true);
		wfHistoryPopupResultConfig.setSelectable(true);
		wfHistoryPopupResultConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkWorkflowInboxClassConstants.JAVA_LANG_STRING,
						GtnFrameworkWorkflowInboxClassConstants.JAVA_UTIL_DATE,
						GtnFrameworkWorkflowInboxClassConstants.JAVA_LANG_STRING });
		wfHistoryPopupResultConfig
				.setTableVisibleHeader(new String[] { GtnFrameworkWorkflowInboxClassConstants.DOCUMENT_NAME,
						GtnFrameworkWorkflowInboxClassConstants.DATE_ADDED,
						GtnFrameworkWorkflowInboxClassConstants.USER_NAME });
		wfHistoryPopupResultConfig.setExtraColumn(new Object[] { GtnFrameworkWorkflowInboxClassConstants.FILETYPE });
		wfHistoryPopupResultConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkWorkflowInboxClassConstants.DOCUMENTNAME, GtnFrameworkWorkflowInboxClassConstants.DATEADDED,
				GtnFrameworkWorkflowInboxClassConstants.USERNAME });
		wfHistoryPopupResultConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		wfHistoryPopupResultConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		wfHistoryPopupResultConfig.setModuleName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWINBOX);
		wfHistoryPopupResultConfig.setQueryName(GtnFrameworkWorkflowInboxClassConstants.ATTACHEMENTSEARCHQUERY);
		wfHistoryPopupResultConfig
				.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.WF_ATTACHMENT_SEARCH);
		wfHistoryPopupResultConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		wfHistoryPopupResultConfig.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> fetchList = new ArrayList<>();
		GtnUIFrameWorkActionConfig fetchActionConfig = new GtnUIFrameWorkActionConfig();
		fetchActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fetchActionConfig.addActionParameter(GtnFrameworkDownloadattachmentAction.class.getName());
		fetchActionConfig.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.ATTACHEMENTSEARCHRESULT_TABLE);
		fetchActionConfig.addActionParameter(true);
		fetchList.add(fetchActionConfig);
		wfHistoryPopupResultConfig.setGtnUIFrameWorkActionConfigList(fetchList);

		searchResultConfig.setGtnPagedTableConfig(wfHistoryPopupResultConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYACTIONBUTTON_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addExcelButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		excelButtonConfig.setAddToParent(true);
		excelButtonConfig.setParentComponentId(GtnFrameworkWorkflowInboxClassConstants.HISTORYACTIONBUTTON_LAYOUT);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonConfig.setExportFileName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWHISTORY);
		gtnUIFrameworkExcelButtonConfig.setExportFromTable(true);
		gtnUIFrameworkExcelButtonConfig
				.setExportTableId(GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHRESULT_TABLE);
		gtnUIFrameworkExcelButtonConfig.setWriteFileInWebService(false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.HISTORYCLOSEBUTTON_LAYOUT, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYACTIONBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.HISTORYCLOSEBUTTON, true,
				GtnFrameworkWorkflowInboxClassConstants.HISTORYCLOSEBUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CLOSE);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.HISTORYLOOKUP);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkWorkflowInboxClassConstants.STATUS,
				GtnFrameworkWorkflowInboxClassConstants.MODIFIEDBY };
		String[] listNameArray = { GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUS,
				GtnFrameworkWorkflowInboxClassConstants.USERS };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig wfHisoryPopupCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			wfHisoryPopupCustomFilterConfig.setPropertId(propertyIds[i]);
			wfHisoryPopupCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig wfHisoryPopupCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			wfHisoryPopupCustomFilterComponentConfig
					.setComponentId(GtnFrameworkWorkflowInboxClassConstants.CUSTOMFILTERCOMBOBOX);
			wfHisoryPopupCustomFilterComponentConfig
					.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CUSTOMFILTERCOMBOBOX);
			wfHisoryPopupCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			wfHisoryPopupCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			wfHisoryPopupCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			wfHisoryPopupCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			wfHisoryPopupCustomFilterConfig.setGtnComponentConfig(wfHisoryPopupCustomFilterComponentConfig);
			customFilterConfigMap.put(wfHisoryPopupCustomFilterConfig.getPropertId(), wfHisoryPopupCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

}
