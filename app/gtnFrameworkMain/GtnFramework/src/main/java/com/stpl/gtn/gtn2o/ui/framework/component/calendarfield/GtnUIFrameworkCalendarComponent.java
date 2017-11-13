/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.calendarfield;

import org.asi.calendarfield.CalendarField;
import org.asi.calendarfield.WeekDay;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkCalendarComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCalendarComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkCalendarComponent");

		GtnUIFrameworkCalendarConfig calendarConfig = componentConfig.getCalendarConfig();
		CalendarField calendarField = new CalendarField();
		calendarField.setVisible(componentConfig.isVisible());
		calendarField.setEnabled(componentConfig.isEnable());
		calendarField.setRangeStart(calendarConfig.getRangeStartDate().getTime());
		calendarField.setRangeEnd(calendarConfig.getRangeEndDate().getTime());
		calendarField.setImmediate(calendarConfig.isImmediate());
		calendarField.setYearResolution(calendarConfig.isYearResolution());
		calendarField.setMatrix(calendarConfig.getDisplayMatrix());
		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkCalendarComponent");
		return calendarField;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		CalendarField calendarField = (CalendarField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		calendarField.setVisible(componentConfig.isVisible());
		calendarField.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkCalendarConfig calendarConfig = componentConfig.getCalendarConfig();
		calendarField.setRangeStart(null);
		calendarField.setRangeEnd(null);
		calendarField.setRangeStart(calendarConfig.getRangeStartDate().getTime());
		calendarField.setRangeEnd(calendarConfig.getRangeEndDate().getTime());
		calendarField.setImmediate(calendarConfig.isImmediate());
		calendarField.setYearResolution(calendarConfig.isYearResolution());
		calendarField.setMatrix(calendarConfig.getDisplayMatrix());
		calendarField.clearAllValue();
	}

	public void setSelectedWeekDays(CalendarField calendarField, int[] days) {
		if (days != null && days.length > 0) {
			WeekDay[] tempWeakDays = new WeekDay[days.length];
			for (int i = 0; i < days.length; i++) {
				tempWeakDays[i] = WeekDay.getWeekDay(days[i]);
			}
			calendarField.setSelectedWeekDays(tempWeakDays);
		} else {
			calendarField.setSelectedWeekDays();
		}
	}
}
