package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNmPpaProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.StNmPpaProjectionPK;

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


public class StNmPpaProjectionClp extends BaseModelImpl<StNmPpaProjection>
    implements StNmPpaProjection {
    private Date _lastModifiedDate;
    private int _periodSid;
    private double _projectionRate;
    private int _projectionDetailsSid;
    private int _userId;
    private double _priceCap;
    private double _projectionDiscountUnits;
    private int _sessionId;
    private double _projectionDiscountDollar;
    private boolean _reset;
    private double _projectionSales;
    private double _projectionMap;
    private boolean _resetPriceCap;
    private BaseModel<?> _stNmPpaProjectionRemoteModel;

    public StNmPpaProjectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNmPpaProjection.class;
    }

    @Override
    public String getModelClassName() {
        return StNmPpaProjection.class.getName();
    }

    @Override
    public StNmPpaProjectionPK getPrimaryKey() {
        return new StNmPpaProjectionPK(_periodSid, _projectionDetailsSid,
            _userId, _sessionId);
    }

    @Override
    public void setPrimaryKey(StNmPpaProjectionPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNmPpaProjectionPK(_periodSid, _projectionDetailsSid,
            _userId, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNmPpaProjectionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("priceCap", getPriceCap());
        attributes.put("projectionDiscountUnits", getProjectionDiscountUnits());
        attributes.put("sessionId", getSessionId());
        attributes.put("projectionDiscountDollar", getProjectionDiscountDollar());
        attributes.put("reset", getReset());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("projectionMap", getProjectionMap());
        attributes.put("resetPriceCap", getResetPriceCap());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
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

        Double priceCap = (Double) attributes.get("priceCap");

        if (priceCap != null) {
            setPriceCap(priceCap);
        }

        Double projectionDiscountUnits = (Double) attributes.get(
                "projectionDiscountUnits");

        if (projectionDiscountUnits != null) {
            setProjectionDiscountUnits(projectionDiscountUnits);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double projectionDiscountDollar = (Double) attributes.get(
                "projectionDiscountDollar");

        if (projectionDiscountDollar != null) {
            setProjectionDiscountDollar(projectionDiscountDollar);
        }

        Boolean reset = (Boolean) attributes.get("reset");

        if (reset != null) {
            setReset(reset);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double projectionMap = (Double) attributes.get("projectionMap");

        if (projectionMap != null) {
            setProjectionMap(projectionMap);
        }

        Boolean resetPriceCap = (Boolean) attributes.get("resetPriceCap");

        if (resetPriceCap != null) {
            setResetPriceCap(resetPriceCap);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNmPpaProjectionRemoteModel, lastModifiedDate);
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

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stNmPpaProjectionRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionRate() {
        return _projectionRate;
    }

    @Override
    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_stNmPpaProjectionRemoteModel, projectionRate);
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

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stNmPpaProjectionRemoteModel,
                    projectionDetailsSid);
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

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNmPpaProjectionRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPriceCap() {
        return _priceCap;
    }

    @Override
    public void setPriceCap(double priceCap) {
        _priceCap = priceCap;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceCap", double.class);

                method.invoke(_stNmPpaProjectionRemoteModel, priceCap);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionDiscountUnits() {
        return _projectionDiscountUnits;
    }

    @Override
    public void setProjectionDiscountUnits(double projectionDiscountUnits) {
        _projectionDiscountUnits = projectionDiscountUnits;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDiscountUnits",
                        double.class);

                method.invoke(_stNmPpaProjectionRemoteModel,
                    projectionDiscountUnits);
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

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNmPpaProjectionRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionDiscountDollar() {
        return _projectionDiscountDollar;
    }

    @Override
    public void setProjectionDiscountDollar(double projectionDiscountDollar) {
        _projectionDiscountDollar = projectionDiscountDollar;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDiscountDollar",
                        double.class);

                method.invoke(_stNmPpaProjectionRemoteModel,
                    projectionDiscountDollar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getReset() {
        return _reset;
    }

    @Override
    public boolean isReset() {
        return _reset;
    }

    @Override
    public void setReset(boolean reset) {
        _reset = reset;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setReset", boolean.class);

                method.invoke(_stNmPpaProjectionRemoteModel, reset);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionSales() {
        return _projectionSales;
    }

    @Override
    public void setProjectionSales(double projectionSales) {
        _projectionSales = projectionSales;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_stNmPpaProjectionRemoteModel, projectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionMap() {
        return _projectionMap;
    }

    @Override
    public void setProjectionMap(double projectionMap) {
        _projectionMap = projectionMap;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMap", double.class);

                method.invoke(_stNmPpaProjectionRemoteModel, projectionMap);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getResetPriceCap() {
        return _resetPriceCap;
    }

    @Override
    public boolean isResetPriceCap() {
        return _resetPriceCap;
    }

    @Override
    public void setResetPriceCap(boolean resetPriceCap) {
        _resetPriceCap = resetPriceCap;

        if (_stNmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setResetPriceCap",
                        boolean.class);

                method.invoke(_stNmPpaProjectionRemoteModel, resetPriceCap);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNmPpaProjectionRemoteModel() {
        return _stNmPpaProjectionRemoteModel;
    }

    public void setStNmPpaProjectionRemoteModel(
        BaseModel<?> stNmPpaProjectionRemoteModel) {
        _stNmPpaProjectionRemoteModel = stNmPpaProjectionRemoteModel;
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

        Class<?> remoteModelClass = _stNmPpaProjectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNmPpaProjectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNmPpaProjectionLocalServiceUtil.addStNmPpaProjection(this);
        } else {
            StNmPpaProjectionLocalServiceUtil.updateStNmPpaProjection(this);
        }
    }

    @Override
    public StNmPpaProjection toEscapedModel() {
        return (StNmPpaProjection) ProxyUtil.newProxyInstance(StNmPpaProjection.class.getClassLoader(),
            new Class[] { StNmPpaProjection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNmPpaProjectionClp clone = new StNmPpaProjectionClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setPeriodSid(getPeriodSid());
        clone.setProjectionRate(getProjectionRate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setUserId(getUserId());
        clone.setPriceCap(getPriceCap());
        clone.setProjectionDiscountUnits(getProjectionDiscountUnits());
        clone.setSessionId(getSessionId());
        clone.setProjectionDiscountDollar(getProjectionDiscountDollar());
        clone.setReset(getReset());
        clone.setProjectionSales(getProjectionSales());
        clone.setProjectionMap(getProjectionMap());
        clone.setResetPriceCap(getResetPriceCap());

        return clone;
    }

    @Override
    public int compareTo(StNmPpaProjection stNmPpaProjection) {
        StNmPpaProjectionPK primaryKey = stNmPpaProjection.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmPpaProjectionClp)) {
            return false;
        }

        StNmPpaProjectionClp stNmPpaProjection = (StNmPpaProjectionClp) obj;

        StNmPpaProjectionPK primaryKey = stNmPpaProjection.getPrimaryKey();

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
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", priceCap=");
        sb.append(getPriceCap());
        sb.append(", projectionDiscountUnits=");
        sb.append(getProjectionDiscountUnits());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", projectionDiscountDollar=");
        sb.append(getProjectionDiscountDollar());
        sb.append(", reset=");
        sb.append(getReset());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append(", projectionMap=");
        sb.append(getProjectionMap());
        sb.append(", resetPriceCap=");
        sb.append(getResetPriceCap());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNmPpaProjection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionRate</column-name><column-value><![CDATA[");
        sb.append(getProjectionRate());
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
            "<column><column-name>priceCap</column-name><column-value><![CDATA[");
        sb.append(getPriceCap());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getProjectionDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getProjectionDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reset</column-name><column-value><![CDATA[");
        sb.append(getReset());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMap</column-name><column-value><![CDATA[");
        sb.append(getProjectionMap());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetPriceCap</column-name><column-value><![CDATA[");
        sb.append(getResetPriceCap());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
