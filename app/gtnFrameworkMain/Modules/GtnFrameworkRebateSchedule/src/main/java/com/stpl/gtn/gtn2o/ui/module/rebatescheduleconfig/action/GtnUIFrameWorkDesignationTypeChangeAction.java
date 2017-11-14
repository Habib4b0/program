package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkDesignationTypeChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String rebateScheduleDesignation = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleDesignation")
				.getCaptionFromComboBox();
		if ("Child".equals(rebateScheduleDesignation)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentRebateScheduleID").setEnable(true);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentRebateScheduleID").setEnable(false);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
