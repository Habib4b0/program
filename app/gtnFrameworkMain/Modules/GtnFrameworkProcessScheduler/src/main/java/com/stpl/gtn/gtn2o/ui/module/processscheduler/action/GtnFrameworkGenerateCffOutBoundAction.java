package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;


import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkGenerateCffOutBoundAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkGenerateCffOutBoundAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		 gtnLogger.info("GtnFrameworkGenerateCffOutBoundAction started executing");
		@SuppressWarnings("unchecked")
		List<Object> customDataList = (List<Object>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MANUAL_RUN_ID).getComponentData().getCustomData();
		
		Object processSid = customDataList.get(0);
		Object processName = customDataList.get(1);
		Object schemaName = customDataList.get(2);
		
		gtnLogger.info("processSid: "+customDataList.get(0));
		gtnLogger.info("processName: "+customDataList.get(1));
		gtnLogger.info("schemaName: "+customDataList.get(2));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return null;
	}

}
