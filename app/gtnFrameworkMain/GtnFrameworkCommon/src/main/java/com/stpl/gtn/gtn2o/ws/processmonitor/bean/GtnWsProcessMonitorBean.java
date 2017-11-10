package com.stpl.gtn.gtn2o.ws.processmonitor.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnWsProcessMonitorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsProcessMonitorBean() {
		super();
	}

	private String processName;
	private String processType;
	private String calender;
	private Date startDate;
	private Date endDate;

	private String activeFlag;
	private String schemaName;
	private int slaCalendarMasterSid;
	private Date createdDate;
	private Date modifiedDate;
	private String hours1;
	private String hours2;
	private String hours3;
	private String minutes1;
	private String minutes2;
	private String minutes3;
	private String processDisplayName = "";
	private int processMonitorSid;
	private String component;
	private boolean errorMessage;

	private String inboundStatus;
	private String frequency;

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public int getProcessMonitorSid() {
		return processMonitorSid;
	}

	public void setProcessMonitorSid(int processMonitorSid) {
		this.processMonitorSid = processMonitorSid;
	}

	public String getProcessDisplayName() {
		return processDisplayName;
	}

	public void setProcessDisplayName(String processDisplayName) {
		this.processDisplayName = processDisplayName;
	}

	public String getHours1() {
		return hours1;
	}

	public void setHours1(String hours1) {
		this.hours1 = hours1;
	}

	public String getHours2() {
		return hours2;
	}

	public void setHours2(String hours2) {
		this.hours2 = hours2;
	}

	public String getHours3() {
		return hours3;
	}

	public void setHours3(String hours3) {
		this.hours3 = hours3;
	}

	public String getMinutes1() {
		return minutes1;
	}

	public void setMinutes1(String minutes1) {
		this.minutes1 = minutes1;
	}

	public String getMinutes2() {
		return minutes2;
	}

	public void setMinutes2(String minutes2) {
		this.minutes2 = minutes2;
	}

	public String getMinutes3() {
		return minutes3;
	}

	public void setMinutes3(String minutes3) {
		this.minutes3 = minutes3;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public int getSlaCalendarMasterSid() {
		return slaCalendarMasterSid;
	}

	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		this.slaCalendarMasterSid = slaCalendarMasterSid;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getInboundStatus() {
		return inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getCalender() {
		return calender;
	}

	public void setCalender(String calender) {
		this.calender = calender;
	}

	public Date getStartDate() {
		return startDate == null ? null : (Date) startDate.clone();
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate == null ? null : (Date) startDate.clone();
	}

	public Date getEndDate() {
		return endDate == null ? null : (Date) endDate.clone();
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate == null ? null : (Date) endDate.clone();
	}

	public boolean isErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(boolean errorMessage) {
		this.errorMessage = errorMessage;
	}

}
