/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnCMasterFinancialCloseBean implements Serializable, Comparator<GtnCMasterFinancialCloseBean> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GtnCMasterFinancialCloseBean() {
		super();
	}

	private String mode = "";
	private String calendar = "";
	private String status = "";
	private String createdBy = "";

	private String yearDdlb = "";
	private String businessDayDdlb = "";
	private String hourDdlb = "";
	private String minuteValue = "";
	private Integer userId = 0;
	private Integer sessionId = 0;
	private boolean automatic = Boolean.FALSE;
	private Date createdDate;
	private Date dateTime;
	private Integer calenderDdlb;
	private int start;
	private int end;
	private boolean count;
	private boolean generate = false;

	private String year;
	private String month;
	private int periodSid;
	private String openClose;
	private String companyMode;
	private Integer companyMasterSid;
	private Map<Integer, String> monthMap = new HashMap<>();

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCalendar() {
		return calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateTime() {
		return dateTime == null ? null : (Date) dateTime.clone();
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime == null ? null : (Date) dateTime.clone();
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getBusinessDayDdlb() {
		return businessDayDdlb;
	}

	public void setBusinessDayDdlb(String businessDayDdlb) {
		this.businessDayDdlb = businessDayDdlb;
	}

	public String getHourDdlb() {
		return hourDdlb;
	}

	public void setHourDdlb(String hourDdlb) {
		this.hourDdlb = hourDdlb;
	}

	public Integer getCalenderDdlb() {
		return calenderDdlb;
	}

	public void setCalenderDdlb(Integer calenderDdlb) {
		this.calenderDdlb = calenderDdlb;
	}

	public String getYearDdlb() {
		return yearDdlb;
	}

	public void setYearDdlb(String yearDdlb) {
		this.yearDdlb = yearDdlb;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	public boolean isGenerate() {
		return generate;
	}

	public void setGenerate(boolean generate) {
		this.generate = generate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public String getOpenClose() {
		return openClose;
	}

	public void setOpenClose(String openClose) {
		this.openClose = openClose;
	}

	public String getCompanyMode() {
		return companyMode;
	}

	public void setCompanyMode(String companyMode) {
		this.companyMode = companyMode;
	}

	public Integer getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(Integer companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public Map<Integer, String> getMonthMap() {
		return monthMap;
	}

	public void setMonthMap(Map<Integer, String> monthMap) {
		this.monthMap = monthMap;
	}

	public String getMinuteValue() {
		return minuteValue;
	}

	public void setMinuteValue(String minuteValue) {
		this.minuteValue = minuteValue;
	}

	@Override
	public int compare(GtnCMasterFinancialCloseBean o1, GtnCMasterFinancialCloseBean o2) {
		return o1.getDateTime().compareTo(o2.getDateTime());
	}

	public boolean isAutomatic() {
		return automatic;
	}

	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
}
