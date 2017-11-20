/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.calendarconfiguration;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;
import java.util.Collections;

public class GtnWsCalendarConfigurationResponse implements GtnWSResponseData {

	public GtnWsCalendarConfigurationResponse() {
		super();
	}

	private GtnWsRecordBean calendarBean;

	private List<Date> holidays;
	private int calendarId;
	private boolean success;
	private String message;
	private String messageHeader;

	public GtnWsRecordBean getCalendarBean() {
		return calendarBean;
	}

	public void setCalendarBean(GtnWsRecordBean calendarBean) {
		this.calendarBean = calendarBean;
	}

	public void addToHolidays(Date date) {
		holidays.add(date);
	}

	public List<Date> getHolidays() {
		return holidays != null ? Collections.unmodifiableList(holidays) : holidays;
	}

	public void setHolidays(List<Date> holidays) {
		this.holidays = holidays != null ? Collections.unmodifiableList(holidays) : holidays;
	}

	public int getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}

}
