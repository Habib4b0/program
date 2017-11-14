package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxTableConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkConfigureBusinessProcessDdlbAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Object[] visibleColumn = null;
		String[] visibleHeader = null;
		Class<?>[] dataType = null;
		Object[] extraColumn = null;
		Class<?>[] extraColumndataType = null;
		GtnWsSearchQueryConfigLoaderType searchQueryConfigLoaderType = null;

		List<String> inputList = null;

		List<GtnUIFrameworkComponentConfig> componentList;

		String combocomponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getCaptionFromComboBox();

		if (combocomponent.equals(GtnFrameworkCommonStringConstants.CONTRACT_MANAGEMENT)) {
			componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL)
					.setVisible(false);

			dataType = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableColumnsDataType();
			visibleHeader = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableHeaders();
			visibleColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxContractSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderType = GtnWsSearchQueryConfigLoaderType.CONTRACT_WORKFLOW_SEARCH;
			inputList = Arrays
					.asList(GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchFieldvalues());
		}

		else if (combocomponent.equals(GtnFrameworkCommonStringConstants.FORECASTING)) {
			componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL)
					.setVisible(false);
			dataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxForecastingSearchTableColumnsDataType();
			visibleHeader = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchTableHeaders();
			visibleColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxForecastingSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderType = GtnWsSearchQueryConfigLoaderType.FORECASTING_WORKFLOW_SEARCH;
			inputList = Arrays
					.asList(GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxForecastingSearchFieldvalues());

		}

		else if (combocomponent.equals(GtnFrameworkCommonStringConstants.ACCRUAL_RATE_PROJECTION)) {
			componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL)
					.setVisible(false);
			dataType = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArpSearchTableColumnsDataType();
			visibleHeader = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArpSearchTableHeaders();
			visibleColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArpSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArpSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxArpSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderType = GtnWsSearchQueryConfigLoaderType.ARP_WORKFLOW_SEARCH;
			inputList = Arrays
					.asList(GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArpSearchFieldvalues());
		}

		else if (combocomponent.equals(GtnFrameworkCommonStringConstants.RETURNS)) {
			componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL)
					.setVisible(false);
			dataType = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxReturnsSearchTableColumnsDataType();
			visibleHeader = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxReturnsSearchTableHeaders();
			visibleColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxReturnsSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxReturnsSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxReturnsSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderType = GtnWsSearchQueryConfigLoaderType.RETURNS_WORKFLOW_SEARCH;
			inputList = Arrays
					.asList(GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxReturnsSearchFieldvalues());

		}

		else if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM)) {
			componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL)
					.setVisible(false);
			dataType = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArmSearchTableColumnsDataType();
			visibleHeader = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArmSearchTableHeaders();
			visibleColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArmSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArmSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxArmSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderType = GtnWsSearchQueryConfigLoaderType.ARM_WORKFLOW_SEARCH;
			inputList = Arrays
					.asList(GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxArmSearchFieldvalues());

		} else {
			componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_ARM_PANEL_INNERLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_COMPANY_BUSINESS_PANEL_INNERLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ARMDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.RETURNDETAILSEARCHPANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTINGDETAILSEARCHPANEL)
					.setVisible(false);
			dataType = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableColumnsDataType();
			visibleHeader = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableHeaders();
			visibleColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxContractSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderType = GtnWsSearchQueryConfigLoaderType.CONTRACT_WORKFLOW_SEARCH;
			inputList = Arrays
					.asList(GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxContractSearchFieldvalues());

		}
		GtnUIFrameworkComponentConfig searchResultConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE)
				.getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig searchResults = searchResultConfig.getGtnPagedTableConfig();
		searchResults.setTableColumnDataType(dataType);
		searchResults.setTableVisibleHeader(visibleHeader);
		searchResults.setTableColumnMappingId(visibleColumn);
		searchResults.setExtraColumn(extraColumn);
		searchResults.setExtraColumnDataType(extraColumndataType);
		searchResults.setSearchQueryConfigLoaderType(searchQueryConfigLoaderType);
		componentList.add(searchResultConfig);

		GtnUIFrameworkGlobalUI.addChildComponent(GtnFrameworkWorkflowInboxClassConstants.RESULTMAINPANEL_INNERLAYOUT,
				componentList);
		GtnUIFrameworkComponentConfig searchbtnConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkWorkflowInboxClassConstants.SEARCH_BTN).getCurrentComponentConfig();
		searchbtnConfig.getGtnUIFrameWorkActionConfigList().get(0).setFieldValues(inputList);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
