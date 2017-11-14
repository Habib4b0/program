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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkConfigureApprovedByAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Object[] visibleColumnprivateLookup = null;
		String[] visibleHeaderprivateLookup = null;
		Class<?>[] dataTypeprivateLookup = null;
		List<GtnUIFrameworkComponentConfig> componentList;

		String combocomponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS)
				.getCaptionFromComboBox();

		@SuppressWarnings("rawtypes")
		List createdByColumns = Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.USERNAME_LAYOUT,
				GtnFrameworkWorkflowInboxClassConstants.USERNAME_ID,
				GtnFrameworkWorkflowInboxClassConstants.FIRSTNAME_ID,
				GtnFrameworkWorkflowInboxClassConstants.LASTNAME_ID,
				GtnFrameworkWorkflowInboxClassConstants.SEARCHRESULT_TABLE,
				GtnFrameworkWorkflowInboxClassConstants.RESULT_LAYOUT);
		String viewType = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();
		if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM)) {
			componentList = new ArrayList<>();
			dataTypeprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxCreatedByArmSearchTableColumnsDataType();
			visibleHeaderprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxCreatedByArmSearchTableHeaders();
			visibleColumnprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxCreatedByArmSearchTableColumns();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(0)))
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(1)))
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(2)))
					.setCaption(GtnFrameworkWorkflowInboxClassConstants.USER_FIRST_NAME);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(3)))
					.setCaption(GtnFrameworkWorkflowInboxClassConstants.USER_LAST_NAME);
		} else {
			componentList = new ArrayList<>();
			dataTypeprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxCreatedByNotArmSearchTableColumnsDataType();
			visibleHeaderprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxCreatedByNotArmSearchTableHeaders();
			visibleColumnprivateLookup = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxCreatedByNotArmSearchTableColumns();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(0)))
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(2)))
					.setCaption(GtnFrameworkWorkflowInboxClassConstants.FIRST_NAME);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewType + String.valueOf(createdByColumns.get(3)))
					.setCaption(GtnFrameworkWorkflowInboxClassConstants.LAST_NAME);
		}
		GtnUIFrameworkComponentConfig searchResultConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(String.valueOf(viewType + createdByColumns.get(4))).getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig searchResults = searchResultConfig.getGtnPagedTableConfig();
		searchResults.setTableColumnDataType(dataTypeprivateLookup);
		searchResults.setTableVisibleHeader(visibleHeaderprivateLookup);
		searchResults.setTableColumnMappingId(visibleColumnprivateLookup);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
		componentList.add(searchResultConfig);
		GtnUIFrameworkGlobalUI.addChildComponent(viewType + String.valueOf(createdByColumns.get(5)), componentList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
