package com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpClassContants;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCGrpDeleteValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpsearchResultTable")
				.getValueFromPagedDataTable();
		if (gtnWsRecordBean == null) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
			List<Object> alertParamsList = new ArrayList<>();
			alertParamsList.add("cGrpsearchResultTable");
			alertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_VIEW_ERROR);
			alertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_DELETE_ERROR);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
		} else {
			String customerGrpName = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(0));
			List<GtnUIFrameWorkActionConfig> customerGrpOnSucessActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig customerGrpConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
			customerGrpConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			List<Object> confirmParamsList = new ArrayList<>();
			confirmParamsList.add(GtnFrameworkCGrpStringContants.GTN_CUSTOMER_GRP_CONFIRMATION_HEADER);
			confirmParamsList.add(
					GtnFrameworkCGrpStringContants.GTN_CUSTOMER_GRP_CONFIRMATION_MSG + " " + customerGrpName + " ?");
			GtnUIFrameWorkActionConfig customerGrpDeleteActionConfig = new GtnUIFrameWorkActionConfig();
			customerGrpDeleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customerGrpDeleteActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_DELETE_ACTION);
			customerGrpOnSucessActionConfigList.add(customerGrpDeleteActionConfig);

			GtnUIFrameWorkActionConfig customerGrpavigationActionConfig = new GtnUIFrameWorkActionConfig();
			customerGrpavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
			customerGrpavigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			customerGrpOnSucessActionConfigList.add(customerGrpavigationActionConfig);

			GtnUIFrameWorkActionConfig customerGrpNotificationAction = new GtnUIFrameWorkActionConfig();
			customerGrpNotificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			String message = customerGrpName + " has been deleted successfully";
			customerGrpNotificationAction.addActionParameter(message);
			customerGrpNotificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			customerGrpOnSucessActionConfigList.add(customerGrpNotificationAction);

			confirmParamsList.add(customerGrpOnSucessActionConfigList);
			customerGrpConfirmationActionConfig.setActionParameterList(confirmParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, customerGrpConfirmationActionConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
