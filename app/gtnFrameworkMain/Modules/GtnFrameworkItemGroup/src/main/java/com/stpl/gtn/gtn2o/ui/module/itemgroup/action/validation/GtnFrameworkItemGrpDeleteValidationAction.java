package com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkItemGrpDeleteValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		/**
		 * 
		 */
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE)
				.getValueFromPagedDataTable();
		if (gtnWsRecordBean == null) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
			List<Object> alertParamsList = new ArrayList<>();
			alertParamsList.add(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
			alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_ERROR_HEADER);
			alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_ALERT_MSG);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		} else {
			String itemGrpName = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(0));
			List<GtnUIFrameWorkActionConfig> itemGrpOnSucessActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig itemGrpConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
			itemGrpConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			List<Object> confirmParamsList = new ArrayList<>();
			confirmParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_CONFIRMATION_HEADER);
			confirmParamsList
					.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_CONFIRMATION_MSG + " " + itemGrpName + " ?");
			GtnUIFrameWorkActionConfig itemGrpDeleteActionConfig = new GtnUIFrameWorkActionConfig();
			itemGrpDeleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			itemGrpDeleteActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_DELETE_ACTION);
			itemGrpOnSucessActionConfigList.add(itemGrpDeleteActionConfig);

			GtnUIFrameWorkActionConfig itemGrpNavigationActionConfig = new GtnUIFrameWorkActionConfig();
			itemGrpNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
			itemGrpNavigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			itemGrpOnSucessActionConfigList.add(itemGrpNavigationActionConfig);

			GtnUIFrameWorkActionConfig itemGrpNotificationAction = new GtnUIFrameWorkActionConfig();
			itemGrpNotificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			String message = itemGrpName + " has been deleted successfully";
			itemGrpNotificationAction.addActionParameter(message);
			itemGrpNotificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			itemGrpOnSucessActionConfigList.add(itemGrpNotificationAction);

			confirmParamsList.add(itemGrpOnSucessActionConfigList);
			itemGrpConfirmationActionConfig.setActionParameterList(confirmParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, itemGrpConfirmationActionConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}
}
