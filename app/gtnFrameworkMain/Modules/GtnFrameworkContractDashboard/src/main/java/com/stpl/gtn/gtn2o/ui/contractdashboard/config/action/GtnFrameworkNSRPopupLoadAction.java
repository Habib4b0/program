package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkNSRPopupLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkNSRPopupLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	/*
	 * Param 0 - Custom Action Class Name , Param 1 - field Id to apply value, Param
	 * 2 - View Id of Popup
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkComponentData viewComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(parameters.get(2).toString());
			Object sharedNsrPopupData = viewComponentData.getSharedPopupData();
			if (sharedNsrPopupData != null) {
				List<Object> sharedPopupDataList = (List<Object>) sharedNsrPopupData;
				if (sharedPopupDataList.size() > 4 && sharedPopupDataList.get(4) != null) {
					GtnUIFrameworkBaseComponent nsrFieldBaseComponent = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(parameters.get(1).toString());
					nsrFieldBaseComponent.getComponentConfig().getGtnComboboxConfig().setHasDefaultValue(true);
					nsrFieldBaseComponent.getComponentConfig().getGtnComboboxConfig()
							.setDefaultDesc(sharedPopupDataList.get(4).toString());
					nsrFieldBaseComponent.getComponentConfig().getComponentType().getGtnComponent()
							.resetToDefault(parameters.get(1).toString(), nsrFieldBaseComponent.getComponentConfig());
					nsrFieldBaseComponent.setEnable(false);
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
