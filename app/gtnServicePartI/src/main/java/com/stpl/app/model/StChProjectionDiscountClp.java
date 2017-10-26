package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StChProjectionDiscountLocalServiceUtil;
import com.stpl.app.service.persistence.StChProjectionDiscountPK;

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


public class StChProjectionDiscountClp extends BaseModelImpl<StChProjectionDiscount>
    implements StChProjectionDiscount {
    private Date _lastModifiedDate;
    private String _adjustmentMethodology;
    private double _productGrowth;
    private double _projectionRate;
    private int _projectionDetailsSid;
    private int _userId;
    private double _accountGrowth;
    private double _discountAmount;
    private double _discountRate;
    private int _periodSid;
    private String _adjustmentBasis;
    private int _sessionId;
    private double _adjustmentValue;
    private String _adjustmentType;
    private int _rsModelSid;
    private double _projectionSales;
    private BaseModel<?> _stChProjectionDiscountRemoteModel;

    public StChProjectionDiscountClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StChProjectionDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return StChProjectionDiscount.class.getName();
    }

    @Override
    public StChProjectionDiscountPK getPrimaryKey() {
        return new StChProjectionDiscountPK(_projectionDetailsSid, _userId,
            _periodSid, _sessionId, _rsModelSid);
    }

    @Override
    public void setPrimaryKey(StChProjectionDiscountPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setPeriodSid(primaryKey.periodSid);
        setSessionId(primaryKey.sessionId);
        setRsModelSid(primaryKey.rsModelSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StChProjectionDiscountPK(_projectionDetailsSid, _userId,
            _periodSid, _sessionId, _rsModelSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StChProjectionDiscountPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("productGrowth", getProductGrowth());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("accountGrowth", getAccountGrowth());
        attributes.put("discountAmount", getDiscountAmount());
        attributes.put("discountRate", getDiscountRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("sessionId", getSessionId());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("projectionSales", getProjectionSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        String adjustmentMethodology = (String) attributes.get(
                "adjustmentMethodology");

        if (adjustmentMethodology != null) {
            setAdjustmentMethodology(adjustmentMethodology);
        }

        Double productGrowth = (Double) attributes.get("productGrowth");

        if (productGrowth != null) {
            setProductGrowth(productGrowth);
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

        Double accountGrowth = (Double) attributes.get("accountGrowth");

        if (accountGrowth != null) {
            setAccountGrowth(accountGrowth);
        }

        Double discountAmount = (Double) attributes.get("discountAmount");

        if (discountAmount != null) {
            setDiscountAmount(discountAmount);
        }

        Double discountRate = (Double) attributes.get("discountRate");

        if (discountRate != null) {
            setDiscountRate(discountRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double adjustmentValue = (Double) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stChProjectionDiscountRemoteModel,
                    lastModifiedDate);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentMethodology",
                        String.class);

                method.invoke(_stChProjectionDiscountRemoteModel,
                    adjustmentMethodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProductGrowth() {
        return _productGrowth;
    }

    @Override
    public void setProductGrowth(double productGrowth) {
        _productGrowth = productGrowth;

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setProductGrowth", double.class);

                method.invoke(_stChProjectionDiscountRemoteModel, productGrowth);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_stChProjectionDiscountRemoteModel, projectionRate);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stChProjectionDiscountRemoteModel,
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stChProjectionDiscountRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAccountGrowth() {
        return _accountGrowth;
    }

    @Override
    public void setAccountGrowth(double accountGrowth) {
        _accountGrowth = accountGrowth;

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountGrowth", double.class);

                method.invoke(_stChProjectionDiscountRemoteModel, accountGrowth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDiscountAmount() {
        return _discountAmount;
    }

    @Override
    public void setDiscountAmount(double discountAmount) {
        _discountAmount = discountAmount;

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountAmount",
                        double.class);

                method.invoke(_stChProjectionDiscountRemoteModel, discountAmount);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountRate", double.class);

                method.invoke(_stChProjectionDiscountRemoteModel, discountRate);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stChProjectionDiscountRemoteModel, periodSid);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentBasis",
                        String.class);

                method.invoke(_stChProjectionDiscountRemoteModel,
                    adjustmentBasis);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stChProjectionDiscountRemoteModel, sessionId);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentValue",
                        double.class);

                method.invoke(_stChProjectionDiscountRemoteModel,
                    adjustmentValue);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_stChProjectionDiscountRemoteModel, adjustmentType);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stChProjectionDiscountRemoteModel, rsModelSid);
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

        if (_stChProjectionDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _stChProjectionDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_stChProjectionDiscountRemoteModel,
                    projectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStChProjectionDiscountRemoteModel() {
        return _stChProjectionDiscountRemoteModel;
    }

    public void setStChProjectionDiscountRemoteModel(
        BaseModel<?> stChProjectionDiscountRemoteModel) {
        _stChProjectionDiscountRemoteModel = stChProjectionDiscountRemoteModel;
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

        Class<?> remoteModelClass = _stChProjectionDiscountRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stChProjectionDiscountRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StChProjectionDiscountLocalServiceUtil.addStChProjectionDiscount(this);
        } else {
            StChProjectionDiscountLocalServiceUtil.updateStChProjectionDiscount(this);
        }
    }

    @Override
    public StChProjectionDiscount toEscapedModel() {
        return (StChProjectionDiscount) ProxyUtil.newProxyInstance(StChProjectionDiscount.class.getClassLoader(),
            new Class[] { StChProjectionDiscount.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StChProjectionDiscountClp clone = new StChProjectionDiscountClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setAdjustmentMethodology(getAdjustmentMethodology());
        clone.setProductGrowth(getProductGrowth());
        clone.setProjectionRate(getProjectionRate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setUserId(getUserId());
        clone.setAccountGrowth(getAccountGrowth());
        clone.setDiscountAmount(getDiscountAmount());
        clone.setDiscountRate(getDiscountRate());
        clone.setPeriodSid(getPeriodSid());
        clone.setAdjustmentBasis(getAdjustmentBasis());
        clone.setSessionId(getSessionId());
        clone.setAdjustmentValue(getAdjustmentValue());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setRsModelSid(getRsModelSid());
        clone.setProjectionSales(getProjectionSales());

        return clone;
    }

    @Override
    public int compareTo(StChProjectionDiscount stChProjectionDiscount) {
        StChProjectionDiscountPK primaryKey = stChProjectionDiscount.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChProjectionDiscountClp)) {
            return false;
        }

        StChProjectionDiscountClp stChProjectionDiscount = (StChProjectionDiscountClp) obj;

        StChProjectionDiscountPK primaryKey = stChProjectionDiscount.getPrimaryKey();

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
        StringBundler sb = new StringBundler(33);

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", adjustmentMethodology=");
        sb.append(getAdjustmentMethodology());
        sb.append(", productGrowth=");
        sb.append(getProductGrowth());
        sb.append(", projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", accountGrowth=");
        sb.append(getAccountGrowth());
        sb.append(", discountAmount=");
        sb.append(getDiscountAmount());
        sb.append(", discountRate=");
        sb.append(getDiscountRate());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", adjustmentBasis=");
        sb.append(getAdjustmentBasis());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", adjustmentValue=");
        sb.append(getAdjustmentValue());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", projectionSales=");
        sb.append(getProjectionSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StChProjectionDiscount");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentMethodology</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productGrowth</column-name><column-value><![CDATA[");
        sb.append(getProductGrowth());
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
            "<column><column-name>accountGrowth</column-name><column-value><![CDATA[");
        sb.append(getAccountGrowth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountAmount</column-name><column-value><![CDATA[");
        sb.append(getDiscountAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountRate</column-name><column-value><![CDATA[");
        sb.append(getDiscountRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentBasis</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentValue</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionSales</column-name><column-value><![CDATA[");
        sb.append(getProjectionSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
