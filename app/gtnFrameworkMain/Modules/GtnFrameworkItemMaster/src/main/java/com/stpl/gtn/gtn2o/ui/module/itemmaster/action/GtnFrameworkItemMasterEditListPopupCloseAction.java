package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkItemMasterEditListPopupCloseAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterEditListPopupCloseAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("In Edit List CLose Action");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			String qualifierComponentId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(6);
			GtnUIFrameworkGlobalUI.getVaadinComponentData(qualifierComponentId).getCurrentComponentConfig()
					.getComponentType().getGtnComponent()
					.reloadComponent(null, qualifierComponentId, componentId, Arrays.asList("simpleReload"));
		} catch (Exception e) {
			logger.error("in GtnFrameworkItemMasterEditListPopupCloseAction ", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
