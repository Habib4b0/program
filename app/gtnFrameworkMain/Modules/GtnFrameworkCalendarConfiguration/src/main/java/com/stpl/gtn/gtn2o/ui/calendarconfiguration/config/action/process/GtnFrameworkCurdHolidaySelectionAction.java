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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCurdHolidaySelectionAction implements GtnUIFrameWorkAction  ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCurdHolidaySelectionAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		gtnLogger.info("Inside holidays selection action");
		
		List<String> parameters = gtnUIFrameWorkActionConfig.getFieldValues();
		try {
			GtnUIFrameworkBaseComponent defaultHolidayBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId);
			String holiday = defaultHolidayBaseComponent.getStringFromField();
			
			GtnUIFrameworkBaseComponent calendarBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(0));
			
			if (Boolean.valueOf(holiday)) {
				calendarBaseComponent.setSelectedWeekDays(0, 6);
				return;
			}
			
			calendarBaseComponent.setSelectedWeekDays();
			
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkCurdHolidaySelectionAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
