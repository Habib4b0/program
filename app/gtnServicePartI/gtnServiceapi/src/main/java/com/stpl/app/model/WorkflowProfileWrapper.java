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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link WorkflowProfile}.
 * </p>
 *
 * @author
 * @see WorkflowProfile
 * @generated
 */
@ProviderType
public class WorkflowProfileWrapper implements WorkflowProfile,
	ModelWrapper<WorkflowProfile> {
	public WorkflowProfileWrapper(WorkflowProfile workflowProfile) {
		_workflowProfile = workflowProfile;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowProfile.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowProfile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("startHour", getStartHour());
		attributes.put("frequency", getFrequency());
		attributes.put("processName", getProcessName());
		attributes.put("startMinutes1", getStartMinutes1());
		attributes.put("endDate", getEndDate());
		attributes.put("emailNotificationFailureCc",
			getEmailNotificationFailureCc());
		attributes.put("failureMailSubject", getFailureMailSubject());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("schemaName", getSchemaName());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("scheduleLastRun", getScheduleLastRun());
		attributes.put("emailNotificationSuccessTo",
			getEmailNotificationSuccessTo());
		attributes.put("startMinutes3", getStartMinutes3());
		attributes.put("startMinutes2", getStartMinutes2());
		attributes.put("processSid", getProcessSid());
		attributes.put("successMailBody", getSuccessMailBody());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("emailNotificationSuccessCc",
			getEmailNotificationSuccessCc());
		attributes.put("emailNotificationFailureTo",
			getEmailNotificationFailureTo());
		attributes.put("failureMailBody", getFailureMailBody());
		attributes.put("activeFlag", getActiveFlag());
		attributes.put("processDisplayName", getProcessDisplayName());
		attributes.put("startMinutes", getStartMinutes());
		attributes.put("manualLastRun", getManualLastRun());
		attributes.put("startDate", getStartDate());
		attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
		attributes.put("successMailSubject", getSuccessMailSubject());
		attributes.put("startHour3", getStartHour3());
		attributes.put("startHour2", getStartHour2());
		attributes.put("userSid", getUserSid());
		attributes.put("startHour1", getStartHour1());
		attributes.put("processType", getProcessType());
		attributes.put("scriptName", getScriptName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer startHour = (Integer)attributes.get("startHour");

		if (startHour != null) {
			setStartHour(startHour);
		}

		String frequency = (String)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		String processName = (String)attributes.get("processName");

		if (processName != null) {
			setProcessName(processName);
		}

		Integer startMinutes1 = (Integer)attributes.get("startMinutes1");

		if (startMinutes1 != null) {
			setStartMinutes1(startMinutes1);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String emailNotificationFailureCc = (String)attributes.get(
				"emailNotificationFailureCc");

		if (emailNotificationFailureCc != null) {
			setEmailNotificationFailureCc(emailNotificationFailureCc);
		}

		String failureMailSubject = (String)attributes.get("failureMailSubject");

		if (failureMailSubject != null) {
			setFailureMailSubject(failureMailSubject);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String schemaName = (String)attributes.get("schemaName");

		if (schemaName != null) {
			setSchemaName(schemaName);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date scheduleLastRun = (Date)attributes.get("scheduleLastRun");

		if (scheduleLastRun != null) {
			setScheduleLastRun(scheduleLastRun);
		}

		String emailNotificationSuccessTo = (String)attributes.get(
				"emailNotificationSuccessTo");

		if (emailNotificationSuccessTo != null) {
			setEmailNotificationSuccessTo(emailNotificationSuccessTo);
		}

		Integer startMinutes3 = (Integer)attributes.get("startMinutes3");

		if (startMinutes3 != null) {
			setStartMinutes3(startMinutes3);
		}

		Integer startMinutes2 = (Integer)attributes.get("startMinutes2");

		if (startMinutes2 != null) {
			setStartMinutes2(startMinutes2);
		}

		Integer processSid = (Integer)attributes.get("processSid");

		if (processSid != null) {
			setProcessSid(processSid);
		}

		String successMailBody = (String)attributes.get("successMailBody");

		if (successMailBody != null) {
			setSuccessMailBody(successMailBody);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String emailNotificationSuccessCc = (String)attributes.get(
				"emailNotificationSuccessCc");

		if (emailNotificationSuccessCc != null) {
			setEmailNotificationSuccessCc(emailNotificationSuccessCc);
		}

		String emailNotificationFailureTo = (String)attributes.get(
				"emailNotificationFailureTo");

		if (emailNotificationFailureTo != null) {
			setEmailNotificationFailureTo(emailNotificationFailureTo);
		}

		String failureMailBody = (String)attributes.get("failureMailBody");

		if (failureMailBody != null) {
			setFailureMailBody(failureMailBody);
		}

		String activeFlag = (String)attributes.get("activeFlag");

		if (activeFlag != null) {
			setActiveFlag(activeFlag);
		}

		String processDisplayName = (String)attributes.get("processDisplayName");

		if (processDisplayName != null) {
			setProcessDisplayName(processDisplayName);
		}

		Integer startMinutes = (Integer)attributes.get("startMinutes");

		if (startMinutes != null) {
			setStartMinutes(startMinutes);
		}

		Date manualLastRun = (Date)attributes.get("manualLastRun");

		if (manualLastRun != null) {
			setManualLastRun(manualLastRun);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Integer slaCalendarMasterSid = (Integer)attributes.get(
				"slaCalendarMasterSid");

		if (slaCalendarMasterSid != null) {
			setSlaCalendarMasterSid(slaCalendarMasterSid);
		}

		String successMailSubject = (String)attributes.get("successMailSubject");

		if (successMailSubject != null) {
			setSuccessMailSubject(successMailSubject);
		}

		Integer startHour3 = (Integer)attributes.get("startHour3");

		if (startHour3 != null) {
			setStartHour3(startHour3);
		}

		Integer startHour2 = (Integer)attributes.get("startHour2");

		if (startHour2 != null) {
			setStartHour2(startHour2);
		}

		Integer userSid = (Integer)attributes.get("userSid");

		if (userSid != null) {
			setUserSid(userSid);
		}

		Integer startHour1 = (Integer)attributes.get("startHour1");

		if (startHour1 != null) {
			setStartHour1(startHour1);
		}

		Integer processType = (Integer)attributes.get("processType");

		if (processType != null) {
			setProcessType(processType);
		}

		String scriptName = (String)attributes.get("scriptName");

		if (scriptName != null) {
			setScriptName(scriptName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WorkflowProfileWrapper((WorkflowProfile)_workflowProfile.clone());
	}

	@Override
	public int compareTo(WorkflowProfile workflowProfile) {
		return _workflowProfile.compareTo(workflowProfile);
	}

	/**
	* Returns the active flag of this workflow profile.
	*
	* @return the active flag of this workflow profile
	*/
	@Override
	public java.lang.String getActiveFlag() {
		return _workflowProfile.getActiveFlag();
	}

	/**
	* Returns the created by of this workflow profile.
	*
	* @return the created by of this workflow profile
	*/
	@Override
	public int getCreatedBy() {
		return _workflowProfile.getCreatedBy();
	}

	/**
	* Returns the created date of this workflow profile.
	*
	* @return the created date of this workflow profile
	*/
	@Override
	public Date getCreatedDate() {
		return _workflowProfile.getCreatedDate();
	}

	/**
	* Returns the email notification failure cc of this workflow profile.
	*
	* @return the email notification failure cc of this workflow profile
	*/
	@Override
	public java.lang.String getEmailNotificationFailureCc() {
		return _workflowProfile.getEmailNotificationFailureCc();
	}

	/**
	* Returns the email notification failure to of this workflow profile.
	*
	* @return the email notification failure to of this workflow profile
	*/
	@Override
	public java.lang.String getEmailNotificationFailureTo() {
		return _workflowProfile.getEmailNotificationFailureTo();
	}

	/**
	* Returns the email notification success cc of this workflow profile.
	*
	* @return the email notification success cc of this workflow profile
	*/
	@Override
	public java.lang.String getEmailNotificationSuccessCc() {
		return _workflowProfile.getEmailNotificationSuccessCc();
	}

	/**
	* Returns the email notification success to of this workflow profile.
	*
	* @return the email notification success to of this workflow profile
	*/
	@Override
	public java.lang.String getEmailNotificationSuccessTo() {
		return _workflowProfile.getEmailNotificationSuccessTo();
	}

	/**
	* Returns the end date of this workflow profile.
	*
	* @return the end date of this workflow profile
	*/
	@Override
	public Date getEndDate() {
		return _workflowProfile.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workflowProfile.getExpandoBridge();
	}

	/**
	* Returns the failure mail body of this workflow profile.
	*
	* @return the failure mail body of this workflow profile
	*/
	@Override
	public java.lang.String getFailureMailBody() {
		return _workflowProfile.getFailureMailBody();
	}

	/**
	* Returns the failure mail subject of this workflow profile.
	*
	* @return the failure mail subject of this workflow profile
	*/
	@Override
	public java.lang.String getFailureMailSubject() {
		return _workflowProfile.getFailureMailSubject();
	}

	/**
	* Returns the frequency of this workflow profile.
	*
	* @return the frequency of this workflow profile
	*/
	@Override
	public java.lang.String getFrequency() {
		return _workflowProfile.getFrequency();
	}

	/**
	* Returns the inbound status of this workflow profile.
	*
	* @return the inbound status of this workflow profile
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _workflowProfile.getInboundStatus();
	}

	/**
	* Returns the manual last run of this workflow profile.
	*
	* @return the manual last run of this workflow profile
	*/
	@Override
	public Date getManualLastRun() {
		return _workflowProfile.getManualLastRun();
	}

	/**
	* Returns the modified by of this workflow profile.
	*
	* @return the modified by of this workflow profile
	*/
	@Override
	public int getModifiedBy() {
		return _workflowProfile.getModifiedBy();
	}

	/**
	* Returns the modified date of this workflow profile.
	*
	* @return the modified date of this workflow profile
	*/
	@Override
	public Date getModifiedDate() {
		return _workflowProfile.getModifiedDate();
	}

	/**
	* Returns the primary key of this workflow profile.
	*
	* @return the primary key of this workflow profile
	*/
	@Override
	public int getPrimaryKey() {
		return _workflowProfile.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workflowProfile.getPrimaryKeyObj();
	}

	/**
	* Returns the process display name of this workflow profile.
	*
	* @return the process display name of this workflow profile
	*/
	@Override
	public java.lang.String getProcessDisplayName() {
		return _workflowProfile.getProcessDisplayName();
	}

	/**
	* Returns the process name of this workflow profile.
	*
	* @return the process name of this workflow profile
	*/
	@Override
	public java.lang.String getProcessName() {
		return _workflowProfile.getProcessName();
	}

	/**
	* Returns the process sid of this workflow profile.
	*
	* @return the process sid of this workflow profile
	*/
	@Override
	public int getProcessSid() {
		return _workflowProfile.getProcessSid();
	}

	/**
	* Returns the process type of this workflow profile.
	*
	* @return the process type of this workflow profile
	*/
	@Override
	public int getProcessType() {
		return _workflowProfile.getProcessType();
	}

	/**
	* Returns the schedule last run of this workflow profile.
	*
	* @return the schedule last run of this workflow profile
	*/
	@Override
	public Date getScheduleLastRun() {
		return _workflowProfile.getScheduleLastRun();
	}

	/**
	* Returns the schema name of this workflow profile.
	*
	* @return the schema name of this workflow profile
	*/
	@Override
	public java.lang.String getSchemaName() {
		return _workflowProfile.getSchemaName();
	}

	/**
	* Returns the script name of this workflow profile.
	*
	* @return the script name of this workflow profile
	*/
	@Override
	public java.lang.String getScriptName() {
		return _workflowProfile.getScriptName();
	}

	/**
	* Returns the sla calendar master sid of this workflow profile.
	*
	* @return the sla calendar master sid of this workflow profile
	*/
	@Override
	public int getSlaCalendarMasterSid() {
		return _workflowProfile.getSlaCalendarMasterSid();
	}

	/**
	* Returns the start date of this workflow profile.
	*
	* @return the start date of this workflow profile
	*/
	@Override
	public Date getStartDate() {
		return _workflowProfile.getStartDate();
	}

	/**
	* Returns the start hour of this workflow profile.
	*
	* @return the start hour of this workflow profile
	*/
	@Override
	public int getStartHour() {
		return _workflowProfile.getStartHour();
	}

	/**
	* Returns the start hour1 of this workflow profile.
	*
	* @return the start hour1 of this workflow profile
	*/
	@Override
	public int getStartHour1() {
		return _workflowProfile.getStartHour1();
	}

	/**
	* Returns the start hour2 of this workflow profile.
	*
	* @return the start hour2 of this workflow profile
	*/
	@Override
	public int getStartHour2() {
		return _workflowProfile.getStartHour2();
	}

	/**
	* Returns the start hour3 of this workflow profile.
	*
	* @return the start hour3 of this workflow profile
	*/
	@Override
	public int getStartHour3() {
		return _workflowProfile.getStartHour3();
	}

	/**
	* Returns the start minutes of this workflow profile.
	*
	* @return the start minutes of this workflow profile
	*/
	@Override
	public int getStartMinutes() {
		return _workflowProfile.getStartMinutes();
	}

	/**
	* Returns the start minutes1 of this workflow profile.
	*
	* @return the start minutes1 of this workflow profile
	*/
	@Override
	public int getStartMinutes1() {
		return _workflowProfile.getStartMinutes1();
	}

	/**
	* Returns the start minutes2 of this workflow profile.
	*
	* @return the start minutes2 of this workflow profile
	*/
	@Override
	public int getStartMinutes2() {
		return _workflowProfile.getStartMinutes2();
	}

	/**
	* Returns the start minutes3 of this workflow profile.
	*
	* @return the start minutes3 of this workflow profile
	*/
	@Override
	public int getStartMinutes3() {
		return _workflowProfile.getStartMinutes3();
	}

	/**
	* Returns the success mail body of this workflow profile.
	*
	* @return the success mail body of this workflow profile
	*/
	@Override
	public java.lang.String getSuccessMailBody() {
		return _workflowProfile.getSuccessMailBody();
	}

	/**
	* Returns the success mail subject of this workflow profile.
	*
	* @return the success mail subject of this workflow profile
	*/
	@Override
	public java.lang.String getSuccessMailSubject() {
		return _workflowProfile.getSuccessMailSubject();
	}

	/**
	* Returns the user sid of this workflow profile.
	*
	* @return the user sid of this workflow profile
	*/
	@Override
	public int getUserSid() {
		return _workflowProfile.getUserSid();
	}

	@Override
	public int hashCode() {
		return _workflowProfile.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _workflowProfile.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workflowProfile.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workflowProfile.isNew();
	}

	@Override
	public void persist() {
		_workflowProfile.persist();
	}

	/**
	* Sets the active flag of this workflow profile.
	*
	* @param activeFlag the active flag of this workflow profile
	*/
	@Override
	public void setActiveFlag(java.lang.String activeFlag) {
		_workflowProfile.setActiveFlag(activeFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workflowProfile.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this workflow profile.
	*
	* @param createdBy the created by of this workflow profile
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_workflowProfile.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this workflow profile.
	*
	* @param createdDate the created date of this workflow profile
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_workflowProfile.setCreatedDate(createdDate);
	}

	/**
	* Sets the email notification failure cc of this workflow profile.
	*
	* @param emailNotificationFailureCc the email notification failure cc of this workflow profile
	*/
	@Override
	public void setEmailNotificationFailureCc(
		java.lang.String emailNotificationFailureCc) {
		_workflowProfile.setEmailNotificationFailureCc(emailNotificationFailureCc);
	}

	/**
	* Sets the email notification failure to of this workflow profile.
	*
	* @param emailNotificationFailureTo the email notification failure to of this workflow profile
	*/
	@Override
	public void setEmailNotificationFailureTo(
		java.lang.String emailNotificationFailureTo) {
		_workflowProfile.setEmailNotificationFailureTo(emailNotificationFailureTo);
	}

	/**
	* Sets the email notification success cc of this workflow profile.
	*
	* @param emailNotificationSuccessCc the email notification success cc of this workflow profile
	*/
	@Override
	public void setEmailNotificationSuccessCc(
		java.lang.String emailNotificationSuccessCc) {
		_workflowProfile.setEmailNotificationSuccessCc(emailNotificationSuccessCc);
	}

	/**
	* Sets the email notification success to of this workflow profile.
	*
	* @param emailNotificationSuccessTo the email notification success to of this workflow profile
	*/
	@Override
	public void setEmailNotificationSuccessTo(
		java.lang.String emailNotificationSuccessTo) {
		_workflowProfile.setEmailNotificationSuccessTo(emailNotificationSuccessTo);
	}

	/**
	* Sets the end date of this workflow profile.
	*
	* @param endDate the end date of this workflow profile
	*/
	@Override
	public void setEndDate(Date endDate) {
		_workflowProfile.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workflowProfile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workflowProfile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workflowProfile.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the failure mail body of this workflow profile.
	*
	* @param failureMailBody the failure mail body of this workflow profile
	*/
	@Override
	public void setFailureMailBody(java.lang.String failureMailBody) {
		_workflowProfile.setFailureMailBody(failureMailBody);
	}

	/**
	* Sets the failure mail subject of this workflow profile.
	*
	* @param failureMailSubject the failure mail subject of this workflow profile
	*/
	@Override
	public void setFailureMailSubject(java.lang.String failureMailSubject) {
		_workflowProfile.setFailureMailSubject(failureMailSubject);
	}

	/**
	* Sets the frequency of this workflow profile.
	*
	* @param frequency the frequency of this workflow profile
	*/
	@Override
	public void setFrequency(java.lang.String frequency) {
		_workflowProfile.setFrequency(frequency);
	}

	/**
	* Sets the inbound status of this workflow profile.
	*
	* @param inboundStatus the inbound status of this workflow profile
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_workflowProfile.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the manual last run of this workflow profile.
	*
	* @param manualLastRun the manual last run of this workflow profile
	*/
	@Override
	public void setManualLastRun(Date manualLastRun) {
		_workflowProfile.setManualLastRun(manualLastRun);
	}

	/**
	* Sets the modified by of this workflow profile.
	*
	* @param modifiedBy the modified by of this workflow profile
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_workflowProfile.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this workflow profile.
	*
	* @param modifiedDate the modified date of this workflow profile
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workflowProfile.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_workflowProfile.setNew(n);
	}

	/**
	* Sets the primary key of this workflow profile.
	*
	* @param primaryKey the primary key of this workflow profile
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_workflowProfile.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workflowProfile.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process display name of this workflow profile.
	*
	* @param processDisplayName the process display name of this workflow profile
	*/
	@Override
	public void setProcessDisplayName(java.lang.String processDisplayName) {
		_workflowProfile.setProcessDisplayName(processDisplayName);
	}

	/**
	* Sets the process name of this workflow profile.
	*
	* @param processName the process name of this workflow profile
	*/
	@Override
	public void setProcessName(java.lang.String processName) {
		_workflowProfile.setProcessName(processName);
	}

	/**
	* Sets the process sid of this workflow profile.
	*
	* @param processSid the process sid of this workflow profile
	*/
	@Override
	public void setProcessSid(int processSid) {
		_workflowProfile.setProcessSid(processSid);
	}

	/**
	* Sets the process type of this workflow profile.
	*
	* @param processType the process type of this workflow profile
	*/
	@Override
	public void setProcessType(int processType) {
		_workflowProfile.setProcessType(processType);
	}

	/**
	* Sets the schedule last run of this workflow profile.
	*
	* @param scheduleLastRun the schedule last run of this workflow profile
	*/
	@Override
	public void setScheduleLastRun(Date scheduleLastRun) {
		_workflowProfile.setScheduleLastRun(scheduleLastRun);
	}

	/**
	* Sets the schema name of this workflow profile.
	*
	* @param schemaName the schema name of this workflow profile
	*/
	@Override
	public void setSchemaName(java.lang.String schemaName) {
		_workflowProfile.setSchemaName(schemaName);
	}

	/**
	* Sets the script name of this workflow profile.
	*
	* @param scriptName the script name of this workflow profile
	*/
	@Override
	public void setScriptName(java.lang.String scriptName) {
		_workflowProfile.setScriptName(scriptName);
	}

	/**
	* Sets the sla calendar master sid of this workflow profile.
	*
	* @param slaCalendarMasterSid the sla calendar master sid of this workflow profile
	*/
	@Override
	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_workflowProfile.setSlaCalendarMasterSid(slaCalendarMasterSid);
	}

	/**
	* Sets the start date of this workflow profile.
	*
	* @param startDate the start date of this workflow profile
	*/
	@Override
	public void setStartDate(Date startDate) {
		_workflowProfile.setStartDate(startDate);
	}

	/**
	* Sets the start hour of this workflow profile.
	*
	* @param startHour the start hour of this workflow profile
	*/
	@Override
	public void setStartHour(int startHour) {
		_workflowProfile.setStartHour(startHour);
	}

	/**
	* Sets the start hour1 of this workflow profile.
	*
	* @param startHour1 the start hour1 of this workflow profile
	*/
	@Override
	public void setStartHour1(int startHour1) {
		_workflowProfile.setStartHour1(startHour1);
	}

	/**
	* Sets the start hour2 of this workflow profile.
	*
	* @param startHour2 the start hour2 of this workflow profile
	*/
	@Override
	public void setStartHour2(int startHour2) {
		_workflowProfile.setStartHour2(startHour2);
	}

	/**
	* Sets the start hour3 of this workflow profile.
	*
	* @param startHour3 the start hour3 of this workflow profile
	*/
	@Override
	public void setStartHour3(int startHour3) {
		_workflowProfile.setStartHour3(startHour3);
	}

	/**
	* Sets the start minutes of this workflow profile.
	*
	* @param startMinutes the start minutes of this workflow profile
	*/
	@Override
	public void setStartMinutes(int startMinutes) {
		_workflowProfile.setStartMinutes(startMinutes);
	}

	/**
	* Sets the start minutes1 of this workflow profile.
	*
	* @param startMinutes1 the start minutes1 of this workflow profile
	*/
	@Override
	public void setStartMinutes1(int startMinutes1) {
		_workflowProfile.setStartMinutes1(startMinutes1);
	}

	/**
	* Sets the start minutes2 of this workflow profile.
	*
	* @param startMinutes2 the start minutes2 of this workflow profile
	*/
	@Override
	public void setStartMinutes2(int startMinutes2) {
		_workflowProfile.setStartMinutes2(startMinutes2);
	}

	/**
	* Sets the start minutes3 of this workflow profile.
	*
	* @param startMinutes3 the start minutes3 of this workflow profile
	*/
	@Override
	public void setStartMinutes3(int startMinutes3) {
		_workflowProfile.setStartMinutes3(startMinutes3);
	}

	/**
	* Sets the success mail body of this workflow profile.
	*
	* @param successMailBody the success mail body of this workflow profile
	*/
	@Override
	public void setSuccessMailBody(java.lang.String successMailBody) {
		_workflowProfile.setSuccessMailBody(successMailBody);
	}

	/**
	* Sets the success mail subject of this workflow profile.
	*
	* @param successMailSubject the success mail subject of this workflow profile
	*/
	@Override
	public void setSuccessMailSubject(java.lang.String successMailSubject) {
		_workflowProfile.setSuccessMailSubject(successMailSubject);
	}

	/**
	* Sets the user sid of this workflow profile.
	*
	* @param userSid the user sid of this workflow profile
	*/
	@Override
	public void setUserSid(int userSid) {
		_workflowProfile.setUserSid(userSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkflowProfile> toCacheModel() {
		return _workflowProfile.toCacheModel();
	}

	@Override
	public WorkflowProfile toEscapedModel() {
		return new WorkflowProfileWrapper(_workflowProfile.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _workflowProfile.toString();
	}

	@Override
	public WorkflowProfile toUnescapedModel() {
		return new WorkflowProfileWrapper(_workflowProfile.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _workflowProfile.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowProfileWrapper)) {
			return false;
		}

		WorkflowProfileWrapper workflowProfileWrapper = (WorkflowProfileWrapper)obj;

		if (Objects.equals(_workflowProfile,
					workflowProfileWrapper._workflowProfile)) {
			return true;
		}

		return false;
	}

	@Override
	public WorkflowProfile getWrappedModel() {
		return _workflowProfile;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workflowProfile.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workflowProfile.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workflowProfile.resetOriginalValues();
	}

	private final WorkflowProfile _workflowProfile;
}