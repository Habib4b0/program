package com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation;

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

public class GtnFrameworkItemGroupTableEmptyValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String searchTableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

		GtnUIFrameworkPagedTableLogic itemGrpSearchTablelogic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(searchTableId, componentId).getLogicFromPagedDataTable();
		if (itemGrpSearchTablelogic.getCount() > 0) {
			GtnUIFrameWorkActionConfig searchNotificationMsgConfig = new GtnUIFrameWorkActionConfig();
			searchNotificationMsgConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			searchNotificationMsgConfig.setActionParameterList(Arrays
					.asList(new Object[] { " Search Completed ", GtnFrameworkCommonStringConstants.STRING_EMPTY }));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, searchNotificationMsgConfig);
		} else {
			GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
			searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);

			List<Object> searchActionParamsList = new ArrayList<>();
			searchActionParamsList.add("Information");
			searchActionParamsList.add(gtnUIFrameWorkActionConfig.getActionParameterList().get(2));
			searchAlertActionConfig.setActionParameterList(searchActionParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, searchAlertActionConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
