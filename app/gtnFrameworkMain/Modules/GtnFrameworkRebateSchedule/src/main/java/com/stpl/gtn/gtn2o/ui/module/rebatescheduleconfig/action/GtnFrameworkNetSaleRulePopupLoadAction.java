package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkNetSaleRulePopupLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkNetSaleRulePopupLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	/*
	 * Param 0 - Custom Action Class Name , Param 1 - field Id to apply value,
	 * Param 2 - View Id of Popup
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkComponentData viewComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(parameters.get(2).toString());
			Object sharedPopupData = viewComponentData.getSharedPopupData();
			if (sharedPopupData != null) {
				List<Object> sharedPopupDataList = (List<Object>) sharedPopupData;
				if (sharedPopupDataList.size() > 4 && sharedPopupDataList.get(4) != null) {
					GtnUIFrameworkBaseComponent fieldBaseComponent = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(parameters.get(1).toString());
					fieldBaseComponent.getComponentConfig().getGtnComboboxConfig().setHasDefaultValue(true);
					fieldBaseComponent.getComponentConfig().getGtnComboboxConfig()
							.setDefaultDesc(sharedPopupDataList.get(4).toString());
					fieldBaseComponent.getComponentConfig().getComponentType().getGtnComponent()
							.resetToDefault(parameters.get(1).toString(), fieldBaseComponent.getComponentConfig());
					fieldBaseComponent.setEnable(false);
				}
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkNSRPopupLoadAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
