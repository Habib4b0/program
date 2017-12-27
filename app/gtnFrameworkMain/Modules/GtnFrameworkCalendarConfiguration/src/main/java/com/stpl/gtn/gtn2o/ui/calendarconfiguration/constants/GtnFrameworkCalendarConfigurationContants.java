/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.constants;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCalendarConfigurationContants {
	private GtnFrameworkCalendarConfigurationContants() {

	}

	public static final String CC_MAIN_VIEW = "CCMainView";
	public static final String CC_CRUD_VIEW = "CCCrudView";
	public static final String CC_CALENDAR_FIELD = "CCCrudView_CalendarField";
	public static final String CALENDAR_FIELD = "CalendarField";
	public static final String RESULT_TABLE = "resultTable";
	public static final String CALENDAR_SELECT_ALERT = "Please select an Existing Calendar from the Results list view";
	public static final String CALENDAR_NAME_EXISTS = "Calendar Name already exists.";
	private static final String[] CALENDAR_CONF_COLUMN = { "calendarName", "calendarDescription", "calendarYear",
			"createdBy", "createdDate", "modifiedBy", "modifiedDate" };
	private static final String[] CALENDAR_CONF_HEADER = { "Calendar Name", "Calendar Description", "Calendar Year",
			"Created By", "Created Date", "Modified By", "Modified Date" };
	private static final Class<?>[] CALENDAR_CONF_COLUMN_TYPE = { String.class, String.class, Integer.class,
			String.class, Date.class, String.class, Date.class };

	public static List<String> getYearDdlbValue() {
		int currentyear = getCurrentYear();
		return Arrays.asList(Integer.toString(currentyear - 3), Integer.toString(currentyear - 2),
				Integer.toString(currentyear - 1), Integer.toString(currentyear), Integer.toString(currentyear + 1),
				Integer.toString(currentyear + 2), Integer.toString(currentyear + 3));
	}

	public static List<String> getFieldList() {
		return Arrays.asList("CCCrudView_CalendarName", "CCCrudView_CalendarDescription", CC_CALENDAR_FIELD,
				"CCCrudView_Country", "CCCrudView_CalendarYear", "CCCrudView_DefaultHolidays");
	}

	public static List<String> getTotalFieldList() {
		return Arrays.asList("CCCrudView_CalendarName", "CCCrudView_CalendarDescription", "CCCrudView_CalendarYear",
				"CCCrudView_DefaultHolidays", "CCCrudView_Country", CC_CALENDAR_FIELD, "CCCrudView_gtnSave",
				"CCCrudView_gtnReset");
	}

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static String[] getCalendarConfColumn() {
		return CALENDAR_CONF_COLUMN.clone();
	}

	public static String[] getCalendarConfHeader() {
		return CALENDAR_CONF_HEADER.clone();
	}

	public static Class<?>[] getCalendarConfColumnType() {
		return CALENDAR_CONF_COLUMN_TYPE.clone();
	}
}