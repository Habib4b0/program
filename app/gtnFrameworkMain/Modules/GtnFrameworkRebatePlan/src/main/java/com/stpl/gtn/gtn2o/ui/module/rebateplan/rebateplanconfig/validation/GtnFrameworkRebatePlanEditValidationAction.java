/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author deepika.krishnakumar
 */
public class GtnFrameworkRebatePlanEditValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig editValidationActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkRebatePlanEditValidationAction.class);

		List<Object> actionParamList = editValidationActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);

		Object itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		GtnWsRecordBean selectedRecord = (GtnWsRecordBean) itemId;
		gtnLogger.info("selectedRecord------------->>>>" + selectedRecord.getProperties());
		for (int i = 0; i < selectedRecord.getProperties().size(); i++) {
			gtnLogger.info("record=----------" + selectedRecord.getPropertyValueByIndex(i));

		}
		gtnLogger.info("selectedRecord ---------------------"
				+ selectedRecord.getPropertyValue("recordLockStatus").equals(true));

		if (String.valueOf(selectedRecord.getPropertyValue("recordLockStatus")).equals("true")) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			Object alertMsg = GtnFrameworkCommonConstants.RP_ALERT_MSG_HEADER;
			alertActionConfig.setActionParameterList(Arrays.asList(alertMsg, GtnFrameworkCommonConstants.RP_ALERT_MSG));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkCommonConstants.RP_ALERT_MSG_HEADER);

		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
