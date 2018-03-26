/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deepika.KrishnaKumar
 */
public class GtnFrameworkRSFieldFactoryPopupDetailAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean beanDto = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE)
				.getValueFromPagedDataTable();

		if (beanDto == null) {
			GtnUIFrameWorkActionConfig alertActionPopupConfig = new GtnUIFrameWorkActionConfig();
			alertActionPopupConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

			List<Object> alertPopupMsgParamsList = new ArrayList<>();
			alertPopupMsgParamsList.add("Select Error");
			alertPopupMsgParamsList.add("Please select a row from the Results list view to proceed");
			alertActionPopupConfig.setActionParameterList(alertPopupMsgParamsList);

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionPopupConfig);

			throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
