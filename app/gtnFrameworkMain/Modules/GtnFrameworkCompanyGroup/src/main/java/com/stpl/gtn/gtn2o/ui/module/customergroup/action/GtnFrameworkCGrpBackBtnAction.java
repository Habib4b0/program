/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Deepika.KrishnaKumar
 */
public class GtnFrameworkCGrpBackBtnAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpBackBtnAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkCompanyGrpSBackBtnAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionConfigList = new ArrayList<>(
				gtnUIFrameWorkActionConfig.getActionParameterList().subList(1, 4));

		GtnUIFrameWorkActionConfig conformationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		actionConfigList.set(1,
				((String) actionConfigList.get(1)).replace("<record name>",
						GtnUIFrameworkGlobalUI
								.getVaadinBaseComponent(GtnFrameworkCGrpStringContants.C_GRP_INFORMATION_C_GRP_NAME)
								.getStringFromField()));
		conformationAction.setActionParameterList(actionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, conformationAction);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
