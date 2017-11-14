package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnFrameworkItemMasterLandingScreenValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		Integer itemQualifier = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("IMasterSearchQualifierName")
				.getIntegerFromField();
		String itemIdentifier = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("IMasterSearchItemIdentifier")
				.getStringFromField();
		if (itemQualifier != null && itemQualifier != 0 && itemIdentifier.isEmpty()) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_LANDING_SCREEN_VALIDATION_MSG;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
