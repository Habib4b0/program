package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;

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


public class SlaCalendarMasterClp extends BaseModelImpl<SlaCalendarMaster>
    implements SlaCalendarMaster {
    private int _createdBy;
    private int _modifiedBy;
    private int _slaCalendarMasterSid;
    private Date _createdDate;
    private boolean _defaultHolidays;
    private String _calendarName;
    private Date _modifiedDate;
    private String _inboundStatus;
    private BaseModel<?> _slaCalendarMasterRemoteModel;

    public SlaCalendarMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return SlaCalendarMaster.class;
    }

    @Override
    public String getModelClassName() {
        return SlaCalendarMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _slaCalendarMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setSlaCalendarMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _slaCalendarMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("defaultHolidays", getDefaultHolidays());
        attributes.put("calendarName", getCalendarName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer slaCalendarMasterSid = (Integer) attributes.get(
                "slaCalendarMasterSid");

        if (slaCalendarMasterSid != null) {
            setSlaCalendarMasterSid(slaCalendarMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Boolean defaultHolidays = (Boolean) attributes.get("defaultHolidays");

        if (defaultHolidays != null) {
            setDefaultHolidays(defaultHolidays);
        }

        String calendarName = (String) attributes.get("calendarName");

        if (calendarName != null) {
            setCalendarName(calendarName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_slaCalendarMasterRemoteModel, createdBy);
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

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_slaCalendarMasterRemoteModel, modifiedBy);
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

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSlaCalendarMasterSid",
                        int.class);

                method.invoke(_slaCalendarMasterRemoteModel,
                    slaCalendarMasterSid);
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

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_slaCalendarMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getDefaultHolidays() {
        return _defaultHolidays;
    }

    @Override
    public boolean isDefaultHolidays() {
        return _defaultHolidays;
    }

    @Override
    public void setDefaultHolidays(boolean defaultHolidays) {
        _defaultHolidays = defaultHolidays;

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDefaultHolidays",
                        boolean.class);

                method.invoke(_slaCalendarMasterRemoteModel, defaultHolidays);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalendarName() {
        return _calendarName;
    }

    @Override
    public void setCalendarName(String calendarName) {
        _calendarName = calendarName;

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalendarName", String.class);

                method.invoke(_slaCalendarMasterRemoteModel, calendarName);
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

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_slaCalendarMasterRemoteModel, modifiedDate);
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

        if (_slaCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_slaCalendarMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getSlaCalendarMasterRemoteModel() {
        return _slaCalendarMasterRemoteModel;
    }

    public void setSlaCalendarMasterRemoteModel(
        BaseModel<?> slaCalendarMasterRemoteModel) {
        _slaCalendarMasterRemoteModel = slaCalendarMasterRemoteModel;
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

        Class<?> remoteModelClass = _slaCalendarMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_slaCalendarMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SlaCalendarMasterLocalServiceUtil.addSlaCalendarMaster(this);
        } else {
            SlaCalendarMasterLocalServiceUtil.updateSlaCalendarMaster(this);
        }
    }

    @Override
    public SlaCalendarMaster toEscapedModel() {
        return (SlaCalendarMaster) ProxyUtil.newProxyInstance(SlaCalendarMaster.class.getClassLoader(),
            new Class[] { SlaCalendarMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SlaCalendarMasterClp clone = new SlaCalendarMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setModifiedBy(getModifiedBy());
        clone.setSlaCalendarMasterSid(getSlaCalendarMasterSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setDefaultHolidays(getDefaultHolidays());
        clone.setCalendarName(getCalendarName());
        clone.setModifiedDate(getModifiedDate());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(SlaCalendarMaster slaCalendarMaster) {
        int primaryKey = slaCalendarMaster.getPrimaryKey();

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

        if (!(obj instanceof SlaCalendarMasterClp)) {
            return false;
        }

        SlaCalendarMasterClp slaCalendarMaster = (SlaCalendarMasterClp) obj;

        int primaryKey = slaCalendarMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", slaCalendarMasterSid=");
        sb.append(getSlaCalendarMasterSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", defaultHolidays=");
        sb.append(getDefaultHolidays());
        sb.append(", calendarName=");
        sb.append(getCalendarName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.SlaCalendarMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>slaCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getSlaCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultHolidays</column-name><column-value><![CDATA[");
        sb.append(getDefaultHolidays());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calendarName</column-name><column-value><![CDATA[");
        sb.append(getCalendarName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
