package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class AcBaseRateBaselineDetailsClp extends BaseModelImpl<AcBaseRateBaselineDetails>
    implements AcBaseRateBaselineDetails {
    private double _periodValue;
    private int _periodSid;
    private boolean _paymentsPeriod;
    private int _acBrMethodologyDetailsSid;
    private boolean _salesPeriod;
    private BaseModel<?> _acBaseRateBaselineDetailsRemoteModel;

    public AcBaseRateBaselineDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AcBaseRateBaselineDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AcBaseRateBaselineDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _acBrMethodologyDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAcBrMethodologyDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _acBrMethodologyDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodValue", getPeriodValue());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("paymentsPeriod", getPaymentsPeriod());
        attributes.put("acBrMethodologyDetailsSid",
            getAcBrMethodologyDetailsSid());
        attributes.put("salesPeriod", getSalesPeriod());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double periodValue = (Double) attributes.get("periodValue");

        if (periodValue != null) {
            setPeriodValue(periodValue);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Boolean paymentsPeriod = (Boolean) attributes.get("paymentsPeriod");

        if (paymentsPeriod != null) {
            setPaymentsPeriod(paymentsPeriod);
        }

        Integer acBrMethodologyDetailsSid = (Integer) attributes.get(
                "acBrMethodologyDetailsSid");

        if (acBrMethodologyDetailsSid != null) {
            setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
        }

        Boolean salesPeriod = (Boolean) attributes.get("salesPeriod");

        if (salesPeriod != null) {
            setSalesPeriod(salesPeriod);
        }
    }

    @Override
    public double getPeriodValue() {
        return _periodValue;
    }

    @Override
    public void setPeriodValue(double periodValue) {
        _periodValue = periodValue;

        if (_acBaseRateBaselineDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBaseRateBaselineDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodValue", double.class);

                method.invoke(_acBaseRateBaselineDetailsRemoteModel, periodValue);
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

        if (_acBaseRateBaselineDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBaseRateBaselineDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_acBaseRateBaselineDetailsRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getPaymentsPeriod() {
        return _paymentsPeriod;
    }

    @Override
    public boolean isPaymentsPeriod() {
        return _paymentsPeriod;
    }

    @Override
    public void setPaymentsPeriod(boolean paymentsPeriod) {
        _paymentsPeriod = paymentsPeriod;

        if (_acBaseRateBaselineDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBaseRateBaselineDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentsPeriod",
                        boolean.class);

                method.invoke(_acBaseRateBaselineDetailsRemoteModel,
                    paymentsPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAcBrMethodologyDetailsSid() {
        return _acBrMethodologyDetailsSid;
    }

    @Override
    public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
        _acBrMethodologyDetailsSid = acBrMethodologyDetailsSid;

        if (_acBaseRateBaselineDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBaseRateBaselineDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAcBrMethodologyDetailsSid",
                        int.class);

                method.invoke(_acBaseRateBaselineDetailsRemoteModel,
                    acBrMethodologyDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getSalesPeriod() {
        return _salesPeriod;
    }

    @Override
    public boolean isSalesPeriod() {
        return _salesPeriod;
    }

    @Override
    public void setSalesPeriod(boolean salesPeriod) {
        _salesPeriod = salesPeriod;

        if (_acBaseRateBaselineDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBaseRateBaselineDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesPeriod", boolean.class);

                method.invoke(_acBaseRateBaselineDetailsRemoteModel, salesPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAcBaseRateBaselineDetailsRemoteModel() {
        return _acBaseRateBaselineDetailsRemoteModel;
    }

    public void setAcBaseRateBaselineDetailsRemoteModel(
        BaseModel<?> acBaseRateBaselineDetailsRemoteModel) {
        _acBaseRateBaselineDetailsRemoteModel = acBaseRateBaselineDetailsRemoteModel;
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

        Class<?> remoteModelClass = _acBaseRateBaselineDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_acBaseRateBaselineDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AcBaseRateBaselineDetailsLocalServiceUtil.addAcBaseRateBaselineDetails(this);
        } else {
            AcBaseRateBaselineDetailsLocalServiceUtil.updateAcBaseRateBaselineDetails(this);
        }
    }

    @Override
    public AcBaseRateBaselineDetails toEscapedModel() {
        return (AcBaseRateBaselineDetails) ProxyUtil.newProxyInstance(AcBaseRateBaselineDetails.class.getClassLoader(),
            new Class[] { AcBaseRateBaselineDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AcBaseRateBaselineDetailsClp clone = new AcBaseRateBaselineDetailsClp();

        clone.setPeriodValue(getPeriodValue());
        clone.setPeriodSid(getPeriodSid());
        clone.setPaymentsPeriod(getPaymentsPeriod());
        clone.setAcBrMethodologyDetailsSid(getAcBrMethodologyDetailsSid());
        clone.setSalesPeriod(getSalesPeriod());

        return clone;
    }

    @Override
    public int compareTo(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        int primaryKey = acBaseRateBaselineDetails.getPrimaryKey();

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

        if (!(obj instanceof AcBaseRateBaselineDetailsClp)) {
            return false;
        }

        AcBaseRateBaselineDetailsClp acBaseRateBaselineDetails = (AcBaseRateBaselineDetailsClp) obj;

        int primaryKey = acBaseRateBaselineDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{periodValue=");
        sb.append(getPeriodValue());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", paymentsPeriod=");
        sb.append(getPaymentsPeriod());
        sb.append(", acBrMethodologyDetailsSid=");
        sb.append(getAcBrMethodologyDetailsSid());
        sb.append(", salesPeriod=");
        sb.append(getSalesPeriod());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AcBaseRateBaselineDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodValue</column-name><column-value><![CDATA[");
        sb.append(getPeriodValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentsPeriod</column-name><column-value><![CDATA[");
        sb.append(getPaymentsPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acBrMethodologyDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getAcBrMethodologyDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesPeriod</column-name><column-value><![CDATA[");
        sb.append(getSalesPeriod());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
