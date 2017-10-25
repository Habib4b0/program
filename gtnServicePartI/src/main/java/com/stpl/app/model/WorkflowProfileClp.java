package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class WorkflowProfileClp extends BaseModelImpl<WorkflowProfile>
    implements WorkflowProfile {
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
    private BaseModel<?> _workflowProfileRemoteModel;

    public WorkflowProfileClp() {
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
    public int getPrimaryKey() {
        return _processSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProcessSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _processSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
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
        Integer startHour = (Integer) attributes.get("startHour");

        if (startHour != null) {
            setStartHour(startHour);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        String processName = (String) attributes.get("processName");

        if (processName != null) {
            setProcessName(processName);
        }

        Integer startMinutes1 = (Integer) attributes.get("startMinutes1");

        if (startMinutes1 != null) {
            setStartMinutes1(startMinutes1);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String emailNotificationFailureCc = (String) attributes.get(
                "emailNotificationFailureCc");

        if (emailNotificationFailureCc != null) {
            setEmailNotificationFailureCc(emailNotificationFailureCc);
        }

        String failureMailSubject = (String) attributes.get(
                "failureMailSubject");

        if (failureMailSubject != null) {
            setFailureMailSubject(failureMailSubject);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String schemaName = (String) attributes.get("schemaName");

        if (schemaName != null) {
            setSchemaName(schemaName);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date scheduleLastRun = (Date) attributes.get("scheduleLastRun");

        if (scheduleLastRun != null) {
            setScheduleLastRun(scheduleLastRun);
        }

        String emailNotificationSuccessTo = (String) attributes.get(
                "emailNotificationSuccessTo");

        if (emailNotificationSuccessTo != null) {
            setEmailNotificationSuccessTo(emailNotificationSuccessTo);
        }

        Integer startMinutes3 = (Integer) attributes.get("startMinutes3");

        if (startMinutes3 != null) {
            setStartMinutes3(startMinutes3);
        }

        Integer startMinutes2 = (Integer) attributes.get("startMinutes2");

        if (startMinutes2 != null) {
            setStartMinutes2(startMinutes2);
        }

        Integer processSid = (Integer) attributes.get("processSid");

        if (processSid != null) {
            setProcessSid(processSid);
        }

        String successMailBody = (String) attributes.get("successMailBody");

        if (successMailBody != null) {
            setSuccessMailBody(successMailBody);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String emailNotificationSuccessCc = (String) attributes.get(
                "emailNotificationSuccessCc");

        if (emailNotificationSuccessCc != null) {
            setEmailNotificationSuccessCc(emailNotificationSuccessCc);
        }

        String emailNotificationFailureTo = (String) attributes.get(
                "emailNotificationFailureTo");

        if (emailNotificationFailureTo != null) {
            setEmailNotificationFailureTo(emailNotificationFailureTo);
        }

        String failureMailBody = (String) attributes.get("failureMailBody");

        if (failureMailBody != null) {
            setFailureMailBody(failureMailBody);
        }

        String activeFlag = (String) attributes.get("activeFlag");

        if (activeFlag != null) {
            setActiveFlag(activeFlag);
        }

        String processDisplayName = (String) attributes.get(
                "processDisplayName");

        if (processDisplayName != null) {
            setProcessDisplayName(processDisplayName);
        }

        Integer startMinutes = (Integer) attributes.get("startMinutes");

        if (startMinutes != null) {
            setStartMinutes(startMinutes);
        }

        Date manualLastRun = (Date) attributes.get("manualLastRun");

        if (manualLastRun != null) {
            setManualLastRun(manualLastRun);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Integer slaCalendarMasterSid = (Integer) attributes.get(
                "slaCalendarMasterSid");

        if (slaCalendarMasterSid != null) {
            setSlaCalendarMasterSid(slaCalendarMasterSid);
        }

        String successMailSubject = (String) attributes.get(
                "successMailSubject");

        if (successMailSubject != null) {
            setSuccessMailSubject(successMailSubject);
        }

        Integer startHour3 = (Integer) attributes.get("startHour3");

        if (startHour3 != null) {
            setStartHour3(startHour3);
        }

        Integer startHour2 = (Integer) attributes.get("startHour2");

        if (startHour2 != null) {
            setStartHour2(startHour2);
        }

        Integer userSid = (Integer) attributes.get("userSid");

        if (userSid != null) {
            setUserSid(userSid);
        }

        Integer startHour1 = (Integer) attributes.get("startHour1");

        if (startHour1 != null) {
            setStartHour1(startHour1);
        }

        Integer processType = (Integer) attributes.get("processType");

        if (processType != null) {
            setProcessType(processType);
        }

        String scriptName = (String) attributes.get("scriptName");

        if (scriptName != null) {
            setScriptName(scriptName);
        }
    }

    @Override
    public int getStartHour() {
        return _startHour;
    }

    @Override
    public void setStartHour(int startHour) {
        _startHour = startHour;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartHour", int.class);

                method.invoke(_workflowProfileRemoteModel, startHour);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFrequency() {
        return _frequency;
    }

    @Override
    public void setFrequency(String frequency) {
        _frequency = frequency;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setFrequency", String.class);

                method.invoke(_workflowProfileRemoteModel, frequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProcessName() {
        return _processName;
    }

    @Override
    public void setProcessName(String processName) {
        _processName = processName;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessName", String.class);

                method.invoke(_workflowProfileRemoteModel, processName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartMinutes1() {
        return _startMinutes1;
    }

    @Override
    public void setStartMinutes1(int startMinutes1) {
        _startMinutes1 = startMinutes1;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartMinutes1", int.class);

                method.invoke(_workflowProfileRemoteModel, startMinutes1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_workflowProfileRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmailNotificationFailureCc() {
        return _emailNotificationFailureCc;
    }

    @Override
    public void setEmailNotificationFailureCc(String emailNotificationFailureCc) {
        _emailNotificationFailureCc = emailNotificationFailureCc;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailNotificationFailureCc",
                        String.class);

                method.invoke(_workflowProfileRemoteModel,
                    emailNotificationFailureCc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFailureMailSubject() {
        return _failureMailSubject;
    }

    @Override
    public void setFailureMailSubject(String failureMailSubject) {
        _failureMailSubject = failureMailSubject;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setFailureMailSubject",
                        String.class);

                method.invoke(_workflowProfileRemoteModel, failureMailSubject);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_workflowProfileRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSchemaName() {
        return _schemaName;
    }

    @Override
    public void setSchemaName(String schemaName) {
        _schemaName = schemaName;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setSchemaName", String.class);

                method.invoke(_workflowProfileRemoteModel, schemaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_workflowProfileRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_workflowProfileRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getScheduleLastRun() {
        return _scheduleLastRun;
    }

    @Override
    public void setScheduleLastRun(Date scheduleLastRun) {
        _scheduleLastRun = scheduleLastRun;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setScheduleLastRun", Date.class);

                method.invoke(_workflowProfileRemoteModel, scheduleLastRun);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmailNotificationSuccessTo() {
        return _emailNotificationSuccessTo;
    }

    @Override
    public void setEmailNotificationSuccessTo(String emailNotificationSuccessTo) {
        _emailNotificationSuccessTo = emailNotificationSuccessTo;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailNotificationSuccessTo",
                        String.class);

                method.invoke(_workflowProfileRemoteModel,
                    emailNotificationSuccessTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartMinutes3() {
        return _startMinutes3;
    }

    @Override
    public void setStartMinutes3(int startMinutes3) {
        _startMinutes3 = startMinutes3;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartMinutes3", int.class);

                method.invoke(_workflowProfileRemoteModel, startMinutes3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartMinutes2() {
        return _startMinutes2;
    }

    @Override
    public void setStartMinutes2(int startMinutes2) {
        _startMinutes2 = startMinutes2;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartMinutes2", int.class);

                method.invoke(_workflowProfileRemoteModel, startMinutes2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProcessSid() {
        return _processSid;
    }

    @Override
    public void setProcessSid(int processSid) {
        _processSid = processSid;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessSid", int.class);

                method.invoke(_workflowProfileRemoteModel, processSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSuccessMailBody() {
        return _successMailBody;
    }

    @Override
    public void setSuccessMailBody(String successMailBody) {
        _successMailBody = successMailBody;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setSuccessMailBody",
                        String.class);

                method.invoke(_workflowProfileRemoteModel, successMailBody);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_workflowProfileRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_workflowProfileRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmailNotificationSuccessCc() {
        return _emailNotificationSuccessCc;
    }

    @Override
    public void setEmailNotificationSuccessCc(String emailNotificationSuccessCc) {
        _emailNotificationSuccessCc = emailNotificationSuccessCc;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailNotificationSuccessCc",
                        String.class);

                method.invoke(_workflowProfileRemoteModel,
                    emailNotificationSuccessCc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmailNotificationFailureTo() {
        return _emailNotificationFailureTo;
    }

    @Override
    public void setEmailNotificationFailureTo(String emailNotificationFailureTo) {
        _emailNotificationFailureTo = emailNotificationFailureTo;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailNotificationFailureTo",
                        String.class);

                method.invoke(_workflowProfileRemoteModel,
                    emailNotificationFailureTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFailureMailBody() {
        return _failureMailBody;
    }

    @Override
    public void setFailureMailBody(String failureMailBody) {
        _failureMailBody = failureMailBody;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setFailureMailBody",
                        String.class);

                method.invoke(_workflowProfileRemoteModel, failureMailBody);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActiveFlag() {
        return _activeFlag;
    }

    @Override
    public void setActiveFlag(String activeFlag) {
        _activeFlag = activeFlag;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setActiveFlag", String.class);

                method.invoke(_workflowProfileRemoteModel, activeFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProcessDisplayName() {
        return _processDisplayName;
    }

    @Override
    public void setProcessDisplayName(String processDisplayName) {
        _processDisplayName = processDisplayName;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessDisplayName",
                        String.class);

                method.invoke(_workflowProfileRemoteModel, processDisplayName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartMinutes() {
        return _startMinutes;
    }

    @Override
    public void setStartMinutes(int startMinutes) {
        _startMinutes = startMinutes;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartMinutes", int.class);

                method.invoke(_workflowProfileRemoteModel, startMinutes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getManualLastRun() {
        return _manualLastRun;
    }

    @Override
    public void setManualLastRun(Date manualLastRun) {
        _manualLastRun = manualLastRun;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setManualLastRun", Date.class);

                method.invoke(_workflowProfileRemoteModel, manualLastRun);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_workflowProfileRemoteModel, startDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSlaCalendarMasterSid() {
        return _slaCalendarMasterSid;
    }

    @Override
    public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
        _slaCalendarMasterSid = slaCalendarMasterSid;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setSlaCalendarMasterSid",
                        int.class);

                method.invoke(_workflowProfileRemoteModel, slaCalendarMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSuccessMailSubject() {
        return _successMailSubject;
    }

    @Override
    public void setSuccessMailSubject(String successMailSubject) {
        _successMailSubject = successMailSubject;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setSuccessMailSubject",
                        String.class);

                method.invoke(_workflowProfileRemoteModel, successMailSubject);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartHour3() {
        return _startHour3;
    }

    @Override
    public void setStartHour3(int startHour3) {
        _startHour3 = startHour3;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartHour3", int.class);

                method.invoke(_workflowProfileRemoteModel, startHour3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartHour2() {
        return _startHour2;
    }

    @Override
    public void setStartHour2(int startHour2) {
        _startHour2 = startHour2;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartHour2", int.class);

                method.invoke(_workflowProfileRemoteModel, startHour2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUserSid() {
        return _userSid;
    }

    @Override
    public void setUserSid(int userSid) {
        _userSid = userSid;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setUserSid", int.class);

                method.invoke(_workflowProfileRemoteModel, userSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStartHour1() {
        return _startHour1;
    }

    @Override
    public void setStartHour1(int startHour1) {
        _startHour1 = startHour1;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setStartHour1", int.class);

                method.invoke(_workflowProfileRemoteModel, startHour1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProcessType() {
        return _processType;
    }

    @Override
    public void setProcessType(int processType) {
        _processType = processType;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessType", int.class);

                method.invoke(_workflowProfileRemoteModel, processType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getScriptName() {
        return _scriptName;
    }

    @Override
    public void setScriptName(String scriptName) {
        _scriptName = scriptName;

        if (_workflowProfileRemoteModel != null) {
            try {
                Class<?> clazz = _workflowProfileRemoteModel.getClass();

                Method method = clazz.getMethod("setScriptName", String.class);

                method.invoke(_workflowProfileRemoteModel, scriptName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getWorkflowProfileRemoteModel() {
        return _workflowProfileRemoteModel;
    }

    public void setWorkflowProfileRemoteModel(
        BaseModel<?> workflowProfileRemoteModel) {
        _workflowProfileRemoteModel = workflowProfileRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _workflowProfileRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_workflowProfileRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            WorkflowProfileLocalServiceUtil.addWorkflowProfile(this);
        } else {
            WorkflowProfileLocalServiceUtil.updateWorkflowProfile(this);
        }
    }

    @Override
    public WorkflowProfile toEscapedModel() {
        return (WorkflowProfile) ProxyUtil.newProxyInstance(WorkflowProfile.class.getClassLoader(),
            new Class[] { WorkflowProfile.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        WorkflowProfileClp clone = new WorkflowProfileClp();

        clone.setStartHour(getStartHour());
        clone.setFrequency(getFrequency());
        clone.setProcessName(getProcessName());
        clone.setStartMinutes1(getStartMinutes1());
        clone.setEndDate(getEndDate());
        clone.setEmailNotificationFailureCc(getEmailNotificationFailureCc());
        clone.setFailureMailSubject(getFailureMailSubject());
        clone.setModifiedDate(getModifiedDate());
        clone.setSchemaName(getSchemaName());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setScheduleLastRun(getScheduleLastRun());
        clone.setEmailNotificationSuccessTo(getEmailNotificationSuccessTo());
        clone.setStartMinutes3(getStartMinutes3());
        clone.setStartMinutes2(getStartMinutes2());
        clone.setProcessSid(getProcessSid());
        clone.setSuccessMailBody(getSuccessMailBody());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setEmailNotificationSuccessCc(getEmailNotificationSuccessCc());
        clone.setEmailNotificationFailureTo(getEmailNotificationFailureTo());
        clone.setFailureMailBody(getFailureMailBody());
        clone.setActiveFlag(getActiveFlag());
        clone.setProcessDisplayName(getProcessDisplayName());
        clone.setStartMinutes(getStartMinutes());
        clone.setManualLastRun(getManualLastRun());
        clone.setStartDate(getStartDate());
        clone.setSlaCalendarMasterSid(getSlaCalendarMasterSid());
        clone.setSuccessMailSubject(getSuccessMailSubject());
        clone.setStartHour3(getStartHour3());
        clone.setStartHour2(getStartHour2());
        clone.setUserSid(getUserSid());
        clone.setStartHour1(getStartHour1());
        clone.setProcessType(getProcessType());
        clone.setScriptName(getScriptName());

        return clone;
    }

    @Override
    public int compareTo(WorkflowProfile workflowProfile) {
        int primaryKey = workflowProfile.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WorkflowProfileClp)) {
            return false;
        }

        WorkflowProfileClp workflowProfile = (WorkflowProfileClp) obj;

        int primaryKey = workflowProfile.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(71);

        sb.append("{startHour=");
        sb.append(getStartHour());
        sb.append(", frequency=");
        sb.append(getFrequency());
        sb.append(", processName=");
        sb.append(getProcessName());
        sb.append(", startMinutes1=");
        sb.append(getStartMinutes1());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", emailNotificationFailureCc=");
        sb.append(getEmailNotificationFailureCc());
        sb.append(", failureMailSubject=");
        sb.append(getFailureMailSubject());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", schemaName=");
        sb.append(getSchemaName());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", scheduleLastRun=");
        sb.append(getScheduleLastRun());
        sb.append(", emailNotificationSuccessTo=");
        sb.append(getEmailNotificationSuccessTo());
        sb.append(", startMinutes3=");
        sb.append(getStartMinutes3());
        sb.append(", startMinutes2=");
        sb.append(getStartMinutes2());
        sb.append(", processSid=");
        sb.append(getProcessSid());
        sb.append(", successMailBody=");
        sb.append(getSuccessMailBody());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", emailNotificationSuccessCc=");
        sb.append(getEmailNotificationSuccessCc());
        sb.append(", emailNotificationFailureTo=");
        sb.append(getEmailNotificationFailureTo());
        sb.append(", failureMailBody=");
        sb.append(getFailureMailBody());
        sb.append(", activeFlag=");
        sb.append(getActiveFlag());
        sb.append(", processDisplayName=");
        sb.append(getProcessDisplayName());
        sb.append(", startMinutes=");
        sb.append(getStartMinutes());
        sb.append(", manualLastRun=");
        sb.append(getManualLastRun());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", slaCalendarMasterSid=");
        sb.append(getSlaCalendarMasterSid());
        sb.append(", successMailSubject=");
        sb.append(getSuccessMailSubject());
        sb.append(", startHour3=");
        sb.append(getStartHour3());
        sb.append(", startHour2=");
        sb.append(getStartHour2());
        sb.append(", userSid=");
        sb.append(getUserSid());
        sb.append(", startHour1=");
        sb.append(getStartHour1());
        sb.append(", processType=");
        sb.append(getProcessType());
        sb.append(", scriptName=");
        sb.append(getScriptName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(109);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.WorkflowProfile");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>startHour</column-name><column-value><![CDATA[");
        sb.append(getStartHour());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>frequency</column-name><column-value><![CDATA[");
        sb.append(getFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processName</column-name><column-value><![CDATA[");
        sb.append(getProcessName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startMinutes1</column-name><column-value><![CDATA[");
        sb.append(getStartMinutes1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailNotificationFailureCc</column-name><column-value><![CDATA[");
        sb.append(getEmailNotificationFailureCc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>failureMailSubject</column-name><column-value><![CDATA[");
        sb.append(getFailureMailSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>schemaName</column-name><column-value><![CDATA[");
        sb.append(getSchemaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>scheduleLastRun</column-name><column-value><![CDATA[");
        sb.append(getScheduleLastRun());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailNotificationSuccessTo</column-name><column-value><![CDATA[");
        sb.append(getEmailNotificationSuccessTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startMinutes3</column-name><column-value><![CDATA[");
        sb.append(getStartMinutes3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startMinutes2</column-name><column-value><![CDATA[");
        sb.append(getStartMinutes2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processSid</column-name><column-value><![CDATA[");
        sb.append(getProcessSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>successMailBody</column-name><column-value><![CDATA[");
        sb.append(getSuccessMailBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailNotificationSuccessCc</column-name><column-value><![CDATA[");
        sb.append(getEmailNotificationSuccessCc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailNotificationFailureTo</column-name><column-value><![CDATA[");
        sb.append(getEmailNotificationFailureTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>failureMailBody</column-name><column-value><![CDATA[");
        sb.append(getFailureMailBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activeFlag</column-name><column-value><![CDATA[");
        sb.append(getActiveFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processDisplayName</column-name><column-value><![CDATA[");
        sb.append(getProcessDisplayName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startMinutes</column-name><column-value><![CDATA[");
        sb.append(getStartMinutes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manualLastRun</column-name><column-value><![CDATA[");
        sb.append(getManualLastRun());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>slaCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getSlaCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>successMailSubject</column-name><column-value><![CDATA[");
        sb.append(getSuccessMailSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startHour3</column-name><column-value><![CDATA[");
        sb.append(getStartHour3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startHour2</column-name><column-value><![CDATA[");
        sb.append(getStartHour2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userSid</column-name><column-value><![CDATA[");
        sb.append(getUserSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startHour1</column-name><column-value><![CDATA[");
        sb.append(getStartHour1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processType</column-name><column-value><![CDATA[");
        sb.append(getProcessType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>scriptName</column-name><column-value><![CDATA[");
        sb.append(getScriptName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
