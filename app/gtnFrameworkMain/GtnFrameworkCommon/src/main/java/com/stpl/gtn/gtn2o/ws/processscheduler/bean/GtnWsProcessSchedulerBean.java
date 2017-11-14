package com.stpl.gtn.gtn2o.ws.processscheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnWsProcessSchedulerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsProcessSchedulerBean() {
		super();
	}

	private String psProcessName;
	private String psProcessType;
	private String psCalender;
	private Date psStartDate;
	private Date psEndDate;
	private Date psCreatedDate;
	private Date psModifiedDate;
	private String psHours1;
	private String psHours2;
	private String psHours3;
	private String psMinutes1;
	private String psMinutes2;
	private String psMinutes3;
	private int processSchedulerSid;
	private String psSchemaName;

	public String getPsProcessName() {
		return psProcessName;
	}

	public void setPsProcessName(String psProcessName) {
		this.psProcessName = psProcessName;
	}

	public String getPsProcessType() {
		return psProcessType;
	}

	public void setPsProcessType(String psProcessType) {
		this.psProcessType = psProcessType;
	}

	public String getPsCalender() {
		return psCalender;
	}

	public void setPsCalender(String psCalender) {
		this.psCalender = psCalender;
	}

	public Date getPsStartDate() {
		return psStartDate == null ? null : (Date) psStartDate.clone();
	}

	public void setPsStartDate(Date psStartDate) {
		this.psStartDate = psStartDate == null ? null : (Date) psStartDate.clone();
	}

	public Date getPsEndDate() {
		return psEndDate == null ? null : (Date) psEndDate.clone();
	}

	public void setPsEndDate(Date psEndDate) {
		this.psEndDate = psEndDate == null ? null : (Date) psEndDate.clone();
	}

	public Date getPsCreatedDate() {
		return psCreatedDate == null ? null : (Date) psCreatedDate.clone();
	}

	public void setPsCreatedDate(Date psCreatedDate) {
		this.psCreatedDate = psCreatedDate == null ? null : (Date) psCreatedDate.clone();
	}

	public Date getPsModifiedDate() {
		return psModifiedDate == null ? null : (Date) psModifiedDate.clone();
	}

	public void setPsModifiedDate(Date psModifiedDate) {
		this.psModifiedDate = psModifiedDate == null ? null : (Date) psModifiedDate.clone();
	}

	public String getPsHours1() {
		return psHours1;
	}

	public void setPsHours1(String psHours1) {
		this.psHours1 = psHours1;
	}

	public String getPsHours2() {
		return psHours2;
	}

	public void setPsHours2(String psHours2) {
		this.psHours2 = psHours2;
	}

	public String getPsHours3() {
		return psHours3;
	}

	public void setPsHours3(String psHours3) {
		this.psHours3 = psHours3;
	}

	public String getPsMinutes1() {
		return psMinutes1;
	}

	public void setPsMinutes1(String psMinutes1) {
		this.psMinutes1 = psMinutes1;
	}

	public String getPsMinutes2() {
		return psMinutes2;
	}

	public void setPsMinutes2(String psMinutes2) {
		this.psMinutes2 = psMinutes2;
	}

	public String getPsMinutes3() {
		return psMinutes3;
	}

	public void setPsMinutes3(String psMinutes3) {
		this.psMinutes3 = psMinutes3;
	}

	public int getProcessSchedulerSid() {
		return processSchedulerSid;
	}

	public void setProcessSchedulerSid(int processSchedulerSid) {
		this.processSchedulerSid = processSchedulerSid;
	}

	public String getPsSchemaName() {
		return psSchemaName;
	}

	public void setPsSchemaName(String psSchemaName) {
		this.psSchemaName = psSchemaName;
	}

}
