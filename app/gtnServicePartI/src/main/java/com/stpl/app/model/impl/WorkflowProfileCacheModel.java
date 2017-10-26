package com.stpl.app.model.impl;

import com.stpl.app.model.WorkflowProfile;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowProfile in entity cache.
 *
 * @author
 * @see WorkflowProfile
 * @generated
 */
public class WorkflowProfileCacheModel implements CacheModel<WorkflowProfile>,
    Externalizable {
    public int startHour;
    public String frequency;
    public String processName;
    public int startMinutes1;
    public long endDate;
    public String emailNotificationFailureCc;
    public String failureMailSubject;
    public long modifiedDate;
    public String schemaName;
    public int createdBy;
    public long createdDate;
    public long scheduleLastRun;
    public String emailNotificationSuccessTo;
    public int startMinutes3;
    public int startMinutes2;
    public int processSid;
    public String successMailBody;
    public String inboundStatus;
    public int modifiedBy;
    public String emailNotificationSuccessCc;
    public String emailNotificationFailureTo;
    public String failureMailBody;
    public String activeFlag;
    public String processDisplayName;
    public int startMinutes;
    public long manualLastRun;
    public long startDate;
    public int slaCalendarMasterSid;
    public String successMailSubject;
    public int startHour3;
    public int startHour2;
    public int userSid;
    public int startHour1;
    public int processType;
    public String scriptName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(71);

        sb.append("{startHour=");
        sb.append(startHour);
        sb.append(", frequency=");
        sb.append(frequency);
        sb.append(", processName=");
        sb.append(processName);
        sb.append(", startMinutes1=");
        sb.append(startMinutes1);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", emailNotificationFailureCc=");
        sb.append(emailNotificationFailureCc);
        sb.append(", failureMailSubject=");
        sb.append(failureMailSubject);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", schemaName=");
        sb.append(schemaName);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", scheduleLastRun=");
        sb.append(scheduleLastRun);
        sb.append(", emailNotificationSuccessTo=");
        sb.append(emailNotificationSuccessTo);
        sb.append(", startMinutes3=");
        sb.append(startMinutes3);
        sb.append(", startMinutes2=");
        sb.append(startMinutes2);
        sb.append(", processSid=");
        sb.append(processSid);
        sb.append(", successMailBody=");
        sb.append(successMailBody);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", emailNotificationSuccessCc=");
        sb.append(emailNotificationSuccessCc);
        sb.append(", emailNotificationFailureTo=");
        sb.append(emailNotificationFailureTo);
        sb.append(", failureMailBody=");
        sb.append(failureMailBody);
        sb.append(", activeFlag=");
        sb.append(activeFlag);
        sb.append(", processDisplayName=");
        sb.append(processDisplayName);
        sb.append(", startMinutes=");
        sb.append(startMinutes);
        sb.append(", manualLastRun=");
        sb.append(manualLastRun);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", slaCalendarMasterSid=");
        sb.append(slaCalendarMasterSid);
        sb.append(", successMailSubject=");
        sb.append(successMailSubject);
        sb.append(", startHour3=");
        sb.append(startHour3);
        sb.append(", startHour2=");
        sb.append(startHour2);
        sb.append(", userSid=");
        sb.append(userSid);
        sb.append(", startHour1=");
        sb.append(startHour1);
        sb.append(", processType=");
        sb.append(processType);
        sb.append(", scriptName=");
        sb.append(scriptName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public WorkflowProfile toEntityModel() {
        WorkflowProfileImpl workflowProfileImpl = new WorkflowProfileImpl();

        workflowProfileImpl.setStartHour(startHour);

        if (frequency == null) {
            workflowProfileImpl.setFrequency(StringPool.BLANK);
        } else {
            workflowProfileImpl.setFrequency(frequency);
        }

        if (processName == null) {
            workflowProfileImpl.setProcessName(StringPool.BLANK);
        } else {
            workflowProfileImpl.setProcessName(processName);
        }

        workflowProfileImpl.setStartMinutes1(startMinutes1);

        if (endDate == Long.MIN_VALUE) {
            workflowProfileImpl.setEndDate(null);
        } else {
            workflowProfileImpl.setEndDate(new Date(endDate));
        }

        if (emailNotificationFailureCc == null) {
            workflowProfileImpl.setEmailNotificationFailureCc(StringPool.BLANK);
        } else {
            workflowProfileImpl.setEmailNotificationFailureCc(emailNotificationFailureCc);
        }

        if (failureMailSubject == null) {
            workflowProfileImpl.setFailureMailSubject(StringPool.BLANK);
        } else {
            workflowProfileImpl.setFailureMailSubject(failureMailSubject);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            workflowProfileImpl.setModifiedDate(null);
        } else {
            workflowProfileImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (schemaName == null) {
            workflowProfileImpl.setSchemaName(StringPool.BLANK);
        } else {
            workflowProfileImpl.setSchemaName(schemaName);
        }

        workflowProfileImpl.setCreatedBy(createdBy);

        if (createdDate == Long.MIN_VALUE) {
            workflowProfileImpl.setCreatedDate(null);
        } else {
            workflowProfileImpl.setCreatedDate(new Date(createdDate));
        }

        if (scheduleLastRun == Long.MIN_VALUE) {
            workflowProfileImpl.setScheduleLastRun(null);
        } else {
            workflowProfileImpl.setScheduleLastRun(new Date(scheduleLastRun));
        }

        if (emailNotificationSuccessTo == null) {
            workflowProfileImpl.setEmailNotificationSuccessTo(StringPool.BLANK);
        } else {
            workflowProfileImpl.setEmailNotificationSuccessTo(emailNotificationSuccessTo);
        }

        workflowProfileImpl.setStartMinutes3(startMinutes3);
        workflowProfileImpl.setStartMinutes2(startMinutes2);
        workflowProfileImpl.setProcessSid(processSid);

        if (successMailBody == null) {
            workflowProfileImpl.setSuccessMailBody(StringPool.BLANK);
        } else {
            workflowProfileImpl.setSuccessMailBody(successMailBody);
        }

        if (inboundStatus == null) {
            workflowProfileImpl.setInboundStatus(StringPool.BLANK);
        } else {
            workflowProfileImpl.setInboundStatus(inboundStatus);
        }

        workflowProfileImpl.setModifiedBy(modifiedBy);

        if (emailNotificationSuccessCc == null) {
            workflowProfileImpl.setEmailNotificationSuccessCc(StringPool.BLANK);
        } else {
            workflowProfileImpl.setEmailNotificationSuccessCc(emailNotificationSuccessCc);
        }

        if (emailNotificationFailureTo == null) {
            workflowProfileImpl.setEmailNotificationFailureTo(StringPool.BLANK);
        } else {
            workflowProfileImpl.setEmailNotificationFailureTo(emailNotificationFailureTo);
        }

        if (failureMailBody == null) {
            workflowProfileImpl.setFailureMailBody(StringPool.BLANK);
        } else {
            workflowProfileImpl.setFailureMailBody(failureMailBody);
        }

        if (activeFlag == null) {
            workflowProfileImpl.setActiveFlag(StringPool.BLANK);
        } else {
            workflowProfileImpl.setActiveFlag(activeFlag);
        }

        if (processDisplayName == null) {
            workflowProfileImpl.setProcessDisplayName(StringPool.BLANK);
        } else {
            workflowProfileImpl.setProcessDisplayName(processDisplayName);
        }

        workflowProfileImpl.setStartMinutes(startMinutes);

        if (manualLastRun == Long.MIN_VALUE) {
            workflowProfileImpl.setManualLastRun(null);
        } else {
            workflowProfileImpl.setManualLastRun(new Date(manualLastRun));
        }

        if (startDate == Long.MIN_VALUE) {
            workflowProfileImpl.setStartDate(null);
        } else {
            workflowProfileImpl.setStartDate(new Date(startDate));
        }

        workflowProfileImpl.setSlaCalendarMasterSid(slaCalendarMasterSid);

        if (successMailSubject == null) {
            workflowProfileImpl.setSuccessMailSubject(StringPool.BLANK);
        } else {
            workflowProfileImpl.setSuccessMailSubject(successMailSubject);
        }

        workflowProfileImpl.setStartHour3(startHour3);
        workflowProfileImpl.setStartHour2(startHour2);
        workflowProfileImpl.setUserSid(userSid);
        workflowProfileImpl.setStartHour1(startHour1);
        workflowProfileImpl.setProcessType(processType);

        if (scriptName == null) {
            workflowProfileImpl.setScriptName(StringPool.BLANK);
        } else {
            workflowProfileImpl.setScriptName(scriptName);
        }

        workflowProfileImpl.resetOriginalValues();

        return workflowProfileImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        startHour = objectInput.readInt();
        frequency = objectInput.readUTF();
        processName = objectInput.readUTF();
        startMinutes1 = objectInput.readInt();
        endDate = objectInput.readLong();
        emailNotificationFailureCc = objectInput.readUTF();
        failureMailSubject = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        schemaName = objectInput.readUTF();
        createdBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        scheduleLastRun = objectInput.readLong();
        emailNotificationSuccessTo = objectInput.readUTF();
        startMinutes3 = objectInput.readInt();
        startMinutes2 = objectInput.readInt();
        processSid = objectInput.readInt();
        successMailBody = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        emailNotificationSuccessCc = objectInput.readUTF();
        emailNotificationFailureTo = objectInput.readUTF();
        failureMailBody = objectInput.readUTF();
        activeFlag = objectInput.readUTF();
        processDisplayName = objectInput.readUTF();
        startMinutes = objectInput.readInt();
        manualLastRun = objectInput.readLong();
        startDate = objectInput.readLong();
        slaCalendarMasterSid = objectInput.readInt();
        successMailSubject = objectInput.readUTF();
        startHour3 = objectInput.readInt();
        startHour2 = objectInput.readInt();
        userSid = objectInput.readInt();
        startHour1 = objectInput.readInt();
        processType = objectInput.readInt();
        scriptName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(startHour);

        if (frequency == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(frequency);
        }

        if (processName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(processName);
        }

        objectOutput.writeInt(startMinutes1);
        objectOutput.writeLong(endDate);

        if (emailNotificationFailureCc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailNotificationFailureCc);
        }

        if (failureMailSubject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(failureMailSubject);
        }

        objectOutput.writeLong(modifiedDate);

        if (schemaName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(schemaName);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeLong(scheduleLastRun);

        if (emailNotificationSuccessTo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailNotificationSuccessTo);
        }

        objectOutput.writeInt(startMinutes3);
        objectOutput.writeInt(startMinutes2);
        objectOutput.writeInt(processSid);

        if (successMailBody == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(successMailBody);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(modifiedBy);

        if (emailNotificationSuccessCc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailNotificationSuccessCc);
        }

        if (emailNotificationFailureTo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailNotificationFailureTo);
        }

        if (failureMailBody == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(failureMailBody);
        }

        if (activeFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(activeFlag);
        }

        if (processDisplayName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(processDisplayName);
        }

        objectOutput.writeInt(startMinutes);
        objectOutput.writeLong(manualLastRun);
        objectOutput.writeLong(startDate);
        objectOutput.writeInt(slaCalendarMasterSid);

        if (successMailSubject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(successMailSubject);
        }

        objectOutput.writeInt(startHour3);
        objectOutput.writeInt(startHour2);
        objectOutput.writeInt(userSid);
        objectOutput.writeInt(startHour1);
        objectOutput.writeInt(processType);

        if (scriptName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(scriptName);
        }
    }
}
