package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkContractHeaderPopupTableItemClickAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkContractHeaderPopupTableItemClickAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			String selectButtonId = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(selectButtonId)
					.setComponentEnable(gtnUIFrameWorkActionConfig.getActionParameterList().get(2) != null);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
