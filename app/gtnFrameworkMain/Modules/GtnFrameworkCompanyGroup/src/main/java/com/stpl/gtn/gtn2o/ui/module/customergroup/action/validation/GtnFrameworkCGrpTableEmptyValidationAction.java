package com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCGrpTableEmptyValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String tableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

		GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(tableId, componentId).getLogicFromPagedDataTable();
		if (cfpCaTabRightTablelogic.getCount() > 0) {
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig();
			notificationConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			Object searchMsg = " Search Completed ";
			notificationConfig
					.setActionParameterList(Arrays.asList(searchMsg, GtnFrameworkCommonStringConstants.STRING_EMPTY));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);
		} else {
			GtnUIFrameWorkActionConfig noResultsAlertActionConfig = new GtnUIFrameWorkActionConfig();
			noResultsAlertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);

			List<Object> noResultsAlertParamsList = new ArrayList<>();
			noResultsAlertParamsList.add("Information");
			noResultsAlertParamsList.add(gtnUIFrameWorkActionConfig.getActionParameterList().get(2));
			noResultsAlertActionConfig.setActionParameterList(noResultsAlertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, noResultsAlertActionConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
