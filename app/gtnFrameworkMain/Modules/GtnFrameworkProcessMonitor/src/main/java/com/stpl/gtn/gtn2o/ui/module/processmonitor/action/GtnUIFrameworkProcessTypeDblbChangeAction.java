package com.stpl.gtn.gtn2o.ui.module.processmonitor.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkProcessTypeDblbChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkProcessTypeDblbChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkProcessTypeDdlbChangeAction");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String processTypeddlbValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getCaptionFromComboBox();

		boolean isManual = !"Manual".equalsIgnoreCase(processTypeddlbValue);
		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(0)).setComponentEnable(isManual);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(1)).setComponentEnable(isManual);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(2)).setComponentEnable(isManual);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(3)).setComponentEnable(isManual);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(4)).setComponentEnable(isManual);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(5)).setComponentEnable(isManual);

	}
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
