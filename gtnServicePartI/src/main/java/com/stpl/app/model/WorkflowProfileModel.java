package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WorkflowProfile service. Represents a row in the &quot;WORKFLOW_PROFILE&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.WorkflowProfileModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.WorkflowProfileImpl}.
 * </p>
 *
 * @author
 * @see WorkflowProfile
 * @see com.stpl.app.model.impl.WorkflowProfileImpl
 * @see com.stpl.app.model.impl.WorkflowProfileModelImpl
 * @generated
 */
public interface WorkflowProfileModel extends BaseModel<WorkflowProfile> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a workflow profile model instance should use the {@link WorkflowProfile} interface instead.
     */

    /**
     * Returns the primary key of this workflow profile.
     *
     * @return the primary key of this workflow profile
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this workflow profile.
     *
     * @param primaryKey the primary key of this workflow profile
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the start hour of this workflow profile.
     *
     * @return the start hour of this workflow profile
     */
    public int getStartHour();

    /**
     * Sets the start hour of this workflow profile.
     *
     * @param startHour the start hour of this workflow profile
     */
    public void setStartHour(int startHour);

    /**
     * Returns the frequency of this workflow profile.
     *
     * @return the frequency of this workflow profile
     */
    @AutoEscape
    public String getFrequency();

    /**
     * Sets the frequency of this workflow profile.
     *
     * @param frequency the frequency of this workflow profile
     */
    public void setFrequency(String frequency);

    /**
     * Returns the process name of this workflow profile.
     *
     * @return the process name of this workflow profile
     */
    @AutoEscape
    public String getProcessName();

    /**
     * Sets the process name of this workflow profile.
     *
     * @param processName the process name of this workflow profile
     */
    public void setProcessName(String processName);

    /**
     * Returns the start minutes1 of this workflow profile.
     *
     * @return the start minutes1 of this workflow profile
     */
    public int getStartMinutes1();

    /**
     * Sets the start minutes1 of this workflow profile.
     *
     * @param startMinutes1 the start minutes1 of this workflow profile
     */
    public void setStartMinutes1(int startMinutes1);

    /**
     * Returns the end date of this workflow profile.
     *
     * @return the end date of this workflow profile
     */
    public Date getEndDate();

    /**
     * Sets the end date of this workflow profile.
     *
     * @param endDate the end date of this workflow profile
     */
    public void setEndDate(Date endDate);

    /**
     * Returns the email notification failure cc of this workflow profile.
     *
     * @return the email notification failure cc of this workflow profile
     */
    @AutoEscape
    public String getEmailNotificationFailureCc();

    /**
     * Sets the email notification failure cc of this workflow profile.
     *
     * @param emailNotificationFailureCc the email notification failure cc of this workflow profile
     */
    public void setEmailNotificationFailureCc(String emailNotificationFailureCc);

    /**
     * Returns the failure mail subject of this workflow profile.
     *
     * @return the failure mail subject of this workflow profile
     */
    @AutoEscape
    public String getFailureMailSubject();

    /**
     * Sets the failure mail subject of this workflow profile.
     *
     * @param failureMailSubject the failure mail subject of this workflow profile
     */
    public void setFailureMailSubject(String failureMailSubject);

    /**
     * Returns the modified date of this workflow profile.
     *
     * @return the modified date of this workflow profile
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this workflow profile.
     *
     * @param modifiedDate the modified date of this workflow profile
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the schema name of this workflow profile.
     *
     * @return the schema name of this workflow profile
     */
    @AutoEscape
    public String getSchemaName();

    /**
     * Sets the schema name of this workflow profile.
     *
     * @param schemaName the schema name of this workflow profile
     */
    public void setSchemaName(String schemaName);

    /**
     * Returns the created by of this workflow profile.
     *
     * @return the created by of this workflow profile
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this workflow profile.
     *
     * @param createdBy the created by of this workflow profile
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the created date of this workflow profile.
     *
     * @return the created date of this workflow profile
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this workflow profile.
     *
     * @param createdDate the created date of this workflow profile
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the schedule last run of this workflow profile.
     *
     * @return the schedule last run of this workflow profile
     */
    public Date getScheduleLastRun();

    /**
     * Sets the schedule last run of this workflow profile.
     *
     * @param scheduleLastRun the schedule last run of this workflow profile
     */
    public void setScheduleLastRun(Date scheduleLastRun);

    /**
     * Returns the email notification success to of this workflow profile.
     *
     * @return the email notification success to of this workflow profile
     */
    @AutoEscape
    public String getEmailNotificationSuccessTo();

    /**
     * Sets the email notification success to of this workflow profile.
     *
     * @param emailNotificationSuccessTo the email notification success to of this workflow profile
     */
    public void setEmailNotificationSuccessTo(String emailNotificationSuccessTo);

    /**
     * Returns the start minutes3 of this workflow profile.
     *
     * @return the start minutes3 of this workflow profile
     */
    public int getStartMinutes3();

    /**
     * Sets the start minutes3 of this workflow profile.
     *
     * @param startMinutes3 the start minutes3 of this workflow profile
     */
    public void setStartMinutes3(int startMinutes3);

    /**
     * Returns the start minutes2 of this workflow profile.
     *
     * @return the start minutes2 of this workflow profile
     */
    public int getStartMinutes2();

    /**
     * Sets the start minutes2 of this workflow profile.
     *
     * @param startMinutes2 the start minutes2 of this workflow profile
     */
    public void setStartMinutes2(int startMinutes2);

    /**
     * Returns the process sid of this workflow profile.
     *
     * @return the process sid of this workflow profile
     */
    public int getProcessSid();

    /**
     * Sets the process sid of this workflow profile.
     *
     * @param processSid the process sid of this workflow profile
     */
    public void setProcessSid(int processSid);

    /**
     * Returns the success mail body of this workflow profile.
     *
     * @return the success mail body of this workflow profile
     */
    @AutoEscape
    public String getSuccessMailBody();

    /**
     * Sets the success mail body of this workflow profile.
     *
     * @param successMailBody the success mail body of this workflow profile
     */
    public void setSuccessMailBody(String successMailBody);

    /**
     * Returns the inbound status of this workflow profile.
     *
     * @return the inbound status of this workflow profile
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this workflow profile.
     *
     * @param inboundStatus the inbound status of this workflow profile
     */
    public void setInboundStatus(String inboundStatus);

    /**
     * Returns the modified by of this workflow profile.
     *
     * @return the modified by of this workflow profile
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this workflow profile.
     *
     * @param modifiedBy the modified by of this workflow profile
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the email notification success cc of this workflow profile.
     *
     * @return the email notification success cc of this workflow profile
     */
    @AutoEscape
    public String getEmailNotificationSuccessCc();

    /**
     * Sets the email notification success cc of this workflow profile.
     *
     * @param emailNotificationSuccessCc the email notification success cc of this workflow profile
     */
    public void setEmailNotificationSuccessCc(String emailNotificationSuccessCc);

    /**
     * Returns the email notification failure to of this workflow profile.
     *
     * @return the email notification failure to of this workflow profile
     */
    @AutoEscape
    public String getEmailNotificationFailureTo();

    /**
     * Sets the email notification failure to of this workflow profile.
     *
     * @param emailNotificationFailureTo the email notification failure to of this workflow profile
     */
    public void setEmailNotificationFailureTo(String emailNotificationFailureTo);

    /**
     * Returns the failure mail body of this workflow profile.
     *
     * @return the failure mail body of this workflow profile
     */
    @AutoEscape
    public String getFailureMailBody();

    /**
     * Sets the failure mail body of this workflow profile.
     *
     * @param failureMailBody the failure mail body of this workflow profile
     */
    public void setFailureMailBody(String failureMailBody);

    /**
     * Returns the active flag of this workflow profile.
     *
     * @return the active flag of this workflow profile
     */
    @AutoEscape
    public String getActiveFlag();

    /**
     * Sets the active flag of this workflow profile.
     *
     * @param activeFlag the active flag of this workflow profile
     */
    public void setActiveFlag(String activeFlag);

    /**
     * Returns the process display name of this workflow profile.
     *
     * @return the process display name of this workflow profile
     */
    @AutoEscape
    public String getProcessDisplayName();

    /**
     * Sets the process display name of this workflow profile.
     *
     * @param processDisplayName the process display name of this workflow profile
     */
    public void setProcessDisplayName(String processDisplayName);

    /**
     * Returns the start minutes of this workflow profile.
     *
     * @return the start minutes of this workflow profile
     */
    public int getStartMinutes();

    /**
     * Sets the start minutes of this workflow profile.
     *
     * @param startMinutes the start minutes of this workflow profile
     */
    public void setStartMinutes(int startMinutes);

    /**
     * Returns the manual last run of this workflow profile.
     *
     * @return the manual last run of this workflow profile
     */
    public Date getManualLastRun();

    /**
     * Sets the manual last run of this workflow profile.
     *
     * @param manualLastRun the manual last run of this workflow profile
     */
    public void setManualLastRun(Date manualLastRun);

    /**
     * Returns the start date of this workflow profile.
     *
     * @return the start date of this workflow profile
     */
    public Date getStartDate();

    /**
     * Sets the start date of this workflow profile.
     *
     * @param startDate the start date of this workflow profile
     */
    public void setStartDate(Date startDate);

    /**
     * Returns the sla calendar master sid of this workflow profile.
     *
     * @return the sla calendar master sid of this workflow profile
     */
    public int getSlaCalendarMasterSid();

    /**
     * Sets the sla calendar master sid of this workflow profile.
     *
     * @param slaCalendarMasterSid the sla calendar master sid of this workflow profile
     */
    public void setSlaCalendarMasterSid(int slaCalendarMasterSid);

    /**
     * Returns the success mail subject of this workflow profile.
     *
     * @return the success mail subject of this workflow profile
     */
    @AutoEscape
    public String getSuccessMailSubject();

    /**
     * Sets the success mail subject of this workflow profile.
     *
     * @param successMailSubject the success mail subject of this workflow profile
     */
    public void setSuccessMailSubject(String successMailSubject);

    /**
     * Returns the start hour3 of this workflow profile.
     *
     * @return the start hour3 of this workflow profile
     */
    public int getStartHour3();

    /**
     * Sets the start hour3 of this workflow profile.
     *
     * @param startHour3 the start hour3 of this workflow profile
     */
    public void setStartHour3(int startHour3);

    /**
     * Returns the start hour2 of this workflow profile.
     *
     * @return the start hour2 of this workflow profile
     */
    public int getStartHour2();

    /**
     * Sets the start hour2 of this workflow profile.
     *
     * @param startHour2 the start hour2 of this workflow profile
     */
    public void setStartHour2(int startHour2);

    /**
     * Returns the user sid of this workflow profile.
     *
     * @return the user sid of this workflow profile
     */
    public int getUserSid();

    /**
     * Sets the user sid of this workflow profile.
     *
     * @param userSid the user sid of this workflow profile
     */
    public void setUserSid(int userSid);

    /**
     * Returns the start hour1 of this workflow profile.
     *
     * @return the start hour1 of this workflow profile
     */
    public int getStartHour1();

    /**
     * Sets the start hour1 of this workflow profile.
     *
     * @param startHour1 the start hour1 of this workflow profile
     */
    public void setStartHour1(int startHour1);

    /**
     * Returns the process type of this workflow profile.
     *
     * @return the process type of this workflow profile
     */
    public int getProcessType();

    /**
     * Sets the process type of this workflow profile.
     *
     * @param processType the process type of this workflow profile
     */
    public void setProcessType(int processType);

    /**
     * Returns the script name of this workflow profile.
     *
     * @return the script name of this workflow profile
     */
    @AutoEscape
    public String getScriptName();

    /**
     * Sets the script name of this workflow profile.
     *
     * @param scriptName the script name of this workflow profile
     */
    public void setScriptName(String scriptName);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(WorkflowProfile workflowProfile);

    @Override
    public int hashCode();

    @Override
    public CacheModel<WorkflowProfile> toCacheModel();

    @Override
    public WorkflowProfile toEscapedModel();

    @Override
    public WorkflowProfile toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
