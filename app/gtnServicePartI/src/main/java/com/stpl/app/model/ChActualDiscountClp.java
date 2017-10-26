package com.stpl.app.model;

import com.stpl.app.service.ChActualDiscountLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.persistence.ChActualDiscountPK;

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


public class ChActualDiscountClp extends BaseModelImpl<ChActualDiscount>
    implements ChActualDiscount {
    private double _actualRate;
    private int _periodSid;
    private int _projectionDetailsSid;
    private int _rsModelSid;
    private double _actualSales;
    private BaseModel<?> _chActualDiscountRemoteModel;

    public ChActualDiscountClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ChActualDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return ChActualDiscount.class.getName();
    }

    @Override
    public ChActualDiscountPK getPrimaryKey() {
        return new ChActualDiscountPK(_periodSid, _projectionDetailsSid,
            _rsModelSid);
    }

    @Override
    public void setPrimaryKey(ChActualDiscountPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setRsModelSid(primaryKey.rsModelSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ChActualDiscountPK(_periodSid, _projectionDetailsSid,
            _rsModelSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ChActualDiscountPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("actualSales", getActualSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
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

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    @Override
    public double getActualRate() {
        return _actualRate;
    }

    @Override
    public void setActualRate(double actualRate) {
        _actualRate = actualRate;

        if (_chActualDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _chActualDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setActualRate", double.class);

                method.invoke(_chActualDiscountRemoteModel, actualRate);
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

        if (_chActualDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _chActualDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_chActualDiscountRemoteModel, periodSid);
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

        if (_chActualDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _chActualDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_chActualDiscountRemoteModel, projectionDetailsSid);
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

        if (_chActualDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _chActualDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_chActualDiscountRemoteModel, rsModelSid);
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

        if (_chActualDiscountRemoteModel != null) {
            try {
                Class<?> clazz = _chActualDiscountRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSales", double.class);

                method.invoke(_chActualDiscountRemoteModel, actualSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getChActualDiscountRemoteModel() {
        return _chActualDiscountRemoteModel;
    }

    public void setChActualDiscountRemoteModel(
        BaseModel<?> chActualDiscountRemoteModel) {
        _chActualDiscountRemoteModel = chActualDiscountRemoteModel;
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

        Class<?> remoteModelClass = _chActualDiscountRemoteModel.getClass();

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

        Object returnValue = method.invoke(_chActualDiscountRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ChActualDiscountLocalServiceUtil.addChActualDiscount(this);
        } else {
            ChActualDiscountLocalServiceUtil.updateChActualDiscount(this);
        }
    }

    @Override
    public ChActualDiscount toEscapedModel() {
        return (ChActualDiscount) ProxyUtil.newProxyInstance(ChActualDiscount.class.getClassLoader(),
            new Class[] { ChActualDiscount.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ChActualDiscountClp clone = new ChActualDiscountClp();

        clone.setActualRate(getActualRate());
        clone.setPeriodSid(getPeriodSid());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setActualSales(getActualSales());

        return clone;
    }

    @Override
    public int compareTo(ChActualDiscount chActualDiscount) {
        ChActualDiscountPK primaryKey = chActualDiscount.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChActualDiscountClp)) {
            return false;
        }

        ChActualDiscountClp chActualDiscount = (ChActualDiscountClp) obj;

        ChActualDiscountPK primaryKey = chActualDiscount.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{actualRate=");
        sb.append(getActualRate());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", actualSales=");
        sb.append(getActualSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ChActualDiscount");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualRate</column-name><column-value><![CDATA[");
        sb.append(getActualRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
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
            "<column><column-name>actualSales</column-name><column-value><![CDATA[");
        sb.append(getActualSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
