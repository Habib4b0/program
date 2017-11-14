package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCfpCompanyAdditionSearchDdlbValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String cfpCompanyAdditionSearchValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getCaptionFromComboBox();
		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(0))
				.loadFieldValue(cfpCompanyAdditionSearchValue);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
