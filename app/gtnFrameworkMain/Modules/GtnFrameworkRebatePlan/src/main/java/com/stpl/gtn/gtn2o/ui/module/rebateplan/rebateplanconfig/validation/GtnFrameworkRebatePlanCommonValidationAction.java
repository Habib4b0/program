package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnFrameworkRebatePlanCommonValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<String> fielValueList = gtnUIFrameWorkActionConfig.getFieldValues();
		String componentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fielValueList.get(0)).getStringFromField();

		if (!componentData.isEmpty() && !getOnlyNumber(componentData)) {
			String msg = "Please enter only numbers with two decimals places in " + fielValueList.get(2);
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

	private boolean getOnlyNumber(String data) {
		return data.matches(GtnFrameworkRegexStringConstants.NUMERIC_DECIAL_DOUBLE_PRECISION_FORMAT);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkRebatePlanCommonValidationAction();
	}

}
