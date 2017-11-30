package com.stpl.gtn.gtn2o.ui.module.udc.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;

public class GtnFrameworkUdcSearchAction implements GtnUIFrameWorkAction,GtnUIFrameworkActionShareable,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkUdcSearchAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into GtnFrameworkUdcSearchAction doAction");
		System.out.println("****GtnFrameworkUdcSearchAction*****");
		String udcCategory = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getCaptionFromComboBox();
		System.out.println("***Category value***" +udcCategory);
		GtnWsUdcInfoBean udcInfoBean = new GtnWsUdcInfoBean();
		udcInfoBean.setUdcCategory(udcCategory);
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
