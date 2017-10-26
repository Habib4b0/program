package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNmActualPpaLocalServiceUtil;
import com.stpl.app.service.persistence.StNmActualPpaPK;

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


public class StNmActualPpaClp extends BaseModelImpl<StNmActualPpa>
    implements StNmActualPpa {
    private Date _lastModifiedDate;
    private double _actualRate;
    private int _periodSid;
    private double _actualProjDiscountDollar;
    private double _actualProjectionSales;
    private int _projectionDetailsSid;
    private int _userId;
    private double _actualProjectionRate;
    private int _sessionId;
    private double _actualProjDiscountUnits;
    private double _actualDiscountDollar;
    private double _actualDiscountUnits;
    private double _actualSales;
    private BaseModel<?> _stNmActualPpaRemoteModel;

    public StNmActualPpaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNmActualPpa.class;
    }

    @Override
    public String getModelClassName() {
        return StNmActualPpa.class.getName();
    }

    @Override
    public StNmActualPpaPK getPrimaryKey() {
        return new StNmActualPpaPK(_periodSid, _projectionDetailsSid, _userId,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StNmActualPpaPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNmActualPpaPK(_periodSid, _projectionDetailsSid, _userId,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNmActualPpaPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualProjDiscountDollar", getActualProjDiscountDollar());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("sessionId", getSessionId());
        attributes.put("actualProjDiscountUnits", getActualProjDiscountUnits());
        attributes.put("actualDiscountDollar", getActualDiscountDollar());
        attributes.put("actualDiscountUnits", getActualDiscountUnits());
        attributes.put("actualSales", getActualSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualProjDiscountDollar = (Double) attributes.get(
                "actualProjDiscountDollar");

        if (actualProjDiscountDollar != null) {
            setActualProjDiscountDollar(actualProjDiscountDollar);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double actualProjDiscountUnits = (Double) attributes.get(
                "actualProjDiscountUnits");

        if (actualProjDiscountUnits != null) {
            setActualProjDiscountUnits(actualProjDiscountUnits);
        }

        Double actualDiscountDollar = (Double) attributes.get(
                "actualDiscountDollar");

        if (actualDiscountDollar != null) {
            setActualDiscountDollar(actualDiscountDollar);
        }

        Double actualDiscountUnits = (Double) attributes.get(
                "actualDiscountUnits");

        if (actualDiscountUnits != null) {
            setActualDiscountUnits(actualDiscountUnits);
        }

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNmActualPpaRemoteModel, lastModifiedDate);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualRate", double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualRate);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stNmActualPpaRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjDiscountDollar() {
        return _actualProjDiscountDollar;
    }

    @Override
    public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
        _actualProjDiscountDollar = actualProjDiscountDollar;

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjDiscountDollar",
                        double.class);

                method.invoke(_stNmActualPpaRemoteModel,
                    actualProjDiscountDollar);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionSales",
                        double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualProjectionSales);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stNmActualPpaRemoteModel, projectionDetailsSid);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNmActualPpaRemoteModel, userId);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionRate",
                        double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualProjectionRate);
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

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNmActualPpaRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjDiscountUnits() {
        return _actualProjDiscountUnits;
    }

    @Override
    public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
        _actualProjDiscountUnits = actualProjDiscountUnits;

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjDiscountUnits",
                        double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualProjDiscountUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualDiscountDollar() {
        return _actualDiscountDollar;
    }

    @Override
    public void setActualDiscountDollar(double actualDiscountDollar) {
        _actualDiscountDollar = actualDiscountDollar;

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscountDollar",
                        double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualDiscountDollar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualDiscountUnits() {
        return _actualDiscountUnits;
    }

    @Override
    public void setActualDiscountUnits(double actualDiscountUnits) {
        _actualDiscountUnits = actualDiscountUnits;

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscountUnits",
                        double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualDiscountUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualSales() {
        return _actualSales;
    }

    @Override
    public void setActualSales(double actualSales) {
        _actualSales = actualSales;

        if (_stNmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _stNmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSales", double.class);

                method.invoke(_stNmActualPpaRemoteModel, actualSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNmActualPpaRemoteModel() {
        return _stNmActualPpaRemoteModel;
    }

    public void setStNmActualPpaRemoteModel(
        BaseModel<?> stNmActualPpaRemoteModel) {
        _stNmActualPpaRemoteModel = stNmActualPpaRemoteModel;
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

        Class<?> remoteModelClass = _stNmActualPpaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNmActualPpaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNmActualPpaLocalServiceUtil.addStNmActualPpa(this);
        } else {
            StNmActualPpaLocalServiceUtil.updateStNmActualPpa(this);
        }
    }

    @Override
    public StNmActualPpa toEscapedModel() {
        return (StNmActualPpa) ProxyUtil.newProxyInstance(StNmActualPpa.class.getClassLoader(),
            new Class[] { StNmActualPpa.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNmActualPpaClp clone = new StNmActualPpaClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setActualRate(getActualRate());
        clone.setPeriodSid(getPeriodSid());
        clone.setActualProjDiscountDollar(getActualProjDiscountDollar());
        clone.setActualProjectionSales(getActualProjectionSales());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setUserId(getUserId());
        clone.setActualProjectionRate(getActualProjectionRate());
        clone.setSessionId(getSessionId());
        clone.setActualProjDiscountUnits(getActualProjDiscountUnits());
        clone.setActualDiscountDollar(getActualDiscountDollar());
        clone.setActualDiscountUnits(getActualDiscountUnits());
        clone.setActualSales(getActualSales());

        return clone;
    }

    @Override
    public int compareTo(StNmActualPpa stNmActualPpa) {
        StNmActualPpaPK primaryKey = stNmActualPpa.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmActualPpaClp)) {
            return false;
        }

        StNmActualPpaClp stNmActualPpa = (StNmActualPpaClp) obj;

        StNmActualPpaPK primaryKey = stNmActualPpa.getPrimaryKey();

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
        StringBundler sb = new StringBundler(27);

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", actualRate=");
        sb.append(getActualRate());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", actualProjDiscountDollar=");
        sb.append(getActualProjDiscountDollar());
        sb.append(", actualProjectionSales=");
        sb.append(getActualProjectionSales());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", actualProjectionRate=");
        sb.append(getActualProjectionRate());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", actualProjDiscountUnits=");
        sb.append(getActualProjDiscountUnits());
        sb.append(", actualDiscountDollar=");
        sb.append(getActualDiscountDollar());
        sb.append(", actualDiscountUnits=");
        sb.append(getActualDiscountUnits());
        sb.append(", actualSales=");
        sb.append(getActualSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNmActualPpa");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualRate</column-name><column-value><![CDATA[");
        sb.append(getActualRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getActualProjDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionSales</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionRate</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getActualProjDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSales</column-name><column-value><![CDATA[");
        sb.append(getActualSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
