package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.PeriodLocalServiceUtil;

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


public class PeriodClp extends BaseModelImpl<Period> implements Period {
    private int _periodSid;
    private Date _periodDate;
    private int _quarter;
    private int _year;
    private int _semiAnnual;
    private int _month;
    private BaseModel<?> _periodRemoteModel;

    public PeriodClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Period.class;
    }

    @Override
    public String getModelClassName() {
        return Period.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _periodSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setPeriodSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _periodSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("periodDate", getPeriodDate());
        attributes.put("quarter", getQuarter());
        attributes.put("year", getYear());
        attributes.put("semiAnnual", getSemiAnnual());
        attributes.put("month", getMonth());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Date periodDate = (Date) attributes.get("periodDate");

        if (periodDate != null) {
            setPeriodDate(periodDate);
        }

        Integer quarter = (Integer) attributes.get("quarter");

        if (quarter != null) {
            setQuarter(quarter);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Integer semiAnnual = (Integer) attributes.get("semiAnnual");

        if (semiAnnual != null) {
            setSemiAnnual(semiAnnual);
        }

        Integer month = (Integer) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_periodRemoteModel != null) {
            try {
                Class<?> clazz = _periodRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_periodRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPeriodDate() {
        return _periodDate;
    }

    @Override
    public void setPeriodDate(Date periodDate) {
        _periodDate = periodDate;

        if (_periodRemoteModel != null) {
            try {
                Class<?> clazz = _periodRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodDate", Date.class);

                method.invoke(_periodRemoteModel, periodDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getQuarter() {
        return _quarter;
    }

    @Override
    public void setQuarter(int quarter) {
        _quarter = quarter;

        if (_periodRemoteModel != null) {
            try {
                Class<?> clazz = _periodRemoteModel.getClass();

                Method method = clazz.getMethod("setQuarter", int.class);

                method.invoke(_periodRemoteModel, quarter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getYear() {
        return _year;
    }

    @Override
    public void setYear(int year) {
        _year = year;

        if (_periodRemoteModel != null) {
            try {
                Class<?> clazz = _periodRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", int.class);

                method.invoke(_periodRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSemiAnnual() {
        return _semiAnnual;
    }

    @Override
    public void setSemiAnnual(int semiAnnual) {
        _semiAnnual = semiAnnual;

        if (_periodRemoteModel != null) {
            try {
                Class<?> clazz = _periodRemoteModel.getClass();

                Method method = clazz.getMethod("setSemiAnnual", int.class);

                method.invoke(_periodRemoteModel, semiAnnual);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMonth() {
        return _month;
    }

    @Override
    public void setMonth(int month) {
        _month = month;

        if (_periodRemoteModel != null) {
            try {
                Class<?> clazz = _periodRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", int.class);

                method.invoke(_periodRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPeriodRemoteModel() {
        return _periodRemoteModel;
    }

    public void setPeriodRemoteModel(BaseModel<?> periodRemoteModel) {
        _periodRemoteModel = periodRemoteModel;
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

        Class<?> remoteModelClass = _periodRemoteModel.getClass();

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

        Object returnValue = method.invoke(_periodRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PeriodLocalServiceUtil.addPeriod(this);
        } else {
            PeriodLocalServiceUtil.updatePeriod(this);
        }
    }

    @Override
    public Period toEscapedModel() {
        return (Period) ProxyUtil.newProxyInstance(Period.class.getClassLoader(),
            new Class[] { Period.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PeriodClp clone = new PeriodClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setPeriodDate(getPeriodDate());
        clone.setQuarter(getQuarter());
        clone.setYear(getYear());
        clone.setSemiAnnual(getSemiAnnual());
        clone.setMonth(getMonth());

        return clone;
    }

    @Override
    public int compareTo(Period period) {
        int primaryKey = period.getPrimaryKey();

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

        if (!(obj instanceof PeriodClp)) {
            return false;
        }

        PeriodClp period = (PeriodClp) obj;

        int primaryKey = period.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{periodSid=");
        sb.append(getPeriodSid());
        sb.append(", periodDate=");
        sb.append(getPeriodDate());
        sb.append(", quarter=");
        sb.append(getQuarter());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", semiAnnual=");
        sb.append(getSemiAnnual());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.Period");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodDate</column-name><column-value><![CDATA[");
        sb.append(getPeriodDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quarter</column-name><column-value><![CDATA[");
        sb.append(getQuarter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>semiAnnual</column-name><column-value><![CDATA[");
        sb.append(getSemiAnnual());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
