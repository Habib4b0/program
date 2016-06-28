package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StMSupplementalDiscActualsLocalServiceUtil;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsPK;

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


public class StMSupplementalDiscActualsClp extends BaseModelImpl<StMSupplementalDiscActuals>
    implements StMSupplementalDiscActuals {
    private double _actualSales;
    private int _periodSid;
    private double _actualRate;
    private int _userId;
    private Date _lastModifiedDate;
    private double _actualProjectionSales;
    private double _actualProjectionRate;
    private int _projectionDetailsSid;
    private int _sessionId;
    private BaseModel<?> _stMSupplementalDiscActualsRemoteModel;

    public StMSupplementalDiscActualsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StMSupplementalDiscActuals.class;
    }

    @Override
    public String getModelClassName() {
        return StMSupplementalDiscActuals.class.getName();
    }

    @Override
    public StMSupplementalDiscActualsPK getPrimaryKey() {
        return new StMSupplementalDiscActualsPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StMSupplementalDiscActualsPK primaryKey) {
        setUserId(primaryKey.userId);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StMSupplementalDiscActualsPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StMSupplementalDiscActualsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualSales", getActualSales());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualRate", getActualRate());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    @Override
    public double getActualSales() {
        return _actualSales;
    }

    @Override
    public void setActualSales(double actualSales) {
        _actualSales = actualSales;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSales", double.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel,
                    actualSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualRate() {
        return _actualRate;
    }

    @Override
    public void setActualRate(double actualRate) {
        _actualRate = actualRate;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualRate", double.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel, actualRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel,
                    lastModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjectionSales() {
        return _actualProjectionSales;
    }

    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _actualProjectionSales = actualProjectionSales;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionSales",
                        double.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel,
                    actualProjectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjectionRate() {
        return _actualProjectionRate;
    }

    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _actualProjectionRate = actualProjectionRate;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionRate",
                        double.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel,
                    actualProjectionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(int sessionId) {
        _sessionId = sessionId;

        if (_stMSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _stMSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stMSupplementalDiscActualsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStMSupplementalDiscActualsRemoteModel() {
        return _stMSupplementalDiscActualsRemoteModel;
    }

    public void setStMSupplementalDiscActualsRemoteModel(
        BaseModel<?> stMSupplementalDiscActualsRemoteModel) {
        _stMSupplementalDiscActualsRemoteModel = stMSupplementalDiscActualsRemoteModel;
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

        Class<?> remoteModelClass = _stMSupplementalDiscActualsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stMSupplementalDiscActualsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StMSupplementalDiscActualsLocalServiceUtil.addStMSupplementalDiscActuals(this);
        } else {
            StMSupplementalDiscActualsLocalServiceUtil.updateStMSupplementalDiscActuals(this);
        }
    }

    @Override
    public StMSupplementalDiscActuals toEscapedModel() {
        return (StMSupplementalDiscActuals) ProxyUtil.newProxyInstance(StMSupplementalDiscActuals.class.getClassLoader(),
            new Class[] { StMSupplementalDiscActuals.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StMSupplementalDiscActualsClp clone = new StMSupplementalDiscActualsClp();

        clone.setActualSales(getActualSales());
        clone.setPeriodSid(getPeriodSid());
        clone.setActualRate(getActualRate());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setActualProjectionSales(getActualProjectionSales());
        clone.setActualProjectionRate(getActualProjectionRate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setSessionId(getSessionId());

        return clone;
    }

    @Override
    public int compareTo(StMSupplementalDiscActuals stMSupplementalDiscActuals) {
        StMSupplementalDiscActualsPK primaryKey = stMSupplementalDiscActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMSupplementalDiscActualsClp)) {
            return false;
        }

        StMSupplementalDiscActualsClp stMSupplementalDiscActuals = (StMSupplementalDiscActualsClp) obj;

        StMSupplementalDiscActualsPK primaryKey = stMSupplementalDiscActuals.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{actualSales=");
        sb.append(getActualSales());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", actualRate=");
        sb.append(getActualRate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", actualProjectionSales=");
        sb.append(getActualProjectionSales());
        sb.append(", actualProjectionRate=");
        sb.append(getActualProjectionRate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StMSupplementalDiscActuals");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualSales</column-name><column-value><![CDATA[");
        sb.append(getActualSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualRate</column-name><column-value><![CDATA[");
        sb.append(getActualRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionSales</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionRate</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
