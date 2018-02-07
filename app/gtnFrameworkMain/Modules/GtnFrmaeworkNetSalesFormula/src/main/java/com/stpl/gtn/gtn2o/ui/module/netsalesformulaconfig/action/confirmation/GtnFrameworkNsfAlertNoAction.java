package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkNsfAlertNoAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private static final String FORMULA_TYPE = "formulaType";

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String formulaTypeValue = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(FORMULA_TYPE));
		if (!"null".equals(formulaTypeValue)) {
			GtnUIFrameworkBaseComponent formulaTypeComponenet = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(viewId + FORMULA_TYPE);
			formulaTypeComponenet.loadComboBoxComponentValue(
					Integer.parseInt(String.valueOf((GtnUIFrameworkGlobalUI.getSessionProperty(FORMULA_TYPE)))));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
