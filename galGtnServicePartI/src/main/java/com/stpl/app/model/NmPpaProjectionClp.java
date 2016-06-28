package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmPpaProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmPpaProjectionPK;

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


public class NmPpaProjectionClp extends BaseModelImpl<NmPpaProjection>
    implements NmPpaProjection {
    private int _periodSid;
    private double _projectionRate;
    private int _projectionDetailsSid;
    private double _priceCap;
    private double _projectionDiscountUnits;
    private double _projectionDiscountDollar;
    private boolean _reset;
    private double _projectionSales;
    private double _projectionMap;
    private boolean _resetPriceCap;
    private BaseModel<?> _nmPpaProjectionRemoteModel;

    public NmPpaProjectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmPpaProjection.class;
    }

    @Override
    public String getModelClassName() {
        return NmPpaProjection.class.getName();
    }

    @Override
    public NmPpaProjectionPK getPrimaryKey() {
        return new NmPpaProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(NmPpaProjectionPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NmPpaProjectionPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NmPpaProjectionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("priceCap", getPriceCap());
        attributes.put("projectionDiscountUnits", getProjectionDiscountUnits());
        attributes.put("projectionDiscountDollar", getProjectionDiscountDollar());
        attributes.put("reset", getReset());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("projectionMap", getProjectionMap());
        attributes.put("resetPriceCap", getResetPriceCap());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        Double priceCap = (Double) attributes.get("priceCap");

        if (priceCap != null) {
            setPriceCap(priceCap);
        }

        Double projectionDiscountUnits = (Double) attributes.get(
                "projectionDiscountUnits");

        if (projectionDiscountUnits != null) {
            setProjectionDiscountUnits(projectionDiscountUnits);
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
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_nmPpaProjectionRemoteModel, periodSid);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionRate",
                        double.class);

                method.invoke(_nmPpaProjectionRemoteModel, projectionRate);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmPpaProjectionRemoteModel, projectionDetailsSid);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceCap", double.class);

                method.invoke(_nmPpaProjectionRemoteModel, priceCap);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDiscountUnits",
                        double.class);

                method.invoke(_nmPpaProjectionRemoteModel,
                    projectionDiscountUnits);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDiscountDollar",
                        double.class);

                method.invoke(_nmPpaProjectionRemoteModel,
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setReset", boolean.class);

                method.invoke(_nmPpaProjectionRemoteModel, reset);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionSales",
                        double.class);

                method.invoke(_nmPpaProjectionRemoteModel, projectionSales);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMap", double.class);

                method.invoke(_nmPpaProjectionRemoteModel, projectionMap);
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

        if (_nmPpaProjectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmPpaProjectionRemoteModel.getClass();

                Method method = clazz.getMethod("setResetPriceCap",
                        boolean.class);

                method.invoke(_nmPpaProjectionRemoteModel, resetPriceCap);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmPpaProjectionRemoteModel() {
        return _nmPpaProjectionRemoteModel;
    }

    public void setNmPpaProjectionRemoteModel(
        BaseModel<?> nmPpaProjectionRemoteModel) {
        _nmPpaProjectionRemoteModel = nmPpaProjectionRemoteModel;
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

        Class<?> remoteModelClass = _nmPpaProjectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmPpaProjectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmPpaProjectionLocalServiceUtil.addNmPpaProjection(this);
        } else {
            NmPpaProjectionLocalServiceUtil.updateNmPpaProjection(this);
        }
    }

    @Override
    public NmPpaProjection toEscapedModel() {
        return (NmPpaProjection) ProxyUtil.newProxyInstance(NmPpaProjection.class.getClassLoader(),
            new Class[] { NmPpaProjection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmPpaProjectionClp clone = new NmPpaProjectionClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setProjectionRate(getProjectionRate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setPriceCap(getPriceCap());
        clone.setProjectionDiscountUnits(getProjectionDiscountUnits());
        clone.setProjectionDiscountDollar(getProjectionDiscountDollar());
        clone.setReset(getReset());
        clone.setProjectionSales(getProjectionSales());
        clone.setProjectionMap(getProjectionMap());
        clone.setResetPriceCap(getResetPriceCap());

        return clone;
    }

    @Override
    public int compareTo(NmPpaProjection nmPpaProjection) {
        NmPpaProjectionPK primaryKey = nmPpaProjection.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmPpaProjectionClp)) {
            return false;
        }

        NmPpaProjectionClp nmPpaProjection = (NmPpaProjectionClp) obj;

        NmPpaProjectionPK primaryKey = nmPpaProjection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{periodSid=");
        sb.append(getPeriodSid());
        sb.append(", projectionRate=");
        sb.append(getProjectionRate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", priceCap=");
        sb.append(getPriceCap());
        sb.append(", projectionDiscountUnits=");
        sb.append(getProjectionDiscountUnits());
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
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmPpaProjection");
        sb.append("</model-name>");

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
            "<column><column-name>priceCap</column-name><column-value><![CDATA[");
        sb.append(getPriceCap());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getProjectionDiscountUnits());
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
