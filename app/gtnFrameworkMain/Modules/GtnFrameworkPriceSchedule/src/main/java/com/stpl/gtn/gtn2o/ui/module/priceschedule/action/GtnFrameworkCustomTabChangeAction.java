package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceProtectionValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceTabValueChangeManager;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCustomTabChangeAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
		if(position==2||position==3)
		{
			GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
			GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
			return;
		}
		GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
