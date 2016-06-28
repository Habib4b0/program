package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.SlaCalendarDetailsLocalServiceUtil;

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


public class SlaCalendarDetailsClp extends BaseModelImpl<SlaCalendarDetails>
    implements SlaCalendarDetails {
    private Date _createdDate;
    private int _createdBy;
    private int _slaCalendarMasterSid;
    private String _holidayYear;
    private int _slaCalendarDetailsSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _holidayDay;
    private Date _modifiedDate;
    private Date _holidayCombined;
    private String _holidayMonth;
    private BaseModel<?> _slaCalendarDetailsRemoteModel;

    public SlaCalendarDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return SlaCalendarDetails.class;
    }

    @Override
    public String getModelClassName() {
        return SlaCalendarDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _slaCalendarDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setSlaCalendarDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _slaCalendarDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
        attributes.put("holidayYear", getHolidayYear());
        attributes.put("slaCalendarDetailsSid", getSlaCalendarDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("holidayDay", getHolidayDay());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("holidayCombined", getHolidayCombined());
        attributes.put("holidayMonth", getHolidayMonth());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer slaCalendarMasterSid = (Integer) attributes.get(
                "slaCalendarMasterSid");

        if (slaCalendarMasterSid != null) {
            setSlaCalendarMasterSid(slaCalendarMasterSid);
        }

        String holidayYear = (String) attributes.get("holidayYear");

        if (holidayYear != null) {
            setHolidayYear(holidayYear);
        }

        Integer slaCalendarDetailsSid = (Integer) attributes.get(
                "slaCalendarDetailsSid");

        if (slaCalendarDetailsSid != null) {
            setSlaCalendarDetailsSid(slaCalendarDetailsSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String holidayDay = (String) attributes.get("holidayDay");

        if (holidayDay != null) {
            setHolidayDay(holidayDay);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date holidayCombined = (Date) attributes.get("holidayCombined");

        if (holidayCombined != null) {
            setHolidayCombined(holidayCombined);
        }

        String holidayMonth = (String) attributes.get("holidayMonth");

        if (holidayMonth != null) {
            setHolidayMonth(holidayMonth);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_slaCalendarDetailsRemoteModel, createdDate);
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

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_slaCalendarDetailsRemoteModel, createdBy);
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

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSlaCalendarMasterSid",
                        int.class);

                method.invoke(_slaCalendarDetailsRemoteModel,
                    slaCalendarMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHolidayYear() {
        return _holidayYear;
    }

    @Override
    public void setHolidayYear(String holidayYear) {
        _holidayYear = holidayYear;

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHolidayYear", String.class);

                method.invoke(_slaCalendarDetailsRemoteModel, holidayYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSlaCalendarDetailsSid() {
        return _slaCalendarDetailsSid;
    }

    @Override
    public void setSlaCalendarDetailsSid(int slaCalendarDetailsSid) {
        _slaCalendarDetailsSid = slaCalendarDetailsSid;

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSlaCalendarDetailsSid",
                        int.class);

                method.invoke(_slaCalendarDetailsRemoteModel,
                    slaCalendarDetailsSid);
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

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_slaCalendarDetailsRemoteModel, modifiedBy);
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

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_slaCalendarDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHolidayDay() {
        return _holidayDay;
    }

    @Override
    public void setHolidayDay(String holidayDay) {
        _holidayDay = holidayDay;

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHolidayDay", String.class);

                method.invoke(_slaCalendarDetailsRemoteModel, holidayDay);
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

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_slaCalendarDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getHolidayCombined() {
        return _holidayCombined;
    }

    @Override
    public void setHolidayCombined(Date holidayCombined) {
        _holidayCombined = holidayCombined;

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHolidayCombined", Date.class);

                method.invoke(_slaCalendarDetailsRemoteModel, holidayCombined);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHolidayMonth() {
        return _holidayMonth;
    }

    @Override
    public void setHolidayMonth(String holidayMonth) {
        _holidayMonth = holidayMonth;

        if (_slaCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _slaCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setHolidayMonth", String.class);

                method.invoke(_slaCalendarDetailsRemoteModel, holidayMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getSlaCalendarDetailsRemoteModel() {
        return _slaCalendarDetailsRemoteModel;
    }

    public void setSlaCalendarDetailsRemoteModel(
        BaseModel<?> slaCalendarDetailsRemoteModel) {
        _slaCalendarDetailsRemoteModel = slaCalendarDetailsRemoteModel;
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

        Class<?> remoteModelClass = _slaCalendarDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_slaCalendarDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SlaCalendarDetailsLocalServiceUtil.addSlaCalendarDetails(this);
        } else {
            SlaCalendarDetailsLocalServiceUtil.updateSlaCalendarDetails(this);
        }
    }

    @Override
    public SlaCalendarDetails toEscapedModel() {
        return (SlaCalendarDetails) ProxyUtil.newProxyInstance(SlaCalendarDetails.class.getClassLoader(),
            new Class[] { SlaCalendarDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SlaCalendarDetailsClp clone = new SlaCalendarDetailsClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSlaCalendarMasterSid(getSlaCalendarMasterSid());
        clone.setHolidayYear(getHolidayYear());
        clone.setSlaCalendarDetailsSid(getSlaCalendarDetailsSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setHolidayDay(getHolidayDay());
        clone.setModifiedDate(getModifiedDate());
        clone.setHolidayCombined(getHolidayCombined());
        clone.setHolidayMonth(getHolidayMonth());

        return clone;
    }

    @Override
    public int compareTo(SlaCalendarDetails slaCalendarDetails) {
        int primaryKey = slaCalendarDetails.getPrimaryKey();

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

        if (!(obj instanceof SlaCalendarDetailsClp)) {
            return false;
        }

        SlaCalendarDetailsClp slaCalendarDetails = (SlaCalendarDetailsClp) obj;

        int primaryKey = slaCalendarDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", slaCalendarMasterSid=");
        sb.append(getSlaCalendarMasterSid());
        sb.append(", holidayYear=");
        sb.append(getHolidayYear());
        sb.append(", slaCalendarDetailsSid=");
        sb.append(getSlaCalendarDetailsSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", holidayDay=");
        sb.append(getHolidayDay());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", holidayCombined=");
        sb.append(getHolidayCombined());
        sb.append(", holidayMonth=");
        sb.append(getHolidayMonth());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.SlaCalendarDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>slaCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getSlaCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>holidayYear</column-name><column-value><![CDATA[");
        sb.append(getHolidayYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>slaCalendarDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getSlaCalendarDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>holidayDay</column-name><column-value><![CDATA[");
        sb.append(getHolidayDay());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>holidayCombined</column-name><column-value><![CDATA[");
        sb.append(getHolidayCombined());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>holidayMonth</column-name><column-value><![CDATA[");
        sb.append(getHolidayMonth());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
