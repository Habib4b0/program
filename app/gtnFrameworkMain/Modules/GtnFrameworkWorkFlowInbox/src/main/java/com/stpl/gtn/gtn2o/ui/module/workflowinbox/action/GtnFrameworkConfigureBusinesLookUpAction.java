package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.ArrayList;
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

public class GtnFrameworkConfigureBusinesLookUpAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String viewType = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();
		Object[] visibleColumnprivateLookup = null;
		String[] visibleHeaderprivateLookup = null;
		Class<?>[] dataTypeprivateLookup = null;
		Object[] extraColumn = null;
		Class<?>[] extraColumndataType = null;
		GtnWsSearchQueryConfigLoaderType searchQueryConfigLoaderTypeprivateLookup = null;

		List<GtnUIFrameworkComponentConfig> componentList;

		String combocomponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS)
				.getCaptionFromComboBox();

		if (combocomponent.equals(GtnFrameworkCommonStringConstants.CONTRACT_MANAGEMENT)
				|| combocomponent.equals(GtnFrameworkCommonStringConstants.FORECASTING)
				|| combocomponent.equals(GtnFrameworkCommonStringConstants.ACCRUAL_RATE_PROJECTION)) {
			componentList = new ArrayList<>();
			dataTypeprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotArmSearchTableColumnsDataType();
			visibleHeaderprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotArmSearchTableHeaders();
			visibleColumnprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotArmSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxPrivateNotarmSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotarmSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderTypeprivateLookup = GtnWsSearchQueryConfigLoaderType.WF_PRIVATE_VIEW_SEARCH;
		}

		else if (combocomponent.equals(GtnFrameworkCommonStringConstants.RETURNS)) {
			componentList = new ArrayList<>();
			dataTypeprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotArmSearchTableColumnsDataType();
			visibleHeaderprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotArmSearchTableHeaders();
			visibleColumnprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateNotArmSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxPrivateReturnsSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateReturnsSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderTypeprivateLookup = GtnWsSearchQueryConfigLoaderType.WF_PRIVATE_VIEW_SEARCH;
		}

		else {
			componentList = new ArrayList<>();
			dataTypeprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateArmSearchTableColumnsDataType();
			visibleHeaderprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateArmSearchTableHeaders();
			visibleColumnprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateArmSearchTableColumns();
			extraColumn = GtnFrameworkWorkflowInboxTableConstants.getGtnWorkflowInboxPrivateArmSearchExtraColumns();
			extraColumndataType = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxPrivateArmSearchTableExtracolumnsDataType();
			searchQueryConfigLoaderTypeprivateLookup = GtnWsSearchQueryConfigLoaderType.WF_PRIVATE_VIEW_SEARCH;

		}

		GtnUIFrameworkComponentConfig lookupSearchResultConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE)
				.getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig lookupSearchResults = lookupSearchResultConfig.getGtnPagedTableConfig();
		lookupSearchResults.setTableColumnDataType(dataTypeprivateLookup);
		lookupSearchResults.setTableVisibleHeader(visibleHeaderprivateLookup);
		lookupSearchResults.setTableColumnMappingId(visibleColumnprivateLookup);
		lookupSearchResults.setExtraColumn(extraColumn);
		lookupSearchResults.setExtraColumnDataType(extraColumndataType);
		lookupSearchResults.setSearchQueryConfigLoaderType(searchQueryConfigLoaderTypeprivateLookup);
		componentList.add(lookupSearchResultConfig);

		GtnUIFrameworkGlobalUI.addChildComponent(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWRESULT_LAYOUT,
				componentList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
