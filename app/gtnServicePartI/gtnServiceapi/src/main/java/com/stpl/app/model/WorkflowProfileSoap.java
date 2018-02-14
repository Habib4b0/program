/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class WorkflowProfileSoap implements Serializable {
	public static WorkflowProfileSoap toSoapModel(WorkflowProfile model) {
		WorkflowProfileSoap soapModel = new WorkflowProfileSoap();

		soapModel.setStartHour(model.getStartHour());
		soapModel.setFrequency(model.getFrequency());
		soapModel.setProcessName(model.getProcessName());
		soapModel.setStartMinutes1(model.getStartMinutes1());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setEmailNotificationFailureCc(model.getEmailNotificationFailureCc());
		soapModel.setFailureMailSubject(model.getFailureMailSubject());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSchemaName(model.getSchemaName());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setScheduleLastRun(model.getScheduleLastRun());
		soapModel.setEmailNotificationSuccessTo(model.getEmailNotificationSuccessTo());
		soapModel.setStartMinutes3(model.getStartMinutes3());
		soapModel.setStartMinutes2(model.getStartMinutes2());
		soapModel.setProcessSid(model.getProcessSid());
		soapModel.setSuccessMailBody(model.getSuccessMailBody());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setEmailNotificationSuccessCc(model.getEmailNotificationSuccessCc());
		soapModel.setEmailNotificationFailureTo(model.getEmailNotificationFailureTo());
		soapModel.setFailureMailBody(model.getFailureMailBody());
		soapModel.setActiveFlag(model.getActiveFlag());
		soapModel.setProcessDisplayName(model.getProcessDisplayName());
		soapModel.setStartMinutes(model.getStartMinutes());
		soapModel.setManualLastRun(model.getManualLastRun());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setSlaCalendarMasterSid(model.getSlaCalendarMasterSid());
		soapModel.setSuccessMailSubject(model.getSuccessMailSubject());
		soapModel.setStartHour3(model.getStartHour3());
		soapModel.setStartHour2(model.getStartHour2());
		soapModel.setUserSid(model.getUserSid());
		soapModel.setStartHour1(model.getStartHour1());
		soapModel.setProcessType(model.getProcessType());
		soapModel.setScriptName(model.getScriptName());

		return soapModel;
	}

	public static WorkflowProfileSoap[] toSoapModels(WorkflowProfile[] models) {
		WorkflowProfileSoap[] soapModels = new WorkflowProfileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowProfileSoap[][] toSoapModels(
		WorkflowProfile[][] models) {
		WorkflowProfileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowProfileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowProfileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowProfileSoap[] toSoapModels(
		List<WorkflowProfile> models) {
		List<WorkflowProfileSoap> soapModels = new ArrayList<WorkflowProfileSoap>(models.size());

		for (WorkflowProfile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowProfileSoap[soapModels.size()]);
	}

	public WorkflowProfileSoap() {
	}

	public int getPrimaryKey() {
		return _processSid;
	}

	public void setPrimaryKey(int pk) {
		setProcessSid(pk);
	}

	public int getStartHour() {
		return _startHour;
	}

	public void setStartHour(int startHour) {
		_startHour = startHour;
	}

	public String getFrequency() {
		return _frequency;
	}

	public void setFrequency(String frequency) {
		_frequency = frequency;
	}

	public String getProcessName() {
		return _processName;
	}

	public void setProcessName(String processName) {
		_processName = processName;
	}

	public int getStartMinutes1() {
		return _startMinutes1;
	}

	public void setStartMinutes1(int startMinutes1) {
		_startMinutes1 = startMinutes1;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getEmailNotificationFailureCc() {
		return _emailNotificationFailureCc;
	}

	public void setEmailNotificationFailureCc(String emailNotificationFailureCc) {
		_emailNotificationFailureCc = emailNotificationFailureCc;
	}

	public String getFailureMailSubject() {
		return _failureMailSubject;
	}

	public void setFailureMailSubject(String failureMailSubject) {
		_failureMailSubject = failureMailSubject;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSchemaName() {
		return _schemaName;
	}

	public void setSchemaName(String schemaName) {
		_schemaName = schemaName;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public Date getScheduleLastRun() {
		return _scheduleLastRun;
	}

	public void setScheduleLastRun(Date scheduleLastRun) {
		_scheduleLastRun = scheduleLastRun;
	}

	public String getEmailNotificationSuccessTo() {
		return _emailNotificationSuccessTo;
	}

	public void setEmailNotificationSuccessTo(String emailNotificationSuccessTo) {
		_emailNotificationSuccessTo = emailNotificationSuccessTo;
	}

	public int getStartMinutes3() {
		return _startMinutes3;
	}

	public void setStartMinutes3(int startMinutes3) {
		_startMinutes3 = startMinutes3;
	}

	public int getStartMinutes2() {
		return _startMinutes2;
	}

	public void setStartMinutes2(int startMinutes2) {
		_startMinutes2 = startMinutes2;
	}

	public int getProcessSid() {
		return _processSid;
	}

	public void setProcessSid(int processSid) {
		_processSid = processSid;
	}

	public String getSuccessMailBody() {
		return _successMailBody;
	}

	public void setSuccessMailBody(String successMailBody) {
		_successMailBody = successMailBody;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getEmailNotificationSuccessCc() {
		return _emailNotificationSuccessCc;
	}

	public void setEmailNotificationSuccessCc(String emailNotificationSuccessCc) {
		_emailNotificationSuccessCc = emailNotificationSuccessCc;
	}

	public String getEmailNotificationFailureTo() {
		return _emailNotificationFailureTo;
	}

	public void setEmailNotificationFailureTo(String emailNotificationFailureTo) {
		_emailNotificationFailureTo = emailNotificationFailureTo;
	}

	public String getFailureMailBody() {
		return _failureMailBody;
	}

	public void setFailureMailBody(String failureMailBody) {
		_failureMailBody = failureMailBody;
	}

	public String getActiveFlag() {
		return _activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		_activeFlag = activeFlag;
	}

	public String getProcessDisplayName() {
		return _processDisplayName;
	}

	public void setProcessDisplayName(String processDisplayName) {
		_processDisplayName = processDisplayName;
	}

	public int getStartMinutes() {
		return _startMinutes;
	}

	public void setStartMinutes(int startMinutes) {
		_startMinutes = startMinutes;
	}

	public Date getManualLastRun() {
		return _manualLastRun;
	}

	public void setManualLastRun(Date manualLastRun) {
		_manualLastRun = manualLastRun;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public int getSlaCalendarMasterSid() {
		return _slaCalendarMasterSid;
	}

	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_slaCalendarMasterSid = slaCalendarMasterSid;
	}

	public String getSuccessMailSubject() {
		return _successMailSubject;
	}

	public void setSuccessMailSubject(String successMailSubject) {
		_successMailSubject = successMailSubject;
	}

	public int getStartHour3() {
		return _startHour3;
	}

	public void setStartHour3(int startHour3) {
		_startHour3 = startHour3;
	}

	public int getStartHour2() {
		return _startHour2;
	}

	public void setStartHour2(int startHour2) {
		_startHour2 = startHour2;
	}

	public int getUserSid() {
		return _userSid;
	}

	public void setUserSid(int userSid) {
		_userSid = userSid;
	}

	public int getStartHour1() {
		return _startHour1;
	}

	public void setStartHour1(int startHour1) {
		_startHour1 = startHour1;
	}

	public int getProcessType() {
		return _processType;
	}

	public void setProcessType(int processType) {
		_processType = processType;
	}

	public String getScriptName() {
		return _scriptName;
	}

	public void setScriptName(String scriptName) {
		_scriptName = scriptName;
	}

	private int _startHour;
	private String _frequency;
	private String _processName;
	private int _startMinutes1;
	private Date _endDate;
	private String _emailNotificationFailureCc;
	private String _failureMailSubject;
	private Date _modifiedDate;
	private String _schemaName;
	private int _createdBy;
	private Date _createdDate;
	private Date _scheduleLastRun;
	private String _emailNotificationSuccessTo;
	private int _startMinutes3;
	private int _startMinutes2;
	private int _processSid;
	private String _successMailBody;
	private String _inboundStatus;
	private int _modifiedBy;
	private String _emailNotificationSuccessCc;
	private String _emailNotificationFailureTo;
	private String _failureMailBody;
	private String _activeFlag;
	private String _processDisplayName;
	private int _startMinutes;
	private Date _manualLastRun;
	private Date _startDate;
	private int _slaCalendarMasterSid;
	private String _successMailSubject;
	private int _startHour3;
	private int _startHour2;
	private int _userSid;
	private int _startHour1;
	private int _processType;
	private String _scriptName;
}