/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import java.util.Calendar;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.calendarfield.GtnUIFrameworkCalendarConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCurdYearChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCurdYearChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> parameters = gtnUIFrameWorkActionConfig.getFieldValues();
		try {
			GtnUIFrameworkBaseComponent yearBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			int year = yearBaseComponent.getIntegerFromField();
			if (year == 0) {
				return;
			}
			GtnUIFrameworkBaseComponent calendarBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(1));
			calendarBaseComponent.clearAllCalendarValue();
			Calendar rangeStartDate = Calendar.getInstance();
			Calendar rangeEndDate = Calendar.getInstance();
			rangeStartDate.set(year, 0, 1, 0, 0, 0);
			rangeEndDate.set(year, 11, 31, 0, 0, 0);
			calendarBaseComponent.setCalendarFieldRangeStart(null);
			calendarBaseComponent.setCalendarFieldRangeEnd(null);
			calendarBaseComponent.setCalendarFieldRangeStart(rangeStartDate.getTime());
			calendarBaseComponent.setCalendarFieldRangeEnd(rangeEndDate.getTime());
			GtnUIFrameworkCalendarConfig gtnUIFrameworkCalendarConfig = calendarBaseComponent.getComponentConfig()
					.getCalendarConfig();
			gtnUIFrameworkCalendarConfig.setRangeStartDate(rangeStartDate);
			gtnUIFrameworkCalendarConfig.setRangeEndDate(rangeEndDate);
			GtnUIFrameworkBaseComponent defaultHolidayBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(0));
			defaultHolidayBaseComponent.setPropertyValue(false);

		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkCurdYearChangeAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
