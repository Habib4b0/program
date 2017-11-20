package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCompanyMasterEditListPopupCloseAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterEditListPopupCloseAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("In Edit List CLose Action");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int qualifierIdFromList = GtnWsNumericConstants.SIX;
		try {
			String qualifierComponentId = (String) gtnUIFrameWorkActionConfig.getActionParameterList()
					.get(qualifierIdFromList);
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
