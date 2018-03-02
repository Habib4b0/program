package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkFilterBarInvisibleAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) parameters.get(1);
		Map<String, Boolean> filterInvisibleMap = (Map<String, Boolean>) parameters.get(2);

		for (Entry<String, Boolean> object : filterInvisibleMap.entrySet()) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).setFilterFieldVisible(object.getKey(),
					object.getValue());
		}
		checkCopyMode(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void checkCopyMode(String componentId) {
		if (componentId.toLowerCase(Locale.ENGLISH).contains(("COPY").toLowerCase(Locale.ENGLISH))) {
			GtnUIFrameworkGlobalUI.addSessionProperty("mode", "COPY");
			GtnUIFrameworkGlobalUI.addSessionProperty("systemId", null);
		}
	}
}
