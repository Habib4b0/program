/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.calendarconfiguration;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import java.util.ArrayList;

public class GtnWsCalendarConfigurationRequest implements GtnWSRequestData {

	public GtnWsCalendarConfigurationRequest() {
		super();
	}

	private GtnWsRecordBean calendarBean;

	private List<Date> holidays;
	private int calendarId;
	private String userId;
	private String calendarName;
	private String calendarDescription;
	private int calendarYear;
	private int countryCode;
	private String country;
	private boolean defaultHolidays;
	private boolean isCalendarNameExists;
	
	public boolean isCalendarNameExists() {
		return isCalendarNameExists;
	}

	public void setCalendarNameExists(boolean isCalendarNameExists) {
		this.isCalendarNameExists = isCalendarNameExists;
	}

	public GtnWsRecordBean getCalendarBean() {
		return calendarBean;
	}

	public void setCalendarBean(GtnWsRecordBean calendarBean) {
		this.calendarBean = calendarBean;
	}

	public List<Date> getHolidays() {
		return holidays != null ? new ArrayList<>(holidays) : holidays;
	}

	public void setHolidays(List<Date> holidays) {
		this.holidays = holidays != null ? new ArrayList<>(holidays) : holidays;
	}

	public int getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public String getCalendarDescription() {
		return calendarDescription;
	}

	public void setCalendarDescription(String calendarDescription) {
		this.calendarDescription = calendarDescription;
	}

	public int getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(int calendarYear) {
		this.calendarYear = calendarYear;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isDefaultHolidays() {
		return defaultHolidays;
	}

	public void setDefaultHolidays(boolean defaultHolidays) {
		this.defaultHolidays = defaultHolidays;
	}

}
