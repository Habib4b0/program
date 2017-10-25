package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNmDiscountProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.StNmDiscountProjectionPK;

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


public class StNmDiscountProjectionClp extends BaseModelImpl<StNmDiscountProjection>
    implements StNmDiscountProjection {
    private double _projectionRate;
    private double _adjustmentValue;
    private int _userId;
    private Date _lastModifiedDate;
    private double _discountRate;
    private double _projectionSales;
    private String _adjustmentType;
    private String _adjustmentBasis;
    private int _periodSid;
    private String _adjustmentMethodology;
    private int _projectionDetailsSid;
    private int _rsModelSid;
    private int _sessionId;
    private BaseModel<?> _stNmDiscountProjectionRemoteModel;

    public StNmDiscountProjectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNmDiscountProjection.class;
    }

    @Override
    public String getModelClassName() {
        return StNmDiscountProjection.class.getName();
    }

    @Override
    public StNmDiscountProjectionPK getPrimaryKey() {
        return new StNmDiscountProjectionPK(_userId, _periodSid,
            _projectionDetailsSid, _rsModelSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StNmDiscountProjectionPK primaryKey) {
        setUserId(primaryKey.userId);
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setRsModelSid(primaryKey.rsModelSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNmDiscountProjectionPK(_userId, _periodSid,
            _projectionDetailsSid, _rsModelSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNmDiscountProjectionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionRate", getProjectionRate());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("discountRate", getDiscountRate());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Double adjustmentValue = (Double) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double discountRate = (Double) attributes.get("discountRate");

        if (discountRate != null) {
            setDiscountRate(discountRate);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentMethodology = (String) attributes.get(
                "adjustmentMethodology");

        if (adjustmentMethodology != null) {
            setAdjustmentMethodology(adjustmentMethodology);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    @Override
    public double getProjectionRate() {
        return _projectionRate;
    }

    @Override
    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, projectionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAdjustmentValue() {
        return _adjustmentValue;
    }

    @Override
    public void setAdjustmentValue(double adjustmentValue) {
        _adjustmentValue = adjustmentValue;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentValue",
                        double.class);

                method.invoke(_stNmDiscountProjectionRemoteModel,
                    adjustmentValue);
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

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, userId);
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

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNmDiscountProjectionRemoteModel,
                    lastModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDiscountRate() {
        return _discountRate;
    }

    @Override
    public void setDiscountRate(double discountRate) {
        _discountRate = discountRate;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate", double.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, discountRate);
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

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_stNmDiscountProjectionRemoteModel,
                    projectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentType() {
        return _adjustmentType;
    }

    @Override
    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, adjustmentType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentBasis() {
        return _adjustmentBasis;
    }

    @Override
    public void setAdjustmentBasis(String adjustmentBasis) {
        _adjustmentBasis = adjustmentBasis;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentBasis",
                        String.class);

                method.invoke(_stNmDiscountProjectionRemoteModel,
                    adjustmentBasis);
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

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentMethodology() {
        return _adjustmentMethodology;
    }

    @Override
    public void setAdjustmentMethodology(String adjustmentMethodology) {
        _adjustmentMethodology = adjustmentMethodology;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentMethodology",
                        String.class);

                method.invoke(_stNmDiscountProjectionRemoteModel,
                    adjustmentMethodology);
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

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stNmDiscountProjectionRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, rsModelSid);
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

        if (_stNmDiscountProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNmDiscountProjectionRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNmDiscountProjectionRemoteModel() {
        return _stNmDiscountProjectionRemoteModel;
    }

    public void setStNmDiscountProjectionRemoteModel(
        BaseModel<?> stNmDiscountProjectionRemoteModel) {
        _stNmDiscountProjectionRemoteModel = stNmDiscountProjectionRemoteModel;
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

        Class<?> remoteModelClass = _stNmDiscountProjectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNmDiscountProjectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNmDiscountProjectionLocalServiceUtil.addStNmDiscountProjection(this);
        } else {
            StNmDiscountProjectionLocalServiceUtil.updateStNmDiscountProjection(this);
        }
    }

    @Override
    public StNmDiscountProjection toEscapedModel() {
        return (StNmDiscountProjection) ProxyUtil.newProxyInstance(StNmDiscountProjection.class.getClassLoader(),
            new Class[] { StNmDiscountProjection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNmDiscountProjectionClp clone = new StNmDiscountProjectionClp();

        clone.setProjectionRate(getProjectionRate());
        clone.setAdjustmentValue(getAdjustmentValue());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setDiscountRate(getDiscountRate());
        clone.setProjectionSales(getProjectionSales());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setAdjustmentBasis(getAdjustmentBasis());
        clone.setPeriodSid(getPeriodSid());
        clone.setAdjustmentMethodology(getAdjustmentMethodology());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setSessionId(getSessionId());

        return clone;
    }

    @Override
    public int compareTo(StNmDiscountProjection stNmDiscountProjection) {
        StNmDiscountProjectionPK primaryKey = stNmDiscountProjection.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmDiscountProjectionClp)) {
            return false;
        }

        StNmDiscountProjectionClp stNmDiscountProjection = (StNmDiscountProjectionClp) obj;

        StNmDiscountProjectionPK primaryKey = stNmDiscountProjection.getPrimaryKey();

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

        sb.append("{projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", adjustmentValue=");
        sb.append(getAdjustmentValue());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", discountRate=");
        sb.append(getDiscountRate());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", adjustmentBasis=");
        sb.append(getAdjustmentBasis());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", adjustmentMethodology=");
        sb.append(getAdjustmentMethodology());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNmDiscountProjection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>projectionRate</column-name><column-value><![CDATA[");
        sb.append(getProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentValue</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentValue());
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
            "<column><column-name>discountRate</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentBasis</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentMethodology</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
