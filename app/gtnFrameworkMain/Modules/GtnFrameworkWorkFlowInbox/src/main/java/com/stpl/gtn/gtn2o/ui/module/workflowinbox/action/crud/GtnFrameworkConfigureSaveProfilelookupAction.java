package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.crud;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkConfigureSaveProfilelookupAction

		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String publicViewName = getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId)
				.getStringFromField();
		String privateViewName = getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId)
				.getStringFromField();
		if ((publicViewName != null && !publicViewName.isEmpty())
				|| (privateViewName != null && !privateViewName.isEmpty())) {
			getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId).setComponentEnable(true);
			getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(3), componentId).setComponentEnable(false);

			if (publicViewName != null && !publicViewName.isEmpty()) {
				getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(4), componentId)
						.loadFieldValue(publicViewName);
				getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(5), componentId)
						.loadFieldValue(gtnUIFrameWorkActionConfig.getFieldValues().get(6));
			}
			if (!privateViewName.isEmpty()) {
				getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(4), componentId)
						.loadFieldValue(privateViewName);
				getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(5), componentId)
						.loadFieldValue(gtnUIFrameWorkActionConfig.getFieldValues().get(7));
			}
		} else {
			getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId).setComponentEnable(false);
			getBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(3), componentId).setComponentEnable(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;

	}

	GtnUIFrameworkBaseComponent getBaseComponent(String componentId, String parentComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, parentComponentId);
	}
}
