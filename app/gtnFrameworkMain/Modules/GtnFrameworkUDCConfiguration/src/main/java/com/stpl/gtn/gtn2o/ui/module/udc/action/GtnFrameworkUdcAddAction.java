package com.stpl.gtn.gtn2o.ui.module.udc.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkUdcAddAction implements GtnUIFrameWorkAction,GtnUIFrameworkActionShareable,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkUdcSearchAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into UdcSaveAction doAction");
		String udcCategory = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY).getCaptionFromComboBox();
		System.out.println("udc category is" +udcCategory);
		String udcValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_VALUE).getStringFromField();
		System.out.println("udc value is" +udcValue);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
