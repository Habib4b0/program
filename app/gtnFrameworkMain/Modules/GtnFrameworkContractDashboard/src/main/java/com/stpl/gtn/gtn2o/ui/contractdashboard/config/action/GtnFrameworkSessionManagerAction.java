package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;

public class GtnFrameworkSessionManagerAction {
	private GtnFrameworkSessionManagerAction() {

	}

	public static GtnWsContractDashboardSessionBean getDashboardSessionBean(String componentId) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);
		String viewId = componentData.getCurrentViewConfig().getViewId();
		GtnUIFrameworkComponentData viewComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(viewId);
		return (GtnWsContractDashboardSessionBean) viewComponentData.getSharedPopupData();
	}
}
