package com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;

public class GtnUIFrameworkTransactionRefreshBeanAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(params.get(1).toString()).getComponentData()
				.setSharedPopupData(new GtnWSTransactionTableCheckAllBean());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(params.get(2).toString()).setPagedTableHeaderCheckBox(false,
				params.get(3).toString());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
