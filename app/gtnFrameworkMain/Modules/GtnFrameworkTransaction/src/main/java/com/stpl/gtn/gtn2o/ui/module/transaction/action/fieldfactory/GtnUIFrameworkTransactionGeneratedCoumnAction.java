package com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkTransactionGeneratedCoumnAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		GtnWsRecordBean recordBean = actionParam.getItemId();
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.setCaption(recordBean.getPropertyValue(actionParametersList.get(1).toString()).toString());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
