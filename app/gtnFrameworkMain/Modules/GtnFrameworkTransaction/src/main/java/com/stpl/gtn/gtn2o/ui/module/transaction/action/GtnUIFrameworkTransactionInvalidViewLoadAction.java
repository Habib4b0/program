package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentListForInvalidBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkTransactionInvalidViewLoadAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> values=gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkTransactionComponentListForInvalidBean bean = (GtnUIFrameworkTransactionComponentListForInvalidBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(values.get(0)).getComponentData().getCustomData();
		GtnUIFrameworkGlobalUI.addChildComponent(values.get(1), bean.getViewComponentList());
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
