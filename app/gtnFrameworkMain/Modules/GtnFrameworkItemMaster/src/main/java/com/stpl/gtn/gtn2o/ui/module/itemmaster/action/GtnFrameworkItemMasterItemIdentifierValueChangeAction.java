package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkItemMasterItemIdentifierValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkItemMasterItemIdentifierValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkItemMasterItemIdentifierValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			Integer value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString())
					.getIntegerFromField();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString()).setComponentEnable(value != 0);
		} catch (Exception e) {
			logger.error("in GtnFrameworkItemMasterItemIdentifierValueChangeAction ", e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
