/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCopyAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCopyAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		try {
			GtnUIFrameworkBaseComponent viewBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(1).toString());
			GtnUIFrameworkComponentData customData = viewBaseComponent.getComponentData();
			List<Object> customDataList = customData.getCustomDataList();
			if (customDataList != null && !customDataList.isEmpty()) {
				customData.addCustomDataList("copy");
				GtnWsRecordBean tableBean = (GtnWsRecordBean) customDataList.get(0);
				tableBean.getProperties().set(0, GtnFrameworkCommonStringConstants.STRING_EMPTY);
				tableBean.getProperties().set(1, GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}
			viewBaseComponent.getComponentData().setCustomDataList(customDataList);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkCopyAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
